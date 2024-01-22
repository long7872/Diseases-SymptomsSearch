package DataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import JDBCUtil.Connect;
import Table.HealthIssues;
import Table.HealthStatus;
import Table.Symptom;

public class DAOHealthStatus implements DAOInterface<HealthStatus> {
	
	public static DAOHealthStatus getInstance() {
		return new DAOHealthStatus();
	}

	@Override
	public int insert(HealthStatus t) {
		int ketQua=0;
		try {
			Connection con = Connect.connecting();
			
			String sql = "INSERT INTO HealthStatus(idHealthIssues,idSymptom)"+
					" VALUES (?,?)";
			
			PreparedStatement pst = con.prepareStatement(sql);
			
			pst.setInt(1, t.getIdHealthIssues());
			pst.setInt(2, t.getIdSymptom());
			
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
	public int update(HealthStatus t) {
		return 0;
	}
	
	public int updateByOther(HealthStatus updated, HealthStatus outdated) {
		int ketQua=0;
		try {
			Connection con = Connect.connecting();
			
			String sql = "UPDATE HealthStatus"+
					" SET "+
					" idHealthIssues=?,"+
					" idSymptom=? "+
					" WHERE idHealthIssues=? AND idSymptom=?";
			
			PreparedStatement pst = con.prepareStatement(sql);
			
			pst.setInt(1, updated.getIdHealthIssues());
			pst.setInt(2, updated.getIdSymptom());
			pst.setInt(3, outdated.getIdHealthIssues());
			pst.setInt(4, outdated.getIdSymptom());
			
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
	public int delete(HealthStatus t) {
		int ketQua=0;
		try {
			Connection con = Connect.connecting();
			
			String sql = "DELETE FROM HealthStatus "+
					" WHERE idHealthIssues=? AND idSymptom=?";
			
			PreparedStatement pst = con.prepareStatement(sql);
			
			pst.setInt(1, t.getIdHealthIssues());
			pst.setInt(2, t.getIdSymptom());
			
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
	public ArrayList<HealthStatus> selectAll() {
		ArrayList<HealthStatus> ketQua = new ArrayList<HealthStatus>();
		
		try {
			Connection con = Connect.connecting();
			
			String sql = "SELECT * FROM Symptom";
			
			PreparedStatement pst = con.prepareStatement(sql);
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				int idHealthIssues = rs.getInt("idHealthIssues");
				int idSymptom = rs.getInt("idSymptom");
				
				HealthStatus ds = new HealthStatus(idHealthIssues, idSymptom);
				ketQua.add(ds);
			}
			
			Connect.disconnecting(con);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ketQua;
	}

	
	@Override
	public HealthStatus selectById(HealthStatus t) {
		HealthStatus ketQua = null;
		try {
			Connection con = Connect.connecting();
			
			String sql = "SELECT * FROM HealthStatus "+
					"WHERE idHealthIssues=? and idSymptom=?";
			
			PreparedStatement pst = con.prepareStatement(sql);
			
			pst.setInt(1, t.getIdHealthIssues());
			pst.setInt(2, t.getIdSymptom());
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				int idHealthIssues = rs.getInt("idHealthIssues");
				int idSymptom = rs.getInt("idSymptom");
				
				ketQua = new HealthStatus(idHealthIssues, idSymptom);
			}
			
			Connect.disconnecting(con);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;
	}
	
	public ArrayList<HealthStatus> selectAllId(HealthStatus t) {
		ArrayList<HealthStatus> ketQua = new ArrayList<HealthStatus>();
		try {
			Connection con = Connect.connecting();
			
			String sql = "SELECT * FROM HealthStatus "+
					"WHERE idHealthIssues=? or idSymptom=?";
			
			PreparedStatement pst = con.prepareStatement(sql);
			
			pst.setInt(1, t.getIdHealthIssues());
			pst.setInt(2, t.getIdSymptom());
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				int idHealthIssues = rs.getInt("idHealthIssues");
				int idSymptom = rs.getInt("idSymptom");
				
				HealthStatus ds = new HealthStatus(idHealthIssues, idSymptom);
				ketQua.add(ds);
			}
			
			Connect.disconnecting(con);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	public HealthStatus selectByName(HealthIssues t, Symptom n) {
		HealthStatus ketQua=null;
		try {
			Connection con = Connect.connecting();
			
			String sql = "SELECT * FROM HealthStatus "+
					" WHERE idHealthIssues IN "+
					" (SELECT idHealthIssues FROM HealthIssues "+
					" WHERE nameHealthIssues=?) "+
					" OR idSymptom IN "+
					" (SELECT idSymptom FROM Symptom "+
					" WHERE nameSymptom=?)";
			
			PreparedStatement pst = con.prepareStatement(sql);
			
			pst.setString(1, t.getNameHealthIssues());
			pst.setString(2, n.getNameSymptom());
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				int idHealthIssues = rs.getInt("idHealthIssues");
				int idSymptom = rs.getInt("idSymptom");
				
				ketQua = new HealthStatus(idHealthIssues, idSymptom);
			}
			
			Connect.disconnecting(con);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;
	}
	
	@Override
	public HealthStatus selectByName(HealthStatus t) {
		return null;
	}

	@Override
	public ArrayList<HealthStatus> selectByCondition(String condition) {
		ArrayList<HealthStatus> ketQua = new ArrayList<HealthStatus>();
		
		try {
			Connection con = Connect.connecting();
			
			String sql = "SELECT * FROM Symptom "+
					" WHERE "+condition;
			
			PreparedStatement pst = con.prepareStatement(sql);
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				int idHealthIssues = rs.getInt("idHealthIssues");
				int idSymptom = rs.getInt("idSymptom");
				
				HealthStatus ds = new HealthStatus(idHealthIssues, idSymptom);
				ketQua.add(ds);
			}
			
			Connect.disconnecting(con);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ketQua;
	}

}
