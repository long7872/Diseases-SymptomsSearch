package DataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import JDBCUtil.Connect;
import Table.Symptom;

public class DAOSymptom implements DAOInterface<Symptom> {
	
	public static DAOSymptom getInstance() {
		return new DAOSymptom();
	}

	@Override
	public int insert(Symptom t) {
		int ketQua=0;
		try {
			Connection con = Connect.connecting();
			
			String sql = "INSERT INTO Symptom(nameSymptom, description)"+
					" VALUES (?,?)";
			
			PreparedStatement pst = con.prepareStatement(sql);
			
			pst.setString(1, t.getNameSymptom());
			pst.setString(2, t.getDescription());
			
			ketQua = pst.executeUpdate();
			
			System.out.println("Executed the statement: "+sql);
			System.out.println(ketQua+" rows affected");
			
			Connect.disconnecting(con);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ketQua;
	}

	@Override
	public int update(Symptom t) {
		int ketQua=0;
		try {
			Connection con = Connect.connecting();
			
			String sql = "UPDATE Symptom"+
					" SET "+
					" nameSymptom=?,"+
					" description=? "+
					" where idSymptom=?";
			
			PreparedStatement pst = con.prepareStatement(sql);
			
			pst.setString(1, t.getNameSymptom());
			pst.setString(2, t.getDescription());
			pst.setInt(3, t.getIdSymptom());
			
			ketQua = pst.executeUpdate();
			
			System.out.println("Executed the statement: "+sql);
			System.out.println(ketQua+" rows affected");
			
			Connect.disconnecting(con);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ketQua;
	}

	@Override
	public int delete(Symptom t) {
		int ketQua=0;
		try {
			Connection con = Connect.connecting();
			
			String sql = "DELETE FROM Symptom "+
					" WHERE idSymptom=?";
			
			PreparedStatement pst = con.prepareStatement(sql);
			
			pst.setInt(1, t.getIdSymptom());
			
			ketQua = pst.executeUpdate();
			
			System.out.println("Executed the statement: "+sql);
			System.out.println(ketQua+" rows affected");
			
			Connect.disconnecting(con);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ketQua;
	}

	@Override
	public ArrayList<Symptom> selectAll() {
		ArrayList<Symptom> ketQua = new ArrayList<Symptom>();
		
		try {
			Connection con = Connect.connecting();
			
			String sql = "SELECT * FROM Symptom";
			
			PreparedStatement pst = con.prepareStatement(sql);
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				int idSymptom = rs.getInt("idSymptom");
				String nameSymptom = rs.getString("nameSymptom");
				String description = rs.getString("description");
				
				Symptom ds = new Symptom(idSymptom, nameSymptom, description);
				ketQua.add(ds);
			}
			
			Connect.disconnecting(con);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ketQua;
	}
	
	@Override
	public Symptom selectById(Symptom t) {
		Symptom ketQua=null;
		try {
			Connection con = Connect.connecting();
			
			String sql = "SELECT * FROM Symptom "+
					"WHERE idSymptom=?";
			
			PreparedStatement pst = con.prepareStatement(sql);
			
			pst.setInt(1, t.getIdSymptom());
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				int idSymptom = rs.getInt("idSymptom");
				String nameSymptom = rs.getString("nameSymptom");
				String description = rs.getString("description");
				
				ketQua = new Symptom(idSymptom, nameSymptom, description);
			}
			
			Connect.disconnecting(con);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	public ArrayList<Symptom> selectAllByName(String txt) {
		ArrayList<Symptom> ketQua = new ArrayList<Symptom>();
		try {
			Connection con = Connect.connecting();
			
			String sql = "SELECT * FROM Symptom "+
					"WHERE nameSymptom like N'"+ txt + "%'";
			
			Statement st = con.createStatement();
			System.out.println(sql);
			
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				int idSymptom = rs.getInt("idSymptom");
				String nameSymptom = rs.getString("nameSymptom");
				String description = rs.getString("description");
				
				Symptom ds = new Symptom(idSymptom, nameSymptom, description);
				ketQua.add(ds);
			}
			
			Connect.disconnecting(con);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;
	}
	
	@Override
	public Symptom selectByName(Symptom t) {
		Symptom ketQua=null;
		try {
			Connection con = Connect.connecting();
			
			String sql = "SELECT * FROM Symptom "+
					"WHERE nameSymptom=?";
			
			PreparedStatement pst = con.prepareStatement(sql);
			
			pst.setString(1, t.getNameSymptom());
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				int idSymptom = rs.getInt("idSymptom");
				String nameSymptom = rs.getString("nameSymptom");
				String description = rs.getString("description");
				
				ketQua = new Symptom(idSymptom, nameSymptom, description);
			}
			
			Connect.disconnecting(con);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public ArrayList<Symptom> selectByCondition(String condition) {
		ArrayList<Symptom> ketQua = new ArrayList<Symptom>();
		
		try {
			Connection con = Connect.connecting();
			
			String sql = "SELECT * FROM Symptom "+
					" WHERE "+condition;
			
			PreparedStatement pst = con.prepareStatement(sql);
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				int idSymptom = rs.getInt("idSymptom");
				String nameSymptom = rs.getString("nameSymptom");
				String description = rs.getString("description");
				
				Symptom ds = new Symptom(idSymptom, nameSymptom, description);
				ketQua.add(ds);
			}
			
			Connect.disconnecting(con);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ketQua;
	}

}
