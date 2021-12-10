/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.hoadonDTO;
import DTO.nhacungcapDTO;
import JDBC.MyConnectUnit;
import JDBC.MySQLConnect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author phamn
 */
public class nhacungcapDAO {

    MySQLConnect connect;

    public nhacungcapDAO() throws Exception {
        connect = new MySQLConnect("localhost", "root", "", "javasql");

    }

    public ArrayList docDSNCC() throws Exception {
        ArrayList dsncc = new ArrayList< nhacungcapDTO>();
        try {

            String qry = "select * from nhacungcap";
            ResultSet rs = connect.excuteQuery(qry);

            while (rs.next()) {
                nhacungcapDTO ncc = new nhacungcapDTO();
                ncc.idnhacungcap = rs.getString(1);
                ncc.tennhacungcap = rs.getString(2);
                ncc.diachi = rs.getString(3);
                ncc.sdt = rs.getString(4);
                ncc.gmail = rs.getString(5);
                dsncc.add(ncc);
                System.out.println(ncc);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc database");
        }
        return dsncc;

    }

    public void them(nhacungcapDTO ncc) {
        try {
            String qry = "INSERT INTO nhacungcap "
                    + "VALUES ('" + ncc.idnhacungcap + "', '" + ncc.tennhacungcap + "', '" + ncc.diachi + "', '" + ncc.sdt + "', '" + ncc.gmail + "')";
            connect.getStatement().executeUpdate(qry);

        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "Lỗi thêm thông tin nhà cung cấp");
        }
    }

    public void xoa(String idnhacungcap) {
        try {

            String qry = "DELETE FROM nhacungcap where idnhacungcap = '" + idnhacungcap + "'";
            connect.getStatement().executeUpdate(qry);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi xóa thông tin nhà cung cấp");
        }
    }

    public void sua(nhacungcapDTO ncc) {
        String qry1 = "UPDATE nhacungcap SET(";
            qry1 += " idnhacungcap = '" + ncc.idnhacungcap + "',";
            qry1 += " tennhacungcap = '" + ncc.tennhacungcap + "',";
            qry1 += " sdt = '" + ncc.diachi + "',";
            qry1 += " gmail = '" + ncc.sdt + "',";
            qry1 += "diachi = '" + ncc.gmail + "'" + " where idnhacungcap = '" + ncc.idnhacungcap + "'";;
            qry1 += ")";
            System.out.println(qry1);
        try {
            String qry = "UPDATE nhacungcap SET";
            qry += " idnhacungcap = '" + ncc.idnhacungcap + "',";
            qry += " tennhacungcap = '" + ncc.tennhacungcap + "',";
            qry += " sdt = '" + ncc.sdt  + "',";
            qry += " gmail = '" +ncc.gmail + "',";
            qry += "diachi = '" + ncc.diachi + "'" + " where idnhacungcap = '" + ncc.idnhacungcap + "'";;
            
            connect.getStatement().executeUpdate(qry);
            System.out.println(ncc.idnhacungcap + "DAO");
            System.out.println(qry);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi sửa thông tin nhà cung cấp");
        }
    }

}
