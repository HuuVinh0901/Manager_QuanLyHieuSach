package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.ConnectDB;
import models.KhachHang;
import models.KhuyenMai;
import utils.LoaiKMEnum;

public class DAOKhuyenMai {
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
	public ArrayList<KhuyenMai> getAllDanhSachKM() {
		ArrayList<KhuyenMai> listKM=new ArrayList<>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select * from KhuyenMai");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				KhuyenMai km=new KhuyenMai();
				km.setId(rs.getString(1));
				km.setTen(rs.getString(2));
				km.setNgayBatDau(rs.getDate(3));
				km.setTrangThai(rs.getBoolean(4));
				int loai=rs.getInt(5);
				LoaiKMEnum loaiEnum=LoaiKMEnum.getById(loai);
				km.setLoai(loaiEnum);
				listKM.add(km);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listKM;
	}
	public boolean ThemKM(KhuyenMai km) throws SQLException {
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "insert into KhuyenMai values (?,?,?,?,?)";
		try {
			PreparedStatement ps= con.prepareStatement(sql);
			
			ps.setString(1, km.getId());
			ps.setString(2, km.getTen());
			ps.setDate(3, km.getNgayBatDau());
			ps.setBoolean(4, km.getTrangThai());
			ps.setInt(5, km.getLoai().getValue());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		con.close();
		return false;
	}
	public void updateKM(KhuyenMai km) {
		ConnectDB.getinstance();
		PreparedStatement pst = null;
		Connection con = ConnectDB.getConnection();
		String sql = "update KhuyenMai set tenKM = ?, ngayBatDau = ?, trangThai = ?, loaiKM = ? where idKM = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, km.getTen());
			pst.setDate(2, km.getNgayBatDau());
			pst.setBoolean(3, km.getTrangThai());
			pst.setInt(4, km.getLoai().getValue());
			
			pst.setString(5, km.getId());
			pst.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pst);
		}
	}
}
