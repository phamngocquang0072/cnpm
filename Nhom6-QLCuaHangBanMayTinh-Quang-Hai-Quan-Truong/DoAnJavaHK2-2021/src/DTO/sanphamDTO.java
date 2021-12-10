package DTO;

public class sanphamDTO {
    public String idsanpham,idloaisanpham,tensanpham,mota,anh;
    public int soluong;
    public Double dongia;
    void SanPhamDTO(){}
    
    void SanPhamDTO(String idsanpham, String idloaisanpham, String tensanpham, int soluong, Double dongia, String thanhtien,String anh, String mota){
        this.idsanpham = idsanpham;
        this.idloaisanpham = idloaisanpham;
        this.tensanpham = tensanpham;
        this.soluong = soluong;
        this.dongia = dongia;
        this.anh = anh;
        this.mota = mota;
    }

    public String getIdsanpham(){
        return idsanpham;
    }

    public String getIdloaisanpham() {
        return idloaisanpham;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public Double getDongia() {
        return dongia;
    }

    public int getSoluong() {
        return soluong;
    }

    public String getAnh() {
        return anh;
    }
    
    public String getMota() {
        return mota;
    }
    
    public void setIdsanpham(String idsanpham) {
        this.idsanpham = idsanpham;
    }

    public void setIdloaisanpham(String idloaisanpham) {
        this.idloaisanpham = idloaisanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public void setDongia(Double dongia) {
        this.dongia = dongia;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
    
    public void setAnh(String anh) {
        this.anh = anh;
    }
    
    public void setMota(String mota) {
        this.mota=mota;
    }
}
