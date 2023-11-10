package models;

public class ChiTietHoaDon {
	private int soLuong;
	private HoaDon hoaDon;
	private SanPhamCha sanPham;
	private double thanhTien;
	
	public ChiTietHoaDon() {
		super();
	}

	public ChiTietHoaDon(int soLuong, HoaDon hoaDon, SanPhamCha sanPham, double thanhTien) {
		super();
		this.soLuong = soLuong;
		this.hoaDon = hoaDon;
		this.sanPham = sanPham;
		this.thanhTien = thanhTien;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public HoaDon getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}

	public SanPhamCha getSanPham() {
		return sanPham;
	}

	public void setSanPham(SanPhamCha sanPham) {
		this.sanPham = sanPham;
	}

	public double getThanhTien() {
		return thanhTien;
	}

	public void setThanhTien(double thanhTien) {
		this.thanhTien = thanhTien;
	}

	@Override
	public String toString() {
		return "ChiTietHoaDon [soLuong=" + soLuong + ", hoaDon=" + hoaDon + ", sanPham=" + sanPham + ", thanhTien="
				+ thanhTien + "]";
	}
	
	
}
