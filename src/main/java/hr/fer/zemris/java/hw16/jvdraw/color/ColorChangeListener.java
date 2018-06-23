package hr.fer.zemris.java.hw16.jvdraw.color;

import java.awt.Color;

/**
 * Interface provides method which every color change listener must implement
 * 
 * @author Mihael
 *
 */
public interface ColorChangeListener {
	/**
	 * Method is called when current selected color is changed
	 * 
	 * @param source
	 *            - {@link IColorProvider} where change happened
	 * @param oldColor
	 *            - old color
	 * @param newColor
	 *            - new color
	 */
	public void newColorSelected(IColorProvider source, Color oldColor, Color newColor);

}
