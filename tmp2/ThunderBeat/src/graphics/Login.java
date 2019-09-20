package graphics;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import request.LoginRequest;
import request.Request;

@SuppressWarnings("unused")
public class Login {

	private JFrame frameLogin;
	JTextField usernameText = new JTextField();
	JTextField userSignText = new JTextField();
	private JPasswordField passwordText;
	private JPasswordField passwordText2;
	Request request = new LoginRequest();

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
		frameLogin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

		usernameText.setForeground(Color.WHITE);
		usernameText.setFont(new Font("Monospaced", Font.BOLD, 14));
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

		userSignText.setForeground(Color.WHITE);
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
		signUpConfermText.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e1) {
					e1.printStackTrace();
				}
				InputMenu im = new InputMenu(getUserSignText(), getPasswordText2());
				im.setLocationRelativeTo(null);

				frameLogin.setVisible(false);

			}
		});
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

		logInConfermText.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(request.logIn(getUsernameText(), getPasswordText())) {
					//NEED A FUNCTION TO REQUEST USERNAME, PROFILE PHOTO, BACKGROUND PHOTO AND STATE
					try {
						UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
						MainFrame mainFrame = new MainFrame(getUsernameText());
						mainFrame.setLocationRelativeTo(null);
						frameLogin.setVisible(false);
					} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
							| UnsupportedLookAndFeelException e1) {
						e1.printStackTrace();
					}
				}else {
					JOptionPane.showMessageDialog(frameLogin,
							"Username o password errati!!!", null, JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	/**
	 * 
	 * @return the username
	 */
	public String getUsernameText() {
		return usernameText.getText();
	}

	/**
	 * @return the username
	 */
	private String getUserSignText() {
		return userSignText.getText();
	}

	/**
	 * 
	 * @return the login password
	 */
	private String getPasswordText() {
		return String.valueOf(passwordText.getPassword());
	}

	/**
	 * 
	 * @return the signup password
	 */
	private String getPasswordText2() {
		return String.valueOf(passwordText2.getPassword());
	}	


}