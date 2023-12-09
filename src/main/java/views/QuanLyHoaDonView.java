package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import dao.DAOKhachHang;
import dao.DAONhanVien;
import dao.DAOQuanLySanPham;
import dao.DAOSach;
import dao.DAO_QuanLyBanHang;
import models.ChiTietHoaDon;
import models.HoaDon;
import models.KhachHang;
import models.NhanVien;
import models.SachCon;
import models.SanPhamCha;

public class QuanLyHoaDonView extends JPanel{
	private JTextField txtTimKiem;
	private JButton btnXemTatCa;
	private JTable tblHoaDon;
	private DefaultTableModel modelHoaDon;
	private DAO_QuanLyBanHang daoBanHang;
	private DAONhanVien daoNhanVien;
	private DAOQuanLySanPham daoSanPham;
	private DAOSach daoSach;
	private DAOKhachHang daoKhachHang;
	private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
	
	public QuanLyHoaDonView() {
		setLayout(new BorderLayout());
		currencyFormat.setCurrency(Currency.getInstance("VND"));
		JPanel pnTitle = new JPanel();
		JPanel pnTimKiem = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel pnXemTatCa = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel pnLeft = new JPanel();
		JPanel pnTimKiemXemTatCa = new JPanel(new BorderLayout());
		JPanel pnContent = new JPanel(new GridBagLayout());
		JPanel pnHeader = new JPanel();
		JPanel pnHeader2 = new JPanel();
		JPanel pn1 = new JPanel(new GridLayout(2, 1, 5, 5));
		JPanel pn2 = new JPanel();
		JLabel lblTitle = new JLabel("QUẢN LÝ HOÁ ĐƠN");
		JLabel lblTimKiem = new JLabel("Tìm kiếm theo mã hoá đơn / SĐT / mã khách hàng");
		txtTimKiem = new JTextField(16);
		btnXemTatCa = new JButton("Xem tất cả");
		pnTitle.add(lblTitle);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTitle.setForeground(new Color(26, 102, 227));
		pn2.add(pnTitle);
		pnHeader.add(pn2);
		pnHeader2.add(pnHeader);
		pn1.add(lblTimKiem);
		lblTimKiem.setFont(new Font("Tahoma", Font.BOLD, 14));
		pn1.add(txtTimKiem);
		txtTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pnTimKiem.add(pn1);
		pnXemTatCa.add(btnXemTatCa);
		pnTimKiemXemTatCa.add(pnTimKiem, BorderLayout.CENTER);
		pnTimKiemXemTatCa.add(pnXemTatCa, BorderLayout.SOUTH);
		pnLeft.add(pnTimKiemXemTatCa);
		pnLeft.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));
		daoBanHang = new DAO_QuanLyBanHang();
		daoNhanVien = new DAONhanVien();
		daoSanPham = new DAOQuanLySanPham();
		daoKhachHang = new DAOKhachHang();
		daoSach= new DAOSach();
		
		tblHoaDon = new JTable();
		modelHoaDon = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Chỉ cho phép chỉnh sửa cột "Xem chi tiết"
                return column == 6;
            }
        };
        
        txtTimKiem.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
            	handleTimKiemHoaDon(txtTimKiem.getText().trim());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            	handleTimKiemHoaDon(txtTimKiem.getText().trim());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Không sử dụng cho JTextField
            }
        });
        
        btnXemTatCa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadHoaDon();
			}
		});
        
        
        modelHoaDon.addColumn("Mã hoá đơn");
        modelHoaDon.addColumn("Ngày lập");
        modelHoaDon.addColumn("Tên khách hàng");
        modelHoaDon.addColumn("Tên nhân viên");
        modelHoaDon.addColumn("Tiền khách đưa");
        modelHoaDon.addColumn("Tổng tiền");
        modelHoaDon.addColumn("Xem chi tiết");
        tblHoaDon.setModel(modelHoaDon);
		JScrollPane scrollTblHD = new JScrollPane(tblHoaDon);
		scrollTblHD.setBorder(BorderFactory.createTitledBorder("Thông tin hoá đơn"));
		pnLeft.setBorder(BorderFactory.createTitledBorder("Chức năng tìm kiếm"));
		
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        pnContent.add(pnLeft, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.7;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        pnContent.add(scrollTblHD, gbc);
        
		
		// Đặt sự kiện cho cột "Xem chi tiết"
		tblHoaDon.getColumn("Xem chi tiết").setCellRenderer(new ButtonRenderer());
		tblHoaDon.getColumn("Xem chi tiết").setCellEditor(new ButtonEditor());
        
        add(pnHeader2, BorderLayout.NORTH);
		add(pnContent, BorderLayout.CENTER);
		
		
		loadHoaDon();
	}
	
	private class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    private class ButtonEditor extends DefaultCellEditor {
        private JButton button;

        public ButtonEditor() {
            super(new JCheckBox());
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {	
				@Override
				public void actionPerformed(ActionEvent e) {
					int Row = tblHoaDon.convertRowIndexToModel(tblHoaDon.getEditingRow());
                    String idHoaDon = (String) modelHoaDon.getValueAt(Row, 0);
                    HoaDon hd = daoBanHang.getHoaDonTheoID(idHoaDon);
                    showDetail(idHoaDon, hd.getNhanVien().getId(), hd.getKhachHang().getIdKhachHang());
				}
			});
        }

        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            button.setText((value == null) ? "" : value.toString());
            return button;
        }

        public Object getCellEditorValue() {
            return button.getText();
        }

        private void showDetail(String idHoaDon, String idNhanVien, String idKhachHang) {
            JDialog dialog = new JDialog();
            dialog.setTitle("Chi tiết hoá đơn");
            dialog.setModalityType(dialog.getModalityType().APPLICATION_MODAL); 
            dialog.setLayout(new BorderLayout());     
            JPanel pnTitle = new JPanel();
            JLabel lblTitle = new JLabel(idHoaDon);
            lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
            lblTitle.setForeground(new Color(26, 102, 227));
            pnTitle.add(lblTitle);
            JPanel pnInfo = new JPanel(new GridLayout(6, 1, 5, 5));
            JPanel pnInfo2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JPanel pnInfo3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
            NhanVien nv = daoNhanVien.getNhanVien(idNhanVien);
            KhachHang kh = daoKhachHang.getKhachHang(idKhachHang);
            HoaDon hd = daoBanHang.getHoaDonTheoID(idHoaDon);
            JLabel lblTenNV = new JLabel("Tên nhân viên : " + nv.getTen());
            JLabel lblMaNV = new JLabel("Mã nhân viên : " + nv.getId());
            JLabel lblTenKH = new JLabel("Tên khách hàng : " + kh.getTenKhachHang());
            JLabel lblMaKH = new JLabel("Mã khách hàng : " + kh.getIdKhachHang());
            JLabel lblSDT = new JLabel("Số điện thoại : " + kh.getSdt());
            JLabel lblNgayLap = new JLabel("Ngày lập : " + hd.getNgayLap());
            JLabel lblTongTien = new JLabel("TỔNG TIỀN : " + currencyFormat.format(hd.getTongTien()));
            JLabel lblTienKhachDua = new JLabel("Tiền khách đưa : " + currencyFormat.format(hd.getTienKhachDua()));
            pnInfo.add(lblTenNV);
            pnInfo.add(lblMaNV);
            pnInfo.add(lblTenKH);
            pnInfo.add(lblMaKH);
            pnInfo.add(lblSDT);
            pnInfo.add(lblNgayLap);
            pnInfo2.add(pnInfo);
            pnInfo3.add(pnInfo2);
            pnInfo3.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            JPanel pnHeader = new JPanel(new BorderLayout());
            pnHeader.add(pnTitle, BorderLayout.NORTH);
            pnHeader.add(pnInfo3, BorderLayout.CENTER);
            
            JTable tblChiTietHoaDon = new JTable();
            DefaultTableModel modelChiTiet = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            modelChiTiet.addColumn("Mã sản phẩm");
            modelChiTiet.addColumn("Tên sản phẩm");
            modelChiTiet.addColumn("Số lượng");
            modelChiTiet.addColumn("Thành tiền");
            tblChiTietHoaDon.setModel(modelChiTiet);
    		JScrollPane scrollTblCTHD = new JScrollPane(tblChiTietHoaDon);
    		scrollTblCTHD.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
    		tblChiTietHoaDon.setPreferredScrollableViewportSize(new Dimension(500, 300));
    		
    		for (ChiTietHoaDon cthd : daoBanHang.getChiTietHoaDonSachTheoId(idHoaDon)) {
    			String idSP = cthd.getSanPham().getIdSanPham();
    			SachCon s = daoSach.getSach(idSP);
    			String tenSP = s.getTenSanPham();
    			String soLuong = String.valueOf(cthd.getSoLuong());
    			String thanhTien = currencyFormat.format(cthd.getThanhTien());
    			modelChiTiet.addRow(new String[] {idSP, tenSP, soLuong, thanhTien});
    		}
    		for (ChiTietHoaDon cthd : daoBanHang.getChiTietHoaDonSanPhamTheoId(idHoaDon)) {
    			String idSP = cthd.getSanPham().getIdSanPham();
    			SanPhamCha sp = daoSanPham.getSanPham(idSP);
    			String tenSP = sp.getTenSanPham();
    			String soLuong = String.valueOf(cthd.getSoLuong());
    			String thanhTien = currencyFormat.format(cthd.getThanhTien());
    			modelChiTiet.addRow(new String[] {idSP, tenSP, soLuong, thanhTien});
    		}
            
    		JPanel pnFooter = new JPanel(new GridLayout(2, 1, 5, 5) );
    		JPanel pnFooter1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    		lblTongTien.setFont(new Font("Tahoma", Font.BOLD, 15));
    		lblTongTien.setForeground(Color.RED);
    		pnFooter.add(lblTienKhachDua);
    		pnFooter.add(lblTongTien);
    		pnFooter1.add(pnFooter);

            dialog.add(pnHeader, BorderLayout.NORTH);
            dialog.add(scrollTblCTHD, BorderLayout.CENTER);
            dialog.add(pnFooter1, BorderLayout.SOUTH);
            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        }
        
        public boolean stopCellEditing() {
            fireEditingStopped(); // Thông báo rằng việc chỉnh sửa ô đã kết thúc
            return super.stopCellEditing();
        }
    }
    
   public void loadHoaDon() {
	    modelHoaDon.setRowCount(0);
		ArrayList<HoaDon> dsHoaDon = daoBanHang.getAllHoaDon();
		for (HoaDon hd : dsHoaDon) {
			String maHD = hd.getIdDonHang();
			String ngayLap = new SimpleDateFormat("dd/MM/yyyy").format(hd.getNgayLap());
			String maKH = hd.getKhachHang().getIdKhachHang();
			KhachHang kh = daoKhachHang.getKhachHang(maKH);
			String maNV = hd.getNhanVien().getId();
			NhanVien nv = daoNhanVien.getNhanVien(maNV);
			String tienKhachDua = currencyFormat.format(hd.getTienKhachDua());
			String tongTien = currencyFormat.format(hd.getTongTien());
			modelHoaDon.addRow(new String[] {maHD, ngayLap, kh.getTenKhachHang(), nv.getTen(), tienKhachDua, tongTien, "Xem"});
			
		}
   }
   
   private void handleTimKiemHoaDon(String cond) {
		if (!cond.equals("")) {
			modelHoaDon.setRowCount(0);
			for (HoaDon hd : daoBanHang.getHoaDonTimKiem(cond)) {
				String maHD = hd.getIdDonHang();
				String ngayLap = new SimpleDateFormat("dd/MM/yyyy").format(hd.getNgayLap());
				String maKH = hd.getKhachHang().getIdKhachHang();
				KhachHang kh = daoKhachHang.getKhachHang(maKH);
				String maNV = hd.getNhanVien().getId();
				NhanVien nv = daoNhanVien.getNhanVien(maNV);
				String tienKhachDua = currencyFormat.format(hd.getTienKhachDua());
				String tongTien = currencyFormat.format(hd.getTongTien());
				modelHoaDon.addRow(new String[] {maHD, ngayLap, kh.getTenKhachHang(), nv.getTen(), tienKhachDua, tongTien, "Xem"});
			}
		} else {
			loadHoaDon();
		}
	}
	
}





