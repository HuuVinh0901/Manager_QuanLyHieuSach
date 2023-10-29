package models;

import java.util.Objects;

import javax.swing.JComboBox;

import utils.TrangThaiSPEnum;

public class SanPham {
	private String hinhAnhSanPham;
	private String idSanPham;
	private String tenSanPham;
	private LoaiSanPham idLoaiSanPham;
	private NhaCungCap idNhaCungCap;
	private double kichThuoc;
	private String mauSac;
	private TrangThaiSPEnum trangThai;
	private double thue;
	private int soLuong;
	private double giaBan;
	
	public SanPham() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SanPham(String idSanPham) {
		this.idSanPham = idSanPham;
	}

	
	public SanPham(String hinhAnhSanPham, String idSanPham, String tenSanPham, LoaiSanPham idLoaiSanPham,
			NhaCungCap idNhaCungCap, double kichThuoc, String mauSac, TrangThaiSPEnum trangThai) {
		this.hinhAnhSanPham = hinhAnhSanPham;
		this.idSanPham = idSanPham;
		this.tenSanPham = tenSanPham;
		this.idLoaiSanPham = idLoaiSanPham;
		this.idNhaCungCap = idNhaCungCap;
		this.kichThuoc = kichThuoc;
		this.mauSac = mauSac;
		this.trangThai = trangThai;
		this.thue = thue;
		this.soLuong = soLuong;
		this.giaBan = giaBan;
		this.tinhThue();
		this.giaBan();
		this.laySoLuong();
	}


	
	public String getHinhAnhSanPham() {
		return hinhAnhSanPham;
	}



	public void setHinhAnhSanPham(String hinhAnhSanPham) {
		this.hinhAnhSanPham = hinhAnhSanPham;
	}



	public String getIdSanPham() {
		return idSanPham;
		
	}


	public void setIdSanPham(String idSanPham) {
		this.idSanPham = idSanPham;
	}


	public String getTenSanPham() {
		return tenSanPham;
	}


	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}


	public LoaiSanPham getIdLoaiSanPham() {
		return idLoaiSanPham;
	}


	public void setIdLoaiSanPham(LoaiSanPham idLoaiSanPham) {
		this.idLoaiSanPham = idLoaiSanPham;
	}


	public NhaCungCap getIdNhaCungCap() {
		return idNhaCungCap;
	}


	public void setIdNhaCungCap(NhaCungCap idNhaCungCap) {
		this.idNhaCungCap = idNhaCungCap;
	}


	public double getKichThuoc() {
		return kichThuoc;
	}


	public void setKichThuoc(double kichThuoc) {
		this.kichThuoc = kichThuoc;
	}


	public String getMauSac() {
		return mauSac;
	}


	public void setMauSac(String mauSac) {
		this.mauSac = mauSac;
	}


	public TrangThaiSPEnum getTrangThai() {
		return trangThai;
	}


	public void setTrangThai(TrangThaiSPEnum trangThai) {
		this.trangThai = trangThai;
	}


	public double getThue() {
		return this.thue;
	}


	public void setThue(double thue) {
		this.thue = this.tinhThue();
	}


	public double getGiaBan() {
		return this.giaBan;
	}


	public void setGiaBan(double giaBan) {
		this.giaBan = this.giaBan();
	}


	public int getSoLuong() {
		return this.soLuong;
	}


	public void setSoLuong(int soLuong) {
		this.soLuong = this.laySoLuong();
	}

	@Override
	public String toString() {
		return "SanPham [hinhAnhSanPham=" + hinhAnhSanPham + ", idSanPham=" + idSanPham + ", tenSanPham=" + tenSanPham
				+ ", idLoaiSanPham=" + idLoaiSanPham + ", idNhaCungCap=" + idNhaCungCap + ", kichThuoc=" + kichThuoc
				+ ", mauSac=" + mauSac + ", trangThai=" + trangThai + ", thue=" + thue + ", soLuong=" + soLuong
				+ ", giaBan=" + giaBan + "]";
	}



	@Override
	public int hashCode() {
		return Objects.hash(idSanPham);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SanPham other = (SanPham) obj;
		return Objects.equals(idSanPham, other.idSanPham);
	}
	
	private double tinhThue() {
//		gia nhap * 0.05
//		this.thue = this.soLuong *0.05;
		return this.soLuong*0.05;
	}
	
	private int laySoLuong() {
		// TODO Auto-generated method stub
		return this.soLuong;
	}
	private double giaBan() {
//		Giá nhập sản phẩm + (Giá nhập sản phẩm x 55%) + thuế
		
		
		return 0.05;
	}
	
	
}
