package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class QuanLyHoaDonView extends JPanel {
	private JTextField txtTimKiem;
	private JButton btnXemTatCa;
	private JTable tblHoaDon;
	private DefaultTableModel modelHoaDon;
	
	public QuanLyHoaDonView() {
		setLayout(new BorderLayout());
		JPanel pnTitle = new JPanel();
		JPanel pnTimKiem = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel pnXemTatCa = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel pnLeft = new JPanel();
		JPanel pnTimKiemXemTatCa = new JPanel(new BorderLayout());
		JPanel pnContent = new JPanel(new GridBagLayout());
		JPanel pnHeader = new JPanel();
		JPanel pnHeader2 = new JPanel();
		JPanel pn1 = new JPanel(new GridLayout(2, 1, 5, 5));
		JLabel lblTitle = new JLabel("QUẢN LÝ HOÁ ĐƠN");
		JLabel lblTimKiem = new JLabel("Tìm kiếm theo mã hoá đơn / SĐT");
		txtTimKiem = new JTextField(20);
		btnXemTatCa = new JButton("Xem tất cả hoá đơn");
		
		pnTitle.add(lblTitle);
		pnHeader.add(pnTitle);
		pnHeader2.add(pnHeader);
		pn1.add(lblTimKiem);
		pn1.add(txtTimKiem);
		pnTimKiem.add(pn1);
		pnXemTatCa.add(btnXemTatCa);
		pnTimKiemXemTatCa.add(pnTimKiem, BorderLayout.CENTER);
		pnTimKiemXemTatCa.add(pnXemTatCa, BorderLayout.SOUTH);
		pnLeft.add(pnTimKiemXemTatCa);
		
		
		tblHoaDon = new JTable();
		modelHoaDon = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không cho phép chỉnh sửa các ô trong bảng
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
//		scrollTblHD.setBorder(BorderFactory.createTitledBorder("Danh sách hoá đơn"));
		
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        pnContent.add(pnLeft, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.7;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        pnContent.add(scrollTblHD, gbc);
        
		add(pnHeader2, BorderLayout.NORTH);
		add(pnContent, BorderLayout.CENTER);
	}
}





