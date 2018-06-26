package hr.fer.zemris.java.hw16.jvdraw.JVDraw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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
import hr.fer.zemris.java.hw16.jvdraw.drawing.JDrawingCanvas;
import hr.fer.zemris.java.hw16.jvdraw.drawing.interfaces.DrawingModel;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.GeometricalObject;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components.Circle;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components.FilledCircle;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components.Line;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.editors.GeometricalObjectEditor;
import hr.fer.zemris.java.hw16.jvdraw.menuactions.Exit;
import hr.fer.zemris.java.hw16.jvdraw.menuactions.Export;
import hr.fer.zemris.java.hw16.jvdraw.menuactions.OpenFile;
import hr.fer.zemris.java.hw16.jvdraw.menuactions.saving.Save;
import hr.fer.zemris.java.hw16.jvdraw.menuactions.saving.SaveAs;

/**
 * Class represents main class where graphical interface is initialized
 * 
 * @author Mihael
 *
 */
public class JVDraw extends JFrame {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Reference to drawing model
	 */
	private DrawingModel model;

	/**
	 * Reference to current drawing object on {@link JDrawingCanvas}
	 */
	private GeometricalObject currentState;

	/**
	 * Color provider for drawing color
	 */
	private JColorArea fgColorArea;
	/**
	 * Color provider for filling color
	 */
	private JColorArea bgColorArea;

	/**
	 * Reference to {@link JDrawingCanvas}
	 */
	private JDrawingCanvas canvas;

	/**
	 * Group of button contains buttons for changing type of
	 * {@link GeometricalObject} we drawing
	 */
	private ButtonGroup group;

	/**
	 * Constructor creates new JVDraw
	 */
	public JVDraw() {
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		model = new DrawingModelImplementation();
		canvas = new JDrawingCanvas(model, this);
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent arg0) {
				new Exit(model).actionPerformed(null);
			}

		});
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
		fgColorArea = new JColorArea(Color.BLUE);
		bgColorArea = new JColorArea(Color.RED);
		this.add(createPanel(fgColorArea, bgColorArea), BorderLayout.PAGE_START);
		this.add(new ColorLabel(fgColorArea, bgColorArea), BorderLayout.PAGE_END);
		this.add(canvas, BorderLayout.CENTER);
		this.add(new JScrollPane(createList()), BorderLayout.LINE_END);
	}

	/**
	 * Method creates {@link JList} with {@link GeometricalObject}s
	 * 
	 * @return {@link JList}
	 */
	private Component createList() {
		JList<GeometricalObject> list = new JList<>(new DrawingObjectListModel(model));
		list.addMouseListener(addDoubleClickListener());
		list.addKeyListener(addKeyListener());

		return list;
	}

	/**
	 * Method adds key listener. Key are: <br>
	 * <ol>
	 * <li>delete button - deletes item from list</li>
	 * <li>plus button - changes item priority to higher</li>
	 * <li>delete button - changes item priority to lower</li>
	 * </ol>
	 * 
	 * @return prepared KeyListener
	 */
	private KeyListener addKeyListener() {
		return new KeyAdapter() {

			@SuppressWarnings("unchecked")
			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();

				if (keyCode == KeyEvent.VK_DELETE) {
					model.remove(((JList<GeometricalObject>) e.getSource()).getSelectedValue());
				} else if (keyCode == 61 || keyCode == 107) { // for some reasons,constant 'KeyEvent.VK_PLUS' doesn't
																// want to work,so I had to write like that :/
					model.changeOrder(((JList<GeometricalObject>) e.getSource()).getSelectedValue(), -1);
				} else if (keyCode == KeyEvent.VK_MINUS) {
					model.changeOrder(((JList<GeometricalObject>) e.getSource()).getSelectedValue(), 1);
				}

			}

		};
	}

	/**
	 * Method returns mouse listener which shows dialog after double click on list
	 * item
	 * 
	 * @return mouse listener
	 */
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

				if (clicked == null) {
					return;
				}

				GeometricalObjectEditor editor = clicked.createGeometricalObjectEditor();
				if (JOptionPane.showConfirmDialog(JVDraw.this, editor, "Edit",
						JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
					try {
						editor.checkEditing();
						editor.acceptEditing();
					} catch (Exception ex) {
						ErrorClass.showError(ex.getLocalizedMessage());
					}
				}
			}

		};
	}

	/**
	 * Method creates {@link JPanel} for top side of out frame
	 * 
	 * @param fgColorArea
	 *            - {@link JColorArea}
	 * @param bgColorArea
	 *            - {@link JColorArea}
	 * @return {@link JPanel} with components for top frame
	 */
	private JPanel createPanel(JColorArea fgColorArea, JColorArea bgColorArea) {
		JPanel panel = new JPanel(new GridLayout(2, 1));

		JMenu file = new JMenu("File");
		JMenuBar bar = new JMenuBar();
		panel.add(bar, JPanel.LEFT_ALIGNMENT);
		bar.add(file);

		addItem(file, "Open", new OpenFile(model));
		addItem(file, "Export", new Export(model));
		addItem(file, "Save As", new SaveAs(model));
		addItem(file, "Save", new Save(model));
		addItem(file, "Exit", new Exit(model));

		JToolBar toolBar = new JToolBar();
		panel.add(toolBar);

		toolBar.add(new JLabel("Draw:"));
		toolBar.add(fgColorArea);
		toolBar.add(new JLabel("Fill:"));
		toolBar.add(bgColorArea);
		List<AbstractButton> list = makeButtons();
		group = new ButtonGroup();
		toolBar.add(new JLabel("Components: "));
		for (AbstractButton comp : list) {
			toolBar.add(comp);
			group.add(comp);
		}

		return panel;
	}

	/**
	 * Method adds item to {@link JMenu}
	 * 
	 * @param menu
	 *            - menu where we put our buttons
	 * @param string
	 *            - button name
	 * @param action
	 *            - button action
	 */
	private void addItem(JMenu menu, String string, AbstractAction action) {
		JMenuItem item = new JMenuItem(string);
		item.addActionListener(action);
		menu.add(item);
	}

	/**
	 * Method creates {@link JToggleButton}s for line,circle and filled circle
	 * 
	 * @return List of {@link JToggleButton}
	 */
	private List<AbstractButton> makeButtons() {
		List<AbstractButton> list = new ArrayList<>();

		JToggleButton line = new JToggleButton("Line");
		line.addActionListener(e -> {
			currentState = new Line(fgColorArea);
		});
		list.add(line);
		JToggleButton circle = new JToggleButton("Circle");
		circle.addActionListener(e -> {
			currentState = new Circle(fgColorArea);
		});
		list.add(circle);

		JToggleButton filledCircle = new JToggleButton("Filled circle");
		filledCircle.addActionListener(e -> {
			currentState = new FilledCircle(fgColorArea, bgColorArea);

		});
		list.add(filledCircle);

		return list;
	}

	/**
	 * Method returns current active {@link GeometricalObject} state
	 * 
	 * @return current active state
	 */
	public GeometricalObject getCurrentState() {
		return currentState;
	}

	/**
	 * Method sets current state to <code>null</code><br>
	 * This method is called when drawing object is finished and stored
	 */
	public void reset() {
		if (currentState instanceof Line) {
			currentState = new Line(fgColorArea);
		} else if (currentState instanceof Circle && !(currentState instanceof FilledCircle)) {
			currentState = new Circle(fgColorArea);
		} else {
			currentState = new FilledCircle(fgColorArea, bgColorArea);
		}
	}

	/**
	 * Main program
	 * 
	 * @param args
	 *            - not in use
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new JVDraw().setVisible(true);
		});
	}
}
