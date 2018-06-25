package hr.fer.zemris.java.hw16.jvdraw.graphicalobject.editors;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.Objects;

import javax.swing.JLabel;
import javax.swing.JPanel;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components.Line;

/**
 * Class represents editor for line parameters
 * 
 * @author Mihael
 *
 */
public class LineEditor extends GeometricalObjectEditor {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Reference to editor owner
	 */
	private Line line;
	/**
	 * X of line start point
	 */
	private Integer xStart;
	/**
	 * Y of line start point
	 */
	private Integer yStart;
	/**
	 * X of line end point
	 */
	private Integer xEnd;
	/**
	 * Y of line end point
	 */
	private Integer yEnd;
	/**
	 * Red color's component
	 */
	private Integer red;
	/**
	 * Green color's component
	 */
	private Integer green;
	/**
	 * Blue component of color
	 */
	private Integer blue;

	/**
	 * Constructor for line editor
	 * 
	 * @param line
	 *            - line
	 */
	public LineEditor(Line line) {
		this.line = Objects.requireNonNull(line, "Reference to line cannot be null!");
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

		xStart = array[0];
		yStart = array[1];

		array = Util.getPoint((JPanel) this.getComponents()[3]);
		xEnd = array[0];
		yEnd = array[1];

		JPanel RGBPanel = (JPanel) (this.getComponents()[5]);
		array = Util.getColorFromPanel(RGBPanel);
		this.red = array[0];
		this.green = array[1];
		this.blue = array[2];

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
		line.setStartPoint(new Point(xStart, yStart));
		line.setEndPoint(new Point(xEnd, yEnd));
		line.setColor(new Color(red, green, blue));
	}

	/**
	 * Method initializes form for editing
	 */
	public void createEditor() {
		this.setLayout(new GridLayout(3, 3));
		this.add(new JLabel("Start:"));
		JPanel panel = new JPanel(new GridLayout(1, 2));
		this.add(panel);
		panel.add(Util.makeField(String.valueOf(line.getStartPoint().x)));
		panel.add(Util.makeField(String.valueOf(line.getStartPoint().y)));

		this.add(new JLabel("End:"));
		JPanel panel2 = new JPanel(new GridLayout(1, 2));
		this.add(panel2);
		panel2.add(Util.makeField(String.valueOf(line.getEndPoint().x)));
		panel2.add(Util.makeField(String.valueOf(line.getEndPoint().y)));

		this.add(new JLabel("Color"));
		JPanel colorPanel = new JPanel(new GridLayout(1, 3));
		this.add(colorPanel);
		colorPanel.add(Util.makeField(String.valueOf(line.getColor().getRed())));
		colorPanel.add(Util.makeField(String.valueOf(line.getColor().getGreen())));
		colorPanel.add(Util.makeField(String.valueOf(line.getColor().getBlue())));
	}

}
