package users;

import java.util.List;
import java.util.ArrayList;
import java.security.*;
import password.Password;
import music.*;
import users.Interests;

public class User {
	protected String name;
	protected String surname;
	protected String username;
	//protected Boolean VIP;
	protected List<Interests> interests;
	protected int publishedAlbum;
	protected int publishedPlaylists;
	protected List<Album> albums;
	protected List<Playlist> playlists;
	protected List<User> followers;
	protected List<User> following;
	protected String password;
	
	public User(String name, String surname, String username, String password) {
		this.name = name;
		this.surname = surname;
		this.username = username;
		try {
			this.password = Password.passwordGenerator(password);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.interests = new ArrayList<Interests>();
		this.albums = new ArrayList<Album>();
		this.playlists = new ArrayList<Playlist>();
		this.followers = new ArrayList<User>();
		this.following = new ArrayList<User>();
		this.publishedAlbum = 0;
		this.publishedPlaylists = 0;
	}
		
	
}
