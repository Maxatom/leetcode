package leetcode.realtest.realTest20191201_WC165;

import utils.PrintUtils;
import utils.Utils;

import java.io.*;

public class CountSquareSubmatriceswithAllOnes {
    public static void main(String[] args) throws IOException {
        CountSquareSubmatriceswithAllOnes ones=new CountSquareSubmatriceswithAllOnes();
        int[][] matrix = { {0,1,1,1}, {1,1,1,1}, {0,1,1,1} };
        matrix = new int[][]{ {1,0,1}, {1,1,0}, {1,1,0} };
        matrix = new int[][]{ {1,1,1}, {1,1,1}, {1,1,0} };
//        matrix=new int[][]{{0}};
//        matrix=new int[][]{{1}};
//        matrix=new int[][]{{0},{1}};
        FileReader fileReader=new FileReader(new File("E:\\javastudy\\leetcode\\src\\leetcode\\realtest\\realTest20191201_WC165\\data.txt"));
        BufferedReader reader=new BufferedReader(fileReader);
        matrix=PrintUtils.convertStringTo2DIntArray(reader.readLine());
        PrintUtils.print2DIntArray(matrix);
        System.out.println(ones.countSquares(matrix));
    }
    public int countSquares(int[][] matrix) {
        int m=matrix.length, n=matrix[0].length, cnt=0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(i!=0 && j!=0 && matrix[i][j]!=0) {
                    if(matrix[i-1][j]==matrix[i][j-1]){
                        int l=matrix[i-1][j];
                        matrix[i][j]=matrix[i-l][j-l]>0? l+1 : l;
                    }else
                    matrix[i][j] = Math.min(matrix[i-1][j], matrix[i][j-1])+1;
                }
                cnt+=matrix[i][j];
            }
        }
        PrintUtils.print2DIntArray(matrix);
        return cnt;
    }
}
