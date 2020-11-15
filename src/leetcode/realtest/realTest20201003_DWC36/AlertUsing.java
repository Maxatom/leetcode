package leetcode.realtest.realTest20201003_DWC36;

import java.util.*;

public class AlertUsing {
    public static void main(String[] args) {
        AlertUsing using = new AlertUsing();
       String[] keyName={"leslie","leslie","leslie","clare","clare","clare","clare"},
    keyTime = {"13:00","13:20","14:00","18:00","18:51","19:30","19:49"} ;
       keyName = new String[]{"a","a","a","a","a","a","a","a","b","b","b","b","b","b","b","b","b","b","c","c","c","c","c","c","d","d","d","d","d","d","d","d","d","e","e","e","e","e","e","e","e","f","f","f","f","f","f","f","g","g","g","g","g","g","g","g","h","h","h","h","h","h","h","h","h","h"};
keyTime = new String[]{"19:17","02:58","11:09","14:03","12:33","18:27","09:06","22:25","10:23","23:16","06:10","19:11","13:49","23:36","20:08","22:41","22:06","01:11","22:35","01:04","06:13","16:18","14:16","22:30","09:01","04:38","08:41","16:55","06:28","10:50","13:08","07:11","05:23","13:04","20:41","07:57","21:14","19:55","00:41","23:09","00:58","12:42","23:45","21:23","00:52","08:30","19:20","22:00","15:23","12:02","07:33","15:13","05:44","11:55","16:26","12:13","16:05","18:18","08:15","23:02","22:10","01:53","07:59","12:29","03:41","17:19"};
        System.out.println(using.alertNames(keyName,keyTime).toString());
    }
    public List<String> alertNames(String[] keyName, String[] keyTime) {
        int n= keyName.length;
        String[][] nameTimes = new String[n][2];
        for(int i=0; i<n; i++){
            nameTimes[i][0]=keyName[i]; nameTimes[i][1]=keyTime[i];
        }
        Arrays.sort(nameTimes, (a,b)->a[0].compareTo(b[0])!=0?a[0].compareTo(b[0]):a[1].compareTo(b[1]));
//        for(int i=0; i<n; i++){
//            System.out.printf("(%s,%s),", nameTimes[i][0], nameTimes[i][1]);
//        }

        Map<String, List<String>> timemap = new HashMap<>();
        Set<String> set = new HashSet<>();
        for(int i=0; i<n; i++){
//            String name = keyName[i], time=keyTime[i];
            String name = nameTimes[i][0], time=nameTimes[i][1];
            if(set.contains(name)) continue;
            if(!timemap.containsKey(name)){
                timemap.put(name, new ArrayList<>());
                timemap.get(name).add(time);
                continue;
            }
            List<String> timelist = timemap.get(name);
            timelist.add(time);
            while (timelist.size()>=2 &&!isInHour(timelist.get(0), timelist.get(timelist.size()-1))){
                timelist.remove(0);
            }
            if(timelist.size()>=3) set.add(name);
        }
        List<String> list = new ArrayList<>();
        for(String name:set) list.add(name);
        list.sort((a,b)->a.compareTo(b));
        return list;
    }

    boolean isInHour(String time1, String time2){
        String[] time1A=time1.split(":");
        String[] time2A=time2.split(":");
        int hour1 = Integer.valueOf(time1A[0]), min1=Integer.valueOf(time1A[1]),
                hour2 = Integer.valueOf(time2A[0]), min2=Integer.valueOf(time2A[1]);
//        System.out.printf("%s=%d:%d,%s=%d:%d,\n", time1, hour1,min1,time2 ,hour2,min2);
        if(hour2==hour1 && min2>min1) return true;
        if(hour2-hour1==1 && min2<=min1) return true;
        return false;
    }
}
