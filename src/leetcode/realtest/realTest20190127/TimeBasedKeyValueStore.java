package leetcode.realtest.realTest20190127;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Shibing
 * @since 2019-01-28 15:42:38
 **/
public class TimeBasedKeyValueStore {
    class Pair{
        String value;
        int timestamp;

        public Pair(String value, int timestamp) {
            this.value = value;
            this.timestamp = timestamp;
        }
    }
    Map<String, List<Pair>> data;
    public static void main(String[] args) {
        TimeBasedKeyValueStore kv=new TimeBasedKeyValueStore();
        kv.set("foo", "bar", 1); // store the key "foo" and value "bar" along with timestamp = 1
        kv.get("foo", 1);  // output "bar"
        kv.get("foo", 3); // output "bar" since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 ie "bar"
        kv.set("foo", "bar2", 4);
        kv.get("foo", 4); // output "bar2"
        kv.get("foo", 5); //output "bar2"


    }
    /** Initialize your data structure here. */
    public TimeBasedKeyValueStore() {
        data=new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        if(!data.containsKey(key))
            data.put(key, new ArrayList<>());
        data.get(key).add(new Pair(value,timestamp));
    }

    public void get(String key, int timestamp) {
        System.out.println(get0(key, timestamp));
    }
    //Binary search
    public String get0(String key, int timestamp) {
        if(!data.containsKey(key)) return "";
        List<Pair> list=data.get(key);
        int i=0, j=list.size()-1;
        int mid;
        while (i<=j) {
            mid = (i+j) / 2;
            Pair p=list.get(mid);
            if(p.timestamp>timestamp)
                j=mid-1;
            else if(p.timestamp<timestamp){
                i=mid+1;
            }else return p.value;
        }
        if(j<0) return "";
        else return list.get(j).value;
    }
}
