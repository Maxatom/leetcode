package leetcode.problems.math;

public class PermutationSequence {
    public static void main(String[] args) {
        PermutationSequence perm = new PermutationSequence();
        int n=3 , k=3;
        System.out.println(perm.getPermutation(n,k));
    }
    public String getPermutation(int n, int k) {
        char[] ch=new char[n+1];
        for(int i=1;i<=n;i++) ch[i]=(char)(i+'0');
        recursive(n,k,1,ch);
        return new String(ch, 1, n);

    }
    char[] recursive(int n, int k,int i, char[] ch){
        if(i==n) return null;
        char[] cht = recursive(n,k,i+1,ch);
        if(cht!=null) return cht;
        swap(i,i+1,ch);
        cht=recursive(n,k,i+1,ch);
        if(cht!=null || cnt==k) return ch;
        return null;
    }
    int cnt=0;
    void swap(int x,int y,char[] ch){
        char c=ch[x]; ch[x]=ch[y]; ch[y]=c;
        cnt++;
    }
}
