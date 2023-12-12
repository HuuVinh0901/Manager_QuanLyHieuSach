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

import org.apache.poi.sl.usermodel.Sheet;
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
import models.LoaiSanPham;
import models.NhaCungCap;
import models.NhanVien;
import models.SanPhamCon;
import utils.TrangThaiSPEnum;

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

		lblTongLN.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTongLN.setForeground(Color.red);

		lblTongHoaDon.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTongHoaDon.setForeground(Color.red);

		lblTongDoanhThu.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTongDoanhThu.setForeground(new Color(26, 102, 227));

		lblTongLoiNhuan.setFont(new Font("Tahoma", Font.BOLD, 15));
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
			if ("Tùy chỉnh".equalsIgnoreCase((String) cbLoc.getSelectedItem())) {
				Date currentDate = new Date();
				Date startDate = chooserDayStart.getDate();
				Date endDate = chooserDayEnd.getDate();
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(startDate);
				if (startDate == null || endDate == null) {
					JOptionPane.showMessageDialog(this, "Vui lòng chọn cả ngày bắt đầu và ngày kết thúc", "Cảnh báo",
							JOptionPane.WARNING_MESSAGE);
				} else if (startDate.after(currentDate) || endDate.after(currentDate)) {
					JOptionPane.showMessageDialog(this,
							"Ngày bắt đầu hoặc ngày kết thúc không thể lớn hơn ngày hiện tại", "Cảnh báo",
							JOptionPane.WARNING_MESSAGE);
				} else if (endDate.before(startDate)) {
					JOptionPane.showMessageDialog(this, "Ngày kết thúc không thể nhỏ hơn ngày bắt đầu", "Cảnh báo",
							JOptionPane.WARNING_MESSAGE);
				} else {
					loadDataSanPhamTheoNgay(startDate, endDate);
					loadSanPhamTheoTuyChinh();
				}
			}
		} else if (o.equals(btnThongKe)) {
			String selectedOption = (String) cbLoc.getSelectedItem();
			if ("6 tháng gần nhất".equals(selectedOption) || "3 tháng gần nhất".equals(selectedOption)
					|| "7 ngày gần nhất".equals(selectedOption) || "1 năm gần nhất".equals(selectedOption)) {
				Date ngayHienTai = new Date();
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(ngayHienTai);
				calendar.add(Calendar.DAY_OF_MONTH, -7);
				Date sevenDaysAgo = calendar.getTime();
				showBieuDoTheoNgayVaThang(ngayHienTai, sevenDaysAgo);
			} else if ("Hiện tại".equals(selectedOption)) {
				Date ngayHienTai = new Date();
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(ngayHienTai);
				showBieuDoTheoNgayVaThang(ngayHienTai, ngayHienTai);
			}
		} else if (o.equals(btnInThongKe)) {
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			String filePath = System.getProperty("user.dir") + "/src/main/resources/DataExports/SanPham/ThongKe/SP_"
					+ timeStamp + ".xlsx";
			ghiFileExcel(filePath);
		}
	}

	private Double parseDoubleWithMultiplePoints(String input) {
		String cleanedInput = input.replaceAll("[^\\d.]", "");
		cleanedInput = cleanedInput.contains(".")
				? cleanedInput.substring(0, cleanedInput.indexOf(".") + 1)
						+ cleanedInput.substring(cleanedInput.indexOf(".") + 1).replace(".", "")
				: cleanedInput;
		return cleanedInput.isEmpty() ? 0.0 : Double.parseDouble(cleanedInput);
	}

	private void ghiFileExcel(String filePath) {
		int rowCount = modelHoaDon.getRowCount();
		ArrayList<SanPhamCon> dsSanPham = new ArrayList<SanPhamCon>();
		for (int i = 0; i < rowCount; i++) {
			String idSanPham = (String) modelHoaDon.getValueAt(i, 0);
			String tenSanPham = (String) modelHoaDon.getValueAt(i, 1);
			String loaiSanPham = (String) modelHoaDon.getValueAt(i, 2);
			String nhaCungCap = (String) modelHoaDon.getValueAt(i, 3);
			int soLuong = Integer.parseInt((String) modelHoaDon.getValueAt(i, 4));
			int soLuongBan = Integer.parseInt((String) modelHoaDon.getValueAt(i, 5));
			String giaNhapStr = ((String) modelHoaDon.getValueAt(i, 6)).replaceAll("\\D+","");
			Double giaNhap = parseDoubleWithMultiplePoints(giaNhapStr);
			String doanhThuStr = ((String) modelHoaDon.getValueAt(i, 7)).replaceAll("\\D+","");
			Double doanhThu = parseDoubleWithMultiplePoints(doanhThuStr);
			String loiNhuanStr = ((String) modelHoaDon.getValueAt(i, 8)).replaceAll("\\D+","");
			Double loiNhuan = parseDoubleWithMultiplePoints(loiNhuanStr);
			String trangThaiStr = (String) modelHoaDon.getValueAt(i, 9);
			TrangThaiSPEnum trangThai = TrangThaiSPEnum.getByName(trangThaiStr);
			
			SanPhamCon sp = new SanPhamCon();
			sp.setIdSanPham(idSanPham);
			sp.setTenSanPham(tenSanPham);
			if (loaiSanPham != null) {
				LoaiSanPham loaiSP = new LoaiSanPham();
				loaiSP.setTenLoaiSanPham(loaiSanPham);
				sp.setIdLoaiSanPham(loaiSP);
			}

			if (nhaCungCap != null) {
				NhaCungCap ncc = new NhaCungCap();
				ncc.setTenNhaCungCap(nhaCungCap);
				sp.setIdNhaCungCap(ncc);
			}
			sp.setSoLuong(soLuong);
			sp.setSoLuongBan(soLuongBan);
			sp.setGiaNhap(giaNhap);
			sp.giaBan();
			sp.setDoanhThu(doanhThu);
			sp.setLoiNhuan(loiNhuan);
			sp.setTrangThai(trangThai);
			dsSanPham.add(sp);
		}
		try (Workbook workbook = new XSSFWorkbook()) {
			org.apache.poi.ss.usermodel.Sheet sheet = workbook.createSheet("Thống kê danh sách sản phẩm");
			Row headerRow = sheet.createRow(0);
			String[] columnNames = { "ID Sản phẩm", "Tên sản phẩm", "Loại sản phẩm", "Nhà cung cấp", "Số lượng",
					"Số lượng đã bán", "Giá nhập", "Giá bán", "Doanh thu", "Lợi nhuận", "Trạng thái" };
			for (int i = 0; i < columnNames.length; i++) {
				org.apache.poi.ss.usermodel.Cell cell = headerRow.createCell(i);
				cell.setCellValue(columnNames[i]);
			}
			int rowNumber = 1;
			for (SanPhamCon spc : dsSanPham) {
				Row row = sheet.createRow(rowNumber++);
				org.apache.poi.ss.usermodel.Cell idSanPhamCell = row.createCell(0);
				idSanPhamCell.setCellValue(spc.getIdSanPham());
				org.apache.poi.ss.usermodel.Cell tenSanPhamCell = row.createCell(1);
				tenSanPhamCell.setCellValue(spc.getTenSanPham());
				org.apache.poi.ss.usermodel.Cell loaiSanPhamCell = row.createCell(2);
				loaiSanPhamCell
						.setCellValue(spc.getIdLoaiSanPham() != null ? spc.getIdLoaiSanPham().getTenLoaiSanPham() : "");
				org.apache.poi.ss.usermodel.Cell nhaCungCapCell = row.createCell(3);
				nhaCungCapCell
						.setCellValue(spc.getIdNhaCungCap() != null ? spc.getIdNhaCungCap().getTenNhaCungCap() : "");
				org.apache.poi.ss.usermodel.Cell soLuongCell = row.createCell(4);
				soLuongCell.setCellValue(spc.getSoLuong());
				org.apache.poi.ss.usermodel.Cell soLuongBanCell = row.createCell(5);
				soLuongBanCell.setCellValue(spc.getSoLuongBan());
				org.apache.poi.ss.usermodel.Cell giaNhapCell = row.createCell(6);
				giaNhapCell.setCellValue(spc.getGiaNhap());
				org.apache.poi.ss.usermodel.Cell giaBanCell = row.createCell(7);
				giaBanCell.setCellValue(spc.giaBan());
				org.apache.poi.ss.usermodel.Cell doanhThuCell = row.createCell(8);
				doanhThuCell.setCellValue(spc.getDoanhThu());
				org.apache.poi.ss.usermodel.Cell loiNhuanCell = row.createCell(9);
				loiNhuanCell.setCellValue(spc.getLoiNhuan());
				org.apache.poi.ss.usermodel.Cell trangThaiCell = row.createCell(10);
				trangThaiCell.setCellValue(spc.getTrangThai().toString());				
			}
			for (int i = 0; i < columnNames.length; i++) {
				sheet.autoSizeColumn(i);
			}
			try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
				workbook.write(outputStream);
			}
			System.out.println("Dữ liệu SanPham đã được ghi vào tệp Excel thành công.");
			JOptionPane.showMessageDialog(this, "Xuất thống kê excel thành công");
			clearData();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void loadSanPhamTheoTuyChinh() {
		modelHoaDon.setRowCount(0);
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
			ArrayList<SanPhamCon> dsSanPham = daoSanPham.getTopSanPhamTheoNgay(
					dfNgaySQL.format(chooserDayStart.getDate()), dfNgaySQL.format(chooserDayEnd.getDate()));
			if (dsSanPham.size() == 0) {
				JOptionPane.showMessageDialog(this, "Khung thời gian này không sản phẩm nào");
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
				double tongDoanhThu = daoSanPham.getDoanhThuTheoNgay(dfNgaySQL.format(chooserDayStart.getDate()),
						dfNgaySQL.format(chooserDayEnd.getDate()));
				double tongLoiNhuan = daoSanPham.getLoiNhuanTheoNgay(dfNgaySQL.format(chooserDayStart.getDate()),
						dfNgaySQL.format(chooserDayEnd.getDate()));
				double soLuongDaBan = daoSanPham.getSoLuongDaBanTheoNgay(dfNgaySQL.format(chooserDayStart.getDate()),
						dfNgaySQL.format(chooserDayEnd.getDate()));
				double soLuongTon = daoSanPham.getSoLuongTonTheoNgay(dfNgaySQL.format(chooserDayStart.getDate()),
						dfNgaySQL.format(chooserDayEnd.getDate()));

				lblTongHoaDon.setText("TỔNG DOANH THU : " + currencyFormat.format(tongDoanhThu));
				lblTongLN.setText("TỔNG LỢI NHUẬN: " + currencyFormat.format(tongLoiNhuan));
				lblTongLoiNhuan.setText("TỔNG SỐ LƯỢNG ĐÃ BÁN : " + Math.round(soLuongDaBan));
				lblTongDoanhThu.setText("TỔNG SỐ LƯỢNG TỒN : " + Math.round(soLuongTon));
			}
		}
	}

	private void inThongKe() {

	}

	private void showBieuDoTheoNgayVaThang(Date ngayHienTai, Date ngayChon) {
		ArrayList<SanPhamCon> dsSanPham = new ArrayList<SanPhamCon>();
		DefaultCategoryDataset datasetSoLuongBan = createDatasetSoLuongBan("Số lượng bán chạy", ngayHienTai, ngayChon);
		DefaultCategoryDataset datasetSoLuongBanCham = createDatasetSoLuongBanCham("Số lượng bán chậm", ngayHienTai,
				ngayChon);
		DefaultCategoryDataset datasetDoanhThu = createDatasetDoanhThu("Doanh thu", ngayHienTai, ngayChon);
		DefaultCategoryDataset datasetLoiNhuan = createDatasetLoiNhuan("Lợi nhuận", ngayHienTai, ngayChon);
		DefaultPieDataset dataset = createDatasetNhaCungCap("Tỉ lệ nhà cung cấp", ngayHienTai, ngayChon);
		DefaultPieDataset datasetLoaiSanPham = createDatasetLoaiSanPham("Tỉ lệ LoaiSanPham", ngayHienTai, ngayChon);

		JFreeChart barChartSoLuongBan = createBarChart(datasetSoLuongBan, "Sản phẩm", "Số lượng sản phẩm bán chạy");
		JFreeChart barChartSoLuongBanCham = createBarChart(datasetSoLuongBanCham, "Sản phẩm",
				"Số lượng sản phẩm bán chậm");
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

		JPanel contentPanel = new JPanel(new GridLayout(3, 2));
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
		ArrayList<SanPhamCon> dsSanPham = daoSanPham.getTopSanPhamTheoNgay(dfNgaySQL.format(ngayChon),
				dfNgaySQL.format(ngayHienTai));

		for (SanPhamCon sp : dsSanPham) {
			if (sp.getIdNhaCungCap() != null && sp.getIdNhaCungCap().getIdNhaCungCap() != null) {
				dataset.setValue(sp.getIdNhaCungCap().getIdNhaCungCap(), sp.getSoLuongBan());
			}
		}

		return dataset;
	}

	private DefaultPieDataset createDatasetLoaiSanPham(String label, Date ngayHienTai, Date ngayChon) {
		DefaultPieDataset dataset = new DefaultPieDataset();
		ArrayList<SanPhamCon> dsSanPham = daoSanPham.getTopSanPhamTheoNgay(dfNgaySQL.format(ngayChon),
				dfNgaySQL.format(ngayHienTai));

		for (SanPhamCon sp : dsSanPham) {
			if (sp.getIdLoaiSanPham() != null && sp.getIdLoaiSanPham().getIdLoaiSanPham() != null) {
				dataset.setValue(sp.getIdLoaiSanPham().getIdLoaiSanPham(), sp.getSoLuongBan());
			}
		}

		return dataset;
	}

	private JFreeChart createPieChart(DefaultPieDataset dataset, String chartTitle) {
		JFreeChart pieChart = ChartFactory.createPieChart(chartTitle, dataset, true, true, false);

		PiePlot plot = (PiePlot) pieChart.getPlot();
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1}"));
		return pieChart;
	}

	private DefaultCategoryDataset createDatasetSoLuongBan(String label, Date ngayHienTai, Date ngayChon) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		ArrayList<SanPhamCon> dsSanPham = daoSanPham.getTopSanPhamTheoNgay(dfNgaySQL.format(ngayChon),
				dfNgaySQL.format(ngayHienTai));

		int count = 0;
		for (SanPhamCon sp : dsSanPham) {
			if (count <= 10) {
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
		ArrayList<SanPhamCon> dsSanPham = daoSanPham.getTopSanPhamBanChamTheoNgay(dfNgaySQL.format(ngayChon),
				dfNgaySQL.format(ngayHienTai));

		int count = 0;
		for (SanPhamCon sp : dsSanPham) {
			if (count <= 10) {
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
		ArrayList<SanPhamCon> dsSanPham = daoSanPham.getTopSanPhamTheoNgay(dfNgaySQL.format(ngayChon),
				dfNgaySQL.format(ngayHienTai));

		for (SanPhamCon sp : dsSanPham) {
			dataset.addValue(sp.getDoanhThu(), label, sp.getTenSanPham());
		}

		return dataset;
	}

	private DefaultCategoryDataset createDatasetLoiNhuan(String label, Date ngayHienTai, Date ngayChon) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		ArrayList<SanPhamCon> dsSanPham = daoSanPham.getTopSanPhamTheoNgay(dfNgaySQL.format(ngayChon),
				dfNgaySQL.format(ngayHienTai));

		for (SanPhamCon sp : dsSanPham) {
			dataset.addValue(sp.getLoiNhuan(), label, sp.getTenSanPham());
		}

		return dataset;
	}

	private JFreeChart createLineChart(String title, DefaultCategoryDataset dataset) {
		CategoryPlot plot = new CategoryPlot(dataset, new CategoryAxis("Sản Phẩm"), new NumberAxis("Số Lượng Bán"),
				new LineAndShapeRenderer());
		JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, true);
		return chart;
	}

	private JFreeChart createBarChart(DefaultCategoryDataset dataset, String categoryAxisLabel, String valueAxisLabel) {
		JFreeChart barChart = ChartFactory.createBarChart(valueAxisLabel, categoryAxisLabel, valueAxisLabel, dataset,
				PlotOrientation.VERTICAL, true, true, false);

		CategoryPlot plot = barChart.getCategoryPlot();
		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setSeriesPaint(0, Color.blue);

		CategoryAxis xAxis = (CategoryAxis) plot.getDomainAxis();
		xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);

		return barChart;
	}

	private JFreeChart createLineChart(DefaultCategoryDataset dataset, String categoryAxisLabel,
			String valueAxisLabel) {
		JFreeChart lineChart = ChartFactory.createLineChart(valueAxisLabel, categoryAxisLabel, valueAxisLabel, dataset,
				PlotOrientation.VERTICAL, true, true, false);

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
						currencyFormat.format(giaBan), currencyFormat.format(doanhThu), currencyFormat.format(loiNhuan),
						trangThai });
			}

			double tongDoanhThu = daoSanPham.getDoanhThuTheoNgay(dfNgaySQL.format(ngayChon),
					dfNgaySQL.format(ngayHienTai));
			double tongLoiNhuan = daoSanPham.getLoiNhuanTheoNgay(dfNgaySQL.format(ngayChon),
					dfNgaySQL.format(ngayHienTai));
			double soLuongDaBan = daoSanPham.getSoLuongDaBanTheoNgay(dfNgaySQL.format(ngayChon),
					dfNgaySQL.format(ngayHienTai));
			double soLuongTon = daoSanPham.getSoLuongTonTheoNgay(dfNgaySQL.format(ngayChon),
					dfNgaySQL.format(ngayHienTai));

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
					loadDataSanPhamTheoNgay(startDate, endDate);
				}
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
			} else if ("6 tháng gần nhất".equals(selectedOption) || "1 tháng gần nhất".equals(selectedOption) || "7 ngày gần nhất".equals(selectedOption)
					|| "3 tháng gần nhất".equals(selectedOption) || "1 năm gần nhất".equals(selectedOption)) {
				for (String option : new String[] { "7 ngày gần nhất","6 tháng gần nhất", "1 tháng gần nhất", "3 tháng gần nhất",
						"1 năm gần nhất" }) {
					Date ngayHienTai = new Date();
					Date ngayChon = tinhNgayChon(option, ngayHienTai);

					if (coDuLieu(ngayHienTai, ngayChon)) {
						XoaDuLieuTable();
						chooserDayStart.setEnabled(false);
						chooserDayEnd.setEnabled(false);
						loadDataSanPhamTheoNgay(ngayHienTai, ngayChon);
						cbLoc.setSelectedItem("Hiện tại");
						JOptionPane.showMessageDialog(this, "Không có dữ liệu", "Cảnh báo",
								JOptionPane.WARNING_MESSAGE);
						return;
					}
				}
			}
		}
	}

	private boolean coDuLieu(Date ngayHienTai, Date ngayChon) {
		ArrayList<SanPhamCon> dsSanPham = daoSanPham.getTopSanPhamTheoNgay(dfNgaySQL.format(ngayChon),
				dfNgaySQL.format(ngayHienTai));
		return !dsSanPham.isEmpty();
	}

	private Date tinhNgayChon(String option, Date ngayHienTai) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(ngayHienTai);

		switch (option) {
		case "7 ngày gần nhất":
			calendar.add(Calendar.DAY_OF_MONTH, -7);
			break;
		case "6 tháng gần nhất":
			calendar.add(Calendar.MONTH, -6);
			break;
		case "1 tháng gần nhất":
			calendar.add(Calendar.MONTH, -1);
			break;
		case "3 tháng gần nhất":
			calendar.add(Calendar.MONTH, -3);
			break;
		case "1 năm gần nhất":
			calendar.add(Calendar.YEAR, -1);
			break;

		default:
			break;
		}

		return calendar.getTime();
	}

}
