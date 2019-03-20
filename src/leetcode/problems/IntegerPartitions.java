package leetcode.problems;

/**
 * 整数拆分问题：
 * 将正整数 N 拆分成若干个整数的和，使拆分成的数是 2 的非负整数幂的形
 * 式。问有多少种拆分方案。如果两个方案仅有数的顺序不同，它们算作同一种方
 * 案
 * @author Shibing
 * @since 2018-12-04 15:15:54
 **/

public class IntegerPartitions {
    public static void main(String[] args) {
        IntegerPartitions partitions=new IntegerPartitions();
        System.out.println(partitions.partition(9,9));
        System.out.println(partitions.partition(36));
//        1,1,1,1,1,1;  2,1,1,1,1; 2,2,1,1; 4,1,1;4,2; 2,2,2; i=6  6
        //1,1,1,1,1,1,1; 2,1,1,1,1,1; 2,2,1,1,1; 2,2,2,1; 4,1,1,1;4,2,1;   i=7   6
//        1,1,1,1,1,1,1,1; 2,1,1,1,1,1,1; 2,2,1,1,1,1; 2,2,2,1,1; 2,2,2,2; 4,1,1,1,1; 4,2,1,1;4,2,2;4,4; i=8, 2  9

    }
    public int partition(int num){
        int i=0;
        while (num >= (1<<(i++)));
        return partition(num,i-2);
    }
    //F[i,j]=F[i-2^j, j] + F[i,j-1]
    //num为2^N
    public int partition(int num, int j){
        if(num==0||j==0) return 1;
        if(num<0) return 0;
        return partition(num-(1<<j), j)+partition(num, j-1);
    }
}
