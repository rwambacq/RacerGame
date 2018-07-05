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

    private Texture tunnelPixel;
    private Texture obstacle;

    private ArrayBlockingQueue<Boolean[]> screenObstacles;

    private int obstacleSize = (Gdx.graphics.getWidth()-3)/4;
    private int topObstacleSize = 0;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        obstacles = new ObstacleSeries();
        obstacle = new Texture("tile.png");
        tunnelPixel = new Texture("pixel_wit.png");
        screenObstacles = new ArrayBlockingQueue<Boolean[]>(8);
        for (int i = 0; i < 8; i++) {
            Boolean[] array = new Boolean[4];
            Arrays.fill(array, Boolean.FALSE);
            screenObstacles.add(array);
        }
    }

    @Override
    protected void handleInput() {
    }

    @Override
    public void update(float dt) {
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
        Iterator<Boolean[]> iterator = screenObstacles.iterator();
        for (int i = 0; i < screenObstacles.size(); i++) {
            Boolean[] nextRow = iterator.next();
            for (int j = 0; j < nextRow.length; j++) {
                if(nextRow[j]){
                    sb.draw(obstacle, (j*obstacleSize) + j, (i*obstacleSize) - topObstacleSize, obstacleSize, obstacleSize);
                }
            }
        }
        sb.end();
    }

    @Override
    public void dispose() {
    }
}
