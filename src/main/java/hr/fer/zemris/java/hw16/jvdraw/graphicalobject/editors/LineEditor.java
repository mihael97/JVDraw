package hr.fer.zemris.java.hw16.jvdraw.graphicalobject.editors;

import java.util.Objects;

import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components.Line;

public class LineEditor extends GeometricalObjectEditor {
	private Line line;

	public LineEditor(Line line) {
		this.line = Objects.requireNonNull(line, "Reference to line cannot be null!");
	}

	@Override
	public void checkEditing() {

	}

	@Override
	public void acceptEditing() {
		// TODO Auto-generated method stub

	}

}
