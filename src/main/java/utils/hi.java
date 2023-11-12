//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Cursor;
//import java.awt.Dimension;
//import java.awt.Font;
//import java.awt.GridLayout;
//
//import javax.swing.BorderFactory;
//import javax.swing.ButtonGroup;
//import javax.swing.JButton;
//import javax.swing.JComboBox;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JRadioButton;
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
//import javax.swing.JTextField;
//import javax.swing.border.EmptyBorder;
//import javax.swing.border.LineBorder;
//import javax.swing.table.DefaultTableModel;
//
//import com.toedter.calendar.JDateChooser;
//
//import connection.ConnectDB;
//
//JPanel NhanVienPanel=new JPanel();
//		NhanVienPanel.setLayout(new BorderLayout());
//		NhanVienPanel.setBorder(new EmptyBorder(10,10,10,10));
//		
//		
//		JPanel PanelNouth =new JPanel();
//		
//		add(NhanVienPanel);
//		NhanVienPanel.add(PanelNouth,BorderLayout.NORTH);
//		PanelNouth.add(lblTieuDe);
//		
//		JPanel pnCenter = new JPanel();
//		pnCenter.setLayout(new GridLayout(5,10, 10, 10));
//		pnCenter.setBorder(BorderFactory.createTitledBorder("Nhập thông tin nhân viên"));
//		
//        
//		cbTimKiem = new JComboBox<Object>(new Object[] { "Tìm kiếm theo ID", "Tìm kiếm theo tên" });
//		
//	    
//        
//	    
//		pnCenter.add(lbID);
//	    pnCenter.add(txtID);
//		pnCenter.add(lbTenNV);
//        pnCenter.add(txtTenNV);
//        pnCenter.add(lbsdt);
//        pnCenter.add(txtsdt);
//        pnCenter.add(lbEmail);
//        pnCenter.add(txtEmail);
//        pnCenter.add(lbDiaChi);
//        pnCenter.add(txtDiaChi);
//      
//        pnCenter.add(lbNgaySinh);
//        pnCenter.add(chooserNgaySinh);
//        pnCenter.add(lbChucVu);
//        pnCenter.add(cbChucVu);
//        pnCenter.add(lbTrangThai);
//        pnCenter.add(cbTrangThai);
//        pnCenter.add(lbGioiTinh);
//       
//        
//		
//        
//      
//        
//       
//        
//        
//        JPanel pnChucNang = new JPanel(new GridLayout(1,6,10,40));
//        
//        
//        
//        btnThemNV=new JButton("THÊM NHÂN VIÊN");
//        btnCapNhatNV=new JButton("CẬP NHẬT");
//        btnXoaNV=new JButton("XÓA NHÂN VIÊN");
//        btnLamMoi=new JButton("LÀM MỚI");
//        btnXemTatCa=new JButton("XEM TẤT CẢ");
//        
//        pnChucNang.add(btnThemNV);
//        pnChucNang.add(btnCapNhatNV);
//        pnChucNang.add(btnXoaNV);
//        pnChucNang.add(btnLamMoi);
//        pnChucNang.add(lbTimKiem);
//        pnChucNang.add(txtTimKiem);
//        pnChucNang.add(btnXemTatCa);
//        
//        //Tạo bảng
//      
//        JPanel PanelMain= new JPanel();
//        PanelMain.setLayout(new BorderLayout());
//        		
//        PanelMain.add(pnCenter,BorderLayout.NORTH);
//        PanelMain.add(pnChucNang,BorderLayout.CENTER);
//        PanelMain.add(scrollPane,BorderLayout.SOUTH);
//        NhanVienPanel.add(PanelMain,BorderLayout.SOUTH);
//        
