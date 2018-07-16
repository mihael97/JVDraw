package hr.fer.zemris.java.hw16.jvdraw.graphicalobject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.editors.GeometricalObjectEditor;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.visitors.GeometricalObjectVisitor;

/**
 * Class represents top hierarchy class for {@link GeometricalObject}
 * representation
 * 
 * @author Mihael
 *
 */
public abstract class GeometricalObject {
	/**
	 * List of all {@link GeometricalObject} listeners
	 */
	private List<GeometricalObjectListener> listeners = new ArrayList<>();

	/**
	 * Method accepts {@link GeometricalObjectVisitor}
	 * 
	 * @param v
	 *            - {@link GeometricalObjectVisitor}
	 */
	public abstract void accept(GeometricalObjectVisitor v);

	/**
	 * Method returns {@link GeometricalObjectEditor} for parameters editing
	 * 
	 * @return {@link GeometricalObjectEditor}
	 */
	public abstract GeometricalObjectEditor createGeometricalObjectEditor();

	/**
	 * Method adds {@link GeometricalObjectListener} into listeners list
	 * 
	 * @param l
	 *            - listener we want to add
	 * @throws NullPointerException
	 *             - if listener is <code>null</code>
	 */
	public void addGeometricalObjectListener(GeometricalObjectListener l) {
		listeners.add(Objects.requireNonNull(l));
	}

	/**
	 * Method removes {@link GeometricalObjectListener} from listeners list
	 * 
	 * @param l
	 * @throws NullPointerException
	 *             - if listener is <code>null</code>
	 */
	public void removeGeometricalObjectListener(GeometricalObjectListener l) {
		listeners.remove(Objects.requireNonNull(l));
	}

	/**
	 * Method informs every listener that change is made on object
	 */
	public void inform() {
		listeners.forEach(e -> e.geometricalObjectChanged(this));
	}
}
