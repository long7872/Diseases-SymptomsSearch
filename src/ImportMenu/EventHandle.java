package ImportMenu;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import DataAccessObject.DAOHealthIssues;
import DataAccessObject.DAOHealthStatus;
import DataAccessObject.DAOSymptom;
import Table.HealthIssues;
import Table.HealthStatus;
import Table.Symptom;

public class EventHandle {
	
	public static ArrayList<HealthIssues> autoCompleteNameIssues(JTextField firstText) {
		String txt1 = firstText.getText();
		System.out.println(txt1);
		ArrayList<HealthIssues> result = DAOHealthIssues.getInstance().selectAllByName(txt1);
		return result;
	}
	
	public static ArrayList<Symptom> autoCompleteNameSymptom(JTextField firstText) {
		String txt1 = firstText.getText();
		System.out.println(txt1);
		ArrayList<Symptom> result = DAOSymptom.getInstance().selectAllByName(txt1);
		return result;
	}
	
	public static void insertStatus(JTextField firstText, JTextField secondText) {
		String txt1 = firstText.getText();
		String txt2 = secondText.getText();
		HealthIssues find1 = new HealthIssues();
		find1.setNameHealthIssues(txt1);
		HealthIssues resultId1 = DAOHealthIssues.getInstance().selectByName(find1);
		Symptom find2 = new Symptom();
		find2.setNameSymptom(txt2);
		Symptom resultId2 = DAOSymptom.getInstance().selectByName(find2);
		if (resultId1!=null && resultId2!=null) {
			HealthStatus result = new HealthStatus(resultId1.getIdHealthIssues(), resultId2.getIdSymptom());
			
			HealthStatus check = DAOHealthStatus.getInstance().selectById(result);
			if (check==null) {
				DAOHealthStatus.getInstance().insert(result);
				JOptionPane.showMessageDialog(null, "Đã thêm vào thành công", "SUCCESFULLY", JOptionPane.OK_OPTION);
			} else {
				JOptionPane.showMessageDialog(null, "Đã tồn tại", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Không tìm thấy tên vấn đề sức khỏe hoặc tên triệu chứng từ kho dữ liệu để thêm vào\n"
					+ "Vui lòng thêm dữ liệu trước khi cập nhật", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void updateStatus(JTextField firstUpdate, JTextField secondUpdate, JTextField firstText, JTextField secondText) {
		String txt1 = firstUpdate.getText();
		String txt2 = secondUpdate.getText();
		String txt3 = firstText.getText();
		String txt4 = secondText.getText();
		
		if (txt1.length()!=0 && txt2.length()!=0 && txt3.length()!=0 && txt4.length()!=0) {
			HealthIssues find1 = new HealthIssues();
			find1.setNameHealthIssues(txt3);
			HealthIssues resultId1 = DAOHealthIssues.getInstance().selectByName(find1);
			Symptom find2 = new Symptom();
			find2.setNameSymptom(txt4);
			Symptom resultId2 = DAOSymptom.getInstance().selectByName(find2);
			if (resultId1!=null && resultId2!=null) {
				HealthStatus outdated = new HealthStatus(resultId1.getIdHealthIssues(), resultId2.getIdSymptom());
				
				HealthIssues find3 = new HealthIssues();
				find3.setNameHealthIssues(txt1);
				HealthIssues resultId3 = DAOHealthIssues.getInstance().selectByName(find3);
				Symptom find4 = new Symptom();
				find4.setNameSymptom(txt2);
				Symptom resultId4 = DAOSymptom.getInstance().selectByName(find4);
				if (resultId3!=null && resultId4!=null) {
					HealthStatus updated = new HealthStatus(resultId3.getIdHealthIssues(), resultId4.getIdSymptom());
					DAOHealthStatus.getInstance().updateByOther(updated, outdated);
					
					JOptionPane.showMessageDialog(null, "Đã cập nhật thành công", "SUCCESFULLY", JOptionPane.OK_OPTION);
				} else {
					JOptionPane.showMessageDialog(null, "Không tìm thấy tên mới cập nhật vấn đề sức khỏe hoặc tên mới cập nhật triệu chứng từ kho dữ liệu để cập nhật\n"
					+ "Vui lòng thêm dữ liệu trước khi cập nhật", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Không tìm thấy tên cũ để cập nhật vấn đề sức khỏe hoặc tên cũ cập nhật triệu chứng từ kho dữ liệu để cập nhật\n"
				+ "Vui lòng thêm dữ liệu trước khi cập nhật", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			
		} else {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng và đầy đủ thông tin", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void deleteStatus(JTextField firstText, JTextField secondText) {
		String txt1 = firstText.getText();
		String txt2 = secondText.getText();
		HealthIssues find1 = new HealthIssues();
		find1.setNameHealthIssues(txt1);
		Symptom find2 = new Symptom();
		find2.setNameSymptom(txt2);
		HealthStatus result = DAOHealthStatus.getInstance().selectByName(find1, find2);
		if (result!=null) {
			DAOHealthStatus.getInstance().delete(result);
			JOptionPane.showMessageDialog(null, "Đã xóa thành công", "SUCCESFULLY", JOptionPane.OK_OPTION);
		} else {
			JOptionPane.showMessageDialog(null, "Không tìm thấy tên vấn đề sức khỏe hoặc tên triệu chứng từ kho dữ liệu để xóa\n"
					+ "Vui lòng thêm dữ liệu trước khi cập nhật", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void insertIssues(JTextField firstText, JComboBox<String> secondText, JTextField thirdText, JComboBox<String> fourthText) {
		String txt1 = firstText.getText();
		String txt2 =  (String) secondText.getSelectedItem();
		String txt3 = thirdText.getText();
		int id = fourthText.getSelectedIndex();
		if (txt1.length()!=0 && txt2.length()!=0 && txt3.length()!=0 && id!=0) {
			HealthIssues result = new HealthIssues(txt1, txt2, txt3, id);
			HealthIssues check = DAOHealthIssues.getInstance().selectByName(result);
			if (check==null) {
				DAOHealthIssues.getInstance().insert(result);
				JOptionPane.showMessageDialog(null, "Đã thêm vào thành công", "SUCCESFULLY", JOptionPane.OK_OPTION);
			} else {
				JOptionPane.showMessageDialog(null, "Đã tồn tại tên vấn đề sức khỏe trong cơ sở dữ liệu", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng và đầy đủ thông tin", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void updateIssues(JTextField firstText, JComboBox<String> secondText, JTextField thirdText, JComboBox<String> fourthText, JTextField fifthText) {
		String txt1 = firstText.getText();
		String txt2 =  (String) secondText.getSelectedItem();
		String txt3 = thirdText.getText();
		int id = fourthText.getSelectedIndex();
		String txt5 = fifthText.getText();
		
		if (txt1.length()!=0 && txt2.length()!=0 && txt3.length()!=0 && id!=0 && txt5.length()!=0) {
			HealthIssues findId = new HealthIssues();
			findId.setNameHealthIssues(txt5);
			HealthIssues resultId = DAOHealthIssues.getInstance().selectByName(findId);
			if (resultId!=null) {
				HealthIssues result = new HealthIssues(resultId.getIdHealthIssues(),txt1, txt2, txt3, id);
				DAOHealthIssues.getInstance().update(result);
				JOptionPane.showMessageDialog(null, "Đã cập nhật thành công", "SUCCESFULLY", JOptionPane.OK_OPTION);
			} else {
				JOptionPane.showMessageDialog(null, "Không tồn tại tên vấn đề sức khỏe cần cập nhật trong hệ thống", "ERROR", JOptionPane.ERROR_MESSAGE);
			} 
			
		} else {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng và đầy đủ thông tin", "ERROR", JOptionPane.ERROR_MESSAGE);
		}		
	}

	public static void deleteIssues(JTextField firstText) {
		String txt1 = firstText.getText();
		if (txt1.length()!=0) {
			HealthIssues findId = new HealthIssues();
			findId.setNameHealthIssues(txt1);
			HealthIssues result = DAOHealthIssues.getInstance().selectByName(findId);
			if (result!=null) {
				DAOHealthIssues.getInstance().delete(result);
				JOptionPane.showMessageDialog(null, "Đã xóa thành công", "SUCCESFULLY", JOptionPane.OK_OPTION);
			} else {
				JOptionPane.showMessageDialog(null, "Không tìm thấy tên vấn đề sức khỏe để xóa", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng thông tin", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void insertSymptom(JTextField firstText, JTextField secondText) {
		String txt1 = firstText.getText();
		String txt2 = secondText.getText();
		if (txt1.length()!=0 && txt2.length()!=0) {
			Symptom result = new Symptom(txt1, txt2);
			Symptom check = DAOSymptom.getInstance().selectByName(result);;
			if (check==null) {
				DAOSymptom.getInstance().insert(result);
				JOptionPane.showMessageDialog(null, "Đã thêm vào thành công", "SUCCESFULLY", JOptionPane.OK_OPTION);
			} else {
				JOptionPane.showMessageDialog(null, "Đã tồn tại tên triệu chứng trong cơ sở dữ liệu", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng và đầy đủ thông tin", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void updateSymptom(JTextField firstText, JTextField secondText, JTextField thirdText) {
		String txt1 = firstText.getText();
		String txt2 = secondText.getText();
		String txt3 = thirdText.getText();
		if (txt1.length()!=0 && txt2.length()!=0 && txt3.length()!=0) {
			Symptom find = new Symptom();
			find.setNameSymptom(txt3);
			Symptom resultId = DAOSymptom.getInstance().selectByName(find);
			if (resultId!=null) {
				Symptom result = new Symptom(resultId.getIdSymptom(), txt1, txt2);
				DAOSymptom.getInstance().update(result);
				JOptionPane.showMessageDialog(null, "Đã cập nhật thành công", "SUCCESFULLY", JOptionPane.OK_OPTION);
			} else {
				JOptionPane.showMessageDialog(null, "Không tồn tại tên vấn đề sức khỏe cần cập nhật trong hệ thống", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng và đầy đủ thông tin", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void deleteSymptom(JTextField firstText) {
		String txt1 = firstText.getText();
		if (txt1.length()!=0) {
			Symptom findId = new Symptom();
			findId.setNameSymptom(txt1);
			Symptom result = DAOSymptom.getInstance().selectByName(findId);
			if (result!=null) {
				DAOSymptom.getInstance().delete(result);
				JOptionPane.showMessageDialog(null, "Đã xóa thành công", "SUCCESFULLY", JOptionPane.OK_OPTION);
			} else {
				JOptionPane.showMessageDialog(null, "Không tìm thấy tên vấn đề sức khỏe để xóa", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng thông tin", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
}
