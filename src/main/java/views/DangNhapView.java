package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import org.kordamp.ikonli.materialdesign.MaterialDesign;
import org.kordamp.ikonli.swing.FontIcon;

import connection.ConnectDB;


public class DangNhapView extends JFrame implements ActionListener{	
	private static final long serialVersionUID = 1L;
	private JTextField txtTaiKhoan;
	private JButton btnThoat;
	private JButton btnDangNhap;
	private JPasswordField txtMatKhau;
	private JLabel lblQuenMatKhau;
	private JLabel lblDangNhap;
	private JLabel lblTaiKhoan;
	private JLabel lblMatKhau;
	private JLabel lblIcon;
	private JPanel pnheader;
	private JLabel lblTieuDe;
	private JCheckBox rememberPassword;
	public DangNhapView() {
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setResizable(false);
	    setTitle("Đăng Nhập Quản Lý Hiệu Sách");
	    setSize(500,300);
	    setLocationRelativeTo(null);
	    ImageIcon icon = new ImageIcon(getClass().getResource("/icons/logo.png"));
	    setIconImage(icon.getImage());
	    init();
	}

	
	private void init() {
		FontIcon iconLogo = FontIcon.of(MaterialDesign.MDI_BOOK_OPEN_PAGE_VARIANT);
		FontIcon iconDangNhap = FontIcon.of(MaterialDesign.MDI_ACCOUNT);
		FontIcon iconMatKhau = FontIcon.of(MaterialDesign.MDI_LOCK);
		FontIcon iconLogin = FontIcon.of(MaterialDesign.MDI_LOGIN_VARIANT);
		FontIcon iconCancel = FontIcon.of(MaterialDesign.MDI_CLOSE);
		
		Font ft = new Font("SansSerif",Font.BOLD,30);
		Font ftItalic = new Font("SansSerif",Font.ITALIC,12);
		Font ftBtn = new Font("SansSerif",Font.BOLD,15);
		Font ftTieuDe = new Font("SansSerif",Font.BOLD,20);
		
		Box b = Box.createVerticalBox();
		Box b1,b2,b3,b4,b5,b6,b7,b8;
		b.add(b1 = Box.createHorizontalBox());
		b1.add(pnheader = new JPanel());
		pnheader.add(Box.createVerticalStrut(100));
		pnheader.add(lblTieuDe = new JLabel("Đăng Nhập Hệ Thống"));
		lblTieuDe.setFont(ftTieuDe);
		lblTieuDe.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		b.add(b2 = Box.createHorizontalBox());
		b2.add(Box.createRigidArea(new Dimension(20, 0)));
		b2.add(lblTaiKhoan = new JLabel("Tên Đăng Nhập:"));
		b2.add(Box.createRigidArea(new Dimension(30,0)));
		b2.add(txtTaiKhoan = new JTextField());
		txtTaiKhoan.setPreferredSize(new Dimension(0,30));
		txtTaiKhoan.setToolTipText("Nhập tài khoản");
		b2.add(Box.createRigidArea(new Dimension(40, 0)));
		b.add(Box.createVerticalStrut(10));
			
		b.add(b3 = Box.createHorizontalBox());
		b3.add(Box.createRigidArea(new Dimension(20, 0)));
		b3.add(lblMatKhau = new JLabel("Mật Khẩu:"));
		b3.add(Box.createRigidArea(new Dimension(63,0)));
		b3.add(txtMatKhau = new JPasswordField());
		txtMatKhau.setPreferredSize(new Dimension(0,30));
		txtMatKhau.setToolTipText("Nhập mật khẩu");
		b3.add(Box.createRigidArea(new Dimension(40, 0)));
		b.add(Box.createVerticalStrut(10));
		
		
		b.add(b5 = Box.createHorizontalBox());
		b5.add(btnDangNhap = new JButton("Đăng Nhập"));
		b5.add(Box.createRigidArea(new Dimension(100, 50)));
		btnDangNhap.setFont(ftBtn);
		btnDangNhap.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		b5.add(Box.createRigidArea(new Dimension(0, 50)));
		b5.add(btnThoat = new JButton("Thoát"));
		
		btnThoat.setFont(ftBtn);
		btnThoat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		add(b,BorderLayout.NORTH);

		btnThoat.addActionListener(this);
		btnDangNhap.addActionListener(this);
		try {
			ConnectDB.getinstance().connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnThoat)) {
			System.exit(0);
		}else if(o.equals(btnDangNhap)) {
			dangNhap();
		}
	}

	private void dangNhap() {
		PhanQuyenView view = new PhanQuyenView();
		this.setVisible(false);
		view.setVisible(true);
	}
}
