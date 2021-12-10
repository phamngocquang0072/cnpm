
package DTO;

import java.sql.Date;



public class khuyenmaiDTO {
    public String      idkm,tenkm;
    public float       dieukienkm,phantramkm;
    public Date        ngaybatdau,ngayketthuc;
    public khuyenmaiDTO() {}
    
    
    public khuyenmaiDTO(String idkm,String tenkm,float dieukienkm,float phantramkm,Date ngaybatdau,Date ngayketthuc){
        this.idkm = idkm;
        this.tenkm = tenkm;
        this.dieukienkm = this.dieukienkm;
        this.phantramkm = this.phantramkm;
        this.ngaybatdau = ngaybatdau;
        this.ngayketthuc = ngayketthuc;   
    }


    public String getIdkm(){
        return idkm;
    }
    public void setIdkm(String idkm){
        this.idkm = idkm;
    }
    public String getTenkm(){
        return tenkm;
    }
    public void setTenkm(String tenkm){
        this.tenkm = this.tenkm;
    }
    public float getDieukienkm(){
        return dieukienkm;
    }
    public void setDieukienkm(float dieukienkm){
        this.dieukienkm = dieukienkm;
    }
    public float getPhantramkm(){
        return phantramkm;
    }
    public void setPhantramkm(float phantramkm){
        this.phantramkm = phantramkm;
    }
    public Date getNgaybatdau(){
        return ngaybatdau;
    }
    public void setNgaybatdau(Date ngaybatdau){
        this.ngaybatdau = ngaybatdau;
    }
    public Date getNgaykethuc(){
        return ngayketthuc;
    }
    public void setNgayketthuc(Date ngayketthuc){
        this.ngayketthuc = ngayketthuc;
    }
  
}
