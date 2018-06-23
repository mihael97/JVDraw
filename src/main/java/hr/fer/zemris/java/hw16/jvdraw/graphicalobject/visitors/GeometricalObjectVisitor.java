package hr.fer.zemris.java.hw16.jvdraw.graphicalobject.visitors;

import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components.Circle;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components.FilledCircle;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.components.Line;

public interface GeometricalObjectVisitor {
	public abstract void visit(Line line);

	public abstract void visit(Circle circle);

	public abstract void visit(FilledCircle filledCircle);
}
