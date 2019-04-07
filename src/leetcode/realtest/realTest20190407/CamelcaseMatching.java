package leetcode.realtest.realTest20190407;

import utils.PrintUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shibing
 * @since 2019/4/7 11:25
 */
public class CamelcaseMatching {
    public static void main(String[] args) {
        CamelcaseMatching camel=new CamelcaseMatching();
        String[] queries = new String[]{"FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"};String pattern = "FB";
        queries=new String[]{"FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"}; pattern="FoBa";
        queries=new String[]{"FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"}; pattern="FoBaT";
        queries=new String[]{"CompetitiveProgramming","CounterPick","ControlPanel"}; pattern= "CooP";
//        queries=new String[]{"IXfGawluvnCa","IsXfGaxwulCa","IXfGawlqtCva","IXjfGawlmeCa","IXfGnaynwlCa","IXfGcamwelCa"};pattern= "IXfGawlCa";
//        queries=new String[]{"aksvbjLiknuTzqon","ksvjLimflkpnTzqn","mmkasvjLiknTxzqn","ksvjLiurknTzzqbn","ksvsjLctikgnTzqn","knzsvzjLiknTszqn"};pattern= "ksvjLiknTzqn";
        PrintUtils.printList(camel.camelMatch(queries,pattern), p->p+"");
    }
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> res=new ArrayList<>();
        Outer: for(String str:queries){
            int i=0, j=0;
            while (j<pattern.length() && i<str.length() ){
                if(str.charAt(i)==pattern.charAt(j)){
                         i++;  j++;
                 }else{
                    if(str.charAt(i)>='A' && str.charAt(i)<='Z'){
                        res.add(false);
                        continue Outer;
                    }
                    i++;
                }
            }
            while (i<str.length()){
                if(str.charAt(i)>='A' && str.charAt(i)<='Z'){
                    res.add(false);
                    continue Outer;
                }else i++;
            }
            if(j!=pattern.length()) res.add(false);
            else res.add(true);
        }
        return res;
    }
}
