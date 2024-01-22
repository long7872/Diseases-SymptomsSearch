package DataAccessObject;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import JDBCUtil.Connect;
import Table.HealthIssues;

public class DAOHealthIssues implements DAOInterface<HealthIssues> {
	
	public static DAOHealthIssues getInstance() {
		return new DAOHealthIssues();
	}

	@Override
	public int insert(HealthIssues t) {
		int ketQua=0;
		try {
			Connection con = Connect.connecting();
			
			String sql = "INSERT INTO HealthIssues(nameHealthIssues, category, causesOfHealthIssues,idSeverity)"+
					" VALUES (?,?,?,?)";
			
			PreparedStatement pst = con.prepareStatement(sql);
			
			pst.setString(1, t.getNameHealthIssues());
			pst.setString(2, t.getCategory());
			pst.setString(3, t.getCausesOfHealthIssues());
			pst.setInt(4, t.getIdSeverity());
			
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
	public int update(HealthIssues t) {
		int ketQua=0;
		try {
			Connection con = Connect.connecting();
			
			String sql = "UPDATE HealthIssues"+
					" SET "+
					" nameHealthIssues=?,"+
					" category=?,"+
					" causesOfHealthIssues=?,"+
					" idSeverity=? "+
					" where idHealthIssues=?";
			
			PreparedStatement pst = con.prepareStatement(sql);
			
			pst.setString(1, t.getNameHealthIssues());
			pst.setString(2, t.getCategory());
			pst.setString(3, t.getCausesOfHealthIssues());
			pst.setInt(4, t.getIdSeverity());
			pst.setInt(5, t.getIdHealthIssues());
			
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
	public int delete(HealthIssues t) {
		int ketQua=0;
		try {
			Connection con = Connect.connecting();
			
			String sql = "DELETE FROM HealthIssues "+
					" WHERE idHealthIssues=?";
			
			PreparedStatement pst = con.prepareStatement(sql);
			
			pst.setInt(1, t.getIdHealthIssues());
			
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
	public ArrayList<HealthIssues> selectAll() {
		ArrayList<HealthIssues> ketQua = new ArrayList<HealthIssues>();
		
		try {
			Connection con = Connect.connecting();
			
			String sql = "SELECT * FROM HealthIssues";
			
			PreparedStatement pst = con.prepareStatement(sql);
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				int idHealthIssues = rs.getInt("idHealthIssues");
				String nameHealthIssues = rs.getString("nameHealthIssues");
				String category = rs.getString("category");
				String causesOfHealthIssues = rs.getString("causesOfHealthIssues");
				int idSeverity = rs.getInt("idSeverity");
				
				HealthIssues ds = new HealthIssues(idHealthIssues, nameHealthIssues, category, causesOfHealthIssues, idSeverity);
				ketQua.add(ds);
			}
			
			Connect.disconnecting(con);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ketQua;
	}
	
	@Override
	public HealthIssues selectById(HealthIssues t) {
		HealthIssues ketQua=null;
		try {
			Connection con = Connect.connecting();
			
			String sql = "SELECT * FROM HealthIssues "+
					"WHERE idHealthIssues=?";
			
			PreparedStatement pst = con.prepareStatement(sql);
			
			pst.setInt(1, t.getIdHealthIssues());
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				int idHealthIssues = rs.getInt("idHealthIssues");
				String nameHealthIssues = rs.getString("nameHealthIssues");
				String category = rs.getString("category");
				String causesOfHealthIssues = rs.getString("causesOfHealthIssues");
				int idSeverity = rs.getInt("idSeverity");
				
				ketQua = new HealthIssues(idHealthIssues, nameHealthIssues, category, causesOfHealthIssues, idSeverity);
			}
			
			Connect.disconnecting(con);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	public ArrayList<HealthIssues> selectAllByName(String txt) {
		ArrayList<HealthIssues> ketQua = new ArrayList<HealthIssues>();
		try {
			Connection con = Connect.connecting();
			
			String sql = "SELECT * FROM HealthIssues "+
					"WHERE nameHealthIssues like N'"+ txt + "%'";
			
			Statement st = con.createStatement();
			System.out.println(sql);
			
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				int idHealthIssues = rs.getInt("idHealthIssues");
				String nameHealthIssues = rs.getString("nameHealthIssues");
				String category = rs.getString("category");
				String causesOfHealthIssues = rs.getString("causesOfHealthIssues");
				int idSeverity = rs.getInt("idSeverity");
				
				HealthIssues ds = new HealthIssues(idHealthIssues, nameHealthIssues, category, causesOfHealthIssues, idSeverity);
				ketQua.add(ds);
			}
			
			Connect.disconnecting(con);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;
	}
	
	@Override
	public HealthIssues selectByName(HealthIssues t) {
		HealthIssues ketQua=null;
		try {
			Connection con = Connect.connecting();
			
			String sql = "SELECT * FROM HealthIssues "+
					"WHERE nameHealthIssues=?";
			
			PreparedStatement pst = con.prepareStatement(sql);
			
			pst.setString(1, t.getNameHealthIssues());
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				int idHealthIssues = rs.getInt("idHealthIssues");
				String nameHealthIssues = rs.getString("nameHealthIssues");
				String category = rs.getString("category");
				String causesOfHealthIssues = rs.getString("causesOfHealthIssues");
				int idSeverity = rs.getInt("idSeverity");
				
				ketQua = new HealthIssues(idHealthIssues, nameHealthIssues, category, causesOfHealthIssues, idSeverity);
			}
			
			Connect.disconnecting(con);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public ArrayList<HealthIssues> selectByCondition(String condition) {
		ArrayList<HealthIssues> ketQua = new ArrayList<HealthIssues>();
		
		try {
			Connection con = Connect.connecting();
			
			String sql = "SELECT * FROM HealthIssues "+
					" WHERE "+condition;
			
			PreparedStatement pst = con.prepareStatement(sql);
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				int idHealthIssues = rs.getInt("idHealthIssues");
				String nameHealthIssues = rs.getString("nameHealthIssues");
				String category = rs.getString("category");
				String causesOfHealthIssues = rs.getString("causesOfHealthIssues");
				int idSeverity = rs.getInt("idSeverity");
				
				HealthIssues ds = new HealthIssues(idHealthIssues, nameHealthIssues, category, causesOfHealthIssues, idSeverity);
				ketQua.add(ds);
			}
			
			Connect.disconnecting(con);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ketQua;
	}

}
