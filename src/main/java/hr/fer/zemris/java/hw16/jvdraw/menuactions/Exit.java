package hr.fer.zemris.java.hw16.jvdraw.menuactions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import hr.fer.zemris.java.hw16.jvdraw.menuactions.saving.SaveFileInfo;
import hr.fer.zemris.java.hw16.jvdraw.menuactions.saving.SaveUtilities;

/**
 * Class implements exit action
 * 
 * @author Mihael
 *
 */
public class Exit extends AbstractAction {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Method tries to exit from applicatio<br>
	 * If there is still unsaved data,method will ask if we want to save it
	 * 
	 * @param arg0
	 *            - action event
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (SaveFileInfo.isModified()) {
			if (SaveUtilities.showExitDialog()) {
				System.out.println("Stija bi");
			}
		}

		System.exit(0);
	}
}
