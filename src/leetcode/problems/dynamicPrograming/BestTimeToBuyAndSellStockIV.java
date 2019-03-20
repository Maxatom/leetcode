package leetcode.problems.dynamicPrograming;

import utils.ArrayGenerator;
import utils.Tuple;
import utils.TwoTuple;
import utils.PrintUtils;

import java.util.*;

/**
 * @author Shibing
 * @since 2018-11-27 13:40:40
 **/
public class BestTimeToBuyAndSellStockIV {
    public static void main(String[] args) {
        BestTimeToBuyAndSellStockIV stock=new BestTimeToBuyAndSellStockIV();
        List<TwoTuple<String,Integer>> testCases=new ArrayList<>();
        testCases.add(Tuple.tuple("[]", 0));
        testCases.add(Tuple.tuple("[3]", 3));
        testCases.add(Tuple.tuple("[1,3]", 0));
        testCases.add(Tuple.tuple("[5,3]", 3));
        testCases.add(Tuple.tuple("[5,4,1]", 1)); // :5
        testCases.add(Tuple.tuple("[2,4,1]", 3));
        testCases.add(Tuple.tuple("[3,2,6,5,0,3]", 2));
        testCases.add(Tuple.tuple("[3,2,6,5,0,3,5,6,7,8]", 2));
        testCases.add(Tuple.tuple("[3,2,6,5,0,3,9,6,7,8]", 2));
        testCases.add(Tuple.tuple("[1,2,4,2,5,7,2,4,9,0,9]", 4)); //24  :10
        testCases.add(Tuple.tuple("[8,6,4,3,3,2,2,2,3,5,5,8,3,8,2,6]", 2));
        testCases.add(Tuple.tuple("[5,2,3,0,3,5,6,8,1,5]", 2));
        testCases.add(Tuple.tuple("[1,3,5,4,3,7,6,9,2,4]",2 ));
        testCases.add(Tuple.tuple("[0,3,4,6,6,7,8,9,10,13,15,15,18]", 2));
//        testCases.add(Tuple.tuple(, 29)); //24
//        testCases.add(Tuple.tuple(, 1000000000 ));
        int i=0;
        for(TwoTuple<String,Integer> testCase:testCases ){
            if(++i!=9) continue;
                int[] array= PrintUtils.convertStringToIntArray(testCase.first);
                array=ArrayGenerator.intArray((int)Math.pow(10,6),0, 1000);
//                PrintUtils.printIntArray(array);
            System.out.printf("------------------------------------\n testcase: %s \n", testCase);
                long start=System.currentTimeMillis();
//                stock.cache.clear();
//                System.out.println(stock.maxProfit(testCase.second, PrintUtils.convertStringToIntArray(testCase.first)));
//                System.out.printf(" times=%s, exetimes=%s, duration=%d, map.size=%d\n", stock.times,stock.exetimes, System.currentTimeMillis()-start, stock.cache.size());
//                PrintUtils.printIntArray(stock.getAscendingSubSeq(PrintUtils.convertStringToIntArray(testCase.first)));

//                stock.cache.clear(); stock.times=0;stock.exetimes=0;
//                start=System.currentTimeMillis();
//                int result=(stock.maxProfit1(testCase.second, array));
//                System.out.printf("maxProfit1: result=%d, times=%s, exetimes=%s, duration=%d, map.size=%d\n", result,stock.times,stock.exetimes, System.currentTimeMillis()-start, stock.cache.size());

                start=System.currentTimeMillis();
                int result =stock.maxProfit3(testCase.second, array);
                System.out.printf("maxProfit3: result=%d, duration=%d\n", result, System.currentTimeMillis()-start);

//                start=System.currentTimeMillis();
//                result=stock.maxProfit4(testCase.second, array);
//                System.out.printf("maxProfit4: result=%d,  duration=%d\n", result, System.currentTimeMillis()-start);

                start=System.currentTimeMillis();
                result=stock.maxProfit5(testCase.second, array);
                System.out.printf("maxProfit5: result=%d,  duration=%d\n", result, System.currentTimeMillis()-start);

//                stock.cache.clear(); stock.times=0;stock.exetimes=0;
//                start=System.currentTimeMillis();
//                System.out.println(stock.maxProfit2(testCase.second, PrintUtils.convertStringToIntArray(testCase.first)));
//                System.out.printf(" times=%s, exetimes=%s, duration=%d, map.size=%d\n", stock.times,stock.exetimes, System.currentTimeMillis()-start, stock.cache.size());

        }
    }

 /**
     * dp[i][j] 表示，进行 i次交易， 从0-j 天范围内，可以取得的最大的收益。
     * 那么，
     * dp[i][j] 可能有两种取值。
     * 第一， prices[j] 在整个买卖策略中，被卖掉了
     * 那么，一定是 [0, p] 完成了 i - 1次交易，然后在 p~j 天之间买入prices[x]  x in [p, j], 然后再在第 j 天 卖掉prices[j], 完成了 i 次交易。
     * p << [0, j - 1]
     *
     * 第二， prices[j] 在整个买卖策略中，并没有被卖掉。
     * 此时最大值是， dp[i][j - 1]
     *
     * 于是：
     * dp[i][j] = Math.max(dp[i][j - 1], maxProfit + prices[j]);
     * maxProfit 不断地记录， dp[i - 1][p] - prices[p] 的最大值
     *
     * @param prices 股票价格数组
     * @return 最大收益
     */
 //使用滚动数组优化
    public int maxProfit5(int k,int[] prices){
        if(k==0||prices==null||prices.length<2) return 0;
        int maxProfit=0;
        if(k>=(prices.length>>1)){
            for (int i = 1; i < prices.length; i++) {
                if(prices[i]>prices[i-1]) maxProfit+= prices[i]-prices[i-1];
            }
            return maxProfit;
        }

        int[] transPre=new int[k+1];
        int[] maxProfitHold=new int[k+1];
        for (int i = 1; i <= k ; i++) {
            maxProfitHold[i]=-prices[0];
        }
        int[] trans=new int[k+1];
        int[] temp;
        for (int i = 1; i < prices.length; i++) {
            for (int j = 1; j <= k; j++) {
                trans[j]=Math.max(transPre[j], maxProfitHold[j]+prices[i]);
                maxProfitHold[j]=Math.max(maxProfitHold[j], transPre[j-1]-prices[i]);
            }
            temp=transPre;
            transPre=trans;
            trans=temp;
        }
        return transPre[k];
    }

    public int maxProfit4(int k,int[] prices){
        if(k==0||prices==null||prices.length<2) return 0;
        int maxProfit=0;
        if(k>=prices.length>>1){
            for (int i = 1; i < prices.length; i++) {
                if(prices[i]>prices[i-1]) maxProfit+= prices[i]-prices[i-1];
            }
            return maxProfit;
        }

        int[][] trans=new int[prices.length][k+1];
        int[][] local=new int[prices.length][k+1];
        for (int i = 1; i < prices.length; i++) {
            int thisTrans=prices[i]-prices[i-1];
            for (int j = 1; j < k+1; j++) {
                local[i][j] =Math.max(trans[i-1][j-1], local[i-1][j]+thisTrans);
                trans[i][j]= Math.max(trans[i-1][j], local[i][j]);
            }
        }
        return trans[prices.length-1][k];
    }
    /**
     * 问题规模N>一千万时，此算法最佳不到1s, 动态规划则需要超过1分钟时间
     * 获取prices的升序序列作为交易，因为只有升序序列才是能获利的交易
     * transaction记录所有的交易，初始化为最初的K个交易，只记录最小和最大价格
     *然后遍历prices，将新的交易逐个并入transaction中，并保持交易数为K个。最后的K个交易就是结果
     * @param k
     * @param prices
     * @return
     */
    //Solution 2  对Solution 1的优化
    //O(KN) DP space O(K) 要使获利最大，交易要尽可能多，
    public int maxProfit3(int k, int[] prices) {
        if(k==0||prices==null||prices.length<2) return 0;
        prices=getAscendingSubSeq(prices);
        if(prices.length<2) return 0;
        if(k>prices.length/2) k=prices.length/2;
        int NUM=2*k;
        int[] transaction=Arrays.copyOf(prices, NUM);
        int maxProfit=0;
        int minDisPos=0;
        for (int i = 0; i < NUM-1; i++) {
            if((i&1)==0) maxProfit+=prices[i+1]-prices[i];
            if(Math.abs(transaction[i+1]-transaction[i])<Math.abs(transaction[minDisPos+1]-transaction[minDisPos])) minDisPos=i;
        }
        if(k==prices.length/2) return maxProfit;

        int start=NUM;
        int min=prices[start];
        int maxProfitTemp=0;
        boolean flag=true;
        for (int i = start+1; i < prices.length; i+=2) {
            if(flag) {
                min=prices[i-1]; maxProfitTemp=0;
            }
            min=Math.min(min,prices[i-1]);
            maxProfitTemp=Math.max(maxProfitTemp,prices[i]-min);
//            System.out.printf(" i=%d, maxProfit=%d\n",i,maxProfit);
           flag=false;
           if(transaction[NUM-1]-min<Math.abs(transaction[minDisPos+1]-transaction[minDisPos])) {//当前交易是否可以和最后的交易合并
               if (maxProfitTemp > transaction[NUM - 1] - min) {
                   maxProfit += maxProfitTemp - (transaction[NUM - 1] - min);
                   transaction[NUM - 1] = maxProfitTemp + min;
                   //重置临时变量
                   flag=true;
               }
           } else{//当前交易是否可以和中间交易合并
               if(maxProfitTemp > Math.abs(transaction[minDisPos+1]-transaction[minDisPos])) {
                   maxProfit += maxProfitTemp - Math.abs(transaction[minDisPos + 1] - transaction[minDisPos]);
                   System.arraycopy(transaction, minDisPos + 2, transaction, minDisPos, NUM - minDisPos - 2);
                   transaction[NUM - 2] = min;
                   transaction[NUM - 1] = maxProfitTemp + min;
                   //寻找结果数组的最小差值
                   minDisPos = 0;
                   for (int j = 0; j < NUM - 1; j++) {
                       if (Math.abs(transaction[j + 1] - transaction[j]) < Math.abs(transaction[minDisPos + 1] - transaction[minDisPos]))
                           minDisPos = j;
                   }
                   //重置临时变量
                   flag=true;
               }
           }
        }
        return maxProfit;
    }

    //Solution 2  对Solution 1的优化
    //只考虑升序序列, 并优化单次交易计算最大值方法
    public int maxProfit1(int k, int[] prices) {
        if(k==0||prices==null||prices.length<2) return 0;
        prices=getAscendingSubSeq(prices);
        int sum=0;
        if(k>=prices.length/2) {
            for (int i = 1; i < prices.length; i+=2) {
                sum+=prices[i]-prices[i-1];
            }
            return sum;
        }
        return recursive(k, prices, 0, prices.length-1);
    }

    public int recursive(int k, int[] prices, int left, int right){
        times++;
        if(left==right) return 0;
        String key=left+","+right+","+k;
        if(cache.containsKey(key)) return cache.get(key);
        exetimes++;
        if(k==1) return maxProfitOneTransForAscendSeq(prices, left, right);
        int maxProfit=0;
        for (int i = left; i <= right; i+=2) {
            int leftsum=maxProfitOneTransForAscendSeq(prices, left, i+1);
            int rightsum=recursive(k-1, prices, i+2, right);
            maxProfit=Math.max(maxProfit, leftsum+rightsum);
        }
        cache.put(key, maxProfit);
        return maxProfit;
    }

    //O(N) 一次交易 T(1,i)=max(T(1,i-1), A[i]-minA[i-1])
    public int maxProfitOneTransForAscendSeq(int[] prices, int left, int right) {
        if(prices==null||right-left<1) return 0;
        int min=prices[left];
        int maxProfit=0;
        for (int i = left+1; i <= right; i+=2) {
            min=Math.min(min,prices[i-1]);
            maxProfit=Math.max(maxProfit,prices[i]-min);
        }
        return maxProfit;
    }

    //获取升序子序列, 记录每个子序列的最小值和最大值。
    public int[] getAscendingSubSeq(int[] prices) {
        int[] ascendingSubSeq=new int[prices.length];
        int start=0;
        int j=0;
        for (int i = 1; i < prices.length; i++) {
            if(prices[i]<prices[i-1]||(prices[i]==prices[i-1]&&i-start<2)) {
                if(i-start>=2){
                    ascendingSubSeq[j++]=prices[start];
                    ascendingSubSeq[j++]=prices[i-1];
                }
                start=i;
            }
        }
        if(prices.length-start>=2){
            ascendingSubSeq[j++]=prices[start];
            ascendingSubSeq[j++]=prices[prices.length-1];
        }
        ascendingSubSeq= Arrays.copyOf(ascendingSubSeq, j);
        return ascendingSubSeq;
    }

    //对Solution 1的优化
    //只考虑升序序列
    public int maxProfit2(int k, int[] prices) {
        if(k==0||prices==null||prices.length<2) return 0;
        prices=getAscendingSubSeq(prices);
        if(k>prices.length/2) k=prices.length/2;
        return maxProfit(k, prices, 0, prices.length-1);
    }

    //Solution 1
    public int maxProfit(int k, int[] prices) {
        if(k==0||prices==null||prices.length<2) return 0;
        return maxProfit(k, prices, 0, prices.length-1);
    }

    //O(N*(N-K)^2) space O(N^2)
    //T(k,A(1,N))=max<1,N>(sum(T(1, A(1,i)), T(k-1,A(i+1,N)))) 递推关系式
    long times=0;
    long exetimes=0;
    Map<String,Integer> cache=new HashMap<>();
    public int maxProfit(int k, int[] prices, int left, int right) {
        times++;
        if(right-left<1) return 0;
        String key=left+","+right+","+k;
        if(cache.containsKey(key)) return cache.get(key);
        exetimes++;
        if(k==1) return maxProfitOneTrans(prices, left, right);
        int maxProfit=0;
        for (int i = left; i <= right; i++) {
//            long keytemp=(left<<20)+(i<<3)+1;
//            int leftsum=cache.containsKey(keytemp)?cache.get(keytemp):maxProfitOneTrans(prices, left, i);
//            keytemp=((i+1)<<20)+(right<<3)+(k-1);
//            int rightsum=cache.containsKey(keytemp)?cache.get(keytemp):maxProfit(k-1, prices, i+1, right);
            int leftsum=maxProfitOneTrans(prices, left, i);
            int rightsum=maxProfit(k-1, prices, i+1, right);
            maxProfit=Math.max(maxProfit, leftsum+rightsum);
        }
        cache.put(key, maxProfit);
        return maxProfit;
    }

    //O(N) 一次交易 T(1,i)=max(T(1,i-1), A[i]-minA[i-1])
    public int maxProfitOneTrans(int[] prices, int left, int right) {
        if(prices==null||right-left<1) return 0;
        int min=prices[left];
        int maxProfit=0;
        for (int i = left+1; i <= right; i++) {
            maxProfit=Math.max(maxProfit,prices[i]-min);
            min=Math.min(min,prices[i]);
        }
        return maxProfit;
    }
}
