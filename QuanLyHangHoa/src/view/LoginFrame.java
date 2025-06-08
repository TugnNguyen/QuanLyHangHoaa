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
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import xuly.ProductManagementFrame;
import javax.swing.*;
import java.awt.*;
public class LoginFrame extends JFrame {
    private JTextField txtUser;
    private JPasswordField txtPass;
    private JButton btnLogin;

    public LoginFrame() {
        setTitle("Đăng nhập hệ thống");
        setSize(350, 220);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        initUI();
    }

    private void initUI() {
    JPanel panel = new JPanel(new BorderLayout(10, 10)); // Layout chính

    JPanel inputPanel = new JPanel();
    inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
    inputPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

    // Tạo dòng Tài khoản
    JPanel userPanel = new JPanel(new BorderLayout(5, 5));
    userPanel.add(new JLabel("Tài khoản:"), BorderLayout.WEST);
    txtUser = new JTextField(20);
    userPanel.add(txtUser, BorderLayout.CENTER);
    inputPanel.add(userPanel);
    inputPanel.add(Box.createVerticalStrut(10)); // khoảng cách dòng

    // Tạo dòng Mật khẩu
    JPanel passPanel = new JPanel(new BorderLayout(5, 5));
    passPanel.add(new JLabel("Mật khẩu:"), BorderLayout.WEST);
    txtPass = new JPasswordField(20);
    passPanel.add(txtPass, BorderLayout.CENTER);
    inputPanel.add(passPanel);

    btnLogin = new JButton("Đăng nhập");

    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    panel.add(inputPanel, BorderLayout.CENTER);
    panel.add(btnLogin, BorderLayout.SOUTH);

    add(panel);

    btnLogin.addActionListener(e -> {
        String username = txtUser.getText();
        String password = String.valueOf(txtPass.getPassword());

        if (username.equals("admin") && password.equals("123")) {
            dispose();
            new MainFrame().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Sai tài khoản hoặc mật khẩu");
        }
    });
    }

    private void doLogin() {
        String username = txtUser.getText().trim();
        String password = new String(txtPass.getPassword());

        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tài khoản", "Thông báo", JOptionPane.WARNING_MESSAGE);
            txtUser.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mật khẩu", "Thông báo", JOptionPane.WARNING_MESSAGE);
            txtPass.requestFocus();
            return;
        }

        if (username.equals("admin") && password.equals("123")) {
            dispose(); // đóng form đăng nhập
            // Mở form chính, thay đổi tên package/class theo dự án bạn
            new view.MainFrame().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Sai tài khoản hoặc mật khẩu", "Lỗi đăng nhập", JOptionPane.ERROR_MESSAGE);
            txtPass.setText("");
            txtPass.requestFocus();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }
}