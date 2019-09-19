package graphics;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import music.Song;

public class DetailPanel extends JPanel{

	private static final long serialVersionUID = 1L;

	public DetailPanel(Song song, JPanel songPanel) {

		setLayout(new BoxLayout(songPanel, BoxLayout.Y_AXIS));
		
		JLabel songLabel = new JLabel(song.getTitle());
		JLabel artistLabel = new JLabel(song.getArtist());
		JLabel songDuration = new JLabel(song.getLength()); 

		songPanel.add(songLabel);
		songPanel.add(artistLabel);

		this.add(songPanel, BorderLayout.WEST);
		this.add(songDuration, BorderLayout.EAST);

	}
}
