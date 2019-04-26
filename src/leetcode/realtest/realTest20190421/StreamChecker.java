package leetcode.realtest.realTest20190421;

/**
 * @author Shibing
 * @since 2019-04-24 15:14:40
 **/
public class StreamChecker {
    public static void main(String[] args) {
        StreamChecker streamChecker = new StreamChecker(new String[]{"cd","f","kl"}); // init the dictionary.
//        streamChecker = new StreamChecker(new String[]{"cdefg"}); // init the dictionary.
        String test="abcdefghijkl";
        for (char c:test.toCharArray()) {
            System.out.println(streamChecker.query(c));          // return false
        }
    }
    public class TrieNode{
        boolean isend=false;
        TrieNode[] next=new TrieNode[26];
    }
    TrieNode root=new TrieNode();
    StringBuilder buf=new StringBuilder();

    public void insert(String word){
        TrieNode node=root;
        for (int i = word.length()-1; i >=0; i--) {
            if(node.next[word.charAt(i)-'a']==null) node.next[word.charAt(i)-'a']=new TrieNode();
            node=node.next[word.charAt(i)-'a'];
        }
        node.isend=true;
    }

    public StreamChecker(String[] words){
        for(String word:words) insert(word);
    }

    public boolean query(char letter){
        buf.append(letter);
        TrieNode node=root;
        for (int i = buf.length()-1 ; i >= 0 ; i--) {
            node=node.next[buf.charAt(i)-'a'];
            if(node==null) return false;
            if(node.isend) return true;
        }
        return false;
    }
}
