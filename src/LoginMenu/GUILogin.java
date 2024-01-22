package LoginMenu;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import DataAccessObject.DAOUserAccount;
import ImportMenu.GUIImport;
import ImportMenu.SubPanel;
import MainMenu.GUIMain;
import Table.UserAccount;

public class GUILogin extends JFrame {
	private static final long serialVersionUID = 5L;
	
	private JButton exit;
	private JTextField username, password;
	private JButton login;
	private GUIMain t;

	public GUILogin(GUIMain t) {
		this.t=t;
		addControl();
		addEvent();
		showWindow();
	}
	
	public void check() {
		String usernameFind = username.getText();
		UserAccount find = new UserAccount();
		find.setUsernameAcc(usernameFind);
		
		try {
			if (username.getText().equals(DAOUserAccount.getInstance().selectByName(find).getUsernameAcc())) {
				String passwordAcc = DAOUserAccount.getInstance().selectByName(find).getPasswordAcc();
				if (password.getText().equals(passwordAcc)) {
					new GUIImport();
					t.close();
					close();
				}
			}
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập lại tài khoản và mật khẩu", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void close() {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.dispose();
	}
	
	public void addEvent() {
		
		exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				close();
				super.mouseClicked(e);
			}
		});
		
		password.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					check();
				}
				super.keyPressed(e);
			}
		});
		
		login.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				check();
				super.mouseClicked(e);
			}
		});
		
	}
	
	public void addControl() {
		
		Container con = getContentPane();
		JPanel pnMain = new JPanel();
		pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));
		con.add(pnMain);
		
		JPanel pnButtonExit = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		Image exitImage = Toolkit.getDefaultToolkit().createImage(GUIMain.class.getResource("/Image/Exit.png"));
		exit = new JButton(new ImageIcon(exitImage));
		exit.setContentAreaFilled(false);
		exit.setBorder(new EmptyBorder(0,0,0,0));
		pnButtonExit.add(exit);
		pnMain.add(pnButtonExit);
		
		JPanel pnTitle = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel title = new JLabel("Login to access");
		Font font = new Font("Arial", Font.BOLD, 30);
		title.setFont(font);
		pnTitle.add(title);
		pnMain.add(pnTitle);
		
		username = new JTextField();
		pnMain.add(SubPanel.textPanel(username, "Username", 300, 50));
		password = new JTextField();
		pnMain.add(SubPanel.textPanel(password, "Password", 300, 50));
		
		JPanel pnButtonLogin = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pnButtonLogin.setPreferredSize(new Dimension(300,30));
		login = new JButton("LOGIN");
		Font fontLogin = new Font("Arial", Font.BOLD, 20);
		login.setPreferredSize(new Dimension(300,30));
		login.setFont(fontLogin);
		pnButtonLogin.add(login);
		pnMain.add(pnButtonLogin);
		pnMain.add(Box.createVerticalStrut(80));
		
		
	}
	
	public void showWindow() {
		
		this.setUndecorated(true);
		this.pack();
		this.setSize(426, 260);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}

}
