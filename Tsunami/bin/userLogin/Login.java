package userLogin;


import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Panel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Label;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

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
					window.frameLogin.setVisible(true);
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
		frameLogin.setLocationByPlatform(true);
		frameLogin.setSize(new Dimension(900, 450));
		frameLogin.setResizable(false);
		frameLogin.setUndecorated(true);
		frameLogin.setAlwaysOnTop(true);
		frameLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameLogin.setVisible(true);
		
		Panel overAll = new Panel();
		overAll.setBounds(0, 0, 900, 450);
		frameLogin.getContentPane().add(overAll);
		overAll.setLayout(null);
		
		JLabel exitIcon = new JLabel("");
		exitIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
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
		logInTitle.setFont(new Font("Dialog", Font.BOLD, 24));
		logInTitle.setAlignment(Label.CENTER);
		logInTitle.setBounds(10, 10, 85, 35);
		logIn.add(logInTitle);
		
		JSeparator usernameMarker = new JSeparator();
		usernameMarker.setBounds(50, 105, 190, 2);
		logIn.add(usernameMarker);
		
		JSeparator passwordMarker = new JSeparator();
		passwordMarker.setBounds(50, 155, 190, 2);
		logIn.add(passwordMarker);
		
		JTextArea usernameText = new JTextArea();
		usernameText.setFont(new Font("Monospaced", Font.BOLD, 14));
		usernameText.setTabSize(4);
		usernameText.setBorder(null);
		usernameText.setBackground(Color.DARK_GRAY);
		usernameText.setBounds(50, 85, 190, 25);
		logIn.add(usernameText);
		
		passwordText = new JPasswordField();
		passwordText.setFont(new Font("Tahoma", Font.BOLD, 14));
		passwordText.setBorder(null);
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
		SignUpTitle.setFont(new Font("Dialog", Font.BOLD, 24));
		SignUpTitle.setAlignment(Label.CENTER);
		SignUpTitle.setBounds(10, 10, 95, 35);
		signUp.add(SignUpTitle);
		
		JSeparator emailSeparator = new JSeparator();
		emailSeparator.setBounds(50, 105, 190, 2);
		signUp.add(emailSeparator);
		
		JTextArea emailText = new JTextArea();
		emailText.setTabSize(4);
		emailText.setFont(new Font("Monospaced", Font.BOLD, 14));
		emailText.setBorder(null);
		emailText.setBackground(Color.DARK_GRAY);
		emailText.setBounds(50, 85, 190, 25);
		signUp.add(emailText);
		
		JSeparator passwordSeparator = new JSeparator();
		passwordSeparator.setBounds(50, 155, 190, 2);
		signUp.add(passwordSeparator);
		
		passwordText2 = new JPasswordField();
		passwordText2.setFont(new Font("Tahoma", Font.BOLD, 14));
		passwordText2.setBorder(null);
		passwordText2.setBackground(Color.DARK_GRAY);
		passwordText2.setBounds(50, 135, 190, 25);
		signUp.add(passwordText2);
		
		JLabel emailIcon = new JLabel("");
		emailIcon.setIcon(new ImageIcon(Login.class.getResource("/images/icons8_New_Post_32px.png")));
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
			public void mousePressed(MouseEvent e) {
				signUp.setVisible(false);
				logIn.setVisible(true);
				
			}
		});
		
		signUpLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				logIn.setVisible(false);
				signUp.setVisible(true);
				
			}
		});
		
		logInConfermText.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
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
				    con.getInputStream();
				    System.out.println(con.getInputStream());
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