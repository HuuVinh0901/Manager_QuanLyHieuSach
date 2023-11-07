package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.ConnectDB;
import models.TacGia;

public class DAOTacGia {
	private Connection connection;

	public DAOTacGia() {
		connection = ConnectDB.getinstance().getConnection();
	}

	public ArrayList<TacGia> getAllTacGia() throws SQLException {
		ArrayList<TacGia> dsTacGia = new ArrayList<TacGia>();
		String sql = "SELECT *from TacGia";
		try (PreparedStatement pst = connection.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {
			while (rs.next()) {
				TacGia tg = new TacGia();
				tg.setIdTacGia(rs.getString("idTacGia"));
				tg.setTenTacGia(rs.getString("tenTacGia"));
				tg.setNgaySinh(rs.getDate("ngaySinh"));
				tg.setSoLuongTacPham(rs.getInt("soLuongTacPham"));
				dsTacGia.add(tg);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsTacGia;
	}

	public boolean themTacGia(TacGia tg) throws SQLException {
		String sql = "INSERT INTO TacGia values (?,?,?,?)";
		try (PreparedStatement pst = connection.prepareStatement(sql)) {
			pst.setString(1, tg.getIdTacGia());
			pst.setString(2, tg.getTenTacGia());
			pst.setDate(3, tg.getNgaySinh());
			pst.setInt(4, tg.getSoLuongTacPham());
			int n = pst.executeUpdate();
			return n > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean checkIdTacGia(String idTacGia) throws SQLException {
		String sql = "SELECT COUNT(*) FROM TacGia WHERE idTacGia = ?";
		try (PreparedStatement pst = connection.prepareStatement(sql)) {
			pst.setString(1, idTacGia);
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

	public String getLatestTacGiaID() {
		String tacGiaID = null;
		String sql = "SELECT TOP 1 idTacGia FROM TacGia ORDER BY idTacGia DESC";
		try (PreparedStatement pst = connection.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {
			if (rs.next()) {
				tacGiaID = rs.getString("idTacGia");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tacGiaID;
	}
	public boolean capNhatTacGia(TacGia tg) throws SQLException{
		String sql = "UPDATE TacGia SET tenTacGia = ?, ngaySinh = ?, soLuongTacPham = ? WHERE idTacGia = ?";
		try (PreparedStatement pst = connection.prepareStatement(sql)) {
			pst.setString(1, tg.getTenTacGia());
			pst.setDate(2, tg.getNgaySinh());
			pst.setInt(3,  tg.getSoLuongTacPham());
			pst.setString(4, tg.getIdTacGia());
			int n = pst.executeUpdate();
			return n > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void xoaTacGia(String idTacGia) {
		String sql = "DELETE TacGia Where idTacGia = ?";
		try (PreparedStatement pst = connection.prepareStatement(sql)){
			pst.setString(1, idTacGia);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
