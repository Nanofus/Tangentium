package fi.nano.tangentium;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static fi.nano.tangentium.gameLogic.enums.Direction.*;

public class Window extends ApplicationAdapter {

    private GameMaster gameMaster = new GameMaster(this);

    private InputHandler inputHandler = new InputHandler(gameMaster);

    private OrthographicCamera cam;
    private SpriteBatch batch;


	@Override
	public void create () {

        cam = new OrthographicCamera(200,200);
        cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
        cam.update();

        batch = new SpriteBatch();

	}

	@Override
	public void render () {
        inputHandler.HandleInput();
        cam.update();

        batch.setProjectionMatrix(cam.combined);

        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        batch.begin();
        //DRAW
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

}
