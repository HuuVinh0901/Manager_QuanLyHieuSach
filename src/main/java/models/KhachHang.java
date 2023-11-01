package models;

import java.sql.Date;
import java.util.Objects;

import utils.GioiTinhEnum;

public class KhachHang {
	private String idKhachHang;
	private String tenKhachHang;
	private String sdt;
	private String email;
	private String diaChi;
	private boolean gioiTinh;
	private Date ngaySinh;

	

	public KhachHang(String idKhachHang, String tenKhachHang, String sdt, String email, String diaChi,
			Date ngaySinh, boolean gioiTinh) {
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
		this.idKhachHang = idKhachHang;
	}
	
	public KhachHang() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public String getIdKhachHang() {
		return idKhachHang;
	}



	public void setIdKhachHang(String idKhachHang) {
		this.idKhachHang = idKhachHang;
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

	@Override
	public int hashCode() {
		return Objects.hash(idKhachHang);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KhachHang other = (KhachHang) obj;
		return Objects.equals(idKhachHang, other.idKhachHang);
	}

	
	
}