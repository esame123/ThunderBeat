package music;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import request.IntentSong;
import request.Request;
import request.InfoRequest;

public class Song {
	private String title;
	private String artist;
	private String genre;
	private String length;
	private int likes;

	public Song(String title, String artist, String genre, String length) {
		this.title = title;
		this.artist = artist;
		this.genre = genre;
		this.length = length;
		this.likes = 0;
	}
	
	public Song(String title, String artist, String length) {
		this.title = title;
		this.artist = artist;
		this.length = length;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public String getTitle() {
		return title;
	}

	public String getArtist() {
		return artist;
	}

	public String getLength() {
		return length;
	}

	public String getGenre() {
		return genre;
	}	

	public void setTitle(String title) {
		this.title = title;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public void setLength(String length) {
		this.length = length;
	}

	/**
	 * Creazione di un panello generico con nome della canzone, artista e album a sinistra e 
	 * durata della canzone a destra del pannello
	 * @return songPanel
	 */
//	public JPanel getPanel() {
//		JPanel songPanel = new JPanel();
//		
//		songPanel.setLayout(new BorderLayout());
//		songPanel.setPreferredSize(new Dimension(200, 75));
//
//		JLabel songName = new JLabel(this.title);
//		songName.setFont(new Font("Tahoma", Font.BOLD, 14));
//
//		JLabel artistName = new JLabel(this.artist);
//		artistName.setFont(new Font("Tahoma", Font.BOLD, 12));
//
//		JLabel songLength = new JLabel(this.length);
//		songLength.setFont(new Font("Tahoma", Font.BOLD, 14));
//		songLength.setBorder(new EmptyBorder(0, 0, 0, 5));
//
//		JPanel songDetail = new JPanel(new BorderLayout());
//		songDetail.setBorder(new EmptyBorder(3, 3, 3, 0));
//		JPanel artistDetail = new JPanel(new BorderLayout());
//
//		artistDetail.add(artistName, BorderLayout.CENTER);
//		JLabel songLabel = new JLabel("Song • ");
//		artistDetail.add(songLabel, BorderLayout.WEST);
//		artistDetail.getComponent(1).setFont(new Font("Papyrus", Font.ITALIC | Font.BOLD, 14));
//
//		songDetail.add(songName, BorderLayout.NORTH);
//		songDetail.add(artistDetail, BorderLayout.SOUTH);
//
//		songPanel.add(songDetail, BorderLayout.WEST);
//		songPanel.add(songLength, BorderLayout.EAST);
//
//		songPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
//		
//		return songPanel;
//	}
	
	public JPanel getPanel() {
		JPanel songPanel = new JPanel();

		songPanel.setLayout(new BorderLayout());
		songPanel.setPreferredSize(new Dimension(200, 60));

		JLabel songName = new JLabel(this.title);
		songName.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel artistName = new JLabel(this.artist);
		artistName.setFont(new Font("Tahoma", Font.BOLD, 12));

		JLabel songLength = new JLabel(this.length);
		songLength.setFont(new Font("Tahoma", Font.BOLD, 14));
		songLength.setBorder(new EmptyBorder(0, 0, 0, 5));

		JLabel songLabel = new JLabel("Song • ");
		songLabel.setFont(new Font("Papyrus", Font.ITALIC | Font.BOLD, 14));

		JPanel artistPanel = new JPanel(new BorderLayout());
		artistPanel.add(songLabel, BorderLayout.WEST);
		artistPanel.add(artistName, BorderLayout.CENTER);
		
		songPanel.add(songName, BorderLayout.NORTH);
		songPanel.add(artistPanel, BorderLayout.SOUTH);
		songPanel.add(songLength, BorderLayout.EAST);
		
		songPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		
		
		return songPanel;
	}


	public void getSong(String title) {
		try {
			Request request = new InfoRequest();
			request = new IntentSong(request);
			request.setURLLink(request.getURLLink(java.net.URLEncoder.encode(title, "UTF-8")));
			ArrayList<String> data = request.doRequest();
			
			Song song = new Song(title, data.get(0), data.get(1), data.get(2));
			song.getPanel();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
