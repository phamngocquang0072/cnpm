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
public class taikhoanDTO {

   public String taikhoan, matkhau, idnhanvien, idquyen, hinh;

    public taikhoanDTO() {

    }

    public taikhoanDTO(String taikhoan, String matkhau, String idnhanvien, String idquyen, String hinh) {
        this.taikhoan = taikhoan;
        this.matkhau = matkhau;
        this.idnhanvien = idnhanvien;
        this.idquyen = idquyen;
        this.hinh = hinh;
    }

    public String getTaikhoan() {
        return taikhoan;
    }

    public void setTaikhoan(String taikhoan) {
        this.taikhoan = taikhoan;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getIdnhanvien() {
        return idnhanvien;
    }

    public void setIdnhanvien(String idnhanvien) {
        this.idnhanvien = idnhanvien;
    }

    public String getIdquyen() {
        return idquyen;
    }

    public void setIdquyen(String idquyen) {
        this.idquyen = idquyen;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }
}
