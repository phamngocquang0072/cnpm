package BUS;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import DTO.nhanvienDTO;
import DAO.nhanvienDAO;
import GUI.nhanvienGUI;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import javax.swing.JFileChooser;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class nhanvienBUS {
    public static ArrayList<nhanvienDTO> dsnv;
    public nhanvienBUS(){}
    public void docDSNV() throws Exception{
        nhanvienDAO data = new nhanvienDAO();
        if(dsnv==null) 
            dsnv=new ArrayList<nhanvienDTO>();
        dsnv=data.docDSNV();
    }
    public boolean them(nhanvienDTO nv){
        nhanvienDAO data=new nhanvienDAO();
        int check=0;
        for(int i=0;i<dsnv.size();i++){
            if(nv.ma.compareTo(dsnv.get(i).ma)==0){
                check=1;
            }
        }
        if(check==0){
            data.them(nv);
            dsnv.add(nv);
            return true;
        }
        else{
            JOptionPane.showMessageDialog(null,"Mã nhân viên "+nv.ma+" đã bị trùng, vui lòng nhập mã khác!");
            return false;
        }
    }
    public void sua(int i,nhanvienDTO nv){
        nhanvienDAO data=new nhanvienDAO();
        data.sua(nv);
        dsnv.set(i, nv);
    }
    public void xoa(int i,String ma){
        nhanvienDAO data=new nhanvienDAO();
        data.xoa(ma);
        dsnv.remove(i);
    }
//    boolean checkTimKiem(String s,nhanvienDTO nv){
//        if(nv.getMa().toLowerCase().contains(s.toLowerCase()) ||
//           nv.getHoten().toLowerCase().contains(s.toLowerCase()) ||
//           new SimpleDateFormat("d-M-yyyy").format(nv.getNgsinh()).toLowerCase().contains(s.toLowerCase()) ||
//           nv.getDiachi().toLowerCase().contains(s.toLowerCase()) ||
//           nv.getSdt().toLowerCase().contains(s.toLowerCase())||
//           nv.getEmail().toLowerCase().contains(s.toLowerCase())){
//            return true;
//        }
//        return false;
//    }
    public void duyetNV(ArrayList nhomnv,nhanvienDTO nv){
        nhanvienDTO nv1=new nhanvienDTO();
        nv1.setMa(nv.ma);
        nv1.setHoten(nv.hoten);
        nv1.setNgsinh(nv.ngsinh);
        nv1.setDiachi(nv.diachi);
        nv1.setSdt(nv.sdt);
        nv1.setEmail(nv.email);
    
        nhomnv.add(nv1);
    }
    
     public ArrayList<nhanvienDTO> timKiem(int i, String s, String s1, String s2) {
        ArrayList<nhanvienDTO> nvnhom = new ArrayList<>();
        if(s1 == null || s1.isEmpty()) s1 = "0";
        if(s2 == null || s2.isEmpty()) s2 = "999999999";
        if(i==0){
            for(nhanvienDTO nv:dsnv){
                if(c1(s,nv) || c2(s,nv) || c3(s,nv) || c4 (s,nv) || c5 (s,nv)){
                    duyetNV(nvnhom,nv);
                }
            }
        }else if(i==1){
            for(nhanvienDTO nv:dsnv){
                if(c1(s,nv)){
                    duyetNV(nvnhom,nv);
                }
            }
        }else if(i==2){
            for(nhanvienDTO nv:dsnv){
                if(c2(s,nv)){
                    duyetNV(nvnhom,nv);
                }
            }
        }else if(i==3){
            for(nhanvienDTO nv:dsnv){
                if(c3(s,nv)){
                    duyetNV(nvnhom,nv);
                }
            }
        }else if(i==4){
            for(nhanvienDTO nv:dsnv){
                if(c4(s,nv)){
                    duyetNV(nvnhom,nv);
                }
            }
        }
        return nvnhom;
    }
    boolean c1(String s, nhanvienDTO nv){
        if(s != null)
            if(removeAccent(nv.ma).toLowerCase().contains(s.toLowerCase()))
                return true;
            else return false;
        return true;
                
    }//check id ma nv
    boolean c2(String s, nhanvienDTO nv){
        if(s != null)
            if(removeAccent(nv.hoten).toLowerCase().contains(s.toLowerCase()))
                return true;
            else return false;
        return true;
                
    }// check id nhan vien
    boolean c3(String s, nhanvienDTO nv){
        if(s != null)
            if(removeAccent(nv.diachi).toLowerCase().contains(s.toLowerCase()))
                return true;
            else return false;
        return true;
                
    }//check id khach hang
    boolean c4(String s, nhanvienDTO nv){
        if(s != null)
            if(removeAccent(nv.sdt).toLowerCase().contains(s.toLowerCase()))
                return true;
            else return false;
        return true;
                
    }// check id giam gia
    boolean c5(String s, nhanvienDTO nv){
        if(s != null)
            if(removeAccent(nv.email).toLowerCase().contains(s.toLowerCase()))
                return true;
            else return false;
        return true;
                
    }//check theo ngay thang
    

    
    public void openFile(String file) {
        try {
            File path = new File(file);
            Desktop.getDesktop().open(path);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    public void binexcelfunction() throws Exception {
        nhanvienGUI gui = new nhanvienGUI();

        JFileChooser jFileChooser = new JFileChooser("");
        jFileChooser.showSaveDialog(gui);
        File saveFile = jFileChooser.getSelectedFile();
        if (saveFile != null) {
            saveFile = new File(saveFile.toString() + ".xlsx");
            Workbook wb = new XSSFWorkbook();
            Sheet sheet = wb.createSheet("Customer");
            Row rowcol = sheet.createRow(0);
            for (int i = 0; i < gui.tblDSNV.getColumnCount(); i++) {
                Cell cell = rowcol.createCell(i);
                cell.setCellValue(gui.tblDSNV.getColumnName(i));
            }
            System.out.println(gui.tblDSNV.getRowCount());
            for (int j = 0; j < gui.tblDSNV.getRowCount(); j++) {
                Row row = sheet.createRow(j);
                for (int k = 0; k < gui.tblDSNV.getColumnCount(); k++) {
                    Cell cell = row.createCell(k);
                    if (gui.tblDSNV.getValueAt(j, k) != null) {
                        cell.setCellValue(gui.tblDSNV.getValueAt(j, k).toString());
                    }
                }
            }
            FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
            wb.write(out);
            wb.close();
            out.close();
            openFile(saveFile.toString());
        } else {
            JOptionPane.showMessageDialog(null, "Lỗi");
        }

    }

    public void boutexcelfunction() throws Exception {
        nhanvienGUI gui = new nhanvienGUI();
        
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
