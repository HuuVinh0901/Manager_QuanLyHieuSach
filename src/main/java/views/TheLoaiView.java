package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import dao.DAOTheLoai;
import models.TheLoai;

public class TheLoaiView extends JPanel implements ActionListener, KeyListener, MouseListener {

	private JPanel pnMain;
	private JPanel pnHeading;
	private JPanel pnContainer;
	private JPanel pnThongTinCha;
	private JPanel pnThongTin;
	private JPanel pnThongTinCT1;
	private JPanel pnThongTinCT2;
	private JPanel pnThongTinCT3;
	private JPanel pnThongTinCT4;
	private JPanel pnThongTinChucNang;
	private JPanel pnSounth;
	private JPanel pnSounthNorth;
	private JPanel pnSounthSouth;

	private JLabel lblTitle;
	private JLabel lblIdTheLoai;
	private JLabel lblTenTheLoai;
	private JLabel lblSoLuongSach;
	private JLabel lblMoTa;
	private JLabel lblTuKhoa;

	private JTextField txtIdtheLoai;
	private JTextField txtTentheLoai;
	private JTextField txtSoLuongSach;
	private JTextField txtMoTa;
	private JTextField txtTuKhoa;

	private JButton btnThem;
	private JButton btnCapNhat;
	private JButton btnXoa;
	private JButton btnLamMoi;
	private JButton btnXemTatCa;

	private JTable table;
	private DefaultTableModel model;

	// boc cac text

	private DAOTheLoai daotheLoai;

	public TheLoaiView() {
		setLayout(new BorderLayout(8, 6));
		daotheLoai = new DAOTheLoai();
		init();
	}

	private void init() {
		pnContainer = new JPanel(new BorderLayout());

		pnMain = new JPanel(new BorderLayout());
		pnHeading = new JPanel();
		lblTitle = new JLabel("Quản Lý Thể Loại");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTitle.setForeground(new Color(26, 102, 227));
		pnHeading.add(lblTitle);

		lblIdTheLoai = new JLabel("Mã thể loại:");
		txtIdtheLoai = new JTextField(20);
		txtIdtheLoai.setEditable(false);
		txtIdtheLoai.setText(generateNewTheLoaiID());
		lblTenTheLoai = new JLabel("Tên thể loại:");
		txtTentheLoai = new JTextField(20);
		lblSoLuongSach = new JLabel("Số lượng sách:");
		txtSoLuongSach = new JTextField(20);
		txtSoLuongSach.setEditable(false);
		lblMoTa = new JLabel("Mô tả:");
		txtMoTa = new JTextField(20);

		lblTuKhoa = new JLabel("Từ khóa:");
		txtTuKhoa = new JTextField(20);

		btnCapNhat = new JButton("Cập nhật");
		btnLamMoi = new JButton("Làm mới");
		btnThem = new JButton("Thêm");
		btnXoa = new JButton("Xóa");
		btnXemTatCa = new JButton("Xem tất cả");

		pnThongTinCha = new JPanel(new BorderLayout());
		pnThongTinCha.setBorder(BorderFactory.createTitledBorder("Thông tin chi tiết"));
		pnThongTin = new JPanel(new GridLayout(2, 8, 20, 10));
		pnThongTinCT1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 5));
		pnThongTinCT2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 5));
		Insets btnInsert = new Insets(0, 70, 0, 0);
		pnThongTin.setBorder(new EmptyBorder(btnInsert));

		Dimension labelSize = new Dimension(87, 30);
		lblIdTheLoai.setPreferredSize(labelSize);
		lblTenTheLoai.setPreferredSize(labelSize);
		lblMoTa.setPreferredSize(labelSize);
		pnThongTinCT1.add(lblIdTheLoai);
		pnThongTinCT1.add(txtIdtheLoai);
		pnThongTinCT1.add(lblTenTheLoai);
		pnThongTinCT1.add(txtTentheLoai);
		pnThongTinCT2.add(lblSoLuongSach);
		pnThongTinCT2.add(txtSoLuongSach);
		pnThongTinCT2.add(lblMoTa);
		pnThongTinCT2.add(txtMoTa);
		pnThongTin.add(pnThongTinCT1);
		pnThongTin.add(pnThongTinCT2);
		pnThongTinChucNang = new JPanel(new FlowLayout(5));
		pnThongTinChucNang.add(btnThem);
		pnThongTinChucNang.add(btnCapNhat);
		pnThongTinChucNang.add(btnXoa);
		pnThongTinChucNang.add(btnLamMoi);
		pnThongTinChucNang.setBorder(new EmptyBorder(btnInsert));

		pnSounth = new JPanel(new BorderLayout());
		pnSounthNorth = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
		pnSounthSouth = new JPanel(new BorderLayout());

		pnSounthNorth.add(lblTuKhoa);
		pnSounthNorth.add(txtTuKhoa);
		pnSounthNorth.add(btnXemTatCa);

		pnSounthSouth.setBorder(BorderFactory.createTitledBorder("Danh mục"));
		model = new DefaultTableModel();
		table = new JTable();
		model.addColumn("Mã thể loại");
		model.addColumn("Tên thể loại");
		model.addColumn("Số lượng tác phẩm");
		model.addColumn("Mô tả");
		table.setModel(model);
		JScrollPane scrollPane = new JScrollPane(table);
		pnSounthSouth.add(scrollPane);

		pnSounth.add(pnSounthNorth, BorderLayout.NORTH);
		pnSounth.add(pnSounthSouth, BorderLayout.CENTER);

		pnThongTinCha.add(pnThongTin, BorderLayout.NORTH);
		pnThongTinCha.add(pnThongTinChucNang, BorderLayout.CENTER);

		pnContainer.add(pnHeading, BorderLayout.NORTH);
		pnMain.add(pnThongTinCha, BorderLayout.NORTH);
		pnMain.add(pnSounth, BorderLayout.CENTER);

		pnContainer.add(pnMain, BorderLayout.CENTER);
		add(pnContainer);
		loadData();

		btnThem.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnXoa.addActionListener(this);
		btnCapNhat.addActionListener(this);
		table.addMouseListener(this);

	}

	private void loadData() {
		model.setRowCount(0);
		try {
			for (TheLoai tl : daotheLoai.getAllTheLoai()) {
				String[] row = { tl.getIdTheLoai(), tl.getTenTheLoai(), tl.getSoLuongSach() + "", tl.getMoTa() };
				model.addRow(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private String generateNewTheLoaiID() {
		String idPrefix = "TL";
		int newProductIDNumber = 1;
		String currentDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
		try {
			String previousProductID = daotheLoai.getLatestTheLoaiID();
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
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			themTheLoai();
		} else if (o.equals(btnLamMoi)) {
			lamMoi();
		} else if (o.equals(btnXoa)) {
			xoaTheLoai();
		} else if (o.equals(btnCapNhat)) {
			capNhatTheLoai();
		}

	}

	private void capNhatTheLoai() {
		int row = table.getSelectedRow();
		if (row < 0) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng để cập nhật", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
			return ;
		}else {
			try {
				TheLoai tl = getTheLoai();
				daotheLoai.capNhatTheLoai(tl);
				JOptionPane.showMessageDialog(this,"Cập nhật thành công");
				loadData();
				lamMoi();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	private void xoaTheLoai() {
		int row = table.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(this, "Bạn cần chọn dòng xóa");
		} else {
			int hoiNhac = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn xóa dòng này không!", "Cảnh báo",
					JOptionPane.YES_NO_OPTION);
			if (hoiNhac == JOptionPane.YES_OPTION) {
				try {
					String idTheLoai = txtIdtheLoai.getText();
					daotheLoai.xoaTheLoai(idTheLoai);
					loadData();
					lamMoi();
					JOptionPane.showMessageDialog(this, "Xóa thành công");
				} catch (SQLException e) {
					System.out.println("SQL Exception catched, SQL State : " + e.getSQLState());
					System.out.println("Error Code                       : " + e.getErrorCode());
					System.out.println("Error Message                    : " + e.getMessage());
				}
			} else {
				JOptionPane.showMessageDialog(this, "Xóa thất bại");
			}
		}

	}

	private void lamMoi() {
		txtIdtheLoai.setText(generateNewTheLoaiID());
		txtTentheLoai.setText("");
		txtSoLuongSach.setText("");
		txtMoTa.setText("");
		txtTentheLoai.requestFocus();
	}

	private void themTheLoai() {
		TheLoai tl = getTheLoai();

		try {
			if (daotheLoai.checkIdTheLoai(getTheLoai().getIdTheLoai())) {
				JOptionPane.showMessageDialog(this, "Trùng ID nhà cung cấp. Vui lòng chọn ID khác.");
				return;
			} else {
				try {
					daotheLoai.themTheLoai(tl);
					model.addRow(
							new Object[] { tl.getIdTheLoai(), tl.getTenTheLoai(), tl.getSoLuongSach(), tl.getMoTa() });
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private TheLoai getTheLoai() {
		String idTheLoai = txtIdtheLoai.getText();
		String tenTheLoai = txtTentheLoai.getText();
		int soLuongSach = 0;

		String moTa = txtMoTa.getText();
		TheLoai tl = new TheLoai(idTheLoai, tenTheLoai, soLuongSach, moTa);
		return tl;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		if (row >= 0) {
			txtIdtheLoai.setText(model.getValueAt(row, 0).toString());
			txtTentheLoai.setText(model.getValueAt(row, 1).toString());
			txtSoLuongSach.setText(model.getValueAt(row, 2).toString());
			txtMoTa.setText(model.getValueAt(row, 3).toString());
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
		// TODO Auto-generated method stub

	}

}
