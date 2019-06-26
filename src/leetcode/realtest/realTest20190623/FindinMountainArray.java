package leetcode.realtest.realTest20190623;

/**
 * @author shibing
 * @since 2019/6/23 16:55
 */
public class FindinMountainArray {
    public static void main(String[] args) {

    }
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int res=dfs(target,mountainArr, 0, mountainArr.length()-1);
        return res==Integer.MAX_VALUE?-1:res;
    }
    public int dfs(int target, MountainArray mountainArr, int left, int right){
        if(left==right) return target==mountainArr.get(left)?left:Integer.MAX_VALUE;
        if(left>right) return Integer.MAX_VALUE;
        int mid=(left+right)/2;
        int num=mountainArr.get(mid);
        if(num==target) return mid;
        if(num<mountainArr.get(mid+1)) {
            if(target>num)
                return dfs(target, mountainArr, mid + 1, right);
        }else {
            if(target>num)
                return dfs(target, mountainArr, left, mid-1);
        }
        return Math.min(dfs(target, mountainArr, left, mid-1), dfs(target, mountainArr, mid+1, right));
    }
}

  interface MountainArray {
      public int get(int index);
      public int length();
  }