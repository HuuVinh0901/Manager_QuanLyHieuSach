package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import dao.DAOSach;
import dao.DAOTacGia;
import dao.DAOTheLoai;
import models.LoaiSanPham;
import models.NhaCungCap;
import models.SachCon;
import models.SanPhamCha;
import models.TacGia;
import models.TheLoai;
import utils.TrangThaiSPEnum;

public class QuanLySachView extends JPanel implements ActionListener, MouseListener, KeyListener {
	private JTabbedPane tabbedPane;

	private JLabel lblTieuDe, lblIDSanPham, lblTinhTrangKinhDoanh;
	private JTable sanPhamTable;
	private JPanel pnCenter, pnChucNang, pnDanhMuc, pnMain;
	private JButton btnThemSP, btnXoaSP, btnCapNhatSP, btnLamMoi;
	private JLabel lblTuKhoa;
	private JTextField txtTuKhoa;
	private JButton btnTimKiem;
	private JButton btnXemTatCa;
	private JPanel pnChucNangCha;
	private JPanel pnChucNangTimKiem;

	private JPanel pnHeading;
	private JLabel lbltitle;
	private JPanel pnMainn;

	private JTable table;
	private DefaultTableModel model;

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
	private SimpleDateFormat dfNgaySinh;
	private JDateChooser chooserXuatBan;

	private TrangThaiSPEnum trangThai;
	private DAOLoaiSanPham daoLoaiSanPham;
	private DAONhaCungCap daoNhaCungCap;
	private DAOTheLoai daoTheLoai;
	private DAOTacGia daoTacGia;
	private DAOSach daoSach;

	public QuanLySachView() {
		init();
		initComponent();
		loadData();
		loadComboBoxByLoaiSanPham();
		loadComboxBoxByNhaCungCap();
		loadComboBoxByTheLoai();
		loadComBoBoxByTacGia();
	}

	private void initComponent() {
		daoLoaiSanPham = new DAOLoaiSanPham();
		daoNhaCungCap = new DAONhaCungCap();
		daoTheLoai = new DAOTheLoai();
		daoTacGia = new DAOTacGia();
		daoSach = new DAOSach();

		table = new JTable();
		model = new DefaultTableModel();

		dfNgaySinh = new SimpleDateFormat("dd/MM/yyyy");

		btnCapNhatSP.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnThemSP.addActionListener(this);
		btnTimKiem.addActionListener(this);
		btnXemTatCa.addActionListener(this);
		btnXoaSP.addActionListener(this);
		table.addMouseListener(this);

	}

	private void loadComboBoxByTheLoai() {
		ArrayList<TheLoai> dstheLoai;
		try {
			dstheLoai = daoTheLoai.getAllTheLoai();
			cbLoaiTheLoai.removeAllItems();
			cbTheLoaiSearch.addItem("Tất cả");
			for (TheLoai tl : dstheLoai) {
				cbLoaiTheLoai.addItem(tl.getTenTheLoai());
				cbTheLoaiSearch.addItem(tl.getTenTheLoai());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void loadComBoBoxByTacGia() {
		try {
			ArrayList<TacGia> dsTacGia = daoTacGia.getAllTacGia();
			cbLoaiTacGia.removeAllItems();
			cbTacGiaSearch.addItem("Tất cả");
			for (TacGia tg : dsTacGia) {
				cbLoaiTacGia.addItem(tg.getTenTacGia());
				cbTacGiaSearch.addItem(tg.getTenTacGia());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void loadComboBoxByLoaiSanPham() {
		ArrayList<LoaiSanPham> danhSachLoaiSanPham = daoLoaiSanPham.getAllLoaiSanPham();
		cbLoaiSanPham.removeAllItems();
		cbLoaiSanPhamSearch.addItem("Tất cả");
		for (LoaiSanPham loaiSanPham : danhSachLoaiSanPham) {
			cbLoaiSanPham.addItem(loaiSanPham.getTenLoaiSanPham());
			cbLoaiSanPhamSearch.addItem(loaiSanPham.getTenLoaiSanPham());
		}
	}

	private void loadComboxBoxByNhaCungCap() {
		ArrayList<NhaCungCap> danhSachNhaCungCap = daoNhaCungCap.getAllNhaCungCap();
		cbNhaCungCap.removeAllItems();
		cbNhaCungCapSearch.addItem("Tất cả");
		for (NhaCungCap ncc : danhSachNhaCungCap) {
			cbNhaCungCap.addItem(ncc.getTenNhaCungCap());
			cbNhaCungCapSearch.addItem(ncc.getTenNhaCungCap());
		}
	}

	private String generateNewProductID() {
		String idPrefix = "SP";
		int newProductIDNumber = 1;
		String currentDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
		try {
			daoSach = new DAOSach();
			String previousProductID = daoSach.getLatestProductID();
			if (previousProductID != null && !previousProductID.isEmpty()) {
				int oldProductIDNumber = Integer.parseInt(previousProductID.substring(10));
				newProductIDNumber = oldProductIDNumber + 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		String newProductID = idPrefix + currentDate + String.format("%04d", newProductIDNumber);
		return newProductID;
	}

	private void loadData() {
		model.setRowCount(0);
		try {
			ArrayList<SachCon> dsSach = daoSach.getAllSachLoadData();
			for (SachCon s : dsSach) {
				String idSanPham = s.getIdSanPham();
				String tenSanPham = s.getTenSanPham();
				String tenTacGia = s.getTacGia().getIdTacGia();
				String tenTheLoai = s.getTheLoai().getIdTheLoai();
				String ISBN = s.getISBN();
				int soTrang = s.getSoTrang();
				String loaiSanPham = s.getIdLoaiSanPham().getIdLoaiSanPham();
				String nhaCungCap = s.getIdNhaCungCap().getIdNhaCungCap();
				double kichThuoc = s.getKichThuoc();
				String mauSac = s.getMauSac();
				String trangThai = s.getTrangThai().getDescription();
				int soLuong = s.getSoLuong();
				double giaNhap = s.getGiaNhap();
				double giaBan = s.giaBan();

				model.addRow(new Object[] { idSanPham, tenSanPham, tenTacGia, tenTheLoai,
						dfNgaySinh.format(s.getNamXuatBan()), ISBN, soTrang, loaiSanPham, nhaCungCap, kichThuoc, mauSac,
						trangThai, soLuong, giaNhap, giaBan });
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void init() {
		setLayout(new BorderLayout());
		// center
		pnCenter = new JPanel();
		pnCenter.setLayout(new GridLayout(5, 6, 10, 10));
		pnCenter.setBorder(BorderFactory.createTitledBorder("Thông tin sản phẩm"));

		lblIDSanPham = new JLabel("ID Sản Phẩm(*):");
		txtIdSanPham = new JTextField();
		txtIdSanPham.setText(generateNewProductID());
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
		model = new DefaultTableModel();
		table = new JTable();
		model.addColumn("ID Sách");
		model.addColumn("Tên Sách");
		model.addColumn("Tác Giả");
		model.addColumn("Thể Loại");
		model.addColumn("Năm Xuất Bản");
		model.addColumn("ISBN");
		model.addColumn("Số Trang");
		model.addColumn("Loại Sản Phẩm");
		model.addColumn("Nhà Cung Cấp");
		model.addColumn("Kích Thước");
		model.addColumn("Màu Sắc");
		model.addColumn("Trạng Thái");
		model.addColumn("Thuế");
		model.addColumn("Số Lượng");
		model.addColumn("Giá Nhập");
		model.addColumn("Giá Bán");
		table.setModel(model);
		JScrollPane scrollPane = new JScrollPane(table);
		pnDanhMuc.add(scrollPane);
		pnMain.add(pnDanhMuc, BorderLayout.CENTER);

		add(pnCenter, BorderLayout.NORTH);
		add(pnMain, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnLamMoi)) {
			lamMoi();
		} else if (o.equals(btnThemSP)) {
			themSach();
		}

	}

	private void themSach() {
		String idSanPham = txtIdSanPham.getText();
		String tenSanPham = txtTenSanPham.getText();
		Date namXuatBan = chooserXuatBan.getDate();
		String ISBN = txtISBN.getText();
		int soTrang = Integer.parseInt(txtSoTrang.getText());
		String tenLoaiSanPham = (String) cbLoaiSanPham.getSelectedItem();
		String tenNhaCungCap = (String) cbNhaCungCap.getSelectedItem();
		String tacGia = (String) cbLoaiTacGia.getSelectedItem();
		String theLoai = (String) cbLoaiTheLoai.getSelectedItem();
		double kichThuoc = Double.parseDouble(txtKichThuoc.getText());
		String mauSac = txtMauSac.getText();
		boolean trangThaiValue = chkTinhTrangKinhDoanh.isSelected();
		TrangThaiSPEnum trangThai = trangThaiValue ? TrangThaiSPEnum.DANG_KINH_DOANH : TrangThaiSPEnum.NGUNG_KINH_DOANH;
		int soLuong = Integer.parseInt(txtSoLuong.getText());
		double giaNhap = Double.parseDouble(txtGiaNhap.getText());
		java.util.Date date = chooserXuatBan.getDate();
		java.sql.Date namXuatBans = new java.sql.Date(date.getYear(), date.getMonth(), date.getDay());
		LoaiSanPham loaiSanPham = null;
		for (LoaiSanPham item : daoLoaiSanPham.getAllLoaiSanPham()) {
			if (item.getTenLoaiSanPham().equals(tenLoaiSanPham)) {
				loaiSanPham = item;
				break;
			}
		}

		NhaCungCap nhaCungCap = null;
		for (NhaCungCap item : daoNhaCungCap.getAllNhaCungCap()) {
			if (item.getTenNhaCungCap().equals(tenNhaCungCap)) {
				nhaCungCap = item;
				break;
			}
		}

		TacGia tenTacGias = null;
		try {
			for (TacGia item : daoTacGia.getAllTacGia()) {
				if (item.getTenTacGia().equals(tacGia)) {
					tenTacGias = item;
					break;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		TheLoai theLoais = null;
		try {
			for (TheLoai item : daoTheLoai.getAllTheLoai()) {
				if (item.getTenTheLoai().equals(theLoai)) {
					theLoais = item;
					break;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (loaiSanPham != null && nhaCungCap != null && theLoais != null && tenTacGias != null) {
			SachCon s = new SachCon();
			s.setIdSanPham(idSanPham);
			s.setTenSanPham(tenSanPham);
			s.setTacGia(tenTacGias);
			s.setTheLoai(theLoais);
			s.setNamXuatBan(namXuatBans);
			s.setISBN(ISBN);
			s.setSoTrang(soTrang);
			s.setIdLoaiSanPham(loaiSanPham);
			s.setIdNhaCungCap(nhaCungCap);
			s.setKichThuoc(kichThuoc);
			s.setMauSac(mauSac);
			s.setTrangThai(trangThai);
			s.setSoLuong(soLuong);
			s.setGiaNhap(giaNhap);
			
			boolean kiemTra;
			try {
				kiemTra = daoSach.themSach(s);
				if (kiemTra) {
					model.addRow(new Object[] { idSanPham, tenSanPham, tenTacGias, theLoais, namXuatBans, ISBN, soTrang, loaiSanPham, nhaCungCap, kichThuoc, mauSac, trangThai,s.thue(), soLuong, giaNhap,s.giaBan()});
					loadData();
					JOptionPane.showMessageDialog(this, "Thêm thông tin thành công");
					lamMoi();
				} else {
					JOptionPane.showMessageDialog(this, "Lỗi khi thêm thông tin");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private void lamMoi() {
		txtIdSanPham.setText(generateNewProductID());
		txtTenSanPham.setText("");
		cbLoaiTacGia.setSelectedIndex(0);
		cbLoaiTheLoai.setSelectedIndex(0);
		chooserXuatBan.setDate(null);
		txtISBN.setText("");
		txtSoTrang.setText("");
		cbLoaiSanPham.setSelectedIndex(0);
		cbNhaCungCap.setSelectedIndex(0);
		txtKichThuoc.setText("");
		txtMauSac.setText("");
		chkTinhTrangKinhDoanh.setSelected(trangThai.DANG_KINH_DOANH == trangThai.DANG_KINH_DOANH);
		txtSoLuong.setText("");
		txtGiaNhap.setText("");
		txtGiaBan.setText("");
		txtTenSanPham.requestFocus();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
