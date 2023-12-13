package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.event.TableModelListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import javax.swing.table.DefaultTableModel;

import javax.swing.table.TableRowSorter;


import com.toedter.calendar.JDateChooser;

import connection.ConnectDB;
import dao.DAOKhuyenMai;
import dao.DAOQuanLySanPham;
import dao.DAOSach;
import models.KhuyenMai;
import models.SachCon;
import models.SachKhuyenMai;
import models.SanPhamCon;
import models.SanPhamKhuyenMai;
import utils.LoaiKMEnum;


public class KhuyenMaiView extends JPanel implements ActionListener,MouseListener,KeyListener{
	private JLabel lbID;
	private JLabel lbTenKM;
	private JLabel lbLoai;
	private JLabel lbNgayKM;
	private JLabel lbtrangThai;
	private JLabel lbTimKiemKM;
	private JTextField txtTimKiemKM;
	private JLabel lbTimKiemSPKM;
	private JTextField txtTimKiemSPKM;
	private JLabel lbTimKiemSKM;
	private JTextField txtTimKiemSKM;
	private JLabel lbTimKiemSP;
	private JTextField txtTimKiemSP;
	private JLabel lbTimKiemSach;
	private JTextField txtTimKiemSach;
	private JTextField txtID;
	private JTextField txtTen;
	private JButton btnChonSP;
	private JButton btnChonSach;
	private JDateChooser ngayBatDau;
	private JComboBox<Object> cbTrangThai;
	private JComboBox<LoaiKMEnum> cbLoai;
	private JButton btnThem;
	private JButton btnCapNhat;
	private JButton btnLamMoi;
	private JTable tblSP;
	private DefaultTableModel modelSP2;
	private JTable tblSach;
	private DefaultTableModel modelSach;
	
	private JButton btnHienTatCaKM;
	private DefaultTableModel modelKM;
	private JTable tableKM;
	private DefaultTableModel modelSP;
	private JTable tableSP;
	private DefaultTableModel modelSKM;
	private JTable tableSKM;
	private DAOKhuyenMai daoKM;
	private SimpleDateFormat dfNgayBD;
	private DAOQuanLySanPham daoQLSP;
	private DAOSach daoSach;
	private JWindow windowSP;
	private JWindow windowSach;
	private JButton btnMoTbSP;
	private JButton btnMoTbSach;
	private JButton btnDongSP;
	private JButton btnDongSach;
	private JButton btnXoaSPKM;
	private JButton btnXoaSKM;
	private JPanel pnSP;
	private JFrame FrameSP;
	private JDialog dlogSP;
	private JFrame FrameSach;
	private JDialog dlogSach;
	private NumberFormat vietnameseFormat;
	public KhuyenMaiView() {
		setLayout(new GridBagLayout());
		vietnameseFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
		daoKM= new DAOKhuyenMai();
		daoQLSP=new DAOQuanLySanPham();
		daoSach=new DAOSach();
		dfNgayBD = new SimpleDateFormat("dd/MM/yyyy");
		JLabel lbTieuDe=new JLabel("CHƯƠNG TRÌNH KHUYẾN MÃI");
		lbTieuDe.setFont(new Font("Tahoma", Font.BOLD, 25));
		lbTieuDe.setForeground(new Color(26, 102, 227));
		
		cbLoai = new JComboBox<>();
		cbLoai.addItem(LoaiKMEnum.Giam_10);
		cbLoai.addItem(LoaiKMEnum.Giam_20);
		cbLoai.addItem(LoaiKMEnum.Giam_30);
		cbTrangThai = new JComboBox<Object>(new Object[] { "Đang áp dụng", "Dừng áp dụng" });
		
		ngayBatDau = new JDateChooser();
		ngayBatDau.getCalendarButton().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ngayBatDau.setBounds(100, 310, 200, 40);
		ngayBatDau.setDateFormatString("dd/MM/yyyy");	
		ngayBatDau.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		ngayBatDau.setFont(new Font("SansSerif", Font.PLAIN, 15));
		ngayBatDau.getCalendarButton().setPreferredSize(new Dimension(20, 24));
		ngayBatDau.getCalendarButton().setBackground(new Color(102, 0, 153));
		ngayBatDau.getCalendarButton().setToolTipText("Chọn ngày");
		lbID=new JLabel("ID khuyến mãi:");
		txtID=new JTextField();
		lbTenKM=new JLabel("Tên khuyến mãi:");
		txtTen=new JTextField();
		lbLoai=new JLabel("Loại khuyến mãi:");
		lbNgayKM=new JLabel("Ngày bắt đầu:");
		lbtrangThai=new JLabel("Trạng thái:");
		lbTimKiemSP=new JLabel("Tìm sản phẩm");
		txtTimKiemSP=new JTextField();
		
		ImageIcon iconThem = new ImageIcon(getClass().getResource("/icons/add.png"));
		ImageIcon iconCapNhat = new ImageIcon(getClass().getResource("/icons/capnhat.png"));
		ImageIcon iconLamMoi = new ImageIcon(getClass().getResource("/icons/lammoi.png"));
		ImageIcon iconMoTBSP = new ImageIcon(getClass().getResource("/icons/product.png"));
		ImageIcon iconMoTBSach = new ImageIcon(getClass().getResource("/icons/book.png"));
		ImageIcon iconDong = new ImageIcon(getClass().getResource("/icons/close.png"));
		btnCapNhat=new JButton("CẬP NHẬT");
		btnCapNhat.setIcon(iconCapNhat);
		btnThem=new JButton("THÊM");
		btnThem.setIcon(iconThem);
		btnLamMoi=new JButton("LÀM MỚI");
		btnLamMoi.setIcon(iconLamMoi);
		btnHienTatCaKM=new JButton("HIỂN THỊ TẤT CẢ");
		lbTimKiemSPKM=new JLabel("Tìm kiếm sản phẩm khuyến mãi:");
		txtTimKiemSPKM=new JTextField();
		lbTimKiemSKM=new JLabel("Tìm kiếm sách khuyến mãi");
		txtTimKiemSKM=new JTextField();
		lbTimKiemKM=new JLabel("Tìm kiếm khuyến mãi:");
		txtTimKiemKM=new JTextField();
		lbTimKiemSach=new JLabel("Tìm kiếm sách:");
		txtTimKiemSach=new JTextField();
		btnMoTbSP=new JButton("CHỌN SẢN PHẨM");
		btnMoTbSP.setIcon(iconMoTBSP);
		btnMoTbSach=new JButton("CHỌN SÁCH");
		btnMoTbSach.setIcon(iconMoTBSach);
		btnDongSP=new JButton("ĐÓNG");
		btnDongSP.setIcon(iconDong);
		btnDongSach=new JButton("ĐÓNG");
		btnDongSach.setIcon(iconDong);
		btnXoaSPKM=new JButton("XÓA");
		btnXoaSKM=new JButton("XÓA");
		JPanel pnKM=new JPanel(new GridLayout(5, 1, 10, 10));
		JPanel pnHeaderLeft=new JPanel(new BorderLayout());
		
		JPanel pnTitleLeft=new JPanel();
	
		JPanel pnChucNang=new JPanel();
		pnTitleLeft.add(lbTieuDe);
		
		pnKM.add(lbID);
		pnKM.add(txtID);
		txtID.setEditable(false);
		pnKM.add(lbTenKM);
		pnKM.add(txtTen);
		pnKM.add(lbLoai);
		pnKM.add(cbLoai);
		pnKM.add(lbNgayKM);
		pnKM.add(ngayBatDau);
		pnKM.add(lbtrangThai);
		pnKM.add(cbTrangThai);
		pnHeaderLeft.add(pnTitleLeft,BorderLayout.NORTH);
		pnHeaderLeft.add(pnKM,BorderLayout.CENTER);
		
		pnKM.setBorder(BorderFactory.createTitledBorder("Thông tin khuyến mãi"));
		
		pnChucNang.add(btnThem);
		pnChucNang.add(btnCapNhat);
		pnChucNang.add(btnLamMoi);
		
		pnHeaderLeft.add(pnChucNang,BorderLayout.SOUTH);
		//Tạo bảng chọn sản phẩm
		tblSP = new JTable();
		modelSP2 = new DefaultTableModel();
		modelSP2.addColumn("ID sản phẩm");
		modelSP2.addColumn("Tên sản phẩm");
		modelSP2.addColumn("Giá bán");
		tblSP.setModel(modelSP2);
		JScrollPane scrollTblKH = new JScrollPane(tblSP);
		btnChonSP = new JButton("ÁP DỤNG");
		
		JPanel TimKiemSP = new JPanel(new GridLayout(2,1,5,5));
		JPanel FooterSP = new JPanel(new GridLayout(1,4,5,5));
		JLabel lbChen=new JLabel();
		JLabel lbChen2=new JLabel();
		FooterSP.add(btnChonSP);
		
		TimKiemSP.add(lbChen);
		TimKiemSP.add(lbChen2);
		TimKiemSP.add(lbTimKiemSP);
		TimKiemSP.add(txtTimKiemSP);
		pnSP = new JPanel(new BorderLayout());
		pnSP.add(scrollTblKH, BorderLayout.CENTER);
		pnSP.add(TimKiemSP, BorderLayout.NORTH);
		pnSP.add(FooterSP, BorderLayout.SOUTH);
		FrameSP=new JFrame();
		dlogSP= new JDialog( dlogSP, "SẢN PHẨM", null);
	    dlogSP.add(pnSP);
	  //Tạo bảng chọn sach
		tblSach = new JTable();
		modelSach = new DefaultTableModel();
        modelSach.addColumn("ID sách");
        modelSach.addColumn("Tên sách");
        modelSach.addColumn("Giá bán");
        tblSach.setModel(modelSach);
		JScrollPane scrollTblSach = new JScrollPane(tblSach);
		btnChonSach = new JButton("ÁP DỤNG");
		JPanel TimKiemSach = new JPanel(new GridLayout(2,1,5,5));
		JPanel FooterSach = new JPanel(new GridLayout(1,4,5,5));
		JLabel lbChen3=new JLabel();
		JLabel lbChen4=new JLabel();
		FooterSach.add(btnChonSach);

		TimKiemSach.add(lbChen3);
		TimKiemSach.add(lbChen4);
		TimKiemSach.add(lbTimKiemSach);
		TimKiemSach.add(txtTimKiemSach);
		JPanel pnSach = new JPanel(new BorderLayout());
		pnSach.add(scrollTblSach, BorderLayout.CENTER);
		pnSach.add(TimKiemSach, BorderLayout.NORTH);
		pnSach.add(FooterSach,BorderLayout.SOUTH);
		FrameSach=new JFrame();
		dlogSach= new JDialog( FrameSach, "SÁCH", null);
	    dlogSach.add(pnSach);
        
		JPanel pnTimKiemSP=new JPanel(new GridLayout(1,4,10,10));
		pnTimKiemSP.add(lbTimKiemSPKM);
		pnTimKiemSP.add(txtTimKiemSPKM);
		pnTimKiemSP.add(btnXoaSPKM);
		
		JPanel pnTimKiemSKM=new JPanel(new GridLayout(1,4,10,10));
		pnTimKiemSKM.add(lbTimKiemSKM);
		pnTimKiemSKM.add(txtTimKiemSKM);
		pnTimKiemSKM.add(btnXoaSKM);
		
		JPanel pnTimKiemKM=new JPanel(new GridLayout(1,4,10,10));
		pnTimKiemKM.add(btnMoTbSP);
		pnTimKiemKM.add(btnMoTbSach);
		pnTimKiemKM.add(lbTimKiemKM);
		pnTimKiemKM.add(txtTimKiemKM);
		pnTimKiemKM.add(btnHienTatCaKM);
		
		
		modelKM = new DefaultTableModel();
		tableKM = new JTable();
		
            
        modelKM.addColumn("ID khuyến mãi");
        modelKM.addColumn("Tên KM");
        modelKM.addColumn("Ngày bắt đầu");
        modelKM.addColumn("Trạng thái");
        modelKM.addColumn("Loại");
		tableKM.setModel(modelKM);
        JScrollPane scrollPane1 = new JScrollPane(tableKM);
        
        JPanel pntbKM=new JPanel(new BorderLayout());
        pntbKM.setBorder(BorderFactory.createTitledBorder("Khuyến mãi"));
        pntbKM.add(pnTimKiemKM,BorderLayout.NORTH);
        pntbKM.add(scrollPane1,BorderLayout.CENTER);
        
        modelSP = new DefaultTableModel() ;
        tableSP = new JTable();
        modelSP.addColumn("Chương trình KM");
		modelSP.addColumn("ID sản phẩm");
		modelSP.addColumn("Tên sản phẩm");
		modelSP.addColumn("Giá bán");
		modelSP.addColumn("Giá KM");
		tableSP.setModel(modelSP);
        JScrollPane scrollPane2 = new JScrollPane(tableSP);
        JPanel pntbSP=new JPanel(new BorderLayout());
        pntbSP.add(pnTimKiemSP,BorderLayout.NORTH);
        pntbSP.add(scrollPane2,BorderLayout.CENTER);
        pntbSP.setBorder(BorderFactory.createTitledBorder("Sản phẩm khuyến mãi"));
        
        modelSKM = new DefaultTableModel() ;
        tableSKM = new JTable();
        modelSKM.addColumn("Chương trình KM");
        modelSKM.addColumn("ID sách");
        modelSKM.addColumn("Tên sách");
        modelSKM.addColumn("Giá bán");
        modelSKM.addColumn("Giá KM");
        tableSKM.setModel(modelSKM);
        JScrollPane scrollPane3 = new JScrollPane(tableSKM);
        JPanel pntbSKM=new JPanel(new BorderLayout());
        pntbSKM.add(pnTimKiemSKM,BorderLayout.NORTH);
        pntbSKM.add(scrollPane3,BorderLayout.CENTER);
        pntbSKM.setBorder(BorderFactory.createTitledBorder("Sách khuyến mãi"));
        
        JPanel pnRigth=new JPanel();
        pnRigth.setLayout(new BorderLayout());
        JPanel pnLeft=new JPanel(new BorderLayout());
        pnRigth.add(pntbSKM,BorderLayout.CENTER);
        pnRigth.add(pntbSP,BorderLayout.NORTH);
        pnLeft.add(pnHeaderLeft,BorderLayout.NORTH);
        pnLeft.add(pntbKM,BorderLayout.CENTER);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.4;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(pnLeft, gbc);

        
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.6;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(pnRigth, gbc);
        
        txtID.setToolTipText("ID + Date + XXXX");
		txtTen.setToolTipText("Tên hợp lệ");
		ngayBatDau.setToolTipText("Trước ngày hiện tại");
        ngayBatDau.setDate(new java.util.Date());
        
        btnHienTatCaKM.addActionListener(this);
        btnLamMoi.addActionListener(this);
        btnCapNhat.addActionListener(this);
        btnChonSP.addActionListener(this);
        btnChonSach.addActionListener(this);
        btnThem.addActionListener(this);
        tableKM.addMouseListener(this);
        txtTimKiemKM.addKeyListener(this);
        txtTimKiemSPKM.addKeyListener(this);
        txtTimKiemSKM.addKeyListener(this);
        txtTimKiemSP.addKeyListener(this);
        txtTimKiemSach.addKeyListener(this);
        btnMoTbSP.addActionListener(this);
        btnMoTbSach.addActionListener(this);
        btnDongSP.addActionListener(this);
        btnDongSach.addActionListener(this);
        btnXoaSKM.addActionListener(this);
        btnXoaSPKM.addActionListener(this);
        loadSPKM();
        loadData();
        LoadDataSach();
        LoadDataSP();
        loadSKM();
        try {
			txtID.setText(autoID());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void loadData() {
		modelKM.setRowCount(0);
		for (KhuyenMai km : daoKM.getAllDanhSachKM() ) {
			modelKM.addRow(new Object[] { km.getId(), km.getTen(),dfNgayBD.format(km.getNgayBatDau()), km.getTrangThai()?"Đang áp dụng":"Dừng áp dụng",km.getLoai()
					});
			
		}
	}
	private void loadSPKM() {
		modelSP.setRowCount(0);
		for (SanPhamKhuyenMai spkm: daoKM.getAllDanhSachSPKM() ) {
			modelSP.addRow(new Object[] {  spkm.getIdKM(),spkm.getIdSanPham(),spkm.getTenSP(),vietnameseFormat.format(spkm.getGiaBan()),"-"+vietnameseFormat.format(spkm.getGiaKM())
					});
			
		}
	}
	private void loadSKM() {
		modelSKM.setRowCount(0);
		for (SachKhuyenMai skm: daoKM.getAllDanhSachSachKM() ) {
			modelSKM.addRow(new Object[] {  skm.getIdKM(),skm.getIdSanPham(),skm.getTenSP(),vietnameseFormat.format(skm.getGiaBan()),"-"+vietnameseFormat.format(skm.getGiaKM())
					});
			
		}
	}
	private void LoadDataSP() {
		modelSP2.setRowCount(0);
			for (SanPhamCon sp : daoQLSP.getAllSanPhamLoadData()) {
				
				modelSP2.addRow(new Object[] {sp.getIdSanPham(),sp.getTenSanPham(),vietnameseFormat.format(sp.giaBan())});
				
			} 
		
	}
	private void LoadDataSach() {
		modelSach.setRowCount(0);
			for (SachCon s : daoSach.getAllSachLoadData()) {
				
				modelSach.addRow(new Object[] {s.getIdSanPham(),s.getTenSanPham(),vietnameseFormat.format(s.giaBan())});
				
			} 
		
	}
	
	private void ThemSachKM() throws SQLException {
		// Lấy thông tin chương trình khuyến mãi đang được chọn
			int chonKM = tableKM.getSelectedRow();
			int chonSach = tblSach.getSelectedRow();
			
			if(chonKM<0 || chonSach<0) {
				JOptionPane.showMessageDialog(null,"Vui lòng chọn chương trình khuyến mãi và sách");
			}
			else {
				String trangThaiKM=(String)tableKM.getValueAt(chonKM,3).toString();
		    	if(trangThaiKM.equals("Đang áp dụng")) {
			    	String idKM=(String)tableKM.getValueAt(chonKM,0);
			        LoaiKMEnum loaiKM= (LoaiKMEnum) tableKM.getValueAt(chonKM, 4);
			       // Lấy thông tin sản phẩm
			        String idSP = (String) tblSach.getValueAt(chonSach, 0);
			        NumberFormat format = NumberFormat.getCurrencyInstance(new java.util.Locale("vi", "VN"));
			        String getGiaBan=(String)tblSach.getValueAt(chonSach, 2);
			        Number number = null;
					try {
						number = format.parse(getGiaBan);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		            double giaBan=number.doubleValue();
			       
			        String tenSP=(String) tblSach.getValueAt(chonSach, 1);
			        // Tính giá bán mới sau khuyến mãi
			        double giaKM = giaBan * ((loaiKM.getValue()*10) / 100.0);
			     // Cập nhật giá bán mới trong bảng và cơ sở dữ liệu
			        if(KiemTraSKM(idSP)) { 
				        daoKM.ThemSachKM(idSP, idKM, tenSP, giaBan, giaKM);
				        daoKM.updateGiaKMSach(idSP, giaKM);
				        modelSKM.addRow(new Object[] {idKM,idSP,tenSP,vietnameseFormat.format(giaBan),"-"+vietnameseFormat.format(giaKM)});
				        dlogSach.setVisible(false);
				        JOptionPane.showMessageDialog(this,"Áp dụng khuyến mãi thành công");
			        }
			        
		    	}
		    
		    	else if(trangThaiKM.equals("Dừng áp dụng")) {
			    	JOptionPane.showMessageDialog(this,"Chương trình khuyến mãi đã dừng áp dụng");
			    }
		    }
		}
	private void ThemSPKM() throws SQLException {
		// Lấy thông tin chương trình khuyến mãi đang được chọn
		int chonKM = tableKM.getSelectedRow();
		int chonSP = tblSP.getSelectedRow();
		
		if(chonKM<0 || chonSP<0) {
			JOptionPane.showMessageDialog(null,"Vui lòng chọn chương trình khuyến mãi và sản phẩm");
		}
		
		else{
			String trangThaiKM=(String)tableKM.getValueAt(chonKM,3).toString();
			if(trangThaiKM.equals("Đang áp dụng")) {
				String idKM=(String)tableKM.getValueAt(chonKM,0);
		        LoaiKMEnum loaiKM= (LoaiKMEnum) tableKM.getValueAt(chonKM, 4);
		       // Lấy thông tin sản phẩm
		        String idSP = (String) tblSP.getValueAt(chonSP, 0);
		        String tenSP=(String) tblSP.getValueAt(chonSP, 1);
		        NumberFormat format = NumberFormat.getCurrencyInstance(new java.util.Locale("vi", "VN"));
		        String getGiaBan=(String)tblSP.getValueAt(chonSP, 2);
		        Number number = null;
				try {
					number = format.parse(getGiaBan);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            double giaBan=number.doubleValue();
		        // Tính giá bán mới sau khuyến mãi
		        double giaKM = giaBan * ((loaiKM.getValue()*10) / 100.0);
		        // Cập nhật giá bán mới trong bảng và cơ sở dữ liệu
			    if(KiemTraSPKM(idSP)){
			    	 daoKM.ThemSPKM(idSP, idKM,tenSP,giaBan,giaKM);
				     daoKM.updateGiaKM(idSP, giaKM);
				     modelSP.addRow(new Object[] {idKM,idSP,tenSP,vietnameseFormat.format(giaBan),"-"+vietnameseFormat.format(giaKM)});
				     dlogSP.setVisible(false);
				     JOptionPane.showMessageDialog(this,"Áp dụng khuyến mãi thành công");
			    }
			    
			}
			else if(trangThaiKM.equals("Dừng áp dụng")) {
		    	JOptionPane.showMessageDialog(this,"Chương trình khuyến mãi đã dừng áp dụng");
		    }
		}
		

	}
	private boolean KiemTraSPKM(String idMoi){
		
	
		for (SanPhamKhuyenMai spkm : daoKM.getAllDanhSachSPKM() ) {
			if(idMoi.equals(spkm.getIdSanPham())) {
				JOptionPane.showMessageDialog(this, "Sản phẩm đã được áp dụng khuyến mãi.Vui lòng chọn sách khác", "Thông báo",
						JOptionPane.WARNING_MESSAGE);
				return false;
			}
		}
		return true;
	}
	private boolean KiemTraSKM(String idMoi){
		for (SachKhuyenMai skm : daoKM.getAllDanhSachSachKM() ) {
			if(idMoi.equals(skm.getIdSanPham())) {
				JOptionPane.showMessageDialog(this, "Sách đã được áp dụng khuyến mãi.Vui lòng chọn sản phẩm khác", "Thông báo",
						JOptionPane.WARNING_MESSAGE);
				return false;
			}
		}
		return true;
	}

	private String autoID() throws SQLException {
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	        java.util.Date date = new java.util.Date();
			
			  ConnectDB.getinstance();
			  Connection con = ConnectDB.getConnection();
			  String query = "SELECT MAX(idKM) FROM KhuyenMai";
	          PreparedStatement preparedStatement = con.prepareStatement(query);
	          
	          ResultSet resultSet = preparedStatement.executeQuery();
	
	          String lastID = null;
	          if (resultSet.next()) {
	              lastID = resultSet.getString(1);
	          }
	
	          String newID;
	          if (lastID != null) {
	              int number = Integer.parseInt(lastID.substring(10));
	              number++; // Tăng giá trị số tiếp theo
	              newID = "KM" + dateFormat.format(date) + String.format("%4d", number).replace(" ", "0");
	          } else {
	              newID = "KM" + dateFormat.format(date) + "0001";
	          }
	
	          preparedStatement.close();
	         return newID;
	     
		}
	private void themKM() throws SQLException {
		String id = autoID();
		String tenKM = txtTen.getText();
		java.util.Date date = ngayBatDau.getDate();
		Date ngayBD = new Date(date.getYear(), date.getMonth(), date.getDate());
		
		LoaiKMEnum selectedValue = (LoaiKMEnum) cbLoai.getSelectedItem();
		String LoaiDescription=selectedValue.getDescription();
		
		
		String trangThaiSelected=cbTrangThai.getSelectedItem().toString();
		Boolean trangThai=null;
		if(trangThaiSelected.equals("Đang áp dụng")) {
			trangThai=true;
		}
		if(trangThaiSelected.equals("Dừng áp dụng")) {
			trangThai=false;
		}
		KhuyenMai km=new KhuyenMai(id, tenKM, ngayBD, trangThai, selectedValue);
		daoKM.ThemKM(km);
		modelKM.addRow(new Object[] {id,tenKM,dfNgayBD.format(km.getNgayBatDau()),trangThaiSelected,LoaiDescription});
	}
	public boolean valiDate() {
		
		String ten = txtTen.getText().trim();
		java.util.Date ngayBD = ngayBatDau.getDate();
		if(ten.equals("") ) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin đầy đủ!", "Thông báo",
					JOptionPane.WARNING_MESSAGE);
			txtTen.requestFocus();
			
			return false;
		}

		if (!(ngayBD!=null  && (ngayBD.before(new java.util.Date())))) {
			JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải trước ngày hiện tại", "Thông báo",
					JOptionPane.WARNING_MESSAGE);
			ngayBatDau.requestFocus();
			
			return false;
		}
		
		return true;
	}
	private void lamMoi() {
		loadData();
		try {
			txtID.setText(autoID());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		txtTen.setText("");
		txtTen.requestFocus();
		ngayBatDau.setDate(new java.util.Date());
		cbLoai.setSelectedItem(LoaiKMEnum.Giam_10);
		cbTrangThai.setSelectedItem("Đang áp dụng");
	}
	private void showDialog(JFrame FrameParent,JDialog dialog) {
		
		dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
		
	}
	private void CapNhatKM() {
		String id = txtID.getText();
		String ten = txtTen.getText();
		java.util.Date date = ngayBatDau.getDate();
		Date ngayBatDau = new Date(date.getYear(), date.getMonth(), date.getDate());
		String trangThaiSelected=cbTrangThai.getSelectedItem().toString();
		Boolean trangThai=null;
		if(trangThaiSelected.equals("Đang áp dụng")) {
			trangThai=true;
		}
		if(trangThaiSelected.equals("Dừng áp dụng")) {
			trangThai=false;
		}
		LoaiKMEnum selectedValue = (LoaiKMEnum) cbLoai.getSelectedItem();
		
		KhuyenMai km=new KhuyenMai();
		km.setId(id);;
		km.setTen(ten);;
		km.setNgayBatDau(ngayBatDau);;
		km.setTrangThai(trangThai);
		km.setLoai(selectedValue);
		
		
			try {
				
				daoKM.updateKM(km);
				loadData();
				JOptionPane.showMessageDialog(this, "Cập nhật thành công");
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Cập nhật thất bại");
			}
		}
	private void xoaSPKM() {
		int row = tableSP.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(this, "Phải chọn dòng cần xoá");
		} else {
			try {
				int hoinhac = JOptionPane.showConfirmDialog(this, "Bạn có muốn xoá dòng này không?", "Cảnh báo",
						JOptionPane.YES_NO_OPTION);
				if (hoinhac == JOptionPane.YES_OPTION) {
					
					String idSP=tableSP.getValueAt(row, 1).toString();
					
					daoKM.XoaSPKM(idSP);
					modelSP.removeRow(row);
					daoKM.updateGiaKM(idSP,0);
					JOptionPane.showMessageDialog(this, "Xoá thành công");
				}
			} catch (Exception e2) {
				e2.printStackTrace();
				JOptionPane.showMessageDialog(this, "Xoá thất bại");
			}
		}
	}
	private void xoaSKM() {
		int row = tableSKM.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(this, "Phải chọn dòng cần xoá");
		} else {
			try {
				int hoinhac = JOptionPane.showConfirmDialog(this, "Bạn có muốn xoá dòng này không?", "Cảnh báo",
						JOptionPane.YES_NO_OPTION);
				if (hoinhac == JOptionPane.YES_OPTION) {
					
					String idSP=tableSKM.getValueAt(row, 1).toString();
					
					
					daoKM.XoaSKM(idSP);
					modelSKM.removeRow(row);
					daoKM.updateGiaKMSach(idSP, 0);
					JOptionPane.showMessageDialog(this, "Xoá thành công");
				}
			} catch (Exception e2) {
				e2.printStackTrace();
				JOptionPane.showMessageDialog(this, "Xoá thất bại");
			}
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tableKM.getSelectedRow();
		txtID.setText(modelKM.getValueAt(row, 0).toString());
		txtTen.setText(modelKM.getValueAt(row, 1).toString());
		
		String ngayBatDau=modelKM.getValueAt(row, 2).toString();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date valueNgaySinh=null;
		try {
			valueNgaySinh = dateFormat.parse(ngayBatDau);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        this.ngayBatDau.setDate(valueNgaySinh);
        
		cbTrangThai.setSelectedItem(modelKM.getValueAt(row,3).toString());
		LoaiKMEnum loaiSelectd=(LoaiKMEnum)modelKM.getValueAt(row,4);
		
		cbLoai.setSelectedItem(loaiSelectd);
		
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
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			try {
				themKM();
				lamMoi();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			} 
		if(o.equals(btnCapNhat)) {
			CapNhatKM();
		}
		if(o.equals(btnLamMoi)) {
			lamMoi();
		}
		if(o.equals(btnHienTatCaKM)) {
			loadData();
		}
		if(o.equals(btnChonSP)) {
			try {
				ThemSPKM();
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(o.equals(btnChonSach)) {
			try {
				ThemSachKM();
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(o.equals(btnMoTbSP)) {
			showDialog(FrameSP, dlogSP);
		}
		if(o.equals(btnMoTbSach)) {
			showDialog(FrameSach, dlogSach);
		}
		if(o.equals(btnXoaSPKM)) {
			xoaSPKM();
		}
		if(o.equals(btnXoaSKM)) {
			xoaSKM();
		}
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
		SwingUtilities.invokeLater(() -> {
			Object o = e.getSource();
			if (o.equals(txtTimKiemKM)) {
				DefaultTableModel model = (DefaultTableModel) tableKM.getModel();
				TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(model);
				tableKM.setRowSorter(tr);
				tr.setRowFilter(RowFilter.regexFilter("(?i)" + txtTimKiemKM.getText().trim(), 1));
			}
			else if (o.equals(txtTimKiemSPKM)) {
				DefaultTableModel model = (DefaultTableModel) tableSP.getModel();
				TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(model);
				tableSP.setRowSorter(tr);
				tr.setRowFilter(RowFilter.regexFilter("(?i)" + txtTimKiemSPKM.getText().trim(), 2));
			}
			else if (o.equals(txtTimKiemSKM)) {
				DefaultTableModel model = (DefaultTableModel) tableSKM.getModel();
				TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(model);
				tableSKM.setRowSorter(tr);
				tr.setRowFilter(RowFilter.regexFilter("(?i)" + txtTimKiemSKM.getText().trim(), 2));
			}
			else if (o.equals(txtTimKiemSP)) {
				DefaultTableModel model = (DefaultTableModel) tblSP.getModel();
				TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(model);
				tblSP.setRowSorter(tr);
				tr.setRowFilter(RowFilter.regexFilter("(?i)" + txtTimKiemSP.getText().trim(), 1));
			}
			else if (o.equals(txtTimKiemSach)) {
				DefaultTableModel model = (DefaultTableModel) tblSach.getModel();
				TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(model);
				tblSach.setRowSorter(tr);
				tr.setRowFilter(RowFilter.regexFilter("(?i)" + txtTimKiemSach.getText().trim(), 1));
			}
		});
	}
}