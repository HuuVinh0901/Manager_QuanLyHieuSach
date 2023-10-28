package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class LoaiNhaSanXuatView extends JPanel{
	private JPanel pnMain;
	private JPanel pnHeader, pnTitle;
	private JPanel pnCener;
	private JLabel lblTitle;
	private JPanel pnMainThongTin, pnLoaiThongTin, pnChucNang;
	private JLabel lblIdLoaiSanPham;
	private JLabel lblTenLoaiSanPham;
	private JLabel lblTuKhoa;
	private JTextField txtIdLoaiSanPham;
	private JTextField txtTenLoaiSanPham;
	private JTextField txtTimKiem;
	private JButton btnThem;
	private JButton btnCapNhat;
	private JButton btnXoa;
	private JButton btnLamMoi;
	private JButton btnTimKiem;
	private JPanel pnSouth, pnSouthNorth, pnSouthBottom,pnDanhMuc;
	private JTable tableSP;
	private DefaultTableModel modelSP;

	public LoaiNhaSanXuatView() {
		setLayout(new BorderLayout());
		pnMain = new JPanel(new BorderLayout(8, 6));

		pnHeader = new JPanel();
		pnHeader.add(lblTitle = new JLabel("Quản Lý Nhà Cung Cấp"));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTitle.setForeground(new Color(26, 102, 227));
		pnHeader.setBackground(new Color(208, 225, 253));

		// layout thong tin
		pnMainThongTin = new JPanel(new BorderLayout());
		pnMainThongTin.setBorder(BorderFactory.createTitledBorder("Thông tin nhà cung cấp"));

		pnLoaiThongTin = new JPanel(new GridLayout(2, 2, 10, 10));

		
		lblIdLoaiSanPham = new JLabel("Mã nhà cung cấp:");
		txtIdLoaiSanPham = new JTextField(20);
		txtIdLoaiSanPham.setPreferredSize(new Dimension(150, 30));

		lblTenLoaiSanPham = new JLabel("Tên nhà cung cấp:");
		txtTenLoaiSanPham = new JTextField(20);
		txtTenLoaiSanPham.setPreferredSize(new Dimension(150, 30));
		
		Insets labelInsets = new Insets(0, 60, 0, 10); 
        lblIdLoaiSanPham.setBorder(new EmptyBorder(labelInsets));
        lblTenLoaiSanPham.setBorder(new EmptyBorder(labelInsets));
		Dimension labelSize = new Dimension(200, 30);
		lblIdLoaiSanPham.setPreferredSize(labelSize);
		lblTenLoaiSanPham.setPreferredSize(labelSize);
		
		// Sử dụng FlowLayout cho từng dòng để giữ các thành phần gần nhau
		JPanel idPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		idPanel.add(lblIdLoaiSanPham);
		idPanel.add(txtIdLoaiSanPham);

		JPanel tenPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		tenPanel.add(lblTenLoaiSanPham);
		tenPanel.add(txtTenLoaiSanPham);

		pnLoaiThongTin.add(idPanel);
		pnLoaiThongTin.add(tenPanel);

		pnChucNang = new JPanel(new FlowLayout(5));
		btnThem = new JButton("Thêm");
		btnCapNhat = new JButton("Sửa");
		btnLamMoi = new JButton("Làm mới");
		btnXoa = new JButton("Xóa");
		btnThem.setBackground(new Color(208, 225, 253));
		btnThem.setForeground(new Color(26, 102, 227));
		
		btnCapNhat.setBackground(new Color(208, 225, 253));
		btnCapNhat.setForeground(new Color(26, 102, 227));
		
		btnLamMoi.setBackground(new Color(208, 225, 253));
		btnLamMoi.setForeground(new Color(26, 102, 227));
		
		btnXoa.setBackground(new Color(208, 225, 253));
		btnXoa.setForeground(new Color(26, 102, 227));
		pnChucNang.add(btnThem);
		pnChucNang.add(btnCapNhat);
		pnChucNang.add(btnXoa);
		pnChucNang.add(btnLamMoi);
		
		Insets btnInsert = new Insets(50, 60, 0, 0); 
        pnChucNang.setBorder(new EmptyBorder(btnInsert));
        
        
        // phan top
        pnSouth = new JPanel(new BorderLayout());
        pnSouthNorth = new JPanel(new FlowLayout(5));
        lblTuKhoa = new JLabel("Từ khóa:");
        txtTimKiem = new JTextField(20);
        btnTimKiem=new JButton("Tìm kiếm");
        pnSouthNorth.add(lblTuKhoa);
        pnSouthNorth.add(txtTimKiem);
        pnSouthNorth.add(btnTimKiem);
        // phan bottom
        pnSouthBottom = new JPanel(new BorderLayout());
        pnSouthBottom = new JPanel(new BorderLayout());
        pnSouthBottom.setBorder(BorderFactory.createTitledBorder("Danh mục"));
        modelSP = new DefaultTableModel();
        tableSP = new JTable();
        modelSP.addColumn("Mã Nhà Cung Cấp");
        modelSP.addColumn("Tên Nhà Cung Cấp");
        tableSP.setModel(modelSP);
        JScrollPane scrollPane = new JScrollPane(tableSP);
        pnSouthBottom.add(scrollPane);
        
        
        pnSouth.add(pnSouthNorth,BorderLayout.NORTH);
        pnSouth.add(pnSouthBottom,BorderLayout.CENTER);
        
        
		pnMainThongTin.add(pnLoaiThongTin, BorderLayout.NORTH);
		pnMainThongTin.add(pnChucNang, BorderLayout.CENTER);
		
		pnMain.add(pnHeader, BorderLayout.NORTH);
		pnMain.add(pnMainThongTin, BorderLayout.CENTER);
		pnMain.add(pnSouth, BorderLayout.SOUTH);
		this.add(pnMain);
	}
}
