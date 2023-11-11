package models;

import java.sql.Date;

import utils.LoaiKMEnum;

public class KhuyenMai {
	private String id;
	private String ten;
	private LoaiKMEnum loai;
	private Date ngayBatDau;
	private Boolean trangThai;
	public KhuyenMai() {
		super();
		
	}
	public KhuyenMai(String id) {
		this.id=id;
	}
	public KhuyenMai(String id, String ten,  Date ngayBatDau, Boolean trangThai,LoaiKMEnum loai) {
		super();
		this.id = id;
		this.ten = ten;
		this.loai = loai;
		this.ngayBatDau = ngayBatDau;
		this.trangThai = trangThai;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public LoaiKMEnum getLoai() {
		return loai;
	}
	public void setLoai(LoaiKMEnum loai) {
		this.loai = loai;
	}
	public Date getNgayBatDau() {
		return ngayBatDau;
	}
	public void setNgayBatDau(Date ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}
	public Boolean getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(Boolean trangThai) {
		this.trangThai = trangThai;
	}
	
}
