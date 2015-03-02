package fi.nano.tangentium.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import fi.nano.tangentium.GameMaster;
import fi.nano.tangentium.gameLogic.enums.Direction;

/**
 * Created by Nanofus on 2.3.2015.
 */
public class InputHandler {
    private GameMaster gameMaster;

    public InputHandler(GameMaster gameMaster) {
        this.gameMaster = gameMaster;
    }

    public void HandleInput() {

            if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                gameMaster.MovePlayer(Direction.RIGHT);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                gameMaster.MovePlayer(Direction.LEFT);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) {
                gameMaster.MovePlayer(Direction.UP);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                gameMaster.MovePlayer(Direction.DOWN);
            }

            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                gameMaster.PlayerUse();
            }

    }


}
