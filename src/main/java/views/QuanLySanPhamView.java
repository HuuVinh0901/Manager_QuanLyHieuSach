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
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import DAO.CategoryDAO;
import connection.ConnectDB;
import dao.DAOLoaiSanPham;
import dao.DAONhaCungCap;
import dao.DAOQuanLySanPham;
import models.LoaiSanPham;
import models.NhaCungCap;
import models.SanPham;
import utils.TrangThaiSPEnum;

public class QuanLySanPhamView extends JPanel implements ActionListener {
	private JTabbedPane tabbedPane;
	private JTextField txtTenSanPham, txtIdSanPham, txtKichThuoc, txtSoLuong, txtMauSac, txtGiaBan, txtHinhAnh;
	private JComboBox<String> cbLoaiSanPham, cbNhaCungCap, cbTinhTrangKhinhDoanh;
	private JCheckBox chkThueVAT, chkTinhTrangKinhDoanh;
	private JLabel lblTieuDe, lblAnhSanPham, lblHinhAnh, lblIDSanPham, lblTenSanPham, lblNhaCungCap, lblLoaiSanPham,
			lblKichThuoc, lblMauSac, lblSoLuong, lblGiaBan, lblThueVAT, lblTinhTrangKinhDoanh;
	private JTable sanPhamTable;
	private JPanel pnCenter, pnChucNang, pnDanhMuc, pnMain;
	private JButton btnThemSP, btnXoaSP, btnNhapSP, btnCapNhatSP, btnHienThiLS, btnLamMoi;
	private JTable tableSP;
	private DefaultTableModel modelSP;
	private DAOLoaiSanPham daoLoaiSanPham;
	private DAONhaCungCap daoNhaCungCap;
	private DAOQuanLySanPham daoSanPham;

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
		pnCenter.setLayout(new GridLayout(7, 7, 10, 10));
		pnCenter.setBorder(BorderFactory.createTitledBorder("Thông tin sản phẩm"));

		lblHinhAnh = new JLabel("Hình ảnh sản phẩm(*):");
		txtHinhAnh = new JTextField();
		lblIDSanPham = new JLabel("ID Sản Phẩm(*):");
		txtIdSanPham = new JTextField();
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
		lblGiaBan = new JLabel("Giá Bán (*):");
		txtGiaBan = new JTextField();
		lblThueVAT = new JLabel("Thuế VAT:");
		chkThueVAT = new JCheckBox();
		lblTinhTrangKinhDoanh = new JLabel("Tình Trạng Kinh Doanh(*):");
		chkTinhTrangKinhDoanh = new JCheckBox();

//        
//        Insets labelInsets = new Insets(0, 60, 0, 10); 
//        Dimension labelSize = new Dimension(100, 30);
//        
//        JPanel idPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        lblIDSanPham.setBorder(new EmptyBorder(labelInsets));
//        lblIDSanPham.setPreferredSize(labelSize);

//		idPanel.add(lblIDSanPham);
//		idPanel.add(txtIdSanPham);

		pnCenter.add(lblHinhAnh);
		pnCenter.add(txtHinhAnh);
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
		pnCenter.add(lblGiaBan);
		pnCenter.add(txtGiaBan);
		pnCenter.add(lblThueVAT);
		pnCenter.add(chkThueVAT);
		pnCenter.add(lblTinhTrangKinhDoanh);
		pnCenter.add(chkTinhTrangKinhDoanh);

		pnMain = new JPanel(new BorderLayout());

		pnChucNang = new JPanel(new GridLayout(1, 6, 10, 40));
		pnChucNang.setBorder(BorderFactory.createTitledBorder("Chức năng"));
		btnThemSP = new JButton("THÊM SẢN PHẨM");
		btnNhapSP = new JButton("NHẬP SẢN PHẨM");
		btnCapNhatSP = new JButton("CẬP NHẬT SẢN PHẨM");
		btnXoaSP = new JButton("XÓA SẢN PHẨM");
		btnHienThiLS = new JButton("LỊCH SỬ XÓA");
		btnLamMoi = new JButton("LÀM MỚI");
		pnChucNang.add(btnThemSP);
		pnChucNang.add(btnNhapSP);
		pnChucNang.add(btnCapNhatSP);
		pnChucNang.add(btnXoaSP);
		pnChucNang.add(btnHienThiLS);
		pnChucNang.add(btnLamMoi);

		pnMain.add(pnChucNang, BorderLayout.NORTH);

		pnDanhMuc = new JPanel(new BorderLayout());
		pnDanhMuc.setBorder(BorderFactory.createTitledBorder("Danh mục"));
		modelSP = new DefaultTableModel();
		tableSP = new JTable();
		modelSP.addColumn("Hình Ảnh");
		modelSP.addColumn("ID Sản Phẩm");
		modelSP.addColumn("Tên Sản Phẩm");
		modelSP.addColumn("Loại Sản Phẩm");
		modelSP.addColumn("Nhà Cung Cấp");
		modelSP.addColumn("Kích Thước");
		modelSP.addColumn("Màu Sắc");
		modelSP.addColumn("Trạng Thái");
		modelSP.addColumn("Thuế");
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
		loadData();
		loadComboxBoxLoaiSanPham();
		loadComboxBoxNhaCungCap();
		btnThemSP.addActionListener(this);
	}

	private void loadData() {
		modelSP.setRowCount(0);
		for (SanPham sp : daoSanPham.getAllSanPham()) {
			String[] row = { sp.getHinhAnhSanPham(), sp.getIdSanPham(), sp.getTenSanPham(),
					sp.getIdLoaiSanPham().getTenLoaiSanPham(), sp.getIdNhaCungCap().getTenNhaCungCap(),
					sp.getKichThuoc() + "", sp.getMauSac(), sp.getTrangThai() + "", sp.getThue() + "",
					sp.getSoLuong() + "", sp.getGiaBan() + "" };
			modelSP.addRow(row);
		}

	}

	private void loadComboxBoxLoaiSanPham() {
		ArrayList<LoaiSanPham> danhSachLoaiSanPham = daoLoaiSanPham.getAllLoaiSanPham();
		for (LoaiSanPham loaiSanPham : danhSachLoaiSanPham) {
			cbLoaiSanPham.addItem(loaiSanPham.getTenLoaiSanPham());
		}
	}

	private void loadComboxBoxNhaCungCap() {
		ArrayList<NhaCungCap> danhSachNhaCungCap = daoNhaCungCap.getAllNhaCungCap();
		for (NhaCungCap ncc : danhSachNhaCungCap) {
			cbNhaCungCap.addItem(ncc.getTenNhaCungCap());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThemSP)) {
			themSanPham();
		}
	}

	private void themSanPham() {
		String hinhAnhSanPham = txtHinhAnh.getText();
		String idSanPham = txtIdSanPham.getText();
		String tenSanPham = txtTenSanPham.getText();
		double kichThuoc = Double.parseDouble(txtKichThuoc.getText());
		String mauSac = txtMauSac.getText();
		boolean trangThaiValue = chkTinhTrangKinhDoanh.isSelected();
		TrangThaiSPEnum trangThai = trangThaiValue ? TrangThaiSPEnum.DANG_KINH_DOANH : TrangThaiSPEnum.NGUNG_KINH_DOANH;
		double thue = tinhThue();
		int soLuong = laySoLuong();
		double giaBan = tinhGiaBan();
		String tenLoaiSanPham = "";
		String categoryNameCbo = cbLoaiSanPham.getSelectedItem().toString();

		// Lấy tên loại sản phẩm dựa trên idSanPham từ daoLoaiSanPham
		if (!idSanPham.isEmpty()) {
		    int idSanPhamInt = Integer.parseInt(idSanPham);
		    tenLoaiSanPham = daoLoaiSanPham.getLoaiSanPhamNameByID(idSanPhamInt);
		}
        
        
//		String tenLoaiSanPham = (String) cbLoaiSanPham.getSelectedItem();
//		LoaiSanPham selectedLoaiSanPham = daoLoaiSanPham.getLoaiSanPhamByName(tenLoaiSanPham);

		String tenNhaCungCap = (String) cbNhaCungCap.getSelectedItem();
		NhaCungCap selectedNhaCungCap = daoNhaCungCap.getNhaCungCapTheoTen(tenNhaCungCap);

		SanPham sp = new SanPham(hinhAnhSanPham, idSanPham, tenSanPham, tenLoaiSanPham, selectedNhaCungCap, kichThuoc, mauSac, trangThai);
		try {
			daoSanPham.themSanPham(sp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		modelSP.addRow(new Object[] { hinhAnhSanPham, idSanPham, tenSanPham, selectedLoaiSanPham, selectedNhaCungCap,
//				kichThuoc, mauSac, trangThai });
	}


	private double tinhGiaBan() {
		double giaBan = 0;
		return giaBan;
	}

	private int laySoLuong() {
		int soLuong = 0;
		return soLuong;
	}

	private double tinhThue() {
		double thue = 0.0;
		return thue;
	}

}
