package leetcode.realtest.realTest20190630;

/**
 * @author shibing
 * @since 2019/6/30 11:46
 */
public class ParsingABooleanExpression {
    public static void main(String[] args) {
        ParsingABooleanExpression expressionP=new ParsingABooleanExpression();
        String expression = "!(f)";
        expression = "|(f,t)";
        expression = "&(t,f)";
        expression = "|(&(t,f,t),!(t))";
        expression="|(t,t,f)";
        System.out.println(expressionP.parseBoolExpr(expression));
    }
    public boolean parseBoolExpr(String expression) {
        int n=expression.length();
        char[] oper=new char[n];
        char[] val=new char[n];
        int topo=0, topv=0;
        for (int i = 0; i < n; i++) {
            char c=expression.charAt(i);
            if(isOper(c)) oper[topo++]=c;
            if(c=='(' || c=='f' || c=='t') val[topv++]=c;
            if(c==')'){
                char op=oper[--topo];
                boolean re=val[topv-1]=='t';
                while (val[topv-1]!='('){
                    if(op=='&') re &= val[topv-1]=='t';
                    if(op=='|') re |= val[topv-1]=='t';
                    if(op=='!') re=val[topv-1]!='t';
                    topv--;
                }
                val[topv-1]= re?'t':'f';
            }
        }
        return val[topv-1]=='t';
    }
    boolean isOper(char c){
        return c=='|' || c=='&' || c=='!';
    }
}
