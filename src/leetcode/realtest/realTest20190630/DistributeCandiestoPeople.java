package leetcode.realtest.realTest20190630;

import utils.PrintUtils;

public class DistributeCandiestoPeople {
    public static void main(String[] args) {
        DistributeCandiestoPeople candiesDis=new DistributeCandiestoPeople();
        int candies = 7, num_people = 4;
        candies = 10; num_people = 3;
        candies=100; num_people=5;
        candies=1; num_people=1;
        candies=90; num_people=4;
        PrintUtils.printArray(candiesDis.distributeCandies(candies, num_people));
    }


    //O(sqrt(N))
    public int[] distributeCandies(int candies, int num_people) {
        int[] result=new int[num_people];
        int cur=0;
        while (candies>0) {
            for (int i = 0; i < num_people; i++) {
                if(candies<=cur){
                    result[i]+=candies; candies=0; break;
                }
                result[i]+=++cur;
                candies-=cur;
                System.out.println("candies="+candies+", cur="+cur);
            }
        }
        return result;
    }
}
