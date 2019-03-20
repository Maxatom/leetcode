package leetcode.problems;

import utils.PrintUtils;
import utils.PrintUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * @author Shibing
 * @since 2018-11-15 14:35:57
 **/
public class MaxPoionsOnALine {
    public static void main(String[] args) {
//       String s="[[1,1],[2,2],[3,3]]";
//        String s="[[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]";
//        String s="[[560,248],[0,16],[30,250],[950,187],[630,277],[950,187],[-212,-268],[-287,-222],[53,37],[-280,-100],[-1,-14],[-5,4],[-35,-387],[-95,11],[-70,-13],[-700,-274],[-95,11],[-2,-33],[3,62],[-4,-47],[106,98],[-7,-65],[-8,-71],[-8,-147],[5,5],[-5,-90],[-420,-158],[-420,-158],[-350,-129],[-475,-53],[-4,-47],[-380,-37],[0,-24],[35,299],[-8,-71],[-2,-6],[8,25],[6,13],[-106,-146],[53,37],[-7,-128],[-5,-1],[-318,-390],[-15,-191],[-665,-85],[318,342],[7,138],[-570,-69],[-9,-4],[0,-9],[1,-7],[-51,23],[4,1],[-7,5],[-280,-100],[700,306],[0,-23],[-7,-4],[-246,-184],[350,161],[-424,-512],[35,299],[0,-24],[-140,-42],[-760,-101],[-9,-9],[140,74],[-285,-21],[-350,-129],[-6,9],[-630,-245],[700,306],[1,-17],[0,16],[-70,-13],[1,24],[-328,-260],[-34,26],[7,-5],[-371,-451],[-570,-69],[0,27],[-7,-65],[-9,-166],[-475,-53],[-68,20],[210,103],[700,306],[7,-6],[-3,-52],[-106,-146],[560,248],[10,6],[6,119],[0,2],[-41,6],[7,19],[30,250]]";
//        String s="[[0,0],[0,0]]";
        String s="[[0,0],[94911151,94911150],[94911152,94911151]]";
        MaxPoionsOnALine max=new MaxPoionsOnALine();
        System.out.println(max.maxPoints(generateByString(s)));

    }


//    public int maxPoints2(Point[] points) {
//
//    }

    //求最大公约数
    public int gcd(int a, int b){
        while(b!=0){
            int r=a%b;
            a=b;
            b=r;
        }
        return a;
    }

    //O(N^2) space O(N^2)
    public int maxPoints(Point[] points) {
       if(points.length<2) return points.length;
        Map<Line, List<Point>> map=new HashMap<>();
        Map<String,Integer> repeatPoints=new HashMap<>();
        removeSamePoints(points,repeatPoints);
        if(repeatPoints.size()==1) return repeatPoints.get(points[0].y+","+points[0].y)+1;
        for (int i=0; i< points.length-1; i++) {
            if(points[i]==null) continue;
            for (int j = i+1; j < points.length; j++) {
                if(points[j]==null) continue;
                Line line=getLine(points[i],points[j]);
                if(map.containsKey(line)){
                    if(!map.get(line).contains(points[i])) map.get(line).add(points[i]);
                    if(!map.get(line).contains(points[j])) map.get(line).add(points[j]);
                }else {
                    List<Point> temp=new ArrayList<>();
                    temp.add(points[i]);
                    temp.add(points[j]);
                    map.put(line,temp);
                }
            }
        }
        int maxSize=0;
        Map.Entry<Line,List<Point>> resultEntry=null;
        for(Map.Entry<Line,List<Point>> entry:map.entrySet()){
            int size=entry.getValue().size();
            for(Point p:entry.getValue()){
                size+=repeatPoints.getOrDefault(p.x+","+p.y, 0);
            }
            if(size>maxSize) {
                resultEntry=entry;
                maxSize=size;
            }
        }
//        utils.printList(resultEntry.getValue(), p->"["+p.x+","+p.y+"]");
       return maxSize;
    }

    public void removeSamePoints(Point[] points, Map<String,Integer> map){
       for(int i=0;i<points.length;i++){
           String key=points[i].x+","+points[i].y;
           if(map.get(key)!=null){
               map.put(key, map.get(key)+1);
               points[i]=null;
           }else {
               map.put(key,0);
           }
       }
    }

    public Line getLine(Point a, Point b){
       Line line=new Line();
       int deltaX=b.x-a.x;
       int deltaY=b.y-a.y;
       if(deltaX==0&&deltaY==0) return null;
       if(deltaX==0){
           line.slope=null;
           line.x0=BigDecimal.valueOf(a.x);
       } else {
           line.slope=BigDecimal.valueOf(deltaY).divide(BigDecimal.valueOf(deltaX),100,RoundingMode.HALF_UP);
           line.x0=BigDecimal.valueOf(a.y).subtract(line.slope.multiply(BigDecimal.valueOf(a.x)));
       }
       return line;
    }

    public static Point[] generateByString(String s){
       int[][] a= PrintUtils.convertStringTo2DIntArray(s);
        Point[] points=new Point[a.length];
        int i=0;
        for (int[] x : a) {
            points[i++] =new Point(x[0],x[1]);
        }
        return points;
    }
}

class Point {
  int x;
  int y;
  Point() { x = 0; y = 0; }
  Point(int a, int b) { x = a; y = b; }
}

//y=slope*x+x0
class Line{
    BigDecimal slope;
    BigDecimal x0;
    Line(){}
    Line(BigDecimal slope,BigDecimal x0){this.slope=slope; this.x0=x0;}

    @Override
    public int hashCode() {
        return Objects.hash(slope,x0);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Line)) return false;
        Line line=(Line)obj;
        if(slope==null && line.slope==null&&x0.equals(line.x0)) return true;
        return slope!=null && line.slope!=null &&slope.equals(line.slope)&&x0.equals(line.x0);
    }
}
