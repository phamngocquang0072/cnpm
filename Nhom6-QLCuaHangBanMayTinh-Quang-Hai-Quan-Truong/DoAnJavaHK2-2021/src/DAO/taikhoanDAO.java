/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.nhanvienDTO;
import DTO.hoadonDTO;
import DTO.taikhoanDTO;
import GUI.taikhoanGUI;
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
import DAO.taikhoanDAO;
/**
 *
 * @author phamn
 */
public class taikhoanDAO {

    MySQLConnect connect;

    public taikhoanDAO() throws Exception {
        connect = new MySQLConnect("localhost", "root", "", "javasql");

    }

    public ArrayList docDSTK() throws Exception {
        ArrayList dstk = new ArrayList< taikhoanDTO>();
        try {

            String qry = "select * from taikhoan";
            ResultSet rs = connect.excuteQuery(qry);

            while (rs.next()) {
                taikhoanDTO tk = new taikhoanDTO();
                tk.taikhoan = rs.getString(1);
                tk.matkhau = rs.getString(2);
                tk.idnhanvien = rs.getString(3);
                tk.idquyen = rs.getString(4);
                tk.hinh = rs.getString(5);
                dstk.add(tk);
                
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc database");
        }
        return dstk;

    }

    public void them(taikhoanDTO tk) {
        try {
            
            String qry = "INSERT INTO taikhoan "
                    + "VALUES ('" + tk.taikhoan + "', '" + tk.matkhau + "', '" + tk.idnhanvien + "', '" + tk.idquyen + "', '" + tk.hinh + "')";
            System.out.println(qry);
            connect.getStatement().executeUpdate(qry);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi thêm thông tin tài khoản");
        }
    }

    public void xoa(String taikhoan) {
        try {

            String qry = "DELETE FROM taikhoan where taikhoan = '" + taikhoan + "'";
            connect.getStatement().executeUpdate(qry);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi xóa thông tin nhà cung cấp");
        }
    }

    public void sua(taikhoanDTO tk, String taikhoan) {
        String qry = "UPDATE taikhoan SET";
            qry += " taikhoan = '" + tk.taikhoan + "',";
            qry += " matkhau = '" + tk.matkhau + "',";
            qry += " idnhanvien = '" +tk.idnhanvien + "',";
            qry += " idquyen = '" + tk.idquyen +  "',";
            qry += "hinh = '"  +tk.hinh+"'" +" where taikhoan = '" + taikhoan + "'";
            
            System.out.println(qry);
        try {
            
            System.out.println(taikhoan + "DAO");
            System.out.println(qry);
            connect.getStatement().executeUpdate(qry);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi sửa thông tin nhà cung cấp");
        }
    }

    public ArrayList fillcombo() {
         ArrayList dsidnv = new ArrayList<nhanvienDTO>();
        try {
            
            String sql = "select * from nhanvien";
            ResultSet rs = connect.excuteQuery(sql);
            
            while(rs.next()){
                 nhanvienDTO id = new nhanvienDTO();
                id.ma = rs.getString(1);
                dsidnv.add(id);
                
                
                }
        } catch (Exception e) {
        }
        return dsidnv;
    }
    
    public void acb(taikhoanDTO tk) throws Exception{
        try{
        String q = "INSERT INTO taikhoan" + "VALUES('"+tk.getTaikhoan()+"','"+tk.getMatkhau()+"','"+tk.getIdnhanvien()+"','"+tk.idquyen+"','"+tk.hinh+"')";
        connect.getStatement().executeUpdate(q);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Lỗi thêm thông tin tài khoản");
        }
        
    }
    public void bcd(taikhoanDTO tk, String taikhoan){
        String q = "UPDATE taikhoan SET";
                q+="taikhoan = '"+tk.getTaikhoan()+"',";
                q+="matkhau = '"+tk.getMatkhau()+"',";
                q+="idnhanvien = '"+tk.getIdnhanvien()+"',";
                q+="idquyen = '"+tk.getIdquyen()+"',";
               
                
    }

}
