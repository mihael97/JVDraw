package hr.fer.zemris.java.hw16.jvdraw.menuactions.saving;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import hr.fer.zemris.java.hw16.jvdraw.drawing.interfaces.DrawingModel;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.GeometricalObject;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components.Circle;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components.FilledCircle;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components.Line;

public class SaveAs extends AbstractAction {
	private DrawingModel model;

	public SaveAs(DrawingModel model) {
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Path path = SaveUtilities.getSavingPath();
		StringBuilder builder = new StringBuilder();

		for (int i = 0, len = model.getSize(); i < len; i++) {
			builder.append(getText(model.getObject(i)) + "\n");
		}

		SaveUtilities.writeData(path, builder.toString());
		SaveFileInfo.setUnModified();
		SaveFileInfo.setPath(path);
	}

	private String getText(GeometricalObject object) {
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

			return (object instanceof FilledCircle) ? "FCIRCLE "
					: "CIRCLE " + center.x + " " + center.y + " " + radius + " " + color.getRed() + " "
							+ color.getGreen() + " " + color.getBlue()
							+ ((object instanceof FilledCircle)
									? (" " + colorFill.getRed() + " " + colorFill.getGreen() + " "
											+ colorFill.getBlue())
									: "");
		}

		return null;
	}

}
