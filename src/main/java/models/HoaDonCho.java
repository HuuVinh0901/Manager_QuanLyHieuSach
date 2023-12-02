package models;

import java.sql.Date;

public class HoaDonCho {
	private String idHoaDonCho;
	private String idDonHang;
	private KhachHang khachHang;
	private Date ngayLap;
	
	public HoaDonCho() {
		super();
		
	}
	
	public HoaDonCho(String idHoaDonCho) {
		super();
		this.idHoaDonCho = idHoaDonCho;
	}
	
	public HoaDonCho(String idHoaDonCho, String idDonHang, KhachHang khachHang, Date ngayLap) {
		super();
		this.idHoaDonCho = idHoaDonCho;
		this.idDonHang = idDonHang;
		this.khachHang = khachHang;
		this.ngayLap = ngayLap;
	}

	public String getIdHoaDonCho() {
		return idHoaDonCho;
	}

	public void setIdHoaDonCho(String idHoaDonCho) {
		this.idHoaDonCho = idHoaDonCho;
	}

	public String getIdDonHang() {
		return idDonHang;
	}

	public void setIdDonHang(String idDonHang) {
		this.idDonHang = idDonHang;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public Date getNgayLap() {
		return ngayLap;
	}

	public void setNgayLap(Date ngayLap) {
		this.ngayLap = ngayLap;
	}

	@Override
	public String toString() {
		return "HoaDonCho [idHoaDonCho=" + idHoaDonCho + ", idDonHang=" + idDonHang + ", khachHang=" + khachHang
				+ ", ngayLap=" + ngayLap + "]";
	}
	
	
}
