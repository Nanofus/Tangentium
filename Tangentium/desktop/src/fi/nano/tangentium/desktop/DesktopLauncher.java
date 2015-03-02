package fi.nano.tangentium.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import fi.nano.tangentium.Window;
import fi.nano.tangentium.fileProcessing.ConfigReader;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        ConfigReader configReader = new ConfigReader();
        config.title = "Tangentium";
        //config.useGL30 = true;
        config.height = configReader.GetWindowHeight();
        config.width = configReader.GetWindowWidth();

        config.addIcon("icon.png", Files.FileType.Internal);

		new LwjglApplication(new Window(), config);
	}
}
