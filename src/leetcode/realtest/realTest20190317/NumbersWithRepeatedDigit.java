package leetcode.realtest.realTest20190317;

import java.util.HashSet;
import java.util.Set;

/**
 * @author shibing
 * @since 2019/3/17 11:36
 */
public class NumbersWithRepeatedDigit {
    public static void main(String[] args) {
        NumbersWithRepeatedDigit digit=new NumbersWithRepeatedDigit();
        int N=20;//1
//        N=99; //9
//        N=100; //10
//        N=999; //261
//        N=1000; //262
//        N=1;//0
//        N=5;//0
//        N=9;//0
//        N=10; //0
//        N=11; //1
//        N=110;
//        N=1000000000;
//        N=(int)Math.pow(10,9);
        System.out.println(digit.numDupDigitsAtMostN(N));
        System.out.println(digit.numDupDigitsAtMostN1(N));
//        System.out.println(Integer.MAX_VALUE);
    }

    //dp
    //f(i,j,k) means The number of i-digit integers with no repeated digits in the interval [j,k] (i>0, j<=k, j,k are i-digit integers)
    //f(i,j,k)=k-j+1, i=1;
    //f(i+1,10j,10k+9)=f(i,j,k)*(10-i) . others.
    public int numDupDigitsAtMostN1(int N) {
        if(N<10) return 0;
        int n=N;
        int count=0;
        int[] digit=new int[11];
        while (n>0) {
            digit[++count]=n%10;
            n/=10;
        }
        int noRepeatBaseSum=9;
        int pre=9, cur;
        for (int i = 1; i < count-1; i++) {
            cur=pre*(10-i);
            noRepeatBaseSum+=cur;
            pre=cur;
        }
//        System.out.println("noRepeatBaseSum="+noRepeatBaseSum);
        int[] digitcnt=new int[10];
        int noRepeatRest=0;
        pre=1;
        boolean duplicate=false;
        for (int i = 1; i <= count; i++) {
            int curdigit=digit[count+1-i];
            noRepeatRest=i==1?9:pre*(10-i+1);
            if(!duplicate) {
                for (int j = curdigit+1; j < 10; j++)
                    noRepeatRest -= (digitcnt[j]==0?1:0);
                digitcnt[curdigit]++;
                if(digitcnt[curdigit]>1) duplicate=true;
            }
            pre=noRepeatRest;
        }
//        System.out.println("noRepeatRest="+noRepeatRest);
        return N-noRepeatBaseSum-noRepeatRest;
    }


    //Math
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
