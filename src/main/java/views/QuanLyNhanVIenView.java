package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

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

import connection.ConnectDB;
import controllers.MenuItem;
import dao.DAOKhachHang;
import dao.DAONhanVien;
import models.KhachHang;
import models.NhanVien;

public class QuanLyNhanVIenView extends JPanel implements ActionListener{
	private JDateChooser chooserNgaySinh;
	private JTextField txtTenNV;
	private JTextField txtsdt;
	private JTextField txtEmail;
	private JTextField txtDiaChi;
	private JTextField txtLuong;
	private JTextField txtIDNhanVien;
	private JTextField txtNS;
	private JTextField txtTimKiem;
	private JTextField txtID;
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
	private SimpleDateFormat dfNgaySinh;
	private DAONhanVien daoNhanVien;
	public QuanLyNhanVIenView() {
		dfNgaySinh = new SimpleDateFormat("dd/MM/yyyy");
		daoNhanVien=new DAONhanVien();
		NhanVien nv=new NhanVien();
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
        txtID=new JTextField();
        btnThemNV=new JButton("THÊM NHÂN VIÊN");
        btnCapNhatNV=new JButton("CẬP NHẬT THÔNG TIN NHÂN VIÊN");
        btnXoaNV=new JButton("XÓA NHÂN VIÊN");
        btnLamMoi=new JButton("LÀM MỚI");
        pnChucNang.add(txtID);
        pnChucNang.add(btnThemNV);
        pnChucNang.add(btnCapNhatNV);
        pnChucNang.add(btnXoaNV);
        pnChucNang.add(btnLamMoi);
        NhanVienPanel.add(pnChucNang,BorderLayout.CENTER);
        
        //Tạo bảng
        modelNhanVien = new DefaultTableModel();
		tableNhanVien = new JTable();
        modelNhanVien.addColumn("ID nhân viên");
		modelNhanVien.addColumn("Tên nhân viên");
		modelNhanVien.addColumn("Số điện thoại");
		modelNhanVien.addColumn("Email");
		modelNhanVien.addColumn("Địa chỉ");
		modelNhanVien.addColumn("Ngày sinh");
		modelNhanVien.addColumn("Giới tính");
		modelNhanVien.addColumn("Chức vụ");
		modelNhanVien.addColumn("Trạng thái");
		modelNhanVien.addColumn("Lương");
       
        tableNhanVien.setModel(modelNhanVien);
        JScrollPane scrollPane = new JScrollPane(tableNhanVien);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh sách nhân viên"));
        JPanel PanelMain= new JPanel();
        PanelMain.setLayout(new BorderLayout());
        		
        PanelMain.add(pnCenter,BorderLayout.NORTH);
        PanelMain.add(pnChucNang,BorderLayout.CENTER);
        PanelMain.add(scrollPane,BorderLayout.SOUTH);
        NhanVienPanel.add(PanelMain,BorderLayout.SOUTH);
        
        btnLamMoi.addActionListener(this);
		btnThemNV.addActionListener(this);
		btnCapNhatNV.addActionListener(this);
		btnXoaNV.addActionListener(this);
		try {
			ConnectDB.getinstance().connect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		loadData();

	}
	private void ThemKH() throws SQLException {
		String idKhachHang = txtID.getText();
		String tenKhachHang = txtTenNV.getText();
		String email= txtEmail.getText();
		String sdt=txtsdt.getText();
		String diaChi=txtDiaChi.getText();
		java.util.Date date = chooserNgaySinh.getDate();
		Date ngaySinh = new Date(date.getYear(), date.getMonth(), date.getDate());
		Boolean GioiTinh=rbNam.isSelected();
		String TrangThai=(String)cbTrangThai.getSelectedItem().toString();
		Boolean TrangThaibooleanValue = Boolean.parseBoolean(TrangThai);
		String chucVu=cbChucVu.getSelectedItem().toString();
		Float luong=Float.parseFloat(txtLuong.getText());
		NhanVien nv=new NhanVien(idKhachHang, tenKhachHang, sdt, diaChi, email, ngaySinh, GioiTinh,chucVu,TrangThaibooleanValue, luong);
		daoNhanVien.themNhanVien(nv);
		modelNhanVien.addRow(new Object[] {idKhachHang, tenKhachHang, sdt,  diaChi,email,dfNgaySinh.format(nv.getNgaySinh()),nv.isGioiTinh()?"Nam":"Nữ",chucVu,TrangThai,luong });

	}
	private void loadData() {
		modelNhanVien.setRowCount(0);
		for (NhanVien nv : daoNhanVien.getAllDanhSachNV() ) {
			modelNhanVien.addRow(new Object[] { nv.getIdNhanVien(), nv.getTenNhanVien(),nv.getSoDienThoai(),nv.getDiaChi(), nv.getEmail(),dfNgaySinh.format(nv.getNgaySinh()),nv.isGioiTinh()?"Nam":"Nữ",nv.getChucVu(),nv.isTrangThai()?"Đang làm việc":"Đã nghỉ việc",nv.getLuong()
					});
			
		}
	}
private void lamMoi() {
		
		txtID.setText("");
		txtTenNV.setText("");
		txtDiaChi.setText("");
		txtsdt.setText("");
		txtEmail.setText("");
		rbNam.setSelected(true);
		rbNu.setSelected(false);
		cbChucVu.setSelectedItem("Quản lý");
		cbTrangThai.setSelectedItem("Đang làm việc");
		txtLuong.setText("");
		
		txtTenNV.requestFocus();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnThemNV)) {
			try {
				ThemKH();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} 
		 if(o.equals(btnLamMoi)) {
			 lamMoi() ;}
	}
	
}




