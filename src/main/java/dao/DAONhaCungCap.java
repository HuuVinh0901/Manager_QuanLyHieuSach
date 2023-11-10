package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import connection.ConnectDB;
import models.NhaCungCap;

public class DAONhaCungCap {
	private Connection connection;

	public DAONhaCungCap() {
		connection = ConnectDB.getinstance().getConnection();
	}

	public ArrayList<NhaCungCap> getAllNhaCungCap() {
		ArrayList<NhaCungCap> ncc = new ArrayList<>();
		String sql = "SELECT * FROM NhaCungCap";

		try (PreparedStatement pst = connection.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {
			while (rs.next()) {
				NhaCungCap nhaCungCap = new NhaCungCap();
				nhaCungCap.setIdNhaCungCap(rs.getString("idNhaCungCap"));
				nhaCungCap.setTenNhaCungCap(rs.getString("tenNhaCungCap"));
				nhaCungCap.setDiaChi(rs.getString("diaChi"));
				nhaCungCap.setSoDienThoai(rs.getString("soDienThoai"));
				ncc.add(nhaCungCap);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ncc;
	}

	public boolean themNhaCungCap(NhaCungCap ncc) throws SQLException {
		String sql = "INSERT INTO NhaCungCap (idNhaCungCap, tenNhaCungCap, diaChi, soDienThoai) VALUES (?, ?, ?, ?)";
		try (PreparedStatement pst = connection.prepareStatement(sql)) {
			pst.setString(1, ncc.getIdNhaCungCap());
			pst.setString(2, ncc.getTenNhaCungCap());
			pst.setString(3, ncc.getDiaChi());
			pst.setString(4, ncc.getSoDienThoai());
			int n = pst.executeUpdate();
			return n > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public NhaCungCap getNhaCungCapTheoTen(String tenNhaCungCap) {
		NhaCungCap ncc = null;
		String query = "SELECT * FROM NhaCungCap WHERE tenNhaCungCap = ?";
		try (PreparedStatement ps = connection.prepareStatement(query)) {
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

	public String getNhaCungCapNameByID(String idNhaCungCap) {
		String ncc = null;
		String query = "SELECT tenNhaCungCap FROM NhaCungCap WHERE idNhaCungCap = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, idNhaCungCap);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					ncc = resultSet.getString("tenNhaCungCap");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ncc;
	}

	public String getLatestNhaCungCapID() {
		String nhaCungCapID = null;
		String sql = "SELECT TOP 1 idNhaCungCap FROM NhaCungCap ORDER BY idNhaCungCap DESC";
		try (PreparedStatement pst = connection.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {
			if (rs.next()) {
				nhaCungCapID = rs.getString("idNhaCungCap");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nhaCungCapID;
	}

	public boolean capNhatNhaCungCap(NhaCungCap ncc) {
		String sql = "UPDATE NhaCungCap SET tenNhaCungCap = ?, diaChi = ?, soDienThoai = ? WHERE idNhaCungCap = ?";
		try (PreparedStatement pst = connection.prepareStatement(sql)) {
			pst.setString(1, ncc.getTenNhaCungCap());
			pst.setString(2, ncc.getDiaChi());
			pst.setString(3, ncc.getSoDienThoai());
			pst.setString(4, ncc.getIdNhaCungCap());
			int n = pst.executeUpdate();
			return n > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean checkIdNhaCungCap(String idNhaCungCap) {
	    String sql = "SELECT COUNT(*) FROM NhaCungCap WHERE idNhaCungCap = ?";
	    try (PreparedStatement pst = connection.prepareStatement(sql)) {
	        pst.setString(1, idNhaCungCap);
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
	public void xoaNhaCungCap(String idNhaCungCap) {
		String sql = "DELETE NhaCungCap Where idNhaCungCap = ?";
		try (PreparedStatement pst = connection.prepareStatement(sql)){
			pst.setString(1, idNhaCungCap);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
