package hr.fer.zemris.java.hw16.jvdraw.drawing;

import java.awt.Graphics;

import javax.swing.JComponent;

import hr.fer.zemris.java.hw16.jvdraw.drawing.interfaces.DrawingModel;
import hr.fer.zemris.java.hw16.jvdraw.drawing.interfaces.DrawingModelListener;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.GeometricalObject;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components.Circle;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components.FilledCircle;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components.Line;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.visitors.GeometricalObjectPainter;

public class JDrawingCanvas extends JComponent implements DrawingModelListener {
	/**
	 * Reference to drawing model
	 */
	private DrawingModel model;

	/**
	 * Constructor
	 * 
	 * @param model
	 *            - drawing model
	 */
	public JDrawingCanvas(DrawingModel model) {
		this.model = model;
		this.model.addDrawingModelListener(this);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.hw16.jvdraw.drawing.interfaces.DrawingModelListener#objectsAdded(hr.fer.zemris.java.hw16.jvdraw.drawing.interfaces.DrawingModel,
	 *      int, int)
	 */
	@Override
	public void objectsAdded(DrawingModel source, int index0, int index1) {
		model.add(source.getObject(index0));
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.hw16.jvdraw.drawing.interfaces.DrawingModelListener#objectsRemoved(hr.fer.zemris.java.hw16.jvdraw.drawing.interfaces.DrawingModel,
	 *      int, int)
	 */
	@Override
	public void objectsRemoved(DrawingModel source, int index0, int index1) {
		repaint();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.hw16.jvdraw.drawing.interfaces.DrawingModelListener#objectsChanged(hr.fer.zemris.java.hw16.jvdraw.drawing.interfaces.DrawingModel,
	 *      int, int)
	 */
	@Override
	public void objectsChanged(DrawingModel source, int index0, int index1) {
		repaint();
	}

	/**
	 * Method goes trough every stored {@link GeometricalObject} and renders them
	 * 
	 * @param graphics - {@link Graphics}
	 */
	@Override
	protected void paintComponent(Graphics graphics) {
		GeometricalObjectPainter painter = new GeometricalObjectPainter(graphics);

		for (int i = 0, len = model.getSize(); i < len; i++) {
			GeometricalObject object = model.getObject(i);

			if (object instanceof Line) {
				painter.visit((Line) object);
			} else if (object instanceof Circle) {
				painter.visit((Circle) object);
			} else if (object instanceof FilledCircle) {
				painter.visit((FilledCircle) object);
			} else {
				throw new ClassCastException("Class cannot be casted to any supported type!");
			}
		}
	}

}
