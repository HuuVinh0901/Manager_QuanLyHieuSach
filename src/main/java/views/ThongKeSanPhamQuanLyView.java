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
	private DAOQuanLySanPham daoSanPham;
	public ThongKeSanPhamQuanLyView() {
		setLayout(new BorderLayout());
		daoSanPham = new DAOQuanLySanPham();
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
		btnThongKe = new JButton("Thống kê hóa đơn");
		btnInThongKe = new JButton("In thống kê");
		btnLamMoi = new JButton("Làm mới");
		btnThongKeLN = new JButton("Thống kê doanh thu & lợi nhuận");
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
		add(pn2, BorderLayout.CENTER);
		add(scrollTblHD, BorderLayout.SOUTH);

		btnTimKiem.addActionListener(this);
		btnThongKe.addActionListener(this);
		btnThongKeLN.addActionListener(this);
		btnInThongKe.addActionListener(this);
		btnLamMoi.addActionListener(this);
		cbLoc.addItemListener(this);
		Date ngayHienTai = new Date();
		loadDataSanPhamTheoNgay(ngayHienTai,ngayHienTai);
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
			if ("6 tháng gần nhất".equals(selectedOption) || "3 tháng gần nhất".equals(selectedOption)
					|| "1 năm gần nhất".equals(selectedOption)) {
				showBieuDoHoaDonTheoThangTrongNam();
			} else {
				showBieuDoHoaDonTheoNgayTrongThang();
			}
		} else if (o.equals(btnInThongKe)) {
			inThongKe();
		} else if (o.equals(btnThongKeLN)) {
			String selectedOption = (String) cbLoc.getSelectedItem();
			if ("6 tháng gần nhất".equals(selectedOption) || "3 tháng gần nhất".equals(selectedOption)
					|| "1 năm gần nhất".equals(selectedOption)) {
				showBieuDoLoiNhuanTheoThangTrongNam();
			} else {
				showBieuDoLoiNhuanTheoNgayTrongThang();
			}
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
	private void showBieuDoHoaDonTheoThangTrongNam() {

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
	    ArrayList<SanPhamCon> dsSanPham = daoSanPham.getTopSanPhamTheoNgay(dfNgaySQL.format(ngayChon), dfNgaySQL.format(ngayHienTai));

	    if (dsSanPham.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Khung thời gian này không có sản phẩm nào");
	        XoaDuLieuTable();
	        lblTongHoaDon.setText("SỐ LƯỢNG HOÁ ĐƠN BÁN RA : " + modelHoaDon.getRowCount());
	        lblTongDoanhThu.setText("TỔNG DOANH THU : " + currencyFormat.format(0));
	        lblTongLoiNhuan.setText("TỔNG LỢI NHUẬN : " + currencyFormat.format(0));
	    } else {
	        for (SanPhamCon sp : dsSanPham) {
	        	ChiTietHoaDon ct = new ChiTietHoaDon();
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

	            modelHoaDon.addRow(new String[]{idSanPham, tenSanPham, loaiSanPham, nhaCungCap, String.valueOf(soLuong), String.valueOf(soLuongBan),String.valueOf(giaNhap),String.valueOf(giaBan), String.valueOf(doanhThu), String.valueOf(loiNhuan),trangThai});
	        }

//	        double tongDoanhThu = dsSanPham.stream().mapToDouble(sp -> sp.getSoLuongBan() * sp.getGiaBan()).sum();
//	        double tongLoiNhuan = dsSanPham.stream().mapToDouble(sp -> (sp.getSoLuongBan() * sp.getGiaBan()) - (sp.getSoLuongBan() * sp.getGiaNhap())).sum();

//	        lblTongHoaDon.setText("SỐ LƯỢNG HOÁ ĐƠN BÁN RA : " + modelHoaDon.getRowCount());
//	        lblTongDoanhThu.setText("TỔNG DOANH THU : " + currencyFormat.format(tongDoanhThu));
//	        lblTongLoiNhuan.setText("TỔNG LỢI NHUẬN : " + currencyFormat.format(tongLoiNhuan));
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
