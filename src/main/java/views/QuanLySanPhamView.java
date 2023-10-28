package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class QuanLySanPhamView extends JPanel {
	private JTabbedPane tabbedPane;
	private JTextField txtTenSanPham, txtIdSanPham, txtKichThuoc, txtSoLuong, txtMauSac, txtGiaBan,txtHinhAnh;
	private JComboBox<String> cbLoaiSanPham, cbNhaCungCap,cbTinhTrangKhinhDoanh;
	private JCheckBox chkThueVAT, chkTinhTrangKinhDoanh;
	private JLabel lblTieuDe,lblAnhSanPham, lblHinhAnh,lblIDSanPham,lblTenSanPham,lblNhaCungCap,lblLoaiSanPham,lblKichThuoc,lblMauSac,
	lblSoLuong,lblGiaBan,lblThueVAT,lblTinhTrangKinhDoanh;
	private JTable sanPhamTable;
	private JPanel pnCenter,pnChucNang,pnDanhMuc,pnMain;
	private JButton btnThemSP, btnXoaSP,btnNhapSP,btnCapNhatSP,btnHienThiLS;
	public QuanLySanPhamView() {
		setLayout(new BorderLayout(8,6));
		tabbedPane = new JTabbedPane();

		// Tab Sản phẩm
		JPanel sanPhamPanel = new JPanel();
		sanPhamPanel.setLayout(new BorderLayout());
		sanPhamPanel.setBorder(new EmptyBorder(10,10,10,10));
		// center
		pnCenter = new JPanel();
		pnCenter.setLayout(new GridLayout(6, 6, 10, 10));
		pnCenter.setBorder(BorderFactory.createTitledBorder("Thông tin sản phẩm"));
		
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
        
        
        pnChucNang=new JPanel(new GridLayout(1,5,10,40));
        pnChucNang.setBorder(BorderFactory.createTitledBorder("Chức năng"));
        btnThemSP=new JButton("THÊM SẢN PHẨM");
        btnNhapSP =new JButton("NHẬP SẢN PHẨM");
        btnCapNhatSP=new JButton("CẬP NHẬT SẢN PHẨM");
        btnXoaSP=new JButton("XÓA SẢN PHẨM");
        btnHienThiLS=new JButton("LỊCH SỬ XÓA");
        pnChucNang.add(btnThemSP);
        pnChucNang.add(btnNhapSP);
        pnChucNang.add(btnCapNhatSP);
        pnChucNang.add(btnXoaSP);
        pnChucNang.add(btnHienThiLS);
        
        
        
        pnMain.add(pnChucNang,BorderLayout.NORTH);
        
        pnDanhMuc = new JPanel(new BorderLayout());
        pnDanhMuc.setBorder(BorderFactory.createTitledBorder("Danh mục"));
        String[] columnNames = {"Column 1", "Column 2","column"};
        Object[][] data = {{"test thu", "test thu 2","test hoai hong ra"}, {"Data 3", "Data 4","HHDSHD"},{"Data 3", "Data 4","HHDSHD"}};
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        pnDanhMuc.add(scrollPane);
        pnMain.add(pnDanhMuc,BorderLayout.CENTER);
        
		// Bảng
		sanPhamTable = new JTable();
		
		
        
        sanPhamPanel.add(pnCenter, BorderLayout.NORTH);
		sanPhamPanel.add(pnMain,BorderLayout.CENTER);
		
		tabbedPane.addTab("Sản phẩm", sanPhamPanel);
		add(tabbedPane);
	}
}
