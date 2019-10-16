package ch.beerpro.domain.models;

import androidx.annotation.NonNull;

import com.google.firebase.firestore.Exclude;

import java.util.Date;

public class Price implements Entity {

    public static final String COLLECTION = "prices";
    public static final String FIELD_BEER_ID = "beerId";
    public static final String FIELD_USER_ID = "userId";
    public static final String FIELD_CREATION_DATE = "creationDate";

    @Exclude
    private String id;
    private String beerId;
    private String userId;
    private String userName;
    private float price;
    private Date creationDate;

    public Price(String id, String beerId, String userId, String userName, float price, Date creationDate) {
        this.id = id;
        this.beerId = beerId;
        this.userId = userId;
        this.userName = userName;
        this.price = price;
        this.creationDate = creationDate;
    }

    public Price() {
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getBeerId() {
        return beerId;
    }

    public void setBeerId(String beerId) {
        this.beerId = beerId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @NonNull
    public String toString() {
        return "Price(id=" + this.getId() + ", beerId=" + this.getBeerId() + ", userId=" + this.getUserId() + ", userName=" + this.getUserName() + ", price=" + this.getPrice() + ", creationDate=" + this.getCreationDate() + ")";
    }
}
