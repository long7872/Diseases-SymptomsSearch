package DataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import JDBCUtil.Connect;
import Table.UserAccount;

public class DAOUserAccount implements DAOInterface<UserAccount> {
	
	public static DAOUserAccount getInstance() {
		return new DAOUserAccount();
	}

	@Override
	public int insert(UserAccount t) {		
		return 0;
	}

	@Override
	public int update(UserAccount t) {
		return 0;
	}

	@Override
	public int delete(UserAccount t) {
		return 0;
	}

	@Override
	public ArrayList<UserAccount> selectAll() {
		ArrayList<UserAccount> ketQua = new ArrayList<UserAccount>();
		
		try {
			Connection con = Connect.connecting();
			
			String sql = "SELECT * FROM UserAccount";
			
			PreparedStatement pst = con.prepareStatement(sql);
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				String usernameAcc = rs.getString("usernameAcc");
				String passwordAcc = rs.getString("passwordAcc");
				
				UserAccount ds = new UserAccount(usernameAcc, passwordAcc);
				ketQua.add(ds);
			}
			
			Connect.disconnecting(con);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ketQua;
	}
	
	@Override
	public UserAccount selectById(UserAccount t) {
		return null;
	}

	@Override
	public UserAccount selectByName(UserAccount t) {
		UserAccount ketQua=null;
		try {
			Connection con = Connect.connecting();
			
			String sql = "SELECT * FROM UserAccount "+
					"WHERE usernameAcc=?";
			
			PreparedStatement pst = con.prepareStatement(sql);
			
			pst.setString(1, t.getUsernameAcc());
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				String usernameAcc = rs.getString("usernameAcc");
				String passwordAcc = rs.getString("passwordAcc");
				
				ketQua = new UserAccount(usernameAcc, passwordAcc);
			}
			
			Connect.disconnecting(con);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public ArrayList<UserAccount> selectByCondition(String condition) {
		ArrayList<UserAccount> ketQua = new ArrayList<UserAccount>();
		
		try {
			Connection con = Connect.connecting();
			
			String sql = "SELECT * FROM UserAccount "+
					" WHERE "+condition;
			
			PreparedStatement pst = con.prepareStatement(sql);
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				String usernameAcc = rs.getString("usernameAcc");
				String passwordAcc = rs.getString("passwordAcc");
				
				UserAccount ds = new UserAccount(usernameAcc, passwordAcc);
				ketQua.add(ds);
			}
			
			Connect.disconnecting(con);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ketQua;
	}

}
