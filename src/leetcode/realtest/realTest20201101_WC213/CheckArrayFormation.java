package leetcode.realtest.realTest20201101_WC213;

public class CheckArrayFormation {
    public static void main(String[] args) {
        CheckArrayFormation checkArrayFormation = new CheckArrayFormation();
        int [] arr = {91,4,64,78}; int[][] pieces = {{78},{4,64},{91}};
        System.out.println(checkArrayFormation.canFormArray(arr, pieces));
    }
    public boolean canFormArray(int[] arr, int[][] pieces) {
        int n=arr.length, pn=0;
        OUTER:for(int i=0; i<n; i++){
            for(int j=pn; j<pieces.length; j++){
                int k=0, m=i;
                while(m<n && k<pieces[j].length && arr[m]==pieces[j][k]){
                    m++; k++;
                }
                if(k==pieces[j].length){
                    int[] temp=pieces[pn];
                    pieces[pn]=pieces[j];
                    pieces[j]=temp;
                    pn++;
                    i=m-1;
                    continue OUTER;
                }
            }
            return false;
        }
        return true;
    }
}
