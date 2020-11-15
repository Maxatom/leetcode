package leetcode.realtest.realTest20201011_WC210;

public class SplitString {
    public static void main(String[] args) {
        SplitString split = new SplitString();
        String a ="abdef" , b="fecab";
        a = "ulacfd"; b= "jizalu";
        System.out.println(split.checkPalindromeFormation(a,b));
    }

    public boolean checkPalindromeFormation(String a, String b) {
        return deal(a,b) || deal(b,a);
    }

    boolean deal(String a, String b){
        int n = a.length(), i=(n-1)/2;
        for(; i>=0; i--){
            if(b.charAt(i)!=b.charAt(n-i-1)) break;
        }
        System.out.println();
        if(i==-1) return true;
        int j=0;
        for(; j<=i; j++){
            if(a.charAt(j)!=b.charAt(n-1-j)) break;
        }
        if(j==i+1) return true;

        for(i=n/2; i>=0; i--){
            if(a.charAt(i)!=a.charAt(n-i-1)) break;
        }
        if(i==-1) return true;
        j=0;
        for(; j<=i; j++){
            if(a.charAt(j)!=b.charAt(n-1-j)) break;
        }
        if(j==i+1) return true;
        return false;
    }
}
