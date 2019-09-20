package graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import request.IntentUser;
import request.Request;
import request.SearchRequest;

import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputMenu extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JFileChooser fileChooser = new JFileChooser();
	private JTextField profilePhotoAddress = new JTextField();
	private JTextField backgroundPhotoAddress = new JTextField();
	private JTextArea stateArea = new JTextArea();
	private JTextField usernameInput = new JTextField();
	private JPasswordField passwordField = new JPasswordField();
	private JPasswordField passwordConfirmField = new JPasswordField();
	Request request = new SearchRequest();

	/**
	 * Launch the application.
	 */
	/**
	 * Create the dialog.
	 */
	public InputMenu(String username, String password) {
		super.setTitle("Data Input Menu");
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		request = new IntentUser(request);
		request.setURLLink(request.getURLLink(usernameInput.getText()));
		ArrayList<String> result = request.doRequest();

		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpg","jpeg","png"));
		setResizable(false);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setVisible(true);
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		{
			{
				profilePhotoAddress.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
				profilePhotoAddress.setPreferredSize(new Dimension(200, 20));
			}
			{
				backgroundPhotoAddress.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
				backgroundPhotoAddress.setPreferredSize(new Dimension(200, 20));
			}
		}
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel upperPanel = new JPanel();
			contentPanel.add(upperPanel, BorderLayout.NORTH);
			upperPanel.setLayout(new BoxLayout(upperPanel, BoxLayout.X_AXIS));
			{
				JPanel userDetail = new JPanel();
				upperPanel.add(userDetail);
				userDetail.setLayout(new BorderLayout(0, 0));
				{
					JPanel usernamePanel = new JPanel();
					userDetail.add(usernamePanel, BorderLayout.WEST);
					usernamePanel.setLayout(new BoxLayout(usernamePanel, BoxLayout.Y_AXIS));
					{
						JLabel usernameLabel = new JLabel("Username: ");
						usernameLabel.setFont(new Font("Dialog", Font.BOLD, 14));
						usernameLabel.setEnabled(true);
						usernamePanel.add(usernameLabel);
					}
					{
						JSeparator userPassSeparator = new JSeparator();
						usernamePanel.add(userPassSeparator);
					}
					{
						JLabel passwordLabel = new JLabel("Password:");
						usernamePanel.add(passwordLabel);
						passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
					}
					{
						JSeparator passConfermPassSeparator = new JSeparator();
						usernamePanel.add(passConfermPassSeparator);
					}
					{
						JLabel passwordConfirmLabel = new JLabel("Confirm Password:");
						usernamePanel.add(passwordConfirmLabel);
						passwordConfirmLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
					}
				}
				{
					JPanel passwordPanel = new JPanel();
					userDetail.add(passwordPanel, BorderLayout.CENTER);
					passwordPanel.setLayout(new BoxLayout(passwordPanel, BoxLayout.Y_AXIS));
					{
						usernameInput.setText(username);
						usernameInput.setBorder(UIManager.getBorder("PasswordField.border"));
						passwordPanel.add(usernameInput);
						usernameInput.setPreferredSize(new Dimension(200, 20));
						usernameInput.setColumns(10);
					}
					{
						JSeparator userPassFieldSeparator = new JSeparator();
						passwordPanel.add(userPassFieldSeparator);
					}
					{
						passwordField.setText(password);
						passwordField.setPreferredSize(new Dimension(200, 20));
						passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
						passwordPanel.add(passwordField);
					}
					{
						JSeparator passConfirmPassFieldSeparator = new JSeparator();
						passwordPanel.add(passConfirmPassFieldSeparator);
					}
					{
						passwordPanel.add(passwordConfirmField);
						passwordConfirmField.setPreferredSize(new Dimension(200, 20));
						passwordConfirmField.setFont(new Font("Tahoma", Font.PLAIN, 14));
					}
				}
			}
		}
		{
			JPanel centralPanel = new JPanel();
			contentPanel.add(centralPanel, BorderLayout.CENTER);
			{
				JPanel labelPanel = new JPanel();
				centralPanel.add(labelPanel);
				labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
				{
					JLabel profileLabel = new JLabel("Profile Photo:   ");
					profileLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
					labelPanel.add(profileLabel);
				}
				{
					JSeparator labelSeparator = new JSeparator();
					labelPanel.add(labelSeparator);
				}
				{
					JLabel backgroundLabel = new JLabel("Background Photo:   ");
					backgroundLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
					labelPanel.add(backgroundLabel);
				}
			}
			JPanel textPanel = new JPanel();
			centralPanel.add(textPanel);
			textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
			textPanel.add(profilePhotoAddress);
			{
				JSeparator textSeparator = new JSeparator();
				textPanel.add(textSeparator);
			}
			textPanel.add(backgroundPhotoAddress);
			{
				JPanel searchPanel = new JPanel();
				centralPanel.add(searchPanel);
				searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));
				{
					JButton profileSearch = new JButton("Search...");
					profileSearch.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							int returnVal = fileChooser.showOpenDialog(InputMenu.this);
							if (returnVal == JFileChooser.APPROVE_OPTION) {
								File file = fileChooser.getSelectedFile();

								profilePhotoAddress.setText(file.getAbsolutePath());

							}												
						}
					});
					searchPanel.add(profileSearch);
				}
				{
					JSeparator buttonSeparator = new JSeparator();
					searchPanel.add(buttonSeparator);
				}
				{
					JButton backgroundSearch = new JButton("Search...");
					backgroundSearch.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							int returnVal = fileChooser.showOpenDialog(InputMenu.this);
							if (returnVal == JFileChooser.APPROVE_OPTION) {
								File file = fileChooser.getSelectedFile();
								backgroundPhotoAddress.setText(file.getAbsolutePath());
								backgroundPhotoAddress.setCaretPosition(backgroundPhotoAddress.getDocument().getLength());
							}

						}
					});
					searchPanel.add(backgroundSearch);
				}
			}
		}
		{
			JPanel lowerPanel = new JPanel();
			contentPanel.add(lowerPanel, BorderLayout.SOUTH);
			lowerPanel.setLayout(new BorderLayout(0, 0));
			{
				JLabel lblStato = new JLabel("Stato: ");
				lblStato.setFont(new Font("Tahoma", Font.BOLD, 14));
				lowerPanel.add(lblStato, BorderLayout.NORTH);
			}
			{
				stateArea.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						String string = stateArea.getText();
						if(string.equals("Scrivi uno stato che ti descrive")) {
							stateArea.setText("");
						}
					}
				});
				stateArea.setText("Scrivi uno stato che ti descrive");
				stateArea.setPreferredSize(new Dimension(400, 200));
				lowerPanel.add(stateArea);
			}
		}
		{
			JSeparator panelSeparator = new JSeparator();
			getContentPane().add(panelSeparator, BorderLayout.CENTER);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(result.get(0).equals("0 results")) {
							if(getPasswordField().equals(getPasswordConfirmField())) {
								if(request.postUser(getUsernameInput(), getPasswordField(), getProfilePhotoAddress(),
										getBackgroundPhotoAddress(), getStateArea().getText())) {
									try {
										UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
										MainFrame mainFrame = new MainFrame(getUsernameInput());
										mainFrame.setLocationRelativeTo(null);
										setVisible(false);
									}catch(ClassNotFoundException | InstantiationException | IllegalAccessException
											| UnsupportedLookAndFeelException e1) {
										e1.printStackTrace();
									}
								}
								else {
									JOptionPane.showMessageDialog(InputMenu.this,
											"Errore nella creazione dell'utente", null, JOptionPane.ERROR_MESSAGE);
								}
							}else {
								JOptionPane.showMessageDialog(InputMenu.this,
										"Password non uguali", null, JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				});
				buttonPane.add(cancelButton);
			}
		}
		pack();
	}

	/**
	 * @return the profilePhotoAddress
	 */
	public String getProfilePhotoAddress() {
		return profilePhotoAddress.getText();
	}

	/**
	 * @return the backgroundPhotoAddress
	 */
	public String getBackgroundPhotoAddress() {
		return backgroundPhotoAddress.getText();
	}

	/**
	 * @return the stateArea
	 */
	public JTextArea getStateArea() {
		return stateArea;
	}

	/**
	 * @return the usernameInput
	 */
	public String getUsernameInput() {
		return usernameInput.getText();
	}

	/**
	 * @return the passwordField
	 */
	public String getPasswordField() {
		return String.valueOf(passwordField.getPassword());
	}

	public String getPasswordConfirmField() {
		return String.valueOf(passwordConfirmField.getPassword());
	}



}
