package models;

import java.sql.Date;

import utils.TrangThaiSPEnum;

public class SachCon extends SanPhamCha {
	private TacGia tacGia;
	private TheLoai theLoai;
	private Date namXuatBan;
	private String ISBN;
	private int soTrang;
	public SachCon() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public SachCon(String idSanPham, String tenSanPham, TacGia tacGia, TheLoai theLoai, Date namXuatBan, String iSBN,
			int soTrang, LoaiSanPham idLoaiSanPham, NhaCungCap idNhaCungCap, double kichThuoc, String mauSac,
			TrangThaiSPEnum trangThai, int soLuong, double giaNhap,double giaKM) {
		super(idSanPham, tenSanPham, idLoaiSanPham, idNhaCungCap, kichThuoc, mauSac, trangThai, soLuong, giaNhap,giaKM);
		this.tacGia = tacGia;
		this.theLoai = theLoai;
		this.namXuatBan = namXuatBan;
		this.ISBN = iSBN;
		this.soTrang = soTrang;
	}

	public SachCon(String idSanPham) {
		super(idSanPham);
		// TODO Auto-generated constructor stub
	}

	
	public TacGia getTacGia() {
		return tacGia;
	}

	public void setTacGia(TacGia tacGia) {
		this.tacGia = tacGia;
	}

	public TheLoai getTheLoai() {
		return theLoai;
	}

	public void setTheLoai(TheLoai theLoai) {
		this.theLoai = theLoai;
	}

	public Date getNamXuatBan() {
		return namXuatBan;
	}

	public void setNamXuatBan(Date namXuatBan) {
		this.namXuatBan = namXuatBan;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public int getSoTrang() {
		return soTrang;
	}

	public void setSoTrang(int soTrang) {
		this.soTrang = soTrang;
	}

	@Override
	public String toString() {
		return "SachCon [idSanPham=" + idSanPham + ", tenSanPham=" + tenSanPham + ", tacGia=" + tacGia + ", theLoai="
				+ theLoai + ", namXuatBan=" + namXuatBan + ", ISBN=" + ISBN + ", soTrang=" + soTrang
				+ ", idLoaiSanPham=" + idLoaiSanPham + ", idNhaCungCap=" + idNhaCungCap + ", kichThuoc=" + kichThuoc
				+ ", mauSac=" + mauSac + ", trangThai=" + trangThai + ", soLuong=" + soLuong + ", giaNhap=" + giaNhap
				+ ", thue()=" + thue() + ", giaBan()=" + giaBan() + "]";
	}

	@Override
	public double thue() {
		return super.giaNhap * 0.05;
	}

	@Override
	public double giaBan() {
		return super.giaNhap + (super.giaNhap * 0.55) + thue();
	}

	
}
