package leetcode.realtest.realTest20190303;

import utils.PrintUtils;

import java.util.*;

/**
 * @author shibing
 * @since 2019/3/9 8:47
 */
public class FindCommonCharacters {
    public static void main(String[] args) {
        FindCommonCharacters find=new FindCommonCharacters();
        String[] A=PrintUtils.convertStringToStringArray("[\"bella\",\"label\",\"roller\"]");
        A=PrintUtils.convertStringToStringArray("[\"cool\",\"lock\",\"cook\"]");
        A=PrintUtils.convertStringToStringArray("[\"colll\"]");
        A=PrintUtils.convertStringToStringArray("[\"abc\", \"def\"]");
        System.out.println(find.commonChars(A).toString());
        System.out.println(find.commonChars1(A).toString());
    }

    //using array O(NM)
    public List<String> commonChars1(String[] A) {
        int[] chars=new int[26];
        for(char c:A[0].toCharArray()) chars[c-'a']++;
        for (int i = 1; i < A.length; i++) {
            int[] cur=new int[26];
            for(char c:A[i].toCharArray()) cur[c-'a']++;
            boolean flag=true; // 是否无相同字符
            for (int j = 0; j < chars.length; j++)
                if(chars[j]>0){
                    chars[j]=Math.min(chars[j], cur[j]);
                    flag=false;
                }
            if(flag) break;
        }
        List<String> list=new ArrayList<>();
        for (int i = 0; i < chars.length; i++)
            for (int j = 0; j < chars[i]; j++)
                list.add((char)('a'+i)+"");
        return list;
    }

    public List<String> commonChars(String[] A) {
        Map<Character, Integer> map=new HashMap<>();
        for(char c:A[0].toCharArray())
            map.put(c, map.getOrDefault(c, 0)+1);
        for (int i = 1; i < A.length; i++) {
            Map<Character, Integer> cur=new HashMap<>();
            for(char c:A[i].toCharArray())
                cur.put(c, cur.getOrDefault(c, 0)+1);
            Iterator<Map.Entry<Character, Integer>> it=map.entrySet().iterator();
            while ( it.hasNext()){
                Map.Entry<Character, Integer> entry=it.next();
                int freq=cur.getOrDefault(entry.getKey(),0);
                if(freq==0) {it.remove(); continue;}
                map.put(entry.getKey(), Math.min(freq, entry.getValue()));
            }
            if(map.size()==0) break;
        }
        List<String> list=new ArrayList<>();
        for (Map.Entry<Character, Integer> entry:map.entrySet()){
            for (int i = 0; i < entry.getValue(); i++) {
                list.add(entry.getKey()+"");
            }
        }
        return list;
    }
}
