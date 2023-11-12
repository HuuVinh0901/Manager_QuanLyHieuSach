package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connection.ConnectDB;
import models.KhachHang;
import models.KhuyenMai;
import models.LoaiSanPham;
import models.NhaCungCap;
import models.SachKhuyenMai;
import models.SanPhamCha;
import models.SanPhamCon;
import models.SanPhamKhuyenMai;
import utils.LoaiKMEnum;
import utils.TrangThaiSPEnum;

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
	public ArrayList<SanPhamKhuyenMai> getAllDanhSachSPKM() {
		ArrayList<SanPhamKhuyenMai> listSPKM=new ArrayList<>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select * from ApDungKhuyenMai");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				SanPhamKhuyenMai spkm=new SanPhamKhuyenMai();
				spkm.setIdSanPham(rs.getString(1));
				spkm.setIdKM(rs.getString(2));
				spkm.setTenSP(rs.getString(3));
				spkm.setGiaBan(rs.getDouble(4));
				spkm.setGiaKM(rs.getDouble(5));
				
				listSPKM.add(spkm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listSPKM;
	}
	public ArrayList<SachKhuyenMai> getAllDanhSachSachKM() {
		ArrayList<SachKhuyenMai> listSKM=new ArrayList<>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select * from ApDungKhuyenMaiSach");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				SachKhuyenMai skm=new SachKhuyenMai();
				skm.setIdSanPham(rs.getString(1));
				skm.setIdKM(rs.getString(2));
				skm.setTenSP(rs.getString(3));
				skm.setGiaBan(rs.getDouble(4));
				skm.setGiaKM(rs.getDouble(5));
				
				listSKM.add(skm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listSKM;
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
	public void updateGiaKM(String idSP, double giaKM) {
        try {
            // Cập nhật giá bán trong cơ sở dữ liệu
        	ConnectDB.getinstance();
    		
    		Connection con = ConnectDB.getConnection();
    		
            String updateQuery = "UPDATE SanPham SET giaKhuyenMai = ? WHERE idSanPham = ?";
            try (PreparedStatement preparedStatement = con.prepareStatement(updateQuery)) {
                preparedStatement.setDouble(1, giaKM);
                preparedStatement.setString(2, idSP);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	public void updateGiaKMSach(String idSP, double giaKM) {
        try {
            // Cập nhật giá bán trong cơ sở dữ liệu
        	ConnectDB.getinstance();
    		
    		Connection con = ConnectDB.getConnection();
    		
            String updateQuery = "UPDATE Sach SET giaKhuyenMai = ? WHERE idSanPham = ?";
            try (PreparedStatement preparedStatement = con.prepareStatement(updateQuery)) {
                preparedStatement.setDouble(1, giaKM);
                preparedStatement.setString(2, idSP);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	public boolean ThemSPKM(String idSP, String idKM,String tenSP,double giaBan,double giaKM) throws SQLException {
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "INSERT INTO ApDungKhuyenMai (idSP, idKM,tenSP, giaBan, giaKM) VALUES (?,?, ?,?,?)";
		try {
			PreparedStatement ps= con.prepareStatement(sql);
			
			ps.setString(1, idSP);
			ps.setString(2, idKM );
			ps.setString(3, tenSP );
			ps.setDouble(4, giaBan );
			ps.setDouble(5, giaKM );
            return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		con.close();
		return false;
		
	}
	public boolean ThemSachKM(String idSach, String idKM,String tenSach,double giaBan,double giaKM) throws SQLException {
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "INSERT INTO ApDungKhuyenMaiSach (idS, idKM,tenSP, giaBan, giaKM) VALUES (?,?, ?,?,?)";
		try {
			PreparedStatement ps= con.prepareStatement(sql);
			
			ps.setString(1, idSach);
			ps.setString(2, idKM );
			ps.setString(3, tenSach );
			ps.setDouble(4, giaBan );
			ps.setDouble(5, giaKM );
            return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		con.close();
		return false;
		
	}
	public void XoaSPKM(String idSP) {
	    try {
	        String deleteQuery = "DELETE FROM ApDungKhuyenMai WHERE idSP = ?";
	        ConnectDB.getinstance();
    		
    		Connection con = ConnectDB.getConnection();
	        try (PreparedStatement preparedStatement = con.prepareStatement(deleteQuery)) {
	            preparedStatement.setString(1, idSP);
	            preparedStatement.executeUpdate();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public void XoaSKM(String idSP) {
	    try {
	        String deleteQuery = "DELETE FROM ApDungKhuyenMaiSach WHERE idS = ?";
	        ConnectDB.getinstance();
    		
    		Connection con = ConnectDB.getConnection();
	        try (PreparedStatement preparedStatement = con.prepareStatement(deleteQuery)) {
	            preparedStatement.setString(1, idSP);
	            preparedStatement.executeUpdate();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


	
}
