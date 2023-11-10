package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connection.ConnectDB;
import models.ChiTietHoaDon;
import models.HoaDon;


public class DAO_QuanLyBanHang {

	
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
	
	public boolean themHoaDon(HoaDon hd) throws SQLException {
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "INSERT INTO HoaDon VALUES (?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps= con.prepareStatement(sql);
			
			ps.setString(1, hd.getIdDonHang());
			ps.setDate(2, hd.getNgayLap());
			ps.setString(3, hd.getKhachHang().getIdKhachHang());
			ps.setString(4, hd.getNhanVien().getIdNhanVien());
			ps.setDouble(5, hd.getTienKhachDua());
			ps.setDouble(6, hd.getTongTien());
			
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		con.close();
		return false;
	}
	
	public boolean themChiTietHoaDon(ChiTietHoaDon cthd) throws SQLException {
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "INSERT INTO ChiTietHoaDon VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement ps= con.prepareStatement(sql);
			
			ps.setInt(1, cthd.getSoLuong());
			ps.setString(2, cthd.getHoaDon().getIdDonHang());
			ps.setString(3, cthd.getSanPham().getIdSanPham());
			ps.setDouble(4, cthd.getThanhTien());
			
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		con.close();
		return false;
	}
	
}
