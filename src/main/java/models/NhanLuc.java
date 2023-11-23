package models;

import java.sql.Date;


	public class NhanLuc {
		protected String id;
		protected String ten;
		protected String soDienThoai;
		protected String diaChi;
		protected String email;
		protected Date ngaySinh;
		protected boolean gioiTinh;
		protected String chucVu;
		protected boolean trangThai;
		
		public NhanLuc() {
			super();
			// TODO Auto-generated constructor stub
		}
		public NhanLuc(String id) {
			this.id = id;
		}
		public NhanLuc(String id, String ten, String soDienThoai, String diaChi, String email, Date ngaySinh,
				boolean gioiTinh, String chucVu, boolean trangThai) {
			super();
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
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getTen() {
			return ten;
		}
		public void setTen(String ten) {
			this.ten = ten;
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
		
		
		@Override
		public String toString() {
			return "NhanLuc [id=" + id + ", ten=" + ten + ", soDienThoai=" + soDienThoai + ", diaChi=" + diaChi
					+ ", email=" + email + ", ngaySinh=" + ngaySinh + ", gioiTinh=" + gioiTinh + ", chucVu=" + chucVu
					+ ", trangThai=" + trangThai + "]";
		}
		
		
		
}

