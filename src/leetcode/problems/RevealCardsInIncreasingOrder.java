package leetcode.problems;

import utils.PrintUtils;

import java.util.Arrays;

/**
 * @author Shibing
 * @since 2018-12-06 12:49:08
 **/
public class RevealCardsInIncreasingOrder {
    public static void main(String[] args) throws Exception {
        RevealCardsInIncreasingOrder revealCard=new RevealCardsInIncreasingOrder();
        String s="[2,13,3,11,5,17,7]";
//        String s1="[17,13,11,2,3,5,7]";
//        String s1="[1,3,4]";
//        String s1="[1,3]";
        String s1="[1]";

        int[] test= PrintUtils.convertStringToIntArray(s1);
//        test=ArrayGenerator.intArrayDinstinct(20, 0, 30);
        int[] result=revealCard.deckRevealedIncreasing(test);
        System.out.print("reveal result:");
        PrintUtils.printArray(result);
        System.out.print("restored:");
        PrintUtils.printArray(revealCard.revealCards(result));
    }
    //恢复顺序
    public int[] revealCards(int[] deck){
        int[] deck2=Arrays.copyOf(deck, deck.length<<1);
        int[] result=new int[deck.length];
        int j=0;
        int tail=deck.length;
        for (int i = 0; i < deck2.length; i+=2) {
            result[j++]=deck2[i];
            deck2[tail++]=deck2[i+1];
        }
        return result;
    }

    //逆向操作，O(NlogN)
    public int[] deckRevealedIncreasing(int[] deck) {
        Arrays.sort(deck);
        int[] result=new int[deck.length<<1];
        int j=0;
        int head=0;
        for (int i = deck.length-1; i >= 0; i--) {
            result[j++]=deck[i];
            result[j++]=result[head++];
        }
        head--;
        int[] ret=new int[deck.length];
        j=0;
        for (int i = head+deck.length-1; i >= head; i--) {
            ret[j++]=result[i];
        }
        return ret;
    }
}
