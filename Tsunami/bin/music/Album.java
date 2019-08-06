package music;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
	
public class Album {
	private String title;
	private int numberSong;
	private List<Song> songs;
	private String author;
	private String publisher;
	private GregorianCalendar releaseDate;
	private String genre;
	private int likes;
	
	public Album(String title, String author, String publisher, GregorianCalendar releaseDate, String genre) {
		super();
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.releaseDate = releaseDate;
		this.genre = genre;
		this.numberSong = 0;
		this.songs = new ArrayList<Song>();
		this.likes = 0;
	}
	
	
}
