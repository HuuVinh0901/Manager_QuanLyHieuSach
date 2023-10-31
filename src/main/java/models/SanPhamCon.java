package models;

import utils.TrangThaiSPEnum;

public class SanPhamCon extends SanPhamCha {

	public SanPhamCon() {
		super();

	}

	public SanPhamCon(String hinhAnhSanPham, String idSanPham, String tenSanPham, LoaiSanPham idLoaiSanPham,
			NhaCungCap idNhaCungCap, double kichThuoc, String mauSac, TrangThaiSPEnum trangThai, int soLuong,
			double giaNhap) {
		super(hinhAnhSanPham, idSanPham, tenSanPham, idLoaiSanPham, idNhaCungCap, kichThuoc, mauSac, trangThai, soLuong,
				giaNhap);
		// TODO Auto-generated constructor stub
	}

	public SanPhamCon(String idSanPham) {
		super(idSanPham);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "SanPhamCon [hinhAnhSanPham=" + hinhAnhSanPham + ", idSanPham=" + idSanPham + ", tenSanPham="
				+ tenSanPham + ", idLoaiSanPham=" + idLoaiSanPham + ", idNhaCungCap=" + idNhaCungCap + ", kichThuoc="
				+ kichThuoc + ", mauSac=" + mauSac + ", trangThai=" + trangThai + ", thue()=" + thue() + ", giaNhap="
				+ giaNhap + ", soLuong=" + soLuong + ", giaBan()=" + giaBan() + "]";
	}

	@Override
	public double thue() {
		return super.giaNhap* 0.05;
	}

	@Override
	public double giaBan() {
		return super.giaNhap+(super.giaNhap*0.55)+thue();
	}
}
