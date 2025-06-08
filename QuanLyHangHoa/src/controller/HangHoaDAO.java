/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author Asus
 */

import model.HangHoa;
import db.DBConnect;
import java.sql.*;
import java.util.ArrayList;
public class HangHoaDAO {

    public ArrayList<HangHoa> getAll() {
        ArrayList<HangHoa> list = new ArrayList<>();
        String sql = "SELECT MaHang, TenHang, MaLoai, DonViTinh, GiaNhap, GhiChu, MaNCC FROM HangHoa";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                HangHoa hh = new HangHoa(
                    rs.getString("MaHang"),
                    rs.getString("TenHang"),
                    rs.getString("MaLoai"),
                    rs.getString("DonViTinh"),
                    rs.getDouble("GiaNhap"),
                    rs.getString("GhiChu"),
                    rs.getString("MaNCC")
                );
                list.add(hh);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return list;
    }
}