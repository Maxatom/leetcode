package leetcode.realtest.realTest20200105_WC170;

public class DecryptString {
    public static void main(String[] args) {
        DecryptString decryptString=new DecryptString();
        String s = "10#11#12";
        System.out.println(decryptString.freqAlphabets(s));
    }
    public String freqAlphabets(String s) {
        String[] chars={"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t"
        ,"u","v","w","x","y","z"};
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<s.length(); i++){
            if(i+2<s.length() && s.charAt(i+2)=='#'){
//                System.out.println(Integer.valueOf(s.substring(i,i+2)));
                sb.append(chars[Integer.valueOf(s.substring(i,i+2))-1]);
                i+=2;
            }else {
                sb.append(chars[s.charAt(i)-'0'-1]);
            }
        }
        return sb.toString();
    }
}
