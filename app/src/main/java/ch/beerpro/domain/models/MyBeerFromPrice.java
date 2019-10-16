package ch.beerpro.domain.models;

import androidx.annotation.NonNull;

import java.util.Date;
import java.util.Objects;

public class MyBeerFromPrice implements MyBeer {

    private Price price;
    private Beer beer;

    public MyBeerFromPrice(Price price, Beer beer) {
        this.price = price;
        this.beer = beer;
    }

    @Override
    public String getBeerId() {
        return price.getId();
    }

    @Override
    public Beer getBeer() {
        return beer;
    }

    @Override
    public Date getDate() {
        return price.getCreationDate();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyBeerFromPrice that = (MyBeerFromPrice) o;
        return Objects.equals(price, that.price) &&
                Objects.equals(beer, that.beer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, beer);
    }

    @NonNull
    public String toString() {
        return "MyBeerFromPrice(price =" + this.price + ", beer=" + this.getBeer() + ")";
    }
}
