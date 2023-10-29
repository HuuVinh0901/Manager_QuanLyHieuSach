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
import views.LoaiNhaSanXuatView;
import models.LoaiSanPham;

public class DAOLoaiSanPham {
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

	public ArrayList<LoaiSanPham> getAllLoaiSanPham() {
		ArrayList<LoaiSanPham> dsLoaiSanPham = new ArrayList<>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM LoaiSanPham");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				LoaiSanPham lsp = new LoaiSanPham();
				lsp.setIdLoaiSanPham(rs.getString(1));
				lsp.setTenLoaiSanPham(rs.getString(2));
				dsLoaiSanPham.add(lsp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsLoaiSanPham;
	}

	public boolean themLoaiSanPham(LoaiSanPham lsp) throws SQLException {
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "insert into LoaiSanPham values (?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, lsp.getIdLoaiSanPham());
			ps.setString(2, lsp.getTenLoaiSanPham());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		con.close();
		return false;
	}

	public LoaiSanPham getLoaiSanPhamTheoTen(String maLoaiSanPham) {
	    LoaiSanPham lsp = null;
	    ConnectDB.getinstance();
	    Connection con = ConnectDB.getConnection();
	    String sql = "SELECT tenLoaiSanPham FROM LoaiSanPham WHERE idLoaiSanPham = ?";
	    
	    try {
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setString(1, maLoaiSanPham);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            lsp = new LoaiSanPham();
	            lsp.setIdLoaiSanPham(rs.getString("idLoaiSanPham"));
	            lsp.setTenLoaiSanPham(rs.getString("tenLoaiSanPham"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return lsp;
	}

}
