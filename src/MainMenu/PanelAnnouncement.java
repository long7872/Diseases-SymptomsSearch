package MainMenu;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelAnnouncement extends JFrame {
	private static final long serialVersionUID = 3L;

	public PanelAnnouncement(String txt){
		window(txt);
	}
	
	public void Open() {
		this.setVisible(true);
	}
	
	public void Close() {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.dispose();
	}
	
	private void window(String txt) {
		JPanel panel = new JPanel();
		
		panel.add(new JLabel(txt));
		this.setSize(1280, 720);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setUndecorated(true); 
		this.add(panel);
		this.pack();
	}

}
