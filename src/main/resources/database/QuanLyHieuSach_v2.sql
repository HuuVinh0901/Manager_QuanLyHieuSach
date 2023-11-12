CREATE DATABASE QLHieuSach

go
-- Sử dụng cơ sở dữ liệu QLHieuSach
USE QLHieuSach
go

CREATE TABLE NhanVien (
    idNhanVien NVARCHAR(14) NOT NULL PRIMARY KEY,
    tenNhanVien NVARCHAR(50) NOT NULL,
    soDienThoai NVARCHAR(10),
    diaChi NVARCHAR(50),
    email NVARCHAR(50),
    ngaySinh DATE CHECK (ngaySinh < GETDATE()), 
    gioiTinh BIT,
    chucVu NVARCHAR(50),
    trangThai BIT DEFAULT 1, -- '1' cho 'Đang làm việc', '0' cho 'Đã nghỉ việc'
    
)
go
CREATE TABLE TaiKhoan (
    idTaiKhoan NVARCHAR(14) NOT NULL PRIMARY KEY,
    matKhau NVARCHAR(20) NOT NULL, 
    ngayLap DATE DEFAULT GETDATE(), 
    FOREIGN KEY (idTaiKhoan) REFERENCES NhanVien(idNhanVien) 
)
go
CREATE TABLE KhachHang(
	idKhachHang NVARCHAR(14) not null PRIMARY KEY,
	tenKhachHang NVARCHAR(50) not null,
	soDienThoai NVARCHAR(10),
	email NVARCHAR(50),
	diaChi NVARCHAR(50),
	ngaySinh DATE CHECK (ngaySinh < GETDATE()), 
	gioiTinh BIT
)
go
CREATE TABLE HoaDon (
    idDonHang NVARCHAR(14) NOT NULL PRIMARY KEY, 
    ngayLap DATE NOT NULL, 
    khachHang NVARCHAR(14) NOT NULL, 
    tienKhachDua FLOAT CHECK (tienKhachDua >= 0), 
	tongTien FLOAT,
    FOREIGN KEY (khachHang) REFERENCES KhachHang(idKhachHang), 
    --FOREIGN KEY (nhanVien) REFERENCES NhanVien(idNhanVien)
)
go

CREATE TABLE NhaCungCap(
	idNhaCungCap NVARCHAR(15) NOT NULL PRIMARY KEY,
	tenNhaCungCap NVARCHAR(50) NOT NULL,
	diaChi NVARCHAR(50),
	soDienThoai NVARCHAR(10)
)
go
CREATE TABLE LoaiSanPham (
    idLoaiSanPham NVARCHAR(15) PRIMARY KEY,
    tenLoaiSanPham NVARCHAR(50) NOT NULL
)


go
CREATE TABLE TacGia(
	idTacGia NVARCHAR(14) NOT NULL PRIMARY KEY,
	tenTacGia NVARCHAR(30) NOT NULL,
	ngaySinh DATE CHECK (ngaySinh < GETDATE()), 
	soLuongTacPham INT CHECK (soLuongTacPham >= 0)
)
go
CREATE TABLE TheLoai(
	idTheLoai NVARCHAR(14) NOT NULL PRIMARY KEY,
	tenTheLoai NVARCHAR(30) NOT NULL,
	soLuongSach INT CHECK (soLuongSach >= 0),
	moTa NVARCHAR(50) NOT NULL
)
go
CREATE TABLE SanPham (
    idSanPham NVARCHAR(14) NOT NULL PRIMARY KEY, 
    tenSanPham NVARCHAR(30) NOT NULL, 
	loaiSanPham NVARCHAR(15) NOT NULL,
    nhaCungCap NVARCHAR(15) NOT NULL,
    kichThuoc FLOAT NOT NULL, 
    mauSac NVARCHAR(255) NOT NULL, 
    trangThai BIT NOT NULL ,
	thue FLOAT CHECK (thue >= 0),
	giaNhap FLOAT CHECK (giaNhap >= 0),
	soLuong INT ,
	giaBan FLOAT,
	giaKhuyenMai FLOAT,
	FOREIGN KEY (loaiSanPham) REFERENCES LoaiSanPham(idLoaiSanPham),
    FOREIGN KEY (nhaCungCap) REFERENCES NhaCungCap(idNhaCungCap)
)

go
CREATE TABLE ChiTietHoaDon (
    soLuong INT CHECK (soLuong > 0), 
    idDonHang NVARCHAR(14) NOT NULL , 
    idSanPham NVARCHAR(14) NOT NULL, 
	thanhTien FLOAT,
	PRIMARY KEY (idDonHang, idSanPham),
    FOREIGN KEY (idDonHang) REFERENCES HoaDon(idDonHang), 
    FOREIGN KEY (idSanPham) REFERENCES SanPham(idSanPham)
)
go
CREATE TABLE Sach (
    idSanPham NVARCHAR(13) NOT NULL PRIMARY KEY, 
	tenSanPham NVARCHAR(30) NOT NULL, 
    tacGia NVARCHAR(14) NOT NULL, 
    theLoai NVARCHAR(14) NOT NULL,
    namXuatBan DATE NOT NULL CHECK (YEAR(namXuatBan) <= YEAR(GETDATE())),
    ISBN NVARCHAR(255) NOT NULL, 
    soTrang INT CHECK (soTrang > 0), 
	loaiSanPham NVARCHAR(15) NOT NULL,
    nhaCungCap NVARCHAR(15) NOT NULL,
    kichThuoc FLOAT NOT NULL, 
    mauSac NVARCHAR(255) NOT NULL, 
    trangThai BIT NOT NULL ,
	thue FLOAT CHECK (thue >= 0),
	soLuong INT,
	giaNhap FLOAT CHECK (giaNhap >= 0),
	giaBan FLOAT,
	giaKhuyenMai FLOAT
	FOREIGN KEY (loaiSanPham) REFERENCES LoaiSanPham(idLoaiSanPham),
    FOREIGN KEY (nhaCungCap) REFERENCES NhaCungCap(idNhaCungCap),
    FOREIGN KEY (tacGia) REFERENCES TacGia(idTacGia), 
    FOREIGN KEY (theLoai) REFERENCES TheLoai(idTheLoai)
)

go

CREATE TABLE KhuyenMai (
    idKM NVARCHAR(14) NOT NULL PRIMARY KEY,
    tenKM NVARCHAR(20) NOT NULL, 
    ngayBatDau DATE DEFAULT GETDATE(), 
    trangThai bit,
	loaiKM NVARCHAR(8)
)
CREATE TABLE ApDungKhuyenMai (
    idSP nvarchar(14) PRIMARY KEY,
    idKM nvarchar(14),
	tenSP nvarchar(30),
	
    giaBan float,
	giaKM float,
	
    FOREIGN KEY (idKM) REFERENCES KhuyenMai(idKM),
	FOREIGN KEY (idSP) REFERENCES SanPham(idSanPham),
	
);
CREATE TABLE ApDungKhuyenMaiSach (
    idS nvarchar(13) PRIMARY KEY,
    idKM nvarchar(14),
	tenSP nvarchar(30),
	
    giaBan float,
	giaKM float,
	
    FOREIGN KEY (idKM) REFERENCES KhuyenMai(idKM),
	FOREIGN KEY (idS) REFERENCES Sach(idSanPham),
	
);
select *from KhuyenMai
select *from ApDungKhuyenMai
select *from NhanVien
select *from TaiKhoan
select *from KhachHang
go

use master
drop database QLHieuSach


select *from SanPham
select *from NhaCungCap
select *from LoaiSanPham
select *from TacGia
select *from TheLoai
select *from Sach
select *from NhanVien
select *from TaiKhoan
select *from KhachHang

delete  from SanPham 
delete   from SanPham 
delete  from NhaCungCap
delete  from LoaiSanPham
delete from TheLoai
delete from TacGia

select *from Sach
select *from LoaiSanPham
select *from NhaCungCap
select *from TacGia
select *from TheLoai

go

SELECT s.idSanPham, s.tenSanPham, tg.tenTacGia, tl.tenTheLoai, s.namXuatBan, s.ISBN, s.soTrang, lsp.tenLoaiSanPham, ncc.tenNhaCungCap, s.kichThuoc, s.mauSac, s.trangThai, s.thue, s.soLuong, s.giaNhap, s.giaKhuyenMai FROM Sach s JOIN LoaiSanPham lsp ON lsp.idLoaiSanPham = s.loaiSanPham JOIN NhaCungCap ncc ON ncc.idNhaCungCap = s.nhaCungCap JOIN TacGia tg ON tg.idTacGia = s.tacGia JOIN TheLoai tl ON tl.idTheLoai = s.theLoai


