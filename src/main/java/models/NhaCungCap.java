package models;

import java.util.Objects;

public class NhaCungCap {
	private String idNhaCungCap;
	private String tenNhaCungCap;
	private String diaChi;
	private String soDienThoai;
	public NhaCungCap() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NhaCungCap(String idNhaCungCap) {
		this.idNhaCungCap=idNhaCungCap;
	}
	public NhaCungCap(String idNhaCungCap, String tenNhaCungCap, String diaChi, String soDienThoai) {
		super();
		this.idNhaCungCap = idNhaCungCap;
		this.tenNhaCungCap = tenNhaCungCap;
		this.diaChi = diaChi;
		this.soDienThoai = soDienThoai;
	}
	public String getIdNhaCungCap() {
		return idNhaCungCap;
	}
	public void setIdNhaCungCap(String idNhaCungCap) {
		this.idNhaCungCap = idNhaCungCap;
	}
	public String getTenNhaCungCap() {
		return tenNhaCungCap;
	}
	public void setTenNhaCungCap(String tenNhaCungCap) {
		this.tenNhaCungCap = tenNhaCungCap;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getSoDienThoai() {
		return soDienThoai;
	}
	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}
	@Override
	public String toString() {
		return "NhaCungCap [idNhaCungCap=" + idNhaCungCap + ", tenNhaCungCap=" + tenNhaCungCap + ", diaChi=" + diaChi
				+ ", soDienThoai=" + soDienThoai + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(idNhaCungCap);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhaCungCap other = (NhaCungCap) obj;
		return Objects.equals(idNhaCungCap, other.idNhaCungCap);
	}
}
