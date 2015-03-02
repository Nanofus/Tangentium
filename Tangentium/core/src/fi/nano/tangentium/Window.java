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
        handleInput();
        cam.update();

        batch.setProjectionMatrix(cam.combined);

        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        batch.begin();
        //DRAW
        batch.end();
	}

    private void handleInput() {

        if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            gameMaster.MovePlayer(RIGHT);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            gameMaster.MovePlayer(LEFT);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) {
            gameMaster.MovePlayer(UP);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            gameMaster.MovePlayer(DOWN);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            gameMaster.PlayerUse();
        }

    }

    @Override
    public void dispose() {
        batch.dispose();
    }

}
