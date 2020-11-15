package leetcode.problems;

import java.util.ArrayList;
import java.util.List;

public class Game24 {
    public static void main(String[] args) {
        Game24 game24=new Game24();
        int[] nums = {0,1,2,6};
        System.out.println(game24.judgePoint24(nums));

    }
        public boolean judgePoint24(int[] nums) {
            List<Double> list = new ArrayList<>();
            for(int i=0; i<nums.length; i++){
                list.add((double)nums[i]);
            }
            return dfs(list);
        }
        static final double EPSILON=1e-6, TARGET=24;

        boolean dfs(List<Double> list){
            if(list.size()==0) return false;
            if(list.size()==1){
                return Math.abs(list.get(0)-TARGET)<=EPSILON;
            }
            for(int i=0; i<list.size(); i++){
                for(int j=0; j<list.size(); j++){
                    if(j==i) continue;
                    List<Double> list1 =new ArrayList<>();
                    for(int k=0;k<list.size();k++){
                        if(k!=i && k!=j) list1.add(list.get(k));
                    }
                    for(int k=0; k<4; k++){
                        double p1=list.get(i), p2=list.get(j);
                        switch(k){
                            case 0: list1.add(p1+p2); break;
                            case 1: list1.add(p1-p2); break;
                            case 2: list1.add(p1*p2); break;
                            case 3: if(p2!=0 && Math.abs(p1/p2)>EPSILON)
                                list1.add(p1/p2);
                                break;

                        }
                        if(dfs(list1)) return true;
                        if(list1.size()!=0) list1.remove(list1.size()-1);
                    }
                }
            }
            return false;
        }
}
