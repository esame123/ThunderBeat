package graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;

import javax.swing.JPanel;

public class Circle extends JPanel{

	private static final long serialVersionUID = 1L;
	private static final Color ELLIPSE_COLOR = Color.BLACK;
	private static final Color ELLIPSE_FILL_COLOR = Color.BLACK;
	private static final int PREF_W = 200;
	private static final int PREF_H = 100;
	private static final Stroke ELLIPSE_STROKE = new BasicStroke(2f);
	Shape shape = null;


	public void paintComponent(Graphics g) {
		super.setBackground(Color.BLACK);
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		try {
			// to draw smooth edges
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setStroke(ELLIPSE_STROKE);
			g2.setColor(ELLIPSE_FILL_COLOR);
			g2.setColor(ELLIPSE_COLOR);
			g2.draw(shape);
		}catch(NullPointerException e) {

		}
//		BufferedImage bufferedImage = null;
//		try {
//			bufferedImage = ImageIO.read(new File("C:\\Users\\Amrit\\Pictures\\Progetto\\casey-horner-4rDCa5hBlCs-unsplash.jpg"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		int width = bufferedImage.getWidth();
//		BufferedImage circleBuffer = new BufferedImage(width, width, BufferedImage.TYPE_INT_ARGB);
//		Graphics2D g2 = circleBuffer.createGraphics();
//		g2.setClip(new Ellipse2D.Float(0, 0, width, width));
//		g2.drawImage(bufferedImage, 0, 0, width, width, null);
	}

	public Dimension getPreferredSize() {
		return new Dimension(PREF_W, PREF_H);
	}
}