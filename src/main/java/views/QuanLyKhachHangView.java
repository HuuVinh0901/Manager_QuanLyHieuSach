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

public class QuanLyKhachHangView extends JPanel{
	private JDateChooser chooserNgaySinh;
	private JTextField txtTenKH;
	private JTextField txtsdt;
	private JTextField txtEmail;
	private JTextField txtDiaChi;
	
	
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
	
	
	private JTable tableKhachHang;
	private DefaultTableModel modelKhachHang;
	private JButton btnThemKH;
	private JButton btnCapNhatKH;
	private JButton btnXoaKH;
	private JButton btnLamMoi;
	public QuanLyKhachHangView() {
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
//		
		chooserNgaySinh.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		chooserNgaySinh.setFont(new Font("SansSerif", Font.PLAIN, 15));
		chooserNgaySinh.getCalendarButton().setPreferredSize(new Dimension(20, 24));
		chooserNgaySinh.getCalendarButton().setBackground(new Color(102, 0, 153));
		chooserNgaySinh.getCalendarButton().setToolTipText("Chọn ngày sinh");
	    
        Box BoxCenter= Box.createVerticalBox();
        Box b1,b2,b3,b4,b5,b6;
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
       
        BoxCenter.setBorder(BorderFactory.createTitledBorder("Nhập thông tin khách hàng"));
		
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
        
        btnThemKH=new JButton("THÊM KHÁCH HÀNG");
        btnCapNhatKH=new JButton("CẬP NHẬT THÔNG TIN KHÁCH HÀNG");
        btnXoaKH=new JButton("XÓA KHÁCH HÀNG");
        btnLamMoi=new JButton("LÀM MỚI");
        pnChucNang.add(btnThemKH);
        pnChucNang.add(btnCapNhatKH);
        pnChucNang.add(btnXoaKH);
        pnChucNang.add(btnLamMoi);
       
        
       //Tạo bảng
        String[] columnNames = {"ID khách hàng", "Tên khách hàng","Số điện thoại","Email","Địa chỉ","Giới tính","Ngày sinh"};
        Object[][] data = {{"NV00001","Vinh","0355420475","Vinh@gmail","Ben Tre","Nam","09/01/2003",}};
        JTable table = new JTable(data, columnNames);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh sách khách hàng"));
        JPanel PanelMain= new JPanel();
        PanelMain.setLayout(new BorderLayout());
//     
        PanelMain.add(BoxCenter,BorderLayout.NORTH);
        PanelMain.add(pnChucNang,BorderLayout.CENTER);
        PanelMain.add(scrollPane,BorderLayout.SOUTH);
//        PanelMain.add(panelSearch,BorderLayout.WEST);
        NhanVienPanel.add(PanelMain,BorderLayout.SOUTH);
        
       
  
	}
	
}





