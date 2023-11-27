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
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.apache.logging.log4j.core.tools.picocli.CommandLine.Help.TextTable.Cell;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.toedter.calendar.JDateChooser;

public class ThongKeDoanhThuNhanVienView extends JPanel implements ActionListener, ItemListener {
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
	private JLabel lblLoc;
	private JComboBox<String> cbLoc;
	private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

	public ThongKeDoanhThuNhanVienView() {
		setLayout(new BorderLayout());
		currencyFormat.setCurrency(Currency.getInstance("VND"));
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
		lblLoc = new JLabel("Lọc từ");
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
		btnThongKe = new JButton("Thống kê");
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
		lblTongHoaDon.setFont(new Font("Tahoma", Font.BOLD, 20));
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
			} else {
				loadSampleData();
			}
		} else if (o.equals(btnThongKe)) {
			bieuDo();
		} else if (o.equals(btnInThongKe)) {
			inThongKe();
		}

	}

	private void inThongKe() {
//		if ("Tùy chỉnh".equals(cbLoc.getSelectedItem())) {
//			Date currentDate = new Date();
//			Date startDate = chooserDayStart.getDate();
//			Date endDate = chooserDayEnd.getDate();
//			if (startDate == null || startDate.after(currentDate)) {
//				JOptionPane.showConfirmDialog(this, "Ngày bắt đầu không thể nhỏ hơn ngày hiện tại", "Cảnh báo",
//						JOptionPane.WARNING_MESSAGE);
//			} else if (endDate == null || endDate.after(currentDate)) {
//				JOptionPane.showConfirmDialog(this, "Ngày kết thúc không thể nhỏ hơn ngày hiện tại", "Cảnh báo",
//						JOptionPane.WARNING_MESSAGE);
//			} else {
//				try {
//					
//
//				} catch (Exception e) {
//					JOptionPane.showConfirmDialog(this, "Error Message: " + e.getMessage(), "Lỗi",
//							JOptionPane.ERROR_MESSAGE);
//				}
		Workbook workbook = new XSSFWorkbook();
		org.apache.poi.ss.usermodel.Sheet sheet = workbook.createSheet("Doanh thu");

		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("Mã Hóa Đơn");
		header.createCell(1).setCellValue("Ngày");
		header.createCell(2).setCellValue("Mã Khách Hàng");
		header.createCell(3).setCellValue("Mã Nhân Viên");
		header.createCell(4).setCellValue("Doanh Thu");
		header.createCell(5).setCellValue("Tổng Tiền");

		Object[][] data = { { "HD001", "01/01/2023", "KH001", "NV001", 500000, 1000000 },
				{ "HD002", "02/01/2023", "KH002", "NV002", 700000, 1200000 }, };

		for (int i = 0; i < data.length; i++) {
			Row row = sheet.createRow(i + 1);
			for (int j = 0; j < data[i].length; j++) {
				row.createCell(j).setCellValue(data[i][j].toString());
			}
		}

		JFileChooser fileChooser = new JFileChooser();
		File defaultDirectory = new File(System.getProperty("user.dir") + "/src/main/resources/import");
		fileChooser.setCurrentDirectory(defaultDirectory);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files", "xls", "xlsx");
		fileChooser.setFileFilter(filter);
		if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			try (FileOutputStream outputStream = new FileOutputStream(file)) {
				workbook.write(outputStream);
				JOptionPane.showMessageDialog(null, "Xuất file Excel thành công!");
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Lỗi khi xuất file: " + e.getMessage(), "Lỗi",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
//		}
//	}

	private void bieuDo() {

	}

	private void clearData() {
		chooserDayStart.setDate(new Date());
		chooserDayEnd.setDate(new Date());
		cbLoc.setSelectedItem("Hiện tại");

	}

	private void loadSampleData() {
		clearData();
		Object[] rowData1 = { "HD001", "01/01/2023", "KH001", "NV001", 500000, 1000000 };
		Object[] rowData2 = { "HD002", "02/01/2023", "KH002", "NV002", 700000, 1200000 };
		Object[] rowData3 = { "HD003", "03/01/2023", "KH003", "NV003", 600000, 1100000 };
		Object[] rowData4 = { "HD004", "04/01/2023", "KH004", "NV004", 550000, 1050000 };
		Object[] rowData5 = { "HD005", "05/01/2023", "KH005", "NV005", 800000, 1300000 };
		modelHoaDon.addRow(rowData1);
		modelHoaDon.addRow(rowData2);
		modelHoaDon.addRow(rowData3);
		modelHoaDon.addRow(rowData4);
		modelHoaDon.addRow(rowData5);
		updateTotalInfo();
	}

	private void updateTotalInfo() {
		lblTongHoaDon.setText("SỐ LƯỢNG HOÁ ĐƠN BÁN RA : " + modelHoaDon.getRowCount());

		int totalRevenue = 0;
		for (int i = 0; i < modelHoaDon.getRowCount(); i++) {
			totalRevenue += (int) modelHoaDon.getValueAt(i, 5);
		}
		lblTongDoanhThu.setText("TỔNG DOANH THU : " + currencyFormat.format(totalRevenue));
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == cbLoc && e.getStateChange() == ItemEvent.SELECTED) {
			String selectedOption = (String) cbLoc.getSelectedItem();
			if ("Tùy chỉnh".equals(selectedOption)) {
				chooserDayStart.setEnabled(true);
				chooserDayEnd.setEnabled(true);
			} else {
				chooserDayStart.setEnabled(false);
				chooserDayEnd.setEnabled(false);
			}
		}
	}
}
