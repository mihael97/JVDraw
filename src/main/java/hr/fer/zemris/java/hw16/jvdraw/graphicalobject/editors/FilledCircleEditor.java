package hr.fer.zemris.java.hw16.jvdraw.graphicalobject.editors;

import java.util.Objects;

import javax.swing.JPanel;
import javax.swing.JTextField;

import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components.FilledCircle;

public class FilledCircleEditor extends GeometricalObjectEditor {
	private FilledCircle circle;
	private Integer xCenter;
	private Integer yCenter;
	private Double radius;
	private Integer red;
	private Integer green;
	private Integer blue;

	public FilledCircleEditor(FilledCircle circle) {
		this.circle = Objects.requireNonNull(circle, "Circle reference cannot be null!");
	}

	@Override
	public void checkEditing() {
		int[] array = Util.getPoint((JPanel) this.getComponentAt(1, 2));
		this.xCenter = array[0];
		this.yCenter = array[1];

		this.radius = Double.parseDouble(((JTextField) (this.getComponentAt(2, 2))).getText());

		if (!Util.checkRadius(radius)) {
			throw new IllegalArgumentException("Invalid radius argument!");
		}

		JPanel RGBPanel = (JPanel) (this.getComponentAt(3, 2));
		this.red = Integer.parseInt(((JTextField) (RGBPanel.getComponentAt(1, 1))).getText());
		this.green = Integer.parseInt(((JTextField) (RGBPanel.getComponentAt(2, 1))).getText());
		this.blue = Integer.parseInt(((JTextField) (RGBPanel.getComponentAt(3, 1))).getText());

		if (!Util.checkRGB(red, green, blue)) {
			throw new IllegalArgumentException("invalid color!");
		}
	}

	@Override
	public void acceptEditing() {
		// TODO Auto-generated method stub

	}

}
