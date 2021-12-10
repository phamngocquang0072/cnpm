/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.phieunhapDTO;
import JDBC.MySQLConnect;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author phamn
 */
public class phieunhapDAO {
    public static Connection conn=null;
    public Statement st=null;
    public ResultSet rs=null;
    public MySQLConnect connect=new MySQLConnect("localhost","root","","javasql?useUnicode=true&characterEncoding=UTF-8");
    
    public phieunhapDAO(){
        if(conn==null){
            try{
                conn=connect.getConnect();
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Không tìm thấy class "+phieunhapDAO.class.getName());
            }
        }
    }
    public ArrayList DocDSPN() throws Exception{
        ArrayList dspn=new ArrayList<phieunhapDTO>();
        try{
            
            String qry="select * from PhieuNhap";
            rs=connect.getStatement().executeQuery(qry);
            while(rs.next()){
            phieunhapDTO pn=new phieunhapDTO();
                pn.idphieunhap=rs.getString(1);
                pn.idnhacungcap=rs.getString(2);
                pn.idnhanvien=rs.getString(3); 
                pn.ngaynhap=rs.getDate(4);
                pn.tongtien=rs.getDouble(5);
                
                dspn.add(pn);
            }
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Lỗi đọc thông tin Phiếu Nhập.");
        }
        return dspn;
    }
    public void them(phieunhapDTO pn) throws Exception{
        
        try {
            java.sql.Date sqlDate = new java.sql.Date(pn.ngaynhap.getTime());
            String qry1 = "INSERT INTO phieunhap "
                    + "VALUES ('" + pn.idphieunhap + "', '" + pn.idnhacungcap + "', '" + pn.idnhanvien + "', '" +(new SimpleDateFormat("yyyy-MM-dd").format(pn.ngaynhap))+"', '" + pn.tongtien + "')";
            System.out.println(qry1);
            connect.getStatement().executeUpdate(qry1);
            JOptionPane.showMessageDialog(null, "Tao phiếu nhập thành công");

        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "Lỗi thêm thông tin nhà cung cấp");
        }
    }
}
