package models;

import models.HoaDonCho;
import models.SanPhamCha;

public class ChiTietHoaDonCho {
	private HoaDonCho hoaDonCho;
	private String idHoaDon;
	private SanPhamCha sanPham;
	private int soLuong;
	private double giaCuoi;
	private double thanhTien;
	
	public ChiTietHoaDonCho() {
		super();
	}
	
	public ChiTietHoaDonCho(HoaDonCho hoaDonCho, String idHoaDon, SanPhamCha sanPham, int soLuong, double giaCuoi, double thanhTien) {
		super();
		this.hoaDonCho = hoaDonCho;
		this.idHoaDon = idHoaDon;
		this.sanPham = sanPham;
		this.soLuong = soLuong;
		this.giaCuoi = giaCuoi;
		this.thanhTien = thanhTien;
	}

	public HoaDonCho getHoaDonCho() {
		return hoaDonCho;
	}

	public void setHoaDonCho(HoaDonCho hoaDonCho) {
		this.hoaDonCho = hoaDonCho;
	}

	public SanPhamCha getSanPham() {
		return sanPham;
	}

	public void setSanPham(SanPhamCha sanPham) {
		this.sanPham = sanPham;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public double getGiaCuoi() {
		return giaCuoi;
	}

	public void setGiaCuoi(double giaCuoi) {
		this.giaCuoi = giaCuoi;
	}

	public String getIdHoaDon() {
		return idHoaDon;
	}

	public void setIdHoaDon(String idHoaDon) {
		this.idHoaDon = idHoaDon;
	}
	
	public double getThanhTien() {
		return thanhTien;
	}

	public void setThanhTien(double thanhTien) {
		this.thanhTien = thanhTien;
	}

	@Override
	public String toString() {
		return "ChiTietHoaDonCho [hoaDonCho=" + hoaDonCho + ", idHoaDon=" + idHoaDon + ", sanPham=" + sanPham
				+ ", soLuong=" + soLuong + ", giaCuoi=" + giaCuoi + ", thanhTien=" + thanhTien + "]";
	}

	

	
	
}
