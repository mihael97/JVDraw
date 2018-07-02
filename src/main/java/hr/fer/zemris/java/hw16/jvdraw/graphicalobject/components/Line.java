package hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.GeometricalObject;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.editors.GeometricalObjectEditor;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.editors.LineEditor;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.interfaces.GeometricalObjectListener;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.interfaces.Tool;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.visitors.GeometricalObjectVisitor;

/**
 * Class represents line for drawing
 * 
 * @author Mihael
 *
 */
public class Line extends GeometricalObject implements Tool {

	/**
	 * List of listeners
	 */
	private List<GeometricalObjectListener> listeners = new ArrayList<>();
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
	 * @see hr.fer.zemris.java.hw16.jvdraw.graphicalobject.interfaces.Tool#mousePressed(
	 *      java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if (startPoint == null) {
			startPoint = e.getPoint();
			endPoint = e.getPoint();
		} else {
			endPoint = e.getPoint();
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.hw16.jvdraw.graphicalobject.interfaces.Tool#mouseReleased(
	 *      java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {

	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.hw16.jvdraw.graphicalobject.interfaces.Tool#mouseClicked(
	 *      java.awt.event.MouseEvent)
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
		endPoint = e.getPoint();
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
		if (startPoint != null) {
			g2d.setColor(color);
			g2d.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
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
	private void inform() {
		listeners.forEach(e -> e.geometricalObjectChanged(this));
	}

}
