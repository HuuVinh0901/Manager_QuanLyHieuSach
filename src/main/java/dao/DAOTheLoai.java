package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.ConnectDB;
import models.TheLoai;

public class DAOTheLoai {
	private Connection connection;
	public DAOTheLoai() {
		connection = ConnectDB.getinstance().getConnection();
	}
	
	public ArrayList<TheLoai> getAllTheLoai()throws SQLException{
		ArrayList<TheLoai> dsTheLoai = new ArrayList<TheLoai>();
		String sql = "SELECT *FROM TheLoai";
		try (PreparedStatement pst = connection.prepareStatement(sql);
				ResultSet rs = pst.executeQuery()){
			while(rs.next()) {
				TheLoai tl = new TheLoai();
				tl.setIdTheLoai(rs.getString("idTheLoai"));
				tl.setTenTheLoai(rs.getString("tenTheLoai"));
				tl.setSoLuongSach(rs.getInt("soLuongSach"));
				tl.setMoTa(rs.getString("moTa"));
				dsTheLoai.add(tl);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsTheLoai;
	}
	
	public boolean themTheLoai(TheLoai tl)throws SQLException {
		String sql = "INSERT INTO VALUES (?,?,?,?)";
		try (PreparedStatement pst = connection.prepareStatement(sql)){
			pst.setString(1, tl.getIdTheLoai());
			pst.setString(2, tl.getTenTheLoai());
			pst.setInt(3, tl.getSoLuongSach());
			pst.setString(4, tl.getMoTa());
			int n = pst.executeUpdate();
			return n > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}	
	}
	
	public String getLatestTheLoaiID() {
		String theLoaiID = null;
		String sql = "SELECT TOP 1 idTheLoai FROM TheLoai ORDER BY idTheLoai DESC";
		try (PreparedStatement pst = connection.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {
			if (rs.next()) {
				theLoaiID = rs.getString("idTheLoai");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return theLoaiID;
	}
	
	public boolean capNhatTheLoai(TheLoai tl)throws SQLException{
		String sql = "UPDATE TheLoai SET tenTheLoai = ?, soLuongTon = ?, moTa = ? WHERE idTheLoai = ?";
		try (PreparedStatement pst = connection.prepareStatement(sql)){
			pst.setString(1, tl.getTenTheLoai());
			pst.setInt(2, tl.getSoLuongSach());
			pst.setString(3, tl.getMoTa());
			pst.setString(4, tl.getIdTheLoai());
			int n = pst.executeUpdate();
			return n >0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean checkIdTheLoai(String idTheLoai) {
	    String sql = "SELECT COUNT(*) FROM TheLoai WHERE idTheLoai = ?";
	    try (PreparedStatement pst = connection.prepareStatement(sql)) {
	        pst.setString(1, idTheLoai);
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
	
	public void xoaTheLoai(String idTheLoai) {
		String sql = "DELETE NhaCungCap Where idTheLoai = ?";
		try (PreparedStatement pst = connection.prepareStatement(sql)){
			pst.setString(1, idTheLoai);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
