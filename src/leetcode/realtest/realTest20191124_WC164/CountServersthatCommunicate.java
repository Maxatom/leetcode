package leetcode.realtest.realTest20191124_WC164;

public class CountServersthatCommunicate {
    public static void main(String[] args) {
        CountServersthatCommunicate countServers=new CountServersthatCommunicate();
        int[][] grid = {{1,0},{0,1}};
        grid = new int[][]{{1,0},{1,1}};
        grid = new int[][]{{1,1,0,0},{0,0,1,0},{0,0,1,0},{0,0,0,1}};
        grid=new int[][]{{0}};
        System.out.println(countServers.countServers(grid));

    }
    public int countServers(int[][] grid) {
        int m=grid.length, n=grid[0].length, cnt=0;
        int[] rows=new int[m], cols=new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(grid[i][j]==1){
                    rows[i]+=1;
                    cols[j]+=1;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(grid[i][j]==1){
                    if(rows[i]>1 || cols[j]>1) cnt++;
                }
            }
        }
        return cnt;
    }
}
