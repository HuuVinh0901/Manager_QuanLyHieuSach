package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.security.Key;
import java.sql.SQLException;
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
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import connection.ConnectDB;
import dao.DAOLoaiSanPham;
import models.LoaiSanPham;

public class LoaiSanPhamView extends JPanel implements ActionListener, KeyListener, MouseListener {
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
	private JButton btnXemTatCa;
	private DAOLoaiSanPham daoLoaiSanPham;

	public LoaiSanPhamView() {

		LoaiSanPham sp = new LoaiSanPham();
		daoLoaiSanPham = new DAOLoaiSanPham();

		setLayout(new BorderLayout());
		pnMain = new JPanel(new BorderLayout(8, 6));

		pnHeader = new JPanel();
		pnHeader.add(lblTitle = new JLabel("Quản Lý Loại Sản Phẩm"));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTitle.setForeground(new Color(26, 102, 227));
//		pnHeader.setBackground(new Color(208, 225, 253));

		// layout thong tin
		pnMainThongTin = new JPanel(new BorderLayout());
		pnMainThongTin.setBorder(BorderFactory.createTitledBorder("Thông tin sản phẩm"));

		pnLoaiThongTin = new JPanel(new GridLayout(2, 2, 10, 10));

		lblIdLoaiSanPham = new JLabel("Mã loại sản phẩm:");
		txtIdLoaiSanPham = new JTextField(20);
		txtIdLoaiSanPham.setEditable(false);
		txtIdLoaiSanPham.setText(generateNewLoaiSanPhamID());
		txtIdLoaiSanPham.setPreferredSize(new Dimension(150, 30));

		lblTenLoaiSanPham = new JLabel("Tên loại sản phẩm:");
		txtTenLoaiSanPham = new JTextField(20);
		txtTenLoaiSanPham.setPreferredSize(new Dimension(150, 30));

		Insets labelInsets = new Insets(0, 60, 0, 10);
		lblIdLoaiSanPham.setBorder(new EmptyBorder(labelInsets));
		lblTenLoaiSanPham.setBorder(new EmptyBorder(labelInsets));
		Dimension labelSize = new Dimension(200, 30);
		lblIdLoaiSanPham.setPreferredSize(labelSize);
		lblTenLoaiSanPham.setPreferredSize(labelSize);

		JPanel idPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		idPanel.add(lblIdLoaiSanPham);
		idPanel.add(txtIdLoaiSanPham);

		JPanel tenPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		tenPanel.add(lblTenLoaiSanPham);
		tenPanel.add(txtTenLoaiSanPham);

		pnLoaiThongTin.add(idPanel);
		pnLoaiThongTin.add(tenPanel);

		pnChucNang = new JPanel(new FlowLayout(5));
		btnThem = new JButton("Thêm");
		btnCapNhat = new JButton("Sửa");
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

		Insets btnInsert = new Insets(50, 60, 0, 0);
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
		modelSP.addColumn("Mã Loại Sản Phẩm");
		modelSP.addColumn("Tên Loại Sản Phẩm");
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
		btnXoa.addActionListener(this);
		btnXemTatCa.addActionListener(this);
		tableSP.addMouseListener(this);
		txtTimKiem.addKeyListener(this);
		loadData();

	}

	private void loadData() {
		modelSP.setRowCount(0);
		for (LoaiSanPham sp : daoLoaiSanPham.getAllLoaiSanPham()) {
			String[] row = { sp.getIdLoaiSanPham(), sp.getTenLoaiSanPham() };
			modelSP.addRow(row);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			themLoaiSanPham();
		} else if (o.equals(btnLamMoi)) {
			lamMoi();
		} else if (o.equals(btnCapNhat)) {
			capNhapLoaSanPham();
		} else if (o.equals(btnXoa)) {
			xoaLoaiSanPham();
		}else if(o.equals(btnXemTatCa)) {
			lamMoi();
			loadData();
		}
	}

	private void xoaLoaiSanPham() {
		int row = tableSP.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(this, "Bạn cần phải chọn dòng xóa");
		} else {
			try {
				int hoiNhac = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn xóa không", "Cảnh báo",
						JOptionPane.YES_NO_OPTION);
				if (hoiNhac == JOptionPane.YES_OPTION) {
					String idLoaiSanPham = txtIdLoaiSanPham.getText();
					daoLoaiSanPham.xoaLoaiSanPham(idLoaiSanPham);
					JOptionPane.showMessageDialog(this, "Xóa thông tin thành công");
					loadData();
					lamMoi();
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Xóa thông tin không thành công");
			}
		}

	}

	private void capNhapLoaSanPham() {
		int row = tableSP.getSelectedRow();
		if (row >= 0) {
			int update = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn sửa thông tin này", "Cảnh báo",
					JOptionPane.YES_NO_OPTION);
			if (update == JOptionPane.YES_OPTION) {
				if(validataFields()) {
					String idLoaiSanPham = txtIdLoaiSanPham.getText().trim();
					String tenLoaiSanPham = txtTenLoaiSanPham.getText().trim();
					LoaiSanPham lsp = new LoaiSanPham();
					lsp.setIdLoaiSanPham(idLoaiSanPham);
					lsp.setTenLoaiSanPham(tenLoaiSanPham);
					daoLoaiSanPham.capNhatLoaiSanPham(lsp);
					JOptionPane.showMessageDialog(this, "Cập nhật thành công");
					lamMoi();
					loadData();					
				}
			} else {
				JOptionPane.showMessageDialog(this, "Cập nhật thất bại");
			}
		}else {
			showErrorDialog("Bạn chưa chọn dòng xóa");
			
		}

	}

	private void lamMoi() {
		txtIdLoaiSanPham.setText(generateNewLoaiSanPhamID());
		txtTenLoaiSanPham.setText("");
		txtTimKiem.setText("");
		txtTenLoaiSanPham.selectAll();
		txtTenLoaiSanPham.requestFocus();
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
	private boolean validataFields() {
		return validataField(txtTenLoaiSanPham, "^[a-zA-Z][a-zA-Z\\s]*[a-zA-Z]$",
				"Tên nhà cung cấp không hợp lệ. Phải bắt đầu bằng chữ cái, không chấp nhận ký tự đặc biệt.");
	}
	
	private void themLoaiSanPham() {
		String idLoaiSanPham = txtIdLoaiSanPham.getText();
		String tenLoaiSanPham = txtTenLoaiSanPham.getText();
		LoaiSanPham lsp = new LoaiSanPham(idLoaiSanPham, tenLoaiSanPham);

		if(validataFields()) {
			if (daoLoaiSanPham.checkIdLoaiSanPham(idLoaiSanPham)) {
				JOptionPane.showMessageDialog(this, "Trùng ID loại sản phẩm. Vui lòng chọn ID khác.");
				return;
			} else {
				try {
					boolean kiemtra = daoLoaiSanPham.themLoaiSanPham(lsp);
					if (kiemtra) {
						modelSP.addRow(new Object[] { idLoaiSanPham, tenLoaiSanPham });
						JOptionPane.showMessageDialog(this, "Thêm nhà cung cấp thành công");
						loadData();
						lamMoi();
					} else {
						JOptionPane.showMessageDialog(this, "Lỗi khi thêm nhà cung cấp");
					}
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(this, "Lỗi khi thêm nhà cung cấp");
					e.printStackTrace();
				}
			}
			
		}
	}

	private String generateNewLoaiSanPhamID() {
		String idPrefix = "LSP";
		int newProductIDNumber = 1;
		String currentDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
		try {
			String previousProductID = daoLoaiSanPham.getLatestLoaiSanPhamID();
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
			if(o.equals(txtTimKiem)) {
				DefaultTableModel model = (DefaultTableModel) tableSP.getModel();
				TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(model);
				tableSP.setRowSorter(tr);
				tr.setRowFilter(RowFilter.regexFilter("(?i)"+txtTimKiem.getText().trim(),1));
			}
		});

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = tableSP.getSelectedRow();
		if (row >= 0) {
			txtIdLoaiSanPham.setText(modelSP.getValueAt(row, 0).toString());
			txtTenLoaiSanPham.setText(modelSP.getValueAt(row, 1).toString());
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

}
