package music;

public class Song {
	private String title;
	private String author;
	private String length;
	private Album album;
	private String genre;
	private int likes;
	
	public Song(String title, String author, String length,
			Album album, String genre) {
		this.title = title;
		this.author = author;
		this.length = length;
		this.album = album;
		this.genre = genre;
		this.likes = 0;
	}
	
	
}
