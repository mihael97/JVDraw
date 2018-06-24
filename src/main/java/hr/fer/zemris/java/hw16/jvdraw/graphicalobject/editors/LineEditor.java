package hr.fer.zemris.java.hw16.jvdraw.graphicalobject.editors;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.Objects;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
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
		int[] array = Util.getPoint((JPanel) this.getComponentAt(1, 2));

		xStart = array[0];
		yStart = array[1];

		array = Util.getPoint((JPanel) this.getComponentAt(2, 2));
		xEnd = array[0];
		yEnd = array[1];

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
		line.setStartPoint(new Point(xStart, yStart));
		line.setEndPoint(new Point(xEnd, yEnd));
		line.setColor(new Color(red, green, blue));
	}

	/**
	 * Method initializes form for editing
	 */
	public void createEditor() {
		this.setLayout(new GridLayout(3, 2));
		Util.addPoint(this, "Start point: ");
		Util.addPoint(this, "End point: ");
		this.add(new JLabel("Color: "));
		Util.addPanels(this, true);
	}

}
