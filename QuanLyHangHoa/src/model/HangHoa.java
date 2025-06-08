/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Asus
 */
public class HangHoa {
    private String maHang;
    private String tenHang;
    private String maLoai;
    private String donViTinh;
    private double giaNhap;
    private String ghiChu;
    private String maNCC;

    public HangHoa() {}

    public HangHoa(String maHang, String tenHang, String maLoai, String donViTinh, double giaNhap, String ghiChu, String maNCC) {
        this.maHang = maHang;
        this.tenHang = tenHang;
        this.maLoai = maLoai;
        this.donViTinh = donViTinh;
        this.giaNhap = giaNhap;
        this.ghiChu = ghiChu;
        this.maNCC = maNCC;
    }

    // getters và setters
    public String getMaHang() { return maHang; }
    public void setMaHang(String maHang) { this.maHang = maHang; }
    public String getTenHang() { return tenHang; }
    public void setTenHang(String tenHang) { this.tenHang = tenHang; }
    public String getMaLoai() { return maLoai; }
    public void setMaLoai(String maLoai) { this.maLoai = maLoai; }
    public String getDonViTinh() { return donViTinh; }
    public void setDonViTinh(String donViTinh) { this.donViTinh = donViTinh; }
    public double getGiaNhap() { return giaNhap; }
    public void setGiaNhap(double giaNhap) { this.giaNhap = giaNhap; }
    public String getGhiChu() { return ghiChu; }
    public void setGhiChu(String ghiChu) { this.ghiChu = ghiChu; }
    public String getMaNCC() { return maNCC; }
    public void setMaNCC(String maNCC) { this.maNCC = maNCC; }
}
