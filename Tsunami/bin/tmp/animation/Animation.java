package animation;

import javax.swing.JPanel;

public class Animation {
	
	public static void moveRightLeftStill(JPanel panel, int finalX) {
		int x = panel.getX();
		int y = panel.getY();
		int width = panel.getWidth();
		int height = panel.getHeight();
		for(int i = width; i < finalX + 1; i++) {
			panel.setBounds(x, y, i, height);
		}
	}
	
	public static void moveLeftLeftStill(JPanel panel, int finalX) {
		int x = panel.getX();
		int y = panel.getY();
		int width = panel.getWidth();
		int height = panel.getHeight();
		for(int i = width; i > finalX - 1; i--) {
			panel.setBounds(x, y, i, height);
		}
	}
}
