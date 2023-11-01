package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.ConnectDB;
import models.NhanVien;





public class DAONhanVien {
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
	public boolean themNhanVien(NhanVien nv) throws SQLException {
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "insert into NhanVien values (?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps= con.prepareStatement(sql);
			
			ps.setString(1, nv.getIdNhanVien());
			ps.setString(2,nv.getTenNhanVien());
			ps.setString(3, nv.getSoDienThoai());
			ps.setString(4, nv.getDiaChi());
			ps.setString(5,nv.getEmail());
			ps.setDate(6, nv.getNgaySinh());
			ps.setBoolean(7, nv.isGioiTinh());
			ps.setString(8,nv.getChucVu());
			ps.setBoolean(9, nv.isTrangThai());
			ps.setFloat(10, nv.getLuong());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		con.close();
		return false;
	}
	public ArrayList<NhanVien> getAllDanhSachNV() {
		ArrayList<NhanVien> listNV=new ArrayList<>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select * from NhanVien");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				NhanVien nv=new NhanVien();
				nv.setIdNhanVien(rs.getString(1));
				nv.setTenNhanVien(rs.getString(2));
				nv.setSoDienThoai(rs.getString(3));
				nv.setDiaChi(rs.getString(4));
				nv.setEmail(rs.getString(5));
				nv.setNgaySinh(rs.getDate(6));
				nv.setGioiTinh(rs.getBoolean(7));
				nv.setChucVu(rs.getString(8));
				nv.setTrangThai(rs.getBoolean(9));
				nv.setLuong(rs.getFloat(10));
				listNV.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listNV;
	}
}
