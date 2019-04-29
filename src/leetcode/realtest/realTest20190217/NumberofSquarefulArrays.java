package leetcode.realtest.realTest20190217;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shibing
 * @since 2019/2/17 14:56
 */
public class NumberofSquarefulArrays {
    public static void main(String[] args) {
        NumberofSquarefulArrays arrays=new NumberofSquarefulArrays();
        int[] A= new int[]{1,17,8}; //2
        A=new int[]{2,2,2}; //1
        A=new int[]{2,2,2,7}; //4
//        A=new int[]{3,6,19}; // 2
//        A=new int[]{0,1}; //2
//        A=new int[]{0,0,1}; //3
//        A=new int[]{1,0,0,1}; //3
//        A=new int[]{0,0,0,1,1,1}; //4
//        A=new int[]{0,4,0,4,0,4};//2
//        A=new int[]{2,2,2,2,2,2,2,2,2,2,2};


        System.out.println(arrays.numSquarefulPerms(A));
        System.out.println(arrays.numSquarefulPerms1(A));
//        arrays.cache.forEach((k,v)->System.out.print("("+Integer.toBinaryString(k)+","+v+")->"));
    }

    //sort
    public int numSquarefulPerms1(int[] A) {
        Arrays.sort(A);
        boolean[] visited=new boolean[A.length];
        return recursive(A, visited, -1, 0, 0);
    }
    public int recursive(int[] A, boolean[] visited, int pre, int visitCnt, int res){
        if(visitCnt==A.length) return ++res;
        int count=0;
        for (int i = 0; i < A.length; i++) {
            if(visited[i] || i>0 && A[i]==A[i-1] && !visited[i-1] || pre!=-1 && !isSquare(A[i]+pre)) continue;
            visited[i]=true;
            count+=recursive(A, visited, A[i], visitCnt+1, res);
            visited[i]=false;
        }
        return count;
    }

    //N*2^N
    public int numSquarefulPerms(int[] A) {
        Map<Integer,Integer> map=new HashMap<>();
        for (int i : A) map.put(i, map.getOrDefault(i, 0)+1);
        int rep=1;
        for (int i: map.values()) rep*=fact(i);
        int state=0;
        int[] P=new int[A.length];
        int count = recursive(A, -1, A.length, state, P);
//        System.out.println("count="+count+", rep="+rep);
        return count/rep;
    }

    //top-down dp, backtrace
    private Map<Integer,Integer> cache=new HashMap<>();
    public int recursive(int[] A, int pre, int rem, int state, int[] P){
        if(rem==0) {
//            PrintUtils.printArray(P);
            return 1;
        }
        int key=(pre+2<<A.length)+state;
        if(cache.containsKey(key)) return cache.get(key);
        int count=0;
        for (int i = 0; i < A.length; i++) {
            int a=A[i], bit=1<<(A.length-i-1);
            if((state&bit)==0 && (pre==-1 || isSquare(a+A[pre]))) {
                P[A.length-rem]=a;
                count += recursive(A, i, rem-1,state|bit, P);
            }
        }
        cache.put(key, count);
        return count;
    }

    private Map<Integer,Boolean> square=new HashMap<>();
    public boolean isSquare(int x){
        if(square.containsKey(x)) return square.get(x);
        int s=(int)Math.sqrt(x);
        boolean res=s*s==x;
        square.put(x, res);
        return res;
    }
    public int fact(int x){
        int result=1;
        while (x>1) result*=x--;
        return result;
    }
}
