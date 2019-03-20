package leetcode.realtest.realTest20190106;

import utils.ThreeTuple;
import utils.ThreeTuple;
import utils.Tuple;
import utils.Tuple;
import utils.PrintUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author shibing
 * @since 2019/1/6 10:39
 */
public class PowerfulIntegers {
    public static void main(String[] args) {
        PowerfulIntegers power=new PowerfulIntegers();
        ThreeTuple<Integer,Integer,Integer> testcases=Tuple.tuple(2,3,10);
        testcases=Tuple.tuple(3,5,15);
        testcases=Tuple.tuple(1,1,1000000);
        testcases=Tuple.tuple(2,1,1000000);
        testcases=Tuple.tuple(1,1,0);
        List<Integer> list=power.powerfulIntegers(testcases.first,testcases.second,testcases.third);
        PrintUtils.printList(list, p->p+"");
//        list.forEach(System.out::println);
    }
    public List<Integer> powerfulIntegers(int x, int y, int bound) {
        Set<Integer> results=new HashSet<>();
        for (int i = 1; i < bound; i*=x) {
            for (int j = 1; i+j <= bound; j*=y) {
                results.add(i+j);
                if(y==1) break;
            }
            if(x==1) break;
        }
        return new ArrayList<>(results);
    }
}
