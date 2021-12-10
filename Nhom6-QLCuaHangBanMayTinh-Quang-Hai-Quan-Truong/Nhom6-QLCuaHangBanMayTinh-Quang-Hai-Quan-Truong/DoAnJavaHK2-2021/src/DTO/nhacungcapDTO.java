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
public class nhacungcapDTO {
    public String idnhacungcap;
    public String tennhacungcap, sdt, gmail, diachi;

    public nhacungcapDTO() {
    }

    public nhacungcapDTO(String idnhacungcap, String tennhacungcap, String sdt, String gmail, String diachi) {
        this.idnhacungcap = idnhacungcap;
        this.tennhacungcap = tennhacungcap;
        this.sdt = sdt;
        this.gmail = gmail;
        this.diachi = diachi;
    }

    public String getIdnhacungcap() {
        return idnhacungcap;
    }

    public void setIdnhacungcap(String idnhacungcap) {
        this.idnhacungcap = idnhacungcap;
    }

    public String getTennhacungcap() {
        return tennhacungcap;
    }

    public void setTennhacungcap(String tennhacungcap) {
        this.tennhacungcap = tennhacungcap;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }
    
}
