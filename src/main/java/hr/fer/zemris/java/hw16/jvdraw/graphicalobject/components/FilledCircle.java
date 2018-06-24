package hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components;

import java.awt.Graphics2D;
import java.awt.Point;

import hr.fer.zemris.java.hw16.jvdraw.color.IColorProvider;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.editors.FilledCircleEditor;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.editors.GeometricalObjectEditor;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.interfaces.Tool;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.visitors.GeometricalObjectVisitor;

/**
 * Class represents filled circle<br>
 * Difference between circle and filled circle is that filled circle is also
 * painted
 * 
 * @author Mihael
 *
 */
public class FilledCircle extends Circle implements Tool {
	/**
	 * Circle center
	 */
	private Point center;
	/**
	 * Circle radius
	 */
	private double radius;

	/**
	 * Provider contains selected color for painting
	 */
	private IColorProvider fillColorProvider;

	/**
	 * Constructor creates new {@link FilledCircle}
	 * 
	 * @param fillColorProvider
	 *            - provider which contains color for circle filling
	 * @param drawColorProvider
	 *            - provider which contains color for circle drawing
	 */
	public FilledCircle(IColorProvider fillColorProvider, IColorProvider drawColorProvider) {
		super(drawColorProvider);
		this.fillColorProvider = fillColorProvider;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.hw16.jvdraw.graphicalobject.interfaces.Tool#paint(java.awt.Graphics2D)
	 */
	@Override
	public void paint(Graphics2D g2d) {
		if (center != null) {
			double halfRadius = (double) (radius / 2);
			g2d.setColor(fillColorProvider.getCurrentColor());
			g2d.fillOval((int) (center.x - halfRadius), (int) (center.y - halfRadius), (int) radius, (int) radius);

			super.paint(g2d);
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
	 * @see hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components.Circle#createGeometricalObjectEditor()
	 */
	@Override
	public GeometricalObjectEditor createGeometricalObjectEditor() {
		return new FilledCircleEditor(this);
	}

}
