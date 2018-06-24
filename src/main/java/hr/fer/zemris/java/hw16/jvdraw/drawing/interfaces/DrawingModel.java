package hr.fer.zemris.java.hw16.jvdraw.drawing.interfaces;

import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.GeometricalObject;

/**
 * Interface provides all methods {@link DrawingModel} must implement
 * 
 * @author Mihael
 *
 */
public interface DrawingModel {
	/**
	 * Method returns number of stored objects
	 * 
	 * @return number of stored objects
	 */
	public int getSize();

	/**
	 * Method returns {@link GeometricalObject} at given index
	 * 
	 * @param index
	 *            - position
	 * @return {@link GeometricalObject} at given index. If element doesn't exist,it
	 *         will return <code>null</code>
	 */
	public GeometricalObject getObject(int index);

	/**
	 * Method adds object into list of stored objects
	 * 
	 * @param object
	 *            - object we want to add
	 * 
	 * @throws NullPointerException
	 *             - if argument is <code>null</code>
	 */
	public void add(GeometricalObject object);
	
	
	/**
	 * Method removes object into list of stored objects
	 * 
	 * @param object
	 *            - object we want to remove
	 * 
	 */
	public void remove(GeometricalObject object);

	/**
	 * Method adds drawing model listener
	 * 
	 * @param l
	 *            - drawing model listener we want to add
	 * 
	 * @throws NullPointerException
	 *             - if listener is <code>null</code>
	 */
	public void addDrawingModelListener(DrawingModelListener l);

	/**
	 * Method removes listener from listeners list
	 * 
	 * @param l
	 *            - listener we want to remove
	 */
	public void removeDrawingModelListener(DrawingModelListener l);
}
