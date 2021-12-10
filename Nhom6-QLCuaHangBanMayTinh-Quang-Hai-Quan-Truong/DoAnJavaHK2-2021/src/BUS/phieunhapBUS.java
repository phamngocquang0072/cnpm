/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.phieunhapDAO;
import DTO.phieunhapDTO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author phamn
 */
public class phieunhapBUS {
    public static ArrayList<phieunhapDTO> dspn;
    public String mapn;

    public phieunhapBUS() {}
    public void docDSPN() throws Exception{
        phieunhapDAO data= new phieunhapDAO();
        if(dspn==null)  dspn = new ArrayList<phieunhapDTO>();
        dspn= data.DocDSPN();
    }
    boolean checkTimKiem(String s,phieunhapDTO nv){
        if(nv.getIdphieunhap().toLowerCase().contains(s.toLowerCase()) ||
           nv.getIdnhacungcap().toLowerCase().contains(s.toLowerCase()) ||
           nv.getIdnhanvien().toLowerCase().contains(s.toLowerCase()) ||
           new SimpleDateFormat("dd-MM-yyyy").format(nv.getNgaynhap()).toLowerCase().contains(s.toLowerCase())
           ){
            return true;
        }
        return false;
    }
    
    public void them(phieunhapDTO hd) throws Exception{
        phieunhapDAO data=new phieunhapDAO();
        data.them(hd);
        dspn.add(hd);
    }
    
    void duyetPN(ArrayList nvnhom,phieunhapDTO pn){
        phieunhapDTO nv1=new phieunhapDTO();
        nv1.setIdphieunhap(pn.getIdphieunhap());
        nv1.setIdnhacungcap(pn.idnhacungcap);
        nv1.setIdnhanvien(pn.idnhanvien);
        nv1.setNgaynhap(pn.ngaynhap);
        nv1.setTongtien(pn.tongtien);
        
        nvnhom.add(nv1);
    }
    
    public ArrayList<phieunhapDTO> timKiem(int i, String s, String s1, String s2, String s3, String s4) {
        ArrayList<phieunhapDTO> nhompn = new ArrayList<>();
        if(s1 == null || s1.isEmpty()) s1 = "0";
        if(s2 == null || s2.isEmpty()) s2 = "999999999";
        if(s3 == null || s3.isEmpty()) s3 = "0";
        if(s4 == null || s4.isEmpty()) s4 = "999999999";
        if(i==0){
            for(phieunhapDTO pn:dspn){
                if(c1(s,pn) || c2(s,pn) || c3(s,pn)){
                    duyetPN(nhompn,pn);
                }
            }
        }else if(i==1){
            for(phieunhapDTO pn:dspn){
                if(c1(s,pn)){
                    duyetPN(nhompn,pn);
                }
            }
        }else if(i==2){
            for(phieunhapDTO pn:dspn){
                if(c2(s,pn)){
                    duyetPN(nhompn,pn);
                }
            }
        }else if(i==3){
            for(phieunhapDTO pn:dspn){
                if(c3(s,pn)){
                    duyetPN(nhompn,pn);
                }
            }
        }
        return nhompn;
    }
    boolean c1(String s, phieunhapDTO pn){
        if(s != null)
            if(removeAccent(pn.idphieunhap).toLowerCase().contains(s.toLowerCase()))
                return true;
            else return false;
        return true;
                
    }// check id hoa don
    boolean c2(String s, phieunhapDTO pn){
        if(s != null)
            if(removeAccent(pn.idnhacungcap).toLowerCase().contains(s.toLowerCase()))
                return true;
            else return false;
        return true;
                
    }// check id nhan vien
    boolean c3(String s, phieunhapDTO pn){
        if(s != null)
            if(removeAccent(pn.idnhanvien).toLowerCase().contains(s.toLowerCase()))
                return true;
            else return false;
        return true;
                
    
    }//check theo ngay thang
    boolean c4(String s1, String s2, phieunhapDTO pn){
        if(s1 != null && s2 != null)
            
            if(pn.tongtien >= Double.parseDouble(s1) && pn.tongtien <= Double.parseDouble(s2))
                return true;
            
        return false;
    }//check theo ngany thang
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
