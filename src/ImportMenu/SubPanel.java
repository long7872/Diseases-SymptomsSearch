package ImportMenu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

public class SubPanel {
	
	public static JPanel infoPanel(JButton back, String name) {
		Font font = new Font("Arial", Font.BOLD, 17);
		JPanel infoDis = new JPanel();
		infoDis.setLayout(new FlowLayout(FlowLayout.LEFT));
		infoDis.add(back);
		JLabel nameLayerDis = new JLabel(name);
		nameLayerDis.setFont(font);
		infoDis.add(nameLayerDis);
		return infoDis;
	}
	
	public static JPanel textPanel(JTextField txt1, String nameTitle, int width, int height) {
		Border txtBorder = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		Border matte = new MatteBorder(2,2,2,2,Color.GRAY);
		Border border = new CompoundBorder(matte, txtBorder);
		TitledBorder name1Border = new TitledBorder(border, nameTitle);
		Font font = new Font("Arial", 0, 20);
		
		JPanel pnTxt = new JPanel();
		txt1.setPreferredSize(new Dimension(width, height));
		txt1.setBorder(name1Border);
		txt1.setFont(font);
		pnTxt.add(txt1);
		return pnTxt;
	}
	
	public static JPanel boxPanel(JComboBox<String> txt, String[] item, String nameTitle, int width, int height) {
		Border txtBorder = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		Border matte = new MatteBorder(2,2,2,2,Color.GRAY);
		Border border = new CompoundBorder(matte, txtBorder);
		TitledBorder name1Border = new TitledBorder(border, nameTitle);
		Font font = new Font("Arial", 0, 17);
		
		JPanel pnBox = new JPanel();
		for (int i=0; i<item.length; i++) {
			txt.addItem(item[i]);
		}
		txt.setPreferredSize(new Dimension(width, height));
		txt.setBorder(name1Border);
		txt.setFont(font);
		pnBox.add(txt);
		return pnBox;
	}
	
	public static JPanel buttonPane(JButton insert, JButton update, JButton delete) {
		JPanel pnBtn = new JPanel();
		pnBtn.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnBtn.add(Box.createHorizontalStrut(20));
		insert.setText("Insert");
		pnBtn.add(insert);
		pnBtn.add(Box.createHorizontalStrut(50));
		update.setText("Update");
		pnBtn.add(update);
		pnBtn.add(Box.createHorizontalStrut(50));
		delete.setText("Delete");
		pnBtn.add(delete);
		return pnBtn;
	}

}
