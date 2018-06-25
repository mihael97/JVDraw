package hr.fer.zemris.java.hw16.jvdraw.JVDraw;

import javax.swing.JOptionPane;

/**
 * Class contains method for showing error messages
 * 
 * @author Mihael
 *
 */
public abstract class ErrorClass {
	/**
	 * Method shows message given by argument
	 * 
	 * @param errorMessage
	 *            - message for show
	 */
	public static void showError(String errorMessage) {
		JOptionPane.showMessageDialog(null, errorMessage, "ERROR!", JOptionPane.ERROR_MESSAGE);
	}
}
