package leetcode.problems.dynamicPrograming;

import java.util.Arrays;

/**
 * @author Shibing
 * @since 2019-02-20 10:26:06
 **/
public class RemoveBoxes {
    class Pair{
        int color;
        int num;

        public Pair(int color, int num) {
            this.color = color;
            this.num = num;
        }
    }
    public static void main(String[] args) {
        RemoveBoxes box=new RemoveBoxes();
        int[] boxes=new int[]{1, 3, 2, 2, 2, 3, 4, 3, 1};
        boxes=new int[]{1,1,1};
        System.out.println(box.removeBoxes(boxes));
    }
    public int removeBoxes(int[] boxes) {
        return recursive(boxes, 0);
    }
    public int recursive(int[] boxes, int sum) {
        int start=0, count=0, max=0, value=0;
        for (int i = 0; i < boxes.length; i++) {
            if(boxes[i]!=-1){
                if(i==0||count==0) {
                    start = i; count=1; value=boxes[i];
                    boxes[i]=-1;
                } else if(value==boxes[i]) {
                    boxes[i]=-1;
                    count++;
                } else if(value!=boxes[i]) {
                    max=Math.max(max,recursive(boxes, sum+count*count));
                    //back trace
                    Arrays.fill(boxes, start, start+count, value);
                    count=0;
                    i--;
                }
            }
        }
        return sum+max;
    }
}
