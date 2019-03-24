package leetcode.Mock.date20190324;

/**
 * @author shibing
 * @since 2019/3/24 14:36
 */
public class RotateString {
    public static void main(String[] args) {
        RotateString date=new RotateString();
        String A = "abcde", B = "cdeab";
        A = "abcde"; B = "abced";
        A=""; B="";
        System.out.println(date.rotateString(A,B));
    }
    public boolean rotateString(String A, String B) {
        if(A.length()!=B.length()) return false;
        if(A.equals("") && B.equals("")) return true;
        for (int i = 0; i < A.length(); i++) {
            char bf=B.charAt(0);
            if(bf==A.charAt(i)){
                if(compare(A,B, i)) return true;
            }
        }
        return false;
    }
    public boolean compare(String A, String B, int start){
        for (int i = 1, j=start+1; i < B.length() && j<A.length(); i++, j++) {
            if(B.charAt(i)!=A.charAt(j)) return false;
        }
        for (int i = start-1, j=B.length()-1; i >=0 ; i--, j--) {
            if(B.charAt(j) != A.charAt(i)) return false;
        }
        return true;
    }
}
