package leetcode.realtest.realTest20190602;

import utils.Utils;

import java.util.List;

/**
 * @author shibing
 * @since 2019/6/2 10:56
 */
public class GreatestCommonDivisorofStrings {
    public static void main(String[] args) {
        GreatestCommonDivisorofStrings divisor=new GreatestCommonDivisorofStrings();
        String  str1 = "ABCABC", str2 = "ABC";
        str1 = "ABABAB"; str2 = "ABAB";
        str1 = "LEET"; str2 = "CODE";
        str1 = "ABABABAB"; str2 = "ABAB";
        System.out.println(divisor.gcdOfStrings(str1, str2));
        System.out.println(divisor.gcdOfStrings1(str1, str2));
    }
    public String gcdOfStrings1(String str1, String str2) {
        if(str1.length()<str2.length()) return gcdOfStrings1(str2, str1);
        if(!str1.startsWith(str2)) return "";
        if(str2.isEmpty()) return str1;
        return gcdOfStrings1(str1.substring(str2.length()), str2);
    }

    //O(N^3)
    public String gcdOfStrings(String str1, String str2) {
        if(str1.length()<str2.length()) gcdOfStrings(str2, str1);
        for (int i = 0; i < str2.length(); i++) {
            if(str1.charAt(i)!=str2.charAt(i)) return "";
            String d=str2.substring(0, i+1);
            if(isDivisor(str1, d) && isDivisor(str2,d )&& Utils.gcd(str1.length()/d.length(), str2.length()/d.length())==1)
                return d;
        }
        return "";
    }
    public boolean isDivisor(String str, String str1){
        if(str.length()%str1.length()!=0) return false;
        for (int i = 0; i < str.length(); i+=str1.length()) {
            if(!str1.equals(str.substring(i, i+str1.length()))) return false;
        }
        return true;
    }
}
