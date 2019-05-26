package leetcode.realtest.realTest20190526;

/**
 * @author shibing
 * @since 2019/5/26 11:37
 */
public class GrumpyBookstoreOwner {
    public static void main(String[] args) {
        GrumpyBookstoreOwner owner=new GrumpyBookstoreOwner();
        int[] customers = {1,0,1,2,1,1,7,5}, grumpy = {0,1,0,1,0,1,0,1};int X = 3;
        System.out.println(owner.maxSatisfied(customers, grumpy, X));
    }
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int n=customers.length, max=0, cnt=0, sum=0;
        for (int i = 0; i < n; i++) {
            sum+=grumpy[i]==0?customers[i]:0;
            cnt+=grumpy[i]==1?customers[i]:0;
            if(i>=X){
                cnt-=grumpy[i-X]==1? customers[i-X]:0;
            }
            max=Math.max(max,cnt);
        }
        return sum+max;
    }
}
