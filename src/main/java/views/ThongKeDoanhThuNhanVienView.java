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
import java.util.Locale;
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

import dao.DAO_QuanLyBanHang;
import models.HoaDon;
import models.NhanVien;

public class ThongKeDoanhThuNhanVienView extends JPanel implements ActionListener, ItemListener {
	private JDateChooser chooserDayStart;
	private JDateChooser chooserDayEnd;
	private JButton btnTimKiem;
	private JButton btnThongKe;
	private JButton btnInThongKe;
	private JButton btnLamMoi;
	private JButton btnThongKeLN;
	private JTable tblHoaDon;
	private DefaultTableModel modelHoaDon;
	private SimpleDateFormat dfNgaySQL;
	private JLabel lblTongHoaDon;
	private JLabel lblTongDoanhThu;
	private JLabel lblTongLoiNhuan;
	private JLabel lblLoc;
	private JComboBox<String> cbLoc;
	private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
	private DAO_QuanLyBanHang daoBanHang;
	public ThongKeDoanhThuNhanVienView() {
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
		JPanel pnTongHoaDon = new JPanel();
		JPanel pnTongDoanhThu = new JPanel();
		JPanel pnTongLoiNhuan = new JPanel();
		JLabel lblTitle = new JLabel("THỐNG KÊ DOANH THU");
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

		// load chooserDayStart and chooserDayEnd
		chooserDayStart.setDate(new Date());
		chooserDayEnd.setDate(new Date());
		chooserDayStart.setEnabled(false);
		chooserDayEnd.setEnabled(false);

		btnTimKiem = new JButton("Tìm kiếm");
		btnThongKe = new JButton("Thống kê hóa đơn");
		btnInThongKe = new JButton("In thống kê");
		btnLamMoi = new JButton("Làm mới");
		btnThongKeLN=new JButton("Thống kê doanh thu & lợi nhuận");
		btnTimKiem.setPreferredSize(new Dimension(100, 30));
		pnTitle.add(lblTitle);
		pnTuNgay.add(lblDayStart);
		pnTuNgay.add(chooserDayStart);
		pnDenNgay.add(lblDayEnd);
		pnDenNgay.add(chooserDayEnd);
		pnDay.add(pnTuNgay);
		pnDay.add(pnDenNgay);
		pnDay.add(btnTimKiem);
		pnDay.add(lblLoc);
		pnDay.add(cbLoc);
		
		pnInThongKe.add(btnThongKe);
		pnInThongKe.add(btnThongKeLN);
		pnInThongKe.add(btnInThongKe);
		pnInThongKe.add(btnLamMoi);
		pn1.add(pnInThongKe);
		pnTop.add(pnTitle, BorderLayout.NORTH);
		pnTop.add(pnDay, BorderLayout.CENTER);
		pnTop.add(pn1, BorderLayout.SOUTH);

		tblHoaDon = new JTable();
		modelHoaDon = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		modelHoaDon.addColumn("Mã hoá đơn");
		modelHoaDon.addColumn("Ngày lập");
		modelHoaDon.addColumn("Mã khách hàng");
		modelHoaDon.addColumn("Mã nhân viên");
		modelHoaDon.addColumn("Tiền khách đưa");
		modelHoaDon.addColumn("Tổng tiền");
		modelHoaDon.addColumn("Lợi nhuận");
		tblHoaDon.setModel(modelHoaDon);
		JScrollPane scrollTblHD = new JScrollPane(tblHoaDon);
		scrollTblHD.setBorder(BorderFactory.createTitledBorder("Thông tin hoá đơn"));
		pnDay.setBorder(BorderFactory.createTitledBorder("Chức năng tìm kiếm"));
		pn1.setBorder(BorderFactory.createTitledBorder("In & Thống kê"));

		lblTongHoaDon = new JLabel("SỐ LƯỢNG HOÁ ĐƠN BÁN RA : " + modelHoaDon.getRowCount());
		lblTongDoanhThu = new JLabel("TỔNG DOANH THU : " + currencyFormat.format(0));
		lblTongLoiNhuan = new JLabel("TỔNG LỢI NHUẬN : " + currencyFormat.format(0));
		lblTongHoaDon.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTongHoaDon.setForeground(new Color(26, 102, 227));
		lblTongDoanhThu.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTongDoanhThu.setForeground(Color.red);
		lblTongLoiNhuan.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTongLoiNhuan.setForeground(Color.red);
		pnTongHoaDon.add(lblTongHoaDon);
		pnTongDoanhThu.add(lblTongDoanhThu);
		pnTongLoiNhuan.add(lblTongLoiNhuan);
		pn2.add(pnTongHoaDon, BorderLayout.WEST);
		pn2.add(pnTongDoanhThu, BorderLayout.CENTER);
		pn2.add(pnTongLoiNhuan, BorderLayout.EAST);
		add(pnTop, BorderLayout.NORTH);
		add(scrollTblHD, BorderLayout.SOUTH);
		add(pn2, BorderLayout.CENTER);

		btnTimKiem.addActionListener(this);
		btnThongKe.addActionListener(this);
		btnThongKeLN.addActionListener(this);
		btnInThongKe.addActionListener(this);
		btnLamMoi.addActionListener(this);
		cbLoc.addItemListener(this);
		
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
					loadDataHoaDonTheoTuyChinh();
				}
			} 
		} else if (o.equals(btnThongKe)) {
			String selectedOption = (String) cbLoc.getSelectedItem();
			if("6 tháng gần nhất".equals(selectedOption) || "3 tháng gần nhất".equals(selectedOption)|| "1 năm gần nhất".equals(selectedOption) ){
				showBieuDoHoaDonTheoThangTrongNam();
			}
			else {
				showBieuDoHoaDonTheoNgayTrongThang();
			}
		} else if (o.equals(btnInThongKe)) {
			inThongKe();
		}
		else if (o.equals(btnThongKeLN)) {
			String selectedOption = (String) cbLoc.getSelectedItem();
			if("6 tháng gần nhất".equals(selectedOption) || "3 tháng gần nhất".equals(selectedOption)|| "1 năm gần nhất".equals(selectedOption) ){
				showBieuDoLoiNhuanTheoThangTrongNam();
			}
			else {
				showBieuDoLoiNhuanTheoNgayTrongThang();
			}
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

	private void showBieuDoHoaDonTheoNgayTrongThang() {
		JDialog dialog = new JDialog();
        dialog.setTitle("Thống kê hóa đơn");
        dialog.setLayout(new GridBagLayout());     
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
        
        	for(int j=1;j<32;j++) {
        		String z = String.valueOf(j);
        		dataset.addValue(daoBanHang.getSoHoaDonTheoNgayThangNam(z,String.valueOf(LocalDate.now().getMonthValue()), String.valueOf(Year.now().getValue())), "Số hóa đơn", z);
            	
        	}
//        	
        JFreeChart chart = ChartFactory.createBarChart(
            "Biểu đồ thống kê số hóa đơn đã bán theo tháng " +LocalDate.now().getMonthValue()+"/"+  Year.now().getValue(),
            "Tháng " +LocalDate.now().getMonthValue()+"/"+ Year.now().getValue(),
            "Số hóa đơn",
            dataset,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
        );

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
	private void showBieuDoLoiNhuanTheoThangTrongNam() {
		JDialog dialog = new JDialog();
        dialog.setTitle("Thống kê lợi nhuận");
        dialog.setLayout(new GridBagLayout());    
		DefaultCategoryDataset dataset3 = new DefaultCategoryDataset();
		DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
		 for (int i = 1; i < 13; i++) {
	        	String j = String.valueOf(i);
	        	dataset3.addValue(daoBanHang.getLoiNhuanTheoThangNam(j, String.valueOf(Year.now().getValue())), "Lợi nhuận", j);
	        	dataset2.addValue(daoBanHang.getTongTienTheoThangNam(j, String.valueOf(Year.now().getValue())), "Doanh thu", j);
	        }
		JFreeChart chart3 = ChartFactory.createBarChart(
			    "BIỂU ĐỒ TỔNG LỢI NHUẬN THEO THÁNG/" +  Year.now().getValue(),
				"Tháng/" + Year.now().getValue(),
				"Lợi nhuận",
				dataset3,
				PlotOrientation.VERTICAL,
				true,
				true,
				false
		    );
			CategoryPlot plot3 = (CategoryPlot) chart3.getPlot();
			CategoryAxis xAxis3 = plot3.getDomainAxis();
			xAxis3.setLowerMargin(0.05);
			xAxis3.setUpperMargin(0.05);
			NumberAxis yAxis3 = (NumberAxis) plot3.getRangeAxis();
			yAxis3.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
			BarRenderer renderer3 = (BarRenderer) plot3.getRenderer();
			renderer3.setDrawBarOutline(false);
			ChartPanel chartPanel3 = new ChartPanel(chart3);
			
			JFreeChart chart2 = ChartFactory.createBarChart(
				    "BIỂU ĐỒ TỔNG DOANH THU THEO THÁNG/" +  Year.now().getValue(),
					"Tháng/" + Year.now().getValue(),
					"Doanh thu",
					dataset2,
					PlotOrientation.VERTICAL,
					true,
					true,
					false
			    );
				CategoryPlot plot2 = (CategoryPlot) chart2.getPlot();
				CategoryAxis xAxis2 = plot2.getDomainAxis();
				xAxis2.setLowerMargin(0.05);
				xAxis2.setUpperMargin(0.05);
				NumberAxis yAxis2 = (NumberAxis) plot2.getRangeAxis();
				yAxis2.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
				BarRenderer renderer2 = (BarRenderer) plot2.getRenderer();
				renderer2.setDrawBarOutline(false);
				for(int i = 0; i < 12; i++) {
					renderer2.setSeriesPaint(i, Color.BLUE);
				}
				ChartPanel chartPanel2 = new ChartPanel(chart2);
			
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.weightx = 0.5;
			gbc.weighty = 1.0;
			gbc.fill = GridBagConstraints.BOTH;
			dialog.add(chartPanel2, gbc);
			gbc.gridx = 1;
	        gbc.gridy = 0;
	        gbc.weightx = 0.5;
	        gbc.weighty = 1.0;
	        gbc.fill = GridBagConstraints.BOTH;
	        
			dialog.add(chartPanel3, gbc);
			dialog.pack();
			dialog.setSize(1200, 720);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
	}
	private void showBieuDoLoiNhuanTheoNgayTrongThang() {
		JDialog dialog = new JDialog();
        dialog.setTitle("Thống kê lợi nhuận");
        dialog.setLayout(new GridBagLayout());    
		DefaultCategoryDataset dataset3 = new DefaultCategoryDataset();
		DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
		 for (int i = 1; i < 32; i++) {
			 String z = String.valueOf(i);
	        	dataset3.addValue(daoBanHang.getLoiNhuanTheoNgayThangNam(z,String.valueOf(LocalDate.now().getMonthValue()), String.valueOf(Year.now().getValue())), "Lợi nhuận", z);
	        	dataset2.addValue(daoBanHang.getTongTienTheoNgayThangNam(z,String.valueOf(LocalDate.now().getMonthValue()), String.valueOf(Year.now().getValue())), "Doanh thu", z);
	        }
		JFreeChart chart3 = ChartFactory.createBarChart(
			    "BIỂU ĐỒ TỔNG LỢI NHUẬN THÁNG " +LocalDate.now().getMonthValue()+"/"  +  Year.now().getValue(),
				"Tháng/" + Year.now().getValue(),
				"Lợi nhuận",
				dataset3,
				PlotOrientation.VERTICAL,
				true,
				true,
				false
		    );
			CategoryPlot plot3 = (CategoryPlot) chart3.getPlot();
			CategoryAxis xAxis3 = plot3.getDomainAxis();
			xAxis3.setLowerMargin(0.02);
			xAxis3.setUpperMargin(0.02);
			NumberAxis yAxis3 = (NumberAxis) plot3.getRangeAxis();
			yAxis3.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
			BarRenderer renderer3 = (BarRenderer) plot3.getRenderer();
			renderer3.setDrawBarOutline(false);
			
			ChartPanel chartPanel3 = new ChartPanel(chart3);
			
			JFreeChart chart2 = ChartFactory.createBarChart(
				    "BIỂU ĐỒ TỔNG DOANH THU THÁNG " +LocalDate.now().getMonthValue()+"/" +  Year.now().getValue(),
					"Tháng "+LocalDate.now().getMonthValue()+"/" + Year.now().getValue(),
					"Doanh thu",
					dataset2,
					PlotOrientation.VERTICAL,
					true,
					true,
					false
			    );
				CategoryPlot plot2 = (CategoryPlot) chart2.getPlot();
				CategoryAxis xAxis2 = plot2.getDomainAxis();
				xAxis2.setLowerMargin(0.02);
				xAxis2.setUpperMargin(0.02);
				NumberAxis yAxis2 = (NumberAxis) plot2.getRangeAxis();
				yAxis2.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
				BarRenderer renderer2 = (BarRenderer) plot2.getRenderer();
				renderer2.setDrawBarOutline(false);
				for(int i = 0; i < 32; i++) {
					renderer2.setSeriesPaint(i, Color.BLUE);
				}
				ChartPanel chartPanel2 = new ChartPanel(chart2);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.weightx = 0.5;
			gbc.weighty = 1.0;
			gbc.fill = GridBagConstraints.BOTH;
			dialog.add(chartPanel3, gbc);
			
		    gbc.gridx = 1;
		    gbc.gridy = 0;
		    gbc.weightx = 0.5;
		    gbc.weighty = 1.0;
		    gbc.fill = GridBagConstraints.BOTH;
		    dialog.add(chartPanel2, gbc);
			dialog.pack();
			dialog.setSize(1200, 720);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
	}
	private void showBieuDoHoaDonTheoThangTrongNam() {
		JDialog dialog = new JDialog();
        dialog.setTitle("Thống kê");
        dialog.setLayout(new GridBagLayout());     
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
        for (int i = 1; i < 13; i++) {
        	String j = String.valueOf(i);
        	dataset.addValue(daoBanHang.getSoHoaDonTheoThangNam(j, String.valueOf(Year.now().getValue())), "Số hóa đơn", j);
        	
        }
        JFreeChart chart = ChartFactory.createBarChart(
            "BIỂU ĐỒ TỔNG HÓA ĐƠN THEO THÁNG/" +  Year.now().getValue(),
            "Tháng/" + Year.now().getValue(),
            "Số hóa đơn",
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
		DefaultTableModel dm = (DefaultTableModel) tblHoaDon.getModel();
		dm.getDataVector().removeAllElements();
	}
	private void clearData() {
		chooserDayStart.setDate(new Date());
		chooserDayEnd.setDate(new Date());
		cbLoc.setSelectedItem("Hiện tại");

	}
	public void loadDataHoaDonTheoNgay(Date ngayHienTai,Date ngayChon) {
		
		modelHoaDon.setRowCount(0);
		ArrayList<HoaDon> dsHoaDon = daoBanHang.getHoaDonTheoNgay(dfNgaySQL.format(ngayChon), dfNgaySQL.format(ngayHienTai));
		if (dsHoaDon.size() == 0) {
			JOptionPane.showMessageDialog(this, "Khung thời gian này không bán hoá đơn nào");
			XoaDuLieuTable();
			lblTongHoaDon.setText("SỐ LƯỢNG HOÁ ĐƠN BÁN RA : " + modelHoaDon.getRowCount());
			lblTongDoanhThu.setText("TỔNG DOANH THU : " + currencyFormat.format(0));
			lblTongLoiNhuan.setText("TỔNG LỢI NHUẬN : " + currencyFormat.format(0));
		} else {
			for (HoaDon hd : dsHoaDon) {
				String maHD = hd.getIdDonHang();
				String ngayLap = new SimpleDateFormat("dd/MM/yyyy").format(hd.getNgayLap());
				String maKH = hd.getKhachHang().getIdKhachHang();
				String maNV = hd.getNhanVien().getId();
				String tienKhachDua = currencyFormat.format(hd.getTienKhachDua());
				String tongTien = currencyFormat.format(hd.getTongTien());
				String tongLoiNhuan= currencyFormat.format(hd.getTongLoiNhuan());
				modelHoaDon.addRow(new String[] {maHD, ngayLap, maKH, maNV, tienKhachDua, tongTien,tongLoiNhuan});
			}
			lblTongHoaDon.setText("SỐ LƯỢNG HOÁ ĐƠN BÁN RA : " + modelHoaDon.getRowCount());
			lblTongDoanhThu.setText("TỔNG DOANH THU : " + currencyFormat.format(daoBanHang.getTongTienTheoNgay(dfNgaySQL.format(ngayChon), dfNgaySQL.format(ngayHienTai))));
			lblTongLoiNhuan.setText("TỔNG LỢI NHUẬN : " + currencyFormat.format(daoBanHang.getLoiNhuanTheoNgay(dfNgaySQL.format(ngayChon), dfNgaySQL.format(ngayHienTai))));
		}
	}
	public void loadDataHoaDonTheoTuyChinh() {
		
		modelHoaDon.setRowCount(0);
		if (chooserDayStart.getDate() == null) {
			JOptionPane.showMessageDialog(this, "Chưa chọn ngày bắt đầu");
			chooserDayStart.requestFocus();
		} else if (chooserDayEnd.getDate() == null) {
			JOptionPane.showMessageDialog(this, "Chưa chọn ngày kết thúc");
			chooserDayEnd.requestFocus();
		}
			else if(chooserDayEnd.getDate().before(chooserDayStart.getDate())) {
				JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải trước ngày kết thúc");
				chooserDayEnd.requestFocus();
			}
		else {
			ArrayList<HoaDon> dsHoaDon = daoBanHang.getHoaDonTheoNgay(dfNgaySQL.format(chooserDayStart.getDate()), dfNgaySQL.format(chooserDayEnd.getDate()));
			if (dsHoaDon.size() == 0) {
				JOptionPane.showMessageDialog(this, "Khung thời gian này không bán hoá đơn nào");
				
				lblTongHoaDon.setText("SỐ LƯỢNG HOÁ ĐƠN BÁN RA : " + modelHoaDon.getRowCount());
				lblTongDoanhThu.setText("TỔNG DOANH THU : " + currencyFormat.format(0));
				lblTongLoiNhuan.setText("TỔNG LỢI NHUẬN : " + currencyFormat.format(0));
			} else {
				for (HoaDon hd : dsHoaDon) {
					
					String maHD = hd.getIdDonHang();
					String ngayLap = new SimpleDateFormat("dd/MM/yyyy").format(hd.getNgayLap());
					String maKH = hd.getKhachHang().getIdKhachHang();
					String maNV = hd.getNhanVien().getId();
					String tienKhachDua = currencyFormat.format(hd.getTienKhachDua());
					String tongTien = currencyFormat.format(hd.getTongTien());
					String tongLoiNhuan= currencyFormat.format(hd.getTongLoiNhuan());
					modelHoaDon.addRow(new String[] {maHD, ngayLap, maKH, maNV, tienKhachDua, tongTien,tongLoiNhuan});
				}
				lblTongHoaDon.setText("SỐ LƯỢNG HOÁ ĐƠN BÁN RA : " + modelHoaDon.getRowCount());
				lblTongDoanhThu.setText("TỔNG DOANH THU : " + currencyFormat.format(daoBanHang.getTongTienTheoNgay(dfNgaySQL.format(chooserDayStart.getDate()), dfNgaySQL.format(chooserDayEnd.getDate()))));
				lblTongLoiNhuan.setText("TỔNG LỢI NHUẬN : " + currencyFormat.format(daoBanHang.getLoiNhuanTheoNgay(dfNgaySQL.format(chooserDayStart.getDate()), dfNgaySQL.format(chooserDayEnd.getDate()))));

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
				loadDataHoaDonTheoTuyChinh();
			} else if("Hiện tại".equals(selectedOption)) {
				chooserDayStart.setEnabled(false);
				chooserDayEnd.setEnabled(false);
				Date ngayHienTai= new Date();
				loadDataHoaDonTheoNgay(ngayHienTai, ngayHienTai);
			}
			else if("7 ngày gần nhất".equals(selectedOption)) {
				chooserDayStart.setEnabled(false);
				chooserDayEnd.setEnabled(false);
				Date ngayHienTai= new Date();
				Calendar calendar = Calendar.getInstance();
			    calendar.setTime(ngayHienTai);
			    calendar.add(Calendar.DAY_OF_MONTH, -7);
			    Date sevenDaysAgo = calendar.getTime();
				loadDataHoaDonTheoNgay(ngayHienTai, sevenDaysAgo);
			}
			 else if("1 tháng gần nhất".equals(selectedOption)) {
					chooserDayStart.setEnabled(false);
					chooserDayEnd.setEnabled(false);
					Date ngayHienTai= new Date();
					Calendar calendar = Calendar.getInstance();
				    calendar.setTime(ngayHienTai);
				    calendar.add(Calendar.MONTH, -1);
				    Date MonthAgo = calendar.getTime();
					loadDataHoaDonTheoNgay(ngayHienTai, MonthAgo);
				}
			 else if("3 tháng gần nhất".equals(selectedOption)) {
					chooserDayStart.setEnabled(false);
					chooserDayEnd.setEnabled(false);
					Date ngayHienTai= new Date();
					Calendar calendar = Calendar.getInstance();
				    calendar.setTime(ngayHienTai);
				    calendar.add(Calendar.MONTH, -3);
				    Date MonthAgo = calendar.getTime();
					loadDataHoaDonTheoNgay(ngayHienTai, MonthAgo);
				}
			 else if("6 tháng gần nhất".equals(selectedOption)) {
					chooserDayStart.setEnabled(false);
					chooserDayEnd.setEnabled(false);
					Date ngayHienTai= new Date();
					Calendar calendar = Calendar.getInstance();
				    calendar.setTime(ngayHienTai);
				    calendar.add(Calendar.MONTH, -6);
				    Date MonthAgo = calendar.getTime();
					loadDataHoaDonTheoNgay(ngayHienTai, MonthAgo);
				}
			 else if("1 năm gần nhất".equals(selectedOption)) {
					chooserDayStart.setEnabled(false);
					chooserDayEnd.setEnabled(false);
					Date ngayHienTai= new Date();
					Calendar calendar = Calendar.getInstance();
				    calendar.setTime(ngayHienTai);
				    calendar.add(Calendar.YEAR, -1);
				    Date MonthAgo = calendar.getTime();
					loadDataHoaDonTheoNgay(ngayHienTai, MonthAgo);
				}
		}
	}
}
