package hr.fer.zemris.java.hw16.jvdraw.graphicalobject.editors;

import java.awt.GridLayout;
import java.awt.Point;
import java.util.Objects;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components.FilledCircle;

public class FilledCircleEditor extends GeometricalObjectEditor {
	private FilledCircle circle;
	private Integer xCenter;
	private Integer yCenter;
	private Double radius;

	private Integer redDraw;
	private Integer greenDraw;
	private Integer blueDraw;

	private Integer redFill;
	private Integer greenFill;
	private Integer blueFill;

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

		JPanel RGBPanel = (JPanel) (this.getComponentAt(2, 3));
		this.redDraw = Integer.parseInt(((JTextField) (RGBPanel.getComponentAt(1, 1))).getText());
		this.greenDraw = Integer.parseInt(((JTextField) (RGBPanel.getComponentAt(2, 1))).getText());
		this.blueDraw = Integer.parseInt(((JTextField) (RGBPanel.getComponentAt(3, 1))).getText());

		if (!Util.checkRGB(redDraw, greenDraw, blueDraw)) {
			throw new IllegalArgumentException("invalid color!");
		}

		RGBPanel = (JPanel) (this.getComponentAt(2, 4));
		this.redFill = Integer.parseInt(((JTextField) (RGBPanel.getComponentAt(1, 1))).getText());
		this.greenFill = Integer.parseInt(((JTextField) (RGBPanel.getComponentAt(2, 1))).getText());
		this.blueFill = Integer.parseInt(((JTextField) (RGBPanel.getComponentAt(3, 1))).getText());

		if (!Util.checkRGB(redDraw, greenDraw, blueDraw)) {
			throw new IllegalArgumentException("invalid color!");
		}
	}

	@Override
	public void acceptEditing() {
		circle.setCenter(new Point(xCenter, yCenter));
		circle.setRadius(radius);
	}

	public GeometricalObjectEditor createEditor() {
		this.setLayout(new GridLayout(4, 2));
		Util.addPoint(this, "Center: ");
		Util.addRadius(this);
		this.add(new JLabel("Color drawing: "));
		Util.addPanels(this, true);
		this.add(new JLabel("Color filling: "));
		Util.addPanels(this, true);
		return this;
	}

}
