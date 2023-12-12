package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import connection.ConnectDB;
import dao.DAOKhachHang;
import dao.DAOQuanLySanPham;
import dao.DAOSach;
import dao.DAO_QuanLyBanHang;
import models.ChiTietHoaDon;
import models.ChiTietHoaDonCho;
import models.HoaDon;
import models.HoaDonCho;
import models.KhachHang;
import models.NhanLuc;
import models.NhanVien;
import models.SachCon;
import models.SanPhamCha;
import models.SanPhamCon;


public class QuanLyBanHangView extends JPanel implements ActionListener, MouseListener{
	private JPanel pnLeft;
	private JPanel pnRight;
	private JTextField txtMaSP;
	private JTextField txtMaSP2;
	private JTextField txtTimKiemSP;
	private JTextField txtMaHD;
	private JTextField txtTenNV;
	private JTextField txtTenKH;
	private JTextField txtTenKH2;
	private JTextField txtMaKH;
	private JTextField txtMaKH2;
	private JTextField txtTimKiemKH;
	private JTextField txtTongTienGioHang;
	private JTextField txtTienKhachDua;
	private JTextField txtTienTraKhach;
	private JTextField txtNgayLap;
	private JTextField txtThue;
	private JTextField txtCapNhatSoLuong;
	private JTextField txtTongTienHoaDon;
	private JTable tblSanPham;
	private JTable tblGioHang;
	private JTable tblkhachHang;
	private JTable tblHangCho;
	private JButton btnThemGioHang;
	private JButton btnThemGioHang2;
	private JButton btnXoaGioHang;
	private JButton btnLamMoiGioHang;
	private JButton btnLuuHoaDon;
	private JButton btnXoaHangCho;
	private JButton btnChonThanhToan;
	private JButton btnLamMoiHangCho;
	private JButton btnThanhToan;
	private JButton btnLamMoiDonHang;
	private JButton btnChonKH;
	private JButton btnTimKiemSanPham;
	private JButton btnXemTatCaSanPham;
	private JButton btnXemTatCaKhachHang;
	private JButton btnDongSanPham;
	private JButton btnDongKhachHang;
	private JButton btnTimKhachHang;
	private JButton btnChonMaSanPham;
	private JButton btnCapNhatSoLuong;
	private JButton btnXacNhanCapNhat;
	private JButton btnTaoMaHoaDon;
	private DefaultTableModel modelSP;
	private DefaultTableModel modelKH;
	private DefaultTableModel modelGioHang;
	private DefaultTableModel modelHangCho;
	private DAOKhachHang daoKhachHang;
	private DAOQuanLySanPham daoQLSP;
	private DAOSach daoSach;
	private DAO_QuanLyBanHang daoQLBH;
	private JSpinner spinnerSoLuong;
	private JSpinner spinnerSoLuong2;
	private JDialog dialogSanPham;
	private JDialog dialogKhachHang;
	private JDialog dialogSoLuong;
	private JComboBox<String> cbLocSanPham;
	private Date ngayNhap;
	private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
    private JWindow windowMaSP;
    private JTable tblMaSp;
    private DefaultTableModel modelMaSP;
    private JFrame frameThongBao;
    private NhanLuc nvLogIn;

	
	public QuanLyBanHangView(NhanLuc nv) {
		nvLogIn = nv;
		// Xóa logo Eclipse ở phía trên hộp thoại
        UIManager.put("OptionPane.windowIcons", new ImageIcon[0]);
		currencyFormat.setCurrency(Currency.getInstance("VND"));
		setLayout(new GridBagLayout());
		pnLeft = new JPanel(new BorderLayout());
		pnRight = new JPanel(new BorderLayout());
		JLabel lblTitleLeft = new JLabel("CHI TIẾT HOÁ ĐƠN");
		JLabel lblTitleRight = new JLabel("HOÁ ĐƠN");
		JPanel pnTitleLeft = new JPanel();
		JPanel pnTitleRight = new JPanel();
		JPanel pnHeaderLeft = new JPanel(new BorderLayout());
		JPanel pnHeaderRight = new JPanel(new BorderLayout());
		JPanel pn1 = new JPanel();
		JPanel pn2 = new JPanel();
		JPanel pn3 = new JPanel();
		JPanel pn5 = new JPanel();
		JPanel pn6 = new JPanel();
		JPanel pn7 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel pnSanPham = new JPanel(new GridLayout(2, 2, 5, 5));
		JPanel pnHoaDon = new JPanel(new GridLayout(4, 2, 5, 5));
		JLabel lblMaSP = new JLabel("Mã sản phẩm");
		JLabel lblSoLuong = new JLabel("Số lượng");
		JLabel lblTimKiemSP = new JLabel("Tìm kiếm theo Tên / Mã / Loại");
		JLabel lblMaHD = new JLabel("Mã đơn hàng");
		JLabel lblTenNV = new JLabel("Tên nhân viên");
		JLabel lblTenKH = new JLabel("Tên khách hàng");
		JLabel lblMaKH = new JLabel("Mã khách hàng");
		JPanel pnGioHang = new JPanel(new BorderLayout());
		JPanel pnXoaLamMoi = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel pnChucNangHangCho = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel pnTblHangCho = new JPanel(new BorderLayout());
		JLabel lblNgayLap = new JLabel("Ngày lập");
		JLabel lblThue = new JLabel("Thuế VAT(10%)");
		JLabel lblTongTien = new JLabel("Tổng tiền giỏ hàng");
		JLabel lblTienKhachDua = new JLabel("Tiền khách đưa");
		JLabel lblTienTraKhach = new JLabel("Tiền trả lại khách");
		JLabel lblTongTienHoaDon = new JLabel("TỔNG TIỀN");
		JPanel pnTinhTien = new JPanel(new GridLayout(6, 2, 5, 5));
		JPanel pnFooterRight = new JPanel(new BorderLayout());
		JPanel pnLamMoiHD = new JPanel();
		JPanel pnLamThanhToan = new JPanel();
		JPanel pnTimKiemKH = new JPanel(new FlowLayout(FlowLayout.LEFT));
		daoKhachHang = new DAOKhachHang();
		daoQLSP = new DAOQuanLySanPham();
		daoSach = new DAOSach();
		daoQLBH = new DAO_QuanLyBanHang();
		txtMaSP = new JTextField(20);
		txtMaSP.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtMaSP2 = new JTextField(20);
		txtMaSP2.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtMaHD = new JTextField(20);
		txtMaHD.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtMaHD.setEditable(false);
		txtTenNV = new JTextField(20);
		txtTenNV.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtTenNV.setEditable(false);
		txtTenNV.setText(nvLogIn.getTen());
		txtTenKH = new JTextField(20);
		txtTenKH.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtTenKH.setEditable(false);
		txtTimKiemKH = new JTextField(20);
		txtTimKiemKH.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtTongTienGioHang = new JTextField(20);
		txtTongTienGioHang.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtTongTienGioHang.setEditable(false);
		txtTongTienGioHang.setText(currencyFormat.format(0.0));
		txtTongTienHoaDon = new JTextField(20);
		txtTongTienHoaDon.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtTongTienHoaDon.setEditable(false);
		txtTongTienHoaDon.setText(currencyFormat.format(0.0));
		txtTienKhachDua = new JTextField(20);
		txtTienKhachDua.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtTienTraKhach = new JTextField(20);
		txtTienTraKhach.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtTienTraKhach.setEditable(false);
		txtMaKH = new JTextField(20);
		txtMaKH.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtMaKH.setEditable(false);
		txtNgayLap = new JTextField(20);
		txtNgayLap.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtNgayLap.setEditable(false);
		txtThue = new JTextField(20);
		txtThue.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtThue.setEditable(false);
		txtThue.setText(currencyFormat.format(0.0));
		txtTienTraKhach.setText(currencyFormat.format(0.0));
		txtCapNhatSoLuong = new JTextField(10);
		txtCapNhatSoLuong.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtMaKH2 = new JTextField(20);
		txtMaKH2.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtMaKH2.setEditable(false);
		txtTenKH2 = new JTextField(20);
		txtTenKH2.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtTenKH2.setEditable(false);
		lblTitleLeft.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTitleLeft.setForeground(new Color(26, 102, 227));
		lblTitleRight.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTitleRight.setForeground(new Color(26, 102, 227));
		lblMaSP.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblSoLuong.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblTimKiemSP.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblMaKH.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblMaHD.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblTenNV.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblTenKH.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblNgayLap.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblTongTien.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblTienKhachDua.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblTienTraKhach.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblTongTienHoaDon.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTongTienHoaDon.setForeground(Color.red);
		lblThue.setFont(new Font("SansSerif", Font.BOLD, 14));
		spinnerSoLuong = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
		spinnerSoLuong.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnTimKiemSanPham = new JButton("Tìm kiếm sản phẩm");
		btnDongSanPham = new JButton("Đóng");
		btnDongKhachHang = new JButton("Đóng");
		btnXacNhanCapNhat = new JButton("Cập nhật");
		cbLocSanPham = new JComboBox<String>();
		cbLocSanPham.addItem("Sản Phẩm");
		cbLocSanPham.addItem("Sách");
		btnThemGioHang2 = new JButton("Thêm vào giỏ hàng");
		btnXemTatCaKhachHang = new JButton("Xem tất cả");
		btnChonKH = new JButton("Chọn khách hàng");
		btnXemTatCaSanPham = new JButton("Xem tất cả");
		tblkhachHang = new JTable();
		modelKH = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không cho phép chỉnh sửa các ô trong bảng
            }
        };
		tblSanPham = new JTable();
		modelSP = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        
        
        tblMaSp = new JTable();
		modelMaSP = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        modelMaSP.addColumn("Mã sản phẩm");
        tblMaSp.setModel(modelMaSP);
        JScrollPane scrollTblSP = new JScrollPane(tblMaSp);
        windowMaSP = new JWindow();
        windowMaSP.setLayout(new BorderLayout());
        btnChonMaSanPham = new JButton("Chọn");
        JPanel pnChonMaSanPham = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnChonMaSanPham.add(btnChonMaSanPham);
        windowMaSP.add(scrollTblSP, BorderLayout.CENTER);
        windowMaSP.add(pnChonMaSanPham, BorderLayout.SOUTH);
        
        
        
		ImageIcon iconThem = new ImageIcon(getClass().getResource("/icons/add.png"));
		ImageIcon iconLamMoi = new ImageIcon(getClass().getResource("/icons/lammoi.png"));
		ImageIcon iconXoa = new ImageIcon(getClass().getResource("/icons/xoa.png"));
		ImageIcon iconLuuVaIn = new ImageIcon(getClass().getResource("/icons/printing.png"));
		ImageIcon iconLuu = new ImageIcon(getClass().getResource("/icons/bookmark.png"));
		
		pnTitleLeft.add(lblTitleLeft);
		pnSanPham.add(lblMaSP);
		pnSanPham.add(txtMaSP);
		pnSanPham.add(lblSoLuong);
		pnSanPham.add(spinnerSoLuong);
		pn1.add(pnSanPham);

		btnThemGioHang = new JButton("Thêm vào giỏ hàng");
		pn5.add(btnThemGioHang);
		pn5.add(btnTimKiemSanPham);
		pn6.add(pn5);
		pn7.add(pn6);
		pnHeaderLeft.add(pnTitleLeft, BorderLayout.NORTH);
		pnHeaderLeft.add(pn1, BorderLayout.CENTER);
		pnHeaderLeft.add(pn7, BorderLayout.SOUTH);
		tblGioHang = new JTable();
		modelGioHang = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không cho phép chỉnh sửa các ô trong bảng
            }
        };
		modelGioHang.addColumn("Mã sản phẩm");
		modelGioHang.addColumn("Tên sản phẩm");
		modelGioHang.addColumn("Giá bán");
		modelGioHang.addColumn("Khuyến mãi");
		modelGioHang.addColumn("Giá Cuối");
		modelGioHang.addColumn("Số lượng");
		modelGioHang.addColumn("Thành tiền");
	
		tblGioHang.setModel(modelGioHang);
		JScrollPane scrollTblGH = new JScrollPane(tblGioHang);
		scrollTblGH.setBorder(BorderFactory.createTitledBorder("Giỏ hàng"));
		scrollTblGH.setPreferredSize(new Dimension(scrollTblGH.getPreferredSize().width, 170));
		btnXoaGioHang = new JButton("Xoá");
		
		btnLamMoiGioHang = new JButton("Làm mới giỏ hàng");
		btnLamMoiGioHang.setIcon(iconLamMoi);
		btnCapNhatSoLuong = new JButton("Cập nhật số lượng");
		pnXoaLamMoi.add(btnXoaGioHang);
		pnXoaLamMoi.add(btnCapNhatSoLuong);
		pnXoaLamMoi.add(btnLamMoiGioHang);
		pnGioHang.add(scrollTblGH, BorderLayout.CENTER);
		pnGioHang.add(pnXoaLamMoi, BorderLayout.SOUTH);
		pnLeft.add(pnHeaderLeft, BorderLayout.NORTH);
		pnLeft.add(pnGioHang, BorderLayout.CENTER);
		pnLeft.setBorder(BorderFactory.createTitledBorder(""));
		
		
		btnTimKhachHang = new JButton("Chọn khách hàng");
		pnTimKiemKH.add(btnTimKhachHang);
		pnTitleRight.add(lblTitleRight);
		pnHoaDon.add(lblMaHD);
		pnHoaDon.add(txtMaHD);
		pnHoaDon.add(lblTenNV);
		pnHoaDon.add(txtTenNV);
		pnHoaDon.add(lblTenKH);
		pnHoaDon.add(txtTenKH);
		pnHoaDon.add(lblMaKH);
		pnHoaDon.add(txtMaKH);
		pn2.add(pnHoaDon);
		pnHeaderRight.add(pnTitleRight, BorderLayout.NORTH);
		pnHeaderRight.add(pn2, BorderLayout.CENTER);
		pnHeaderRight.add(pnTimKiemKH, BorderLayout.SOUTH);
		
		tblHangCho = new JTable();
		modelHangCho = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        modelHangCho.addColumn("Mã hoá đơn");
        modelHangCho.addColumn("Tên khách hàng");
        modelHangCho.addColumn("Mã khách hàng");
        modelHangCho.addColumn("Số điện thoại");
        modelHangCho.addColumn("Ngày lập");
		tblHangCho.setModel(modelHangCho);
		JScrollPane scrollTblHangDoi = new JScrollPane(tblHangCho);
		scrollTblHangDoi.setBorder(BorderFactory.createTitledBorder("Đơn hàng chờ thanh toán"));
		
		btnLuuHoaDon = new JButton("Lưu");
		btnLuuHoaDon.setIcon(iconLuu);
		btnXoaHangCho = new JButton("Xoá");
		btnXoaHangCho.setIcon(iconXoa);
		btnChonThanhToan = new JButton("Chọn thanh toán");
		btnXoaGioHang.setIcon(iconXoa);
		btnLamMoiHangCho = new JButton("Làm mới hàng chờ");
		btnLamMoiHangCho.setIcon(iconLamMoi);
		pnChucNangHangCho.add(btnChonThanhToan);
		pnChucNangHangCho.add(btnLuuHoaDon);
		pnChucNangHangCho.add(btnXoaHangCho);
		pnChucNangHangCho.add(btnLamMoiHangCho);
		pnTblHangCho.add(scrollTblHangDoi, BorderLayout.CENTER);
		pnTblHangCho.add(pnChucNangHangCho, BorderLayout.SOUTH);
		
		ngayNhap = new Date();
		String currentDate = new SimpleDateFormat("dd/MM/yyyy").format(ngayNhap);
		txtNgayLap.setText(currentDate);
		pnTinhTien.add(lblNgayLap);
		pnTinhTien.add(txtNgayLap); 
		pnTinhTien.add(lblThue); 
		pnTinhTien.add(txtThue); 
		pnTinhTien.add(lblTongTien);
		pnTinhTien.add(txtTongTienGioHang);
		pnTinhTien.add(lblTienKhachDua);
		pnTinhTien.add(txtTienKhachDua);
		pnTinhTien.add(lblTienTraKhach);
		pnTinhTien.add(txtTienTraKhach);
		pnTinhTien.add(lblTongTienHoaDon);
		pnTinhTien.add(txtTongTienHoaDon);
		pn3.add(pnTinhTien);
		pnFooterRight.add(pn3, BorderLayout.NORTH);
		pnFooterRight.add(pnLamMoiHD, BorderLayout.CENTER);
		btnThanhToan = new JButton("THANH TOÁN & IN");
		btnThanhToan.setIcon(iconLuuVaIn);
		btnThanhToan.setPreferredSize(new Dimension(200, 35));
		btnLamMoiDonHang = new JButton("Làm mới hoá đơn");
		btnLamMoiDonHang.setIcon(iconLamMoi);
		btnTaoMaHoaDon = new JButton("Tạo mã hoá đơn");
		pnLamThanhToan.add(btnTaoMaHoaDon);
		pnLamThanhToan.add(btnThanhToan);
		pnLamThanhToan.add(btnLamMoiDonHang);
		pnFooterRight.add(pnLamThanhToan, BorderLayout.SOUTH);
		pnRight.add(pnHeaderRight, BorderLayout.NORTH);
		pnRight.add(pnTblHangCho, BorderLayout.CENTER);
		pnRight.add(pnFooterRight, BorderLayout.SOUTH);
		pnRight.setBorder(BorderFactory.createTitledBorder(""));
		
		
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.7;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(pnLeft, gbc);

        
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(pnRight, gbc);
        
        btnChonMaSanPham.addActionListener(this);
        btnThemGioHang.addActionListener(this);
        btnThemGioHang2.addActionListener(this);
        btnXoaGioHang.addActionListener(this);
        btnLamMoiGioHang.addActionListener(this);
        tblGioHang.addMouseListener(this);
        tblSanPham.addMouseListener(this);
        tblkhachHang.addMouseListener(this);
        btnThanhToan.addActionListener(this);
    	btnLamMoiDonHang.addActionListener(this);
    	btnLuuHoaDon.addActionListener(this);
    	btnChonThanhToan.addActionListener(this);
    	btnXoaHangCho.addActionListener(this);
    	btnLamMoiHangCho.addActionListener(this);
    	btnTimKiemSanPham.addActionListener(this);
    	btnDongSanPham.addActionListener(this);
    	btnDongKhachHang.addActionListener(this);
    	btnTimKhachHang.addActionListener(this);
    	cbLocSanPham.addActionListener(this);
    	btnCapNhatSoLuong.addActionListener(this);
    	btnXacNhanCapNhat.addActionListener(this);
    	btnXemTatCaKhachHang.addActionListener(this);
    	btnChonKH.addActionListener(this);
    	btnTaoMaHoaDon.addActionListener(this);
    	btnXemTatCaSanPham.addActionListener(this);
        txtTienKhachDua.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
            	
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            	
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Không sử dụng cho JTextField
            }
        });
               
        try {
			ConnectDB.getinstance().connect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
        
        txtMaSP.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
            	modelMaSP.setRowCount(0);
            	handleTimKiemMaSP(txtMaSP.getText().trim());
            	if (!txtMaSP.getText().equals("")) {
            		 windowMaSP.setLocation(txtMaSP.getLocationOnScreen().x, txtMaSP.getLocationOnScreen().y + txtMaSP.getHeight());
            		 windowMaSP.pack();
            		 windowMaSP.setSize(txtMaSP.getWidth(), windowMaSP.getHeight()/2);
            		 windowMaSP.setVisible(true);
            	} else {
            		windowMaSP.setVisible(false);
            	}
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            	modelMaSP.setRowCount(0);
            	handleTimKiemMaSP(txtMaSP.getText().trim());
            	if (!txtMaSP.getText().equals("")) {
            		windowMaSP.setLocation(txtMaSP.getLocationOnScreen().x, txtMaSP.getLocationOnScreen().y + txtMaSP.getHeight());
            		windowMaSP.pack();
            		windowMaSP.setSize(txtMaSP.getWidth(), windowMaSP.getHeight()/2);
            		windowMaSP.setVisible(true);
            	} else {
            		windowMaSP.setVisible(false);
            	}
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            	
            }
        });
        modelGioHang.addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.INSERT || e.getType() == TableModelEvent.DELETE || e.getType() == TableModelEvent.UPDATE) {
					tinhTongThanhTien();
					tinhThue();
					tinhTongTienHoaDon();
					tinhTienTraKhach();
                }
				
			}
		});

        ListSelectionModel selectionModel = tblGioHang.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					tinhTongThanhTien();
					tinhThue();
					tinhTongTienHoaDon();
					tinhTienTraKhach();
                }
				
			}
		});
        
        txtTienKhachDua.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
            	if (!txtTienKhachDua.getText().equals("") && txtTienKhachDua.getText().matches("\\d+")) {
            	    tinhTienTraKhach();
            	} else if (txtTienKhachDua.getText().equals("")){
                	txtTienTraKhach.setText(currencyFormat.format(0.0));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            	if (!txtTienKhachDua.getText().equals("") && txtTienKhachDua.getText().matches("\\d+")) {
            	    tinhTienTraKhach();
            	} else if (txtTienKhachDua.getText().equals("")){
                	txtTienTraKhach.setText(currencyFormat.format(0.0));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            	
            }
        });
        
        loadHoaDonCho();
	}
	
	private void tinhTienTraKhach() {
		if (!txtTienKhachDua.getText().equals("")) {
			Double tienKhachDua = Double.parseDouble(txtTienKhachDua.getText());
			String tongThanhTienHoaDon = txtTongTienHoaDon.getText();
			double tongThanhTienHoaDonDouble = Double.parseDouble(tongThanhTienHoaDon.trim().replace("\u00A0", "").replaceAll("[.,₫]", ""));
			if (tienKhachDua - tongThanhTienHoaDonDouble < 0) {
				txtTienTraKhach.setText(currencyFormat.format(0.0));
			} else {
				txtTienTraKhach.setText(currencyFormat.format(tienKhachDua - tongThanhTienHoaDonDouble));
			}
		}
	}
	
	private void tinhTongTienHoaDon() {
		String thue = txtThue.getText();
		double thueDouble = Double.parseDouble(thue.trim().replace("\u00A0", "").replaceAll("[.,₫]", ""));
		String tongThanhTienGioHang = txtTongTienGioHang.getText();
		double tongThanhTienGioHangDouble = Double.parseDouble(tongThanhTienGioHang.trim().replace("\u00A0", "").replaceAll("[.,₫]", ""));
		txtTongTienHoaDon.setText(currencyFormat.format(tongThanhTienGioHangDouble + thueDouble));
	}
	
	private void tinhThue() {
		String tongThanhTienGioHang = txtTongTienGioHang.getText();
		double tongThanhTienGioHangDouble = Double.parseDouble(tongThanhTienGioHang.trim().replace("\u00A0", "").replaceAll("[.,₫]", ""));
		String thue = currencyFormat.format(tongThanhTienGioHangDouble * 0.1);
		txtThue.setText(thue);
	}
	
	private void tinhTongThanhTien() {
		int row = modelGioHang.getRowCount();
		double tongTienHoaDon = 0;
		if (row > 0) {
			for (int i = 0; i < row; i++) {
				String thanhTien = modelGioHang.getValueAt(i, 6).toString();
				double thanhTienDouble = Double.parseDouble(thanhTien.trim().replace("\u00A0", "").replaceAll("[.,₫]", ""));
				tongTienHoaDon += thanhTienDouble;
			}
			txtTongTienGioHang.setText(currencyFormat.format(tongTienHoaDon));
		} else {
			txtTongTienGioHang.setText(currencyFormat.format(0.0));
		}
	}
	
	private void loadSach() {
		modelSP.setRowCount(0);
		ArrayList<SachCon> ds = daoSach.getDanhSachSachTimKiemTheoDieuKien("s");
		for (SachCon s : ds) {
			String idSanPham = s.getIdSanPham();
			String tenSanPham = s.getTenSanPham();
			String tenTacGia = s.getTacGia().getIdTacGia();
			String tenTheLoai = s.getTheLoai().getIdTheLoai();
			Date namXuatBan = s.getNamXuatBan();
			String ISBN = s.getISBN();
			int soTrang = s.getSoTrang();
			String loaiSanPham = s.getIdLoaiSanPham().getIdLoaiSanPham();
			String nhaCungCap = s.getIdNhaCungCap().getIdNhaCungCap();
			double kichThuoc = s.getKichThuoc();
			String mauSac = s.getMauSac();
			String trangThai = s.getTrangThai().getDescription();
			double thue = s.thue();
			int soLuong = s.getSoLuong();
			double giaKM = s.getGiaKM();
			double giaBan = s.giaBan();
			modelSP.addRow(new Object[] { idSanPham, tenSanPham, tenTacGia, tenTheLoai, namXuatBan, ISBN, soTrang,
					loaiSanPham, nhaCungCap, kichThuoc, mauSac, trangThai, currencyFormat.format(thue), soLuong
					, currencyFormat.format(giaBan), currencyFormat.format(giaKM) });
		}
	}
	
	private void loadKhachHang() {
		for (KhachHang kh : daoKhachHang.getKhachHangTimKiem("kh")) {
			String ngaySinh = new SimpleDateFormat("dd/MM/yyyy").format(kh.getNgaySinh());
			modelKH.addRow(new String[] { kh.getIdKhachHang(), kh.getTenKhachHang(),kh.getSdt(), kh.getEmail(),kh.getDiaChi(),ngaySinh,kh.isGioiTinh()?"Nam":"Nữ"
					});	
		}
	}
	
	private void loadSanPham() {
		modelSP.setRowCount(0);
		ArrayList<SanPhamCon> ds = daoQLSP.getDanhSachSanPhamTimKiem("sp");
		for (SanPhamCon sanPham : ds) {
			String idSanPham = sanPham.getIdSanPham();
			String tenSanPham = sanPham.getTenSanPham();
			String tenLoaiSanPham = sanPham.getIdLoaiSanPham().getIdLoaiSanPham();
			String tenNhaCungCap = sanPham.getIdNhaCungCap().getIdNhaCungCap();
			double kichThuoc = sanPham.getKichThuoc();
			String mauSac = sanPham.getMauSac();
			String trangThai = sanPham.getTrangThai()+"";
			double thue = sanPham.thue();
			int soLuong = sanPham.getSoLuong();
			double giaBan = sanPham.giaBan();
			double giaKM = sanPham.getGiaKM();
			modelSP.addRow(new Object[] { idSanPham, tenSanPham, tenLoaiSanPham, tenNhaCungCap, kichThuoc, mauSac,
					trangThai, currencyFormat.format(thue), soLuong, currencyFormat.format(giaBan),  currencyFormat.format(giaKM)});
		}
	}
	
	private void lamMoiHangCho() {
		int hoiNhac = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xoá toàn bộ hàng chờ không", "Cảnh báo",
				JOptionPane.YES_NO_OPTION);
		if (hoiNhac == JOptionPane.YES_OPTION) {
			daoQLBH.xoaAllChiTietHoaDonChoSach();
			daoQLBH.xoaAllChiTietHoaDonChoSanPham();
			daoQLBH.xoaAllHoaDonCho();
			modelHangCho.setRowCount(0);
		}
	}
	
	private void handleXoaHangCho() {
		if (modelHangCho.getRowCount() > 0) {
			int rowSelectedDonHangCho = tblHangCho.getSelectedRow();
			if (rowSelectedDonHangCho >= 0) {
				int hoiNhac = JOptionPane.showConfirmDialog(this, "Bạn có muốn xoá hàng chờ không", "Cảnh báo",
						JOptionPane.YES_NO_OPTION);
				if (hoiNhac == JOptionPane.YES_OPTION) {
					daoQLBH.xoaChiTietHoaDonChoSanPham(modelHangCho.getValueAt(rowSelectedDonHangCho, 0).toString());
					daoQLBH.xoaChiTietHoaDonChoSach(modelHangCho.getValueAt(rowSelectedDonHangCho, 0).toString());
					daoQLBH.xoaHoaDonCho(modelHangCho.getValueAt(rowSelectedDonHangCho, 0).toString());
					modelHangCho.removeRow(rowSelectedDonHangCho);
					showThongBao("Xoá hàng chờ thành công");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Bạn chưa chọn hoá đơn chờ để xoá");
			}
		} else {
			showThongBao("Hàng chờ rỗng");
		}
	}
	
	private void chonThanhToan(int rowSelectedDonHangCho) {
		txtMaHD.setText(modelHangCho.getValueAt(rowSelectedDonHangCho, 0).toString());
		txtTenKH.setText(modelHangCho.getValueAt(rowSelectedDonHangCho, 1).toString());
		txtMaKH.setText(modelHangCho.getValueAt(rowSelectedDonHangCho, 2).toString());
		modelHangCho.removeRow(rowSelectedDonHangCho);
		ArrayList<ChiTietHoaDonCho> dsChiTietSanPham = daoQLBH.getChiTietHoaDonChoSanPhamTheoId(txtMaHD.getText());
		ArrayList<ChiTietHoaDonCho> dsChiTietSach = daoQLBH.getChiTietHoaDonChoSachTheoId(txtMaHD.getText());
		if (dsChiTietSanPham.size() != 0) {
			for (ChiTietHoaDonCho cthdc : dsChiTietSanPham) {
				SanPhamCon sp = daoQLSP.getSanPham(cthdc.getSanPham().getIdSanPham());
				modelGioHang.addRow(new Object[] {
						cthdc.getSanPham().getIdSanPham(), sp.getTenSanPham(),
						currencyFormat.format(sp.giaBan()), "-"+currencyFormat.format(sp.getGiaKM()),
						currencyFormat.format(cthdc.getGiaCuoi()), cthdc.getSoLuong(), currencyFormat.format(cthdc.getThanhTien())
				});
			}
		}
		if (dsChiTietSach.size() != 0) {
			for (ChiTietHoaDonCho cthdc : dsChiTietSach) {
				SachCon s = daoSach.getSach(cthdc.getSanPham().getIdSanPham());
				modelGioHang.addRow(new Object[] {
						cthdc.getSanPham().getIdSanPham(), s.getTenSanPham(),
						currencyFormat.format(s.giaBan()), "-"+currencyFormat.format(s.getGiaKM()),
						currencyFormat.format(cthdc.getGiaCuoi()), cthdc.getSoLuong(), currencyFormat.format(cthdc.getThanhTien())
				});
			}
		} 
		daoQLBH.xoaChiTietHoaDonChoSach(txtMaHD.getText());
		daoQLBH.xoaChiTietHoaDonChoSanPham(txtMaHD.getText());
		daoQLBH.xoaHoaDonCho(txtMaHD.getText());
	}
	
	private void handleChonThanhToan() {
		if (modelHangCho.getRowCount() > 0) {
			int rowSelectedDonHangCho = tblHangCho.getSelectedRow();
			if (modelGioHang.getRowCount() == 0) {
				if (rowSelectedDonHangCho >= 0) {
					chonThanhToan(rowSelectedDonHangCho);
				} else {
					JOptionPane.showMessageDialog(this, "Bạn chưa chọn hoá đơn chờ để tiếp tục thanh toán");
				}
			} else {
				if (rowSelectedDonHangCho >= 0) {
					int hoiNhac = JOptionPane.showConfirmDialog(this, "Bạn có muốn lưu đơn hàng hiện tại không!", "Cảnh báo",
							JOptionPane.YES_NO_OPTION);
					if (hoiNhac == JOptionPane.YES_OPTION) {
						handleLuuHoaDonChoVaCTHDC();
						chonThanhToan(rowSelectedDonHangCho);
					} else {
						modelGioHang.setRowCount(0);
						chonThanhToan(rowSelectedDonHangCho);
					}
				} else {
					JOptionPane.showMessageDialog(this, "Bạn chưa chọn hoá đơn chờ để tiếp tục thanh toán");
				}
			} 
		} else {
			JOptionPane.showMessageDialog(this, "Hàng chờ rỗng");
		}
	}

	
	private void luuHoaDonChoVaCTHDC() {
		String maHD = txtMaHD.getText();
		String maKhachHang = txtMaKH.getText();
		KhachHang kh = daoKhachHang.getKhachHang(maKhachHang);
		java.sql.Date ngayNhapSql = new java.sql.Date(ngayNhap.getTime());
		String currentDate = new SimpleDateFormat("yyMMddHHmmss").format(new java.util.Date());
		HoaDonCho hdc = new HoaDonCho("HDC" + currentDate, maHD, kh, ngayNhapSql);
		try {
			daoQLBH.themHoaDonCho(hdc);
			String ngayLap = new SimpleDateFormat("dd/MM/yyyy").format(ngayNhap);
			modelHangCho.addRow(new Object[] {
					maHD, kh.getTenKhachHang(), kh.getIdKhachHang(), kh.getSdt(), ngayLap
					
			});
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Thêm hoá đơn chờ thất bại");
		}
		int rowCountGioHang = modelGioHang.getRowCount();
		for (int i = 0; i < rowCountGioHang; i++) {
			String maSP = modelGioHang.getValueAt(i, 0).toString();
			if (maSP.startsWith("SP")) {
				int soLuong = Integer.parseInt(modelGioHang.getValueAt(i, 5).toString());
				SanPhamCon sp = daoQLSP.getSanPham(maSP);
				String giaCuoi = modelGioHang.getValueAt(i, 4).toString();
				double giaCuoiDouble = Double.parseDouble(giaCuoi.trim().replace("\u00A0", "").replaceAll("[.,₫]", ""));
				String thanhTien = modelGioHang.getValueAt(i, 6).toString();
				double thanhTienDouble = Double.parseDouble(thanhTien.trim().replace("\u00A0", "").replaceAll("[.,₫]", ""));
				ChiTietHoaDonCho cthdc = new ChiTietHoaDonCho(hdc, maHD, sp, soLuong, giaCuoiDouble, thanhTienDouble);
				try {
					daoQLBH.themChiTietHoaDonChoSanPham(cthdc);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(this, "Thêm chi tiết hoá đơn chờ thất bại");
				}
			} else {
				int soLuong = Integer.parseInt(modelGioHang.getValueAt(i, 5).toString());
				SachCon sp = daoSach.getSach(maSP);
				String giaCuoi = modelGioHang.getValueAt(i, 4).toString();
				double giaCuoiDouble = Double.parseDouble(giaCuoi.trim().replace("\u00A0", "").replaceAll("[.,₫]", ""));
				String thanhTien = modelGioHang.getValueAt(i, 6).toString();
				double thanhTienDouble = Double.parseDouble(thanhTien.trim().replace("\u00A0", "").replaceAll("[.,₫]", ""));
				ChiTietHoaDonCho cthdc = new ChiTietHoaDonCho(hdc, maHD, sp, soLuong, giaCuoiDouble, thanhTienDouble);
				try {
					daoQLBH.themChiTietHoaDonChoSach(cthdc);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(this, "Thêm chi tiết hoá đơn chờ thất bại");
				}
			}
		}
		modelGioHang.setRowCount(0);
		handleLamMoiHoaDon();
	}
	
	private void handleLuuHoaDonChoVaCTHDC() {
		if (!txtMaKH.getText().equals("")) {
			if (modelGioHang.getRowCount() > 0) {
				luuHoaDonChoVaCTHDC();
				showThongBao("Lưu hoá đơn thành công");
			} else {
				JOptionPane.showMessageDialog(this, "Giỏ hàng rỗng");
			}
		} else {
			JOptionPane.showMessageDialog(this, "Không thể lưu hoá đơn chờ vì chưa chọn khách hàng");
		}
	}
	
	
	private void loadHoaDonCho() {
		ArrayList<HoaDonCho> dsHoaDonCho = daoQLBH.getAllHoaDonCho();
		if (dsHoaDonCho.size() > 0) {
			for (HoaDonCho hdc : dsHoaDonCho) {
				String ngayLap = new SimpleDateFormat("dd/MM/yyyy").format(hdc.getNgayLap());
				modelHangCho.addRow(new Object[] {
						hdc.getIdDonHang(), hdc.getKhachHang().getTenKhachHang(),
						hdc.getKhachHang().getIdKhachHang(), hdc.getKhachHang().getSdt(),
						ngayLap
				});
			}
		}
	}
	
	
	
	private void handLeThanhToan() {
		if (!txtMaKH.getText().equals("")) {
			if (modelGioHang.getRowCount() >= 0) {
				
				if (txtTienKhachDua.getText().equals("")) {
            	    JOptionPane.showMessageDialog(this, "Chưa nhập tiền khách đưa");
            	    txtTienKhachDua.requestFocus(); 
            	    txtTienKhachDua.setSelectionStart(0); 
            	    txtTienKhachDua.setSelectionEnd(txtTienKhachDua.getText().length());
            	    return;
            	} 
				
				if (!txtTienKhachDua.getText().matches("\\d+")) {
					JOptionPane.showMessageDialog(this, "Tiền khách đưa nhập sai định dạng");
            	    txtTienKhachDua.requestFocus(); 
            	    txtTienKhachDua.setSelectionStart(0); 
            	    txtTienKhachDua.setSelectionEnd(txtTienKhachDua.getText().length());
            	    return;
            	} 
				
				String tongTienHoaDon = txtTongTienHoaDon.getText();
				double tongTienHoaDonDouble = Double.parseDouble(tongTienHoaDon.trim().replace("\u00A0", "").replaceAll("[.,₫]", ""));
				double tienKhachDua = Double.parseDouble(txtTienKhachDua.getText());
				
				if (tienKhachDua < tongTienHoaDonDouble){
            		JOptionPane.showMessageDialog(this, "Tiền khách đưa bé hơn tổng tiền hoá đơn");
            		txtTienKhachDua.requestFocus(); 
             	    txtTienKhachDua.setSelectionStart(0); 
             	    txtTienKhachDua.setSelectionEnd(txtTienKhachDua.getText().length());
             	    return;
            	} 
				luuHoaDonVaCTHD();
				lamMoiGioHang();
				handleLamMoiHoaDon();
				txtTienKhachDua.setText("");
			} else {
				JOptionPane.showMessageDialog(this, "Giỏ hàng rỗng");
			}
		} else {
			JOptionPane.showMessageDialog(this, "Chưa thể thanh toán được vì chưa chọn khách hàng");
			showDialogKhachHang();
		}
	}
	
	private void luuHoaDonVaCTHD() {
		String idHD = txtMaHD.getText();
		KhachHang kh = new KhachHang(txtMaKH.getText());
		NhanVien nv = new NhanVien(nvLogIn.getId());
		String tienKhachDua = txtTienKhachDua.getText();
		double tienKhachDuaDouble = Double.parseDouble(tienKhachDua.trim().replace("\u00A0", "").replaceAll("[.,₫]", ""));
		String tongTienHoaDon = txtTongTienHoaDon.getText();
		double tongTienHoaDonDouble = Double.parseDouble(tongTienHoaDon.trim().replace("\u00A0", "").replaceAll("[.,₫]", ""));
		java.sql.Date ngayNhapSql = new java.sql.Date(ngayNhap.getTime());
		double tongLoiNhuan = 0;
		int gioHangRowCount = modelGioHang.getRowCount();
		
		for (int i = 0; i < gioHangRowCount; i++) {
			if (modelGioHang.getValueAt(i, 0).toString().startsWith("SP")) {
				SanPhamCon sanPham = daoQLSP.getSanPhamTimKiemTheoMa(modelGioHang.getValueAt(i, 0).toString());
				int soLuong = Integer.parseInt(modelGioHang.getValueAt(i, 5).toString());
				double giaNhap = sanPham.getGiaNhap();
				double loiNhuan = (giaNhap * 0.55) * soLuong;
				tongLoiNhuan += loiNhuan;
			} else {
				SachCon sach = daoSach.getSachTimKiemTheoMa(modelGioHang.getValueAt(i, 0).toString());
				int soLuong = Integer.parseInt(modelGioHang.getValueAt(i, 5).toString());
				double giaNhap = sach.getGiaNhap();
				double loiNhuan = (giaNhap * 0.55) * soLuong;
				tongLoiNhuan += loiNhuan;
			}
		}
		
		HoaDon hd = new HoaDon(idHD, ngayNhapSql, kh, nv, tienKhachDuaDouble, tongTienHoaDonDouble, tongLoiNhuan);
		try {
			daoQLBH.themHoaDon(hd);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Thêm hoá đơn thất bại");
		}
		int rowCountGioHang = modelGioHang.getRowCount();
		for (int i = 0; i < rowCountGioHang; i++) {
			if (modelGioHang.getValueAt(i, 0).toString().startsWith("SP")) {
				int soLuong = Integer.parseInt(modelGioHang.getValueAt(i, 5).toString());
				String maSP = modelGioHang.getValueAt(i, 0).toString();
				SanPhamCha sp = new SanPhamCha(maSP) {
					
					@Override
					public double thue() {
						// TODO Auto-generated method stub
						return 0;
					}
					
					@Override
					public double giaBan() {
						// TODO Auto-generated method stub
						return 0;
					}

				};
				String thanhTien = modelGioHang.getValueAt(i, 6).toString();
				double thanhTienDouble = Double.parseDouble(thanhTien.trim().replace("\u00A0", "").replaceAll("[.,₫]", ""));
				SanPhamCon sanPham = daoQLSP.getSanPhamTimKiemTheoMa(modelGioHang.getValueAt(i, 0).toString());
				double giaNhap = sanPham.getGiaNhap();
				double loiNhuan = (giaNhap * 0.55) * soLuong;
				ChiTietHoaDon cthd = new ChiTietHoaDon(soLuong, hd, sp, thanhTienDouble, loiNhuan);
				try {
					daoQLBH.themChiTietHoaDonSanPham(cthd);
					daoQLSP.capNhatSoLuongSanPham(soLuong, maSP);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(this, "Thêm chi tiết hoá đơn thất bại");
				}
			} else {
				int soLuong = Integer.parseInt(modelGioHang.getValueAt(i, 5).toString());
				String maSP = modelGioHang.getValueAt(i, 0).toString();
				SanPhamCha sp = new SanPhamCha(maSP) {
					
					@Override
					public double thue() {
						// TODO Auto-generated method stub
						return 0;
					}
					
					@Override
					public double giaBan() {
						// TODO Auto-generated method stub
						return 0;
					}

				};
				String thanhTien = modelGioHang.getValueAt(i, 6).toString();
				Double thanhTienDouble = Double.parseDouble(thanhTien.trim().replace("\u00A0", "").replaceAll("[.,₫]", ""));
				SachCon sach = daoSach.getSachTimKiemTheoMa(modelGioHang.getValueAt(i, 0).toString());
				double giaNhap = sach.getGiaNhap();
				double loiNhuan = (giaNhap * 0.55) * soLuong;
				ChiTietHoaDon cthd = new ChiTietHoaDon(soLuong, hd, sp, thanhTienDouble, loiNhuan);
				try {
					daoQLBH.themChiTietHoaDonSach(cthd);
					daoSach.capNhatSoLuongSach(soLuong, maSP);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(this, "Thêm chi tiết hoá đơn thất bại");
				}
			}
		}
	}
	
	
	private void handleLamMoiHoaDon() {
		if (modelGioHang.getRowCount() == 0) {
			txtMaHD.setText("");
			txtTenKH.setText("");
			txtMaKH.setText("");
			tblHangCho.clearSelection();
		} else {
			int hoiNhac = JOptionPane.showConfirmDialog(this, "Bạn có muốn lưu đơn hàng hiện tại không!", "Cảnh báo",
					JOptionPane.YES_NO_OPTION);
			if (hoiNhac == JOptionPane.YES_OPTION) {
				handleLuuHoaDonChoVaCTHDC();
			} else {
				txtMaHD.setText("");
				txtTenKH.setText("");
				txtMaKH.setText("");
				modelGioHang.setRowCount(0);
				tblHangCho.clearSelection();
			}
		} 
		
	}
	
	
	private void handleChonKhachHang() {
		int rowKH = tblkhachHang.getSelectedRow();
		if (rowKH >= 0) {
			txtTenKH.setText(modelKH.getValueAt(rowKH, 1).toString());
			txtMaKH.setText(modelKH.getValueAt(rowKH, 0).toString());
			dialogKhachHang.dispose();
			modelKH.setColumnCount(0);
			modelKH.setRowCount(0);
			phatSinhMaHD();
		} else {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng");
		}
	}
	
	
	private void lamMoiKhachHang() {
		txtTimKiemKH.setText("");
	}
	
	
	private void chonMaSanPham() {
		if (modelMaSP.getRowCount() > 0) {
			int rowSP = tblMaSp.getSelectedRow();
			if (rowSP >= 0) {
				txtMaSP.setText(modelMaSP.getValueAt(rowSP, 0).toString());
				windowMaSP.setVisible(false);
			} else {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn mã sản phẩm");
				windowMaSP.setLocation(txtMaSP.getLocationOnScreen().x, txtMaSP.getLocationOnScreen().y + txtMaSP.getHeight());
	       		windowMaSP.pack();
	       		windowMaSP.setSize(txtMaSP.getWidth(), windowMaSP.getHeight()/2);
	       		windowMaSP.setVisible(true);
			}
		} else {
			JOptionPane.showMessageDialog(this, "Mã sản phẩm không tồn tại");
		}
	}
	
	
	private void xacNhanCapNhat(String idSanPham) {
		if (idSanPham.startsWith("SP")) {
			SanPhamCon sp = daoQLSP.getSanPham(idSanPham);
			if (txtCapNhatSoLuong.getText().equals("")) {
        	    JOptionPane.showMessageDialog(this, "Chưa nhập số lượng cập nhật");
        	    dialogSoLuong.setVisible(false);
				showDialogCapNhatSoLuong();
        	} else if (!txtCapNhatSoLuong.getText().matches("\\d+")) {
				JOptionPane.showMessageDialog(this, "Số lượng nhập sai định dạng");
        	    dialogSoLuong.setVisible(false);
				showDialogCapNhatSoLuong();
        	} else if (Integer.parseInt(txtCapNhatSoLuong.getText()) <= 0) {
				JOptionPane.showMessageDialog(this, "Số lượng không được âm");
        	    dialogSoLuong.setVisible(false);
				showDialogCapNhatSoLuong();
        	} else if (Integer.parseInt(txtCapNhatSoLuong.getText()) <= sp.getSoLuong()) {
				modelGioHang.setValueAt(txtCapNhatSoLuong.getText(), tblGioHang.getSelectedRow(), 5);
				tinhThanhTienGioHang();
				dialogSoLuong.setVisible(false);
			} else {
				JOptionPane.showMessageDialog(this, "Số lượng sản phẩm trong kho không đủ");
				dialogSoLuong.setVisible(false);
				showDialogCapNhatSoLuong();
			}
		} else {
			SachCon sach = daoSach.getSach(idSanPham);
			if (txtCapNhatSoLuong.getText().equals("")) {
        	    JOptionPane.showMessageDialog(this, "Chưa nhập số lượng cập nhật");
        	    dialogSoLuong.setVisible(false);
				showDialogCapNhatSoLuong();
        	} else if (!txtCapNhatSoLuong.getText().matches("\\d+")) {
				JOptionPane.showMessageDialog(this, "Số lượng nhập sai định dạng");
        	    dialogSoLuong.setVisible(false);
				showDialogCapNhatSoLuong();
        	} else if (Integer.parseInt(txtCapNhatSoLuong.getText()) <= 0) {
				JOptionPane.showMessageDialog(this, "Số lượng không được âm");
        	    dialogSoLuong.setVisible(false);
				showDialogCapNhatSoLuong();
        	} else if (Integer.parseInt(txtCapNhatSoLuong.getText()) <= sach.getSoLuong()) {
				modelGioHang.setValueAt(txtCapNhatSoLuong.getText(), tblGioHang.getSelectedRow(), 5);
				tinhThanhTienGioHang();
				dialogSoLuong.setVisible(false);
			} else {
				JOptionPane.showMessageDialog(this, "Số lượng sách trong kho không đủ");
				dialogSoLuong.setVisible(false);
				showDialogCapNhatSoLuong();
			}
			
		}
	}
	
	
	private void lamMoiGioHang() {
		int rowCountGioHang = modelGioHang.getRowCount();
		if (rowCountGioHang > 0) {
			modelGioHang.setRowCount(0);
		} else {
			showThongBao("Giỏ hàng rỗng");
		}
	}
	
	
	private void xoaGioHang() {
		int rowSelectedGioHang = tblGioHang.getSelectedRow();
		int rowCountGioHang = modelGioHang.getRowCount();
		if (rowCountGioHang > 0) {
			if (rowSelectedGioHang >= 0) {
				modelGioHang.removeRow(rowSelectedGioHang);
			} else {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần xoá");
			}
		} else {
			showThongBao("Giỏ hàng rỗng");
		}
				
	}
	
	private void tinhThanhTienGioHang() {
		int row = modelGioHang.getRowCount();
		if (row > 0) {
			for (int i = 0; i < row; i++) {
				String giaCuoi = modelGioHang.getValueAt(i, 4).toString();
				String giaKMDouble = giaCuoi.trim().replace("\u00A0", "").replaceAll("[.,₫]", "");
				String soLuong = modelGioHang.getValueAt(i, 5).toString();
				String thanhTien = currencyFormat.format(Double.parseDouble(giaKMDouble) * Integer.parseInt(soLuong));
				modelGioHang.setValueAt(thanhTien, i, 6);
			}
		}
	}
	
	
	private void showDialogCapNhatSoLuong() {
		int rowSelectedGioHang = tblGioHang.getSelectedRow();
		int rowCountGioHang = modelGioHang.getRowCount();
		if (rowCountGioHang > 0) {
			if (rowSelectedGioHang >= 0) {
				dialogSoLuong = new JDialog();
				dialogSoLuong.setType(Type.UTILITY);
				dialogSoLuong.setLayout(new BorderLayout());
				JPanel pnXacNhanCapNhat = new JPanel();
				pnXacNhanCapNhat.add(btnXacNhanCapNhat);
				dialogSoLuong.add(txtCapNhatSoLuong, BorderLayout.CENTER);
				dialogSoLuong.add(pnXacNhanCapNhat, BorderLayout.SOUTH);
				
				Rectangle cellRect = tblGioHang.getCellRect(rowSelectedGioHang, 4, true);
		        int cellX = cellRect.x;
		        int cellY = cellRect.y;
		        dialogSoLuong.setLocation(cellX, cellY);
				dialogSoLuong.pack();
				dialogSoLuong.setLocationRelativeTo(null);
				dialogSoLuong.setVisible(true);
				txtCapNhatSoLuong.setText(modelGioHang.getValueAt(rowSelectedGioHang, 5).toString());
				txtCapNhatSoLuong.requestFocus(); 
				txtCapNhatSoLuong.setSelectionStart(0); 
				txtCapNhatSoLuong.setSelectionEnd(txtCapNhatSoLuong.getText().length());
			} else {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần thay đổi số lượng");
			}
		} else {
			showThongBao("Giỏ hàng rỗng");
		}
    }

	
	private void showThongBao(String message) {
	    frameThongBao = new JFrame();
	    frameThongBao.setUndecorated(true);
	    frameThongBao.setBackground(new Color(0, 0, 0, 0));
	    frameThongBao.setAlwaysOnTop(true);

	    JLabel label = new JLabel(message, SwingConstants.CENTER);
	    label.setFont(new Font("Arial", Font.BOLD, 25));
	    label.setForeground(Color.gray);
	    frameThongBao.add(label);

	    frameThongBao.addWindowListener(new WindowAdapter() {
	        @Override
	        public void windowOpened(WindowEvent e) {
	            fadeIn();
	        }
	    });

	    frameThongBao.pack();

	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    int screenWidth = screenSize.width;
	    int screenHeight = screenSize.height;
	    int frameWidth = frameThongBao.getSize().width;
	    int frameHeight = frameThongBao.getSize().height;
	    int x = (screenWidth - frameWidth) / 2;
	    int y = (screenHeight - frameHeight) / 2;
	    frameThongBao.setLocation(x, y);

	    frameThongBao.setVisible(true);
	}

   
	private void fadeIn() {
        Timer timer = new Timer(20, new ActionListener() {
            float opacity = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (opacity < 1) {
                	frameThongBao.setOpacity(opacity);
                    opacity += 0.05f;
                } else {
                    ((Timer) e.getSource()).stop();
                    fadeOut();
                }
            }
        });
        timer.start();
    }

    
	private void fadeOut() {
        Timer timer = new Timer(20, new ActionListener() {
            float opacity = 1;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (opacity > 0) {
                	frameThongBao.setOpacity(opacity);
                    opacity -= 0.05f;
                } else {
                    ((Timer) e.getSource()).stop();
                    frameThongBao.dispose();
                }
            }
        });
        timer.setInitialDelay(100);
        timer.start();
    }

	private void handleTimKiemMaSP(String cond) {
		if (!cond.equals("")) {
			ArrayList<SanPhamCon> ds = daoQLSP.getDanhSachSanPhamTimKiemTheoMa(cond);
			for (SanPhamCon sanPham : ds) {
				String idSanPham = sanPham.getIdSanPham();
				modelMaSP.addRow(new Object[] { idSanPham });
			}
			ArrayList<SachCon> ds2 = daoSach.getDanhSachSachTimKiemTheoMa(cond);
			for (SachCon s : ds2) {
				String idSanPham = s.getIdSanPham();
				modelMaSP.addRow(new Object[] { idSanPham });
			}
			
		}
	}
	
	private void themGioHangBangTimKiem(int row) {
		String soLuong = String.valueOf(spinnerSoLuong2.getValue());
		if (cbLocSanPham.getSelectedIndex() == 0) {
			int soLuongTrongKho = Integer.parseInt(modelSP.getValueAt(row, 8).toString());
			if (Integer.parseInt(soLuong) <= soLuongTrongKho) {
				String idSP = modelSP.getValueAt(row, 0).toString();
				String tenSP = modelSP.getValueAt(row, 1).toString();
				String giaBan = modelSP.getValueAt(row, 9).toString();
				String giaKM = modelSP.getValueAt(row, 10).toString();
				String giaKMDouble = giaKM.trim().replace("\u00A0", "").replaceAll("[.,₫]", "");
				String giaBanDouble = giaBan.trim().replace("\u00A0", "").replaceAll("[.,₫]", "");
				double giaCuoi = Double.parseDouble(giaBanDouble) - Double.parseDouble(giaKMDouble);
				String thanhTien = currencyFormat.format(giaCuoi * Integer.parseInt(soLuong));
				modelGioHang.addRow(new String[] {
					idSP, tenSP, giaBan, "-"+giaKM, currencyFormat.format(giaCuoi), soLuong, thanhTien
				});
				showThongBao("Đã thêm vào giỏ hàng");
				spinnerSoLuong2.setValue(1);
				txtTimKiemSP.setText("");
				txtMaSP2.setText("");
			} else {
				JOptionPane.showMessageDialog(dialogSanPham, "Số lượng sản phẩm trong kho không đủ");
			}
		} else {
			int soLuongTrongKho = Integer.parseInt(modelSP.getValueAt(row, 13).toString());
			if (Integer.parseInt(soLuong) <= soLuongTrongKho) {
				String idSP = modelSP.getValueAt(row, 0).toString();
				String tenSP = modelSP.getValueAt(row, 1).toString();
				String giaBan = modelSP.getValueAt(row, 14).toString();
				String giaKM = modelSP.getValueAt(row, 15).toString();
				String giaKMDouble = giaKM.trim().replace("\u00A0", "").replaceAll("[.,₫]", "");
				String giaBanDouble = giaBan.trim().replace("\u00A0", "").replaceAll("[.,₫]", "");
				double giaCuoi = Double.parseDouble(giaBanDouble) - Double.parseDouble(giaKMDouble);
				String thanhTien = currencyFormat.format(giaCuoi * Integer.parseInt(soLuong));
				modelGioHang.addRow(new String[] {
					idSP, tenSP, giaBan, "-"+giaKM, currencyFormat.format(giaCuoi), soLuong, thanhTien
				});
				showThongBao("Đã thêm vào giỏ hàng");
				spinnerSoLuong2.setValue(1);
				txtTimKiemSP.setText("");
				txtMaSP2.setText("");
			} else {
				JOptionPane.showMessageDialog(dialogSanPham, "Số lượng sách trong kho không đủ");
			}
			
		}
	}
	
	
	private void themGioHangBangMa() {
		SachCon sach = daoSach.getSachTimKiemTheoMa(txtMaSP.getText());
		SanPhamCon sp = daoQLSP.getSanPhamTimKiemTheoMa(txtMaSP.getText());
		int soLuong = (int) spinnerSoLuong.getValue();
		if (sach != null) {
			if (soLuong <= sach.getSoLuong()) {
				String idSP = sach.getIdSanPham();
				String tenSP = sach.getTenSanPham();
				Double giaBan = sach.giaBan();
				Double giaKM = sach.getGiaKM();
				Double giaCuoi = giaBan - giaKM;
				String thanhTien = currencyFormat.format(giaCuoi * soLuong);
				modelGioHang.addRow(new Object[] {
					idSP, tenSP, currencyFormat.format(giaBan), "-"+currencyFormat.format(giaKM), currencyFormat.format(giaCuoi), soLuong, thanhTien
				});
				spinnerSoLuong.setValue(1);
				txtMaSP.setText("");
				showThongBao("Đã thêm vào giỏ hàng");
				spinnerSoLuong.setValue(1);
				txtMaSP.setText("");
			} else {
				JOptionPane.showMessageDialog(this, "Số lượng sách trong kho không đủ");
			}
		} else if (sp != null){
			if (soLuong <= sp.getSoLuong()) {
				String idSP = sp.getIdSanPham();
				String tenSP = sp.getTenSanPham();
				Double giaBan = sp.giaBan();
				Double giaKM = sp.getGiaKM();
				Double giaCuoi = giaBan - giaKM;
				String thanhTien = currencyFormat.format(giaCuoi * soLuong);
				modelGioHang.addRow(new Object[] {
					idSP, tenSP, currencyFormat.format(giaBan), "-"+currencyFormat.format(giaKM), currencyFormat.format(giaCuoi), soLuong, thanhTien
				});
				spinnerSoLuong.setValue(1);
				txtMaSP.setText("");
				showThongBao("Đã thêm vào giỏ hàng");
				spinnerSoLuong.setValue(1);
				txtMaSP.setText("");
			} else {
				JOptionPane.showMessageDialog(this, "Số lượng sản phẩm trong kho không đủ");
			}
		} else {
			JOptionPane.showMessageDialog(this, "Mã sản phẩm không tồn tại");
			txtMaSP.setText("");
		}
	}
	
	
	private void handleLocSanPham() {
		if (cbLocSanPham.getSelectedIndex() == 0) {
			modelSP.setColumnCount(0);
			modelSP.addColumn("ID Sản Phẩm");
			modelSP.addColumn("Tên Sản Phẩm");
			modelSP.addColumn("Loại sản phẩm");
			modelSP.addColumn("Nhà Cung Cấp");
			modelSP.addColumn("Kích Thước");
			modelSP.addColumn("Màu Sắc");
			modelSP.addColumn("Trạng Thái");
			modelSP.addColumn("Thuế");
			modelSP.addColumn("Số Lượng");
			modelSP.addColumn("Giá Bán");
			modelSP.addColumn("Giá KM");
			modelSP.setRowCount(0);
			loadSanPham();
			txtTimKiemSP.setText("");
		} else {
			modelSP.setColumnCount(0);
			modelSP.addColumn("ID Sách");
			modelSP.addColumn("Tên Sách");
			modelSP.addColumn("Tác Giả");
			modelSP.addColumn("Thể Loại");
			modelSP.addColumn("Năm Xuất Bản");
			modelSP.addColumn("ISBN");
			modelSP.addColumn("Số Trang");
			modelSP.addColumn("Loại Sản Phẩm");
			modelSP.addColumn("Nhà Cung Cấp");
			modelSP.addColumn("Kích Thước");
			modelSP.addColumn("Màu Sắc");
			modelSP.addColumn("Trạng Thái");
			modelSP.addColumn("Thuế");
			modelSP.addColumn("Số Lượng");
			modelSP.addColumn("Giá Bán");
			modelSP.addColumn("Giá KM");
			modelSP.setRowCount(0);
			loadSach();
			txtTimKiemSP.setText("");
		}
	}
	
	
	private void handleThemGioHangBangMa() {
		if (!txtMaKH.getText().equals("")) {
			if (!txtMaSP.getText().equals("")) {
				int rowCountGioHang = modelGioHang.getRowCount();
				int flag = -1;
				Boolean isExists = false;
				if (rowCountGioHang > 0) {
					for (int i = 0; i < rowCountGioHang; ++i) {
						if (modelGioHang.getValueAt(i, 0).toString().equals(txtMaSP.getText())) {
							isExists = true;
							flag = i;
							break;
						} 
					}
				}
				if (!isExists) {
					themGioHangBangMa();
				} else {
					int soLuongCu = Integer.parseInt(modelGioHang.getValueAt(flag, 5).toString());
					int SoLuongMoi = soLuongCu + Integer.parseInt(spinnerSoLuong.getValue().toString());
					modelGioHang.setValueAt(SoLuongMoi, flag, 5);
					showThongBao("Đã thêm vào giỏ hàng");
					tinhThanhTienGioHang();
					spinnerSoLuong.setValue(1);
					txtMaSP.setText("");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Vui lòng điền mã để thêm vào giỏ hàng");
			}
		} else {
			showThongBao("Vui lòng chọn khách hàng để thêm vào giỏ hàng");
			showDialogKhachHang();
		}
	}
	
	
	private void handleThemGioHangBangTimKiem() {
		if (!txtMaKH.getText().equals("")) {
			int row = tblSanPham.getSelectedRow();
			if (row >= 0) {
				int rowCountGioHang = modelGioHang.getRowCount();
				int flag = -1;
				Boolean isExists = false;
				if (rowCountGioHang > 0) {
					for (int i = 0; i < rowCountGioHang; ++i) {
						if (modelGioHang.getValueAt(i, 0).toString().equals(modelSP.getValueAt(row, 0).toString())) {
							isExists = true;
							flag = i;
							break;
						} 
					}
					if (!isExists) {
						themGioHangBangTimKiem(row);
					} else {
						int soLuongCu = Integer.parseInt(modelGioHang.getValueAt(flag, 5).toString());
						int SoLuongMoi = soLuongCu + Integer.parseInt(spinnerSoLuong2.getValue().toString());
						
						modelGioHang.setValueAt(SoLuongMoi, flag, 5);
						showThongBao("Đã thêm vào giỏ hàng");
						tinhThanhTienGioHang();
						spinnerSoLuong2.setValue(1);
						txtTimKiemSP.setText("");
						txtMaSP2.setText("");
					}
				} else {
					themGioHangBangTimKiem(row);
				}
			} else {
				JOptionPane.showMessageDialog(dialogSanPham, "Vui lòng chọn sản phẩm để thêm vào giỏ hàng");
			}
		} else {
			showThongBao("Vui lòng chọn khách hàng để thêm vào giỏ hàng");
			showDialogKhachHang();
		}

		
		
	}
	
	
	private void handleTimKiemKH(String cond) {
		if (!cond.equals("")) {
			for (KhachHang kh : daoKhachHang.getKhachHangTimKiem(cond)) {
				String ngaySinh = new SimpleDateFormat("dd/MM/yyyy").format(kh.getNgaySinh());
				modelKH.addRow(new String[] { kh.getIdKhachHang(), kh.getTenKhachHang(),kh.getSdt(), kh.getEmail(),kh.getDiaChi(),ngaySinh,kh.isGioiTinh()?"Nam":"Nữ"
						});	
			}
		} else {
			loadKhachHang();
		}
	}
	
	
	private void handleTimKiemSach(String cond) {
		if (!cond.equals("")) {
			modelSP.setRowCount(0);
			ArrayList<SachCon> ds = daoSach.getDanhSachSachTimKiemTheoDieuKien(cond);
			for (SachCon s : ds) {
				String idSanPham = s.getIdSanPham();
				String tenSanPham = s.getTenSanPham();
				String tenTacGia = s.getTacGia().getIdTacGia();
				String tenTheLoai = s.getTheLoai().getIdTheLoai();
				Date namXuatBan = s.getNamXuatBan();
				String ISBN = s.getISBN();
				int soTrang = s.getSoTrang();
				String loaiSanPham = s.getIdLoaiSanPham().getIdLoaiSanPham();
				String nhaCungCap = s.getIdNhaCungCap().getIdNhaCungCap();
				double kichThuoc = s.getKichThuoc();
				String mauSac = s.getMauSac();
				String trangThai = s.getTrangThai().getDescription();
				double thue = s.thue();
				int soLuong = s.getSoLuong();
				double giaKM = s.getGiaKM();
				double giaBan = s.giaBan();
				modelSP.addRow(new Object[] { idSanPham, tenSanPham, tenTacGia, tenTheLoai, namXuatBan, ISBN, soTrang,
						loaiSanPham, nhaCungCap, kichThuoc, mauSac, trangThai, currencyFormat.format(thue), soLuong
						, currencyFormat.format(giaBan), currencyFormat.format(giaKM) });
			}
		} 
	}
	
	
	private void handleTimKiemSanPham(String cond) {
		if (!cond.equals("")) {
			modelSP.setRowCount(0);
			ArrayList<SanPhamCon> ds = daoQLSP.getDanhSachSanPhamTimKiem(cond);
			for (SanPhamCon sanPham : ds) {
				String idSanPham = sanPham.getIdSanPham();
				String tenSanPham = sanPham.getTenSanPham();
				String tenLoaiSanPham = sanPham.getIdLoaiSanPham().getIdLoaiSanPham();
				String tenNhaCungCap = sanPham.getIdNhaCungCap().getIdNhaCungCap();
				double kichThuoc = sanPham.getKichThuoc();
				String mauSac = sanPham.getMauSac();
				String trangThai = sanPham.getTrangThai()+"";
				double thue = sanPham.thue();
				int soLuong = sanPham.getSoLuong();
				double giaBan = sanPham.giaBan();
				double giaKM = sanPham.getGiaKM();
				modelSP.addRow(new Object[] { idSanPham, tenSanPham, tenLoaiSanPham, tenNhaCungCap, kichThuoc, mauSac,
						trangThai, currencyFormat.format(thue), soLuong, currencyFormat.format(giaBan),  currencyFormat.format(giaKM)});
			}
		} 
	}
	
	
	private void showDialogKhachHang() {
		dialogKhachHang = new JDialog();
		dialogKhachHang.setLayout(new BorderLayout());
		dialogKhachHang.setUndecorated(true);
		txtTimKiemKH = new JTextField(20);
		txtTimKiemKH.setFont(new Font("SansSerif", Font.PLAIN, 14));
		JPanel pnMain = new JPanel(new BorderLayout());
		JPanel pn1 = new JPanel(new BorderLayout());
		JPanel pnTop = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel pnLocTimKiem = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel pnTimKiem = new JPanel();
		JPanel pnTitle = new JPanel();
		JPanel pnBot = new JPanel(new BorderLayout());
		JPanel pnMaTen = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel pnChonLamMoi = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel pn2 = new JPanel(new BorderLayout());
		JPanel pnDong = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel pnMaKH = new JPanel();
		JPanel pnTenKH = new JPanel();
		JLabel lblTitle = new JLabel("Tìm kiếm khách hàng");
		JLabel lblTimKiem;
		JLabel lblMaKH;
		JLabel lblTenKH;
		
		lblTimKiem = new JLabel("Tìm kiếm theo Tên / Mã / SDT");
		lblTimKiem.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTitle.setForeground(new Color(26, 102, 227));
		lblMaKH = new JLabel("Mã khách hàng");
		lblMaKH.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblTenKH = new JLabel("Tên khách hàng");
		lblTenKH.setFont(new Font("SansSerif", Font.BOLD, 14));
		pnTimKiem.add(lblTimKiem);
		pnTimKiem.add(Box.createHorizontalStrut(10));
		pnTimKiem.add(txtTimKiemKH);
		pnLocTimKiem.add(pnTimKiem);
		pnTop.add(pnLocTimKiem, BorderLayout.CENTER);
		pnTop.setBorder(BorderFactory.createTitledBorder("Chức năng"));
		pnTitle.add(lblTitle);
		pn1.add(pnTop, BorderLayout.CENTER);
		pn1.add(pnTitle, BorderLayout.NORTH);
		pnMain.add(pn1, BorderLayout.NORTH);
		pnMain.setBorder(BorderFactory.createTitledBorder(""));
        modelKH.addColumn("ID Khách Hàng");
		modelKH.addColumn("Tên Khách Hàng");
		modelKH.addColumn("Số Điện Thoại");
		modelKH.addColumn("Email");
		modelKH.addColumn("Địa Chỉ");
		modelKH.addColumn("Ngày Sinh");
		tblkhachHang.setModel(modelKH);
		JScrollPane scrollTblKH = new JScrollPane(tblkhachHang);
		scrollTblKH.setBorder(BorderFactory.createTitledBorder("Khách hàng"));
		pnMain.add(scrollTblKH, BorderLayout.CENTER);
		
		pnMaKH.add(lblMaKH);
		pnMaKH.add(Box.createHorizontalStrut(20));
		pnMaKH.add(txtMaKH2);
		pnMaKH.add(Box.createHorizontalStrut(20));
		pnTenKH.add(lblTenKH);
		pnTenKH.add(Box.createHorizontalStrut(10));
		pnTenKH.add(txtTenKH2);
		pnMaTen.add(pnMaKH);
		pnMaTen.add(pnTenKH);
		pnBot.add(pnMaTen, BorderLayout.CENTER);
		pnChonLamMoi.add(btnChonKH);
		pnChonLamMoi.add(btnXemTatCaKhachHang);
		pnDong.add(btnDongKhachHang);
		pn2.add(pnChonLamMoi, BorderLayout.CENTER);
		pn2.add(pnDong, BorderLayout.EAST);
		pnBot.add(pn2, BorderLayout.SOUTH);
		pnMaTen.setBorder(BorderFactory.createTitledBorder("Mã & Tên"));
		pnChonLamMoi.setBorder(BorderFactory.createTitledBorder(""));
		pnDong.setBorder(BorderFactory.createTitledBorder(""));
		pnMain.add(pnBot, BorderLayout.SOUTH);
		
		 txtTimKiemKH.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
            	modelKH.setRowCount(0);
            	handleTimKiemKH(txtTimKiemKH.getText().trim());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            	modelKH.setRowCount(0);
            	handleTimKiemKH(txtTimKiemKH.getText().trim());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            	
            }
        });
		
		dialogKhachHang.add(pnMain);
		Dimension frameSize = this.getSize();
		int frameWidth = frameSize.width;
		int frameHeight = frameSize.height;
		dialogKhachHang.setSize(frameWidth, frameHeight);
		dialogKhachHang.setLocationRelativeTo(this);
		dialogKhachHang.setVisible(true);
		loadKhachHang();
	}
	
	private void showDialogSanPham() {
		dialogSanPham = new JDialog();
		dialogSanPham.setLayout(new BorderLayout());
		dialogSanPham.setUndecorated(true);
		txtTimKiemSP = new JTextField(20);
		txtTimKiemSP.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtMaSP2 = new JTextField(20);
		txtMaSP2.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtMaSP2.setEditable(false);
		spinnerSoLuong2 = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
		spinnerSoLuong2.setFont(new Font("SansSerif", Font.BOLD, 14));
		JPanel pnMain = new JPanel(new BorderLayout());
		JPanel pn1 = new JPanel(new BorderLayout());
		JPanel pnTop = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel pnLocTimKiem = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel pnLoc = new JPanel();
		JPanel pnTimKiem = new JPanel();
		JPanel pnTitle = new JPanel();
		JPanel pnBot = new JPanel(new BorderLayout());
		JPanel pnMaSoLuong = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel pnThemLamMoi = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel pn2 = new JPanel(new BorderLayout());
		JPanel pnDong = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel pnMa = new JPanel();
		JPanel pnSoLuong = new JPanel();
		JLabel lblTitle = new JLabel("Tìm kiếm sản phẩm");
		JLabel lblTimKiem;
		JLabel lblMaSP;
		JLabel lblSoLuong;
		JLabel lblTieuChiLoc;
		
		lblTimKiem = new JLabel("Tìm kiếm theo Tên / Mã / Loại");
		lblTimKiem.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTitle.setForeground(new Color(26, 102, 227));
		lblMaSP = new JLabel("Mã sản phẩm");
		lblMaSP.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblSoLuong = new JLabel("Số lượng");
		lblSoLuong.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblTieuChiLoc = new JLabel("Tiêu chí lọc");
		lblTieuChiLoc.setFont(new Font("SansSerif", Font.BOLD, 14));
		pnLoc.add(lblTieuChiLoc);
		pnLoc.add(Box.createHorizontalStrut(10));
		pnLoc.add(cbLocSanPham);
		pnLoc.add(Box.createHorizontalStrut(40));
		pnTimKiem.add(lblTimKiem);
		pnTimKiem.add(Box.createHorizontalStrut(10));
		pnTimKiem.add(txtTimKiemSP);
		pnLocTimKiem.add(pnLoc);
		pnLocTimKiem.add(pnTimKiem);
		pnTop.add(pnLocTimKiem, BorderLayout.CENTER);
		pnTop.setBorder(BorderFactory.createTitledBorder("Chức năng"));
		pnTitle.add(lblTitle);
		pn1.add(pnTop, BorderLayout.CENTER);
		pn1.add(pnTitle, BorderLayout.NORTH);
		pnMain.add(pn1, BorderLayout.NORTH);
		pnMain.setBorder(BorderFactory.createTitledBorder(""));
        handleLocSanPham();
        loadSanPham();
		tblSanPham.setModel(modelSP);
		JScrollPane scrollTblSP = new JScrollPane(tblSanPham);
		scrollTblSP.setBorder(BorderFactory.createTitledBorder("Sản phẩm"));
		pnMain.add(scrollTblSP, BorderLayout.CENTER);
		
		pnMa.add(lblMaSP);
		pnMa.add(Box.createHorizontalStrut(20));
		pnMa.add(txtMaSP2);
		pnMa.add(Box.createHorizontalStrut(20));
		pnSoLuong.add(lblSoLuong);
		pnSoLuong.add(Box.createHorizontalStrut(10));
		pnSoLuong.add(spinnerSoLuong2);
		pnMaSoLuong.add(pnMa);
		pnMaSoLuong.add(pnSoLuong);
		pnBot.add(pnMaSoLuong, BorderLayout.CENTER);
		pnThemLamMoi.add(btnThemGioHang2);
		pnThemLamMoi.add(btnXemTatCaSanPham);
		pnDong.add(btnDongSanPham);
		pn2.add(pnThemLamMoi, BorderLayout.CENTER);
		pn2.add(pnDong, BorderLayout.EAST);
		pnBot.add(pn2, BorderLayout.SOUTH);
		pnMaSoLuong.setBorder(BorderFactory.createTitledBorder("Số lượng"));
		pnThemLamMoi.setBorder(BorderFactory.createTitledBorder(""));
		pnDong.setBorder(BorderFactory.createTitledBorder(""));
		pnMain.add(pnBot, BorderLayout.SOUTH);
		
		txtTimKiemSP.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
            	if (!txtTimKiemSP.getText().equals("")) {
            		if (cbLocSanPham.getSelectedIndex() == 0) {
                		handleTimKiemSanPham(txtTimKiemSP.getText().trim());
                	} else {
                		handleTimKiemSach(txtTimKiemSP.getText().trim());
                	}
            	} else {
            		modelSP.setRowCount(0);
            		if (cbLocSanPham.getSelectedIndex() == 0) {
            			loadSanPham();
            		} else {
            			loadSach();
            		}
            	}
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            	if (!txtTimKiemSP.getText().equals("")) {
            		if (cbLocSanPham.getSelectedIndex() == 0) {
                		handleTimKiemSanPham(txtTimKiemSP.getText());
                	} else {
                		handleTimKiemSach(txtTimKiemSP.getText().trim());
                	}
            	} else {
            		modelSP.setRowCount(0);
            		if (cbLocSanPham.getSelectedIndex() == 0) {
            			loadSanPham();
            		} else {
            			loadSach();
            		}
            	}
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Không sử dụng cho JTextField
            }
		});
		
		dialogSanPham.add(pnMain);
		Dimension frameSize = this.getSize();
		int frameWidth = frameSize.width;
		int frameHeight = frameSize.height;
		dialogSanPham.setSize(frameWidth, frameHeight);
		dialogSanPham.setLocationRelativeTo(this);
		dialogSanPham.setVisible(true);
	}
	
	
	private void phatSinhMaHD() {
		String currentDate = new SimpleDateFormat("yyMMddHHmmss").format(new java.util.Date());
		txtMaHD.setText("HD" + currentDate);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int rowSP = tblSanPham.getSelectedRow();
		if (rowSP >= 0) {
			txtMaSP2.setText(modelSP.getValueAt(rowSP, 0).toString());
		} else {
			txtMaSP2.setText("");
		}
		
		int rowKH = tblkhachHang.getSelectedRow();
		if (rowKH >= 0) {
			txtMaKH2.setText(modelKH.getValueAt(rowKH, 0).toString());
			txtTenKH2.setText(modelKH.getValueAt(rowKH, 1).toString());
		} else {
			txtMaKH2.setText("");
			txtTenKH2.setText("");
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
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();	
		if (o.equals(btnThemGioHang)) {
			handleThemGioHangBangMa();
		} else if (o.equals(btnThemGioHang2)) {
			handleThemGioHangBangTimKiem();
		} else if (o.equals(cbLocSanPham)) {
			handleLocSanPham();
		} else if (o.equals(btnXoaGioHang)) {
			xoaGioHang();
		} else if (o.equals(btnCapNhatSoLuong)) {
			showDialogCapNhatSoLuong();
		} else if (o.equals(btnLamMoiGioHang)) {
			lamMoiGioHang();
		} else if (o.equals(btnLamMoiDonHang)) {
			handleLamMoiHoaDon();
		} else if (o.equals(btnTimKiemSanPham)) {
			showDialogSanPham();
		} else if (o.equals(btnTimKhachHang)) {
			showDialogKhachHang();
		} else if (o.equals(btnDongSanPham)) {
			dialogSanPham.dispose();
		} else if (o.equals(btnDongKhachHang)) {
			dialogKhachHang.dispose();
			modelKH.setColumnCount(0);
			modelKH.setRowCount(0);
		} else if (o.equals(btnChonKH)) {
			handleChonKhachHang();
		} else if (o.equals(btnChonMaSanPham)) {
			chonMaSanPham();
		} else if (o.equals(btnXacNhanCapNhat)) {
			int rowSelected = tblGioHang.getSelectedRow();
			xacNhanCapNhat(modelGioHang.getValueAt(rowSelected, 0).toString());
		} else if (o.equals(btnXemTatCaKhachHang)) {
			lamMoiKhachHang();
		} else if (o.equals(btnThanhToan)) {
			handLeThanhToan();
  		} else if (o.equals(btnLuuHoaDon)) {
  			handleLuuHoaDonChoVaCTHDC();
		} else if (o.equals(btnXoaHangCho)) {
			handleXoaHangCho();
		} else if (o.equals(btnLamMoiHangCho)) {
			lamMoiHangCho();
		} else if (o.equals(btnChonThanhToan)) {
			handleChonThanhToan();
		} else if (o.equals(btnTaoMaHoaDon)) {
			phatSinhMaHD();
		} else if (o.equals(btnXemTatCaSanPham)) {
			txtTimKiemSP.setText("");
		}
		
	}
}