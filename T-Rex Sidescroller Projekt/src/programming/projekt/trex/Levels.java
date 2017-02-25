package programming.projekt.trex;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by ricoklimpel on 25.02.17.
 */
public class Levels {

    ArrayList<int[]> levelList= new ArrayList<>();

    Random rn = new Random();

    //Lvl Arrays
    int[] lvl0 = {300, 300, 300, 300, 300, 300, 300, 300}; // gleiche abstände
    int[] lvl1 = {10, 10, 10, 400, 10, 10}; // zwei dreierblöcke
    int[] lvl2 = {10, 10, 300, 10, 300, 10, 300, 10}; // drei zweierblöcke


    int activeLvl = -1;

    public Levels() {

        levelList.add(lvl0);
        levelList.add(lvl1);
        levelList.add(lvl2);

    }

    public int[] getActiveLvlArray(){
        if(activeLvl == -1){
            return null;
        }
        return levelList.get(activeLvl);
    }

    public int getActiveLvl() {
        return activeLvl;
    }

    public void setActiveLvl(int activeLvl) {
        this.activeLvl = activeLvl;
    }

    public void setRandomLvl(){

        if(levelList.size()!=0){
            activeLvl = rn.nextInt(levelList.size()) + 0;
        }else{
            activeLvl = -1;
        }

        System.out.println("lvl active: " + activeLvl);

    }

}
