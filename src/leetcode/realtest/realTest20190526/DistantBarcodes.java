package leetcode.realtest.realTest20190526;

import utils.PrintUtils;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author shibing
 * @since 2019/5/26 10:40
 */
public class DistantBarcodes {
    public static void main(String[] args) {
        DistantBarcodes  bar=new DistantBarcodes();
        int[] barcodes=new int[]{1,1,1,2,2,2};
        barcodes=new int[]{1,1,1,1,2,2,3,3};
        barcodes=new int[]{1,1,2,3};
        barcodes=new int[]{2};
        barcodes=new int[]{2,3,4};
//        barcodes=new int[]{1,1,1,2,3};
        barcodes=new int[]{2};
        barcodes=new int[]{7,7,7,8,5,7,5,5,5,8};
        PrintUtils.printArray(bar.rearrangeBarcodes(barcodes));
    }

     class Pair{
           public int num;
           public int cnt;

            public Pair(int num, int cnt) {
                this.num = num;
                this.cnt = cnt;
            }
        }    public int[] rearrangeBarcodes(int[] barcodes) {
        int n=barcodes.length, len=0, total=0;
        int[] cnts=new int[10001];// res=new int[n];
        for (int i = 0; i < n; i++) {
            if(cnts[barcodes[i]]==0) total++;
            cnts[barcodes[i]]++;
        }

        Pair[] pairs=new Pair[total];
        int j=0;
        for (int i = 1; i < cnts.length; i++) {
            if(cnts[i]!=0) pairs[j++]=new Pair(i, cnts[i]);
        }
        Arrays.sort(pairs, (p1,p2)->p2.cnt-p1.cnt);

        int x=0, y=1;
        for (int i = 1; i < pairs.length; i++) {
            if(pairs[x].cnt==0){
                x=i++;
            }
            if(pairs[y].cnt==0 && i<pairs.length){
                y=i;
            }
            if(pairs[x].cnt!=0 && pairs[y].cnt!=0) {
               len=set(barcodes,len, x, y, pairs);
            }
        }
        if(pairs[x].cnt!=0) barcodes[len]=pairs[x].num;
        else if(pairs[y].cnt!=0) barcodes[len]=pairs[y].num;
        return barcodes;
    }
    public int set(int[] barcodes,int len, int x, int y, Pair[] pairs){
        while (pairs[x].cnt!=0 && pairs[y].cnt!=0){
            barcodes[len++]=pairs[x].num;  pairs[x].cnt--;
            barcodes[len++]=pairs[y].num;  pairs[y].cnt--;
        }
        return len;
    }
}
