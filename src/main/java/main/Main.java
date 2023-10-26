package main;

import java.awt.EventQueue;

import views.DangNhapView;

public class Main {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				DangNhapView view = new DangNhapView();
				view.setVisible(true);
			}
		});
	}
}
