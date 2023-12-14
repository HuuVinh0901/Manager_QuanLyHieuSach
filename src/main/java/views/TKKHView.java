package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.compress.harmony.pack200.NewAttribute;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import com.toedter.calendar.JDateChooser;

import dao.DAOKhachHang;
import dao.DAO_QuanLyBanHang;
import models.HoaDon;
import models.KhachHang;


public class TKKHView extends JPanel implements ActionListener, ItemListener {
	private JDateChooser chooserDayStart;
	private JDateChooser chooserDayEnd;
	private JButton btnTimKiem;
	private JButton btnThongKe;
	private JButton btnInThongKe;
	private JButton btnLamMoi;
	
	// nút khách hàng thân thiết
	private JButton btnKHThanThiet;
	
	private JTable tblKhachHang;
	private DefaultTableModel modelKhachHang;
	private SimpleDateFormat dfNgaySQL;
	private JLabel lblTongKhachHang;
	private JLabel lblTongDoanhThu;
	private JLabel lblTongLoiNhuan;
	private JLabel lblLoc;
	private JComboBox<String> cbLoc;
	private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
	private DAO_QuanLyBanHang daoBanHang;
	public TKKHView() {
		setLayout(new BorderLayout());
		currencyFormat.setCurrency(Currency.getInstance("VND"));
		daoBanHang = new DAO_QuanLyBanHang();
		dfNgaySQL = new SimpleDateFormat("yyyy-MM-dd");
		JPanel pn2 = new JPanel(new BorderLayout());
		JPanel pn1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel pnTop = new JPanel(new BorderLayout());
		JPanel pnTitle = new JPanel();
		JPanel pnDay = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel pnTuNgay = new JPanel();
		JPanel pnDenNgay = new JPanel();
		JPanel pnInThongKe = new JPanel();
		JPanel pnTongKhachHang = new JPanel();
		JLabel lblTitle = new JLabel("THỐNG KÊ KHÁCH HÀNG");
		JLabel lblDayStart = new JLabel("Từ ngày");
		JLabel lblDayEnd = new JLabel("Đến ngày");
		lblLoc = new JLabel("Thời gian:");
		lblLoc.setPreferredSize(new Dimension(70, 50));
		cbLoc = new JComboBox<String>();
		cbLoc.addItem("Hiện tại");
		cbLoc.addItem("7 ngày gần nhất");
		cbLoc.addItem("1 tháng gần nhất");
		cbLoc.addItem("3 tháng gần nhất");
		cbLoc.addItem("6 tháng gần nhất");
		cbLoc.addItem("1 năm gần nhất");
		cbLoc.addItem("Tùy chỉnh");

		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTitle.setForeground(new Color(26, 102, 227));
		chooserDayStart = new JDateChooser();
		chooserDayStart.getCalendarButton().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chooserDayStart.setDateFormatString("dd/MM/yyyy");
		chooserDayStart.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		chooserDayStart.setFont(new Font("SansSerif", Font.PLAIN, 15));
		chooserDayStart.setPreferredSize(new Dimension(200, 30));
		chooserDayStart.getCalendarButton().setPreferredSize(new Dimension(20, 24));
		chooserDayStart.getCalendarButton().setBackground(new Color(102, 0, 153));
		chooserDayStart.getCalendarButton().setToolTipText("Chọn ngày bắt đầu");
		chooserDayEnd = new JDateChooser();
		chooserDayEnd.getCalendarButton().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chooserDayEnd.setDateFormatString("dd/MM/yyyy");
		chooserDayEnd.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		chooserDayEnd.setFont(new Font("SansSerif", Font.PLAIN, 15));
		chooserDayEnd.setPreferredSize(new Dimension(200, 30));
		chooserDayEnd.getCalendarButton().setPreferredSize(new Dimension(20, 24));
		chooserDayEnd.getCalendarButton().setBackground(new Color(102, 0, 153));
		chooserDayEnd.getCalendarButton().setToolTipText("Chọn ngày bắt đầu");

		chooserDayStart.setDate(new Date());
		chooserDayEnd.setDate(new Date());
		chooserDayStart.setEnabled(false);
		chooserDayEnd.setEnabled(false);

		btnTimKiem = new JButton("Tìm kiếm");
		btnThongKe = new JButton("Thống kê khách hàng");
		btnInThongKe = new JButton("In thống kê");
		btnLamMoi = new JButton("Làm mới");
		btnTimKiem.setPreferredSize(new Dimension(100, 30));
		pnTitle.add(lblTitle);
		pnTuNgay.add(lblDayStart);
		pnTuNgay.add(chooserDayStart);
		pnDenNgay.add(lblDayEnd);
		pnDenNgay.add(chooserDayEnd);
		
		JPanel pnDayLeft = new JPanel();
		pnDayLeft.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		pnDayLeft.add(pnTuNgay);
		pnDayLeft.add(pnDenNgay);
		pnDayLeft.add(btnTimKiem);
		pnDayLeft.add(lblLoc);
		pnDayLeft.add(cbLoc);
		
		JPanel pnDayRight = new JPanel();
		pnDayRight.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		// nút khách hàng thân thiết
		btnKHThanThiet = new JButton("Khách hàng Thân thiết");
		pnDayRight.add(btnKHThanThiet);
		JLabel lbThongBao=new JLabel("(*) Có từ 3 lần mua trở lên");
		pnDayRight.add(lbThongBao);
		pnDay.add(pnDayLeft);
		pnDay.add(pnDayRight);
		
		pnInThongKe.add(btnThongKe);
		pnInThongKe.add(btnInThongKe);
		pnInThongKe.add(btnLamMoi);
		pn1.add(pnInThongKe);
		pnTop.add(pnTitle, BorderLayout.NORTH);
		pnTop.add(pnDay, BorderLayout.CENTER);
		pnTop.add(pn1, BorderLayout.SOUTH);

		tblKhachHang = new JTable();
		modelKhachHang = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		modelKhachHang.addColumn("Mã khách hàng");
		modelKhachHang.addColumn("Tên khách hàng");
		modelKhachHang.addColumn("Số điện thoại");
		modelKhachHang.addColumn("Email");
		modelKhachHang.addColumn("Địa chỉ");
		modelKhachHang.addColumn("Ngày sinh");
		modelKhachHang.addColumn("Số hóa đơn");
		tblKhachHang.setModel(modelKhachHang);
		JScrollPane scrollTblHD = new JScrollPane(tblKhachHang);
		scrollTblHD.setBorder(BorderFactory.createTitledBorder("Thông tin khách hàng"));
		pnDay.setBorder(BorderFactory.createTitledBorder("Chức năng tìm kiếm"));
		pn1.setBorder(BorderFactory.createTitledBorder("In & Thống kê"));

		lblTongKhachHang = new JLabel("SỐ LƯỢNG KHÁCH HÀNG: " + modelKhachHang.getRowCount());
		lblTongKhachHang.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTongKhachHang.setForeground(new Color(26, 102, 227));

		pnTongKhachHang.add(lblTongKhachHang);
		pn2.add(pnTongKhachHang, BorderLayout.WEST);

		add(pnTop, BorderLayout.NORTH);
		add(scrollTblHD, BorderLayout.SOUTH);
		add(pn2, BorderLayout.CENTER);

		btnTimKiem.addActionListener(this);
		btnThongKe.addActionListener(this);
		btnInThongKe.addActionListener(this);
		btnLamMoi.addActionListener(this);
		cbLoc.addItemListener(this);
		btnKHThanThiet.addActionListener(this);
		Date ngayHienTai = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(ngayHienTai);
		loadKhachHangTheoNgay(ngayHienTai, ngayHienTai);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnLamMoi)) {
			clearData();
		} else if (o.equals(btnTimKiem)) {
			if ("Tùy chỉnh".equals(cbLoc.getSelectedItem())) {
				Date currentDate = new Date();
				Date startDate = chooserDayStart.getDate();
				Date endDate = chooserDayEnd.getDate();
				if (startDate == null || startDate.after(currentDate)) {
					JOptionPane.showConfirmDialog(this, "Ngày bắt đầu không thể nhỏ hơn ngày hiện tại", "Cảnh báo",
							JOptionPane.WARNING_MESSAGE);
				} else if (endDate == null || endDate.after(currentDate)) {
					JOptionPane.showConfirmDialog(this, "Ngày kết thúc không thể nhỏ hơn ngày hiện tại", "Cảnh báo",
							JOptionPane.WARNING_MESSAGE);
				}
				else {
					loadKhachHangTheoMocThoiGian();
				}
			} 
		} else if (o.equals(btnThongKe)) {
			String selectedOption = (String) cbLoc.getSelectedItem();
			if("6 tháng gần nhất".equals(selectedOption) || "3 tháng gần nhất".equals(selectedOption)|| "1 năm gần nhất".equals(selectedOption) ){
				showBieuDoKhachHangTheoThangTrongNam();
			}
			else {
				showBieuDoKhachHangTheoNgayTrongThang();
			}
		} else if (o.equals(btnInThongKe)) {
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());
			String filePath = System.getProperty("user.dir") + "/src/main/resources/DataExports/KhachHang/TKKhachHang/TKKH_" + timeStamp
					+ ".xlsx";
			inThongKeKH(filePath);
		}
		else if (o.equals(btnKHThanThiet)) {
			loadKhachHangThanThiet();
		}
	}

	private void loadKhachHangThanThiet() {
	    modelKhachHang.setRowCount(0);

	    // Lấy danh sách khách hàng thân thiết từ CSDL
	    DAOKhachHang daoKhachHang = new DAOKhachHang();
	    ArrayList<KhachHang> dsKhachHang = daoKhachHang.getKhachHangThanThiet(3);

	    if (dsKhachHang.size() == 0) {
	        JOptionPane.showMessageDialog(this, "Không có khách hàng thân thiết nào");
	        // Có thể thêm xử lý khác nếu muốn thông báo khác khi không có khách hàng thân thiết
	    } else {
	        for (KhachHang khachHang : dsKhachHang) {
	            String maKH = khachHang.getIdKhachHang();
	            String tenKH = khachHang.getTenKhachHang();
	            int soLuongHoaDon = daoKhachHang.getSoLuongHoaDon(maKH);

	            modelKhachHang.addRow(new String[] {
	                    khachHang.getIdKhachHang(),
	                    khachHang.getTenKhachHang(),
	                    khachHang.getSdt(),
	                    khachHang.getEmail(),
	                    khachHang.getDiaChi(),
	                    khachHang.getNgaySinh().toString(),
	                    String.valueOf(soLuongHoaDon)
	                });
	        }
	        lblTongKhachHang.setText("SỐ LƯỢNG KHÁCH HÀNG THÂN THIẾT: " + modelKhachHang.getRowCount());
	    }
	}

	private void inThongKe() {
		try {
		Workbook workbook = new XSSFWorkbook();
		org.apache.poi.ss.usermodel.Sheet sheet = workbook.createSheet("Thống kê kinh doanh");

		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("Mã Hóa Đơn");
		header.createCell(1).setCellValue("Ngày");
		header.createCell(2).setCellValue("Mã Khách Hàng");
		header.createCell(3).setCellValue("Mã Nhân Viên");
		header.createCell(4).setCellValue("Tổng Tiền");
		header.createCell(5).setCellValue("Lợi nhuận");
		

		int rowNum = 1;
		for(HoaDon kh: daoBanHang.getAllHoaDon()) {
			Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(kh.getIdDonHang());
            row.createCell(1).setCellValue(new SimpleDateFormat("dd/MM/yyyy").format(kh.getNgayLap()));
            row.createCell(2).setCellValue(kh.getKhachHang().getIdKhachHang());
            row.createCell(3).setCellValue(kh.getNhanVien().getId());
            row.createCell(4).setCellValue(kh.getTongTien());
            row.createCell(5).setCellValue(kh.getTongLoiNhuan());
        
		}

		JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn đường dẫn và tên tệp Excel");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Tệp Excel (*.xlsx)", "xlsx");
        fileChooser.setFileFilter(filter);

        // Hiển thị hộp thoại mở cửa sổ lưu tệp
        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            // Lấy đường dẫn và tên tệp từ người dùng
            String filePathString = fileChooser.getSelectedFile().getAbsolutePath();

            // Ghi workbook ra tệp Excel
            try (FileOutputStream outputStream = new FileOutputStream(filePathString + ".xlsx")) {
                workbook.write(outputStream);
            }

            System.out.println("Dữ liệu đã được ghi vào tệp Excel thành công.");
            JOptionPane.showMessageDialog(null, "Xuất Excel thành công");
        }
        
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void inThongKeKH(String filePath) {
	    try {
	        Workbook workbook = new XSSFWorkbook();
	        org.apache.poi.ss.usermodel.Sheet sheet = workbook.createSheet("Thống kê khách hàng");

	        Row header = sheet.createRow(0);
	        header.createCell(0).setCellValue("Mã Khách Hàng");
	        header.createCell(1).setCellValue("Tên Khách Hàng");
	        header.createCell(2).setCellValue("Số Điện Thoại");
	        header.createCell(3).setCellValue("Email");
	        header.createCell(4).setCellValue("Địa Chỉ");
	        header.createCell(5).setCellValue("Ngày Sinh");
	        header.createCell(6).setCellValue("Giới Tính");
	        
	        DAOKhachHang daoKhachHang = new DAOKhachHang();

	        int rowNum = 1;
	        for (KhachHang khachHang : daoKhachHang.getAllDanhSachKH()) {
	            Row row = sheet.createRow(rowNum++);
	            row.createCell(0).setCellValue(khachHang.getIdKhachHang());
	            row.createCell(1).setCellValue(khachHang.getTenKhachHang());
	            row.createCell(2).setCellValue(khachHang.getSdt());
	            row.createCell(3).setCellValue(khachHang.getEmail());
	            row.createCell(4).setCellValue(khachHang.getDiaChi());
	            row.createCell(5).setCellValue(new SimpleDateFormat("dd/MM/yyyy").format(khachHang.getNgaySinh()));
	            row.createCell(6).setCellValue(khachHang.isGioiTinh() ? "Nam" : "Nữ");
	        }

	        JFileChooser fileChooser = new JFileChooser();
	        fileChooser.setDialogTitle("Chọn đường dẫn và tên tệp Excel");
	        FileNameExtensionFilter filter = new FileNameExtensionFilter("Tệp Excel (*.xlsx)", "xlsx");
	        fileChooser.setFileFilter(filter);
	           

	            // Ghi workbook ra tệp Excel
	            try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
	                workbook.write(outputStream);
	            }

	            System.out.println("Dữ liệu đã được ghi vào tệp Excel thành công.");
	            JOptionPane.showMessageDialog(null, "Xuất Excel thành công");
	        

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	
	private void showBieuDoKhachHangTheoNgayTrongThang() {
	    JDialog dialog = new JDialog();
	    dialog.setTitle("Thống kê khách hàng");
	    dialog.setLayout(new GridBagLayout());
	    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	    
	    DAOKhachHang daoKhachHang = new DAOKhachHang();

	    for (int j = 1; j < 32; j++) {
	        String ngay = String.valueOf(j);
	        int soLuongKhachHang = daoKhachHang.getSoLuongKhachHangTheoNgayThangNam(ngay,
	                String.valueOf(LocalDate.now().getMonthValue()), String.valueOf(Year.now().getValue()));
	        dataset.addValue(soLuongKhachHang, "Số lượng khách hàng", ngay);
	    }

	    JFreeChart chart = ChartFactory.createBarChart(
	            "Biểu đồ thống kê số lượng khách hàng theo ngày trong tháng " + LocalDate.now().getMonthValue() + "/"
	                    + Year.now().getValue(),
	            "Tháng " + LocalDate.now().getMonthValue() + "/" + Year.now().getValue(), "Số lượng khách hàng", dataset,
	            PlotOrientation.VERTICAL, true, true, false);

	    CategoryPlot plot = (CategoryPlot) chart.getPlot();
	    CategoryAxis xAxis = plot.getDomainAxis();
	    xAxis.setLowerMargin(0.02);
	    xAxis.setUpperMargin(0.02);
	    NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
	    yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
	    BarRenderer renderer = (BarRenderer) plot.getRenderer();
	    renderer.setDrawBarOutline(false);
	    ChartPanel chartPanel = new ChartPanel(chart);

	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.weightx = 0.5;
	    gbc.weighty = 1.0;
	    gbc.fill = GridBagConstraints.BOTH;
	    dialog.add(chartPanel, gbc);

	    dialog.pack();
	    dialog.setSize(1200, 720);
	    dialog.setLocationRelativeTo(null);
	    dialog.setVisible(true);
	}
	
	private void showBieuDoKhachHangTheoThangTrongNam() {
	    JDialog dialog = new JDialog();
	    dialog.setTitle("Thống kê");
	    dialog.setLayout(new GridBagLayout());     
	    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	    DAOKhachHang daoKhachHang = new DAOKhachHang();
	    
	    for (int i = 1; i < 13; i++) {
	        String thang = String.valueOf(i);
	        
	        int soLuongKhachHang = daoKhachHang.getSoLuongKhachHangTheoThangNam(thang, String.valueOf(Year.now().getValue()));
	        dataset.addValue(soLuongKhachHang, "Số lượng khách hàng", thang);
	    }

	    JFreeChart chart = ChartFactory.createBarChart(
	        "BIỂU ĐỒ SỐ LƯỢNG KHÁCH HÀNG THEO THÁNG TRONG NĂM: "+ Year.now().getValue(),
	        "Năm " + Year.now().getValue(),
	        "Số lượng khách hàng",
	        dataset,
	        PlotOrientation.VERTICAL,
	        true,
	        true,
	        false
	    );
	    
	    CategoryPlot plot = (CategoryPlot) chart.getPlot();
	    CategoryAxis xAxis = plot.getDomainAxis();
	    xAxis.setLowerMargin(0.05);
	    xAxis.setUpperMargin(0.05);
	    NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
	    yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
	    BarRenderer renderer = (BarRenderer) plot.getRenderer();
	    renderer.setDrawBarOutline(false);
	    
	    ChartPanel chartPanel = new ChartPanel(chart);
	    
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.weightx = 0.4;
	    gbc.weighty = 1.0;
	    gbc.fill = GridBagConstraints.BOTH;
	    dialog.add(chartPanel, gbc);
	    
	    dialog.pack();
	    dialog.setSize(1200, 720);
	    dialog.setLocationRelativeTo(null);
	    dialog.setVisible(true);
	}

	
	
	private void XoaDuLieuTable() {
		DefaultTableModel dm = (DefaultTableModel) tblKhachHang.getModel();
		dm.getDataVector().removeAllElements();
	}
	private void clearData() {
		chooserDayStart.setDate(new Date());
		chooserDayEnd.setDate(new Date());
		cbLoc.setSelectedItem("Hiện tại");
		Date ngayHienTai = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(ngayHienTai);
		loadKhachHangTheoNgay(ngayHienTai, ngayHienTai);
	}
	
	public void loadKhachHangTheoNgay(Date ngayHienTai, Date ngayChon) {
	    modelKhachHang.setRowCount(0);

	    // Lấy danh sách hóa đơn trong khoảng thời gian
	    ArrayList<HoaDon> dsHoaDon = daoBanHang.getHoaDonTheoNgay(
	            dfNgaySQL.format(ngayChon), 
	            dfNgaySQL.format(ngayHienTai)
	    );

	    if (dsHoaDon.size() == 0) {
	        JOptionPane.showMessageDialog(this, "Khung thời gian này không có hóa đơn nào");
	        // Có thể cần thêm xử lý khác nếu muốn thông báo khác khi không có hóa đơn
	        XoaDuLieuTable();
	        lblTongKhachHang.setText("SỐ LƯỢNG HOÁ ĐƠN BÁN RA : " + modelKhachHang.getRowCount());
	        lblTongDoanhThu.setText("TỔNG DOANH THU : " + currencyFormat.format(0));
	        lblTongLoiNhuan.setText("TỔNG LỢI NHUẬN : " + currencyFormat.format(0));
	    } else {
	        // Tạo một Map để lưu số lượng hóa đơn của mỗi khách hàng
	        Map<String, Integer> khachHangCountMap = new HashMap<>();

	        for (HoaDon hd : dsHoaDon) {
	            String maKH = hd.getKhachHang().getIdKhachHang();
	            // Tăng số lượng hóa đơn của khách hàng trong Map
	            khachHangCountMap.put(maKH, khachHangCountMap.getOrDefault(maKH, 0) + 1);
	        }

	        // Lấy danh sách khách hàng từ Map
	        for (Map.Entry<String, Integer> entry : khachHangCountMap.entrySet()) {
	            String maKH = entry.getKey();
	            int soLuongHoaDon = entry.getValue();

	            // Lấy thông tin khách hàng từ CSDL dựa trên ID khách hàng
	            DAOKhachHang daoKhachHang = new DAOKhachHang();
                // Lấy thông tin khách hàng từ CSDL dựa trên ID khách hàng
                KhachHang khachHang = daoKhachHang.getKhachHang(maKH);

             // Hiển thị thông tin lên JTable
                modelKhachHang.addRow(new String[] {
                    khachHang.getIdKhachHang(),
                    khachHang.getTenKhachHang(),
                    khachHang.getSdt(),
                    khachHang.getEmail(),
                    khachHang.getDiaChi(),
                    khachHang.getNgaySinh().toString(),
                    String.valueOf(soLuongHoaDon)
                });
	        }

	        lblTongKhachHang.setText("SỐ LƯỢNG KHÁCH HÀNG : " + modelKhachHang.getRowCount());
	    }
	}
	
	public void loadKhachHangTheoMocThoiGian() {
	    modelKhachHang.setRowCount(0);

	    if (chooserDayStart.getDate() == null) {
	        JOptionPane.showMessageDialog(this, "Chưa chọn ngày bắt đầu");
	        chooserDayStart.requestFocus();
	    } else if (chooserDayEnd.getDate() == null) {
	        JOptionPane.showMessageDialog(this, "Chưa chọn ngày kết thúc");
	        chooserDayEnd.requestFocus();
	    } else if (chooserDayEnd.getDate().before(chooserDayStart.getDate())) {
	        JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải trước ngày kết thúc");
	        chooserDayEnd.requestFocus();
	    } else {
	        // Lấy danh sách hóa đơn trong khoảng thời gian
	        ArrayList<HoaDon> dsHoaDon = daoBanHang.getHoaDonTheoNgay(
	            dfNgaySQL.format(chooserDayStart.getDate()), 
	            dfNgaySQL.format(chooserDayEnd.getDate())
	        );

	        if (dsHoaDon.size() == 0) {
	            JOptionPane.showMessageDialog(this, "Khung thời gian này không có hóa đơn nào");
	            // Có thể cần thêm xử lý khác nếu muốn thông báo khác khi không có hóa đơn
	        } else {
	            // Tạo một Map để lưu số lượng hóa đơn của mỗi khách hàng
	            Map<String, Integer> khachHangCountMap = new HashMap<>();

	            for (HoaDon hd : dsHoaDon) {
	                String maKH = hd.getKhachHang().getIdKhachHang();
	                // Tăng số lượng hóa đơn của khách hàng trong Map
	                khachHangCountMap.put(maKH, khachHangCountMap.getOrDefault(maKH, 0) + 1);
	            }

	            // Lấy danh sách khách hàng từ Map
	            for (Map.Entry<String, Integer> entry : khachHangCountMap.entrySet()) {
	                String maKH = entry.getKey();
	                int soLuongHoaDon = entry.getValue();

	                DAOKhachHang daoKhachHang = new DAOKhachHang();
	                // Lấy thông tin khách hàng từ CSDL dựa trên ID khách hàng
	                KhachHang khachHang = daoKhachHang.getKhachHang(maKH);

	                // Hiển thị thông tin lên JTable
	                modelKhachHang.addRow(new String[] {
	                    khachHang.getIdKhachHang(),
	                    khachHang.getTenKhachHang(),
	                    khachHang.getSdt(),
	                    khachHang.getEmail(),
	                    khachHang.getDiaChi(),
	                    khachHang.getNgaySinh().toString(),
	                    String.valueOf(soLuongHoaDon)
	                });
	            }
	            lblTongKhachHang.setText("SỐ LƯỢNG KHÁCH HÀNG : " + modelKhachHang.getRowCount());
	        }
	    }
	}

	


	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == cbLoc && e.getStateChange() == ItemEvent.SELECTED) {
			String selectedOption = (String) cbLoc.getSelectedItem();
			if ("Tùy chỉnh".equals(selectedOption)) {
				chooserDayStart.setEnabled(true);
				chooserDayEnd.setEnabled(true);
				loadKhachHangTheoMocThoiGian();
			} else if("Hiện tại".equals(selectedOption)) {
				chooserDayStart.setEnabled(false);
				chooserDayEnd.setEnabled(false);
				Date ngayHienTai= new Date();
				
				loadKhachHangTheoNgay(ngayHienTai, ngayHienTai);
			}
			else if("7 ngày gần nhất".equals(selectedOption)) {
				chooserDayStart.setEnabled(false);
				chooserDayEnd.setEnabled(false);
				Date ngayHienTai= new Date();
				Calendar calendar = Calendar.getInstance();
			    calendar.setTime(ngayHienTai);
			    calendar.add(Calendar.DAY_OF_MONTH, -7);
			    Date sevenDaysAgo = calendar.getTime();
			    loadKhachHangTheoNgay(ngayHienTai, sevenDaysAgo);
			}
			 else if("1 tháng gần nhất".equals(selectedOption)) {
					chooserDayStart.setEnabled(false);
					chooserDayEnd.setEnabled(false);
					Date ngayHienTai= new Date();
					Calendar calendar = Calendar.getInstance();
				    calendar.setTime(ngayHienTai);
				    calendar.add(Calendar.MONTH, -1);
				    Date MonthAgo = calendar.getTime();
				    loadKhachHangTheoNgay(ngayHienTai, MonthAgo);
				}
			 else if("3 tháng gần nhất".equals(selectedOption)) {
					chooserDayStart.setEnabled(false);
					chooserDayEnd.setEnabled(false);
					Date ngayHienTai= new Date();
					Calendar calendar = Calendar.getInstance();
				    calendar.setTime(ngayHienTai);
				    calendar.add(Calendar.MONTH, -3);
				    Date MonthAgo = calendar.getTime();
				    loadKhachHangTheoNgay(ngayHienTai, MonthAgo);
				}
			 else if("6 tháng gần nhất".equals(selectedOption)) {
					chooserDayStart.setEnabled(false);
					chooserDayEnd.setEnabled(false);
					Date ngayHienTai= new Date();
					Calendar calendar = Calendar.getInstance();
				    calendar.setTime(ngayHienTai);
				    calendar.add(Calendar.MONTH, -6);
				    Date MonthAgo = calendar.getTime();
				    loadKhachHangTheoNgay(ngayHienTai, MonthAgo);
				}
			 else if("1 năm gần nhất".equals(selectedOption)) {
					chooserDayStart.setEnabled(false);
					chooserDayEnd.setEnabled(false);
					Date ngayHienTai= new Date();
					Calendar calendar = Calendar.getInstance();
				    calendar.setTime(ngayHienTai);
				    calendar.add(Calendar.YEAR, -1);
				    Date MonthAgo = calendar.getTime();
				    loadKhachHangTheoNgay(ngayHienTai, MonthAgo);
				}
		}
	}
}
