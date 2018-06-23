package hr.fer.zemris.java.hw16.jvdraw.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.JColorChooser;
import javax.swing.JComponent;

import hr.fer.zemris.java.hw16.jvdraw.color.ColorChangeListener;
import hr.fer.zemris.java.hw16.jvdraw.color.IColorProvider;

/**
 * Class represents structure that stored informations about color<br>
 * When selected color has been change,class calls method for informs all
 * listeners about color changing
 * 
 * @author Mihael
 *
 */
public class JColorArea extends JComponent implements IColorProvider {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Current selected color
	 */
	private Color selectedColor;
	/**
	 * List of all listeners
	 */
	private List<ColorChangeListener> listeners;

	/**
	 * Constructor initializes color
	 * 
	 * @param selectedColor
	 *            - initial color
	 */
	public JColorArea(Color selectedColor) {
		this.selectedColor = selectedColor;
		this.listeners = new ArrayList<>();
		addMouseListener();
	}

	/**
	 * Method adds mouse listener on {@link JColorArea} component. On every
	 * event,color chooser appears so we can select new selected color
	 */
	private void addMouseListener() {
		this.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent arg0) {
				setColorAndInform(JColorChooser.showDialog(JColorArea.this, "Color chooser", selectedColor));
			}

		});
	}

	/**
	 * Method sets selected color to new color and informs all listeners about
	 * change
	 * 
	 * @param color
	 *            - selected color
	 */
	private void setColorAndInform(Color color) {
		Color oldColor = this.selectedColor;
		this.selectedColor = color;
		listeners.forEach(e -> e.newColorSelected(this, oldColor, this.selectedColor));

		// repaint();
	}

	/**
	 * Method returns preferred size. In this case,method always returns dimensions
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
	 * @see hr.fer.zemris.java.hw16.jvdraw.interfaces.IColorProvider#removeColorChangeListener(hr.fer.zemris.java.hw16.jvdraw.interfaces.ColorChangeListener)
	 */
	@Override
	public void removeColorChangeListener(ColorChangeListener listener) {
		listeners.remove(listener);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.hw16.jvdraw.interfaces.IColorProvider#addColorChangeListner(hr.fer.zemris.java.hw16.jvdraw.interfaces.ColorChangeListener)
	 */
	@Override
	public void addColorChangeListener(ColorChangeListener listener) {
		listeners.add(Objects.requireNonNull(listener, "Listener cannot be null!"));

	}

}
