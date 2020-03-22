package leetcode.realtest.realTest20200322_WC181;

/**
 * @author ShiBing
 * @projectName leetcode
 * @date 2020/3/2213:47
 */
public class FourDivisors {
    public static void main(String[] args) {
        FourDivisors four=new FourDivisors();
        int[] nums=new int[]{100000};
        System.out.println(four.sumFourDivisors(nums));
    }
    public int sumFourDivisors(int[] nums) {
        int sum=0;
        for(int n:nums){
            sum += check(n);
        }
        return sum;
    }
    int check(int num){
        int end=num, sum=num+1, cnt=0;
        for(int i=2;i<end;i++){
            if(cnt>1) return 0;
            if(num%i==0){
                if(i==num/i) return 0;
                cnt++;
                sum+= i+ num/i;
                end=num/i;
            }
            System.out.println("i="+i+", end="+end);
        }
        System.out.println("num="+num+", sum="+sum+",cnt="+cnt);
        return cnt==1?sum:0;
    }
}
