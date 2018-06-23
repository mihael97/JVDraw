package hr.fer.zemris.java.hw16.jvdraw.drawing;

import javax.swing.JComponent;

import hr.fer.zemris.java.hw16.jvdraw.drawing.interfaces.DrawingModel;
import hr.fer.zemris.java.hw16.jvdraw.drawing.interfaces.DrawingModelListener;

public class JDrawingCanvas extends JComponent implements DrawingModelListener {
	private DrawingModel model;

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

}
