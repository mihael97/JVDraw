package hr.fer.zemris.java.hw16.jvdraw.graphicalobject.editors;

import java.awt.GridLayout;
import java.awt.Point;
import java.awt.TextField;
import java.util.Objects;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components.Circle;

public class CircleEditor extends GeometricalObjectEditor {
	private Circle circle;
	Integer xCenter = null;
	Integer yCenter = null;
	Double radius = null;
	Integer red = null;
	Integer green = null;
	Integer blue = null;

	public CircleEditor(Circle circle) {
		this.circle = Objects.requireNonNull(circle, "Reference to circle cannot be null!");
	}

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

	@Override
	public void acceptEditing() {
		circle.setCenter(new Point(xCenter, yCenter));
		circle.setRadius(radius);
	}

	public GeometricalObjectEditor createEditor() {
		this.setLayout(new GridLayout(3, 2));
		Util.addPoint(this, "Center: ");
		Util.addRadius(this);
		this.add(new JLabel("Color: "));
		Util.addPanels(this, true);
		return this;
	}

}
