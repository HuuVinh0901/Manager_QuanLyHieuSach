
package models;

import java.sql.Date;

public class NhanVien extends NhanLuc{

	public NhanVien() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NhanVien(String id, String ten, String soDienThoai, String diaChi, String email, Date ngaySinh,
			boolean gioiTinh, String chucVu, boolean trangThai) {
		super(id, ten, soDienThoai, diaChi, email, ngaySinh, gioiTinh, chucVu, trangThai);
		// TODO Auto-generated constructor stub
	}

	public NhanVien(String id) {
		this.id=id;
	}

	
	

	
	
}

