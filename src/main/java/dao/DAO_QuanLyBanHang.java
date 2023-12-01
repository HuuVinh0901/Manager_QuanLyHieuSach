package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.ConnectDB;
import models.ChiTietHoaDon;
import models.HoaDon;
import models.KhachHang;
import models.LoaiSanPham;
import models.NhaCungCap;
import models.NhanVien;
import models.SanPhamCha;
import models.SanPhamCon;
import utils.TrangThaiSPEnum;


public class DAO_QuanLyBanHang {
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
	
	
	public DAO_QuanLyBanHang() {
		connection = ConnectDB.getinstance().getConnection();
	}
	
	
	
	
	public double getLoiNhuanTheoNgay(String dayStart, String dayEnd) {
		double tongTien = 0;
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
				tongTien = rs.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return tongTien;
	}
	public double getLoiNhuanTheoNgayThangNam(String ngay,String thang, String nam) {
		double soLuong = 0;
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
				soLuong = rs.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return soLuong;
	}
	public double getLoiNhuanTheoThangNam(String thang, String nam) {
		double soLuong = 0;
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
				soLuong = rs.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return soLuong;
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
		double soLuong = 0;
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
				soLuong = rs.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return soLuong;
	}
	public double getTongTienTheoThangNam(String thang, String nam) {
		double soLuong = 0;
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
				soLuong = rs.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return soLuong;
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
				hd.setKhachHang(new KhachHang(rs.getString(3)));
				hd.setNhanVien(new NhanVien(rs.getString(4)));
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
	
	public ArrayList<ChiTietHoaDon> getChiTietHoaDonSachTheoId(String idHoaDon) {
		ArrayList<ChiTietHoaDon> dsCTHDSach = new ArrayList<ChiTietHoaDon>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT * FROM ChiTietHoaDonSach WHERE idDonHang = ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, idHoaDon);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				ChiTietHoaDon cthd = new ChiTietHoaDon();
				cthd.setSoLuong(rs.getInt(1));
				cthd.setHoaDon(new HoaDon(rs.getString(2)));
				cthd.setSanPham(new SanPhamCha(rs.getString(3)) {
					
					@Override
					public double thue() {
						// TODO Auto-generated method stub
						return 0;
					}
					
					@Override
					public double giaBan() {
						// TODO Auto-generated method stub
						return 0;
					}

					
				});
				cthd.setThanhTien(rs.getDouble(4));
				cthd.setLoiNhuan(rs.getDouble(5));
				dsCTHDSach.add(cthd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dsCTHDSach;
	}
	
	public ArrayList<ChiTietHoaDon> getChiTietHoaDonSanPhamTheoId(String idHoaDon) {
		ArrayList<ChiTietHoaDon> dsCTHDSanPham = new ArrayList<ChiTietHoaDon>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT * FROM ChiTietHoaDonSanPham WHERE idDonHang = ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, idHoaDon);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				ChiTietHoaDon cthd = new ChiTietHoaDon();
				cthd.setSoLuong(rs.getInt(1));
				cthd.setHoaDon(new HoaDon(rs.getString(2)));
				cthd.setSanPham(new SanPhamCha(rs.getString(3)) {
					
					@Override
					public double thue() {
						// TODO Auto-generated method stub
						return 0;
					}
					
					@Override
					public double giaBan() {
						// TODO Auto-generated method stub
						return 0;
					}

					
				});
				cthd.setThanhTien(rs.getDouble(4));
				cthd.setLoiNhuan(rs.getDouble(5));
				dsCTHDSanPham.add(cthd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dsCTHDSanPham;
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
				hd.setKhachHang(new KhachHang(rs.getString(3)));
				hd.setNhanVien(new NhanVien(rs.getString(4)));
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
	
	public HoaDon getHoaDonTheoID(String idHoaDon) {
		HoaDon hd = new HoaDon();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT * FROM HoaDon WHERE idDonHang = ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, idHoaDon);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				hd.setIdDonHang(rs.getString(1));
				hd.setNgayLap(rs.getDate(2));
				hd.setKhachHang(new KhachHang(rs.getString(3)));
				hd.setNhanVien(new NhanVien(rs.getString(4)));
				hd.setTienKhachDua(rs.getDouble(5));
				hd.setTongTien(rs.getDouble(6));
				hd.setTongLoiNhuan(rs.getDouble(7));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return hd;
	}
	
	public ArrayList<HoaDon> getHoaDonTimKiem(String cond) {
		ArrayList<HoaDon> dsHoaDon = new ArrayList<HoaDon>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT idDonHang, ngayLap, khachHang, nhanVien, tienKhachDua, tongTien, tongLoiNhuan"
					+ " FROM HoaDon hd" + " JOIN KhachHang kh ON hd.khachHang = kh.idKhachHang"
					+ " WHERE idDonHang LIKE '%" + cond + "%' OR" 
					+ " soDienThoai LIKE '%" + cond + "%' OR" + " idKhachHang LIKE N'%" + cond + "%'";
			
			statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				HoaDon hd = new HoaDon();
				hd.setIdDonHang(rs.getString(1));
				hd.setNgayLap(rs.getDate(2));
				hd.setKhachHang(new KhachHang(rs.getString(3)));
				hd.setNhanVien(new NhanVien(rs.getString(4)));
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
	
	public boolean themHoaDon(HoaDon hd) throws SQLException {
		String sql = "INSERT INTO HoaDon VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement pst = connection.prepareStatement(sql)) {
			pst.setString(1, hd.getIdDonHang());
			pst.setDate(2, hd.getNgayLap());
			pst.setString(3, hd.getNhanVien().getId());
			pst.setString(4, hd.getKhachHang().getIdKhachHang());
			pst.setDouble(5, hd.getTienKhachDua());
			pst.setDouble(6, hd.getTongTien());
			pst.setDouble(7, hd.getTongLoiNhuan());
			return pst.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean themChiTietHoaDonSach(ChiTietHoaDon cthd) throws SQLException {
		String sql = "INSERT INTO ChiTietHoaDonSach VALUES (?, ?, ?, ?, ?)";
		try (PreparedStatement pst = connection.prepareStatement(sql)) {
			pst.setInt(1, cthd.getSoLuong());
			pst.setString(2, cthd.getHoaDon().getIdDonHang());
			pst.setString(3, cthd.getSanPham().getIdSanPham());
			pst.setDouble(4, cthd.getThanhTien());
			pst.setDouble(5, cthd.getLoiNhuan());
			return pst.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean themChiTietHoaDonSanPham(ChiTietHoaDon cthd) throws SQLException {
		String sql = "INSERT INTO ChiTietHoaDonSanPham VALUES (?, ?, ?, ?, ?)";
		try (PreparedStatement pst = connection.prepareStatement(sql)) {
			pst.setInt(1, cthd.getSoLuong());
			pst.setString(2, cthd.getHoaDon().getIdDonHang());
			pst.setString(3, cthd.getSanPham().getIdSanPham());
			pst.setDouble(4, cthd.getThanhTien());
			pst.setDouble(5, cthd.getLoiNhuan());
			return pst.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	

	
	
	
}
