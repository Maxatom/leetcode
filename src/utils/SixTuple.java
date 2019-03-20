package utils;


/**
 * @author Shibing
 * @since 2018-08-30 14:03:37
 **/
public class SixTuple<A,B,C,D,E,F> extends FiveTuple<A,B,C,D,E>{
    public final F sixth;

    public SixTuple(A first, B second, C third, D fourth, E fifth, F sixth) {
        super(first, second, third, fourth,fifth);
        this.sixth = sixth;
    }
    @Override
    public String toString() {
        return "ThreeTuple{" +
                "sixth=" + sixth +
                ", fifth=" + fifth +
                ", fourth=" + fourth +
                ", third=" + third +
                ", second=" + second +
                ", first=" + first +
                '}';
    }
}
