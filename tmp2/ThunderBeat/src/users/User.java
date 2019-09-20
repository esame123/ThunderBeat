package users;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class User {
	private String username;
	private String password;
	private String profilePhoto;
	private String backgroundPhoto;
	private String state;
	//protected Boolean VIP;
//	protected List<Interests> interests;
//	protected List<Album> albums;
//	protected List<Playlist> playlists;
//	public int publishedAlbum = 0;
//	public int publishedPlaylists = 0;
//	public List<User> followers;
//	public int numberOfFollowers = 0;
//	public List<User> following;
//	public int numberOfFollowing = 0;
	
	public User(String username, String password, String profilePhoto, String backgroundPhoto, String state) {
		this.username = username;
		this.password = password;
		this.profilePhoto = profilePhoto;
		this.backgroundPhoto = backgroundPhoto;
		this.state = state;	
		
	}

	public User(String username, String profilePhoto, String backgroundPhoto, String state) {
		super();
		this.username = username;
		this.profilePhoto = profilePhoto;
		this.backgroundPhoto = backgroundPhoto;
		this.state = state;
	}

	public User(String username) {
		this.username = username;
	}
	
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the profilePhoto
	 */
	public String getProfilePhoto() {
		return profilePhoto;
	}

	/**
	 * @param profilePhoto the profilePhoto to set
	 */
	public void setProfilePhoto(String profilePhoto) {
		this.profilePhoto = profilePhoto;
	}

	/**
	 * @return the backgroundPhoto
	 */
	public String getBackgroundPhoto() {
		return backgroundPhoto;
	}

	/**
	 * @param backgroundPhoto the backgroundPhoto to set
	 */
	public void setBackgroundPhoto(String backgroundPhoto) {
		this.backgroundPhoto = backgroundPhoto;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	
	public JPanel getPanel() {
		JPanel userPanel = new JPanel();
		//artistPanel.setPreferredSize(new Dimension(200, 75));
		userPanel.setLayout(new BorderLayout());
		userPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		
		
		JLabel artistName = new JLabel(this.username);
		artistName.setFont(new Font("Tahoma", Font.BOLD, 14));

		userPanel.add(artistName, BorderLayout.NORTH);
		JLabel userLabel = new JLabel("User");
		userLabel.setFont(new Font("Papyrus", Font.ITALIC | Font.BOLD, 14));
		
		JPanel southPanel = new JPanel();
		southPanel.add(userLabel);
		
		userPanel.add(userLabel, BorderLayout.SOUTH);		
		
		return userPanel;
	}
	
}
