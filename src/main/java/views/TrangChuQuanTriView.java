package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Menu;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import javax.swing.JLabel;

import javax.swing.JOptionPane;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import connection.ConnectDB;
import controllers.MenuItem;
import dao.DAONhanVien;
import models.NhanLuc;
import models.NhanVien;
import models.QuanLy;

public class TrangChuQuanTriView extends JFrame {
	private JScrollPane jScrollPane1;
	private JPanel menus;
	private JPanel panelBody;
	private JPanel panelHeader;
	private JPanel panelMenu;
	private JPanel paneCu;
	private JLabel lbID;
	private JLabel lbTen;
	private QuanLy headerQL;
	private NhanVien headerNV;
	private DAONhanVien daoNV;

	public TrangChuQuanTriView(QuanLy ql) {
		this.headerQL = ql;

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
		ImageIcon icon = new ImageIcon(getClass().getResource("/icons/logo.png"));
		setIconImage(icon.getImage());
		paneCu = new HomeView(); // Bắt đầu với HomeView
		execute();
		menus.setBackground(new Color(153, 255, 255));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn đóng cửa sổ?",
						"Xác nhận đóng cửa sổ", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					e.getWindow().dispose();
				} else if (result == JOptionPane.NO_OPTION) {

				}
			}
		});

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

		ImageIcon iconBH = new ImageIcon(getClass().getResource("/icons/banhang.png"));

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
		MenuItem QLSP = new MenuItem(iconSP, "Sản phẩm", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				switchToPanel(new QuanLySanPhamView());

			}
		}, subQLLoaiSanPham, subQLNhaCungCap, subQLTacGia, subQLTheLoai);

		MenuItem QLNV = new MenuItem(iconNV, "Quản lý Nhân viên", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

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

		MenuItem subThongKeDoanhThu = new MenuItem(iconSubMenu, "Thống kê doanh thu", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		MenuItem subThongKeSanPham = new MenuItem(iconSubMenu, "Thống kê sản phẩm", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		MenuItem subThongKeNhanVien = new MenuItem(iconSubMenu, "Thống kê nhân viên", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		MenuItem subThongKeKhachHang = new MenuItem(iconSubMenu, "Thống kê khách hàng", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		MenuItem ThongKe = new MenuItem(iconTK, "Thống kê doanh thu", null, subThongKeDoanhThu, subThongKeSanPham,
				subThongKeKhachHang, subThongKeNhanVien);
		MenuItem subCaiDatTT = new MenuItem(iconSubMenu, "Thông tin", null);
		MenuItem subGiaoDien = new MenuItem(iconSubMenu, "Giao diện", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				switchToPanel(new HomeView());

			}
		});
//		MenuItem subCaiDatDMK = new MenuItem(iconSubMenu, "Đổi mật khẩu", new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				switchToPanel(new SetPassWordView());
//			}
//		});
		MenuItem subCaiDatHDSD = new MenuItem(iconSubMenu, "Hướng dẫn sử dụng", null);
		MenuItem CaiDat = new MenuItem(iconSetting, "Cài đặt", null, subGiaoDien);

		addMenu(QLSP, QLNV, QLKH, KM, QLHD, ThongKe, CaiDat, DangXuat);
		QLSP.setBackground(new Color(153, 255, 255));
		QLNV.setBackground(new Color(153, 255, 255));
		QLKH.setBackground(new Color(153, 255, 255));
		KM.setBackground(new Color(153, 255, 255));
		QLHD.setBackground(new Color(153, 255, 255));
		ThongKe.setBackground(new Color(153, 255, 255));
		CaiDat.setBackground(new Color(153, 255, 255));
		DangXuat.setBackground(new Color(153, 255, 255));
//		QLBH.setBackground(new Color(153,255,255));

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
		lbID = new JLabel(": " + headerQL.getId());
		lbID.setFont(new Font("Arial", Font.ITALIC, 15));
		lbTen = new JLabel(": " + headerQL.getTen());
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
		panelHeader.add(pnTen, BorderLayout.CENTER);

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
