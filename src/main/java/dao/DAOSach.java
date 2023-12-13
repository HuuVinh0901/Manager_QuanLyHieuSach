package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.ConnectDB;
import models.ChiTietHoaDon;
import models.LoaiSanPham;
import models.NhaCungCap;
import models.SachCon;
import models.SanPhamCha;
import models.SanPhamCon;
import models.TacGia;
import models.TheLoai;
import utils.TrangThaiSPEnum;

public class DAOSach implements Serializable {
	private Connection connection;

	public DAOSach() {
		connection = ConnectDB.getinstance().getConnection();
	}

	public boolean capNhatSoLuongSach(int sl, String idSP) {
		String sql = "UPDATE Sach " + "SET soLuong = soLuong - ?" + "WHERE idSanPham = ? ";
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

	public SachCon getSach(String idSanPham) {
		SachCon s = new SachCon();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT * FROM Sach WHERE idSanPham = ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, idSanPham);

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				s.setIdSanPham(rs.getString("idSanPham"));
				s.setTenSanPham(rs.getString("tenSanPham"));
				s.setIdLoaiSanPham(new LoaiSanPham(rs.getString("loaiSanPham")));
				s.setIdNhaCungCap(new NhaCungCap(rs.getString("nhaCungCap")));
				s.setKichThuoc(rs.getDouble("kichThuoc"));
				s.setMauSac(rs.getString("mauSac"));
				String trangThai = rs.getString("trangThai");
				TrangThaiSPEnum trangThaiEnum = TrangThaiSPEnum.getByName(trangThai);
				s.setTrangThai(trangThaiEnum);
				s.setGiaNhap(rs.getDouble("giaNhap"));
				s.setGiaKM(rs.getDouble("giaKhuyenMai"));
				s.setSoLuong(rs.getInt("soLuong"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return s;
	}

	public SachCon getSachTimKiemTheoMa(String cond) {
		SachCon sc = new SachCon();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT s.idSanPham, s.tenSanPham, tg.tenTacGia, tl.tenTheLoai, s.namXuatBan, s.ISBN, s.soTrang, lsp.tenLoaiSanPham, ncc.tenNhaCungCap, s.kichThuoc, s.mauSac, s.trangThai, s.thue, s.soLuong, s.giaNhap, s.giaBan,s.giaKhuyenMai "
					+ "FROM Sach s " + "JOIN LoaiSanPham lsp ON s.loaiSanPham = lsp.idLoaiSanPham "
					+ "JOIN NhaCungCap ncc ON s.nhaCungCap = ncc.idNhaCungCap "
					+ "JOIN TacGia tg ON s.tacGia = tg.idTacGia " + "JOIN TheLoai tl ON s.theLoai= tl.idTheLoai "
					+ "WHERE s.idSanPham = ?";

			statement = con.prepareStatement(sql);
			statement.setString(1, cond);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				sc.setIdSanPham(rs.getString("idSanPham"));
				sc.setTenSanPham(rs.getString("tenSanPham"));
				sc.setTacGia(new TacGia(rs.getString("tenTacGia")));
				sc.setTheLoai(new TheLoai(rs.getString("tenTheLoai")));
				sc.setNamXuatBan(rs.getDate("namXuatBan"));
				sc.setISBN(rs.getString("ISBN"));
				sc.setSoTrang(rs.getInt("soTrang"));
				sc.setIdLoaiSanPham(new LoaiSanPham(rs.getString("tenLoaiSanPham")));
				sc.setIdNhaCungCap(new NhaCungCap(rs.getString("tenNhaCungCap")));
				sc.setKichThuoc(rs.getDouble("kichThuoc"));
				sc.setMauSac(rs.getString("mauSac"));
				int trangThai = rs.getInt("trangThai");
				TrangThaiSPEnum trangThaiEnum = TrangThaiSPEnum.getById(trangThai);
				sc.setTrangThai(trangThaiEnum);
				sc.thue();
				sc.setSoLuong(rs.getInt("soLuong"));
				sc.setGiaNhap(rs.getDouble("giaNhap"));
				sc.giaBan();
				sc.setGiaKM(rs.getDouble("giaKhuyenMai"));
				return sc;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public ArrayList<SachCon> getDanhSachSachTimKiemTheoMa(String cond) {
		ArrayList<SachCon> ds = new ArrayList<SachCon>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT s.idSanPham, s.tenSanPham, tg.tenTacGia, tl.tenTheLoai, s.namXuatBan, s.ISBN, s.soTrang, lsp.tenLoaiSanPham, ncc.tenNhaCungCap, s.kichThuoc, s.mauSac, s.trangThai, s.thue, s.soLuong, s.giaNhap, s.giaBan,s.giaKhuyenMai "
					+ "FROM Sach s " + "JOIN LoaiSanPham lsp ON s.loaiSanPham = lsp.idLoaiSanPham "
					+ "JOIN NhaCungCap ncc ON s.nhaCungCap = ncc.idNhaCungCap "
					+ "JOIN TacGia tg ON s.tacGia = tg.idTacGia " + "JOIN TheLoai tl ON s.theLoai= tl.idTheLoai "
					+ "WHERE s.idSanPham LIKE '%" + cond + "%'";

			statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				SachCon sc = new SachCon();
				sc.setIdSanPham(rs.getString("idSanPham"));
				sc.setTenSanPham(rs.getString("tenSanPham"));
				sc.setTacGia(new TacGia(rs.getString("tenTacGia")));
				sc.setTheLoai(new TheLoai(rs.getString("tenTheLoai")));
				sc.setNamXuatBan(rs.getDate("namXuatBan"));
				sc.setISBN(rs.getString("ISBN"));
				sc.setSoTrang(rs.getInt("soTrang"));
				sc.setIdLoaiSanPham(new LoaiSanPham(rs.getString("tenLoaiSanPham")));
				sc.setIdNhaCungCap(new NhaCungCap(rs.getString("tenNhaCungCap")));
				sc.setKichThuoc(rs.getDouble("kichThuoc"));
				sc.setMauSac(rs.getString("mauSac"));
				int trangThai = rs.getInt("trangThai");
				TrangThaiSPEnum trangThaiEnum = TrangThaiSPEnum.getById(trangThai);
				sc.setTrangThai(trangThaiEnum);
				sc.thue();
				sc.setSoLuong(rs.getInt("soLuong"));
				sc.setGiaNhap(rs.getDouble("giaNhap"));
				sc.giaBan();
				sc.setGiaKM(rs.getDouble("giaKhuyenMai"));
				ds.add(sc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ds;
	}

	public ArrayList<SachCon> getDanhSachSachTimKiemTheoDieuKien(String cond) {
		ArrayList<SachCon> ds = new ArrayList<SachCon>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT s.idSanPham, s.tenSanPham, tg.tenTacGia, tl.tenTheLoai, s.namXuatBan, s.ISBN, s.soTrang, lsp.tenLoaiSanPham, ncc.tenNhaCungCap, s.kichThuoc, s.mauSac, s.trangThai, s.thue, s.soLuong, s.giaNhap, s.giaBan,s.giaKhuyenMai "
					+ "FROM Sach s " + "JOIN LoaiSanPham lsp ON s.loaiSanPham = lsp.idLoaiSanPham "
					+ "JOIN NhaCungCap ncc ON s.nhaCungCap = ncc.idNhaCungCap "
					+ "JOIN TacGia tg ON s.tacGia = tg.idTacGia " + "JOIN TheLoai tl ON s.theLoai= tl.idTheLoai "
					+ "WHERE s.idSanPham LIKE '%" + cond + "%' OR " + "s.tenSanPham LIKE '%" + cond + "%' OR "
					+ "lsp.tenLoaiSanPham LIKE N'%" + cond + "%'";

			statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				SachCon sc = new SachCon();
				sc.setIdSanPham(rs.getString("idSanPham"));
				sc.setTenSanPham(rs.getString("tenSanPham"));
				sc.setTacGia(new TacGia(rs.getString("tenTacGia")));
				sc.setTheLoai(new TheLoai(rs.getString("tenTheLoai")));
				sc.setNamXuatBan(rs.getDate("namXuatBan"));
				sc.setISBN(rs.getString("ISBN"));
				sc.setSoTrang(rs.getInt("soTrang"));
				sc.setIdLoaiSanPham(new LoaiSanPham(rs.getString("tenLoaiSanPham")));
				sc.setIdNhaCungCap(new NhaCungCap(rs.getString("tenNhaCungCap")));
				sc.setKichThuoc(rs.getDouble("kichThuoc"));
				sc.setMauSac(rs.getString("mauSac"));
				int trangThai = rs.getInt("trangThai");
				TrangThaiSPEnum trangThaiEnum = TrangThaiSPEnum.getById(trangThai);
				sc.setTrangThai(trangThaiEnum);
				sc.thue();
				sc.setSoLuong(rs.getInt("soLuong"));
				sc.setGiaNhap(rs.getDouble("giaNhap"));
				sc.giaBan();
				sc.setGiaKM(rs.getDouble("giaKhuyenMai"));
				ds.add(sc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ds;
	}

	public ArrayList<SachCon> getAllSachLoadData() {
		ArrayList<SachCon> dsSach = new ArrayList<>();
		String sql = "SELECT s.idSanPham, s.tenSanPham, tg.tenTacGia, tl.tenTheLoai, s.namXuatBan, s.ISBN, s.soTrang, lsp.tenLoaiSanPham, ncc.tenNhaCungCap, s.kichThuoc, s.mauSac, s.trangThai, s.thue, s.soLuong, s.giaNhap, s.giaBan,s.giaKhuyenMai "
				+ "FROM Sach s " + "JOIN LoaiSanPham lsp ON s.loaiSanPham = lsp.idLoaiSanPham "
				+ "JOIN NhaCungCap ncc ON s.nhaCungCap = ncc.idNhaCungCap "
				+ "JOIN TacGia tg ON s.tacGia = tg.idTacGia " + "JOIN TheLoai tl ON s.theLoai= tl.idTheLoai";
		try (PreparedStatement pst = connection.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {
			while (rs.next()) {
				SachCon sc = new SachCon();
				sc.setIdSanPham(rs.getString("idSanPham"));
				sc.setTenSanPham(rs.getString("tenSanPham"));
				sc.setTacGia(new TacGia(rs.getString("tenTacGia")));
				sc.setTheLoai(new TheLoai(rs.getString("tenTheLoai")));
				sc.setNamXuatBan(rs.getDate("namXuatBan"));
				sc.setISBN(rs.getString("ISBN"));
				sc.setSoTrang(rs.getInt("soTrang"));
				sc.setIdLoaiSanPham(new LoaiSanPham(rs.getString("tenLoaiSanPham")));
				sc.setIdNhaCungCap(new NhaCungCap(rs.getString("tenNhaCungCap")));
				sc.setKichThuoc(rs.getDouble("kichThuoc"));
				sc.setMauSac(rs.getString("mauSac"));
				int trangThai = rs.getInt("trangThai");
				TrangThaiSPEnum trangThaiEnum = TrangThaiSPEnum.getById(trangThai);
				sc.setTrangThai(trangThaiEnum);
				sc.thue();
				sc.setSoLuong(rs.getInt("soLuong"));
				sc.setGiaNhap(rs.getDouble("giaNhap"));
				sc.giaBan();
				sc.setGiaKM(rs.getDouble("giaKhuyenMai"));
				dsSach.add(sc);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsSach;
	}

	public ArrayList<SachCon> loadComboBoxByLoaiSanPham(String idLoaiSanPham) throws SQLException {
		ArrayList<SachCon> dsSach = new ArrayList<SachCon>();
		String sql = "SELECT s.idSanPham, s.tenSanPham, tg.tenTacGia, tl.tenTheLoai, s.namXuatBan, s.ISBN, s.soTrang, lsp.tenLoaiSanPham, ncc.tenNhaCungCap, s.kichThuoc, s.mauSac, s.trangThai, s.thue, s.soLuong, s.giaNhap, s.giaBan "
				+ "FROM Sach s " + "JOIN LoaiSanPham lsp ON lsp.idLoaiSanPham = s.loaiSanPham "
				+ "JOIN NhaCungCap ncc ON ncc.idNhaCungCap = s.nhaCungCap "
				+ "JOIN TacGia tg ON tg.idTacGia = s.tacGia " + "JOIN TheLoai tl ON tl.idTheLoai = s.theLoai "
				+ "WHERE lsp.tenLoaiSanPham = ?";

		try (PreparedStatement pst = connection.prepareStatement(sql)) {
			pst.setString(1, idLoaiSanPham);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				SachCon s = new SachCon();
				s.setIdSanPham(rs.getString("idSanPham"));
				s.setTenSanPham(rs.getString("tenSanPham"));
				s.setTacGia(new TacGia(rs.getString("tenTacGia")));
				s.setTheLoai(new TheLoai(rs.getString("tenTheLoai")));
				s.setNamXuatBan(rs.getDate("namXuatBan"));
				s.setISBN(rs.getString("ISBN"));
				s.setSoTrang(rs.getInt("soTrang"));
				s.setIdLoaiSanPham(new LoaiSanPham(rs.getString("tenLoaiSanPham")));
				s.setIdNhaCungCap(new NhaCungCap(rs.getString("tenNhaCungCap")));
				s.setKichThuoc(rs.getDouble("kichThuoc"));
				s.setMauSac(rs.getString("mauSac"));
				int trangThai = rs.getInt("trangThai");
				TrangThaiSPEnum trangThaiEnum = TrangThaiSPEnum.getById(trangThai);
				s.setTrangThai(trangThaiEnum);
				s.thue();
				s.setSoLuong(rs.getInt("soLuong"));
				s.setGiaNhap(rs.getDouble("giaNhap"));

				dsSach.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dsSach;
	}

	public ArrayList<SachCon> loadComboBoxByNhaCungCap(String idNhaCungCap) throws SQLException {
		ArrayList<SachCon> dsSach = new ArrayList<SachCon>();
		String sql = "SELECT s.idSanPham, s.tenSanPham, tg.tenTacGia, tl.tenTheLoai, s.namXuatBan, s.ISBN, s.soTrang, lsp.tenLoaiSanPham, ncc.tenNhaCungCap, s.kichThuoc, s.mauSac, s.trangThai, s.thue, s.soLuong, s.giaNhap, s.giaBan "
				+ "FROM Sach s " + "JOIN LoaiSanPham lsp ON lsp.idLoaiSanPham = s.loaiSanPham "
				+ "JOIN NhaCungCap ncc ON ncc.idNhaCungCap = s.nhaCungCap "
				+ "JOIN TacGia tg ON tg.idTacGia = s.tacGia " + "JOIN TheLoai tl ON tl.idTheLoai = s.theLoai "
				+ "WHERE ncc.tenNhaCungCap = ?";

		try (PreparedStatement pst = connection.prepareStatement(sql)) {
			pst.setString(1, idNhaCungCap);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				SachCon s = new SachCon();
				s.setIdSanPham(rs.getString("idSanPham"));
				s.setTenSanPham(rs.getString("tenSanPham"));
				s.setTacGia(new TacGia(rs.getString("tenTacGia")));
				s.setTheLoai(new TheLoai(rs.getString("tenTheLoai")));
				s.setNamXuatBan(rs.getDate("namXuatBan"));
				s.setISBN(rs.getString("ISBN"));
				s.setSoTrang(rs.getInt("soTrang"));
				s.setIdLoaiSanPham(new LoaiSanPham(rs.getString("tenLoaiSanPham")));
				s.setIdNhaCungCap(new NhaCungCap(rs.getString("tenNhaCungCap")));
				s.setKichThuoc(rs.getDouble("kichThuoc"));
				s.setMauSac(rs.getString("mauSac"));
				int trangThai = rs.getInt("trangThai");
				TrangThaiSPEnum trangThaiEnum = TrangThaiSPEnum.getById(trangThai);
				s.setTrangThai(trangThaiEnum);
				s.thue();
				s.setSoLuong(rs.getInt("soLuong"));
				s.setGiaNhap(rs.getDouble("giaNhap"));

				dsSach.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dsSach;
	}

	public ArrayList<SachCon> loadComboBoxByTacGia(String idTacGia) throws SQLException {
		ArrayList<SachCon> dsSach = new ArrayList<SachCon>();
		String sql = "SELECT s.idSanPham, s.tenSanPham, tg.tenTacGia, tl.tenTheLoai, s.namXuatBan, s.ISBN, s.soTrang, lsp.tenLoaiSanPham, ncc.tenNhaCungCap, s.kichThuoc, s.mauSac, s.trangThai, s.thue, s.soLuong, s.giaNhap, s.giaBan "
				+ "FROM Sach s " + "JOIN LoaiSanPham lsp ON lsp.idLoaiSanPham = s.loaiSanPham "
				+ "JOIN NhaCungCap ncc ON ncc.idNhaCungCap = s.nhaCungCap "
				+ "JOIN TacGia tg ON tg.idTacGia = s.tacGia " + "JOIN TheLoai tl ON tl.idTheLoai = s.theLoai "
				+ "WHERE tg.tenTacGia = ?";

		try (PreparedStatement pst = connection.prepareStatement(sql)) {
			pst.setString(1, idTacGia);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				SachCon s = new SachCon();
				s.setIdSanPham(rs.getString("idSanPham"));
				s.setTenSanPham(rs.getString("tenSanPham"));
				s.setTacGia(new TacGia(rs.getString("tenTacGia")));
				s.setTheLoai(new TheLoai(rs.getString("tenTheLoai")));
				s.setNamXuatBan(rs.getDate("namXuatBan"));
				s.setISBN(rs.getString("ISBN"));
				s.setSoTrang(rs.getInt("soTrang"));
				s.setIdLoaiSanPham(new LoaiSanPham(rs.getString("tenLoaiSanPham")));
				s.setIdNhaCungCap(new NhaCungCap(rs.getString("tenNhaCungCap")));
				s.setKichThuoc(rs.getDouble("kichThuoc"));
				s.setMauSac(rs.getString("mauSac"));
				int trangThai = rs.getInt("trangThai");
				TrangThaiSPEnum trangThaiEnum = TrangThaiSPEnum.getById(trangThai);
				s.setTrangThai(trangThaiEnum);
				s.thue();
				s.setSoLuong(rs.getInt("soLuong"));
				s.setGiaNhap(rs.getDouble("giaNhap"));

				dsSach.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dsSach;
	}

	public ArrayList<SachCon> loadComboBoxByTheLoai(String idTheLoai) throws SQLException {
		ArrayList<SachCon> dsSach = new ArrayList<SachCon>();
		String sql = "SELECT s.idSanPham, s.tenSanPham, tg.tenTacGia, tl.tenTheLoai, s.namXuatBan, s.ISBN, s.soTrang, lsp.tenLoaiSanPham, ncc.tenNhaCungCap, s.kichThuoc, s.mauSac, s.trangThai, s.thue, s.soLuong, s.giaNhap, s.giaBan "
				+ "FROM Sach s " + "JOIN LoaiSanPham lsp ON lsp.idLoaiSanPham = s.loaiSanPham "
				+ "JOIN NhaCungCap ncc ON ncc.idNhaCungCap = s.nhaCungCap "
				+ "JOIN TacGia tg ON tg.idTacGia = s.tacGia " + "JOIN TheLoai tl ON tl.idTheLoai = s.theLoai "
				+ "WHERE tl.tenTheLoai= ?";

		try (PreparedStatement pst = connection.prepareStatement(sql)) {
			pst.setString(1, idTheLoai);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				SachCon s = new SachCon();
				s.setIdSanPham(rs.getString("idSanPham"));
				s.setTenSanPham(rs.getString("tenSanPham"));
				s.setTacGia(new TacGia(rs.getString("tenTacGia")));
				s.setTheLoai(new TheLoai(rs.getString("tenTheLoai")));
				s.setNamXuatBan(rs.getDate("namXuatBan"));
				s.setISBN(rs.getString("ISBN"));
				s.setSoTrang(rs.getInt("soTrang"));
				s.setIdLoaiSanPham(new LoaiSanPham(rs.getString("tenLoaiSanPham")));
				s.setIdNhaCungCap(new NhaCungCap(rs.getString("tenNhaCungCap")));
				s.setKichThuoc(rs.getDouble("kichThuoc"));
				s.setMauSac(rs.getString("mauSac"));
				int trangThai = rs.getInt("trangThai");
				TrangThaiSPEnum trangThaiEnum = TrangThaiSPEnum.getById(trangThai);
				s.setTrangThai(trangThaiEnum);
				s.thue();
				s.setSoLuong(rs.getInt("soLuong"));
				s.setGiaNhap(rs.getDouble("giaNhap"));

				dsSach.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dsSach;
	}

	public String getLatestProductID() {
		String productID = null;
		String sql = "SELECT TOP 1 idSanPham FROM Sach ORDER BY idSanPham DESC";
		try (PreparedStatement pst = connection.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {
			if (rs.next()) {
				productID = rs.getString("idSanPham");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productID;
	}

	public double getSoLuongTonTheoNgay(String dayStart, String dayEnd) {
		double soLuongTon = 0;
		String sql = "SELECT SUM(s.soLuong) FROM Sach s "
				+ "JOIN ChiTietHoaDonSach ct ON s.idSanPham = ct.idSanPham "
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
	
	public double getSoLuongDaBanTheoNgay(String dayStart, String dayEnd) {
		double soLuongTon = 0;
		String sql = "SELECT SUM(soLuong) FROM ChiTietHoaDonSach ct join HoaDon hd ON ct.idDonHang = hd.idDonHang WHERE hd.ngayLap BETWEEN ? AND ?";
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
                + "FROM ChiTietHoaDonSach ct " 
                + "JOIN Sach s ON ct.idSanPham = s.idSanPham "
                + "JOIN HoaDon hd ON ct.idDonHang = hd.idDonHang "
                + "JOIN TacGia tg ON s.tacGia = tg.idTacGia "
                + "JOIN TheLoai tl ON s.theLoai = tl.idTheLoai "
                + "JOIN LoaiSanPham lsp ON s.loaiSanPham = lsp.idLoaiSanPham "
                + "JOIN NhaCungCap ncc ON s.nhaCungCap = ncc.idNhaCungCap " 
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
	
	public double getDoanhThuTheoNgay(String dayStart, String dayEnd) {
		double soLuongTon = 0;
		String sql = "SELECT SUM(ct.thanhTien) AS doanhThu "
                + "FROM ChiTietHoaDonSach ct " 
                + "JOIN Sach s ON ct.idSanPham = s.idSanPham "
                + "JOIN HoaDon hd ON ct.idDonHang = hd.idDonHang "
                + "JOIN LoaiSanPham lsp ON s.loaiSanPham = lsp.idLoaiSanPham "
                + "JOIN NhaCungCap ncc ON s.nhaCungCap = ncc.idNhaCungCap " 
                + "JOIN TacGia tg ON s.tacGia = tg.idTacGia "
                + "JOIN TheLoai tl ON s.theLoai = tl.idTheLoai "
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
	
	public ArrayList<SachCon> getTopSanPhamBanChamTheoNgay(String dayStart, String dayEnd) {
		ArrayList<SachCon> dsSanPham = new ArrayList<>();
		ArrayList<ChiTietHoaDon> dsChiTietHoaDon = new ArrayList<ChiTietHoaDon>();
		String sql = "SELECT s.idSanPham, s.tenSanPham,tg.tenTacGia,tl.tenTheLoai, lsp.tenLoaiSanPham, ncc.tenNhaCungCap, s.soLuong, "
				+ "SUM(ct.soLuong) AS soLuongBan, s.giaNhap, s.giaBan, "
				+ "SUM(ct.thanhTien) AS doanhThu, SUM(ct.loiNhuan) AS loiNhuan, s.trangThai "
				+ "FROM ChiTietHoaDonSach ct " + "JOIN Sach s ON ct.idSanPham = s.idSanPham "
				+ "JOIN HoaDon hd ON ct.idDonHang = hd.idDonHang "
				+ "JOIN LoaiSanPham lsp ON s.loaiSanPham = lsp.idLoaiSanPham "
				+ "JOIN NhaCungCap ncc ON s.nhaCungCap = ncc.idNhaCungCap " 
				+ "JOIN TacGia tg ON s.tacGia = tg.idTacGia "
                + "JOIN TheLoai tl ON s.theLoai = tl.idTheLoai "
				+ "WHERE hd.ngayLap BETWEEN ? AND ? "
				+ "GROUP BY s.idSanPham, s.tenSanPham,tg.tenTacGia,tl.tenTheLoai, lsp.tenLoaiSanPham, ncc.tenNhaCungCap, s.soLuong, "
				+ "s.giaNhap, s.giaBan, s.trangThai " + "ORDER BY soLuongBan ASC";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, dayStart);
			preparedStatement.setString(2, dayEnd);

			try (ResultSet rs = preparedStatement.executeQuery()) {
				while (rs.next()) {
					SachCon sp = new SachCon();
					sp.setIdSanPham(rs.getString("idSanPham"));
					sp.setTenSanPham(rs.getString("tenSanPham"));
					sp.setTacGia(new TacGia(rs.getString("tenTacGia")));
					sp.setTheLoai(new TheLoai(rs.getString("tenTheLoai")));
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
	
	public ArrayList<SachCon> getTopSanPhamTheoNgay(String dayStart, String dayEnd) {
		ArrayList<SachCon> dsSanPham = new ArrayList<>();
		ArrayList<ChiTietHoaDon> dsChiTietHoaDon = new ArrayList<ChiTietHoaDon>();
		String sql = "SELECT " + "s.idSanPham, " + "s.tenSanPham, " + "tg.tenTacGia, " + "tl.tenTheLoai, "
				+ "lsp.tenLoaiSanPham, " + "ncc.tenNhaCungCap, " + "s.soLuong, " + "SUM(ct.soLuong) AS soLuongBan, "
				+ "s.giaNhap, " + "s.giaBan, " + "SUM(ct.thanhTien) AS doanhThu, " + "SUM(ct.loiNhuan) AS loiNhuan, "
				+ "s.trangThai " + "FROM " + "ChiTietHoaDonSach ct " + "JOIN " + "Sach s ON s.idSanPham = ct.idSanPham "
				+ "JOIN " + "HoaDon hd ON ct.idDonHang = hd.idDonHang " + "JOIN "
				+ "LoaiSanPham lsp ON s.loaiSanPham = lsp.idLoaiSanPham " + "JOIN "
				+ "NhaCungCap ncc ON s.nhaCungCap = ncc.idNhaCungCap " + "JOIN "
				+ "TacGia tg ON s.tacGia = tg.idTacGia " + "JOIN " + "TheLoai tl ON s.theLoai = tl.idTheLoai "
				+ "WHERE " + "hd.ngayLap BETWEEN ? AND ? " + "GROUP BY " + "s.idSanPham, "
				+ "s.tenSanPham, " + "tg.tenTacGia, " + "tl.tenTheLoai, " + "lsp.tenLoaiSanPham, "
				+ "ncc.tenNhaCungCap, " + "s.soLuong, " + "s.giaNhap, " + "s.giaBan, " + "s.trangThai " + "ORDER BY "
				+ "soLuongBan DESC;";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, dayStart);
			preparedStatement.setString(2, dayEnd);

			try (ResultSet rs = preparedStatement.executeQuery()) {
				while (rs.next()) {
					SachCon s = new SachCon();
					s.setIdSanPham(rs.getString("idSanPham"));
					s.setTenSanPham(rs.getString("tenSanPham"));
					s.setTacGia(new TacGia(rs.getString("tenTacGia")));
					s.setTheLoai(new TheLoai(rs.getString("tenTheLoai")));
					s.setIdLoaiSanPham(new LoaiSanPham(rs.getString("tenLoaiSanPham")));
					s.setIdNhaCungCap(new NhaCungCap(rs.getString("tenNhaCungCap")));
					s.setSoLuong(rs.getInt("soLuong"));
					s.setGiaNhap(rs.getDouble("giaNhap"));
					s.setSoLuongBan(rs.getInt("soLuongBan"));
					s.giaBan();
					s.setDoanhThu(rs.getDouble("doanhThu"));
					s.setLoiNhuan(rs.getDouble("loiNhuan"));
					s.setTrangThai(TrangThaiSPEnum.getById(rs.getInt("trangThai")));
					dsSanPham.add(s);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsSanPham;
	}

	public boolean themSach(SachCon s) throws SQLException {
		String sql = "INSERT INTO Sach VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try (PreparedStatement pst = connection.prepareStatement(sql)) {
			pst.setString(1, s.getIdSanPham());
			pst.setString(2, s.getTenSanPham());
			pst.setString(3, s.getTacGia().getIdTacGia());
			pst.setString(4, s.getTheLoai().getIdTheLoai());
			pst.setDate(5, s.getNamXuatBan());
			pst.setString(6, s.getISBN());
			pst.setInt(7, s.getSoTrang());
			pst.setString(8, s.getIdLoaiSanPham().getIdLoaiSanPham());
			pst.setString(9, s.getIdNhaCungCap().getIdNhaCungCap());
			pst.setDouble(10, s.getKichThuoc());
			pst.setString(11, s.getMauSac());
			pst.setInt(12, s.getTrangThai().getValue());
			pst.setDouble(13, s.thue());
			pst.setInt(14, s.getSoLuong());
			pst.setDouble(15, s.getGiaNhap());
			pst.setDouble(16, s.giaBan());
			pst.setDouble(17, s.getGiaKM());
			int n = pst.executeUpdate();
			return n > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean capNhatSach(SachCon s) throws SQLException {
		String sql = "UPDATE Sach "
				+ "SET tenSanPham = ?, tacGia = ?, theLoai = ?, namXuatBan = ?, ISBN = ?, soTrang = ?, loaiSanPham = ?, "
				+ "nhaCungCap = ?, kichThuoc = ?, mauSac = ?, trangThai = ? ,thue = ?, soLuong = ?, giaNhap = ?, giaBan = ?, giaKhuyenMai = ? WHERE idSanPham = ? ";
		try (PreparedStatement pst = connection.prepareStatement(sql)) {
			pst.setString(1, s.getTenSanPham());
			pst.setString(2, s.getTacGia().getIdTacGia());
			pst.setString(3, s.getTheLoai().getIdTheLoai());
			pst.setDate(4, s.getNamXuatBan());
			pst.setString(5, s.getISBN());
			pst.setInt(6, s.getSoTrang());
			pst.setString(7, s.getIdLoaiSanPham().getIdLoaiSanPham());
			pst.setString(8, s.getIdNhaCungCap().getIdNhaCungCap());
			pst.setDouble(9, s.getKichThuoc());
			pst.setString(10, s.getMauSac());
			pst.setInt(11, s.getTrangThai().getValue());
			pst.setDouble(12, s.thue());
			pst.setInt(13, s.getSoLuong());
			pst.setDouble(14, s.getGiaNhap());
			pst.setDouble(15, s.giaBan());
			pst.setDouble(16, s.getGiaKM());
			pst.setString(17, s.getIdSanPham());
			int n = pst.executeUpdate();
			return n > 0;
		} catch (SQLException e) {
			return false;
		}
	}

	public void xoaSach(String idSach) throws SQLException {
		String sql = "DELETE FROM Sach WHERE idSanPham =?";
		try (PreparedStatement pst = connection.prepareStatement(sql)) {
			pst.setString(1, idSach);
			pst.executeUpdate();
		} catch (SQLException e) {
			
		}
	}

	public boolean checkIdSach(String idSanPham) throws SQLException {
		String sql = "SELECT COUNT(*) FROM Sach WHERE idSanPham = ?";
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
		return false;
	}

	public ArrayList<SachCon> getSachTimKiem(String cond) {
		ArrayList<SachCon> dsSach = new ArrayList<SachCon>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT s.idSanPham, s.tenSanPham, s.giaNhap,s.giaBan FROM Sach s WHERE s.idSanPham LIKE '%"
					+ cond + "%'OR s.tenSanPham LIKE '%" + cond + "%'";

			statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				SachCon s = new SachCon();
				s.setIdSanPham(rs.getString(1));
				s.setTenSanPham(rs.getString(2));
				s.setGiaNhap(rs.getDouble(3));
				s.giaBan();
				dsSach.add(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dsSach;
	}

}