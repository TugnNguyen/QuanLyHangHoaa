/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author Asus
 */

import controller.HangHoaDAO;
import model.HangHoa;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class DanhMucHangHoaFrame extends JFrame {
    private JTable table;

    public DanhMucHangHoaFrame() {
        setTitle("Danh mục hàng hóa");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        loadData();
    }

    private void loadData() {
    HangHoaDAO dao = new HangHoaDAO();
    ArrayList<HangHoa> list = dao.getAll();
    System.out.println("🔍 Số lượng hàng hóa lấy được: " + list.size());
    String[] columnNames = {"Mã hàng", "Tên hàng", "Mã loại", "Đơn vị tính", "Giá nhập", "Ghi chú", "Mã NCC"};
    DefaultTableModel model = new DefaultTableModel(columnNames, 0);

    for (HangHoa hh : list) {
        model.addRow(new Object[]{
            hh.getMaHang(),
            hh.getTenHang(),
            hh.getMaLoai(),
            hh.getDonViTinh(),
            hh.getGiaNhap(),
            hh.getGhiChu(),
            hh.getMaNCC()
        });
    }
    table.setModel(model);
}

}
