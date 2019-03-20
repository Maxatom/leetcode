package leetcode.realtest.realTest20190127;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shibing
 * @since 2019/1/27 10:34
 */
public class StringWithoutAAAorBBB {
    public static void main(String[] args) {
        StringWithoutAAAorBBB  sw=new StringWithoutAAAorBBB();
        int A=1,B=2;
//        A=0; B=2;
//        A=0; B=1;
//        A=0;B=0;
//        A=1; B=0;
//        A=2; B=0;
//        A=6;B=6;
//        A=100;B=100;
//        A=1; B=3;
//        A=1;B=4;
//        A=4;B=1;
//        A=43;B=69;
//        System.out.println(sw.strWithout3a3b1(A,B));
        System.out.println(sw.strWithout3a3b2(A,B));
        System.out.println(sw.strWithout3a3b3(A,B));
    }
    public String strWithout3a3b3(int A, int B) {
        StringBuilder sb=new StringBuilder(A+B);
        char a='a', b='b';
        int  i=A, j=B;
        if(A<B){ a='b'; b='a'; i=B; j=A; }
        while (i-->0){
            sb.append(a);
            if(i>j){ sb.append(a); i--;}
            if(j-->0) sb.append(b);
        }
        return sb.toString();
    }

    public String strWithout3a3b2(int A, int B) {
        int L=A+B;
        StringBuilder result=new StringBuilder();
        String a="a", b="b";
//        boolean isA=A>=B;
        for (int i = 0; i < L; i++) {
            if(i>1&&result.charAt(result.length()-1)==result.charAt(result.length()-2)){
                if(result.charAt(result.length()-1)=='a'){
                    result.append(b);
                    B--;
                } else{
                    result.append(a);
                    A--;
                }
            }else if(A>0&&(A>=B)) {
                result.append(a);
                A--;
            } else if(B>0&&(B>A)){
                result.append(b);
                B--;
            }
        }
        return result.toString();
    }

    public String strWithout3a3b1(int A, int B) {
        int x=A-B;
        StringBuilder str=new StringBuilder();
        if(A>=B) {
            int numA=A;
            int numB=B;
            int i=0;
            while (numA!=0 && numB!=0){
                if(i++<x) {
                    str.append("aa");
                    numA -= 2;
                }else {
                    str.append("a");
                    numA--;
                }
                str.append("b");
                numB-=1;
            }
            if(numA==2){
                str.append("aa");
            }else if(numA==1) str.append("a");
        }
        if(A<B) {
            int numA=A;
            int numB=B;
            x=-x;
            int i=0;
            while (numA!=0 && numB!=0){
                if(i++<x) {
                    str.append("bb");
                    numB -= 2;
                }else {
                    str.append("b");
                    numB--;
                }
                str.append("a");
                numA-=1;
            }
            if(numB==2){
                str.append("bb");
            }else if(numB==1) str.append("b");
        }
        return str.toString();
    }
}
