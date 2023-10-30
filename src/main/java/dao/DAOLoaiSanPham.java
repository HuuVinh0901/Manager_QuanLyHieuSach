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
import views.LoaiNhaSanXuatView;
import models.LoaiSanPham;

public class DAOLoaiSanPham implements Serializable {
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

	public String getLoaiSanPhamNameByID(int categoryID) {
	    String query = "SELECT tenLoaiSanPham FROM LoaiSanPham WHERE id = ?";
	    String categoryName = "";
	    
	    try (Connection con = ConnectDB.getinstance().getConnection();
	         PreparedStatement ps = con.prepareStatement(query)) {
	        ps.setInt(1, categoryID);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                categoryName = rs.getString("tenLoaiSanPham");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return categoryName;
	}

}
