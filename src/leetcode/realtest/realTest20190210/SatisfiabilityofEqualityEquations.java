package leetcode.realtest.realTest20190210;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Shibing
 * @since 2019-02-15 17:01:15
 **/
public class SatisfiabilityofEqualityEquations {
    public static void main(String[] args) {
        SatisfiabilityofEqualityEquations equa=new SatisfiabilityofEqualityEquations();
        String[] A=new String[]{"a==b","b!=a"};
//        A=new String[]{"b==a","a==b"};
//        A=new String[]{"a==b","b==c","a==c"};
//        A=new String[]{"a==b","b!=c","c==a"};
//        A=new String[]{"c==c","b==d","x!=z"};
//        A=new String[]{"a==b"};
//        A=new String[]{"b==b"};
//        A=new String[]{"a!=b"};
//        A=new String[]{"b!=b"};
//        A=new String[]{"c==c","f!=a","f==b","b==c"};
        A=new String[]{"f==a","a==b","f!=e","a==c","b==e","c==f"};
        System.out.println(equa.equationsPossible1(A));
        System.out.println(equa.equationsPossible(A));
    }

    //O(N) union find
    int[] uf=new int[26];
    public boolean equationsPossible1(String[] equations) {
        for (int i = 0; i < 26; i++) uf[i]=i;
        for (String e:equations)
            if (e.charAt(1) == '=') uf[find(e.charAt(0) - 'a')] = find(e.charAt(3) - 'a');
        for (String e:equations)
            if(e.charAt(1)=='!' && find(e.charAt(0)-'a')==find(e.charAt(3)-'a')) return false;
        return true;
    }
    public int find(int x) {
//        PrintUtils.printIntArray(uf);
        if (x != uf[x]) uf[x] = find(uf[x]);
        return uf[x];
    }
    //recursive
    public int find1(int x){
        return x==uf[x]?uf[x]:find(uf[x]);
    }
    //loop
    public int find2(int x){
        while (x!=uf[x]) x=uf[x];
        return x;
    }
    //O(N^2)
    public boolean equationsPossible(String[] equations) {
        Arrays.sort(equations, Comparator.comparingInt(p->-p.charAt(1)));
        for (int i = 0; i < equations.length; i++) {
            char a=equations[i].charAt(0), b=equations[i].charAt(3);
            if(equations[i].charAt(1)=='='){
                if(a==b) continue;
                for (int j = i+1; j < equations.length; j++)
                    equations[j]=equations[j].replace(a, b);
            }else if(a==b) return false;
        }
        return true;
    }
}
