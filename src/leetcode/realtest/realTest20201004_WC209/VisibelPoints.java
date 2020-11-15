package leetcode.realtest.realTest20201004_WC209;

import java.util.*;
import java.util.stream.Collectors;

public class VisibelPoints {
    public static void main(String[] args) {
        VisibelPoints visible = new VisibelPoints();
        int[][] points = {{2,1},{2,2},{3,3}}; int angle = 90; int[] location = {10000,10000};
//        points = new int[][]{{0,1},{2,1}}; angle = 13; location = new int[]{1,1};
//        points = new int[][]{{1,1},{2,2},{3,3},{4,4},{1,2},{2,1}}; angle=0; location=new int[]{1,1};
//        points = new int[][]{{1_000_000_000,1_000_000_000},{1_000_000_000,1_000_000_000-1},{1_000_000_000-1,1_000_000_000}}; angle = 13; location = new int[]{1,1};
//        points = new int[][]{{2,1},{2,2},{3,4},{1,1}}; angle=90; location=new int[]{1,1};
//        System.out.println(visible.visiblePoints(Arrays.stream(points).map(p->arrToList(p)).collect(Collectors.toList()), angle, arrToList(location)));
//        System.out.println(visible.visiblePoints1(Arrays.stream(points).map(p->arrToList(p)).collect(Collectors.toList()), angle, arrToList(location)));
        System.out.println(visible.visiblePoints(visible.generator(), 14, arrToList(location)));
    }
    List<List<Integer>> generator(){
        List<List<Integer>> res = new ArrayList<>();
        Random random = new Random();
        for(int i=0; i<100_0000; i++){
            List<Integer> list = new ArrayList<>();
            list.add(random.nextInt(1_000_000_000));
            list.add(random.nextInt(1_000_000_000));
            res.add(list);
        }
        return res;
    }
    static List<Integer> arrToList(int[] arr){
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<arr.length; i++)
            list.add(arr[i]);
        return list;
    }
    public int visiblePoints1(List<List<Integer>> points, int angle, List<Integer> location) {
        List<Double> angles = new ArrayList<>();
        int inPos= 0;
        double T = 2*Math.PI;
        long start = System.currentTimeMillis();
        for(List<Integer> point:points){
            if(point.get(1)==location.get(1) && point.get(0)==location.get(0)){
                    inPos++; continue;
            }
            double v = Math.atan2(point.get(1)-location.get(1), point.get(0)-location.get(0));
            if(point.get(1)<0) angles.add(v+T);
            else angles.add(v);
        }
        System.out.println("预处理："+(System.currentTimeMillis()-start));
        start = System.currentTimeMillis();

        angles.sort(Comparator.naturalOrder());
        int L=0, wsize=0, max=0, S=angles.size();
        double angleR= Math.toRadians(angle);
        OUTER: for(int i=0; i< S; i=(i+1)%S){
            while ((angles.get(i)-angles.get(L)+T)%T > angleR){
                L++;
                if(L==S) break OUTER;
                wsize--;
            }
            wsize++;
            if(wsize>=S) return wsize+inPos;
            max=Math.max(max, wsize);
        }
        System.out.println("窗口："+(System.currentTimeMillis()-start));

        return max+inPos;
    }
    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        List<Double> angles = new ArrayList<>();
        int inPos= 0;
        long start = System.currentTimeMillis();
        for(List<Integer> point:points){
            if(point.get(1)==location.get(1)){
                if(point.get(0)==location.get(0)){
                    inPos++; continue;
                }
                angles.add(point.get(0)>location.get(0) ? 0D : 180D);
            }
            else if(point.get(0)==location.get(0))
                angles.add(point.get(1)>location.get(1) ? 90D : 270D);
            else {
                double tan = (point.get(1)-location.get(1))/(double)(point.get(0)-location.get(0));
//                double ang = Math.toDegrees(Math.atan(tan));
                double ang = Math.atan(tan);
                if(point.get(0)>location.get(0) && point.get(1)>location.get(1))
                    angles.add(ang);
                else if(point.get(0)>location.get(0) && point.get(1)<location.get(1))
                    angles.add(ang+360D);
                else angles.add(ang+180D);

            }
        }
        long end = System.currentTimeMillis();
        System.out.println("预处理："+(end-start));
        angles.sort(Comparator.naturalOrder());
        start = System.currentTimeMillis();
        int L=0, wsize=0, max=0, S=angles.size();
        OUTER: for(int i=0; i< S; i=(i+1)%S){
            while ((angles.get(i)-angles.get(L)+360D)%360D > angle){
                L++;
                if(L==S) break OUTER;
                wsize--;
            }
            wsize++;
            if(wsize>=S) return wsize+inPos;
            max=Math.max(max, wsize);
        }
        System.out.println("窗口："+(System.currentTimeMillis()-start));
        return max+inPos;
    }
}
