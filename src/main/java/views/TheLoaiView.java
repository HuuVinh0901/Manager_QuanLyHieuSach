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
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import dao.DAOTheLoai;

public class TheLoaiView extends JPanel implements ActionListener, KeyListener, MouseListener {

	private JPanel pnMain;
	private JPanel pnHeading;
	private JPanel pnContainer;
	private JPanel pnThongTinCha;
	private JPanel pnThongTin;
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
		txtIdtheLoai = new JTextField();
		lblTenTheLoai = new JLabel("Tên thể loại:");
		txtTentheLoai = new JTextField();
		lblSoLuongSach = new JLabel("Số lượng sách:");
		txtSoLuongSach = new JTextField();
		lblMoTa = new JLabel("Mô tả:");
		txtMoTa = new JTextField();
		
		lblTuKhoa = new JLabel("Từ khóa:");
		txtTuKhoa = new JTextField(20);
		
		btnCapNhat = new JButton("Cập nhật");
		btnLamMoi = new JButton("Làm mới");
		btnThem = new JButton("Thêm");
		btnXoa = new JButton("Xóa");
		btnXemTatCa = new JButton("Xem tất cả");
		
		
		pnThongTinCha = new JPanel(new BorderLayout());
		pnThongTinCha.setBorder(BorderFactory.createTitledBorder("Thông tin chi tiết"));
		pnThongTin = new JPanel(new GridLayout(2,4,20,10));
		pnThongTin.add(lblIdTheLoai);
		pnThongTin.add(txtIdtheLoai);
		pnThongTin.add(lblTenTheLoai);
		pnThongTin.add(txtTentheLoai);
		pnThongTin.add(lblSoLuongSach);
		pnThongTin.add(txtSoLuongSach);
		pnThongTin.add(lblMoTa);
		pnThongTin.add(txtMoTa);
		
		pnThongTinChucNang = new JPanel(new FlowLayout(5));
		pnThongTinChucNang.add(btnThem);
		pnThongTinChucNang.add(btnCapNhat);
		pnThongTinChucNang.add(btnXoa);
		pnThongTinChucNang.add(btnLamMoi);
		
		
		pnSounth = new JPanel(new BorderLayout());
		pnSounthNorth = new JPanel(new FlowLayout(FlowLayout.LEFT,5,5));
		pnSounthSouth = new JPanel(new BorderLayout());
		
		pnSounthNorth.add(lblTuKhoa);
		pnSounthNorth.add(txtTuKhoa);
		pnSounthNorth.add(btnXemTatCa);
		
		pnSounthSouth.setBorder(BorderFactory.createTitledBorder("Danh mục"));
		model = new DefaultTableModel();
		table = new JTable();
		model.addColumn("Mã thể loại");
		model.addColumn("Tên thể loại");
		model.addColumn("Mô tả");
		model.addColumn("Số lượng tác phẩm");
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
		// TODO Auto-generated method stub

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
