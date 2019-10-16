package ch.beerpro.presentation.details;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ch.beerpro.data.repositories.BeersRepository;
import ch.beerpro.data.repositories.CurrentUser;
import ch.beerpro.data.repositories.LikesRepository;
import ch.beerpro.data.repositories.RatingsRepository;
import ch.beerpro.data.repositories.WishlistRepository;
import ch.beerpro.domain.models.Beer;
import ch.beerpro.domain.models.Price;
import ch.beerpro.domain.models.Rating;
import ch.beerpro.domain.models.Wish;
import ch.beerpro.presentation.utils.EntityClassSnapshotParser;

public class DetailsViewModel extends ViewModel implements CurrentUser {

    private final MutableLiveData<String> beerId = new MutableLiveData<>();
    private final MutableLiveData<String> currentUserId = new MutableLiveData<>();
    private final LiveData<Beer> beer;
    private final LiveData<List<Rating>> ratings;
    private final LiveData<List<Rating>> myRatings;
    private final LiveData<Wish> wish;

    private final LikesRepository likesRepository;
    private final WishlistRepository wishlistRepository;
    private final RatingsRepository ratingsRepository;
    private EntityClassSnapshotParser<Rating> parser = new EntityClassSnapshotParser<>(Rating.class);


    public DetailsViewModel() {
        // TODO We should really be injecting these!
        BeersRepository beersRepository = new BeersRepository();
        ratingsRepository = new RatingsRepository();
        likesRepository = new LikesRepository();
        wishlistRepository = new WishlistRepository();

//        MutableLiveData<String> currentUserId = new MutableLiveData<>();
        beer = beersRepository.getBeer(beerId);
        wish = wishlistRepository.getMyWishForBeer(currentUserId, getBeer());
        ratings = ratingsRepository.getRatingsForBeer(beerId);
        currentUserId.setValue(getCurrentUser().getUid());
        myRatings = ratingsRepository.getMyRatings(currentUserId);
    }

    public LiveData<Beer> getBeer() {
        return beer;
    }

    public LiveData<Wish> getWish() {
        return wish;
    }

    public LiveData<List<Rating>> getRatings() {
        return ratings;
    }

    public LiveData<List<Rating>> getMyRatings() { return myRatings; }

    public void setBeerId(String beerId) {
        this.beerId.setValue(beerId);
    }

    public void toggleLike(Rating rating) {
        likesRepository.toggleLike(rating);
    }

    public Task<Void> toggleItemInWishlist(String itemId) {
        return wishlistRepository.toggleUserWishlistItem(getCurrentUser().getUid(), itemId);
    }

    public Task<Rating> savePrice(String beerId, float price) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;

        Price newPrice = new Price(null, beerId, user.getUid(), user.getDisplayName(), price, new Date());
        CollectionReference prices = FirebaseFirestore.getInstance().collection(Price.COLLECTION);
        return prices.add(newPrice)
                .continueWithTask(task -> {
                    if (task.isSuccessful()) {
                        return task.getResult().get();
                    } else {
                        throw task.getException();
                    }
                }).continueWithTask(task -> {

                    if (task.isSuccessful()) {
                        return Tasks.forResult(parser.parseSnapshot(task.getResult()));
                    } else {
                        throw task.getException();
                    }
                });
    }
}