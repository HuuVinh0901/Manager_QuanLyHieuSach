//import java.awt.GridBagConstraints;
//import java.awt.GridBagLayout;
//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.time.Year;
//import java.util.ArrayList;
//import java.util.Date;
//
//import javax.swing.JDialog;
//import javax.swing.JOptionPane;
//
//import org.jfree.chart.ChartFactory;
//import org.jfree.chart.ChartPanel;
//import org.jfree.chart.JFreeChart;
//import org.jfree.chart.axis.CategoryAxis;
//import org.jfree.chart.axis.NumberAxis;
//import org.jfree.chart.plot.CategoryPlot;
//import org.jfree.chart.plot.PlotOrientation;
//import org.jfree.chart.renderer.category.BarRenderer;
//import org.jfree.data.category.DefaultCategoryDataset;
//
//import models.HoaDon;
//import models.KhachHang;
//import models.NhanVien;

//package views;
//
//import java.awt.Color;
//import java.awt.GridBagConstraints;
//import java.awt.GridBagLayout;
//import java.time.LocalDate;
//import java.time.Year;
//
//import javax.swing.JDialog;
//
//import org.jfree.chart.ChartFactory;
//import org.jfree.chart.ChartPanel;
//import org.jfree.chart.JFreeChart;
//import org.jfree.chart.axis.CategoryAxis;
//import org.jfree.chart.axis.NumberAxis;
//import org.jfree.chart.plot.CategoryPlot;
//import org.jfree.chart.plot.PlotOrientation;
//import org.jfree.chart.renderer.category.BarRenderer;
//import org.jfree.data.category.DefaultCategoryDataset;
//
//public class hi {
//	private void showBieuDoLoiNhuanTheoNgayTrongThang() {
//		JDialog dialog = new JDialog();
//        dialog.setTitle("Thống kê lợi nhuận");
//        dialog.setLayout(new GridBagLayout());    
//		DefaultCategoryDataset dataset3 = new DefaultCategoryDataset();
//		DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
//		 for (int i = 1; i < 32; i++) {
//			 String z = String.valueOf(i);
//	        	dataset3.addValue(daoTKDT.getLoiNhuanTheoNgayThangNam(z,String.valueOf(LocalDate.now().getMonthValue()), String.valueOf(Year.now().getValue())), "Lợi nhuận", z);
//	        	dataset2.addValue(daoTKDT.getTongTienTheoNgayThangNam(z,String.valueOf(LocalDate.now().getMonthValue()), String.valueOf(Year.now().getValue())), "Doanh thu", z);
//	        }
//		JFreeChart chart3 = ChartFactory.createBarChart(
//			    "BIỂU ĐỒ TỔNG LỢI NHUẬN THÁNG " +LocalDate.now().getMonthValue()+"/"  +  Year.now().getValue(),
//				"Tháng/" + Year.now().getValue(),
//				"Lợi nhuận",
//				dataset3,
//				PlotOrientation.VERTICAL,
//				true,
//				true,
//				false
//		    );
//			CategoryPlot plot3 = (CategoryPlot) chart3.getPlot();
//			CategoryAxis xAxis3 = plot3.getDomainAxis();
//			xAxis3.setLowerMargin(0.02);
//			xAxis3.setUpperMargin(0.02);
//			NumberAxis yAxis3 = (NumberAxis) plot3.getRangeAxis();
//			yAxis3.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
//			BarRenderer renderer3 = (BarRenderer) plot3.getRenderer();
//			renderer3.setDrawBarOutline(false);
//			
//			ChartPanel chartPanel3 = new ChartPanel(chart3);
//			
//			JFreeChart chart2 = ChartFactory.createBarChart(
//				    "BIỂU ĐỒ TỔNG DOANH THU THÁNG " +LocalDate.now().getMonthValue()+"/" +  Year.now().getValue(),
//					"Tháng "+LocalDate.now().getMonthValue()+"/" + Year.now().getValue(),
//					"Doanh thu",
//					dataset2,
//					PlotOrientation.VERTICAL,
//					true,
//					true,
//					false
//			    );
//				CategoryPlot plot2 = (CategoryPlot) chart2.getPlot();
//				CategoryAxis xAxis2 = plot2.getDomainAxis();
//				xAxis2.setLowerMargin(0.02);
//				xAxis2.setUpperMargin(0.02);
//				NumberAxis yAxis2 = (NumberAxis) plot2.getRangeAxis();
//				yAxis2.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
//				BarRenderer renderer2 = (BarRenderer) plot2.getRenderer();
//				renderer2.setDrawBarOutline(false);
//				for(int i = 0; i < 32; i++) {
//					renderer2.setSeriesPaint(i, Color.BLUE);
//				}
//				ChartPanel chartPanel2 = new ChartPanel(chart2);
//			GridBagConstraints gbc = new GridBagConstraints();
//			gbc.gridx = 0;
//			gbc.gridy = 0;
//			gbc.weightx = 0.5;
//			gbc.weighty = 1.0;
//			gbc.fill = GridBagConstraints.BOTH;
//			dialog.add(chartPanel3, gbc);
//			
//		    gbc.gridx = 1;
//		    gbc.gridy = 0;
//		    gbc.weightx = 0.5;
//		    gbc.weighty = 1.0;
//		    gbc.fill = GridBagConstraints.BOTH;
//		    dialog.add(chartPanel2, gbc);
//			dialog.pack();
//			dialog.setSize(1200, 720);
//			dialog.setLocationRelativeTo(null);
//			dialog.setVisible(true);
//	}
//	private void showBieuDoHoaDonTheoThangTrongNam() {
//		JDialog dialog = new JDialog();
//        dialog.setTitle("Thống kê");
//        dialog.setLayout(new GridBagLayout());     
//        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//        DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
//        for (int i = 1; i < 13; i++) {
//        	String j = String.valueOf(i);
//        	dataset.addValue(daoTKDT.getSoHoaDonTheoThangNam(j, String.valueOf(Year.now().getValue())), "Số hóa đơn", j);
//        	
//        }
//        JFreeChart chart = ChartFactory.createBarChart(
//            "BIỂU ĐỒ TỔNG HÓA ĐƠN THEO THÁNG/" +  Year.now().getValue(),
//            "Tháng/" + Year.now().getValue(),
//            "Số hóa đơn",
//            dataset,
//            PlotOrientation.VERTICAL,
//            true,
//            true,
//            false
//        );
//        CategoryPlot plot = (CategoryPlot) chart.getPlot();
//        CategoryAxis xAxis = plot.getDomainAxis();
//        xAxis.setLowerMargin(0.05);
//        xAxis.setUpperMargin(0.05);
//        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
//        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
//        BarRenderer renderer = (BarRenderer) plot.getRenderer();
//        renderer.setDrawBarOutline(false);
//        ChartPanel chartPanel = new ChartPanel(chart);
//        
//        GridBagConstraints gbc = new GridBagConstraints();
//		gbc.gridx = 0;
//		gbc.gridy = 0;
//		gbc.weightx = 0.4;
//		gbc.weighty = 1.0;
//		gbc.fill = GridBagConstraints.BOTH;
//		dialog.add(chartPanel, gbc);
//
//        
//        
//        dialog.pack();
//		dialog.setSize(1200, 720);
//		dialog.setLocationRelativeTo(null);
//		dialog.setVisible(true);
//	}
//}
//public void loadDataHoaDonTheoNgay(Date ngayHienTai,Date ngayChon) {
//		
//		modelHoaDon.setRowCount(0);
//		ArrayList<HoaDon> dsHoaDon = daoTKDT.getHoaDonTheoNgay(dfNgaySQL.format(ngayChon), dfNgaySQL.format(ngayHienTai));
//		if (dsHoaDon.size() == 0) {
//			JOptionPane.showMessageDialog(this, "Khung thời gian này không bán hoá đơn nào");
//			XoaDuLieuTable();
//			lblTongHoaDon.setText("SỐ LƯỢNG HOÁ ĐƠN BÁN RA : " + modelHoaDon.getRowCount());
//			lblTongDoanhThu.setText("TỔNG DOANH THU : " + currencyFormat.format(0));
//			lblTongLoiNhuan.setText("TỔNG LỢI NHUẬN : " + currencyFormat.format(0));
//		} else {
//			for (HoaDon hd : dsHoaDon) {
//				String maKH = hd.getKhachHang().getIdKhachHang();
//				String maNV = hd.getNhanVien().getId();
//				KhachHang kh=daoKH.getKhachHang(maKH);
//				NhanVien nv=daoNV.getNhanVien(maNV);
//				String maHD = hd.getIdDonHang();
//				String ngayLap = new SimpleDateFormat("dd/MM/yyyy").format(hd.getNgayLap());
//				String tongTien = currencyFormat.format(hd.getTongTien());
//				String tongLoiNhuan= currencyFormat.format(hd.getTongLoiNhuan());
//				modelHoaDon.addRow(new String[] {maHD, ngayLap,maKH, kh.getTenKhachHang(), nv.getTen(), tongTien,tongLoiNhuan});
//			}
//			lblTongHoaDon.setText("SỐ LƯỢNG HOÁ ĐƠN BÁN RA : " + modelHoaDon.getRowCount());
//			lblTongDoanhThu.setText("TỔNG DOANH THU : " + currencyFormat.format(daoTKDT.getTongTienTheoNgay(dfNgaySQL.format(ngayChon), dfNgaySQL.format(ngayHienTai))));
//			lblTongLoiNhuan.setText("TỔNG LỢI NHUẬN : " + currencyFormat.format(daoTKDT.getLoiNhuanTheoNgay(dfNgaySQL.format(ngayChon), dfNgaySQL.format(ngayHienTai))));
//		}
//	}
//	public void loadDataHoaDonTheoTuyChinh() {
//		
//		modelHoaDon.setRowCount(0);
//		if (chooserDayStart.getDate() == null) {
//			JOptionPane.showMessageDialog(this, "Chưa chọn ngày bắt đầu");
//			chooserDayStart.requestFocus();
//		} else if (chooserDayEnd.getDate() == null) {
//			JOptionPane.showMessageDialog(this, "Chưa chọn ngày kết thúc");
//			chooserDayEnd.requestFocus();
//		}
//			else if(chooserDayEnd.getDate().before(chooserDayStart.getDate())) {
//				JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải trước ngày kết thúc");
//				chooserDayEnd.requestFocus();
//			}
//		else {
//			ArrayList<HoaDon> dsHoaDon = daoTKDT.getHoaDonTheoNgay(dfNgaySQL.format(chooserDayStart.getDate()), dfNgaySQL.format(chooserDayEnd.getDate()));
//			if (dsHoaDon.size() == 0) {
//				JOptionPane.showMessageDialog(this, "Khung thời gian này không bán hoá đơn nào");
//				
//				lblTongHoaDon.setText("SỐ LƯỢNG HOÁ ĐƠN BÁN RA : " + modelHoaDon.getRowCount());
//				lblTongDoanhThu.setText("TỔNG DOANH THU : " + currencyFormat.format(0));
//				lblTongLoiNhuan.setText("TỔNG LỢI NHUẬN : " + currencyFormat.format(0));
//			} else {
//				for (HoaDon hd : dsHoaDon) {
//					
//					String maKH = hd.getKhachHang().getIdKhachHang();
//					String maNV = hd.getNhanVien().getId();
//					KhachHang kh=daoKH.getKhachHang(maKH);
//					NhanVien nv=daoNV.getNhanVien(maNV);
//					String maHD = hd.getIdDonHang();
//					String ngayLap = new SimpleDateFormat("dd/MM/yyyy").format(hd.getNgayLap());
//					String tongTien = currencyFormat.format(hd.getTongTien());
//					String tongLoiNhuan= currencyFormat.format(hd.getTongLoiNhuan());
//					modelHoaDon.addRow(new String[] {maHD, ngayLap,maKH, kh.getTenKhachHang(), nv.getTen(), tongTien,tongLoiNhuan});
//				}
//				lblTongHoaDon.setText("SỐ LƯỢNG HOÁ ĐƠN BÁN RA : " + modelHoaDon.getRowCount());
//				lblTongDoanhThu.setText("TỔNG DOANH THU : " + currencyFormat.format(daoTKDT.getTongTienTheoNgay(dfNgaySQL.format(chooserDayStart.getDate()), dfNgaySQL.format(chooserDayEnd.getDate()))));
//				lblTongLoiNhuan.setText("TỔNG LỢI NHUẬN : " + currencyFormat.format(daoTKDT.getLoiNhuanTheoNgay(dfNgaySQL.format(chooserDayStart.getDate()), dfNgaySQL.format(chooserDayEnd.getDate()))));
//
//			}
//		}
//			
//	}
//private void showBieuDoHoaDonTheoNgayTrongThang() {
//	JDialog dialog = new JDialog();
//    dialog.setTitle("Thống kê hóa đơn");
//    dialog.setLayout(new GridBagLayout());     
//    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//    DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
//    
//    	for(int j=1;j<32;j++) {
//    		String z = String.valueOf(j);
//    		dataset.addValue(daoTKDT.getSoHoaDonTheoNgayThangNam(z,String.valueOf(LocalDate.now().getMonthValue()), String.valueOf(Year.now().getValue())), "Số hóa đơn", z);
//        	
//    	}
////    	
//    JFreeChart chart = ChartFactory.createBarChart(
//        "Biểu đồ thống kê số hóa đơn đã bán theo tháng " +LocalDate.now().getMonthValue()+"/"+  Year.now().getValue(),
//        "Tháng " +LocalDate.now().getMonthValue()+"/"+ Year.now().getValue(),
//        "Số hóa đơn",
//        dataset,
//        PlotOrientation.VERTICAL,
//        true,
//        true,
//        false
//    );
//
//    CategoryPlot plot = (CategoryPlot) chart.getPlot();
//    CategoryAxis xAxis = plot.getDomainAxis();
//    xAxis.setLowerMargin(0.02);
//    xAxis.setUpperMargin(0.02);
//    NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
//    yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
//    BarRenderer renderer = (BarRenderer) plot.getRenderer();
//    renderer.setDrawBarOutline(false);
//    ChartPanel chartPanel = new ChartPanel(chart);
//	
//    
//    GridBagConstraints gbc = new GridBagConstraints();
//	gbc.gridx = 0;
//	gbc.gridy = 0;
//	gbc.weightx = 0.5;
//	gbc.weighty = 1.0;
//	gbc.fill = GridBagConstraints.BOTH;
//	dialog.add(chartPanel, gbc);
//
//
//    
//	dialog.pack();
//	dialog.setSize(1200, 720);
//	dialog.setLocationRelativeTo(null);
//	dialog.setVisible(true);
//}
