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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
import javax.swing.JFileChooser;
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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicSplitPaneUI.KeyboardHomeHandler;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import org.apache.commons.lang3.Validate;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codehaus.plexus.util.dag.DAG;

import com.toedter.calendar.JDateChooser;

import connection.ConnectDB;
import controllers.MenuItem;
import dao.DAOKhachHang;
import dao.DAOQuanLySanPham;
import models.KhachHang;
import models.LoaiSanPham;
import models.NhaCungCap;
import models.NhanVien;
import models.SanPhamCon;
import utils.TrangThaiSPEnum;



public class QuanLyKhachHangView extends JPanel implements MouseListener, KeyListener,ActionListener{
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
	private JTabbedPane tabbedPane;
	private JRadioButton rbNam;
	private JRadioButton rbNu;
	private JButton btnThemKH;
	private JButton btnCapNhatKH;
	private JButton btnXoaKH;
	private JButton btnLamMoi;
	private JButton btnXemTatCa;
	private JButton btnXuatExcel;
	private ButtonGroup groupGT;
	private SimpleDateFormat dfNgaySinh;
	private DAOKhachHang daoKhachHang;
	private DefaultTableModel modelKhachHang;
	private JTable tableKH;
	private JComboBox<Object> cbTimKiem;
	public QuanLyKhachHangView() {
		dfNgaySinh = new SimpleDateFormat("dd/MM/yyyy");
		daoKhachHang=new DAOKhachHang();
		KhachHang kh=new KhachHang();
		setLayout(new BorderLayout());
		this.setBackground(new Color(102,255,255));
///		Tiêu đề
		JPanel pnNouth=new JPanel(new BorderLayout());
		JPanel pnSounth=new JPanel(new BorderLayout());
		JPanel pnTitle=new JPanel();
		JPanel pnInfo=new JPanel(new GridLayout(4,1,10,10));
		JPanel pnChucNang=new JPanel(new GridLayout(1,4,10,10));
		JPanel pnTimKiem=new JPanel(new GridLayout(1,3,10,10));
		
		
		JLabel lblTieuDe = new JLabel("QUẢN LÝ KHÁCH HÀNG");
		lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTieuDe.setForeground(new Color(26, 102, 227));
		pnTitle.add(lblTieuDe);
		pnNouth.add(pnTitle,BorderLayout.NORTH);
		add(pnNouth,BorderLayout.NORTH);
        
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
		lbid=new JLabel("ID nhân viên:");
	    txtId=new JTextField();

	    try {
			txtId.setText(autoID());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    txtId.setEditable(false);
	    //Button giới tính
	    groupGT = new ButtonGroup();
	    groupGT.add(rbNam);
	    groupGT.add(rbNu);
	    rbNam.setActionCommand("Nam");
	    rbNu.setActionCommand("Nữ");
	    lbTimKiem=new JLabel("Tìm kiếm khách hàng:");
	    txtTimKiem=new JTextField();
	    
	    pnInfo.add(lbid);
	    pnInfo.add(txtId);
	    pnInfo.add(lbTenKH);
	    pnInfo.add(txtTenKH);
	    pnInfo.add(lbsdt);
	    pnInfo.add(txtsdt);
	    pnInfo.add(lbEmail);
	    pnInfo.add(txtEmail);
	    pnInfo.add(lbDiaChi);
	    pnInfo.add(txtDiaChi);
	    pnInfo.add(lbNgaySinh);
	    pnInfo.add(chooserNgaySinh);
	    pnInfo.add(lbGioiTinh);
	    pnInfo.add(rbNam);
	    pnInfo.add(rbNu);
	    pnNouth.add(pnInfo,BorderLayout.CENTER);
	    pnInfo.setBorder(BorderFactory.createTitledBorder("Thông tin khách hàng"));
	    btnXoaKH=new JButton("XÓA KHÁCH HÀNG");
	    btnCapNhatKH=new JButton("CẬP NHẬT THÔNG TIN KHÁCH HÀNG");
	    btnLamMoi=new JButton("LÀM MỚI");
	    btnXemTatCa=new JButton("XEM TẤT CẢ");
	    btnXuatExcel=new JButton("XUẤT EXCEL");
	    btnThemKH=new JButton("THÊM KHÁCH HÀNG");
	    ImageIcon iconThem = new ImageIcon(getClass().getResource("/icons/add.png"));
		ImageIcon iconCapNhat = new ImageIcon(getClass().getResource("/icons/capnhat.png"));
		ImageIcon iconLamMoi = new ImageIcon(getClass().getResource("/icons/lammoi.png"));
		ImageIcon iconXoa = new ImageIcon(getClass().getResource("/icons/xoa.png"));
		btnCapNhatKH.setIcon(iconCapNhat);
	    btnThemKH.setIcon(iconThem);
	    btnLamMoi.setIcon(iconLamMoi);
	    btnXoaKH.setIcon(iconXoa);
	    
	    pnChucNang.add(btnThemKH);
	    pnChucNang.add(btnCapNhatKH);
	    pnChucNang.add(btnXoaKH);
	    pnChucNang.add(btnLamMoi);
	    pnChucNang.add(btnXuatExcel);
	    pnNouth.add(pnChucNang,BorderLayout.SOUTH);
	    pnChucNang.setBorder(BorderFactory.createTitledBorder("Chức năng"));
	  
	    pnTimKiem.add(lbTimKiem);
	    pnTimKiem.add(txtTimKiem);
	    pnTimKiem.add(btnXemTatCa);
	    pnSounth.add(pnTimKiem,BorderLayout.NORTH);
	    add(pnSounth,BorderLayout.CENTER);
	    modelKhachHang =  new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không cho phép chỉnh sửa các ô trong bảng
            }
        };
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

		pnSounth.add(scrollPane,BorderLayout.CENTER);
		
		
		txtId.setToolTipText("ID + Date + XXXX");
		txtTenKH.setToolTipText("Chỉ nhận chữ");
		txtEmail.setToolTipText("Điền mail hợp lệ");
		txtsdt.setToolTipText("10 số bắt đầu bằng 0 hoặc +84");
		txtDiaChi.setToolTipText("Nhận số và chữ");
		rbNam.setToolTipText("Chọn 1 trong 2");
		rbNu.setToolTipText("Chọn 1 trong 2");
        chooserNgaySinh.setToolTipText("Trước ngày hiện tại");
		
        chooserNgaySinh.setDate(new java.util.Date());
		btnLamMoi.addActionListener(this);
		btnThemKH.addActionListener(this);
		btnCapNhatKH.addActionListener(this);
		btnXoaKH.addActionListener(this);
		btnXemTatCa.addActionListener(this);
		btnXuatExcel.addActionListener(this);
		tableKH.addMouseListener(this);
		txtTimKiem.addKeyListener(this);
		txtTenKH.addKeyListener(this);
		txtDiaChi.addKeyListener(this);
		txtsdt.addKeyListener(this);
		txtEmail.addKeyListener(this);
		tableKH.addKeyListener(this);
		txtTimKiem.getDocument().addDocumentListener(new DocumentListener() {
	            @Override
	            public void insertUpdate(DocumentEvent e) {
	            	modelKhachHang.setRowCount(0);
	            	handleTimKiemKH(txtTimKiem.getText().trim());
	            }

	            @Override
	            public void removeUpdate(DocumentEvent e) {
	            	modelKhachHang.setRowCount(0);
	            	handleTimKiemKH(txtTimKiem.getText().trim());
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
		loadData();
        
	}
	private void handleTimKiemKH(String cond) {
		if (!cond.equals("")) {
			for (KhachHang kh : daoKhachHang.getKhachHangTimKiem(cond)) {
				String ngaySinh = new SimpleDateFormat("dd/MM/yyyy").format(kh.getNgaySinh());
				modelKhachHang.addRow(new String[] { kh.getIdKhachHang(), kh.getTenKhachHang(),kh.getSdt(), kh.getEmail(),kh.getDiaChi(),ngaySinh,kh.isGioiTinh()?"Nam":"Nữ"
						});	
			}
		} else {
			loadData();
		}
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
		if(valiDate()) {
			KhachHang kh=new KhachHang(idKhachHang, tenKhachHang, sdt, email, diaChi,ngaySinh,GioiTinh);
			daoKhachHang.themKhachHang(kh);
			modelKhachHang.addRow(new Object[] {idKhachHang, tenKhachHang, sdt, email, diaChi,dfNgaySinh.format(kh.getNgaySinh()),kh.isGioiTinh()?"Nam":"Nữ" });
		}
		

	}
	private void loadData() {
		modelKhachHang.setRowCount(0);
		for (KhachHang kh : daoKhachHang.getAllDanhSachKH() ) {
			modelKhachHang.addRow(new Object[] { kh.getIdKhachHang(), kh.getTenKhachHang(),kh.getSdt(), kh.getEmail(),kh.getDiaChi(),dfNgaySinh.format(kh.getNgaySinh()),kh.isGioiTinh()?"Nam":"Nữ"
					});
			
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
		if(valiDate()) {
			try {
				
				daoKhachHang.updateKhachHang(kh);
				loadData();
				JOptionPane.showMessageDialog(this, "Cập nhật thành công");
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Cập nhật thất bại");
			}
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
	public boolean valiDate() {
		
		String ten = txtTenKH.getText().trim();
		
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
			txtTenKH.requestFocus();
			
			return false;
		}
		if (!(ten.length() > 0 && ten.matches("^[A-ZÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸĐ][a-zàáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđ]*(?:[ ][A-ZÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸĐ][a-zàáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđ]*)*$"))) {
			JOptionPane.showMessageDialog(this, "Tên phải viết hoa và không chứa số", "Thông báo",
					JOptionPane.WARNING_MESSAGE);
			txtTenKH.selectAll();
			txtTenKH.requestFocus();
			return false;
		}
//
		if (!(diaChi.length() > 0 && diaChi.matches("^[^!@#$%^&*()+-]*$"))) {
			JOptionPane.showMessageDialog(this, "Địa chỉ không được chứa kí tự đặc biệt", "Thông báo",
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
	private void xuatExcel(String filePath) {

	try {	
		Workbook workbook = new XSSFWorkbook();
		org.apache.poi.ss.usermodel.Sheet sheet = workbook.createSheet("Khách hàng");

		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("ID nhân viên");
		header.createCell(1).setCellValue("Tên nhân viên");
		header.createCell(2).setCellValue("Số điện thoại");
		header.createCell(3).setCellValue("Email");
		header.createCell(4).setCellValue("Địa chỉ");
		header.createCell(5).setCellValue("Ngày sinh");
		header.createCell(6).setCellValue("Giới tính");
		
		int rowNum = 1;
		for(KhachHang kh: daoKhachHang.getAllDanhSachKH()) {
			Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(kh.getIdKhachHang());
            row.createCell(1).setCellValue(kh.getTenKhachHang());
            row.createCell(2).setCellValue(kh.getSdt());
            row.createCell(3).setCellValue(kh.getEmail());
            row.createCell(4).setCellValue(kh.getDiaChi());
            row.createCell(5).setCellValue(new SimpleDateFormat("dd/MM/yyyy").format(kh.getNgaySinh()));
            row.createCell(6).setCellValue(kh.isGioiTinh()?"Nam":"Nữ");
            
		}
		 // Hiển thị hộp thoại mở cửa sổ lưu tệp
        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
			workbook.write(outputStream);
		}

		System.out.println("Dữ liệu SanPham đã được ghi vào tệp Excel thành công.");
		JOptionPane.showMessageDialog(this, "Xuất thống kê excel thành công");
        
		} catch (IOException e) {
			e.printStackTrace();
		}
}

	private void lamMoi() {
		loadData();
		try {
			txtId.setText(autoID());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		txtTenKH.setText("");
		txtDiaChi.setText("");
		txtsdt.setText("");
		txtEmail.setText("");
		rbNam.setSelected(true);
		rbNu.setSelected(false);
		txtTenKH.requestFocus();
		chooserNgaySinh.setDate(new java.util.Date());
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
		if(o.equals(btnXemTatCa)) {
			 txtTimKiem.setText("");
		 }
		if(o.equals(btnXuatExcel)) {
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());
			String filePath = System.getProperty("user.dir") + "/src/main/resources/DataExports/KhachHang/KH_" + timeStamp
					+ ".xlsx";
		 xuatExcel(filePath);
		 }
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
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			Object o = e.getSource();
			if (o == txtTenKH || o == txtDiaChi || o == txtEmail || o == txtsdt ) {
				try {
					ThemKH();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		else if (e.getKeyCode() == KeyEvent.VK_F5) {
			lamMoi();
		} 
		else if (tableKH.getSelectedRow() != -1) {
			if (e.getKeyCode() == KeyEvent.VK_F5) 
			{
				lamMoi();
			}

		} 
		else if (e.getKeyCode() == KeyEvent.VK_TAB) {
			tabbedPane.setSelectedIndex(1);
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(() -> {
			Object o = e.getSource();
		if (e.getKeyCode() == KeyEvent.VK_F5) 
		{
			lamMoi();
		} 
		else if (e.getKeyCode() == KeyEvent.VK_F10) 
		{
			lamMoi();
		}
		});
		
	}
}





