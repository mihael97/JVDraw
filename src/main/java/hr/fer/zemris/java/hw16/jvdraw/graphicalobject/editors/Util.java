package hr.fer.zemris.java.hw16.jvdraw.graphicalobject.editors;

import java.awt.TextField;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Class contains methods for parameters validation checking
 * 
 * @author Mihael
 *
 */
public abstract class Util {
	/**
	 * Method checks if RGB color has valid components<br>
	 * Color is valid if every color component is <code>between 0 and 256</code>
	 * 
	 * @param red
	 *            - red component
	 * @param green
	 *            - green component
	 * @param blue
	 *            - blue component
	 * @return <code>true</code> if color is valid,otherwise <code>false</code>
	 */
	public static boolean checkRGB(Integer red, Integer green, Integer blue) {
		if (!(red >= 0 && red <= 256 && green >= 0 && green <= 256 && blue >= 0 && blue <= 256)) {
			return false;
		}

		return true;
	}

	/**
	 * Method checks if radius is in valid interval<br>
	 * Valid interval is <code>between 0 and maximal double value</code>
	 * 
	 * @param radius
	 *            - radius
	 * @return <code>true</code> if radius is valid,otherwise <code>false</code>
	 */
	public static boolean checkRadius(Double radius) {
		if (radius < 0 || radius > Double.MAX_VALUE) {
			return false;
		}

		return true;
	}

	/**
	 * Method gets Point coordinates from {@link JPanel}
	 * 
	 * @param panel
	 *            - {@link JPanel} with point coordinates
	 * @return array with coordinates where first element is
	 *         <code>x coordinate</code> and second is <code>y coordinate</code>
	 */
	public static int[] getPoint(JPanel panel) {
		int[] forReturn = new int[2];
		forReturn[0] = Integer.parseInt(((JTextField) (panel.getComponentAt(1, 1))).getText());
		forReturn[1] = Integer.parseInt(((JTextField) (panel.getComponentAt(2, 1))).getText());

		return forReturn;
	}

	/**
	 * Method adds {@link JTextField} into panel. <br>
	 * Argument b shows how many {@link JTextField} we want to add. If we want add
	 * <code>3</code> fields,argument will be <code>true</code>. Otherwise if we
	 * want to add <code>2</code> fields,value will be <code>false</code>
	 * 
	 * @param mainPanel
	 *            - panel where we put fields
	 * @param b
	 *            - boolean argument which shows how many fields we want add
	 */
	public static void addPanels(JPanel mainPanel, boolean b) {
		JPanel panel = new JPanel();
		mainPanel.add(panel);
		panel.add(new TextField(3));
		panel.add(new TextField(3));
		if (b == true) {
			panel.add(new TextField(3));
		}
	}

	/**
	 * Method adds on panel form for point editing<br>
	 * For every <code>point form </code>we must have two {@link JTextField}
	 * 
	 * @param mainPanel
	 *            - panel where we put our label and fields
	 * @param string
	 *            - appropriate message,text of label
	 */
	public static void addPoint(JPanel mainPanel, String string) {
		mainPanel.add(new JLabel(string));
		addPanels(mainPanel, false);
	}

	/**
	 * Method adds on panel form for radius editing<br>
	 * For every <code>radius form </code>we must have one {@link JTextField}
	 * 
	 * @param panel
	 *            - panel where we put our label and fields
	 */
	public static void addRadius(JPanel panel) {
		panel.add(new JLabel("Radius: "));
		panel.add(new JTextField(5));
	}
}
