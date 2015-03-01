package fi.nano.tangentium.fileProcessing;

import fi.nano.tangentium.ui.ErrorDialog;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that reads text files into ArrayLists.
 * 
 * @author Nanofus
 */
public class FileReader {

    private final ErrorDialog errorDialog;

    public FileReader() {
        errorDialog = new ErrorDialog();
    }

    public ArrayList<String> ReadFile(String filePath) {
        ArrayList<String> readStrings = new ArrayList<>();
        Scanner in = null;

        try {
            in = new Scanner(new File(filePath), "UTF-8");
        } catch (FileNotFoundException ex) {
            errorDialog.ShowError("File '" + filePath + "' not found! Exiting...");
            System.exit(1);
        }

        while (in.hasNext()) {
            readStrings.add(in.nextLine());
        }

        return readStrings;
    }
}
