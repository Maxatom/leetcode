package leetcode.realtest.realTest20190609;

import utils.PrintUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author shibing
 * @since 2019/6/9 10:36
 */
public class OccurrencesAfterBigram {
    public static void main(String[] args) {
        OccurrencesAfterBigram bigram=new OccurrencesAfterBigram();
        String text = "alice is a good girl she is a good student", first = "a", second = "good";
//        text = "we will we will rock you"; first = "we"; second = "will";
//        text = "a a a a"; first = "a"; second = "a";
        PrintUtils.printArray(bigram.findOcurrences(text, first, second));
        PrintUtils.printArray(bigram.findOcurrences1(text, first, second));
    }
    //O(N)
    public String[] findOcurrences1(String text, String first, String second) {
        String[] words=text.split(" ");
        String[] res=new String[words.length];
        int k=0;
        for (int i = 0; i +2< words.length; i++)
            if(words[i].equals(first) && words[i+1].equals(second)) res[k++]=words[i+2];
        return Arrays.copyOf(res, k);
    }

    public String[] findOcurrences(String text, String first, String second) {
        List<String> list=new ArrayList<>();
        String str=first+" "+second+" ";
        int i=0;
        while (i<text.length()){
            int c=text.indexOf(str, i);
            if(c==-1) break;
            int begin=c+str.length(), end=text.indexOf(" ", begin);
            list.add(text.substring(begin, end==-1?text.length():end));
            i=c+first.length();
        }
        return list.toArray(new String[list.size()]);
    }
}
