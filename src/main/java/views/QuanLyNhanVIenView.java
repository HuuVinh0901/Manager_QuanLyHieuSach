package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import controllers.MenuItem;

public class QuanLyNhanVIenView extends JPanel{
	private JDateChooser chooserNgaySinh;
	private JTextField txtTenNV;
	private JTextField txtsdt;
	private JTextField txtEmail;
	private JTextField txtDiaChi;
	private JTextField txtLuong;
	private JTextField txtIDNhanVien;
	private JTextField txtNS;
	private JTextField txtTimKiem;
	private JLabel lbTenNV;
	private JLabel lbsdt;
	private JLabel lbEmail;
	private JLabel lbDiaChi;
	private JLabel lbNgaySinh;
	private JLabel lbGioiTinh;
	private JLabel lbLuong;
	private JLabel lbIDNhanVien;
	private JLabel lbTimKiem;
	private JRadioButton rbNam;
	private JRadioButton rbNu;
	private JComboBox<Object> cbTrangThai,cbChucVu;
	private JLabel lbChucVu;
	private JLabel lbTrangThai;
	private JTable tableNhanVien;
	private DefaultTableModel modelNhanVien;
	private JButton btnThemNV;
	private JButton btnCapNhatNV;
	private JButton btnXoaNV;
	private JButton btnLamMoi;
	public QuanLyNhanVIenView() {
		setLayout(new BorderLayout());
////		Tiêu đề
		JPanel NhanVienPanel=new JPanel();
		NhanVienPanel.setLayout(new BorderLayout());
		NhanVienPanel.setBorder(new EmptyBorder(10,10,10,10));
		
		JLabel lblTieuDe = new JLabel("QUẢN LÝ NHÂN VIÊN");
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 30));
		lblTieuDe.setForeground(Color.black);
		JPanel PanelNouth =new JPanel();
		
		add(NhanVienPanel);
		NhanVienPanel.add(PanelNouth,BorderLayout.NORTH);
		PanelNouth.add(lblTieuDe);
		
		JPanel pnCenter = new JPanel();
		pnCenter.setLayout(new GridLayout(5,10, 10, 10));
		pnCenter.setBorder(BorderFactory.createTitledBorder("Nhập thông tin nhân viên"));
		
//        lbIDNhanVien = new JLabel("ID nhân viên:");
//        
//        txtIDNhanVien = new JTextField();
//        txtIDNhanVien.setEnabled(false);
        lbTenNV = new JLabel("Tên nhân viên:");
        txtTenNV = new JTextField();
        lbsdt = new JLabel("Số điện thoại:");
        txtsdt = new JTextField();
        lbEmail = new JLabel("Email:");
        txtEmail = new JTextField();
        cbChucVu = new JComboBox<Object>(new Object[] { "Quản lý", "Nhân viên" });
		cbTrangThai = new JComboBox<Object>(new Object[] { "Đang làm việc", "Đã nghỉ việc" });
		lbDiaChi = new JLabel("Địa chỉ:");
	    txtDiaChi = new JTextField();
	    lbChucVu = new JLabel("Chức vụ:");
	    lbTrangThai = new JLabel("Trạng thái:");
	    lbGioiTinh=new JLabel("Giới tính");
	    lbNgaySinh=new JLabel("Ngày sinh:");
	    txtNS= new JTextField();
	    lbLuong=new JLabel("Lương:");
	    txtLuong = new JTextField();
        
	    chooserNgaySinh = new JDateChooser();
		chooserNgaySinh.getCalendarButton().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chooserNgaySinh.setBounds(100, 310, 200, 40);
		chooserNgaySinh.setDateFormatString("dd/MM/yyyy");
//		chooserNgaySinh.setDate(dNgayHienTai);
		chooserNgaySinh.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		chooserNgaySinh.setFont(new Font("SansSerif", Font.PLAIN, 15));
		chooserNgaySinh.getCalendarButton().setPreferredSize(new Dimension(30, 24));
		chooserNgaySinh.getCalendarButton().setBackground(new Color(102, 0, 153));
		chooserNgaySinh.getCalendarButton().setToolTipText("Chọn ngày sinh");
        pnCenter.add(lbTenNV);
        pnCenter.add(txtTenNV);
        pnCenter.add(lbsdt);
        pnCenter.add(txtsdt);
        pnCenter.add(lbEmail);
        pnCenter.add(txtEmail);
        pnCenter.add(lbDiaChi);
        pnCenter.add(txtDiaChi);
        pnCenter.add(lbLuong);
        pnCenter.add(txtLuong);
        pnCenter.add(lbNgaySinh);
        pnCenter.add(chooserNgaySinh);
        pnCenter.add(lbChucVu);
        pnCenter.add(cbChucVu);
        pnCenter.add(lbTrangThai);
        pnCenter.add(cbTrangThai);
        pnCenter.add(lbGioiTinh);
        pnCenter.add(rbNam=new JRadioButton("Nam"));
        pnCenter.add(rbNu=new JRadioButton("Nữ"));
        
		
//		chooserNgaySinh.setIcon((ImageIcon) iconCalendar);
        
        //Tạo button groud giới tính
        ButtonGroup groupGT = new ButtonGroup();
        groupGT.add(rbNam);
        groupGT.add(rbNu);
        
        lbTimKiem=new JLabel("Tìm kiếm nhân viên:");
        txtTimKiem=new JTextField();
        JPanel panelSearch=new JPanel();
        panelSearch.add(lbTimKiem);
        panelSearch.add(txtTimKiem);
        
        JPanel pnChucNang = new JPanel(new GridLayout(1,6,10,40));
        
        btnThemNV=new JButton("THÊM NHÂN VIÊN");
        btnCapNhatNV=new JButton("CẬP NHẬT THÔNG TIN NHÂN VIÊN");
        btnXoaNV=new JButton("XÓA NHÂN VIÊN");
        btnLamMoi=new JButton("LÀM MỚI");
        pnChucNang.add(btnThemNV);
        pnChucNang.add(btnCapNhatNV);
        pnChucNang.add(btnXoaNV);
        pnChucNang.add(btnLamMoi);
        NhanVienPanel.add(pnChucNang,BorderLayout.CENTER);
        
       //Tạo bảng
        String[] columnNames = {"ID nhân viên", "Tên nhân viên","Số điện thoại","Email","Địa chỉ","Giới tính","Ngày sinh","Chức vụ","Trạng thái","Lương"};
        Object[][] data = {{"NV00001","Vinh","0355420475","Vinh@gmail","Ben Tre","Nam","09/01/2003","Qly","Nghỉ","10000000"}};
        JTable table = new JTable(data, columnNames);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh sách nhân viên"));
        JPanel PanelMain= new JPanel();
        PanelMain.setLayout(new BorderLayout());
        		
        PanelMain.add(pnCenter,BorderLayout.NORTH);
        PanelMain.add(pnChucNang,BorderLayout.CENTER);
        PanelMain.add(scrollPane,BorderLayout.SOUTH);
//        PanelMain.add(panelSearch,BorderLayout.WEST);
        NhanVienPanel.add(PanelMain,BorderLayout.SOUTH);
        
       
  
	}
	
}




