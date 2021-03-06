package hr.fer.zemris.java.hw16.jvdraw.menuactions.saving;

import java.awt.Color;
import java.awt.Point;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import hr.fer.zemris.java.hw16.jvdraw.JVDraw.ErrorClass;
import hr.fer.zemris.java.hw16.jvdraw.drawing.interfaces.DrawingModel;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.GeometricalObject;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components.Circle;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components.FilledCircle;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components.Line;

/**
 * Class contains utility methods for saving process
 * 
 * @author Mihael
 *
 */
public abstract class SaveUtilities {
	/**
	 * Method goes trough every object,turn its parameters into text,and writes it
	 * into file
	 * 
	 * @param path
	 *            - path to file
	 * @param model
	 *            - drawing model
	 */
	public static void saveFile(Path path, DrawingModel model) {
		StringBuilder builder = new StringBuilder();

		for (int i = 0, len = model.getSize(); i < len; i++) {
			builder.append(getText(model.getObject(i)) + "\n");
		}

		SaveUtilities.writeData(path, builder.toString());
		SaveFileInfo.setUnModified();
	}

	/**
	 * Method shows save dialog and returns selected path
	 * 
	 * @return selected path
	 */
	public static Path getSavingPath() {
		JFileChooser cf = new JFileChooser();
		cf.setDialogTitle("Select location: ");

		if (cf.showSaveDialog(null) != JFileChooser.APPROVE_OPTION) {
			return null;
		}

		Path path = cf.getSelectedFile().toPath();

		if (path.toFile().exists()) {
			int response = JOptionPane.showConfirmDialog(null, "File already exists. Do you want overwrite it?",
					"Warning", JOptionPane.YES_NO_OPTION);

			if (response == JOptionPane.CANCEL_OPTION || response == JOptionPane.NO_OPTION) {
				return null;
			}
		}

		return path;
	}

	/**
	 * Method writes data on disc in <code>jvd</code> format
	 * 
	 * @param path
	 *            - path to place where we store data
	 * @param data
	 *            - data for store
	 */
	private static void writeData(Path path, String data) {
		System.out.println(path.toString().lastIndexOf("."));

		path = Paths.get(path.toString() + ".jvd");
		System.out.println(path);

		try (BufferedWriter writer = new BufferedWriter(Files.newBufferedWriter(path))) {
			writer.write(data);
		} catch (IOException e1) {
			ErrorClass.showError(e1.getLocalizedMessage());
		}
	}

	/**
	 * Method shows exit dialog in case when we want to exit program but have some
	 * unsaved data
	 * 
	 * @return <code>true</code> if we want save data,otherwise <code>false</code>
	 */
	public static boolean showExitDialog() {
		if (JOptionPane.showConfirmDialog(null, "There are still unsaved objects. Do you want to save it?", "Exit",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			return true;
		}

		return false;
	}

	/**
	 * Method turns {@link GeometricalObject} parameters into text
	 * 
	 * @param object
	 *            - {@link GeometricalObject}
	 * @return parameters in textual format
	 */
	private static String getText(GeometricalObject object) {
		if (object instanceof Line) {
			Point start = ((Line) object).getStartPoint();
			Point end = ((Line) object).getEndPoint();
			Color color = ((Line) object).getColor();

			return "LINE " + start.x + " " + start.y + " " + end.x + " " + end.y + " " + color.getRed() + " "
					+ color.getGreen() + " " + color.getBlue();
		} else if (object instanceof Circle) {
			Point center = ((Circle) object).getCenter();
			double radius = ((Circle) object).getRadius();
			Color color = ((Circle) object).getColor();
			Color colorFill = (object instanceof FilledCircle) ? ((FilledCircle) object).getFillColor() : null;

			return ((object instanceof FilledCircle) ? "FCIRCLE " : "CIRCLE ") + center.x + " " + center.y + " "
					+ (int)radius + " " + color.getRed() + " " + color.getGreen() + " " + color.getBlue()
					+ ((object instanceof FilledCircle)
							? (" " + colorFill.getRed() + " " + colorFill.getGreen() + " " + colorFill.getBlue())
							: "");
		}

		return null;
	}
}
