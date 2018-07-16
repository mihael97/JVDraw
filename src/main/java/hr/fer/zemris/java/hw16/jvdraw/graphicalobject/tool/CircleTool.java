package hr.fer.zemris.java.hw16.jvdraw.graphicalobject.tool;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;

import hr.fer.zemris.java.hw16.jvdraw.color.IColorProvider;
import hr.fer.zemris.java.hw16.jvdraw.drawing.interfaces.DrawingModel;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components.Circle;

/**
 * Class implements {@link Tool} and represents tool for drawing circles
 * 
 * @author Mihael
 *
 */
public class CircleTool implements Tool {

	/**
	 * Circle's center
	 */
	protected Point center;
	/**
	 * Reference to {@link IColorProvider} where current drawing color is stored
	 */
	protected IColorProvider drawingColor;
	/**
	 * Circle's radius
	 */
	protected double radius;
	/**
	 * Reference to {@link DrawingModel}
	 */
	protected DrawingModel model;

	/**
	 * Constructor creates new tool for {@link Circle} drawing
	 * 
	 * @param drawingColor
	 *            - reference to provider for drawing color
	 * @param model
	 *            - drawing model
	 */
	public CircleTool(IColorProvider drawingColor, DrawingModel model) {
		this.drawingColor = drawingColor;
		this.model = model;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.hw16.jvdraw.graphicalobject.tool.Tool#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if (center == null) {
			center = e.getPoint();
			this.radius = 0;
		} else {
			this.radius = calculateRadius(e.getPoint());
			Circle circle = new Circle(center.x, center.y, radius, drawingColor.getCurrentColor());
			model.add(circle);
			reset();
		}
	}

	/**
	 * Method resets {@link CircleTool#center} and {@link CircleTool#radius} to
	 * their default values<br>
	 * Default value for center is <code>null</code> (doesn't exist) and for radius
	 * is <code>0</code>
	 */
	protected void reset() {
		center = null;
		radius = 0;
	}

	/**
	 * Method calculates distance between center and current point on screen
	 * 
	 * @param point
	 *            - current point on screen
	 * @return double as distance between two points
	 */
	protected double calculateRadius(Point point) {
		return center.distance(point);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.hw16.jvdraw.graphicalobject.tool.Tool#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		mouseClicked(e);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.hw16.jvdraw.graphicalobject.tool.Tool#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.hw16.jvdraw.graphicalobject.tool.Tool#mouseMoved(java.awt.event.MouseEvent)
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
	 * @see hr.fer.zemris.java.hw16.jvdraw.graphicalobject.tool.Tool#mouseDragged(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		mouseMoved(e);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.hw16.jvdraw.graphicalobject.tool.Tool#paint(java.awt.Graphics2D)
	 */
	@Override
	public void paint(Graphics2D g2d) {
		if (center != null) {
			g2d.setColor(drawingColor.getCurrentColor());
			g2d.drawOval((int) (center.x - radius), (int) (center.y - radius), (int) (2 * radius), (int) (2 * radius));
		}
	}
}
