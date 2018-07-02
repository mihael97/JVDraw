package hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.GeometricalObject;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.editors.CircleEditor;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.editors.GeometricalObjectEditor;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.interfaces.GeometricalObjectListener;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.interfaces.Tool;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.visitors.GeometricalObjectVisitor;

/**
 * Class represents filled circle<br>
 * 
 * 
 * @author Mihael
 *
 */
public class Circle extends GeometricalObject implements Tool {

	/**
	 * List of listeners
	 */
	private List<GeometricalObjectListener> listeners = new ArrayList<>();
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
	 * @see hr.fer.zemris.java.hw16.jvdraw.graphicalobject.interfaces.Tool#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if (center == null) {
			center = e.getPoint();
			this.radius = 0;
		} else {
			this.radius = calculateRadius(e.getPoint());
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.hw16.jvdraw.graphicalobject.interfaces.Tool#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		mouseClicked(e);
	}

	/**
	 * Method calculates distance between center point and current focused point<br>
	 * 
	 * @param point
	 *            - current focused point
	 * @return distance
	 */
	protected double calculateRadius(Point point) {
		return sqrt(pow(center.x - point.x, 2) + pow(center.y - point.y, 2));
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.hw16.jvdraw.graphicalobject.interfaces.Tool#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.hw16.jvdraw.graphicalobject.interfaces.Tool#mouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		if (center != null) {
			this.radius = calculateRadius(e.getPoint());
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.hw16.jvdraw.graphicalobject.interfaces.Tool#mouseDragged(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		mouseMoved(e);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.hw16.jvdraw.graphicalobject.interfaces.Tool#paint(java.awt.Graphics2D)
	 */
	@Override
	public void paint(Graphics2D g2d) {
		if (center != null) {
			g2d.setColor(color);
			g2d.drawOval((int) (center.x - radius), (int) (center.y - radius), (int) (2 * radius), (int) (2 * radius));
		}
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

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.hw16.jvdraw.graphicalobject.GeometricalObject#addGeometricalObjectListener(hr.fer.zemris.java.hw16.jvdraw.graphicalobject.interfaces.GeometricalObjectListener)
	 */
	@Override
	public void addGeometricalObjectListener(GeometricalObjectListener l) {
		listeners.add(Objects.requireNonNull(l));
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.hw16.jvdraw.graphicalobject.GeometricalObject#removeGeometricalObjectListener(hr.fer.zemris.java.hw16.jvdraw.graphicalobject.interfaces.GeometricalObjectListener)
	 */
	@Override
	public void removeGeometricalObjectListener(GeometricalObjectListener l) {
		listeners.remove(l);
	}

	/**
	 * Method informs every listener that change is made on object
	 */
	protected void inform() {
		listeners.forEach(e -> e.geometricalObjectChanged(this));
	}

}
