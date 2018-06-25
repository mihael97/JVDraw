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
	// /**
	// * X coordinate of bottom left corner
	// */
	// private int xLeft;
	// /**
	// * X coordinate of top right corner
	// */
	// private int xRight;
	// /**
	// * Y coordinate of bottom left corner
	// */
	// private int yLeft;
	// /**
	// * Y coordinate of top right corner
	// */
	// private int yRight;

	/**
	 * The x coordinate of the top left corner of the rectangle.
	 */
	private int xMin = Integer.MAX_VALUE;

	/**
	 * The y coordinate of the top left corner of the rectangle.
	 */
	private int yMin = Integer.MAX_VALUE;

	/**
	 * The x coordinate of the bottom right corner of the rectangle.
	 */
	private int xMax = Integer.MIN_VALUE;

	/**
	 * The y coordinate of the bottom right corner of the rectangle.
	 */
	private int yMax = Integer.MIN_VALUE;

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.hw16.jvdraw.graphicalobject.visitors.GeometricalObjectVisitor#visit(hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components.Line)
	 */
	@Override
	public void visit(Line line) {
		Point start = line.getStartPoint();
		Point end = line.getEndPoint();
		//
		// if (start.x < xLeft) {
		// xLeft = start.x;
		// } else if (start.x > xRight) {
		// xRight = start.x;
		// }
		// if (end.x < xLeft) {
		// xLeft = end.x;
		// } else if (end.x > xRight) {
		// xRight = end.x;
		// }
		//
		// if (start.y < yRight) {
		// yRight = start.y;
		// } else if (start.y > yLeft) {
		// yLeft = start.y;
		// }
		// if (end.y < yRight) {
		// yRight = end.y;
		// } else if (end.y > yLeft) {
		// yLeft = end.y;
		// }

		if (start.x < xMin)
			xMin = start.x;
		if (end.x < xMin)
			xMin = end.x;

		if (start.y < yMin)
			yMin = start.y;
		if (end.y < yMin)
			yMin = end.y;

		if (start.x > xMax)
			xMax = start.x;
		if (end.x > xMax)
			xMax = end.x;

		if (start.y > yMax)
			yMax = start.y;
		if (end.y > yMax)
			yMin = end.y;
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
//
//		int leftY = center.y + radius;
//		int rightY = center.x - radius;
//		int leftX = center.x - radius;
//		int rightX = center.x + radius;
//
//		if (leftY > yLeft) {
//			yLeft = leftY;
//		}
//
//		if (leftX < xLeft) {
//			xLeft = leftX;
//		}
//
//		if (rightX > xRight) {
//			xRight = rightX;
//		}
//
//		if (rightY < yRight) {
//			yRight = rightY;
//		}
		int x1 = center.x - (int) radius;
		int y1 = center.y - (int) radius;
		int x2 = center.x + (int) radius;
		int y2 = center.y + (int) radius;

		if (x1 < xMin) xMin = x1;
		if (y1 < yMin) yMin = y1;
		if (x2 > xMax) xMax = x2;
		if (y2 > yMax) yMax = y2;
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
		//return new Rectangle(xLeft, yRight, xRight, yLeft);
		return new Rectangle(xMin, yMin, xMax, yMax);

	}

}
