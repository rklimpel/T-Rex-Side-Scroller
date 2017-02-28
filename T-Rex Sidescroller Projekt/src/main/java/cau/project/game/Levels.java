package main.java.cau.project.game;

import main.java.cau.project.Helper;
import main.java.cau.project.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by ricoklimpel on 25.02.17.
 */
public class Levels {

    private ArrayList<int[][]> levelList= new ArrayList<>();

    private Random rn = new Random();

    private int activeLvl = R.EMPTY;

    public Levels() {
        loadLevelsFromFile();
    }

    /**
     * Get Levels from File and load them into levellist array
     */
    private void loadLevelsFromFile() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                //Load long String of all Level Data from file
                String levelData = null;
                try{
                    levelData = Helper.readFile(R.levelsPath);
                }catch (IOException e){
                    System.out.println("Error while lvl reading: " + e);
                }

                //Levellines Array holds Level Data, splitted in lines
                String[] levelLines = levelData.split("\n");

                //Check String for saved levels and store the number of saved levels
                //Also disable empty lines by setting them to a comment
                int savedLevels=0;
                for (int i = 0; i < levelLines.length; i++) {

                    //LvL is stored with an '#' before it so this is the sign for a new lvl
                    if(levelLines[i].contains("#")){
                        savedLevels+=1;
                    }
                    //if a Line is Empty put the comment marker inside it
                    if(levelLines[i].trim().equals("")){
                        levelLines[i] = levelLines[i] + "//";
                    }

                    //Remove this crazy \r characters from text file
                    levelLines[i].replace("\r","");
                }

                //Check Level Length for every level
                int[] lvlLength = new int[savedLevels];
                int checklvl = -1;
                for (int i = 0; i < levelLines.length; i++) {
                    //If read id lvl start marker go to next lvl
                    if(levelLines[i].contains("#")){
                        checklvl += 1;
                    }
                    //Every line that is not lvl end or comment is a lvl line
                    else if(!levelLines[i].contains("---") && !levelLines[i].contains("//")){
                        lvlLength[checklvl] += 1;
                    }
                }

                System.out.println("saved levels: " + savedLevels);
                for (int i = 0; i < savedLevels; i++) {
                    System.out.println("Level "+i+" length: " +lvlLength[i]);
                }

                //Saves the Data for one lvl
                int[][] lvl = null;
                //remembers which lvl we are watching
                checklvl = 0;
                //counts up wich obstacle for the lvl we're watching
                int lvlIndex = 0;

                for (int i = 0; i < levelLines.length; i++) {

                    if(levelLines[i].contains("#")){

                        lvl = new int[3][lvlLength[checklvl]];
                        checklvl+=1;
                        lvlIndex = 0;

                    }else if(levelLines[i].contains("---")){

                        //add the lvl we read to Level class LevelList Array
                        levelList.add(lvl);

                    }else if (!levelLines[i].contains("//")){

                        //at this point we are looking on one lvl data line
                        //and split it into the different values
                        //on 0 we find the gap value
                        //on 1 we find the type value
                        //on 2 we find the y value
                        String[] values;
                        values = levelLines[i].split("\\|");

                        //Load LVL Object Gaps
                        lvl[0][lvlIndex] = Integer.parseInt(Helper.extractDigits(values[0]));

                        //Load LVL Objects Types
                        if(values.length>=2 && values[1]!=null){
                            lvl[1][lvlIndex] = Integer.parseInt(Helper.extractDigits(values[1]));
                        }

                        //Load LVL Object Y Coordinates
                        if(values.length>=3 && values[2]!=null){
                            lvl[2][lvlIndex] = Integer.parseInt(Helper.extractDigits(values[2]));
                        }

                        try{
                            System.out.println("LvL "+checklvl+" load " + (lvlIndex) +". line: " + lvl[0][lvlIndex] + ", "
                                    + lvl[1][lvlIndex] + ", "
                                    + lvl[2][lvlIndex]);
                        }catch(IndexOutOfBoundsException e){
                            System.out.println(e);
                        }

                        lvlIndex+=1;

                    }
                }
            }
        }).start();
    }

    /**
     * return lvl array for active lvl is active lvl is not -1 (no activ lvl)
     *
     * @return
     */
    public int[][] getActiveLvlArray(){
        if(activeLvl == R.EMPTY){
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
            if(R.onlyLvl==R.EMPTY){
                activeLvl = rn.nextInt(levelList.size()) + 0;
            }else{
                activeLvl = R.onlyLvl;
            }
        }else{
            activeLvl = R.EMPTY;
        }

        System.out.println("lvl loaded: " + activeLvl);

        for (int i = 0; i < levelList.get(activeLvl)[0].length; i++) {
            System.out.print(levelList.get(activeLvl)[0][i] + ",");
        }

        System.out.println();

    }

}