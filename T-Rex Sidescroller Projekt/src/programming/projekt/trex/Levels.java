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
    int[] lvl3 = {400, 10, 400, 350, 300, 250, 230, 10}; //nice one
    int[] lvl4 = {10,400,10,10,10,400,10,200};
    int[] lvl5 = {500, 10, 200,10};
    int[] lvl6 = {10,200,250,300,350,400,35,220,35};

    int activeLvl = -1;

    public Levels() {

        levelList.add(lvl0);
        levelList.add(lvl1);
        levelList.add(lvl2);
        levelList.add(lvl3);
        levelList.add(lvl4);
        levelList.add(lvl5);
        levelList.add(lvl6);

    }

    /**
     * return lvl array for active lvl is active lvl is not -1 (no activ lvl)
     *
     * @return
     */
    public int[] getActiveLvlArray(){
        if(activeLvl == -1){
            return null;
        }
        return levelList.get(activeLvl);
    }

    /**
     *
     * @return active lvl number
     */
    public int getActiveLvl() {
        return activeLvl;
    }

    /**
     * sets active lvl number
     *
     * @param activeLvl
     */
    public void setActiveLvl(int activeLvl) {
        this.activeLvl = activeLvl;
    }

    /**
     * generates a random number and set active lvl to this number
     */
    public void setRandomLvl(){

        if(levelList.size()!=0){
            activeLvl = rn.nextInt(levelList.size()) + 0;
        }else{
            activeLvl = -1;
        }

        System.out.println("lvl loaded: " + activeLvl);
    }

}
