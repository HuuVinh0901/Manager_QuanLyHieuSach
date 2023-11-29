package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.ConnectDB;
import models.NhanLuc;


public class DAONhanLuc {
	public NhanLuc getQuanLy(String idNV) {
		NhanLuc nv = new NhanLuc();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT * from NhanVien where idNhanVien=? UNION ALL SELECT * from QuanLy where idQuanLy=?";
			statement = con.prepareStatement(sql);
			statement.setString(1, idNV);
			statement.setString(2, idNV);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				nv.setId(rs.getString(1));
				nv.setTen(rs.getString(2));
				nv.setSoDienThoai(rs.getString(3));
				nv.setDiaChi(rs.getString(4));
				nv.setEmail(rs.getString(5));
				nv.setNgaySinh(rs.getDate(6));
				nv.setGioiTinh(rs.getBoolean(7));
				nv.setChucVu(rs.getString(8));
				nv.setTrangThai(rs.getBoolean(9));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return nv;
	}
}
