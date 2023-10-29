package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.ConnectDB;
import models.NhaCungCap;

public class DAONhaCungCap {
	private void close(PreparedStatement pst) {
		if(pst!=null) {
			try {
				pst.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<NhaCungCap> getAllNhaCungCap() {
		ArrayList<NhaCungCap> ncc = new ArrayList<>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "SELECT * FROM NhaCungCap";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				NhaCungCap nhaCungCap = new NhaCungCap();
				nhaCungCap.setIdNhaCungCap(rs.getString(1));
				nhaCungCap.setTenNhaCungCap(rs.getString(2));
				nhaCungCap.setDiaChi(rs.getString(3));
				nhaCungCap.setSoDienThoai(rs.getString(4));
				ncc.add(nhaCungCap);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ncc;
	}
	
	public boolean themNhaCungCap(NhaCungCap ncc ) throws SQLException{
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "INSERT INTO NhaCungCap values (?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, ncc.getIdNhaCungCap());
			ps.setString(2, ncc.getTenNhaCungCap());
			ps.setString(3, ncc.getDiaChi());
			ps.setString(4, ncc.getSoDienThoai());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		con.close();
		return false;
	}
	
	
	public NhaCungCap getNhaCungCapTheoTen(String maNhaCungCap) {
		NhaCungCap ncc = null;
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "SELECT tenNhaCungCap FROM NhaCungCap WHERE idNhaCungCap = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, maNhaCungCap);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				ncc = new NhaCungCap();
				ncc.setIdNhaCungCap(rs.getString(1));
				ncc.setTenNhaCungCap(rs.getString(2));
				ncc.setDiaChi(rs.getString(3));
				ncc.setSoDienThoai(rs.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ncc;
	}
}
