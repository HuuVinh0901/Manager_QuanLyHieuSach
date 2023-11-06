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
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
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
	private JTextField txtSoLuong;
	private JTextField txtTimKiemSP;
	private JTextField txtMaHD;
	private JTextField txtTenNV;
	private JTextField txtTenKH;
	private JTextField txtTimKiemKH;
	private JTextField txtTongTien;
	private JTextField txtTienKhachDua;
	private JTextField txtThueVat;
	private JTextField txtTienTraKhach;
	private JTable tblSanPham;
	private JTable tblGioHang;
	private JTable tblkhachHang;
	private JButton btnThemGioHang;
	private JButton btnXoaGioHang;
	private JButton btnLamMoiGioHang;
	private JButton btnTaoMoiKH;
	private JButton btnLamMoiHD;
	private JButton btnThanhToan;
	private JDateChooser chooserNgayLap;
	private DefaultTableModel modelSP;
	private DefaultTableModel modelKH;
	private DAOKhachHang daoKhachHang;
	DefaultTableModel modelGioHang;
	private DAOQuanLySanPham daoQLSP;
	
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
		JPanel pnSanPham = new JPanel(new GridLayout(3, 3, 5, 5));
		JPanel pnHoaDon = new JPanel(new GridLayout(4, 4, 5, 5));
		JLabel lblMaSP = new JLabel("Mã sản phẩm");
		JLabel lblSoLuong = new JLabel("Số lượng");
		JLabel lblTimKiemSP = new JLabel("Tìm kiếm theo Tên / Mã / Loại");
		JLabel lblMaHD = new JLabel("Mã đơn hàng");
		JLabel lblTenNV = new JLabel("Tên nhân viên");
		JLabel lblTenKH = new JLabel("Tên khách hàng");
		JLabel lblTimKiemKH = new JLabel("Tìm kiếm theo Tên/ Mã / SDT");
		JPanel pnTblSP = new JPanel(new BorderLayout());
		JPanel pnGioHang = new JPanel(new BorderLayout());
		JPanel pnXoaLamMoi = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel pnThemGioHang = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel pnTaoMoiKH = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel pnTblKH = new JPanel(new BorderLayout());
		JLabel lblNgayLap = new JLabel("Ngày lập");
		JLabel lblTongTien = new JLabel("TỔNG TIỀN");
		JLabel lblTienKhachDua = new JLabel("Tiền khách đưa");
		JLabel lblThueVat = new JLabel("Thuế VAT");
		JLabel lblTienTraKhach = new JLabel("Tiền trả lại khách");
		JPanel pnTinhTien = new JPanel(new GridLayout(5, 2, 5, 5));
		JPanel pnFooterRight = new JPanel(new BorderLayout());
		JPanel pnLamMoiHD = new JPanel();
		JPanel pnLamThanhToan = new JPanel();
		daoKhachHang = new DAOKhachHang();
		daoQLSP = new DAOQuanLySanPham();
		txtMaSP = new JTextField(20);
		txtMaSP.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtMaSP.setEditable(false);
		txtSoLuong = new JTextField(20);
		txtSoLuong.setFont(new Font("SansSerif", Font.PLAIN, 14));
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
		txtThueVat = new JTextField(20);
		txtThueVat.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtThueVat.setEditable(false);
		txtThueVat.setText("50%");
		txtTienTraKhach = new JTextField(20);
		txtTienTraKhach.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtTienTraKhach.setEditable(false);
		lblTitleLeft.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTitleLeft.setForeground(new Color(26, 102, 227));
		lblTitleRight.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTitleRight.setForeground(new Color(26, 102, 227));
		lblMaSP.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblSoLuong.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblTimKiemSP.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblMaHD.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblTenNV.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblTenKH.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblTimKiemKH.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblNgayLap.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblTongTien.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblTienKhachDua.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblThueVat.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblTienTraKhach.setFont(new Font("SansSerif", Font.BOLD, 14));
		
		
		pnTitleLeft.add(lblTitleLeft);
		pnSanPham.add(lblMaSP);
		pnSanPham.add(txtMaSP);
		pnSanPham.add(lblSoLuong);
		pnSanPham.add(txtSoLuong);
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
		tblSanPham.setModel(modelSP);
		JScrollPane scrollTblSP = new JScrollPane(tblSanPham);
		scrollTblSP.setBorder(BorderFactory.createTitledBorder("Sản phẩm"));
		scrollTblSP.setPreferredSize(new Dimension(scrollTblSP.getPreferredSize().width, 375));
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
		scrollTblGH.setPreferredSize(new Dimension(scrollTblGH.getPreferredSize().width, 225));
		btnXoaGioHang = new JButton("Xoá");
		btnLamMoiGioHang = new JButton("Làm mới");
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
		scrollTblKH.setBorder(BorderFactory.createTitledBorder("Khách hàng"));
		btnTaoMoiKH = new JButton("Tạo mới khách hàng");
		pnTaoMoiKH.add(btnTaoMoiKH);
		pnTblKH.add(scrollTblKH, BorderLayout.CENTER);
		pnTblKH.add(pnTaoMoiKH, BorderLayout.SOUTH);
		
		
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
		pnTinhTien.add(lblThueVat);
		pnTinhTien.add(txtThueVat);
		pnTinhTien.add(lblTongTien);
		pnTinhTien.add(txtTongTien);
		pnTinhTien.add(lblTienKhachDua);
		pnTinhTien.add(txtTienKhachDua);
		pnTinhTien.add(lblTienTraKhach);
		pnTinhTien.add(txtTienTraKhach);
		pn3.add(pnTinhTien);
		btnLamMoiHD = new JButton("Làm mới");
		btnLamMoiHD.setPreferredSize(new Dimension(300, 25));
		pnLamMoiHD.add(btnLamMoiHD);
		pnFooterRight.add(pn3, BorderLayout.NORTH);
		pnFooterRight.add(pnLamMoiHD, BorderLayout.CENTER);
		btnThanhToan = new JButton("THANH TOÁN");
		btnThanhToan.setPreferredSize(new Dimension(400, 30));
		pnLamThanhToan.add(btnThanhToan);
		pnFooterRight.add(pnLamThanhToan, BorderLayout.SOUTH);
		pnRight.add(pnHeaderRight, BorderLayout.NORTH);
		pnRight.add(pnTblKH, BorderLayout.CENTER);
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
        btnLamMoiHD.addActionListener(this);
        btnThanhToan.addActionListener(this);
        tblGioHang.addMouseListener(this);
        tblSanPham.addMouseListener(this);
        tblkhachHang.addMouseListener(this);
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
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            	modelKH.setRowCount(0);
            	handleTimKiemKH(txtTimKiemKH.getText());
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
		
        
        
	}
	
	private void lamMoiHD() {
//		txtMaHD.setText("");
//		txtTenKH.setText("");
//		txtTongTien.setText("0.0");
//		chooserNgayLap.setDate();
		
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
						sp.getGiaNhap()+""
						 };
				modelSP.addRow(row);
			}
			txtSoLuong.setEditable(true);
			
		} else {
			txtSoLuong.setText("");
			txtSoLuong.setEditable(false);
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
						int soLuong = Integer.parseInt(modelGioHang.getValueAt(i, 3).toString()) + Integer.parseInt(txtSoLuong.getText());
						double thanhTien = Double.parseDouble(modelGioHang.getValueAt(i, 2).toString()) * soLuong;
						modelGioHang.setValueAt(String.valueOf(soLuong), i, 3);
						modelGioHang.setValueAt(String.valueOf(thanhTien), i, 4);
						tinhTongTien();
					}
				}
			} else {
				String ma = modelSP.getValueAt(rowSelected, 0).toString();
				String ten = modelSP.getValueAt(rowSelected, 1).toString();
				String giaBan = modelSP.getValueAt(rowSelected, 3).toString();
				String soLuong = txtSoLuong.getText().toString();
				String thanhTien = String.valueOf(Double.parseDouble(soLuong) * Double.parseDouble(giaBan));
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
			txtSoLuong.setText(String.valueOf(1));
		}
		
		int rowKH = tblkhachHang.getSelectedRow();
		if (rowKH >= 0) {
			txtTenKH.setText(modelKH.getValueAt(rowKH, 1).toString());
			phatSinhMaHD();
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
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();	
		if (o.equals(btnThemGioHang)) {
			themGioHang();
		} else if (o.equals(btnXoaGioHang)) {
			xoaGioHang();
		} else if (o.equals(btnLamMoiGioHang)) {
			lamMoiGioHang();
		} else if (o.equals(btnTaoMoiKH)) {
			
		} else if (o.equals(btnLamMoiHD)) {
			
		} else if (o.equals(btnThanhToan)) {
			
		}
		
	}


	
}