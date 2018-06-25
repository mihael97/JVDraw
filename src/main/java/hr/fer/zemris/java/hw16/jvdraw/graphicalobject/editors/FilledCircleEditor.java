package hr.fer.zemris.java.hw16.jvdraw.graphicalobject.editors;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.Objects;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components.FilledCircle;

/**
 * Class represents editor for {@link FilledCircle} parameters
 * 
 * @author Mihael
 *
 */
public class FilledCircleEditor extends GeometricalObjectEditor {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Reference to editor's owner
	 */
	private FilledCircle circle;
	/**
	 * X coordinate of center
	 */
	private Integer xCenter;
	/**
	 * Y coordinate of center
	 */
	private Integer yCenter;
	/**
	 * Radius
	 */
	private Double radius;

	/**
	 * Red component of draw color
	 */
	private Integer redDraw;
	/**
	 * Green component of fill color
	 */
	private Integer greenDraw;
	/**
	 * Blue component of fill color
	 */
	private Integer blueDraw;

	/**
	 * Red component of fill color
	 */
	private Integer redFill;
	/**
	 * Green component of fill color
	 */
	private Integer greenFill;
	/**
	 * Blue component of fill color
	 */
	private Integer blueFill;

	/**
	 * Constructor for new {@link FilledCircle} editor
	 * 
	 * @param circle
	 */
	public FilledCircleEditor(FilledCircle circle) {
		this.circle = Objects.requireNonNull(circle, "Circle reference cannot be null!");
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
		this.redDraw = colorArray[0];
		this.greenDraw = colorArray[1];
		this.blueDraw = colorArray[2];

		if (!Util.checkRGB(redDraw, greenDraw, blueDraw)) {
			throw new IllegalArgumentException("invalid color!");
		}

		RGBPanel = (JPanel) (this.getComponents()[7]);
		colorArray = Util.getColorFromPanel(RGBPanel);
		this.redFill = colorArray[0];
		this.greenFill = colorArray[1];
		this.blueFill = colorArray[2];

		if (!Util.checkRGB(redFill, greenFill, blueFill)) {
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
		circle.setColor(new Color(redDraw, greenDraw, blueDraw));
		circle.setFillColor(new Color(redFill, greenFill, blueFill));
	}

	/**
	 * Method initializes form for {@link FilledCircle} parameters editing
	 */
	public void createEditor() {
		this.setLayout(new GridLayout(4, 2));
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

		this.add(new JLabel("Color fill"));
		JPanel colorPanelFill = new JPanel(new GridLayout(1, 3));
		this.add(colorPanelFill);
		colorPanelFill.add(Util.makeField(String.valueOf(circle.getFillColor().getRed())));
		colorPanelFill.add(Util.makeField(String.valueOf(circle.getFillColor().getGreen())));
		colorPanelFill.add(Util.makeField(String.valueOf(circle.getFillColor().getBlue())));
	}

}
