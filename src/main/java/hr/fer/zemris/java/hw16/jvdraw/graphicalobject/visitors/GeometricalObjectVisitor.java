package hr.fer.zemris.java.hw16.jvdraw.graphicalobject.visitors;

import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.GeometricalObject;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components.Circle;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components.FilledCircle;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components.Line;

/**
 * Interface provides all methods {@link GeometricalObjectVisitor} must
 * implement
 * 
 * @author Mihael
 *
 */
public interface GeometricalObjectVisitor {
	/**
	 * Method is called when visitor wants to visit {@link GeometricalObject} line
	 * 
	 * @param line
	 *            - line
	 */
	public abstract void visit(Line line);

	/**
	 * Method is called when visitor wants to visit {@link GeometricalObject} circle
	 * 
	 * @param circle
	 *            - circle
	 */
	public abstract void visit(Circle circle);

	/**
	 * Method is called when visitor wants to visit {@link GeometricalObject} filled
	 * circle
	 * 
	 * @param filledCircle
	 *            - filled circle
	 */
	public abstract void visit(FilledCircle filledCircle);
}
