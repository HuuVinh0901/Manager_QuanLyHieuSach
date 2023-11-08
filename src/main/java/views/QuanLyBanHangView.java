package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;


import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;
import connection.ConnectDB;
import dao.DAOKhachHang;
import dao.DAOQuanLySanPham;
import models.KhachHang;
import models.SanPhamCha;

	


public class QuanLyBanHangView extends JPanel implements ActionListener, MouseListener{
	private JPanel pnLeft;
	private JPanel pnRight;
	private JTextField txtMaSP;
	private JTextField txtTimKiemSP;
	private JTextField txtMaHD;
	private JTextField txtTenNV;
	private JTextField txtTenKH;
	private JTextField txtMaKH;
	private JTextField txtTimKiemKH;
	private JTextField txtTongTien;
	private JTextField txtTienKhachDua;
	private JTextField txtTienTraKhach;
	private JTable tblSanPham;
	private JTable tblGioHang;
	private JTable tblkhachHang;
	private JTable tblHangCho;
	private JButton btnThemGioHang;
	private JButton btnXoaGioHang;
	private JButton btnLamMoiGioHang;
	private JButton btnTaoMoiKH;
	private JButton btnThemHangCho;
	private JButton btnXoaHangCho;
	private JButton btnChonThanhToan;
	private JButton btnLamMoiHangCho;
	private JButton btnThanhToan;
	private JButton btnTaoDonHang;
	private JButton btnLamMoiDonHang;
	private JButton btnChonKH;
	private JDateChooser chooserNgayLap;
	private DefaultTableModel modelSP;
	private DefaultTableModel modelKH;
	private DefaultTableModel modelGioHang;
	private DefaultTableModel modelHangCho;
	private DAOKhachHang daoKhachHang;
	private DAOQuanLySanPham daoQLSP;
	private JSpinner spinnerSoLuong;
	private JWindow windowKhachHang;
	private JLabel lblTimKiemKH;
	
	public QuanLyBanHangView() {
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
		JPanel pn4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel pnSanPham = new JPanel(new GridLayout(3, 3, 5, 5));
		JPanel pnHoaDon = new JPanel(new GridLayout(5, 5, 5, 5));
		JLabel lblMaSP = new JLabel("Mã sản phẩm");
		JLabel lblSoLuong = new JLabel("Số lượng");
		JLabel lblTimKiemSP = new JLabel("Tìm kiếm theo Tên / Mã / Loại");
		JLabel lblMaHD = new JLabel("Mã đơn hàng");
		JLabel lblTenNV = new JLabel("Tên nhân viên");
		JLabel lblTenKH = new JLabel("Tên khách hàng");
		JLabel lblMaKH = new JLabel("Mã khách hàng");
		JPanel pnTblSP = new JPanel(new BorderLayout());
		JPanel pnGioHang = new JPanel(new BorderLayout());
		JPanel pnXoaLamMoi = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel pnThemGioHang = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel pnChucNangHangCho = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel pnTblHangCho = new JPanel(new BorderLayout());
		JLabel lblNgayLap = new JLabel("Ngày lập");
		JLabel lblTongTien = new JLabel("TỔNG TIỀN");
		JLabel lblTienKhachDua = new JLabel("Tiền khách đưa");
		JLabel lblTienTraKhach = new JLabel("Tiền trả lại khách");
		JPanel pnTinhTien = new JPanel(new GridLayout(4, 2, 5, 5));
		JPanel pnFooterRight = new JPanel(new BorderLayout());
		JPanel pnLamMoiHD = new JPanel();
		JPanel pnLamThanhToan = new JPanel();
		daoKhachHang = new DAOKhachHang();
		daoQLSP = new DAOQuanLySanPham();
		txtMaSP = new JTextField(20);
		txtMaSP.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtMaSP.setEditable(false);
		txtTimKiemSP = new JTextField(20);
		txtTimKiemSP.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtMaHD = new JTextField(20);
		txtMaHD.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtMaHD.setEditable(false);
		txtTenNV = new JTextField(20);
		txtTenNV.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtTenNV.setEditable(false);
		txtTenNV.setText("Trần Vũ Duy");
		txtTenKH = new JTextField(20);
		txtTenKH.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtTenKH.setEditable(false);
		txtTimKiemKH = new JTextField(20);
		txtTimKiemKH.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtTongTien = new JTextField(20);
		txtTongTien.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtTongTien.setEditable(false);
		txtTongTien.setText("0.0");
		txtTienKhachDua = new JTextField(20);
		txtTienKhachDua.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtTienTraKhach = new JTextField(20);
		txtTienTraKhach.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtTienTraKhach.setEditable(false);
		txtMaKH = new JTextField(20);
		txtMaKH.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtMaKH.setEditable(false);
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
		spinnerSoLuong = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
		spinnerSoLuong.setFont(new Font("SansSerif", Font.BOLD, 14));
		
		pnTitleLeft.add(lblTitleLeft);
		pnSanPham.add(lblMaSP);
		pnSanPham.add(txtMaSP);
		pnSanPham.add(lblSoLuong);
		pnSanPham.add(spinnerSoLuong);
		pnSanPham.add(lblTimKiemSP);
		pnSanPham.add(txtTimKiemSP);
		pn1.add(pnSanPham);
		
		tblSanPham = new JTable();
		
		modelSP = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không cho phép chỉnh sửa các ô trong bảng
            }
        };
		modelSP.addColumn("Mã sản phẩm");
		modelSP.addColumn("Tên sản phẩm");
		modelSP.addColumn("Loại sản phẩm");
		modelSP.addColumn("Giá bán");
		modelSP.addColumn("Giá khuyến mãi");
		tblSanPham.setModel(modelSP);
		JScrollPane scrollTblSP = new JScrollPane(tblSanPham);
		scrollTblSP.setBorder(BorderFactory.createTitledBorder("Sản phẩm"));
		btnThemGioHang = new JButton("Thêm vào giỏ hàng");
		pnTblSP.add(scrollTblSP, BorderLayout.CENTER);
		pnThemGioHang.add(btnThemGioHang);
		pnTblSP.add(pnThemGioHang, BorderLayout.SOUTH);
		pnHeaderLeft.add(pnTitleLeft, BorderLayout.NORTH);
		pnHeaderLeft.add(pn1, BorderLayout.CENTER);
		
		
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
		modelGioHang.addColumn("Số lượng");
		modelGioHang.addColumn("Thành tiền");
		tblGioHang.setModel(modelGioHang);
		JScrollPane scrollTblGH = new JScrollPane(tblGioHang);
		scrollTblGH.setBorder(BorderFactory.createTitledBorder("Giỏ hàng"));
		scrollTblGH.setPreferredSize(new Dimension(scrollTblGH.getPreferredSize().width, 170));
		btnXoaGioHang = new JButton("Xoá");
		btnLamMoiGioHang = new JButton("Làm mới giỏ hàng");
		pnXoaLamMoi.add(btnXoaGioHang);
		pnXoaLamMoi.add(btnLamMoiGioHang);
		pnGioHang.add(scrollTblGH, BorderLayout.CENTER);
		pnGioHang.add(pnXoaLamMoi, BorderLayout.SOUTH);
		pnLeft.add(pnHeaderLeft, BorderLayout.NORTH);
		pnLeft.add(pnTblSP, BorderLayout.CENTER);
		pnLeft.add(pnGioHang, BorderLayout.SOUTH);
		pnLeft.setBorder(BorderFactory.createTitledBorder(""));
		
		
		pnTitleRight.add(lblTitleRight);
		pnHoaDon.add(lblMaHD);
		pnHoaDon.add(txtMaHD);
		pnHoaDon.add(lblTenNV);
		pnHoaDon.add(txtTenNV);
		pnHoaDon.add(lblTenKH);
		pnHoaDon.add(txtTenKH);
		pnHoaDon.add(lblMaKH);
		pnHoaDon.add(txtMaKH);
		lblTimKiemKH = new JLabel("Tìm kiếm theo Tên/ Mã / SDT");
		lblTimKiemKH.setFont(new Font("SansSerif", Font.BOLD, 14));
		pnHoaDon.add(lblTimKiemKH);
		pnHoaDon.add(txtTimKiemKH);
		pn2.add(pnHoaDon);
		pnHeaderRight.add(pnTitleRight, BorderLayout.NORTH);
		pnHeaderRight.add(pn2, BorderLayout.CENTER);
		
		
		tblkhachHang = new JTable();
		modelKH = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không cho phép chỉnh sửa các ô trong bảng
            }
        };
		modelKH.addColumn("Mã khách hàng");
		modelKH.addColumn("Tên khách hàng");
		modelKH.addColumn("Số điện thoại");
		tblkhachHang.setModel(modelKH);
		JScrollPane scrollTblKH = new JScrollPane(tblkhachHang);
		btnTaoMoiKH = new JButton("Tạo mới khách hàng");
		btnChonKH = new JButton("Chọn khách hàng");
		pn4.add(btnChonKH);
		pn4.add(btnTaoMoiKH);
		JPanel pnKhachHang = new JPanel(new BorderLayout());
		pnKhachHang.add(scrollTblKH, BorderLayout.CENTER);
		pnKhachHang.add(pn4, BorderLayout.SOUTH);
		
        windowKhachHang = new JWindow();
        windowKhachHang.add(pnKhachHang);
        
		
		tblHangCho = new JTable();
		modelHangCho = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không cho phép chỉnh sửa các ô trong bảng
            }
        };
        modelHangCho.addColumn("Mã hoá đơn");
        modelHangCho.addColumn("Tên khách hàng");
        modelHangCho.addColumn("Mã khách hàng");
        modelHangCho.addColumn("Số điện thoại");
		tblHangCho.setModel(modelHangCho);
		JScrollPane scrollTblHangDoi = new JScrollPane(tblHangCho);
		scrollTblHangDoi.setBorder(BorderFactory.createTitledBorder("Đơn hàng chờ thanh toán"));
		btnThemHangCho = new JButton("Lưu");
		btnXoaHangCho = new JButton("Xoá");
		btnChonThanhToan = new JButton("Chọn");
		btnLamMoiHangCho = new JButton("Làm mới hàng chờ");
		pnChucNangHangCho.add(btnChonThanhToan);
		pnChucNangHangCho.add(btnThemHangCho);
		pnChucNangHangCho.add(btnXoaHangCho);
		pnChucNangHangCho.add(btnLamMoiHangCho);
		pnTblHangCho.add(scrollTblHangDoi, BorderLayout.CENTER);
		pnTblHangCho.add(pnChucNangHangCho, BorderLayout.SOUTH);
		
		chooserNgayLap = new JDateChooser();
		chooserNgayLap.getCalendarButton().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chooserNgayLap.setDateFormatString("dd/MM/yyyy");	
		chooserNgayLap.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		chooserNgayLap.setFont(new Font("SansSerif", Font.PLAIN, 14));
		chooserNgayLap.getCalendarButton().setPreferredSize(new Dimension(20, 24));
		chooserNgayLap.getCalendarButton().setBackground(new Color(102, 0, 153));
		chooserNgayLap.getCalendarButton().setToolTipText("Chọn ngày sinh");
		pnTinhTien.add(lblNgayLap);
		pnTinhTien.add(chooserNgayLap); 
		pnTinhTien.add(lblTongTien);
		pnTinhTien.add(txtTongTien);
		pnTinhTien.add(lblTienKhachDua);
		pnTinhTien.add(txtTienKhachDua);
		pnTinhTien.add(lblTienTraKhach);
		pnTinhTien.add(txtTienTraKhach);
		pn3.add(pnTinhTien);
		pnFooterRight.add(pn3, BorderLayout.NORTH);
		pnFooterRight.add(pnLamMoiHD, BorderLayout.CENTER);
		btnThanhToan = new JButton("THANH TOÁN & IN");
		btnThanhToan.setPreferredSize(new Dimension(200, 35));
		btnTaoDonHang = new JButton("Tạo mới");
		btnLamMoiDonHang = new JButton("Làm mới");
		pnLamThanhToan.add(btnTaoDonHang);
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
        gbc.weightx = 0.5;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(pnLeft, gbc);

        
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(pnRight, gbc);
        
        btnThemGioHang.addActionListener(this);
        btnXoaGioHang.addActionListener(this);
        btnLamMoiGioHang.addActionListener(this);
        btnTaoMoiKH.addActionListener(this);
        btnThanhToan.addActionListener(this);
        tblGioHang.addMouseListener(this);
        tblSanPham.addMouseListener(this);
        tblkhachHang.addMouseListener(this);
        btnChonKH.addActionListener(this);
        txtTienKhachDua.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
            	tinhTienTraKhach();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            	tinhTienTraKhach();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Không sử dụng cho JTextField
            }
        });
        
        txtTimKiemSP.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
            	modelSP.setRowCount(0);
            	handleTimKiemSP(txtTimKiemSP.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            	modelSP.setRowCount(0);
            	handleTimKiemSP(txtTimKiemSP.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Không sử dụng cho JTextField
            }
        });
        
        
        txtTimKiemKH.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
            	modelKH.setRowCount(0);
            	handleTimKiemKH(txtTimKiemKH.getText());
            	if (!txtTimKiemKH.getText().equals("")) {
            		 windowKhachHang.setLocation(lblTimKiemKH.getLocationOnScreen().x - 50, lblTimKiemKH.getLocationOnScreen().y + lblTimKiemKH.getHeight());
            		 windowKhachHang.pack();
                     windowKhachHang.setVisible(true);
            	} else {
            		windowKhachHang.setVisible(false);
            	}
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            	modelKH.setRowCount(0);
            	handleTimKiemKH(txtTimKiemKH.getText());
            	if (!txtTimKiemKH.getText().equals("")) {
            		windowKhachHang.setLocation(lblTimKiemKH.getLocationOnScreen().x - 50, lblTimKiemKH.getLocationOnScreen().y + lblTimKiemKH.getHeight());
           		 	windowKhachHang.pack();
                    windowKhachHang.setVisible(true);
            	} else {
            		windowKhachHang.setVisible(false);
            	}
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            	
            }
        });
        
        try {
			ConnectDB.getinstance().connect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	

	private void tinhTienTraKhach() {
		Double tienDua = Double.parseDouble(txtTienKhachDua.getText().toString());
		Double tongTien = Double.parseDouble(txtTongTien.getText().toString());
		Double tienTra = 0.0;
		if (tienDua > tongTien) {
			tienTra = tienDua - tongTien ;
			txtTienTraKhach.setText(String.valueOf(tienTra));
		} else {
			txtTienTraKhach.setText(String.valueOf(tienTra));
		}
	}
	
	private void tinhTongTien() {
		int rowCountGioHang = modelGioHang.getRowCount();
		Double tongTien = 0.0;
		if (rowCountGioHang > 0) {
			for (int i = 0; i < rowCountGioHang; i++) {
				tongTien += Double.parseDouble(modelGioHang.getValueAt(i, 4).toString());
			}
			tongTien = tongTien + (tongTien * 0.5);
		}
		txtTongTien.setText(String.valueOf(tongTien));
		
	}
	
	private void handleTimKiemSP(String cond) {
		if (!cond.equals("")) {
			for (SanPhamCha sp : daoQLSP.getSanPhamTimKiem(cond)) {
				String tenLoaiSanPham = sp.getIdLoaiSanPham().getIdLoaiSanPham();
				String[] row = { sp.getIdSanPham(), sp.getTenSanPham(), tenLoaiSanPham,
						sp.getGiaNhap()+"", sp.getGiaNhap()+"" };
				modelSP.addRow(row);
			}
			
		} else {
			txtMaSP.setText("");
		}
	}
	
	private void handleTimKiemKH(String cond) {
		if (!cond.equals("")) {
			for (KhachHang kh : daoKhachHang.getKhachHangTimKiem(cond)) {
				
				String[] row = { kh.getIdKhachHang(), kh.getTenKhachHang(), kh.getSdt()};
				modelKH.addRow(row);
			}
		}
	}
	
	
	private void themGioHang() {
		int rowSelected = tblSanPham.getSelectedRow();
		if (rowSelected >= 0) {
			Boolean exist = false;
			for (int i = 0; i < modelGioHang.getRowCount(); i++) {
				if (modelSP.getValueAt(rowSelected, 0).equals(modelGioHang.getValueAt(i, 0))) {
					exist = true;
					break;
				}
			}
			if (exist) {
				for (int i = 0; i < modelGioHang.getRowCount(); i++) {
					if (modelGioHang.getValueAt(i, 0).equals(modelSP.getValueAt(rowSelected, 0))) {
						int soLuong = Integer.parseInt(modelGioHang.getValueAt(i, 3).toString()) + Integer.parseInt(spinnerSoLuong.getValue().toString());
						double thanhTien = Double.parseDouble(modelGioHang.getValueAt(i, 2).toString()) * soLuong;
						modelGioHang.setValueAt(String.valueOf(soLuong), i, 3);
						modelGioHang.setValueAt(String.valueOf(thanhTien), i, 4);
						tinhTongTien();
					}
				}
			} else {
				String ma = modelSP.getValueAt(rowSelected, 0).toString();
				String ten = modelSP.getValueAt(rowSelected, 1).toString();
				String giaBan = modelSP.getValueAt(rowSelected, 4).toString();
				String soLuong = spinnerSoLuong.getValue().toString();
				String thanhTien = String.valueOf(Integer.parseInt(soLuong) * Double.parseDouble(giaBan));
				String[] row = {ma, ten, giaBan, soLuong, thanhTien};
				modelGioHang.addRow(row);
				tinhTongTien();
			}
		} else {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm muốn thêm giỏ hàng");
		}
	}

	
	private void lamMoiGioHang() {
		modelGioHang.setRowCount(0);
		tinhTongTien();
	}
	
	private void xoaGioHang() {
		int row = tblGioHang.getSelectedRow();
		if (row < 0) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng muốn xoá");
		} else {
			int hoiNhac = JOptionPane.showConfirmDialog(this, "Bạn có chắc xóa không!", "Cảnh báo",
					JOptionPane.YES_NO_OPTION);
			if (hoiNhac == JOptionPane.YES_OPTION) {
				modelGioHang.removeRow(row);
				tinhTongTien();
			}
		}
	}
	
	private void phatSinhMaHD() {
		String currentDate = new SimpleDateFormat("yyMMddHHmmss").format(new java.util.Date());
		txtMaHD.setText("HD" + currentDate);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int row = tblSanPham.getSelectedRow();
		if (row >= 0) {
			txtMaSP.setText(modelSP.getValueAt(row, 0).toString());
			spinnerSoLuong.setValue(1);
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
			themGioHang();
		} else if (o.equals(btnXoaGioHang)) {
			xoaGioHang();
		} else if (o.equals(btnLamMoiGioHang)) {
			lamMoiGioHang();
		} else if (o.equals(btnTaoMoiKH)) {
			
		} else if (o.equals(btnThanhToan)) {
			
		} else if (o.equals(btnThemHangCho)) {
			
		} else if (o.equals(btnXoaGioHang)) {
			
		} else if (o.equals(btnLamMoiGioHang)) {
			
		} else if (o.equals(btnChonThanhToan)) {
			
		} else if (o.equals(btnChonKH)) {
			int rowKH = tblkhachHang.getSelectedRow();
			if (rowKH >= 0) {
				txtMaKH.setText(modelKH.getValueAt(rowKH, 0).toString());
				txtTenKH.setText(modelKH.getValueAt(rowKH, 1).toString());
				phatSinhMaHD();
				windowKhachHang.setVisible(false);
			} else {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng");
				 windowKhachHang.setLocation(lblTimKiemKH.getLocationOnScreen().x - 50, lblTimKiemKH.getLocationOnScreen().y + lblTimKiemKH.getHeight());
        		 windowKhachHang.pack();
                 windowKhachHang.setVisible(true);
			}
		}
		
	}


	
}