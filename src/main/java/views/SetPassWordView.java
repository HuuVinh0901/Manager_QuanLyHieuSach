package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import models.TaiKhoan;

public class SetPassWordView extends JFrame {
	private JLabel lblTieuDe;
	private JLabel lblSub;
	private JLabel lblMaXacNhan;
	private JLabel lblMatKhauMoi;
	private JLabel lblXacNhanMatKhau;
	private JTextField txtMaXacNhan;
	private JTextField txtMatKhauMoi;
	private JTextField txtXacNhanMatKhau;
	private JButton btnGuiLaiMa;
	private JButton btnXacNhan;
	
	private JPanel pnContainer;
	private JPanel pnMain;
	private JPanel pnHeading;
	private JPanel pnCenter;
	private JPanel pnSounth;
	private TaiKhoan taiKhoan;
	public SetPassWordView(TaiKhoan tk) {
		 this.taiKhoan = tk;
		setSize(300,300);
		setTitle("Đặt lại mật khẩu");
		ImageIcon icon = new ImageIcon(getClass().getResource("/icons/logo.png"));
		setIconImage(icon.getImage());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		init();
	}
	
	
	private void init() {
		 lblTieuDe = new JLabel("Đặt lại mật khẩu của bạn");
		    lblMaXacNhan = new JLabel("Mã xác nhận *");
		    lblMatKhauMoi = new JLabel("Mật khẩu mới");
		    lblXacNhanMatKhau = new JLabel("Xác nhận mật khẩu");

		    txtMaXacNhan = new JTextField(20);
		    txtMatKhauMoi = new JTextField(20);
		    txtXacNhanMatKhau = new JTextField(20);

		    btnGuiLaiMa = new JButton("Gửi lại mã");
		    btnXacNhan = new JButton("Xác nhận");

		    pnContainer = new JPanel(new BorderLayout());
		    pnMain = new JPanel(new BorderLayout());
		    pnHeading = new JPanel(new FlowLayout(FlowLayout.CENTER));
		    lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 20));
		    pnHeading.add(lblTieuDe);

		    pnCenter = new JPanel(new GridBagLayout());
		    GridBagConstraints gbc = new GridBagConstraints();
		    gbc.gridx = 0;
		    gbc.gridy = 0;
		    gbc.anchor = GridBagConstraints.WEST;
		    gbc.insets = new Insets(5, 5, 5, 5);

		    pnCenter.add(lblMaXacNhan, gbc);
		    gbc.gridy++;
		    pnCenter.add(txtMaXacNhan, gbc);
		    gbc.gridy++;
		    pnCenter.add(lblMatKhauMoi, gbc);
		    gbc.gridy++;
		    pnCenter.add(txtMatKhauMoi, gbc);
		    gbc.gridy++;
		    pnCenter.add(lblXacNhanMatKhau, gbc);
		    gbc.gridy++;
		    pnCenter.add(txtXacNhanMatKhau, gbc);
		    
		    
		    pnSounth = new JPanel();
		    pnSounth.add(btnGuiLaiMa);
		    pnSounth.add(btnXacNhan);

		    pnMain.add(pnHeading, BorderLayout.NORTH);
		    pnMain.add(pnCenter, BorderLayout.CENTER);
		    pnMain.add(pnSounth, BorderLayout.SOUTH);


		    pnContainer.add(pnMain, BorderLayout.NORTH);
		    add(pnContainer);
	}
}
