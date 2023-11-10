package models;

public class GioHang {
	private String maHoaDon;;
	private String maSanPham;
	private String tenSanPham;
	private double giaBan;
	private int soLuong;
	private double thanhTien;
	
	public GioHang() {
		super();
	}

	public GioHang(String maHoaDon, String maSanPham, String tenSanPham, double giaBan, int soLuong, double thanhTien) {
		super();
		this.maHoaDon = maHoaDon;
		this.maSanPham = maSanPham;
		this.tenSanPham = tenSanPham;
		this.giaBan = giaBan;
		this.soLuong = soLuong;
		this.thanhTien = thanhTien;
	}

	public String getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public String getMaSanPham() {
		return maSanPham;
	}

	public void setMaSanPham(String maSanPham) {
		this.maSanPham = maSanPham;
	}

	public String getTenSanPham() {
		return tenSanPham;
	}

	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}

	public double getGiaBan() {
		return giaBan;
	}

	public void setGiaBan(double giaBan) {
		this.giaBan = giaBan;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public double getThanhTien() {
		return thanhTien;
	}

	public void setThanhTien(double thanhTien) {
		this.thanhTien = thanhTien;
	}

	@Override
	public String toString() {
		return "GioHang [maHoaDon=" + maHoaDon + ", maSanPham=" + maSanPham + ", tenSanPham=" + tenSanPham + ", giaBan="
				+ giaBan + ", soLuong=" + soLuong + ", thanhTien=" + thanhTien + "]";
	}
	
	
	
}
