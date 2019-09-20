package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.MatteBorder;

public class ImageCrop {

	public JFrame frame;
	protected Circle circlePanel = new Circle();
	public static final int ELLIPSE_WIDTH = 200;
	public static final int ELLIPSE_HEIGHT = 200;
	private Boolean mouseClick = true;
	private String file;
	private JScrollPane scrollPanel_1;
	
	/**
	 * Create the application.
	 */
	public ImageCrop(String filename) {
		this.file = filename;
		initialize(file);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("unused")
	private void initialize(String filename) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		frame = new JFrame();
		frame.setResizable(false);
		frame.setAlwaysOnTop(true);
		frame.setType(Type.UTILITY);
		
		frame.setLocationByPlatform(true);
		frame.setPreferredSize(new Dimension(800, 600));
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setMinimumSize(new Dimension(400, 400));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBackground(SystemColor.desktop);
		frame.setVisible(true);
		JScrollPane scrollPanel = null;
		JLabel imageLabel = null;

		BufferedImage image = null;
		circlePanel.setLocation(0, 0);
		
		circlePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(mouseClick) {
					mouseClick = false;
				}else {
					mouseClick = true;
				}
			}
		});


		JPanel overPanel = new JPanel();
		overPanel.setLocation(0, 0);
		overPanel.setOpaque(false);
		
		circlePanel.setOpaque(true);
		circlePanel.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent mEvt) {
				if(mouseClick) {
					double x = mEvt.getX() - ELLIPSE_WIDTH / 2;
					double y = mEvt.getY() - ELLIPSE_HEIGHT / 2;
					double w = ELLIPSE_WIDTH;
					double h = ELLIPSE_HEIGHT;
					circlePanel.shape = new Ellipse2D.Double(x, y, w, h);
					circlePanel.repaint();
				}
			}
		});
		frame.getContentPane().setLayout(null);
		
		overPanel.setLayout(null);
		frame.getContentPane().add(overPanel);

		try {
			image = ImageIO.read(new File(filename));
			imageLabel = new JLabel(new ImageIcon(image));
			imageLabel.setAutoscrolls(true);
			imageLabel.setBackground(SystemColor.desktop);

			scrollPanel_1 = new JScrollPane(
					imageLabel);
			scrollPanel_1.setBounds(0, 0, 794, 536);
			scrollPanel_1.setBorder(new MatteBorder(0, 1, 0, 1, (Color) new Color(0, 0, 0)));
			scrollPanel_1.setBackground(Color.WHITE);
			scrollPanel_1.setAutoscrolls(true);
			scrollPanel_1.getVerticalScrollBar().setUnitIncrement(16);
			scrollPanel_1.getHorizontalScrollBar().setUnitIncrement(16);
			circlePanel.setSize(200, 200);

		} catch (IOException e) {

		}
		frame.getContentPane().add(scrollPanel_1);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBounds(0, 537, 794, 34);
		bottomPanel.setBorder(new MatteBorder(0, 1, 1, 1, (Color) new Color(0, 0, 0)));
		frame.getContentPane().add(bottomPanel);
		bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				circlePanel.setLocation(circlePanel.getX(), circlePanel.getY());
				frame.dispose();
			}
		});
		bottomPanel.add(okButton);
		
		overPanel.setSize(new Dimension(794, 537));
		overPanel.add(circlePanel);
		frame.pack();
	}
	
	public JPanel getCirclePanel() {
		JPanel tmpPanel = new JPanel();
		tmpPanel = RoundAnImage.RoundImage(file, circlePanel);
		return tmpPanel;
	}
}
