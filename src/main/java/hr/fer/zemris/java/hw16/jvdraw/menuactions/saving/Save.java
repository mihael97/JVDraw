package hr.fer.zemris.java.hw16.jvdraw.menuactions.saving;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import hr.fer.zemris.java.hw16.jvdraw.drawing.interfaces.DrawingModel;

/**
 * Class represents saving action when path for current file exists
 * 
 * @author Mihael
 *
 */
public class Save extends AbstractAction {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Drawing model
	 */
	private DrawingModel model;

	/**
	 * Constructor for new Save action
	 * 
	 * @param model
	 *            - drawing model
	 */
	public Save(DrawingModel model) {
		this.model = model;
	}

	/**
	 * Method provides implementation for saving process.<br>
	 * If path doesn't exist,method delegates request to {@link SaveAs}
	 */
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
