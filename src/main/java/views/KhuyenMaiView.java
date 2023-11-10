package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.event.TableModelListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultCellEditor;
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
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;

import javax.swing.plaf.basic.BasicSplitPaneUI.KeyboardHomeHandler;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;

import connection.ConnectDB;
import dao.DAOKhuyenMai;
import dao.DAOQuanLySanPham;
import models.KhachHang;
import models.KhuyenMai;
import models.SanPhamCha;
import models.SanPhamCon;
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
	private JLabel lbTimKiemSP;
	private JTextField txtTimKiemSP;
	private JTextField txtID;
	private JTextField txtTen;
	
	private JDateChooser ngayBatDau;
	private JComboBox<Object> cbTrangThai;
	private JComboBox<LoaiKMEnum> cbLoai;
	private JButton btnThem;
	private JButton btnCapNhat;
	private JButton btnLamMoi;
	
	private JButton btnHienTatCaSP;
	private JButton btnHienTatCaKM;
	private DefaultTableModel modelKM;
	private JTable tableKM;
	private DefaultTableModel modelSP;
	private JTable tableSP;
	private DAOKhuyenMai daoKM;
	private SimpleDateFormat dfNgayBD;
	private DAOQuanLySanPham daoQLSP;
	public KhuyenMaiView() {
		setLayout(new GridBagLayout());
		
		daoKM= new DAOKhuyenMai();
		daoQLSP=new DAOQuanLySanPham();
		dfNgayBD = new SimpleDateFormat("dd/MM/yyyy");
		JLabel lbTieuDe=new JLabel("CHƯƠNG TRÌNH KHUYẾN MÃI");
		lbTieuDe.setFont(new Font("Arial", Font.BOLD, 25));
		lbTieuDe.setForeground(Color.black);
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
		btnCapNhat=new JButton("CẬP NHẬT");
		btnThem=new JButton("THÊM");
		btnLamMoi=new JButton("LÀM MỚI");
		btnHienTatCaKM=new JButton("HIỂN THỊ TẤT CẢ");
		btnHienTatCaSP=new JButton("HIỂN THỊ TẤT CẢ");
		lbTimKiemSP=new JLabel("Tìm kiếm sản phẩm:");
		txtTimKiemSP=new JTextField();
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

	
//		
		JPanel pnTimKiemSP=new JPanel(new GridLayout(1,4,10,10));
		pnTimKiemSP.add(lbTimKiemSP);
		pnTimKiemSP.add(txtTimKiemSP);
		pnTimKiemSP.add(btnHienTatCaSP);
		JPanel pnTimKiemKM=new JPanel(new GridLayout(1,4,10,10));
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
         modelSP = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 4) {
                    return Boolean.class; // Cột checkbox có kiểu dữ liệu là Boolean
                }
                return super.getColumnClass(columnIndex);
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4; // Chỉ cho phép chỉnh sửa cột checkbox
            }
        };
        
		tableSP = new JTable();
		modelSP.addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
				int row = e.getFirstRow();
                int column = e.getColumn();
                
                if (column == 4) { // Cột checkbox
                    boolean checkKM = (boolean) tableSP.getValueAt(row, column);
                    
                    if (checkKM) {
                        // Lấy thông tin chương trình khuyến mãi đang được chọn
                    	int selectedPromotionIndex = tableKM.getSelectedRow();
                        
                        if (selectedPromotionIndex >= 0) {
                        	String idKM=(String)tableKM.getValueAt(selectedPromotionIndex,0);
                            
                            LoaiKMEnum loaiKM= (LoaiKMEnum) tableKM.getValueAt(selectedPromotionIndex, 4);
                            
                            // Lấy thông tin sản phẩm
                            String idSP = (String) tableSP.getValueAt(row, 0);
                            double giaBan = (double) tableSP.getValueAt(row, 2);

                            // Tính giá bán mới sau khuyến mãi
                            double giaKM = giaBan - (giaBan * ((loaiKM.getValue()*10) / 100.0));

                            // Cập nhật giá bán mới trong bảng và cơ sở dữ liệu
                            tableSP.setValueAt(giaKM, row, 3);
                            ThemSPKM(idSP, idKM);
                            updateProductPrice(idSP, giaKM);
                        }
                        else {
                        	// Hiển thị thông báo khi chưa chọn chương trình khuyến mãi
                            JOptionPane.showMessageDialog(null, "Vui lòng chọn chương trình khuyến mãi");
                            // Bỏ tích checkbox
                            tableSP.setValueAt(false, row, column);
                        }
                        
                    }
                    else {
                        // Nếu checkbox không được chọn, đặt lại giá khuyến mãi bằng giá bán
                    	
                    	String idSP = (String) tableSP.getValueAt(row, 0);
                        double giaBan = (double) tableSP.getValueAt(row, 2);
                        tableSP.setValueAt(giaBan, row, 3);
                        updateProductPrice(idSP, giaBan);
                        XoaSPKM(idSP);
                    }
                }
			}
			
		});
		modelSP.addColumn("ID sản phẩm");
		modelSP.addColumn("Tên sản phẩm");
		modelSP.addColumn("Giá bán");
		modelSP.addColumn("Giá KM");
		modelSP.addColumn("Áp dụng");
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
        btnThem.addActionListener(this);
        tableKM.addMouseListener(this);
        txtTimKiemKM.addKeyListener(this);
        txtTimKiemSP.addKeyListener(this);
        loadSP();
        loadData();
	}

	private void loadData() {
		modelKM.setRowCount(0);
		for (KhuyenMai km : daoKM.getAllDanhSachKM() ) {
			modelKM.addRow(new Object[] { km.getId(), km.getTen(),dfNgayBD.format(km.getNgayBatDau()), km.getTrangThai()?"Đang áp dụng":"Dừng áp dụng",km.getLoai()
					});
			
		}
	}
	private void loadSP() {
		modelSP.setRowCount(0);
		for (SanPhamCon sp : daoQLSP.getAllSanPhamLoadData() ) {
			modelSP.addRow(new Object[] { sp.getIdSanPham(), sp.getTenSanPham(),sp.giaBan(), 
					});
			
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
			else if (o.equals(txtTimKiemSP)) {
				DefaultTableModel model = (DefaultTableModel) tableSP.getModel();
				TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(model);
				tableSP.setRowSorter(tr);
				tr.setRowFilter(RowFilter.regexFilter("(?i)" + txtTimKiemSP.getText().trim(), 1));
			}
		});
	}
	private void updateProductPrice(String idSP, double giaKM) {
        try {
            // Cập nhật giá bán trong cơ sở dữ liệu
        	ConnectDB.getinstance();
    		
    		Connection con = ConnectDB.getConnection();
    		
            String updateQuery = "UPDATE SanPham SET giaKhuyenMai = ? WHERE idSanPham = ?";
            try (PreparedStatement preparedStatement = con.prepareStatement(updateQuery)) {
                preparedStatement.setDouble(1, giaKM);
                preparedStatement.setString(2, idSP);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	private void ThemSPKM(String idSP, String idKM) {
	    try {
	    	ConnectDB.getinstance();
    		
    		Connection con = ConnectDB.getConnection();
    		
	        String insertQuery = "INSERT INTO ApDungKhuyenMai (idSanPham, idKM) VALUES (?, ?)";
	        try (PreparedStatement preparedStatement = con.prepareStatement(insertQuery)) {
	            preparedStatement.setString(1, idSP);
	            preparedStatement.setString(2, idKM );
	            preparedStatement.executeUpdate();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	private  void XoaSPKM(String idSP) {
	    try {
	        String deleteQuery = "DELETE FROM ApDungKhuyenMai WHERE idSanPham = ?";
	        ConnectDB.getinstance();
    		
    		Connection con = ConnectDB.getConnection();
	        try (PreparedStatement preparedStatement = con.prepareStatement(deleteQuery)) {
	            preparedStatement.setString(1, idSP);
	            preparedStatement.executeUpdate();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	
}