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

import connection.ConnectDB;
import dao.DAOLoaiSanPham;
import dao.DAONhaCungCap;
import dao.DAOQuanLySanPham;
import models.LoaiSanPham;
import models.NhaCungCap;
import models.SanPhamCha;
import models.SanPhamCon;
import utils.TrangThaiSPEnum;

public class QuanLySanPhamView extends JPanel implements ActionListener {
	private JTabbedPane tabbedPane;
	private JTextField txtTenSanPham, txtIdSanPham, txtKichThuoc, txtSoLuong, txtMauSac, txtGiaBan, txtHinhAnh,
			txtGiaNhap;
	private JComboBox<String> cbTinhTrangKhinhDoanh;
	private JComboBox<String> cbLoaiSanPham;
	private JComboBox<String> cbNhaCungCap;
	private JCheckBox chkThueVAT, chkTinhTrangKinhDoanh;
	private JLabel lblTieuDe, lblAnhSanPham, lblHinhAnh, lblIDSanPham, lblTenSanPham, lblNhaCungCap, lblLoaiSanPham,
			lblKichThuoc, lblMauSac, lblSoLuong, lblGiaBan, lblThueVAT, lblTinhTrangKinhDoanh, lblGiaNhap;
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
		lblGiaNhap = new JLabel("Giá Nhập (*)");
		txtGiaNhap = new JTextField();
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

		pnCenter.add(lblGiaNhap);
		pnCenter.add(txtGiaNhap);

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
		btnThemSP.addActionListener(this);
	}

	private void loadDataIntoTable() {
		modelSP.setRowCount(0);
		ArrayList<SanPhamCon> sanPhamList = daoSanPham.getAllSanPhamLoadData();
		for (SanPhamCon sanPham : sanPhamList) {
			String hinhAnh = sanPham.getHinhAnhSanPham();
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
			modelSP.addRow(new Object[] { hinhAnh, idSanPham, tenSanPham, tenLoaiSanPham, tenNhaCungCap, kichThuoc,
					mauSac, trangThai, thue, giaNhap,soLuong, giaBan });
		}
	}

	private void loadData() {
		modelSP.setRowCount(0);
		for (SanPhamCha sp : daoSanPham.getAllSanPham()) {
			String tenLoaiSanPham = sp.getIdLoaiSanPham().getIdLoaiSanPham();
			String tenNhaCungCap = sp.getIdNhaCungCap().getIdNhaCungCap();

			String[] row = { sp.getHinhAnhSanPham(), sp.getIdSanPham(), sp.getTenSanPham(), tenLoaiSanPham,
					tenNhaCungCap, sp.getKichThuoc() + "", sp.getMauSac(), sp.getTrangThai() + "" };
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
			sp.setHinhAnhSanPham(hinhAnhSanPham);
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
				daoSanPham.themSanPham(sp);
				String trangThaiDescription = trangThai.getDescription();
				modelSP.addRow(new Object[] { hinhAnhSanPham, generateNewProductID(), tenSanPham, loaiSanPham.getTenLoaiSanPham(),
						nhaCungCap.getTenNhaCungCap(), kichThuoc, mauSac, trangThaiDescription, sp.thue(), giaNhap,soLuong,
						sp.giaBan() });
//				loadDataIntoTable();
				JOptionPane.showMessageDialog(QuanLySanPhamView.this, "Thêm sản phẩm thành công!");
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(QuanLySanPhamView.this, "Lỗi khi thêm sản phẩm!");
				e.printStackTrace();
			}
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
}
