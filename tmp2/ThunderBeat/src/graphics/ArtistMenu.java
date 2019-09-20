package graphics;

import java.awt.BorderLayout;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import music.Artist;
import request.IntentUser;
import request.LibraryRequest;
import request.Request;


public class ArtistMenu extends JPanel {

	private static final long serialVersionUID = 1L;
	JPanel artistPanel = new JPanel(new BorderLayout());
	/**
	 * Create the panel.
	 */
	public ArtistMenu(String username) {
		setLayout(new BorderLayout());
		JScrollPane songListScrollPanel = new JScrollPane();
		add(songListScrollPanel);
		songListScrollPanel.getVerticalScrollBar().setUnitIncrement(16);
		JPanel underPanel = new JPanel();
		underPanel.setLayout(new BoxLayout(underPanel, BoxLayout.Y_AXIS));
		
		songListScrollPanel.getViewport().setView(artistPanel);
		addArtist(underPanel, username);
		artistPanel.add(underPanel, BorderLayout.NORTH);
		songListScrollPanel.repaint();
		songListScrollPanel.validate();
	}
	
	private void addArtist(JPanel underPanel, String username) {
		ArrayList<String> artistDetails = new ArrayList<String>();
		try {
			Request request = new LibraryRequest();
			request = new IntentUser(request);
			request.setURLLink(request.getURLLink(java.net.URLEncoder.encode(username, "UTF-8")));
			
			artistDetails = request.doRequestArtist();
			
			if(artistDetails == null) {
				JPanel noArtistPanel = new JPanel();
				noArtistPanel.add(new JLabel("Nessun Artista!!!   "));
				noArtistPanel.getComponent(0).setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
				underPanel.add(noArtistPanel);
				underPanel.setEnabled(false);
				return;
			}
			
			for(String name : artistDetails) {
				Artist artist = new Artist(name);
				underPanel.add(artist.getPanel());
			}			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
