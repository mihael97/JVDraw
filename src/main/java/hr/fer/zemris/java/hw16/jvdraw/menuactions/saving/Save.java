package hr.fer.zemris.java.hw16.jvdraw.menuactions.saving;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import hr.fer.zemris.java.hw16.jvdraw.drawing.interfaces.DrawingModel;

public class Save extends AbstractAction {
	private DrawingModel model;

	public Save(DrawingModel model) {
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (SaveFileInfo.isModified()) {
			if (SaveFileInfo.getPath() != null) {
				SaveUtilities.saveFile(SaveFileInfo.getPath(), model);
			} else {
				new SaveAs(model).actionPerformed(null);
			}
		}
	}

}
