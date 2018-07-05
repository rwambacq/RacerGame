package rwambacq.blobgame.sprites;

import java.util.ArrayList;
import java.util.Random;

public class ObstacleSeries {

    private Boolean[][] inBetween = new Boolean[][]{{false, false, false, false},
                                                    {false, false, false, false}};
    private boolean takingABreak;

    private ArrayList<Boolean[][]> obstacles = new ArrayList<Boolean[][]>();
    private Boolean[][] layoutOne = new Boolean[][]{
            {false, false, false, false},
            {true, false, false, false},
            {false, true, false, false},
            {false, false, false, false},
            {false, false, false, true},
            {false, false, true, false}};

    private Boolean[][] layoutTwo = new Boolean[][]{
            {false, true, true, true},
            {false, true, false, false},
            {false, true, false, false},
            {false, false, false, false},
            {false, false, false, false},
            {false, false, true, false},
            {false, false, true, false},
            {true, true, true, false}};

    private Boolean[][] layoutThree = new Boolean[][]{
            {false, false, true, false},
            {false, false, false, true},
            {false, false, false, true},
            {false, true, false, false},
            {true, false, false, false},
            {true, false, false, false}};

    private Boolean[][] currentObstacle;
    private int currentRow;

    private Random r;

    public ObstacleSeries(){
        r = new Random();
        obstacles.add(layoutOne);
        obstacles.add(layoutTwo);
        obstacles.add(layoutThree);

        currentObstacle = obstacles.get(r.nextInt(obstacles.size()));
        currentRow = currentObstacle.length-1;

        takingABreak = false;
    }

    public void update(float dt){}

    public Boolean[] getNextRow(){
        Boolean[] toReturn = currentObstacle[currentRow];
        if(currentRow != 0){
            currentRow--;
        } else if (currentRow == 0 && !takingABreak){
            takingABreak = true;
            currentObstacle = inBetween;
            currentRow = inBetween.length-1;
        } else if (currentRow == 0 && takingABreak) {
            takingABreak = false;
            currentObstacle = obstacles.get(r.nextInt(obstacles.size()));
            currentRow = currentObstacle.length-1;
        }
        return toReturn;
    }
}
