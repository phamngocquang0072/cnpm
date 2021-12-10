
package BUS;


import static BUS.khuyenmaiBUS.removeAccent;
import DAO.khachhangDAO;
import DTO.khachhangDTO;
import java.awt.List;

import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;



public class khachhangBUS {
    public static ArrayList<khachhangDTO>  dskh;
    public static ArrayList<khachhangDTO>  dskh2;
    public khachhangBUS(){}
    public void docDSKH() throws Exception {
        khachhangDAO dao = new khachhangDAO();
        if (dskh == null) 
          dskh = new ArrayList<khachhangDTO>(); 
          dskh=dao.docDSKH();
  }
  
    public boolean them(khachhangDTO kh){
      khachhangDAO dao = new khachhangDAO();
      int check = 0;
      for(int i=0;i<dskh.size();i++){
            if(kh.idkhachhang.compareTo(dskh.get(i).idkhachhang) == 0){
            check  = 1;
            }
      } 
      if (check == 0){
        dao.them(kh);
        dskh.add(kh);
        
        JOptionPane.showMessageDialog(null, "them thanh cong");
        return true;
    }
    else {
        JOptionPane.showMessageDialog(null, "ma khach khang "+ kh.idkhachhang+ " da bi trung, vui long nhap lai"  );
        return false;
        }   
    }
    
    public void xoa(int i, String idkhachhang){
        khachhangDAO dao = new khachhangDAO();
        dao.xoa(idkhachhang);
        dskh.remove(i);
        
        JOptionPane.showMessageDialog(null, " xoa thanh cong");
    }

    public void sua(int i, khachhangDTO kh) {
        khachhangDAO dao = new khachhangDAO();
        dao.sua(kh);
        dskh.set(i,kh);
        
        JOptionPane.showMessageDialog(null,"sua thanh cong");
    }

    
    public void duyetKH(ArrayList nhomkh,khachhangDTO kh){
        khachhangDTO kh2 = new khachhangDTO();
        kh2.setIdkhachhang(kh.idkhachhang);
        kh2.setTenkhachhang(kh.tenkhachhang);
        kh2.setDiachi(kh.diachi);
        kh2.setSdt(kh.sdt);
        kh2.setGmail(kh.gmail);
        
        nhomkh.add(kh2);
    }
    
    public ArrayList<khachhangDTO> timKiem(int i, String s, String s1, String s2){
        ArrayList<khachhangDTO> khnhom = new ArrayList<>();
        if(s1 == null || s1.isEmpty()) s1 = "0";
        if(s2 == null || s2.isEmpty()) s2 = "999999999"; 
        if(i==0){
            for(khachhangDTO kh:dskh){
                if (c1(s,kh) || c2(s,kh) || c3(s,kh) || c4(s,kh)){
                    duyetKH(khnhom,kh);
                }
            }
        }else if(i==1){
            for(khachhangDTO kh:dskh){
                if(c1(s,kh)){
                    duyetKH(khnhom,kh);
                }
            }
        }else if(i==2){
            for(khachhangDTO kh:dskh){
                if(c2(s,kh)){
                    duyetKH(khnhom,kh);
                }
            }
        }else if(i==3){
            for(khachhangDTO kh:dskh){
                if(c3(s,kh)){
                    duyetKH(khnhom,kh);
                }
            }
        }else if(i==4){
            for (khachhangDTO kh:dskh){
                if(c4(s,kh)){
                    duyetKH(khnhom,kh);
                }
            }
        }
        return khnhom;    
    }
    boolean c1(String s, khachhangDTO kh){
        if(s != null)
            if(removeAccent(kh.idkhachhang).toLowerCase().contains(s.toLowerCase()))
                return true;
            else return false;
        return true;
    }
    boolean c2(String s, khachhangDTO kh){
        if(s != null)
            if(removeAccent(kh.tenkhachhang).toLowerCase().contains(s.toLowerCase()))
                return true;
            else return false;
        return true;
    }
    boolean c3(String s, khachhangDTO kh){
        if(s != null)
            if(removeAccent(kh.diachi).toLowerCase().contains(s.toLowerCase()))
                return true;
            else return false;
        return true;
    }
    boolean c4(String s, khachhangDTO kh){
        if(s != null)
            if(removeAccent(kh.sdt).toLowerCase().contains(s.toLowerCase()))
                return true;
            else return false;
        return true;
    }
    boolean c5(String s, khachhangDTO kh){
        if(s != null)
            if(removeAccent(kh.gmail).toLowerCase().contains(s.toLowerCase()))
                return true;
            else return false;
        return true;
    }

    public void binexcelfunction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
