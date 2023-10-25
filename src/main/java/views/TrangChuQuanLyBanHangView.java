package views;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import views.QuanLyBanHangView;

public class TrangChuQuanLyBanHangView extends JFrame implements ActionListener, MouseListener {
	private JPanel menuPanel;
    private JPanel contentPanel;
    private CardLayout cardLayout;
    private  JButton menuButton7;
	public TrangChuQuanLyBanHangView() {
		 setTitle("Quản Lý Bán Hàng");
	        setSize(1200, 750);
	        setLocationRelativeTo(null);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        menuPanel = new JPanel();
	        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
	        menuPanel.setPreferredSize(new Dimension(100, 500));
	        TitledBorder titledBorder = BorderFactory.createTitledBorder(null, "aaaa", TitledBorder.LEADING, TitledBorder.TOP, null, null);
	        menuPanel.setBorder(titledBorder);
	        // Tạo các nút menu
	        JButton menuButton1 = createMenuButton("Quản lý bán hàng");
	        JButton menuButton2 = createMenuButton("Quản lý khách hàng");
	        JButton menuButton3 = createMenuButton("Quản lý hóa đơn");
	        JButton menuButton4 = createMenuButton("Thống kê báo cáo");
	        JButton menuButton5 = createMenuButton("Cài đặt");
	        JButton menuButton6 = createMenuButton("Thông tin");
	        menuButton7 = createMenuButton("Đăng xuất"); 

	        menuPanel.add(Box.createVerticalStrut(10));
	        menuPanel.add(menuButton1);
	        menuPanel.add(Box.createVerticalStrut(10));
	        menuPanel.setPreferredSize(new Dimension(10,20));
	        menuButton1.setMaximumSize(new Dimension(Integer.MAX_VALUE, menuButton1.getPreferredSize().height));
	        menuPanel.add(menuButton2);
	        menuPanel.add(Box.createVerticalStrut(10));
	        menuButton2.setMaximumSize(new Dimension(Integer.MAX_VALUE, menuButton2.getPreferredSize().height));
	        menuPanel.add(menuButton3);
	        menuPanel.add(Box.createVerticalStrut(10));
	        menuButton3.setMaximumSize(new Dimension(Integer.MAX_VALUE, menuButton3.getPreferredSize().height));
	        menuPanel.add(menuButton4);
	        menuPanel.add(Box.createVerticalStrut(10));
	        menuButton4.setMaximumSize(new Dimension(Integer.MAX_VALUE, menuButton4.getPreferredSize().height));
	        menuPanel.add(menuButton5);
	        menuPanel.add(Box.createVerticalStrut(10));
	        menuButton5.setMaximumSize(new Dimension(Integer.MAX_VALUE, menuButton5.getPreferredSize().height));
	        menuPanel.add(menuButton6);
	        menuPanel.add(Box.createVerticalStrut(10));
	        menuButton6.setMaximumSize(new Dimension(Integer.MAX_VALUE, menuButton6.getPreferredSize().height));
	        menuPanel.add(menuButton7);
	        menuPanel.add(Box.createVerticalStrut(10));
	        menuButton7.setMaximumSize(new Dimension(Integer.MAX_VALUE, menuButton7.getPreferredSize().height));
	        
	        // Panel chứa nội dung
	        contentPanel = new JPanel();
	        contentPanel.setLayout(new CardLayout());

	        
	        cardLayout = new CardLayout();
	        contentPanel.setLayout(cardLayout);


	        contentPanel.add(new QuanLyBanHangView(), "Quản lý bán hàng");
	      
	        menuButton1.addActionListener(e -> {
	            cardLayout.show(contentPanel, "Quản lý bán hàng");
	        });

	        menuButton2.addActionListener(e -> {
	            cardLayout.show(contentPanel, "Quản lý khách hàng");
	        });
	        
	        menuButton3.addActionListener(e -> {
	            cardLayout.show(contentPanel, "Quản lý hóa đơn");
	        });
	        
	        menuButton4.addActionListener(e -> {
	            cardLayout.show(contentPanel, "Thống kê báo cáo");
	        });
	        menuButton5.addActionListener(e -> {
	            cardLayout.show(contentPanel, "Cài đặt");
	        });
	        menuButton6.addActionListener(e -> {
	            cardLayout.show(contentPanel, "Thông tin");
	        });
	        menuButton7.addActionListener(e -> {
	            cardLayout.show(contentPanel, "Đăng xuất");
	        });
	        // Sử dụng GridBagLayout để chia màn hình thành 1/5 và 4/5
	        JPanel mainPanel = new JPanel(new GridBagLayout());
	        GridBagConstraints gbc = new GridBagConstraints();
	        gbc.gridx = 0;
	        gbc.gridy = 0;
	        gbc.weightx = 0.2; // 1/5 
	        gbc.fill = GridBagConstraints.BOTH;
	        mainPanel.add(menuPanel, gbc);

	        gbc.gridx = 1;
	        gbc.weightx = 0.8; // 4/5 
	        mainPanel.add(contentPanel, gbc);

	        getContentPane().add(mainPanel);
	        
	        menuButton7.addActionListener(this);
	}
	 private JButton createMenuButton(String text) {
	        JButton button = new JButton(text);
	        button.setPreferredSize(new Dimension(100, 100));
	        button.setAlignmentX(Component.CENTER_ALIGNMENT);
	        return button;
	    }
	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(menuButton7)) {
			this.dispose();
			PhanQuyenView view = new PhanQuyenView();
			view.setVisible(true);
		}
		
	}

}
