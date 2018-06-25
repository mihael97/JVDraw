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
		int[] array = Util.getPoint((JPanel) this.getComponents()[1]);
		this.xCenter = array[0];
		this.yCenter = array[1];

		this.radius = Double.parseDouble(((JTextField) (this.getComponents()[3])).getText());

		if (!Util.checkRadius(radius)) {
			throw new IllegalArgumentException("Invalid radius argument!");
		}

		JPanel RGBPanel = (JPanel) (this.getComponents()[5]);
		int[] colorArray = Util.getColorFromPanel(RGBPanel);
		this.red = colorArray[0];
		this.green = colorArray[1];
		this.blue = colorArray[2];

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
		this.add(new JLabel("Center:"));
		JPanel panel = new JPanel(new GridLayout(1, 2));
		this.add(panel);
		panel.add(Util.makeField(String.valueOf(circle.getCenter().x)));
		panel.add(Util.makeField(String.valueOf(circle.getCenter().y)));

		this.add(new JLabel("Radius:"));
		this.add(Util.makeField(String.valueOf(circle.getRadius())));

		this.add(new JLabel("Color"));
		JPanel colorPanel = new JPanel(new GridLayout(1, 3));
		this.add(colorPanel);
		colorPanel.add(Util.makeField(String.valueOf(circle.getColor().getRed())));
		colorPanel.add(Util.makeField(String.valueOf(circle.getColor().getGreen())));
		colorPanel.add(Util.makeField(String.valueOf(circle.getColor().getBlue())));

	}

}
