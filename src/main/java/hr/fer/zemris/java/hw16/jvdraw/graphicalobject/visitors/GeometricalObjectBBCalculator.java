package hr.fer.zemris.java.hw16.jvdraw.graphicalobject.visitors;

import java.awt.Point;
import java.awt.Rectangle;

import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components.Circle;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components.FilledCircle;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components.Line;

/**
 * Class implements {@link GeometricalObjectBBCalculator} and has implementation
 * as bounding box calculator<br>
 * We use bounding box calculator when we want to render our drawing into image
 * but without additional free space
 * 
 * @author Mihael
 *
 */
public class GeometricalObjectBBCalculator implements GeometricalObjectVisitor {
	/**
	 * X coordinate of bottom left corner
	 */
	private int xLeft;
	/**
	 * X coordinate of top right corner
	 */
	private int xRight;
	/**
	 * Y coordinate of bottom left corner
	 */
	private int yLeft;
	/**
	 * Y coordinate of top right corner
	 */
	private int yRight;

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.hw16.jvdraw.graphicalobject.visitors.GeometricalObjectVisitor#visit(hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components.Line)
	 */
	@Override
	public void visit(Line line) {
		Point start = line.getStartPoint();
		Point end = line.getEndPoint();

		if (start.x < xLeft) {
			xLeft = start.x;
		} else if (start.x > xRight) {
			xRight = start.x;
		}
		if (end.x < xLeft) {
			xLeft = end.x;
		} else if (end.x > xRight) {
			xRight = end.x;
		}

		if (start.y < yRight) {
			yRight = start.y;
		} else if (start.y > yLeft) {
			yLeft = start.y;
		}
		if (end.y < yRight) {
			yRight = end.y;
		} else if (end.y > yLeft) {
			yLeft = end.y;
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.hw16.jvdraw.graphicalobject.visitors.GeometricalObjectVisitor#visit(hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components.Circle)
	 */
	@Override
	public void visit(Circle circle) {
		Point center = circle.getCenter();
		int radius = (int) circle.getRadius();

		int leftY = center.y + radius;
		int rightY = center.x - radius;
		int leftX = center.x - radius;
		int rightX = center.x + radius;

		if (leftY > yLeft) {
			yLeft = leftY;
		}

		if (leftX < xLeft) {
			xLeft = leftX;
		}

		if (rightX > xRight) {
			xRight = rightX;
		}

		if (rightY < yRight) {
			yRight = rightY;
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.hw16.jvdraw.graphicalobject.visitors.GeometricalObjectVisitor#visit(hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components.FilledCircle)
	 */
	@Override
	public void visit(FilledCircle filledCircle) {
		visit((Circle) filledCircle);
	}

	/**
	 * Method returns rectangle(bounding box) of all stored objects
	 * 
	 * @return bounding box
	 */
	public Rectangle getBoundingBox() {
		return new Rectangle(xLeft, yRight, xRight, yLeft);
	}

}
