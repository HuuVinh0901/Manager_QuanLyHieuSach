package views;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import dao.DAONhanVien;
import dao.DAOTaiKhoan;
import models.NhanLuc;
import models.NhanVien;
import models.TaiKhoan;

public class SetPassWordView extends Dialog implements ActionListener, MouseListener {
	private JLabel lblTieuDe;
	private JLabel lblSub;
	private JLabel lblMaXacNhan;
	private JLabel lblMatKhauMoi;
	private JLabel lblXacNhanMatKhau;
	private JLabel lblTenDangNhap;
	private JLabel lblTenNhanVien;
	private JTextField txtMaXacNhan;
	private JPasswordField txtMatKhauMoi;
	private JPasswordField txtXacNhanMatKhau;
	private JTextField txtTenDangNhap;
	private JTextField txtTenNhanVien;
	private JButton btnGuiLaiMa;
	private JButton btnXacNhan;

	private JPanel pnContainer;
	private JPanel pnMain;
	private JPanel pnHeading;
	private JPanel pnCenter;
	private JPanel pnSounth;
	private TaiKhoan taiKhoan;
	private NhanVien nhanvien;
	private DAONhanVien daoNhanVien;
	private DAOTaiKhoan daoTaiKhoan;

	public SetPassWordView(NhanVien nv) {
		super((JDialog) null, "Đặt lại mật khẩu", true);
		this.nhanvien = nv;
		daoNhanVien = new DAONhanVien();
		daoTaiKhoan = new DAOTaiKhoan();
		setSize(300, 430);
		setTitle("Đặt lại mật khẩu");
		ImageIcon icon = new ImageIcon(getClass().getResource("/icons/logo.png"));
		setIconImage(icon.getImage());
		setLocationRelativeTo(null);

		setModal(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int result = JOptionPane.showConfirmDialog(SetPassWordView.this, "Bạn có chắc chắn muốn đóng cửa sổ?",
						"Xác nhận đóng cửa sổ", JOptionPane.YES_NO_OPTION);

				if (result == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});
		lblTieuDe = new JLabel("Đặt lại mật khẩu của bạn");
		lblMaXacNhan = new JLabel("Số điện thoại");
		lblMatKhauMoi = new JLabel("Mật khẩu mới");
		lblXacNhanMatKhau = new JLabel("Xác nhận mật khẩu");
		lblTenDangNhap = new JLabel("Mã Nhân viên");
		lblTenNhanVien = new JLabel("Tên nhân viên");

		txtMaXacNhan = new JTextField(20);
		txtMatKhauMoi = new JPasswordField(20);
		txtXacNhanMatKhau = new JPasswordField(20);
		txtTenDangNhap = new JTextField(20);
		txtTenNhanVien = new JTextField(20);

		txtTenDangNhap.setText(nv.getId());
		txtTenNhanVien.setText(nv.getTen());
//		txtMaXacNhan.setText(nv.getSoDienThoai());

		btnGuiLaiMa = new JButton("Hủy bỏ");
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

		gbc.gridy++;
		pnCenter.add(lblTenDangNhap, gbc);
		gbc.gridy++;
		pnCenter.add(txtTenDangNhap, gbc);

		gbc.gridy++;
		pnCenter.add(lblTenNhanVien, gbc);
		gbc.gridy++;
		pnCenter.add(txtTenNhanVien, gbc);

		gbc.gridy++;
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
		btnGuiLaiMa.addActionListener(this);
		btnXacNhan.addActionListener(this);
		init();
	}

	private void init() {

//		changeAccount(nhanVien);
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
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnGuiLaiMa)) {
			xacNhanHuy();
		} else if (o.equals(btnXacNhan)) {
			capNhatMatKhau();
			lamMoi();
		}

	}

	private void lamMoi() {
		txtMatKhauMoi.setText("");
		txtXacNhanMatKhau.setText("");
		txtMatKhauMoi.requestFocus();

	}

	private boolean kiemTraMatKhau(String matKhauMoi, String xacNhanMatKhau) {
		return matKhauMoi.equals(xacNhanMatKhau);
	}

	private void capNhatMatKhau() {
		String matKhauMoi = txtMatKhauMoi.getText();
		String xacNhanMatKhau = txtXacNhanMatKhau.getText();
		String nhanvien = txtTenDangNhap.getText();
		String soDienThoai = txtMaXacNhan.getText();
		TaiKhoan tk = new TaiKhoan(nhanvien);
		try {
			boolean soDienThoaiTonTai = daoNhanVien.kiemTraSoDienThoaiTonTai(soDienThoai);
			if (!soDienThoaiTonTai) {
				JOptionPane.showMessageDialog(this, "Số điện thoại không tồn tại trong hệ thống", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
				return;
			} else if (matKhauMoi.isEmpty() || xacNhanMatKhau.isBlank()) {
				JOptionPane.showConfirmDialog(this, "Mật khẩu không được để trống","Cảnh báo", JOptionPane.ERROR_MESSAGE);
				return;
			} else if (kiemTraMatKhau(matKhauMoi, xacNhanMatKhau)) {
				try {
					BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
					String hasdPassword = passwordEncoder.encode(xacNhanMatKhau);
					boolean updateSuccess = daoTaiKhoan.capNhatTaiKhoan(tk.getIdTaiKhoan(), hasdPassword);
					if (updateSuccess) {
						JOptionPane.showMessageDialog(this, "Đổi mật khẩu thành công");
					} else {
						JOptionPane.showMessageDialog(this, "Đổi mật khẩu thất bại", "Cảnh báo",
								JOptionPane.WARNING_MESSAGE);
					}
				} catch (SQLException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(this, "Lỗi khi đổi mật khẩu", "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(this, "Mật khẩu và xác nhận mật khẩu không trùng khớp", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void xacNhanHuy() {
		int hoiNhac = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn hủy", "Cảnh báo",
				JOptionPane.YES_NO_OPTION);
		if (hoiNhac == JOptionPane.YES_OPTION) {
			dispose();
		}
	}
}
