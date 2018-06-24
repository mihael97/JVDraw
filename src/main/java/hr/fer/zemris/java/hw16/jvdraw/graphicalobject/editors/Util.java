package hr.fer.zemris.java.hw16.jvdraw.graphicalobject.editors;

import java.awt.TextField;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public abstract class Util {
	public static boolean checkRGB(Integer red, Integer green, Integer blue) {
		if (!(red >= 0 && red <= 256 && green >= 0 && green <= 256 && blue >= 0 && blue <= 256)) {
			return false;
		}

		return true;
	}

	public static boolean checkRadius(Double radius) {
		if (radius < 0 || radius > Double.MAX_VALUE) {
			return false;
		}

		return true;
	}

	public static int[] getPoint(JPanel panel) {
		int[] forReturn = new int[2];
		forReturn[0] = Integer.parseInt(((JTextField) (panel.getComponentAt(1, 1))).getText());
		forReturn[1] = Integer.parseInt(((JTextField) (panel.getComponentAt(2, 1))).getText());

		return forReturn;
	}

	public static void addPanels(JPanel mainPanel, boolean b) {
		JPanel panel = new JPanel();
		mainPanel.add(panel);
		panel.add(new TextField(3));
		panel.add(new TextField(3));
		if (b == true) {
			panel.add(new TextField(3));
		}
	}

	public static void addPoint(JPanel mainPanel, String string) {
		mainPanel.add(new JLabel(string));
		addPanels(mainPanel, false);
	}

	public static void addRadius(JPanel panel) {
		panel.add(new JLabel("Radius: "));
		panel.add(new JTextField(5));
	}
}
