### -题目描述
######    最佳景点对
　　给一个数组A，数组元素都是整数，数组的每个值A[i]表示一个景点的评分，
   一个景点对（i,j）的分数是A[i]+A[j]+i-j; <br> 求分数最大的景点对。
<br>（原题 https://leetcode.com/problems/best-sightseeing-pair/）
  <br>
  Note:<br>
　　2 <= A.length <= 50000<br>
　　1 <= A[i] <= 1000

### -思路
 
###### 方法1（TLE）：<br>
 　最直接的方法就是计算每两个景点的距离，算法复杂度是 time O(N^2), space O(1)。<br>
但是这样比较慢，题目给出的数组A的大小最大是50000，这在leetcode上就要求至少O(N)的算法，
```
//brute force
    public int maxScoreSightseeingPair(int[] A) {
        int max=0;
        for (int i = 1; i < A.length; i++) {
            for (int j = 0; j < i; j++) {
                max=Math.max(A[j]+A[i]+j-i, max);
            }
        }
        return max;
    }
```
######  方法2：<br>
&nbsp;&nbsp;动态规划：　
 假设我们遍历数组到了第i个景点， 我们计算A[i]和它直接的元素的最大值可以这样
 A[i]+max(A[j]+j-i) (0=<j<i)
 这需要循环，但是我们可以一次循环中记录每个景点分数减去他们到右侧景点的距离，并记录最大值，每向右移动一次，所有景点分数减一，这样只需要一次遍历。
```
//dp(i)= max(max(A[j]+j-i)+A[i])
    public int maxScoreSightseeingPair1(int[] A) {
        int cur=A[0]-1, max=0;
        for (int i = 1; i < A.length; i++) {
            max=Math.max(cur+A[i], max);
            cur=Math.max(cur-1, A[i]-1);
        }
        return max;
    }
```

### - 总结

 　　这个题目是动态规划的解法，遍历过程中关键是要计算景点分数减去其到右侧景点的距离，并记录这个最大值，这样遍历到第i个景点就可以立刻知道它和前面的哪个景点可以组成最大分数。 