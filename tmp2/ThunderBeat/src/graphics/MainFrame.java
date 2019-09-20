package graphics;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import audioplay.*;
import request.InfoRequest;
import request.IntentUser;
import request.Request;
import users.User;


@SuppressWarnings("unused")
public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel = new JPanel();
	private MainMenu homeMenu = new MainMenu();
	private SearchResultPanel searchMenu = new SearchResultPanel();
	private Library libraryMenu;
	private ArtistMenu artistMenu;
	private Play play;
	private User user;
	private JPanel contentPane;
	private AudioPlay audioPlay;
	
	/**
	 * Create the frame.
	 */
	public MainFrame(String username) {
		//Richiedi al database la foto profilo, foto panoramica, stato dato l'utente
		//this.user = new User(username, profileAddress, backgroundAddress, state);
		String profileImage = "";
		String backgroundImage = "";
		String state = "";
		profileImage = Request.getImageRequest(profileImage);
		backgroundImage = Request.getImageRequest(backgroundImage);
		
		
		libraryMenu = new Library(username);
		artistMenu = new ArtistMenu(username);
		
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setTitle("ThunderBeat");
		setVisible(true);
		setMinimumSize(new Dimension(600, 400));
		setPreferredSize(new Dimension(800, 600));

		getContentPane().setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JScrollPane RapidMenu = new JScrollPane();
		RapidMenu.setPreferredSize(new Dimension(200, 200));
		RapidMenu.setBounds(new Rectangle(0, 0, 200, 0));
		contentPane.add(RapidMenu, BorderLayout.WEST);

		JPanel RapidMenuPanel = new JPanel();
		RapidMenuPanel.setPreferredSize(new Dimension(5, 5));
		RapidMenu.setViewportView(RapidMenuPanel);
		RapidMenuPanel.setLayout(new BorderLayout(0, 0));

		JPanel profileImageLabel = new JPanel();
		profileImageLabel.add(ImageEdit.clipPhoto(profileImage));
		RapidMenuPanel.add(profileImageLabel, BorderLayout.NORTH);
		profileImageLabel.setLayout(new BoxLayout(profileImageLabel, BoxLayout.X_AXIS));

		JPanel detailPanel = new JPanel();
		RapidMenuPanel.add(detailPanel);
		detailPanel.setLayout(new GridLayout(7, 1, 0, 0));

		JLabel homeLabel = new JLabel("Home");
		homeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		detailPanel.add(homeLabel);
		mouseHovering(homeLabel);
		homeLabel.setForeground(Color.WHITE);
		homeLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		homeLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel searchLabel = new JLabel("Search");
		searchLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		detailPanel.add(searchLabel);
		mouseHovering(searchLabel);
		searchLabel.setForeground(Color.WHITE);
		searchLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		searchLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel profileLabel = new JLabel("Profile");
		profileLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		detailPanel.add(profileLabel);
		mouseHovering(profileLabel);
		profileLabel.setForeground(Color.WHITE);
		profileLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		profileLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel libraryLabel = new JLabel("Library");
		libraryLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		detailPanel.add(libraryLabel);
		mouseHovering(libraryLabel);
		libraryLabel.setForeground(Color.WHITE);
		libraryLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		libraryLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel artistLabel = new JLabel("Artist");
		artistLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		detailPanel.add(artistLabel);
		mouseHovering(artistLabel);
		artistLabel.setForeground(Color.WHITE);
		artistLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		artistLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel closeLabel = new JLabel("Close");
		closeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		detailPanel.add(closeLabel);
		mouseHovering(closeLabel);
		closeLabel.setForeground(Color.WHITE);
		closeLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		closeLabel.setHorizontalAlignment(SwingConstants.CENTER);

		contentPane.add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new CardLayout(0, 0));
		mainPanel.add(homeMenu, "homeMenu");
		pack();
		
//		JPanel songProgressBar = new JPanel();
//		songProgressBar.add(SeekBar);
//		contentPane.add(songProgressBar, BorderLayout.SOUTH);
//		mainPanel.add(searchMenu, "searchMenu");
	}

	private void mouseHovering(JLabel label) {
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {				
				JLabel sourceName = (JLabel) e.getSource();				
				mainPanel.removeAll();
				
				switch(sourceName.getText()) {
				case "Home":
					mainPanel.add(homeMenu);
					break;
				case "Search":
					mainPanel.add(searchMenu);
					break;
//				case "Profile":
//					mainPanel.add(profileMenu);
//					break;
				case "Library":
					mainPanel.add(libraryMenu);
					break;
				case "Artist":
					mainPanel.add(artistMenu);
					break;
				case "Close":
					System.exit(0);
					break;
				}
				mainPanel.repaint();
				mainPanel.validate();
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				label.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				label.setBorder(null);
			}
			
		});
	}

	private void shiftPanel(JPanel panel) {
		new Timer(1, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setLocation(0, panel.getY() - 5);
				if (panel.getY() + panel.getHeight() == 0) {
					((Timer) e.getSource()).stop();
				}
			}
		}).start();
	}
}
