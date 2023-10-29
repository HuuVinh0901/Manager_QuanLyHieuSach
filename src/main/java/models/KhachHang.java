package models;

import java.sql.Date;

public class KhachHang {
	private String idKhachHang;
	private String tenKhachHang;
	private String sdt;
	private String email;
	private String diaChi;
	private boolean gioiTinh;
	private Date ngaySinh;
	public KhachHang(String idKhachHang, String tenKhachHang, String sdt, String email, String diaChi, boolean gioiTinh,
			Date ngaySinh) {
		super();
		this.idKhachHang = idKhachHang;
		this.tenKhachHang = tenKhachHang;
		this.sdt = sdt;
		this.email = email;
		this.diaChi = diaChi;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
	}
	public KhachHang(String idKhachHang) {
		this.idKhachHang=idKhachHang;
	}
	
	public KhachHang() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getIDKhachHang() {
		return idKhachHang;
	}
	public void setIDKhachHang(String iDKhachHang) {
		idKhachHang = iDKhachHang;
	}
	public String getTenKhachHang() {
		return tenKhachHang;
	}
	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public boolean isGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public Date getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	@Override
	public String toString() {
		return "KhachHang [idKhachHang=" + idKhachHang + ", tenKhachHang=" + tenKhachHang + ", sdt=" + sdt + ", email="
				+ email + ", diaChi=" + diaChi + ", gioiTinh=" + gioiTinh + ", ngaySinh=" + ngaySinh + "]";
	}
	
}