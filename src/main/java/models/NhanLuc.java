package models;

import java.sql.Date;


	public class NhanLuc {
		private String id;
		private String ten;
		private String soDienThoai;
		private String diaChi;
		private String email;
		private Date ngaySinh;
		private boolean gioiTinh;
		private String chucVu;
		private boolean trangThai;
		
		
		public NhanLuc(String id, String ten, String soDienThoai, String diaChi, String email,
				 Date ngaySinh,boolean gioiTinh, String chucVu, boolean trangThai) {
			
			this.id = id;
			this.ten = ten;
			this.soDienThoai = soDienThoai;
			this.diaChi = diaChi;
			this.email = email;
			this.ngaySinh = ngaySinh;
			this.gioiTinh = gioiTinh;
			this.chucVu = chucVu;
			this.trangThai = trangThai;
			
		}
		
		
		public NhanLuc(String id) {
			
			this.id = id;
		}
		public NhanLuc() {
			super();
			// TODO Auto-generated constructor stub
		}


		public String getIdNhanVien() {
			return id;
		}
		public void setIdNhanVien(String idNhanVien) {
			this.id = idNhanVien;
		}
		public String getTenNhanVien() {
			return ten;
		}
		public void setTenNhanVien(String tenNhanVien) {
			this.ten = tenNhanVien;
		}
		public String getSoDienThoai() {
			return soDienThoai;
		}
		public void setSoDienThoai(String soDienThoai) {
			this.soDienThoai = soDienThoai;
		}
		public String getDiaChi() {
			return diaChi;
		}
		public void setDiaChi(String diaChi) {
			this.diaChi = diaChi;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public Date getNgaySinh() {
			return ngaySinh;
		}
		public void setNgaySinh(Date ngaySinh) {
			this.ngaySinh = ngaySinh;
		}
		public boolean isGioiTinh() {
			return gioiTinh;
		}
		public void setGioiTinh(boolean gioiTinh) {
			this.gioiTinh = gioiTinh;
		}
		public String getChucVu() {
			return chucVu;
		}
		public void setChucVu(String chucVu) {
			this.chucVu = chucVu;
		}
		
		public boolean isTrangThai() {
			return trangThai;
		}


		public void setTrangThai(boolean trangThai) {
			this.trangThai = trangThai;
		}
}

