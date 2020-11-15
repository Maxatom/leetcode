package leetcode.realtest.realTest20200920_WC207;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrangeWords {
    public static void main(String[] args) {
        ArrangeWords words = new ArrangeWords();
        String text = "  walks  udp package   into  bar a";
        text="a";
        System.out.println(words.reorderSpaces(text));
    }
    public String reorderSpaces(String text) {
        int bc=0, tl=0;
        String[] arr = text.split(" ");
        List<String> list = new ArrayList<>();
        for(int i=0; i<arr.length; i++){
            if(arr[i].length()==0) continue;
            list.add(arr[i]);
            tl+=arr[i].length();
        }
        if(list.size()>1)
            bc = (text.length()-tl)/(list.size()-1);
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<list.size(); i++){
            sb.append(list.get(i));
            if(i!=list.size()-1) for(int j=0;j<bc;j++) sb.append(" ");
        }
//        sb.delete()
        while (sb.length()<text.length()) sb.append(" ");
        return sb.toString();
    }
}
