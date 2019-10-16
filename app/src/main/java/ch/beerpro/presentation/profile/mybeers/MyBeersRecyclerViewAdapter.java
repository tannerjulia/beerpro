package ch.beerpro.presentation.profile.mybeers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseUser;

import java.text.DateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import ch.beerpro.GlideApp;
import ch.beerpro.R;
import ch.beerpro.domain.models.Beer;
import ch.beerpro.domain.models.MyBeer;
import ch.beerpro.domain.models.MyBeerFromPrice;
import ch.beerpro.domain.models.MyBeerFromRating;
import ch.beerpro.domain.models.MyBeerFromWishlist;
import ch.beerpro.presentation.utils.DrawableHelpers;


public class MyBeersRecyclerViewAdapter extends ListAdapter<MyBeer, MyBeersRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "MyBeersRecyclerViewAdap";

    private static final DiffUtil.ItemCallback<MyBeer> DIFF_CALLBACK = new DiffUtil.ItemCallback<MyBeer>() {
        @Override
        public boolean areItemsTheSame(@NonNull MyBeer oldItem, @NonNull MyBeer newItem) {
            return oldItem.getBeerId().equals(newItem.getBeerId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull MyBeer oldItem, @NonNull MyBeer newItem) {
            return oldItem.equals(newItem);
        }
    };

    private final OnMyBeerItemInteractionListener listener;
    private FirebaseUser user;

    public MyBeersRecyclerViewAdapter(OnMyBeerItemInteractionListener listener, FirebaseUser user) {
        super(DIFF_CALLBACK);
        this.listener = listener;
        this.user = user;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_my_beers_listentry, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        MyBeer entry = getItem(position);
        holder.bind(entry, listener);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.manufacturer)
        TextView manufacturer;

        @BindView(R.id.category)
        TextView category;

        @BindView(R.id.photo)
        ImageView photo;

        @BindView(R.id.ratingBar)
        RatingBar ratingBar;

        @BindView(R.id.numRatings)
        TextView numRatings;

        @BindView(R.id.addedAt)
        TextView addedAt;

        @BindView(R.id.onTheListSince)
        TextView onTheListSince;

        @BindView(R.id.removeFromWishlist)
        Button removeFromWishlist;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, itemView);
        }

        public void bind(MyBeer entry, OnMyBeerItemInteractionListener listener) {

            Beer item = entry.getBeer();

            name.setText(item.getName());
            manufacturer.setText(item.getManufacturer());
            category.setText(item.getCategory());
            name.setText(item.getName());
            GlideApp.with(itemView).load(item.getPhoto()).apply(new RequestOptions().override(240, 240).centerInside())
                    .into(photo);
            ratingBar.setNumStars(5);
            ratingBar.setRating(item.getAvgRating());
            numRatings.setText(itemView.getResources().getString(R.string.fmt_num_ratings, item.getNumRatings()));
            itemView.setOnClickListener(v -> listener.onMoreClickedListener(photo, item));
            removeFromWishlist.setOnClickListener(v -> listener.onWishClickedListener(item));

            String formattedDate =
                    DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.SHORT).format(entry.getDate());
            addedAt.setText(formattedDate);

            if (entry instanceof MyBeerFromWishlist) {
                DrawableHelpers
                        .setDrawableTint(removeFromWishlist, itemView.getResources().getColor(R.color.colorPrimary));
                onTheListSince.setText("auf der Wunschliste seit");
            } else if (entry instanceof MyBeerFromRating) {
                DrawableHelpers.setDrawableTint(removeFromWishlist,
                        itemView.getResources().getColor(android.R.color.darker_gray));
                removeFromWishlist.setText("Wunschliste");
                onTheListSince.setText("beurteilt am");
            } else if (entry instanceof MyBeerFromPrice) {
                DrawableHelpers.setDrawableTint(removeFromWishlist,
                        itemView.getResources().getColor(android.R.color.darker_gray));
                removeFromWishlist.setText("Wunschliste");
                onTheListSince.setText("Preis hinzugefügt am");
            }
        }
    }
}
