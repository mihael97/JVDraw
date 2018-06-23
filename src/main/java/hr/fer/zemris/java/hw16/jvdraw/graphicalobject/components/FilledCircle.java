package hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;

import hr.fer.zemris.java.hw16.jvdraw.color.IColorProvider;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.GeometricalObject;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.editors.GeometricalObjectEditor;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.interfaces.Tool;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.visitors.GeometricalObjectVisitor;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class FilledCircle extends GeometricalObject implements Tool {
	private Point center;
	private double radius;
	private IColorProvider fillColorProvider;
	private IColorProvider drawColorProvider;

	public FilledCircle(IColorProvider fillColorProvider, IColorProvider drawColorProvider) {
		super();
		this.fillColorProvider = fillColorProvider;
		this.drawColorProvider = drawColorProvider;
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	private double calculateRadius(Point point) {
		return sqrt(pow(center.x - point.x, 2) + pow(center.y - point.y, 2));
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (center == null) {
			center = e.getLocationOnScreen();
		} else {
			this.radius = calculateRadius(e.getLocationOnScreen());
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		this.radius = calculateRadius(e.getLocationOnScreen());
	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void paint(Graphics2D g2d) {
		double halfRadius = (double) (radius / 2);
		g2d.setColor(drawColorProvider.getCurrentColor());
		g2d.fillOval((int) (center.x - halfRadius), (int) (center.y - halfRadius), (int) radius, (int) radius);
	}

	@Override
	public void accept(GeometricalObjectVisitor v) {
		// TODO Auto-generated method stub

	}

	@Override
	public GeometricalObjectEditor createGeometricalObjectEditor() {

	}

}
