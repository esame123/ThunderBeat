package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

public class MainMenu extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JList<JPanel> popular = new JList<JPanel>();
	JList<JPanel> interest = new JList<JPanel>();
	JList<JPanel> playlist = new JList<JPanel>();
	JTextField searchTextField;


	/**
	 * Create the panel.
	 */
	public MainMenu(/*BufferedImage image, BufferedImage profileImage*/) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		setLayout(new BorderLayout(0, 0));

		JPanel lowerPanel = new JPanel();
		add(lowerPanel);
		lowerPanel.setLayout(new BoxLayout(lowerPanel, BoxLayout.Y_AXIS));

		JPanel popularSongPanel = new JPanel();
		popularSongPanel.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
		lowerPanel.add(popularSongPanel);
		popularSongPanel.setLayout(new BorderLayout(0, 0));

		JLabel popularSectionName = new JLabel("Pi\u00F9 Ascoltate");
		popularSectionName.setFont(new Font("Tahoma", Font.BOLD, 16));
		popularSectionName.setBorder(new EmptyBorder(2, 5, 0, 0));
		popularSongPanel.add(popularSectionName, BorderLayout.NORTH);

		JList<JPanel> songList = new JList<JPanel>();
		songList.setBorder(new MatteBorder(2, 0, 0, 0, new Color(128, 128, 128)));
		songList.setBackground(UIManager.getColor("Button.background"));
		popularSongPanel.add(songList, BorderLayout.CENTER);
		songList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		songList.setVisibleRowCount(5);
		songList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JLabel previousPopular = new JLabel("");
		previousPopular.setBorder(new MatteBorder(2, 0, 0, 0, Color.GRAY));
		previousPopular.setIcon(new ImageIcon(MainMenu.class.getResource("/images/Actions-go-previous-icon.png")));
		popularSongPanel.add(previousPopular, BorderLayout.WEST);

		JLabel nextPopular = new JLabel("");
		nextPopular.setBorder(new MatteBorder(2, 0, 0, 0, Color.GRAY));
		nextPopular.setIcon(new ImageIcon(MainMenu.class.getResource("/images/Actions-go-next-icon.png")));
		popularSongPanel.add(nextPopular, BorderLayout.EAST);

		JPanel interestPanel = new JPanel();
		interestPanel.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
		lowerPanel.add(interestPanel);
		interestPanel.setLayout(new BorderLayout(0, 0));

		JLabel interestLabel = new JLabel("Interessi");
		interestLabel.setBorder(new EmptyBorder(2, 5, 0, 0));
		interestLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		interestPanel.add(interestLabel, BorderLayout.NORTH);

		JLabel previousInterest = new JLabel("");
		previousInterest.setBorder(new MatteBorder(2, 0, 0, 0, Color.GRAY));
		previousInterest.setIcon(new ImageIcon(MainMenu.class.getResource("/images/Actions-go-previous-icon.png")));
		interestPanel.add(previousInterest, BorderLayout.WEST);

		JList<JPanel> interestList = new JList<JPanel>();
		interestList.setBorder(new MatteBorder(2, 0, 0, 0, Color.GRAY));
		interestList.setBackground(UIManager.getColor("Button.background"));
		interestPanel.add(interestList, BorderLayout.CENTER);
		interestList.setVisibleRowCount(5);
		interestList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		interestList.setLayoutOrientation(JList.HORIZONTAL_WRAP);

		JLabel nextInterest = new JLabel();
		nextInterest.setBorder(new MatteBorder(2, 0, 0, 0, Color.GRAY));
		nextInterest.setIcon(new ImageIcon(MainMenu.class.getResource("/images/Actions-go-next-icon.png")));
		interestPanel.add(nextInterest, BorderLayout.EAST);

		JPanel playlistPanel = new JPanel();
		playlistPanel.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
		lowerPanel.add(playlistPanel);
		playlistPanel.setLayout(new BorderLayout(0, 0));

		JLabel playlistLabel = new JLabel("Playlist");
		playlistLabel.setBorder(new EmptyBorder(2, 5, 0, 0));
		playlistLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		playlistPanel.add(playlistLabel, BorderLayout.NORTH);

		JLabel previousPlaylist = new JLabel();
		previousPlaylist.setBorder(new MatteBorder(2, 0, 0, 0, Color.GRAY));
		previousPlaylist.setIcon(new ImageIcon(MainMenu.class.getResource("/images/Actions-go-previous-icon.png")));
		playlistPanel.add(previousPlaylist, BorderLayout.WEST);

		JList<JPanel> playlistList = new JList<JPanel>();
		playlistList.setBorder(new MatteBorder(2, 0, 0, 0, Color.GRAY));
		playlistList.setBackground(UIManager.getColor("Button.background"));
		playlistPanel.add(playlistList, BorderLayout.CENTER);
		playlistList.setVisibleRowCount(5);
		playlistList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		playlistList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JLabel nextPlaylist = new JLabel();
		nextPlaylist.setBorder(new MatteBorder(2, 0, 0, 0, Color.GRAY));
		nextPlaylist.setIcon(new ImageIcon(MainMenu.class.getResource("/images/Actions-go-next-icon.png")));
		playlistPanel.add(nextPlaylist, BorderLayout.EAST);

	}

}
