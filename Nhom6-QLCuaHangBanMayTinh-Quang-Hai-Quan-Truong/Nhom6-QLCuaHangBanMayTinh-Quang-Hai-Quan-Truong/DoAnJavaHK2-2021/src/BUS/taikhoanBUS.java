/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.taikhoanDAO;
import DTO.taikhoanDTO;
import DTO.nhanvienDTO;
import DTO.taikhoanDTO;
import GUI.taikhoanGUI;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author phamn
 */
public class taikhoanBUS {
      public static ArrayList<taikhoanDTO> dstk;
      public static ArrayList<nhanvienDTO> dsidnv;
    public taikhoanBUS(){
        
    }
    public void docDSTK() throws Exception{
        taikhoanDAO data = new taikhoanDAO();
        if(dstk == null) dstk = new ArrayList<taikhoanDTO>();
        dstk = data.docDSTK();
    }
    public void them(taikhoanDTO tk) throws Exception{
        taikhoanDAO data = new taikhoanDAO();
        data.them(tk);
        dstk.add(tk);
    }
    public void xoa(String idtaikhoan, int i) throws Exception{
        System.out.println(idtaikhoan+"BUS");
        taikhoanDAO data = new taikhoanDAO();
        data.xoa(idtaikhoan);
        
        dstk.remove(i);
        
    }
    public void sua(taikhoanDTO tk, String idtaikhoan, int i) throws Exception{
        taikhoanDAO data = new taikhoanDAO();
        data.sua(tk,idtaikhoan);
        dstk.set(i, tk);
    }
     public void openFile(String file) {
        try {
            File path = new File(file);
            Desktop.getDesktop().open(path);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }
        public void binexcelfunction() throws Exception {
        taikhoanGUI gui = new taikhoanGUI();

        JFileChooser jFileChooser = new JFileChooser("");
        jFileChooser.showSaveDialog(gui);
        File saveFile = jFileChooser.getSelectedFile();
        if (saveFile != null) {
            saveFile = new File(saveFile.toString() + ".xlsx");
            Workbook wb = new XSSFWorkbook();
            Sheet sheet = wb.createSheet("Customer");
            Row rowcol = sheet.createRow(0);
            for (int i = 0; i < gui.table.getColumnCount(); i++) {
                Cell cell = rowcol.createCell(i);
                cell.setCellValue(gui.table.getColumnName(i));
            }
            
            for (int j = 0; j < gui.table.getRowCount(); j++) {
                Row row = sheet.createRow(j);
                for (int k = 0; k < gui.table.getColumnCount(); k++) {
                    Cell cell = row.createCell(k);
                    if (gui.table.getValueAt(j, k) != null) {
                        cell.setCellValue(gui.table.getValueAt(j, k).toString());
                    }
                }
            }
            FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
            wb.write(out);
            wb.close();
            out.close();
            openFile(saveFile.toString());
        } else {
            JOptionPane.showMessageDialog(null, "looix");
        }

    }
        public void fillcombo() throws Exception{
            taikhoanDAO data = new taikhoanDAO();
            if(dsidnv == null) dsidnv = new ArrayList<nhanvienDTO>();
            data.fillcombo();
            
        }
        public ArrayList<taikhoanDTO> timKiem(int i, String s, String s1, String s2, String s3, String s4) {
        ArrayList<taikhoanDTO> nhomhd = new ArrayList<>();
        if(s1 == null || s1.isEmpty()) s1 = "0";
        if(s2 == null || s2.isEmpty()) s2 = "999999999";
        if(s3 == null || s3.isEmpty()) s3 = "0";
        if(s4 == null || s4.isEmpty()) s4 = "999999999";
        if(i==0){
            for(taikhoanDTO tk:dstk){
                if(c1(s,tk) || c2(s,tk) || c3(s,tk) || c4 (s,tk)){
                    duyetTK(nhomhd,tk);
                }
            }
        }else if(i==1){
            for(taikhoanDTO tk:dstk){
                if(c1(s,tk)){
                    duyetTK(nhomhd,tk);
                }
            }
        }else if(i==2){
            for(taikhoanDTO tk:dstk){
                if(c2(s,tk)){
                    duyetTK(nhomhd,tk);
                }
            }
        }else if(i==3){
            for(taikhoanDTO tk:dstk){
                if(c3(s,tk)){
                    duyetTK(nhomhd,tk);
                }
            }
        }else if(i==4){
            for(taikhoanDTO tk:dstk){
                if(c4(s,tk)){
                    duyetTK(nhomhd,tk);
                }
            }
        }
        return nhomhd;
    }
    boolean c1(String s, taikhoanDTO tk){
        if(s != null)
            if(removeAccent(tk.taikhoan).toLowerCase().contains(s.toLowerCase()))
                return true;
            else return false;
        return true;
                
    }// check id hoa don
    boolean c2(String s, taikhoanDTO tk){
        if(s != null)
            if(removeAccent(tk.matkhau).toLowerCase().contains(s.toLowerCase()))
                return true;
            else return false;
        return true;
                
    }// check id nhan vien
    boolean c3(String s, taikhoanDTO tk){
        if(s != null)
            if(removeAccent(tk.idnhanvien).toLowerCase().contains(s.toLowerCase()))
                return true;
            else return false;
        return true;
                
    }//check id khach hang
    boolean c4(String s, taikhoanDTO tk){
        if(s != null)
            if(removeAccent(tk.idquyen).toLowerCase().contains(s.toLowerCase()))
                return true;
            else return false;
        return true;
                
    }// check id giam gia
    
   
    

    private void duyetTK(ArrayList nhomtk,taikhoanDTO tk) {
        taikhoanDTO hd1=new taikhoanDTO();
        hd1.setTaikhoan(tk.taikhoan);
        hd1.setMatkhau(tk.matkhau);
        hd1.setIdnhanvien(tk.idnhanvien);
        hd1.setIdquyen(tk.idquyen);
        hd1.setHinh(tk.hinh);
        nhomtk.add(hd1);
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
