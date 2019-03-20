package leetcode.realtest.realTest20190210;

/**
 * @author shibing
 * @since 2019/2/10 11:30
 */
public class BrokenCalculator {
    public static void main(String[] args) {
        BrokenCalculator bc=new BrokenCalculator();
        int X = 2, Y = 3;//2
//        X = 5; Y = 8;//2
//        X = 3; Y = 10; //3
//        X = 1024; Y = 1;
//        X=1; Y= 1000000000;
//        X=1; Y= 5; //2
//        X=1; Y= 10; //6
//        X=8; Y=9; //5

        System.out.println(bc.brokenCalc(X,Y));

    }

    public int brokenCalc(int X, int Y) {
        int n=0;
        while (Y>X){
            Y=(Y&1)==1?Y+1:Y>>1;
            n++;
        }
        return n+X-Y;
    }
}
