package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.security.Key;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;

import dao.DAOTacGia;
import models.TacGia;

public class TacGiaView extends JPanel implements ActionListener, MouseListener, KeyListener {
	private JPanel pnMain;
	private JPanel pnHeading;
	private JPanel pnThongTinMain;
	private JPanel pnThongTin;
	private JPanel pnChucNangThongTin;
	private JPanel pnChucNang;
	private JPanel pnTimKiem;
	private JPanel pnMainCenter;
	private JPanel pnTable;

	private JLabel lblIdTacGia;
	private JLabel lblTenTacGia;
	private JLabel lblNgaySinh;
	private JLabel lblTitle;
	private JLabel lblSoLuongTacPham;
	private JLabel lblTuKhoa;

	private JTextField txtIdTacGia;
	private JTextField txtTenTacGia;
	private JTextField txtSoLuongTacPham;
	private SimpleDateFormat dfNgaySinh;
	private JDateChooser chooserNgaySinh;
	private JTextField txtTuKhoa;

	private JButton btnThem;
	private JButton btnCapNhat;
	private JButton btnXoa;
	private JButton btnLamMoi;
	private JButton btnXemTatCa;

	private JTable table;
	private DefaultTableModel model;

	// boc cac text
	private JPanel pnIdTacGia;
	private JPanel pnTenTacGia;
	private JPanel pnNgaySinh;
	private JPanel pnSoLuong;

	private DAOTacGia daoTacGia;

	public TacGiaView() {

		setLayout(new BorderLayout(8, 6));
		daoTacGia = new DAOTacGia();
		init();

	}

	private void init() {
		dfNgaySinh = new SimpleDateFormat("dd/MM/yyyy");
		pnMain = new JPanel(new BorderLayout());

		pnHeading = new JPanel();
		lblTitle = new JLabel("Quản Lý Tác Giả");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTitle.setForeground(new Color(26, 102, 227));
		pnHeading.add(lblTitle);

		pnThongTinMain = new JPanel(new BorderLayout());
		pnThongTinMain.setBorder(BorderFactory.createTitledBorder("Thông tin tác giả"));
		pnThongTin = new JPanel(new GridLayout(2, 4, 20, 20));

		lblIdTacGia = new JLabel("Mã tác giả:");
		lblTenTacGia = new JLabel("Tên tác giả:");
		lblSoLuongTacPham = new JLabel("Số lượng tác phẩm:");
		lblNgaySinh = new JLabel("Ngày sinh:");

		txtIdTacGia = new JTextField();
		txtIdTacGia.setEditable(false);
		txtIdTacGia.setText(generateNewTacGiaID());
		txtTenTacGia = new JTextField();
		txtSoLuongTacPham = new JTextField();
		txtSoLuongTacPham.setEditable(false);
		chooserNgaySinh = new JDateChooser();
		chooserNgaySinh.setPreferredSize(new Dimension(200, 22));
		chooserNgaySinh.getCalendarButton().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chooserNgaySinh.setDateFormatString("dd/MM/yyyy");
		chooserNgaySinh.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		chooserNgaySinh.setFont(new Font("SansSerif", Font.PLAIN, 15));
		chooserNgaySinh.getCalendarButton().setPreferredSize(new Dimension(30, 24));
		chooserNgaySinh.getCalendarButton().setBackground(new Color(102, 0, 153));
		chooserNgaySinh.getCalendarButton().setToolTipText("Chọn ngày sinh");

		Insets labelInsets = new Insets(0, 70, 0, 10);
		lblIdTacGia.setBorder(new EmptyBorder(labelInsets));
		lblTenTacGia.setBorder(new EmptyBorder(labelInsets));
		lblSoLuongTacPham.setBorder(new EmptyBorder(labelInsets));
		lblNgaySinh.setBorder(new EmptyBorder(labelInsets));

		pnThongTin.add(lblIdTacGia);
		pnThongTin.add(txtIdTacGia);
		pnThongTin.add(lblTenTacGia);
		pnThongTin.add(txtTenTacGia);
		pnThongTin.add(lblSoLuongTacPham);
		pnThongTin.add(txtSoLuongTacPham);
		pnThongTin.add(lblNgaySinh);
		pnThongTin.add(chooserNgaySinh);

		pnChucNangThongTin = new JPanel(new BorderLayout());
		pnChucNang = new JPanel(new FlowLayout(5));
		btnCapNhat = new JButton("Cập nhật");
		btnLamMoi = new JButton("Làm mới");
		btnThem = new JButton("Thêm");
		btnXemTatCa = new JButton("Xem tất cả");
		btnXoa = new JButton("Xóa");

		ImageIcon iconThem = new ImageIcon(getClass().getResource("/icons/add.png"));
		ImageIcon iconCapNhat = new ImageIcon(getClass().getResource("/icons/capnhat.png"));
		ImageIcon iconLamMoi = new ImageIcon(getClass().getResource("/icons/lammoi.png"));
		ImageIcon iconXoa = new ImageIcon(getClass().getResource("/icons/xoa.png"));
		
		btnThem.setIcon(iconThem);
		btnLamMoi.setIcon(iconLamMoi);
		btnCapNhat.setIcon(iconCapNhat);
		
		pnChucNang.add(btnThem);
		pnChucNang.add(btnCapNhat);
//		pnChucNang.add(btnXoa);
		pnChucNang.add(btnLamMoi);
		Insets btnInsert = new Insets(0, 70, 0, 0);
		pnChucNang.setBorder(new EmptyBorder(btnInsert));
		pnChucNangThongTin.add(pnChucNang);

		pnThongTinMain.add(pnThongTin, BorderLayout.NORTH);
		pnThongTinMain.add(pnChucNangThongTin, BorderLayout.SOUTH);

		pnMainCenter = new JPanel(new BorderLayout());
		pnTimKiem = new JPanel(new FlowLayout(5));
		lblTuKhoa = new JLabel("Từ khóa:");
		txtTuKhoa = new JTextField(20);
		btnXemTatCa = new JButton("Xem tất cả");
		pnTimKiem.add(lblTuKhoa);
		pnTimKiem.add(txtTuKhoa);
		pnTimKiem.add(btnXemTatCa);

		pnTable = new JPanel(new BorderLayout());
		pnTable.setBorder(BorderFactory.createTitledBorder("Danh mục"));
		model = new DefaultTableModel();
		table = new JTable();
		model.addColumn("Mã tác giả");
		model.addColumn("Tên tác giả");
		model.addColumn("Ngày sinh");
		model.addColumn("Số lượng tác phẩm");
		table.setModel(model);
		JScrollPane scrollPane = new JScrollPane(table);
		pnTable.add(scrollPane);

		pnMainCenter.add(pnTimKiem, BorderLayout.NORTH);
		pnMainCenter.add(pnTable, BorderLayout.SOUTH);

		pnMain.add(pnHeading, BorderLayout.NORTH);
		pnMain.add(pnThongTinMain, BorderLayout.CENTER);
		pnMain.add(pnMainCenter, BorderLayout.SOUTH);
		add(pnMain);

		btnThem.addActionListener(this);
		btnCapNhat.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnXemTatCa.addActionListener(this);
//		btnXoa.addActionListener(this);
		table.addMouseListener(this);
		txtTuKhoa.addKeyListener(this);
		loadData();
		lamMoi();
	}

	private void loadData() {
		model.setRowCount(0);
		try {
			for (TacGia tg : daoTacGia.getAllTacGia()) {
				String soLuongTacPham = String.valueOf(tg.getSoLuongTacPham());
				String row[] = { tg.getIdTacGia(), tg.getTenTacGia(), dfNgaySinh.format(tg.getNgaySinh()),
						soLuongTacPham };
				model.addRow(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			themTacGia();
		} else if (o.equals(btnLamMoi)) {
			lamMoi();
		} else if (o.equals(btnCapNhat)) {
			capNhatTacGia();
		} else if (o.equals(btnXoa)) {
			xoaTacGia();
		} else if (o.equals(btnXemTatCa)) {
			lamMoi();
			loadData();
		}

	}

	private void xoaTacGia() {
		int row = table.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(this, "Bạn cần phải chọn dòng xóa");
		} else {
			try {
				int hoiNhac = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn xóa không", "Cảnh báo",
						JOptionPane.YES_NO_OPTION);
				if (hoiNhac == JOptionPane.YES_OPTION) {
					String idTacGia = txtIdTacGia.getText();
					daoTacGia.xoaTacGia(idTacGia);
					JOptionPane.showMessageDialog(this, "Xóa thông tin thành công");
					loadData();
					lamMoi();
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Xóa thông tin không thành công");
			}
		}

	}

	private void capNhatTacGia() {
		int row = table.getSelectedRow();
		if (row < 0) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng để cập nhật", "Cảnh báo",
					JOptionPane.WARNING_MESSAGE);
			return;
		} else if (validataFields()) {

			String idTacGia = txtIdTacGia.getText().trim();
			String tenTacGia = txtTenTacGia.getText().trim();
			java.util.Date utilDate = chooserNgaySinh.getDate();
			java.sql.Date ngaySinh = new java.sql.Date(utilDate.getTime());
			int soLuongTacPham = 0;
			
			java.util.Date currentDate = new java.util.Date();
			if (ngaySinh.after(currentDate)) {
				JOptionPane.showMessageDialog(this, "Ngày sinh không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			TacGia tg = new TacGia(idTacGia, tenTacGia, ngaySinh, soLuongTacPham);
			try {
				boolean success = daoTacGia.capNhatTacGia(tg);
				if (success) {
					JOptionPane.showMessageDialog(this, "Cập nhật thông tin thành công");
					loadData();
				} else {
					JOptionPane.showMessageDialog(this, "Cập nhật thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
			} catch (SQLException e) {
				String errorMessage = "Cập nhật thất bại: " + e.getMessage();
				JOptionPane.showMessageDialog(this, errorMessage, "Lỗi", JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	private void lamMoi() {
		txtIdTacGia.setText(generateNewTacGiaID());
		txtTenTacGia.setText("");
		txtSoLuongTacPham.setText("");
		chooserNgaySinh.setDate(new Date());
		txtTuKhoa.setText("");

	}

	private boolean validateDateChooser(JDateChooser dateChooser) {
		Date selectedDate = dateChooser.getDate();
		if (selectedDate == null) {
			showErrorDialog("Vui lòng chọn ngày sinh!");
			return false;
		}
		Date currentDate = new Date();
		if (selectedDate.after(currentDate)) {
			showErrorDialog("Ngày sinh không được lớn hơn ngày hiện tại!");
			return false;
		}

		java.sql.Date ngaySinh = new java.sql.Date(selectedDate.getTime());
		return true;
	}

	private boolean validataFields() {
		return validataField(txtTenTacGia, "^[a-zA-Z][a-zA-Z\\s]*[a-zA-Z]$",
				"Tên tác giả không hợp lệ. Phải bắt đầu bằng chữ cái, không chấp nhận ký tự đặc biệt.")
				&& validateDateChooser(chooserNgaySinh);
	}

	private boolean validataField(JTextField textField, String regex, String errorMessage) {
		String fieldValue = textField.getText().trim();
		if (fieldValue.isEmpty()) {
			showErrorDialog("Vui lòng nhập giá trị cho " + textField.getName() + "!");
			textField.requestFocus();
			return false;
		}

		if (!fieldValue.matches(regex)) {
			showErrorDialog(errorMessage);
			textField.requestFocus();
			textField.selectAll();
			return false;
		}
		return true;
	}

	private void showErrorDialog(String message) {
		JOptionPane.showMessageDialog(this, message, "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
	}

	private void showSuccessMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}

	private void showErrorMessage(String message) {
		JOptionPane.showMessageDialog(this, message, "Lỗi", JOptionPane.ERROR_MESSAGE);
	}

	private void themTacGia() {
		String idTacGia = txtIdTacGia.getText();
		String tenTacGia = txtTenTacGia.getText();
		java.util.Date date = chooserNgaySinh.getDate();
		java.sql.Date ngaySinh = new java.sql.Date(date.getTime());
		int soLuongTacPham = 0;
		if (validataFields()) {
			try {
				TacGia tg = new TacGia(idTacGia, tenTacGia, ngaySinh, soLuongTacPham);
				if (daoTacGia.checkIdTacGia(idTacGia)) {
					JOptionPane.showMessageDialog(this, "Trùng ID nhà cung cấp. Vui lòng chọn ID khác.");
					return;
				} else {
					try {
						boolean kiemTra = daoTacGia.themTacGia(tg);
						if (kiemTra) {
							model.addRow(new Object[] { idTacGia, tenTacGia, ngaySinh, soLuongTacPham });
							loadData();
							JOptionPane.showMessageDialog(this, "Thêm thông tin thành công");
							lamMoi();
						} else {
							JOptionPane.showMessageDialog(this, "Lỗi khi thêm thông tin");
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			} catch (HeadlessException | SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private String generateNewTacGiaID() {
		String idPrefix = "TG";
		int newProductIDNumber = 1;
		String currentDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
		try {
			String previousProductID = daoTacGia.getLatestTacGiaID();
			if (previousProductID != null && !previousProductID.isEmpty()) {
				int oldProductIDNumber = Integer.parseInt(previousProductID.substring(10));
				newProductIDNumber = oldProductIDNumber + 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		String newProductID = idPrefix + currentDate + String.format("%04d", newProductIDNumber);
		return newProductID;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		if (row >= 0) {
			txtIdTacGia.setText(model.getValueAt(row, 0).toString());
			txtTenTacGia.setText(model.getValueAt(row, 1).toString());

			String ngaySinhString = model.getValueAt(row, 2).toString();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			java.util.Date ngaySinhDate = null;
			try {
				ngaySinhDate = dateFormat.parse(ngaySinhString);
			} catch (ParseException ex) {
				ex.printStackTrace();
			}

			chooserNgaySinh.setDate(ngaySinhDate);

			txtSoLuongTacPham.setText(model.getValueAt(row, 3).toString());
		}
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
		SwingUtilities.invokeLater(() -> {
			Object o = e.getSource();
			if (o.equals(txtTuKhoa)) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(model);
				table.setRowSorter(tr);
				tr.setRowFilter(RowFilter.regexFilter("(?i)" + txtTuKhoa.getText().trim(), 0, 1, 2, 3));
			}
		});
	}

}
