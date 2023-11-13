package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EventListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import connection.ConnectDB;
import dao.DAONhaCungCap;
import models.NhaCungCap;

public class LoaiNhaSanXuatView extends JPanel implements ActionListener, MouseListener, KeyListener, EventListener {
	private JPanel pnMain;
	private JPanel pnHeader, pnTitle;
	private JPanel pnCener;
	private JLabel lblTitle;
	private JPanel pnMainThongTin, pnLoaiThongTin, pnChucNang;
	private JLabel lblIdLoaiSanPham;
	private JLabel lblTenLoaiSanPham;
	private JLabel lblTuKhoa;
	private JTextField txtIdLoaiSanPham;
	private JTextField txtTenLoaiSanPham;
	private JTextField txtTimKiem;
	private JButton btnThem;
	private JButton btnCapNhat;
	private JButton btnXoa;
	private JButton btnLamMoi;
	private JButton btnTimKiem;
	private JPanel pnSouth, pnSouthNorth, pnSouthBottom, pnDanhMuc;
	private JTable tableSP;
	private DefaultTableModel modelSP;
	private JLabel lblDiaChi;
	private JLabel lblSoDienThoai;
	private JTextField txtDiaChi;
	private JTextField txtSoDienThoai;
	private JButton btnXemTatCa;
	private DAONhaCungCap daoNhaCungCap;

	public LoaiNhaSanXuatView() {
		daoNhaCungCap = new DAONhaCungCap();

		setLayout(new BorderLayout());
		pnMain = new JPanel(new BorderLayout(8, 6));

		pnHeader = new JPanel();
		pnHeader.add(lblTitle = new JLabel("Quản Lý Nhà Cung Cấp"));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTitle.setForeground(new Color(26, 102, 227));
//		pnHeader.setBackground(new Color(208, 225, 253));

		// layout thong tin
		pnMainThongTin = new JPanel(new BorderLayout());
		pnMainThongTin.setBorder(BorderFactory.createTitledBorder("Thông tin nhà cung cấp"));

		pnLoaiThongTin = new JPanel(new GridLayout(2, 4, 10, 10));

		lblIdLoaiSanPham = new JLabel("Mã nhà cung cấp:");
		txtIdLoaiSanPham = new JTextField(20);
		txtIdLoaiSanPham.setText(generateNewNhaCungCapID());
		txtIdLoaiSanPham.setEditable(false);
		txtIdLoaiSanPham.setPreferredSize(new Dimension(150, 30));

		lblTenLoaiSanPham = new JLabel("Tên nhà cung cấp:");
		txtTenLoaiSanPham = new JTextField(20);
		txtTenLoaiSanPham.setPreferredSize(new Dimension(150, 30));

		lblDiaChi = new JLabel("Địa chỉ:");
		txtDiaChi = new JTextField(20);
		txtDiaChi.setPreferredSize(new Dimension(150, 30));

		lblSoDienThoai = new JLabel("Số điện thoại:");
		txtSoDienThoai = new JTextField(20);
		txtSoDienThoai.setPreferredSize(new Dimension(150, 30));

		Insets labelInsets = new Insets(0, 60, 0, 10);
//        lblIdLoaiSanPham.setBorder(new EmptyBorder(labelInsets));
//        lblTenLoaiSanPham.setBorder(new EmptyBorder(labelInsets));
//        lblDiaChi.setBorder(new );
		Dimension labelSize = new Dimension(200, 30);
		lblIdLoaiSanPham.setPreferredSize(labelSize);
		lblDiaChi.setPreferredSize(labelSize);
		lblSoDienThoai.setPreferredSize(labelSize);
		lblTenLoaiSanPham.setPreferredSize(labelSize);
//		lblTenLoaiSanPham.setPreferredSize(labelSize);

		// Sử dụng FlowLayout cho từng dòng để giữ các thành phần gần nhau
		JPanel idPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		idPanel.add(lblIdLoaiSanPham);
		idPanel.add(txtIdLoaiSanPham);

		JPanel tenPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		tenPanel.add(lblTenLoaiSanPham);
		tenPanel.add(txtTenLoaiSanPham);

		JPanel diaChiPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		diaChiPanel.add(lblDiaChi);
		diaChiPanel.add(txtDiaChi);

		JPanel soDienThoaiPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		soDienThoaiPanel.add(lblSoDienThoai);
		soDienThoaiPanel.add(txtSoDienThoai);

		pnLoaiThongTin.add(idPanel);
		pnLoaiThongTin.add(tenPanel);
		pnLoaiThongTin.add(diaChiPanel);
		pnLoaiThongTin.add(soDienThoaiPanel);

		pnChucNang = new JPanel(new FlowLayout(5));
		btnThem = new JButton("Thêm");
		btnCapNhat = new JButton("Cập nhật");
		btnLamMoi = new JButton("Làm mới");
		btnXoa = new JButton("Xóa");
		ImageIcon iconThem = new ImageIcon(getClass().getResource("/icons/add.png"));
		ImageIcon iconCapNhat = new ImageIcon(getClass().getResource("/icons/capnhat.png"));
		ImageIcon iconLamMoi = new ImageIcon(getClass().getResource("/icons/lammoi.png"));
		ImageIcon iconXoa = new ImageIcon(getClass().getResource("/icons/xoa.png"));
		btnCapNhat.setIcon(iconCapNhat);
	    btnThem.setIcon(iconThem);
	    btnLamMoi.setIcon(iconLamMoi);
	    btnXoa.setIcon(iconXoa);
//		btnThem.setBackground(new Color(208, 225, 253));
//		btnThem.setForeground(new Color(26, 102, 227));

//		btnCapNhat.setBackground(new Color(208, 225, 253));
//		btnCapNhat.setForeground(new Color(26, 102, 227));

//		btnLamMoi.setBackground(new Color(208, 225, 253));
//		btnLamMoi.setForeground(new Color(26, 102, 227));

//		btnXoa.setBackground(new Color(208, 225, 253));
//		btnXoa.setForeground(new Color(26, 102, 227));
		
		
		
		pnChucNang.add(btnThem);
		pnChucNang.add(btnCapNhat);
//		pnChucNang.add(btnXoa);
		pnChucNang.add(btnLamMoi);

		Insets btnInsert = new Insets(50, 110, 0, 0);
		pnChucNang.setBorder(new EmptyBorder(btnInsert));

		// phan top
		pnSouth = new JPanel(new BorderLayout());
		pnSouthNorth = new JPanel(new FlowLayout(5));
		lblTuKhoa = new JLabel("Từ khóa:");
		txtTimKiem = new JTextField(20);
		btnXemTatCa = new JButton("Xem tất cả");
		pnSouthNorth.add(lblTuKhoa);
		pnSouthNorth.add(txtTimKiem);
		pnSouthNorth.add(btnXemTatCa);

		// phan bottom
		pnSouthBottom = new JPanel(new BorderLayout());
		pnSouthBottom = new JPanel(new BorderLayout());
		pnSouthBottom.setBorder(BorderFactory.createTitledBorder("Danh mục"));
		modelSP = new DefaultTableModel();
		tableSP = new JTable();
		modelSP.addColumn("Mã Nhà Cung Cấp");
		modelSP.addColumn("Tên Nhà Cung Cấp");
		modelSP.addColumn("Địa Chỉ");
		modelSP.addColumn("Số Điện Thoại");
		tableSP.setModel(modelSP);
		JScrollPane scrollPane = new JScrollPane(tableSP);
		pnSouthBottom.add(scrollPane);

		pnSouth.add(pnSouthNorth, BorderLayout.NORTH);
		pnSouth.add(pnSouthBottom, BorderLayout.CENTER);

		pnMainThongTin.add(pnLoaiThongTin, BorderLayout.NORTH);
		pnMainThongTin.add(pnChucNang, BorderLayout.CENTER);

		pnMain.add(pnHeader, BorderLayout.NORTH);
		pnMain.add(pnMainThongTin, BorderLayout.CENTER);
		pnMain.add(pnSouth, BorderLayout.SOUTH);
		this.add(pnMain);

		btnThem.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnCapNhat.addActionListener(this);
//		btnXoa.addActionListener(this);
		tableSP.addMouseListener(this);
		txtTimKiem.addKeyListener(this);
		btnXemTatCa.addActionListener(this);
		loadData();
		this.setFocusable(true);
		this.requestFocusInWindow();
		tableSP.requestFocus();
	}

	private void loadData() {
		modelSP.setRowCount(0);
		for (NhaCungCap ncc : daoNhaCungCap.getAllNhaCungCap()) {
			String[] row = { ncc.getIdNhaCungCap(), ncc.getTenNhaCungCap(), ncc.getDiaChi(), ncc.getSoDienThoai() };
			modelSP.addRow(row);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			themNhaCungCap();
		} else if (o.equals(btnLamMoi)) {
			lamMoi();
		} else if (o.equals(btnCapNhat)) {
			capNhatNhaCungCap();
		} else if (o.equals(btnXoa)) {
			xoaNhaCungCap();
		} else if (o.equals(btnXemTatCa)) {
			lamMoi();
			loadData();
		}

	}

	private void xoaNhaCungCap() {
		int row = tableSP.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(this, "Bạn cần phải chọn dòng xóa");
		} else {
			try {
				int hoiNhac = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn xóa không", "Cảnh báo",
						JOptionPane.YES_NO_OPTION);
				if (hoiNhac == JOptionPane.YES_OPTION) {
					String idNhaCungCap = txtIdLoaiSanPham.getText();
					daoNhaCungCap.xoaNhaCungCap(idNhaCungCap);
					JOptionPane.showMessageDialog(this, "Xóa thông tin thành công");
					loadData();
					lamMoi();
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Xóa thông tin không thành công");
			}
		}
	}

	private void capNhatNhaCungCap() {
		int row = tableSP.getSelectedRow();
		if (row > 0) {
			int update = JOptionPane.showConfirmDialog(this, "Bạn có chắc sửa thông tin nhà cung cấp", "Cảnh báo",
					JOptionPane.YES_NO_OPTION);
			if (update == JOptionPane.YES_OPTION) {
				if(validataFields()) {
					String idNhaCungCap = txtIdLoaiSanPham.getText().trim();
					String tenNhaCungCap = txtTenLoaiSanPham.getText().trim();
					String diaChi = txtDiaChi.getText().trim();
					String soDienThoai = txtSoDienThoai.getText().trim();
					NhaCungCap ncc = new NhaCungCap();
					ncc.setIdNhaCungCap(idNhaCungCap);
					ncc.setTenNhaCungCap(tenNhaCungCap);
					ncc.setDiaChi(diaChi);
					ncc.setSoDienThoai(soDienThoai);
					daoNhaCungCap.capNhatNhaCungCap(ncc);
					JOptionPane.showMessageDialog(this, "Cập nhật thông tin nhà cung cấp thành công");
					loadData();					
				}
			} else {
				JOptionPane.showMessageDialog(this, "Cập nhật thất bại");
			}
		}else {
			showErrorDialog("Bạn chưa chọn dòng xóa!!");
		}
	}

	private void lamMoi() {
		txtIdLoaiSanPham.setText(generateNewNhaCungCapID());
		txtTenLoaiSanPham.setText("");
		txtSoDienThoai.setText("");
		txtTimKiem.setText("");
		txtDiaChi.setText("");
		txtTenLoaiSanPham.selectAll();
		txtTenLoaiSanPham.requestFocus();

	}

	private void themNhaCungCap() {
		String idNhaCungCap = txtIdLoaiSanPham.getText().trim();
		String tenNhaCungCap = txtTenLoaiSanPham.getText().trim();
		String diaChi = txtDiaChi.getText().trim();
		String soDienThoai = txtSoDienThoai.getText().trim();

		if (daoNhaCungCap.checkIdNhaCungCap(idNhaCungCap)) {
			showErrorDialog("Trùng ID nhà cung cấp. Vui lòng chọn ID khác.");
			return;
		}
		if (validataFields()) {
			try {
				NhaCungCap ncc = new NhaCungCap();
				ncc.setIdNhaCungCap(idNhaCungCap);
				ncc.setTenNhaCungCap(tenNhaCungCap);
				ncc.setDiaChi(diaChi);
				ncc.setSoDienThoai(soDienThoai);

				boolean kiemtra = daoNhaCungCap.themNhaCungCap(ncc);
				if (kiemtra) {
					modelSP.addRow(new Object[] { idNhaCungCap, tenNhaCungCap, diaChi, soDienThoai });
					loadData();
					lamMoi();
					showSuccessMessage("Thêm nhà cung cấp thành công");
				} else {
					showErrorMessage("Lỗi khi thêm nhà cung cấp");
				}
			} catch (SQLException e) {
				showErrorMessage("Lỗi khi thêm nhà cung cấp");
				e.printStackTrace();
			}
		}
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
	
	

	private boolean validataFields() {
		return validataField(txtTenLoaiSanPham, "^[\\p{L}\\s]+$",
				"Tên nhà cung cấp không hợp lệ. Phải bắt đầu bằng chữ cái, không chấp nhận ký tự đặc biệt.")
				&& validataField(txtSoDienThoai, "^(03|04|05|06|07|08|09)\\d{8}$",
						"Số điện thoại không hợp lệ. Phải bắt đầu bằng 03, 04, 05, 06 , 07, 08 hoặc 09 và có 10 chữ số.")
				&& validataField(txtDiaChi, "^[^,\\p{P} ]+[\\p{L}\\p{M}0-9,]*(\\s[^,\\p{P} ]+[\\p{L}\\p{M}0-9,]*)*[^,\\p{P} ]$",
					    "Địa chỉ không được chứa kí tự đặc biệt trừ dấu phẩy và không bắt đầu hoặc kết thúc bằng dấu phẩy, kí tự đặc biệt.");
	}

	private String generateNewNhaCungCapID() {
		String idPrefix = "NCC";
		int newProductIDNumber = 1;
		String currentDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
		try {
			String previousProductID = daoNhaCungCap.getLatestNhaCungCapID();
			if (previousProductID != null && !previousProductID.isEmpty()) {
				int oldProductIDNumber = Integer.parseInt(previousProductID.substring(11));
				newProductIDNumber = oldProductIDNumber + 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		String newProductID = idPrefix + currentDate + String.format("%04d", newProductIDNumber);
		return newProductID;
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

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = tableSP.getSelectedRow();
		if (row >= 0) {
			txtIdLoaiSanPham.setText(modelSP.getValueAt(row, 0).toString());
			txtTenLoaiSanPham.setText(modelSP.getValueAt(row, 1).toString());
			txtDiaChi.setText(modelSP.getValueAt(row, 2).toString());
			txtSoDienThoai.setText(modelSP.getValueAt(row, 3).toString());
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
			if (o.equals(txtTimKiem)) {
				DefaultTableModel model = (DefaultTableModel) tableSP.getModel();
				TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(model);
				tableSP.setRowSorter(tr);
				tr.setRowFilter(RowFilter.regexFilter("(?i)" + txtTimKiem.getText().trim(), 1, 3));
			}
		});
	}
}
