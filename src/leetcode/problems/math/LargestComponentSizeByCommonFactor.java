package leetcode.problems.math;

import utils.PrintUtils;
import utils.Utils;

import java.util.*;

/**
 * @author Shibing
 * @since 2018-12-07 13:09:04
 **/
public class LargestComponentSizeByCommonFactor {
    public static void main(String[] args) throws Exception {
        LargestComponentSizeByCommonFactor factor=new LargestComponentSizeByCommonFactor();
//        String s="[4,6,15,35]";
//        String s="[20,50,9,63]";
//        String s="[2,3,6,7,4,12,21,39]";
        String s="[9, 38, 7, 39, 29, 8, 22, 11, 42, 40, 41, 31, 23, 0, 27, 13, 2, 5, 4, 18]";

        int[] test= PrintUtils.convertStringToIntArray(s.toString());
//        test=ArrayGenerator.intArrayDinstinct(20000,0 , 100000);
//        test=PrintUtils.readArrayFromFile("E:\\MyProjects\\springdemo\\testlearning\\src\\main\\java\\leetcode\\data.txt");
//        test=PrintUtils.readArrayFromFile("E:\\javastudy\\multimodule\\testlearning\\src\\main\\java\\leetcode\\data.txt");
        PrintUtils.printArray(test);

        long start=System.currentTimeMillis();
        System.out.println(factor.largestComponentSize(test)+", time="+(System.currentTimeMillis()-start)+
                ", count="+factor.count+", count1="+factor.count1+", times="+factor.times);

        start=System.currentTimeMillis();
        System.out.println(factor.largestComponentSize1(test)+", time="+(System.currentTimeMillis()-start)+
                ", count="+factor.count+", count1="+factor.count1+", times="+factor.times);
    }

    public int largestComponentSize1(int[] A) {
        int max=Utils.max(A);
        ArrayList<Integer>[] list=new ArrayList[(int)Math.ceil(Math.sqrt(max))+1];

        for (int i = 0; i < A.length; i++) {
            int r=(int)Math.ceil(Math.sqrt(A[i]));
            for (int j = 2; j <= r; j++) {
                if(A[i]%j==0) {
                    if(list[j]==null) list[j]=new ArrayList<>();
                    list[j].add(A[i]);
                }
            }
        }
        int maxlength=0;
        for (int i = 1; i < list.length; i++) {
            if(list[i]!=null&&list[i].size()>maxlength) maxlength=list[i].size();
        }
        return maxlength;
    }

    int count=0;
    int count1=0;
    int times=0;
    //O(N^2) TLE
    public int largestComponentSize(int[] A) {
        Map<Integer,List<Integer>> map=new HashMap<>();
        map.put(A[0], new LinkedList<>());
        map.get(A[0]).add(A[0]);
        for (int i = 1; i < A.length; i++) {
            int belong=0;
            boolean flag=false;
            Iterator<Map.Entry<Integer, List<Integer>>> iterator=map.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry<Integer, List<Integer>> entry= iterator.next();
                for (Integer elem:entry.getValue()){
                    times++;
                    if(judge(elem,A[i])) {
                        flag=true;
                        if(belong==0) {
                            count1++;
                            belong=entry.getKey();
                            entry.getValue().add(A[i]);
                            break;
                        }else {
                            count++;
                            map.get(belong).addAll(entry.getValue());
//                            map.remove(entry.getKey());
                            iterator.remove();
                            break;
                        }
                    }
                }
            }
            if(!flag){
                map.put(A[i], new LinkedList<>());
                map.get(A[i]).add(A[i]);
            }
        }
        return map.values().stream().map(List::size).reduce(Integer::max).get();
    }
    public boolean judge(int a , int b){
        if(a==1||b==1) return false;
        if((a&1)==0&&(b&1)==0) return true;
        return Utils.gcd(a,b)>1;
    }
}
