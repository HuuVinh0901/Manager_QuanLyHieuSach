package models;

import utils.TrangThaiSPEnum;

public class SanPhamCon extends SanPhamCha {
	private int soLuongBan;
	private double doanhThu;
	private double loiNhuan;
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
	}



	public double getDoanhThu() {
		return doanhThu;
	}


	public void setDoanhThu(double doanhThu) {
		this.doanhThu = doanhThu;
	}


	public double getLoiNhuan() {
		return loiNhuan;
	}


	public void setLoiNhuan(double loiNhuan) {
		this.loiNhuan = loiNhuan;
	}


	public int getSoLuongBan() {
		return soLuongBan;
	}


	public void setSoLuongBan(int soLuongBan) {
		this.soLuongBan = soLuongBan;
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

	

//	@Override
//	public String toString() {
//		return "SanPhamCon [soLuongBan=" + soLuongBan + ", idSanPham=" + idSanPham + ", tenSanPham=" + tenSanPham
//				+ ", idLoaiSanPham=" + idLoaiSanPham + ", idNhaCungCap=" + idNhaCungCap + ", kichThuoc=" + kichThuoc
//				+ ", mauSac=" + mauSac + ", trangThai=" + trangThai + ", soLuong=" + soLuong + ", giaNhap=" + giaNhap
//				+ ", giaKM=" + giaKM + "]";
//	}


	@Override
	public double giaBan() {
		return super.giaNhap+(super.giaNhap*0.55)+thue();
	}
	
}
