package leetcode.realtest.realTest20190324;

import java.util.HashSet;
import java.util.Set;

/**
 * @author shibing
 * @since 2019/3/24 10:42
 */
public class BinaryStringWithSubstringsRepresenting1ToN {
    public static void main(String[] args) {
        BinaryStringWithSubstringsRepresenting1ToN binary=new BinaryStringWithSubstringsRepresenting1ToN();
        String S="0110"; int N=4;
//        S="0110"; N=3;
//        S="1011100"; N=7;
//        S="1111000101";N= 5;
        System.out.println(binary.queryString(S, N));
//        System.out.println(binary.queryString1(S, N));
        System.out.println(binary.queryString2(S, N));
    }

    public boolean queryString2(String S, int N) {
        for (int i = N; i >N/2 ; i--)
            if(!S.contains(Integer.toBinaryString(i))) return false;
        return true;
    }

    //simplifying code
    public boolean queryString1(String S, int N) {
        char[] chars=S.toCharArray();
        int n=N>>1;
        int len=Integer.toBinaryString(n+1).length();
        int maxlen=Integer.toBinaryString(N).length();
        for (int i = len; i <= maxlen; i++) {
            Set<Integer> sets=new HashSet<>();
            int total;
            if(i==maxlen) total=N-(1<<(i-1))+1;
            else total=(1<<(i-1));
            for (int j = 0; j+i-1 < chars.length; j++) {
                if(chars[j]=='0') continue;
                int num=toInt(chars, j, j+i);
                if(!sets.contains(num) && num<=N)
                    sets.add(num);
            }
            if(sets.size()!=total) return false;
        }
        return true;
    }


    //O(S^2)
    public boolean queryString(String S, int N) {
        char[] chars=S.toCharArray();
        int n=N>>1;
        int len=Integer.toBinaryString(n+1).length();
        int maxlen=Integer.toBinaryString(N).length();
        for (int i = len; i <= maxlen; i++) {
            Set<Integer> sets=new HashSet<>();
            int num=toInt(chars, 0, i);
            if(chars[0]=='1' && num<=N) sets.add(num);
            int total;
            if(i==maxlen) total=N-(1<<(i-1))+1;
            else total=(1<<(i-1));
            for (int j = 1; j+i-1 < chars.length; j++) {
                num=((((1<<(i-1))-1)&num)<<1)+chars[j+i-1]-'0';
                if(chars[j]=='0') continue;
                if(!sets.contains(num) && num<=N)
                    sets.add(num);
            }
            if(sets.size()!=total) return false;
        }
        return true;
    }

    int toInt(char[] chars, int s, int e){
        int num=0;
        for (int i=s; i<e; i++)
            num=(num<<1)+chars[i]-'0';
        return num;
    }
}
