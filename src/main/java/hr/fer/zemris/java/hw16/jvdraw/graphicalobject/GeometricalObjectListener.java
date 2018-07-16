package hr.fer.zemris.java.hw16.jvdraw.graphicalobject;

/**
 * Interface provides method which every {@link GeometricalObjectListener} must
 * implement
 * 
 * @author Mihael
 *
 */
public interface GeometricalObjectListener {
	/**
	 * Method is called when {@link GeometricalObject} is changed
	 * 
	 * @param o
	 *            - changed object
	 */
	public void geometricalObjectChanged(GeometricalObject o);
}
