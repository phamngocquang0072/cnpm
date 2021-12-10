/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;


import DAO.chitiethoadonDAO;
import DAO.hoadonDAO;
import DAO.khachhangDAO;
import DAO.nhacungcapDAO;
import DAO.nhanvienDAO;
import DAO.sanphamDAO;
import DTO.chitiethoadonDTO;
import DTO.hoadonDTO;
import DTO.khachhangDTO;
import DTO.nhacungcapDTO;
import DTO.nhanvienDTO;
import DTO.sanphamDTO;
import DTO.taikhoanDTO;
import GUI.thongkeGUI;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author phamn
 */
public class thongkeBUS {

    public static ArrayList<hoadonDTO> dshd, dshd3;
    public static ArrayList<nhacungcapDTO> dsncc;
    public static ArrayList<sanphamDTO> dssp;
    public static ArrayList<khachhangDTO> dskh;
    public static ArrayList<sanphamDTO> dstsp;
    public static ArrayList<chitiethoadonDTO> dscthd;
    public static ArrayList<nhanvienDTO> dsnv;
    public Number tongthang11;
    DecimalFormat df = new DecimalFormat("#.####");
    public thongkeBUS() throws Exception {
        docnam();
        
            
        
    }

    public double docdoanhthu() throws Exception {
        double tong = 15.36;
        hoadonDAO data = new hoadonDAO();
        if (dshd == null) {
            dshd = new ArrayList<hoadonDTO>();
        }
        dshd = data.docDSHD();
        for (int i = 0; i < dshd.size(); i++) {
            tong += dshd.get(i).getTonghoadon();
        }
        double tong1 = Math.round(tong * 100) / 100;

        return tong1;
    }

    public int docncc() throws Exception {
        int tong = 0;
        nhacungcapDAO data = new nhacungcapDAO();
        if (dsncc == null) {
            dsncc = new ArrayList<nhacungcapDTO>();
        }
        dsncc = data.docDSNCC();
        tong = dsncc.size();

        return tong;
    }

    public int docsp() throws Exception {
        int tong = 0;
        sanphamDAO data = new sanphamDAO();
        if (dssp == null) {
            dssp = new ArrayList<sanphamDTO>();
        }
        dssp = data.docDSSP();
        tong = dssp.size();

        return tong;
    }
    public int docnv() throws Exception {
        int tong = 0;
        nhanvienDAO data = new nhanvienDAO();
        if (dsnv == null) {
            dsnv = new ArrayList<nhanvienDTO>();
        }
        dsnv = data.docDSNV();
        tong = dsnv.size();

        return tong;
    }

    public int dockh() throws Exception {
        int tong = 0;
        khachhangDAO data = new khachhangDAO();
        if (dskh == null) {
            dskh = new ArrayList<khachhangDTO>();
        }
        dskh = data.docDSKH();
        tong = dskh.size();

        return tong;
    }

    public double tongthang5() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse(docnam()+"-5-1");
        Date date2 = sdf.parse(docnam()+"-5-31");
        double tong = 15.36;
        hoadonDAO data = new hoadonDAO();
        if (dshd == null) {
            dshd = new ArrayList<hoadonDTO>();
        }
        dshd = data.docDSHD();
        for (int i = 0; i < dshd.size(); i++) {
            Date date = dshd.get(i).getNgaylap();
            if (date1.compareTo(date) <= 0 && date.compareTo(date2) <= 0) {
                tong += dshd.get(i).getTonghoadon();
            }
        }
        df.setRoundingMode(RoundingMode.CEILING);
        double tong1 = Math.round(tong * 100) / 100;
        
        
        return tong1;
    }
    
    public Number tongthang1() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse(docnam()+"-1-1");
        Date date2 = sdf.parse(docnam()+"-1-31");
        double tong = 15.36;
        hoadonDAO data = new hoadonDAO();
        if (dshd == null) {
            dshd = new ArrayList<hoadonDTO>();
        }
        dshd = data.docDSHD();
        for (int i = 0; i < dshd.size(); i++) {
            Date date = dshd.get(i).getNgaylap();
            if (date1.compareTo(date) <= 0 && date.compareTo(date2) <= 0) {
                tong += dshd.get(i).getTonghoadon();
            }
        }
        
        double tong1 = Math.round(tong * 100) / 100;

        return tong1;
    }

    public Number tongthang2() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse(docnam()+"-2-1");
        Date date2 = sdf.parse(docnam()+"-2-28");
        double tong = 15.36;
        hoadonDAO data = new hoadonDAO();
        if (dshd == null) {
            dshd = new ArrayList<hoadonDTO>();
        }
        dshd = data.docDSHD();
        for (int i = 0; i < dshd.size(); i++) {
            Date date = dshd.get(i).getNgaylap();
            if (date1.compareTo(date) <= 0 && date.compareTo(date2) <= 0) {
                tong += dshd.get(i).getTonghoadon();
            }
        }
        double tong1 = Math.round(tong * 100) / 100;

        return tong1;
    }

    public Number tongthang3() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse(docnam()+"-3-1");
        Date date2 = sdf.parse(docnam()+"-3-31");
        double tong = 15.36;
        hoadonDAO data = new hoadonDAO();
        if (dshd == null) {
            dshd = new ArrayList<hoadonDTO>();
        }
        dshd = data.docDSHD();
        for (int i = 0; i < dshd.size(); i++) {
            Date date = dshd.get(i).getNgaylap();
            if (date1.compareTo(date) <= 0 && date.compareTo(date2) <= 0) {
                tong += dshd.get(i).getTonghoadon();
            }
        }
        double tong1 = Math.round(tong * 100) / 100;

        return tong1;
    }

    public Number tongthang4() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse(docnam()+"-4-1");
        Date date2 = sdf.parse(docnam()+"-4-30");
        double tong = 15.36;
        hoadonDAO data = new hoadonDAO();
        if (dshd == null) {
            dshd = new ArrayList<hoadonDTO>();
        }
        dshd = data.docDSHD();
        for (int i = 0; i < dshd.size(); i++) {
            Date date = dshd.get(i).getNgaylap();
            if (date1.compareTo(date) <= 0 && date.compareTo(date2) <= 0) {
                tong += dshd.get(i).getTonghoadon();
            }
        }
        double tong1 = Math.round(tong * 100) / 100;

        return tong1;
    }

    public Number tongthang6() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse(docnam()+"-6-1");
        Date date2 = sdf.parse(docnam()+"-6-31");
        double tong = 15.36;
        hoadonDAO data = new hoadonDAO();
        if (dshd == null) {
            dshd = new ArrayList<hoadonDTO>();
        }
        dshd = data.docDSHD();
        for (int i = 0; i < dshd.size(); i++) {
            Date date = dshd.get(i).getNgaylap();
            if (date1.compareTo(date) <= 0 && date.compareTo(date2) <= 0) {
                tong += dshd.get(i).getTonghoadon();
            }
        }
        double tong1 = Math.round(tong * 100) / 100;

        return tong1;
    }

    public Number tongthang7() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse(docnam()+"-7-1");
        Date date2 = sdf.parse(docnam()+"-7-30");
        double tong = 15.36;
        hoadonDAO data = new hoadonDAO();
        if (dshd == null) {
            dshd = new ArrayList<hoadonDTO>();
        }
        dshd = data.docDSHD();
        for (int i = 0; i < dshd.size(); i++) {
            Date date = dshd.get(i).getNgaylap();
            if (date1.compareTo(date) <= 0 && date.compareTo(date2) <= 0) {
                tong += dshd.get(i).getTonghoadon();
            }
        }
        double tong1 = Math.round(tong * 100) / 100;

        return tong1;
    }

    public Number tongthang8() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse(docnam()+"-8-1");
        Date date2 = sdf.parse(docnam()+"-8-31");
        double tong = 15.36;
        hoadonDAO data = new hoadonDAO();
        if (dshd == null) {
            dshd = new ArrayList<hoadonDTO>();
        }
        dshd = data.docDSHD();
        for (int i = 0; i < dshd.size(); i++) {
            Date date = dshd.get(i).getNgaylap();
            if (date1.compareTo(date) <= 0 && date.compareTo(date2) <= 0) {
                tong += dshd.get(i).getTonghoadon();
            }
        }
        double tong1 = Math.round(tong * 100) / 100;

        return tong1;
    }

    public Number tongthang9() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse(docnam()+"-9-1");
        Date date2 = sdf.parse(docnam()+"-9-30");
        double tong = 15.36;
        hoadonDAO data = new hoadonDAO();
        if (dshd == null) {
            dshd = new ArrayList<hoadonDTO>();
        }
        dshd = data.docDSHD();
        for (int i = 0; i < dshd.size(); i++) {
            Date date = dshd.get(i).getNgaylap();
            if (date1.compareTo(date) <= 0 && date.compareTo(date2) <= 0) {
                tong += dshd.get(i).getTonghoadon();
            }
        }
        double tong1 = Math.round(tong * 100) / 100;

        return tong1;
    }

    public Number tongthang10() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse(docnam()+"-10-1");
        Date date2 = sdf.parse(docnam()+"-10-31");
        double tong = 15.36;
        hoadonDAO data = new hoadonDAO();
        if (dshd == null) {
            dshd = new ArrayList<hoadonDTO>();
        }
        dshd = data.docDSHD();
        for (int i = 0; i < dshd.size(); i++) {
            Date date = dshd.get(i).getNgaylap();
            if (date1.compareTo(date) <= 0 && date.compareTo(date2) <= 0) {
                tong += dshd.get(i).getTonghoadon();
            }
        }
        double tong1 = Math.round(tong * 100) / 100;

        return tong1;
    }

    public Number tongthang11() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse(docnam()+"-11-1");
        Date date2 = sdf.parse(docnam()+"-11-30");
        double tong = 15.36;
        hoadonDAO data = new hoadonDAO();
        if (dshd == null) {
            dshd = new ArrayList<hoadonDTO>();
        }
        dshd = data.docDSHD();
        for (int i = 0; i < dshd.size(); i++) {
            Date date = dshd.get(i).getNgaylap();
            if (date1.compareTo(date) <= 0 && date.compareTo(date2) <= 0) {
                tong += dshd.get(i).getTonghoadon();
            }
        }
        double tong1 = Math.round(tong * 100) / 100;

        return tong1;
    }

    public Number tongthang12() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse(docnam()+"-12-1");
        Date date2 = sdf.parse(docnam()+"-12-31");
        double tong = 15.36;
        hoadonDAO data = new hoadonDAO();
        if (dshd == null) {
            dshd = new ArrayList<hoadonDTO>();
        }
        dshd = data.docDSHD();
        for (int i = 0; i < dshd.size(); i++) {
            Date date = dshd.get(i).getNgaylap();
            if (date1.compareTo(date) <= 0 && date.compareTo(date2) <= 0) {
                tong += dshd.get(i).getTonghoadon();
            }
        }
        double tong1 = Math.round(tong * 100) / 100;

        return tong1;
    }

    public void tongsp() throws Exception {

        sanphamDAO data2 = new sanphamDAO();
        chitiethoadonDAO data1 = new chitiethoadonDAO();
        hoadonDAO data3 = new hoadonDAO();
        if (dstsp == null) {
            dstsp = new ArrayList<sanphamDTO>();
        }
        dstsp = data2.docDSSP();
        
        
        
       

        

    }

    public String docnam() {
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        DateFormat fm = new SimpleDateFormat("yyyy");  
        String dateFormat = fm.format(date);
        
        return dateFormat;
    }
}
