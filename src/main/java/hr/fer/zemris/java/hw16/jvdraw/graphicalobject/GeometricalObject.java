package hr.fer.zemris.java.hw16.jvdraw.graphicalobject;

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
}
