package DTO;

import java.util.Date;

public class nhanvienDTO {
    public String ma;
    public String hoten;
    public Date ngsinh;
    public String diachi;
    public String email;
    public String sdt;

    public nhanvienDTO(String ma, String hoten, Date ngsinh,String diachi, String sdt, String email){
        this.setMa(ma);
        this.setHoten(hoten);
        this.setNgsinh(ngsinh);
        this.setDiachi(diachi);        
        this.setSdt(sdt);
        this.setEmail(email);
    }
    public nhanvienDTO(){}

    /**
     * @return the ma
     */
    public String getMa() {
        return ma;
    }

    /**
     * @param ma the ma to set
     */
    public void setMa(String ma) {
        this.ma = ma;
    }

    /**
     * @return the hoten
     */
    public String getHoten() {
        return hoten;
    }

    /**
     * @param hoten the hoten to set
     */
    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    /**
     * @return the ngsinh
     */
    public Date getNgsinh() {
        return ngsinh;
    }

    /**
     * @param ngsinh the ngsinh to set
     */
    public void setNgsinh(Date ngsinh) {
        this.ngsinh = ngsinh;
    }

    /**
     * @return the diachi
     */
    public String getDiachi() {
        return diachi;
    }

    /**
     * @param diachi the diachi to set
     */
    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the sdt
     */
    public String getSdt() {
        return sdt;
    }

    /**
     * @param sdt the sdt to set
     */
    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
   
}
