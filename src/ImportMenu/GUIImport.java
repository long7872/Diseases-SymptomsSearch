package ImportMenu;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;

import MainMenu.GUIMain;
import MainMenu.PanelAnnouncement;
import Table.HealthIssues;
import Table.Symptom;

public class GUIImport extends JFrame {
	private static final long serialVersionUID = 4L;
	
	private Timer checkPosition;
	private JButton back;
	
	private JPanel upper;
	private JTextField nameHealthIssues, nameSymptom;
	private JButton insertStatus, updateStatus, deleteStatus;
	private JTextField updateNameHealthIssues, updateNameSymptom;
	private JButton confirmUpper;
	private PanelAnnouncement announceDeleteStatus = new PanelAnnouncement("Nhập vào cả hai phần tên để tìm kiếm dữ liệu để xóa thông tin liên quan");
	
	private JPanel left;
	private JTextField nameHealthIssuesLP, causesOfHealthIssues;
	private JComboBox<String> category, idSeverity;
	private JButton insertIssuesLP, updateIssuesLP, deleteIssuesLP;
	private JTextField updateNameHealthIssuesLP;
	private JButton confirmLeft;
	private PanelAnnouncement announceDeleteIssuesLP = new PanelAnnouncement("Nhập vào phần tên để tìm kiếm dữ liệu để xóa thông tin liên quan");
	
	private JPanel right;
	private JTextField nameSymptomRP, description;
	private JButton insertSymptomRP, updateSymptomRP, deleteSymptomRP;
	private JTextField updateNameSymptomRP;
	private JButton confirmRight;
	private PanelAnnouncement announceDeleteSymptomRP = new PanelAnnouncement("Nhập vào phần tên để tìm kiếm dữ liệu để xóa thông tin liên quan");
	
	JPanel list;
	DefaultListModel<String> listModelIssues;
	DefaultListModel<String> listModelSymptom;
	JScrollPane issuesScroll;
	JScrollPane symptomScroll;
	
	public GUIImport() {
		addControl();
		addEvent();
		showWindow();
	}
	
	public void close() {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.dispose();
	}
	
	public void addEvent() {
		
		checkPosition = new Timer(1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sendPositionToButtonDeleteStatus();
				sendPositionToButtonDeleteIssuesLP();
				sendPositionToButtonDeleteSymptomRP();
			}
		});
		checkPosition.setInitialDelay(500);
		checkPosition.start();
		
		back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new GUIMain();
				close();
				super.mouseClicked(e);
			}
		});
		
		nameHealthIssues.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					addIssuesListComponent();
				}
				super.keyPressed(e);
			}
		});
		
		nameSymptom.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					addSymptomListComponent();
				}
				super.keyPressed(e);
			}
		});
		
		insertStatus.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EventHandle.insertStatus(nameHealthIssues, nameSymptom);
				super.mouseClicked(e);
			}
		});
		
		updateStatus.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addUpdateStatusComponent();
				super.mouseClicked(e);
			}
		});
		
		deleteStatus.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EventHandle.deleteStatus(nameHealthIssues, nameSymptom);
				new GUIImport();
				announceDeleteStatus.Close();
				close();
				super.mouseClicked(e);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				announceDeleteStatus.Close();
				super.mouseExited(e);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				announceDeleteStatus.Open();
				super.mouseEntered(e);
			}
		});
		
		insertIssuesLP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EventHandle.insertIssues(nameHealthIssuesLP, category, causesOfHealthIssues, idSeverity);
				new GUIImport();
				close();
				super.mouseClicked(e);
			}
		});
		
		updateIssuesLP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addUpdateIssuesComponent();
				super.mouseClicked(e);
			}
		});
		
		deleteIssuesLP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EventHandle.deleteIssues(nameHealthIssuesLP);
				new GUIImport();
				announceDeleteIssuesLP.Close();
				close();
				super.mouseClicked(e);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				announceDeleteIssuesLP.Close();
				super.mouseExited(e);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				announceDeleteIssuesLP.Open();
				super.mouseEntered(e);
			}
		});
		
		insertSymptomRP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EventHandle.insertSymptom(nameSymptomRP, description);
				new GUIImport();
				close();
				super.mouseClicked(e);
			}
		});
		
		updateSymptomRP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addUpdateSymptomComponent();
				super.mouseClicked(e);
			}
		});
		
		deleteSymptomRP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EventHandle.deleteSymptom(nameSymptomRP);
				new GUIImport();
				announceDeleteSymptomRP.Close();
				close();
				super.mouseClicked(e);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				announceDeleteSymptomRP.Close();
				super.mouseExited(e);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				announceDeleteSymptomRP.Open();
				super.mouseEntered(e);
			}
		});
		
	}
	
	public void addControl() {
		
		Font font = new Font("Arial", Font.BOLD, 17);
		
		Container con = getContentPane();
		
		upper = new JPanel();
		JPanel lower = new JPanel();
		
		JSplitPane main = new JSplitPane(JSplitPane.VERTICAL_SPLIT, upper, lower);
		main.setDividerLocation(260);
		main.setDividerSize(3);
		main.setEnabled(false);
		
		con.add(main);
		
//		upper setting
		
		upper.setLayout(new BoxLayout(upper, BoxLayout.Y_AXIS));
		back = new JButton("Back");
		upper.add(SubPanel.infoPanel(back, "Trạng thái sinh lí"));
		nameHealthIssues = new JTextField();		
		nameSymptom = new JTextField();
		JPanel pnTxtStatus = new JPanel();
		pnTxtStatus.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnTxtStatus.add(SubPanel.textPanel(nameHealthIssues, "Tên vấn đề sức khỏe", 500, 50));
		pnTxtStatus.add(Box.createHorizontalStrut(50));
		pnTxtStatus.add(SubPanel.textPanel(nameSymptom, "Tên triệu chứng", 500, 50));
		upper.add(pnTxtStatus);
		
		list = new JPanel(new FlowLayout(FlowLayout.LEFT));
		list.add(Box.createHorizontalStrut(2));
		listModelIssues = new DefaultListModel<>();
		JList<String> autoIssuesList = new JList<>(listModelIssues);
		autoIssuesList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		issuesScroll = new JScrollPane(autoIssuesList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		issuesScroll.setPreferredSize(new Dimension(500,80));
		list.add(issuesScroll);
		
		list.add(Box.createHorizontalStrut(60));
		
		listModelSymptom = new DefaultListModel<>();
		JList<String> autoSymptomList = new JList<>(listModelSymptom);
		symptomScroll = new JScrollPane(autoSymptomList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		symptomScroll.setPreferredSize(new Dimension(500,80));
		list.add(symptomScroll);
		
		upper.add(list);
		
		insertStatus = new JButton(); updateStatus = new JButton(); deleteStatus = new JButton();
		upper.add(SubPanel.buttonPane(insertStatus, updateStatus, deleteStatus));
		
//		upper setting
		
//		lower setting

		lower.setLayout(new BorderLayout());
		left = new JPanel();
		right = new JPanel();
		JSplitPane bottom = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, left, right);
		lower.add(bottom);
		bottom.setDividerLocation(640);
		bottom.setDividerSize(5);
		bottom.setEnabled(false);
		
//		left setting
		
		left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
		
		JPanel titleLeft = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel infoIssuesLP = new JLabel("Phần nhập dữ liệu về các vấn đề sức khỏe");
		infoIssuesLP.setFont(font);
		titleLeft.add(infoIssuesLP);
		left.add(titleLeft);
		
		JPanel pnTxtIssues1 = new JPanel();
		pnTxtIssues1.setLayout(new FlowLayout(FlowLayout.LEFT));
		nameHealthIssuesLP = new JTextField();
		pnTxtIssues1.add(SubPanel.textPanel(nameHealthIssuesLP, "Tên vấn đề sức khỏe", 300, 50));
		category = new JComboBox<String>();
		String[] s1 = {"","Bệnh", "Ngộ độc"};
		pnTxtIssues1.add(SubPanel.boxPanel(category, s1, "Loại", 150, 50));
		
		
		JPanel pnTxtIssues2 = new JPanel();
		pnTxtIssues2.setLayout(new FlowLayout(FlowLayout.LEFT));
		causesOfHealthIssues = new JTextField();
		pnTxtIssues2.add(SubPanel.textPanel(causesOfHealthIssues, "Nguyên nhân", 300, 50));
		idSeverity = new JComboBox<String>();
		String[] s2 = {"","Rất nặng", "Nặng", "Trung Bình", "Nhẹ", "Rất nhẹ"};
		pnTxtIssues2.add(SubPanel.boxPanel(idSeverity, s2, "Mức độ", 150, 50));
		category.setPreferredSize(idSeverity.getPreferredSize());
		
		left.add(Box.createVerticalStrut(70));
		left.add(pnTxtIssues1);
		left.add(pnTxtIssues2);
		insertIssuesLP = new JButton(); updateIssuesLP = new JButton(); deleteIssuesLP = new JButton();
		left.add(SubPanel.buttonPane(insertIssuesLP, updateIssuesLP, deleteIssuesLP));
		
//		left setting
		
//		right setting
		
		right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
		
		JPanel titleRight = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel infoSymptom = new JLabel("Phần nhập dữ liệu về triệu chứng");
		infoSymptom.setFont(font);
		titleRight.add(infoSymptom);
		right.add(titleRight);
		
		JPanel pnTxtSymptom1 = new JPanel();
		pnTxtSymptom1.setLayout(new FlowLayout(FlowLayout.LEFT));
		nameSymptomRP = new JTextField();
		pnTxtSymptom1.add(SubPanel.textPanel(nameSymptomRP, "Tên triệu chứng", 300, 50));
		
		
		JPanel pnTxtSymptom2 = new JPanel();
		pnTxtSymptom2.setLayout(new FlowLayout(FlowLayout.LEFT));
		description = new JTextField();
		pnTxtSymptom2.add(SubPanel.textPanel(description, "Mô tả", 300, 50));
		
		right.add(Box.createVerticalStrut(70));
		right.add(pnTxtSymptom1);
		right.add(pnTxtSymptom2);
		insertSymptomRP = new JButton(); updateSymptomRP = new JButton(); deleteSymptomRP = new JButton();
		right.add(SubPanel.buttonPane(insertSymptomRP, updateSymptomRP, deleteSymptomRP));
		
//		right setting
		
//		lower setting
		
	}
	
	private void addIssuesListComponent() {
		listModelIssues.clear();
		ArrayList<HealthIssues> auto = EventHandle.autoCompleteNameIssues(nameHealthIssues);
		auto.sort((o1, o2) -> o1.compareToL(o2));
		for (HealthIssues n : auto) {
			listModelIssues.addElement(n.getNameHealthIssues());
		}
	}
	
	private void addSymptomListComponent() {
		listModelSymptom.clear();
		ArrayList<Symptom> auto = EventHandle.autoCompleteNameSymptom(nameSymptom);
		auto.sort((o1, o2) -> o1.compareTo(o2));
		for (Symptom n : auto) {
			listModelSymptom.addElement(n.getNameSymptom());
		}
	}
	
	private void addUpdateStatusComponent(){
		JPanel pnUpdateStatus = new JPanel();
		pnUpdateStatus.setLayout(new FlowLayout(FlowLayout.LEFT));
		updateNameHealthIssues = new JTextField();
		pnUpdateStatus.add(SubPanel.textPanel(updateNameHealthIssues, "Tên vấn đề sức khỏe cần cập nhật", 500, 50));
		pnUpdateStatus.add(Box.createHorizontalStrut(50));
		updateNameSymptom = new JTextField();
		pnUpdateStatus.add(SubPanel.textPanel(updateNameSymptom, "Tên triệu chứng cần cập nhật", 500, 50));
		confirmUpper = new JButton("Xác nhận");
		pnUpdateStatus.add(confirmUpper);
		upper.add(pnUpdateStatus);
		upper.revalidate();
		upper.repaint();
		
		confirmUpper.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EventHandle.updateStatus(nameHealthIssues, nameSymptom, updateNameHealthIssues, updateNameSymptom);
				new GUIImport();
				close();
				super.mouseClicked(e);
			}
		});
		
	}
	
	private void addUpdateIssuesComponent() {
		JPanel pnUpdateIssues = new JPanel();
		pnUpdateIssues.setLayout(new FlowLayout(FlowLayout.LEFT));
		updateNameHealthIssuesLP = new JTextField();
		pnUpdateIssues.add(SubPanel.textPanel(updateNameHealthIssuesLP, "Tên vấn đề sức khỏe cần cập nhật", 300, 50));
		confirmLeft = new JButton("Xác nhận");
		pnUpdateIssues.add(confirmLeft);
		left.add(pnUpdateIssues);
		left.revalidate();
		left.repaint();
		
		confirmLeft.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EventHandle.updateIssues(nameHealthIssuesLP, category, causesOfHealthIssues, idSeverity, updateNameHealthIssuesLP);
				new GUIImport();
				close();
				super.mouseClicked(e);
			}
		});
		
	}
	
	private void addUpdateSymptomComponent() {
		JPanel pnUpdateSymptom = new JPanel();
		pnUpdateSymptom.setLayout(new FlowLayout(FlowLayout.LEFT));
		updateNameSymptomRP = new JTextField();
		pnUpdateSymptom.add(SubPanel.textPanel(updateNameSymptomRP, "Tên triệu chứng cần cập nhật", 300, 50));
		confirmRight = new JButton("Xác nhận");
		pnUpdateSymptom.add(confirmRight);
		right.add(pnUpdateSymptom);
		right.revalidate();
		right.repaint();
		
		confirmRight.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EventHandle.updateSymptom(nameSymptomRP, description, updateNameSymptomRP);
				new GUIImport();
				close();
				super.mouseClicked(e);
			}
		});
	}
	
	private void sendPositionToButtonDeleteStatus() {
		announceDeleteStatus.setLocation(this.getX()+this.getWidth()/4-20, this.getY()+this.getHeight()/3+20);
	}
	
	private void sendPositionToButtonDeleteIssuesLP() {
		announceDeleteIssuesLP.setLocation(this.getX()+this.getWidth()/4-20, this.getY()+this.getHeight()*7/8+50);
	}
	
	private void sendPositionToButtonDeleteSymptomRP(){
		announceDeleteSymptomRP.setLocation(this.getX()+this.getWidth()*3/4-20, this.getY()+this.getHeight()*7/8+50);
	}
	
	public void showWindow() {
		
		this.setSize(1280, 720);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);	
		
	}
	
	public static void main(String[] args) {
		new GUIImport();
	}

}
