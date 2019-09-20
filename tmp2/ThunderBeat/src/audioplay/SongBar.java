package audioplay;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class SongBar extends JPanel {
	
	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	public SongBar() {
		setLayout(new BorderLayout(0, 0));
		
		JLabel songname = new JLabel("");
		add(songname, BorderLayout.NORTH);
		
		JPanel progressBar = new JPanel();
		add(progressBar, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel buttons = new JPanel();
		buttonPanel.add(buttons, BorderLayout.CENTER);
		
		JButton previousButton = new JButton("");
		previousButton.setOpaque(false);
		previousButton.setIcon(new ImageIcon(SongBar.class.getResource("/images/first-track-icon.png")));
		buttons.add(previousButton);
		
		JButton playPauseButton = new JButton("");
		playPauseButton.setIcon(new ImageIcon(SongBar.class.getResource("/images/play-icon.png")));
		buttons.add(playPauseButton);
		
		JButton nextButton = new JButton("");
		nextButton.setIcon(new ImageIcon(SongBar.class.getResource("/images/last-track-icon.png")));
		buttons.add(nextButton);
		
	}

}
