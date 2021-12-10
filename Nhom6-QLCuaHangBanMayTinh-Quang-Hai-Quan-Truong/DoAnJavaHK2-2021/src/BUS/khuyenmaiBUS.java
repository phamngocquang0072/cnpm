
package BUS;

import static BUS.khachhangBUS.removeAccent;
import static BUS.nhanvienBUS.removeAccent;
import DAO.khuyenmaiDAO;
import DTO.khuyenmaiDTO;
import DTO.khuyenmaiDTO;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class khuyenmaiBUS {
    public static ArrayList<khuyenmaiDTO> dskm;
    public khuyenmaiBUS(){}
    
    public void docDSKM() throws Exception {
        khuyenmaiDAO dao = new khuyenmaiDAO();
        if (dskm==null)
            dskm = new ArrayList<khuyenmaiDTO>();
            dskm=dao.docDSKM();
    }
     
    public boolean them (khuyenmaiDTO km){
        khuyenmaiDAO dao = new khuyenmaiDAO();
        int check = 0;
        for (int i=0;i<dskm.size();i++){
            if(km.idkm.compareTo(dskm.get(i).idkm) == 0){
                check = 1;       
              }
        }
        if (check == 0){
            dao.them(km);
            dskm.add(km);
            
            JOptionPane.showMessageDialog(null, " them thanh cong");
            return true;
        }
        else {
            JOptionPane.showMessageDialog(null, "ma khuyen mai " +km.idkm+ " da bi trung, vui long nhap lai");
            return false;
        }
    } 
    
    public void sua(int i,khuyenmaiDTO km){
        khuyenmaiDAO dao = new khuyenmaiDAO();
        dao.sua(km);
        dskm.set(i,km); 
        
       
    }
    
    public void xoa(int i,String idkm){
        khuyenmaiDAO dao = new khuyenmaiDAO();
        dao.xoa(idkm);
        dskm.remove(i);
    }

    public void duyetKM (ArrayList nhomkm,khuyenmaiDTO km){
        khuyenmaiDTO km2 = new khuyenmaiDTO();
        km2.setIdkm(km.idkm);
        km2.setTenkm(km.tenkm);
        km2.setDieukienkm(km.dieukienkm);
        km2.setPhantramkm(km.phantramkm);
        km2.setNgaybatdau(km.ngaybatdau);
        km2.setNgayketthuc(km.ngayketthuc);
        
        nhomkm.add(km2);
    }
    
    public float layPhanTram(String s){
        for(khuyenmaiDTO km:dskm){
            if(s.compareTo(km.idkm)==0){
                return km.phantramkm;
            }
        }
        return 0;
    }
    
    public ArrayList<khuyenmaiDTO> timKiem(int i, String s,String s1,String s2){
        ArrayList<khuyenmaiDTO> kmnhom =new ArrayList<>();
        if(s1 == null || s1.isEmpty()) s1 = "0";
        if(s2 == null || s2.isEmpty()) s2= "999999999";
        if(i == 0){
            for(khuyenmaiDTO km:dskm){
                if(c1(s,km) || c2(s,km)){
                    duyetKM(kmnhom,km);
                }
            }
        }else if (i == 1){
            for (khuyenmaiDTO km:dskm){
                if (c1(s,km)){
                    duyetKM(kmnhom,km);
                }
            }
        }else if (i == 2){
            for (khuyenmaiDTO km:dskm){
                if (c2(s,km)){
                    duyetKM(kmnhom,km);
                }
            }
        }
        return kmnhom;   
    }
    boolean c1(String s,khuyenmaiDTO km){
        if (s != null)
            if(removeAccent(km.idkm).toLowerCase().contains(s.toLowerCase()))
                return true;
            else return false;
        return true;
    }   
   
    boolean c2(String s,khuyenmaiDTO km){
        if (s != null)
            if(removeAccent(km.tenkm).toLowerCase().contains(s.toLowerCase()))
                return true;
            else return false;
        return true;
    } 
    
    
    private static final char[] SOURCE_CHARACTERS = {'À', 'Á', 'Â', 'Ã', 'È', 'É',
            'Ê', 'Ì', 'Í', 'Ò', 'Ó', 'Ô', 'Õ', 'Ù', 'Ú', 'Ý', 'à', 'á', 'â',
            'ã', 'è', 'é', 'ê', 'ì', 'í', 'ò', 'ó', 'ô', 'õ', 'ù', 'ú', 'ý',
            'Ă', 'ă', 'Đ', 'đ', 'Ĩ', 'ĩ', 'Ũ', 'ũ', 'Ơ', 'ơ', 'Ư', 'ư', 'Ạ',
            'ạ', 'Ả', 'ả', 'Ấ', 'ấ', 'Ầ', 'ầ', 'Ẩ', 'ẩ', 'Ẫ', 'ẫ', 'Ậ', 'ậ',
            'Ắ', 'ắ', 'Ằ', 'ằ', 'Ẳ', 'ẳ', 'Ẵ', 'ẵ', 'Ặ', 'ặ', 'Ẹ', 'ẹ', 'Ẻ',
            'ẻ', 'Ẽ', 'ẽ', 'Ế', 'ế', 'Ề', 'ề', 'Ể', 'ể', 'Ễ', 'ễ', 'Ệ', 'ệ',
            'Ỉ', 'ỉ', 'Ị', 'ị', 'Ọ', 'ọ', 'Ỏ', 'ỏ', 'Ố', 'ố', 'Ồ', 'ồ', 'Ổ',
            'ổ', 'Ỗ', 'ỗ', 'Ộ', 'ộ', 'Ớ', 'ớ', 'Ờ', 'ờ', 'Ở', 'ở', 'Ỡ', 'ỡ',
            'Ợ', 'ợ', 'Ụ', 'ụ', 'Ủ', 'ủ', 'Ứ', 'ứ', 'Ừ', 'ừ', 'Ử', 'ử', 'Ữ',
            'ữ', 'Ự', 'ự',};

    private static final char[] DESTINATION_CHARACTERS = {'A', 'A', 'A', 'A', 'E',
            'E', 'E', 'I', 'I', 'O', 'O', 'O', 'O', 'U', 'U', 'Y', 'a', 'a',
            'a', 'a', 'e', 'e', 'e', 'i', 'i', 'o', 'o', 'o', 'o', 'u', 'u',
            'y', 'A', 'a', 'D', 'd', 'I', 'i', 'U', 'u', 'O', 'o', 'U', 'u',
            'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A',
            'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'E', 'e',
            'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E',
            'e', 'I', 'i', 'I', 'i', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o',
            'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O',
            'o', 'O', 'o', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u',
            'U', 'u', 'U', 'u',};

    public static char removeAccent(char ch) {
        int index = Arrays.binarySearch(SOURCE_CHARACTERS, ch);
        if (index >= 0) {
            ch = DESTINATION_CHARACTERS[index];
        }
        return ch;
    }

    public static String removeAccent(String str) {
        StringBuilder sb = new StringBuilder(str);
        for (int i = 0; i < sb.length(); i++) {
            sb.setCharAt(i, removeAccent(sb.charAt(i)));
        }
        return sb.toString();
    }
    
   
    
    
   
}
