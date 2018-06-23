package hr.fer.zemris.java.hw16.jvdraw.graphicalobject.interfaces;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

/**
 * Interface provides methods for mouse events: <br>
 * <ol>
 * <li>mouse pressed</li>
 * <li>mouse released</li>
 * <li>mouse clicked</li>
 * <li>mouse moved</li>
 * <li>mouse dragged</li>
 * </ol>
 * 
 * and for component painting
 * 
 * @author Mihael
 *
 */
public interface Tool {
	/**
	 * Method provides implementation for action when mouse button is pressed
	 * 
	 * @param e
	 *            - mouse event
	 */
	public void mousePressed(MouseEvent e);

	/**
	 * Method provides implementation for action when mouse button is released
	 * 
	 * @param e
	 *            - mouse event
	 */
	public void mouseReleased(MouseEvent e);

	/**
	 * Method provides implementation for action when mouse button is clicked
	 * 
	 * @param e
	 *            - mouse event
	 */
	public void mouseClicked(MouseEvent e);

	/**
	 * Method provides implementation for action when mouse position is changed
	 * 
	 * @param e
	 *            - mouse event
	 */
	public void mouseMoved(MouseEvent e);

	/**
	 * Method provides implementation for action when mouse position is dragged
	 * 
	 * @param e
	 *            - mouse event
	 */
	public void mouseDragged(MouseEvent e);

	/**
	 * Method paints component
	 * 
	 * @param g2d
	 *            - graphics
	 */
	public void paint(Graphics2D g2d);
}
