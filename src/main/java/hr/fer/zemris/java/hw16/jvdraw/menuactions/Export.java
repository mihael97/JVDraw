package hr.fer.zemris.java.hw16.jvdraw.menuactions;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;

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

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JPanel panel = createJPanel();

		if (JOptionPane.showConfirmDialog(null, panel, "Choose format:",
				JOptionPane.OK_CANCEL_OPTION) == JOptionPane.CANCEL_OPTION) {
			return;
		}

		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Choose path:");

		if (fc.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
			return;
		}

		File file = fc.getSelectedFile();
		
		//bounding box
		GeometricalObjectBBCalculator calc=new GeometricalObjectBBCalculator();
		
		for(int i=0,len=model.getSize();i<len;i++) {
			model.getObject(i).accept(calc);
		}
		
		Rectangle box=calc.getBoundingBox();
		BufferedImage image=new BufferedImage(box.width, box.height, BufferedImage.TYPE_3BYTE_BGR);
		
		Graphics2D g=image.createGraphics();
		g.translate(-box.width, -box.height);
		
		GeometricalObjectPainter painter=new GeometricalObjectPainter(g);
		
		for(int i=0,len=model.getSize();i<len;i++) {
			model.getObject(i).accept(painter);
		}
		
		g.dispose();
		
		ImageIO.write(image,((JComboBox<String>)panel.getComponent(2)).getSelectedItem() ,file)
		
	}

	private JPanel createJPanel() {
		JPanel panel = new JPanel();

		panel.add(new JLabel("Select format: "));
		JComboBox<String> box = new JComboBox<>();

		box.add(new JLabel("png"));
		box.add(new JLabel("jpg"));
		box.add(new JLabel("gif"));

		panel.add(box);

		return panel;
	}

}
