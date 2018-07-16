package hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components;

import java.awt.Color;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.editors.FilledCircleEditor;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.editors.GeometricalObjectEditor;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.visitors.GeometricalObjectVisitor;

/**
 * Class represents filled circle<br>
 * Difference between circle and filled circle is that filled circle is also
 * painted
 * 
 * @author Mihael
 *
 */
public class FilledCircle extends Circle{
	/**
	 * Color for fill circle
	 */
	private Color fillColor;

	/**
	 * Constructor creates new {@link FilledCircle}
	 * 
	 * @param drawColor
	 *            - color for drawing
	 * @param fillColor
	 *            - color for painting
	 * 
	 */
	public FilledCircle(Color drawColor, Color fillColor) {
		super(drawColor);
		this.fillColor = fillColor;
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
