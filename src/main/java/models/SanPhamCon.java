package models;

import utils.TrangThaiSPEnum;

public class SanPhamCon extends SanPhamCha{
	
	public SanPhamCon() {
		super();
		
	}

	public SanPhamCon(String hinhAnhSanPham, String idSanPham, String tenSanPham, LoaiSanPham idLoaiSanPham,
			NhaCungCap idNhaCungCap, double kichThuoc, String mauSac, TrangThaiSPEnum trangThai) {
		super(hinhAnhSanPham, idSanPham, tenSanPham, idLoaiSanPham, idNhaCungCap, kichThuoc, mauSac, trangThai);

	}

	public SanPhamCon(String idSanPham) {
		super(idSanPham);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "SanPhamCon [hinhAnhSanPham=" + hinhAnhSanPham + ", idSanPham=" + idSanPham + ", tenSanPham="
				+ tenSanPham + ", idLoaiSanPham=" + idLoaiSanPham + ", idNhaCungCap=" + idNhaCungCap + ", kichThuoc="
				+ kichThuoc + ", mauSac=" + mauSac + ", trangThai=" + trangThai + ", thue()=" + thue() + ", giaBan()="
				+ giaBan() + ", soLuong()=" + soLuong() + "]";
	}

	@Override
	public double thue() {
		return 0.05;
	}

	@Override
	public double giaBan() {
	    return 6;
	}

	@Override
	public int soLuong() {
		return 9;
	}
}
