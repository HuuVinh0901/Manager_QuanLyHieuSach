package views;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class SetPassWordView extends JFrame {
	
	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			SetPassWordView frm = new SetPassWordView();
			frm.setVisible(true);
		});
	}
}
