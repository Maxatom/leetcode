package leetcode.realtest.realTest20200405_WC183;

/**
 * @author ShiBing
 * @projectName leetcode
 * @date 2020/4/511:36
 */
public class NumberOfSteps {
    public static void main(String[] args) {
        NumberOfSteps steps=new NumberOfSteps();
        String s = "1101";
        s = "10";
        System.out.println(steps.numSteps(s));
    }
    public int numSteps(String s) {
        char[] ca=s.toCharArray();
        int n=ca.length,sum=0, end=0;
        for(int i=0; i<n; i++){
            if(ca[i]=='1'){
                end=i; break;
            }
        }
        for(int i=n-1; i>end; i--){
            if(ca[i]!='0'){
                plus(ca, i);
                sum++;
            }
            sum++;
        }
        return ca[end]=='0'?sum+1:sum;

    }
    void plus(char[] ca, int s){
        int i=s;
        while(i>=0&&ca[i]=='1'){
            ca[i]='0'; i--;
        }
        if(i>=0) ca[i]='1';
    }
}
