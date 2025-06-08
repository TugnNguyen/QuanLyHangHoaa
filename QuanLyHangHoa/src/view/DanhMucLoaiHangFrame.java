/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author Asus
 */

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.util.ArrayList;
import controller.LoaiHangDAO;
import model.LoaiHang;

public class DanhMucLoaiHangFrame extends JFrame {
     private JTable table;

    public DanhMucLoaiHangFrame() {
        setTitle("Danh mục loại hàng");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Quan trọng để không thoát toàn bộ ứng dụng

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        loadData();
    }

    private void loadData() {
        LoaiHangDAO dao = new LoaiHangDAO();
        ArrayList<LoaiHang> list = dao.getAll();

        String[] columnNames = {"Mã loại", "Tên loại"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (LoaiHang lh : list) {
            model.addRow(new Object[]{lh.getMaLoai(), lh.getTenLoai()});
        }

        table.setModel(model);
    }
}
