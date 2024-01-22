package MainMenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.OverlayLayout;
import javax.swing.Timer;

import LoginMenu.GUILogin;
import ResultMenu.GUIResult;

public class GUIMain extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private Timer checkPosition;
	private JTextField txt;
	private JButton btnCre, btnChangeMode;
	private PanelAnnouncement t = new PanelAnnouncement("Chi Tiết");
	private PanelAnnouncement n = new PanelAnnouncement("Đổi chế độ dành cho người nhập");

	public GUIMain() {
		addControl();
		addEvent();
		showWindow();
	}
	
	public String getTxt() {
		return txt.getText();
	}
	
	public void open() {
		this.setVisible(true);
	}
	
	public void close() {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.dispose();
	}
	
	public void displayGUILogin() {
		new GUILogin(this);
		n.Close();
		
	}
	
	public void addEvent() {
		
		checkPosition = new Timer(1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sendPositionToButtonCredit();
				sendPositionToButtonChange();
			}
		});
		checkPosition.setInitialDelay(500);
		checkPosition.start();
		
//		button change event ------------------------------------------------------------

		btnChangeMode.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {
				n.Close();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				n.Open();
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				displayGUILogin();
			}
		});
		
//		button change event ------------------------------------------------------------
		
//		button credit event ------------------------------------------------------------
		
		btnCre.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {
				t.Close();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				t.Open();
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "Developer:     Trương Minh Khánh Long (VKU)\n"
												  + "Data provider: Lê Minh Hoàng (YDH)"
						, "CREDIT", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
//		button credit event ------------------------------------------------------------
		
		txt.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					new GUIResult(getTxt());
					close();
				}
			}
		});
		
	}
	
	private void sendPositionToButtonCredit() {
		t.setLocation(this.getX()+this.getWidth()-t.getWidth()-10, this.getY()+this.getHeight()-5);
	}
	
	private void sendPositionToButtonChange() {
		n.setLocation(this.getX()+10, this.getY()+this.getHeight()-5);
	}
	
	public void addControl() {
		
		//Tạo layer pane
		JLayeredPane pane = getLayeredPane();
		//Set Layout Overlay chồng lên. Dùng vị trí tương đối
		pane.setLayout(new OverlayLayout(pane));
		
		
		JPanel pnBg = new JPanel();
		pnBg.setLayout(new BorderLayout());
		//Tạo Label làm background
		JLabel bgLbl = new JLabel();
		//Lấy ảnh
		Image bgImage = Toolkit.getDefaultToolkit().createImage(GUIMain.class.getResource("/Image/MainBackground.jpg"));
		//Thêm ảnh vào Label
		bgLbl.setIcon(new ImageIcon(bgImage));
		
		pnBg.add(bgLbl, BorderLayout.SOUTH);
		//Thêm Background vào layer và đặt thấp nhất
		pane.add(pnBg, JLayeredPane.DEFAULT_LAYER);		
		
		//Lưu trữ font và màu
		Font font = new Font("Arial", Font.BOLD, 30);
		Font fontSub = new Font("Arial", Font.BOLD,15);
		Font fontTxt = new Font("Arial", Font.PLAIN, 20);
		Color color1 = new Color(19, 108, 251);
		Color color2 = new Color(60, 132, 251);
		Color color3 = new Color(2, 51, 130);
		
		//Tạo panel chính
		JPanel pnMain = new JPanel();
		pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));
		
		
		//Thêm panel chính vào layer cao hơn
		pane.add(pnMain, JLayeredPane.PALETTE_LAYER);
		
		//Tạo khoảng cách bên trên đẩy tiêu đề xuống dưới
		pnMain.add(Box.createVerticalStrut(220));
		
		//Tiêu đề
		JPanel pnTitle = new JPanel();
		pnTitle.setLayout(new FlowLayout());
		
		JLabel lbltitle1 = new JLabel("C");
		lbltitle1.setFont(font);
		lbltitle1.setForeground(color1);
		
		JLabel lbltitle2 = new JLabel("a");
		lbltitle2.setFont(font);
		lbltitle2.setForeground(color2);
		
		JLabel lbltitle3 = new JLabel("r");
		lbltitle3.setFont(font);
		lbltitle3.setForeground(color3);
		
		JLabel lbltitle4 = new JLabel("e");
		lbltitle4.setFont(font);
		lbltitle4.setForeground(color1);
		
		JLabel lbltitle5 = new JLabel("A");
		lbltitle5.setFont(font);
		lbltitle5.setForeground(color2);
		
		JLabel lbltitle6 = new JLabel("w");
		lbltitle6.setFont(font);
		lbltitle6.setForeground(color3);
		
		JLabel lbltitle7 = new JLabel("a");
		lbltitle7.setFont(font);
		lbltitle7.setForeground(color1);
		
		JLabel lbltitle8 = new JLabel("r");
		lbltitle8.setFont(font);
		lbltitle8.setForeground(color2);
		
		JLabel lbltitle9 = new JLabel("e");
		lbltitle9.setFont(font);
		lbltitle9.setForeground(color3);
		
		pnTitle.add(lbltitle1);
		pnTitle.add(lbltitle2);
		pnTitle.add(lbltitle3);
		pnTitle.add(lbltitle4);
		pnTitle.add(lbltitle5);
		pnTitle.add(lbltitle6);
		pnTitle.add(lbltitle7);
		pnTitle.add(lbltitle8);
		pnTitle.add(lbltitle9);
		
		//Thêm tiêu đề vào panel chính
		pnMain.add(pnTitle);
		
		
		//Tiêu đề phụ
		JPanel pnSubTitle = new JPanel();
		pnSubTitle.setLayout(new FlowLayout());
		JLabel lblSubTitle = new JLabel("Chăm sóc sức khỏe của bạn");
		lblSubTitle.setFont(fontSub);
		lblSubTitle.setForeground(Color.BLACK);
		pnSubTitle.add(lblSubTitle);
		//Tạo khoảng cách trên trái đẩy tiêu đề phụ sang bên phải
		pnSubTitle.add(Box.createHorizontalStrut(-350));
		
		//Thêm tiêu đề phụ vào panel chính
		pnMain.add(pnSubTitle);
		
		//Tạo khoảng cách bên dưới đẩy tiêu đề phụ lên trên
		pnMain.add(Box.createVerticalStrut(70));
		
		//Tạo khung search
		JPanel pnTxt = new JPanel();
		txt = new JTextField();
		txt.setPreferredSize(new Dimension(600,30));
		txt.setFont(fontTxt);
		pnTxt.add(txt);
		
		//Thêm khung search vào panel chính
		pnMain.add(pnTxt);
		
		//Tạo khoảng cách bên dưới đẩy khung search lên trên
		pnMain.add(Box.createVerticalStrut(230));
		
		//Tạo nút thông tin
		JPanel pnCre = new JPanel();
		pnCre.setLayout(new FlowLayout());
		
		Image imgCI = Toolkit.getDefaultToolkit().createImage(GUIMain.class.getResource("/Image/Exchange.png"));
		btnChangeMode = new JButton(new ImageIcon(imgCI));
		btnChangeMode.setContentAreaFilled(false);
		btnChangeMode.setBorder(BorderFactory.createEmptyBorder());
		
		Image img = Toolkit.getDefaultToolkit().createImage(GUIMain.class.getResource("/Image/Button.png"));
		//Đặt lại kích thước ảnh
		Image scaledImg = img.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		btnCre = new JButton(new ImageIcon(scaledImg));
		//Xóa vùng button
		btnCre.setContentAreaFilled(false);
		//Xóa viền
		btnCre.setBorder(BorderFactory.createEmptyBorder());
		
		pnCre.add(btnChangeMode);
		pnCre.add(Box.createHorizontalStrut(1170));
		pnCre.add(btnCre);
		//Thêm nút vào panel chính
		pnMain.add(pnCre);
		
		
		pnMain.setOpaque(false);
		pnTitle.setOpaque(false);
		pnSubTitle.setOpaque(false);
		pnTxt.setOpaque(false);
		pnCre.setOpaque(false);
	
	}
	
	public void showWindow() {
		
		this.pack();
		this.setSize(1280, 720);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);	
		
	}
	
	public static void main(String[] args) {
		
		new GUIMain();
	}

}
