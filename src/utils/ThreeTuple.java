package utils;

/**
 * @author Shibing
 * @since 2018-08-30 14:01:20
 **/
public class ThreeTuple<A,B,C> extends TwoTuple<A,B>{
    public final C third;

    public ThreeTuple(A first, B second, C third) {
        super(first, second);
        this.third = third;
    }

    @Override
    public String toString() {
        return "ThreeTuple{" +
                "third=" + third +
                ", second=" + second +
                ", first=" + first +
                '}';
    }
}
