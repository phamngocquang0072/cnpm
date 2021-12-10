/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import static BUS.chitiethoadonBUS.dscthd;
import static BUS.khachhangBUS.removeAccent;
import static BUS.thongkeBUS.dskh;
import static BUS.thongkeBUS.dsnv;
import static BUS.thongkeBUS.dssp;
import DAO.chitiethoadonDAO;
import DAO.hoadonDAO;
import DAO.khachhangDAO;
import DAO.khuyenmaiDAO;
import DAO.nhanvienDAO;
import DAO.sanphamDAO;
import DTO.chitiethoadonDTO;
import DTO.hoadonDTO;
import DTO.hoadonDTO;
import DTO.khachhangDTO;
import DTO.khuyenmaiDTO;
import DTO.nhanvienDTO;
import DTO.sanphamDTO;
import GUI.hoadonGUI;
import JDBC.MySQLConnect;
import com.itextpdf.text.BadElementException;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.BaseColor;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.AcroFields.Item;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.FontSelector;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfAction;
import com.itextpdf.text.pdf.PdfArray;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfFormField;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfNumber;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PushbuttonField;
import com.itextpdf.text.pdf.TextField;

import java.awt.Color;
import java.awt.Desktop;
import static java.awt.Frame.NORMAL;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author phamn
 */
public class hoadonBUS {

    public static ArrayList<sanphamDTO> dssp;
    public static ArrayList<khachhangDTO> dskh;
    public static ArrayList<nhanvienDTO> dsnv;
    public static ArrayList<khuyenmaiDTO> dskm;
    MySQLConnect connect;
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 28,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);
    hoadonGUI gui;

    public static ArrayList<hoadonDTO> dshd;

    public hoadonBUS() {
        connect = new MySQLConnect("localhost", "root", "", "javasql");
    }

    public void docDSHD() throws Exception {
        hoadonDAO data = new hoadonDAO();
        if (dshd == null) {
            dshd = new ArrayList<hoadonDTO>();
        }
        dshd = data.docDSHD();
    }

    public void them(hoadonDTO hd) throws Exception {
        hoadonDAO data = new hoadonDAO();
//        int check=0;
//        if(dshd==null)
//            return false;
//        else{
//            for(int i=0;i<dshd.size();i++){
//                if(hd.idhoadon.compareTo(dshd.get(i).idhoadon)==0){
//                    check++;
//                }
//            }
//            if(check==0){
        data.them(hd);

        dshd.add(hd);

//                return true;
//            }
//            else{
//                JOptionPane.showMessageDialog(null,"Mã hóa đơn "+hd.idhoadon+" đã bị trùng, vui lòng nhập mã khác!");
//                return false;
//            }
//        }
    }

    public void para(String condition, String mnv, String mkh, String mgg, String ngay) throws Exception {

        String path = "";

        JFileChooser j = new JFileChooser("");
        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int x = j.showSaveDialog(gui);
        if (x == JFileChooser.APPROVE_OPTION) {
            path = j.getSelectedFile().getPath();
        }

        Document doc = new Document();
        PdfWriter.getInstance(doc, new FileOutputStream(path + ".pdf"));

        doc.open();
        addMetaData(doc);
        addTitlePage(doc, mnv);
        addContent(doc, condition, mnv, mkh, mgg, ngay);

        doc.close();

    }

    private void addMetaData(Document document) {
        document.addTitle("My first PDF");
        document.addSubject("Using iText");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("Lars Vogel");
        document.addCreator("Lars Vogel");
    }

    private void addTitlePage(Document document, String mnv) throws DocumentException, Exception {
        Paragraph preface = new Paragraph();
        // We add one empty line
        addEmptyLine(preface, 1);
        // Lets write a big header
        preface.add(new Paragraph("INVOICE", catFont));

        addEmptyLine(preface, 1);
        // Will create: Report generated by: _name, _date
        String tennv = "";
        nhanvienDAO data2 = new nhanvienDAO();
        if (dsnv == null) {
            dsnv = new ArrayList<nhanvienDTO>();
        }
        dsnv = data2.docDSNV();
        for (int i = 0; i < dsnv.size(); i++) {
                if(dsnv.get(i).getMa().equals(mnv)){
                    tennv = dsnv.get(i).hoten;
                }
            }
        preface.add(new Paragraph(
                "Report generated by: " + removeAccent(tennv) + ", " + new Date(), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                smallBold));
        addEmptyLine(preface, 1);

        document.add(preface);
        // Start a new page

    }
    

    private static void createTable(Section subCatPart)
            throws BadElementException {
        PdfPTable table = new PdfPTable(3);

        // t.setBorderColor(BaseColor.GRAY);
        // t.setPadding(4);
        // t.setSpacing(4);
        // t.setBorderWidth(1);
        PdfPCell c1 = new PdfPCell(new Phrase("Table Header 1"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Table Header 2"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Table Header 3"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);

        table.addCell("1.0");
        table.addCell("1.1");
        table.addCell("1.2");
        table.addCell("2.1");
        table.addCell("2.2");
        table.addCell("2.3");

        subCatPart.add(table);

    }

    private static void createList(Section subCatPart) {
        List list = new List(true, false, 10);
        list.add(new ListItem("First point"));
        list.add(new ListItem("Second point"));
        list.add(new ListItem("Third point"));
        subCatPart.add(list);
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    private void addContent(Document document, String condition, String mnv, String mkh, String mgg, String ngay) throws DocumentException, Exception {
        Paragraph preface = new Paragraph();
        // We add one empty line
        khachhangDAO data3 = new khachhangDAO();
        if (dskh == null) {
            dskh = new ArrayList<khachhangDTO>();
        }
        dskh = data3.docDSKH();
        String tenkh = "";
        for (int i = 0; i < dskh.size(); i++) {
                if(dskh.get(i).idkhachhang.equals(mkh)){
                    tenkh = dskh.get(i).tenkhachhang;
                }
            }
        
        addEmptyLine(preface, 2);
        preface.add(new Paragraph(
                "Customer: " + removeAccent(tenkh),
                smallBold));
        addEmptyLine(preface, 1);
        preface.add(new Paragraph(
                "Invoice id: " + condition,
                smallBold));
        addEmptyLine(preface, 1);
        preface.add(new Paragraph(
                "Date: " + ngay,
                smallBold));
        document.add(preface);
        float[] columnWidths = new float[]{50f, 15f, 30.5f, 30.5f};

        PdfPTable tbl = new PdfPTable(4);
        tbl.setWidths(columnWidths);
        tbl.setWidthPercentage(110f);

        tbl.addCell("Product's name");
        tbl.addCell("Amount");
        tbl.addCell("Price");
        tbl.addCell("Total");

        tbl.setPaddingTop(20f);
        tbl.setSpacingAfter(20f);
        tbl.setSpacingBefore(20f);

        double total = 0;
        DecimalFormat df = new DecimalFormat("#.####");
        df.setRoundingMode(RoundingMode.CEILING);
        sanphamDAO data1 = new sanphamDAO();
        if (dssp == null) {
            dssp = new ArrayList<sanphamDTO>();
        }
        chitiethoadonDAO data = new chitiethoadonDAO();
        if (dscthd == null) {
            dscthd = new ArrayList<chitiethoadonDTO>();
        }
        dscthd = data.docCTHD(condition);
        System.out.println(condition + "BUS PARA");
        for (chitiethoadonDTO hd : chitiethoadonBUS.dscthd) {

            double thanht = 0;
            String tensanpham = "";
            String soluong = String.valueOf(hd.soluong);
            String dongia = String.valueOf(df.format(hd.dongia));
            thanht = hd.dongia * hd.soluong;
            String thanhtien = String.valueOf(df.format(thanht));

            dssp = data1.docDSSP();
            for (int i = 0; i < dssp.size(); i++) {
                if(dssp.get(i).getIdsanpham().equals(hd.idsanpham)){
                    tensanpham = dssp.get(i).tensanpham;
                }
            }

            tbl.addCell(tensanpham);
            tbl.addCell(soluong);
            tbl.addCell(dongia);
            tbl.addCell(thanhtien);
            total += thanht;

        }
        document.add(tbl);
        Paragraph preface1 = new Paragraph();
        // We add one empty line

        addEmptyLine(preface, 3);
        preface1.add(new Paragraph(
                "Total money: " + df.format(total) + " VND ",
                smallBold));
        khuyenmaiDAO data4 = new khuyenmaiDAO();
        if (dskm == null) {
            dskm = new ArrayList<khuyenmaiDTO>();
        }
        dskm = data4.docDSKM();
        Float sokm = 0.0f;
        for (int i = 0; i < dskm.size(); i++) {
                if(dskm.get(i).idkm.equals(mgg)){
                    sokm = dskm.get(i).phantramkm;
                }
            }
        String skm = String.valueOf(sokm);
        double dkm = Double.parseDouble(skm);
        addEmptyLine(preface, 1);
        preface1.add(new Paragraph(
                "Discount: " + skm + "% ",
                smallBold));
        preface1.add(new Paragraph(
                "Total payment: " + df.format(total-(total*dkm*0.01)) + " VND ",
                smallBold));
        document.add(preface1);

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
        gui = new hoadonGUI();

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

    public void boutexcelfunction() throws Exception {
        gui = new hoadonGUI();

    }

    public ArrayList<hoadonDTO> timKiem(int i, String s, String s1, String s2, String s3, String s4) throws ParseException {
        System.out.println("chay ne tim kiem");
        ArrayList<hoadonDTO> nhomhd = new ArrayList<>();
        System.out.println(s1 + "----" + s2 + "||||" + s3 + "----" + s4);
        if (s1 == null || s1.isEmpty()) {
            s1 = "0000-00-00";
        }
        if (s2 == null || s2.isEmpty()) {
            s2 = "3999-12-31";
        }
        if (s3 == null || s3.isEmpty()) {
            s3 = "0";
        }
        if (s4 == null || s4.isEmpty()) {
            s4 = "999999999";
        }

        if (i == 0) {
            for (hoadonDTO hd : dshd) {
                if ((c1(s, hd) || c2(s, hd) || c3(s, hd) || c4(s, hd))) {
                    duyetHD(nhomhd, hd);
                }
            }
        } else if (i == 1) {
            for (hoadonDTO hd : dshd) {
                if (c1(s, hd)) {
                    duyetHD(nhomhd, hd);
                }
            }
        } else if (i == 2) {
            for (hoadonDTO hd : dshd) {
                if (c2(s, hd)) {
                    duyetHD(nhomhd, hd);
                }
            }
        } else if (i == 3) {
            for (hoadonDTO hd : dshd) {
                if (c3(s, hd)) {
                    duyetHD(nhomhd, hd);
                }
            }
        } else if (i == 4) {
            for (hoadonDTO hd : dshd) {
                if (c4(s, hd)) {
                    duyetHD(nhomhd, hd);
                }
            }
        } else if (i == 5) {
            for (hoadonDTO hd : dshd) {
                System.out.println("chay ne i5");
                if (c5(s1, s2, hd) && c6(s3, s4, hd)) {
                    duyetHD(nhomhd, hd);
                }
            }
        } else if (i == 6) {
            System.out.println("CHAY NEEEEEEE i6");
            for (hoadonDTO hd : dshd) {
                if (c5(s1, s2, hd)) {
                    duyetHD(nhomhd, hd);
                }
            }
        } else if (i == 7) {
            for (hoadonDTO hd : dshd) {
                if (c6(s3, s4, hd)) {
                    duyetHD(nhomhd, hd);
                }
            }
        }
        return nhomhd;
    }

    boolean c1(String s, hoadonDTO hd) {
        if (s != null) {
            if (removeAccent(hd.idhoadon).toLowerCase().contains(s.toLowerCase())) {
                return true;
            } else {
                return false;
            }
        }
        return true;

    }// check id hoa don

    boolean c2(String s, hoadonDTO hd) {
        if (s != null) {
            if (removeAccent(hd.idnhanvien).toLowerCase().contains(s.toLowerCase())) {
                return true;
            } else {
                return false;
            }
        }
        return true;

    }// check id nhan vien

    boolean c3(String s, hoadonDTO hd) {
        if (s != null) {
            if (removeAccent(hd.idkhachhang).toLowerCase().contains(s.toLowerCase())) {
                return true;
            } else {
                return false;
            }
        }
        return true;

    }//check id khach hang

    boolean c4(String s, hoadonDTO hd) {
        if (s != null) {
            if (removeAccent(hd.idgiamgia).toLowerCase().contains(s.toLowerCase())) {
                return true;
            } else {
                return false;
            }
        }
        return true;

    }// check id giam gia

    boolean c5(String s1, String s2, hoadonDTO hd) throws ParseException {
        System.out.println("CHAY NEEEEEEE C5");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse(s1);
        Date date2 = sdf.parse(s2);
        if (s1 != null && s2 != null) {
            if (date1.compareTo(hd.ngaylap) <= 0 && hd.ngaylap.compareTo(date2) <= 0) {
                return true;
            }
        }
        return false;

    }//check theo khoang so luong

    boolean c6(String s1, String s2, hoadonDTO hd) throws ParseException {
        if (isNumeric(s1) && s1 != null && isNumeric(s2) && s2 != null) {
            if (hd.tonghoadon >= Integer.parseInt(s1) && hd.tonghoadon <= Integer.parseInt(s2)) {
                return true;
            }
        }
        return false;
    }//check theo khoang don gia

    private boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    private boolean isAlpha(String str) {
        for (char c : str.toCharArray()) {
            if (c == ' ') {
            } else if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    private void duyetHD(ArrayList nhomhd, hoadonDTO hd) {
        hoadonDTO hd1 = new hoadonDTO();
        hd1.setIdhoadon(hd.idhoadon);
        hd1.setIdnhanvien(hd.idnhanvien);
        hd1.setIdkhachhang(hd.idkhachhang);
        hd1.setIdgiamgia(hd.idgiamgia);
        hd1.setNgaylap(hd.ngaylap);
        hd1.setTonghoadon(hd.tonghoadon);
        nhomhd.add(hd1);
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
