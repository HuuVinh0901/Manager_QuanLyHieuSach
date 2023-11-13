package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connection.ConnectDB;

public class DAOSetPassWord {
	private Connection connection;

	public DAOSetPassWord() {
		connection = ConnectDB.getinstance().getConnection();
	}

	private void close(PreparedStatement pst) {
		if (pst != null) {
			try {
				pst.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

	private void capNhatMatKhauTrongDB(String idNhanVien, String matKhauMoi) {
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pst = null;

		try {
			String sql = "UPDATE TaiKhoan SET matKhau = ? WHERE idTaiKhoan = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, matKhauMoi);
			pst.setString(2, idNhanVien);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pst);
		}
	}
	
	

}
