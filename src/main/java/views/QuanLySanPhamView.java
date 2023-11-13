package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import connection.ConnectDB;
import dao.DAOLoaiSanPham;
import dao.DAONhaCungCap;
import dao.DAOQuanLySanPham;
import dao.DAOSach;
import models.KhuyenMai;
import models.LoaiSanPham;
import models.NhaCungCap;
import models.SanPhamCha;
import models.SanPhamCon;
import utils.TrangThaiSPEnum;

public class QuanLySanPhamView extends JPanel implements ActionListener, ItemListener, MouseListener, KeyListener {
	private JTabbedPane tabbedPane;
	private JTextField txtTenSanPham, txtIdSanPham, txtKichThuoc, txtSoLuong, txtMauSac, txtGiaBan, txtGiaNhap;
	private JComboBox<String> cbTinhTrangKhinhDoanh;
	private JComboBox<String> cbLoaiSanPham;
	private JComboBox<String> cbNhaCungCap;
	private JCheckBox chkTinhTrangKinhDoanh;
	private JLabel lblTieuDe, lblIDSanPham, lblTenSanPham, lblNhaCungCap, lblLoaiSanPham, lblKichThuoc, lblMauSac,
			lblSoLuong, lblGiaBan, lblThueVAT, lblTinhTrangKinhDoanh, lblGiaNhap;
	private JTable sanPhamTable;
	private JPanel pnCenter, pnChucNang, pnDanhMuc, pnMain;
	private JButton btnThemSP, btnXoaSP, btnNhapSP, btnCapNhatSP, btnHienThiLS, btnLamMoi;
	private JTable tableSP;
	private DefaultTableModel modelSP;
	private DAOLoaiSanPham daoLoaiSanPham;
	private DAONhaCungCap daoNhaCungCap;
	private DAOQuanLySanPham daoSanPham;
	private JLabel lblTuKhoa;
	private JTextField txtTuKhoa;
	private JButton btnTimKiem;
	private JButton btnXemTatCa;
	private JPanel pnChucNangCha;
	private JPanel pnChucNangTimKiem;
	private JComboBox<String> cbLoaiSanPhamSearch;
	private JComboBox<String> cbNhaCungCapSearch;
	private JLabel lblLoaiSanPhamSearch;
	private JLabel lblNhaCungCapSearch;
	private QuanLySachView sachPanel;

	public QuanLySanPhamView() {
		daoLoaiSanPham = new DAOLoaiSanPham();
		daoNhaCungCap = new DAONhaCungCap();
		daoSanPham = new DAOQuanLySanPham();

		setLayout(new BorderLayout(8, 6));
		tabbedPane = new JTabbedPane();
		// tab sách
		sachPanel = new QuanLySachView();
//		JPanel sachPanel = new JPanel();
		// Tab Sản phẩm
		JPanel sanPhamPanel = new JPanel();
		sanPhamPanel.setLayout(new BorderLayout());
		sanPhamPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		// center
		pnCenter = new JPanel();
		pnCenter.setLayout(new GridLayout(5, 5, 10, 10));
		pnCenter.setBorder(BorderFactory.createTitledBorder("Thông tin sản phẩm"));

		lblIDSanPham = new JLabel("ID Sản Phẩm(*):");
		txtIdSanPham = new JTextField();
		txtIdSanPham.setText(generateNewProductID());
		txtIdSanPham.setEditable(false);
		lblTenSanPham = new JLabel("Tên Sản Phẩm(*):");
		txtTenSanPham = new JTextField();
		txtTenSanPham.requestFocus();
		lblNhaCungCap = new JLabel("Nhà Cung Cấp(*):");
		cbNhaCungCap = new JComboBox<>();
		lblLoaiSanPham = new JLabel("Loại Sản Phẩm(*):");
		cbLoaiSanPham = new JComboBox<>();
		lblKichThuoc = new JLabel("Kích Thước (*):");
		txtKichThuoc = new JTextField();
		lblMauSac = new JLabel("Màu Sắc (*):");
		txtMauSac = new JTextField();
		lblSoLuong = new JLabel("Số Lượng (*):");
		txtSoLuong = new JTextField();
		lblGiaNhap = new JLabel("Giá Nhập (*)");
		txtGiaNhap = new JTextField();
		lblGiaBan = new JLabel("Giá Bán (*):");
		txtGiaBan = new JTextField();
		txtGiaBan.setEditable(false);
		lblTinhTrangKinhDoanh = new JLabel("Tình Trạng Kinh Doanh(*):");
		chkTinhTrangKinhDoanh = new JCheckBox();

		txtIdSanPham.setToolTipText("ID + Date + XXXX");
		txtKichThuoc.setToolTipText("Chỉ nhận số");
		txtSoLuong.setToolTipText("Chỉ nhận số nguyên");
		txtMauSac.setToolTipText("Chỉ nhận chữ và không có kí tự đặc biệt");
		txtTenSanPham.setToolTipText("Chỉ nhận chữ và số và không có kí tự đặc biệt");
		txtGiaNhap.setToolTipText("Chỉ nhận số");
		
		
		
		
		pnCenter.add(lblIDSanPham);
		pnCenter.add(txtIdSanPham);
		pnCenter.add(lblTenSanPham);
		pnCenter.add(txtTenSanPham);
		pnCenter.add(lblNhaCungCap);
		pnCenter.add(cbNhaCungCap);
		pnCenter.add(lblLoaiSanPham);
		pnCenter.add(cbLoaiSanPham);
		pnCenter.add(lblKichThuoc);
		pnCenter.add(txtKichThuoc);
		pnCenter.add(lblMauSac);
		pnCenter.add(txtMauSac);
		pnCenter.add(lblSoLuong);
		pnCenter.add(txtSoLuong);
		pnCenter.add(lblGiaNhap);
		pnCenter.add(txtGiaNhap);
		pnCenter.add(lblGiaBan);
		pnCenter.add(txtGiaBan);
		pnCenter.add(lblTinhTrangKinhDoanh);
		pnCenter.add(chkTinhTrangKinhDoanh);

		pnMain = new JPanel(new BorderLayout());

		pnChucNangCha = new JPanel(new BorderLayout(6, 8));
		pnChucNang = new JPanel(new GridLayout(1, 4, 10, 40));
		pnChucNangCha.setBorder(BorderFactory.createTitledBorder("Chức năng"));
		btnThemSP = new JButton("THÊM SẢN PHẨM");
		btnCapNhatSP = new JButton("CẬP NHẬT SẢN PHẨM");
		btnXoaSP = new JButton("XÓA SẢN PHẨM");
		lblTuKhoa = new JLabel("Tìm kiếm theo tên sản phẩm:");
		txtTuKhoa = new JTextField();
		btnTimKiem = new JButton("Tìm kiếm");
		btnXemTatCa = new JButton("Xem tất cả");
		btnLamMoi = new JButton("LÀM MỚI");
		ImageIcon iconThem = new ImageIcon(getClass().getResource("/icons/add.png"));
		ImageIcon iconCapNhat = new ImageIcon(getClass().getResource("/icons/capnhat.png"));
		ImageIcon iconLamMoi = new ImageIcon(getClass().getResource("/icons/lammoi.png"));
		ImageIcon iconXoa = new ImageIcon(getClass().getResource("/icons/xoa.png"));
		btnCapNhatSP.setIcon(iconCapNhat);
	    btnThemSP.setIcon(iconThem);
	    btnLamMoi.setIcon(iconLamMoi);
	    btnXoaSP.setIcon(iconXoa);
		pnChucNang.add(btnThemSP);
		pnChucNang.add(btnCapNhatSP);
		pnChucNang.add(btnXoaSP);
		pnChucNang.add(btnLamMoi);

		pnChucNangTimKiem = new JPanel(new GridLayout(1, 7, 10, 10));
		lblLoaiSanPhamSearch = new JLabel("Loại sản phẩm:");
		lblNhaCungCapSearch = new JLabel("Nhà cung cấp:");
		cbLoaiSanPhamSearch = new JComboBox<>();
		cbNhaCungCapSearch = new JComboBox<>();

		pnChucNangTimKiem.add(lblLoaiSanPhamSearch);
		pnChucNangTimKiem.add(cbLoaiSanPhamSearch);
		pnChucNangTimKiem.add(lblNhaCungCapSearch);
		pnChucNangTimKiem.add(cbNhaCungCapSearch);
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
		modelSP.addColumn("ID Sản Phẩm");
		modelSP.addColumn("Tên Sản Phẩm");
		modelSP.addColumn("Loại Sản Phẩm");
		modelSP.addColumn("Nhà Cung Cấp");
		modelSP.addColumn("Kích Thước");
		modelSP.addColumn("Màu Sắc");
		modelSP.addColumn("Trạng Thái");
		modelSP.addColumn("Thuế");
		modelSP.addColumn("Giá Nhập");
		modelSP.addColumn("Số Lượng");
		modelSP.addColumn("Giá Bán");
		tableSP.setModel(modelSP);
		JScrollPane scrollPane = new JScrollPane(tableSP);
		pnDanhMuc.add(scrollPane);
		pnMain.add(pnDanhMuc, BorderLayout.CENTER);

		// Bảng
		sanPhamTable = new JTable();
		sanPhamPanel.add(pnCenter, BorderLayout.NORTH);
		sanPhamPanel.add(pnMain, BorderLayout.CENTER);

		tabbedPane.addTab("Sản phẩm", sanPhamPanel);
		tabbedPane.add("Sách", sachPanel);
		add(tabbedPane);
		try {
			ConnectDB.getinstance().connect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		loadDataIntoTable();
		loadComboxBoxLoaiSanPham();
		loadComboxBoxNhaCungCap();
		tableSP.addMouseListener(this);
		btnThemSP.addActionListener(this);
		btnCapNhatSP.addActionListener(this);
		btnXemTatCa.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnXoaSP.addActionListener(this);
		cbLoaiSanPhamSearch.addItemListener(this);
		cbNhaCungCapSearch.addItemListener(this);
		txtTuKhoa.addKeyListener(this);
		txtKichThuoc.addKeyListener(this);
		txtSoLuong.addKeyListener(this);
		txtGiaNhap.addKeyListener(this);
		txtMauSac.addKeyListener(this);
		txtTenSanPham.addKeyListener(this);
		tabbedPane.addKeyListener(this);

		this.setFocusable(true);
		this.requestFocusInWindow();
		tableSP.requestFocus();
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {

			@Override
			public boolean dispatchKeyEvent(KeyEvent e) {
				if (e.getID() == KeyEvent.KEY_RELEASED) {
					if (e.getKeyCode() == KeyEvent.VK_F5) {
						lamMoi();
						loadDataIntoTable();
					}
				} else if (e.getKeyCode() == KeyEvent.VK_DELETE || e.getKeyCode() == KeyEvent.VK_INSERT) {
					System.out.println("Xoa thanh cong");
					xoaSanPham();
				} else if (e.getKeyCode() == KeyEvent.VK_F2) {
					capNhatSanPham();
				} else if ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0 && e.getKeyCode() == KeyEvent.VK_S) {
					txtTuKhoa.requestFocusInWindow();
				}
				return false;
			}
		});
	}

	private void loadDataIntoTable() {
		modelSP.setRowCount(0);
		ArrayList<SanPhamCon> sanPhamList = daoSanPham.getAllSanPhamLoadData();
		for (SanPhamCon sanPham : sanPhamList) {
			String idSanPham = sanPham.getIdSanPham();
			String tenSanPham = sanPham.getTenSanPham();
			String tenLoaiSanPham = sanPham.getIdLoaiSanPham().getIdLoaiSanPham();
			String tenNhaCungCap = sanPham.getIdNhaCungCap().getIdNhaCungCap();
			double kichThuoc = sanPham.getKichThuoc();
			String mauSac = sanPham.getMauSac();
			String trangThai = sanPham.getTrangThai()+"";
			double thue = sanPham.thue();
			double giaNhap = sanPham.getGiaNhap();
			int soLuong = sanPham.getSoLuong();
			double giaBan = sanPham.giaBan();
			modelSP.addRow(new Object[] { idSanPham, tenSanPham, tenLoaiSanPham, tenNhaCungCap, kichThuoc, mauSac,
					trangThai, thue, giaNhap, soLuong, giaBan });
		}
	}

	private void loadData() {
		txtTenSanPham.requestFocus();
		modelSP.setRowCount(0);
		for (SanPhamCha sp : daoSanPham.getAllSanPham()) {
			String tenLoaiSanPham = sp.getIdLoaiSanPham().getIdLoaiSanPham();
			String tenNhaCungCap = sp.getIdNhaCungCap().getIdNhaCungCap();

			String[] row = { sp.getIdSanPham(), sp.getTenSanPham(), tenLoaiSanPham, tenNhaCungCap,
					sp.getKichThuoc() + "", sp.getMauSac(), sp.getTrangThai() + "" };
			modelSP.addRow(row);
		}
	}

	private void loadComboxBoxLoaiSanPham() {
		ArrayList<LoaiSanPham> danhSachLoaiSanPham = daoLoaiSanPham.getAllLoaiSanPham();
		cbLoaiSanPham.removeAllItems();
		cbLoaiSanPhamSearch.addItem("Tất cả");
		for (LoaiSanPham loaiSanPham : danhSachLoaiSanPham) {
			cbLoaiSanPham.addItem(loaiSanPham.getTenLoaiSanPham());
			cbLoaiSanPhamSearch.addItem(loaiSanPham.getTenLoaiSanPham());
		}
	}

	private void loadComboxBoxNhaCungCap() {
		ArrayList<NhaCungCap> danhSachNhaCungCap = daoNhaCungCap.getAllNhaCungCap();
		cbNhaCungCap.removeAllItems();
		cbNhaCungCapSearch.addItem("Tất cả");
		for (NhaCungCap ncc : danhSachNhaCungCap) {
			cbNhaCungCap.addItem(ncc.getTenNhaCungCap());
			cbNhaCungCapSearch.addItem(ncc.getTenNhaCungCap());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThemSP)) {
			themSanPham();
		} else if (o.equals(btnCapNhatSP)) {
			capNhatSanPham();
		} else if (o.equals(btnLamMoi)) {
			lamMoi();
		} else if (o.equals(btnXemTatCa)) {
			lamMoi();
			loadDataIntoTable();
		} else if (o.equals(btnXoaSP)) {
			xoaSanPham();
		}
	}

//	private void showErrorDialog(String message) {
//		JOptionPane.showMessageDialog(this, message, "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
//	}

	private void showSuccessMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}

	private void showErrorMessage(String message) {
		JOptionPane.showMessageDialog(this, message, "Lỗi", JOptionPane.ERROR_MESSAGE);
	}

	private void themSanPham() {
		if (validataFieldsAndShowErrors()) {
			String idSanPham = txtIdSanPham.getText();
			String tenSanPham = txtTenSanPham.getText();
			double kichThuoc = Double.parseDouble(txtKichThuoc.getText());
			String mauSac = txtMauSac.getText();
			boolean trangThaiValue = chkTinhTrangKinhDoanh.isSelected();
			TrangThaiSPEnum trangThai = trangThaiValue ? TrangThaiSPEnum.DANG_KINH_DOANH
					: TrangThaiSPEnum.NGUNG_KINH_DOANH;
			double giaNhap = Double.parseDouble(txtGiaNhap.getText());
			String tenLoaiSanPham = (String) cbLoaiSanPham.getSelectedItem();
			String tenNhaCungCap = (String) cbNhaCungCap.getSelectedItem();
			int soLuong = Integer.parseInt(txtSoLuong.getText());
			LoaiSanPham loaiSanPham = null;
			KhuyenMai khuyenMai = null;
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

			if (loaiSanPham != null && nhaCungCap != null) {
				SanPhamCon sp = new SanPhamCon();
				sp.setIdSanPham(idSanPham);
				sp.setTenSanPham(tenSanPham);
				sp.setIdLoaiSanPham(loaiSanPham);
				sp.setIdNhaCungCap(nhaCungCap);
				sp.setKichThuoc(kichThuoc);
				sp.setMauSac(mauSac);
				sp.setTrangThai(trangThai);
				sp.setGiaNhap(giaNhap);
				sp.setSoLuong(soLuong);

				try {
					if (daoSanPham.checkIdSanPham(idSanPham)) {
						JOptionPane.showMessageDialog(this, "Trùng ID sản phẩm. Vui lòng chọn ID khác.");
						lamMoi();
						return;
					} else {
						daoSanPham.themSanPham(sp);
						String trangThaiDescription = trangThai.getDescription();
						modelSP.addRow(new Object[] { idSanPham, tenSanPham, loaiSanPham.getTenLoaiSanPham(),
								nhaCungCap.getTenNhaCungCap(), kichThuoc, mauSac, sp.getTrangThai(), sp.thue(), giaNhap,
								soLuong, sp.giaBan() });
						loadDataIntoTable();
						lamMoi();
						JOptionPane.showMessageDialog(QuanLySanPhamView.this, "Thêm sản phẩm thành công!");
					}
				} catch (HeadlessException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(QuanLySanPhamView.this,
						"Không tìm thấy loại sản phẩm hoặc nhà cung cấp!");

			}
		}
	}

	private boolean validataFieldsAndShowErrors() {
		String tenSanPham = txtTenSanPham.getText().trim();
		String mauSac = txtMauSac.getText().trim();
		String kichThuoc = txtKichThuoc.getText().trim();
		String soLuong = txtSoLuong.getText().trim();
		String giaNhap = txtGiaNhap.getText().trim();

		if (tenSanPham.isEmpty() && mauSac.isEmpty() && kichThuoc.isEmpty() && soLuong.isEmpty() && giaNhap.isEmpty()) {
			showErrorAndFocus(this, "Vui lòng điền thông tin trước khi thêm.", getFirstEmptyTextField());

			return false;
		}

		if (!isValidName(tenSanPham)) {
			showErrorAndFocus(this, "Tên sản phẩm không hợp lệ. Chỉ chấp nhận chữ và số và không có kí tự đặc biệt",
					txtTenSanPham);
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

	private boolean isValidName(String input) {
		return input.matches("^[\\p{L}0-9\\s]+$");
	}

	private boolean isValidMauSac(String input) {
		return input.matches("^[\\p{L}\\s]+$");
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
			showErrorDialog("Giá trị không hợp lệ. Vui lòng nhập số thực.");
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
			showErrorDialog("Giá trị không hợp lệ. Vui lòng nhập số nguyên.");
			return false;
		}
	}

	private void showErrorDialog(String message) {
		JOptionPane.showMessageDialog(this, message, "Lỗi", JOptionPane.ERROR_MESSAGE);
	}

	private void xoaSanPham() {
		int row = tableSP.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(this, "Bạn cần phải chọn dòng xóa");
		} else {
			try {
				int hoiNhac = JOptionPane.showConfirmDialog(this, "Bạn có chắc xóa không!", "Cảnh báo",
						JOptionPane.YES_NO_OPTION);
				if (hoiNhac == JOptionPane.YES_OPTION) {
					modelSP.removeRow(row);
					String idSanPham = txtIdSanPham.getText();
					daoSanPham.xoaSanPham(idSanPham);
					JOptionPane.showMessageDialog(this, "Xóa sản phẩm thành công");
					lamMoi();
				}
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Xóa sản phẩm không thành công");
			}
		}

	}

	private void lamMoi() {
		txtIdSanPham.setText(generateNewProductID());
		txtTenSanPham.setText("");
		cbLoaiSanPham.setSelectedIndex(0);
		cbNhaCungCap.setSelectedIndex(0);
		txtKichThuoc.setText("");
		txtMauSac.setText("");
		txtGiaBan.setText("");
		txtSoLuong.setText("");
		txtGiaBan.setText("");
		txtGiaNhap.setText("");
		txtTuKhoa.setText("");
		txtTenSanPham.requestFocus();
		txtTenSanPham.selectAll();
	}

	private void capNhatSanPham() {
		int row = tableSP.getSelectedRow();
		if (row >= 0) {
			int update = JOptionPane.showConfirmDialog(this, "Bạn muốn sửa thông tin sản phẩm này", "Thông báo",
					JOptionPane.YES_NO_OPTION);
			if (update == JOptionPane.YES_OPTION) {
				if (!validataFieldsAndShowErrors()) {
					return ;
				}
				String idSanPham = txtIdSanPham.getText().trim();
				String tenSanPham = txtTenSanPham.getText().trim();
				double kichThuoc = Double.parseDouble(txtKichThuoc.getText());
				String mauSac = txtMauSac.getText();
				boolean trangThaiValue = chkTinhTrangKinhDoanh.isSelected();
				TrangThaiSPEnum trangThai = trangThaiValue ? TrangThaiSPEnum.DANG_KINH_DOANH : TrangThaiSPEnum.NGUNG_KINH_DOANH;
				double giaNhap = Double.parseDouble(txtGiaNhap.getText());
				int soLuong = Integer.parseInt(txtSoLuong.getText());

				LoaiSanPham loaiSanPham = null;
				for (LoaiSanPham item : daoLoaiSanPham.getAllLoaiSanPham()) {
					if (item.getTenLoaiSanPham().equals(cbLoaiSanPham.getSelectedItem())) {
						loaiSanPham = item;
						break;
					}
				}

				NhaCungCap nhaCungCap = null;
				for (NhaCungCap item : daoNhaCungCap.getAllNhaCungCap()) {
					if (item.getTenNhaCungCap().equals(cbNhaCungCap.getSelectedItem())) {
						nhaCungCap = item;
						break;
					}
				}
				if (loaiSanPham != null && nhaCungCap != null) {
					SanPhamCon sp = new SanPhamCon();
					sp.setIdSanPham(idSanPham);
					sp.setTenSanPham(tenSanPham);
					sp.setIdLoaiSanPham(loaiSanPham);
					sp.setIdNhaCungCap(nhaCungCap);
					sp.setKichThuoc(kichThuoc);
					sp.setMauSac(mauSac);
					sp.setTrangThai(trangThai);
					sp.setGiaNhap(giaNhap);
					sp.setSoLuong(soLuong);

					daoSanPham.capNhatSanPham(sp);
					JOptionPane.showMessageDialog(QuanLySanPhamView.this, "Cập nhật sản phẩm thành công!");
					loadDataIntoTable();
					lamMoi();
				} else {
					JOptionPane.showMessageDialog(QuanLySanPhamView.this,
							"Không tìm thấy loại sản phẩm hoặc nhà cung cấp!");
				}
			} else {
				JOptionPane.showMessageDialog(QuanLySanPhamView.this, "Vui lòng chọn sản phẩm cần cập nhật!");
			}

		}

	}

	private void loadtableByLoaiSanPham(String selectedLoaiSanPham) {
		modelSP.setRowCount(0);
		try {
			ArrayList<SanPhamCon> sanPhamList = daoSanPham.loadComboBoxByLoaiSanPham(selectedLoaiSanPham);
			for (SanPhamCon sanPham : sanPhamList) {

				String idSanPham = sanPham.getIdSanPham();
				String tenSanPham = sanPham.getTenSanPham();
				String tenLoaiSanPham = sanPham.getIdLoaiSanPham().getIdLoaiSanPham();
				String tenNhaCungCap = sanPham.getIdNhaCungCap().getIdNhaCungCap();
				double kichThuoc = sanPham.getKichThuoc();
				String mauSac = sanPham.getMauSac();
				String trangThai = sanPham.getTrangThai()+"";
				double thue = sanPham.thue();
				double giaNhap = sanPham.getGiaNhap();
				int soLuong = sanPham.getSoLuong();
				double giaBan = sanPham.giaBan();
				modelSP.addRow(new Object[] { idSanPham, tenSanPham, tenLoaiSanPham, tenNhaCungCap, kichThuoc, mauSac,
						trangThai, thue, giaNhap, soLuong, giaBan });
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void loadtableByNhaCungCap(String selectedNhaCungCap) {
		modelSP.setRowCount(0);
		try {
			ArrayList<SanPhamCon> sanPhamList = daoSanPham.loadComboBoxByNhaCungCap(selectedNhaCungCap);
			for (SanPhamCon sanPham : sanPhamList) {
				String idSanPham = sanPham.getIdSanPham();
				String tenSanPham = sanPham.getTenSanPham();
				String tenLoaiSanPham = sanPham.getIdLoaiSanPham().getIdLoaiSanPham();
				String tenNhaCungCap = sanPham.getIdNhaCungCap().getIdNhaCungCap();
				double kichThuoc = sanPham.getKichThuoc();
				String mauSac = sanPham.getMauSac();
				String trangThai = sanPham.getTrangThai()+"";
				double thue = sanPham.thue();
				double giaNhap = sanPham.getGiaNhap();
				int soLuong = sanPham.getSoLuong();
				double giaBan = sanPham.giaBan();
				modelSP.addRow(new Object[] { idSanPham, tenSanPham, tenLoaiSanPham, tenNhaCungCap, kichThuoc, mauSac,
						trangThai, thue, giaNhap, soLuong, giaBan });
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private String generateNewProductID() {
		String idPrefix = "SP";
		int newProductIDNumber = 1;
		String currentDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
		try {
			String previousProductID = daoSanPham.getLatestProductID();
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

	@Override
	public void itemStateChanged(ItemEvent e) {
		Object source = e.getSource();
		if (source == cbLoaiSanPhamSearch) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				String selectedLoaiSanPham = (String) cbLoaiSanPhamSearch.getSelectedItem();
				if (selectedLoaiSanPham.equals("Tất cả")) {
					loadDataIntoTable();
				} else {
					ArrayList<SanPhamCon> sanPhamLoai = daoSanPham.loadComboBoxByLoaiSanPham(selectedLoaiSanPham);
					if (sanPhamLoai.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm cho loại sản phẩm này.",
								"Thông báo", JOptionPane.WARNING_MESSAGE);
						loadDataIntoTable();
						cbLoaiSanPhamSearch.setSelectedItem("Tất cả");
					} else {
						loadtableByLoaiSanPham(selectedLoaiSanPham);
					}
				}
			}
		} else if (source == cbNhaCungCapSearch) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				String selectedNhaCungCap = (String) cbNhaCungCapSearch.getSelectedItem();
				if (selectedNhaCungCap.equals("Tất cả")) {
					loadDataIntoTable();
				} else {
					ArrayList<SanPhamCon> sanPhamList = daoSanPham.loadComboBoxByNhaCungCap(selectedNhaCungCap);
					if (sanPhamList.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm cho nhà cung cấp này.",
								"Thông báo", JOptionPane.WARNING_MESSAGE);
						loadDataIntoTable();
						cbNhaCungCapSearch.setSelectedItem("Tất cả");
					} else {
						loadtableByNhaCungCap(selectedNhaCungCap);
					}
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = tableSP.getSelectedRow();
		if (row >= 0) {
			txtIdSanPham.setText(modelSP.getValueAt(row, 0).toString());
			txtTenSanPham.setText(modelSP.getValueAt(row, 1).toString());
			cbLoaiSanPham.setSelectedItem(modelSP.getValueAt(row, 2).toString());
			cbNhaCungCap.setSelectedItem(modelSP.getValueAt(row, 3).toString());
			txtKichThuoc.setText(modelSP.getValueAt(row, 4).toString());
			txtMauSac.setText(modelSP.getValueAt(row, 5).toString());
			
			String trangThaiValue = modelSP.getValueAt(row, 6).toString();
			TrangThaiSPEnum trangThai = TrangThaiSPEnum.getByName(trangThaiValue);

			chkTinhTrangKinhDoanh.setSelected(trangThai == TrangThaiSPEnum.DANG_KINH_DOANH);
			
			txtGiaNhap.setText(modelSP.getValueAt(row, 8).toString());
			txtSoLuong.setText(modelSP.getValueAt(row, 9).toString());
			txtGiaBan.setText(modelSP.getValueAt(row, 10).toString());
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
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			Object o = e.getSource();
			if (o == txtTenSanPham || o == txtKichThuoc || o == txtMauSac || o == txtSoLuong || o == txtGiaNhap
					|| o == cbLoaiSanPham || o == cbNhaCungCap) {
				themSanPham();
			}
		} else if (e.getKeyCode() == KeyEvent.VK_F5) {
			lamMoi();
		} else if (tableSP.getSelectedRow() != -1) {
			if (e.getKeyCode() == KeyEvent.VK_F5) {
				lamMoi();
				loadDataIntoTable();
			}

		} else if (e.getKeyCode() == KeyEvent.VK_TAB) {
			tabbedPane.setSelectedIndex(1);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		SwingUtilities.invokeLater(() -> {
			Object o = e.getSource();
			if (o.equals(txtTuKhoa)) {
				DefaultTableModel model = (DefaultTableModel) tableSP.getModel();
				TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(model);
				tableSP.setRowSorter(tr);
				tr.setRowFilter(RowFilter.regexFilter("(?i)" + txtTuKhoa.getText().trim(), 1));
			} else if (e.getKeyCode() == KeyEvent.VK_F5) {
				lamMoi();
				loadDataIntoTable();
			} else if (e.getKeyCode() == KeyEvent.VK_F10) {
				lamMoi();
				loadDataIntoTable();
			} else if (o.equals(txtGiaNhap)) {
				calculateSellingPrice(txtGiaNhap, txtGiaBan);
			}
		});
	}

	private void calculateSellingPrice(JTextField txtGiaNhap2, JTextField txtGiaBan2) {
		try {
			double giaNhap = Double.parseDouble(txtGiaNhap2.getText());
			double thue = giaNhap * 0.05;
			double sellingPrice = giaNhap + (giaNhap * 0.55) + thue;
			txtGiaBan2.setText(String.valueOf(sellingPrice));
		} catch (NumberFormatException ex) {
			txtGiaBan2.setText("Vui lòng nhập số hợp lệ.");
		}

	}

}