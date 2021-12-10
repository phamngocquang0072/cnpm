/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import static BUS.khachhangBUS.dskh;
import static BUS.taikhoanBUS.dstk;
import DAO.khachhangDAO;
import DAO.nhacungcapDAO;
import DTO.khachhangDTO;
import DTO.nhacungcapDTO;
import DTO.nhacungcapDTO;
import DTO.taikhoanDTO;
import GUI.hoadonGUI;
import GUI.nhacungcapGUI;
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
public class nhacungcapBUS {
    public static ArrayList<nhacungcapDTO> dsncc;
    public nhacungcapBUS(){
        
    }
    public void docDSNCC() throws Exception{
        nhacungcapDAO data = new nhacungcapDAO();
        if(dsncc == null) dsncc = new ArrayList<nhacungcapDTO>();
        dsncc = data.docDSNCC();
    }
   /* public void them(nhacungcapDTO ncc) throws Exception{
        nhacungcapDAO data = new nhacungcapDAO();
        data.them(ncc);
        dsncc.add(ncc);
    }*/
    public boolean them(nhacungcapDTO ncc) throws Exception{
      nhacungcapDAO data = new nhacungcapDAO();
      int check = 0;
      nhacungcapGUI gui = new nhacungcapGUI();
      
      for(int i=0;i<dsncc.size();i++){
            if(ncc.idnhacungcap.compareTo(dsncc.get(i).idnhacungcap) == 0 && (gui.tname.getText() != null && gui.tpnum.getText() != null && gui.temail.getText() != null && gui.taddress.getText() != null && gui.tid.getText() != null)){
            check  = 1;
            }
      } 
      if (check == 0){
        data.them(ncc);
        dsncc.add(ncc);
        
        JOptionPane.showMessageDialog(null, "Thêm thành công");
        return true;
    }
    else {
        JOptionPane.showMessageDialog(null, "Mã nhà cung cấp "+ ncc.idnhacungcap+ " đã tồn tại hoặc bạn chưa nhập đủ thông tin, vui lòng nhập lại"  );
        return false;
        }   
    }
    public void xoa(String idnhacungcap, int i) throws Exception{
        System.out.println(idnhacungcap+"BUS");
        nhacungcapDAO data = new nhacungcapDAO();
        data.xoa(idnhacungcap);
        
        dsncc.remove(i);
        
        
    }
    public void sua(nhacungcapDTO ncc, int i) throws Exception{
        nhacungcapDAO data = new nhacungcapDAO();
        data.sua(ncc);
        dsncc.set(i, ncc);
        JOptionPane.showMessageDialog(null,"Sửa thành công");
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
        nhacungcapGUI gui = new nhacungcapGUI();

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
            System.out.println(gui.table.getRowCount());
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
         public ArrayList<nhacungcapDTO> timKiem(int i, String s, String s1, String s2, String s3, String s4) {
        ArrayList<nhacungcapDTO> nhomncc = new ArrayList<>();
        if(s1 == null || s1.isEmpty()) s1 = "0";
        if(s2 == null || s2.isEmpty()) s2 = "999999999";
        if(s3 == null || s3.isEmpty()) s3 = "0";
        if(s4 == null || s4.isEmpty()) s4 = "999999999";
        if(i==0){
            for(nhacungcapDTO ncc:dsncc){
                if(c1(s,ncc) || c2(s,ncc) || c3(s,ncc) || c4 (s,ncc) || c5(s,ncc)){
                    duyetNCC(nhomncc,ncc);
                    
                }
            }
        }else if(i==1){
            for(nhacungcapDTO ncc:dsncc){
                if(c1(s,ncc)){
                    duyetNCC(nhomncc,ncc);
                }
            }
        }else if(i==2){
            for(nhacungcapDTO ncc:dsncc){
                if(c2(s,ncc)){
                    duyetNCC(nhomncc,ncc);
                }
            }
        }else if(i==3){
            for(nhacungcapDTO ncc:dsncc){
                if(c3(s,ncc)){
                    duyetNCC(nhomncc,ncc);
                }
            }
        }else if(i==4){
            for(nhacungcapDTO ncc:dsncc){
                if(c4(s,ncc)){
                    duyetNCC(nhomncc,ncc);
                }
            }
        }
        else if(i==5){
            for(nhacungcapDTO ncc:dsncc){
                if(c5(s,ncc)){
                    duyetNCC(nhomncc,ncc);
                }
            }
        }
        return nhomncc;
    }
    boolean c1(String s, nhacungcapDTO ncc){
        if(s != null)
            if(removeAccent(ncc.idnhacungcap).toLowerCase().contains(s.toLowerCase()))
                return true;
            else return false;
        return true;
                
    }// check id hoa don
    boolean c2(String s, nhacungcapDTO ncc){
        if(s != null)
            if(removeAccent(ncc.tennhacungcap).toLowerCase().contains(s.toLowerCase()))
                return true;
            else return false;
        return true;
                
    }// check id nhan vien
    boolean c3(String s, nhacungcapDTO ncc){
        if(s != null)
            if(removeAccent(ncc.sdt).toLowerCase().contains(s.toLowerCase()))
                return true;
            else return false;
        return true;
                
    }//check id khach hang
    boolean c4(String s, nhacungcapDTO ncc){
        if(s != null)
            if(removeAccent(ncc.gmail).toLowerCase().contains(s.toLowerCase()))
                return true;
            else return false;
        return true;
                
    }// check id giam gia
    boolean c5(String s, nhacungcapDTO ncc){
        if(s != null)
            if(removeAccent(ncc.diachi).toLowerCase().contains(s.toLowerCase()))
                return true;
            else return false;
        return true;
                
    }// check id giam gi
    
   
    

    private void duyetNCC(ArrayList nhomtk,nhacungcapDTO ncc) {
        nhacungcapDTO ncc1=new nhacungcapDTO();
        ncc1.setIdnhacungcap(ncc.idnhacungcap);
        ncc1.setTennhacungcap(ncc.tennhacungcap);
        ncc1.setSdt(ncc.sdt);
        ncc1.setGmail(ncc.gmail);
        ncc1.setDiachi(ncc.diachi);
        
        nhomtk.add(ncc1);
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
