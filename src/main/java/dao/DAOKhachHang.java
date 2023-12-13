package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.plaf.basic.BasicSplitPaneUI.KeyboardHomeHandler;

import connection.ConnectDB;
import models.KhachHang;
import models.NhanVien;




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
	
	public KhachHang getKhachHang(String idKH) {
		KhachHang kh = new KhachHang();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT * FROM KhachHang WHERE idKhachHang = ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, idKH);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				kh.setIdKhachHang(rs.getString(1));
				kh.setTenKhachHang(rs.getString(2));
				kh.setSdt(rs.getString(3));
				kh.setEmail(rs.getString(4));
				kh.setDiaChi(rs.getString(5));
				kh.setNgaySinh(rs.getDate(6));
				kh.setGioiTinh(rs.getBoolean(7));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return kh;
	}
	
	public String getSDTTheoMa(String maKH) {
        String sdt = null;
        String sql = "SELECT soDienThoai FROM KhachHang WHERE idKhachHang = ?";
        ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
        try {
        	PreparedStatement statement = con.prepareStatement(sql);
        	statement.setString(1, maKH);
        	ResultSet resultSet = statement.executeQuery();
        	if (resultSet.next()) {
        		sdt = resultSet.getString("soDienThoai");
        	}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sdt;
    }
	
	public ArrayList<KhachHang> getKhachHangTimKiem(String cond) {
		ArrayList<KhachHang> listKH = new ArrayList<KhachHang>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT * FROM KhachHang WHERE soDienThoai LIKE '%" + cond + "%' OR"
					+ " idKhachHang LIKE '%" + cond + "%' OR" 
					+ " tenKhachHang LIKE N'%" + cond + "%'" ;
			
			statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
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
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listKH;
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
	public void updateKhachHang(KhachHang kh) {
		ConnectDB.getinstance();
		PreparedStatement pst = null;
		Connection con = ConnectDB.getConnection();
		String sql = "update KhachHang set tenKhachHang = ?, soDienThoai = ?, email = ?, diaChi = ?, ngaySinh = ?, gioiTinh = ?  where idKhachHang = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, kh.getTenKhachHang());
			pst.setString(2, kh.getSdt());
			pst.setString(3, kh.getEmail());
			pst.setString(4, kh.getDiaChi());
			pst.setDate(5, kh.getNgaySinh());
			pst.setBoolean(6, kh.isGioiTinh());
			pst.setString(7, kh.getIdKhachHang());
			pst.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pst);
		}
	}
	public void DeleteKH(String maXoa) {
		ConnectDB.getinstance();
		PreparedStatement pst = null;
		Connection con = ConnectDB.getConnection();
		String sql = "delete from KhachHang where idKhachHang = ? ";
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
	public ArrayList<KhachHang> getMa(String maTim) {
		ArrayList<KhachHang> lstNV = new ArrayList<KhachHang>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sqlMa = "select * from KhachHang where idKhachHang = '" + maTim + "'";

		try {
			Statement stm = con.createStatement();
			ResultSet rsMa = stm.executeQuery(sqlMa);
			while (rsMa.next()) {
				KhachHang kh = new KhachHang();
				kh.setIdKhachHang(rsMa.getString(1));
				kh.setTenKhachHang(rsMa.getString(2));
				kh.setSdt(rsMa.getString(3));
				kh.setEmail(rsMa.getString(4));
				kh.setDiaChi(rsMa.getString(5));
				kh.setNgaySinh(rsMa.getDate(6));
				kh.setGioiTinh(rsMa.getBoolean(7));
				
				lstNV.add(kh);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstNV;
	}
	public ArrayList<KhachHang> getTen(String tenTim) {
		ArrayList<KhachHang> lstNV = new ArrayList<KhachHang>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sqlMa = "select * from KhachHang where tenKhachHang = '" + tenTim + "'";

		try {
			Statement stm = con.createStatement();
			ResultSet rsMa = stm.executeQuery(sqlMa);
			while (rsMa.next()) {
				KhachHang kh = new KhachHang();
				kh.setIdKhachHang(rsMa.getString(1));
				kh.setTenKhachHang(rsMa.getString(2));
				kh.setSdt(rsMa.getString(3));
				kh.setEmail(rsMa.getString(4));
				kh.setDiaChi(rsMa.getString(5));
				kh.setNgaySinh(rsMa.getDate(6));
				kh.setGioiTinh(rsMa.getBoolean(7));
				
				lstNV.add(kh);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstNV;
	}
	public ArrayList<KhachHang> getKhachHangThanThiet(int soLuongHoaDonToiThieu) {
        ArrayList<KhachHang> dsKhachHang = new ArrayList<>();

        try {
            String query = "SELECT * FROM KhachHang WHERE idKhachHang IN " +
                           "(SELECT khachHang FROM HoaDon GROUP BY khachHang HAVING COUNT(*) >= ?)";
            
            Connection conn = ConnectDB.getConnection();
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, soLuongHoaDonToiThieu);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                KhachHang khachHang = new KhachHang();
                khachHang.setIdKhachHang(resultSet.getString("idKhachHang"));
                khachHang.setTenKhachHang(resultSet.getString("tenKhachHang"));
                khachHang.setSdt(resultSet.getString("soDienThoai"));
                khachHang.setEmail(resultSet.getString("email"));
                khachHang.setDiaChi(resultSet.getString("diaChi"));
                khachHang.setNgaySinh(resultSet.getDate("ngaySinh"));
                khachHang.setGioiTinh(resultSet.getBoolean("gioiTinh"));
                dsKhachHang.add(khachHang);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dsKhachHang;
    }
	
	public int getSoLuongHoaDon(String maKH) {
        int soLuongHoaDon = 0;

        try {
            String query = "SELECT COUNT(*) FROM HoaDon WHERE khachHang = ?";
            Connection conn = ConnectDB.getConnection();
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, maKH);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                soLuongHoaDon = resultSet.getInt(1);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return soLuongHoaDon;
    }
	
	public int getSoLuongKhachHangTheoThangNam(String thang, String nam) {
        int soLuongKhachHang = 0;

        try {
            String query = "SELECT COUNT(DISTINCT khachHang) " +
                           "FROM HoaDon " +
                           "WHERE MONTH(ngayLap) = ? AND YEAR(ngayLap) = ?";
            Connection conn = ConnectDB.getConnection();
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, thang);
            statement.setString(2, nam);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                soLuongKhachHang = resultSet.getInt(1);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return soLuongKhachHang;
    }
	
	public int getSoLuongKhachHangTheoNgayThangNam(String ngay, String thang, String nam) {
	    int soLuongKhachHang = 0;
	    ConnectDB.getinstance();
	    Connection con = ConnectDB.getConnection();
	    PreparedStatement statement = null;

	    try {
	        String sql = "SELECT COUNT(DISTINCT khachHang) AS total " +
	                     "FROM HoaDon " +
	                     "WHERE DAY(ngayLap) = ? AND MONTH(ngayLap) = ? AND YEAR(ngayLap) = ? " +
	                     "GROUP BY DAY(ngayLap), MONTH(ngayLap), YEAR(ngayLap)";

	        statement = con.prepareStatement(sql);
	        statement.setString(1, ngay);
	        statement.setString(2, thang);
	        statement.setString(3, nam);

	        ResultSet rs = statement.executeQuery();
	        while (rs.next()) {
	            soLuongKhachHang = rs.getInt("total");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return soLuongKhachHang;
	}
}
