/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.chitiethoadonDTO;
import DTO.hoadonDTO;
import JDBC.MyConnectUnit;
import JDBC.MySQLConnect;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author phamn
 */
public class chitiethoadonDAO {

    MySQLConnect connect;

    public chitiethoadonDAO() {
        connect = new MySQLConnect("localhost", "root", "", "javasql");

    }

    public ArrayList docCTHD(String Condition) throws Exception {
        ArrayList dscthd = new ArrayList<chitiethoadonDTO>();
        try {
            String qry = "select * from chitiethoadon where idhoadon = '" + Condition + "'";
            ResultSet rs = connect.excuteQuery(qry);
            while (rs.next()) {
                chitiethoadonDTO cthd = new chitiethoadonDTO();
                cthd.idhoadon = rs.getString(1);
                cthd.idsanpham = rs.getString(2);
                cthd.soluong = rs.getInt(3);
                cthd.dongia = rs.getDouble(4);
                cthd.thanhtien = rs.getDouble(5);

                dscthd.add(cthd);
            }

        } catch (java.lang.ClassNotFoundException e) {
            throw new Exception("Không đọc được");
        }
        return dscthd;
    }

    public ArrayList docCTHD1() throws Exception {
        ArrayList dscthd = new ArrayList<chitiethoadonDTO>();
        try {
            String qry = "select * from chitiethoadon ";
            ResultSet rs = connect.excuteQuery(qry);
            while (rs.next()) {
                chitiethoadonDTO cthd = new chitiethoadonDTO();
                cthd.idhoadon = rs.getString(1);
                cthd.idsanpham = rs.getString(2);
                cthd.soluong = rs.getInt(3);
                cthd.dongia = rs.getDouble(4);
                cthd.thanhtien = rs.getDouble(5);

                dscthd.add(cthd);
            }

        } catch (java.lang.ClassNotFoundException e) {
            throw new Exception("Không đọc được");
        }
        return dscthd;
    }
    public void them(chitiethoadonDTO cthd) throws Exception{
        String qry="Insert into chitiethoadon Values(";
        qry=qry+"'"+cthd.idhoadon+"'";
        qry=qry+",'"+cthd.idsanpham+"'";
        qry=qry+",'"+cthd.soluong+"'";
        qry=qry+",'"+cthd.dongia+"'";
        qry=qry+",'"+cthd.thanhtien+"'";
        qry=qry+")";
        connect.getStatement().executeUpdate(qry);
    }

    
}
