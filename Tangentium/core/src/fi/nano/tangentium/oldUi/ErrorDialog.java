package fi.nano.tangentium.oldUi;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Class that draws error messages.
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
