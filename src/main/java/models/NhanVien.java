
package models;

import java.sql.Date;

public class NhanVien {
	private String idNhanVien;
	private String tenNhanVien;
	private String soDienThoai;
	private String diaChi;
	private String email;
	private Date ngaySinh;
	private boolean gioiTinh;
	private String chucVu;
	private String trangThai;
	private float luong;
	
	public NhanVien(String idNhanVien, String tenNhanVien, String soDienThoai, String diaChi, String email,
			Date ngaySinh, boolean gioiTinh, String chucVu, String trangThai, float luong) {
		
		this.idNhanVien = idNhanVien;
		this.tenNhanVien = tenNhanVien;
		this.soDienThoai = soDienThoai;
		this.diaChi = diaChi;
		this.email = email;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.chucVu = chucVu;
		this.trangThai = trangThai;
		this.luong = luong;
	}
	
	
	public NhanVien(String idNhanVien) {
		
		this.idNhanVien = idNhanVien;
	}
	public NhanVien() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getIdNhanVien() {
		return idNhanVien;
	}
	public void setIdNhanVien(String idNhanVien) {
		this.idNhanVien = idNhanVien;
	}
	public String getTenNhanVien() {
		return tenNhanVien;
	}
	public void setTenNhanVien(String tenNhanVien) {
		this.tenNhanVien = tenNhanVien;
	}
	public String getSoDienThoai() {
		return soDienThoai;
	}
	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public boolean isGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public String getChucVu() {
		return chucVu;
	}
	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	public float getLuong() {
		return luong;
	}
	public void setLuong(float luong) {
		this.luong = luong;
	}
	
}

