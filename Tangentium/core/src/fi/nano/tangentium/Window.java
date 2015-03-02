package fi.nano.tangentium;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fi.nano.tangentium.gameLogic.Position;
import fi.nano.tangentium.input.InputHandler;
import fi.nano.tangentium.ui.SpriteManager;

public class Window extends ApplicationAdapter {

    private GameMaster gameMaster = new GameMaster(this);
    private InputHandler inputHandler = new InputHandler(gameMaster);
    private SpriteManager spriteManager = new SpriteManager();

    private OrthographicCamera cam;
    private SpriteBatch batch;

    private int TILE_WIDTH = 64;
    private int TILE_HEIGHT = 32;

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

    /**
     * Converts a 2D position into an isometric position
     *
     * @param position Position in 2D coordinates
     * @return New position in isometric coordinates.
     */
    public Position TwoDToIso(Position position) {
        Position pos = new Position(0, 0);
        pos.x = (position.x - position.y) * (TILE_WIDTH / 2);
        pos.y = (position.x + position.y) * (TILE_HEIGHT / 2);
        return pos;
    }
}