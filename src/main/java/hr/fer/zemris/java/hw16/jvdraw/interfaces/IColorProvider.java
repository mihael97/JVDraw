package hr.fer.zemris.java.hw16.jvdraw.interfaces;

import java.awt.Color;

/**
 * Interface provides all method every color provider must have. They are:<br>
 * <ol>
 * <li>getCurrentColor</li>
 * <li>addColorChangeListener</li>
 * <li>removeColorChangeListener</li>
 * </ol>
 * 
 * @author ime
 *
 */
public interface IColorProvider {
	/**
	 * Method returns current active color
	 * 
	 * @return current active color
	 */
	Color getCurrentColor();

	/**
	 * Method adds color change listener
	 * 
	 * @param listener
	 *            - listener we want to add
	 */
	void addColorChangeListner(ColorChangeListener listener);

	/**
	 * Method removes color change listener
	 * 
	 * @param listener
	 *            - listener we want to remove
	 */
	void removeColorChangeListener(ColorChangeListener listener);
}
