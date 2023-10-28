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

public class DAOSanPham implements Serializable{
	private TrangThaiSPEnum trangThaiEnum;
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

	public ArrayList<SanPham> getAllSanPham(){
		ArrayList<SanPham> dsSanPham = new ArrayList<SanPham>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM SanPham");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				SanPham sp = new SanPham();
				sp.setIdSanPham(rs.getString(0));
				sp.setTenSanPham(rs.getString(2));
				sp.setIdLoaiSanPham(new LoaiSanPham(rs.getString(3)));
				sp.setIdNhaCungCap(new NhaCungCap(rs.getString(4)));
				sp.setKichThuoc(rs.getDouble(5));
				sp.setMauSac(rs.getString(6));
				sp.setTrangThai(TrangThaiSPEnum.getByName(rs.getNString("trangThaiEnum")));
				sp.setThue(rs.getDouble(7));
				sp.setSoLuong(rs.getInt(8));
				sp.setGiaBan(rs.getDouble(9));
				dsSanPham.add(sp);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsSanPham;
	}
}


//public ArrayList<NhanVien> getAllDanhSachNV() {
//	ArrayList<NhanVien> lstNV = new ArrayList<>();
//	ConnectDB.getinstance();
//	Connection con = ConnectDB.getConnection();
//	try {
//		PreparedStatement ps = con.prepareStatement("select * from NhanVien");
//		ResultSet rs = ps.executeQuery();
//		while (rs.next()) {
//			NhanVien nv = new NhanVien();
//			nv.setMaNhanVien(rs.getString(1));
//			nv.setTaiKhoan(new TaiKhoan(rs.getString(2)));
//			nv.setTenNhanVien(rs.getString(3));
//			nv.setChucVu(rs.getString(4));
//			nv.setGioiTinh(rs.getString(5));
//			nv.setNgaySinh(rs.getDate(6));
//			nv.setDiaChi(rs.getString(7));
//			nv.setSoDienThoai(rs.getString(8));
//			nv.setSoCanCuoc(rs.getString(9));
//			nv.setLuong(rs.getDouble(10));
//			lstNV.add(nv);
//		}
//	} catch (SQLException e) {
//		e.printStackTrace();
//	}
//	return lstNV;
//}