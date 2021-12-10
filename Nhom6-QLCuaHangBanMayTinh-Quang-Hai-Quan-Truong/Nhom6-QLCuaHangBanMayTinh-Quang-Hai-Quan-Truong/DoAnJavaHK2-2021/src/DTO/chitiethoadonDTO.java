/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author phamn
 */
public class chitiethoadonDTO {
    public String idhoadon, idsanpham;
    public int soluong;
    public double dongia, thanhtien;

    public chitiethoadonDTO() {
    }

    public chitiethoadonDTO(String idhoadon, String idsanpham, int soluong, double dongia, double thanhtien) {
        this.idhoadon = idhoadon;
        this.idsanpham = idsanpham;
        this.soluong = soluong;
        
        this.dongia = dongia;
        this.thanhtien = thanhtien;
    }

    public String getIdhoadon() {
        return idhoadon;
    }

    public void setIdhoadon(String idhoadon) {
        this.idhoadon = idhoadon;
    }

    public String getIdsanpham() {
        return idsanpham;
    }

    public void setIdsanpham(String idsanpham) {
        this.idsanpham = idsanpham;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

   

    public double getDongia() {
        return dongia;
    }

    public void setDongia(double dongia) {
        this.dongia = dongia;
    }

    public double getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(double thanhtien) {
        this.thanhtien = thanhtien;
    }
}
