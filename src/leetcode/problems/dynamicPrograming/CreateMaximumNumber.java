package leetcode.problems.dynamicPrograming;

import utils.PrintUtils;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Shibing
 * @since 2019-05-08 14:00:32
 **/
public class CreateMaximumNumber {
    public static void main(String[] args) {
        CreateMaximumNumber number=new CreateMaximumNumber();
        int[] nums1={3, 4, 6, 5}, nums2={9, 1, 2, 5, 8, 3}; int k=5;
        nums1=new int[]{6, 7}; nums2=new int[]{6, 0, 4}; k=5;
        nums1=new int[]{3, 9}; nums2=new int[]{8, 9}; k=3;
        PrintUtils.printArray(number.maxNumber(nums1, nums2, k));
    }
    public int[] maxNumber1(int[] nums1, int[] nums2, int k) {
        int l1=0, l2=0, n1=nums1.length, n2=nums2.length;
        while (k>0){
            int s=9;
            while (s>=0){
                int i=l1;
                while (i<n1){
                    if(nums1[i]==s) break;
                }
                if(i<n1) {
                    l1=i;
                }
                int j=l2;
                while (j<n1){
                    if(nums2[i]==s) break;
                }
            }
        }
        return new int[1];
    }
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int m=nums1.length, n=nums2.length;
        Integer[] index1=new Integer[m];
        Integer[] index2=new Integer[n];
        int[] res=new int[k];
        for (int i = 0; i < m; i++)  index1[i]=i;
        for (int i = 0; i < n; i++)  index2[i]=i;
        Arrays.sort(index1, (a,b)->nums1[b]-nums1[a]);
        Arrays.sort(index2, (a,b)->nums2[b]-nums2[a]);
        int i=0, j=0, l=0, i1=0, j1=0;
        while (i<m && j<n && l<k) {
            if(m-index1[i]+n-index2[j]==k-l || m+n==k){
                break;
            }
            if (nums1[index1[i]] > nums2[index2[j]]) {
                if (index1[i] >= index1[i1] && m - index1[i] + n - index2[j1]-1 >= k - l ) {
                    res[l++] = nums1[index1[i]];
                    i1 = i;
                }
                i++;
            }else if (nums1[index1[i]] < nums2[index2[j]]) {
                if (index2[j] >= index2[j1] && m - index1[i1]-1 + n - index2[j] >= k - l ) {
                    res[l++] = nums2[index2[j]];
                    j1 = j;
                }
                j++;
            } else {
                if(index1[i]>=index1[i1] && index2[j]>=index2[j1]) {
                    if (m - index1[i] >= n - index2[j]) {
                        res[l++] = nums1[index1[i]];
                        i1 = i;
                        i++;
                    } else {
                        res[l++] = nums2[index2[j]];
                        j1 = j;
                        j++;
                    }
                }else if(index1[i]<index1[i1]){
                    i++;
                }else j++;

            }
        }
        if(m-index1[i]+n-index2[j]==k-l || m+n==k){
            int k1=index1[i], k2=index2[j];
            if(m+n==k){ k1=0; k2=0;}
            while (k1<m && k2<n){
                if(nums1[k1]>=nums2[k2]){
                    res[l++]=nums1[k1++];
                }else res[l++]=nums2[k2++];
            }
            while (l < k && k1 < m) res[l++] = nums1[k1++];
            while (l < k && k2 < n) res[l++] = nums2[k2++];
        }else {
            while (l < k && i < m) res[l++] = nums1[index1[i++]];
            while (l < k && j < n) res[l++] = nums2[index2[j++]];
        }
        return res;
    }
}
