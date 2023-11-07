package models;



import java.sql.Date;
import java.util.Objects;

public class TacGia {
	private String idTacGia;
	private String tenTacGia;
	private Date ngaySinh;
	private int soLuongTacPham;
	public TacGia() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public TacGia(String idTacGia) {
		this.idTacGia = idTacGia;
	}

	public TacGia(String idTacGia, String tenTacGia, Date ngaySinh, int soLuongTacPham) {
		super();
		this.idTacGia = idTacGia;
		this.tenTacGia = tenTacGia;
		this.ngaySinh = ngaySinh;
		this.soLuongTacPham = soLuongTacPham;
	}

	public String getIdTacGia() {
		return idTacGia;
	}

	public void setIdTacGia(String idTacGia) {
		this.idTacGia = idTacGia;
	}

	public String getTenTacGia() {
		return tenTacGia;
	}

	public void setTenTacGia(String tenTacGia) {
		this.tenTacGia = tenTacGia;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public int getSoLuongTacPham() {
		return soLuongTacPham;
	}

	public void setSoLuongTacPham(int soLuongTacPham) {
		this.soLuongTacPham = soLuongTacPham;
	}

	@Override
	public String toString() {
		return "TacGia [idTacGia=" + idTacGia + ", tenTacGia=" + tenTacGia + ", ngaySinh=" + ngaySinh
				+ ", soLuongTacPham=" + soLuongTacPham + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(idTacGia);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TacGia other = (TacGia) obj;
		return Objects.equals(idTacGia, other.idTacGia);
	}

	
}
