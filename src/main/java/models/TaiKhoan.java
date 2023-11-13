package models;

import java.sql.Date;

public class TaiKhoan {
	private String idTaiKhoan;
	private String matKhau;
	private Date ngayLap;
	public TaiKhoan() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TaiKhoan(String id) {
		super();
		// TODO Auto-generated constructor stub
		this.idTaiKhoan=id;
	}
	public TaiKhoan(String idTaiKhoan, String matKhau, Date ngayLap) {
		super();
		this.idTaiKhoan = idTaiKhoan;
		this.matKhau = matKhau;
		this.ngayLap = ngayLap;
	}
	public String getIdTaiKhoan() {
		return idTaiKhoan;
	}
	public void setIdTaiKhoan(String idTaiKhoan) {
		this.idTaiKhoan = idTaiKhoan;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	public Date getNgayLap() {
		return ngayLap;
	}
	public void setNgayLap(Date ngayLap) {
		this.ngayLap = ngayLap;
	}
	
}
