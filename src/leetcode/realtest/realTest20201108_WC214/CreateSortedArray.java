package leetcode.realtest.realTest20201108_WC214;

public class CreateSortedArray {
    public static void main(String[] args) {
        CreateSortedArray sortedArray = new CreateSortedArray();
        int[] instructions = {1,5,6,2};
        instructions = new int[]{1,2,3,6,5,4};
        instructions = new int[]{1,3,3,3,2,4,2,1,2};
        System.out.println(sortedArray.createSortedArray(instructions));

    }

    public int createSortedArray(int[] instructions) {
        return 0;
    }


    int M=(int)1e9+7;
    public int createSortedArray1(int[] instructions) {
        int max=0;
        for(int x:instructions) max=Math.max(max, x);
        SegTree segTree = new SegTree(max);
        int sum=0;
        for(int i=0; i< instructions.length; i++){
            int left=segTree.query(1, instructions[i]-1);
            int right=segTree.query(instructions[i]+1, max);
            sum+=Math.min(left ,right);
            sum %= M;
//            System.out.printf("%d,%d,%d\n", i, instructions[i], sum);
            segTree.update(instructions[i]);
        }
        return sum;
    }
    class SegTree{
        int n;
        int[] data;

        public SegTree(int n) {
            this.n = n;
            this.data = new int[(n<<2)+1];
        }
        int query(int ql, int qr){
            return query(1, 1, n, ql, qr);
        }
        void update(int x){
            update(1, x, 1, n);
        }
        void update(int id,int x, int l, int r){
            if(x<l || x>r) return;
            int mid =(l+r)>>1;
            data[id]++;
            if(l==r) return;
            update(id<<1, x, l, mid);
            update((id<<1)+1, x, mid+1, r);
        }

        int query(int id, int l, int r, int ql, int qr){
            if(qr<l || ql>r) return 0;
            if(ql<=l && qr>=r) return data[id];
            int mid= (l+r)>>1;
            return query(id<<1, l, mid, ql, qr)+query((id<<1)+1, mid+1, r, ql ,qr);
        }
    }
}
