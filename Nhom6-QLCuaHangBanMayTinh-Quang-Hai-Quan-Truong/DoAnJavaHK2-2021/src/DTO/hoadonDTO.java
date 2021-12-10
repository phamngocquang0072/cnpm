/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.*;

/**
 *
 * @author phamn
 */
public class hoadonDTO {
    public String idhoadon, idkhachhang, idnhanvien, idgiamgia;
    public double tonghoadon;
    public Date ngaylap;

    public hoadonDTO() {
    }

    public hoadonDTO(String idhoadon, String idkhachhang, String idnhanvien, String idgiamgia,  Date ngaylap, double tonghoadon) {
        this.idhoadon = idhoadon;
        this.idkhachhang = idkhachhang;
        this.idnhanvien = idnhanvien;
        this.idgiamgia = idgiamgia;
        this.tonghoadon = tonghoadon;
        this.ngaylap = ngaylap;
    }

    public String getIdhoadon() {
        return idhoadon;
    }

    public void setIdhoadon(String idhoadon) {
        this.idhoadon = idhoadon;
    }

    public String getIdkhachhang() {
        return idkhachhang;
    }

    public void setIdkhachhang(String idkhachhang) {
        this.idkhachhang = idkhachhang;
    }

    public String getIdnhanvien() {
        return idnhanvien;
    }

    public void setIdnhanvien(String idnhanvien) {
        this.idnhanvien = idnhanvien;
    }

    public String getIdgiamgia() {
        return idgiamgia;
    }

    public void setIdgiamgia(String idgiamgia) {
        this.idgiamgia = idgiamgia;
    }

    public double getTonghoadon() {
        return tonghoadon;
    }

    public void setTonghoadon(double tonghoadon) {
        this.tonghoadon = tonghoadon;
    }

    public Date getNgaylap() {
        return ngaylap;
    }

    public void setNgaylap(Date ngaylap) {
        this.ngaylap = ngaylap;
    }

    public void getTonghoadon(double tonghoadon) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
