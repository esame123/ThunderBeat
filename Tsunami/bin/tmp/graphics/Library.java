package graphics;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import music.Song;
import request.InfoRequest;
import request.IntentSong;
import request.Request;
import request.SearchRequest;


public class Library extends JPanel {

	private static final long serialVersionUID = 1L;
	JPanel songPanel = new JPanel(new BorderLayout());
	/**
	 * Create the panel.
	 */
	public Library() {
		setLayout(new BorderLayout());
		JScrollPane songListScrollPanel = new JScrollPane();
		add(songListScrollPanel);
		songListScrollPanel.getVerticalScrollBar().setUnitIncrement(16);
		JPanel underPanel = new JPanel();
		underPanel.setLayout(new BoxLayout(underPanel, BoxLayout.Y_AXIS));
		
		songListScrollPanel.getViewport().setView(songPanel);
		addSong(underPanel);
		songPanel.add(underPanel);
		songListScrollPanel.repaint();
		songListScrollPanel.validate();
	}
	
	private void addSong(JPanel underPanel) {

		try {
			ArrayList<String> songDetails = new ArrayList<String>();
			Request request = new SearchRequest();
			request = new IntentSong(request);
			request.setURLLink(request.getURLLink());
			ArrayList<String> songName = request.doRequest();
			String[] args = songName.get(0).split("<br>");
			songName.clear();
			for(String string : args) {
				songName.add(string);
			}
			request = new InfoRequest();
			request = new IntentSong(request);
			
			for(String title : songName) {
				title = title.replace("\'", "\\\'");
				request.setURLLink(request.getURLLink(java.net.URLEncoder.encode(title, "UTF-8")));
				songDetails = request.doRequest();
				title = title.replace("\\\'", "\'");
				Song song = new Song(title, songDetails.get(0), songDetails.get(1), songDetails.get(2));
				underPanel.add(song.getPanel());
				underPanel.validate();
			}
			underPanel.setVisible(true);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
