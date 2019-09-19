//package graphics;
//
//import java.awt.Color;
//import java.awt.Component;
//import java.awt.Dimension;
//import java.awt.EventQueue;
//import java.awt.Font;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//
//import javax.imageio.ImageIO;
//import javax.swing.BoxLayout;
//import javax.swing.ImageIcon;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JList;
//import javax.swing.JPanel;
//import javax.swing.ListSelectionModel;
//import javax.swing.border.MatteBorder;
//
//import animation.Animation;
//import users.User;
//
//public class UserProfile {
//	private JFrame userFrame;
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					UserProfile window = new UserProfile();
//					window.userFrame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	/**
//	 * Create the application.
//	 */
//	public UserProfile() {
//		initialize();
//	}
//
//	/**
//	 * Initialize the contents of the frame.
//	 * @throws IOException 
//	 */
//	@SuppressWarnings("rawtypes")
//	private void initialize(){
//		userFrame = new JFrame();
//		userFrame.setUndecorated(true);
//		userFrame.setResizable(false);
//		userFrame.setBounds(100, 100, 900, 450);
//		userFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		userFrame.getContentPane().setLayout(null);
//
//		JLabel exitLabel = new JLabel("");
//		exitLabel.setVisible(false);
//		exitLabel.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mousePressed(MouseEvent e) {
//				System.exit(0);
//			}
//		});
//		exitLabel.setIcon(new ImageIcon(UserProfile.class.getResource("/images/icons8_Cancel_30px.png")));
//		exitLabel.setBounds(870, 0, 30, 30);
//		userFrame.getContentPane().add(exitLabel);
//
//		JPanel rapidMenuPanel = new JPanel();
//		rapidMenuPanel.setBackground(new Color(128, 128, 128));
//		rapidMenuPanel.setSize(new Dimension(210, 450));
//		rapidMenuPanel.setBorder(new MatteBorder(1, 1, 1, 0, (Color) new Color(0, 0, 0)));
//		rapidMenuPanel.setBounds(0, 0, 30, userFrame.getHeight());
//		userFrame.getContentPane().add(rapidMenuPanel);
//		rapidMenuPanel.setLayout(new BoxLayout(rapidMenuPanel, BoxLayout.Y_AXIS));
//
//		JLabel appLogo = new JLabel("");
//		appLogo.setToolTipText("");
//		rapidMenuPanel.add(appLogo);
//
//		JLabel detailLabel = new JLabel("");
//		detailLabel.setToolTipText("Expand Lateral Bar");
//		detailLabel.setIcon(new ImageIcon(UserProfile.class.getResource("/images/Editing-Line-Width-icon.png")));
//		rapidMenuPanel.add(detailLabel);
//
//		JLabel searchLogo = new JLabel("");
//		searchLogo.setToolTipText("Search for songs, artists, albums or other users");
//
//		searchLogo.setIcon(new ImageIcon(UserProfile.class.getResource("/images/search-icon.png")));
//		rapidMenuPanel.add(searchLogo);
//
//		JLabel userLogo = new JLabel("");
//		userLogo.setToolTipText("User Profile");
//		userLogo.setIcon(new ImageIcon(UserProfile.class.getResource("/images/Person-Male-Light-icon.png")));
//		rapidMenuPanel.add(userLogo);
//
//		JLabel musicLogo = new JLabel("");
//		musicLogo.setToolTipText("Music");
//		musicLogo.setIcon(new ImageIcon(UserProfile.class.getResource("/images/music-icon32.png")));
//		rapidMenuPanel.add(musicLogo);
//
//		JLabel playlistLogo = new JLabel("");
//		playlistLogo.setAlignmentY(Component.BOTTOM_ALIGNMENT);
//		playlistLogo.setIcon(new ImageIcon(UserProfile.class.getResource("/images/playlist-icon.png")));
//		rapidMenuPanel.add(playlistLogo);
//		
//		JPanel userPhotoPanel = new JPanel();
//		userPhotoPanel.setOpaque(false);
//		userPhotoPanel.setBounds(50, 65, 150, 150);
//		userFrame.getContentPane().add(userPhotoPanel);
//		userPhotoPanel.setLayout(null);
//		
//		JPanel photoPanel = new JPanel();
//		photoPanel.setOpaque(false);
//		photoPanel.setBounds(0, 0, 150, 150);
//		photoPanel.add(ImageEdit.clipPhoto("C:\\Users\\Amrit\\Pictures\\Progetto\\shutter.jpg"));
//		userPhotoPanel.add(photoPanel);
//		
//
//		JPanel upperPanel = new JPanel();
//		upperPanel.setBackground(new Color(248, 248, 255));
//		upperPanel.setSize(870, 150);
//		upperPanel.setBorder(new MatteBorder(1, 1, 0, 1, (Color) new Color(0, 0, 0)));
//		upperPanel.setLocation(30, 0);
//		userFrame.getContentPane().add(upperPanel);
//		upperPanel.setLayout(null);
//
//		JLabel backgroundImage = new JLabel("");
//		backgroundImage.setBorder(new MatteBorder(0, 1, 0, 0, (Color) new Color(0, 0, 0)));
//		backgroundImage.setBounds(0, 0, 870, 150);
//		//backgroundImage.setIcon(new ImageIcon(UserProfile.class.getResource("/images/rainbow-music-background-means-melody-singing-and-soundwaves-.jpg")));
//		BufferedImage image = null;
//		try {
//			image = ImageIO.read(new File("C:\\Users\\Amrit\\Pictures\\Progetto\\rainbow-music-background-means-melody-singing-and-soundwaves-.jpg"));
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//		backgroundImage.setIcon(new ImageIcon(ImageEdit.scale(image, 870, 150)));
//		upperPanel.add(backgroundImage);
//
//		JPanel detailPanel = new JPanel();
//		detailPanel.setBorder(new MatteBorder(0, 1, 1, 0, (Color) new Color(0, 0, 0)));
//		detailPanel.setBackground(new Color(245, 245, 245));
//		detailPanel.setBounds(30, 150, 210, 300);
//		userFrame.getContentPane().add(detailPanel);
//		detailPanel.setLayout(null);
//
//		JPanel followerPanel = new JPanel();
//		followerPanel.setBackground(new Color(245, 245, 245));
//		followerPanel.setBounds(10, 85, 100, 15);
//		detailPanel.add(followerPanel);
//		followerPanel.setLayout(null);
//
//		JLabel followerLabel = new JLabel("Follower");
//		followerLabel.setBackground(new Color(248, 248, 255));
//		followerLabel.setBounds(0, 0, 100, 15);
//		followerLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
//		followerPanel.add(followerLabel);
//
//		JPanel followingPanel = new JPanel();
//		followingPanel.setBackground(new Color(245, 245, 245));
//		followingPanel.setBounds(110, 85, 100, 15);
//		detailPanel.add(followingPanel);
//		followingPanel.setLayout(null);
//
//		JLabel followingLabel = new JLabel("Following");
//		followingLabel.setBackground(new Color(248, 248, 255));
//		followingLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
//		followingLabel.setBounds(0, 0, 100, 15);
//		followingPanel.add(followingLabel);
//
//		JPanel followerNumberPanel = new JPanel();
//		followerNumberPanel.setBackground(new Color(245, 245, 245));
//		followerNumberPanel.setBounds(10, 100, 100, 15);
//		detailPanel.add(followerNumberPanel);
//		followerNumberPanel.setLayout(null);
//
//		JLabel followerNumber = new JLabel("");
//		followerNumber.setBackground(new Color(248, 248, 255));
//		followerNumber.setFont(new Font("Tahoma", Font.BOLD, 12));
//		followerNumber.setBounds(0, 0, 100, 15);
//		followerNumber.setText(user.getNumberOfFollowers() + "");
//		followerNumberPanel.add(followerNumber);
//
//		JPanel followingNumberPanel = new JPanel();
//		followingNumberPanel.setBackground(new Color(245, 245, 245));
//		followingNumberPanel.setBounds(110, 100, 100, 15);
//		followingNumberPanel.setLayout(null);
//
//		JLabel followingNumber = new JLabel("");
//		followingNumber.setBackground(new Color(248, 248, 255));
//		followingNumber.setFont(new Font("Tahoma", Font.BOLD, 12));
//		followingNumber.setBounds(0, 0, 100, 15);
//		followingNumber.setText(user.getNumberOfFollowing() + "");
//		followingNumberPanel.add(followingNumber);
//		detailPanel.add(followingNumberPanel);
//
//		JPanel publishedAlbumLabel = new JPanel();
//		publishedAlbumLabel.setBackground(new Color(245, 245, 245));
//		publishedAlbumLabel.setBounds(10, 125, 200, 15);
//		detailPanel.add(publishedAlbumLabel);
//		publishedAlbumLabel.setLayout(null);
//
//		JLabel publishedAlbum = new JLabel("");
//		publishedAlbum.setBackground(new Color(248, 248, 255));
//		publishedAlbum.setText("Albums  " + user.getPublishedAlbum());
//		publishedAlbum.setFont(new Font("Tahoma", Font.BOLD, 14));
//		publishedAlbum.setBounds(0, 0, 200, 15);
//		publishedAlbumLabel.add(publishedAlbum);
//
//		JPanel publishedPlaylistPanel = new JPanel();
//		publishedPlaylistPanel.setBackground(new Color(245, 245, 245));
//		publishedPlaylistPanel.setBounds(10, 140, 200, 15);
//		detailPanel.add(publishedPlaylistPanel);
//		publishedPlaylistPanel.setLayout(null);
//
//		JLabel publishedPlaylist = new JLabel("");
//		publishedPlaylist.setBackground(new Color(248, 248, 255));
//		publishedPlaylist.setText("Playlists  " + user.getPublishedPlaylists());
//		publishedPlaylist.setFont(new Font("Tahoma", Font.BOLD, 14));
//		publishedPlaylist.setBounds(0, 0, 200, 15);
//		publishedPlaylistPanel.add(publishedPlaylist);
//
//		JPanel titlePanel = new JPanel();
//		titlePanel.setBorder(new MatteBorder(0, 0, 0, 1, (Color) new Color(0, 0, 0)));
//		titlePanel.setBackground(Color.WHITE);
//		titlePanel.setBounds(240, 150, 660, 75);
//		userFrame.getContentPane().add(titlePanel);
//		titlePanel.setLayout(null);
//
//		JPanel namePanel = new JPanel();
//		namePanel.setBackground(Color.WHITE);
//		namePanel.setBounds(30, 10, 150, 45);
//		titlePanel.add(namePanel);
//		namePanel.setLayout(null);
//
//		JLabel usernameText = new JLabel("");
//		usernameText.setBounds(5, 5, 122, 37);
//		usernameText.setFont(new Font("Tahoma", Font.PLAIN, 30));
//		usernameText.setBackground(Color.BLACK);
//		usernameText.setText(user.getUsername());
//		namePanel.add(usernameText);
//
//		JPanel historyPanel = new JPanel();
//		historyPanel.setBackground(new Color(245, 245, 245));
//		historyPanel.setBorder(new MatteBorder(0, 0, 1, 1, (Color) new Color(0, 0, 0)));
//		historyPanel.setBounds(240, 225, 660, 225);
//		userFrame.getContentPane().add(historyPanel);
//		historyPanel.setLayout(new BoxLayout(historyPanel, BoxLayout.X_AXIS));
//		
//		JList historyList = new JList();
//		historyList.setPreferredSize(new Dimension(660, 200));
//		historyList.setVisibleRowCount(3);
//		historyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		historyList.setFixedCellWidth(660);
//		historyList.setFixedCellHeight(200);
//		historyList.setBackground(new Color(245, 245, 245));
//		historyPanel.add(historyList);
//
//		detailLabel.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mousePressed(MouseEvent e) {
//				if(rapidMenuPanel.getWidth() == 30) {
//					Animation.moveRightLeftStill(rapidMenuPanel, 210);
//					detailLabel.setToolTipText("Reduce Lateral Bar");
//				}
//				else {
//					Animation.moveLeftLeftStill(rapidMenuPanel, 30);
//					detailLabel.setToolTipText("Expand Lateral Bar");
//				}
//			}
//		});
//		
//		
//	}
//}
