package leetcode.realtest.realTest20201108_WC214;

import java.util.Arrays;
import java.util.Collections;

public class SellDimishings {
    public static void main(String[] args) {
        SellDimishings sell = new SellDimishings();
        int[] iven = {2,5}; int orders= 4;
        iven = new int[]{3,5}; orders = 6;
        iven=new int[]{2,8,4,10,6}; orders = 20;
        iven = new int[]{1000000000}; orders = 1000000000;
        iven = new int[]{7_7316_0767} ; orders=2_5226_4991;
//        iven = new int[]{701695700,915736894,35093938,364836059,452183894,951826038,861556610,441929847,842650446,858413011,457896886,35119509,776620034,396643588,83585103,681609037};
//        orders=598226067;
        System.out.println(sell.maxProfit(iven, orders));
    }
    public int maxProfit(int[] inventory, int orders) {
        Arrays.sort(inventory);
        reverse(inventory);
        int n=inventory.length;
        long total=0, remain=0;
        int pos=0;
        for(int i=0; i<=n; i++){
            long value=i==n?0:inventory[i];
            long temp=total-(i*value);
            if(temp<orders){
                total+=value;
            }else if(temp>=orders){
                remain=temp-orders;
                pos=i;
                break;
            }
        }
        return (int)cal(inventory, remain, pos);
    }
    void reverse(int[] arr){
        for(int i=0; i<arr.length/2;i++){
            int temp=arr[i];
            arr[i]=arr[arr.length-i-1];
            arr[arr.length-i-1]=temp;
        }
    }
    int M=(int)1e9+7;
    long cal(int[] iv, long remain, int pos){
        int n=iv.length;
        long res=0;
        long num=pos==n?0:iv[pos];
        num+=remain/pos;
        for(int i=0; i<pos; i++){
            if(iv[i]<=num) break;
            long temp=(iv[i]+num+1)*(iv[i]-num)/2;
            temp%=M;
            res=(res+temp)%M;
//            System.out.println(res);
        }
        for(int i=0;i<remain%pos; i++){
            res-=(num+1);
            res=(res+M)%M;
        }
        return res;
    }
}
