package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import connection.ConnectDB;
import models.HoaDon;
import models.KhachHang;
import models.NhanVien;

public class DAOThongKeDT {
private Connection connection;
	
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
	public DAOThongKeDT() {
		connection = ConnectDB.getinstance().getConnection();
	}
	public double getLoiNhuanTheoNgay(String dayStart, String dayEnd) {
		double tongLoiNhuan=0;
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {

			String sql = "SELECT SUM(tongLoiNhuan) FROM HoaDon WHERE ngayLap BETWEEN ? AND ?";
			
			statement = con.prepareStatement(sql);
			statement.setString(1, dayStart);
			statement.setString(2, dayEnd);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				tongLoiNhuan = rs.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tongLoiNhuan;
	}
	public double getLoiNhuanTheoNgayThangNam(String ngay,String thang, String nam) {
		double tongLoiNhuan=0;
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {

			String sql = "SELECT SUM(tongLoiNhuan) AS total  FROM HoaDon WHERE DAY(ngayLap)=? AND MONTH(ngayLap) = ? AND YEAR(ngayLap) = ? GROUP BY DAY(ngayLap),MONTH(ngayLap), YEAR(ngayLap)";
			
			statement = con.prepareStatement(sql);
			statement.setString(1, ngay);
			statement.setString(2, thang);
			statement.setString(3, nam);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				tongLoiNhuan = rs.getDouble(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tongLoiNhuan;
	}

	public double getLoiNhuanTheoThangNam(String thang, String nam) {
		double tongLoiNhuan=0;
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {

			String sql = "SELECT SUM(tongLoiNhuan) AS total  FROM HoaDon WHERE MONTH(ngayLap) = ? AND YEAR(ngayLap) = ? GROUP BY MONTH(ngayLap), YEAR(ngayLap)";
			
			statement = con.prepareStatement(sql);
			statement.setString(1, thang);
			statement.setString(2, nam);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				tongLoiNhuan = rs.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tongLoiNhuan;
		
	}
	
	
	
	
		
	//Dùng cho comboBox tùy chỉnh



	
	
	
	public double getTongTienTheoNgay(String dayStart, String dayEnd) {
		double tongTien = 0;
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT SUM(tongTien) FROM HoaDon WHERE ngayLap BETWEEN ? AND ?";
			
			statement = con.prepareStatement(sql);
			statement.setString(1, dayStart);
			statement.setString(2, dayEnd);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				tongTien = rs.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return tongTien;
	}
	public double getTongTienTheoNgayThangNam(String ngay,String thang, String nam) {
		double tongTien=0;
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT SUM(tongTien) AS total  FROM HoaDon WHERE DAY(ngayLap)=? AND MONTH(ngayLap) = ? AND YEAR(ngayLap) = ? GROUP BY DAY(ngayLap),MONTH(ngayLap), YEAR(ngayLap)";
			
			statement = con.prepareStatement(sql);
			statement.setString(1, ngay);
			statement.setString(2, thang);
			statement.setString(3, nam);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				tongTien = rs.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tongTien;
	}
	public double getTongTienTheoThangNam(String thang, String nam) {
		double tongTien = 0;
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT SUM(tongTien) AS total  FROM HoaDon WHERE MONTH(ngayLap) = ? AND YEAR(ngayLap) = ? GROUP BY MONTH(ngayLap), YEAR(ngayLap)";
			
			statement = con.prepareStatement(sql);
			statement.setString(1, thang);
			statement.setString(2, nam);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				tongTien = rs.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tongTien;
	}
	//Dùng vẽ biểu đồ tháng trong năm
	public int getSoHoaDonTheoThangNam(String thang, String nam) {
		int soLuong = 0;
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT COUNT(*) AS total  FROM HoaDon WHERE MONTH(ngayLap) = ? AND YEAR(ngayLap) = ? GROUP BY MONTH(ngayLap), YEAR(ngayLap)";
			
			statement = con.prepareStatement(sql);
			statement.setString(1, thang);
			statement.setString(2, nam);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				soLuong = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return soLuong;
	}
	//Dùng vẽ biểu đồ theo ngày trong tháng
	public int getSoHoaDonTheoNgayThangNam(String ngay,String thang, String nam) {
		int soLuong = 0;
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT COUNT(*) AS total  FROM HoaDon WHERE DAY(ngayLap)=? AND MONTH(ngayLap) = ? AND YEAR(ngayLap) = ? GROUP BY DAY(ngayLap),MONTH(ngayLap), YEAR(ngayLap)";
			
			statement = con.prepareStatement(sql);
			statement.setString(1, ngay);
			statement.setString(2, thang);
			statement.setString(3, nam);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				soLuong = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return soLuong;
	}
	public ArrayList<HoaDon> getAllHoaDon() {
		ArrayList<HoaDon> dsHoaDon = new ArrayList<HoaDon>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT * FROM HoaDon";
			statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				HoaDon hd = new HoaDon();
				hd.setIdDonHang(rs.getString(1));
				hd.setNgayLap(rs.getDate(2));
				hd.setKhachHang(new KhachHang(rs.getString(4)));
				hd.setNhanVien(new NhanVien(rs.getString(3)));
				hd.setTienKhachDua(rs.getDouble(5));
				hd.setTongTien(rs.getDouble(6));
				hd.setTongLoiNhuan(rs.getDouble(7));
				dsHoaDon.add(hd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dsHoaDon;
	}
	public ArrayList<HoaDon> getHoaDonTheoNgay(String dayStart, String dayEnd) {
		ArrayList<HoaDon> dsHoaDon = new ArrayList<HoaDon>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT * FROM HoaDon WHERE ngayLap BETWEEN ? AND ?";
			
			statement = con.prepareStatement(sql);
			statement.setString(1, dayStart);
			statement.setString(2, dayEnd);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				HoaDon hd = new HoaDon();
				hd.setIdDonHang(rs.getString(1));
				hd.setNgayLap(rs.getDate(2));
				hd.setKhachHang(new KhachHang(rs.getString(4)));
				hd.setNhanVien(new NhanVien(rs.getString(3)));
				hd.setTienKhachDua(rs.getDouble(5));
				hd.setTongTien(rs.getDouble(6));
				hd.setTongLoiNhuan(rs.getDouble(7));
				dsHoaDon.add(hd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dsHoaDon;
	}
}
