package leetcode.TopInterview;

/**
 * @author Shibing
 * @since 2019-06-12 14:31:15
 **/
public class WordSearch {
    public static void main(String[] args) {
        WordSearch search=new WordSearch();
        char[][] board = { {'A','B','C','E'}, {'S','F','C','S'}, {'A','D','E','E'} }; String word="SEED";
        board=new char[][]{{'a','a'}}; word= "aaa";
        System.out.println(search.exist(board, word));
    }


    //dfs
    public boolean exist(char[][] board, String word) {
        int m=board.length, n=board[0].length;
        boolean[][] visited=new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(board[i][j]==word.charAt(0)) {
                    visited[i][j]=true;
                    if (dfs(board, i, j, word, 1, visited)) return true;
                    visited[i][j]=false;
                }
            }
        }
        return false;
    }
    int[][] dires={{-1,0}, {0,1}, {1,0}, {0,-1}};
    public boolean dfs(char[][] board, int i, int j, String word, int idx, boolean[][] visited){
        int m=board.length, n=board[0].length;
        if(idx==word.length()) return true;
        boolean res=false;
        for(int[] dire:dires){
            int x=i+dire[0], y=j+dire[1];
            if(x>=0 && y>=0 && x<m && y<n && !visited[x][y] && board[x][y]==word.charAt(idx)) {
                visited[x][y]=true;
                res = res || dfs(board, x, y, word, idx + 1, visited);
                visited[x][y]=false;
            }
        }
        return res;
    }
}
