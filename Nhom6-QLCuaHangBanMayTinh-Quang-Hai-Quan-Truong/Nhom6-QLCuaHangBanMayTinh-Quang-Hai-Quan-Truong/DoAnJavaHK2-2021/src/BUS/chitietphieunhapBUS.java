/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;


import java.util.ArrayList;
import DTO.chitietphieunhapDTO;
import DAO.chitietphieunhapDAO;

/**
 *
 * @author admin
 */
public class chitietphieunhapBUS {
    public static ArrayList<chitietphieunhapDTO> dsctpn;
    private String maPN;

    public chitietphieunhapBUS(){}
    public void DocDSCTPN(String condition) throws Exception{
        chitietphieunhapDAO data= new chitietphieunhapDAO();
        if(dsctpn==null)  dsctpn = new ArrayList<chitietphieunhapDTO>();
        dsctpn= data.DocDSCTPN(condition);
    }
    
    public void docDSPN() throws Exception{
        chitietphieunhapDAO data= new chitietphieunhapDAO();
        if(dsctpn==null)  dsctpn = new ArrayList<chitietphieunhapDTO>();
        dsctpn= data.DocDSPN();
    }
    
    public void them(chitietphieunhapDTO ctpn) throws Exception{
        chitietphieunhapDAO data=new chitietphieunhapDAO();
        data.them(ctpn);
        dsctpn.add(ctpn);
    }
    
void duyetPN(ArrayList nvnhom,chitietphieunhapDTO pn){
        chitietphieunhapDTO nv1=new chitietphieunhapDTO();
        nv1.setIdphieunhap(pn.idphieunhap);
        nv1.setIdsanpham(pn.idsanpham);
        nv1.setSoluong(pn.soluong);
        nv1.setDongia(pn.dongia);
        
        nvnhom.add(nv1);
    }   
public ArrayList<chitietphieunhapDTO> timKiem(int i, String s, String s1, String s2, String s3, String s4) {
        ArrayList<chitietphieunhapDTO> nhompn = new ArrayList<>();
        if(s1 == null || s1.isEmpty()) s1 = "0";
        if(s2 == null || s2.isEmpty()) s2 = "999999999";
        if(i==0){
            for(chitietphieunhapDTO ctpn:dsctpn){
                if(c1(s,ctpn) || c2(s,ctpn)){
                    duyetPN(nhompn,ctpn);
                }
            }
        }else if(i==1){
            for(chitietphieunhapDTO ctpn:dsctpn){
                if(c1(s,ctpn)){
                    duyetPN(nhompn,ctpn);
                }
            }
        }else if(i==2){
            for(chitietphieunhapDTO ctpn:dsctpn){
                if(c2(s,ctpn)){
                    duyetPN(nhompn,ctpn);
                }
            }
        
        }
        return nhompn;
    }
    boolean c1(String s, chitietphieunhapDTO ctpn){
        if(s != null)
            if(ctpn.idphieunhap.toLowerCase().contains(s.toLowerCase()))
                return true;
            else return false;
        return true;
                
    }// check id hoa don
    boolean c2(String s, chitietphieunhapDTO ctpn){
        if(s != null)
            if(ctpn.idsanpham.toLowerCase().contains(s.toLowerCase()))
                return true;
            else return false;
        return true;
                
    }// check id nhan vien
   
    boolean c3(String s1, String s2, chitietphieunhapDTO ctpn){
        if(s1 != null && s2 != null)
            
            if(ctpn.dongia >= Double.parseDouble(s1) && ctpn.dongia <= Double.parseDouble(s2))
                return true;
            
        return false;
    }//check theo ngany thang
}
