package views;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DoiMatKhau extends JPanel{
	
	public DoiMatKhau() {
		setLayout(new BorderLayout());
		JPanel pnNouth=new JPanel();
		JPanel pnCenter=new JPanel(new BorderLayout());
		JLabel lbIcon=new JLabel();
		ImageIcon iconTieuDe = new ImageIcon(getClass().getResource("/icons/user.png"));
		lbIcon.setIcon(iconTieuDe);
		pnNouth.add(lbIcon);
		add(pnNouth,BorderLayout.NORTH);
	}
}
