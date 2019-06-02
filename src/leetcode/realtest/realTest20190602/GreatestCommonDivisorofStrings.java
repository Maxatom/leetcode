package leetcode.realtest.realTest20190602;

/**
 * @author shibing
 * @since 2019/6/2 10:56
 */
public class GreatestCommonDivisorofStrings {
    public static void main(String[] args) {

    }
    public String gcdOfStrings(String str1, String str2) {
        if(str1.length()<str2.length()) gcdOfStrings(str2, str1);
        for (int i = 0; i < str2.length(); i++) {
            if(str1.charAt(i)!=str2.charAt(i)) return "";
        }
//        gcdOfStrings(str1)
        return ";";
    }
}
