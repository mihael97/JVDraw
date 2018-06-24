package hr.fer.zemris.java.hw16.jvdraw.menuactions;

import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import hr.fer.zemris.java.hw16.jvdraw.drawing.interfaces.DrawingModel;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.visitors.GeometricalObjectBBCalculator;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.visitors.GeometricalObjectPainter;

public class Export extends AbstractAction {

	DrawingModel model;

	public Export(DrawingModel model) {
		this.model = model;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JPanel panel = createJPanel();

		Path path = getFormatAndPath();

		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Choose path:");

		if (fc.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
			return;
		}

		File file = fc.getSelectedFile();

		// bounding box
		GeometricalObjectBBCalculator calc = new GeometricalObjectBBCalculator();

		for (int i = 0, len = model.getSize(); i < len; i++) {
			model.getObject(i).accept(calc);
		}

		Rectangle box = calc.getBoundingBox();
		BufferedImage image = new BufferedImage(box.width, box.height, BufferedImage.TYPE_3BYTE_BGR);

		Graphics2D g = image.createGraphics();
		g.translate(-box.width, -box.height);

		GeometricalObjectPainter painter = new GeometricalObjectPainter(g);

		for (int i = 0, len = model.getSize(); i < len; i++) {
			model.getObject(i).accept(painter);
		}

		g.dispose();

		System.out.println(panel.getComponentCount());
		String string = (String) (((JLabel) panel.getComponentAt(1, 1)).getText());
		System.out.println(string);

		try {
			ImageIO.write(image, (String) ((JComboBox<String>) panel.getComponentAt(1, 2)).getSelectedItem(), file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		JOptionPane.showMessageDialog(null, "Image has been exported!");
	}

	private Path getFormatAndPath() {
		Path forReturn = null;
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Choose path(extension must be png,jpg or gif");
		do {
			if (fc.showSaveDialog(null) != JFileChooser.APPROVE_OPTION) {
				return null;
			}

			forReturn = fc.getSelectedFile().toPath();
			String ext = fc.toString().substring(fc.toString().lastIndexOf(".") + 1);

			if (ext.equals("gif") || ext.equals("png") || ext.equals("jpg")) {
				break;
			}

		} while (true);

		System.out.println(forReturn.toString());
		return forReturn;
	}

	private JPanel createJPanel() {
		JPanel panel = new JPanel(new GridLayout(1, 2));

		panel.add(new JLabel("Select format: "));
		JComboBox<String> box = new JComboBox<>(new String[] { "png", "gif", "jpg" });
		panel.add(box);

		System.out.println(panel.getComponentCount());

		return panel;
	}

}
