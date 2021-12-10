
package DTO;

/**
 *
 * @author truong
 */
public class khachhangDTO {
    public String idkhachhang;
    public String tenkhachhang;
    public String diachi;
    public String sdt;
    public String gmail;
    
    public khachhangDTO(){}
    
    public khachhangDTO(String idkhachhang, String tenkhachhang, String diachi, String sdt,String gmail){
        this.setIdkhachhang(idkhachhang);
        this.setTenkhachhang(tenkhachhang);
        this.setDiachi(diachi);
        this.setSdt(sdt);
        this.setGmail(gmail);
    }
    
    public String getIdkhachhang(){
        return idkhachhang;
    }
    public void setIdkhachhang(String idkhachhang){
        this.idkhachhang = idkhachhang;
    }
    
    public String getTenkhachhang(){
        return tenkhachhang;
    }
    public void setTenkhachhang(String tenkhachhang){
        this.tenkhachhang= tenkhachhang;
    }
    
    public String getDiachi(){
        return diachi;
    }
    public void setDiachi(String diachi){
        this.diachi= diachi;
    }
    
    public String getSdt(){
        return sdt;
    }
    public void setSdt(String sdt){
        this.sdt= sdt;
    }    
    
    public String getGmail(){
        return gmail;
    }
    public void setGmail(String gmail){
        this.gmail= gmail;
    }
    
    
    
}
