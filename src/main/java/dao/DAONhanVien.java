package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

	public NhanVien getNhanVienBySoDienThoai(String soDienThoai) {
		NhanVien nv = new NhanVien();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT * FROM NhanVien WHERE soDienThoai = ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, soDienThoai);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				nv.setId(rs.getString(1));
				nv.setTen(rs.getString(2));
				nv.setSoDienThoai(rs.getString(3));
				nv.setDiaChi(rs.getString(4));
				nv.setEmail(rs.getString(5));
				nv.setNgaySinh(rs.getDate(6));
				nv.setGioiTinh(rs.getBoolean(7));
				nv.setChucVu(rs.getString(8));
				nv.setTrangThai(rs.getBoolean(9));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return nv;
	}
	public NhanVien getNhanVien(String idNV) {
		NhanVien nv = new NhanVien();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT * FROM NhanVien WHERE idNhanVien = ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, idNV);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				nv.setId(rs.getString(1));
				nv.setTen(rs.getString(2));
				nv.setSoDienThoai(rs.getString(3));
				nv.setDiaChi(rs.getString(4));
				nv.setEmail(rs.getString(5));
				nv.setNgaySinh(rs.getDate(6));
				nv.setGioiTinh(rs.getBoolean(7));
				nv.setChucVu(rs.getString(8));
				nv.setTrangThai(rs.getBoolean(9));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return nv;
	}
	
	public boolean themNhanVien(NhanVien nv)  {
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "insert into NhanVien values (?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, nv.getId());
			ps.setString(2, nv.getTen());
			ps.setString(3, nv.getSoDienThoai());
			ps.setString(4, nv.getDiaChi());
			ps.setString(5, nv.getEmail());
			ps.setDate(6, nv.getNgaySinh());
			ps.setBoolean(7, nv.isGioiTinh());
			ps.setString(8, nv.getChucVu());
			ps.setBoolean(9, nv.isTrangThai());
			
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	public ArrayList<NhanVien> getAllDanhSachNV() {
		ArrayList<NhanVien> listNV = new ArrayList<>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select * from NhanVien");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				NhanVien nv = new NhanVien();
				nv.setId(rs.getString(1));
				nv.setTen(rs.getString(2));
				nv.setSoDienThoai(rs.getString(3));
				nv.setDiaChi(rs.getString(4));
				nv.setEmail(rs.getString(5));
				nv.setNgaySinh(rs.getDate(6));
				nv.setGioiTinh(rs.getBoolean(7));
				nv.setChucVu(rs.getString(8));
				nv.setTrangThai(rs.getBoolean(9));
				
				listNV.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listNV;
	}
	public void updateKhachHang(NhanVien nv) {
		ConnectDB.getinstance();
		PreparedStatement pst = null;
		Connection con = ConnectDB.getConnection();
		String sql = "update NhanVien set tenNhanVien = ?, soDienThoai = ?, diaChi = ?, email = ? ,ngaySinh = ?, gioiTinh = ? ,chucVu=?,trangThai=? where idNhanVien = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, nv.getTen());
			pst.setString(2, nv.getSoDienThoai());
			pst.setString(3, nv.getDiaChi());
			pst.setString(4, nv.getEmail());
			pst.setDate(5, nv.getNgaySinh());
			pst.setBoolean(6, nv.isGioiTinh());
			pst.setString(7,nv.getChucVu());
			pst.setBoolean(6, nv.isTrangThai());
			pst.setString(7, nv.getId());
			pst.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pst);
		}
	}
	public void DeleteNV(String maXoa) {
		ConnectDB.getinstance();
		PreparedStatement pst = null;
		Connection con = ConnectDB.getConnection();
		String sql = "delete from NhanVien where idNhanVien = ? ";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, maXoa);
			pst.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pst);
		}
	}
	public void updateNhanVien(NhanVien nv) {
		ConnectDB.getinstance();
		PreparedStatement pst = null;
		Connection con = ConnectDB.getConnection();
		String sql = "update NhanVien set tenNhanVien = ?, soDienThoai = ?, diaChi = ?, email = ?, ngaySinh = ?, gioiTinh = ? ,chucVu=?,trangThai=? where idNhanVien = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, nv.getTen());
			pst.setString(2, nv.getSoDienThoai());
			pst.setString(3, nv.getDiaChi());
			pst.setString(4, nv.getEmail());
			pst.setDate(5, nv.getNgaySinh());
			pst.setBoolean(6, nv.isGioiTinh());
			pst.setString(7,nv.getChucVu());
			pst.setBoolean(8, nv.isTrangThai());
			pst.setString(9, nv.getId());
			pst.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pst);
		}
	}
	public ArrayList<NhanVien> getMa(String maTim) {
		ArrayList<NhanVien> lstNV = new ArrayList<NhanVien>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sqlMa = "select * from NhanVien where idNhanVien = '" + maTim + "'";

		try {
			Statement stm = con.createStatement();
			ResultSet rsMa = stm.executeQuery(sqlMa);
			while (rsMa.next()) {
				NhanVien nv = new NhanVien();
				nv.setId(rsMa.getString(1));
				nv.setTen(rsMa.getString(2));
				nv.setSoDienThoai(rsMa.getString(3));
				nv.setDiaChi(rsMa.getString(4));
				nv.setEmail(rsMa.getString(5));
				nv.setNgaySinh(rsMa.getDate(6));
				nv.setGioiTinh(rsMa.getBoolean(7));
				nv.setChucVu(rsMa.getString(8));
				nv.setTrangThai(rsMa.getBoolean(9));
				lstNV.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstNV;
	}
	public ArrayList<NhanVien> getTen(String tenTim) {
		ArrayList<NhanVien> lstNV = new ArrayList<NhanVien>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sqlMa = "select * from NhanVien where tenNhanVien = '" + tenTim + "'";

		try {
			Statement stm = con.createStatement();
			ResultSet rsMa = stm.executeQuery(sqlMa);
			while (rsMa.next()) {
				NhanVien nv = new NhanVien();
				nv.setId(rsMa.getString(1));
				nv.setTen(rsMa.getString(2));
				nv.setSoDienThoai(rsMa.getString(3));
				nv.setDiaChi(rsMa.getString(4));
				nv.setEmail(rsMa.getString(5));
				nv.setNgaySinh(rsMa.getDate(6));
				nv.setGioiTinh(rsMa.getBoolean(7));
				nv.setChucVu(rsMa.getString(8));
				nv.setTrangThai(rsMa.getBoolean(9));
				lstNV.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstNV;
	}
}
