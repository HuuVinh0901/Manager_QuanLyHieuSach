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
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
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
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import com.toedter.calendar.JDateChooser;

import dao.DAOQuanLySanPham;
import dao.DAO_QuanLyBanHang;
import models.ChiTietHoaDon;
import models.HoaDon;
import models.NhanVien;
import models.SanPhamCon;

public class ThongKeSanPhamQuanLyView extends JPanel implements ActionListener, ItemListener {
	private JDateChooser chooserDayStart;
	private JDateChooser chooserDayEnd;
	private JButton btnTimKiem;
	private JButton btnThongKe;
	private JButton btnInThongKe;
	private JButton btnLamMoi;
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
	private DAOQuanLySanPham daoSanPham;
	private JLabel lblTongLN;
	private JPanel pnTongLN;
	public ThongKeSanPhamQuanLyView() {
		setLayout(new BorderLayout());
		daoSanPham = new DAOQuanLySanPham();
		currencyFormat.setCurrency(Currency.getInstance("VND"));
		daoBanHang = new DAO_QuanLyBanHang();
		dfNgaySQL = new SimpleDateFormat("yyyy-MM-dd");
		JPanel pn2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
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
		pnTongLN = new JPanel();
		JLabel lblTitle = new JLabel("THỐNG KÊ SẢN PHẨM");
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
		btnThongKe = new JButton("Thống kê sản phẩm");
		btnInThongKe = new JButton("In thống kê");
		btnLamMoi = new JButton("Làm mới");

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

		modelHoaDon.addColumn("Mã sản phẩm");
		modelHoaDon.addColumn("Tên sản phẩm");
		modelHoaDon.addColumn("Loại sản phẩm");
		modelHoaDon.addColumn("Nhà cung cấp");
		modelHoaDon.addColumn("Số lượng tồn");
		modelHoaDon.addColumn("Số lượng đã bán");
		modelHoaDon.addColumn("Giá nhập");
		modelHoaDon.addColumn("Giá bán");
		modelHoaDon.addColumn("Doanh thu");
		modelHoaDon.addColumn("Lợi nhuận");
		modelHoaDon.addColumn("Tình trạng");
		tblHoaDon.setModel(modelHoaDon);
		JScrollPane scrollTblHD = new JScrollPane(tblHoaDon);
		scrollTblHD.setBorder(BorderFactory.createTitledBorder("Thông tin sản phẩm"));
		pnDay.setBorder(BorderFactory.createTitledBorder("Chức năng tìm kiếm"));
		pn1.setBorder(BorderFactory.createTitledBorder("In & Thống kê"));

		lblTongHoaDon = new JLabel("TỔNG DOANH THU : " + currencyFormat.format(0));
		lblTongLN = new JLabel("TỔNG LỢI NHUẬN: " + currencyFormat.format(0));
		lblTongDoanhThu = new JLabel("TỔNG SỐ LƯỢNG TỒN  : " + currencyFormat.format(0));
		lblTongLoiNhuan = new JLabel("TỔNG SỐ LƯỢNG ĐÃ BÁN  : " + currencyFormat.format(0));
		
		lblTongLN.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTongLN.setForeground(Color.red);
		
		lblTongHoaDon.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTongHoaDon.setForeground(Color.red);
		
		lblTongDoanhThu.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTongDoanhThu.setForeground(new Color(26, 102, 227));
		
		lblTongLoiNhuan.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTongLoiNhuan.setForeground(new Color(26, 102, 227));
		
		pnTongHoaDon.add(lblTongHoaDon);
		pnTongLN.add(lblTongLN);
		pnTongDoanhThu.add(lblTongDoanhThu);
		pnTongLoiNhuan.add(lblTongLoiNhuan);
		pn2.add(pnTongHoaDon);
		pn2.add(pnTongLN);
		pn2.add(pnTongDoanhThu);
		pn2.add(pnTongLoiNhuan);
		add(pnTop, BorderLayout.NORTH);
		add(pn2, BorderLayout.CENTER);
		add(scrollTblHD, BorderLayout.SOUTH);

		btnTimKiem.addActionListener(this);
		btnThongKe.addActionListener(this);
		btnInThongKe.addActionListener(this);
		btnLamMoi.addActionListener(this);
		cbLoc.addItemListener(this);
		

		Date ngayHienTai = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(ngayHienTai);
		loadDataSanPhamTheoNgay(ngayHienTai, ngayHienTai);
		
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
				} else {
					loadDataHoaDonTheoTuyChinh();
				}
			}
		} else if (o.equals(btnThongKe)) {
			String selectedOption = (String) cbLoc.getSelectedItem();
			if ("6 tháng gần nhất".equals(selectedOption) || "3 tháng gần nhất".equals(selectedOption) || "7 ngày gần nhất".equals(selectedOption)
					|| "1 năm gần nhất".equals(selectedOption)) {
				Date ngayHienTai = new Date();
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(ngayHienTai);
				calendar.add(Calendar.DAY_OF_MONTH, -7);
				Date sevenDaysAgo = calendar.getTime();
				showBieuDoTheoNgayVaThang(ngayHienTai,sevenDaysAgo);
			}else if("Hiện tại".equals(selectedOption)) {
				Date ngayHienTai = new Date();
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(ngayHienTai);
				showBieuDoTheoNgayVaThang(ngayHienTai,ngayHienTai);
			}
		} else if (o.equals(btnInThongKe)) {
			inThongKe();
		} 
	}

	private void inThongKe() {

	}

	private void showBieuDoHoaDonTheoNgayTrongThang() {

	}

	private void showBieuDoLoiNhuanTheoThangTrongNam() {

	}

	private void showBieuDoLoiNhuanTheoNgayTrongThang() {

	}

	private void showBieuDoTheoNgayVaThang(Date ngayHienTai, Date ngayChon) {
	    ArrayList<SanPhamCon> dsSanPham = new ArrayList<SanPhamCon>();
	    DefaultCategoryDataset datasetSoLuongBan = createDatasetSoLuongBan("Số lượng bán chạy", ngayHienTai, ngayChon);
	    DefaultCategoryDataset datasetSoLuongBanCham = createDatasetSoLuongBanCham("Số lượng bán chậm", ngayHienTai, ngayChon);
	    DefaultCategoryDataset datasetDoanhThu = createDatasetDoanhThu("Doanh thu", ngayHienTai, ngayChon);
	    DefaultCategoryDataset datasetLoiNhuan = createDatasetLoiNhuan("Lợi nhuận", ngayHienTai, ngayChon);
	    DefaultPieDataset dataset = createDatasetNhaCungCap("Tỉ lệ nhà cung cấp", ngayHienTai, ngayChon);
	    DefaultPieDataset datasetLoaiSanPham = createDatasetLoaiSanPham("Tỉ lệ LoaiSanPham", ngayHienTai, ngayChon);
	    
	    
	    JFreeChart barChartSoLuongBan = createBarChart(datasetSoLuongBan, "Sản phẩm", "Số lượng sản phẩm bán chạy");
	    JFreeChart barChartSoLuongBanCham = createBarChart(datasetSoLuongBanCham, "Sản phẩm", "Số lượng sản phẩm bán chậm");
	    JFreeChart lineChartDoanhThu = createLineChart(datasetDoanhThu, "Sản phẩm", "Doanh thu");
	    JFreeChart barChartLoiNhuan = createBarChart(datasetLoiNhuan, "Sản phẩm", "Lợi nhuận");
	    JFreeChart pieChart = createPieChart(dataset, "Tỉ lệ nhà cung cấp của sản phẩm");
	    JFreeChart pieChartLoaiSanPham = createPieChart(datasetLoaiSanPham, "Tỉ lệ loại sản phẩm của sản phẩm");
	    
	    ChartPanel chartPanelSoLuongBan = new ChartPanel(barChartSoLuongBan);
	    ChartPanel chartPanelSoLuongBanCham = new ChartPanel(barChartSoLuongBanCham);
	    ChartPanel chartPanelDoanhThu = new ChartPanel(lineChartDoanhThu);
	    ChartPanel chartPanelLoiNhuan = new ChartPanel(barChartLoiNhuan);
	    ChartPanel chartPanel = new ChartPanel(pieChart);
	    ChartPanel chartPanelLoaiSanPham = new ChartPanel(pieChartLoaiSanPham);
	    
	    chartPanelSoLuongBan.setPreferredSize(new Dimension(400, 600)); 
	    chartPanelSoLuongBanCham.setPreferredSize(new Dimension(400, 600)); 
	    chartPanelDoanhThu.setPreferredSize(new Dimension(400, 300));
	    chartPanelLoiNhuan.setPreferredSize(new Dimension(400, 300));
	    chartPanel.setPreferredSize(new Dimension(400, 300));
	    chartPanelLoaiSanPham.setPreferredSize(new Dimension(400, 300));

	    JPanel contentPanel = new JPanel(new GridLayout(3,2 ));
	    contentPanel.add(chartPanelSoLuongBan);
	    contentPanel.add(chartPanelSoLuongBanCham);
	    contentPanel.add(chartPanelDoanhThu);
	    contentPanel.add(chartPanelLoiNhuan);
	    contentPanel.add(chartPanel);
	    contentPanel.add(chartPanelLoaiSanPham);
	    
	    JScrollPane scrollPane = new JScrollPane(contentPanel);

	    JDialog dialog = new JDialog();
	    dialog.setTitle("Thống kê sản phẩm");
	    dialog.setSize(1300, 800);
	    dialog.setLocationRelativeTo(null);

	    dialog.add(scrollPane);

	    dialog.setVisible(true);
	}


	private DefaultPieDataset createDatasetNhaCungCap(String label, Date ngayHienTai, Date ngayChon) {
	    DefaultPieDataset dataset = new DefaultPieDataset();
	    ArrayList<SanPhamCon> dsSanPham = daoSanPham.getTopSanPhamTheoNgay(dfNgaySQL.format(ngayChon), dfNgaySQL.format(ngayHienTai));

	    for (SanPhamCon sp : dsSanPham) {
	        if (sp.getIdNhaCungCap() != null && sp.getIdNhaCungCap().getIdNhaCungCap() != null) {
	            dataset.setValue(sp.getIdNhaCungCap().getIdNhaCungCap(), sp.getSoLuongBan());
	        }
	    }

	    return dataset;
	}
	private DefaultPieDataset createDatasetLoaiSanPham(String label, Date ngayHienTai, Date ngayChon) {
	    DefaultPieDataset dataset = new DefaultPieDataset();
	    ArrayList<SanPhamCon> dsSanPham = daoSanPham.getTopSanPhamTheoNgay(dfNgaySQL.format(ngayChon), dfNgaySQL.format(ngayHienTai));

	    for (SanPhamCon sp : dsSanPham) {
	        if (sp.getIdLoaiSanPham() != null && sp.getIdLoaiSanPham().getIdLoaiSanPham() != null) {
	            dataset.setValue(sp.getIdLoaiSanPham().getIdLoaiSanPham(), sp.getSoLuongBan());
	        }
	    }

	    return dataset;
	}

	private JFreeChart createPieChart(DefaultPieDataset dataset, String chartTitle) {
	    JFreeChart pieChart = ChartFactory.createPieChart(
	            chartTitle,
	            dataset,
	            true, true, false
	    );

	    PiePlot plot = (PiePlot) pieChart.getPlot();
	    plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1}"));
	    return pieChart;
	}

	private DefaultCategoryDataset createDatasetSoLuongBan(String label, Date ngayHienTai, Date ngayChon) {
	    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	    ArrayList<SanPhamCon> dsSanPham = daoSanPham.getTopSanPhamTheoNgay(dfNgaySQL.format(ngayChon), dfNgaySQL.format(ngayHienTai));

	    int count = 0;
	    for (SanPhamCon sp : dsSanPham) {
	        if (count < 10) {
	            dataset.addValue(sp.getSoLuongBan(), label, sp.getTenSanPham());
	            count++;
	        } else {
	            break;
	        }
	    }

	    return dataset;
	}
	
	private DefaultCategoryDataset createDatasetSoLuongBanCham(String label, Date ngayHienTai, Date ngayChon) {
	    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	    ArrayList<SanPhamCon> dsSanPham = daoSanPham.getTopSanPhamBanChamTheoNgay(dfNgaySQL.format(ngayChon), dfNgaySQL.format(ngayHienTai));

	    int count = 0;
	    for (SanPhamCon sp : dsSanPham) {
	        if (count < 10) {
	            dataset.addValue(sp.getSoLuongBan(), label, sp.getTenSanPham());
	            count++;
	        } else {
	            break;
	        }
	    }

	    return dataset;
	}
	

	private DefaultCategoryDataset createDatasetDoanhThu(String label, Date ngayHienTai, Date ngayChon) {
	    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	    ArrayList<SanPhamCon> dsSanPham = daoSanPham.getTopSanPhamTheoNgay(dfNgaySQL.format(ngayChon), dfNgaySQL.format(ngayHienTai));

	    int count = 0;
	    for (SanPhamCon sp : dsSanPham) {
	        if (count < 10) {
	            dataset.addValue(sp.getDoanhThu(), label, sp.getTenSanPham());
	            count++;
	        } else {
	            break;
	        }
	    }

	    return dataset;
	}

	private DefaultCategoryDataset createDatasetLoiNhuan(String label, Date ngayHienTai, Date ngayChon) {
	    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	    ArrayList<SanPhamCon> dsSanPham = daoSanPham.getTopSanPhamTheoNgay(dfNgaySQL.format(ngayChon), dfNgaySQL.format(ngayHienTai));

	    int count = 0;
	    for (SanPhamCon sp : dsSanPham) {
	        if (count < 10) {
	            dataset.addValue(sp.getLoiNhuan(), label, sp.getTenSanPham());
	            count++;
	        } else {
	            break;
	        }
	    }

	    return dataset;
	}

	private JFreeChart createBarChart(DefaultCategoryDataset dataset, String categoryAxisLabel, String valueAxisLabel) {
	    JFreeChart barChart = ChartFactory.createBarChart(
	            valueAxisLabel,
	            categoryAxisLabel,
	            valueAxisLabel,
	            dataset,
	            PlotOrientation.VERTICAL,
	            true, true, false
	    );

	    CategoryPlot plot = barChart.getCategoryPlot();
	    BarRenderer renderer = (BarRenderer) plot.getRenderer();
	    renderer.setSeriesPaint(0, Color.blue);

	    CategoryAxis xAxis = (CategoryAxis) plot.getDomainAxis();
	    xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);

	    return barChart;
	}

	private JFreeChart createLineChart(DefaultCategoryDataset dataset, String categoryAxisLabel, String valueAxisLabel) {
	    JFreeChart lineChart = ChartFactory.createLineChart(
	            valueAxisLabel,
	            categoryAxisLabel,
	            valueAxisLabel,
	            dataset,
	            PlotOrientation.VERTICAL,
	            true, true, false
	    );

	    CategoryPlot plot = lineChart.getCategoryPlot();
	    LineAndShapeRenderer renderer = new LineAndShapeRenderer();
	    CategoryAxis xAxis = (CategoryAxis) plot.getDomainAxis();
	    xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);
	    plot.setRenderer(renderer);
	    renderer.setSeriesPaint(0, Color.red);

	    return lineChart;
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

	public void loadDataSanPhamTheoNgay(Date ngayHienTai, Date ngayChon) {
		modelHoaDon.setRowCount(0);
		ArrayList<SanPhamCon> dsSanPham = daoSanPham.getTopSanPhamTheoNgay(dfNgaySQL.format(ngayChon),
				dfNgaySQL.format(ngayHienTai));
		int tongSoLuongTon = 0;
		int tongSoLuongDaBan = 0;
		if (dsSanPham.isEmpty()) {
//			JOptionPane.showMessageDialog(this, "Khung thời gian này không có sản phẩm nào");
			XoaDuLieuTable();
		} else {
			for (SanPhamCon sp : dsSanPham) {
				String idSanPham = sp.getIdSanPham();
				String tenSanPham = sp.getTenSanPham();
				String loaiSanPham = sp.getIdLoaiSanPham().getIdLoaiSanPham();
				String nhaCungCap = sp.getIdNhaCungCap().getIdNhaCungCap();
				int soLuong = sp.getSoLuong();
				int soLuongBan = sp.getSoLuongBan();
				double giaNhap = sp.getGiaNhap();
				double giaBan = sp.giaBan();
				double doanhThu = sp.getDoanhThu();
				double loiNhuan = sp.getLoiNhuan();
				String trangThai = sp.getTrangThai() + "";

				 modelHoaDon.addRow(new String[] { idSanPham, tenSanPham, loaiSanPham, nhaCungCap,
		                    String.valueOf(soLuong), String.valueOf(soLuongBan), currencyFormat.format(giaNhap),
		                    currencyFormat.format(giaBan), currencyFormat.format(doanhThu),
		                    currencyFormat.format(loiNhuan), trangThai });
			}

			
			double tongDoanhThu = daoSanPham.getDoanhThuTheoNgay(dfNgaySQL.format(ngayChon), dfNgaySQL.format(ngayHienTai));
			double tongLoiNhuan = daoSanPham.getLoiNhuanTheoNgay(dfNgaySQL.format(ngayChon), dfNgaySQL.format(ngayHienTai));
			double soLuongDaBan = daoSanPham.getSoLuongDaBanTheoNgay(dfNgaySQL.format(ngayChon), dfNgaySQL.format(ngayHienTai));
			double soLuongTon = daoSanPham.getSoLuongTonTheoNgay(dfNgaySQL.format(ngayChon), dfNgaySQL.format(ngayHienTai));

			lblTongHoaDon.setText("TỔNG DOANH THU : " + currencyFormat.format(tongDoanhThu)); 
			lblTongLN.setText("TỔNG LỢI NHUẬN: " + currencyFormat.format(tongLoiNhuan)); 
			lblTongLoiNhuan.setText("TỔNG SỐ LƯỢNG ĐÃ BÁN : " + Math.round(soLuongDaBan));
			lblTongDoanhThu.setText("TỔNG SỐ LƯỢNG TỒN : " + Math.round(soLuongTon));

		}
	}

	public void loadDataHoaDonTheoTuyChinh() {

	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == cbLoc && e.getStateChange() == ItemEvent.SELECTED) {
			String selectedOption = (String) cbLoc.getSelectedItem();
			if ("Tùy chỉnh".equals(selectedOption)) {
				chooserDayStart.setEnabled(true);
				chooserDayEnd.setEnabled(true);
				loadDataHoaDonTheoTuyChinh();
			} else if ("Hiện tại".equals(selectedOption)) {
				chooserDayStart.setEnabled(false);
				chooserDayEnd.setEnabled(false);
				Date ngayHienTai = new Date();
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(ngayHienTai);
				loadDataSanPhamTheoNgay(ngayHienTai, ngayHienTai);
			} else if ("7 ngày gần nhất".equals(selectedOption)) {
				chooserDayStart.setEnabled(false);
				chooserDayEnd.setEnabled(false);
				Date ngayHienTai = new Date();
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(ngayHienTai);
				calendar.add(Calendar.DAY_OF_MONTH, -7);
				Date sevenDaysAgo = calendar.getTime();
				loadDataSanPhamTheoNgay(ngayHienTai, sevenDaysAgo);
			}
		}
	}
}
