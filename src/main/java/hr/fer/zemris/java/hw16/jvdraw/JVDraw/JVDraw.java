package hr.fer.zemris.java.hw16.jvdraw.JVDraw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import hr.fer.zemris.java.hw16.jvdraw.components.ColorLabel;
import hr.fer.zemris.java.hw16.jvdraw.components.JColorArea;
import hr.fer.zemris.java.hw16.jvdraw.drawing.DrawingModelImplementation;
import hr.fer.zemris.java.hw16.jvdraw.drawing.DrawingObjectListModel;
import hr.fer.zemris.java.hw16.jvdraw.drawing.interfaces.DrawingModel;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.GeometricalObject;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.editors.GeometricalObjectEditor;

public class JVDraw extends JFrame {
	/**
	 * Reference to drawing model
	 */
	private DrawingModel model;

	public JVDraw() {
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		model = new DrawingModelImplementation();
		setTitle("JDraw");
		setLocation(400, 200);
		setSize(500, 500);

		initGUI();
	}

	/**
	 * Method initializes graphical interface
	 */
	private void initGUI() {
		this.setLayout(new BorderLayout());

		this.add(setItems(), BorderLayout.PAGE_END);
		this.add(new JScrollPane(createList()), BorderLayout.LINE_END);
	}

	private Component createList() {
		JList<GeometricalObject> list = new JList<>(new DrawingObjectListModel(model));

		list.addMouseListener(addDoubleClickListener());

		return list;
	}

	private MouseListener addDoubleClickListener() {
		return new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent event) {
				if (event.getClickCount() != 2) {
					return;
				}

				@SuppressWarnings("unchecked")
				JList<GeometricalObject> list = (JList<GeometricalObject>) event.getSource();
				GeometricalObject clicked = list.getSelectedValue();
				GeometricalObjectEditor editor = clicked.createGeometricalObjectEditor();
				if (JOptionPane.showConfirmDialog(JVDraw.this, editor, "Edit",
						JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
					try {
						editor.checkEditing();
						editor.acceptEditing();
					} catch (Exception ex) {

					}
				}
			}

		};
	}

	private Component setItems() {
		JColorArea fgColorArea = new JColorArea(Color.RED);
		JColorArea bgColorArea = new JColorArea(Color.BLUE);

		this.add(createPanel(fgColorArea, bgColorArea), BorderLayout.PAGE_START);

		return new ColorLabel(fgColorArea, bgColorArea);
	}

	private JPanel createPanel(JColorArea fgColorArea, JColorArea bgColorArea) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1));

		JMenu file = new JMenu("File");
		panel.add(file);

		JToolBar toolBar = new JToolBar();
		panel.add(toolBar, JToolBar.LEFT_ALIGNMENT);

		toolBar.add(fgColorArea);
		toolBar.add(bgColorArea);
		List<AbstractButton> list = makeButtons();
		ButtonGroup group = new ButtonGroup();
		for (AbstractButton comp : list) {
			toolBar.add(comp);
			group.add(comp);
		}

		return panel;
	}

	private List<AbstractButton> makeButtons() {
		List<AbstractButton> list = new ArrayList<>();

		JToggleButton line = new JToggleButton("Line");
		list.add(line);
		JToggleButton circle = new JToggleButton("Circle");
		list.add(circle);

		JToggleButton filledCircle = new JToggleButton("Filled circle");
		list.add(filledCircle);

		return list;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new JVDraw().setVisible(true);
		});
	}
}
