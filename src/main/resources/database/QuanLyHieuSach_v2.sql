CREATE DATABASE QLHieuSach
ON PRIMARY
(NAME = N'QLHieuSach_data',
FILENAME = N'F:\ashleynguci\PTUD\SQL\QLHieuSach_data.mdf',
SIZE = 10MB,
MAXSIZE = 20MB,
FILEGROWTH = 20%)
LOG ON
(NAME = N'QLHieuSach_log',
FILENAME = N'F:\ashleynguci\PTUD\SQL\QLHieuSach_log.ldf',
SIZE = 5MB,
MAXSIZE = 10MB,
FILEGROWTH = 5MB);

-- Thêm tệp dữ liệu phụ (.ndf) vào nhóm tệp QLBHgroup1
ALTER DATABASE QLHieuSach
ADD FILEGROUP QLHieuSachgroup1;

ALTER DATABASE QLHieuSach
ADD FILE
(NAME = N'QLBH_data1',
FILENAME = N'F:\ashleynguci\PTUD\SQL\QLHieuSach_data1.ndf',
SIZE = 10MB,
MAXSIZE = 20MB,
FILEGROWTH = 10MB);
go
-- Sử dụng cơ sở dữ liệu QLHieuSach
USE QLHieuSach
go
CREATE TABLE NhanVien (
    idNhanVien NVARCHAR(7) NOT NULL PRIMARY KEY,
    tenNhanVien NVARCHAR(50) NOT NULL,
    soDienThoai NVARCHAR(10),
    diaChi NVARCHAR(50),
    email NVARCHAR(50),
    ngaySinh DATE CHECK (ngaySinh < GETDATE()), 
    gioiTinh BIT,
    chucVu NVARCHAR(50),
    trangThai BIT DEFAULT 1, -- '1' cho 'Đang làm việc', '0' cho 'Đã nghỉ việc'
    luong FLOAT CHECK (luong > 2000000)
)
go
CREATE TABLE TaiKhoan (
    idTaiKhoan NVARCHAR(7) NOT NULL PRIMARY KEY,
    matKhau NVARCHAR(20) NOT NULL, 
    ngayLap DATE DEFAULT GETDATE(), 
    FOREIGN KEY (idTaiKhoan) REFERENCES NhanVien(idNhanVien) 
)
go
CREATE TABLE KhachHang(
	idKhachHang NVARCHAR(7) not null PRIMARY KEY,
	tenKhachHang NVARCHAR(50) not null,
	soDienThoai NVARCHAR(10),
	email NVARCHAR(50),
	diaChi NVARCHAR(50),
	ngaySinh DATE CHECK (ngaySinh < GETDATE()), 
	gioiTinh BIT
)
go
CREATE TABLE HoaDon (
    idDonHang NVARCHAR(7) NOT NULL PRIMARY KEY, 
    ngayLap DATE NOT NULL, 
    khachHang NVARCHAR(7) NOT NULL, 
    nhanVien NVARCHAR(7) NOT NULL, 
    tienKhachDua FLOAT CHECK (tienKhachDua >= 0), 
	tongTien FLOAT,
    FOREIGN KEY (khachHang) REFERENCES KhachHang(idKhachHang), 
    FOREIGN KEY (nhanVien) REFERENCES NhanVien(idNhanVien)
)
go
CREATE TABLE PhieuNhapSanPham (
    idPhieuNhapSanPham NVARCHAR(7) NOT NULL PRIMARY KEY, 
    ngayNhap DATE CHECK (ngayNhap <= GETDATE()),
    nhanVien NVARCHAR(7) NOT NULL, 
	tongTien FLOAT,
    FOREIGN KEY (nhanVien) REFERENCES NhanVien(idNhanVien)
)
go
CREATE TABLE NhaCungCap(
	idNhaCungCap NVARCHAR(7) NOT NULL PRIMARY KEY,
	tenNhaCungCap NVARCHAR(50) NOT NULL,
	diaChi NVARCHAR(50),
	soDienThoai NVARCHAR(10)
)
go
CREATE TABLE LoaiSanPham(
	idLoaiSanPham NVARCHAR(7) NOT NULL PRIMARY KEY,
	tenLoaiSanPham NVARCHAR(30) NOT NULL
)

go
CREATE TABLE TacGia(
	idTacGia NVARCHAR(7) NOT NULL PRIMARY KEY,
	tenTacGia NVARCHAR(30) NOT NULL,
	ngaySinh DATE CHECK (ngaySinh < GETDATE()), 
	soLuongTacPham INT CHECK (soLuongTacPham > 0)
)
go
CREATE TABLE TheLoai(
	idTheLoai NVARCHAR(7) NOT NULL PRIMARY KEY,
	tenTheLoai NVARCHAR(30) NOT NULL,
	soLuongSach INT CHECK (soLuongSach > 0),
	moTa NVARCHAR(50) NOT NULL
)
go
CREATE TABLE SanPham (
    hinhAnhSanPham NVARCHAR(255) NOT NULL, 
    idSanPham NVARCHAR(7) NOT NULL PRIMARY KEY, 
    tenSanPham NVARCHAR(30) NOT NULL, 
	loaiSanPham NVARCHAR(7) NOT NULL,
    nhaCungCap NVARCHAR(7) NOT NULL,
    kichThuoc FLOAT NOT NULL, 
    mauSac NVARCHAR(255) NOT NULL, 
    trangThai BIT NOT NULL ,
	thue FLOAT CHECK (thue > 0),
	soLuong INT ,
	giaBan FLOAT,
	FOREIGN KEY (loaiSanPham) REFERENCES LoaiSanPham(idLoaiSanPham),
    FOREIGN KEY (nhaCungCap) REFERENCES NhaCungCap(idNhaCungCap)
)
go
CREATE TABLE ChuongTrinhKhuyenMai (
    idCTKM NVARCHAR(7) NOT NULL PRIMARY KEY,
    soTienGiamGia FLOAT NOT NULL, 
    idSanPham NVARCHAR(7) NOT NULL, 
    FOREIGN KEY (idSanPham) REFERENCES SanPham(idSanPham) 
)
go
CREATE TABLE ChiTietHoaDon (
    soLuong INT CHECK (soLuong > 0), 
    idDonHang NVARCHAR(7) NOT NULL , 
    idSanPham NVARCHAR(7) NOT NULL, 
	thanhTien FLOAT,
	PRIMARY KEY (idDonHang, idSanPham),
    FOREIGN KEY (idDonHang) REFERENCES HoaDon(idDonHang), 
    FOREIGN KEY (idSanPham) REFERENCES SanPham(idSanPham)
)
go
CREATE TABLE Sach (
    idSanPham NVARCHAR(7) NOT NULL PRIMARY KEY, 
	tenSanPham NVARCHAR(30) NOT NULL, 
    tacGia NVARCHAR(7) NOT NULL, 
    theLoai NVARCHAR(7) NOT NULL,
    namXuatBan DATE NOT NULL CHECK (YEAR(namXuatBan) <= YEAR(GETDATE())),
    ISBN NVARCHAR(255) NOT NULL, 
    soTrang INT CHECK (soTrang > 0), 
	hinhAnhSanPham NVARCHAR(255) NOT NULL, 
	loaiSanPham NVARCHAR(7) NOT NULL,
    nhaCungCap NVARCHAR(7) NOT NULL,
    kichThuoc FLOAT NOT NULL, 
    mauSac NVARCHAR(255) NOT NULL, 
    trangThai BIT NOT NULL 
	FOREIGN KEY (loaiSanPham) REFERENCES LoaiSanPham(idLoaiSanPham),
    FOREIGN KEY (nhaCungCap) REFERENCES NhaCungCap(idNhaCungCap),
    FOREIGN KEY (tacGia) REFERENCES TacGia(idTacGia), 
    FOREIGN KEY (theLoai) REFERENCES TheLoai(idTheLoai)
)
CREATE TABLE ChiTietPhieuNhapSanPham (
    soLuongNhap INT CHECK (soLuongNhap > 0), 
    giaNhap FLOAT CHECK (giaNhap > 0), 
    phieuNhapSanPham NVARCHAR(7) NOT NULL,
    idsanPham NVARCHAR(7) NOT NULL, 
	PRIMARY KEY (phieuNhapSanPham, idsanPham),
    FOREIGN KEY (phieuNhapSanPham) REFERENCES PhieuNhapSanPham(idPhieuNhapSanPham),
    FOREIGN KEY (idsanPham) REFERENCES SanPham(idSanPham) 
)
go

use master
drop database QLHieuSach


