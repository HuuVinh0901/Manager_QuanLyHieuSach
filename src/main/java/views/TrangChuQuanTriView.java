 package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import connection.ConnectDB;
import controllers.MenuItem;

public class TrangChuQuanTriView extends JFrame {
	private JScrollPane jScrollPane1;
	private JPanel menus;
	private JPanel panelBody;
	private JPanel panelHeader;
	private JPanel panelMenu;
	private JPanel paneCu;

	public TrangChuQuanTriView() {
		try {
			ConnectDB.getinstance().connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		setTitle("Quản trị");
		setSize(new Dimension(871, 473));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		initComponents();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		paneCu = new HomeView(); // Bắt đầu với HomeView
		panelBody.add(paneCu);
		execute();
<<<<<<< HEAD
		menus.setBackground(new Color(153,225,225));
		
=======

>>>>>>> main
	}

	private void execute() {

		ImageIcon iconSetting = new ImageIcon(getClass().getResource("/icons/settings.png"));
		ImageIcon iconSubMenu = new ImageIcon(getClass().getResource("/icons/plus.png"));
		ImageIcon iconKH = new ImageIcon(getClass().getResource("/icons/KH.png"));
		ImageIcon iconNV = new ImageIcon(getClass().getResource("/icons/NV.png"));
		ImageIcon iconSP = new ImageIcon(getClass().getResource("/icons/SP.png"));
		ImageIcon iconKM = new ImageIcon(getClass().getResource("/icons/KM.png"));
		ImageIcon iconHD = new ImageIcon(getClass().getResource("/icons/bill.png"));
		ImageIcon iconTK = new ImageIcon(getClass().getResource("/icons/TK.png"));
		ImageIcon iconDX = new ImageIcon(getClass().getResource("/icons/DX.png"));
		MenuItem subQLLoaiSanPham = new MenuItem(iconSubMenu, "Loại sản phẩm", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				switchToPanel(new LoaiSanPhamView());
			}
		});
		MenuItem subQLNhaCungCap = new MenuItem(iconSubMenu, "Nhà cung cấp", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				switchToPanel(new LoaiNhaSanXuatView());

			}
		});
<<<<<<< HEAD
		MenuItem DangXuat = new MenuItem(iconDX, "Đăng xuất", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				

			}
		});
		MenuItem QLSP = new MenuItem(iconSP, "Sản phẩm", new ActionListener() {
=======

		MenuItem subQLTacGia = new MenuItem(iconSubMenu, "Tác giả", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				switchToPanel(new TacGiaView());
			}
		});

		MenuItem subQLTheLoai = new MenuItem(iconSubMenu, "Thể loại", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				switchToPanel(new TheLoaiView());
			}
		});

		MenuItem QLSP = new MenuItem(iconStaff, "Hàng Hóa", new ActionListener() {
>>>>>>> main

			@Override
			public void actionPerformed(ActionEvent e) {
				switchToPanel(new QuanLySanPhamView());

			}
<<<<<<< HEAD
		}, subQLLoaiSanPham, subQLNhaCungCap);
		
		MenuItem QLNV = new MenuItem(iconNV, "Quản lý Nhân viên", new ActionListener() {
=======
		}, subQLLoaiSanPham, subQLNhaCungCap, subQLTacGia,subQLTheLoai);
		MenuItem QLTKNV = new MenuItem(iconStaff, "Quản lý tài khoản", null);
		MenuItem QLNV = new MenuItem(iconStaff, "Quản lý Nhân viên", new ActionListener() {
>>>>>>> main

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				switchToPanel(new QuanLyNhanVienView());
			}
		});
		MenuItem QLKH = new MenuItem(iconKH, "Quản lý khách hàng", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				switchToPanel(new QuanLyKhachHangView());
			}
		});
		MenuItem KM = new MenuItem(iconKM, "Chương trình khuyến mãi", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				switchToPanel(new KhuyenMaiView());
			}
		});
		MenuItem QLHD = new MenuItem(iconHD, "Quản lý hóa đơn", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				switchToPanel(new QuanLyHoaDonView());
			}
		});
		MenuItem subTKCa = new MenuItem(iconSubMenu, "Thống kê theo ca", null);
		MenuItem subTKCP = new MenuItem(iconSubMenu, "Thống kê chi phí", null);
		MenuItem TKBC = new MenuItem(iconTK, "Thống kê báo cáo", null, subTKCa, subTKCP);
		MenuItem subCaiDatTT = new MenuItem(iconSubMenu, "Thông tin", null);
		MenuItem subGiaoDien = new MenuItem(iconSubMenu, "Giao diện", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				switchToPanel(new HomeView());

			}
		});
		MenuItem subCaiDatDMK = new MenuItem(iconSubMenu, "Đổi mật khẩu", null);
		MenuItem subCaiDatHDSD = new MenuItem(iconSubMenu, "Hướng dẫn sử dụng", null);
		MenuItem CaiDat = new MenuItem(iconSetting, "Cài đặt", null, subCaiDatTT, subGiaoDien, subCaiDatDMK,
				subCaiDatHDSD);
		addMenu(QLSP, QLNV, QLKH,KM, QLHD, TKBC, CaiDat,DangXuat);
		QLSP.setBackground(new Color(153,225,225));
		QLNV.setBackground(new Color(153,225,225));
		QLKH.setBackground(new Color(153,225,225));
		KM.setBackground(new Color(153,225,225));
		QLHD.setBackground(new Color(153,225,225));
		TKBC.setBackground(new Color(153,225,225));
		CaiDat.setBackground(new Color(153,225,225));
		DangXuat.setBackground(new Color(153,225,225));
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
//		panelHeader = new JPanel();
		panelMenu = new JPanel();
		jScrollPane1 = new JScrollPane();
		menus = new JPanel();
		panelBody = new JPanel();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

//		panelHeader.setBackground(new Color(225,223,223));
//		panelHeader.setPreferredSize(new Dimension(561, 50));

//		GroupLayout panelHeaderLayout = new GroupLayout(panelHeader);
//		panelHeader.setLayout(panelHeaderLayout);
//		panelHeaderLayout.setHorizontalGroup(
//				panelHeaderLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 855, Short.MAX_VALUE));
//		panelHeaderLayout.setVerticalGroup(
//				panelHeaderLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 50, Short.MAX_VALUE));
//
//		getContentPane().add(panelHeader, BorderLayout.PAGE_START);

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
