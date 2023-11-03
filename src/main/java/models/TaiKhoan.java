package models;

import java.util.Date;

public class TaiKhoan {
	private NhanVien idTaiKhoan;
	private String matKhau;
	private Date ngayLap;
	public TaiKhoan() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TaiKhoan(NhanVien idTaiKhoan, String matKhau, Date ngayLap) {
		super();
		this.idTaiKhoan = idTaiKhoan;
		this.matKhau = matKhau;
		this.ngayLap = ngayLap;
	}
	public NhanVien getIdTaiKhoan() {
		return idTaiKhoan;
	}
	public void setIdTaiKhoan(NhanVien idTaiKhoan) {
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
	@Override
	public String toString() {
		return "TaiKhoan [idTaiKhoan=" + idTaiKhoan + ", matKhau=" + matKhau + ", ngayLap=" + ngayLap + "]";
	}
}
