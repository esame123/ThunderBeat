package audioplay;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Play extends JPanel implements LineListener{

	private static final long serialVersionUID = 1L;
	private final JButton playBtn = new JButton();
	private final JButton pauseBtn = new JButton();
	private final JButton stopBtn = new JButton();
	private final JButton previousBtn = new JButton();
	private final JButton nextBtn = new JButton();
	private SeekBar progressBar;
	private AudioInputStream wavstream;
	public Clip wavaudio, mp3audio;
	private File wavfile;
	private int min, sec;
	/**
	 * Create the panel.
	 */
	public Play(File song) {
		wavfile = song;
		wavInit();
		setLayout(new BorderLayout());

		JPanel songnamePanel = new JPanel();
		add(songnamePanel, BorderLayout.NORTH);
		songnamePanel.setLayout(new BoxLayout(songnamePanel, BoxLayout.X_AXIS));

		JLabel songname = new JLabel(song.getName());
		songname.setFont(new Font("Tahoma", Font.BOLD, 14));
		songnamePanel.add(songname);

		JPanel seekBarPanel = new JPanel();
		add(seekBarPanel, BorderLayout.CENTER);
		seekBarPanel.setLayout(new BorderLayout(0, 0));

		JPanel buttonPanel = new JPanel();
		seekBarPanel.add(buttonPanel);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));


		//Image img = ImageIO.read(getClass().getResource("images/play-icon.png"));
		playBtn.setOpaque(false);
		playBtn.setPreferredSize(new Dimension(32, 32));
		//playBtn.setIcon(new ImageIcon(img));
		playBtn.setSize(32, 32);

		//img = ImageIO.read(getClass().getResource("images/pause-icon.png"));
		//pauseBtn.setIcon(new ImageIcon(img));
		pauseBtn.setSize(32, 32);

		//img = ImageIO.read(getClass().getResource("images/stop-icon.png"));
		//stopBtn.setIcon(new ImageIcon(img));
		stopBtn.setSize(32, 32);

		//img = ImageIO.read(getClass().getResource("images/first-track-icon.png"));
		previousBtn.setOpaque(false);
		previousBtn.setPreferredSize(new Dimension(32, 32));
		//previousBtn.setIcon(new ImageIcon(img));
		previousBtn.setSize(32, 32);

		//img = ImageIO.read(getClass().getResource("images/last-track-icon.png"));
		nextBtn.setOpaque(false);
		nextBtn.setPreferredSize(new Dimension(32, 32));
		//nextBtn.setIcon(new ImageIcon(img));
		nextBtn.setSize(32, 32);

		playBtn.setEnabled(true);
		pauseBtn.setEnabled(false);
		stopBtn.setEnabled(false);			
		previousBtn.setEnabled(true);
		nextBtn.setEnabled(true);

		playBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playBtn.setEnabled(false);
				pauseBtn.setEnabled(true);
				stopBtn.setEnabled(true);
				if(!wavaudio.isOpen()) {
					wavInit();
				}
				wavaudio.start();
			}
		});
		pauseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playBtn.setEnabled(true);
				pauseBtn.setEnabled(false);
				stopBtn.setEnabled(true);
				wavaudio.stop();
			}
		});
		stopBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wavaudio.stop();
				playBtn.setEnabled(true);
				pauseBtn.setEnabled(false);
				stopBtn.setEnabled(false);
				wavaudio.setFramePosition(0);						
			}
		});



		buttonPanel.add(previousBtn);
		buttonPanel.add(playBtn);
		buttonPanel.add(nextBtn);
		seekBarPanel.add(buttonPanel, BorderLayout.SOUTH);

		JLabel timer = new JLabel();
		seekBarPanel.add(timer, BorderLayout.EAST);

		progressBar = new SeekBar(wavaudio);

		System.out.println("Dio");
		play(timer);
		System.out.println("Boia");
		
		repaint();
		validate();

	}



	public void play(JLabel timer) {
		String temp;
		int min_t, sec_t;
		double progress, position, length;

		while(true) {
			position = (int) wavaudio.getMicrosecondPosition()/1000000.0;
			length = (int) wavaudio.getMicrosecondLength()/1000000.0;
			min_t = (int) (wavaudio.getMicrosecondLength()/1000000.0)/60;
			sec_t = (int) (wavaudio.getMicrosecondLength() - min_t*60*1000000)/1000000;

			progress = position * 100.0 /length;
			progressBar.updateX(progress);
			temp = progressTime();
			timer.setText(temp + " / " + min_t + ":" + sec_t);
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

	@Override
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

}
