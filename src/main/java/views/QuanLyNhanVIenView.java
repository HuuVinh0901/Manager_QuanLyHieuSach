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
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;

import connection.ConnectDB;
import controllers.MenuItem;
import dao.DAOKhachHang;
import dao.DAONhanVien;
import models.KhachHang;
import models.NhanVien;

public class QuanLyNhanVIenView extends JPanel implements ActionListener,MouseListener,KeyListener{
	private JDateChooser chooserNgaySinh;
	private JTextField txtTenNV;
	private JTextField txtsdt;
	private JTextField txtEmail;
	private JTextField txtDiaChi;
	
	
	
	private JTextField txtTimKiem;
	private JTextField txtID;
	private JLabel lbTenNV;
	private JLabel lbsdt;
	private JLabel lbEmail;
	private JLabel lbDiaChi;
	private JLabel lbNgaySinh;
	private JLabel lbGioiTinh;
	private JLabel lbID;
	
	private JLabel lbTimKiem;
	private JRadioButton rbNam;
	private JRadioButton rbNu;
	private JComboBox<Object> cbTrangThai,cbChucVu,cbTimKiem;
	private JLabel lbChucVu;
	private JLabel lbTrangThai;
	private JTable tableNhanVien;
	private DefaultTableModel modelNhanVien;
	private JButton btnThemNV;
	private JButton btnCapNhatNV;
	private JButton btnXoaNV;
	private JButton btnLamMoi;
	private JButton btnXemTatCa;
	private SimpleDateFormat dfNgaySinh;
	private DAONhanVien daoNhanVien;
	public QuanLyNhanVIenView() {
		dfNgaySinh = new SimpleDateFormat("dd/MM/yyyy");
		daoNhanVien=new DAONhanVien();
		NhanVien nv=new NhanVien();
		setLayout(new BorderLayout());
////		Tiêu đề
		JPanel NhanVienPanel=new JPanel();
		NhanVienPanel.setLayout(new BorderLayout());
		NhanVienPanel.setBorder(new EmptyBorder(10,10,10,10));
		
		JLabel lblTieuDe = new JLabel("QUẢN LÝ NHÂN VIÊN");
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 30));
		lblTieuDe.setForeground(Color.black);
		JPanel PanelNouth =new JPanel();
		
		add(NhanVienPanel);
		NhanVienPanel.add(PanelNouth,BorderLayout.NORTH);
		PanelNouth.add(lblTieuDe);
		
		JPanel pnCenter = new JPanel();
		pnCenter.setLayout(new GridLayout(5,10, 10, 10));
		pnCenter.setBorder(BorderFactory.createTitledBorder("Nhập thông tin nhân viên"));
		
        lbID = new JLabel("ID nhân viên:");
        

        lbTenNV = new JLabel("Tên nhân viên:");
        txtTenNV = new JTextField();
        lbsdt = new JLabel("Số điện thoại:");
        txtsdt = new JTextField();
        lbEmail = new JLabel("Email:");
        txtEmail = new JTextField();
        cbChucVu = new JComboBox<Object>(new Object[] {  "Nhân viên" });
		cbTrangThai = new JComboBox<Object>(new Object[] { "Đang làm việc", "Đã nghỉ việc" });
		cbTimKiem = new JComboBox<Object>(new Object[] { "Tìm kiếm theo ID", "Tìm kiếm theo tên" });
		lbDiaChi = new JLabel("Địa chỉ:");
	    txtDiaChi = new JTextField();
	    lbChucVu = new JLabel("Chức vụ:");
	    lbTrangThai = new JLabel("Trạng thái:");
	    lbGioiTinh=new JLabel("Giới tính");
	    lbNgaySinh=new JLabel("Ngày sinh:");
	    txtID=new JTextField();
	    txtID.setEditable(false);
	    
        
	    chooserNgaySinh = new JDateChooser();
		chooserNgaySinh.getCalendarButton().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chooserNgaySinh.setBounds(100, 310, 200, 40);
		chooserNgaySinh.setDateFormatString("dd/MM/yyyy");
		chooserNgaySinh.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		chooserNgaySinh.setFont(new Font("SansSerif", Font.PLAIN, 15));
		chooserNgaySinh.getCalendarButton().setPreferredSize(new Dimension(30, 24));
		chooserNgaySinh.getCalendarButton().setBackground(new Color(102, 0, 153));
		chooserNgaySinh.getCalendarButton().setToolTipText("Chọn ngày sinh");
		pnCenter.add(lbID);
	    pnCenter.add(txtID);
		pnCenter.add(lbTenNV);
        pnCenter.add(txtTenNV);
        pnCenter.add(lbsdt);
        pnCenter.add(txtsdt);
        pnCenter.add(lbEmail);
        pnCenter.add(txtEmail);
        pnCenter.add(lbDiaChi);
        pnCenter.add(txtDiaChi);
      
        pnCenter.add(lbNgaySinh);
        pnCenter.add(chooserNgaySinh);
        pnCenter.add(lbChucVu);
        pnCenter.add(cbChucVu);
        pnCenter.add(lbTrangThai);
        pnCenter.add(cbTrangThai);
        pnCenter.add(lbGioiTinh);
        pnCenter.add(rbNam=new JRadioButton("Nam"));
        pnCenter.add(rbNu=new JRadioButton("Nữ"));
        
		
        
        //Tạo button groud giới tính
        ButtonGroup groupGT = new ButtonGroup();
        groupGT.add(rbNam);
        groupGT.add(rbNu);
        
        lbTimKiem=new JLabel("Tìm kiếm nhân viên:");
        txtTimKiem=new JTextField();
        
        
        JPanel pnChucNang = new JPanel(new GridLayout(1,6,10,40));
        
        
        
        btnThemNV=new JButton("THÊM NHÂN VIÊN");
        btnCapNhatNV=new JButton("CẬP NHẬT");
        btnXoaNV=new JButton("XÓA NHÂN VIÊN");
        btnLamMoi=new JButton("LÀM MỚI");
        btnXemTatCa=new JButton("XEM TẤT CẢ");
        
        pnChucNang.add(btnThemNV);
        pnChucNang.add(btnCapNhatNV);
        pnChucNang.add(btnXoaNV);
        pnChucNang.add(btnLamMoi);
        pnChucNang.add(lbTimKiem);
        pnChucNang.add(txtTimKiem);
        pnChucNang.add(btnXemTatCa);
        
        //Tạo bảng
        modelNhanVien = new DefaultTableModel();
		tableNhanVien = new JTable();
        modelNhanVien.addColumn("ID nhân viên");
		modelNhanVien.addColumn("Tên nhân viên");
		modelNhanVien.addColumn("Số điện thoại");
		modelNhanVien.addColumn("Email");
		modelNhanVien.addColumn("Địa chỉ");
		modelNhanVien.addColumn("Ngày sinh");
		modelNhanVien.addColumn("Giới tính");
		modelNhanVien.addColumn("Chức vụ");
		modelNhanVien.addColumn("Trạng thái");
		
       
        tableNhanVien.setModel(modelNhanVien);
        JScrollPane scrollPane = new JScrollPane(tableNhanVien);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh sách nhân viên"));
        JPanel PanelMain= new JPanel();
        PanelMain.setLayout(new BorderLayout());
        		
        PanelMain.add(pnCenter,BorderLayout.NORTH);
        PanelMain.add(pnChucNang,BorderLayout.CENTER);
        PanelMain.add(scrollPane,BorderLayout.SOUTH);
        NhanVienPanel.add(PanelMain,BorderLayout.SOUTH);
        
        btnLamMoi.addActionListener(this);
		btnThemNV.addActionListener(this);
		btnCapNhatNV.addActionListener(this);
		btnXoaNV.addActionListener(this);
		btnXemTatCa.addActionListener(this);
		tableNhanVien.addMouseListener(this);
		txtTimKiem.addKeyListener(this);
		txtTenNV.addKeyListener(this);
		txtDiaChi.addKeyListener(this);
		txtEmail.addKeyListener(this);
		txtsdt.addKeyListener(this);
		
		try {
			ConnectDB.getinstance().connect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		loadData();

	}
	private void ThemNV() throws SQLException {
		String idKhachHang = autoID();
		String tenKhachHang = txtTenNV.getText();
		String email= txtEmail.getText();
		String sdt=txtsdt.getText();
		String diaChi=txtDiaChi.getText();
		java.util.Date date = chooserNgaySinh.getDate();
		Date ngaySinh = new Date(date.getYear(), date.getMonth(), date.getDate());
		Boolean GioiTinh=rbNam.isSelected();
		String TrangThai=(String)cbTrangThai.getSelectedItem().toString();
		Boolean TrangThaibooleanValue = Boolean.parseBoolean(TrangThai);
		String chucVu=cbChucVu.getSelectedItem().toString();
		
		NhanVien nv=new NhanVien(idKhachHang, tenKhachHang, sdt, diaChi, email, ngaySinh, GioiTinh,chucVu,TrangThaibooleanValue);
		daoNhanVien.themNhanVien(nv);
		modelNhanVien.addRow(new Object[] {idKhachHang, tenKhachHang, sdt,  diaChi,email,dfNgaySinh.format(nv.getNgaySinh()),nv.isGioiTinh()?"Nam":"Nữ",chucVu,TrangThai });

	}
	private void loadData() {
		modelNhanVien.setRowCount(0);
		for (NhanVien nv : daoNhanVien.getAllDanhSachNV() ) {
			modelNhanVien.addRow(new Object[] { nv.getIdNhanVien(), nv.getTenNhanVien(),nv.getSoDienThoai(), nv.getEmail(),nv.getDiaChi(),dfNgaySinh.format(nv.getNgaySinh()),nv.isGioiTinh()?"Nam":"Nữ",nv.getChucVu(),nv.isTrangThai()?"Đang làm việc":"Đã nghỉ việc"
					});
			
		}
	}
	private void CapNhatNV() {
		String id = txtID.getText();
		String ten = txtTenNV.getText();
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
		String trangThaiValue=cbTrangThai.getSelectedItem().toString();
		Boolean trangThai=null;
		if(trangThaiValue.equals("Đang làm việc")) {
			trangThai=true;
		}
		if(trangThaiValue.equals("Đã nghỉ việc")) {
			trangThai=false;
		}
		String chucVu =cbChucVu.getSelectedItem().toString();
		NhanVien nv=new NhanVien();
		nv.setTenNhanVien(ten);
		nv.setSoDienThoai(soDienThoai);
		nv.setNgaySinh(ngaySinh);
		nv.setEmail(email);
		nv.setDiaChi(diaChi);
		nv.setGioiTinh(gioiTinh);
		nv.setIdNhanVien(id);
		nv.setTrangThai(trangThai);
		nv.setChucVu(chucVu);
		if(valiDate()) {
			try {
				daoNhanVien.updateNhanVien(nv);;
				loadData();
				JOptionPane.showMessageDialog(this, "Cập nhật thành công");
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Cập nhật thất bại");
			}
		}
		
}
private String autoID() throws SQLException {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        java.util.Date date = new java.util.Date();
		
		  ConnectDB.getinstance();
		  Connection con = ConnectDB.getConnection();
		  String query = "SELECT MAX(idNhanVien) FROM NhanVien";
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
              newID = "NV" + dateFormat.format(date) + String.format("%4d", number).replace(" ", "0");
          } else {
              newID = "NV" + dateFormat.format(date) + "0001";
          }

          preparedStatement.close();
         return newID;
     
	}
private void xoaNV() {
	int row = tableNhanVien.getSelectedRow();
	if (row == -1) {
		JOptionPane.showMessageDialog(this, "Phải chọn dòng cần xoá");
	} else {
		try {
			int HopThoai = JOptionPane.showConfirmDialog(this, "Bạn có muốn xoá dòng này không?", "Cảnh báo",
					JOptionPane.YES_NO_OPTION);
			if (HopThoai == JOptionPane.YES_OPTION) {
				modelNhanVien.removeRow(row);
				String manv = txtID.getText();
				daoNhanVien.DeleteNV(manv);
				JOptionPane.showMessageDialog(this, "Xoá nhân viên thành công");
			}
		} catch (Exception e2) {
			e2.printStackTrace();
			JOptionPane.showMessageDialog(this, "Xoá nhân viên thất bại");
		}
	}
}





public boolean valiDate() {
	
	String ten = txtTenNV.getText().trim();
	
	Boolean gioiTinh=null;
	if(rbNam.isSelected() || rbNu.isSelected()) {
		gioiTinh=true;
	}
	else {
		gioiTinh=false;
	}
	java.util.Date ngaySinh = chooserNgaySinh.getDate();
	String diaChi = txtDiaChi.getText().trim();
	String email = txtEmail.getText().trim();
	String soDienThoai = txtsdt.getText().trim();
	
	
	if(ten.equals("") || diaChi.equals("")|| email.equals("") || soDienThoai.equals("")) {
		JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin đầy đủ!", "Thông báo",
				JOptionPane.WARNING_MESSAGE);
		txtTenNV.requestFocus();
		
		return false;
	}
	if (!(ten.length() > 0 && ten.matches("^[A-Z][a-z]+( [A-Z][a-z]+)*$"))) {
		JOptionPane.showMessageDialog(this, "Tên phải viết hoa và không chứa số", "Thông báo",
				JOptionPane.WARNING_MESSAGE);
		txtTenNV.selectAll();
		txtTenNV.requestFocus();
		return false;
	}
//
	if (!(diaChi.length() > 0 && diaChi.matches("^[A-Za-z0-9/,\\s]*[A-Za-z]+[A-Za-z0-9/,\\s]*$"))) {
		JOptionPane.showMessageDialog(this, "Địa chỉ không được chứa toàn số và kí tự đặc biệt", "Thông báo",
				JOptionPane.WARNING_MESSAGE);
		txtDiaChi.requestFocus();
		txtDiaChi.selectAll();
		return false;
	}
	if (!(email.length() > 0 && email.matches("^[A-Za-z][A-Za-z0-9@.]*$"))) {
		JOptionPane.showMessageDialog(this, "Email phải bắt đầu bằng chữ và không được chứa kí tự đặc biệt", "Thông báo",
				JOptionPane.WARNING_MESSAGE);
		txtEmail.requestFocus();
		txtEmail.selectAll();
		return false;
	}
	if (!(ngaySinh!=null  && (ngaySinh.before(new java.util.Date())))) {
		JOptionPane.showMessageDialog(this, "Ngày sinh phải trước ngày hiện tại", "Thông báo",
				JOptionPane.WARNING_MESSAGE);
		chooserNgaySinh.requestFocus();
		
		return false;
	}
	if(!(soDienThoai.length()>0 && soDienThoai.matches("^(0|\\+84)[0-9]{9}$")))
	{
		JOptionPane.showMessageDialog(this, "Số điện thoại gồm 10 số và bắt đầu bằng 0 hoặc +84", "Thông báo",
				JOptionPane.WARNING_MESSAGE);
		txtsdt.requestFocus();
		txtsdt.selectAll();
		return false;
	}
	if(gioiTinh=false)
	{
		JOptionPane.showMessageDialog(this, "Vui lòng chọn giới tính", "Thông báo",
				JOptionPane.WARNING_MESSAGE);
		
		return false;
	}
	return true;
}
private void lamMoi() {
		
		txtID.setText("");
		txtTenNV.setText("");
		txtDiaChi.setText("");
		txtsdt.setText("");
		txtEmail.setText("");
		rbNam.setSelected(true);
		rbNu.setSelected(false);
		cbChucVu.setSelectedItem("Quản lý");
		cbTrangThai.setSelectedItem("Đang làm việc");
		
		chooserNgaySinh.setDate(new java.util.Date());
		txtTenNV.requestFocus();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnThemNV)) {
			if(valiDate()) {
				try {
					ThemNV();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		} 
		 if(o.equals(btnLamMoi)) {
			 lamMoi() ;}
		 if(o.equals(btnXoaNV)) {
			 xoaNV();
		 }
		 if(o.equals(btnCapNhatNV)) {
			 CapNhatNV();
		 }
		 
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tableNhanVien.getSelectedRow();
		txtID.setText(modelNhanVien.getValueAt(row, 0).toString());
		txtTenNV.setText(modelNhanVien.getValueAt(row, 1).toString());
		txtsdt.setText(modelNhanVien.getValueAt(row, 2).toString());
		txtEmail.setText(modelNhanVien.getValueAt(row, 3).toString());
		txtDiaChi.setText(modelNhanVien.getValueAt(row, 4).toString());
		String ngaySinh=modelNhanVien.getValueAt(row, 5).toString();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date valueNgaySinh=null;
		try {
			valueNgaySinh = dateFormat.parse(ngaySinh);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		chooserNgaySinh.setDate(valueNgaySinh);
		rbNam.setSelected(modelNhanVien.getValueAt(row, 6).toString()=="Nam");
		rbNu.setSelected(modelNhanVien.getValueAt(row,6).toString()=="Nữ");
		cbChucVu.setSelectedItem(modelNhanVien.getValueAt(row,7).toString());
		cbTrangThai.setSelectedItem(modelNhanVien.getValueAt(row,8).toString());
		
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
		SwingUtilities.invokeLater(() -> {
			Object o = e.getSource();
			if (o.equals(txtTimKiem)) {
				DefaultTableModel model = (DefaultTableModel) tableNhanVien.getModel();
				TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(model);
				tableNhanVien.setRowSorter(tr);
				tr.setRowFilter(RowFilter.regexFilter("(?i)" + txtTimKiem.getText().trim(), 1));
			} else if (e.getKeyCode() == KeyEvent.VK_F5) {
				lamMoi();
				loadData();
	}
		});
	}
}




