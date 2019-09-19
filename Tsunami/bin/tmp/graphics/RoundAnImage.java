package graphics;

import javax.imageio.*;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.image.*;
import java.io.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;


public class RoundAnImage{

	public static JPanel RoundImage(String filename, JPanel frame){

		if(!filename.endsWith(".jpg")) {
			System.out.println("Errore: il file inserito non è un immagine");
		}

		BufferedImage master = null;
		try {
			master = ImageIO.read(new File(filename));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		int diameter = Math.min(master.getWidth(), master.getHeight());
//		BufferedImage mask = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
//
//		Graphics2D g2d = mask.createGraphics();
//		applyQualityRenderingHints(g2d);
//		g2d.fillOval(40, 75, 195, 195);
//		g2d.dispose();
//
//		BufferedImage masked = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
//		g2d = masked.createGraphics();
//		applyQualityRenderingHints(g2d);
//		int x = (diameter - master.getWidth()) / 2;
//		int y = (diameter - master.getHeight()) / 2;
//		g2d.drawImage(master, x, y, null);
//		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_IN));
//		g2d.drawImage(mask, 0, 0, null);
//		g2d.dispose();
		
		int width = master.getWidth();
		BufferedImage circleBuffer = new BufferedImage(width, width, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = circleBuffer.createGraphics();
		g2.setClip(new Ellipse2D.Float(0, 0, width, width));
		g2.drawImage(master, 0, 0, width, width, null);
		
		//JOptionPane.showMessageDialog(null, new JLabel(new ImageIcon(masked)));
		JLabel label = new JLabel(new ImageIcon(master));
		frame.add(label);
		frame.setOpaque(false);
		return frame;

	}

	@SuppressWarnings("unused")
	private static void applyQualityRenderingHints(final Graphics2D g2d) {

		g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
		g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

	}
}
