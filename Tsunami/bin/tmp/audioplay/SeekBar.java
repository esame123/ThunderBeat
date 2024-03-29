package audioplay;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.awt.*;
import javax.sound.sampled.Clip;

@SuppressWarnings("unused")
public class SeekBar extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private int xInset = 1;
	private int yInset = 1;
	private Color seekBarColor = new Color(46, 204, 113);
	private double xPercent = 0;
	private boolean releasedFlag = true;
	private Rectangle scrollArea;
	private double value = 100;
	private Image bg;
	public Clip audio;
	
	public SeekBar(Clip song) {
		setPreferredSize(new Dimension(300,10));
		scrollArea = calculateScrollArea();
		audio = song;
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				checkConsistentScrollArea();
				updateXPercent(e.getX());
				releasedFlag = true;
			}
			@Override
			public void mousePressed(MouseEvent e) {
				checkConsistentScrollArea();
				updateXPercent(e.getX());
				releasedFlag = false;
			}
		});
		
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				checkConsistentScrollArea();
				updateXPercent(e.getX());
				releasedFlag = false;
			}
		});
		
		setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.darkGray));
	}
	
	@Override
	public void paintComponent(Graphics arg0) {
		checkConsistentScrollArea();
		//make sure back buffer is acceptable
		if(bg == null || bg.getWidth(this) != getWidth() || bg.getHeight(this) != getHeight()) {
			bg = createImage(getWidth(), getHeight());
		}
		
		Graphics2D g = (Graphics2D) bg.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		//paint the background
		g.setColor(getBackground());
		g.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
		//paint the seek bar
		g.setColor(getSeekBarColor());
		g.fill(new Rectangle2D.Double(scrollArea.x, scrollArea.y, scrollArea.width*(xPercent/100.0), scrollArea.height));
		
		arg0.drawImage(bg, 0, 0, null);
		g.dispose();
	}
	
	public void checkConsistentScrollArea() {
		if(scrollArea == null || scrollArea.width != getWidth() - xInset*2 || scrollArea.height != getHeight() - yInset*2) {
			scrollArea = calculateScrollArea();
		}
	}
	
	public void updateXPercent(double x) {
		if(x - xInset > scrollArea.width) {
			x = scrollArea.width + xInset;
		} else if(x - xInset < xInset) {
			x = xInset;
		}
		
		xPercent = (((double)(x-scrollArea.x)) / ((double)scrollArea.width) * 100.0);
		audio.setMicrosecondPosition((int)(xPercent/100.0 * audio.getMicrosecondLength()));
		repaint();
	}
	
	public void updateX(double x) {
		xPercent = x;
		repaint();
	}
	
	public Rectangle calculateScrollArea() {
		return new Rectangle(xInset, yInset, getWidth() - xInset*2, getHeight() - yInset*2);
	}
	
	public Color getSeekBarColor() {
		return seekBarColor;
	}

	public void setSong(Clip song) {
		audio = song;
	}
}