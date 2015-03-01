/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.nano.tangentium.fileProcessing;

import fi.nano.tangentium.ui.ErrorDialog;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
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
