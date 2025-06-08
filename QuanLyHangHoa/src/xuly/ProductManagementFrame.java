/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xuly;

/**
 *
 * @author Asus
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ProductManagementFrame extends JFrame {
    private JTextField txtMaHang, txtTenHang, txtGia, txtSearch, txtDonViTinh, txtGhiChu, txtSoLuong;
    private JComboBox<String> cbLoaiHang, cbNhaCungCap;
    private JButton btnAdd, btnUpdate, btnDelete, btnSearch, btnTinhTien;
    private JTable table;
    private DefaultTableModel tableModel;

    // DB config
    private final String url = "jdbc:mysql://localhost:3306/qlhh?serverTimezone=UTC";
    private final String user = "root";
    private final String password = "";

    public ProductManagementFrame() {
        setTitle("Quản lý hàng hóa");
        setSize(800, 550); // rộng hơn cho đủ chỗ
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initComponents();
        loadLoaiHang();
        loadTableData();
        loadNhaCungCap();

        // Sự kiện chọn hàng trong bảng để hiển thị lên form
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                int row = table.getSelectedRow();
                txtMaHang.setText(tableModel.getValueAt(row, 0).toString());
                txtTenHang.setText(tableModel.getValueAt(row, 1).toString());
                cbLoaiHang.setSelectedItem(tableModel.getValueAt(row, 2).toString());
                txtGia.setText(tableModel.getValueAt(row, 3).toString());
                txtDonViTinh.setText(tableModel.getValueAt(row, 4).toString());
                cbNhaCungCap.setSelectedItem(tableModel.getValueAt(row, 5).toString());
                txtGhiChu.setText(tableModel.getValueAt(row, 6).toString());
                txtSoLuong.setText(tableModel.getValueAt(row, 7).toString());
                txtMaHang.setEditable(false); // không được sửa mã hàng
            }
        });
    }

    private void initComponents() {
        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        // Mã hàng
        gbc.gridx = 0; gbc.gridy = 0;
        panelForm.add(new JLabel("Mã hàng:"), gbc);
        gbc.gridx = 1;
        txtMaHang = new JTextField(15);
        panelForm.add(txtMaHang, gbc);

        // Tên hàng
        gbc.gridx = 0; gbc.gridy++;
        panelForm.add(new JLabel("Tên hàng:"), gbc);
        gbc.gridx = 1;
        txtTenHang = new JTextField(15);
        panelForm.add(txtTenHang, gbc);

        // Loại hàng
        gbc.gridx = 0; gbc.gridy++;
        panelForm.add(new JLabel("Loại hàng:"), gbc);
        gbc.gridx = 1;
        cbLoaiHang = new JComboBox<>();
        panelForm.add(cbLoaiHang, gbc);

        // Giá nhập
        gbc.gridx = 0; gbc.gridy++;
        panelForm.add(new JLabel("Giá nhập:"), gbc);
        gbc.gridx = 1;
        txtGia = new JTextField(15);
        panelForm.add(txtGia, gbc);

        // Đơn vị tính
        gbc.gridx = 0; gbc.gridy++;
        panelForm.add(new JLabel("Đơn vị tính:"), gbc);
        gbc.gridx = 1;
        txtDonViTinh = new JTextField(15);
        panelForm.add(txtDonViTinh, gbc);

        // Nhà cung cấp
        gbc.gridx = 0; gbc.gridy++;
        panelForm.add(new JLabel("Nhà cung cấp:"), gbc);
        gbc.gridx = 1;
        cbNhaCungCap = new JComboBox<>();
        panelForm.add(cbNhaCungCap, gbc);

        // Ghi chú
        gbc.gridx = 0; gbc.gridy++;
        panelForm.add(new JLabel("Ghi chú:"), gbc);
        gbc.gridx = 1;
        txtGhiChu = new JTextField(15);
        panelForm.add(txtGhiChu, gbc);

        // Số lượng (mới thêm)
        gbc.gridx = 0; gbc.gridy++;
        panelForm.add(new JLabel("Số lượng:"), gbc);
        gbc.gridx = 1;
        txtSoLuong = new JTextField(15);
        panelForm.add(txtSoLuong, gbc);

        // Panel nút bấm nằm bên dưới form
        JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        btnAdd = new JButton("Thêm");
        btnUpdate = new JButton("Sửa");
        btnDelete = new JButton("Xóa");
        btnTinhTien = new JButton("Tính Tổng Tiền");
        panelButtons.add(btnAdd);
        panelButtons.add(btnUpdate);
        panelButtons.add(btnDelete);
        panelButtons.add(btnTinhTien);

        // Panel tìm kiếm nằm trên đầu frame
        JPanel panelSearch = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        txtSearch = new JTextField(20);
        btnSearch = new JButton("Tìm");
        panelSearch.add(new JLabel("Tìm theo tên:"));
        panelSearch.add(txtSearch);
        panelSearch.add(btnSearch);

        // Bảng dữ liệu: bổ sung cột số lượng
        tableModel = new DefaultTableModel(new Object[]{"Mã hàng", "Tên hàng", "Loại hàng", "Giá nhập", "Đơn vị tính", "Nhà cung cấp", "Ghi chú", "Số lượng"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Layout tổng thể Frame
        setLayout(new BorderLayout(10, 10));
        add(panelSearch, BorderLayout.NORTH);

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(panelForm, BorderLayout.CENTER);
        leftPanel.add(panelButtons, BorderLayout.SOUTH);
        add(leftPanel, BorderLayout.WEST);

        add(scrollPane, BorderLayout.CENTER);

        // Gán hành động cho nút
        btnAdd.addActionListener(e -> addProduct());
        btnUpdate.addActionListener(e -> updateProduct());
        btnDelete.addActionListener(e -> deleteProduct());
        btnSearch.addActionListener(e -> searchProducts());
        btnTinhTien.addActionListener(e -> {
            try {
                int soLuong = Integer.parseInt(txtSoLuong.getText().trim());
                double giaNhap = Double.parseDouble(txtGia.getText().trim());
                if (soLuong < 0) {
                    JOptionPane.showMessageDialog(this, "Số lượng không được âm");
                    return;
                }
                if (giaNhap <= 0) {
                    JOptionPane.showMessageDialog(this, "Giá nhập phải lớn hơn 0");
                    return;
                }
                double tongTien = tinhTongTienNhap(soLuong, giaNhap);
                JOptionPane.showMessageDialog(this, "Tổng tiền nhập: " + tongTien, "Kết quả", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập số hợp lệ cho số lượng và giá nhập");
            }
        });
    }

    private void loadLoaiHang() {
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT TenLoai FROM LoaiHang")) {
            cbLoaiHang.removeAllItems();
            while (rs.next()) {
                cbLoaiHang.addItem(rs.getString("TenLoai"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi tải loại hàng", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadNhaCungCap() {
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT TenNCC FROM NhaCungCap")) {
            cbNhaCungCap.removeAllItems();
            while (rs.next()) {
                cbNhaCungCap.addItem(rs.getString("TenNCC"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi tải nhà cung cấp", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadTableData() {
        tableModel.setRowCount(0);
        String sql = "SELECT hh.MaHang, hh.TenHang, lh.TenLoai, hh.GiaNhap, hh.DonViTinh, ncc.TenNCC, hh.GhiChu, hh.SoLuong "
                   + "FROM HangHoa hh "
                   + "JOIN LoaiHang lh ON hh.MaLoai = lh.MaLoai "
                   + "JOIN NhaCungCap ncc ON hh.MaNCC = ncc.MaNCC";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Object[] row = new Object[]{
                    rs.getString("MaHang"),
                    rs.getString("TenHang"),
                    rs.getString("TenLoai"),
                    rs.getDouble("GiaNhap"),
                    rs.getString("DonViTinh"),
                    rs.getString("TenNCC"),
                    rs.getString("GhiChu"),
                    rs.getInt("SoLuong")
                };
                tableModel.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi tải dữ liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validateInput() {
        String maHang = txtMaHang.getText().trim();
        String tenHang = txtTenHang.getText().trim();
        String giaStr = txtGia.getText().trim();
        String donViTinh = txtDonViTinh.getText().trim();
        String ghiChu = txtGhiChu.getText().trim();
        String soLuongStr = txtSoLuong.getText().trim();

        if (maHang.isEmpty() || tenHang.isEmpty() || giaStr.isEmpty() || donViTinh.isEmpty() || soLuongStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ các trường bắt buộc.");
            return false;
        }

        try {
            double giaNhap = Double.parseDouble(giaStr);
            if (giaNhap <= 0) {
                JOptionPane.showMessageDialog(this, "Giá nhập phải lớn hơn 0.");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Giá nhập phải là số hợp lệ.");
            return false;
        }

        try {
            int soLuong = Integer.parseInt(soLuongStr);
            if (soLuong < 0) {
                JOptionPane.showMessageDialog(this, "Số lượng không được âm.");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Số lượng phải là số nguyên hợp lệ.");
            return false;
        }

        return true;
    }

    private void addProduct() {
        if (!validateInput()) return;

        String maHang = txtMaHang.getText().trim();
        String tenHang = txtTenHang.getText().trim();
        String loaiHang = (String) cbLoaiHang.getSelectedItem();
        String nhaCungCap = (String) cbNhaCungCap.getSelectedItem();
        double giaNhap = Double.parseDouble(txtGia.getText().trim());
        String donViTinh = txtDonViTinh.getText().trim();
        String ghiChu = txtGhiChu.getText().trim();
        int soLuong = Integer.parseInt(txtSoLuong.getText().trim());

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            // Lấy MaLoai từ TenLoai
            int maLoai = 0;
            String sqlLoai = "SELECT MaLoai FROM LoaiHang WHERE TenLoai = ?";
            try (PreparedStatement psLoai = conn.prepareStatement(sqlLoai)) {
                psLoai.setString(1, loaiHang);
                try (ResultSet rs = psLoai.executeQuery()) {
                    if (rs.next()) {
                        maLoai = rs.getInt("MaLoai");
                    }
                }
            }

            // Lấy MaNCC từ TenNCC
            int maNCC = 0;
            String sqlNCC = "SELECT MaNCC FROM NhaCungCap WHERE TenNCC = ?";
            try (PreparedStatement psNCC = conn.prepareStatement(sqlNCC)) {
                psNCC.setString(1, nhaCungCap);
                try (ResultSet rs = psNCC.executeQuery()) {
                    if (rs.next()) {
                        maNCC = rs.getInt("MaNCC");
                    }
                }
            }

            String sql = "INSERT INTO HangHoa (MaHang, TenHang, MaLoai, GiaNhap, DonViTinh, MaNCC, GhiChu, SoLuong) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, maHang);
                ps.setString(2, tenHang);
                ps.setInt(3, maLoai);
                ps.setDouble(4, giaNhap);
                ps.setString(5, donViTinh);
                ps.setInt(6, maNCC);
                ps.setString(7, ghiChu);
                ps.setInt(8, soLuong);

                int rows = ps.executeUpdate();
                if (rows > 0) {
                    JOptionPane.showMessageDialog(this, "Thêm hàng hóa thành công.");
                    loadTableData();
                    clearForm();
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm hàng hóa thất bại.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi thêm hàng hóa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateProduct() {
    if (!validateInput()) return;

    String maHang = txtMaHang.getText().trim();
    String tenHang = txtTenHang.getText().trim();
    String loaiHang = (String) cbLoaiHang.getSelectedItem();
    String nhaCungCap = (String) cbNhaCungCap.getSelectedItem();
    double giaNhap = Double.parseDouble(txtGia.getText().trim());
    String donViTinh = txtDonViTinh.getText().trim();
    String ghiChu = txtGhiChu.getText().trim();
    int soLuong = Integer.parseInt(txtSoLuong.getText().trim());

    try (Connection conn = DriverManager.getConnection(url, user, password)) {
        // Lấy MaLoai từ TenLoai
        String maLoai = null;
        String sqlLoai = "SELECT MaLoai FROM LoaiHang WHERE TenLoai = ?";
        try (PreparedStatement psLoai = conn.prepareStatement(sqlLoai)) {
            psLoai.setString(1, loaiHang);
            try (ResultSet rs = psLoai.executeQuery()) {
                if (rs.next()) {
                    maLoai = rs.getString("MaLoai");
                }
            }
        }

        // Lấy MaNCC từ TenNCC
        String maNCC = null;
        String sqlNCC = "SELECT MaNCC FROM NhaCungCap WHERE TenNCC = ?";
        try (PreparedStatement psNCC = conn.prepareStatement(sqlNCC)) {
            psNCC.setString(1, nhaCungCap);
            try (ResultSet rs = psNCC.executeQuery()) {
                if (rs.next()) {
                    maNCC = rs.getString("MaNCC");
                }
            }
        }

        String sql = "UPDATE HangHoa SET TenHang=?, MaLoai=?, GiaNhap=?, DonViTinh=?, MaNCC=?, GhiChu=?, SoLuong=? WHERE MaHang=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tenHang);
            ps.setString(2, maLoai);
            ps.setDouble(3, giaNhap);
            ps.setString(4, donViTinh);
            ps.setString(5, maNCC);
            ps.setString(6, ghiChu);
            ps.setInt(7, soLuong);
            ps.setString(8, maHang);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Cập nhật hàng hóa thành công.");
                loadTableData();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật hàng hóa thất bại.");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi cập nhật hàng hóa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
    }
    private void deleteProduct() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hàng để xóa.");
            return;
        }

        String maHang = tableModel.getValueAt(row, 0).toString();

        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa hàng hóa này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "DELETE FROM HangHoa WHERE MaHang=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, maHang);
                int rows = ps.executeUpdate();
                if (rows > 0) {
                    JOptionPane.showMessageDialog(this, "Xóa hàng hóa thành công.");
                    loadTableData();
                    clearForm();
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa hàng hóa thất bại.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi xóa hàng hóa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchProducts() {
        String keyword = txtSearch.getText().trim();
        tableModel.setRowCount(0);
        String sql = "SELECT hh.MaHang, hh.TenHang, lh.TenLoai, hh.GiaNhap, hh.DonViTinh, ncc.TenNCC, hh.GhiChu, hh.SoLuong "
                   + "FROM HangHoa hh "
                   + "JOIN LoaiHang lh ON hh.MaLoai = lh.MaLoai "
                   + "JOIN NhaCungCap ncc ON hh.MaNCC = ncc.MaNCC "
                   + "WHERE hh.TenHang LIKE ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Object[] row = new Object[]{
                        rs.getString("MaHang"),
                        rs.getString("TenHang"),
                        rs.getString("TenLoai"),
                        rs.getDouble("GiaNhap"),
                        rs.getString("DonViTinh"),
                        rs.getString("TenNCC"),
                        rs.getString("GhiChu"),
                        rs.getInt("SoLuong")
                    };
                    tableModel.addRow(row);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi tìm kiếm hàng hóa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        txtMaHang.setText("");
        txtTenHang.setText("");
        cbLoaiHang.setSelectedIndex(0);
        txtGia.setText("");
        txtDonViTinh.setText("");
        cbNhaCungCap.setSelectedIndex(0);
        txtGhiChu.setText("");
        txtSoLuong.setText("");
        txtMaHang.setEditable(true);
        table.clearSelection();
    }

    private double tinhTongTienNhap(int soLuong, double giaNhap) {
        return soLuong * giaNhap;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ProductManagementFrame().setVisible(true);
        });
    }
}

