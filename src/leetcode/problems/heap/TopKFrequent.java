package leetcode.problems.heap;

import utils.ArrayGenerator;

import java.util.*;

public class TopKFrequent {
    public static void main(String[] args) throws Exception {
        TopKFrequent top = new TopKFrequent();
        Test();
//        int[] nums ={0, 0, 0, 0, 0, 0, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 4};
//        System.out.println(Arrays.toString(top.topKFrequent(nums, 5)));
    }
    static void Test() throws Exception {
        TopKFrequent top = new TopKFrequent();
        for(int i=-2; i<100; i++) {
            int[] nums = top.generate(10);
            String s1= Arrays.toString(top.topKFrequent(nums, 5));
            String s2=Arrays.toString(top.topKFrequent2(nums, 5));
            if(!s1.equals(s2)) {
                System.out.println("----" + Arrays.toString(nums));
                System.out.println(s1);
                System.out.println(s2);
            }
        }
    }
    int[] generate(int len) throws Exception {
        List<Integer> list=new ArrayList<>();
        int[] nums = ArrayGenerator.intArrayDinstinct(len, 1, 20);
//        System.out.println(Arrays.toString(nums));
        for(int i=0; i<nums.length; i++){
            for(int j=0; j<nums[i] ; j++) list.add(i);
        }
        int[] res = new int[list.size()];
        for(int i=0; i<list.size(); i++) res[i] = list.get(i);
        return res;
    }
    public int[] topKFrequent(int[] nums, int k) {
        int n=nums.length, size=1;

        Map<Integer, Integer> map =new HashMap<>();
        for(int i=0; i<n; i++){
            map.put(nums[i], map.getOrDefault(nums[i], 0)+1);
        }
        int[][] heap= new int[n+1][2]; int s=0;
        for(Map.Entry<Integer,Integer> entry:map.entrySet()){
            heap[++s]=new int[]{entry.getKey(), entry.getValue()};
        }
        //init heap
        for(int i=s; i>1; i--){
            if(heap[i][1]>heap[i/2][1]){
                swap(heap, i, i/2);
                heapify(heap, s, i);
            }
        }
//        PrintUtils.print2DIntArray(heap);
        int[] result = new int[k]; int cnt=0;
        while(cnt<k && s>0){
            result[cnt++] = heap[1][0];
            swap(heap, 1, s);
            s--;
            heapify(heap,s, 1);
        }
//        result[cnt]=heap[1][0];
        return result;

    }
    void swap(int[][] res, int p1, int p2){
        int[] temp = res[p1]; res[p1]=res[p2]; res[p2]=temp;
    }
    void heapify(int[][] res, int s, int start){
        int i=start, left=2*i, right=left+1;
        while(left<=s){
            int p = right>s ? left : (res[left][1]>res[right][1] ? left : right);
            if(res[p][1]>res[i][1]){
                swap(res, p, i);
                i=p; left=2*i; right=left+1;
            }else{
                return;
            }
        }
    }

    public int[] topKFrequent2(int[] nums, int k) {
        int n=nums.length, size=1;

        Map<Integer, Integer> map =new HashMap<>();
        for(int i=0; i<n; i++){
            map.put(nums[i], map.getOrDefault(nums[i], 0)+1);
        }
        Queue<int[]> queue= new PriorityQueue<>((p1, p2)->p2[1]-p1[1]);
        for(Map.Entry<Integer,Integer> entry:map.entrySet()){
            queue.add(new int[]{entry.getKey(), entry.getValue()});
        }
        // System.out.println(queue.toString());
        int[] res= new int[k]; int s=0;
        while(!queue.isEmpty()){
            res[s++] = queue.poll()[0];
            if(s==k) return res;
        }
        return res;

    }
}
