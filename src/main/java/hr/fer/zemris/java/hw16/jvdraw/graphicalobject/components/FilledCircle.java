package hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import hr.fer.zemris.java.hw16.jvdraw.color.ColorChangeListener;
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
	 * Color for fill circle
	 */
	private Color fillColor;

	/**
	 * Constructor creates new {@link FilledCircle}
	 * 
	 * @param fgColorArea
	 *            - color for drawing
	 * @param bgColorArea
	 *            - color for painting
	 * 
	 */
	public FilledCircle(IColorProvider fgColorArea, IColorProvider bgColorArea) {
		super(fgColorArea);
		this.fillColor = bgColorArea.getCurrentColor();
		bgColorArea.addColorChangeListener(new ColorChangeListener() {

			@Override
			public void newColorSelected(IColorProvider source, Color oldColor, Color newColor) {
				FilledCircle.this.fillColor = newColor;
			}
		});
	}

	/**
	 * Constructor creates new final filled circle
	 * 
	 * @param x
	 *            - x coordinate of center
	 * @param y
	 *            - y coordinate of center
	 * @param rad
	 *            - radius
	 * @param col
	 *            - color for drawing
	 * @param colFill
	 *            - color for filling
	 */
	public FilledCircle(int x, int y, double rad, Color col, Color colFill) {
		super(x, y, rad, col);
		this.fillColor = colFill;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.hw16.jvdraw.graphicalobject.interfaces.Tool#paint(java.awt.Graphics2D)
	 */
	@Override
	public void paint(Graphics2D g2d) {
		if (getCenter() != null) {
			g2d.setColor(fillColor);
			g2d.fillOval((int) (getCenter().x - getRadius()), (int) (getCenter().y - getRadius()),
					(int) (2 * getRadius()), (int) (2 * getRadius()));

			super.paint(g2d);
		}
	}



	/**
	 * Method calculates distance between center point and current focused point<br>
	 * 
	 * @param point
	 *            - current focused point
	 * @return distance
	 */
	protected double calculateRadius(Point point) {
		return sqrt(pow(getCenter().x - point.x, 2) + pow(getCenter().y - point.y, 2));
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

	/**
	 * Method returns fill color
	 * 
	 * @return fill color
	 */
	public Color getFillColor() {
		return fillColor;
	}

	/**
	 * Method sets fill color
	 * 
	 * @param fillColor
	 *            - fill color
	 */
	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
		super.inform();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components.Circle#toString()
	 */
	@Override
	public String toString() {
		return "Filled Circle (" + getCenter().x + "," + getCenter().y + ")," + (int) getRadius() + String
				.format(",#%02x%02x%02x", fillColor.getRed(), fillColor.getGreen(), fillColor.getBlue()).toUpperCase();
	}
}
