package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connection.ConnectDB;
import models.LoaiSanPham;
import models.TaiKhoan;

public class DAOTaiKhoan {
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

	private Connection connection;

	public DAOTaiKhoan() {
		connection = ConnectDB.getinstance().getConnection();
	}

	public boolean createTK(TaiKhoan tk) {
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "insert into TaiKhoan values (?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, tk.getIdTaiKhoan());
			ps.setString(2, tk.getMatKhau());
			ps.setDate(3, tk.getNgayLap());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public void DeleteTK(String maXoa) {
		ConnectDB.getinstance();
		PreparedStatement pst = null;
		Connection con = ConnectDB.getConnection();
		String sql = "delete from TaiKhoan where idTaiKhoan = ? ";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, maXoa);
			pst.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pst);
		}
	}

	public boolean capNhatTaiKhoan(String tkID, String newPassword) throws SQLException {
	    String sql = "UPDATE TaiKhoan SET matKhau = ? WHERE idTaiKhoan = ?";
	    try (PreparedStatement pst = connection.prepareStatement(sql)) {
	        pst.setString(1, newPassword);
	        pst.setString(2, tkID);
	        int n = pst.executeUpdate();
	        return n > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	public TaiKhoan getTaiKhoanTheoMa(String maTK) {
		TaiKhoan tk = new TaiKhoan();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select * from TaiKhoan where idTaiKhoan = '" + maTK + "'";

		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				tk.setIdTaiKhoan(rs.getString(1));
				tk.setMatKhau(rs.getString(2));
				tk.setNgayLap(rs.getDate(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tk;

	}
}
