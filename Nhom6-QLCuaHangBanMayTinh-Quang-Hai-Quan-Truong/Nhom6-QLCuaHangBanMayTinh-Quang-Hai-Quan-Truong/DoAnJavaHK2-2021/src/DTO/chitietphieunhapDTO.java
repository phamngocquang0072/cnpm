/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author admin
 */
public class chitietphieunhapDTO {
    public String idphieunhap;
    public String idsanpham;
    public int soluong;
    public double dongia, thanhtien;

   
    public chitietphieunhapDTO() {

    }

    public chitietphieunhapDTO(String idphieunhap, String idsanpham, int soluong, double dongia, double thanhtien) {
        this.idphieunhap = idphieunhap;
        this.idsanpham = idsanpham;
        this.soluong = soluong;
        this.dongia = dongia;
    }

    /**
     * @return the idphieunhap
     */
    public String getIdphieunhap() {
        return idphieunhap;
    }

    /**
     * @param idphieunhap the idphieunhap to set
     */
    public void setIdphieunhap(String idphieunhap) {
        this.idphieunhap = idphieunhap;
    }

    /**
     * @return the idsanpham
     */
    public String getIdsanpham() {
        return idsanpham;
    }

    /**
     * @param idsanpham the idsanpham to set
     */
    public void setIdsanpham(String idsanpham) {
        this.idsanpham = idsanpham;
    }

    /**
     * @return the soluong
     */
    public int getSoluong() {
        return soluong;
    }

    /**
     * @param soluong the soluong to set
     */
    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    /**
     * @return the dongia
     */
    public double getDongia() {
        return dongia;
    }

    /**
     * @param dongia the dongia to set
     */
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
