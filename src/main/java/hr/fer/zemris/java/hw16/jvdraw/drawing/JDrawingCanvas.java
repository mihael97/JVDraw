package hr.fer.zemris.java.hw16.jvdraw.drawing;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;

import hr.fer.zemris.java.hw16.jvdraw.JVDraw.JVDraw;
import hr.fer.zemris.java.hw16.jvdraw.drawing.interfaces.DrawingModel;
import hr.fer.zemris.java.hw16.jvdraw.drawing.interfaces.DrawingModelListener;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.GeometricalObject;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components.Circle;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components.FilledCircle;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components.Line;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.visitors.GeometricalObjectPainter;
import hr.fer.zemris.java.hw16.jvdraw.menuactions.saving.SaveFileInfo;

/**
 * Class represents {@link JComponent} where we draw our objects
 * 
 * @author Mihael
 *
 */
public class JDrawingCanvas extends JComponent implements DrawingModelListener {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Reference to drawing model
	 */
	private DrawingModel model;

	/**
	 * Reference to frame where {@link JDrawingCanvas} is set
	 */
	private JVDraw frame;

	/**
	 * Shows if there is active objects which must be finished
	 */
	private boolean firstClick = false;

	/**
	 * Flag shows if button is pressed
	 */
	private boolean isPressed;

	/**
	 * Constructor
	 * 
	 * @param model
	 *            - drawing model
	 * @param jvDraw
	 */
	public JDrawingCanvas(DrawingModel model, JVDraw jvDraw) {
		this.model = model;
		this.frame = jvDraw;
		this.model.addDrawingModelListener(this);
		addMouseListener();
	}

	/**
	 * Method adds mouse listener for actions when: <br>
	 * <ul>
	 * <li>mouse is clicked</li>
	 * <li>mouse is moved</li>
	 * </ul>
	 */
	private void addMouseListener() {
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				GeometricalObject object = frame.getCurrentState();
				if (object instanceof Line) {
					((Line) object).mousePressed(e);
				} else if (object instanceof Circle) {
					((Circle) object).mousePressed(e);
				} else if (object instanceof FilledCircle) {
					((FilledCircle) object).mousePressed(e);
				}

				if (object != null) {
					if (!firstClick) {
						firstClick = true;
						isPressed = true;
						repaint();
					} else {
						frame.reset();
						firstClick = false;
						model.add(object);
					}
				}
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				if (firstClick == true && !isPressed) {
					mousePressed(arg0);
				}

				if (isPressed) {
					isPressed = false;
				}
			}

		});

		this.addMouseMotionListener(new MouseAdapter() {

			@Override
			public void mouseDragged(MouseEvent e) {
				if (isPressed) {
					isPressed = false;
				}
				moveMotion(e, false);
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				moveMotion(e, true);
			}

		});
	}

	/**
	 * Method informs objects about mouse position change
	 * 
	 * @param e
	 *            - mouse event
	 * @param flag
	 *            - shows if mouse was dragged on moved
	 */
	public void moveMotion(MouseEvent e, boolean flag) {
		GeometricalObject object = frame.getCurrentState();
		if (object instanceof Line) {
			if (flag == true)
				((Line) object).mouseMoved(e);
			else
				((Line) object).mouseDragged(e);
		} else if (object instanceof Circle) {
			if (flag == true) {
				((Circle) object).mouseMoved(e);
			} else
				((Circle) object).mouseDragged(e);
		} else if (object instanceof FilledCircle) {
			if (flag == true)
				((FilledCircle) object).mouseMoved(e);
			else
				((FilledCircle) object).mouseDragged(e);
		}

		repaint();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.hw16.jvdraw.drawing.interfaces.DrawingModelListener#objectsAdded(hr.fer.zemris.java.hw16.jvdraw.drawing.interfaces.DrawingModel,
	 *      int, int)
	 */
	@Override
	public void objectsAdded(DrawingModel source, int index0, int index1) {
		repaintAndModified();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.hw16.jvdraw.drawing.interfaces.DrawingModelListener#objectsRemoved(hr.fer.zemris.java.hw16.jvdraw.drawing.interfaces.DrawingModel,
	 *      int, int)
	 */
	@Override
	public void objectsRemoved(DrawingModel source, int index0, int index1) {
		repaintAndModified();

	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.hw16.jvdraw.drawing.interfaces.DrawingModelListener#objectsChanged(hr.fer.zemris.java.hw16.jvdraw.drawing.interfaces.DrawingModel,
	 *      int, int)
	 */
	@Override
	public void objectsChanged(DrawingModel source, int index0, int index1) {
		repaintAndModified();
	}

	/**
	 * Method calls repaint method and sets saved status to <code>false</code>
	 */
	private void repaintAndModified() {
		SaveFileInfo.setModified();
		repaint();
	}

	/**
	 * Method goes trough every stored {@link GeometricalObject} and renders them
	 * 
	 * @param graphics
	 *            - {@link Graphics}
	 */
	@Override
	protected void paintComponent(Graphics graphics) {
		GeometricalObjectPainter painter = new GeometricalObjectPainter(graphics);
		for (int i = 0, len = model.getSize(); i < len; i++) {
			GeometricalObject object = model.getObject(i);

			if (object instanceof Line) {
				painter.visit((Line) object);
			} else if (object instanceof FilledCircle) {
				painter.visit((FilledCircle) object);
			} else if (object instanceof Circle) {
				painter.visit((Circle) object);
			}
		}

		GeometricalObject object = frame.getCurrentState();

		if (object == null)
			return;

		if (object instanceof Line) {
			((Line) object).paint((Graphics2D) graphics);

		} else if (object instanceof FilledCircle) {
			((FilledCircle) object).paint((Graphics2D) graphics);
		} else if (object instanceof Circle) {
			((Circle) object).paint((Graphics2D) graphics);
		}
	}

}
