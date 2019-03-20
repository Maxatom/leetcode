package utils;


/**
 * @author Shibing
 * @since 2018-08-30 13:57:34
 **/
public class TwoTuple <A,B>{
    public final A first;//声明为public是安全的，因为成员是final类型
    public final B second;

    public TwoTuple(A first, B second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return "TwoTuple{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}
