package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.toedter.calendar.JDateChooser;

import connection.ConnectDB;
import controllers.MenuItem;
import dao.DAOKhachHang;
import dao.DAONhanVien;
import dao.DAOQuanLy;
import dao.DAOTaiKhoan;
import models.KhachHang;
import models.NhanVien;
import models.QuanLy;
import models.TaiKhoan;

public class QuanLyView extends JPanel implements KeyListener, MouseListener, ActionListener {

	private JDateChooser chooserNgaySinh;
	private JTextField txtTenNV;
	private JTextField txtsdt;
	private JTextField txtEmail;
	private JTextField txtDiaChi;
	private JTextField txtTimKiem;
	private JTextField txtID;
	private JLabel lbTenNV;
	private JLabel lbsdt;
	private JLabel lbEmail;
	private JLabel lbDiaChi;
	private JLabel lbNgaySinh;
	private JLabel lbGioiTinh;
	private JLabel lbID;

	private JLabel lbTimKiem;
	private JRadioButton rbNam;
	private JRadioButton rbNu;
	private JComboBox<Object> cbTrangThai, cbChucVu, cbTimKiem;
	private JLabel lbChucVu;
	private JLabel lbTrangThai;
	private JTable tableNhanVien;
	private DefaultTableModel modelNhanVien;
	private JButton btnThemNV;
	private JButton btnCapNhatNV;
	private JButton btnXoaNV;
	private JButton btnLamMoi;
	private JButton btnXemTatCa;
	private SimpleDateFormat dfNgaySinh;
	private DAOQuanLy daoQuanLy;
	private DAOTaiKhoan daoTaiKhoan;

	public QuanLyView() {
		dfNgaySinh = new SimpleDateFormat("dd/MM/yyyy");
		daoTaiKhoan = new DAOTaiKhoan();
		daoQuanLy = new DAOQuanLy();

		setLayout(new BorderLayout());

		JPanel pnNouth = new JPanel(new BorderLayout());
		JPanel pnSounth = new JPanel(new BorderLayout());
		JPanel pnTitle = new JPanel();
		JPanel pnInfo = new JPanel(new GridLayout(5, 1, 5, 5));
		JPanel pnChucNang = new JPanel(new GridLayout(1, 4, 10, 10));
		JPanel pnTimKiem = new JPanel(new GridLayout(1, 3, 10, 10));

//		Tiêu đề
		JLabel lblTieuDe = new JLabel("THÔNG TIN QUẢN LÝ");
		lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTieuDe.setForeground(new Color(26, 102, 227));
		pnTitle.add(lblTieuDe);
		pnNouth.add(pnTitle, BorderLayout.NORTH);
		add(pnNouth, BorderLayout.NORTH);

		lbID = new JLabel("ID quản lý:");
		lbTenNV = new JLabel("Tên quản lý:");
		txtTenNV = new JTextField();
		lbsdt = new JLabel("Số điện thoại:");
		txtsdt = new JTextField();
		lbEmail = new JLabel("Email:");
		txtEmail = new JTextField();
		lbDiaChi = new JLabel("Địa chỉ:");
		txtDiaChi = new JTextField();
		lbChucVu = new JLabel("Chức vụ:");
		lbTrangThai = new JLabel("Trạng thái:");
		lbGioiTinh = new JLabel("Giới tính:");
		lbNgaySinh = new JLabel("Ngày sinh:");
		txtID = new JTextField();
		try {
			txtID.setText(autoID());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		txtID.setEditable(false);
		cbChucVu = new JComboBox<Object>(new Object[] { "Quản lý" });
		cbTrangThai = new JComboBox<Object>(new Object[] { "Đang làm việc", "Đã nghỉ việc" });

		chooserNgaySinh = new JDateChooser();
		chooserNgaySinh.getCalendarButton().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chooserNgaySinh.setBounds(100, 310, 200, 40);
		chooserNgaySinh.setDateFormatString("dd/MM/yyyy");
		chooserNgaySinh.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		chooserNgaySinh.setFont(new Font("SansSerif", Font.PLAIN, 15));
		chooserNgaySinh.getCalendarButton().setPreferredSize(new Dimension(30, 24));
		chooserNgaySinh.getCalendarButton().setBackground(new Color(102, 0, 153));
		chooserNgaySinh.getCalendarButton().setToolTipText("Chọn ngày sinh");
		rbNam = new JRadioButton("Nam");
		rbNu = new JRadioButton("Nữ");
		// Tạo button groud giới tính
		ButtonGroup groupGT = new ButtonGroup();
		groupGT.add(rbNam);
		groupGT.add(rbNu);
		pnInfo.add(lbID);
		pnInfo.add(txtID);
		pnInfo.add(lbTenNV);
		pnInfo.add(txtTenNV);
		pnInfo.add(lbsdt);
		pnInfo.add(txtsdt);
		pnInfo.add(lbEmail);
		pnInfo.add(txtEmail);
		pnInfo.add(lbDiaChi);
		pnInfo.add(txtDiaChi);
		pnInfo.add(lbChucVu);
		pnInfo.add(cbChucVu);
		pnInfo.add(lbTrangThai);
		pnInfo.add(cbTrangThai);
		pnInfo.add(lbNgaySinh);
		pnInfo.add(chooserNgaySinh);
		pnInfo.add(lbGioiTinh);
		pnInfo.setBorder(BorderFactory.createTitledBorder("Thông tin nhân viên"));

		pnInfo.add(rbNam);
		pnInfo.add(rbNu);

		pnNouth.add(pnInfo, BorderLayout.CENTER);

		lbTimKiem = new JLabel("Tìm kiếm nhân viên:");
		txtTimKiem = new JTextField();
		pnTimKiem.add(lbTimKiem);
		pnTimKiem.add(txtTimKiem);
		ImageIcon iconThem = new ImageIcon(getClass().getResource("/icons/add.png"));
		ImageIcon iconCapNhat = new ImageIcon(getClass().getResource("/icons/capnhat.png"));
		ImageIcon iconLamMoi = new ImageIcon(getClass().getResource("/icons/lammoi.png"));
		ImageIcon iconXoa = new ImageIcon(getClass().getResource("/icons/xoa.png"));
		btnThemNV = new JButton("THÊM QUẢN LÝ");
		btnThemNV.setIcon(iconThem);
		btnCapNhatNV = new JButton("CẬP NHẬT THÔNG TIN ");
		btnCapNhatNV.setIcon(iconCapNhat);
		btnXoaNV = new JButton("XÓA QUẢN LÝ");
		btnXoaNV.setIcon(iconXoa);
		btnLamMoi = new JButton("LÀM MỚI");
		btnLamMoi.setIcon(iconLamMoi);
		btnXemTatCa = new JButton("XEM TẤT CẢ");
		pnChucNang.add(btnThemNV);
		pnChucNang.add(btnCapNhatNV);
		pnChucNang.add(btnXoaNV);
		pnChucNang.add(btnLamMoi);
		pnChucNang.setBorder(BorderFactory.createTitledBorder("Chức năng"));
		pnNouth.add(pnChucNang, BorderLayout.SOUTH);
		add(pnSounth, BorderLayout.CENTER);
		pnSounth.add(pnTimKiem, BorderLayout.NORTH);
		pnTimKiem.add(btnXemTatCa);

		modelNhanVien = new DefaultTableModel();
		tableNhanVien = new JTable();
		modelNhanVien.addColumn("ID");
		modelNhanVien.addColumn("Tên");
		modelNhanVien.addColumn("Số điện thoại");
		modelNhanVien.addColumn("Email");
		modelNhanVien.addColumn("Địa chỉ");
		modelNhanVien.addColumn("Ngày sinh");
		modelNhanVien.addColumn("Giới tính");
		modelNhanVien.addColumn("Chức vụ");
		modelNhanVien.addColumn("Trạng thái");
		tableNhanVien.setModel(modelNhanVien);
		JScrollPane scrollPane = new JScrollPane(tableNhanVien);
//        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh sách nhân viên"));
		pnSounth.add(scrollPane, BorderLayout.CENTER);

		btnLamMoi.addActionListener(this);
		btnThemNV.addActionListener(this);
		btnCapNhatNV.addActionListener(this);
		btnXoaNV.addActionListener(this);
		btnXemTatCa.addActionListener(this);
		tableNhanVien.addMouseListener(this);
		txtTimKiem.addKeyListener(this);
		txtTenNV.addKeyListener(this);
		txtDiaChi.addKeyListener(this);
		txtEmail.addKeyListener(this);
		txtsdt.addKeyListener(this);

		try {
			ConnectDB.getinstance().connect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		loadData();
	}

	public static String hashWithSHA3_256(String input) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA3-256");
		byte[] hashBytes = md.digest(input.getBytes());
		StringBuilder sb = new StringBuilder();
		for (byte b : hashBytes) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}

	public void ThemNV() throws SQLException {
		String id = autoID();
		String ten = txtTenNV.getText();
		String email = txtEmail.getText();
		String sdt = txtsdt.getText();
		String diaChi = txtDiaChi.getText();
		java.util.Date date = chooserNgaySinh.getDate();
		Date ngaySinh = new Date(date.getYear(), date.getMonth(), date.getDate());
		Boolean GioiTinh = rbNam.isSelected();
		String TrangThai = (String) cbTrangThai.getSelectedItem().toString();
		Boolean TrangThaibooleanValue = Boolean.parseBoolean(TrangThai);
		String chucVu = cbChucVu.getSelectedItem().toString();
		Date ngayLap = new Date(System.currentTimeMillis());
		String matkhau = "1111";

		// String hashedPasswordSHA3_256 = hashWithSHA3_256(matkhau);
		//
		// BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
		// String hashedPasswordBCrypt = passwordEncoder.encode(hashedPasswordSHA3_256);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
		String hasdPassword = passwordEncoder.encode(matkhau);
		TaiKhoan tk = new TaiKhoan(id, hasdPassword, ngayLap);
		System.out.println(hasdPassword);
		QuanLy nv = new QuanLy();
		nv.setId(id);
		nv.setTen(ten);
		nv.setChucVu(chucVu);
		nv.setGioiTinh(GioiTinh);
		nv.setNgaySinh(ngaySinh);
		nv.setDiaChi(diaChi);
		nv.setEmail(email);
		nv.setTrangThai(TrangThaibooleanValue);
		nv.setSoDienThoai(sdt);

		daoTaiKhoan.createTK(tk);
		daoQuanLy.themQuanLy(nv);
		modelNhanVien.addRow(new Object[] { id, ten, sdt, diaChi, email, dfNgaySinh.format(nv.getNgaySinh()),
				nv.isGioiTinh() ? "Nam" : "Nữ", chucVu, TrangThai });
	}

	private void loadData() {
		modelNhanVien.setRowCount(0);
		for (QuanLy nv : daoQuanLy.getAllDanhSachNV()) {
			modelNhanVien.addRow(new Object[] { nv.getId(), nv.getTen(), nv.getSoDienThoai(), nv.getEmail(),
					nv.getDiaChi(), dfNgaySinh.format(nv.getNgaySinh()), nv.isGioiTinh() ? "Nam" : "Nữ", nv.getChucVu(),
					nv.isTrangThai() ? "Đang làm việc" : "Đã nghỉ việc" });

		}
	}

	private void CapNhatNV() {
		String id = txtID.getText();
		String ten = txtTenNV.getText();
		String diaChi = txtDiaChi.getText();
		String soDienThoai = txtsdt.getText();
		String email = txtEmail.getText();

		java.util.Date date = chooserNgaySinh.getDate();
		Date ngaySinh = new Date(date.getYear(), date.getMonth(), date.getDate());
		Boolean gioiTinh = null;
		if (rbNam.isSelected()) {
			gioiTinh = true;
		}
		if (rbNu.isSelected()) {
			gioiTinh = false;
		}
		String trangThaiValue = cbTrangThai.getSelectedItem().toString();
		Boolean trangThai = null;
		if (trangThaiValue.equals("Đang làm việc")) {
			trangThai = true;
		}
		if (trangThaiValue.equals("Đã nghỉ việc")) {
			trangThai = false;
		}
		String chucVu = cbChucVu.getSelectedItem().toString();
		QuanLy nv = new QuanLy();
		nv.setTen(ten);
		nv.setSoDienThoai(soDienThoai);
		nv.setNgaySinh(ngaySinh);
		nv.setEmail(email);
		nv.setDiaChi(diaChi);
		nv.setGioiTinh(gioiTinh);
		nv.setId(id);
		nv.setTrangThai(trangThai);
		nv.setChucVu(chucVu);
		if (valiDate()) {
			try {
				daoQuanLy.updateNhanVien(nv);
				;
				loadData();
				JOptionPane.showMessageDialog(this, "Cập nhật thành công");
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Cập nhật thất bại");
			}
		}

	}

	public boolean valiDate() {
		
		String ten = txtTenNV.getText().trim();

		
		Boolean gioiTinh;
		if(rbNam.isSelected() || rbNu.isSelected()) {
			gioiTinh=true;
		}
		else {
			gioiTinh=false;
		}
		java.util.Date ngaySinh = chooserNgaySinh.getDate();
		java.util.Date ngayHienTai=new java.util.Date();
		String diaChi = txtDiaChi.getText().trim();
		String email = txtEmail.getText().trim();
		String soDienThoai = txtsdt.getText().trim();
		
	
		if(ten.equals("") || diaChi.equals("")|| email.equals("") || soDienThoai.equals("")) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin đầy đủ!", "Thông báo",
					JOptionPane.WARNING_MESSAGE);
			txtTenNV.requestFocus();
			
			return false;
		}
		if (!(ten.length() > 0 && ten.matches("^[A-ZÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸĐ][a-zàáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđ]*(?:[ ][A-ZÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸĐ][a-zàáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđ]*)*$"))) {
			JOptionPane.showMessageDialog(this, "Tên phải viết hoa và không chứa số", "Thông báo",
					JOptionPane.WARNING_MESSAGE);
			txtTenNV.selectAll();
			txtTenNV.requestFocus();
			return false;
		}

		if (!(diaChi.length() > 0 && diaChi.matches("^[^!@#$%^&*()+-]*$"))) {
			JOptionPane.showMessageDialog(this, "Địa chỉ không được chứa kí tự đặc biệt", "Thông báo",
					JOptionPane.WARNING_MESSAGE);
			txtDiaChi.requestFocus();
			txtDiaChi.selectAll();
			return false;
		}
		if (!(email.length() > 0 && email.matches("^[A-Za-z][A-Za-z0-9@.]*$"))) {
			JOptionPane.showMessageDialog(this, "Email phải bắt đầu bằng chữ và không được chứa kí tự đặc biệt", "Thông báo",
					JOptionPane.WARNING_MESSAGE);
			txtEmail.requestFocus();
			txtEmail.selectAll();
			return false;
		}
		if (!(ngaySinh!=null  && (ngayHienTai.getYear()-ngaySinh.getYear()>18))) {
			JOptionPane.showMessageDialog(this, "Nhân viên chưa đủ 18 tuổi", "Thông báo",
					JOptionPane.WARNING_MESSAGE);
			chooserNgaySinh.requestFocus();
			
			return false;
		}
		if(!(soDienThoai.length()>0 && soDienThoai.matches("^(0|\\+84)[0-9]{9}$")))
		{
			JOptionPane.showMessageDialog(this, "Số điện thoại gồm 10 số và bắt đầu bằng 0 hoặc +84", "Thông báo",
					JOptionPane.WARNING_MESSAGE);
			txtsdt.requestFocus();
			txtsdt.selectAll();
			return false;
		}
		if(gioiTinh==false)
		{
			JOptionPane.showMessageDialog(this, "Vui lòng chọn giới tính", "Thông báo",
					JOptionPane.WARNING_MESSAGE);
			
			return false;
		}
		return true;
	}

	private String autoID() throws SQLException {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		java.util.Date date = new java.util.Date();

		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String query = "SELECT MAX(idQuanLy) FROM QuanLy";
		PreparedStatement preparedStatement = con.prepareStatement(query);

		ResultSet resultSet = preparedStatement.executeQuery();

		String lastID = null;
		if (resultSet.next()) {
			lastID = resultSet.getString(1);
		}

		String newID;
		if (lastID != null) {
			int number = Integer.parseInt(lastID.substring(10));
			number++; // Tăng giá trị số tiếp theo
			newID = "QL" + dateFormat.format(date) + String.format("%4d", number).replace(" ", "0");
		} else {
			newID = "QL" + dateFormat.format(date) + "0001";
		}

		preparedStatement.close();
		return newID;

	}

	private void xoaNV() {
		int row = tableNhanVien.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(this, "Phải chọn dòng cần xoá");
		} else {
			try {
				int HopThoai = JOptionPane.showConfirmDialog(this, "Bạn có muốn xoá dòng này không?", "Cảnh báo",
						JOptionPane.YES_NO_OPTION);
				if (HopThoai == JOptionPane.YES_OPTION) {
					modelNhanVien.removeRow(row);
					String manv = txtID.getText();
					daoQuanLy.DeleteNV(manv);
					daoTaiKhoan.DeleteTK(manv);
					JOptionPane.showMessageDialog(this, "Xoá thành công");
				}
			} catch (Exception e2) {
				e2.printStackTrace();
				JOptionPane.showMessageDialog(this, "Xoá thất bại");
			}
		}
	}

	private void lamMoi() {

		try {
			txtID.setText(autoID());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		txtTenNV.setText("");
		txtDiaChi.setText("");
		txtsdt.setText("");
		txtEmail.setText("");
		rbNam.setSelected(true);
		rbNu.setSelected(false);
		cbChucVu.setSelectedItem("Quản lý");
		cbTrangThai.setSelectedItem("Đang làm việc");

		chooserNgaySinh.setDate(new java.util.Date());
		txtTenNV.requestFocus();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnThemNV)) {
			if (valiDate()) {
				try {
					ThemNV();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}
		if (o.equals(btnLamMoi)) {
			lamMoi();
		}
		if (o.equals(btnXoaNV)) {
			xoaNV();
		}
		if (o.equals(btnCapNhatNV)) {
//			 CapNhatNV();
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tableNhanVien.getSelectedRow();
		txtID.setText(modelNhanVien.getValueAt(row, 0).toString());
		txtTenNV.setText(modelNhanVien.getValueAt(row, 1).toString());
		txtsdt.setText(modelNhanVien.getValueAt(row, 2).toString());
		txtEmail.setText(modelNhanVien.getValueAt(row, 3).toString());
		txtDiaChi.setText(modelNhanVien.getValueAt(row, 4).toString());
		String ngaySinh = modelNhanVien.getValueAt(row, 5).toString();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date valueNgaySinh = null;
		try {
			valueNgaySinh = dateFormat.parse(ngaySinh);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		chooserNgaySinh.setDate(valueNgaySinh);
		rbNam.setSelected(modelNhanVien.getValueAt(row, 6).toString() == "Nam");
		rbNu.setSelected(modelNhanVien.getValueAt(row, 6).toString() == "Nữ");
		cbChucVu.setSelectedItem(modelNhanVien.getValueAt(row, 7).toString());
		cbTrangThai.setSelectedItem(modelNhanVien.getValueAt(row, 8).toString());

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
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(() -> {
			Object o = e.getSource();
			if (o.equals(txtTimKiem)) {
				DefaultTableModel model = (DefaultTableModel) tableNhanVien.getModel();
				TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(model);
				tableNhanVien.setRowSorter(tr);
				tr.setRowFilter(RowFilter.regexFilter("(?i)" + txtTimKiem.getText().trim(), 1));
			} else if (e.getKeyCode() == KeyEvent.VK_F5) {
				lamMoi();
//				loadData();
			}
		});
	}
}
