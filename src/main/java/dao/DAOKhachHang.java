package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.ConnectDB;
import models.KhachHang;




public class DAOKhachHang {
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
	public boolean themKhachHang(KhachHang kh) throws SQLException {
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "insert into KhachHang values (?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps= con.prepareStatement(sql);
			
			ps.setString(1, kh.getIdKhachHang());
			ps.setString(2, kh.getTenKhachHang());
			ps.setString(3, kh.getSdt());
			ps.setString(4, kh.getEmail());
			ps.setString(5, kh.getDiaChi());
			ps.setDate(6, kh.getNgaySinh());
			ps.setBoolean(7, kh.isGioiTinh());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		con.close();
		return false;
	}
	public ArrayList<KhachHang> getAllDanhSachKH() {
		ArrayList<KhachHang> listKH=new ArrayList<>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select * from KhachHang");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				KhachHang kh=new KhachHang();
				kh.setIdKhachHang(rs.getString(1));
				kh.setTenKhachHang(rs.getString(2));
				kh.setSdt(rs.getString(3));
				kh.setEmail(rs.getString(4));
				kh.setDiaChi(rs.getString(5));
				kh.setNgaySinh(rs.getDate(6));
				kh.setGioiTinh(rs.getBoolean(7));
				listKH.add(kh);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listKH;
	}
}
