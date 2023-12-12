CREATE DATABASE QLHieuSach

go
-- Sử dụng cơ sở dữ liệu QLHieuSach
USE QLHieuSach
go
CREATE TABLE TaiKhoan (
    idTaiKhoan NVARCHAR(14) NOT NULL PRIMARY KEY,
    matKhau NVARCHAR(255) NOT NULL, 
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
	nhanVien NVARCHAR(14) NOT NULL, 
    khachHang NVARCHAR(14) NOT NULL, 
    tienKhachDua FLOAT CHECK (tienKhachDua >= 0), 
	tongTien FLOAT,
	tongLoiNhuan FLOAT,
    FOREIGN KEY (khachHang) REFERENCES KhachHang(idKhachHang), 
    FOREIGN KEY (nhanVien) REFERENCES NhanVien(idNhanVien)
)
go

CREATE TABLE NhaCungCap(
	idNhaCungCap NVARCHAR(15) NOT NULL PRIMARY KEY,
	tenNhaCungCap NVARCHAR(50) NOT NULL,
	diaChi NVARCHAR(100),
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
	tenTheLoai NVARCHAR(100) NOT NULL,
	soLuongSach INT CHECK (soLuongSach >= 0),
	moTa NVARCHAR(100) NOT NULL
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
CREATE TABLE ChiTietHoaDonSanPham (
    soLuong INT CHECK (soLuong > 0), 
    idDonHang NVARCHAR(14) NOT NULL , 
    idSanPham NVARCHAR(14) NOT NULL, 
	thanhTien FLOAT,
	loiNhuan FLOAT,
	PRIMARY KEY (idDonHang, idSanPham),
    FOREIGN KEY (idDonHang) REFERENCES HoaDon(idDonHang), 
    FOREIGN KEY (idSanPham) REFERENCES SanPham(idSanPham)
)
go
CREATE TABLE Sach (
    idSanPham NVARCHAR(13) NOT NULL PRIMARY KEY, 
	tenSanPham NVARCHAR(100) NOT NULL, 
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
CREATE TABLE ChiTietHoaDonSach (
    soLuong INT CHECK (soLuong > 0), 
    idDonHang NVARCHAR(14) NOT NULL , 
    idSanPham NVARCHAR(13) NOT NULL, 
	thanhTien FLOAT,
	loiNhuan FLOAT,
	PRIMARY KEY (idDonHang, idSanPham),
    FOREIGN KEY (idDonHang) REFERENCES HoaDon(idDonHang), 
    FOREIGN KEY (idSanPham) REFERENCES Sach(idSanPham)
)
go
CREATE TABLE HoaDonCho (
	idDonHangCho NVARCHAR(15) NOT NULL PRIMARY KEY, 
    idDonHang NVARCHAR(14) NOT NULL, 
	idKhachHang NVARCHAR(14) NOT NULL,
	tenKhachHang NVARCHAR(30) NOT NULL,
	soDienThoai NVARCHAR(14) NOT NULL,
    ngayLap DATE NOT NULL
    FOREIGN KEY (idKhachHang) REFERENCES KhachHang(idKhachHang), 
)
go

CREATE TABLE ChiTietHoaDonChoSach (
    idDonHangCho NVARCHAR(15) NOT NULL , 
	idDonHang NVARCHAR(14) NOT NULL , 
	tenSanPham NVARCHAR(30) NOT NULL , 
    idSanPham NVARCHAR(13) NOT NULL, 
	giaBan FLOAT,
	khuyenMai FLOAT,
	soLuong INT CHECK (soLuong > 0), 
	giaCuoi FLOAT,
	thanhTien FLOAT,
	PRIMARY KEY (idDonHangCho, idSanPham),
    FOREIGN KEY (idDonHangCho) REFERENCES HoaDonCho(idDonHangCho), 
    FOREIGN KEY (idSanPham) REFERENCES Sach(idSanPham)
)
go
CREATE TABLE ChiTietHoaDonChoSanPham (
    idDonHangCho NVARCHAR(15) NOT NULL , 
	idDonHang NVARCHAR(14) NOT NULL , 
	tenSanPham NVARCHAR(30) NOT NULL , 
    idSanPham NVARCHAR(14) NOT NULL, 
	giaBan FLOAT,
	khuyenMai FLOAT,
	soLuong INT CHECK (soLuong > 0), 
	giaCuoi FLOAT,
	thanhTien FLOAT,
	PRIMARY KEY (idDonHangCho, idSanPham),
    FOREIGN KEY (idDonHangCho) REFERENCES HoaDonCho(idDonHangCho), 
    FOREIGN KEY (idSanPham) REFERENCES SanPham(idSanPham)
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

INSERT INTO LoaiSanPham VALUES (N'',N'Sách')
INSERT INTO LoaiSanPham VALUES (N'', N'Trò chơi giáo dục')
INSERT INTO LoaiSanPham VALUES (N'', N'Sổ tay và sổ ghi chú')
INSERT INTO LoaiSanPham VALUES (N'', N'Đồ chơi')
INSERT INTO LoaiSanPham VALUES (N'', N'Phim và album')
INSERT INTO LoaiSanPham VALUES (N'', N'Bản đồ')
INSERT INTO LoaiSanPham VALUES (N'', N'Dấu trang')
INSERT INTO LoaiSanPham VALUES (N'', N'Văn phòng phẩm')
INSERT INTO LoaiSanPham VALUES (N'', N'Bút mực trang trí')
INSERT INTO LoaiSanPham VALUES (N'', N'Thiệp chúc mừng và thiệp ghi chú trống')
INSERT INTO LoaiSanPham VALUES (N'',N'Trò chơi bảng')
go
INSERT INTO NhaCungCap VALUES (N'', N'Công ty Điện tử Minh Châu', N'123 Đường Nguyễn Văn Linh, Quận 1, TP.Hồ Chí Minh', N'0901234567');
INSERT INTO NhaCungCap VALUES (N'', N'Công ty Thời trang Áo Đẹp', N'456 Đường Lê Lai, Quận 3, TP.Hồ Chí Minh', N'0918765432');
INSERT INTO NhaCungCap VALUES (N'', N'Công ty Đồ gia dụng Hạnh Phúc', N'789 Đường Lê Thị Riêng, Quận 5, TP.Hồ Chí Minh', N'0987654321');
INSERT INTO NhaCungCap VALUES (N'', N'Công ty Mỹ phẩm Tâm Anh', N'234 Đường Bà Triệu, Quận 7, TP.Hồ Chí Minh', N'0954321897');
INSERT INTO NhaCungCap VALUES (N'', N'Công ty Quà lưu niệm Vui Vẻ', N'567 Đường Đống Đa, Quận 10, TP.Hồ Chí Minh', N'0967890123');
INSERT INTO NhaCungCap VALUES (N'', N'Công ty Sách Hữu Nghị', N'890 Đường Phan Chu Trinh, Quận Bình Thạnh', N'0923456789');
INSERT INTO NhaCungCap VALUES (N'', N'Công ty Đồ chơi Trí Tuệ', N'321 Đường Hoàng Sa, Q. Phú Nhuận, TP.Hồ Chí Minh', N'0945678901');
INSERT INTO NhaCungCap VALUES (N'', N'Công ty Nước hoa Thanh Xuân', N'654 Đường Cách Mạng Tháng 8', N'0912345678');
INSERT INTO NhaCungCap VALUES (N'', N'Công ty Đồ điện gia dụng Tiến Đạt', N'111 Đường Trần Hưng Đạo', N'0932109876');
INSERT INTO NhaCungCap VALUES (N'', N'Công ty Phụ kiện Thời trang Sang Trọng', N'11 Đường Trần Hưng Đạo', N'0978563412');
INSERT INTO NhaCungCap VALUES (N'', N'Công ty Sách Văn Học Việt Nam', N'22 Đường Trần Hưng Đạo, Quận 1, TP.Hồ Chí Minh', N'0901234987');
INSERT INTO NhaCungCap VALUES (N'', N'Công ty Sách Giáo Khoa Thành Phố', N'12 Đường Nguyễn Thị Minh Khai, Quận 1, TP.Hồ Chí Minh', N'0909123456'); 
INSERT INTO NhaCungCap VALUES (N'', N'Công ty Văn Phòng Phẩm Thái Bình', N'34 Đường Lý Tự Trọng, Quận 3, TP.Hồ Chí Minh', N'0919876543');
INSERT INTO NhaCungCap VALUES (N'', N'Công ty Đồ Dùng Học Tập Huy Hoàng', N'56 Đường Nguyễn Trãi, Quận 5, TP.Hồ Chí Minh', N'0987765432');
INSERT INTO NhaCungCap VALUES (N'', N'Công ty Sách Ngoại Văn Anh Văn', N'78 Đường Nguyễn Đình Chiểu, Quận 7, TP.Hồ Chí Minh', N'0955432189');
INSERT INTO NhaCungCap VALUES (N'', N'Công ty Quà Tặng Văn Hóa Việt', N'90 Đường Cao Thắng, Quận 10, TP.Hồ Chí Minh', N'0968901234');
INSERT INTO NhaCungCap VALUES (N'', N'Công ty Sách Khoa Học Kỹ Thuật', N'11 Đường Trần Quang Khải, Quận Bình Thạnh, TP.Hồ Chí Minh', N'0924567890');
INSERT INTO NhaCungCap VALUES (N'', N'Công ty Đồ Chơi Giáo Dục Thông Minh', N'33 Đường Phan Đăng Lưu, Quận Phú Nhuận, TP.Hồ Chí Minh', N'0946789012'); 
INSERT INTO NhaCungCap VALUES (N'', N'Công ty Sách Truyện Thiếu Nhi', N'55 Đường Nguyễn Văn Cừ, Quận 11, TP.Hồ Chí Minh', N'0913456789'); 
INSERT INTO NhaCungCap VALUES (N'', N'Công ty Văn Phòng Phẩm Sáng Tạo', N'77 Đường Lê Văn Sỹ, Quận Tân Bình, TP.Hồ Chí Minh', N'0933210987');
INSERT INTO NhaCungCap VALUES (N'', N'Công ty Quà Tặng Sách Nghệ Thuật', N'99 Đường Nguyễn Thị Thập, Quận 7, TP.Hồ Chí Minh', N'0979654321');
INSERT INTO NhaCungCap VALUES (N'', N'Công ty Văn Phòng Phẩm Chất Lượng', N'44 Đường Lê Thánh Tôn, Quận 3, TP.Hồ Chí Minh', N'0918765439');
INSERT INTO NhaCungCap VALUES (N'', N'Công ty Đồ Dùng Học Tập Tiện Lợi', N'66 Đường Trần Phú, Quận 5, TP.Hồ Chí Minh', N'0987654398');
INSERT INTO NhaCungCap VALUES (N'', N'Công ty Sách Ngoại Văn Pháp Văn', N'88 Đường Nguyễn Thái Học, Quận 7, TP.Hồ Chí Minh', N'0954321987');
INSERT INTO NhaCungCap VALUES (N'', N'Công ty Quà Tặng Văn Hóa Nhật', N'111 Đường Lý Thường Kiệt, Quận 10, TP.Hồ Chí Minh', N'0967890345');
INSERT INTO NhaCungCap VALUES (N'', N'Công ty Sách Khoa Học Phổ Thông', N'222 Đường Nguyễn Văn Cừ, Quận Bình Thạnh, TP.Hồ Chí Minh', N'0923456901'); 
INSERT INTO NhaCungCap VALUES (N'', N'Công ty Đồ Chơi Giáo Dục Vui Nhộn', N'333 Đường Nguyễn Kiệm, Quận Phú Nhuận, TP.Hồ Chí Minh', N'0945678034'); 
INSERT INTO NhaCungCap VALUES (N'', N'Công ty Sách Truyện Thiếu Niên', N'444 Đường Lê Đại Hành, Quận 11, TP.Hồ Chí Minh', N'0913456987');
INSERT INTO NhaCungCap VALUES (N'', N'Công ty Văn Phòng Phẩm Đa Dạng', N'555 Đường Bạch Đằng, Quận Tân Bình, TP.Hồ Chí Minh', N'0933210976'); 
INSERT INTO NhaCungCap VALUES (N'', N'Công ty Quà Tặng Sách Hài Hước', N'666 Đường Nguyễn Hữu Thọ, Quận 7, TP.Hồ Chí Minh', N'0979654310');




go
INSERT INTO TacGia VALUES (N'', N'Nguyễn Nhật Ánh', '1955-05-07', 6);
INSERT INTO TacGia VALUES (N'', N'Tô Hoài', '1920-08-04', 6);
INSERT INTO TacGia VALUES (N'', N'Nguyễn Du', '1766-01-05', 6);
INSERT INTO TacGia VALUES (N'', N'Trí Tuệ', '1980-12-15', 4);
INSERT INTO TacGia VALUES (N'', N'Tuổi Trẻ', '1988-03-20', 4);
INSERT INTO TacGia VALUES (N'', N'Kim Dung', '1924-02-07', 4);
INSERT INTO TacGia VALUES (N'', N'Xuân Diệu', '1916-03-02', 6);
INSERT INTO TacGia VALUES (N'', N'Hồ Xuân Hương', '1772-02-06', 4);
INSERT INTO TacGia VALUES (N'', N'Nam Cao', '1915-12-15', 6);
INSERT INTO TacGia VALUES (N'', N'Lê Lợi', '1385-01-10', 4);
INSERT INTO TacGia VALUES (N'', N'Lê Thanh An', '2011-01-10', 10);
INSERT INTO TacGia VALUES (N'', N'Vũ Thảo Ánh', '2000-02-07', 4);
INSERT INTO TacGia VALUES (N'', N'Bùi Thanh Bình', '1997-02-17', 4);
INSERT INTO TacGia VALUES (N'', N'Nguyễn Thanh Cảnh', '1898-07-10', 6);
INSERT INTO TacGia VALUES (N'', N'Lê Đôn Chủng', '2002-09-11', 8);
INSERT INTO TacGia VALUES (N'', N'Vũ Hòa Bình', '1978-09-19', 10);
INSERT INTO TacGia VALUES (N'', N'Nguyễn Duy Cường', '1999-09-25', 10);
INSERT INTO TacGia VALUES (N'', N'Lê Văn Hoàng', '2003-07-10', 10);
INSERT INTO TacGia VALUES (N'', N'Trần Gia Huy', '1977-02-19', 6);
INSERT INTO TacGia VALUES (N'', N'Hồ Nguyễn Đăng Khoa', '1986-01-29', 10);
INSERT INTO TacGia VALUES (N'', N'Đặng Phạm Thiên Khải', '1980-11-27', 8);
INSERT INTO TacGia VALUES (N'', N'Lê Hoàng Khang', '1967-09-25', 4);
INSERT INTO TacGia VALUES (N'', N'Nguyễn Xuân Nam', '1909-09-02', 22);
INSERT INTO TacGia VALUES (N'', N'Nguyễn Thị Tuyết Ngân', '2003-12-01', 6);
INSERT INTO TacGia VALUES (N'', N'Phạm Hoàng Ngọc Quân', '1885-09-03', 6);
INSERT INTO TacGia VALUES (N'', N'Lê Vũ Hạo', '1866-11-09', 4);
INSERT INTO TacGia VALUES (N'', N'Nguyễn Thị Hồng Lương', '1978-07-28', 3);
INSERT INTO TacGia VALUES (N'', N'Phạm Hoàng Ngọc Quân', '2001-09-03', 3);
INSERT INTO TacGia VALUES (N'', N'Lê Thị Như Ngọc', '2002-05-12', 4);


go
INSERT INTO TheLoai VALUES (N'', N'Tiểu thuyết khoa học viễn tưởng', 2, N'Tiểu thuyết dựa trên những khả năng khoa học và công nghệ trong tương lai hoặc thế giới khác');
INSERT INTO TheLoai VALUES (N'', N'Tiểu thuyết giả tưởng', 7, N'Tiểu thuyết sử dụng những yếu tố huyền bí, siêu nhiên, thần thoại hoặc không có thật');
INSERT INTO TheLoai VALUES (N'', N'Truyện ngắn', 6, N'Tác phẩm văn học có độ dài ngắn hơn tiểu thuyết, thường xoay quanh một sự kiện');
INSERT INTO TheLoai VALUES (N'', N'Cổ tích', 5, N'Truyện kể về những sự kiện kỳ diệu, những nhân vật có phép thuật hoặc những bài học đạo đức');
INSERT INTO TheLoai VALUES (N'', N'Chính trị', 5, N'Sách nói về những vấn đề, quan điểm, lịch sử hoặc hệ thống chính trị của một quốc gia, vùng lãnh thổ');
INSERT INTO TheLoai VALUES (N'', N'Giáo dục giới tính', 3, N'Sách cung cấp những kiến thức, kỹ năng, thái độ và giá trị liên quan đến giới tính, sinh lý');
INSERT INTO TheLoai VALUES (N'', N'Hài hước', 1, N'Sách có mục đích làm cho người đọc cười hoặc giải trí bằng những tình huống, nhân vật, ngôn ngữ');
INSERT INTO TheLoai VALUES (N'', N'Hình sự', 2, N'Sách xoay quanh những vụ án, tội phạm, điều tra, truy tố hoặc pháp lý');
INSERT INTO TheLoai VALUES (N'', N'Hồi ký', 2, N'Sách viết về cuộc đời, sự nghiệp, trải nghiệm hoặc suy nghĩ của một người hoặc một nhóm người');
INSERT INTO TheLoai VALUES (N'', N'Kinh dị', 4, N'Sách tạo ra những cảm xúc sợ hãi, ghê rợn, căng thẳng cho người đọc bằng những yếu tố bạo lực');
INSERT INTO TheLoai VALUES (N'', N'Kinh doanh', 13, N'Sách cung cấp những kiến thức, kỹ năng, chiến lược, phân tích hoặc kinh nghiệm ');
INSERT INTO TheLoai VALUES (N'', N'Kỹ năng sống', 4, N'Sách hướng dẫn người đọc cách đối phó, giải quyết hoặc cải thiện những vấn đề');
INSERT INTO TheLoai VALUES (N'', N'Khoa học viễn tưởng', 9, N'Sách giải thích những hiện tượng, sự kiện, khả năng hoặc thế giới khác');
INSERT INTO TheLoai VALUES (N'', N'Ngôn tình', 4, N'Tiểu thuyết tập trung vào những mối quan hệ, tình cảm, đời sống hoặc xã hội của những nhân vật nữ');
INSERT INTO TheLoai VALUES (N'', N'Phát triển bản thân', 19, N'Sách giúp người đọc nhận thức, phát huy hoặc thay đổi những phẩm chất');
INSERT INTO TheLoai VALUES (N'', N'Phiêu lưu', 4, N'Sách kể về những chuyến đi, cuộc hành trình, khám phá hoặc thử thách');
INSERT INTO TheLoai VALUES (N'', N'Thiếu nhi', 1, N'Sách dành cho độc giả nhỏ tuổi, thường có nội dung giáo dục, giải trí');
INSERT INTO TheLoai VALUES (N'', N'Truyện tranh', 8, N'Tác phẩm văn học kết hợp giữa hình ảnh và chữ viết để kể một câu chuyện');
INSERT INTO TheLoai VALUES (N'', N'Tâm lý học', 7, N'Sách nghiên cứu, phân tích hoặc ứng dụng những kiến thức, nguyên lý, phương pháp');
INSERT INTO TheLoai VALUES (N'', N'TIểu thuyết', 20, N'Tác phẩm văn học dài, kể về một hoặc nhiều câu chuyện, nhân vật, tình tiết hoặc đề tài');
INSERT INTO TheLoai VALUES (N'', N'Trinh thám', 5, N'Sách kể về quá trình tìm kiếm, phát hiện, giải mã hoặc làm sáng tỏ những bí ẩn');
INSERT INTO TheLoai VALUES (N'', N'Văn học', 13, N'Sách thuộc về lĩnh vực nghệ thuật sử dụng ngôn ngữ để thể hiện những ý nghĩa, cảm xúc, suy nghĩ');
INSERT INTO TheLoai VALUES (N'', N'Thơ', 3, N'Tác phẩm văn học sử dụng những từ ngữ, âm điệu, nhịp điệu, hình ảnh hoặc biểu tượng');
INSERT INTO TheLoai VALUES (N'', N'Thần thoại', 1, N'Sách kể về những truyền thuyết, thần thoại, thần tiên, thần linh hoặc những sự kiện siêu nhiên');

go
insert KhachHang (tenKhachHang,soDienThoai,email,diaChi,ngaySinh,gioiTinh) values (N'Hữu Vinh',N'0918255167',N'vinhpham123@gmail.com',N'320D phường 7, TP.Bến Tre','2003-01-09',1)
insert KhachHang (tenKhachHang,soDienThoai,email,diaChi,ngaySinh,gioiTinh) values (N'Vũ Duy','0918288167','duy123@gmail.com','Phan Rang','2003-06-27',1)
insert KhachHang (tenKhachHang,soDienThoai,email,diaChi,ngaySinh,gioiTinh) values (N'Tấn Đạt','0929255167','datpham@gmail.com',N'Tiền Giang','2003-04-13',1)
insert KhachHang (tenKhachHang,soDienThoai,email,diaChi,ngaySinh,gioiTinh) values (N'Văn Hoàng','0918255167','hoangle@gmail.com',N'Long Thành,Đồng Nai','2003-01-09',1)
insert KhachHang (tenKhachHang,soDienThoai,email,diaChi,ngaySinh,gioiTinh) values (N'Bảo Trinh','0379121672','trinhxinhdep@gmail.com',N'Bình Định','2003-11-17',0)
insert KhachHang (tenKhachHang,soDienThoai,email,diaChi,ngaySinh,gioiTinh) values (N'Hồng Luyên','0976321697','luyenxinh@gmail.com',N'Bình Định','2003-09-29',0)
insert KhachHang (tenKhachHang,soDienThoai,email,diaChi,ngaySinh,gioiTinh) values (N'Hồng Lưu','0783126567','luuxinh@gmail.com',N'Bình Định','2003-09-29',0)
insert KhachHang (tenKhachHang,soDienThoai,email,diaChi,ngaySinh,gioiTinh) values (N'Phước Hậu','0783454367','haungo@gmail.com',N'Bến Tre','2003-11-21',1)
insert KhachHang (tenKhachHang,soDienThoai,email,diaChi,ngaySinh,gioiTinh) values (N'Đức Minh','0776754367','minhcute@gmail.com',N'Bến Tre','2003-01-21',1)
insert KhachHang (tenKhachHang,soDienThoai,email,diaChi,ngaySinh,gioiTinh) values (N'Thái Công','0989891437','congthai@gmail.com',N'Bến Tre','2003-07-23',1)
insert KhachHang (tenKhachHang,soDienThoai,email,diaChi,ngaySinh,gioiTinh) values (N'Lan Anh','0778884367','lananh25@gmail.com',N'Bến Tre','2003-11-25',0)
insert KhachHang (tenKhachHang,soDienThoai,email,diaChi,ngaySinh,gioiTinh) values (N'Mỹ Dung','0765754367','dungxd@gmail.com',N'Bến Tre','2003-02-06',0)
insert KhachHang (tenKhachHang,soDienThoai,email,diaChi,ngaySinh,gioiTinh) values (N'Thanh Thảo','0744454367','thaodep@gmail.com',N'Kon Tum','2002-02-05',0)
insert KhachHang (tenKhachHang,soDienThoai,email,diaChi,ngaySinh,gioiTinh) values (N'Duy Thống',N'0917755167',N'thongtruong123@gmail.com',N'329 phường 8, TP.Bến Tre','2003-08-17',1)
insert KhachHang (tenKhachHang,soDienThoai,email,diaChi,ngaySinh,gioiTinh) values (N'Thành Đạt','0918987867','datcool@gmail.com',N'Bến Tre','2003-06-27',1)
insert KhachHang (tenKhachHang,soDienThoai,email,diaChi,ngaySinh,gioiTinh) values (N'Tường Vy','0989255167','vynguyen@gmail.com',N'Bến Tre','2003-11-13',0)
insert KhachHang (tenKhachHang,soDienThoai,email,diaChi,ngaySinh,gioiTinh) values (N'Mỹ Hạnh','0918257767','myhanh@gmail.com',N'222 phương 7, Bến Tre','2003-01-18',0)
insert KhachHang (tenKhachHang,soDienThoai,email,diaChi,ngaySinh,gioiTinh) values (N'Cát Tường','0879121672','cattuong@gmail.com',N'Bến Tre','2003-05-28',0)
insert KhachHang (tenKhachHang,soDienThoai,email,diaChi,ngaySinh,gioiTinh) values (N'Tuyết Nhung','0976321688','tuyenxinh@gmail.com',N'Hà Nội','2003-09-29',0)
insert KhachHang (tenKhachHang,soDienThoai,email,diaChi,ngaySinh,gioiTinh) values (N'Yến Vân','0783776567','yenvan@gmail.com',N'250 phương 7, TP.Bến Tre','2003-09-19',0)
insert KhachHang (tenKhachHang,soDienThoai,email,diaChi,ngaySinh,gioiTinh) values (N'Tuấn Duy','0783455367','duy23@gmail.com',N'Bến Tre','2003-11-20',1)
insert KhachHang (tenKhachHang,soDienThoai,email,diaChi,ngaySinh,gioiTinh) values (N'Minh Khang','0746754367','minhkhang@gmail.com',N'Bến Tre','2003-01-16',1)
insert KhachHang (tenKhachHang,soDienThoai,email,diaChi,ngaySinh,gioiTinh) values (N'Duy Thuận','0989891777','dt@gmail.com',N'Bến Tre','2003-01-16',1)
insert KhachHang (tenKhachHang,soDienThoai,email,diaChi,ngaySinh,gioiTinh) values (N'Mỹ Vy','0778810367','vydep@gmail.com',N'Bến Tre','2003-11-25',0)
insert KhachHang (tenKhachHang,soDienThoai,email,diaChi,ngaySinh,gioiTinh) values (N'Tường Vân','0765754797','tv@gmail.com',N'Bến Tre','2003-02-06',0)
insert KhachHang (tenKhachHang,soDienThoai,email,diaChi,ngaySinh,gioiTinh) values (N'Quốc Tuấn','0744454355','tuan@gmail.com',N'Kon Tum','2002-02-05',1)
insert KhachHang (tenKhachHang,soDienThoai,email,diaChi,ngaySinh,gioiTinh) values (N'Minh Nhựt','0744554367','minhnhutg@gmail.com',N'Bến Tre','2003-01-26',1)
insert KhachHang (tenKhachHang,soDienThoai,email,diaChi,ngaySinh,gioiTinh) values (N'Thanh Nhiên','0989891789','tn@gmail.com',N'Bến Tre','2003-01-16',1)
insert KhachHang (tenKhachHang,soDienThoai,email,diaChi,ngaySinh,gioiTinh) values (N'Phương Quyên','0773303675','quyen@gmail.com',N'Bến Tre','2003-01-01',0)
insert KhachHang (tenKhachHang,soDienThoai,email,diaChi,ngaySinh,gioiTinh) values (N'Ngọc Châu','0765754740','chau@gmail.com',N'Cần Thơ','2003-05-06',0)
insert KhachHang (tenKhachHang,soDienThoai,email,diaChi,ngaySinh,gioiTinh) values (N'Chí Khang','0744454355','khang@gmail.com',N'Kon Tum','2002-08-20',1)
insert KhachHang  (tenKhachHang,soDienThoai,email,diaChi,ngaySinh,gioiTinh) values (N'Quang Vinh','0355430475','vinh@mail',N'125 Bùi Đình Túy, TP.HCM','2023/01/09',1)
insert KhachHang  (tenKhachHang,soDienThoai,email,diaChi,ngaySinh,gioiTinh) values (N'Hoàng Anh','0355430754','hoang123@mail',N'Bình Quới, Bến Tre','2023/01/04',1)



--INSERT INTO Sach VALUES (N'S202311130001', N'Chút gió thoáng qua', N'TG202311230001', N'TL202311230001', '2022-01-01', N'978-123-123-123-2', 200, N'LSP202311230001', N'NCC202311230001', 15.5, N'Nâu', 1, 2500, 22, 500000, 800000,800000);
--INSERT INTO Sach VALUES (N'S202311130002', N'Cây cam ngọt ngào', N'TG202311230001', N'TL202311230001', '2022-01-01', N'978-123-123-123-2', 200, N'LSP202311230003', N'NCC202311230003', 15.5, N'Nâu', 1, 2500, 100, 700000, 1120000,1120000);
--go

--INSERT INTO SanPham VALUES (N'SP202311130001', N'Sản phẩm A', N'LSP202311230001', N'NCC202311230001', 10.0, N'Đỏ', 1, 0, 100000, 50, 160000, 160000);
--INSERT INTO SanPham VALUES (N'SP202311130002', N'Sản phẩm B', N'LSP202311230002', N'NCC202311230002', 12.5, N'Xanh', 1, 0, 120000, 30, 192000, 192000);
--INSERT INTO SanPham VALUES (N'SP202311130003', N'Sản phẩm C', N'LSP202311230003', N'NCC202311230003', 8.0, N'Vàng', 1, 0, 80000, 70, 128000, 128000);
--INSERT INTO SanPham VALUES (N'SP202311130004', N'Sản phẩm D', N'LSP202311230001', N'NCC202311230002', 15.0, N'Đen', 1, 0, 150000, 40, 240000, 240000);
--INSERT INTO SanPham VALUES (N'SP202311130005', N'Sản phẩm E', N'LSP202311230002', N'NCC202311230003', 9.5, N'Hồng', 1, 0, 95000, 60, 152000, 152000);
go
insert TaiKhoan values(N'QL202311300002',N'1111',N'2023-11-30')




--insert TaiKhoan values('ADMIN','1111','2023-11-13')
insert TaiKhoan values('ADMIN',HASHBYTES('SHA2_512', '1111'),'2023-10-13')
select *from NhanVien
select *from TaiKhoan
go
--QL202312120001
--NV202312090001
select *from TheLoai
select *from NhaCungCap
select *from LoaiSanPham
select *from Sach
select *from TacGia
select *from TaiKhoan
select *from NhanVien
select *from HoaDon
select *from SanPham
select *from ChiTietHoaDonSanPham



