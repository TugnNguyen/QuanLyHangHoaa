/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author Asus
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class SearchFrame extends JFrame {
    private JTextField txtSearch;
    private JButton btnSearch;
    private JTable table;
    private DefaultTableModel tableModel;

    public SearchFrame() {
        setTitle("Tra cứu hàng hóa");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelSearch = new JPanel(new FlowLayout());
        panelSearch.add(new JLabel("Tên hàng hóa: "));
        txtSearch = new JTextField(20);
        panelSearch.add(txtSearch);
        btnSearch = new JButton("Tìm");
        panelSearch.add(btnSearch);

        tableModel = new DefaultTableModel(new Object[]{"Mã hàng", "Tên hàng", "Loại", "Giá"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        setLayout(new BorderLayout());
        add(panelSearch, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        btnSearch.addActionListener(e -> searchProducts());
    }

    private void searchProducts() {
    String keyword = txtSearch.getText().trim();
    tableModel.setRowCount(0);

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Không tìm thấy driver MySQL!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
    }

    String url = "jdbc:mysql://localhost:3306/qlhh?serverTimezone=UTC";
    String user = "root";
    String password = "";  // điền mật khẩu của bạn nếu có

    String sql = "SELECT hh.MaHang, hh.TenHang, lh.TenLoai, hh.GiaNhap, hh.DonViTinh, ncc.TenNCC, hh.GhiChu " +
             "FROM HangHoa hh " +
             "LEFT JOIN LoaiHang lh ON hh.MaLoai = lh.MaLoai " +
             "LEFT JOIN NhaCungCap ncc ON hh.MaNCC = ncc.MaNCC " +
             "WHERE hh.TenHang LIKE ?";


    try (Connection conn = DriverManager.getConnection(url, user, password);
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, "%" + keyword + "%");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            String maHang = rs.getString("MaHang");
            String tenHang = rs.getString("TenHang");
            String tenLoai = rs.getString("TenLoai");
            double giaNhap = rs.getDouble("GiaNhap");

            tableModel.addRow(new Object[]{maHang, tenHang, tenLoai, giaNhap});
        }

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi kết nối hoặc truy vấn dữ liệu!\n" + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
    }
}
