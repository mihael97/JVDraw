package hr.fer.zemris.java.hw16.jvdraw.graphicalobject.editors;

import javax.swing.JPanel;

import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.GeometricalObject;

/**
 * Abstract class all method {@link GeometricalObjectEditor} must have
 * 
 * @author Mihael
 *
 */
public abstract class GeometricalObjectEditor extends JPanel {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Method checks if every value from <code>form</code> is in valid value
	 * interval
	 */
	public abstract void checkEditing();

	/**
	 * Method sets {@link GeometricalObject} parameters to values in
	 * <code>form</code>
	 */
	public abstract void acceptEditing();
}
