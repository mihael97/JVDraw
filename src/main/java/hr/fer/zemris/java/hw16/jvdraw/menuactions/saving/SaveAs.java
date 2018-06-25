package hr.fer.zemris.java.hw16.jvdraw.menuactions.saving;

import java.awt.event.ActionEvent;
import java.nio.file.Path;
import javax.swing.AbstractAction;
import hr.fer.zemris.java.hw16.jvdraw.drawing.interfaces.DrawingModel;

/**
 * Class represents saving process when path doesn't exist or we just want save
 * file to different location
 * 
 * @author Mihael
 *
 */
public class SaveAs extends AbstractAction {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Drawing model
	 */
	private DrawingModel model;

	/**
	 * Default constructor
	 * 
	 * @param model
	 *            - drawing model
	 */
	public SaveAs(DrawingModel model) {
		this.model = model;
	}

	/**
	 * Method performs save as process
	 * 
	 * @param arg0
	 *            - action event
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Path path = SaveUtilities.getSavingPath();
		SaveUtilities.saveFile(path, model);
		SaveFileInfo.setPath(path);
	}
}
