package graphics;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import music.Artist;
import request.InfoRequest;
import request.IntentArtist;
import request.IntentSong;
import request.Request;
import request.SearchRequest;


public class ArtistMenu extends JPanel {

	private static final long serialVersionUID = 1L;
	JPanel artistPanel = new JPanel(new BorderLayout());
	/**
	 * Create the panel.
	 */
	public ArtistMenu() {
		setLayout(new BorderLayout());
		JScrollPane songListScrollPanel = new JScrollPane();
		add(songListScrollPanel);
		songListScrollPanel.getVerticalScrollBar().setUnitIncrement(16);
		JPanel underPanel = new JPanel();
		underPanel.setLayout(new BoxLayout(underPanel, BoxLayout.Y_AXIS));
		
		songListScrollPanel.getViewport().setView(artistPanel);
		addSong(underPanel);
		artistPanel.add(underPanel);
		songListScrollPanel.repaint();
		songListScrollPanel.validate();
	}
	
	private void addSong(JPanel underPanel) {

		try {
			Request request = new SearchRequest();
			request = new IntentArtist(request);
			request.setURLLink(request.getURLLink());
			ArrayList<String> songName = request.doRequest();
			String[] args = songName.get(0).split("<br>");
			songName.clear();
			for(String string : args) {
				songName.add(string);
			}
			request = new InfoRequest();
			request = new IntentSong(request);
			
			for(String name : songName) {
				name = name.replace("\'", "\\\'");
				request.setURLLink(request.getURLLink(java.net.URLEncoder.encode(name, "UTF-8")));
				name = name.replace("\\\'", "\'");
				Artist artist = new Artist(name);
				underPanel.add(artist.getPanel());
				underPanel.validate();
			}
			underPanel.setVisible(true);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
