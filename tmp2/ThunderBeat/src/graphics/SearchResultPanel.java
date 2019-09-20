package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import audioplay.AudioPlay;
import audioplay.Cerca;
import music.Artist;
import music.Song;
import request.InfoRequest;
import request.IntentArtist;
import request.IntentSong;
import request.IntentUser;
import request.Request;
import request.RequestValues;
import users.User;


@SuppressWarnings("unused")
public class SearchResultPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	JTextField searchTextField;
	ArrayList<Song> songList = new ArrayList<Song>();
	ArrayList<Artist> artistList = new ArrayList<Artist>();
	JPanel songPanel = new JPanel();
	JPanel underPanel = new JPanel();
	JScrollPane songListPanel = new JScrollPane();
	ArrayList<Song> songs = new ArrayList<Song>();

	public SearchResultPanel() {
		setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		setLayout(new BorderLayout(0, 0));

		JPanel searchBarPanel = new JPanel();
		add(searchBarPanel, BorderLayout.NORTH);

		searchTextField = new JTextField() {
			private static final long serialVersionUID = 2L;
			@Override 
			protected void paintComponent(Graphics g) {
				if (!isOpaque() && getBorder() instanceof RoundedCornerBorder) {
					Graphics2D g2 = (Graphics2D) g.create();
					g2.setPaint(getBackground());
					g2.fill(((RoundedCornerBorder) getBorder()).getBorderShape(
							0, 0, getWidth() - 1, getHeight() - 1));
					g2.dispose();
				}
				super.paintComponent(g);
			}
			@Override 
			public void updateUI() {
				super.updateUI();
				setOpaque(false);
				setBorder(new RoundedCornerBorder());
			}
		};
		searchTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if("Search songs or artists".equals(searchTextField.getText())) {
					searchTextField.setForeground(Color.black);
					searchTextField.setText("");
				}
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					Cerca cerca = new Cerca(searchTextField.getText());
					songPanel.removeAll();
					underPanel.removeAll();
					searchSimilar(cerca);
					songPanel.repaint();
					songPanel.validate();
				}
			}
		});
		searchTextField.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				// DO NOTHING				
			}

			@Override
			public void focusLost(FocusEvent e) {
				try {
					JTextField source = (JTextField) e.getSource();
					if("".equals(source.getText())) {
						source.setText("Search songs or artists");
						searchTextField.setForeground(new Color(109, 109, 109));
					}
				}catch(ClassCastException ex) {

				}

			}

		});
		searchBarPanel.setLayout(new BorderLayout());
		searchTextField.setText("Search songs or artists");
		searchTextField.setBackground(SystemColor.window);
		searchBarPanel.add(searchTextField, BorderLayout.CENTER);

		JLabel searchLogo = new JLabel("");
		searchLogo.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Cerca cerca = new Cerca(searchTextField.getText());
				songPanel.removeAll();
				underPanel.removeAll();
				searchSimilar(cerca);
				songPanel.repaint();
				songPanel.validate();

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		searchBarPanel.add(searchLogo, BorderLayout.EAST);
		searchLogo.setIcon(new ImageIcon(SearchResultPanel.class.getResource("/images/search-icon.png")));
		songListPanel.getVerticalScrollBar().setUnitIncrement(16);
		add(songListPanel, BorderLayout.CENTER);

		songListPanel.getViewport().setView(songPanel);
		songPanel.setLayout(new BorderLayout());

		songListPanel.validate();
		songPanel.repaint();
	}


	public void searchSimilar(Cerca cerca) {
		try {
			underPanel.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseEntered(MouseEvent e) {
					JPanel component = (JPanel) underPanel.getComponentAt(new Point(e.getX(), e.getY()));
					component.addMouseListener(new MouseListener() {

						@Override
						public void mouseClicked(MouseEvent e) {
							JLabel songName = (JLabel) component.getComponent(0);
							JPanel subComponent = (JPanel) component.getComponent(1);
							JLabel typeLabel = (JLabel) subComponent.getComponent(0);
							if("Song • ".equals(typeLabel.getText())) {
								//InputStream fis=new URL("http://thunderbeat.altervista.org/Sound/"+songName.getText()).openStream();
								//AudioPlay audio = new AudioPlay(songName.getText().replace("\'", "\\\'"));

							}	
						}
						@Override
						public void mousePressed(MouseEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void mouseReleased(MouseEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void mouseEntered(MouseEvent e) {
							component.setBorder(new BevelBorder(BevelBorder.LOWERED));

						}

						@Override
						public void mouseExited(MouseEvent e) {
							component.setBorder(new BevelBorder(BevelBorder.RAISED));

						}

					});

				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub

				}
			});
			underPanel.setLayout(new BoxLayout(underPanel, BoxLayout.Y_AXIS));

			ArrayList<RequestValues> songValues = new ArrayList<RequestValues>();
			ArrayList<RequestValues> artistValues = new ArrayList<RequestValues>();
			ArrayList<RequestValues> userValues = new ArrayList<RequestValues>();
			for(RequestValues value : cerca.getSong()) {
				songValues.add(value);
			}

			for(RequestValues value : cerca.getArtist()) {
				artistValues.add(value);
			}

			for(RequestValues value : cerca.getUser()) {
				userValues.add(value);
			}

			//			if((songValues.size() != 0 && artistValues.size() != 0 && userValues.size() != 0) &&
			//					songValues.get(0).getValues() < artistValues.get(0).getValues()) {
			//				if(userValues.get(0).getValues() < songValues.get(0).getValues()) {
			//					showSongFirst(songValues, artistValues, userValues, underPanel);
			//				}else {
			//					showArtistFirst(artistValues, songValues, userValues, underPanel);
			//				}
			//			}
			if(songValues.size() != 0 && artistValues.size() != 0 && userValues.size() != 0 &&
					songValues.get(0).getValues() < artistValues.get(0).getValues()){
				if(songValues.get(0).getValues() < userValues.get(0).getValues()) {
					showSongFirst(songValues, artistValues, userValues);
				}
				else {
					showUserFirst(userValues, songValues, artistValues);
				}
			}
			else {
				showArtistFirst(artistValues, songValues, userValues);
			}
			songPanel.add(underPanel, BorderLayout.NORTH);
			songListPanel.repaint();
			songListPanel.validate();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void showSongFirst(ArrayList<RequestValues> songs, ArrayList<RequestValues> artists,
			ArrayList<RequestValues> users) {
		showSong(songs);
		showArtist(artists);
		showUser(users);
	}

	public void showArtistFirst(ArrayList<RequestValues> artists, ArrayList<RequestValues> songs,
			ArrayList<RequestValues> users) {
		showArtist(artists);
		showSong(songs);
		showUser(users);
	}

	public void showUserFirst(ArrayList<RequestValues> users, ArrayList<RequestValues> songs,
			ArrayList<RequestValues> artists) {
		showUser(users);
		showSong(songs);
		showArtist(artists);
		
	}

	public void showSong(ArrayList<RequestValues> songs) {
		try {
			for(RequestValues value : songs) {
				Request request = new InfoRequest();
				request = new IntentSong(request);
				request.setURLLink(request.getURLLink(java.net.URLEncoder.encode(value.getName(), "UTF-8")));
				ArrayList<String> data = request.doRequest();
				value.setName(value.getName().replace("\\", ""));
				Song song = new Song(value.getName(), data.get(0), data.get(1), data.get(2));
				underPanel.add(song.getPanel());
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void showArtist(ArrayList<RequestValues> artists) {
		try {
			for(RequestValues value : artists) {
				Request request = new InfoRequest();
				request = new IntentArtist(request);
				request.setURLLink(request.getURLLink(java.net.URLEncoder.encode(value.getName(), "UTF-8")));
				//ArrayList<String> data = request.doRequest();
				value.setName(value.getName().replace("\\", ""));
				Artist artist = new Artist(value.getName());
				underPanel.add(artist.getPanel());

			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void showUser(ArrayList<RequestValues> users) {
		try {
			for(RequestValues value : users) {
				Request request = new InfoRequest();
				request = new IntentUser(request);
				request.setURLLink(request.getURLLink(java.net.URLEncoder.encode(value.getName(), "UTF-8")));
				//ArrayList<String> data = request.doRequest();
				value.setName(value.getName().replace("\\", ""));
				User user = new User(value.getName());
				underPanel.add(user.getPanel());
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
