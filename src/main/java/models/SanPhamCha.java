package models;

import java.util.Objects;

import javax.swing.JComboBox;

import utils.TrangThaiSPEnum;

public abstract class SanPhamCha {
	protected String hinhAnhSanPham;
	protected String idSanPham;
	protected String tenSanPham;
	protected LoaiSanPham idLoaiSanPham;
	protected NhaCungCap idNhaCungCap;
	protected double kichThuoc;
	protected String mauSac;
	protected TrangThaiSPEnum trangThai;
	protected int soLuong;
	protected double giaNhap;

	public SanPhamCha() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SanPhamCha(String idSanPham) {
		this.idSanPham = idSanPham;
	}

	
	

	

	public SanPhamCha(String hinhAnhSanPham, String idSanPham, String tenSanPham, LoaiSanPham idLoaiSanPham,
			NhaCungCap idNhaCungCap, double kichThuoc, String mauSac, TrangThaiSPEnum trangThai, int soLuong,
			double giaNhap) {
		super();
		this.hinhAnhSanPham = hinhAnhSanPham;
		this.idSanPham = idSanPham;
		this.tenSanPham = tenSanPham;
		this.idLoaiSanPham = idLoaiSanPham;
		this.idNhaCungCap = idNhaCungCap;
		this.kichThuoc = kichThuoc;
		this.mauSac = mauSac;
		this.trangThai = trangThai;
		this.soLuong = soLuong;
		this.giaNhap = giaNhap;
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

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public double getGiaNhap() {
		return giaNhap;
	}

	public void setGiaNhap(double giaNhap) {
		this.giaNhap = giaNhap;
	}

	@Override
	public String toString() {
		return "SanPhamCha [hinhAnhSanPham=" + hinhAnhSanPham + ", idSanPham=" + idSanPham + ", tenSanPham="
				+ tenSanPham + ", idLoaiSanPham=" + idLoaiSanPham + ", idNhaCungCap=" + idNhaCungCap + ", kichThuoc="
				+ kichThuoc + ", mauSac=" + mauSac + ", trangThai=" + trangThai + ", soLuong=" + soLuong + ", giaNhap="
				+ giaNhap + "]";
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
		SanPhamCha other = (SanPhamCha) obj;
		return Objects.equals(idSanPham, other.idSanPham);
	}
	public abstract double thue();
	public abstract double giaBan();
}
