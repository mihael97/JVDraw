package hr.fer.zemris.java.hw16.jvdraw.graphicalobject.editors;

import javax.swing.JPanel;

public abstract class GeometricalObjectEditor extends JPanel {
	public abstract void checkEditing();

	public abstract void acceptEditing();
}
