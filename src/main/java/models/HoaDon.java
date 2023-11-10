package models;

import java.sql.Date;

public class HoaDon {
	private String idDonHang;
	private Date ngayLap;
	private KhachHang khachHang;
	private NhanVien nhanVien;
	private Double tienKhachDua;
	private Double tongTien;
	
	public HoaDon() {
		super();
	}

	public HoaDon(String idDonHang, Date ngayLap, KhachHang khachHang, NhanVien nhanVien, Double tienKhachDua,
			Double tongTien) {
		super();
		this.idDonHang = idDonHang;
		this.ngayLap = ngayLap;
		this.khachHang = khachHang;
		this.nhanVien = nhanVien;
		this.tienKhachDua = tienKhachDua;
		this.tongTien = tongTien;
	}

	public String getIdDonHang() {
		return idDonHang;
	}

	public void setIdDonHang(String idDonHang) {
		this.idDonHang = idDonHang;
	}

	public Date getNgayLap() {
		return ngayLap;
	}

	public void setNgayLap(Date ngayLap) {
		this.ngayLap = ngayLap;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public Double getTienKhachDua() {
		return tienKhachDua;
	}

	public void setTienKhachDua(Double tienKhachDua) {
		this.tienKhachDua = tienKhachDua;
	}

	public Double getTongTien() {
		return tongTien;
	}

	public void setTongTien(Double tongTien) {
		this.tongTien = tongTien;
	}

	@Override
	public String toString() {
		return "HoaDon [idDonHang=" + idDonHang + ", ngayLap=" + ngayLap + ", khachHang=" + khachHang + ", nhanVien="
				+ nhanVien + ", tienKhachDua=" + tienKhachDua + ", tongTien=" + tongTien + "]";
	}
	
	
	
}
