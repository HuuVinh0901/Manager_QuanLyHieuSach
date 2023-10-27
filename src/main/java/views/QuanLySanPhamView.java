package views;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class QuanLySanPhamView extends JPanel {
    private JTabbedPane tabbedPane;
    private JTable sanPhamTable;
    private JTable sachTable;
    private JPanel pnTop;
    private JPanel pnTop_Left;
    private JPanel pnTop_Right;
    private JLabel lblTimKiem;
    private JTextField txtTimKiem;
    private JButton btnXuatEX;
    private JButton btnTimKiem;
    private JComboBox<String> cbBoLoc;
    private JPanel pnCenter;
    private JButton btnThemSP;
    private JButton btnNhapSP;
    private JButton btnCapNhatSP;
    private JButton btnXoaSP;
    private JButton btnLSXoa;
    private JPanel pnTable;
    public QuanLySanPhamView() {
    	setLayout(new BorderLayout());
        Container mainContainer = new Container();
        mainContainer.setLayout(new BorderLayout(20, 20));
        tabbedPane = new JTabbedPane();

        // Tab Sản phẩm
        JPanel sanPhamPanel = new JPanel();
        sanPhamPanel.setLayout(new BorderLayout());

        pnTop = new JPanel(new BorderLayout());
        JPanel pnTop_Left = new JPanel();
        JPanel pnTop_Right = new JPanel();
        pnTop_Left.add(new JLabel("Tìm kiếm"));
        JTextField txtTimKiem = new JTextField(20);
        JButton btnTimKiem = new JButton("Tìm kiếm");
        pnTop_Left.add(txtTimKiem);
        pnTop_Left.add(btnTimKiem);
        String[] items = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};
        JComboBox<String> cbBoLoc = new JComboBox<>(items);
        JButton btnXuatEX = new JButton("Xuất Excel");
        pnTop_Right.add(cbBoLoc);
        pnTop_Right.add(Box.createRigidArea(new Dimension(50, 0)));
        pnTop_Right.add(btnXuatEX);
        pnTop_Left.setBorder(new EmptyBorder(10, 20, 10, 10));
        pnTop_Right.setBorder(new EmptyBorder(10, 0, 10, 50));
        pnTop.add(pnTop_Left, BorderLayout.WEST);
        pnTop.add(pnTop_Right, BorderLayout.EAST);
        sanPhamPanel.add(pnTop, BorderLayout.NORTH);

        Border lineBorder = BorderFactory.createLineBorder(Color.decode("#d3d3d3"), 2);
        Border emptyBorder = new EmptyBorder(20, 20, 40, 20);
        Border compoundBorder = BorderFactory.createCompoundBorder(lineBorder, emptyBorder);
        pnCenter = new JPanel(new BorderLayout());
        pnCenter.setBorder(compoundBorder);
        sanPhamPanel.setBorder(compoundBorder);
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER,110,10));
        btnThemSP = new JButton("THÊM SẢN PHẨM");
        btnNhapSP = new JButton("NHẬP SẢN PHẨM");
        btnCapNhatSP = new JButton("CẬP NHẬT SẢN PHẨM");
        btnXoaSP = new JButton("XÓA SẢN PHẨM");
        btnLSXoa = new JButton("LỊCH SỬ XÓA");
        buttonsPanel.add(btnThemSP);
        buttonsPanel.add(btnNhapSP);
        buttonsPanel.add(btnCapNhatSP);
        buttonsPanel.add(btnXoaSP);
        buttonsPanel.add(btnLSXoa);
        pnCenter.add(buttonsPanel, BorderLayout.NORTH);

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());

        String[] sachColumnNamess = {"Mã sách", "Tên sách", "Tác giả"};
        Object[][] sachDatas = {{"S001", "Sách 1", "Tác giả 1"}, {"S002", "Sách 2", "Tác giả 2"}};
        JTable table = new JTable(sachDatas, sachColumnNamess);
        JScrollPane scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane);
        tablePanel.setBorder(new EmptyBorder(5, 20, 5, 20));
        pnCenter.add(tablePanel, BorderLayout.CENTER);

        sanPhamPanel.add(pnCenter, BorderLayout.CENTER);
        
        
        tabbedPane.addTab("Sản phẩm", sanPhamPanel);
        
        
        
        
        
        // Tab Sách
        JPanel sachPanel = new JPanel();
        sachPanel.setLayout(new BorderLayout());
        String[] sachColumnNames = {"Mã sách", "Tên sách", "Tác giả"};
        Object[][] sachData = {{"S001", "Sách 1", "Tác giả 1"}, {"S002", "Sách 2", "Tác giả 2"}};
        sachTable = new JTable(sachData, sachColumnNames);
        JScrollPane sachScrollPane = new JScrollPane(sachTable);
        sachPanel.add(sachScrollPane, BorderLayout.CENTER);
        tabbedPane.addTab("Sách", sachPanel);

        mainContainer.add(tabbedPane);
        add(mainContainer);
    }
}
