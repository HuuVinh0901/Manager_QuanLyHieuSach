package models;

public class SachKhuyenMai {
	private String idSanPham;
	private String idKM;
	private String tenSP;
	private double giaBan;
	private double giaKM;
	public SachKhuyenMai() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SachKhuyenMai(String idSanPham, String idKM, String tenSP, double giaBan, double giaKM) {
		super();
		this.idSanPham = idSanPham;
		this.idKM = idKM;
		this.tenSP = tenSP;
		this.giaBan = giaBan;
		this.giaKM = giaKM;
	}
	public String getIdSanPham() {
		return idSanPham;
	}
	public void setIdSanPham(String idSanPham) {
		this.idSanPham = idSanPham;
	}
	public String getIdKM() {
		return idKM;
	}
	public void setIdKM(String idKM) {
		this.idKM = idKM;
	}
	public String getTenSP() {
		return tenSP;
	}
	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}
	public double getGiaBan() {
		return giaBan;
	}
	public void setGiaBan(double giaBan) {
		this.giaBan = giaBan;
	}
	public double getGiaKM() {
		return giaKM;
	}
	public void setGiaKM(double giaKM) {
		this.giaKM = giaKM;
	}
	@Override
	public String toString() {
		return "SanPhamKhuyenMai [idSanPham=" + idSanPham + ", idKM=" + idKM + ", tenSP=" + tenSP + ", giaBan=" + giaBan
				+ ", giaKM=" + giaKM + "]";
	}
	
	
	
	
}
