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
import javax.swing.JScrollPane;

public class Prova extends JPanel {

	private static final long serialVersionUID = 1L;

	JList<JPanel> popular = new JList<JPanel>();
	JList<JPanel> interest = new JList<JPanel>();
	JList<JPanel> playlist = new JList<JPanel>();
	JTextField searchTextField;

	/**
	 * Create the panel.
	 */
	public Prova() {

		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		setLayout(new BorderLayout(0, 0));
		
		JPanel upperPanel = new JPanel();
		add(upperPanel, BorderLayout.NORTH);
		upperPanel.setLayout(new BorderLayout(0, 0));

		JPanel lowerPanel = new JPanel();
		add(lowerPanel);
		lowerPanel.setLayout(new BoxLayout(lowerPanel, BoxLayout.Y_AXIS));

		JPanel popularSongPanel = new JPanel();
		popularSongPanel.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
		lowerPanel.add(popularSongPanel);
		popularSongPanel.setLayout(new BorderLayout(0, 0));

		JLabel popularSectionName = new JLabel("AAAAAAAAAAAAAAA");
		popularSectionName.setFont(new Font("Tahoma", Font.BOLD, 16));
		popularSectionName.setBorder(new EmptyBorder(2, 5, 0, 0));
		popularSongPanel.add(popularSectionName, BorderLayout.NORTH);

		JPanel recentPanel = new JPanel();
		recentPanel.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
		lowerPanel.add(recentPanel);
		recentPanel.setLayout(new BorderLayout(0, 0));

		JLabel interestLabel = new JLabel("Recenti");
		interestLabel.setBorder(new EmptyBorder(2, 5, 0, 0));
		interestLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		recentPanel.add(interestLabel, BorderLayout.NORTH);
		
		JPanel topRecentSong = new JPanel();
		recentPanel.add(topRecentSong, BorderLayout.CENTER);

		JPanel playlistPanel = new JPanel();
		playlistPanel.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
		lowerPanel.add(playlistPanel);
		playlistPanel.setLayout(new BorderLayout(0, 0));

		JLabel playlistLabel = new JLabel("Playlist");
		playlistLabel.setBorder(new EmptyBorder(2, 5, 0, 0));
		playlistLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		playlistPanel.add(playlistLabel, BorderLayout.NORTH);
		
		JPanel topPlaylist = new JPanel();
		playlistPanel.add(topPlaylist, BorderLayout.CENTER);

	}
}
