package leetcode.realtest.realTest20191201_WC165;

import leetcode.realtest.realTest20191124_WC164.NumberofWaystoStayintheSamePlaceAfterSomeSteps;

import java.util.ArrayList;
import java.util.List;

public class NumberofBurgerswithNoWasteofIngredients {
    public static void main(String[] args) {
        NumberofBurgerswithNoWasteofIngredients ingredients=new NumberofBurgerswithNoWasteofIngredients();
        int tomatoSlices = 16, cheeseSlices = 7;
        System.out.println(ingredients.numOfBurgers(tomatoSlices,cheeseSlices));

    }
    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
        int x=(tomatoSlices-2*cheeseSlices);
        int y=(4*cheeseSlices-tomatoSlices);
        System.out.println("x="+x+",y="+y);
        if(x%2==0 && y%2==0 && x>=0 && y>=0){
            List<Integer> list=new ArrayList<>();
            list.add(x/2); list.add(y/2);
            return list;
        }
        return new ArrayList<>();
    }
}
