package hr.fer.zemris.java.hw16.jvdraw.menuactions;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import hr.fer.zemris.java.hw16.jvdraw.drawing.interfaces.DrawingModel;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.visitors.GeometricalObjectBBCalculator;
import hr.fer.zemris.java.hw16.jvdraw.graphicalobject.visitors.GeometricalObjectPainter;

/**
 * Class provides implementation for exporting process into image.Supported
 * image formats are: <br>
 * <ol>
 * <li>png</li>
 * <li>jpg</li>
 * <li>gif</li>
 * </ol>
 * 
 * @author Mihael
 *
 */
public class Export extends AbstractAction {

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
	public Export(DrawingModel model) {
		this.model = model;
	}

	/**
	 * Method represents implementation of exporting process
	 * 
	 * @param event
	 *            - action event
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		Path path = getFormatAndPath();

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

		try {
			ImageIO.write(image, getFormat(path), path.toFile());
		} catch (IOException e) {
			e.printStackTrace();
		}

		JOptionPane.showMessageDialog(null, "Image has been exported!");
	}

	/**
	 * Method returns image type from path
	 * 
	 * @param path
	 *            - path
	 * @return image format
	 */
	private String getFormat(Path path) {
		if (path.endsWith(".jpg")) {
			return "jpg";
		}

		if (path.endsWith(".gif")) {
			return "gif";
		}

		return "png";
	}

	/**
	 * Method returns path where we want to store image
	 * 
	 * @return path to location
	 */
	private Path getFormatAndPath() {

		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(new FileFilter() {

			@Override
			public String getDescription() {
				return "gif,jpg,png";
			}

			@Override
			public boolean accept(File arg0) {
				String fileName = arg0.toString().toLowerCase();

				if (fileName.endsWith(".jpg") || fileName.endsWith(".gif") || fileName.endsWith(".png")) {
					return true;
				}

				return false;

			}
		});

		fc.setDialogTitle("Choose path");
		if (fc.showSaveDialog(null) != JFileChooser.APPROVE_OPTION) {
			return null;
		}

		File file = fc.getSelectedFile();
		Path path = file.toPath();

		return path;
	}

}
