package hr.fer.zemris.java.hw16.jvdraw.graphicalobject.editors;

import java.awt.GridLayout;
import java.awt.Point;
import java.awt.TextField;
import java.util.Objects;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;

import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components.Line;

public class LineEditor extends GeometricalObjectEditor {
	private Line line;
	private Integer xStart;
	private Integer yStart;
	private Integer xEnd;
	private Integer yEnd;

	public LineEditor(Line line) {
		this.line = Objects.requireNonNull(line, "Reference to line cannot be null!");
		createEditor();
	}

	@Override
	public void checkEditing() {
		int[] array = Util.getPoint((JPanel) this.getComponentAt(1, 2));

		xStart = array[0];
		yStart = array[1];

		array = Util.getPoint((JPanel) this.getComponentAt(2, 2));
		xEnd = array[0];
		yEnd = array[1];

		JPanel RGBPanel = (JPanel) (this.getComponentAt(3, 2));
		int red = Integer.parseInt(((JTextField) (RGBPanel.getComponentAt(1, 1))).getText());
		int green = Integer.parseInt(((JTextField) (RGBPanel.getComponentAt(2, 1))).getText());
		int blue = Integer.parseInt(((JTextField) (RGBPanel.getComponentAt(3, 1))).getText());

		if (!Util.checkRGB(red, green, blue)) {
			throw new IllegalArgumentException("invalid color!");
		}
	}

	@Override
	public void acceptEditing() {
		line.setStartPoint(new Point(xStart, yStart));
		line.setEndPoint(new Point(xEnd, yEnd));
	}

	public void createEditor() {
		this.setLayout(new GridLayout(3, 2));
		Util.addPoint(this, "Start point: ");
		Util.addPoint(this, "End point: ");
		this.add(new JLabel("Color: "));
		Util.addPanels(this, true);
	}

}
