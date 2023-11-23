CREATE DATABASE QLHieuSach

go
-- Sử dụng cơ sở dữ liệu QLHieuSach
USE QLHieuSach
go
CREATE TABLE TaiKhoan (
    idTaiKhoan NVARCHAR(14) NOT NULL PRIMARY KEY,
    matKhau NVARCHAR(20) NOT NULL, 
    ngayLap DATE DEFAULT GETDATE(), 
)
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
CREATE TABLE QuanLy (
    idQuanLy NVARCHAR(14) NOT NULL PRIMARY KEY,
    tenNhanVien NVARCHAR(50) NOT NULL,
    soDienThoai NVARCHAR(10),
    diaChi NVARCHAR(50),
    email NVARCHAR(50),
    ngaySinh DATE CHECK (ngaySinh < GETDATE()), 
    gioiTinh BIT,
    chucVu NVARCHAR(50),
    trangThai BIT DEFAULT 1, -- '1' cho 'Đang làm việc', '0' cho 'Đã nghỉ việc'
   
)
ALTER TABLE NhanVien
ADD FOREIGN KEY (idNhanVien) REFERENCES TaiKhoan(idTaiKhoan);
ALTER TABLE QuanLy
ADD FOREIGN KEY (idQuanLy) REFERENCES TaiKhoan(idTaiKhoan);
go

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
    soTrang INT , 
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

go
--Quản lý
CREATE TRIGGER trg_GenerateQuanLyID
ON QuanLy
INSTEAD OF INSERT
AS
BEGIN
    DECLARE @NewID NVARCHAR(15);
    DECLARE @MaxID NVARCHAR(15);

    SELECT @MaxID = MAX(idQuanLy) FROM QuanLy;

    IF @MaxID IS NULL
        SET @NewID = 'QL' + FORMAT(GETDATE(), 'yyyyMMdd') + '0001';
    ELSE
    BEGIN
        DECLARE @LastCounter INT;
        SELECT @LastCounter = CAST(RIGHT(@MaxID, 4) AS INT);
        SET @LastCounter = @LastCounter + 1;

        SET @NewID = 'QL' + FORMAT(GETDATE(), 'yyyyMMdd') + RIGHT('0000' + CAST(@LastCounter AS NVARCHAR(4)), 4);
    END

    
    INSERT INTO QuanLy(idQuanLy,tenNhanVien,soDienThoai,diaChi,email,ngaySinh,gioiTinh,chucVu,trangThai)
    SELECT @NewID,tenNhanVien,soDienThoai,diaChi,email,ngaySinh,gioiTinh,chucVu,trangThai
    FROM INSERTED
END;
go
--Nhân viên
CREATE TRIGGER trg_GenerateNhanVienID
ON NhanVien
INSTEAD OF INSERT
AS
BEGIN
    DECLARE @NewID NVARCHAR(15);
    DECLARE @MaxID NVARCHAR(15);

    SELECT @MaxID = MAX(idNhanVien) FROM NhanVien;

    IF @MaxID IS NULL
        SET @NewID = 'NV' + FORMAT(GETDATE(), 'yyyyMMdd') + '0001';
    ELSE
    BEGIN
        DECLARE @LastCounter INT;
        SELECT @LastCounter = CAST(RIGHT(@MaxID, 4) AS INT);
        SET @LastCounter = @LastCounter + 1;

        SET @NewID = 'NV' + FORMAT(GETDATE(), 'yyyyMMdd') + RIGHT('0000' + CAST(@LastCounter AS NVARCHAR(4)), 4);
    END

    
    INSERT INTO NhanVien(idNhanVien,tenNhanVien,soDienThoai,diaChi,email,ngaySinh,gioiTinh,chucVu,trangThai)
    SELECT @NewID,tenNhanVien,soDienThoai,diaChi,email,ngaySinh,gioiTinh,chucVu,trangThai
    FROM INSERTED
END;
go
--KhachHang
CREATE TRIGGER trg_GenerateKhachHangID
ON KhachHang
INSTEAD OF INSERT
AS
BEGIN
    DECLARE @NewID NVARCHAR(15);
    DECLARE @MaxID NVARCHAR(15);

    SELECT @MaxID = MAX(idKhachHang) FROM KhachHang;

    IF @MaxID IS NULL
        SET @NewID = 'KH' + FORMAT(GETDATE(), 'yyyyMMdd') + '0001';
    ELSE
    BEGIN
        DECLARE @LastCounter INT;
        SELECT @LastCounter = CAST(RIGHT(@MaxID, 4) AS INT);
        SET @LastCounter = @LastCounter + 1;

        SET @NewID = 'KH' + FORMAT(GETDATE(), 'yyyyMMdd') + RIGHT('0000' + CAST(@LastCounter AS NVARCHAR(4)), 4);
    END

    
    INSERT INTO KhachHang(idKhachHang,tenKhachHang,soDienThoai,email,diaChi,ngaySinh,gioiTinh)
    SELECT @NewID,tenKhachHang,soDienThoai,email,diaChi,ngaySinh,gioiTinh
    FROM INSERTED
END;
go
--loai san pham 
CREATE TRIGGER trg_GenerateLoaiSanPhamID
ON LoaiSanPham
INSTEAD OF INSERT
AS
BEGIN
    DECLARE @NewID NVARCHAR(15);
    DECLARE @MaxID NVARCHAR(15);

    SELECT @MaxID = MAX(idLoaiSanPham) FROM LoaiSanPham;

    IF @MaxID IS NULL
        SET @NewID = 'LSP' + FORMAT(GETDATE(), 'yyyyMMdd') + '0001';
    ELSE
    BEGIN
        DECLARE @LastCounter INT;
        SELECT @LastCounter = CAST(RIGHT(@MaxID, 4) AS INT);
        SET @LastCounter = @LastCounter + 1;

        SET @NewID = 'LSP' + FORMAT(GETDATE(), 'yyyyMMdd') + RIGHT('0000' + CAST(@LastCounter AS NVARCHAR(4)), 4);
    END

    
    INSERT INTO LoaiSanPham(idLoaiSanPham,tenLoaiSanPham)
    SELECT @NewID,tenLoaiSanPham
    FROM INSERTED
END
go
--Nha Cung Cap 
CREATE TRIGGER trg_GenerateNhaCungCapID
ON NhaCungCap
INSTEAD OF INSERT
AS
BEGIN
    DECLARE @NewID NVARCHAR(15);
    DECLARE @MaxID NVARCHAR(15);

    SELECT @MaxID = MAX(idNhaCungCap) FROM NhaCungCap;

    IF @MaxID IS NULL
        SET @NewID = 'NCC' + FORMAT(GETDATE(), 'yyyyMMdd') + '0001';
    ELSE
    BEGIN
        DECLARE @LastCounter INT;
        SELECT @LastCounter = CAST(RIGHT(@MaxID, 4) AS INT);
        SET @LastCounter = @LastCounter + 1;

        SET @NewID = 'NCC' + FORMAT(GETDATE(), 'yyyyMMdd') + RIGHT('0000' + CAST(@LastCounter AS NVARCHAR(4)), 4);
    END

    
    INSERT INTO NhaCungCap(idNhaCungCap,tenNhaCungCap,diaChi, soDienThoai)
    SELECT @NewID,tenNhaCungCap, diaChi, soDienThoai
    FROM INSERTED
END
go
--Tac gia
CREATE TRIGGER trg_GenerateTacGiaID
ON TacGia
INSTEAD OF INSERT
AS
BEGIN
    DECLARE @NewID NVARCHAR(14);
    DECLARE @MaxID NVARCHAR(14);

    SELECT @MaxID = MAX(idTacGia) FROM TacGia;

    IF @MaxID IS NULL
        SET @NewID = 'TG' + FORMAT(GETDATE(), 'yyyyMMdd') + '0001';
    ELSE
    BEGIN
        DECLARE @LastCounter INT;
        SELECT @LastCounter = CAST(RIGHT(@MaxID, 4) AS INT);
        SET @LastCounter = @LastCounter + 1;

        SET @NewID = 'TG' + FORMAT(GETDATE(), 'yyyyMMdd') + RIGHT('0000' + CAST(@LastCounter AS NVARCHAR(4)), 4);
    END

    
    INSERT INTO TacGia(idTacGia,tenTacGia,ngaySinh, soLuongTacPham)
    SELECT @NewID,tenTacGia, ngaySinh, soLuongTacPham
    FROM INSERTED
END
go

--The Loai
CREATE TRIGGER trg_GenerateTheLoaiID
ON TheLoai
INSTEAD OF INSERT
AS
BEGIN
    DECLARE @NewID NVARCHAR(14);
    DECLARE @MaxID NVARCHAR(14);

    SELECT @MaxID = MAX(idTheLoai) FROM TheLoai;

    IF @MaxID IS NULL
        SET @NewID = 'TL' + FORMAT(GETDATE(), 'yyyyMMdd') + '0001';
    ELSE
    BEGIN
        DECLARE @LastCounter INT;
        SELECT @LastCounter = CAST(RIGHT(@MaxID, 4) AS INT);
        SET @LastCounter = @LastCounter + 1;

        SET @NewID = 'TL' + FORMAT(GETDATE(), 'yyyyMMdd') + RIGHT('0000' + CAST(@LastCounter AS NVARCHAR(4)), 4);
    END

    
    INSERT INTO TheLoai(idTheLoai,tenTheLoai,soLuongSach,moTa)
    SELECT @NewID,tenTheLoai,soLuongSach,moTa
    FROM INSERTED
END
go
--Sach
CREATE TRIGGER trg_GenerateSachID
ON Sach
INSTEAD OF INSERT
AS
BEGIN
    DECLARE @NewID NVARCHAR(13);
    DECLARE @MaxID NVARCHAR(13);

    SELECT @MaxID = MAX(idSanPham) FROM Sach;

    IF @MaxID IS NULL
        SET @NewID = 'S' + FORMAT(GETDATE(), 'yyyyMMdd') + '0001';
    ELSE
    BEGIN
        DECLARE @LastCounter INT;
        SELECT @LastCounter = CAST(RIGHT(@MaxID, 4) AS INT);
        SET @LastCounter = @LastCounter + 1;

        SET @NewID = 'S' + FORMAT(GETDATE(), 'yyyyMMdd') + RIGHT('0000' + CAST(@LastCounter AS NVARCHAR(4)), 4);
    END

    
    INSERT INTO Sach(idSanPham,tenSanPham,tacGia, theLoai, namXuatBan, ISBN, soTrang, loaiSanPham, nhaCungCap, kichThuoc, mauSac, trangThai, thue, soLuong, giaNhap, giaBan, giaKhuyenMai)
    SELECT @NewID,tenSanPham,tacGia, theLoai, namXuatBan, ISBN, soTrang, loaiSanPham, nhaCungCap, kichThuoc, mauSac, trangThai, thue, soLuong, giaNhap, giaBan, giaKhuyenMai
    FROM INSERTED
END

go

INSERT INTO LoaiSanPham VALUES (N'LSP202311130001',N'Đồ chơi')
INSERT INTO LoaiSanPham VALUES (N'LSP202311130002', N'Trò chơi giáo dục')
INSERT INTO LoaiSanPham VALUES (N'LSP202311130003', N'Sổ tay và sổ ghi chú')
INSERT INTO LoaiSanPham VALUES (N'LSP202311130004', N'Đồ chơi')
INSERT INTO LoaiSanPham VALUES (N'LSP202311130005', N'Phim và album')
INSERT INTO LoaiSanPham VALUES (N'LSP202311130006', N'Bản đồ')
INSERT INTO LoaiSanPham VALUES (N'LSP202311130007', N'Dấu trang')
INSERT INTO LoaiSanPham VALUES (N'LSP202311130008', N'Văn phòng phẩm')
INSERT INTO LoaiSanPham VALUES (N'LSP202311130009', N'Bút mực trang trí')
INSERT INTO LoaiSanPham VALUES (N'LSP202311131010', N'Thiệp chúc mừng và thiệp ghi chú trống')
INSERT INTO LoaiSanPham VALUES (N'LSP202311130011', N'Trò chơi bảng')
go
INSERT INTO NhaCungCap VALUES (N'NCC202311130001', N'Công ty Điện tử Minh Châu', N'123 Đường Nguyễn Văn Linh, Quận 1, TP.Hồ Chí Minh', N'0901234567');
INSERT INTO NhaCungCap VALUES (N'NCC202311130002', N'Công ty Thời trang Áo Đẹp', N'456 Đường Lê Lai, Quận 3, TP.Hồ Chí Minh', N'0918765432');
INSERT INTO NhaCungCap VALUES (N'NCC202311130003', N'Công ty Đồ gia dụng Hạnh Phúc', N'789 Đường Lê Thị Riêng, Quận 5, TP.Hồ Chí Minh', N'0987654321');
INSERT INTO NhaCungCap VALUES (N'NCC202311130004', N'Công ty Mỹ phẩm Tâm Anh', N'234 Đường Bà Triệu, Quận 7, TP.Hồ Chí Minh', N'0954321897');
INSERT INTO NhaCungCap VALUES (N'NCC202311130005', N'Công ty Quà lưu niệm Vui Vẻ', N'567 Đường Đống Đa, Quận 10, TP.Hồ Chí Minh', N'0967890123');
INSERT INTO NhaCungCap VALUES (N'NCC202311130006', N'Công ty Sách Hữu Nghị', N'890 Đường Phan Chu Trinh, Quận Bình Thạnh', N'0923456789');
INSERT INTO NhaCungCap VALUES (N'NCC202311130007', N'Công ty Đồ chơi Trí Tuệ', N'321 Đường Hoàng Sa, Q. Phú Nhuận, TP.Hồ Chí Minh', N'0945678901');
INSERT INTO NhaCungCap VALUES (N'NCC202311130008', N'Công ty Nước hoa Thanh Xuân', N'654 Đường Cách Mạng Tháng 8', N'0912345678');
INSERT INTO NhaCungCap VALUES (N'NCC202311130009', N'Công ty Đồ điện gia dụng Tiến Đạt', N'111 Đường Trần Hưng Đạo', N'0932109876');
INSERT INTO NhaCungCap VALUES (N'NCC202311130010', N'Công ty Phụ kiện Thời trang Sang Trọng', N'11 Đường Trần Hưng Đạo', N'0978563412');
go
INSERT INTO TacGia VALUES (N'TG202311130001', N'Nguyễn Nhật Ánh', '1955-05-07', 1);
INSERT INTO TacGia VALUES (N'TG202311130002', N'Tô Hoài', '1920-08-04', 1);
INSERT INTO TacGia VALUES (N'TG202311130003', N'Nguyễn Du', '1766-01-05', 0);
INSERT INTO TacGia VALUES (N'TG202311130004', N'Trí Tuệ', '1980-12-15', 0);
INSERT INTO TacGia VALUES (N'TG202311130005', N'Tuổi Trẻ', '1988-03-20', 0);
INSERT INTO TacGia VALUES (N'TG202311130006', N'Kim Dung', '1924-02-07', 0);
INSERT INTO TacGia VALUES (N'TG202311130007', N'Xuân Diệu', '1916-03-02', 0);
INSERT INTO TacGia VALUES (N'TG202311130008', N'Hồ Xuân Hương', '1772-02-06', 0);
INSERT INTO TacGia VALUES (N'TG202311130009', N'Nam Cao', '1915-12-15', 0);
INSERT INTO TacGia VALUES (N'TG202311130010', N'Lê Lợi', '1385-01-10', 0);
go
INSERT INTO TheLoai VALUES (N'TL202311130001', N'Tiểu thuyết', 1, N'Tiểu thuyết văn học');
INSERT INTO TheLoai VALUES (N'TL202311130002', N'Khoa học', 1, N'Sách khoa học tự nhiên');
INSERT INTO TheLoai VALUES (N'TL202311130003', N'Lịch sử', 0, N'Sách về lịch sử');
INSERT INTO TheLoai VALUES (N'TL202311130004', N'Tâm lý', 0, N'Sách về tâm lý');
INSERT INTO TheLoai VALUES (N'TL202311130005', N'Thể thao', 0, N'Sách về thể thao');
INSERT INTO TheLoai VALUES (N'TL202311130006', N'Kinh tế', 0, N'Sách về kinh tế');
INSERT INTO TheLoai VALUES (N'TL202311130007', N'Nấu ăn', 0, N'Sách nấu ăn và ẩm thực');
INSERT INTO TheLoai VALUES (N'TL202311130008', N'Manga', 0, N'Sách manga');
INSERT INTO TheLoai VALUES (N'TL202311130009', N'Thơ', 0, N'Sách thơ');
INSERT INTO TheLoai VALUES (N'TL202311130010', N'Tiểu sử', 0, N'Tiểu sử nhân vật nổi tiếng');
INSERT INTO TheLoai VALUES (N'TL202311130011', N'Khoa học - Viễn tưởng', 0, N'Sách khoa học viễn tưởng');
INSERT INTO TheLoai VALUES (N'TL202311130012', N'Dựa trên sự kiện có thật', 0, N'Sách dựa trên sự kiện có thật');
INSERT INTO TheLoai VALUES (N'TL202311130013', N'Chính trị - Pháp luật', 0, N'Sách về chính trị và pháp luật');
INSERT INTO TheLoai VALUES (N'TL202311130014', N'Kỹ năng sống', 0, N'Sách về kỹ năng sống');
INSERT INTO TheLoai VALUES (N'TL202311130015', N'Hài hước', 0, N'Sách hài hước');
go
INSERT INTO Sach VALUES (N'S202311130001', N'Chút gió thoáng qua', N'TG202311230001', N'TL202311230001', '2022-01-01', N'978-123-123-123-2', 200, N'LSP202311230001', N'NCC202311230001', 15.5, N'Nâu', 1, 2500, 22, 500000, 800000,800000);
INSERT INTO Sach VALUES (N'S202311130002', N'Cây cam ngọt ngào', N'TG202311230001', N'TL202311230001', '2022-01-01', N'978-123-123-123-2', 200, N'LSP202311230003', N'NCC202311230003', 15.5, N'Nâu', 1, 2500, 100, 700000, 1120000,1120000);
go

INSERT INTO SanPham VALUES (N'SP202311130001', N'Sản phẩm A', N'LSP202311230001', N'NCC202311230001', 10.0, N'Đỏ', 1, 0, 100000, 50, 160000, 160000);
INSERT INTO SanPham VALUES (N'SP202311130002', N'Sản phẩm B', N'LSP202311230002', N'NCC202311230002', 12.5, N'Xanh', 1, 0, 120000, 30, 192000, 192000);
INSERT INTO SanPham VALUES (N'SP202311130003', N'Sản phẩm C', N'LSP202311230003', N'NCC202311230003', 8.0, N'Vàng', 1, 0, 80000, 70, 128000, 128000);
INSERT INTO SanPham VALUES (N'SP202311130004', N'Sản phẩm D', N'LSP202311230001', N'NCC202311230002', 15.0, N'Đen', 1, 0, 150000, 40, 240000, 240000);
INSERT INTO SanPham VALUES (N'SP202311130005', N'Sản phẩm E', N'LSP202311230002', N'NCC202311230003', 9.5, N'Hồng', 1, 0, 95000, 60, 152000, 152000);
go
insert TaiKhoan values('ADMIN','1111','2023-11-13')
go
--QL202311230001
--NV202311130001
select *from TheLoai
select *from NhaCungCap
select *from LoaiSanPham
select *from Sach
select *from TacGia
select *from SanPham

use QLHieuSach
use master
drop database QLHieuSach

delete from SanPham