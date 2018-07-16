package hr.fer.zemris.java.hw16.jvdraw.graphicalobject.tool;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import hr.fer.zemris.java.hw16.jvdraw.color.IColorProvider;
import hr.fer.zemris.java.hw16.jvdraw.drawing.interfaces.DrawingModel;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components.FilledCircle;

/**
 * Class extends {@link CircleTool} and represents tool for drawing
 * {@link FilledCircle}s
 * 
 * @author Mihael
 *
 */
public class FilledCircleTool extends CircleTool {
	/**
	 * {@link IColorProvider} that contains current color for filling circle
	 */
	private IColorProvider fillColor;

	/**
	 * Constructor creates new drawing tool for {@link FilledCircle}\
	 * 
	 * @param drawingColor
	 *            - drawing color provider
	 * @param fillColor
	 *            - fill color provider
	 * @param model
	 *            - {@link DrawingModel}
	 */
	public FilledCircleTool(IColorProvider drawingColor, IColorProvider fillColor, DrawingModel model) {
		super(drawingColor, model);
		this.fillColor = fillColor;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.hw16.jvdraw.graphicalobject.tool.CircleTool#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if (center == null) {
			center = e.getPoint();
			this.radius = 0;
		} else {
			this.radius = super.calculateRadius(e.getPoint());
			FilledCircle circle = new FilledCircle(center.x, center.y, radius, drawingColor.getCurrentColor(),
					fillColor.getCurrentColor());
			model.add(circle);
			super.reset();
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.hw16.jvdraw.graphicalobject.tool.CircleTool#paint(java.awt.Graphics2D)
	 */
	@Override
	public void paint(Graphics2D g2d) {
		if (center != null) {
			g2d.setColor(fillColor.getCurrentColor());
			g2d.fillOval((int) (center.x - radius), (int) (center.y - radius), (int) (2 * radius), (int) (2 * radius));

			super.paint(g2d);
		}
	}
}
