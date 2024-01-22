package DataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import JDBCUtil.Connect;
import Table.Severity;

public class DAOSeverity implements DAOInterface<Severity> {
	
	public static DAOSeverity getInstance() {
		return new DAOSeverity();
	}

	@Override
	public int insert(Severity t) {		
		return 0;
	}

	@Override
	public int update(Severity t) {
		return 0;
	}

	@Override
	public int delete(Severity t) {
		return 0;
	}

	@Override
	public ArrayList<Severity> selectAll() {
		ArrayList<Severity> ketQua = new ArrayList<Severity>();
		
		try {
			Connection con = Connect.connecting();
			
			String sql = "SELECT * FROM Severity";
			
			PreparedStatement pst = con.prepareStatement(sql);
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				int idSeverity = rs.getInt("idSeverity");
				String levelSeverity = rs.getString("levelSeverity");
				String description = rs.getString("description");
				
				Severity ds = new Severity(idSeverity, levelSeverity, description);
				ketQua.add(ds);
			}
			
			Connect.disconnecting(con);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ketQua;
	}
	
	@Override
	public Severity selectById(Severity t) {
		Severity ketQua=null;
		try {
			Connection con = Connect.connecting();
			
			String sql = "SELECT * FROM Severity "+
					"WHERE idSeverity=?";
			
			PreparedStatement pst = con.prepareStatement(sql);
			
			pst.setInt(1, t.getIdSeverity());
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				int idSeverity = rs.getInt("idSeverity");
				String levelSeverity = rs.getString("levelSeverity");
				String description = rs.getString("description");
				
				ketQua = new Severity(idSeverity, levelSeverity, description);
			}
			
			Connect.disconnecting(con);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public Severity selectByName(Severity t) {
		return null;
	}

	@Override
	public ArrayList<Severity> selectByCondition(String condition) {
		ArrayList<Severity> ketQua = new ArrayList<Severity>();
		
		try {
			Connection con = Connect.connecting();
			
			String sql = "SELECT * FROM Severity "+
					" WHERE "+condition;
			
			PreparedStatement pst = con.prepareStatement(sql);
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				int idSeverity = rs.getInt("idSeverity");
				String levelSeverity = rs.getString("levelSeverity");
				String description = rs.getString("description");
				
				Severity ds = new Severity(idSeverity, levelSeverity, description);
				ketQua.add(ds);
			}
			
			Connect.disconnecting(con);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ketQua;
	}

}
