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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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

import org.codehaus.plexus.util.dag.DAG;

import com.toedter.calendar.JDateChooser;

import connection.ConnectDB;
import controllers.MenuItem;
import dao.DAOKhachHang;
import dao.DAOQuanLySanPham;
import models.KhachHang;
import models.LoaiSanPham;
import utils.GioiTinhEnum;

public class QuanLyKhachHangView extends JPanel implements ActionListener,MouseListener{
	private JDateChooser chooserNgaySinh;
	private JTextField txtTenKH;
	private JTextField txtsdt;
	private JTextField txtEmail;
	private JTextField txtDiaChi;
	
	private JTextField txtId;
	
	
	private JTextField txtNS;
	private JTextField txtTimKiem;
	private JLabel lbTenKH;
	private JLabel lbsdt;
	private JLabel lbEmail;
	private JLabel lbDiaChi;
	private JLabel lbNgaySinh;
	private JLabel lbGioiTinh;
	
	
	private JLabel lbTimKiem;
	private JRadioButton rbNam;
	private JRadioButton rbNu;
	
	
	
	private DefaultTableModel modelKhachHang;
	private JButton btnThemKH;
	private JButton btnCapNhatKH;
	private JButton btnXoaKH;
	private JButton btnLamMoi;
	private ButtonGroup groupGT;
	private SimpleDateFormat dfNgaySinh;
	private DAOKhachHang daoKhachHang;
	private JTable tableKH;
	
	public QuanLyKhachHangView() {
		dfNgaySinh = new SimpleDateFormat("dd/MM/yyyy");
		daoKhachHang=new DAOKhachHang();
		KhachHang kh=new KhachHang();
		setLayout(new BorderLayout());
////		Tiêu đề
		JPanel NhanVienPanel=new JPanel();
		NhanVienPanel.setLayout(new BorderLayout());
		NhanVienPanel.setBorder(new EmptyBorder(10,10,10,10));
		
		JLabel lblTieuDe = new JLabel("QUẢN LÝ KHÁCH HÀNG");
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 30));
		lblTieuDe.setForeground(Color.black);
		JPanel PanelNouth =new JPanel();
		
		add(NhanVienPanel);
		NhanVienPanel.add(PanelNouth,BorderLayout.NORTH);
		PanelNouth.add(lblTieuDe);
		
		JPanel pnCenter = new JPanel();
		pnCenter.setLayout(new GridLayout(5,10, 10, 10));
		pnCenter.setBorder(BorderFactory.createTitledBorder("Nhập thông tin khách hàng"));
		

        lbTenKH = new JLabel("Tên khách hàng: ");
        txtTenKH = new JTextField();
        lbsdt = new JLabel("Số điện thoại: ");
        txtsdt = new JTextField();
        lbEmail = new JLabel("Email: ");
        txtEmail = new JTextField();
        
		lbDiaChi = new JLabel("Địa chỉ: ");
	    txtDiaChi = new JTextField();
	    
	    lbGioiTinh=new JLabel("Giới tính: ");
	    lbNgaySinh=new JLabel("Ngày sinh: ");
	    txtNS= new JTextField();
	    rbNam=new JRadioButton("Nam");
	    rbNu=new JRadioButton("Nữ");
	    
	    chooserNgaySinh = new JDateChooser();
		chooserNgaySinh.getCalendarButton().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chooserNgaySinh.setBounds(100, 310, 200, 40);
		chooserNgaySinh.setDateFormatString("dd/MM/yyyy");	
		chooserNgaySinh.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		chooserNgaySinh.setFont(new Font("SansSerif", Font.PLAIN, 15));
		chooserNgaySinh.getCalendarButton().setPreferredSize(new Dimension(20, 24));
		chooserNgaySinh.getCalendarButton().setBackground(new Color(102, 0, 153));
		chooserNgaySinh.getCalendarButton().setToolTipText("Chọn ngày sinh");
	    
        Box BoxCenter= Box.createVerticalBox();
        Box b1,b2,b3,b4,b5,b6,b7;
        b1=Box.createHorizontalBox();
        b1.add(lbTenKH);
        b1.add(txtTenKH);
        b1.add(Box.createHorizontalStrut(20));
        b1.add(lbsdt);
        b1.add(txtsdt);
        b2=Box.createHorizontalBox();
        b2.add(lbEmail);
        b2.add(txtEmail);
        b2.add(Box.createHorizontalStrut(20));
        b2.add(lbDiaChi);
        b2.add(txtDiaChi);
        b3=Box.createHorizontalBox();
       
        JTextField txtGT=new JTextField();
        b6=Box.createHorizontalBox();
        b6.add(Box.createHorizontalStrut(420));
        b4=Box.createHorizontalBox();

        
        b7=Box.createHorizontalBox();
        txtId=new JTextField();
        b7.add(txtId);
        
        b4.add(lbNgaySinh);
        b4.add(chooserNgaySinh);
        b5=Box.createHorizontalBox();
        b5.add(lbGioiTinh);
        b5.add(Box.createHorizontalStrut(20));
        b5.add(rbNam);
        b5.add(rbNu);
        b3.add(b4);
        b3.add(Box.createHorizontalStrut(20));
        b3.add(b5);
        b3.add(b6);
        lbEmail.setPreferredSize(lbTenKH.getPreferredSize());
		lbDiaChi.setPreferredSize(lbsdt.getPreferredSize());
		lbGioiTinh.setPreferredSize(lbsdt.getPreferredSize());
		lbNgaySinh.setPreferredSize(lbTenKH.getPreferredSize());
	    BoxCenter.add(Box.createVerticalStrut(10));
        BoxCenter.add(b1);
        BoxCenter.add(Box.createVerticalStrut(10));
        BoxCenter.add(b2);
        BoxCenter.add(Box.createVerticalStrut(10));
        BoxCenter.add(b3);
        BoxCenter.add(Box.createVerticalStrut(10));
        BoxCenter.add(b7);
        BoxCenter.setBorder(BorderFactory.createTitledBorder("Nhập thông tin khách hàng"));
		
        //Tạo button groud giới tính
        groupGT = new ButtonGroup();
        groupGT.add(rbNam);
        groupGT.add(rbNu);
        rbNam.setActionCommand("Nam");
        rbNu.setActionCommand("Nữ");
        
        lbTimKiem=new JLabel("Tìm kiếm nhân viên:");
        txtTimKiem=new JTextField();
        JPanel panelSearch=new JPanel();
        panelSearch.add(lbTimKiem);
        panelSearch.add(txtTimKiem);
        
        JPanel pnChucNang = new JPanel(new GridLayout(1,6,10,40));
        
        btnThemKH=new JButton("THÊM KHÁCH HÀNG");
        btnCapNhatKH=new JButton("CẬP NHẬT THÔNG TIN KHÁCH HÀNG");
        btnXoaKH=new JButton("XÓA KHÁCH HÀNG");
        btnLamMoi=new JButton("LÀM MỚI");
        pnChucNang.add(btnThemKH);
        pnChucNang.add(btnCapNhatKH);
        pnChucNang.add(btnXoaKH);
        pnChucNang.add(btnLamMoi);
       
        
       //Tạo bảng
        modelKhachHang = new DefaultTableModel();
		tableKH = new JTable();
        modelKhachHang.addColumn("ID KhachHang");
		modelKhachHang.addColumn("Tên khách hàng");
		modelKhachHang.addColumn("Số điện thoại");
		modelKhachHang.addColumn("Email");
		modelKhachHang.addColumn("Địa chỉ");
		modelKhachHang.addColumn("Ngày sinh");
		modelKhachHang.addColumn("Giới tính");
       
        tableKH.setModel(modelKhachHang);
        JScrollPane scrollPane = new JScrollPane(tableKH);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh sách khách hàng"));
       
        JPanel PanelMain= new JPanel();
        PanelMain.setLayout(new BorderLayout());
    	PanelMain.add(BoxCenter,BorderLayout.NORTH);
        PanelMain.add(pnChucNang,BorderLayout.CENTER);
        PanelMain.add(scrollPane,BorderLayout.SOUTH);
        NhanVienPanel.add(PanelMain,BorderLayout.SOUTH);
    
        btnLamMoi.addActionListener(this);
		btnThemKH.addActionListener(this);
		btnCapNhatKH.addActionListener(this);
		btnXoaKH.addActionListener(this);
		try {
			ConnectDB.getinstance().connect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		loadData();
        
	}
	private void ThemKH() throws SQLException {
		String idKhachHang = txtId.getText();
		String tenKhachHang = txtTenKH.getText();
		String email= txtEmail.getText();
		String sdt=txtsdt.getText();
		String diaChi=txtDiaChi.getText();
		java.util.Date date = chooserNgaySinh.getDate();
		Date ngaySinh = new Date(date.getYear(), date.getMonth(), date.getDate());
		Boolean GioiTinh=rbNam.isSelected();
		
		KhachHang kh=new KhachHang(idKhachHang, tenKhachHang, sdt, email, diaChi,ngaySinh,GioiTinh);
		daoKhachHang.themKhachHang(kh);
		modelKhachHang.addRow(new Object[] {idKhachHang, tenKhachHang, sdt, email, diaChi,dfNgaySinh.format(kh.getNgaySinh()),kh.isGioiTinh()?"Nam":"Nữ" });

	}
	private void loadData() {
		modelKhachHang.setRowCount(0);
		for (KhachHang kh : daoKhachHang.getAllDanhSachKH() ) {
			modelKhachHang.addRow(new Object[] { kh.getIdKhachHang(), kh.getTenKhachHang(),kh.getSdt(), kh.getEmail(),kh.getDiaChi(),dfNgaySinh.format(kh.getNgaySinh()),kh.isGioiTinh()?"Nam":"Nữ"
					});
			
		}
	}

	private void lamMoi() {
		

		txtTenKH.setText("");
		txtDiaChi.setText("");
		txtsdt.setText("");
		txtEmail.setText("");
		rbNam.setSelected(true);
		rbNu.setSelected(false);
		txtTenKH.requestFocus();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnThemKH)) {
			try {
				ThemKH();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} 
		 if(o.equals(btnLamMoi)) {
			 lamMoi() ;}
//		} else if (o.equals(btnXoa)) {
//			xoaNV();
//		} else if (o.equals(btnSua)) {
//			suaNV();
//		} else if (o.equals(btnTim)) {
//			timNhanVien();
////			timNV();
		
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





