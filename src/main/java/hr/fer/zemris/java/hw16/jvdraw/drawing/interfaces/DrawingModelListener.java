package hr.fer.zemris.java.hw16.jvdraw.drawing.interfaces;

/**
 * Interface provides method which evert {@link DrawingModelListener} must
 * implement
 * 
 * @author Mihael
 *
 */
public interface DrawingModelListener {
	/**
	 * Method is called when object is added
	 * 
	 * @param source
	 *            - model where change happened
	 * @param index0
	 *            - start of interval
	 * @param index1
	 *            - end of interval
	 */
	public void objectsAdded(DrawingModel source, int index0, int index1);

	/**
	 * Method is called when object is removed
	 * 
	 * @param source
	 *            - model where change happened
	 * @param index0
	 *            - start of interval
	 * @param index1
	 *            - end of interval
	 */
	public void objectsRemoved(DrawingModel source, int index0, int index1);

	/**
	 * Method is called when object is changed
	 * 
	 * @param source
	 *            - model where change happened
	 * @param index0
	 *            - start of interval
	 * @param index1
	 *            - end of interval
	 */
	public void objectsChanged(DrawingModel source, int index0, int index1);
}
