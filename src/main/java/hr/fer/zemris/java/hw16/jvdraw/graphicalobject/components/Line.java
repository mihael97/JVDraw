package hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;

import hr.fer.zemris.java.hw16.jvdraw.color.IColorProvider;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.GeometricalObject;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.editors.GeometricalObjectEditor;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.editors.LineEditor;
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
	 * Line's start point
	 */
	private Point startPoint;
	/**
	 * Line's end point
	 */
	private Point endPoint;
	/**
	 * Color provider
	 */
	private IColorProvider colorProvider;

	/**
	 * Constructor
	 * 
	 * @param colorProvider
	 *            - {@link IColorProvider} which contains current selected color for
	 *            drawing
	 */
	public Line(IColorProvider colorProvider) {
		this.colorProvider = colorProvider;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.hw16.jvdraw.graphicalobject.interfaces.Tool#mousePressed(
	 *      java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {

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
		if (startPoint == null) {
			startPoint = e.getLocationOnScreen();
			endPoint = e.getLocationOnScreen();
		} else {
			endPoint = e.getLocationOnScreen();
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.hw16.jvdraw.graphicalobject.interfaces.Tool#mouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		endPoint = e.getLocationOnScreen();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.hw16.jvdraw.graphicalobject.interfaces.Tool#mouseDragged(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseDragged(MouseEvent e) {

	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.hw16.jvdraw.graphicalobject.interfaces.Tool#paint(java.awt.Graphics2D)
	 */
	@Override
	public void paint(Graphics2D g2d) {
		if (startPoint != null) {
			g2d.setColor(colorProvider.getCurrentColor());
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
	}

}
