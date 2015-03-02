package fi.nano.tangentium.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import fi.nano.tangentium.fileProcessing.SpriteListReader;

import java.util.ArrayList;

public class SpriteManager {

    ArrayList<String> spriteName = new ArrayList<>();
    ArrayList<Texture> textures = new ArrayList<>();

    AssetManager manager = new AssetManager();

    public SpriteManager() {
        LoadSprites();
    }

    public void LoadSprites() {
        SpriteListReader spriteListReader = new SpriteListReader();

        ArrayList<String> actorNames = spriteListReader.GetActorList();
        for (String actorname : actorNames) {
            System.out.println("Loading sprite '"+actorname+"'");
            spriteName.add(actorname);
            Texture texture = new Texture(Gdx.files.internal("data/graphics/actors/"+actorname+".png"));
            textures.add(texture);
        }
    }

    public ArrayList<String> GetNames() {
        return spriteName;
    }

    public ArrayList<Texture> GetTextures() {
        return textures;
    }
}
