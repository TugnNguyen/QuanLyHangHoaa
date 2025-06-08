/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author Asus
 */

import db.DBConnect;
import model.LoaiHang;
import java.sql.*;
import java.util.ArrayList;
public class LoaiHangDAO {
     public ArrayList<LoaiHang> getAll() {
        ArrayList<LoaiHang> list = new ArrayList<>();
        try (Connection conn = DBConnect.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM LoaiHang")) {

            while (rs.next()) {
                list.add(new LoaiHang(rs.getString("MaLoai"), rs.getString("TenLoai")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
