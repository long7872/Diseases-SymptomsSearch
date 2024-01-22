package ResultMenu;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import DataAccessObject.DAOHealthIssues;
import DataAccessObject.DAOHealthStatus;
import DataAccessObject.DAOSeverity;
import DataAccessObject.DAOSymptom;
import MainMenu.GUIMain;
import Table.HealthIssues;
import Table.HealthStatus;
import Table.Severity;
import Table.Symptom;

public class GUIResult extends JFrame {
	private static final long serialVersionUID = 2L;
	
	JButton back;
	JTextField txt;
	JTextPane textPane = new JTextPane();
	StyledDocument doc = textPane.getStyledDocument();

	public GUIResult(String txt){
		addControl();
		this.txt.setText(txt);
		addEvent();
		displayResult();
		showWindow();
	}
	
	private Style fontTitle() {
        Style style = doc.addStyle("Style", null);
        StyleConstants.setFontSize(style, 20);
        StyleConstants.setFontFamily(style, ".VnVogue");
        StyleConstants.setBold(style, false);
        return style;
	}
	private Style fontSubTitle() {
        Style style = doc.addStyle("Style", null);
        StyleConstants.setFontSize(style, 17);
        StyleConstants.setFontFamily(style, "Calibri (Body)");
        StyleConstants.setBold(style, false);
        return style;
	}
	private Style fontDetails() {
        Style style = doc.addStyle("Style", null);
        StyleConstants.setFontSize(style, 15);
        StyleConstants.setFontFamily(style, "Arial");
        StyleConstants.setBold(style, false);
        return style;
	}
	
	public void close() {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.dispose();
	}
	
	public void create() {
		new GUIResult(txt.getText());
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.dispose();
	}
	
	public void addEvent() {
		
		back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new GUIMain();
				close();
				super.mouseClicked(e);
			}
		});
		
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
					create();
				}
			}
		});
		
	}
	
	public void addControl() {
		
		Font fontTxt = new Font("Arial", Font.PLAIN, 20);
		
		Container con = getContentPane();
		
		JPanel upper = new JPanel();
		JPanel lower = new JPanel();
		JSplitPane all = new JSplitPane(JSplitPane.VERTICAL_SPLIT,upper,lower);
		all.setDividerLocation(72);
		all.setDividerSize(1);
		all.setEnabled(false);
		con.add(all);
		
		upper.setLayout(new FlowLayout(FlowLayout.LEFT));
		back = new JButton("Back");
		upper.add(back);
		
		txt = new JTextField();
		txt.setPreferredSize(new Dimension(600,30));
		txt.setFont(fontTxt);
		upper.add(Box.createVerticalStrut(63));
		upper.add(txt);
		
		lower.setLayout(new FlowLayout(FlowLayout.LEFT));
		textPane.setEditable(false);
		textPane.setPreferredSize(new Dimension(1240,580));
		
		JScrollPane displayInf = new JScrollPane(textPane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		lower.add(displayInf);
		
		
		
		
	}
	
	public void displayResult() {
		String find = txt.getText();
		Symptom findId = new Symptom();
		findId.setNameSymptom(find);
		Symptom result = DAOSymptom.getInstance().selectByName(findId);
		if (result!=null) {
			HealthStatus findAll = new HealthStatus();
			findAll.setIdSymptom(result.getIdSymptom());
			ArrayList<HealthStatus> allResult = DAOHealthStatus.getInstance().selectAllId(findAll);
			ArrayList<HealthIssues> query = new ArrayList<HealthIssues>();
			for (HealthStatus n : allResult) {
				HealthIssues out = new HealthIssues();
				out.setIdHealthIssues(n.getIdHealthIssues());
				HealthIssues output = DAOHealthIssues.getInstance().selectById(out);
				query.add(output);
			}
			
			if (query.size()!=0) {
				query.sort((o1, o2) -> o1.compareTo(o2));
				
				try {
					doc.insertString(doc.getLength(), "   Mô tả: " + result.getDescription()+"\n\n", fontSubTitle());
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
				
				for (HealthIssues u : query) {
					Severity findLevel = new Severity();
					findLevel.setIdSeverity(u.getIdSeverity());
					Severity level = DAOSeverity.getInstance().selectById(findLevel);
					try {
						doc.insertString(doc.getLength(), u.getNameHealthIssues()+" - "+u.getCategory()+"\n", fontTitle());
						doc.insertString(doc.getLength(), "Mức độ: "+level.getIdSeverity()+" - "+level.getLevelSeverity()+" \t<"+
						level.getDescription()+"> "+"\n", fontSubTitle());
						doc.insertString(doc.getLength(), "Nguyên nhân: "+u.getCausesOfHealthIssues()+"\n\n", fontDetails());
					} catch (BadLocationException e) {
						e.printStackTrace();
					}
				}
				try {
					doc.insertString(doc.getLength(), "\n\n   \"Đã tìm kiếm thấy "+query.size()+" kết quả\"", fontSubTitle());
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			} else {
				try {
					doc.insertString(doc.getLength(), "Phát hiện triệu chứng nhưng chưa thấy những vấn đề sức khỏe liên quan", fontDetails());
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			}
			
		} else {
			try {
				doc.insertString(doc.getLength(), "Không có triệu chứng liên quan hoặc chưa cập nhật vào cơ sở dữ liệu", fontDetails());
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void showWindow() {
		this.pack();
		this.setSize(1280, 720);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

}
