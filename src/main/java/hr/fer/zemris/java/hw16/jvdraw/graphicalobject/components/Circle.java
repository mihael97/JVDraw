package hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;

import hr.fer.zemris.java.hw16.jvdraw.color.IColorProvider;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.GeometricalObject;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.editors.CircleEditor;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.editors.GeometricalObjectEditor;
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
	 * Center circle point
	 */
	private Point center;
	/**
	 * Circle radius
	 */
	private double radius;
	/**
	 * Provider contains color for drawing
	 */
	private IColorProvider drawColorProvider;

	/**
	 * Constructor
	 * 
	 * @param drawColorProvider
	 *            - provider that contains color type for drawing
	 */
	public Circle(IColorProvider drawColorProvider) {
		super();
		this.drawColorProvider = drawColorProvider;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.hw16.jvdraw.graphicalobject.interfaces.Tool#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {

	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.hw16.jvdraw.graphicalobject.interfaces.Tool#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
	}

	/**
	 * Method calculates distance between center point and current focused point<br>
	 * 
	 * @param point
	 *            - current focused point
	 * @return distance
	 */
	private double calculateRadius(Point point) {
		return sqrt(pow(center.x - point.x, 2) + pow(center.y - point.y, 2));
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.hw16.jvdraw.graphicalobject.interfaces.Tool#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if (center == null) {
			center = e.getLocationOnScreen();
		} else {
			this.radius = calculateRadius(e.getLocationOnScreen());
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.hw16.jvdraw.graphicalobject.interfaces.Tool#mouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		this.radius = calculateRadius(e.getLocationOnScreen());
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
		double halfRadius = (double) (radius / 2);
		g2d.setColor(drawColorProvider.getCurrentColor());
		g2d.fillOval((int) (center.x - halfRadius), (int) (center.y - halfRadius), (int) radius, (int) radius);
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

	@Override
	public GeometricalObjectEditor createGeometricalObjectEditor() {
		CircleEditor editor = new CircleEditor(this);
		return editor.createEditor();
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
	}

}
