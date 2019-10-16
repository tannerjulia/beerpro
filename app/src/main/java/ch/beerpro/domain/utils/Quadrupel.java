package ch.beerpro.domain.utils;

public class Quadrupel<A, B, C, D> {

    private final A lastA;
    private final B lastB;
    private final C lastC;
    private final D lastD;

    public Quadrupel(A lastA, B lastB, C lastC, D lastD) {
        this.lastA = lastA;
        this.lastB = lastB;
        this.lastC = lastC;
        this.lastD = lastD;
    }

    public A getLastA() {
        return lastA;
    }

    public B getLastB() {
        return lastB;
    }

    public C getLastC() {
        return lastC;
    }

    public D getLastD() {
        return lastD;
    }
}
