package views;

import java.awt.BorderLayout;
import java.awt.Panel;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class QuanLyBanHangView extends Panel{
	public QuanLyBanHangView() {
		setLayout(new BorderLayout());
        String[] columnNames = {"Column 1", "Column 2","column"};
        Object[][] data = {{"test thu", "test thu 2","test hoai hong ra"}, {"Data 3", "Data 4","HHDSHD"},{"Data 3", "Data 4","HHDSHD"}};
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        JButton addButton = new JButton("Thêm");
        JButton editButton = new JButton("Sửa");
        JButton deleteButton = new JButton("Xóa");
        JButton saveButton = new JButton("Lưu");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(saveButton);
        add(buttonPanel, BorderLayout.SOUTH);
	}
	
}
