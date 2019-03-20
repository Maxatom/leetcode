package leetcode.problems.dynamicPrograming;

import utils.RandomGenerator;
import utils.RandomGenerator;
import utils.PrintUtils;
import utils.PrintUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Shibing
 * @since 2018-12-17 14:42:29
 **/
public class DeleteColumnsToMakeSortedII {
    public static void main(String[] args) throws Exception {
        DeleteColumnsToMakeSortedII sortedII=new DeleteColumnsToMakeSortedII();
        List<String> tCases=new ArrayList<>();
        tCases.add("[a]");
        tCases.add("[a,b,c]");
        tCases.add("[aa,ae,ab]");
        tCases.add("[ca,ab,ac]");
        tCases.add("[ca,ae,ac]");
        tCases.add("[aa,be,fc, me]");//n5
        tCases.add("[aeix,beka,aekb, menz]");
        tCases.add("[\"ca\",\"bb\",\"ac\"]");
        tCases.add("[\"xc\",\"yb\",\"za\"]");
        tCases.add("[\"zyx\",\"wvu\",\"tsr\"]");
        tCases.add("[\"xga\",\"xfb\",\"yfa\"]"); //n10
        tCases.add("[\"doeeqiy\",\"yabhbqe\",\"twckqte\"]"); //3
        tCases.add("[\"dbyucsndxb\",\"ewuahnjszs\",\"ndywixgsip\",\"aexjiusjwb\",\"zowkkykbpt\",\"wesumzisir\",\"rptinecjts\",\"eixwovxhtx\",\"mhlcpfwfps\",\"ybsuszcccc\"]");// 5
        tCases.add("[cbcaaca,bccbdba,acdadcc,acbbada,adcbdab]");
        for (int i = 0; i < tCases.size(); i++) {

            String[] A = PrintUtils.convertStringToStringArray(tCases.get(i));
//            A=sortedII.generateCase(100);
//            PrintUtils.printArray(A);
            long start=System.currentTimeMillis();
            int result1=sortedII.minDeletionSize(A);
            System.out.println(result1+" time="+(System.currentTimeMillis()-start)+" times1="+sortedII.times1);

            start=System.currentTimeMillis();
            int result2=sortedII.minDeletionSize2(A);
            System.out.println(result2+" time2="+(System.currentTimeMillis()-start)+" times2="+sortedII.times2);

            if(result1!=result2) throw new Exception("Not Equal!");
//            start=System.currentTimeMillis();
//            System.out.println(sortedII.minDeletionSize1(A)+" time1="+(System.currentTimeMillis()-start));
        }
    }

    //按列处理, 下一列只受上一列影响
    int times2=0;
    public int minDeletionSize2(String[] A) {
        int dels = 0;
        int[] lastrelations = new int[A.length];
        int[] relations = new int[A.length];
        for (int i = 0; i < A[0].length(); i++) {
            boolean delFlag=false;
            for (int j = 1; j < A.length; j++) {
                times2++;
                relations[j]=0;
                if(lastrelations[j]==0){
                    if (A[j].charAt(i) > A[j - 1].charAt(i)) {
                        relations[j] = 1;
                    }
                    if(A[j].charAt(i) < A[j-1].charAt(i)){
                        delFlag=true;
                        dels++;
                        break;
                    }
                }else {
                    relations[j]=lastrelations[j];
                }
            }
            if(!delFlag) {//滚动数组
                int[] temp=lastrelations;
                lastrelations=relations;
                relations=temp;
            }
        }
        // PrintUtils.printArray(cols);
        return dels;
    }

        //记录单词比较的列号, 失败的优化, 实际执行时间更长
    public int minDeletionSize1(String[] A) {
        int dels = 0;
        boolean[] cols = new boolean[A[0].length()];
        int[] cpCols=new int[A.length];
        for (int j = 1; j < A.length; j++) {
            for (int i = 0; i < A[0].length(); i++) {//比较单词，如果某一列不能使当前单词大于前一个单词，删除该列，重新比较
                if (!cols[i]&&i>=cpCols[j]) {
                    if (A[j].charAt(i) > A[j - 1].charAt(i)){ cpCols[j]=i;break;}
                    if (A[j].charAt(i) < A[j - 1].charAt(i)){ cols[i] = true; dels++; j = 0; break;}
                }
            }
        }
        // PrintUtils.printArray(cols);
        return dels;
    }
        //O(L*N)  回溯
    int times1=0;
    public int minDeletionSize(String[] A) {
        int dels=0;
        boolean[] cols=new boolean[A[0].length()];
        for (int j = 1; j < A.length; j++) {
            for (int i = 0; i < A[0].length(); i++) {//比较单词，如果某一列不能使当前单词大于前一个单词，删除该列，重新比较
                times1++;
                if(!cols[i]){
                    if(A[j].charAt(i)>A[j-1].charAt(i)) break;
                    if(A[j].charAt(i)<A[j-1].charAt(i)) {cols[i]=true; dels++; j=0;break;}
                }
            }
        }
//        PrintUtils.printArray(cols);
        return dels;
    }

    String[] generateCase(int length){
        Random rand=new Random(System.currentTimeMillis());
        int strlen=rand.nextInt(20)+1;
        return generateCase(length,strlen);
    }

    String[] generateCase(int length, int strlen){
        String[] result=new String[length];
        RandomGenerator.String string=new RandomGenerator.String(strlen);
        string.setCharBound('a', 'e');
        for (int i = 0; i < length; i++) {
            result[i] = string.next();
        }
        return result;
    }
}
