package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import dao.DAOLoaiSanPham;
import dao.DAONhaCungCap;
import dao.DAOQuanLySanPham;

public class QuanLySachView extends JPanel {
	private JTabbedPane tabbedPane;
	
	
	private JLabel lblTieuDe, lblIDSanPham, lblTinhTrangKinhDoanh;
	private JTable sanPhamTable;
	private JPanel pnCenter, pnChucNang, pnDanhMuc, pnMain;
	private JButton btnThemSP, btnXoaSP, btnNhapSP, btnCapNhatSP, btnHienThiLS, btnLamMoi;
	private JLabel lblTuKhoa;
	private JTextField txtTuKhoa;
	private JButton btnTimKiem;
	private JButton btnXemTatCa;
	private JPanel pnChucNangCha;
	private JPanel pnChucNangTimKiem;
	
	
	private JPanel pnHeading;
	private JLabel lbltitle;
	private JPanel pnMainn;

	private JTable tableSP;
	private DefaultTableModel modelSP;
	
	private JLabel lblIdSanPham;
	private JLabel lblTenSanPham;
	private JLabel lblTacGia;
	private JLabel lblTheLoai;
	private JLabel lblNamXuatBan;
	private JLabel lblISBN;
	private JLabel lblSoTrang;
	private JLabel lblLoaiSanPham;
	private JLabel lblNhaCungCap;
	private JLabel lblKichThuoc;
	private JLabel lblMauSac;
	private JLabel lblTrangThai;
	private JLabel lblGiaNhap;
	private JLabel lblGiaMoi;
	private JLabel lblSoLuong;
	private JLabel lblGiaBan;
	private JLabel lblThueVAT;
	private JLabel lblLoaiSanPhamSearch;
	private JLabel lblNhaCungCapSearch;
	private JLabel lblTacGiaSearch;
	private JLabel lblTheLoaiSearch;

	private JTextField txtTenSanPham;
	private JTextField txtIdSanPham;
	private JTextField txtISBN;
	private JTextField txtSoTrang;
	private JTextField txtKichThuoc;
	private JTextField txtMauSac;
	private JTextField txtGiaNhap;
	private JTextField txtGiaMoi;
	private JTextField txtSoLuong;
	private JTextField txtGiaBan;
	
	private JComboBox<String> cbLoaiTacGia, cbLoaiTheLoai;
	private JComboBox<String> cbLoaiSanPhamSearch;
	private JComboBox<String> cbNhaCungCapSearch;
	private JComboBox<String> cbTacGiaSearch;
	private JComboBox<String> cbTheLoaiSearch;
	private JComboBox<String> cbTinhTrangKhinhDoanh;
	private JComboBox<String> cbLoaiSanPham;
	private JComboBox<String> cbNhaCungCap;
	
	private JCheckBox chkTinhTrangKinhDoanh;
	
	private JDateChooser chooserXuatBan;

	public QuanLySachView() {
		init();
	}

	private void init() {
		setLayout(new BorderLayout());

		// center
		pnCenter = new JPanel();
		pnCenter.setLayout(new GridLayout(5, 6, 10, 10));
		pnCenter.setBorder(BorderFactory.createTitledBorder("Thông tin sản phẩm"));

		lblIDSanPham = new JLabel("ID Sản Phẩm(*):");
		txtIdSanPham = new JTextField();
//				txtIdSanPham.setText(generateNewProductID());
		txtIdSanPham.setEditable(false);
		lblTenSanPham = new JLabel("Tên Sản Phẩm(*):");
		txtTenSanPham = new JTextField();
		lblTacGia = new JLabel("Tác Giả:");
		cbLoaiTacGia = new JComboBox<>();
		lblTheLoai = new JLabel("Thể Loại:");
		cbLoaiTheLoai = new JComboBox<>();
		lblNamXuatBan = new JLabel("Năm xuất bản:");
		chooserXuatBan = new JDateChooser();
		chooserXuatBan.setPreferredSize(new Dimension(200, 22));
		chooserXuatBan.getCalendarButton().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chooserXuatBan.setDateFormatString("dd/MM/yyyy");
		chooserXuatBan.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		chooserXuatBan.setFont(new Font("SansSerif", Font.PLAIN, 15));
		chooserXuatBan.getCalendarButton().setPreferredSize(new Dimension(30, 24));
		chooserXuatBan.getCalendarButton().setBackground(new Color(102, 0, 153));
		chooserXuatBan.getCalendarButton().setToolTipText("Chọn ngày xuất bản");
		lblISBN = new JLabel("ISBN:");
		txtISBN = new JTextField();
		lblSoTrang = new JLabel("Số Trang:");
		txtSoTrang = new JTextField();
		lblLoaiSanPham = new JLabel("Loại Sản Phẩm:");
		cbLoaiSanPham = new JComboBox<>();
		lblNhaCungCap = new JLabel("Nhà Cung Cấp:");
		cbNhaCungCap = new JComboBox<>();
		lblKichThuoc = new JLabel("Kích Thước:");
		txtKichThuoc = new JTextField();
		lblMauSac = new JLabel("Màu Sắc:");
		txtMauSac = new JTextField();
		lblTrangThai = new JLabel("Tình Trạng Kinh Doanh:");
		chkTinhTrangKinhDoanh = new JCheckBox();
		lblSoLuong = new JLabel("Số Lượng:");
		txtSoLuong = new JTextField();
		lblGiaNhap = new JLabel("Giá Nhập:");
		txtGiaNhap = new JTextField();
		lblGiaBan = new JLabel("Giá Bán:");
		txtGiaBan = new JTextField();
		txtGiaBan.setEditable(false);

		pnCenter.add(lblIDSanPham);
		pnCenter.add(txtIdSanPham);
		
		pnCenter.add(lblTenSanPham);
		pnCenter.add(txtTenSanPham);
		
		pnCenter.add(lblNamXuatBan);
		pnCenter.add(chooserXuatBan);
		
		pnCenter.add(lblTacGia);
		pnCenter.add(cbLoaiTacGia);

		pnCenter.add(lblISBN);
		pnCenter.add(txtISBN);
		
		pnCenter.add(lblSoTrang);
		pnCenter.add(txtSoTrang);

		pnCenter.add(lblTheLoai);
		pnCenter.add(cbLoaiTheLoai);

		pnCenter.add(lblKichThuoc);
		pnCenter.add(txtKichThuoc);

		pnCenter.add(lblMauSac);
		pnCenter.add(txtMauSac);
		
		pnCenter.add(lblLoaiSanPham);
		pnCenter.add(cbLoaiSanPham);
		
		
		pnCenter.add(lblGiaNhap);
		pnCenter.add(txtGiaNhap);
		
		pnCenter.add(lblSoLuong);
		pnCenter.add(txtSoLuong);
		
		
		pnCenter.add(lblNhaCungCap);
		pnCenter.add(cbNhaCungCap);
		
		pnCenter.add(lblGiaBan);
		pnCenter.add(txtGiaBan);
		
		pnCenter.add(lblTrangThai);
		pnCenter.add(chkTinhTrangKinhDoanh);

		pnMain = new JPanel(new BorderLayout());

		pnChucNangCha = new JPanel(new BorderLayout(6, 8));
		pnChucNang = new JPanel(new GridLayout(1, 4, 10, 40));
		pnChucNangCha.setBorder(BorderFactory.createTitledBorder("Chức năng"));
		btnThemSP = new JButton("THÊM SÁCH");
		btnCapNhatSP = new JButton("CẬP NHẬT SÁCH");
		btnXoaSP = new JButton("XÓA SÁCH");
		lblTuKhoa = new JLabel("Tìm kiếm:");
		txtTuKhoa = new JTextField();
		btnTimKiem = new JButton("Tìm kiếm");
		btnXemTatCa = new JButton("Xem tất cả");
		btnLamMoi = new JButton("LÀM MỚI");

		pnChucNang.add(btnThemSP);
		pnChucNang.add(btnCapNhatSP);
		pnChucNang.add(btnXoaSP);
		pnChucNang.add(btnLamMoi);

		pnChucNangTimKiem = new JPanel(new GridLayout(1, 8, 10, 10));
		lblLoaiSanPhamSearch = new JLabel("Loại sản phẩm:");
		lblNhaCungCapSearch = new JLabel("Nhà cung cấp:");
		lblTacGiaSearch = new JLabel("Tác giả:");
		lblTheLoaiSearch = new JLabel("Thể loại:");
		cbLoaiSanPhamSearch = new JComboBox<>();
		cbNhaCungCapSearch = new JComboBox<>();
		cbTacGiaSearch = new JComboBox<>();
		cbTheLoaiSearch = new JComboBox<>();
		
		pnChucNangTimKiem.add(lblLoaiSanPhamSearch);
		pnChucNangTimKiem.add(cbLoaiSanPhamSearch);
		pnChucNangTimKiem.add(lblNhaCungCapSearch);
		pnChucNangTimKiem.add(cbNhaCungCapSearch);
		pnChucNangTimKiem.add(lblTacGiaSearch);
		pnChucNangTimKiem.add(cbTacGiaSearch);
		pnChucNangTimKiem.add(lblTheLoaiSearch);
		pnChucNangTimKiem.add(cbTheLoaiSearch);
		pnChucNangTimKiem.add(lblTuKhoa);
		pnChucNangTimKiem.add(txtTuKhoa);
		pnChucNangTimKiem.add(btnXemTatCa);

		pnChucNangCha.add(pnChucNang, BorderLayout.NORTH);
		pnChucNangCha.add(pnChucNangTimKiem, BorderLayout.SOUTH);
		pnMain.add(pnChucNangCha, BorderLayout.NORTH);

		pnDanhMuc = new JPanel(new BorderLayout());
		pnDanhMuc.setBorder(BorderFactory.createTitledBorder("Danh mục"));
		modelSP = new DefaultTableModel();
		tableSP = new JTable();
		modelSP.addColumn("ID Sách");
		modelSP.addColumn("Tên Sách");
		modelSP.addColumn("Tác Giả");
		modelSP.addColumn("Thể Loại");
		modelSP.addColumn("Năm Xuất Bản");
		modelSP.addColumn("ISBN");
		modelSP.addColumn("Số Trang");
		modelSP.addColumn("Loại Sản Phẩm");
		modelSP.addColumn("Nhà Cung Cấp");
		modelSP.addColumn("Kích Thước");
		modelSP.addColumn("Màu Sắc");
		modelSP.addColumn("Trạng Thái");
		modelSP.addColumn("Thuế");
		modelSP.addColumn("Số Lượng");
		modelSP.addColumn("Giá Nhập");
		modelSP.addColumn("Giá Bán");
		tableSP.setModel(modelSP);
		JScrollPane scrollPane = new JScrollPane(tableSP);
		pnDanhMuc.add(scrollPane);
		pnMain.add(pnDanhMuc, BorderLayout.CENTER);

		add(pnCenter, BorderLayout.NORTH);
		add(pnMain, BorderLayout.CENTER);
	}
}
