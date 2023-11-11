package models;

import java.sql.Date;

public class QuanLy extends NhanLuc{

	public QuanLy() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuanLy(String id, String ten, String soDienThoai, String diaChi, String email, Date ngaySinh,
			boolean gioiTinh, String chucVu, boolean trangThai) {
		super(id, ten, soDienThoai, diaChi, email, ngaySinh, gioiTinh, chucVu, trangThai);
		// TODO Auto-generated constructor stub
	}

	public QuanLy(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	
}
