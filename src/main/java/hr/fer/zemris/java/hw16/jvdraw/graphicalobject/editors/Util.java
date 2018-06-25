package hr.fer.zemris.java.hw16.jvdraw.graphicalobject.editors;

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
		forReturn[0] = Integer.parseInt(((JTextField) (panel.getComponents()[0])).getText());
		forReturn[1] = Integer.parseInt(((JTextField) (panel.getComponents()[1])).getText());

		return forReturn;
	}

	/**
	 * Method creates {@link JTextField} with given text
	 * 
	 * @param text
	 *            - text
	 * @return {@link JTextField}
	 */
	public static JTextField makeField(String text) {
		JTextField field = new JTextField(3);
		field.setText(text);
		return field;
	}

	/**
	 * Method accepts {@link JPanel} with 3 {@link JTextField} where every
	 * {@link JTextField} represents one of color components<br>
	 * Value for return is array where integer values of colors are stored in RGB
	 * format(on index <code>0</code> is <code>red</code>,on index <code>1</code> is
	 * <code>green</code> and on index <code>2</code> is <code>blue</code>
	 * 
	 * @param panel
	 *            - panel with colors
	 * @return array with integer values of color
	 */
	public static int[] getColorFromPanel(JPanel panel) {
		int[] array = { 0, 0, 0 };
		array[0] = Integer.parseInt(((JTextField) (panel.getComponents()[0])).getText());
		array[1] = Integer.parseInt(((JTextField) (panel.getComponents()[1])).getText());
		array[2] = Integer.parseInt(((JTextField) (panel.getComponents()[2])).getText());
		return array;
	}
}
