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
//        barcodes=new int[]{1,1,1,1,2,2,3,3};
//        barcodes=new int[]{1,1,2,3};
//        barcodes=new int[]{2};
//        barcodes=new int[]{2,3,4};
//        barcodes=new int[]{1,1,1,2,3};
//        barcodes=new int[]{2};
//        barcodes=new int[]{7,7,7,8,5,7,5,5,5,8};
        PrintUtils.printArray(bar.rearrangeBarcodes(barcodes));
    }

    public int[] rearrangeBarcodes(int[] barcodes) {
        int n=barcodes.length, most=0;// res=new int[n];
        int[] cnts=new int[10001];
        for (int i = 0; i < n; i++) {
            cnts[barcodes[i]]++;
            if(cnts[most] <cnts[barcodes[i]]) most=barcodes[i];
        }
        int j=0;
        while (cnts[most]>0){
            barcodes[j%n] = most;
            cnts[most]--; j+=2;
            if(j>=n) j=1;
        }
        for (int i = 10000; i > 0 ; i--) {
                while (cnts[i]>0){
                    barcodes[j] = i;
                    cnts[i]--; j+=2;
                    if(j>=n) j=1;
                }
        }
        return barcodes;
    }
}
