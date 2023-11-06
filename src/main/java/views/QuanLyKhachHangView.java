package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicSplitPaneUI.KeyboardHomeHandler;
import javax.swing.table.DefaultTableModel;

import org.codehaus.plexus.util.dag.DAG;

import com.toedter.calendar.JDateChooser;

import connection.ConnectDB;
import controllers.MenuItem;
import dao.DAOKhachHang;
import dao.DAOQuanLySanPham;
import models.KhachHang;
import models.LoaiSanPham;


public class QuanLyKhachHangView extends JPanel implements ActionListener,MouseListener{
	private JDateChooser chooserNgaySinh;
	private JTextField txtTenKH;
	private JTextField txtsdt;
	private JTextField txtEmail;
	private JTextField txtDiaChi;
	
	private JTextField txtId;
	
	
	
	private JTextField txtTimKiem;
	private JLabel lbTenKH;
	private JLabel lbsdt;
	private JLabel lbid;
	private JLabel lbEmail;
	private JLabel lbDiaChi;
	private JLabel lbNgaySinh;
	private JLabel lbGioiTinh;
	
	
	private JLabel lbTimKiem;
	private JRadioButton rbNam;
	private JRadioButton rbNu;
	
	
	
	private DefaultTableModel modelKhachHang;
	private JButton btnThemKH;
	private JButton btnCapNhatKH;
	private JButton btnXoaKH;
	private JButton btnLamMoi;
	private JButton btnTimKiem;
	private ButtonGroup groupGT;
	private SimpleDateFormat dfNgaySinh;
	private DAOKhachHang daoKhachHang;
	private JTable tableKH;
	private JComboBox<Object> cbTimKiem;
	public QuanLyKhachHangView() {
		dfNgaySinh = new SimpleDateFormat("dd/MM/yyyy");
		daoKhachHang=new DAOKhachHang();
		KhachHang kh=new KhachHang();
		setLayout(new BorderLayout());
////		Tiêu đề
		JPanel NhanVienPanel=new JPanel();
		NhanVienPanel.setLayout(new BorderLayout());
		NhanVienPanel.setBorder(new EmptyBorder(10,10,10,10));
		
		JLabel lblTieuDe = new JLabel("QUẢN LÝ KHÁCH HÀNG");
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 30));
		lblTieuDe.setForeground(Color.black);
		JPanel PanelNouth =new JPanel();
		
		add(NhanVienPanel);
		NhanVienPanel.add(PanelNouth,BorderLayout.NORTH);
		PanelNouth.add(lblTieuDe);
		
		JPanel pnCenter = new JPanel();
		pnCenter.setLayout(new GridLayout(5,10, 10, 10));
		pnCenter.setBorder(BorderFactory.createTitledBorder("Nhập thông tin khách hàng"));
		

        lbTenKH = new JLabel("Tên khách hàng: ");
        txtTenKH = new JTextField();
        lbsdt = new JLabel("Số điện thoại: ");
        txtsdt = new JTextField();
        lbEmail = new JLabel("Email: ");
        txtEmail = new JTextField();
        
		lbDiaChi = new JLabel("Địa chỉ: ");
	    txtDiaChi = new JTextField();
	    
	    lbGioiTinh=new JLabel("Giới tính: ");
	    lbNgaySinh=new JLabel("Ngày sinh: ");
	  
	    rbNam=new JRadioButton("Nam");
	    rbNu=new JRadioButton("Nữ");
	    cbTimKiem = new JComboBox<Object>(new Object[] { "Tìm kiếm theo ID", "Tìm kiếm theo tên" });
	    chooserNgaySinh = new JDateChooser();
		chooserNgaySinh.getCalendarButton().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chooserNgaySinh.setBounds(100, 310, 200, 40);
		chooserNgaySinh.setDateFormatString("dd/MM/yyyy");	
		chooserNgaySinh.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		chooserNgaySinh.setFont(new Font("SansSerif", Font.PLAIN, 15));
		chooserNgaySinh.getCalendarButton().setPreferredSize(new Dimension(20, 24));
		chooserNgaySinh.getCalendarButton().setBackground(new Color(102, 0, 153));
		chooserNgaySinh.getCalendarButton().setToolTipText("Chọn ngày sinh");
	    
        Box BoxCenter= Box.createVerticalBox();
        Box b1,b2,b3,b4,b5,b6,b7;
        b1=Box.createHorizontalBox();
        b1.add(lbTenKH);
        b1.add(txtTenKH);
        b1.add(Box.createHorizontalStrut(20));
        b1.add(lbsdt);
        b1.add(txtsdt);
        b2=Box.createHorizontalBox();
        b2.add(lbEmail);
        b2.add(txtEmail);
        b2.add(Box.createHorizontalStrut(20));
        b2.add(lbDiaChi);
        b2.add(txtDiaChi);
        b3=Box.createHorizontalBox();
       
        JTextField txtGT=new JTextField();
        b6=Box.createHorizontalBox();
        b6.add(Box.createHorizontalStrut(420));
        b4=Box.createHorizontalBox();

        
        b7=Box.createHorizontalBox();
        lbid=new JLabel("ID nhân viên:");
        txtId=new JTextField();
        txtId.setEditable(false);
        b7.add(lbid);
        b7.add(txtId);
        
        b4.add(lbNgaySinh);
        b4.add(chooserNgaySinh);
        b5=Box.createHorizontalBox();
        b5.add(lbGioiTinh);
        b5.add(Box.createHorizontalStrut(20));
        b5.add(rbNam);
        b5.add(rbNu);
        b3.add(b4);
        b3.add(Box.createHorizontalStrut(20));
        b3.add(b5);
        b3.add(b6);
        lbEmail.setPreferredSize(lbTenKH.getPreferredSize());
		lbDiaChi.setPreferredSize(lbsdt.getPreferredSize());
		lbGioiTinh.setPreferredSize(lbsdt.getPreferredSize());
		lbNgaySinh.setPreferredSize(lbTenKH.getPreferredSize());
		lbid.setPreferredSize(lbTenKH.getPreferredSize());
	    BoxCenter.add(Box.createVerticalStrut(10));
        BoxCenter.add(b1);
        BoxCenter.add(Box.createVerticalStrut(10));
        BoxCenter.add(b2);
        BoxCenter.add(Box.createVerticalStrut(10));
        BoxCenter.add(b3);
        BoxCenter.add(Box.createVerticalStrut(10));
        BoxCenter.add(b7);
        BoxCenter.setBorder(BorderFactory.createTitledBorder("Nhập thông tin khách hàng"));
		
        //Tạo button groud giới tính
        groupGT = new ButtonGroup();
        groupGT.add(rbNam);
        groupGT.add(rbNu);
        rbNam.setActionCommand("Nam");
        rbNu.setActionCommand("Nữ");
        
        lbTimKiem=new JLabel("Tìm kiếm nhân viên:");
        txtTimKiem=new JTextField();
        
        
        JPanel pnChucNang = new JPanel(new GridLayout(2,6,10,40));
        JPanel pnChucNang1 = new JPanel(new GridLayout(1,6,10,40));
        JPanel pnChucNang2 = new JPanel(new GridLayout(1,6,10,40));
        btnThemKH=new JButton("THÊM KHÁCH HÀNG");
        btnCapNhatKH=new JButton("CẬP NHẬT THÔNG TIN KHÁCH HÀNG");
        btnXoaKH=new JButton("XÓA KHÁCH HÀNG");
        btnLamMoi=new JButton("LÀM MỚI");
        btnTimKiem=new JButton("TÌM");
        pnChucNang1.add(btnThemKH);
        pnChucNang1.add(btnCapNhatKH);
        pnChucNang1.add(btnXoaKH);
        pnChucNang1.add(btnLamMoi);
        pnChucNang2.add(cbTimKiem);
        pnChucNang2.add(lbTimKiem);
        pnChucNang2.add(txtTimKiem);
        pnChucNang2.add(btnTimKiem);
        pnChucNang.add(pnChucNang1);
        pnChucNang.add(pnChucNang2);
       //Tạo bảng
        modelKhachHang = new DefaultTableModel();
		tableKH = new JTable();
        modelKhachHang.addColumn("ID KhachHang");
		modelKhachHang.addColumn("Tên khách hàng");
		modelKhachHang.addColumn("Số điện thoại");
		modelKhachHang.addColumn("Email");
		modelKhachHang.addColumn("Địa chỉ");
		modelKhachHang.addColumn("Ngày sinh");
		modelKhachHang.addColumn("Giới tính");
       
        tableKH.setModel(modelKhachHang);
        JScrollPane scrollPane = new JScrollPane(tableKH);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh sách khách hàng"));
       
        JPanel PanelMain= new JPanel();
        PanelMain.setLayout(new BorderLayout());
    	PanelMain.add(BoxCenter,BorderLayout.NORTH);
        PanelMain.add(pnChucNang,BorderLayout.CENTER);
        PanelMain.add(scrollPane,BorderLayout.SOUTH);
        NhanVienPanel.add(PanelMain,BorderLayout.SOUTH);
    
        btnLamMoi.addActionListener(this);
		btnThemKH.addActionListener(this);
		btnCapNhatKH.addActionListener(this);
		btnXoaKH.addActionListener(this);
		btnTimKiem.addActionListener(this);
		tableKH.addMouseListener(this);
		try {
			ConnectDB.getinstance().connect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		loadData();
        
	}
	private void ThemKH() throws SQLException {
		String idKhachHang = autoID();
		String tenKhachHang = txtTenKH.getText();
		String email= txtEmail.getText();
		String sdt=txtsdt.getText();
		String diaChi=txtDiaChi.getText();
		java.util.Date date = chooserNgaySinh.getDate();
		Date ngaySinh = new Date(date.getYear(), date.getMonth(), date.getDate());
		Boolean GioiTinh=rbNam.isSelected();
		
		KhachHang kh=new KhachHang(idKhachHang, tenKhachHang, sdt, email, diaChi,ngaySinh,GioiTinh);
		daoKhachHang.themKhachHang(kh);
		modelKhachHang.addRow(new Object[] {idKhachHang, tenKhachHang, sdt, email, diaChi,dfNgaySinh.format(kh.getNgaySinh()),kh.isGioiTinh()?"Nam":"Nữ" });

	}
	private void loadData() {
		modelKhachHang.setRowCount(0);
		for (KhachHang kh : daoKhachHang.getAllDanhSachKH() ) {
			modelKhachHang.addRow(new Object[] { kh.getIdKhachHang(), kh.getTenKhachHang(),kh.getSdt(), kh.getEmail(),kh.getDiaChi(),dfNgaySinh.format(kh.getNgaySinh()),kh.isGioiTinh()?"Nam":"Nữ"
					});
			
		}
	}
	private void XoaDuLieuTable() {
		DefaultTableModel dm = (DefaultTableModel) tableKH.getModel();
		dm.getDataVector().removeAllElements();
	}
	private void loadDanhSachTimKiem(ArrayList<KhachHang> lstKH) {
		XoaDuLieuTable();
		
		for (KhachHang kh : lstKH) {
			
			modelKhachHang.addRow(new Object[] { kh.getIdKhachHang(), kh.getTenKhachHang(),kh.getSdt(), kh.getEmail(),kh.getDiaChi(),dfNgaySinh.format(kh.getNgaySinh()),kh.isGioiTinh()?"Nam":"Nữ"
			});
		}
	}
	private void timTheoID() {
		String idTim = txtTimKiem.getText().trim();
		
		ArrayList<KhachHang> lstKH = new ArrayList<KhachHang>() ;
		lstKH=daoKhachHang.getMa(idTim);
		if(idTim.equals("")) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập ID cần tìm", "Thông báo",
					JOptionPane.ERROR_MESSAGE);
		}
		else {
			if(lstKH.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng có mã "+idTim, "Thông báo",
						JOptionPane.ERROR_MESSAGE);
				txtTimKiem.requestFocus();
				txtTimKiem.selectAll();
			}
			
			else {
				loadDanhSachTimKiem(lstKH);
				
			}
		}
	}
	private void timTheoTen() {
		String tenTim = txtTimKiem.getText().trim();
		
		ArrayList<KhachHang> lstKH = new ArrayList<KhachHang>() ;
		lstKH=daoKhachHang.getTen(tenTim);
		if(tenTim.equals("")) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập tên cần tìm", "Thông báo",
					JOptionPane.ERROR_MESSAGE);
		}
		else {
			if(lstKH.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng có tên "+tenTim, "Thông báo",
						JOptionPane.ERROR_MESSAGE);
				txtTimKiem.requestFocus();
				txtTimKiem.selectAll();
			}
			
			else {
				loadDanhSachTimKiem(lstKH);
				
			}
		}
	}
	private String autoID() throws SQLException {
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	        java.util.Date date = new java.util.Date();
			
			  ConnectDB.getinstance();
			  Connection con = ConnectDB.getConnection();
			  String query = "SELECT MAX(idKhachHang) FROM KhachHang";
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
	              newID = "KH" + dateFormat.format(date) + String.format("%4d", number).replace(" ", "0");
	          } else {
	              newID = "KH" + dateFormat.format(date) + "0001";
	          }
	
	          preparedStatement.close();
	         return newID;
	     
	}
	private void CapNhatKH() {
		String id = txtId.getText();
		String ten = txtTenKH.getText();
		String diaChi = txtDiaChi.getText();
		String soDienThoai = txtsdt.getText();
		String email = txtEmail.getText();
	
	
		java.util.Date date = chooserNgaySinh.getDate();
		Date ngaySinh = new Date(date.getYear(), date.getMonth(), date.getDate());
		Boolean gioiTinh=null;
		if(rbNam.isSelected()) {
			gioiTinh=true;
		}
		if(rbNu.isSelected()) {
			gioiTinh=false;
		}
		KhachHang kh=new KhachHang();
		kh.setTenKhachHang(ten);
		kh.setSdt(soDienThoai);
		kh.setNgaySinh(ngaySinh);
		kh.setEmail(email);
		kh.setDiaChi(diaChi);
		kh.setGioiTinh(gioiTinh);
		kh.setIdKhachHang(id);

		try {
			daoKhachHang.updateKhachHang(kh);
			loadData();
			JOptionPane.showMessageDialog(this, "Cập nhật thành công");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Cập nhật thất bại");
		}
}
	private void xoaKH() {
		int row = tableKH.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(this, "Phải chọn dòng cần xoá");
		} else {
			try {
				int HopThoai = JOptionPane.showConfirmDialog(this, "Bạn có muốn xoá dòng này không?", "Cảnh báo",
						JOptionPane.YES_NO_OPTION);
				if (HopThoai == JOptionPane.YES_OPTION) {
					modelKhachHang.removeRow(row);
					String manv = txtId.getText();
					daoKhachHang.DeleteKH(manv);
					JOptionPane.showMessageDialog(this, "Xoá khách hàng thành công");
				}
			} catch (Exception e2) {
				e2.printStackTrace();
				JOptionPane.showMessageDialog(this, "Xoá khách hàng thất bại");
			}
		}
	}
	private void lamMoi() {
		loadData();
		txtId.setText("");
		txtTenKH.setText("");
		txtDiaChi.setText("");
		txtsdt.setText("");
		txtEmail.setText("");
		rbNam.setSelected(true);
		rbNu.setSelected(false);
		txtTenKH.requestFocus();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnThemKH)) {
			try {
				ThemKH();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} 
		 if(o.equals(btnLamMoi)) {
			 lamMoi() ;
		} else if (o.equals(btnXoaKH)) {
			xoaKH();
		}
		 if (o.equals(btnCapNhatKH)) {
			CapNhatKH();
		}
		 if (o.equals(btnTimKiem)) {
			if(cbTimKiem.getSelectedItem().toString()=="Tìm kiếm theo ID")
			{
				timTheoID();
			}
			if(cbTimKiem.getSelectedItem().toString()=="Tìm kiếm theo tên")
			{
				timTheoTen();
			}
			}
//			timNV();
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int row = tableKH.getSelectedRow();
		txtId.setText(modelKhachHang.getValueAt(row, 0).toString());
		txtTenKH.setText(modelKhachHang.getValueAt(row, 1).toString());
		txtsdt.setText(modelKhachHang.getValueAt(row, 2).toString());
		txtEmail.setText(modelKhachHang.getValueAt(row, 3).toString());
		txtDiaChi.setText(modelKhachHang.getValueAt(row, 4).toString());
		String ngaySinh=modelKhachHang.getValueAt(row, 5).toString();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date valueNgaySinh=null;
		try {
			valueNgaySinh = dateFormat.parse(ngaySinh);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        chooserNgaySinh.setDate(valueNgaySinh);
		rbNam.setSelected(modelKhachHang.getValueAt(row, 6).toString()=="Nam");
		rbNu.setSelected(modelKhachHang.getValueAt(row,6).toString()=="Nữ");
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

}





