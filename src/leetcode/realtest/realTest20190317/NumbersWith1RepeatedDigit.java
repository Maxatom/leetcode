package leetcode.realtest.realTest20190317;

import java.util.HashSet;
import java.util.Set;

/**
 * @author shibing
 * @since 2019/3/17 11:36
 */
public class NumbersWith1RepeatedDigit {
    public static void main(String[] args) {
        NumbersWith1RepeatedDigit digit=new NumbersWith1RepeatedDigit();
        int N=20;//1
        N=99; //9
        N=100; //10
        N=999; //261
        N=1000; //262
        N=1;//0
//        N=5;//0
//        N=9;//0
        N=10; //0
        N=11; //1
        N=110;
//        N=1000000000;
        N=(int)Math.pow(10,9);
        System.out.println(digit.numDupDigitsAtMostN(N));
        System.out.println(digit.numDupDigitsAtMostN1(N));
//        System.out.println(Integer.MAX_VALUE);
    }


    public int numDupDigitsAtMostN1(int N) {
        int n=N;
        int count=0;
        int[] arr=new int[11];
        while (n>0) {
            arr[++count]=n%10;
            n/=10;
        }
        return N-recursive(N, 0,  count, new HashSet<>())+1;
    }
    public int recursive(int N, int cur, int total, Set<Integer> set){
        if(cur>N || cur<0) return 0;
        if(0==total) return 1;
        int count=0;
        for (int i = 0; i <=9; i++) {
            if(set.contains(i)) continue;
            if(cur!=0||i!=0) set.add(i);
            count+=recursive(N,cur+i*pow10(total-1), total-1,set);
            set.remove(i);
        }
        return count;
    }
    public int pow10(int n){
        int res=1;
        while (n-->0)
            res*=10;
        return res;
    }



    public int numDupDigitsAtMostN(int N) {
        int n=N;
        int count=0;
        int[] arr=new int[11];
        while (n>0) {
            arr[++count]=n%10;
            n/=10;
        }
        int res=count(count, arr[count]-1);
        Set<Integer> set=new HashSet<>(10);
        set.add(arr[count]);
        for (int i = count-1; i >0; i--) {
            int maxcur=arr[i], rems=0;
            while (maxcur>=0 && set.contains(maxcur))
                maxcur--;
            if(maxcur<0) break;
            set.add(maxcur);
            int bit=maxcur-1;
            while (bit>=0){
                if(!set.contains(bit)) rems++;
                bit--;
            }
            if(maxcur<arr[i]) rems++;
            int cnt=rems;
            for (int j = 1; j < i; j++) {
                cnt*=(10-(count-i)-j);
            }
            res+=cnt;
            if(maxcur<arr[i]) {
                set.remove(maxcur);
                break;
            }
            arr[i]=maxcur;
        }
        if(set.size()==count) res+=1;
        return N-res;
    }
    public int count(int n, int num){
        if(n<1) return 0;
        if(n==1) return num;
        int count=0, N=n;
        if(num>0) {
            count=num;
            while (--n > 0) {
                count *= (10 - n);
            }
        }
        count+=count(N-1, 9);
        return count;
    }
}
