package DAO;

import DTO.hoadonDTO;
import JDBC.MyConnectUnit;
import JDBC.MySQLConnect;
import java.util.ArrayList;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author phamn
 */
public class hoadonDAO {
    
    MySQLConnect connect;
    public hoadonDAO(){
        
       connect = new MySQLConnect("localhost", "root", "", "javasql");
    }
    public ArrayList docDSHD() throws Exception{
        ArrayList dshd = new ArrayList<hoadonDTO>();
        try {
            
            String qry = "select * from hoadon";
            ResultSet rs = connect.excuteQuery(qry);
            while(rs.next()){
                hoadonDTO hd = new hoadonDTO();
                hd.idhoadon = rs.getString(1);
                hd.idkhachhang = rs.getString(3);
                hd.idnhanvien = rs.getString(2);
                hd.idgiamgia = rs.getString(4);
                hd.ngaylap = rs.getDate(5);
                hd.tonghoadon = rs.getDouble(6);
                
                dshd.add(hd);
            }
           
            
        } catch (java.lang.ClassNotFoundException e) {
             JOptionPane.showMessageDialog(null, "Lỗi đọc database");
        }
        return dshd;
    }
    public void them(hoadonDTO sp) throws Exception{
        
        try {
            java.sql.Date sqlDate = new java.sql.Date(sp.ngaylap.getTime());
            String qry1 = "INSERT INTO hoadon "
                    + "VALUES ('" + sp.idhoadon + "', '" + sp.idnhanvien + "', '" + sp.idkhachhang + "', '" + sp.idgiamgia + "', '"+(new SimpleDateFormat("yyyy-MM-dd").format(sp.ngaylap))+"', '" + sp.tonghoadon + "')";
            System.out.println(qry1);
            connect.getStatement().executeUpdate(qry1);
            JOptionPane.showMessageDialog(null, "Tạo hóa đơn thành công");

        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "Lỗi thêm thông tin nhà cung cấp");
        }
    }
    
    
}
