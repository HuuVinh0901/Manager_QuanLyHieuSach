package models;

import java.util.Objects;

public class LoaiSanPham {
	private String idLoaiSanPham;
	private String tenLoaiSanPham;
	public LoaiSanPham() {
		super();
		// TODO Auto-generated constructor stub
	}public LoaiSanPham(String idLoaiSanPham) {
		this.idLoaiSanPham=idLoaiSanPham;
	}
	public LoaiSanPham(String idLoaiSanPham, String tenLoaiSanPham) {
		super();
		this.idLoaiSanPham = idLoaiSanPham;
		this.tenLoaiSanPham = tenLoaiSanPham;
	}
	public String getIdLoaiSanPham() {
		return idLoaiSanPham;
	}
	public void setIdLoaiSanPham(String idLoaiSanPham) {
		this.idLoaiSanPham = idLoaiSanPham;
	}
	public String getTenLoaiSanPham() {
		return tenLoaiSanPham;
	}
	public void setTenLoaiSanPham(String tenLoaiSanPham) {
		this.tenLoaiSanPham = tenLoaiSanPham;
	}
	@Override
	public String toString() {
		return "LoaiSanPham [idLoaiSanPham=" + idLoaiSanPham + ", tenLoaiSanPham=" + tenLoaiSanPham + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(idLoaiSanPham);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoaiSanPham other = (LoaiSanPham) obj;
		return Objects.equals(idLoaiSanPham, other.idLoaiSanPham);
	}
	
	
}
