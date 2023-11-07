package models;

import java.util.Objects;

public class TheLoai {
	private String idTheLoai;
	private String tenTheLoai;
	private int soLuongSach;
	private String moTa;
	public TheLoai() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public TheLoai(String idTheLoai) {
		this.idTheLoai=idTheLoai;
	}

	public TheLoai(String idTheLoai, String tenTheLoai, int soLuongSach, String moTa) {
		super();
		this.idTheLoai = idTheLoai;
		this.tenTheLoai = tenTheLoai;
		this.soLuongSach = soLuongSach;
		this.moTa = moTa;
	}

	public String getIdTheLoai() {
		return idTheLoai;
	}

	public void setIdTheLoai(String idTheLoai) {
		this.idTheLoai = idTheLoai;
	}

	public String getTenTheLoai() {
		return tenTheLoai;
	}

	public void setTenTheLoai(String tenTheLoai) {
		this.tenTheLoai = tenTheLoai;
	}

	public int getSoLuongSach() {
		return soLuongSach;
	}

	public void setSoLuongSach(int soLuongSach) {
		this.soLuongSach = soLuongSach;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	@Override
	public String toString() {
		return "TheLoai [idTheLoai=" + idTheLoai + ", tenTheLoai=" + tenTheLoai + ", soLuongSach=" + soLuongSach
				+ ", moTa=" + moTa + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(idTheLoai);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TheLoai other = (TheLoai) obj;
		return Objects.equals(idTheLoai, other.idTheLoai);
	}
	
	
	
	
}
