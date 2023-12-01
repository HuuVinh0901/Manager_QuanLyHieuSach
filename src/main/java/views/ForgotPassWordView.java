package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.HttpURLConnection;
public class ForgotPassWordView extends JDialog implements ActionListener {

	private JTextField phoneField;
	private JTextField codeField;
	private JTextField txtAPi;
	private JButton getCodeButton;
	private JTextField txtMess;
	public ForgotPassWordView() {
		super((JDialog) null, "Đặt lại mật khẩu", true);
		ImageIcon icon = new ImageIcon(getClass().getResource("/icons/logo.png"));
		setIconImage(icon.getImage());
		setLocationRelativeTo(null);

		setModal(true);
		setLayout(new GridLayout(3, 2));

		add(new JLabel("API:"));
		txtAPi = new JTextField();
		add(txtAPi);
		
		add(new JLabel("Số điện thoại:"));
		phoneField = new JTextField();
		add(phoneField);

		add(new JLabel("Mest:"));
		txtMess = new JTextField();
		add(txtMess);
		
		add(new JLabel("Mã code:"));
		codeField = new JTextField();
		add(codeField);

		getCodeButton = new JButton("Lấy mã");
		add(getCodeButton);

		ịnit();
		pack();
		getCodeButton.addActionListener(this);

	}

	private void ịnit() {

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(getCodeButton)) {
			try {
				   
                
				   String apiKey = "apikey=" + txtAPi.getText();
				   String message = "&message=" + txtMess.getText();
//				   String sender = "&sender=" + getCodeButton.getText();
				   String numbers = "&numbers=" + phoneField.getText();
				                        
				   
				   HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
				   String data = apiKey + numbers + message ;
				   conn.setDoOutput(true);
				   conn.setRequestMethod("POST");
				   conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
				   conn.getOutputStream().write(data.getBytes("UTF-8"));
				   final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				   final StringBuffer stringBuffer = new StringBuffer();
				   String line;
				   while ((line = rd.readLine()) != null) {
				    
				                                JOptionPane.showMessageDialog(null,"message"+line);
				   }
				   rd.close();
				   
				   
				  } catch (Exception e1) {
				                    JOptionPane.showMessageDialog(null,e1);
				   }
		}

	}
}
