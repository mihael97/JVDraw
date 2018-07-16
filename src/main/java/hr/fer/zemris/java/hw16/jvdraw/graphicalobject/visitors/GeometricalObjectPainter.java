package hr.fer.zemris.java.hw16.jvdraw.graphicalobject.visitors;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

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
		Point startPoint=line.getStartPoint();
		Point endPoint=line.getEndPoint();
		graphics.setColor(line.getColor());
		graphics.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.hw16.jvdraw.graphicalobject.visitors.GeometricalObjectVisitor#visit(hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components.Circle)
	 */
	@Override
	public void visit(Circle circle) {
		double radius=circle.getRadius();
		Point center=circle.getCenter();
		graphics.setColor(circle.getColor());
		graphics.drawOval((int) (center.x - radius), (int) (center.y - radius), (int) (2 * radius), (int) (2 * radius));
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.hw16.jvdraw.graphicalobject.visitors.GeometricalObjectVisitor#visit(hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components.FilledCircle)
	 */
	@Override
	public void visit(FilledCircle filledCircle) {
		Point center=filledCircle.getCenter();
		double radius=filledCircle.getRadius();
		graphics.setColor(filledCircle.getFillColor());
		graphics.fillOval((int) (center.x - radius), (int) (center.y - radius), (int) (2 * radius), (int) (2 * radius));
		graphics.setColor((filledCircle.getColor()));
		graphics.drawOval((int) (center.x - radius), (int) (center.y - radius), (int) (2 * radius), (int) (2 * radius));
	}

}
