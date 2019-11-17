package leetcode.realtest.realTest20190728;

/**
 * @author shibing
 * @since 2019/7/28 10:40
 */
public class AlphabetBoardPath {
    public static void main(String[] args) {
        AlphabetBoardPath path=new AlphabetBoardPath();
        String target = "leet";
        target="c";
        System.out.println(path.alphabetBoardPath(target));

    }
    public String alphabetBoardPath(String target) {
        StringBuilder path=new StringBuilder(minPath('a', target.charAt(0)));
        for (int i = 0; i < target.length()-1; i++) {
            path.append(minPath(target.charAt(i), target.charAt(i+1)));
        }
        return path.toString();
    }
    String minPath(char c0, char c1){
        if(c0==c1) return "!";
        int len=c0-'a', len1=c1-'a';
        int x0= len/5, y0 =len-(len/5)*5;
        int x1=len1/5, y1=len1-(len1/5)*5;
        System.out.printf("%s,%s;  %s, %s \n", x0,y0,x1,y1);
        StringBuilder path=new StringBuilder();
        if(c0=='z'){
            for (int i = 0; i < x0-x1; i++) path.append('U');
            for (int i = 0; i < y1-y0; i++) path.append('R');
        }else if(c1=='z'){
            for (int i = 0; i < y0-y1; i++) path.append('L');
            for (int i = 0; i < x1-x0; i++) path.append('D');
        }else {
            for (int i = 0; i < Math.abs(x1-x0); i++)
                path.append(x0<x1?'D':'U');
            for (int i = 0; i < Math.abs(y1-y0); i++)
                path.append(y0<y1?'R':'L');
        }
        path.append('!');
        return path.toString();
    }
}
