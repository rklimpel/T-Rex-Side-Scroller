package programming.projekt.trex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by ricoklimpel on 25.02.17.
 */
public class Levels {

    ArrayList<int[]> levelList= new ArrayList<>();

    Random rn = new Random();

    int activeLvl = R.EMPTY;

    public Levels() {
        loadLevelsFromFile();
    }

    private void loadLevelsFromFile() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                Helper helper = new Helper();

                String levelData = null;

                try{
                    levelData = helper.readFile(R.levelsPath);
                }catch (IOException e){
                    System.out.println("Error while lvl reading: " + e);
                }

                int savedLevels=0;
                String[] levelLines = levelData.split("\n");
                for (int i = 0; i < levelLines.length; i++) {
                    if(levelLines[i].contains("#")){
                        savedLevels+=1;
                    }
                    if(levelLines[i].trim().equals("")){
                        levelLines[i] = levelLines[i] + "//";
                    }
                }

                int[] lvlLength = new int[savedLevels];
                int checklvl = -1;
                for (int i = 0; i < levelLines.length; i++) {
                    if(levelLines[i].contains("#")){
                        checklvl += 1;
                    }else if(!levelLines[i].contains("---") && !levelLines[i].contains("//")){
                        lvlLength[checklvl] += 1;
                    }
                }

                System.out.println("saved levels: " + savedLevels);
                System.out.println("lvl0 length: " + lvlLength[0]);
                System.out.println("lvl1 length: " + lvlLength[1]);

                int[][] lvl = new int[3][levelLines.length];

                checklvl = 0;
                int lvlIndex = 0;

                for (int i = 0; i < levelLines.length; i++) {

                    if(levelLines[i].contains("#")){

                        lvl = new int[3][lvlLength[checklvl]];
                        checklvl+=1;
                        lvlIndex = 0;

                    }else if(levelLines[i].contains("---")){

                        levelList.add(lvl[0]);

                    }else if (!levelLines[i].contains("//")){

                        String[] values = new String[3];
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
    public int[] getActiveLvlArray(){
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
            activeLvl = rn.nextInt(levelList.size()) + 0;
        }else{
            activeLvl = R.EMPTY;
        }

        System.out.println("lvl loaded: " + activeLvl);

        for (int i = 0; i < levelList.get(activeLvl).length; i++) {
            System.out.print(levelList.get(activeLvl)[i] + ",");
        }

        System.out.println();

    }

}