package leetcode.problems.dynamicPrograming;

import utils.CountingGenerator;
import utils.CountingGenerator;
import utils.Generator;
import utils.Generator;

import java.util.Random;


/**
 * @author shibing
 * @since 2018/11/11 13:42
 */
public class MedianOfTwoSortedArrays {

    public static void main(String args[]) {
        MedianOfTwoSortedArrays mos = new MedianOfTwoSortedArrays();
        Generator generator = new CountingGenerator.Integer();
        Random random=new Random(47);
        int[] a1 =new int[200000000];//= new Random().ints(1, 500).distinct().limit(20000).sorted().toArray();
        int[] a2 =new int[200000000]; //= new Random().ints(1, 900).distinct().limit(20000).sorted().toArray();
        for (int i = 0; i < 200000000; i++) {
            a1[i]=random.nextInt(10000);
            if(i<20)
                a2[i]=random.nextInt(10000);
        }
//        int[] a1={1,5,6,7};
//        int[] a2={2,3,4,8};//,4,5,6,7,8};
//        printArray(a1);
//        System.out.println(mos.binarySearch(new int[0],1));
//        printArray(a2);
//        System.out.println(mos.findMediam(a1,a2));
        long start=System.currentTimeMillis();
        System.out.println(mos.findMedianSortedArrays(a1, a2)+"  " + (System.currentTimeMillis()-start));
        start=System.currentTimeMillis();
        System.out.println(mos.findMedianSortedArrays2(a1, a2)+"  " + (System.currentTimeMillis()-start));

    }

    public static void printArray(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
            if ((i + 1) % 10 == 0) System.out.print("- ");
        }
        System.out.println();
    }


    //O(log(min(M,N))
//    @AnalysisActuator
    public double findMedianSortedArrays(int[] nums1, int[] nums2){
        if (nums1.length + nums2.length == 0) return 0f;
        //交换数组
        if(nums1.length>nums2.length){
            int[] temp=nums1; nums1=nums2; nums2=temp;
        }
        if (nums1.length + nums2.length == 1) return nums1.length == 1 ? nums1[0] : nums2[0];
        if(nums1.length==0) return oneArray(nums2);
        int imin=0, imax=nums1.length, jmax=(nums1.length+nums2.length+1)/2;
        int i=(imin+imax)/2, j=jmax-i;
        while (imin<imax){
            if(i==0)
                if(nums2[j-1]<=nums1[i]) break;
                else imin=i+1;
            else if(nums1[i-1]<=nums2[j] && nums2[j-1]<=nums1[i])//找到中位数位置
                break;
            else if (nums1[i-1]>nums2[j]) imax=i-1;
            else if(nums2[j-1]>nums1[i]) imin=i+1;
            i=(imin+imax)/2;
            j=jmax-i;
        }
        boolean iseven=(nums1.length+nums2.length)%2==0;
        if(i>0&&i<nums1.length) return iseven?(Math.max(nums1[i-1],nums2[j-1])+Math.min(nums1[i],nums2[j]))/2.0f:Math.max(nums1[i-1],nums2[j-1]);
        else if(i==0)
            if (iseven) return j==nums2.length?(nums1[i]+nums2[j-1])/2.0d:(nums2[j-1]+Math.min(nums1[i],nums2[j]))/2.0d;
            else return nums2[j-1];
        else //if(i==imax)
            if(iseven) return j!=0?(Math.max(nums1[i-1],nums2[j-1])+nums2[j])/2.0d:(nums1[i-1]+nums2[j])/2.0d;
            else return Math.max(nums1[i-1], nums2[j-1]);
    }


    //O(min(M,N))
    //将两个数组合并后找到第（M+N）/2个位置上的数，这里只记录合并后数据当前元素和他的前一个元素，并不真正合并两个数组
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int[] a1 = nums1;
        int[] a2 = nums2;
        if (a1.length + a2.length == 0) return 0f;
        if (a1.length + a2.length == 1) return a1.length == 1 ? a1[0] : a2[0];
        if (a1.length == 0 || a2.length == 0) return a1.length != 0 ? oneArray(a1) : oneArray(a2);
        boolean flag = (a1.length + a2.length) % 2 == 0;
        int min = (a1.length + a2.length) / 2;
        int i = 0, j = 0; //a1和a2的下标
        int k = 0; //将两个数组合并的元素下标
        int[] b = new int[2];
        //归并思想合并两个数组，只记录下标，并不真正合并
        while (k <= min && i < a1.length && j < a2.length) {
            b[0] = b[1];
            if (i < a1.length && a1[i] <= a2[j]) b[1] = a1[i++];
            else if (j < a2.length) b[1] = a2[j++];
            k++;
        }
        if (i == a1.length && k - 1 < min) {
            if (k == min) {
                b[0] = b[1];
                b[1] = a2[j];
            } else {
                int index = min + 1 - k;
                j = (j == 0) ? index - 1 : j + index - 1;
                b[0] = a2[j - 1];
                b[1] = a2[j];
            }
        } else if (j == a2.length && k - 1 < min) {
            if (k == min) {
                b[0] = b[1];
                b[1] = a1[i];
            } else {
                int index = min + 1 - k;
                i = (i == 0) ? index - 1 : i + index - 1;
                b[0] = a1[i - 1];
                b[1] = a1[i];
            }
        }
        if (!flag) return b[1];
        else return (b[0] + b[1]) / 2.0f;
    }

    //返回一个数组的中位数
    public double oneArray(int[] a) {
        if (a.length % 2 != 0) return a[a.length / 2];
        else return (a[a.length / 2 - 1] + a[a.length / 2]) / 2.0;
    }

    //查找中位数，使用二分查找 ,  复杂度O(M*logN)
    //失败的算法
    public double findMediam(int[] a1, int[] a2) {
        if (a1.length + a2.length == 0) return 0f;
        if (a1.length + a2.length == 1) return a1.length == 1 ? a1[0] : a2[0];
        if (a1.length == 0 || a2.length == 0) return a1.length != 0 ? oneArray(a1) : oneArray(a2);
        boolean flag = (a1.length + a2.length) % 2 == 0;
        int min = (a1.length + a2.length) / 2 ;
        int i = 0, j = -1, prej=-1; //a1和a2的下标
        int k = 0; //将两个数组合并的元素下标
        int[] b = new int[2];//记录中位数，偶数要记录两位
        if(a1.length<a2.length) {
            while (i<a1.length&&j<a2.length) {
                j=binarySearch0(a2, j+1,a2.length-1,a1[i]);
                k=j+i+1;
                if(k==min) {b[0]=a2[j]; b[1]=a1[i]; break;}//奇数时取b[0]
                //K>min时无法取值，
                else if(k>min) {
                    if (prej == j) {//奇数时取b[1]
                        b[0] = a2[i - 1];
                        b[1] = a1[i];
                    } else {
//                        if(k-j)
                        b[0]=a2[min-1];
                        b[1]=a1[min];
                    }
                }
                i++; prej=j;
            }
        }
        if (i == a1.length && k  +1 <= min) {//a1先结束
            if (k +1 == min) {
                b[0] = a1[i-1];
                b[1] = a2[j+1];
            } else {
                int index = min +1 - a1.length;
                j =  j + index ;
                b[0] = a2[j - 1];
                b[1] = a2[j];
            }
        } else if (j == a2.length && k - 1 < min) {
                int index = min + 1 - a2.length;
                i =  i + index ;
                b[0] = a1[i - 1];
                b[1] = a1[i];
        }
        if (!flag) return b[1];
        else return (b[0] + b[1]) / 2.0f;
    }

    /**
     * 二分查找一个元素在一个有序数组中的位置
     * @param a 数组
     * @param num 需要查找的数
     * @return 返回数组的>num的最小数下标-1
     * 例 [ 2,4,5,6,7 ]  查找3的位置 ，是0
     *
     */
    public int binarySearch(int a[],int num){
        if(a.length==0) return -2;
        return binarySearch0(a,0,a.length-1,num);
    }
    int binarySearch(int a[], int start,int end,int num){
        int mid=(start+end) >>> 1;
        if(end<0) return -1;
        if(start>a.length-1) return a.length-1;
        if(start==end) return a[mid]>num?mid-1:mid;
        if(a[mid]<=num&&a[mid+1]>=num) return mid;
        else if(a[mid]>num) return binarySearch(a,start,mid-1, num);
        else return binarySearch(a,mid+2,end,num);

    }

    /**
     * 二分查找一个元素在一个有序数组中的位置
     * @param a 数组
     * @param num 需要查找的数
     * @return 返回数组的>num的最小数下标-1
     * 例 [ 2,4,5,6,7 ]  查找3的位置 ，是0
     *
     */
    //非递归实现
    public int binarySearch0(int a[], int start,int end,int num) {
                //非递归实现
        int low = start;
        int high = end;

        while (low < high) {
            int mid = (low + high) >>> 1;
            int midVal = a[mid];

            if (midVal <= num && a[mid+1]>num)
                return mid;
            else if (midVal > num)
                high = mid - 1;
            else
                low = low +1; // key found
        }
        if(high<0) return -1;
        if(low>a.length-1) return a.length-1;
        if(low==high) return a[low]>num?low-1:low;
        return -(low + 1);  // key not found.
    }
}
