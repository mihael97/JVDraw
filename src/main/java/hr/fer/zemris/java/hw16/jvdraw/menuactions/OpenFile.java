package hr.fer.zemris.java.hw16.jvdraw.menuactions;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;

import hr.fer.zemris.java.hw16.jvdraw.JVDraw.ErrorClass;
import hr.fer.zemris.java.hw16.jvdraw.drawing.interfaces.DrawingModel;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.GeometricalObject;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components.Circle;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components.FilledCircle;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components.Line;
import hr.fer.zemris.java.hw16.jvdraw.menuactions.saving.SaveFileInfo;

/**
 * Action performs <code>*.jvd</code> file opening
 * 
 * @author Mihael
 *
 */
public class OpenFile extends AbstractAction {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Drawing model
	 */
	private DrawingModel model;

	/**
	 * Constructor
	 * 
	 * @param model
	 *            - drawing model
	 */
	public OpenFile(DrawingModel model) {
		this.model = Objects.requireNonNull(model, "Model cannot be null!");
	}

	/**
	 * Method open file from disc and fills {@link DrawingModel} with new objects
	 * 
	 * @param arg0
	 *            - action event
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JFileChooser fc = new JFileChooser();

		fc.setDialogTitle("Open file");

		if (fc.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
			return;
		}

		Path path = fc.getSelectedFile().toPath();
		try {
			List<GeometricalObject> list = readFile(path);

			for (GeometricalObject object : list) {
				model.add(object);
			}

		} catch (NoSuchFileException e) {
			ErrorClass.showError(e.getMessage() + " doesn't exist!");
		} catch (IOException e) {
			e.printStackTrace();
		}

		SaveFileInfo.setUnModified();
		SaveFileInfo.setPath(path);
	}

	/**
	 * Method reads <code>*.jvd</code> file form disc
	 * 
	 * @param file
	 *            - path to file
	 * @return list with {@link GeometricalObject} which parameters are read in file
	 * @throws IOException
	 *             - if exception during reading appears
	 */
	private List<GeometricalObject> readFile(Path file) throws IOException {
		List<String> list = Files.readAllLines(file);
		List<GeometricalObject> forReturn = new ArrayList<>();

		for (String string : list) {
			String[] array = string.split("\\s");

			switch (array[0]) {
			case "LINE":
				int xStart = Integer.parseInt(array[1]);
				int yStart = Integer.parseInt(array[2]);
				int xEnd = Integer.parseInt(array[3]);
				int yEnd = Integer.parseInt(array[4]);

				Color color = new Color(Integer.parseInt(array[5]), Integer.parseInt(array[6]),
						Integer.parseInt(array[7]));

				forReturn.add(new Line(xStart, yStart, xEnd, yEnd, color));
				break;
			case "CIRCLE":
				int xCenter = Integer.parseInt(array[1]);
				int yCenter = Integer.parseInt(array[2]);
				double radius = Integer.parseInt(array[3]);
				Color colo = new Color(Integer.parseInt(array[4]), Integer.parseInt(array[5]),
						Integer.parseInt(array[6]));

				forReturn.add(new Circle(xCenter, yCenter, radius, colo));
				break;

			case "FCIRCLE":
				int x = Integer.parseInt(array[1]);
				int y = Integer.parseInt(array[2]);
				double rad = Integer.parseInt(array[3]);
				Color col = new Color(Integer.parseInt(array[4]), Integer.parseInt(array[5]),
						Integer.parseInt(array[6]));
				Color colFill = new Color(Integer.parseInt(array[7]), Integer.parseInt(array[8]),
						Integer.parseInt(array[9]));

				forReturn.add(new FilledCircle(x, y, rad, col, colFill));
				break;
			}
		}

		return forReturn;
	}

}
