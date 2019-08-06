package music;

import java.util.List;
import java.util.ArrayList;

public class Playlist {
	private String title;
	private List<Song> song;
	private int likes;
	
	public Playlist(String title) {
		this.title = title;
		this.song = new ArrayList<Song>();
		this.likes = 0;
	}
}
