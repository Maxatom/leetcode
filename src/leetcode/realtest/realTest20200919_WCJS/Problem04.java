package leetcode.realtest.realTest20200919_WCJS;

public class Problem04 {
    public static void main(String[] args) {
        Problem04 problem04 = new Problem04();
        int k=1, n=26;
        k=2; n=1;
        k=5; n=20;
        k=2; n=3;
        System.out.println(problem04.keyboard(k,n));
    }
    public int keyboard(int k, int n) {
        return A(26*k, n);
    }
    int MOD=(int)1e9+7;
    int A(int n, int m){
        if(n<m) return 0;
        long res1=1;
        for(int i=0; i<m; i++){
            System.out.println(n-i);
            res1= res1*(n-i);
            res1%=MOD;
        }
        return (int)res1;
    }
}
