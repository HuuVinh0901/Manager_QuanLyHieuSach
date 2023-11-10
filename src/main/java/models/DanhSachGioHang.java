package models;

import java.util.ArrayList;
import java.util.List;

public class DanhSachGioHang {
	private List<GioHang> ds;

	public DanhSachGioHang() {
		ds = new ArrayList<GioHang>();
	}
	
	public void themGioHang(GioHang gioHang ) {
		ds.add(gioHang);
	}
	
	
	public ArrayList<GioHang> getDanhSachGioHangTheoMaHD(String maHD) {
		ArrayList<GioHang> dsGioHang = new ArrayList<GioHang>();
		for (int i = 0; i < ds.size(); i++) {
			if (ds.get(i).getMaHoaDon().equals(maHD)) {
				dsGioHang.add(ds.get(i));
			}
		}
		return dsGioHang;
	}
	
	public void xoaGioHangTheoHoaDon(String maHD) {
		for (int i = 0; i < ds.size(); i++) {
			if (ds.get(i).getMaHoaDon().equals(maHD)) {
				ds.remove(i);
			}
		}
	}
 
}
