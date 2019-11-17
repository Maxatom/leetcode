package leetcode.realtest.realTest20191103;

public class CountNumberofNiceSubarrays {
    public static void main(String[] args) {
        CountNumberofNiceSubarrays subarrays=new CountNumberofNiceSubarrays();
        int[] nums = new int[]{1,1,2,1,1}; int  k = 3;
//        nums = new int[]{2,4,6}; k = 1;
//        nums = new int[]{2,2,2,1,2,2,1,2,2,2}; k = 2;
        System.out.println(subarrays.numberOfSubarrays2(nums, k));

    }

    //atMost
    public int numberOfSubarrays2(int[] nums, int k) {
        return atMost(nums, k) - atMost(nums, k-1);
    }
    public int atMost(int[] a, int k){
        int i=0,j=0, n=a.length, cnt=0;
        while (i<n){
            k-=isOdd(a[i]) ? 1: 0;
            while (k<0){
                k+=isOdd(a[j]) ? 1: 0;
                j++;
            }
            cnt += i-j+1;
            i++;
        }
        return cnt;
    }
    //two pointers
    public int numberOfSubarrays1(int[] nums, int k) {
        int l=0, r=1,  n=nums.length, odd=0;
        int cnt= 0;
        while (l<n && r<=n){
            if(odd<k)  {
                odd += (nums[r-1]&1)==1 ? 1 : 0;
            }
            if(odd==k){
                cnt++;
                int l1=l;
                while (l<r && !isOdd(nums[l])){
                    l++;
                    cnt++;
                }
                if(r<n && !isOdd(nums[r])) {
                        r++;
                        l=l1;
                    } else  {
                        r++;
                        l++;
                        odd--;
                    }
            }else r++;
        }
        return cnt;
    }

    // prefix sum
    public int numberOfSubarrays(int[] nums, int k) {
        int cnt=0, n=nums.length, total=0;
        int[] preSum=new int[n];
        preSum[0]=1;
        for (int j = 0; j < n; j++) {
            total += isOdd(nums[j]) ?1 : 0;
            preSum[total]++;
            if(total>=k)
                cnt+=preSum[total-k];
        }
        return cnt;
    }
    public boolean isOdd(int n){
        return (n&1)==1;
    }
}
