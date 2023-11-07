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
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

public class TacGiaView extends JPanel implements ActionListener {
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

	public TacGiaView() {
		setLayout(new BorderLayout(8, 6));
		init();

	}

	private void init() {
		pnMain = new JPanel(new BorderLayout());

		pnHeading = new JPanel();
		lblTitle = new JLabel("Quản Lý Tác Giả");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTitle.setForeground(new Color(26, 102, 227));
		pnHeading.setBackground(Color.cyan); // test layout
		pnHeading.add(lblTitle);

		pnThongTinMain = new JPanel(new BorderLayout());
		pnThongTinMain.setBorder(BorderFactory.createTitledBorder("Thông tin tác giả"));
		pnThongTin = new JPanel(new GridLayout(2, 4, 20, 20));

		lblIdTacGia = new JLabel("Mã tác giả:");
		lblTenTacGia = new JLabel("Tên tác giả:");
		lblSoLuongTacPham = new JLabel("Số lượng tác phẩm:");
		lblNgaySinh = new JLabel("Ngày sinh:");

		txtIdTacGia = new JTextField();
		txtTenTacGia = new JTextField();
		txtSoLuongTacPham = new JTextField();
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

		pnChucNang.add(btnThem);
		pnChucNang.add(btnCapNhat);
		pnChucNang.add(btnXoa);
		pnChucNang.add(btnLamMoi);
		Insets btnInsert = new Insets(20, 70, 0, 0);
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
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
