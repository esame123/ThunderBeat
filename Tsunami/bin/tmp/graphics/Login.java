package graphics;


import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.NoSuchAlgorithmException;
import javax.swing.border.MatteBorder;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JTree;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

@SuppressWarnings("unused")
public class Login {

	private JFrame frameLogin;
	private JPasswordField passwordText;
	private JPasswordField passwordText2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameLogin = new JFrame();
		frameLogin.setUndecorated(true);
		frameLogin.setSize(new Dimension(900, 450));
		frameLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameLogin.setLocationRelativeTo(null);
		frameLogin.setVisible(true);

		Panel overAll = new Panel();
		overAll.setBounds(0, 0, 900, 450);
		frameLogin.getContentPane().add(overAll);
		overAll.setLayout(null);

		JLabel exitIcon = new JLabel("");
		exitIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exitIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		exitIcon.setBounds(870, 0, 30, 30);
		overAll.add(exitIcon);
		exitIcon.setIcon(new ImageIcon(Login.class.getResource("/images/icons8_Cancel_30px.png")));

		Panel logIn = new Panel();
		logIn.setVisible(false);
		logIn.setBackground(Color.DARK_GRAY);
		logIn.setBounds(100, 100, 250, 270);
		overAll.add(logIn);
		logIn.setLayout(null);

		JPanel logInMarker = new JPanel();
		logInMarker.setBackground(new Color(30, 144, 255));
		logInMarker.setBounds(10, 45, 85, 5);
		logIn.add(logInMarker);

		Label logInTitle = new Label("Log In");
		logInTitle.setForeground(Color.WHITE);
		logInTitle.setFont(new Font("Dialog", Font.BOLD, 24));
		logInTitle.setAlignment(Label.CENTER);
		logInTitle.setBounds(10, 10, 85, 35);
		logIn.add(logInTitle);

		JTextArea usernameText = new JTextArea();
		usernameText.setText("Username");

		usernameText.setForeground(Color.WHITE);
		
		usernameText.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if("Username".equals(usernameText.getText())){
					usernameText.setText("");
				}
			}
		});
		usernameText.setFont(new Font("Monospaced", Font.BOLD, 14));
		usernameText.setTabSize(4);
		usernameText.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
		usernameText.setBackground(Color.DARK_GRAY);
		usernameText.setBounds(50, 85, 190, 25);
		logIn.add(usernameText);

		passwordText = new JPasswordField();
		passwordText.setFont(new Font("Tahoma", Font.BOLD, 14));
		passwordText.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
		passwordText.setBackground(Color.DARK_GRAY);
		passwordText.setBounds(50, 135, 190, 25);
		logIn.add(passwordText);

		JLabel userIcon = new JLabel("");
		userIcon.setIcon(new ImageIcon(Login.class.getResource("/images/icons8_User_35px_1.png")));
		userIcon.setBounds(10, 75, 35, 35);
		logIn.add(userIcon);

		JLabel passwordIcon = new JLabel("");
		passwordIcon.setIcon(new ImageIcon(Login.class.getResource("/images/icons8_Lock_32px.png")));
		passwordIcon.setBounds(13, 125, 35, 35);
		logIn.add(passwordIcon);

		JPanel logInConferm = new JPanel();
		logInConferm.setBackground(Color.DARK_GRAY);
		logInConferm.setBorder(new LineBorder(new Color(30, 144, 255)));
		logInConferm.setBounds(10, 210, 230, 40);
		logIn.add(logInConferm);
		logInConferm.setLayout(null);

		JLabel logInConfermText = new JLabel("Log In");
		logInConfermText.setBackground(Color.DARK_GRAY);
		logInConfermText.setBorder(new LineBorder(new Color(30, 144, 255)));
		logInConfermText.setHorizontalTextPosition(SwingConstants.CENTER);
		logInConfermText.setFont(new Font("Tahoma", Font.BOLD, 18));
		logInConfermText.setHorizontalAlignment(SwingConstants.CENTER);
		logInConfermText.setBounds(0, 0, 230, 40);
		logInConferm.add(logInConfermText);

		JPanel signInPanel = new JPanel();		
		signInPanel.setBorder(null);
		signInPanel.setBounds(150, 200, 150, 40);
		overAll.add(signInPanel);
		signInPanel.setLayout(null);

		Label signInLabel = new Label("Log In");
		signInLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
		signInLabel.setAlignment(Label.CENTER);
		signInLabel.setBackground(new Color(30, 144, 255));
		signInLabel.setBounds(0, 0, 150, 40);
		signInPanel.add(signInLabel);

		Panel signUp = new Panel();
		signUp.setVisible(false);
		signUp.setBounds(500, 100, 250, 270);
		overAll.add(signUp);
		signUp.setLayout(null);
		signUp.setBackground(Color.DARK_GRAY);

		JPanel signUpMarker = new JPanel();
		signUpMarker.setBackground(new Color(30, 144, 255));
		signUpMarker.setBounds(10, 45, 95, 5);
		signUp.add(signUpMarker);

		Label SignUpTitle = new Label("Sign Up");
		SignUpTitle.setForeground(Color.WHITE);
		SignUpTitle.setFont(new Font("Dialog", Font.BOLD, 24));
		SignUpTitle.setAlignment(Label.CENTER);
		SignUpTitle.setBounds(10, 10, 95, 35);
		signUp.add(SignUpTitle);

		JTextArea userSignText = new JTextArea();
		userSignText.setText("Username");
		userSignText.setForeground(Color.WHITE);
		userSignText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if("".equals(userSignText.getText())) {
					userSignText.setText("Username");
				}
			}
		});
		userSignText.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if("Username".equals(userSignText.getText())){
					userSignText.setText("");
				}
			}
		});
		userSignText.setTabSize(4);
		userSignText.setFont(new Font("Monospaced", Font.BOLD, 14));
		userSignText.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
		userSignText.setBackground(Color.DARK_GRAY);
		userSignText.setBounds(50, 85, 190, 25);
		signUp.add(userSignText);

		passwordText2 = new JPasswordField();
		passwordText2.setFont(new Font("Tahoma", Font.BOLD, 14));
		passwordText2.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
		passwordText2.setBackground(Color.DARK_GRAY);
		passwordText2.setBounds(50, 135, 190, 25);
		signUp.add(passwordText2);

		JLabel emailIcon = new JLabel("");
		emailIcon.setIcon(new ImageIcon(Login.class.getResource("/images/icons8_User_35px_1.png")));
		emailIcon.setBounds(13, 74, 35, 35);
		signUp.add(emailIcon);

		JLabel passwordIcon2 = new JLabel("");
		passwordIcon2.setIcon(new ImageIcon(Login.class.getResource("/images/icons8_Lock_32px.png")));
		passwordIcon2.setBounds(13, 125, 35, 35);
		signUp.add(passwordIcon2);

		JPanel signUpConferm = new JPanel();
		signUpConferm.setLayout(null);
		signUpConferm.setBorder(new LineBorder(new Color(30, 144, 255)));
		signUpConferm.setBackground(Color.DARK_GRAY);
		signUpConferm.setBounds(10, 210, 230, 40);
		signUp.add(signUpConferm);

		JLabel signUpConfermText = new JLabel("Sign Up");
		signUpConfermText.setHorizontalAlignment(SwingConstants.CENTER);
		signUpConfermText.setFont(new Font("Tahoma", Font.BOLD, 18));
		signUpConfermText.setBounds(0, 0, 230, 40);
		signUpConferm.add(signUpConfermText);

		JPanel signUpPanel = new JPanel();
		signUpPanel.setBounds(550, 200, 150, 40);
		overAll.add(signUpPanel);
		signUpPanel.setLayout(null);

		Label signUpLabel = new Label("Sign Up");
		signUpLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
		signUpLabel.setBackground(new Color(30, 144, 255));
		signUpLabel.setAlignment(Label.CENTER);
		signUpLabel.setBounds(0, 0, 150, 40);
		signUpPanel.add(signUpLabel);

		JLabel backGround = new JLabel("");
		backGround.setBounds(0, 0, 900, 450);
		overAll.add(backGround);
		backGround.setIcon(new ImageIcon(Login.class.getResource("/images/johannes-plenio-E-Zuyev2XWo-unsplash1.jpg")));

		signInLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				signUp.setVisible(false);
				logIn.setVisible(true);
				usernameText.setText("");
				passwordText.setText("");

			}
		});

		signUpLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				logIn.setVisible(false);
				signUp.setVisible(true);
				userSignText.setText("");
				passwordText2.setText("");

			}
		});
		//Function to see

		
		logInConfermText.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					// open a connection to the site
					URL url = new URL("https://thunderbeat.altervista.org/connection.php");
					URLConnection con = url.openConnection();
					// activate the output
					con.setDoOutput(true);
					PrintStream ps = new PrintStream(con.getOutputStream());
					// send your parameters to your site
					ps.print("username=" + usernameText.getText());
					ps.print("&password=" + passwordText.getPassword().toString());
					// we have to get the input stream in order to actually send the request
					ps.println();
					con.getInputStream();
					// close the print stream
					ps.close();
				} catch (MalformedURLException ex) {
					ex.printStackTrace();
				} catch (IOException ex) {
					ex.printStackTrace();
				}

			}
		});
	}	
}