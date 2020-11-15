package leetcode.realtest.realTest20200919_WCJS;

public class Problem01 {
    public static void main(String[] args) {
        Problem01 problem01 = new Problem01();
        int n=2, k=4;
        n=3; k=8;
        System.out.println(problem01.paintingPlan(n,k));
    }
    public int paintingPlan(int n, int k) {
        if(k==0 || k==n*n) return 1;
        int c=0;
        for(int i=0; i<=n; i++){
            for(int j=0; j<=n; j++){
                if(i*n+j*n-i*j==k)
                    c+= calc(n,i)*calc(n,j);
            }
        }
        return c;
    }
    int calc(int n, int k){
        if(k==0 || n==k) return 1;
        int res1=1;
        for(int i=0; i<k; i++){
            res1*=n-i;
        }
        for(int i=1;i<=k; i++){
            res1/=i;
        }
        return res1;
    }
}
