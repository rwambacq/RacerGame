package rwambacq.blobgame.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;

import rwambacq.blobgame.sprites.ObstacleSeries;

public class PlayState extends State {

    private ObstacleSeries obstacles;

    private Texture pixelWhite;
    private Texture obstacle;
    private Texture rightArrow;
    private Texture leftArrow;

    private ArrayBlockingQueue<Boolean[]> screenObstacles;

    private int obstacleSize = (Gdx.graphics.getWidth()-3)/4;
    private int topObstacleSize = 0;

    protected PlayState(GameStateManager gsm) {
        super(gsm);

        obstacles = new ObstacleSeries();

        obstacle = new Texture("tile.png");
        pixelWhite = new Texture("pixel_wit.png");
        rightArrow = new Texture("arrow_right.png");
        leftArrow = new Texture("arrow_left.png");

        screenObstacles = new ArrayBlockingQueue<Boolean[]>(8);
        for (int i = 0; i < 8; i++) {
            Boolean[] array = new Boolean[4];
            Arrays.fill(array, Boolean.FALSE);
            screenObstacles.add(array);
        }
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            if(Gdx.input.getX()>Gdx.graphics.getWidth()/2){
                System.out.println("Move Right");
            } else if(Gdx.input.getX() <= Gdx.graphics.getWidth()/2){
                System.out.println("Move left");
            }
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        if(topObstacleSize < obstacleSize){
            topObstacleSize += 15;
        } else {
            topObstacleSize = 0;
            screenObstacles.poll();
            screenObstacles.add(obstacles.getNextRow());
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        for (int i = 1; i < 4; i++) {
            sb.draw(pixelWhite, (Gdx.graphics.getWidth()/4)*i, 0,
                    1, Gdx.graphics.getHeight());
        }
        Iterator<Boolean[]> iterator = screenObstacles.iterator();
        for (int i = 0; i < screenObstacles.size(); i++) {
            Boolean[] nextRow = iterator.next();
            for (int j = 0; j < nextRow.length; j++) {
                if(nextRow[j]){
                    sb.draw(obstacle, (j*obstacleSize) + j, (i*obstacleSize) - topObstacleSize, obstacleSize, obstacleSize);
                }
            }
        }
        sb.draw(leftArrow, 20, 20, Gdx.graphics.getWidth()/4, Gdx.graphics.getWidth()/4);
        sb.draw(rightArrow, Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()/4)-20, 20,Gdx.graphics.getWidth()/4, Gdx.graphics.getWidth()/4);
        sb.end();
    }

    @Override
    public void dispose() {
        rightArrow.dispose();
        leftArrow.dispose();
        pixelWhite.dispose();
        obstacle.dispose();
    }
}
