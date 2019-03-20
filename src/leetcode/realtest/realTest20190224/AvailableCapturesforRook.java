package leetcode.realtest.realTest20190224;

/**
 * @author shibing
 * @since 2019/2/24 10:59
 */
public class AvailableCapturesforRook {
    public static void main(String[] args) {
        AvailableCapturesforRook rook=new AvailableCapturesforRook();
        char[][] board=new char[][]{{'.','.','.','.','.','.','.','.'},{'.','.','.','p','.','.','.','.'},{'.','.','.','R','.','.','.','p'},{'.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.'},{'.','.','.','p','.','.','.','.'},{'.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.'}};
        board=new char[][]{{'.','.','.','.','.','.','.','.'},{'.','p','p','p','p','p','.','.'},{'.','p','p','B','p','p','.','.'},{'.','p','B','R','B','p','.','.'},{'.','p','p','B','p','p','.','.'},{'.','p','p','p','p','p','.','.'},{'.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.'}};
        board=new char[][]{{'.','.','.','.','.','.','.','.'},{'.','.','.','p','.','.','.','.'},{'.','.','.','p','.','.','.','.'},{'p','p','.','R','.','p','B','.'},{'.','.','.','.','.','.','.','.'},{'.','.','.','B','.','.','.','.'},{'.','.','.','p','.','.','.','.'},{'.','.','.','.','.','.','.','.'}};
        System.out.println(rook.numRookCaptures(board));

    }
    public int numRookCaptures(char[][] board) {
        int row=0, col=0;
        for (int i = 0; i < 8 ;i++)
            for (int j = 0; j < 8; j++)
                if(board[i][j]=='R') {
                    row=i; col=j;
                }
        int count=0;
        for (int j = col+1; j <8 ; j++) {
            char c=board[row][j];
            if(c=='p') {count++; break;}
            if(c=='B') break;
        }

        for (int j = col-1; j >=0 ; j--) {
            char c=board[row][j];
            if(c=='p') {count++; break;}
            if(c=='B') break;
        }
        for (int i = row+1; i <8 ; i++) {
            char c=board[i][col];
            if(c=='p') {count++; break;}
            if(c=='B') break;
        }

        for (int i = row-1; i>=0; i--) {
            char c=board[i][col];
            if(c=='p') {count++; break;}
            if(c=='B') break;
        }
        return count;
    }

}
