package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Timer;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import connection.ConnectDB;
import controllers.MenuItem;
import dao.DAONhanVien;
import models.NhanVien;
import models.TaiKhoan;

public class TrangChuQuanLyBanHangView extends JFrame {
	private JScrollPane jScrollPane1;
	private JPanel menus;
	private JPanel panelBody;
	private JPanel panelHeader;
	private JPanel panelMenu;
	private JPanel paneCu;
	private JLabel lblDate;
	private JLabel lbID;
	private JLabel lbTen;
	private NhanVien headerNV;
	private DAONhanVien daoNV;
	private JPanel pnDate;
	public TrangChuQuanLyBanHangView(NhanVien nv) {
		this.headerNV = nv;
		daoNV = new DAONhanVien();

		try {
			ConnectDB.getinstance().connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		setTitle("Quản lý bán hàng");
		setSize(new Dimension(871, 473));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		ImageIcon icon = new ImageIcon(getClass().getResource("/icons/logo.png"));
		setIconImage(icon.getImage());
		initComponents();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		paneCu = new HomeView(); // Bắt đầu với HomeView
		panelBody.add(paneCu);
		execute();
		menus.setBackground(new Color(153, 225, 225));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int result = JOptionPane.showConfirmDialog(TrangChuQuanLyBanHangView.this,
						"Bạn có chắc chắn muốn đóng cửa sổ?", "Xác nhận đóng cửa sổ", JOptionPane.YES_NO_OPTION);

				if (result == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});
	}

	private void execute() {
		ImageIcon iconSetting = new ImageIcon(getClass().getResource("/icons/settings.png"));
		ImageIcon iconKH = new ImageIcon(getClass().getResource("/icons/KH.png"));
		ImageIcon iconSP = new ImageIcon(getClass().getResource("/icons/banhang.png"));
		ImageIcon iconSubMenu = new ImageIcon(getClass().getResource("/icons/plus.png"));
		ImageIcon iconBH = new ImageIcon(getClass().getResource("/icons/banhang.png"));
		ImageIcon iconDX = new ImageIcon(getClass().getResource("/icons/DX.png"));
		ImageIcon iconTK = new ImageIcon(getClass().getResource("/icons/TK.png"));

		MenuItem QLBH = new MenuItem(iconBH, "Quản lý bán hàng", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				NhanVien nv = daoNV.getNhanVien(headerNV.getId());
				switchToPanel(new QuanLyBanHangView(nv));

			}
		});

		MenuItem DangXuat = new MenuItem(iconDX, "Đăng xuất", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int hoiNhac = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn đăng xuất", "Cảnh báo",
						JOptionPane.YES_NO_OPTION);
				if (hoiNhac == JOptionPane.YES_OPTION) {
					DangNhapView view = new DangNhapView();
					view.setVisible(true);
					dispose();
				}

			}
		});

		MenuItem QLKH = new MenuItem(iconKH, "Quản lý khách hàng", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				switchToPanel(new QuanLyKhachHangView());
			}
		});
		MenuItem subCaiDatTT = new MenuItem(iconSubMenu, "Thông tin", null);
		MenuItem subCaiDatDMK = new MenuItem(iconSubMenu, "Đổi mật khẩu", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				TaiKhoan tk = new TaiKhoan();
				NhanVien nv = new NhanVien();
				SetPassWordView frame = new SetPassWordView(nv, tk);
				switchToPanel(new HomeView());
				frame.setVisible(true);
			}
		});
		MenuItem thongKe = new MenuItem(iconTK, "Thống kê", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				switchToPanel(new ThongKeDoanhThuNhanVienView());

			}
		});
		MenuItem subCaiDatHDSD = new MenuItem(iconSubMenu, "Hướng dẫn sử dụng", null);
		MenuItem subGiaoDien = new MenuItem(iconSubMenu, "Giao diện", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				switchToPanel(new HomeView());

			}
		});
		MenuItem CaiDat = new MenuItem(iconSetting, "Cài đặt", null, subGiaoDien);
		addMenu(QLBH, QLKH, CaiDat, thongKe, DangXuat);
		QLKH.setBackground(new Color(153, 225, 225));
		QLBH.setBackground(new Color(153, 225, 225));
		thongKe.setBackground(new Color(153, 225, 225));
		CaiDat.setBackground(new Color(153, 225, 225));
		DangXuat.setBackground(new Color(153, 225, 225));

	}

	private void switchToPanel(JPanel newPanel) {
		panelBody.remove(paneCu); // Loại bỏ panel hiện tại
		paneCu = newPanel; // Cập nhật panel hiện tại
		panelBody.add(paneCu); // Thêm panel mới vào panelBody
		panelBody.repaint();
		panelBody.revalidate();
	}

	private void addMenu(MenuItem... menu) {
		for (int i = 0; i < menu.length; i++) {
			menus.add(menu[i]);
			ArrayList<MenuItem> subMenu = menu[i].getSubMenu();
			for (MenuItem m : subMenu) {
				addMenu(m);
			}
		}
		menus.revalidate();
	}

	private void initComponents() {
		panelHeader = new JPanel(new BorderLayout());
		panelMenu = new JPanel();
		jScrollPane1 = new JScrollPane();
		menus = new JPanel();
		panelBody = new JPanel();
		lbID = new JLabel(": " + headerNV.getId());
		lbID.setFont(new Font("Arial", Font.ITALIC, 15));
		lbTen = new JLabel(": " + headerNV.getTen());


		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		String dateTiemString = now.format(formatter);
		lblDate = new JLabel(dateTiemString);
		lblDate.setFont(new Font("Arial", Font.BOLD, 15));

		pnDate = new JPanel();
		pnDate.add(lblDate);
		
		
		
		lbTen.setFont(new Font("Arial", Font.ITALIC, 15));
		JPanel pnTen = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel pnID = new JPanel(new FlowLayout(FlowLayout.LEFT));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		panelHeader.setBackground(new Color(225, 223, 223));
		panelHeader.setPreferredSize(new Dimension(561, 50));
		pnID.add(lbID);
		pnTen.add(lbTen);
		ImageIcon iconid = new ImageIcon(getClass().getResource("/icons/id.png"));
		ImageIcon iconTen = new ImageIcon(getClass().getResource("/icons/Ten.png"));
		lbID.setIcon(iconid);
		lbTen.setIcon(iconTen);
		panelHeader.add(pnID, BorderLayout.NORTH);
		panelHeader.add(pnTen, BorderLayout.WEST);
		panelHeader.add(pnDate, BorderLayout.CENTER);
		
		getContentPane().add(panelHeader, BorderLayout.PAGE_START);

		jScrollPane1.setBorder(null);

		menus.setLayout(new BoxLayout(menus, BoxLayout.Y_AXIS));
		jScrollPane1.setViewportView(menus);

		GroupLayout panelMenuLayout = new GroupLayout(panelMenu);
		panelMenu.setLayout(panelMenuLayout);
		panelMenuLayout.setHorizontalGroup(panelMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE));
		panelMenuLayout.setVerticalGroup(panelMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE));

		getContentPane().add(panelMenu, BorderLayout.LINE_START);

		panelBody.setLayout(new BorderLayout());
		getContentPane().add(panelBody, BorderLayout.CENTER);
	}
}
