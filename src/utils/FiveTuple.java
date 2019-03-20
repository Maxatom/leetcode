package utils;


/**
 * @author Shibing
 * @since 2018-08-30 14:03:37
 **/
public class FiveTuple<A,B,C,D,E> extends FourTuple<A,B,C,D>{
    public final E fifth;

    public FiveTuple(A first, B second, C third, D fourth, E fifth) {
        super(first, second, third, fourth);
        this.fifth = fifth;
    }

    @Override
    public String toString() {
        return "FiveTuple{" +
                "fifth=" + fifth +
                ", fourth=" + fourth +
                ", third=" + third +
                ", first=" + first +
                ", second=" + second +
                '}';
    }
}
