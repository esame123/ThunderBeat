package provagui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.Player;

public class PausablePlayer {

	private final static int NOTSTARTED = 0;
	private final static int PLAYING = 1;
	private final static int PAUSED = 2;
	private final static int FINISHED = 3;
	static String song;

	// the player actually doing all the work
	private final Player player;

	// locking object used to communicate with player thread
	private final Object playerLock = new Object();

	// status variable what player thread is doing/supposed to do
	private static int playerStatus = NOTSTARTED;

	public PausablePlayer(final InputStream inputStream) throws JavaLayerException {
		this.player = new Player(inputStream);
	}

	public PausablePlayer(final InputStream inputStream, final AudioDevice audioDevice) throws JavaLayerException {
		this.player = new Player(inputStream, audioDevice);
	}

	//  public PausablePlayer(String songname) {};

	/**
	 * Starts playback (resumes if paused)
	 */
	public void play() throws JavaLayerException {
		synchronized (playerLock) {
			switch (playerStatus) {
			case NOTSTARTED:
				final Runnable r = new Runnable() {
					public void run() {
						playInternal();
					}
				};
				final Thread t = new Thread(r);
				t.setDaemon(true);
				t.setPriority(Thread.MAX_PRIORITY);
				playerStatus = PLAYING;
				t.start();
				break;
			case PAUSED:
				resume();
				break;
			default:
				break;
			}
		}
	}

	/**
	 * Pauses playback. Returns true if new state is PAUSED.
	 */
	public boolean pause() {
		synchronized (playerLock) {
			if (playerStatus == PLAYING) {
				playerStatus = PAUSED;
			}
			return playerStatus == PAUSED;
		}
	}

	/**
	 * Resumes playback. Returns true if the new state is PLAYING.
	 * @throws JavaLayerException 
	 */
	public boolean resume() throws JavaLayerException {
		synchronized (playerLock) {
			if (playerStatus == PAUSED) {
				playerStatus = PLAYING;
				playerLock.notifyAll();
			}
			return playerStatus == PLAYING ;

		}
	}

	/**
	 * Stops playback. If not playing, does nothing
	 */
	public void stop() {
		synchronized (playerLock) {
			playerStatus = FINISHED;
			playerLock.notifyAll();
		}
	}

	private void playInternal() {
		while (playerStatus != FINISHED) {
			try {
				if (!player.play(1)) {
					break;
				}
			} catch (final JavaLayerException e) {
				break;
			}
			// check if paused or terminated
			synchronized (playerLock) {
				while (playerStatus == PAUSED) {
					try {
						playerLock.wait();
					} catch (final InterruptedException e) {
						// terminate player
						break;
					}
				}
			}
		}
		close();
	}

	/**
	 * Closes the player, regardless of current state.
	 */
	public void close() {
		synchronized (playerLock) {
			playerStatus = FINISHED;
		}
		try {
			player.close();
		} catch (final Exception e) {
			// ignore, we are terminating anyway
		}
	}

	// demo how to use
	public static void main(String[] argv) {
		try {

			JFrame frame=new JFrame();
			frame.setLayout(new BorderLayout());
			JButton btnplay=new JButton("play");
			JButton btnpause=new JButton("pause");
			JTextField jtf =new JTextField("fcerca");
			JButton btnsearch=new JButton("bcerca");
			frame.setLayout(new GridLayout(3,2));
			frame.add(jtf);
			frame.add(btnsearch);
			btnsearch.setSize(100,100);
			frame.add(btnplay);            
			frame.add(btnpause);
			frame.setSize(500, 300);

			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);



			btnsearch.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					String h;
					h=jtf.getText();
					Cerca sc=new Cerca(h);

					try {
						song=sc.prova();
						Init init=new Init(song);

						InputStream fis=new URL("http://thunderbeat.altervista.org/Sound/"+init.Go().substring(1)).openStream();
						BufferedInputStream bis=new BufferedInputStream(fis);
						PausablePlayer player=new PausablePlayer(bis);
						btnplay.addActionListener(new ActionListener() {

							public void actionPerformed(ActionEvent e) {
								// start playing
								try {
									if (playerStatus==PAUSED) {
										player.resume();
									}else if (playerStatus==NOTSTARTED) player.play();
								} catch (JavaLayerException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}

							}
						});
						btnpause.addActionListener(new ActionListener() {

							public void actionPerformed(ActionEvent e) {
								player.pause();
							}
						});
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (JavaLayerException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});

		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

}