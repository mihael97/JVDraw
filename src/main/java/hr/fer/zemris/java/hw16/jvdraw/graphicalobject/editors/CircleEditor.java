package hr.fer.zemris.java.hw16.jvdraw.graphicalobject.editors;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.Objects;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components.Circle;

/**
 * Class represents editor for circle parameters
 * 
 * @author Mihael
 *
 */
public class CircleEditor extends GeometricalObjectEditor {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Reference to editor owner
	 */
	private Circle circle;
	/**
	 * X coordinate of center
	 */
	Integer xCenter = null;
	/**
	 * Y coordinate of center
	 */
	Integer yCenter = null;
	/**
	 * Circle radius
	 */
	Double radius = null;
	/**
	 * Red component of RGB
	 */
	Integer red = null;
	/**
	 * Green component of RGB
	 */
	Integer green = null;
	/**
	 * Blue component of RGB
	 */
	Integer blue = null;

	/**
	 * Constructor creates new parameter editor for circle
	 * 
	 * @param circle
	 *            - circle we want to edit
	 */
	public CircleEditor(Circle circle) {
		this.circle = Objects.requireNonNull(circle, "Reference to circle cannot be null!");
		createEditor();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.hw16.jvdraw.graphicalobject.editors.GeometricalObjectEditor#checkEditing()
	 */
	@Override
	public void checkEditing() {
		int[] array = Util.getPoint((JPanel) this.getComponentAt(1, 2));
		xCenter = array[0];
		yCenter = array[1];

		radius = Double.parseDouble(((JTextField) (this.getComponentAt(2, 2))).getText());

		if (!Util.checkRadius(radius)) {
			throw new IllegalArgumentException("Invalid radius argument!");
		}

		JPanel RGBPanel = (JPanel) (this.getComponentAt(3, 2));
		red = Integer.parseInt(((JTextField) (RGBPanel.getComponentAt(1, 1))).getText());
		green = Integer.parseInt(((JTextField) (RGBPanel.getComponentAt(2, 1))).getText());
		blue = Integer.parseInt(((JTextField) (RGBPanel.getComponentAt(3, 1))).getText());

		if (!Util.checkRGB(red, green, blue)) {
			throw new IllegalArgumentException("invalid color!");
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.hw16.jvdraw.graphicalobject.editors.GeometricalObjectEditor#acceptEditing()
	 */
	@Override
	public void acceptEditing() {
		circle.setCenter(new Point(xCenter, yCenter));
		circle.setRadius(radius);
		circle.setColor(new Color(red, green, blue));
	}

	/**
	 * Method initializes frame components
	 */
	public void createEditor() {
		this.setLayout(new GridLayout(3, 2));
		Util.addPoint(this, "Center: ");
		Util.addRadius(this);
		this.add(new JLabel("Color: "));
		Util.addPanels(this, true);
	}

}
