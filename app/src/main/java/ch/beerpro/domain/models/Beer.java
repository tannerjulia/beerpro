package ch.beerpro.domain.models;

import androidx.annotation.NonNull;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

public class Beer implements Entity, Serializable {

    public static final String COLLECTION = "beers";

    @Exclude
    private String id;
    private String manufacturer;
    private String name;
    private String category;
    private String photo;
    private float avgRating;
    private int numRatings;
    private float avgPrice;
    private int numPrices;

    public Beer(String id, String manufacturer, String name, String category, String photo, float avgRating, int numRatings, float avgPrice, int numPrices) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.name = name;
        this.category = category;
        this.photo = photo;
        this.avgRating = avgRating;
        this.numRatings = numRatings;
        this.avgPrice = avgPrice;
        this.numPrices = numPrices;
    }

    public Beer() {
    }

    public String getId() {
        return this.id;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    public String getName() {
        return this.name;
    }

    public String getCategory() {
        return this.category;
    }

    public String getPhoto() {
        return this.photo;
    }

    public float getAvgRating() {
        return this.avgRating;
    }

    public int getNumRatings() {
        return this.numRatings;
    }

    public float getAvgPrice() {
        return this.avgPrice;
    }

    public int getNumPrices() {
        return numPrices;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setNumRatings(int numRatings) {
        this.numRatings = numRatings;
    }

    public void setNumPrices(int numPrices) {
        this.numPrices = numPrices;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Beer)) return false;
        final Beer other = (Beer) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$manufacturer = this.getManufacturer();
        final Object other$manufacturer = other.getManufacturer();
        if (this$manufacturer == null ? other$manufacturer != null : !this$manufacturer.equals(other$manufacturer))
            return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$category = this.getCategory();
        final Object other$category = other.getCategory();
        if (this$category == null ? other$category != null : !this$category.equals(other$category))
            return false;
        final Object this$photo = this.getPhoto();
        final Object other$photo = other.getPhoto();
        if (this$photo == null ? other$photo != null : !this$photo.equals(other$photo))
            return false;
        if (Float.compare(this.getAvgRating(), other.getAvgRating()) != 0) return false;
        return this.getNumRatings() == other.getNumRatings();
    }

    private boolean canEqual(final Object other) {
        return other instanceof Beer;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $manufacturer = this.getManufacturer();
        result = result * PRIME + ($manufacturer == null ? 43 : $manufacturer.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $category = this.getCategory();
        result = result * PRIME + ($category == null ? 43 : $category.hashCode());
        final Object $photo = this.getPhoto();
        result = result * PRIME + ($photo == null ? 43 : $photo.hashCode());
        result = result * PRIME + Float.floatToIntBits(this.getAvgRating());
        result = result * PRIME + this.getNumRatings();
        return result;
    }

    @NonNull
    public String toString() {
        return "Beer(id=" + this.getId() + ", manufacturer=" + this.getManufacturer() + ", name=" + this.getName() + ", category=" + this.getCategory() + ", photo=" + this.getPhoto() + ", avgRating=" + this.getAvgRating() + ", numRatings=" + this.getNumRatings() + ")";
    }
}
