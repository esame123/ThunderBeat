package graphics;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;

import audioplay.AudioPlay;
import audioplay.Download;
import audioplay.Init;
import audioplay.Play;
import music.Song;
import request.InfoRequest;
import request.IntentSong;
import request.IntentUser;
import request.LibraryRequest;
import request.Request;
import request.SearchRequest;


@SuppressWarnings("unused")
public class Library extends JPanel {

	private static final long serialVersionUID = 1L;
	JPanel songPanel = new JPanel(new BorderLayout());
	private Play play;
	private File file;
	
	/**
	 * Create the panel.
	 */
	public Library(String username) {
		setLayout(new BorderLayout());
		JScrollPane songListScrollPanel = new JScrollPane();
		add(songListScrollPanel);
		songListScrollPanel.getVerticalScrollBar().setUnitIncrement(16);
		JPanel underPanel = new JPanel();
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
//						JPanel panel = new JPanel(new BorderLayout());
//						JLabel label = new JLabel("Ciaone");
//						panel.add(label, BorderLayout.CENTER);
//						underPanel.add(panel, BorderLayout.SOUTH);
						//Il nome della canzone
						JLabel songName = (JLabel) component.getComponent(0);
						//Prendi il secondo pannello inserito dentro component(pannello della canzone o dell'artista o dell'utente
						JPanel subComponent = (JPanel) component.getComponent(1);
						//Di questo subComponent è "Song •" vuol dire che è una canzone...per ora non serve
						JLabel typeLabel = (JLabel) subComponent.getComponent(0);
						Init init = new Init(songName.getText().replace("\'", "\\\'"));
						try {
							System.out.println("Dio");
							play = new Play(new Download(init.Go().substring(1)).getFileWav());
							System.out.println("Boia");
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						underPanel.add(play, BorderLayout.SOUTH);
						songPanel.repaint();
						songPanel.validate();
						
					}
					/*@Override
					public void mouseClicked(MouseEvent e) {
						JLabel songName = (JLabel) component.getComponent(0);
						JPanel subComponent = (JPanel) component.getComponent(1);
						JLabel typeLabel = (JLabel) subComponent.getComponent(0);
						if("Song • ".equals(typeLabel.getText())) {
							Request request = new InfoRequest();
							request = new IntentSong(request);
							Init init = new Init(songName.getText().replace("\'", "\\\'"));

							//Play play = new Play(download.getFileWav());
							
							new Thread(new Runnable() {
								public void run() {
									try {
										file = new Download(init.Go().substring(1)).getFileWav();
										System.out.println(file);
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									
								}
							}).run();
							play = new Play(file);
							JPanel panel = new JPanel(new BorderLayout());
							JLabel label = new JLabel(file.getName());
							panel.add(label, BorderLayout.CENTER);
							songPanel.add(panel, BorderLayout.SOUTH);
							songPanel.add(play, BorderLayout.CENTER);
							songPanel.repaint();
							songPanel.validate();


						}	
					}*/
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

		songListScrollPanel.getViewport().setView(songPanel);
		addUserSong(underPanel, username);
		songPanel.add(underPanel, BorderLayout.NORTH);
		songListScrollPanel.repaint();
		songListScrollPanel.validate();
	}

	private void addAllSong(JPanel underPanel) {

		try {
			ArrayList<String> songDetails = new ArrayList<String>();
			Request request = new SearchRequest();
			request = new IntentSong(request);
			request.setURLLink(request.getURLLink());
			ArrayList<String> artistName = request.doRequestArtist();
			Collections.sort(artistName);
			for(String title : artistName) {
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

	private void addUserSong(JPanel underPanel, String username) {
		if(username.equals(null)) {
			return;
		}
		try {
			ArrayList<Song> songs = new ArrayList<Song>();
			ArrayList<String> songDetail = new ArrayList<String>();
			Request request = new LibraryRequest();
			request = new IntentUser(request);
			request.setURLLink(request.getURLLink(java.net.URLEncoder.encode(username, "UTF-8")));
			songs = request.doRequestSongs();

			//			request = new InfoRequest();
			//			request = new IntentSong(request);
			if(songs == null) {
				JPanel noSongPanel = new JPanel();
				noSongPanel.add(new JLabel("Nessuna Canzone!!!   "));
				noSongPanel.getComponent(0).setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
				underPanel.add(noSongPanel);
				underPanel.setEnabled(false);
				return;
			}
			for(Song song : songs) {
				//				song.setTitle(song.getTitle().replace("\'", "\\\'"));
				//				request.setURLLink(request.getURLLink(java.net.URLEncoder.encode(song.getTitle(), "UTF-8")));
				//				songDetail = request.doRequest();
				song.setTitle(song.getTitle().replace("\\\'", "\'"));
				underPanel.add(song.getPanel());
				underPanel.validate();
			}
			underPanel.setVisible(true);

		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
