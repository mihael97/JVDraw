package hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components;

import java.awt.Color;
import java.awt.Point;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.GeometricalObject;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.editors.CircleEditor;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.editors.GeometricalObjectEditor;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.visitors.GeometricalObjectVisitor;

/**
 * Class represents filled circle<br>
 * 
 * 
 * @author Mihael
 *
 */
public class Circle extends GeometricalObject {

	/**
	 * Center circle point
	 */
	private Point center;
	/**
	 * Circle radius
	 */
	private double radius;

	/**
	 * Color for drawing
	 */
	private Color color;

	/**
	 * Constructor
	 * 
	 * @param color
	 *            - color type for drawing
	 */
	public Circle(Color color) {
		this.color = color;
	}

	/**
	 * Constructor accepts new {@link Circle}
	 * 
	 * @param xCenter
	 *            - x coordinate of center
	 * @param yCenter
	 *            - y coordinate of center
	 * @param radius
	 *            - radius
	 * @param color
	 *            - color
	 */
	public Circle(int xCenter, int yCenter, double radius, Color color) {
		this.center = new Point(xCenter, yCenter);
		this.radius = radius;
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
		return new CircleEditor(this);
	}

	/**
	 * Method returns circle center point
	 * 
	 * @return radius
	 */
	public Point getCenter() {
		return center;
	}

	/**
	 * Method sets circle center
	 * 
	 * @param center
	 *            - center
	 */
	public void setCenter(Point center) {
		this.center = center;
		inform();
	}

	/**
	 * Method returns circle radius
	 * 
	 * @return radius - current radius
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * Method sets circle radius
	 * 
	 * @param radius
	 *            - new radius
	 */
	public void setRadius(double radius) {
		this.radius = radius;
		inform();
	}

	/**
	 * Method returns current color for drawing
	 * 
	 * @return color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Method sets current drawing color
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
		return "Circle (" + center.x + "," + center.y + ")," + (int) radius;
	}
}
