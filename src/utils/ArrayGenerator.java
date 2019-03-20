package utils;

import java.util.Random;

/**
 * @author shibing
 * @since 2018/11/10 12:57
 */
public class ArrayGenerator {
    public static int[] intArrayDinstinct(int length, int bottomBound, int upBound) throws Exception {
        if(upBound-bottomBound<length) throw new Exception("Error");
        int[] ints = new Random().ints(bottomBound, upBound).distinct().limit(length).toArray();
        return ints;
    }

    public static int[] intArray(int length, int bottomBound, int upBound) {
        return  new Random().ints(bottomBound, upBound).limit(length).toArray();
    }
}
