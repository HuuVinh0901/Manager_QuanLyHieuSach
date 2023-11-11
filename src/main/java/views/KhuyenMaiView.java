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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.event.TableModelListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;

import javax.swing.plaf.basic.BasicSplitPaneUI.KeyboardHomeHandler;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import javax.swing.text.html.HTMLDocument.HTMLReader.SpecialAction;

import com.toedter.calendar.JDateChooser;

import connection.ConnectDB;
import dao.DAOKhuyenMai;
import dao.DAOQuanLySanPham;
import models.KhachHang;
import models.KhuyenMai;
import models.SanPhamCha;
import models.SanPhamCon;
import models.SanPhamKhuyenMai;
import utils.LoaiKMEnum;
import utils.TrangThaiSPEnum;

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
	private JLabel lbTimKiemSP;
	private JTextField txtTimKiemSP;
	private JTextField txtID;
	private JTextField txtTen;
	private JButton btnChonSP;
	private JDateChooser ngayBatDau;
	private JComboBox<Object> cbTrangThai;
	private JComboBox<LoaiKMEnum> cbLoai;
	private JButton btnThem;
	private JButton btnCapNhat;
	private JButton btnLamMoi;
	private JTable tblSP;
	private DefaultTableModel modelSP2;
	private JButton btnHienTatCaSP;
	private JButton btnHienTatCaKM;
	private DefaultTableModel modelKM;
	private JTable tableKM;
	private DefaultTableModel modelSP;
	private JTable tableSP;
	private DAOKhuyenMai daoKM;
	private SimpleDateFormat dfNgayBD;
	private DAOQuanLySanPham daoQLSP;
	private JWindow windowSP;
	public KhuyenMaiView() {
		setLayout(new GridBagLayout());
		
		daoKM= new DAOKhuyenMai();
		daoQLSP=new DAOQuanLySanPham();
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
		btnCapNhat=new JButton("CẬP NHẬT");
		btnCapNhat.setIcon(iconCapNhat);
		btnThem=new JButton("THÊM");
		btnThem.setIcon(iconThem);
		btnLamMoi=new JButton("LÀM MỚI");
		btnLamMoi.setIcon(iconLamMoi);
		btnHienTatCaKM=new JButton("HIỂN THỊ TẤT CẢ");
		btnHienTatCaSP=new JButton("HIỂN THỊ TẤT CẢ");
		lbTimKiemSPKM=new JLabel("Tìm kiếm sản phẩm:");
		txtTimKiemSPKM=new JTextField();
		lbTimKiemKM=new JLabel("Tìm kiếm khuyến mãi:");
		txtTimKiemKM=new JTextField();
		
		
		
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

		tblSP = new JTable();
		modelSP2 = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không cho phép chỉnh sửa các ô trong bảng
            }
        };
		modelSP2.addColumn("Mã sản phẩm");
		modelSP2.addColumn("Tên sản phẩm");
		modelSP2.addColumn("Giá bán");
		tblSP.setModel(modelSP2);
		
			

						
		
		JScrollPane scrollTblKH = new JScrollPane(tblSP);
		btnChonSP = new JButton("Chọn sản phẩm");
		JPanel pn = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pn.setBorder(BorderFactory.createTitledBorder("Sản phẩm"));
		pn.add(btnChonSP);
		
		JPanel pnSP = new JPanel(new BorderLayout());
		pnSP.add(scrollTblKH, BorderLayout.CENTER);
		pnSP.add(pn, BorderLayout.NORTH);
		
        windowSP = new JWindow();
        windowSP.add(pnSP);
//		
		JPanel pnTimKiemSP=new JPanel(new GridLayout(1,4,10,10));
		pnTimKiemSP.add(lbTimKiemSPKM);
		pnTimKiemSP.add(txtTimKiemSPKM);
		pnTimKiemSP.add(btnHienTatCaSP);
		
		JPanel pnTimKiemKM=new JPanel(new GridLayout(1,4,10,10));
		pnTimKiemKM.add(lbTimKiemSP);
		pnTimKiemKM.add(txtTimKiemSP);
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
        pntbSP.setBorder(BorderFactory.createTitledBorder("Sản phẩm"));
        JPanel pnRigth=new JPanel();
        pnRigth.setLayout(new BorderLayout());
        JPanel pnLeft=new JPanel(new BorderLayout());
       
        pnRigth.add(pntbSP,BorderLayout.CENTER);
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
        btnHienTatCaKM.addActionListener(this);
        btnHienTatCaSP.addActionListener(this);
        btnLamMoi.addActionListener(this);
        btnCapNhat.addActionListener(this);
        btnChonSP.addActionListener(this);
        btnThem.addActionListener(this);
        tableKM.addMouseListener(this);
        txtTimKiemKM.addKeyListener(this);
        txtTimKiemSPKM.addKeyListener(this);
        loadSPKM();
        loadData();
//        initializeCheckboxStates();
        txtTimKiemSP.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
            	modelSP2.setRowCount(0);
            	handleTimKiemSP(txtTimKiemSP.getText());
            	if (!txtTimKiemSP.getText().equals("")) {
            		 windowSP.setLocation(lbTimKiemSP.getLocationOnScreen().x - 40, lbTimKiemSP.getLocationOnScreen().y + lbTimKiemSP.getHeight());
            		 windowSP.pack();
            		 windowSP.setVisible(true);
            	} else {
            		windowSP.setVisible(false);
            	}
            }

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				modelSP2.setRowCount(0);
            	handleTimKiemSP(txtTimKiemSP.getText());
            	if (!txtTimKiemSP.getText().equals("")) {
            		 windowSP.setLocation(lbTimKiemSP.getLocationOnScreen().x - 50, lbTimKiemSP.getLocationOnScreen().y + lbTimKiemSP.getHeight());
            		 windowSP.pack();
            		 windowSP.setVisible(true);
            	} else {
            		windowSP.setVisible(false);
            	}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
        });
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
			modelSP.addRow(new Object[] {  spkm.getIdKM(),spkm.getIdSanPham(),spkm.getTenSP(),spkm.getGiaBan(),spkm.getGiaKM()
					});
			
		}
	}
	private void handleTimKiemSP(String cond) {
		if (!cond.equals("")) {
			for (SanPhamCon sp : daoQLSP.getSanPhamTimKiem(cond)) {
				
				modelSP2.addRow(new Object[] {sp.getIdSanPham(),sp.getTenSanPham(),sp.giaBan()});
				
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
	    if (chonKM >= 0 && chonSP>=0) {
	    	String idKM=(String)tableKM.getValueAt(chonKM,0);
	        
	        LoaiKMEnum loaiKM= (LoaiKMEnum) tableKM.getValueAt(chonKM, 4);
	       
	        // Lấy thông tin sản phẩm
	       
	        String idSP = (String) tblSP.getValueAt(chonSP, 0);
	        double giaBan = (double) tblSP.getValueAt(chonSP, 2);
	        String tenSP=(String) tblSP.getValueAt(chonSP, 1);
	        // Tính giá bán mới sau khuyến mãi
	        double giaKM = giaBan - (giaBan * ((loaiKM.getValue()*10) / 100.0));
	
	//        // Cập nhật giá bán mới trong bảng và cơ sở dữ liệu
	//       
	        daoKM.ThemSPKM(idSP, idKM,tenSP,giaBan,giaKM);
	        daoKM.updateGiaKM(idSP, giaKM);
	        modelSP.addRow(new Object[] {idKM,idSP,tenSP,giaBan,giaKM});
	    }
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
		
		
		String trangThaiValue=cbTrangThai.getSelectedItem().toString();
		
		Boolean TrangThaibooleanValue = Boolean.parseBoolean(trangThaiValue);
		KhuyenMai km=new KhuyenMai(id, tenKM, ngayBD, TrangThaibooleanValue, selectedValue);
		daoKM.ThemKM(km);
		modelKM.addRow(new Object[] {id,tenKM,dfNgayBD.format(km.getNgayBatDau()),km.getTrangThai()?"Đang áp dụng":"Dừng áp dụng",LoaiDescription});
	}
	private void lamMoi() {
		loadData();
		txtID.setText("");
		txtTen.setText("");
		txtTen.requestFocus();
		ngayBatDau.setDate(new java.util.Date());
		cbLoai.setSelectedItem(LoaiKMEnum.Giam_10);
		cbTrangThai.setSelectedItem("Đang áp dụng");
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
		});
	}
	

	
	
}