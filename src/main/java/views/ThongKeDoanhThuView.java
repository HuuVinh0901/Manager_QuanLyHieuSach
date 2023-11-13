package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import dao.DAO_QuanLyBanHang;
import models.ChiTietHoaDon;
import models.HoaDon;
import models.KhachHang;
import models.NhanVien;
import models.SanPhamCha;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
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

public class ThongKeDoanhThuView extends JPanel implements ActionListener{
	
	private JDateChooser chooserDayStart;
	private JDateChooser chooserDayEnd;
	private JButton btnTimKiem;
	private JButton btnThongKe;
	private JButton btnInThongKe;
	private JButton btnLamMoi;
	private JTable tblHoaDon;
	private DefaultTableModel modelHoaDon;
	private DAO_QuanLyBanHang daoBanHang;
	private SimpleDateFormat dfNgaySQL;
	private JLabel lblTongHoaDon;
	private JLabel lblTongDoanhThu;
	private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
    
	
	public ThongKeDoanhThuView() {
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
		JLabel lblTitle = new JLabel("THỐNG KÊ DOANH THU");
		JLabel lblDayStart = new JLabel("Từ ngày");
		JLabel lblDayEnd = new JLabel("Đến ngày");
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
		btnTimKiem = new JButton("Tìm kiếm");
		btnThongKe = new JButton("Thống kê");
		btnInThongKe = new JButton("In thống kê");
		btnLamMoi = new JButton("Làm mới");
		pnTitle.add(lblTitle);
		pnTuNgay.add(lblDayStart);
		pnTuNgay.add(chooserDayStart);
		pnDenNgay.add(lblDayEnd);
		pnDenNgay.add(chooserDayEnd);
		pnDay.add(pnTuNgay);
		pnDay.add(pnDenNgay);
		pnDay.add(btnTimKiem);
		
		pnInThongKe.add(btnThongKe);
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
        tblHoaDon.setModel(modelHoaDon);
		JScrollPane scrollTblHD = new JScrollPane(tblHoaDon);
		scrollTblHD.setBorder(BorderFactory.createTitledBorder("Thông tin hoá đơn"));
		pnDay.setBorder(BorderFactory.createTitledBorder("Chức năng tìm kiếm"));
		pn1.setBorder(BorderFactory.createTitledBorder("In & Thống kê"));
		
		lblTongHoaDon = new JLabel("SỐ LƯỢNG HOÁ ĐƠN BÁN RA : " + modelHoaDon.getRowCount());
		lblTongDoanhThu = new JLabel("TỔNG DOANH THU : " + currencyFormat.format(0));
		lblTongHoaDon.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTongHoaDon.setForeground(new Color(26, 102, 227));
		lblTongDoanhThu.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTongDoanhThu.setForeground(Color.red);
		pnTongHoaDon.add(lblTongHoaDon);
		pnTongDoanhThu.add(lblTongDoanhThu);
		pn2.add(pnTongHoaDon, BorderLayout.WEST);
		pn2.add(pnTongDoanhThu, BorderLayout.CENTER);
		
		add(pnTop, BorderLayout.NORTH);
		add(scrollTblHD, BorderLayout.CENTER);
		add(pn2, BorderLayout.SOUTH);

		btnTimKiem.addActionListener(this);
		btnThongKe.addActionListener(this);
		btnInThongKe.addActionListener(this);
		btnLamMoi.addActionListener(this);
	}

	public void loadDataHoaDon() {
		modelHoaDon.setRowCount(0);
		if (chooserDayStart.getDate() == null) {
			JOptionPane.showMessageDialog(this, "Chưa chọn ngày bắt đầu");
			chooserDayStart.requestFocus();
		} else if (chooserDayEnd.getDate() == null) {
			JOptionPane.showMessageDialog(this, "Chưa chọn ngày kết thúc");
			chooserDayEnd.requestFocus();
		} else {
			ArrayList<HoaDon> dsHoaDon = daoBanHang.getHoaDonTheoNgay(dfNgaySQL.format(chooserDayStart.getDate()), dfNgaySQL.format(chooserDayEnd.getDate()));
			if (dsHoaDon.size() == 0) {
				JOptionPane.showMessageDialog(this, "Khung thời gian này không bán hoá đơn nào");
			} else {
				for (HoaDon hd : dsHoaDon) {
					String maHD = hd.getIdDonHang();
					String ngayLap = new SimpleDateFormat("dd/MM/yyyy").format(hd.getNgayLap());
					String maKH = hd.getKhachHang().getIdKhachHang();
					String maNV = hd.getNhanVien().getId();
					String tienKhachDua = currencyFormat.format(hd.getTienKhachDua());
					String tongTien = currencyFormat.format(hd.getTongTien());
					modelHoaDon.addRow(new String[] {maHD, ngayLap, maKH, maNV, tienKhachDua, tongTien});
				}
				lblTongHoaDon.setText("SỐ LƯỢNG HOÁ ĐƠN BÁN RA : " + modelHoaDon.getRowCount());
				lblTongDoanhThu.setText("TỔNG DOANH THU : " + currencyFormat.format(daoBanHang.getTongTienTheoNgay(dfNgaySQL.format(chooserDayStart.getDate()), dfNgaySQL.format(chooserDayEnd.getDate()))));
			}
		}
			
	}
	
	
	public void ghiFileExcel(String filePath) {
		ArrayList<HoaDon> dsHoaDon = new ArrayList<HoaDon>();
		if (chooserDayStart.getDate() == null) {
			JOptionPane.showMessageDialog(this, "Chưa chọn ngày bắt đầu");
			chooserDayStart.requestFocus();
		} else if (chooserDayEnd.getDate() == null){
			JOptionPane.showMessageDialog(this, "Chưa chọn ngày kết thúc");
			chooserDayEnd.requestFocus();
		} else {
			dsHoaDon = daoBanHang.getHoaDonTheoNgay(dfNgaySQL.format(chooserDayStart.getDate()), dfNgaySQL.format(chooserDayEnd.getDate()));
			if (dsHoaDon.size() == 0) {
				JOptionPane.showMessageDialog(this, "Khung thời gian này không bán hoá đơn nào để ghi vào file excel");
			} else {
				try (Workbook workbook = new XSSFWorkbook()) {
					String dayStart = String.valueOf(chooserDayStart.getDate());
					String dayEnd = String.valueOf(chooserDayEnd.getDate());
		            Sheet sheet = workbook.createSheet("Danh sách Hoá Đơn từ " + dayStart + " đến " + dayEnd);
		            Row headerRow = sheet.createRow(0);
			        int columnCount = modelHoaDon.getColumnCount();
			        for (int i = 0; i < columnCount; i++) {
			            Cell cell = headerRow.createCell(i);
			            cell.setCellValue(modelHoaDon.getColumnName(i));
			        }
		            int rowNumber = 1;
		            for (HoaDon hd : dsHoaDon) {
		                Row row = sheet.createRow(rowNumber++);
		                Cell maHDCell = row.createCell(0);
		                maHDCell.setCellValue(hd.getIdDonHang());

		                Cell ngayLapCell = row.createCell(1);
		                ngayLapCell.setCellValue(new SimpleDateFormat("dd/MM/yyyy").format(hd.getNgayLap()));

		                Cell maKHCell = row.createCell(2);
		                maKHCell.setCellValue(hd.getKhachHang().getIdKhachHang());

		                Cell maNVCell = row.createCell(3);
		                maNVCell.setCellValue(hd.getNhanVien().getId());

		                Cell tienKhachDuaCell = row.createCell(4);
		                tienKhachDuaCell.setCellValue(hd.getTienKhachDua());

		                Cell tongTienCell = row.createCell(5);
		                tongTienCell.setCellValue(hd.getTongTien());
		            }

		            // Ghi workbook ra tệp Excel
		            try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
		                workbook.write(outputStream);
		            }

		            System.out.println("Dữ liệu HoaDon đã được ghi vào tệp Excel thành công.");
		            JOptionPane.showMessageDialog(this, "Xuất thống kê excel thành công");
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
			}
		}
		
		
        
    }
	
	private void showBieuDo() {
		JDialog dialog = new JDialog();
        dialog.setTitle("Thống kê");
        dialog.setLayout(new GridBagLayout());     
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
        for (int i = 1; i < 13; i++) {
        	String j = String.valueOf(i);
        	dataset.addValue(daoBanHang.getSoHoaDonTheoThangNam(j, String.valueOf(Year.now().getValue())), "Số hóa đơn", j);
        	dataset2.addValue(daoBanHang.getTongTienTheoThangNam(j, String.valueOf(Year.now().getValue())), "Doanh thu", j);
        }
        
        JFreeChart chart = ChartFactory.createBarChart(
            "Biểu đồ thống kê số hóa đơn đã bán theo tháng/" +  Year.now().getValue(),
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
		
	    JFreeChart chart2 = ChartFactory.createBarChart(
		    "Biểu đồ thống kê tổng doanh thu theo tháng/" +  Year.now().getValue(),
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
		dialog.add(chartPanel, gbc);

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
	
	public void lamMoi() {
		modelHoaDon.setRowCount(0);
		chooserDayStart.setDate(null);
		chooserDayEnd.setDate(null);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnTimKiem)) {
			loadDataHoaDon();
		} else if (o.equals(btnThongKe)) {
			showBieuDo();
		} else if (o.equals(btnInThongKe)) {
			String filePath = "C:\\Users\\Admin\\Documents\\HoaDon.xlsx";
			ghiFileExcel(filePath);
		} else if (o.equals(btnLamMoi)) {
			lamMoi();
		} 
		
	}	
}
