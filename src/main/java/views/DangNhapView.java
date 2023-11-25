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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.security.Key;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import org.kordamp.ikonli.materialdesign.MaterialDesign;
import org.kordamp.ikonli.swing.FontIcon;

import connection.ConnectDB;
import dao.DAONhanVien;
import dao.DAOQuanLy;
import dao.DAOTaiKhoan;
import models.NhanVien;
import models.QuanLy;
import models.TaiKhoan;

public class DangNhapView extends JFrame implements ActionListener , MouseListener, KeyListener{
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
	private DAOTaiKhoan daoTaiKhoan;
	private DAONhanVien daoNV;
	private DAOQuanLy daoQL;
	public DangNhapView()  {
		daoTaiKhoan = new DAOTaiKhoan();
		daoNV=new DAONhanVien();
		daoQL=new DAOQuanLy();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Đăng Nhập Quản Lý Hiệu Sách");
		setSize(500, 300);
		setLocationRelativeTo(null);
		ImageIcon icon = new ImageIcon(getClass().getResource("/icons/logo.png"));
		setIconImage(icon.getImage());
		init();
	}

	private void init() {
		ImageIcon iconBig = new ImageIcon(getClass().getResource("/icons/open-book.png"));
		ImageIcon iconDangNhap = new ImageIcon(getClass().getResource("/icons/log-in.png"));
		ImageIcon iconThoat = new ImageIcon(getClass().getResource("/icons/button.png"));

		Font ft = new Font("SansSerif", Font.BOLD, 30);
		Font ftItalic = new Font("SansSerif", Font.ITALIC, 12);
		Font ftBtn = new Font("SansSerif", Font.BOLD, 15);
		Font ftTieuDe = new Font("SansSerif", Font.BOLD, 30);

		Box b = Box.createVerticalBox();
		Box b1, b2, b3, b4, b5, b6, b7, b8;
		b.add(b1 = Box.createHorizontalBox());
		b1.add(pnheader = new JPanel());
		pnheader.add(Box.createVerticalStrut(100));

		pnheader.add(lblTieuDe = new JLabel("  ĐĂNG NHẬP"));
		lblTieuDe.setIcon(iconBig);
		lblTieuDe.setFont(ftTieuDe);
		lblTieuDe.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		b.add(b2 = Box.createHorizontalBox());
		b2.add(Box.createRigidArea(new Dimension(20, 0)));
		b2.add(lblTaiKhoan = new JLabel("Tên Đăng Nhập:"));
		b2.add(Box.createRigidArea(new Dimension(30, 0)));
		b2.add(txtTaiKhoan = new JTextField());
		txtTaiKhoan.setPreferredSize(new Dimension(0, 30));
		txtTaiKhoan.setToolTipText("Nhập tài khoản");
		txtTaiKhoan.setText("QL202311130001");
		b2.add(Box.createRigidArea(new Dimension(40, 0)));
		b.add(Box.createVerticalStrut(10));

		b.add(b3 = Box.createHorizontalBox());
		b3.add(Box.createRigidArea(new Dimension(20, 0)));
		b3.add(lblMatKhau = new JLabel("Mật Khẩu:"));
		b3.add(Box.createRigidArea(new Dimension(63, 0)));
		b3.add(txtMatKhau = new JPasswordField());
		txtMatKhau.setPreferredSize(new Dimension(0, 30));
		txtMatKhau.setToolTipText("Nhập mật khẩu");
		txtMatKhau.setText("1111");
		b3.add(Box.createRigidArea(new Dimension(40, 0)));
		b.add(Box.createVerticalStrut(10));

		b.add(b5 = Box.createHorizontalBox());
		b5.add(btnDangNhap = new JButton("ĐĂNG NHẬP"));
		b5.add(Box.createRigidArea(new Dimension(100, 50)));
		btnDangNhap.setFont(ftBtn);
		btnDangNhap.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDangNhap.setIcon(iconDangNhap);

		b5.add(Box.createRigidArea(new Dimension(0, 50)));
		b5.add(btnThoat = new JButton("THOÁT"));
		btnThoat.setIcon(iconThoat);
		btnThoat.setFont(ftBtn);
		btnThoat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		add(b, BorderLayout.NORTH);


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
		if (o.equals(btnThoat)) {
			System.exit(0);
		} else if (o.equals(btnDangNhap)) {
			Login();

		}

	}

	public void Login() {
		String maTK=txtTaiKhoan.getText().toString().trim();
		String mk=txtMatKhau.getText().toString().trim();
		TaiKhoan tk = daoTaiKhoan.getTaiKhoanTheoMa(maTK);
		if(tk.getIdTaiKhoan()== null) {
			JOptionPane.showMessageDialog(this, "Tài khoản không đúng", "Thông báo",
					JOptionPane.WARNING_MESSAGE);
		}else if(!tk.getMatKhau().equalsIgnoreCase(mk)) {
			JOptionPane.showMessageDialog(this, "Mật khẩu không đúng", "Thông báo",
					JOptionPane.WARNING_MESSAGE);
		}else {
			String PhanLoaiTK=tk.getIdTaiKhoan().substring(0,2);
			if(PhanLoaiTK.equals("QL")){
				QuanLy ql=daoQL.getQuanLy(tk.getIdTaiKhoan());
				TrangChuQuanTriView QTV = new TrangChuQuanTriView(ql);
				QTV.setVisible(true);
				this.setVisible(false);
			}
			if(PhanLoaiTK.equals("NV")){
				NhanVien nv=daoNV.getNhanVien(tk.getIdTaiKhoan());
				TrangChuQuanLyBanHangView QLBH = new TrangChuQuanLyBanHangView(nv);
				QLBH.setVisible(true);
				this.setVisible(false);
			}
			if(PhanLoaiTK.equals("AD")){
				AdminView Admin = new AdminView();
				Admin.setVisible(true);
				this.setVisible(false);
			}
			}
			
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

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			Object o = e.getSource();
			if (o == txtTaiKhoan || o == txtMatKhau) {
				Login();
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}

}
