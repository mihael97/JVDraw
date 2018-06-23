package hr.fer.zemris.java.hw16.jvdraw.drawing;

import javax.swing.AbstractListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import hr.fer.zemris.java.hw16.jvdraw.drawing.interfaces.DrawingModel;
import hr.fer.zemris.java.hw16.jvdraw.drawing.interfaces.DrawingModelListener;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.GeometricalObject;

/**
 * Class represents list model<br>
 * It extends {@link AbstractListModel} where already implementation for
 * listeners handling exists. In constructor,class accepts reference to
 * {@link DrawingModel} where {@link GeometricalObject} are stored. When context
 * is changed,model informs all listeners(this list model also) about change so
 * we can have real time informations in our GUI list model<br>
 * 
 * @author Mihael
 *
 */
public class DrawingObjectListModel extends AbstractListModel<GeometricalObject> implements DrawingModelListener {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Reference to drawing model
	 */
	private DrawingModel model;

	/**
	 * Constructor for list model initialization
	 * 
	 * @param model
	 *            - drawing model
	 */
	public DrawingObjectListModel(DrawingModel model) {
		super();
		this.model = model;
		this.model.addDrawingModelListener(this);
	}

	/**
	 * Method returns element and given position. If element doesn't exist,method
	 * will return <code>null</code>
	 * 
	 * @param index
	 *            - position
	 */
	@Override
	public GeometricalObject getElementAt(int index) {
		return model.getObject(index);
	}

	/**
	 * Method return number of stored {@link GeometricalObject}s
	 * 
	 * @return number of stored objects
	 */
	@Override
	public int getSize() {
		return model.getSize();
	}

	/**
	 * Method receives information about addition and updates list
	 * 
	 * @param source
	 *            - drawing model where change happened
	 * 
	 * @param index0
	 *            - interval's index of beginning
	 * 
	 * @param index1
	 *            - interval's index of ending
	 */
	@Override
	public void objectsAdded(DrawingModel source, int index0, int index1) {
		ListDataEvent event = new ListDataEvent(source, ListDataEvent.INTERVAL_ADDED, index0, index1);

		for (ListDataListener listener : super.getListDataListeners()) {
			listener.intervalAdded(event);
		}
	}

	/**
	 * Method receives information about removed items and updates list
	 * 
	 * @param source
	 *            - drawing model where change happened
	 * 
	 * @param index0
	 *            - interval's index of beginning
	 * 
	 * @param index1
	 *            - interval's index of ending
	 */
	@Override
	public void objectsRemoved(DrawingModel source, int index0, int index1) {
		ListDataEvent event = new ListDataEvent(source, ListDataEvent.INTERVAL_REMOVED, index0, index1);

		for (ListDataListener listener : super.getListDataListeners()) {
			listener.intervalRemoved(event);
		}

	}

	/**
	 * Method receives information about changed objects and updates list
	 * 
	 * @param source
	 *            - drawing model where change happened
	 * 
	 * @param index0
	 *            - interval's index of beginning
	 * 
	 * @param index1
	 *            - interval's index of ending
	 */
	@Override
	public void objectsChanged(DrawingModel source, int index0, int index1) {
		ListDataEvent event = new ListDataEvent(source, ListDataEvent.CONTENTS_CHANGED, index0, index1);

		for (ListDataListener listener : super.getListDataListeners()) {
			listener.contentsChanged(event);
		}
	}
}
