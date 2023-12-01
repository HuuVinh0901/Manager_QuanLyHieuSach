package models;

import java.sql.Date;

public class HoaDon {
	private String idDonHang;
	private Date ngayLap;
	private KhachHang khachHang;
	private NhanVien nhanVien;
	private double tienKhachDua;
	private double tongTien;
	private double tongLoiNhuan;
	
	public HoaDon() {
		super();
	}
	
	public HoaDon(String id) {
		this.idDonHang = id;
	}
	
	public HoaDon(String idDonHang, Date ngayLap, KhachHang khachHang, NhanVien nhanVien, double tienKhachDua,
			double tongTien, double tongLoiNhuan) {
		super();
		this.idDonHang = idDonHang;
		this.ngayLap = ngayLap;
		this.khachHang = khachHang;
		this.nhanVien = nhanVien;
		this.tienKhachDua = tienKhachDua;
		this.tongTien = tongTien;
		this.tongLoiNhuan = tongLoiNhuan;
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

	public double getTienKhachDua() {
		return tienKhachDua;
	}

	public void setTienKhachDua(double tienKhachDua) {
		this.tienKhachDua = tienKhachDua;
	}

	public double getTongTien() {
		return tongTien;
	}

	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}

	public double getTongLoiNhuan() {
		return tongLoiNhuan;
	}

	public void setTongLoiNhuan(double tongLoiNhuan) {
		this.tongLoiNhuan = tongLoiNhuan;
	}

	@Override
	public String toString() {
		return "HoaDon [idDonHang=" + idDonHang + ", ngayLap=" + ngayLap + ", khachHang=" + khachHang + ", nhanVien="
				+ nhanVien + ", tienKhachDua=" + tienKhachDua + ", tongTien=" + tongTien + ", tongLoiNhuan="
				+ tongLoiNhuan + "]";
	}
	
	
	
	
}
