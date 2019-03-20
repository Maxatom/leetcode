package utils;

/**
 * @author Shibing
 * @since 2018-09-11 17:04:03
 **/
public class Tuple {
    public static <A,B> TwoTuple<A,B> tuple(A a ,B b){
        return new TwoTuple<>(a, b);
    }
    public static <A,B,C> ThreeTuple<A,B,C> tuple(A a ,B b,C c){
        return new ThreeTuple<>(a, b, c);
    }
    public static <A,B,C,D> FourTuple<A,B,C,D> tuple(A a ,B b, C c,D d){
        return new FourTuple<>(a, b,c ,d);
    }
    public static <A,B,C,D,E> FiveTuple<A,B,C,D,E> tuple(A a ,B b,C c,D d,E e){
        return new FiveTuple<>(a, b, c, d, e);
    }
    public static <A,B,C,D,E,F> SixTuple<A,B,C,D,E,F> tuple(A a ,B b,C c,D d,E e,F f){
        return new SixTuple<>(a, b, c, d, e, f);
    }

}
