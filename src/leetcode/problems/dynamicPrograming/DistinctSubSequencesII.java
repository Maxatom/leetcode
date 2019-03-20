package leetcode.problems.dynamicPrograming;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Shibing
 * @since 2018-11-13 11:38:23
 **/
public class DistinctSubSequencesII{
    public static void main(String[] args) {
        DistinctSubSequencesII sequencesII=new DistinctSubSequencesII();
        String s="zchmliaqdgvwncfatcfivphddpzjkgyygueikthqzyeeiebczqbqhdytkoawkehkbizdmcnilcjjlpoeoqqoqpswtqdpvszfaksn";
        System.out.println("length: "+ s.length());
//        s="zfaksn";
        System.out.println(sequencesII.distinctSubseqII(s));
    }

    public int distinctSubseqII(String S) {
        Map<Character,BigInteger> headSet=new HashMap<>();
        BigInteger count=subSeqCount(headSet,S);
        return (count.mod(BigInteger.valueOf(10).pow(9).add(BigInteger.valueOf(7l))).intValue());
    }

    //优化后的算法
    public int distinctSubseqII2(String S) {
        Map<Character,BigInteger> headSet=new HashMap<>();
        return 0;
    }


    public BigInteger subSeqCount(Map<Character,BigInteger> headSet, String S){
        char headChar=S.charAt(0);
        if(S.length()==1) {
            headSet.put(headChar, BigInteger.valueOf(1));
            return BigInteger.valueOf(1l);
        }
        BigInteger subsetcount=subSeqCount(headSet,S.substring(1));
        BigInteger thisSetCount;
        if(headSet.get(headChar)!=null) {
            thisSetCount=BigInteger.valueOf(1l).add(subsetcount).add(subsetcount.subtract(headSet.get(headChar)));
            headSet.put(headChar, subsetcount.add(BigInteger.valueOf(1l)));
        }else {
            thisSetCount=BigInteger.valueOf(1l).add(subsetcount).add(subsetcount);
            headSet.put(headChar, subsetcount.add(BigInteger.valueOf(1l)));
        }
        //  System.out.println("String: " +S+" , " +thisSetCount);
        return thisSetCount;
    }


    public Map<Character,Integer[]> frequency(String S){
        Map<Character, Integer[]> elemSet=new HashMap<>();
        for (int i = 0; i < S.length(); i++) {
            char c=S.charAt(i);
            if(elemSet.get(c)!=null) elemSet.get(c)[0]++;
            else {
                elemSet.put(c, new Integer[2]);
                elemSet.get(c)[0]=1;
                elemSet.get(c)[1]=0;
            }
        }
        return elemSet;
    }

    //查找一个字符在字符串中出现的次数
    public int findOccurence( char c,String str){
        int occurence=0;
        for(int i=0;i<str.length();i++){
            if(c==str.charAt(i)) occurence++;
        }
        return occurence;
    }
}
