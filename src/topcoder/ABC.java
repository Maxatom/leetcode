package topcoder;

/**
 * @author Shibing
 * @since 2019-04-04 19:20:40
 **/
public class ABC {
    public static void main(String[] args) {
        ABC abc=new ABC();
        int N=3, K=3;
        N=3; K=0;
        N=5; K=10;
        N=15; K=36;
        System.out.println(abc.createString(N, K));
    }
    String createString(int N, int K){
        if(K==0){
            StringBuilder sb=new StringBuilder();
            for (int i = 0; i < N; i++) {
                sb.append('A');
            }
            return sb.toString();
        }
        int[] letters=new int[3];
        String res=create("", letters, N, K);
        return res==null?"":res;
    }

    String create(String s, int[] letters, int N, int K){
        if(K==0) return s;
        if(N==0 || K<0) return null;
        for (int i = 0; i < 3; i++) {
            letters[i]++;
            String s1;
            if(i==0)
                s1=create(s+"A", letters, N-1, K);
            else if(i==1)
                s1=create(s+"B", letters, N-1, K-letters[0]);
            else
                s1=create(s+"C", letters, N-1, K-letters[0]-letters[1]);
            if(s1!=null) return s1;
            letters[i]--;
        }
       return null;
    }
}
