package hr.fer.zemris.java.hw16.jvdraw.color;

import java.awt.Color;

/**
 * Interface provides all method every color provider must have
 * 
 * @author Mihael
 *
 */
public interface IColorProvider {
	/**
	 * Method returns current selected color
	 * 
	 * @return current selected color
	 */
	public Color getCurrentColor();

	/**
	 * Method adds color change listener into listener list
	 * 
	 * @param l
	 *            - listener for add
	 */
	public void addColorChangeListener(ColorChangeListener l);

	/**
	 * Method removes color change listener from listener list
	 * 
	 * @param l
	 *            - listener we want to remove
	 */
	public void removeColorChangeListener(ColorChangeListener l);
}
