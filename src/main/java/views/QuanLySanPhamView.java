package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import models.LoaiSanPham;
import models.NhaCungCap;
import models.SanPhamCha;
import models.SanPhamCon;
import utils.TrangThaiSPEnum;

public class QuanLySanPhamView extends JPanel implements ActionListener, ItemListener, MouseListener ,KeyListener{
	private JTabbedPane tabbedPane;
	private JTextField txtTenSanPham, txtIdSanPham, txtKichThuoc, txtSoLuong, txtMauSac, txtGiaBan,
			txtGiaNhap;
	private JComboBox<String> cbTinhTrangKhinhDoanh;
	private JComboBox<String> cbLoaiSanPham;
	private JComboBox<String> cbNhaCungCap;
	private JCheckBox chkTinhTrangKinhDoanh;
	private JLabel lblTieuDe, lblIDSanPham, lblTenSanPham, lblNhaCungCap, lblLoaiSanPham,
			lblKichThuoc, lblMauSac, lblSoLuong, lblGiaBan, lblThueVAT, lblTinhTrangKinhDoanh, lblGiaNhap;
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

	public QuanLySanPhamView() {
		daoLoaiSanPham = new DAOLoaiSanPham();
		daoNhaCungCap = new DAONhaCungCap();
		daoSanPham = new DAOQuanLySanPham();

		setLayout(new BorderLayout(8, 6));
		tabbedPane = new JTabbedPane();
		// tab sách
		JPanel sachPanel = new JPanel();
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
//		pnChucNangTimKiem.add(btnTimKiem);
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

//		loadData();
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
			String trangThai = sanPham.getTrangThai().getDescription();
			double thue = sanPham.thue();
			double giaNhap = sanPham.getGiaNhap();
			int soLuong = sanPham.getSoLuong();
			double giaBan = sanPham.giaBan();
			modelSP.addRow(new Object[] {  idSanPham, tenSanPham, tenLoaiSanPham, tenNhaCungCap, kichThuoc,
					mauSac, trangThai, thue, giaNhap, soLuong, giaBan });
		}
	}

	private void loadData() {
		modelSP.setRowCount(0);
		for (SanPhamCha sp : daoSanPham.getAllSanPham()) {
			String tenLoaiSanPham = sp.getIdLoaiSanPham().getIdLoaiSanPham();
			String tenNhaCungCap = sp.getIdNhaCungCap().getIdNhaCungCap();

			String[] row = {  sp.getIdSanPham(), sp.getTenSanPham(), tenLoaiSanPham,
					tenNhaCungCap, sp.getKichThuoc() + "", sp.getMauSac(), sp.getTrangThai() + "" };
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
		}else if(o.equals(btnLamMoi)) {
			lamMoi();
		}else if(o.equals(btnXemTatCa)) {
			lamMoi();
			loadDataIntoTable();
		}else if(o.equals(btnXoaSP)) {
			xoaSanPham();
		}
	}

	private void xoaSanPham() {
		int row = tableSP.getSelectedRow();
		if(row == -1) {
			JOptionPane.showMessageDialog(this, "Bạn cần phải chọn dòng xóa");
		}else {
			try {
				int hoiNhac = JOptionPane.showConfirmDialog(this, "Bạn có chắc xóa không!","Cảnh báo",JOptionPane.YES_NO_OPTION);
				if(hoiNhac==JOptionPane.YES_OPTION) {
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
	}

	private void capNhatSanPham() {
	    int row = tableSP.getSelectedRow();
	    if (row >= 0) {
	        int update = JOptionPane.showConfirmDialog(this, "Bạn muốn sửa thông tin sản phẩm này", "Thông báo",
	                JOptionPane.YES_NO_OPTION);
	        if (update == JOptionPane.YES_OPTION) {
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
	                if (item.getTenLoaiSanPham().equals(cbLoaiSanPham.getSelectedItem().toString())) {
	                    loaiSanPham = item;
	                    break;
	                }
	            }

	            NhaCungCap nhaCungCap = null;
	            for (NhaCungCap item : daoNhaCungCap.getAllNhaCungCap()) {
	                if (item.getTenNhaCungCap().equals(cbNhaCungCap.getSelectedItem().toString())) {
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
	                JOptionPane.showMessageDialog(QuanLySanPhamView.this, "Không tìm thấy loại sản phẩm hoặc nhà cung cấp!");
	            }
	        }
	    } else {
	        JOptionPane.showMessageDialog(QuanLySanPhamView.this, "Vui lòng chọn sản phẩm cần cập nhật!");
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
				String trangThai = sanPham.getTrangThai().getDescription();
				double thue = sanPham.thue();
				double giaNhap = sanPham.getGiaNhap();
				int soLuong = sanPham.getSoLuong();
				double giaBan = sanPham.giaBan();
				modelSP.addRow(new Object[] { idSanPham, tenSanPham, tenLoaiSanPham, tenNhaCungCap, kichThuoc,
						mauSac, trangThai, thue, giaNhap, soLuong, giaBan });
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
				String trangThai = sanPham.getTrangThai().getDescription();
				double thue = sanPham.thue();
				double giaNhap = sanPham.getGiaNhap();
				int soLuong = sanPham.getSoLuong();
				double giaBan = sanPham.giaBan();
				modelSP.addRow(new Object[] { idSanPham, tenSanPham, tenLoaiSanPham, tenNhaCungCap, kichThuoc,
						mauSac, trangThai, thue, giaNhap, soLuong, giaBan });
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void themSanPham() {
		String idSanPham = txtIdSanPham.getText();
		String tenSanPham = txtTenSanPham.getText();
		double kichThuoc = Double.parseDouble(txtKichThuoc.getText());
		String mauSac = txtMauSac.getText();
		boolean trangThaiValue = chkTinhTrangKinhDoanh.isSelected();
		TrangThaiSPEnum trangThai = trangThaiValue ? TrangThaiSPEnum.DANG_KINH_DOANH : TrangThaiSPEnum.NGUNG_KINH_DOANH;
		double giaNhap = Double.parseDouble(txtGiaNhap.getText());
		String tenLoaiSanPham = (String) cbLoaiSanPham.getSelectedItem();
		String tenNhaCungCap = (String) cbNhaCungCap.getSelectedItem();
		int soLuong = Integer.parseInt(txtSoLuong.getText());
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
		if (loaiSanPham != null && nhaCungCap != null) {
			SanPhamCon sp = new SanPhamCon();
			sp.setIdSanPham(generateNewProductID());
			sp.setTenSanPham(tenSanPham);
			sp.setIdLoaiSanPham(loaiSanPham);
			sp.setIdNhaCungCap(nhaCungCap);
			sp.setKichThuoc(kichThuoc);
			sp.setMauSac(mauSac);
			sp.setTrangThai(trangThai);
			sp.setGiaNhap(giaNhap);
			sp.setSoLuong(soLuong);
			daoSanPham.themSanPham(sp);
			String trangThaiDescription = trangThai.getDescription();
			modelSP.addRow(new Object[] {  generateNewProductID(), tenSanPham,
					loaiSanPham.getTenLoaiSanPham(), nhaCungCap.getTenNhaCungCap(), kichThuoc, mauSac,
					trangThaiDescription, sp.thue(), giaNhap, soLuong, sp.giaBan()});
			loadDataIntoTable();
			lamMoi();
			JOptionPane.showMessageDialog(QuanLySanPhamView.this, "Thêm sản phẩm thành công!");
		} else {
			JOptionPane.showMessageDialog(QuanLySanPhamView.this, "Không tìm thấy loại sản phẩm hoặc nhà cung cấp!");
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
				}else {
					ArrayList<SanPhamCon> sanPhamLoai = daoSanPham.loadComboBoxByLoaiSanPham(selectedLoaiSanPham);
					if (sanPhamLoai.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm cho nhà cung cấp này.",
								"Thông báo", JOptionPane.WARNING_MESSAGE);
						loadDataIntoTable();
						cbLoaiSanPhamSearch.setSelectedItem("Tất cả");
					} else {
						loadtableByLoaiSanPham(selectedLoaiSanPham);
					}
				}
			}
		}else if (source == cbNhaCungCapSearch) {
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
	        boolean isKinhDoanh = trangThai == TrangThaiSPEnum.DANG_KINH_DOANH;
	        chkTinhTrangKinhDoanh.setSelected(isKinhDoanh);
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
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
	    Object o = e.getSource();
	    if (o.equals(txtTuKhoa)) {
	        DefaultTableModel model = (DefaultTableModel) tableSP.getModel();
	        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(model);
	        tableSP.setRowSorter(tr);
	        tr.setRowFilter(RowFilter.regexFilter("(?i)" + txtTuKhoa.getText().trim(), 1));
	    }
	}


}