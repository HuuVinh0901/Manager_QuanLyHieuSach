package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Currency;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.BadLocationException;
import javax.swing.text.MaskFormatter;

import org.apache.logging.log4j.core.tools.picocli.CommandLine.Help.TextTable.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import models.SanPhamCon;
import models.TacGia;
import models.TheLoai;
import utils.TrangThaiSPEnum;

public class QuanLySachView extends JPanel
		implements ActionListener, MouseListener, KeyListener, ItemListener, DocumentListener, ListSelectionListener {
	private JTabbedPane tabbedPane;
	private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
	private JLabel lblTieuDe, lblIDSanPham, lblTinhTrangKinhDoanh;
	private JTable sanPhamTable;
	private JPanel pnCenter, pnChucNang, pnDanhMuc, pnMain;
	private JButton btnThemSP, btnXoaSP, btnCapNhatSP, btnLamMoi;
	private JLabel lblTuKhoa;
	private JTextField txtTuKhoa;
	private JButton btnTimKiem;
	private JButton btnXuatExCel;
	private JButton btnXemTatCa;
	private JButton btnThemNhieuSach;
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
//	private JTextField txtISBN;
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
	private NumberFormat currencyVN;
	private JDateChooser chooserXuatBan;

	private MaskFormatter formatter;
	JFormattedTextField isbnField;

	private TrangThaiSPEnum trangThai;
	private DAOLoaiSanPham daoLoaiSanPham;
	private DAONhaCungCap daoNhaCungCap;
	private DAOTheLoai daoTheLoai;
	private DAOTacGia daoTacGia;
	private DAOSach daoSach;
	private DecimalFormat df;

	public QuanLySachView() {
		init();

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
		String idPrefix = "S";
		int newProductIDNumber = 1;
		String currentDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
		try {
			daoSach = new DAOSach();
			String previousProductID = daoSach.getLatestProductID();
			if (previousProductID != null && !previousProductID.isEmpty()) {
				int oldProductIDNumber = Integer.parseInt(previousProductID.substring(9));
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
		ArrayList<SachCon> dsSach = daoSach.getAllSachLoadData();
		for (SachCon s : dsSach) {
			double thuePhanTram = s.thue() * 100;
			currencyVN = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
			df = new DecimalFormat("#,###.##");
			String idSanPham = s.getIdSanPham();
			String tenSanPham = s.getTenSanPham();
			String tenTacGia = s.getTacGia().getIdTacGia();
			String tenTheLoai = s.getTheLoai().getIdTheLoai();
			java.sql.Date Date = s.getNamXuatBan();
			String isbn = s.getISBN();
			int soTrang = s.getSoTrang();
			String loaiSanPham = s.getIdLoaiSanPham().getIdLoaiSanPham();
			String nhaCungCap = s.getIdNhaCungCap().getIdNhaCungCap();
			double kichThuoc = s.getKichThuoc();
			String mauSac = s.getMauSac();
			String trangThai = s.getTrangThai() + "";
			double thue = s.thue();
			int soLuong = s.getSoLuong();
			double giaNhap = s.getGiaNhap();
			double giaBan = s.giaBan();
//			String[] row = { s.getIdSanPham(), s.getTenSanPham(), tenTacGia, tenTheLoai,
//					dfNgaySinh.format(s.getNamXuatBan()), s.getISBN(), s.getSoTrang() + "", loaiSanPham, nhaCungCap,
//					s.getKichThuoc() + "", s.getMauSac(), s.getTrangThai() + "", s.thue() + "", s.getSoLuong() + "",
//					s.getGiaNhap() + "", s.giaBan() + "" };
			model.addRow(new Object[] { idSanPham, tenSanPham, tenTacGia, tenTheLoai,
					dfNgaySinh.format(s.getNamXuatBan()), isbn, soTrang, loaiSanPham, nhaCungCap, kichThuoc, mauSac,
					trangThai, currencyFormat.format(thue), soLuong, currencyFormat.format(giaNhap),
					currencyFormat.format(giaBan) });
		}

	}

	private void init() {
		setLayout(new BorderLayout());

		dfNgaySinh = new SimpleDateFormat("dd/MM/yyyy");
		df = new DecimalFormat("#,###.##");
		daoSach = new DAOSach();
		daoLoaiSanPham = new DAOLoaiSanPham();
		daoNhaCungCap = new DAONhaCungCap();
		daoTheLoai = new DAOTheLoai();
		daoTacGia = new DAOTacGia();
		currencyFormat.setCurrency(Currency.getInstance("VND"));
		table = new JTable();
		model = new DefaultTableModel();

		dfNgaySinh = new SimpleDateFormat("dd/MM/yyyy");

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

		try {
			formatter = new MaskFormatter("###-###-###-###-#");
			formatter.setPlaceholderCharacter('X');
			isbnField = new JFormattedTextField(formatter);
			isbnField.setColumns(17);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lblISBN = new JLabel("ISBN:");
//		txtISBN = new JTextField();

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
		pnCenter.add(isbnField);

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
		btnThemNhieuSach = new JButton("NHẬP SÁCH EXCEL ");

		btnXuatExCel = new JButton("XUẤT EXCEL");
//		btnXuatExCel.setEnabled(false);

		ImageIcon iconThem = new ImageIcon(getClass().getResource("/icons/add.png"));
		ImageIcon iconCapNhat = new ImageIcon(getClass().getResource("/icons/capnhat.png"));
		ImageIcon iconLamMoi = new ImageIcon(getClass().getResource("/icons/lammoi.png"));
		ImageIcon iconXoa = new ImageIcon(getClass().getResource("/icons/xoa.png"));

		btnCapNhatSP.setIcon(iconCapNhat);
		btnThemSP.setIcon(iconThem);
		btnLamMoi.setIcon(iconLamMoi);
		btnXoaSP.setIcon(iconXoa);

//		ImageIcon iconExcel = new ImageIcon(getClass().getResource("/icons/Excel.png"));

		btnThemSP.setIcon(iconThem);
		btnLamMoi.setIcon(iconLamMoi);
		btnCapNhatSP.setIcon(iconCapNhat);
		btnXoaSP.setIcon(iconXoa);
//		btnXuatExCel.setIcon(iconExcel);

		pnChucNang.add(btnThemSP);
		pnChucNang.add(btnThemNhieuSach);
		pnChucNang.add(btnCapNhatSP);
		pnChucNang.add(btnLamMoi);
		pnChucNang.add(btnXoaSP);
		pnChucNang.add(btnXuatExCel);

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
		btnCapNhatSP.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnThemSP.addActionListener(this);
		btnTimKiem.addActionListener(this);
		btnXemTatCa.addActionListener(this);
		btnXoaSP.addActionListener(this);
		btnXuatExCel.addActionListener(this);
		btnThemNhieuSach.addActionListener(this);
		table.addMouseListener(this);
		table.getSelectionModel();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		txtGiaNhap.addKeyListener(this);
		cbLoaiSanPhamSearch.addItemListener(this);
		cbNhaCungCapSearch.addItemListener(this);
		cbTacGiaSearch.addItemListener(this);
		cbTheLoaiSearch.addItemListener(this);
		txtTuKhoa.addKeyListener(this);

//		txtISBN.getDocument().addDocumentListener(this);
		this.setFocusable(true);
		this.requestFocusInWindow();
		table.requestFocus();
		loadData();
		loadComboBoxByLoaiSanPham();
		loadComboxBoxByNhaCungCap();
		loadComboBoxByTheLoai();
		loadComBoBoxByTacGia();
		lamMoi();

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					int row = table.getSelectedRow();
					if (row != -1) {
						txtIdSanPham.setText(model.getValueAt(row, 0).toString());
						txtTenSanPham.setText(model.getValueAt(row, 1).toString());
						cbLoaiTacGia.setSelectedItem(model.getValueAt(row, 2).toString());
						cbLoaiTheLoai.setSelectedItem(model.getValueAt(row, 3).toString());
						String namXuatBanString = model.getValueAt(row, 4).toString();
						SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
						java.util.Date namXuatBan = null;
						try {
							namXuatBan = dateFormat.parse(namXuatBanString);
						} catch (ParseException ex) {
							ex.printStackTrace();
						}
						chooserXuatBan.setDate(namXuatBan);
						isbnField.setText(model.getValueAt(row, 5).toString());
						txtSoTrang.setText(model.getValueAt(row, 6).toString());
						cbLoaiSanPham.setSelectedItem(model.getValueAt(row, 7).toString());
						cbNhaCungCap.setSelectedItem(model.getValueAt(row, 8).toString());
						txtKichThuoc.setText(model.getValueAt(row, 9).toString());
						txtMauSac.setText(model.getValueAt(row, 10).toString());

						String trangThaiValue = model.getValueAt(row, 11).toString();
						TrangThaiSPEnum trangThai = TrangThaiSPEnum.getByName(trangThaiValue);

						chkTinhTrangKinhDoanh.setSelected(trangThai == TrangThaiSPEnum.DANG_KINH_DOANH);

						txtSoLuong.setText(model.getValueAt(row, 13).toString());
						txtGiaNhap.setText(model.getValueAt(row, 14).toString());
						txtGiaBan.setText(model.getValueAt(row, 15).toString());
					}
				}
			}
		});

		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {

			@Override
			public boolean dispatchKeyEvent(KeyEvent e) {
				if (e.getID() == KeyEvent.KEY_RELEASED) {
					if (e.getKeyCode() == KeyEvent.VK_F5) {
						lamMoi();
						loadData();
					}
				} else if (e.getKeyCode() == KeyEvent.VK_DELETE || e.getKeyCode() == KeyEvent.VK_INSERT) {
					xoaSP();
				} else if (e.getKeyCode() == KeyEvent.VK_F2) {
					capNhatSach();
				} else if ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0 && e.getKeyCode() == KeyEvent.VK_S) {
					txtTuKhoa.requestFocusInWindow();
				}
				return false;
			}
		});

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnLamMoi)) {
			lamMoi();
		} else if (o.equals(btnThemSP)) {
			themSach();
		} else if (o.equals(btnCapNhatSP)) {
			capNhatSach();
		} else if (o.equals(btnXoaSP)) {
			xoaSP();
		} else if (o.equals(btnXemTatCa)) {
			lamMoi();
			loadData();
		} else if (o.equals(btnXuatExCel)) {
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			String filePath = System.getProperty("user.dir") + "/src/main/resources/DataExports/Sach/S_" + timeStamp
					+ ".xlsx";

			ghiFileExcel(filePath);

		} else if (o.equals(btnThemNhieuSach)) {
			try {
				themNhieuSach();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void themNhieuSach() throws SQLException {
		String defaultCurrentDirectoryPath = System.getProperty("user.dir") + "/src/main/resources/DataImports";
		JFileChooser excelFileChooser = new JFileChooser(defaultCurrentDirectoryPath);
		excelFileChooser.setDialogTitle("Chọn File Excel");
		FileNameExtensionFilter fnef = new FileNameExtensionFilter("TẤT CẢ CÁC FILE EXCEL", "xls", "xlsx", "xlsm");
		excelFileChooser.setFileFilter(fnef);
		int excelChooser = excelFileChooser.showOpenDialog(null);

		if (excelChooser == JFileChooser.APPROVE_OPTION) {
			File excelFile = excelFileChooser.getSelectedFile();

			try (FileInputStream fis = new FileInputStream(excelFile); Workbook workbook = new XSSFWorkbook(fis)) {
				xulyTrangTacGia(workbook.getSheetAt(0));
				xulyTrangTheLoai(workbook.getSheetAt(1));
				xulyTrangNhaCungCap(workbook.getSheetAt(2));
				xulyTrangLoaiSanPham(workbook.getSheetAt(3));
				xulyTrangSanPhamCon(workbook.getSheetAt(4));
				loadComboBoxByLoaiSanPham();
				loadComBoBoxByTacGia();
				loadComboBoxByTheLoai();
				loadComboxBoxByNhaCungCap();
				JOptionPane.showMessageDialog(null, "Thêm thành công");
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
	}

	private void xulyTrangSanPhamCon(Sheet sheet) throws SQLException {
		for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
			Row row = sheet.getRow(rowIndex);
			if (row != null) {
				SachCon s = new SachCon();
				s.setIdSanPham(row.getCell(0).getStringCellValue());
				s.setTenSanPham(row.getCell(1).getStringCellValue());
				s.setTacGia(new TacGia(row.getCell(2).getStringCellValue()));
				s.setTheLoai(new TheLoai(row.getCell(3).getStringCellValue()));
				java.util.Date namXuatBanUtil = row.getCell(4).getDateCellValue();
				java.sql.Date namXuatBanSql = new java.sql.Date(namXuatBanUtil.getTime());
				s.setNamXuatBan(namXuatBanSql);
				s.setISBN(row.getCell(5).getStringCellValue());
				int soTrang = (int) row.getCell(6).getNumericCellValue();
				s.setSoTrang(soTrang);
				s.setIdLoaiSanPham(new LoaiSanPham(row.getCell(7).getStringCellValue()));
				s.setIdNhaCungCap(new NhaCungCap(row.getCell(8).getStringCellValue()));
				s.setKichThuoc(row.getCell(9).getNumericCellValue());
				s.setMauSac(row.getCell(10).getStringCellValue());
				int trangThaiValue = (int) row.getCell(11).getNumericCellValue();
				TrangThaiSPEnum trangThaiEnum = TrangThaiSPEnum.getById(trangThaiValue);
				s.setTrangThai(trangThaiEnum);
				s.thue();
				int soLuong = (int) row.getCell(13).getNumericCellValue();
				s.setSoLuong(soLuong);
				s.setGiaNhap(row.getCell(14).getNumericCellValue());
				s.giaBan();
				s.setGiaKM(row.getCell(16).getNumericCellValue());
				boolean checkIDLoaiSanPham = daoLoaiSanPham.checkIdLoaiSanPham(s.getIdLoaiSanPham().getIdLoaiSanPham());
				boolean checkIDNhaCungCap = daoNhaCungCap.checkIdNhaCungCap(s.getIdNhaCungCap().getIdNhaCungCap());

				boolean checkIDTacGia = daoTacGia.checkIdTacGia(s.getTacGia().getIdTacGia());

				boolean checkIDTheLoai = daoTheLoai.checkIdTheLoai(s.getTheLoai().getIdTheLoai());

				if (!checkIDLoaiSanPham || !checkIDNhaCungCap || !checkIDTacGia || !checkIDTheLoai) {
					JOptionPane.showMessageDialog(this,
							"Không thể thêm sản phẩm: Một hoặc nhiều khóa phụ không tồn tại hoặc không hợp lệ.");
					continue;
				}if (daoSach.checkIdSach(s.getIdSanPham())) {
					daoSach.capNhatSach(s);
					lamMoi();
				} else {
					boolean result = daoSach.themSach(s);
					loadData();
					lamMoi();
					if (!result) {
						System.out.println("Không thể thêm sản phẩm: " + s.getIdSanPham());
					}
				}
			}
		}
	}

	private void xulyTrangTheLoai(Sheet sheet) {
		for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
			Row row = sheet.getRow(rowIndex);
			if (row != null) {
				TheLoai tl = new TheLoai();
				tl.setIdTheLoai(row.getCell(0).getStringCellValue());
				tl.setTenTheLoai(row.getCell(1).getStringCellValue());
				int soLuong = (int) row.getCell(2).getNumericCellValue();
				tl.setSoLuongSach(soLuong);
				tl.setMoTa(row.getCell(3).getStringCellValue());
				try {
					if (!daoTheLoai.checkIdTheLoai(tl.getIdTheLoai())) {
						boolean result = daoTheLoai.themTheLoai(tl);
						if (!result) {
							System.out.println("Không thể thêm thể loại: " + tl.getIdTheLoai());
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void xulyTrangTacGia(Sheet sheet) {
		for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
			Row row = sheet.getRow(rowIndex);
			if (row != null) {
				TacGia tg = new TacGia();
				tg.setIdTacGia(row.getCell(0).getStringCellValue());
				tg.setTenTacGia(row.getCell(1).getStringCellValue());
				java.util.Date namXuatBanUtil = row.getCell(3).getDateCellValue();
				java.sql.Date namXuatBanSql = new java.sql.Date(namXuatBanUtil.getTime());
				tg.setNgaySinh(namXuatBanSql);
				
				
//				tg.setNgaySinh(new java.sql.Date(row.getCell(2).getDateCellValue().getTime()));
				int soLuong = (int) row.getCell(3).getNumericCellValue();
				tg.setSoLuongTacPham(soLuong);
				try {
					if (!daoTacGia.checkIdTacGia(tg.getIdTacGia())) {
						boolean result = daoTacGia.themTacGia(tg);
						if (!result) {
							System.out.println("Không thể thêm tác giả: " + tg.getIdTacGia());
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void xulyTrangLoaiSanPham(Sheet sheet) {
		for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
			Row row = sheet.getRow(rowIndex);
			if (row != null) {
				LoaiSanPham lsp = new LoaiSanPham();
				lsp.setIdLoaiSanPham(row.getCell(0).getStringCellValue());
				lsp.setTenLoaiSanPham(row.getCell(1).getStringCellValue());

				try {
					if (!daoLoaiSanPham.checkIdLoaiSanPham(lsp.getIdLoaiSanPham())) {
						boolean result = daoLoaiSanPham.themLoaiSanPham(lsp);
						if (!result) {
							System.out.println("Không thể thêm loại sản phẩm: " + lsp.getIdLoaiSanPham());
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void xulyTrangNhaCungCap(Sheet sheet) {
		for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
			Row row = sheet.getRow(rowIndex);
			if (row != null) {
				NhaCungCap ncc = new NhaCungCap();
				ncc.setIdNhaCungCap(row.getCell(0).getStringCellValue());
				ncc.setTenNhaCungCap(row.getCell(1).getStringCellValue());
				ncc.setDiaChi(row.getCell(2).getStringCellValue());
				ncc.setSoDienThoai(row.getCell(3).getStringCellValue());

				try {
					if (!daoNhaCungCap.checkIdNhaCungCap(ncc.getIdNhaCungCap())) {
						boolean result = daoNhaCungCap.themNhaCungCap(ncc);
						if (!result) {
							System.out.println("Không thể thêm nhà cung cấp: " + ncc.getIdNhaCungCap());
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

//	private void themNhieuSach() throws SQLException {
//		String defaultCurrentDirectoryPath = System.getProperty("user.dir") + "/src/main/resources/DataImports";
//		JFileChooser exelFileChooser = new JFileChooser(defaultCurrentDirectoryPath);
//		exelFileChooser.setDialogTitle("Select Excel file:");
//		FileNameExtensionFilter fnef = new FileNameExtensionFilter("EXCEL FILES", "xls", "xlsx", "xlsm");
//		exelFileChooser.setFileFilter(fnef);
//		int excelChooser = exelFileChooser.showOpenDialog(null);
//		if (excelChooser == JFileChooser.APPROVE_OPTION) {
//			File excelFile = exelFileChooser.getSelectedFile();
//			try (FileInputStream fis = new FileInputStream(excelFile); Workbook workbook = new XSSFWorkbook(fis)) {
//				Sheet sheet = workbook.getSheetAt(0);
//				for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
//					Row row = sheet.getRow(rowIndex);
//					if (row != null) {
//						SachCon s = new SachCon();
//						s.setIdSanPham(row.getCell(0).getStringCellValue());
//						s.setTenSanPham(row.getCell(1).getStringCellValue());
//						s.setTacGia(new TacGia(row.getCell(2).getStringCellValue()));
//						s.setTheLoai(new TheLoai(row.getCell(3).getStringCellValue()));
//						java.util.Date namXuatBanUtil = row.getCell(4).getDateCellValue();
//						java.sql.Date namXuatBanSql = new java.sql.Date(namXuatBanUtil.getTime());
//						s.setNamXuatBan(namXuatBanSql);
//						s.setISBN(row.getCell(5).getStringCellValue());
//						int soTrang = (int) row.getCell(6).getNumericCellValue();
//						s.setSoTrang(soTrang);
//						s.setIdLoaiSanPham(new LoaiSanPham(row.getCell(7).getStringCellValue()));
//						s.setIdNhaCungCap(new NhaCungCap(row.getCell(8).getStringCellValue()));
//						s.setKichThuoc(row.getCell(9).getNumericCellValue());
//						s.setMauSac(row.getCell(10).getStringCellValue());
//						int trangThaiValue = (int) row.getCell(11).getNumericCellValue();
//						TrangThaiSPEnum trangThaiEnum = TrangThaiSPEnum.getById(trangThaiValue);
//						s.setTrangThai(trangThaiEnum);
//						s.thue();
//						int soLuong = (int) row.getCell(13).getNumericCellValue();
//						s.setSoLuong(soLuong);
//						s.setGiaNhap(row.getCell(14).getNumericCellValue());
//						s.giaBan();
//						s.setGiaKM(row.getCell(16).getNumericCellValue());
//						boolean checkIDLoaiSanPham = daoLoaiSanPham
//								.checkIdLoaiSanPham(s.getIdLoaiSanPham().getIdLoaiSanPham());
//						boolean checkIDNhaCungCap = daoNhaCungCap
//								.checkIdNhaCungCap(s.getIdNhaCungCap().getIdNhaCungCap());
//						boolean checkIDTacGia = daoTacGia.checkIdTacGia(s.getTacGia().getIdTacGia());
//						boolean checkIDTheLoai = daoTheLoai.checkIdTheLoai(s.getTheLoai().getIdTheLoai());
//						if (!checkIDLoaiSanPham || !checkIDNhaCungCap || !checkIDTacGia || !checkIDTheLoai) {
//							JOptionPane.showMessageDialog(this,
//									"Không thể thêm sản phẩm: Một hoặc nhiều khóa phụ không tồn tại hoặc không hợp lệ.");
//							continue;
//						}
//						if (daoSach.checkIdSach(s.getIdSanPham())) {
//							daoSach.capNhatSach(s);
//							lamMoi();
//						} else {
//							boolean result = daoSach.themSach(s);
//							loadData();
//							lamMoi();
//							if (!result) {
//								System.out.println("Không thể thêm sản phẩm: " + s.getIdSanPham());
//							}
//						}
//					}
//				}
//				JOptionPane.showMessageDialog(null, "Thêm thành công");
//			} catch (IOException e) {
//				JOptionPane.showMessageDialog(null, e.getMessage());
//			}
//		}
//	}

	private Double parseDoubleWithMultiplePoints(String input) {
		String cleanedInput = input.replaceAll("[^\\d.]", "");
		cleanedInput = cleanedInput.contains(".")
				? cleanedInput.substring(0, cleanedInput.indexOf(".") + 1)
						+ cleanedInput.substring(cleanedInput.indexOf(".") + 1).replace(".", "")
				: cleanedInput;
		return cleanedInput.isEmpty() ? 0.0 : Double.parseDouble(cleanedInput);
	}

	private void ghiFileExcel(String filePath) {
		double tongGiaNhap = 0;
		int tongSoLuong = 0;
		double tongGiaBan = 0;
		int rowCount = model.getRowCount();
		ArrayList<SachCon> dsSach = new ArrayList<>();
		for (int i = 0; i < rowCount; i++) {
//			String thueStr = ((String) modelSP.getValueAt(i, 7)).replaceAll("\\D+","");
//			Double thue = parseDoubleWithMultiplePoints(thueStr);
			String idSanPham = (String) model.getValueAt(i, 0);
			String tenSanPham = (String) model.getValueAt(i, 1);
			String tacGia = (String) model.getValueAt(i, 2);
			String theLoai = (String) model.getValueAt(i, 3);

			java.sql.Date date = (java.sql.Date) model.getValueAt(i, 4);

			String isbn = (String) model.getValueAt(i, 5);
			int soTrang = (int) model.getValueAt(i, 6);
//			double giaNhap = Double.parseDouble(txtGiaNhap2.getText());
			String loaiSanPham = (String) model.getValueAt(i, 7);
			String nhaCungCap = (String) model.getValueAt(i, 8);
			Double kichThuoc = (Double) model.getValueAt(i, 9);
			String mauSac = (String) model.getValueAt(i, 10);
			String trangThaiStr = (String) model.getValueAt(i, 11);
			TrangThaiSPEnum trangThai = TrangThaiSPEnum.getByName(trangThaiStr);
			String thueStr = ((String) model.getValueAt(i, 12)).replaceAll("\\D+", "");
			Double thue = parseDoubleWithMultiplePoints(thueStr);
			int soLuong = (int) model.getValueAt(i, 13);
			String giaNhapStr = ((String) model.getValueAt(i, 14)).replaceAll("\\D+", "");
			Double giaNhap = parseDoubleWithMultiplePoints(giaNhapStr);
			SachCon s = new SachCon();
			s.setIdSanPham(idSanPham);
			s.setTenSanPham(tenSanPham);
			if (tacGia != null) {
				TacGia loaiTG = new TacGia();
				loaiTG.setTenTacGia(tacGia);
				s.setTacGia(loaiTG);
			}
			if (theLoai != null) {
				TheLoai loaiTL = new TheLoai();
				loaiTL.setTenTheLoai(theLoai);
				s.setTheLoai(loaiTL);
			}

			s.setNamXuatBan(date);
			s.setISBN(isbn);

			s.setSoTrang(soTrang);
			if (loaiSanPham != null) {
				LoaiSanPham loaiSP = new LoaiSanPham();
				loaiSP.setTenLoaiSanPham(loaiSanPham);
				s.setIdLoaiSanPham(loaiSP);
			}

			if (nhaCungCap != null) {
				NhaCungCap ncc = new NhaCungCap();
				ncc.setTenNhaCungCap(nhaCungCap);
				s.setIdNhaCungCap(ncc);
			}
			s.setKichThuoc(kichThuoc);
			s.setMauSac(mauSac);
			s.setTrangThai(trangThai);
			s.setSoLuong(soLuong);
			s.setGiaNhap(giaNhap);
			dsSach.add(s);
		}
		try (Workbook workbook = new XSSFWorkbook()) {
			Sheet sheet = workbook.createSheet("Danh sách sách");
			Row headerRow = sheet.createRow(0);
			String[] columnNames = { "ID Sản phẩm", "Tên sản phẩm", "Tác giả", "Thể loại", "Năm xuất bản", "ISBN",
					"Số trang", "Loại sản phẩm", "Nhà cung cấp", "Kích thước", "Màu sắc", "Trạng thái", "Thuế",
					"Số lượng", "Giá nhập", "Giá bán" };

			for (int i = 0; i < columnNames.length; i++) {
				org.apache.poi.ss.usermodel.Cell cell = headerRow.createCell(i);
				cell.setCellValue(columnNames[i]);
			}
			int rowNumber = 1;
			for (SachCon s : dsSach) {
				Row row = sheet.createRow(rowNumber++);
				org.apache.poi.ss.usermodel.Cell idSanPhamCell = row.createCell(0);
				idSanPhamCell.setCellValue(s.getIdSanPham());
				org.apache.poi.ss.usermodel.Cell tenSanPhamCell = row.createCell(1);
				tenSanPhamCell.setCellValue(s.getTenSanPham());
				org.apache.poi.ss.usermodel.Cell tacGia = row.createCell(2);
				tacGia.setCellValue(s.getTacGia() != null ? s.getTacGia().getTenTacGia() : "");
				org.apache.poi.ss.usermodel.Cell theLoai = row.createCell(3);
				theLoai.setCellValue(s.getTheLoai() != null ? s.getTheLoai().getTenTheLoai() : "");
				org.apache.poi.ss.usermodel.Cell namXuatBan = row.createCell(4);
				namXuatBan.setCellValue(s.getNamXuatBan());
				org.apache.poi.ss.usermodel.Cell ISBN = row.createCell(5);
				ISBN.setCellValue(s.getISBN());
				org.apache.poi.ss.usermodel.Cell soTrang = row.createCell(6);
				soTrang.setCellValue(s.getSoTrang());
				org.apache.poi.ss.usermodel.Cell loaiSanPhamCell = row.createCell(7);
				loaiSanPhamCell
						.setCellValue(s.getIdLoaiSanPham() != null ? s.getIdLoaiSanPham().getTenLoaiSanPham() : "");
				org.apache.poi.ss.usermodel.Cell nhaCungCapCell = row.createCell(8);
				nhaCungCapCell.setCellValue(s.getIdNhaCungCap() != null ? s.getIdNhaCungCap().getTenNhaCungCap() : "");
				org.apache.poi.ss.usermodel.Cell kichThuocCell = row.createCell(9);
				kichThuocCell.setCellValue(s.getKichThuoc());
				org.apache.poi.ss.usermodel.Cell mauSacCell = row.createCell(10);
				mauSacCell.setCellValue(s.getMauSac());
				org.apache.poi.ss.usermodel.Cell trangThaiCell = row.createCell(11);
				trangThaiCell.setCellValue(s.getTrangThai() != null ? s.getTrangThai().getDescription() : "");
				org.apache.poi.ss.usermodel.Cell thueCell = row.createCell(12);
				thueCell.setCellValue(s.thue());
				org.apache.poi.ss.usermodel.Cell soLuongCell = row.createCell(13);
				soLuongCell.setCellValue(s.getSoLuong());
				org.apache.poi.ss.usermodel.Cell giaNhapCell = row.createCell(14);
				giaNhapCell.setCellValue(s.getGiaNhap());
				org.apache.poi.ss.usermodel.Cell giaBanCell = row.createCell(15);
				giaBanCell.setCellValue(s.giaBan());
				tongGiaNhap += s.getGiaNhap();
				tongSoLuong += s.getSoLuong();
				tongGiaBan += s.giaBan();
			}
			for (int i = 0; i < columnNames.length; i++) {
				sheet.autoSizeColumn(i);
			}
			sheet.createRow(rowNumber);
			org.apache.poi.ss.usermodel.Cell summaryCell = sheet.getRow(rowNumber).createCell(0);
			summaryCell.setCellValue("Tổng cộng");

			org.apache.poi.ss.usermodel.Cell tongGiaNhapCell = sheet.getRow(rowNumber).createCell(14);
			tongGiaNhapCell.setCellValue(tongGiaNhap);

			org.apache.poi.ss.usermodel.Cell tongSoLuongCell = sheet.getRow(rowNumber).createCell(13);
			tongSoLuongCell.setCellValue(tongSoLuong);

			org.apache.poi.ss.usermodel.Cell tongGiaBanCell = sheet.getRow(rowNumber).createCell(15);
			tongGiaBanCell.setCellValue(tongGiaBan);
			try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
				workbook.write(outputStream);
			}

			System.out.println("Dữ liệu Sach đã được ghi vào tệp Excel thành công.");
			JOptionPane.showMessageDialog(this, "Xuất thống kê excel thành công");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private Date convertStringToDate(String dateString) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			return dateFormat.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace(); // Handle the exception appropriately
			return null;
		}
	}

	private void xoaSP() {
		int row = table.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(this, "Bạn cần phải chọn dòng xóa");
		} else {
			try {
				SachCon s = layThongTinSach();
				int hoiNhac = JOptionPane.showConfirmDialog(this, "Bạn có chắc xóa không!", "Cảnh báo",
						JOptionPane.YES_NO_OPTION);
				if (hoiNhac == JOptionPane.YES_OPTION) {
					model.removeRow(row);
					String idSanPham = txtIdSanPham.getText();
					daoSach.xoaSach(idSanPham);
					daoTacGia.giamSoLuongTacPham(s.getTacGia().getIdTacGia());
					daoTheLoai.giamSoLuongTheLoai(s.getTheLoai().getIdTheLoai());
					JOptionPane.showMessageDialog(this, "Xóa sản phẩm thành công");
					lamMoi();
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this, "Xóa sản phẩm không thành công");
				e.printStackTrace();
			}
		}

	}

	private void capNhatSach() {
		int row = table.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng để cập nhật", "Cảnh báo",
					JOptionPane.WARNING_MESSAGE);
			return;
		} else {
			int hoiNhac = JOptionPane.showConfirmDialog(this, "Bạn muốn sửa thông tin sản phẩm này", "Thông báo",
					JOptionPane.YES_NO_OPTION);
			if (hoiNhac == JOptionPane.YES_OPTION) {
				if (validataFieldsAndShowErrors()) {
					try {
						SachCon s = layThongTinSach();
						String idSanPham = txtIdSanPham.getText();
						daoSach.capNhatSach(s);
						JOptionPane.showMessageDialog(this, "Cập nhật sách thành công!");
						loadData();
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(this, "Vui lòng kiểm tra định dạng số", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		}

	}

	private SachCon layThongTinSach() throws NumberFormatException {
		String idSanPham = txtIdSanPham.getText().trim();
		String tenSanPham = txtTenSanPham.getText().trim();
		String tacGia = (String) cbLoaiTacGia.getSelectedItem();
		String theLoai = (String) cbLoaiTheLoai.getSelectedItem();
		java.util.Date date = chooserXuatBan.getDate();
		java.sql.Date namXuatBan = new java.sql.Date(date.getYear(), date.getMonth(), date.getDay());
		String ISBN = isbnField.getText().trim();
		int soTrang = Integer.parseInt(txtSoTrang.getText().trim());
		String tenLoaiSanPham = (String) cbLoaiSanPham.getSelectedItem();
		String tenNhaCungCap = (String) cbNhaCungCap.getSelectedItem();
		double kichThuoc = Double.parseDouble(txtKichThuoc.getText().trim());
		String mauSac = txtMauSac.getText().trim();
		boolean trangThaiValue = chkTinhTrangKinhDoanh.isSelected();
		TrangThaiSPEnum trangThai = trangThaiValue ? TrangThaiSPEnum.DANG_KINH_DOANH : TrangThaiSPEnum.NGUNG_KINH_DOANH;
		int soLuong = Integer.parseInt(txtSoLuong.getText().trim());
		double giaNhap = Double.parseDouble(txtGiaNhap.getText().trim());

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

		if (tenTacGias == null || theLoais == null || nhaCungCap == null || loaiSanPham == null) {
			JOptionPane.showMessageDialog(this,
					"Vui lòng chọn đầy đủ thông tin về tác giả, thể loại, nhà cung cấp, và loại sản phẩm.");
			return null;
		}
		SachCon sach = new SachCon();
		sach.setIdSanPham(idSanPham);
		sach.setTenSanPham(tenSanPham);
		sach.setTacGia(tenTacGias);
		sach.setTheLoai(theLoais);
		sach.setNamXuatBan(namXuatBan);
		sach.setISBN(ISBN);
		sach.setSoTrang(soTrang);
		sach.setIdLoaiSanPham(loaiSanPham);
		sach.setIdNhaCungCap(nhaCungCap);
		sach.setKichThuoc(kichThuoc);
		sach.setMauSac(mauSac);
		sach.setTrangThai(trangThai);
		sach.setSoLuong(soLuong);
		sach.setGiaNhap(giaNhap);

		return sach;
	}

	private boolean validataFieldsAndShowErrors() {
		String tenSanPham = txtTenSanPham.getText().trim();
		String ISBN = isbnField.getText().trim();
		String soTrang = txtSoTrang.getText().trim();
		String kichThuoc = txtKichThuoc.getText().trim();
		String mauSac = txtMauSac.getText().trim();
		String giaNhap = txtGiaNhap.getText().trim();
		String soLuong = txtSoLuong.getText().trim();

		if (tenSanPham.isEmpty() && ISBN.isEmpty() && soTrang.isEmpty() && kichThuoc.isEmpty() && mauSac.isEmpty()
				&& giaNhap.isEmpty() && soLuong.isEmpty()) {
			showErrorAndFocus(this, "Vui lòng điền thông tin trước", getFirstEmptyTextField());
			return false;
		}
		if (!isValidISBN(ISBN)) {
			showErrorAndFocus(this, "ISBN không hợp lệ.", isbnField);
			return false;
		}

		if (!isValidName(tenSanPham)) {
			showErrorAndFocus(this, "Tên sản phẩm không hợp lệ. Chỉ chấp nhận chữ và số và không có kí tự đặc biệt",
					txtTenSanPham);
			return false;
		}

		if (!isValidInt(soTrang)) {
			showErrorAndFocus(this, "Số trang không hợp lệ. Nhập số nguyên.", txtSoTrang);
			return false;
		}

		if (!isValidMauSac(mauSac)) {
			showErrorAndFocus(this, "Màu sắc không hợp lệ. Chỉ chấp nhận chữ", txtMauSac);
			return false;
		}
		if (!isValidDouble(kichThuoc)) {
			showErrorAndFocus(this, "Kích thước không hợp lệ. Nhập số thực.", txtKichThuoc);
			return false;
		}

		if (!isValidDouble(giaNhap)) {
			showErrorAndFocus(this, "Giá nhập không hợp lệ. Nhập số thực.", txtGiaNhap);
			return false;
		}

		if (!isValidInt(soLuong)) {
			showErrorAndFocus(this, "Số lượng không hợp lệ. Nhập số nguyên.", txtSoLuong);
			return false;
		}
		if (!validateDateChooser(chooserXuatBan)) {
			JOptionPane.showConfirmDialog(this, "Ngày xuất bản không hợp lệ.", "Lỗi", JOptionPane.WARNING_MESSAGE);
			chooserXuatBan.requestFocus();
			return false;
		}

		return true;
	}

	private void showErrorAndFocus(Component parentComponent, String message, JTextField textField) {
		JOptionPane.showMessageDialog(parentComponent, message, "Lỗi", JOptionPane.ERROR_MESSAGE);
		textField.requestFocusInWindow();
		textField.selectAll();
	}

	private JTextField getFirstEmptyTextField() {
		if (txtTenSanPham.getText().trim().isEmpty()) {
			return txtTenSanPham;
		} else if (isbnField.getText().trim().isEmpty()) {
			return isbnField;
		} else if (txtSoTrang.getText().trim().isEmpty()) {
			return txtSoTrang;
		} else if (txtMauSac.getText().trim().isEmpty()) {
			return txtMauSac;
		} else if (txtKichThuoc.getText().trim().isEmpty()) {
			return txtKichThuoc;
		} else if (txtSoLuong.getText().trim().isEmpty()) {
			return txtSoLuong;
		} else if (txtGiaNhap.getText().trim().isEmpty()) {
			return txtGiaNhap;
		}
		return null;
	}

	private boolean validateDateChooser(JDateChooser dateChooser) {
		Date selectedDate = dateChooser.getDate();
		if (selectedDate == null) {
			showErrorDialog("Vui lòng chọn Năm xuất bản");
			return false;
		}
		Date currentDate = new Date();
		if (selectedDate.after(currentDate)) {
			showErrorDialog("Năm xuất bản không được lớn hơn ngày hiện tại!");
			return false;
		}

		java.sql.Date ngaySinh = new java.sql.Date(selectedDate.getTime());
		return true;
	}

	private boolean isValidName(String input) {
		return input.matches("^[\\p{L}0-9\\s]+$");
	}

	private boolean isValidMauSac(String input) {
		return input.matches("^[\\p{L}\\s]+$");
	}

	private boolean isValidISBN(String isbn) {
		return isbn.matches("^\\d{3}-\\d{3}-\\d{3}-\\d{3}-\\d{1}$");
	}

	private boolean isValidDouble(String input) {
		if (input == null || input.trim().isEmpty()) {
			showErrorDialog("Giá trị không được để trống.");
			return false;
		}

		try {
			Double.parseDouble(input);
			return true;
		} catch (NumberFormatException e) {
//			showErrorDialog("Giá trị không hợp lệ. Vui lòng nhập số thực.");
			return false;
		}
	}

	private boolean isValidInt(String input) {
		if (input == null || input.trim().isEmpty()) {
			showErrorDialog("Giá trị không được để trống.");
			return false;
		}

		try {
			Integer.parseInt(input);
			return true;
		} catch (NumberFormatException e) {
//			showErrorDialog("Giá trị không hợp lệ. Vui lòng nhập số nguyên.");
			return false;
		}
	}

	private void showErrorDialog(String message) {
		JOptionPane.showMessageDialog(this, message, "Lỗi", JOptionPane.ERROR_MESSAGE);
	}

	private void themSach() {
		String idSanPham = txtIdSanPham.getText();
		try {
			if (daoSach.checkIdSach(idSanPham)) {
				JOptionPane.showMessageDialog(this, "Trùng ID sản phẩm. Vui lòng chọn ID khác.");
				return;
			} else if (validataFieldsAndShowErrors()) {
				SachCon sach = layThongTinSach();
				if (sach != null) {
					boolean kiemTra = daoSach.themSach(sach);
					if (kiemTra) {

						model.addRow(new Object[] { sach.getIdSanPham(), sach.getTenSanPham(),
								sach.getTacGia().getTenTacGia(), sach.getTheLoai().getTenTheLoai(),
								dfNgaySinh.format(sach.getNamXuatBan()), sach.getISBN(), sach.getSoTrang(),
								sach.getIdLoaiSanPham().getTenLoaiSanPham(), sach.getIdNhaCungCap().getTenNhaCungCap(),
								sach.getKichThuoc(), sach.getMauSac(), sach.getTrangThai(), sach.thue(),
								sach.getSoLuong(), sach.getGiaNhap(), sach.giaBan() });
						JOptionPane.showMessageDialog(this, "Thêm sách thành công");
						daoTacGia.tangSoLuongTacPham(sach.getTacGia().getIdTacGia());
						daoTheLoai.tangSoLuongTheLoai(sach.getTheLoai().getIdTheLoai());
						loadData();
						lamMoi();
					} else {
						JOptionPane.showMessageDialog(this, "Lỗi khi thêm sách");
					}
				} else {
					lamMoi();
				}
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Vui lòng kiểm tra định dạng số", "Lỗi", JOptionPane.ERROR_MESSAGE);
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Lỗi khi thêm sách", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void lamMoi() {
		txtIdSanPham.setText(generateNewProductID());
		txtTenSanPham.setText("");

		if (cbLoaiTacGia.getItemCount() > 1) {
			cbLoaiTacGia.setSelectedIndex(1);
		}

		if (cbLoaiTheLoai.getItemCount() > 1) {
			cbLoaiTheLoai.setSelectedIndex(1);
		}

		chooserXuatBan.setDate(new Date());
		isbnField.setText("");
		txtSoTrang.setText("");

		if (cbLoaiSanPham.getItemCount() > 1) {
			cbLoaiSanPham.setSelectedIndex(1);
		}

		if (cbNhaCungCap.getItemCount() > 1) {
			cbNhaCungCap.setSelectedIndex(1);
		}

		txtKichThuoc.setText("");
		txtMauSac.setText("");
		chkTinhTrangKinhDoanh.setSelected(trangThai.DANG_KINH_DOANH == trangThai.DANG_KINH_DOANH);
		txtSoLuong.setText("");
		txtGiaNhap.setText("");
		txtGiaBan.setText("");
		table.clearSelection();
		txtTenSanPham.requestFocus();
		txtTenSanPham.selectAll();
		table.requestFocus();
		// table.changeSelection(0, 0, false, false);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			Object o = e.getSource();
			if (o == txtTenSanPham) {
				themSach();
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		SwingUtilities.invokeLater(() -> {
			Object o = e.getSource();
			if (o.equals(txtGiaNhap)) {
				calculateSellingPrice(txtGiaNhap, txtGiaBan);
			} else if (o.equals(txtTuKhoa)) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(model);
				table.setRowSorter(tr);
				tr.setRowFilter(RowFilter.regexFilter("(?i)" + txtTuKhoa.getText().trim(), 1, 4, 5));
			}
		});

	}

	private void calculateSellingPrice(JTextField txtGiaNhap2, JTextField txtGiaBan2) {
		try {
			double giaNhap = Double.parseDouble(txtGiaNhap2.getText());
			double thue = giaNhap * 0.05;
			double sellingPrice = giaNhap + (giaNhap * 0.55) + thue;
			txtGiaBan2.setText(df.format(sellingPrice));
		} catch (NumberFormatException ex) {
			txtGiaBan2.setText("Vui lòng nhập số hợp lệ.");
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		if (row >= 0) {
			txtIdSanPham.setText(model.getValueAt(row, 0).toString());
			txtTenSanPham.setText(model.getValueAt(row, 1).toString());
			cbLoaiTacGia.setSelectedItem(model.getValueAt(row, 2).toString());
			cbLoaiTheLoai.setSelectedItem(model.getValueAt(row, 3).toString());
			String namXuatBanString = model.getValueAt(row, 4).toString();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			java.util.Date namXuatBan = null;
			try {
				namXuatBan = dateFormat.parse(namXuatBanString);
			} catch (ParseException ex) {
				ex.printStackTrace();
			}
			chooserXuatBan.setDate(namXuatBan);
			isbnField.setText(model.getValueAt(row, 5).toString());
			txtSoTrang.setText(model.getValueAt(row, 6).toString());
			cbLoaiSanPham.setSelectedItem(model.getValueAt(row, 7).toString());
			cbNhaCungCap.setSelectedItem(model.getValueAt(row, 8).toString());
			txtKichThuoc.setText(model.getValueAt(row, 9).toString());
			txtMauSac.setText(model.getValueAt(row, 10).toString());

			String trangThaiValue = model.getValueAt(row, 11).toString();
			TrangThaiSPEnum trangThai = TrangThaiSPEnum.getByName(trangThaiValue);

			chkTinhTrangKinhDoanh.setSelected(trangThai == TrangThaiSPEnum.DANG_KINH_DOANH);

			String giaNhapStr = model.getValueAt(row, 14).toString().replaceAll("\\D+", "");
			String giaBanStr = model.getValueAt(row, 15).toString().replaceAll("\\D+", "");

			txtGiaNhap.setText(giaNhapStr);
			txtGiaBan.setText(giaBanStr);

			txtSoLuong.setText(model.getValueAt(row, 13).toString());
		}

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

	@Override
	public void itemStateChanged(ItemEvent e) {
		Object o = e.getSource();
		if (o == cbLoaiSanPhamSearch) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				String selectedLoaiSanPham = (String) cbLoaiSanPhamSearch.getSelectedItem();
				if (selectedLoaiSanPham.equals("Tất cả")) {
					loadData();
				} else {
					ArrayList<SachCon> sachByLoaiSP;
					try {
						sachByLoaiSP = daoSach.loadComboBoxByLoaiSanPham(selectedLoaiSanPham);
						if (sachByLoaiSP.isEmpty()) {
							JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm cho loại sản phẩm này.",
									"Thông báo", JOptionPane.WARNING_MESSAGE);
							loadData();
							cbLoaiSanPhamSearch.setSelectedItem("Tất cả");
						} else {
							loadtableByLoaiSanPham(selectedLoaiSanPham);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "Lỗi khi tải dữ liệu theo loại sản phẩm.", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		} else if (o == cbNhaCungCapSearch) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				String selectedNhaCungCap = (String) cbNhaCungCapSearch.getSelectedItem();
				if (selectedNhaCungCap.equals("Tất cả")) {
					loadData();
				} else {
					ArrayList<SachCon> sachByLoaiNCC;
					try {
						sachByLoaiNCC = daoSach.loadComboBoxByNhaCungCap(selectedNhaCungCap);
						if (sachByLoaiNCC.isEmpty()) {
							JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm cho nhà cung cấp này.",
									"Thông báo", JOptionPane.WARNING_MESSAGE);
							loadData();
							cbLoaiSanPhamSearch.setSelectedItem("Tất cả");
						} else {
							loadtableByNhaCungCap(selectedNhaCungCap);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "Lỗi khi tải dữ liệu theo loại sản phẩm.", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		} else if (o == cbTacGiaSearch) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				String selectedTacGia = (String) cbTacGiaSearch.getSelectedItem();
				if (selectedTacGia.equals("Tất cả")) {
					loadData();
				} else {
					ArrayList<SachCon> sachByLoaiTG;
					try {
						sachByLoaiTG = daoSach.loadComboBoxByTacGia(selectedTacGia);
						if (sachByLoaiTG.isEmpty()) {
							JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm cho tác giả này.", "Thông báo",
									JOptionPane.WARNING_MESSAGE);
							loadData();
							cbLoaiSanPhamSearch.setSelectedItem("Tất cả");
						} else {
							loadtableByTacGia(selectedTacGia);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "Lỗi khi tải dữ liệu theo tác giả.", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		} else if (o == cbTheLoaiSearch) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				String selectedTheLoai = (String) cbTheLoaiSearch.getSelectedItem();
				if (selectedTheLoai.equals("Tất cả")) {
					loadData();
				} else {
					ArrayList<SachCon> sachByTheLoai;
					try {
						sachByTheLoai = daoSach.loadComboBoxByTheLoai(selectedTheLoai);
						if (sachByTheLoai.isEmpty()) {
							JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm cho thể loại này.",
									"Thông báo", JOptionPane.WARNING_MESSAGE);
							loadData();
							cbLoaiSanPhamSearch.setSelectedItem("Tất cả");
						} else {
							loadtableByTheLoai(selectedTheLoai);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "Lỗi khi tải dữ liệu theo thể loại.", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}

	}

	private void loadtableByTheLoai(String selectedTheLoai) {
		model.setRowCount(0);
		try {
			ArrayList<SachCon> dsSach = daoSach.loadComboBoxByTheLoai(selectedTheLoai);
			if (dsSach.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm cho thể loại này.", "Thông báo",
						JOptionPane.WARNING_MESSAGE);
				loadData();
				cbTacGiaSearch.setSelectedItem("Tất cả");
			} else {
				for (SachCon s : dsSach) {
					String idSanPham = s.getIdSanPham();
					String tenSanPham = s.getTenSanPham();
					String tenTacGia = s.getTacGia().getIdTacGia();
					String tenTheLoai = s.getTheLoai().getIdTheLoai();
					Date namXuatBan = s.getNamXuatBan();
					String ISBN = s.getISBN();
					int soTrang = s.getSoTrang();
					String loaiSanPham = s.getIdLoaiSanPham().getIdLoaiSanPham();
					String nhaCungCap = s.getIdNhaCungCap().getIdNhaCungCap();
					double kichThuoc = s.getKichThuoc();
					String mauSac = s.getMauSac();
					String trangThai = s.getTrangThai().getDescription();
					double thue = s.thue();
					int soLuong = s.getSoLuong();
					double giaNhap = s.getGiaNhap();
					double giaBan = s.giaBan();
					model.addRow(new Object[] { idSanPham, tenSanPham, tenTacGia, tenTheLoai, namXuatBan, ISBN, soTrang,
							loaiSanPham, nhaCungCap, kichThuoc, mauSac, trangThai, currencyFormat.format(thue), soLuong,
							currencyFormat.format(giaNhap), currencyFormat.format(giaBan) });
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void loadtableByTacGia(String selectedTacGia) {
		model.setRowCount(0);
		try {
			ArrayList<SachCon> dsSach = daoSach.loadComboBoxByTacGia(selectedTacGia);
			if (dsSach.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm cho tác giả này.", "Thông báo",
						JOptionPane.WARNING_MESSAGE);
				loadData();
				cbTacGiaSearch.setSelectedItem("Tất cả");
			} else {
				for (SachCon s : dsSach) {
					String idSanPham = s.getIdSanPham();
					String tenSanPham = s.getTenSanPham();
					String tenTacGia = s.getTacGia().getIdTacGia();
					String tenTheLoai = s.getTheLoai().getIdTheLoai();
					Date namXuatBan = s.getNamXuatBan();
					String ISBN = s.getISBN();
					int soTrang = s.getSoTrang();
					String loaiSanPham = s.getIdLoaiSanPham().getIdLoaiSanPham();
					String nhaCungCap = s.getIdNhaCungCap().getIdNhaCungCap();
					double kichThuoc = s.getKichThuoc();
					String mauSac = s.getMauSac();
					String trangThai = s.getTrangThai().getDescription();
					double thue = s.thue();
					int soLuong = s.getSoLuong();
					double giaNhap = s.getGiaNhap();
					double giaBan = s.giaBan();
					model.addRow(new Object[] { idSanPham, tenSanPham, tenTacGia, tenTheLoai, namXuatBan, ISBN, soTrang,
							loaiSanPham, nhaCungCap, kichThuoc, mauSac, trangThai, currencyFormat.format(thue), soLuong,
							currencyFormat.format(giaNhap), currencyFormat.format(giaBan) });
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void loadtableByNhaCungCap(String selectedNhaCungCap) {
		model.setRowCount(0);
		try {
			ArrayList<SachCon> dsSach = daoSach.loadComboBoxByNhaCungCap(selectedNhaCungCap);
			if (dsSach.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm cho loại sản phẩm này.", "Thông báo",
						JOptionPane.WARNING_MESSAGE);
				loadData();
				cbNhaCungCapSearch.setSelectedItem("Tất cả");
			} else {
				for (SachCon s : dsSach) {
					String idSanPham = s.getIdSanPham();
					String tenSanPham = s.getTenSanPham();
					String tenTacGia = s.getTacGia().getIdTacGia();
					String tenTheLoai = s.getTheLoai().getIdTheLoai();
					Date namXuatBan = s.getNamXuatBan();
					String ISBN = s.getISBN();
					int soTrang = s.getSoTrang();
					String loaiSanPham = s.getIdLoaiSanPham().getIdLoaiSanPham();
					String nhaCungCap = s.getIdNhaCungCap().getIdNhaCungCap();
					double kichThuoc = s.getKichThuoc();
					String mauSac = s.getMauSac();
					String trangThai = s.getTrangThai().getDescription();
					double thue = s.thue();
					int soLuong = s.getSoLuong();
					double giaNhap = s.getGiaNhap();
					double giaBan = s.giaBan();
					model.addRow(new Object[] { idSanPham, tenSanPham, tenTacGia, tenTheLoai, namXuatBan, ISBN, soTrang,
							loaiSanPham, nhaCungCap, kichThuoc, mauSac, trangThai, currencyFormat.format(thue), soLuong,
							currencyFormat.format(giaNhap), currencyFormat.format(giaBan) });
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void loadtableByLoaiSanPham(String selectedLoaiSanPham) {
		model.setRowCount(0);
		try {
			ArrayList<SachCon> dsSach = daoSach.loadComboBoxByLoaiSanPham(selectedLoaiSanPham);
			if (dsSach.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm cho loại sản phẩm này.", "Thông báo",
						JOptionPane.WARNING_MESSAGE);
				loadData();
				cbLoaiSanPhamSearch.setSelectedItem("Tất cả");
			} else {
				for (SachCon s : dsSach) {
					String idSanPham = s.getIdSanPham();
					String tenSanPham = s.getTenSanPham();
					String tenTacGia = s.getTacGia().getIdTacGia();
					String tenTheLoai = s.getTheLoai().getIdTheLoai();
					Date namXuatBan = s.getNamXuatBan();
					String ISBN = s.getISBN();
					int soTrang = s.getSoTrang();
					String loaiSanPham = s.getIdLoaiSanPham().getIdLoaiSanPham();
					String nhaCungCap = s.getIdNhaCungCap().getIdNhaCungCap();
					double kichThuoc = s.getKichThuoc();
					String mauSac = s.getMauSac();
					String trangThai = s.getTrangThai().getDescription();
					double thue = s.thue();
					int soLuong = s.getSoLuong();
					double giaNhap = s.getGiaNhap();
					double giaBan = s.giaBan();
					model.addRow(new Object[] { idSanPham, tenSanPham, tenTacGia, tenTheLoai, namXuatBan, ISBN, soTrang,
							loaiSanPham, nhaCungCap, kichThuoc, mauSac, trangThai, currencyFormat.format(thue), soLuong,
							currencyFormat.format(giaNhap), currencyFormat.format(giaBan) });
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
//	    updateISBN(e);
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
//	    updateISBN(e);
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// updateISBN(e);
	}

	private void updateISBN(DocumentEvent e) {

	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			int row = table.getSelectedRow();
			if (row != -1) {
				txtIdSanPham.setText(model.getValueAt(row, 0).toString());
				txtTenSanPham.setText(model.getValueAt(row, 1).toString());
				cbLoaiTacGia.setSelectedItem(model.getValueAt(row, 2).toString());
				cbLoaiTheLoai.setSelectedItem(model.getValueAt(row, 3).toString());
				String namXuatBanString = model.getValueAt(row, 4).toString();
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				java.util.Date namXuatBan = null;
				try {
					namXuatBan = dateFormat.parse(namXuatBanString);
				} catch (ParseException ex) {
					ex.printStackTrace();
				}
				chooserXuatBan.setDate(namXuatBan);
				isbnField.setText(model.getValueAt(row, 5).toString());
				txtSoTrang.setText(model.getValueAt(row, 6).toString());
				cbLoaiSanPham.setSelectedItem(model.getValueAt(row, 7).toString());
				cbNhaCungCap.setSelectedItem(model.getValueAt(row, 8).toString());
				txtKichThuoc.setText(model.getValueAt(row, 9).toString());
				txtMauSac.setText(model.getValueAt(row, 10).toString());

				String trangThaiValue = model.getValueAt(row, 11).toString();
				TrangThaiSPEnum trangThai = TrangThaiSPEnum.getByName(trangThaiValue);

				chkTinhTrangKinhDoanh.setSelected(trangThai == TrangThaiSPEnum.DANG_KINH_DOANH);

				txtSoLuong.setText(model.getValueAt(row, 13).toString());
				txtGiaNhap.setText(model.getValueAt(row, 14).toString());
				txtGiaBan.setText(model.getValueAt(row, 15).toString());
			}
		}
	}
}
