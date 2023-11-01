package views;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;

import connection.ConnectDB;
import controllers.MenuItem;
import views.QuanLyBanHangView;

public class TrangChuQuanLyBanHangView extends JFrame {
	private JScrollPane jScrollPane1;
	private JPanel menus;
	private JPanel panelBody;
	private JPanel panelHeader;
	private JPanel panelMenu;

	public TrangChuQuanLyBanHangView() {
		try {
			ConnectDB.getinstance().connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		setTitle("Quản lý bán hàng");
		setSize(new Dimension(871, 473));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		initComponents();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		execute();
	}

	private void execute() {
		ImageIcon iconStaff = new ImageIcon(getClass().getResource("/icons/user.png"));
		ImageIcon iconSetting = new ImageIcon(getClass().getResource("/icons/setting.png"));
		ImageIcon iconSubMenu = new ImageIcon(getClass().getResource("/icons/subMenu.png"));

		MenuItem QLBH = new MenuItem(iconStaff, "Quản lý bán hàng",new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				panelBody.add(new QuanLyBanHangView());
				panelBody.repaint();
				panelBody.revalidate();
				
			}
		});
		

		MenuItem QLKH = new MenuItem(iconStaff, "Quản lý khách hàng",null);
		MenuItem subCaiDatTT = new MenuItem(iconSubMenu, "Thông tin", null);
		MenuItem subCaiDatDMK = new MenuItem(iconSubMenu, "Đổi mật khẩu", null);
		MenuItem subCaiDatHDSD = new MenuItem(iconSubMenu, "Hướng dẫn sử dụng", null);
		MenuItem CaiDat = new MenuItem(iconSetting, "Cài đặt",null,subCaiDatTT,subCaiDatDMK,subCaiDatHDSD);
		addMenu(QLBH,QLKH,CaiDat);

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
		panelHeader = new JPanel();
		panelMenu = new JPanel();
		jScrollPane1 = new JScrollPane();
		menus = new JPanel();
		panelBody = new JPanel();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		panelHeader.setBackground(new Color(120, 120, 120));
		panelHeader.setPreferredSize(new Dimension(561, 50));

		GroupLayout panelHeaderLayout = new GroupLayout(panelHeader);
		panelHeader.setLayout(panelHeaderLayout);
		panelHeaderLayout.setHorizontalGroup(
				panelHeaderLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 855, Short.MAX_VALUE));
		panelHeaderLayout.setVerticalGroup(
				panelHeaderLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 50, Short.MAX_VALUE));

		getContentPane().add(panelHeader, BorderLayout.PAGE_START);

		jScrollPane1.setBorder(null);

		menus.setBackground(new Color(255, 255, 255));
		menus.setLayout(new BoxLayout(menus, BoxLayout.Y_AXIS));
		jScrollPane1.setViewportView(menus);

		GroupLayout panelMenuLayout = new GroupLayout(panelMenu);
		panelMenu.setLayout(panelMenuLayout);
		panelMenuLayout.setHorizontalGroup(panelMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE));
		panelMenuLayout.setVerticalGroup(panelMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE));

		getContentPane().add(panelMenu, BorderLayout.LINE_START);

		panelBody.setBackground(new Color(255, 255, 255));
		panelBody.setLayout(new BorderLayout());
		getContentPane().add(panelBody, BorderLayout.CENTER);
	}

}
