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
import java.awt.BorderLayout;
import java.awt.*;
public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Màn hình chính");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton btnDanhMucHangHoa = new JButton("Danh mục hàng hóa");
        JButton btnDanhMucLoaiHang = new JButton("Danh mục loại hàng");
        JButton btnQuanLyHangHoa = new JButton("Quản lý hàng hóa");

        btnDanhMucHangHoa.addActionListener(e -> new DanhMucHangHoaFrame().setVisible(true));
        btnDanhMucLoaiHang.addActionListener(e -> new DanhMucLoaiHangFrame().setVisible(true));
        btnQuanLyHangHoa.addActionListener(e -> new xuly.ProductManagementFrame().setVisible(true));

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        panel.add(btnDanhMucHangHoa);
        panel.add(btnDanhMucLoaiHang);
        panel.add(btnQuanLyHangHoa);

        add(panel);
    }
}