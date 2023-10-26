package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.mysql.cj.x.protobuf.MysqlxExpr.DocumentPathItem;

public class PhanQuyenView extends JFrame implements ActionListener{
	private JButton btnLogOut, btnQLBanHang, btnQLHeThong;
	public PhanQuyenView() {
		 setTitle("Điều hướng quản lý");
	        setSize(600, 375);
	        setResizable(false);
	        setLocationRelativeTo(null);
	        setDefaultCloseOperation(EXIT_ON_CLOSE);
	        init();
	}
	private void init() {
		JPanel pnMain = new JPanel();
        pnMain.setBackground(Color.WHITE);
        getContentPane().add(pnMain, BorderLayout.CENTER);
        pnMain.setLayout(new BorderLayout(0, 0));

        JPanel pnTitle = new JPanel();
        pnMain.add(pnTitle, BorderLayout.NORTH);

        JLabel lbTitle = new JLabel("CHÀO MỪNG BẠN ĐẾN VỚI PHẦN MỀM QUẢN LÝ HIỆU SÁCH TƯ NHÂN ");
        pnTitle.add(lbTitle);

        JPanel pnLeft = new JPanel();
        pnLeft.setBackground(Color.WHITE);
        pnLeft.setPreferredSize(new Dimension(295, 110));
        pnMain.add(pnLeft, BorderLayout.WEST);

        btnQLHeThong = new JButton("QUẢN TRỊ HỆ THỐNG");
        btnQLHeThong.setPreferredSize(new Dimension(280, 250));
        pnLeft.add(btnQLHeThong);

        JPanel pnRight = new JPanel();
        pnRight.setBackground(Color.WHITE);
        pnRight.setPreferredSize(new Dimension(295, 110));
        pnMain.add(pnRight, BorderLayout.EAST);

        btnQLBanHang = new JButton("QUẢN LÝ BÁN HÀNG");
        btnQLBanHang.setPreferredSize(new Dimension(280, 250));
    
        pnRight.add(btnQLBanHang);

        JPanel pnBottom = new JPanel();
        pnBottom.setBackground(Color.WHITE);
        pnBottom.setLayout(new BoxLayout(pnBottom, BoxLayout.X_AXIS));
        pnBottom.setPreferredSize(new Dimension(280, 40));
        pnMain.add(pnBottom, BorderLayout.SOUTH);

        Component horizontalGlue = Box.createHorizontalGlue();
        pnBottom.add(horizontalGlue);

        btnLogOut = new JButton("Đăng xuất");
        pnBottom.add(btnLogOut);

        Component horizontalStrut = Box.createHorizontalStrut(9);
        pnBottom.add(horizontalStrut);

        btnLogOut.addActionListener(this);
        btnQLBanHang.addActionListener(this);
        btnQLHeThong.addActionListener(this);
        
       
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnLogOut)) {
			this.dispose();
			DangNhapView view = new DangNhapView();
			view.setVisible(true);
		}else if(o.equals(btnQLBanHang)) {
			this.dispose();
			TrangChuQuanLyBanHangView view = new TrangChuQuanLyBanHangView();
			view.setVisible(true);
		}else if(o.equals(btnQLHeThong)) {
			this.dispose();
			TrangChuQuanTriView view = new TrangChuQuanTriView();
			view.setVisible(true);
		}
	}

}
