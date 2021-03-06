package hr.fer.zemris.java.hw16.jvdraw.drawing;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import hr.fer.zemris.java.hw16.jvdraw.JVDraw.ErrorClass;
import hr.fer.zemris.java.hw16.jvdraw.drawing.interfaces.DrawingModel;
import hr.fer.zemris.java.hw16.jvdraw.drawing.interfaces.DrawingModelListener;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.GeometricalObject;

/**
 * Class implements {@link DrawingModel} and provides implementation for all
 * methods
 * 
 * @author Mihael
 *
 */
public class DrawingModelImplementation implements DrawingModel {
	/**
	 * List of stored objects
	 */
	private List<GeometricalObject> objects = new ArrayList<>();
	/**
	 * List of drawing model listeners
	 */
	private List<DrawingModelListener> listeners = new ArrayList<>();

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.hw16.jvdraw.drawing.interfaces.DrawingModel#getSize()
	 */
	@Override
	public int getSize() {
		return objects.size();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.hw16.jvdraw.drawing.interfaces.DrawingModel#getObject(int)
	 */
	@Override
	public GeometricalObject getObject(int index) {
		return objects.get(index);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.hw16.jvdraw.drawing.interfaces.DrawingModel#add(hr.fer.zemris.java.hw16.jvdraw.graphicalobject.GeometricalObject)
	 */
	@Override
	public void add(GeometricalObject object) {
		objects.add(Objects.requireNonNull(object, "Geometrical object cannot be null!"));
		object.addGeometricalObjectListener(p -> {

			listeners.forEach(
					e -> e.objectsChanged(DrawingModelImplementation.this, objects.indexOf(p), objects.indexOf(p)));
		});
		listeners.forEach(e -> e.objectsAdded(this, objects.size() - 1, objects.size() - 1));
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.hw16.jvdraw.drawing.interfaces.DrawingModel#addDrawingModelListener(hr.fer.zemris.java.hw16.jvdraw.drawing.interfaces.DrawingModelListener)
	 */
	@Override
	public void addDrawingModelListener(DrawingModelListener l) {
		listeners.add(Objects.requireNonNull(l, "Drawing listener cannot be null!"));
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.hw16.jvdraw.drawing.interfaces.DrawingModel#removeDrawingModelListener(hr.fer.zemris.java.hw16.jvdraw.drawing.interfaces.DrawingModelListener)
	 */
	@Override
	public void removeDrawingModelListener(DrawingModelListener l) {
		listeners.remove(l);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.hw16.jvdraw.drawing.interfaces.DrawingModel#remove(hr.fer.zemris.java.hw16.jvdraw.graphicalobject.GeometricalObject)
	 */
	@Override
	public void remove(GeometricalObject object) {
		int index = objects.indexOf(object);
		objects.remove(object);
		listeners.forEach(e -> e.objectsRemoved(this, index, index));
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.hw16.jvdraw.drawing.interfaces.DrawingModel#changeOrder(hr.fer.zemris.java.hw16.jvdraw.graphicalobject.GeometricalObject,
	 *      int)
	 */
	@Override
	public void changeOrder(GeometricalObject object, int offset) {
		try {
			int index = objects.indexOf(object);

			if (index + offset < 0 || index + offset > (objects.size() - 1)) {
				return;
			}

			objects.remove(index);
			objects.add(index + offset, object);

			int upper = (offset > 0) ? index + offset : index;
			int lower = (offset < 0) ? index + offset : index;

			listeners.forEach(e -> e.objectsChanged(this, lower, upper));
		} catch (IndexOutOfBoundsException e) {
			ErrorClass.showError(e.getLocalizedMessage());
		}
	}

}
