package hr.fer.zemris.java.hw16.jvdraw.components;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JLabel;

import hr.fer.zemris.java.hw16.jvdraw.color.ColorChangeListener;
import hr.fer.zemris.java.hw16.jvdraw.color.IColorProvider;

/**
 * Class extends {@link JLabel} and in real time shows current informations
 * about chosen colors<br>
 * There are two types of color,background and foreground. For every color,their
 * representation in <code>RGB</code> format is shown
 * 
 * @author Mihael
 *
 */
public class ColorLabel extends JLabel implements ColorChangeListener {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Color provider for background color
	 */
	private IColorProvider backgroudProvider;
	/**
	 * Color provider for foreground color
	 */
	private IColorProvider foregroundProvider;

	/**
	 * Constructor accepts references of provider to background and foreground color
	 * and initializes new color label which shows informations about current colors
	 *
	 * @param backgroundProvider
	 *            - background color provider
	 * @param foregroundProvider
	 *            - foreground color provider
	 */
	public ColorLabel(IColorProvider backgroundProvider, IColorProvider foregroundProvider) {
		super("Mihael");
		this.backgroudProvider = backgroundProvider;
		this.foregroundProvider = foregroundProvider;

		this.backgroudProvider.addColorChangeListener(this);
		this.foregroundProvider.addColorChangeListener(this);
	}

	/**
	 * Method is called when color change happened
	 *
	 * @param source
	 *            - place where color changed
	 * @param oldColor
	 *            - old color
	 * @param newColor
	 *            - new color
	 */
	@Override
	public void newColorSelected(IColorProvider source, Color oldColor, Color newColor) {
		if (newColor != null) {
			repaint();
		}
	}

	/**
	 * Method accepts {@link Graphics} and sets updates informations of chosen
	 * colors
	 *
	 * @param graphics
	 *            - graphics
	 */
	@Override
	protected void paintComponent(Graphics graphics) {
		Color front = foregroundProvider.getCurrentColor();
		Color back = backgroudProvider.getCurrentColor();

		super.setText("Foreground color: (" + front.getRed() + ", " + front.getGreen() + ", " + front.getBlue()
				+ "), Background: (" + back.getRed() + ", " + back.getGreen() + ", " + back.getBlue() + ").");

		super.paintComponent(graphics);
	}
}
