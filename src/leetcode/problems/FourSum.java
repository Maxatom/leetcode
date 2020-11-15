package leetcode.problems;

import utils.PrintUtils;

import java.util.*;

public class FourSum {
    public static void main(String[] args) {
        FourSum four= new FourSum();
        int[] nums ={1,0,-1,0,-2,2}; int target= 0;
        nums = new int[]{-3,-2,-1,0,0,1,2,3}; target= 0;
//        PrintUtils.printList(four.fourSum(nums,target), p->p.toString());
//        PrintUtils.printList(four.fourSum1(nums,target), p->p.toString());
        PrintUtils.printList(four.fourSum2(nums,target), p->p.toString());
    }
    //双指针
    public List<List<Integer>> fourSum2(int[] nums, int target) {
        int n=nums.length;
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for(int i=0; i<n-3; i++){
            if(i>0&&nums[i]==nums[i-1]) continue;
            if(nums[i]+nums[n-3]+nums[n-2]+nums[n-1]<target) continue;
            if(nums[i]+ nums[i+1]+nums[i+2]+nums[i+3]>target) continue;
            for(int j=i+1; j<n-2; j++){
                if(j>i+1&&nums[j]==nums[j-1]) continue;
                if(nums[i] + nums[j]+nums[n-2]+nums[n-1]<target) continue;
                if(nums[i] + nums[j]+nums[j+1]+nums[j+2]>target) continue;
                int num = target-nums[i]-nums[j];
                int k= j+1, l=n-1;
                while (k<l){
                    if(nums[k]+nums[l]==num){
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        list.add(nums[l]);
                        res.add(list);
                        k++;
                        while ( k<l && nums[k]==nums[k-1]) k++;
                    }else if(nums[k]+nums[l]<num){
                        k++;
                        while ( k<l && nums[k]==nums[k-1]) k++;
                    }else{
                        l--;
                        while (l<n-1 && l>k && nums[l]==nums[l+1]) l--;
                    }
                }
            }
        }
        return res;
    }

    public List<List<Integer>> fourSum1(int[] nums, int target) {
        int n=nums.length;
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        Map<Integer, List<int[]>> map = new HashMap<>();
        for(int i=0; i<n; i++) {
            if(i<n-1 && nums[i]==nums[i+1]) continue;
            if(i>0 && nums[i]==nums[i-1]){
                int sum = nums[i-1]+nums[i];
                if(!map.containsKey(sum)) map.put(sum, new ArrayList<>());
                map.get(sum).add(new int[]{i-1, i});
            }
            for (int j = i+1; j < n; j++) {
                if(j<n-1 && nums[j]==nums[j+1]) continue;
                int sum = nums[i]+nums[j];
                if(!map.containsKey(sum)) map.put(sum, new ArrayList<>());
                map.get(sum).add(new int[]{i, j});
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        for(int i=0; i<n-3; i++){
            if(i>0&&nums[i]==nums[i-1]) continue;
            if(nums[i]+nums[n-3]+nums[n-2]+nums[n-1]<target) continue;
            if(nums[i]+ nums[i+1]+nums[i+2]+nums[i+3]>target) continue;
            for(int j=i+1; j<n-2; j++){
                if(j>i+1&&nums[j]==nums[j-1]) continue;
                if(nums[i] + nums[j]+nums[n-2]+nums[n-1]<target) continue;
                if(nums[i] + nums[j]+nums[j+1]+nums[j+2]>target) continue;
                int num = target-nums[i]-nums[j];
                if(!map.containsKey(num)) continue;
                List<int[]> postlist = map.get(num);
                for(int k=postlist.size()-1; k>=0; k--){
                    int[] post = postlist.get(k);
                    if(post[0]<=j) break;
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[post[0]]);
                    list.add(nums[post[1]]);
                    res.add(list);
                }

            }
        }
        return res;
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        int n=nums.length;
        for(int i=0; i<n; i++){
            if(i>0&&nums[i]==nums[i-1]) continue;
            for(int j=i+1; j<n; j++){
                if(j>i+1&&nums[j]==nums[j-1]) continue;
                for(int k=j+1; k<n; k++){
                    if(k>j+1&&nums[k]==nums[k-1]) continue;
                    for(int l=k+1; l<n; l++){
                        if(l>k+1&&nums[l]==nums[l-1]) continue;
                        if(nums[i]+nums[j]+nums[k]+nums[l]==target){
                            List<Integer> list = new ArrayList<>();
                            list.add(nums[i]);
                            list.add(nums[j]);
                            list.add(nums[k]);
                            list.add(nums[l]);
                            res.add(list);
                        }
                    }
                }
            }
        }
        return res;
    }
}
