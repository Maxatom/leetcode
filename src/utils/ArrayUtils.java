package utils;

/**
 * @author shibing
 * @since 2018/11/14 22:46
 */
public class ArrayUtils {
    public static void printIntArray(int[] a){
        System.out.print("[ ");
        for (int i = 0; i < a.length-1; i++) {
            System.out.print(a[i] + ", ");
        }
        System.out.println(a[a.length-1] + " ] ");
    }
}
