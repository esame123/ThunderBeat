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
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.UIManager;
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
	private JTextField usernameInput;
	private JPasswordField passwordField;
	private JPasswordField passwordConfirmField;

	Request request = new SearchRequest();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
			InputMenu dialog = new InputMenu();
			dialog.setLocationRelativeTo(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InputMenu() {

		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpg","jpeg","png"));
		setResizable(false);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
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
						usernameInput = new JTextField();
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
						passwordField = new JPasswordField();
						passwordField.setPreferredSize(new Dimension(200, 20));
						passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
						passwordPanel.add(passwordField);
					}
					{
						JSeparator passConfirmPassFieldSeparator = new JSeparator();
						passwordPanel.add(passConfirmPassFieldSeparator);
					}
					{
						passwordConfirmField = new JPasswordField();
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
						request = new IntentUser(request);
						request.setURLLink(request.getURLLink(usernameInput.getText()));
						ArrayList<String> result = request.doRequest();
						if(result.get(0).equals("0 results")) {
							
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

}
