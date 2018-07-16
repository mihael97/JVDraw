package hr.fer.zemris.java.hw16.jvdraw.graphicalobject.tool;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;

import hr.fer.zemris.java.hw16.jvdraw.color.IColorProvider;
import hr.fer.zemris.java.hw16.jvdraw.drawing.interfaces.DrawingModel;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components.Line;

/**
 * Class implements {@link Tool} and represents tool for line drawing
 * @author Mihael
 *
 */
public class LineTool implements Tool {

	/**
	 * Starting line point
	 */
	private Point startPoint;
	/**
	 * Ending line point
	 */
	private Point endPoint;
	/**
	 * Reference to drawing color provider
	 */
	private IColorProvider drawingColor;
	/**
	 * Reference to {@link DrawingModel}
	 */
	private DrawingModel model;

	/**
	 * Constructor creates new tool for {@link Line} drawing
	 * @param drawingColor - drawing color provider
	 * @param model - {@link DrawingModel}
	 */
	public LineTool(IColorProvider drawingColor, DrawingModel model) {
		this.drawingColor = drawingColor;
		this.model = model;
	}

	/** (non-Javadoc)
	 * @see hr.fer.zemris.java.hw16.jvdraw.graphicalobject.tool.Tool#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if (startPoint == null) {
			startPoint = e.getPoint();
			endPoint = e.getPoint();
		} else {
			endPoint = e.getPoint();
			Line line = new Line(startPoint.x,startPoint.y,endPoint.x,endPoint.y,drawingColor.getCurrentColor());
			model.add(line);
			reset();
		}
	}

	/**
	 * Method resets start and end point to their default values<br>
	 * Default value for both points is <code>null</code>
	 */
	private void reset() {
		startPoint = endPoint = null;
	}

	/** (non-Javadoc)
	 * @see hr.fer.zemris.java.hw16.jvdraw.graphicalobject.tool.Tool#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {	
	}

	/** (non-Javadoc)
	 * @see hr.fer.zemris.java.hw16.jvdraw.graphicalobject.tool.Tool#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	/** (non-Javadoc)
	 * @see hr.fer.zemris.java.hw16.jvdraw.graphicalobject.tool.Tool#mouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		endPoint = e.getPoint();
	}

	/** (non-Javadoc)
	 * @see hr.fer.zemris.java.hw16.jvdraw.graphicalobject.tool.Tool#mouseDragged(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		mouseMoved(e);
	}

	/** (non-Javadoc)
	 * @see hr.fer.zemris.java.hw16.jvdraw.graphicalobject.tool.Tool#paint(java.awt.Graphics2D)
	 */
	@Override
	public void paint(Graphics2D g2d) {
		if (startPoint != null) {
			g2d.setColor(drawingColor.getCurrentColor());
			g2d.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
		}
	}

}
