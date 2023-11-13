package views;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;

import connection.ConnectDB;
import controllers.MenuItem;
import models.NhanVien;
import models.QuanLy;
import views.QuanLyBanHangView;

public class TrangChuQuanLyBanHangView extends JFrame {
	private JScrollPane jScrollPane1;
	private JPanel menus;
	private JPanel panelBody;
	private JPanel panelHeader;
	private JPanel panelMenu;
	private JPanel paneCu;
	private JLabel lbID;
	private JLabel lbTen;
	private NhanVien headerNV;
	public TrangChuQuanLyBanHangView(NhanVien nv) {
		this.headerNV=nv;
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
		menus.setBackground(new Color(153,225,225));
	}

	private void execute() {
		ImageIcon iconSetting = new ImageIcon(getClass().getResource("/icons/settings.png"));
		ImageIcon iconKH = new ImageIcon(getClass().getResource("/icons/KH.png"));
		ImageIcon iconSP = new ImageIcon(getClass().getResource("/icons/banhang.png"));
		ImageIcon iconSubMenu = new ImageIcon(getClass().getResource("/icons/plus.png"));
		ImageIcon iconDX = new ImageIcon(getClass().getResource("/icons/DX.png"));	
		MenuItem QLBH = new MenuItem(iconSP, "Quản lý bán hàng",new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				switchToPanel(new QuanLyBanHangView());
				
			}
		});
		MenuItem DangXuat = new MenuItem(iconDX, "Đăng xuất", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new DangNhapView().setVisible(true);
				setVisible(false);
			}
		});

		MenuItem QLKH = new MenuItem(iconKH, "Quản lý khách hàng",new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				switchToPanel(new QuanLyKhachHangView());
			}
		});
		MenuItem subCaiDatTT = new MenuItem(iconSubMenu, "Thông tin", null);
		MenuItem subCaiDatDMK = new MenuItem(iconSubMenu, "Đổi mật khẩu", null);
		MenuItem subCaiDatHDSD = new MenuItem(iconSubMenu, "Hướng dẫn sử dụng", null);
		MenuItem CaiDat = new MenuItem(iconSetting, "Cài đặt",null,subCaiDatTT,subCaiDatDMK,subCaiDatHDSD);
		addMenu(QLBH,QLKH,CaiDat,DangXuat);
		QLKH.setBackground(new Color(153,225,225));
		QLBH.setBackground(new Color(153,225,225));
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
		panelHeader = new JPanel(new BorderLayout());
		panelMenu = new JPanel();
		jScrollPane1 = new JScrollPane();
		menus = new JPanel();
		panelBody = new JPanel();
		lbID=new JLabel(": "+headerNV.getId());
		lbID.setFont(new Font("Arial", Font.ITALIC, 15));
		lbTen=new JLabel(": "+headerNV.getTen());
		lbTen.setFont(new Font("Arial", Font.ITALIC, 15));
		JPanel pnTen=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel pnID=new JPanel(new FlowLayout(FlowLayout.LEFT));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		panelHeader.setBackground(new Color(225,223,223));
		panelHeader.setPreferredSize(new Dimension(561, 50));
		pnID.add(lbID);
		pnTen.add(lbTen);
		ImageIcon iconid = new ImageIcon(getClass().getResource("/icons/id.png"));
		ImageIcon iconTen = new ImageIcon(getClass().getResource("/icons/Ten.png"));
		lbID.setIcon(iconid);
		lbTen.setIcon(iconTen);
		panelHeader.add(pnID,BorderLayout.NORTH);
		panelHeader.add(pnTen,BorderLayout.CENTER);

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
