package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import connection.ConnectDB;
import models.ChiTietHoaDon;
import models.KhuyenMai;
import models.LoaiSanPham;
import models.NhaCungCap;
import models.NhanVien;
import models.SachCon;
import models.SanPhamCha;
import models.SanPhamCon;
import utils.TrangThaiSPEnum;
import views.QuanLySanPhamView;

public class DAOQuanLySanPham implements Serializable {
	private Connection connection;

	public DAOQuanLySanPham() {
		connection = ConnectDB.getinstance().getConnection();
	}

	private void close(PreparedStatement pst) {
		if (pst != null) {
			try {
				pst.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public boolean capNhatSoLuongSanPham(int sl, String idSP) {
		String sql = "UPDATE SanPham " + "SET soLuong = soLuong - ?" + "WHERE idSanPham = ? ";
		try (PreparedStatement pst = connection.prepareStatement(sql)) {
			pst.setInt(1, sl);
			pst.setString(2, idSP);
			int n = pst.executeUpdate();
			return n > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean checkIdSanPham(String idSanPham) throws SQLException {
		String sql = "SELECT COUNT(*) FROM SanPham WHERE idSanPham = ?";
		try (PreparedStatement pst = connection.prepareStatement(sql)) {
			pst.setString(1, idSanPham);
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					int count = rs.getInt(1);
					return count > 0;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public ArrayList<SanPhamCon> getTopSanPhamTheoNgay(String dayStart, String dayEnd) {
		ArrayList<SanPhamCon> dsSanPham = new ArrayList<>();
		ArrayList<ChiTietHoaDon> dsChiTietHoaDon = new ArrayList<ChiTietHoaDon>();
		String sql = "SELECT sp.idSanPham, sp.tenSanPham, lsp.tenLoaiSanPham, ncc.tenNhaCungCap, sp.soLuong, "
				+ "SUM(ct.soLuong) AS soLuongBan, sp.giaNhap, sp.giaBan, "
				+ "SUM(ct.thanhTien) AS doanhThu, SUM(ct.loiNhuan) AS loiNhuan, sp.trangThai "
				+ "FROM ChiTietHoaDonSanPham ct " + "JOIN SanPham sp ON ct.idSanPham = sp.idSanPham "
				+ "JOIN HoaDon hd ON ct.idDonHang = hd.idDonHang "
				+ "JOIN LoaiSanPham lsp ON sp.loaiSanPham = lsp.idLoaiSanPham "
				+ "JOIN NhaCungCap ncc ON sp.nhaCungCap = ncc.idNhaCungCap " + "WHERE hd.ngayLap BETWEEN ? AND ? "
				+ "GROUP BY sp.idSanPham, sp.tenSanPham, lsp.tenLoaiSanPham, ncc.tenNhaCungCap, sp.soLuong, "
				+ "sp.giaNhap, sp.giaBan, sp.trangThai " + "ORDER BY soLuongBan DESC;";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, dayStart);
			preparedStatement.setString(2, dayEnd);

			try (ResultSet rs = preparedStatement.executeQuery()) {
				while (rs.next()) {
					SanPhamCon sp = new SanPhamCon();
					sp.setIdSanPham(rs.getString("idSanPham"));
					sp.setTenSanPham(rs.getString("tenSanPham"));
					sp.setIdLoaiSanPham(new LoaiSanPham(rs.getString("tenLoaiSanPham")));
					sp.setIdNhaCungCap(new NhaCungCap(rs.getString("tenNhaCungCap")));
					sp.setSoLuong(rs.getInt("soLuong"));
					sp.setGiaNhap(rs.getDouble("giaNhap"));
					sp.setSoLuongBan(rs.getInt("soLuongBan"));
					sp.giaBan();
					sp.setDoanhThu(rs.getDouble("doanhThu"));
					sp.setLoiNhuan(rs.getDouble("loiNhuan"));
					sp.setTrangThai(TrangThaiSPEnum.getById(rs.getInt("trangThai")));
					dsSanPham.add(sp);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsSanPham;
	}

	public ArrayList<SanPhamCon> getTopSanPhamBanChamTheoNgay(String dayStart, String dayEnd) {
		ArrayList<SanPhamCon> dsSanPham = new ArrayList<>();
		ArrayList<ChiTietHoaDon> dsChiTietHoaDon = new ArrayList<ChiTietHoaDon>();
		String sql = "SELECT sp.idSanPham, sp.tenSanPham, lsp.tenLoaiSanPham, ncc.tenNhaCungCap, sp.soLuong, "
				+ "SUM(ct.soLuong) AS soLuongBan, sp.giaNhap, sp.giaBan, "
				+ "SUM(ct.thanhTien) AS doanhThu, SUM(ct.loiNhuan) AS loiNhuan, sp.trangThai "
				+ "FROM ChiTietHoaDonSanPham ct " + "JOIN SanPham sp ON ct.idSanPham = sp.idSanPham "
				+ "JOIN HoaDon hd ON ct.idDonHang = hd.idDonHang "
				+ "JOIN LoaiSanPham lsp ON sp.loaiSanPham = lsp.idLoaiSanPham "
				+ "JOIN NhaCungCap ncc ON sp.nhaCungCap = ncc.idNhaCungCap " + "WHERE hd.ngayLap BETWEEN ? AND ? "
				+ "GROUP BY sp.idSanPham, sp.tenSanPham, lsp.tenLoaiSanPham, ncc.tenNhaCungCap, sp.soLuong, "
				+ "sp.giaNhap, sp.giaBan, sp.trangThai " + "ORDER BY soLuongBan ASC";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, dayStart);
			preparedStatement.setString(2, dayEnd);

			try (ResultSet rs = preparedStatement.executeQuery()) {
				while (rs.next()) {
					SanPhamCon sp = new SanPhamCon();
					sp.setIdSanPham(rs.getString("idSanPham"));
					sp.setTenSanPham(rs.getString("tenSanPham"));
					sp.setIdLoaiSanPham(new LoaiSanPham(rs.getString("tenLoaiSanPham")));
					sp.setIdNhaCungCap(new NhaCungCap(rs.getString("tenNhaCungCap")));
					sp.setSoLuong(rs.getInt("soLuong"));
					sp.setGiaNhap(rs.getDouble("giaNhap"));
					sp.setSoLuongBan(rs.getInt("soLuongBan"));
					sp.giaBan();
					sp.setDoanhThu(rs.getDouble("doanhThu"));
					sp.setLoiNhuan(rs.getDouble("loiNhuan"));
					sp.setTrangThai(TrangThaiSPEnum.getById(rs.getInt("trangThai")));
					dsSanPham.add(sp);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsSanPham;
	}

	public double getSoLuongTonTheoNgay(String dayStart, String dayEnd) {
		double soLuongTon = 0;
		String sql = "SELECT SUM(sp.soLuong) FROM SanPham sp "
				+ "JOIN ChiTietHoaDonSanPham ct ON sp.idSanPham = ct.idSanPham "
				+ "JOIN HoaDon hd ON ct.idDonHang = hd.idDonHang " + "WHERE hd.ngayLap BETWEEN ? AND ? ";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, dayStart);
			preparedStatement.setString(2, dayEnd);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				soLuongTon = rs.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return soLuongTon;
	}

	public double getDoanhThuTheoNgay(String dayStart, String dayEnd) {
		double soLuongTon = 0;
		String sql = "SELECT SUM(ct.thanhTien) AS doanhThu "
                + "FROM ChiTietHoaDonSanPham ct " 
                + "JOIN SanPham sp ON ct.idSanPham = sp.idSanPham "
                + "JOIN HoaDon hd ON ct.idDonHang = hd.idDonHang "
                + "JOIN LoaiSanPham lsp ON sp.loaiSanPham = lsp.idLoaiSanPham "
                + "JOIN NhaCungCap ncc ON sp.nhaCungCap = ncc.idNhaCungCap " 
                + "WHERE hd.ngayLap BETWEEN ? AND ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, dayStart);
			preparedStatement.setString(2, dayEnd);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				soLuongTon = rs.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return soLuongTon;
	}
	
	public double getLoiNhuanTheoNgay(String dayStart, String dayEnd) {
		double soLuongTon = 0;
		String sql = "SELECT SUM(ct.loiNhuan) AS loiNhuan "
                + "FROM ChiTietHoaDonSanPham ct " 
                + "JOIN SanPham sp ON ct.idSanPham = sp.idSanPham "
                + "JOIN HoaDon hd ON ct.idDonHang = hd.idDonHang "
                + "JOIN LoaiSanPham lsp ON sp.loaiSanPham = lsp.idLoaiSanPham "
                + "JOIN NhaCungCap ncc ON sp.nhaCungCap = ncc.idNhaCungCap " 
                + "WHERE hd.ngayLap BETWEEN ? AND ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, dayStart);
			preparedStatement.setString(2, dayEnd);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				soLuongTon = rs.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return soLuongTon;
	}
	
	public double getSoLuongDaBanTheoNgay(String dayStart, String dayEnd) {
		double soLuongTon = 0;
		String sql = "SELECT SUM(soLuong) FROM ChiTietHoaDonSanPham ct join HoaDon hd ON ct.idDonHang = hd.idDonHang WHERE hd.ngayLap BETWEEN ? AND ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, dayStart);
			preparedStatement.setString(2, dayEnd);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				soLuongTon = rs.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return soLuongTon;
	}

	public SanPhamCon getSanPhamTimKiemTheoMa(String cond) {
		SanPhamCon lsp = new SanPhamCon();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT sp.idSanPham, sp.tenSanPham, lsp.tenLoaiSanPham, ncc.tenNhaCungCap, sp.kichThuoc, sp.mauSac, sp.trangThai, sp.thue,sp.giaNhap, sp.soLuong, sp.giaBan, sp.giaKhuyenMai "
					+ "FROM SanPham sp " + "JOIN LoaiSanPham lsp ON sp.loaiSanPham = lsp.idLoaiSanPham "
					+ "JOIN NhaCungCap ncc ON sp.nhaCungCap = ncc.idNhaCungCap " + "WHERE sp.idSanPham = ?";

			statement = con.prepareStatement(sql);
			statement.setString(1, cond);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				lsp.setIdSanPham(rs.getString(1));
				lsp.setTenSanPham(rs.getString(2));
				lsp.setIdLoaiSanPham(new LoaiSanPham(rs.getString(3)));
				lsp.setIdNhaCungCap(new NhaCungCap(rs.getString(4)));
				lsp.setKichThuoc(rs.getDouble(5));
				lsp.setMauSac(rs.getString(6));
				int trangThai = rs.getInt(7);
				TrangThaiSPEnum trangThaiEnum = TrangThaiSPEnum.getById(trangThai);
				lsp.setTrangThai(trangThaiEnum);
				lsp.thue();
				lsp.setGiaNhap(rs.getDouble(9));
				lsp.setSoLuong(rs.getInt(10));
				lsp.setGiaKM(rs.getDouble(12));
				return lsp;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public ArrayList<SanPhamCon> getDanhSachSanPhamTimKiemTheoMa(String cond) {
		ArrayList<SanPhamCon> dsSanPham = new ArrayList<SanPhamCon>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT sp.idSanPham, sp.tenSanPham, lsp.tenLoaiSanPham, ncc.tenNhaCungCap, sp.kichThuoc, sp.mauSac, sp.trangThai, sp.thue,sp.giaNhap, sp.soLuong, sp.giaBan, sp.giaKhuyenMai "
					+ "FROM SanPham sp " + "JOIN LoaiSanPham lsp ON sp.loaiSanPham = lsp.idLoaiSanPham "
					+ "JOIN NhaCungCap ncc ON sp.nhaCungCap = ncc.idNhaCungCap " + "WHERE sp.idSanPham LIKE '%" + cond
					+ "%'";

			statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				SanPhamCon lsp = new SanPhamCon();
				lsp.setIdSanPham(rs.getString(1));
				lsp.setTenSanPham(rs.getString(2));
				lsp.setIdLoaiSanPham(new LoaiSanPham(rs.getString(3)));
				lsp.setIdNhaCungCap(new NhaCungCap(rs.getString(4)));
				lsp.setKichThuoc(rs.getDouble(5));
				lsp.setMauSac(rs.getString(6));
				int trangThai = rs.getInt(7);
				TrangThaiSPEnum trangThaiEnum = TrangThaiSPEnum.getById(trangThai);
				lsp.setTrangThai(trangThaiEnum);
				lsp.thue();
				lsp.setGiaNhap(rs.getDouble(9));
				lsp.setSoLuong(rs.getInt(10));
				lsp.setGiaKM(rs.getDouble(12));
				dsSanPham.add(lsp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dsSanPham;
	}

	public ArrayList<SanPhamCon> getDanhSachSanPhamTimKiem(String cond) {
		ArrayList<SanPhamCon> dsSanPham = new ArrayList<SanPhamCon>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT sp.idSanPham, sp.tenSanPham, lsp.tenLoaiSanPham, ncc.tenNhaCungCap, sp.kichThuoc, sp.mauSac, sp.trangThai, sp.thue,sp.giaNhap, sp.soLuong, sp.giaBan, sp.giaKhuyenMai "
					+ "FROM SanPham sp " + "JOIN LoaiSanPham lsp ON sp.loaiSanPham = lsp.idLoaiSanPham "
					+ "JOIN NhaCungCap ncc ON sp.nhaCungCap = ncc.idNhaCungCap " + "WHERE sp.idSanPham LIKE '%" + cond
					+ "%' OR " + "sp.tenSanPham LIKE '%" + cond + "%' OR " + "lsp.tenLoaiSanPham LIKE N'%" + cond
					+ "%'";

			statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				SanPhamCon lsp = new SanPhamCon();
				lsp.setIdSanPham(rs.getString(1));
				lsp.setTenSanPham(rs.getString(2));
				lsp.setIdLoaiSanPham(new LoaiSanPham(rs.getString(3)));
				lsp.setIdNhaCungCap(new NhaCungCap(rs.getString(4)));
				lsp.setKichThuoc(rs.getDouble(5));
				lsp.setMauSac(rs.getString(6));
				int trangThai = rs.getInt(7);
				TrangThaiSPEnum trangThaiEnum = TrangThaiSPEnum.getById(trangThai);
				lsp.setTrangThai(trangThaiEnum);
				lsp.thue();
				lsp.setGiaNhap(rs.getDouble(9));
				lsp.setSoLuong(rs.getInt(10));
				lsp.setGiaKM(rs.getDouble(12));
				dsSanPham.add(lsp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dsSanPham;
	}

	public SanPhamCon getSanPham(String idSanPham) {
		SanPhamCon sp = new SanPhamCon();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT * FROM SanPham WHERE idSanPham = ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, idSanPham);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				sp.setIdSanPham(rs.getString(1));
				sp.setTenSanPham(rs.getString(2));
				sp.setIdLoaiSanPham(new LoaiSanPham(rs.getString(3)));
				sp.setIdNhaCungCap(new NhaCungCap(rs.getString(4)));
				sp.setKichThuoc(rs.getDouble(5));
				sp.setMauSac(rs.getString(6));
				String trangThai = rs.getString(7);
				TrangThaiSPEnum trangThaiEnum = TrangThaiSPEnum.getByName(trangThai);
				sp.setTrangThai(trangThaiEnum);
				sp.setGiaNhap(rs.getDouble("giaNhap"));
				sp.setGiaKM(rs.getDouble("giaKhuyenMai"));
				sp.setSoLuong(rs.getInt("soLuong"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sp;
	}

	public ArrayList<SanPhamCon> loadComboBoxByLoaiSanPham(String id) {
		ArrayList<SanPhamCon> dsSanPham = new ArrayList<SanPhamCon>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT  sp.idSanPham, sp.tenSanPham, lsp.tenLoaiSanPham, ncc.tenNhaCungCap, sp.kichThuoc, sp.mauSac, sp.trangThai, sp.thue, sp.giaNhap ,sp.soLuong,sp.giaBan "
					+ "FROM SanPham sp " + "JOIN LoaiSanPham lsp ON sp.loaiSanPham = lsp.idLoaiSanPham "
					+ "JOIN NhaCungCap ncc ON sp.nhaCungCap = ncc.idNhaCungCap " + "WHERE lsp.tenLoaiSanPham = ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				SanPhamCon lsp = new SanPhamCon();
				lsp.setIdSanPham(rs.getString(1));
				lsp.setTenSanPham(rs.getString(2));
				lsp.setIdLoaiSanPham(new LoaiSanPham(rs.getString(3)));
				lsp.setIdNhaCungCap(new NhaCungCap(rs.getString(4)));
				lsp.setKichThuoc(rs.getDouble(5));
				lsp.setMauSac(rs.getString(6));
				int trangThai = rs.getInt(7);
				TrangThaiSPEnum trangThaiEnum = TrangThaiSPEnum.getById(trangThai);
				lsp.setTrangThai(trangThaiEnum);
				lsp.thue();
				lsp.setGiaNhap(rs.getDouble(9));
				lsp.setSoLuong(rs.getInt(10));
				lsp.giaBan();

				dsSanPham.add(lsp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dsSanPham;
	}

	public ArrayList<SanPhamCon> loadComboBoxByNhaCungCap(String id) {
		ArrayList<SanPhamCon> dsSanPham = new ArrayList<SanPhamCon>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT  sp.idSanPham, sp.tenSanPham, lsp.tenLoaiSanPham, ncc.tenNhaCungCap, sp.kichThuoc, sp.mauSac, sp.trangThai, sp.thue, sp.giaNhap ,sp.soLuong,sp.giaBan "
					+ "FROM SanPham sp " + "JOIN LoaiSanPham lsp ON sp.loaiSanPham = lsp.idLoaiSanPham "
					+ "JOIN NhaCungCap ncc ON sp.nhaCungCap = ncc.idNhaCungCap " + "WHERE ncc.tenNhaCungCap = ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				SanPhamCon lsp = new SanPhamCon();
				lsp.setIdSanPham(rs.getString(1));
				lsp.setTenSanPham(rs.getString(2));
				lsp.setIdLoaiSanPham(new LoaiSanPham(rs.getString(3)));
				lsp.setIdNhaCungCap(new NhaCungCap(rs.getString(4)));
				lsp.setKichThuoc(rs.getDouble(5));
				lsp.setMauSac(rs.getString(6));
				int trangThai = rs.getInt(7);
				TrangThaiSPEnum trangThaiEnum = TrangThaiSPEnum.getById(trangThai);
				lsp.setTrangThai(trangThaiEnum);
				lsp.thue();
				lsp.setGiaNhap(rs.getDouble(9));
				lsp.setSoLuong(rs.getInt(10));
				lsp.giaBan();
				dsSanPham.add(lsp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dsSanPham;
	}

	public ArrayList<SanPhamCon> getAllSanPhamLoadData() {
		ArrayList<SanPhamCon> dsSanPham = new ArrayList<SanPhamCon>();
		String sql = "SELECT sp.idSanPham, sp.tenSanPham, lsp.tenLoaiSanPham, ncc.tenNhaCungCap, sp.kichThuoc, sp.mauSac, sp.trangThai, sp.thue,sp.giaNhap, sp.soLuong, sp.giaBan ,sp.giaKhuyenMai FROM SanPham sp JOIN LoaiSanPham lsp ON sp.loaiSanPham = lsp.idLoaiSanPham JOIN NhaCungCap ncc ON sp.nhaCungCap = ncc.idNhaCungCap";
		try (PreparedStatement pst = connection.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {
			while (rs.next()) {
				SanPhamCon lsp = new SanPhamCon();
				lsp.setIdSanPham(rs.getString("idSanPham"));
				lsp.setTenSanPham(rs.getString("tenSanPham"));
				lsp.setIdLoaiSanPham(new LoaiSanPham(rs.getString("tenLoaiSanPham")));
				lsp.setIdNhaCungCap(new NhaCungCap(rs.getString("tenNhaCungCap")));
				lsp.setKichThuoc(rs.getDouble("kichThuoc"));
				lsp.setMauSac(rs.getString("mauSac"));
				int trangThai = rs.getInt("trangThai");
				TrangThaiSPEnum trangThaiEnum = TrangThaiSPEnum.getById(trangThai);
				lsp.setTrangThai(trangThaiEnum);
				lsp.thue();
				lsp.setGiaNhap(rs.getDouble("giaNhap"));
				lsp.setSoLuong(rs.getInt("soLuong"));
				lsp.giaBan();
				lsp.setGiaKM(rs.getDouble("giaKhuyenMai"));
				dsSanPham.add(lsp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsSanPham;
	}

	public ArrayList<SanPhamCha> getAllSanPham() {
		ArrayList<SanPhamCha> dsSanPham = new ArrayList<>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "SELECT * FROM SanPham";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				SanPhamCha lsp = new SanPhamCon();
				lsp.setIdSanPham(rs.getString(1));
				lsp.setTenSanPham(rs.getString(2));
				lsp.setIdLoaiSanPham(new LoaiSanPham(rs.getString(3)));
				lsp.setIdNhaCungCap(new NhaCungCap(rs.getString(4)));
				lsp.setKichThuoc(rs.getDouble(5));
				lsp.setMauSac(rs.getString(6));
				String trangThai = rs.getString(7);
				TrangThaiSPEnum trangThaiEnum = TrangThaiSPEnum.getByName(trangThai);
				lsp.setTrangThai(trangThaiEnum);
				dsSanPham.add(lsp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsSanPham;
	}

	public boolean themSanPham(SanPhamCon sp) {
		String sql = "INSERT INTO SanPham VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		try (PreparedStatement pst = connection.prepareStatement(sql)) {
			pst.setString(1, sp.getIdSanPham());
			pst.setString(2, sp.getTenSanPham());
			pst.setString(3, sp.getIdLoaiSanPham().getIdLoaiSanPham());
			pst.setString(4, sp.getIdNhaCungCap().getIdNhaCungCap());
			pst.setDouble(5, sp.getKichThuoc());
			pst.setString(6, sp.getMauSac());
			pst.setInt(7, sp.getTrangThai().getValue());
			pst.setDouble(8, sp.thue());
			pst.setDouble(9, sp.getGiaNhap());
			pst.setInt(10, sp.getSoLuong());
			pst.setDouble(11, sp.giaBan());
			pst.setDouble(12, sp.getGiaKM());
			int n = pst.executeUpdate();
			return n > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public String getLatestProductID() {
		String productID = null;
		String sql = "SELECT TOP 1 idSanPham FROM SanPham ORDER BY idSanPham DESC";
		try (PreparedStatement pst = connection.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {
			if (rs.next()) {
				productID = rs.getString("idSanPham");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productID;
	}

	public boolean capNhatSanPham(SanPhamCon sp) {
		String sql = "UPDATE SanPham "
				+ "SET  tenSanPham = ?, loaiSanPham = ?, nhaCungCap = ?, kichThuoc = ?, mauSac = ?, trangThai = ?, thue = ?, giaNhap = ?, soLuong = ? "
				+ "WHERE idSanPham = ? ";
		try (PreparedStatement pst = connection.prepareStatement(sql)) {
			pst.setString(1, sp.getTenSanPham());
			pst.setString(2, sp.getIdLoaiSanPham().getIdLoaiSanPham());
			pst.setString(3, sp.getIdNhaCungCap().getIdNhaCungCap());
			pst.setDouble(4, sp.getKichThuoc());
			pst.setString(5, sp.getMauSac());
			pst.setInt(6, sp.getTrangThai().getValue());
			pst.setDouble(7, sp.thue());
			pst.setDouble(8, sp.getGiaNhap());
			pst.setInt(9, sp.getSoLuong());
			pst.setString(10, sp.getIdSanPham());
			int n = pst.executeUpdate();
			return n > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void xoaSanPham(String idSanPham) {
		String sql = "DELETE FROM SanPham WHERE idSanPham =?";
		try (PreparedStatement pst = connection.prepareStatement(sql)) {
			pst.setString(1, idSanPham);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}