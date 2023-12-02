package models;

import utils.TrangThaiSPEnum;

public class SanPhamCon extends SanPhamCha {

	public SanPhamCon() {
		super();

	}


	public SanPhamCon(String idSanPham, String tenSanPham, LoaiSanPham idLoaiSanPham, NhaCungCap idNhaCungCap,
			double kichThuoc, String mauSac, TrangThaiSPEnum trangThai, int soLuong, double giaNhap, double giaKM) {
		super(idSanPham, tenSanPham, idLoaiSanPham, idNhaCungCap, kichThuoc, mauSac, trangThai, soLuong, giaNhap, giaKM);
		// TODO Auto-generated constructor stub
	}



	public SanPhamCon(String idSanPham) {
		super(idSanPham);
		// TODO Auto-generated constructor stub
	}



	@Override
	public String toString() {
		return "SanPhamCon [idSanPham=" + idSanPham + ", tenSanPham=" + tenSanPham + ", idLoaiSanPham=" + idLoaiSanPham
				+ ", idNhaCungCap=" + idNhaCungCap + ", kichThuoc=" + kichThuoc + ", mauSac=" + mauSac + ", trangThai="
				+ trangThai + ", soLuong=" + soLuong + ", giaNhap=" + giaNhap + ", thue()=" + thue() + ", giaBan()="
				+ giaBan() + ", ]";
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
