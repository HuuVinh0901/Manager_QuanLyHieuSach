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
import models.SanPham;
import utils.TrangThaiSPEnum;

public class DAOQuanLySanPham implements Serializable {

	private void close(PreparedStatement pst) {
		if (pst != null) {
			try {
				pst.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<SanPham> getAllSanPhamLoadData() {
		ArrayList<SanPham> dsSanPham = new ArrayList<>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "SELECT sp.hinhAnhSanPham, sp.idSanPham, sp.tenSanPham, lsp.tenLoaiSanPham, ncc.tenNhaCungCap, sp.kichThuoc, sp.mauSac, sp.trangThai, sp.thue, sp.soLuong, sp.giaBan FROM SanPham sp JOIN LoaiSanPham lsp ON sp.loaiSanPham = lsp.idLoaiSanPham JOIN NhaCungCap ncc ON sp.nhaCungCap = ncc.idNhaCungCap";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (con != null) {
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();

				while (rs.next()) {
					SanPham lsp = new SanPham();
					lsp.setHinhAnhSanPham(rs.getString(1));
					lsp.setIdSanPham(rs.getString(2));
					lsp.setTenSanPham(rs.getString(3));
					lsp.setIdLoaiSanPham(new LoaiSanPham(rs.getString(4)));
					lsp.setIdNhaCungCap(new NhaCungCap(rs.getString(5)));
					lsp.setKichThuoc(rs.getDouble(6));
					lsp.setMauSac(rs.getString(7));
					boolean trangThaiValue = rs.getBoolean("trangThai");
		            TrangThaiSPEnum trangThai = trangThaiValue ? TrangThaiSPEnum.DANG_KINH_DOANH : TrangThaiSPEnum.NGUNG_KINH_DOANH;
		            lsp.setTrangThai(trangThai);
					lsp.setThue(rs.getDouble(9));
					lsp.setSoLuong(rs.getInt(10));
					lsp.setGiaBan(rs.getDouble(11));
					dsSanPham.add(lsp);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsSanPham;
	}

	public ArrayList<SanPham> getAllSanPham() {
		ArrayList<SanPham> dsSanPham = new ArrayList<>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "SELECT * FROM SanPham";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				SanPham lsp = new SanPham();
				lsp.setHinhAnhSanPham(rs.getString(1));
				lsp.setIdSanPham(rs.getString(2));
				lsp.setTenSanPham(rs.getString(3));
				lsp.setIdLoaiSanPham(new LoaiSanPham(rs.getString(4)));
				lsp.setIdNhaCungCap(new NhaCungCap(rs.getString(5)));
				lsp.setKichThuoc(rs.getDouble(6));
				lsp.setMauSac(rs.getString(7));
				String trangThai = rs.getString(8);
				TrangThaiSPEnum trangThaiEnum = TrangThaiSPEnum.getByName(trangThai);
				lsp.setTrangThai(trangThaiEnum);
				lsp.setThue(rs.getDouble(9));
				lsp.setSoLuong(rs.getInt(10));
				lsp.setGiaBan(rs.getDouble(11));
				dsSanPham.add(lsp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsSanPham;
	}

	public boolean themSanPham(SanPham sp) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ConnectDB.getConnection();
			if (con != null && !con.isClosed()) {
				String sql = "INSERT INTO SanPham VALUES (?,?,?,?,?,?,?,?,?,?,?)";
				ps = con.prepareStatement(sql);
				ps.setString(1, sp.getHinhAnhSanPham());
				ps.setString(2, sp.getIdSanPham());
				ps.setString(3, sp.getTenSanPham());
				ps.setString(4, sp.getIdLoaiSanPham().getIdLoaiSanPham());
				ps.setString(5, sp.getIdNhaCungCap().getIdNhaCungCap());
				ps.setDouble(6, sp.getKichThuoc());
				ps.setString(7, sp.getMauSac());
				ps.setInt(8, sp.getTrangThai().getValue());
				ps.setDouble(9, sp.getThue());
				ps.setInt(10, sp.getSoLuong());
				ps.setDouble(11, sp.getGiaBan());

				int rowsAffected = ps.executeUpdate();
				return rowsAffected > 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	public String getIdFromTenLoaiSanPham(String tenLoaiSanPham) throws SQLException {
		String id = null;
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "SELECT idLoaiSanPham FROM LoaiSanPham WHERE tenLoaiSanPham = ?";
		try (PreparedStatement statement = con.prepareStatement(sql)) {
			statement.setString(1, tenLoaiSanPham);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					id = resultSet.getString("idLoaiSanPham");
				}
			}
		}
		return id;
	}

}
