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
	
	public ArrayList<ChiTietHoaDon> getChiTietHoaDonTheoId(String idHoaDon) {
		ArrayList<ChiTietHoaDon> dsCTHD = new ArrayList<ChiTietHoaDon>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT * FROM ChiTietHoaDon WHERE idDonHang = ?";
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
				dsCTHD.add(cthd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dsCTHD;
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
			String sql = "SELECT idDonHang, ngayLap, khachHang, nhanVien, tienKhachDua, tongTien"
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
				dsHoaDon.add(hd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dsHoaDon;
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
