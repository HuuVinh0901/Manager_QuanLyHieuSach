package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connection.ConnectDB;
import models.LoaiSanPham;
import models.NhaCungCap;

public class DAONhaCungCap implements Serializable {
	private void close(PreparedStatement pst) {
		if (pst != null) {
			try {
				pst.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<NhaCungCap> getAllNhaCungCap() {
		ArrayList<NhaCungCap> ncc = new ArrayList<>();
		try {
			ConnectDB.getinstance();
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT * FROM NhaCungCap";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				NhaCungCap nhaCungCap = new NhaCungCap();
				nhaCungCap.setIdNhaCungCap(rs.getString(1));
				nhaCungCap.setTenNhaCungCap(rs.getString(2));
				nhaCungCap.setDiaChi(rs.getString(3));
				nhaCungCap.setSoDienThoai(rs.getString(4));
				ncc.add(nhaCungCap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ncc;
	}



	public boolean themNhaCungCap(NhaCungCap ncc)  {
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		String sql = "INSERT INTO NhaCungCap values (?,?,?,?)";
		int n =0;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, ncc.getIdNhaCungCap());
			stmt.setString(2, ncc.getTenNhaCungCap());
			stmt.setString(3, ncc.getDiaChi());
			stmt.setString(4, ncc.getSoDienThoai());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return n >0;
	}

	public NhaCungCap getNhaCungCapTheoTen(String tenNhaCungCap) {
		NhaCungCap ncc = null;
		try (Connection con = ConnectDB.getinstance().getConnection();
				PreparedStatement ps = con.prepareStatement("SELECT * FROM NhaCungCap WHERE tenNhaCungCap = ?")) {
			ps.setString(1, tenNhaCungCap);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					ncc = new NhaCungCap();
					ncc.setIdNhaCungCap(rs.getString("idNhaCungCap"));
					ncc.setTenNhaCungCap(rs.getString("tenNhaCungCap"));
					ncc.setDiaChi(rs.getString("diaChi"));
					ncc.setSoDienThoai(rs.getString("soDienThoai"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ncc;
	}

	public String getNhaCungCapNameByID(String idNhaCungCap) throws SQLException {
		String ncc = null;
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String query = "select tenNhaCungCap from NhaCungCap where idNhaCungCap= ?";
		try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
			preparedStatement.setString(1, idNhaCungCap);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					ncc = resultSet.getString("tenNhaCungCap");
				}
			}
		}
		return ncc;
	}

	public NhaCungCap getNhaCungCapByTen(String tenNhaCungCap) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		NhaCungCap nhaCungCap = null;

		try {
			con = ConnectDB.getConnection();
			String sql = "SELECT * FROM NhaCungCap WHERE tenNhaCungCap = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, tenNhaCungCap);
			rs = ps.executeQuery();

			if (rs.next()) {
				nhaCungCap = new NhaCungCap();
				nhaCungCap.setIdNhaCungCap(rs.getString("idNhaCungCap"));
				nhaCungCap.setTenNhaCungCap(rs.getString("tenNhaCungCap"));
				nhaCungCap.setTenNhaCungCap(rs.getString("diaChi"));
				nhaCungCap.setTenNhaCungCap(rs.getString("soDienThoai"));
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

		return nhaCungCap;
	}

}
