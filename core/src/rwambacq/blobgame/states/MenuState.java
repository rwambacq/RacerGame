package rwambacq.blobgame.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuState extends State {

    private Texture button;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        button = new Texture("startButton.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
            dispose();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();

        int size_ratio = (Gdx.graphics.getWidth()/2)/button.getWidth();

        sb.draw(button, (Gdx.graphics.getWidth()/2) - (button.getWidth()/2), (Gdx.graphics.getHeight()/2) - (button.getHeight()/2));
        sb.end();
    }

    @Override
    public void dispose() {
        button.dispose();
    }
}
