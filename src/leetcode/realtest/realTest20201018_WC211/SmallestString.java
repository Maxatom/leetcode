package leetcode.realtest.realTest20201018_WC211;

import java.util.Set;
import java.util.TreeSet;

public class SmallestString {
    public static void main(String[] args) {
        TreeSet<String> set = new TreeSet<>();
        set.first();
    }

    public String findLexSmallestString(String s, int a, int b) {
        TreeSet<String> set = new TreeSet<>();
        set.add(s);
        dfs(s, a, b, set);
        return set.first();
    }
    void dfs(String s, int a, int b, TreeSet<String> set){
        String s1 = addS(s , a);
        if(!set.contains(s1)){
            set.add(s1);
            dfs(s1, a, b, set);
        }
        String s2 = rollS(s, b);
        if(!set.contains(s2)){
            set.add(s2);
            dfs(s2, a, b, set);
        }
    }

    String addS(String s, int a){
        StringBuilder res = new  StringBuilder();
        for(int i=0; i<s.length(); i++){
            if(i%2==0)
                res.append(s.charAt(i));
            else
                res.append((char)((s.charAt(i)-'0'+a)%10));
        }
        return res.toString();
    }

    String rollS(String s, int b){
        StringBuilder res = new StringBuilder();
        for(int i=s.length()-b; i<s.length(); i++){
            res.append(s.charAt(i));
        }
        for(int i=0; i<s.length()-b; i++){
            res.append(s.charAt(i));
        }
        return res.toString();
    }
}
