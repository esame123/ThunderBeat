package music;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class Artist {
	
	private String name;
	
	public Artist(String name) {
		this.name = name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String setName() {
		return this.name;
	}

	public JPanel getPanel() {
		JPanel artistPanel = new JPanel();
		//artistPanel.setPreferredSize(new Dimension(200, 75));
		artistPanel.setLayout(new BorderLayout());
		artistPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		
		
		JLabel artistName = new JLabel(this.name);
		artistName.setFont(new Font("Tahoma", Font.BOLD, 14));

		artistPanel.add(artistName, BorderLayout.NORTH);
		JLabel artistLabel = new JLabel("Artist");
		artistPanel.add(artistLabel, BorderLayout.SOUTH);
		artistPanel.getComponent(1).setFont(new Font("Papyrus", Font.ITALIC | Font.BOLD, 14));
		
		
		return artistPanel;
	}
}
