package hr.fer.zemris.java.hw16.jvdraw;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.JComponent;

import hr.fer.zemris.java.hw16.jvdraw.interfaces.ColorChangeListener;
import hr.fer.zemris.java.hw16.jvdraw.interfaces.IColorProvider;

public class JColorArea extends JComponent implements IColorProvider {
	/**
	 * Current selected color
	 */
	private Color selectedColor;
	/**
	 * List of all listeners
	 */
	private List<ColorChangeListener> listeners;

	public JColorArea(Color selectedColor) {
		this.selectedColor = selectedColor;
		this.listeners = new ArrayList<>();
	}

	/**
	 * Method returns preferred size. In this case,method always return
	 * <code>15x15</code>
	 * 
	 * @return preferred size
	 */
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(15, 15);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.hw16.jvdraw.interfaces.IColorProvider#getCurrentColor()
	 */
	@Override
	public Color getCurrentColor() {
		return selectedColor;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.hw16.jvdraw.interfaces.IColorProvider#addColorChangeListner(hr.fer.zemris.java.hw16.jvdraw.interfaces.ColorChangeListener)
	 */
	@Override
	public void addColorChangeListner(ColorChangeListener listener) {
		listeners.add(Objects.requireNonNull(listener, "Listener cannot be null!"));
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.hw16.jvdraw.interfaces.IColorProvider#removeColorChangeListener(hr.fer.zemris.java.hw16.jvdraw.interfaces.ColorChangeListener)
	 */
	@Override
	public void removeColorChangeListener(ColorChangeListener listener) {
		listeners.remove(listener);
	}
}
