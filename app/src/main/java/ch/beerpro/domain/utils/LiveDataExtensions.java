package ch.beerpro.domain.utils;

import android.util.Pair;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import org.apache.commons.lang3.tuple.Triple;

public class LiveDataExtensions {

    public static <A, B> LiveData<Pair<A, B>> zip(LiveData<A> as, LiveData<B> bs) {
        return new MediatorLiveData<Pair<A, B>>() {

            A lastA = null;
            B lastB = null;

            {
                {
                    addSource(as, (A a) -> {
                        lastA = a;
                        update();
                    });
                    addSource(bs, (B b) -> {
                        lastB = b;
                        update();
                    });
                }
            }

            private void update() {
                this.setValue(new Pair<>(lastA, lastB));
            }
        };
    }

    public static <A, B> LiveData<Pair<A, B>> combineLatest(LiveData<A> as, LiveData<B> bs) {
        return new MediatorLiveData<Pair<A, B>>() {

            A lastA = null;
            B lastB = null;

            {
                {
                    addSource(as, (A a) -> {
                        lastA = a;
                        update();
                    });
                    addSource(bs, (B b) -> {
                        lastB = b;
                        update();
                    });
                }
            }

            private void update() {
                if (lastA != null && lastB != null) {
                    this.setValue(new Pair<>(lastA, lastB));
                }
            }
        };
    }

    public static <A, B, C> LiveData<Triple<A, B, C>> combineLatest(LiveData<A> as, LiveData<B> bs, LiveData<C> cs) {
        return new MediatorLiveData<Triple<A, B, C>>() {

            A lastA = null;
            B lastB = null;
            C lastC = null;

            {
                {
                    addSource(as, (A a) -> {
                        lastA = a;
                        update();
                    });
                    addSource(bs, (B b) -> {
                        lastB = b;
                        update();
                    });
                    addSource(cs, (C c) -> {
                        lastC = c;
                        update();
                    });
                }
            }

            private void update() {
                if (lastA != null && lastB != null && lastC != null) {
                    this.setValue(Triple.of(lastA, lastB, lastC));
                }
            }
        };
    }

    public static <A, B, C, D> LiveData<Quadrupel<A, B, C, D>> combineLatest(LiveData<A> as, LiveData<B> bs, LiveData<C> cs, LiveData<D> ds) {
        return new MediatorLiveData<Quadrupel<A, B, C, D>>() {

            A lastA = null;
            B lastB = null;
            C lastC = null;
            D lastD = null;

            {
                {
                    addSource(as, (A a) -> {
                        lastA = a;
                        update();
                    });
                    addSource(bs, (B b) -> {
                        lastB = b;
                        update();
                    });
                    addSource(cs, (C c) -> {
                        lastC = c;
                        update();
                    });
                    addSource(ds, (D d) -> {
                        lastD = d;
                        update();
                    });
                }
            }

            private void update() {
                if (lastA != null && lastB != null && lastC != null && lastD != null) {
                    this.setValue(new Quadrupel(lastA, lastB, lastC, lastD));
                }
            }
        };
    }
}
