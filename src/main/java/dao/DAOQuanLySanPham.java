package dao;

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

public class DAOQuanLySanPham {
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

	public ArrayList<SanPham> getAllSanPham() {
		ArrayList<SanPham> dsSanPham = new ArrayList<>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM SanPham");
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
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "insert into SanPham (hinhAnhSanPham, idSanPham, tenSanPham, idLoaiSanPham, idNhaCungCap, kichThuoc, mauSac, trangThai, thue, soLuong, giaBan) values (?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps = con.prepareStatement(sql);
			ps.setString(1, sp.getHinhAnhSanPham());
			ps.setString(2, sp.getIdSanPham());
			ps.setString(3, sp.getTenSanPham());
			ps.setString(4, sp.getIdLoaiSanPham().getTenLoaiSanPham());
			ps.setString(5, sp.getIdNhaCungCap().getTenNhaCungCap());
			ps.setDouble(6, sp.getKichThuoc());
			ps.setString(7, sp.getMauSac());
			ps.setInt(8, sp.getTrangThai().getValue());
			ps.setDouble(9, sp.getThue());
			ps.setInt(10, sp.getSoLuong());
			ps.setDouble(11, sp.getGiaBan());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		con.close();
		return false;
	}
}
