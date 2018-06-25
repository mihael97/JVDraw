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
		SaveUtilities.saveFile(path, model);
		SaveFileInfo.setPath(path);
	}
}
