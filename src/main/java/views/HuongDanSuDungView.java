package views;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import javax.swing.*;
import java.awt.*;

public class HuongDanSuDungView extends JPanel{
	public HuongDanSuDungView() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        addSection("1. Đăng nhập và Phân quyền",
                "1.1 Đăng nhập\n" +
                        "Khi mở ứng dụng, nhập thông tin tài khoản và mật khẩu để đăng nhập vào hệ thống.\n\n" +
                        "1.2 Phân quyền\n" +
                        "- Quản lý: Có quyền quản lý sản phẩm, nhân viên, chương trình khuyến mãi, hóa đơn và thực hiện thống kê.\n" +
                        "- Nhân viên: Có quyền bán hàng, quản lý khách hàng và xem thống kê doanh thu."
        );

        addSection("2. Quản lý Sản phẩm",
                "2.1 Sản phẩm thông thường\n" +
                        "- Thêm sản phẩm: Quản lý thông tin sản phẩm thông thường, bao gồm loại sản phẩm và nhà cung cấp.\n" +
                        "- Sửa thông tin sản phẩm: Cập nhật thông tin sản phẩm đã tồn tại.\n" +
                        "- Xóa sản phẩm: Loại bỏ sản phẩm khỏi danh sách.\n\n" +
                        "2.2 Sách\n" +
                        "- Thêm sách: Quản lý thông tin sách, bao gồm loại sách, tác giả và nhà cung cấp.\n" +
                        "- Sửa thông tin sách: Cập nhật thông tin sách đã tồn tại.\n" +
                        "- Xóa sách: Loại bỏ sách khỏi danh sách."
        );

        addSection("3. Quản lý Nhân viên",
                "3.1 Thêm nhân viên\n" +
                        "- Thêm nhân viên mới: Nhập đầy đủ thông tin nhân viên, bao gồm tên, địa chỉ, tài khoản và mật khẩu.\n\n" +
                        "3.2 Sửa thông tin nhân viên\n" +
                        "- Sửa thông tin nhân viên: Cập nhật thông tin nhân viên đã tồn tại.\n\n" +
                        "3.3 Danh sách nhân viên\n" +
                        "- Xem danh sách nhân viên: Hiển thị danh sách tất cả nhân viên.\n\n" +
                        "3.4 Tìm kiếm nhân viên\n" +
                        "- Tìm kiếm nhân viên: Tìm kiếm nhân viên dựa trên mã, tên hoặc các tiêu chí khác."
        );

        addSection("4. Quản lý Chương trình khuyến mãi",
                "4.1 Tạo chương trình khuyến mãi\n" +
                        "- Thêm chương trình mới: Tạo chương trình với ngày bắt đầu, trạng thái áp dụng và loại sản phẩm áp dụng.\n\n" +
                        "4.2 Xem danh sách khuyến mãi\n" +
                        "- Xem danh sách khuyến mãi: Hiển thị danh sách tất cả chương trình khuyến mãi.\n\n" +
                        "4.3 Tìm kiếm khuyến mãi\n" +
                        "- Tìm kiếm khuyến mãi: Tìm kiếm chương trình khuyến mãi dựa trên ngày, trạng thái hoặc loại sản phẩm."
        );

        addSection("5. Quản lý Hóa đơn",
                "5.1 Theo dõi hóa đơn\n" +
                        "- Xem chi tiết hóa đơn: Xem thông tin chi tiết của một hóa đơn, bao gồm sản phẩm, nhân viên và khách hàng.\n\n" +
                        "5.2 Tìm kiếm hóa đơn\n" +
                        "- Tìm kiếm hóa đơn: Tìm kiếm hóa đơn dựa trên mã, nhân viên bán hàng hoặc khách hàng."
        );

        addSection("6. Thống kê doanh thu",
                "6.1 Tìm kiếm theo khoảng thời gian\n" +
                        "- Tìm kiếm hóa đơn theo khoảng thời gian: Xác định doanh thu, lợi nhuận trong một khoảng thời gian cụ thể.\n\n" +
                        "6.2 Xem biểu đồ thống kê\n" +
                        "- Xem biểu đồ thống kê: Hiển thị biểu đồ về doanh thu, lợi nhuận để dễ dàng theo dõi."
        );

        addSection("7. Quản lý Khách hàng",
                "7.1 Thêm, sửa, xóa khách hàng\n" +
                        "- Quản lý thông tin khách hàng: Thêm mới, cập nhật hoặc xóa thông tin khách hàng."
        );

        addSection("8. Bán hàng (Dành cho nhân viên)",
                "8.1 Tạo hóa đơn\n" +
                        "- Chọn khách hàng và sản phẩm: Tạo hóa đơn bằng cách chọn sản phẩm và khách hàng từ danh sách.\n\n" +
                        "8.2 Thanh toán hóa đơn\n" +
                        "- Nhập số tiền khách đưa: Hệ thống sẽ tính toán tiền trả lại và lưu hóa đơn."
        );

        addSection("9. Cài đặt và Tùy chọn",
                "9.1 Cài đặt giao diện\n" +
                        "- Chọn giao diện: Thay đổi giao diện của ứng dụng theo sở thích cá nhân.\n\n" +
                        "9.2 Đổi mật khẩu\n" +
                        "- Thay đổi mật khẩu: Cập nhật mật khẩu đăng nhập vào hệ thống."
        );
	}
	
	private void addSection(String title, String content) {
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(titleLabel);

        JTextArea contentArea = new JTextArea(content);
        contentArea.setEditable(false);
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        contentArea.setRows(0); // Cho phép JTextArea mở rộng theo chiều cao
        contentArea.setMaximumSize(new Dimension(Integer.MAX_VALUE, Short.MAX_VALUE));

        // Đặt chiều cao mặc định của JScrollPane
        JScrollPane scrollPane = new JScrollPane(contentArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane);

        add(Box.createRigidArea(new Dimension(0, 20))); // Khoảng cách giữa các phần
    }
}
