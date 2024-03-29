package audioplay;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;

import audioplay.Cerca;
import audioplay.Download;
import audioplay.Init;

@SuppressWarnings("unused")
public class AudioPlay implements LineListener {
	private File wavfile;
	private AudioInputStream wavstream;
	public Clip wavaudio, mp3audio;
	private int min, sec;
	private final JButton playBtn = new JButton();
	private final JButton pauseBtn = new JButton();
	private final JButton stopBtn = new JButton();
	private SeekBar progressBar;
	private JFrame player;
	private File song;
	public AudioPlay(File song) {
		wavfile = song;
		wavInit();
		initComponents();
	}


	//Creates the gui for the player
	//Defines a file chooser, play/pause/stop buttons, playtime tracker, and a seek bar
	//Todo: Add a volume control bar, add option to loop, add error catching for file chooser
	public void initComponents() {
		//Create the frame for the player which everything is added to
		player = new JFrame();

		JMenuBar greenMenu = new JMenuBar();
		greenMenu.setOpaque(true);
		greenMenu.setBackground(new Color(204, 255, 153));
		greenMenu.setPreferredSize(new Dimension(600, 20));
		player.setJMenuBar(greenMenu);

		player.getContentPane().setLayout(new FlowLayout());
		player.setTitle(wavfile.getName());
		player.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Set images for the buttons
		//try {
		//		Image img = ImageIO.read(getClass().getResource("resources/play.bmp"));
		//		playBtn.setIcon(new ImageIcon(img));

		playBtn.setSize(100, 100);

		//		img = ImageIO.read(getClass().getResource("resources/pause.bmp"));
		//	pauseBtn.setIcon(new ImageIcon(img));
		pauseBtn.setSize(100,100);
		//		img = ImageIO.read(getClass().getResource("resources/stop.bmp"));
		//	stopBtn.setIcon(new ImageIcon(img));
		stopBtn.setSize(100,100);
		//	} catch(IOException e) {

		//	}

		//Toggle button defaults
		playBtn.setEnabled(true);
		pauseBtn.setEnabled(false);
		stopBtn.setEnabled(false);

		//Create and add the file chooser browser to the frame
		//		final JPanel browser = new JPanel();
		//		final JFileChooser fileChooser = new JFileChooser(wavfile);
		//		FileNameExtensionFilter filter = new FileNameExtensionFilter("wav files", "wav");
		//		fileChooser.addChoosableFileFilter(filter);
		//		fileChooser.removeChoosableFileFilter(fileChooser.getChoosableFileFilters()[0]);
		//		fileChooser.setFileFilter(filter);
		//		
		//		JButton openBtn = new JButton("Open");
		//		//Describe what actions to perform when the open button is clicked and add the button to the frame
		//		openBtn.addActionListener(
		//				new ActionListener() {
		//					public void actionPerformed(ActionEvent e) {
		//						int returnVal = fileChooser.showOpenDialog(browser);
		//						if(returnVal == fileChooser.APPROVE_OPTION) {
		//							final File newFile;
		//							newFile = fileChooser.getSelectedFile();
		//							//System.out.println("Changing song from " + wavfile + " to " + newFile);
		//							wavfile = newFile;
		//							wavaudio.stop();
		//							try {
		//								wavstream.close();
		//							} catch(IOException ex) {
		//
		//							}
		//							wavInit();
		//							progressBar.setSong(wavaudio);
		//							player.setTitle(wavfile.getName());
		//							playBtn.setEnabled(false);
		//							pauseBtn.setEnabled(true);
		//							stopBtn.setEnabled(true);
		//							wavaudio.start();
		//						}
		//					}
		//				});
		//		browser.add(openBtn);
		//		player.getContentPane().add(browser);

		//Describe what actions to perform when the play button is clicked and add the button to the frame
		playBtn.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						playBtn.setEnabled(false);
						pauseBtn.setEnabled(true);
						stopBtn.setEnabled(true);
						if(!wavaudio.isOpen()) {
							wavInit();
						}
						wavaudio.start();
					}
				}
				);
		player.getContentPane().add(playBtn);
		//Describe what actions to perform when the pause button is clicked and add the button to the frame
		pauseBtn.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						playBtn.setEnabled(true);
						pauseBtn.setEnabled(false);
						stopBtn.setEnabled(true);
						wavaudio.stop();
					}
				}
				);
		player.getContentPane().add(pauseBtn);
		//Describe what actions to perform when the stop button is clicked and add the button to the frame
		stopBtn.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						wavaudio.stop();
						playBtn.setEnabled(true);
						pauseBtn.setEnabled(false);
						stopBtn.setEnabled(false);
						wavaudio.setFramePosition(0);						
					}
				}
				);
		player.getContentPane().add(stopBtn);
		//Create a JLabel object to display text/images on the frame, timerLabel will display the current playtime
		JLabel timerLabel = new JLabel();
		player.getContentPane().add(timerLabel);


		progressBar = new SeekBar(wavaudio);
		player.getContentPane().add(progressBar);

		//Determine the total runtime
		String temp;
		int min_t, sec_t;
		double progress, position, length;

		player.pack();
		player.setVisible(true);

		//While playing, keep updating the current play time/progress of the song
		while(true) {
			position = (int) wavaudio.getMicrosecondPosition()/1000000.0;
			length = (int) wavaudio.getMicrosecondLength()/1000000.0;
			min_t = (int) (wavaudio.getMicrosecondLength()/1000000.0)/60;
			sec_t = (int) (wavaudio.getMicrosecondLength() - min_t*60*1000000)/1000000;

			progress = position * 100.0 /length;
			progressBar.updateX(progress);
			temp = progressTime();
			timerLabel.setText(temp + " / " + min_t + ":" + sec_t);
		}
	}

	public boolean wavInit() {
		try {
			//Set up data input

			wavstream = AudioSystem.getAudioInputStream(wavfile);
			wavaudio = AudioSystem.getClip();
			wavaudio.open(wavstream);

			//Set loop points (Only if you want to loop a song)
			//int startFrame = 0;
			//int endFrame = wavaudio.getFrameLength() - 2;
			//wavaudio.setLoopPoints(startFrame, endFrame);
			//Add a line listener to perform certain actions on events
			wavaudio.addLineListener(this);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	//Need to implement this method to qualify as an implementation of LineListener
	//This tells the listener what to do when an event occurs
	public void update(LineEvent event) {
		Line eventLine = event.getLine();
		LineEvent.Type eventType = event.getType();
		if(eventLine == wavaudio && eventType == LineEvent.Type.STOP) {
			//Differentiate between pressing the pause/stop button and letting the song run to the end
			if(pauseBtn.isEnabled() && !playBtn.isEnabled()) {
				playBtn.setEnabled(true);
				pauseBtn.setEnabled(false);
				stopBtn.setEnabled(false);
				wavaudio.setFramePosition(0);
			} else if(!pauseBtn.isEnabled() && playBtn.isEnabled()) {
				playBtn.setEnabled(true);
				pauseBtn.setEnabled(false);
				stopBtn.setEnabled(true);
			}
		} 
	}

	public String progressTime() {
		min = (int) (wavaudio.getMicrosecondPosition()/1000000.0)/60;
		sec = (int) (wavaudio.getMicrosecondPosition() - min*60*1000000)/1000000;
		if(sec < 10) {
			return min + ":0" + sec;
		} else {
			return min + ":" + sec;
		}
	}

	//	public static void main(String[] args) throws Exception{
	//		//Initialize the demo
	//		Cerca sc=new Cerca("aint no monuo");
	//		String song=sc.prova();
	//		Init init=new Init(song);
	//		Download dl=new Download(init.Go().substring(1));
	//		AudioPlay demo = new AudioPlay(dl.getFileWav());
	//
	//	}

	public  AudioPlay(String songName) {
		try {

			Init init = new Init(songName);
			Download download = new Download(init.Go().substring(1));
			AudioPlay demo = new AudioPlay(download.getFileWav());
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}