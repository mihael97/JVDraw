package hr.fer.zemris.java.hw16.jvdraw.graphicalobject.visitors;

import java.awt.Graphics;
import java.awt.Graphics2D;

import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.GeometricalObject;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components.Circle;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components.FilledCircle;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components.Line;

/**
 * Class implements {@link GeometricalObjectVisitor} and represents visitor
 * which goes trough every stored object and draws it
 * 
 * @author Mihael
 *
 */
public class GeometricalObjectPainter implements GeometricalObjectVisitor {

	/**
	 * {@link Graphics2D} that contains methods for drawing
	 */
	private Graphics2D graphics;

	/**
	 * Constructor creates {@link GeometricalObjectPainter}<br>
	 * For argument accepts {@link Graphics2D} which will be used for
	 * {@link GeometricalObject} drawing
	 * 
	 * @param graphics
	 *            - {@link Graphics2D}
	 */
	public GeometricalObjectPainter(Graphics graphics) {
		super();
		this.graphics = (Graphics2D) graphics;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.hw16.jvdraw.graphicalobject.visitors.GeometricalObjectVisitor#visit(hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components.Line)
	 */
	@Override
	public void visit(Line line) {
		line.paint(graphics);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.hw16.jvdraw.graphicalobject.visitors.GeometricalObjectVisitor#visit(hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components.Circle)
	 */
	@Override
	public void visit(Circle circle) {
		circle.paint(graphics);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.hw16.jvdraw.graphicalobject.visitors.GeometricalObjectVisitor#visit(hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components.FilledCircle)
	 */
	@Override
	public void visit(FilledCircle filledCircle) {
		filledCircle.paint(graphics);
	}

}
