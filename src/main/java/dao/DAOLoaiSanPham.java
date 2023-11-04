package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.management.RuntimeErrorException;

import connection.ConnectDB;
import models.LoaiSanPham;
import models.NhaCungCap;
import models.SanPhamCha;
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
		ArrayList<LoaiSanPham> dsLoaiSanPham = new ArrayList<LoaiSanPham>();
		try {
			ConnectDB.getinstance();
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT * FROM LoaiSanPham";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				LoaiSanPham lsp = new LoaiSanPham();
				lsp.setIdLoaiSanPham(rs.getString(1));
				lsp.setTenLoaiSanPham(rs.getString(2));
				dsLoaiSanPham.add(lsp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsLoaiSanPham;
	}

	public boolean themLoaiSanPham(LoaiSanPham lsp)  {
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			String sql = "insert into LoaiSanPham values (?,?)";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, lsp.getIdLoaiSanPham());
			stmt.setString(2, lsp.getTenLoaiSanPham());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return n>0;
	}

	public String getLoaiSanPhamNameByID(String idLoaiSanPham) throws SQLException {
	    String tenLoaiSanPham = null;
	    ConnectDB.getinstance();
	    Connection con = ConnectDB.getConnection();
	    String query = "SELECT tenLoaiSanPham FROM LoaiSanPham WHERE idLoaiSanPham = ?";
	    try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
	        preparedStatement.setString(1, idLoaiSanPham);
	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            if (resultSet.next()) {
	                tenLoaiSanPham = resultSet.getString("tenLoaiSanPham");
	            }
	        }
	    }
	    return tenLoaiSanPham;
	}

	
	 public LoaiSanPham getLoaiSanPhamByTen(String tenLoaiSanPham) {
	        Connection con = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        LoaiSanPham loaiSanPham = null;

	        try {
	            con = ConnectDB.getConnection();
	            String sql = "SELECT * FROM LoaiSanPham WHERE tenLoaiSanPham = ?";
	            ps = con.prepareStatement(sql);
	            ps.setString(1, tenLoaiSanPham);
	            rs = ps.executeQuery();

	            if (rs.next()) {
	                loaiSanPham = new LoaiSanPham();
	                loaiSanPham.setIdLoaiSanPham(rs.getString("idLoaiSanPham"));
	                loaiSanPham.setTenLoaiSanPham(rs.getString("tenLoaiSanPham"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (rs != null) {
	                    rs.close();
	                }
	                if (ps != null) {
	                    ps.close();
	                }
	                if (con != null) {
	                    con.close();
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }

	        return loaiSanPham;
	    }
}
