/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.Date;

/**
 *
 * @author phamn
 */
public class phieunhapDTO {
    public String idphieunhap;
    public String idnhacungcap;
    public String idnhanvien;
    public Date ngaynhap;
    public double tongtien;


    public void PhieuNhapDTO(String idphieunhap, String idnhacungcap, String idnhanvien, Date ngaynhap, double tongtien) {

       this.idphieunhap=idphieunhap;
       this.idnhacungcap=idnhacungcap;
       this.idnhanvien=idnhanvien;
       this.ngaynhap=ngaynhap;
       this.tongtien=tongtien;
    }

    public void PhieuNhapDTO(){}

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
     * @return the idnhacungcap
     */
    public String getIdnhacungcap() {
        return idnhacungcap;
    }

    /**
     * @param idnhacungcap the idnhacungcap to set
     */
    public void setIdnhacungcap(String idnhacungcap) {
        this.idnhacungcap = idnhacungcap;
    }

    /**
     * @return the idnhanvien
     */
    public String getIdnhanvien() {
        return idnhanvien;
    }

    /**
     * @param idnhanvien the idnhanvien to set
     */
    public void setIdnhanvien(String idnhanvien) {
        this.idnhanvien = idnhanvien;
    }

    /**
     * @return the ngaynhap
     */
    public Date getNgaynhap() {
        return ngaynhap;
    }

    /**
     * @param ngaynhap the ngaynhap to set
     */
    public void setNgaynhap(Date ngaynhap) {
        this.ngaynhap = ngaynhap;
    }

    /**
     * @return the tongtien
     */
    public double getTongtien() {
        return tongtien;
    }

    /**
     * @param tongtien the tongtien to set
     */
    public void setTongtien(double tongtien) {
        this.tongtien = tongtien;
    }
}
