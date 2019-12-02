package leetcode.realtest.realTest20191201_WC165;

import utils.PrintUtils;

import java.util.HashMap;
import java.util.Map;

public class FindWinneronaTicTacToeGame {
    public static void main(String[] args) {
        FindWinneronaTicTacToeGame findWinner=new FindWinneronaTicTacToeGame();
        int[][] moves = {{0,0},{2,0},{1,1},{2,1},{2,2}};
        moves = new int[][]{{0,0},{1,1},{0,1},{0,2},{1,0},{2,2},{2,0}};
        moves = new int[][]{{0,0},{1,1},{0,1},{0,2},{1,0},{2,0}};
//        moves = new int[][]{{0,0},{1,1},{2,0},{1,0},{1,2},{2,1},{0,1},{0,2},{2,2}};
//        moves = new int[][]{{0,0},{1,1}};
        System.out.println(findWinner.tictactoe(moves));
    }
    public String tictactoe(int[][] moves) {
        Map<Integer,Integer>[] xlines=new HashMap[2];
        xlines[0]=new HashMap<>(); xlines[1]=new HashMap<>();
        Map<Integer,Integer>[] ylines=new HashMap[2];
        ylines[0]=new HashMap<>(); ylines[1]=new HashMap<>();
        Map<Integer,Integer>[] dgnlines=new HashMap[2];
        dgnlines[0]=new HashMap<>(); dgnlines[1]=new HashMap<>();
        for (int i = 2; i < moves.length; i++) {
            for (int j = i-2; j >= 0; j-=2) {
                Map<Integer,Integer> map=xlines[i%2];
                int key=0;
                if(moves[i][1]==moves[j][1]) {
                    key = moves[i][1];
                    if (map.containsKey(key)) {
                        if (map.get(key) > 2)
                            return i % 2 == 0 ? "A" : "B";
                        else map.put(key, map.get(key) + 1);
                    } else {
                        map.put(key, 2);
                    }
                }
                map=ylines[i%2];
                if(moves[i][0]==moves[j][0]) {
                    key = moves[i][0];
                    if (map.containsKey(key)) {
                        if (map.get(key) > 2)
                            return i % 2 == 0 ? "A" : "B";
                        else map.put(key, map.get(key) + 1);
                    } else {
                        map.put(key, 2);
                    }
                }
                map=dgnlines[i%2];
                if(moves[i][0]!=moves[j][0]){
                    key=(moves[i][1]-moves[j][1])/(moves[i][0]-moves[j][0]);
                    if(key==1 || key==-1){
                        if(map.containsKey(key)){
                            if(map.get(key)>2)
                                return i%2==0?"A":"B";
                            else map.put(key, map.get(key)+1);
                        }else {
                            map.put(key,2);
                        }
                    }
                }
            }
        }

        return moves.length==9?"Draw":"Pending";

    }
}
