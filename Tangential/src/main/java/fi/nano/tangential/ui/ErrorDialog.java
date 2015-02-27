package fi.nano.tangential.ui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Luokka joka piirtää virheilmoitukset
 *
 * @author Nanofus
 */
public class ErrorDialog {

    JFrame frame = new JFrame();

    public ErrorDialog() {

    }

    public void ShowError(String message) {
        JOptionPane.showMessageDialog(frame,
                message,
                "Fatal error",
                JOptionPane.ERROR_MESSAGE);
    }
}
