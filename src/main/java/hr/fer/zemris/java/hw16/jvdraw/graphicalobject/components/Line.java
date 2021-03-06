package hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components;

import java.awt.Color;
import java.awt.Point;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.GeometricalObject;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.editors.GeometricalObjectEditor;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.editors.LineEditor;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.visitors.GeometricalObjectVisitor;

/**
 * Class represents line for drawing
 * 
 * @author Mihael
 *
 */
public class Line extends GeometricalObject{

	/**
	 * Line's start point
	 */
	private Point startPoint;
	/**
	 * Line's end point
	 */
	private Point endPoint;

	/**
	 * Color for drawing
	 */
	private Color color;

	/**
	 * Constructor
	 * 
	 * @param yEnd
	 *            - y coordinate of end point
	 * @param xEnd
	 *            - x coordinate of end point
	 * @param yStart
	 *            - y coordinate of start point
	 * @param xStart
	 *            - x coordinate of start point
	 * @param col
	 *            - color
	 */
	public Line(int xStart, int yStart, int xEnd, int yEnd, Color col) {
		this.startPoint = new Point(xStart, yStart);
		this.endPoint = new Point(xEnd, yEnd);
		this.color = col;
	}

	/**
	 * Default constructor
	 * 
	 * @param color
	 *            - {@link Color} for drawing
	 */
	public Line(Color color) {
		this.color = color;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.hw16.jvdraw.graphicalobject.GeometricalObject#accept(hr.fer.zemris.java.hw16.jvdraw.graphicalobject.visitors.GeometricalObjectVisitor)
	 */
	@Override
	public void accept(GeometricalObjectVisitor v) {
		v.visit(this);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.hw16.jvdraw.graphicalobject.GeometricalObject#createGeometricalObjectEditor()
	 */
	@Override
	public GeometricalObjectEditor createGeometricalObjectEditor() {
		return new LineEditor(this);
	}

	/**
	 * Method returns line's starting point
	 * 
	 * @return starting point
	 */
	public Point getStartPoint() {
		return startPoint;
	}

	/**
	 * Method sets line's starting point
	 * 
	 * @param startPoint
	 *            - new starting point
	 */
	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
		inform();
	}

	/**
	 * Method returns line's ending point
	 * 
	 * @return starting point
	 */
	public Point getEndPoint() {
		return endPoint;
	}

	/**
	 * Method sets line's ending point
	 * 
	 * @param endPoint
	 *            - new starting point
	 */
	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
		inform();
	}

	/**
	 * Method returns color for drawing
	 * 
	 * @return drawing color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Method sets drawing color
	 * 
	 * @param color
	 *            - new drawing color
	 */
	public void setColor(Color color) {
		this.color = color;
		inform();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Line (" + startPoint.x + "," + startPoint.y + ")-(" + endPoint.x + "," + endPoint.y + ")";
	}
}
