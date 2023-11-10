package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.ConnectDB;
import models.LoaiSanPham;
import models.NhaCungCap;
import models.SachCon;
import models.TacGia;
import models.TheLoai;
import utils.TrangThaiSPEnum;

public class DAOSach implements Serializable {
	private Connection connection;

	public DAOSach() {
		connection = ConnectDB.getinstance().getConnection();
	}

	public ArrayList<SachCon> getAllSachLoadData() {
		ArrayList<SachCon> dsSach = new ArrayList<>();
		String sql = "SELECT s.idSanPham, s.tenSanPham, tg.tenTacGia, tl.tenTheLoai, s.namXuatBan, s.ISBN, s.soTrang, lsp.tenLoaiSanPham, ncc.tenNhaCungCap, s.kichThuoc, s.mauSac, s.trangThai, s.thue, s.soLuong, s.giaNhap, s.giaBan "
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
				dsSach.add(sc);
				System.out.println("Load thanh cong");
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
				+ "WHERE lsp.idLoaiSanPham = ?";

		try (PreparedStatement pst = connection.prepareStatement(sql)) {
			pst.setString(1, idLoaiSanPham);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				SachCon s = new SachCon();
				s.setIdSanPham(rs.getString("idSanPham"));
				s.setTenSanPham(rs.getString("tenSanPham"));
				s.setTacGia(new TacGia(rs.getString("idTacGia")));
				s.setTheLoai(new TheLoai(rs.getString("idTheLoai")));
				s.setNamXuatBan(rs.getDate("namXuatBan"));
				s.setISBN(rs.getString("ISBN"));
				s.setSoTrang(rs.getInt("soTrang"));
				s.setIdLoaiSanPham(new LoaiSanPham(rs.getString("idLoaiSanPham")));
				s.setIdNhaCungCap(new NhaCungCap(rs.getString("idNhaCungCap")));
				s.setKichThuoc(rs.getDouble("kichThuoc"));
				s.setMauSac(rs.getString("mauSac"));
				int trangThai = rs.getInt("trangThai");
				TrangThaiSPEnum trangThaiEnum = TrangThaiSPEnum.getById(trangThai);
				s.setTrangThai(trangThaiEnum);
				s.thue();
				s.setSoLuong(rs.getInt("soLuong"));
				s.setGiaNhap(rs.getDouble("giaNhap"));
				s.giaKM();
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
			pst.setDouble(17, s.giaKM());
			int n = pst.executeUpdate();
			return n > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}