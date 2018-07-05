package rwambacq.blobgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import rwambacq.blobgame.states.GameStateManager;
import rwambacq.blobgame.states.MenuState;

public class RacerMain extends ApplicationAdapter {

	private GameStateManager gm;
	private SpriteBatch spriteBatch;

	@Override
	public void create () {
		Gdx.gl.glClearColor((float)0.8549, (float)0.6666, (float)1, 1);
		spriteBatch = new SpriteBatch();
		gm = new GameStateManager();
		gm.push(new MenuState(gm));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Wipes screen clean and redraws everything

		gm.update(Gdx.graphics.getDeltaTime());

		gm.render(spriteBatch);
	}
	
	@Override
	public void dispose () {
	}
}
