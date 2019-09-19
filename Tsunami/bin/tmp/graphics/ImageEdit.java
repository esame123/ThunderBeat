package graphics;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImageEdit {
	private final static int TARGET_WIDTH = 150;
	private final static int TARGET_HEIGHT = 150;
	
	public static JPanel clipPhoto(String filename) {
		
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
		master = scale(master, TARGET_WIDTH, TARGET_HEIGHT);
		
		int width = master.getWidth();
		BufferedImage circleBuffer = new BufferedImage(width, width, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = circleBuffer.createGraphics();
		g2.setClip(new Ellipse2D.Float(0, 0, width, width));
		g2.drawImage(master, 0, 0, width, width, null);
		
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		JLabel label = new JLabel(toCircle(new ImageIcon(master)));
		panel.add(label);
		
		return panel;
	}
	
	
	public static BufferedImage scale(BufferedImage bufferedImage, int targetWidth, int targetHeight) {

	    int type = (bufferedImage.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
	    BufferedImage ret = bufferedImage;
	    BufferedImage scratchImage = null;
	    Graphics2D g2 = null;

	    int w = bufferedImage.getWidth();
	    int h = bufferedImage.getHeight();

	    int prevW = w;
	    int prevH = h;

	    do {
	        if (w > targetWidth) {
	            w /= 2;
	            w = (w < targetWidth) ? targetWidth : w;
	        }

	        if (h > targetHeight) {
	            h /= 2;
	            h = (h < targetHeight) ? targetHeight : h;
	        }

	        if (scratchImage == null) {
	            scratchImage = new BufferedImage(w, h, type);
	            g2 = scratchImage.createGraphics();
	        }

	        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
	                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	        g2.drawImage(ret, 0, 0, w, h, 0, 0, prevW, prevH, null);

	        prevW = w;
	        prevH = h;
	        ret = scratchImage;
	    } while (w != targetWidth || h != targetHeight);

	    if (g2 != null) {
	        g2.dispose();
	    }

	    if (targetWidth != ret.getWidth() || targetHeight != ret.getHeight()) {
	        scratchImage = new BufferedImage(targetWidth, targetHeight, type);
	        g2 = scratchImage.createGraphics();
	        g2.drawImage(ret, 0, 0, null);
	        g2.dispose();
	        ret = scratchImage;
	    }

	    return ret;

	}
	
	public static Icon toCircle(ImageIcon logo) {
	    BufferedImage image = new BufferedImage(TARGET_WIDTH, TARGET_HEIGHT + 20, BufferedImage.TYPE_INT_ARGB); // Assuming logo 150x150
	    Graphics2D g = image.createGraphics();
	    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g.fillOval(1, 1, TARGET_WIDTH - 5, TARGET_HEIGHT - 15); // Leaving some room for antialiasing if needed
	    g.setComposite(AlphaComposite.SrcIn);
	    g.drawImage(logo.getImage(), 0, -10, null);
	    g.dispose();

	    return new ImageIcon(image);
	}
	
	public static Icon Zoom(BufferedImage image, int zoomLevel) {
		int newImageWidth = image.getWidth() * zoomLevel;
		int newImageHeight = image.getHeight() * zoomLevel;
		BufferedImage resizedImage = new BufferedImage(newImageWidth , newImageHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(image, 0, 0, newImageWidth , newImageHeight , null);
		g.dispose();
		
		return new ImageIcon(resizedImage);
	}
}
