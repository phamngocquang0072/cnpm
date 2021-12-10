/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import DTO.chitietphieunhapDTO;
import BUS.chitietphieunhapBUS;
import JDBC.MySQLConnect;
/**
 *
 * @author admin
 */
public class chitietphieunhapDAO {
   public static Connection conn=null;
    public Statement st=null;
    public ResultSet rs=null;
    public MySQLConnect connect=new MySQLConnect("localhost","root","","javasql?useUnicode=true&characterEncoding=UTF-8");
    
    public chitietphieunhapDAO(){
        if(conn==null){
            try{
                conn=connect.getConnect();
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Không tìm thấy class "+chitietphieunhapDAO.class.getName());
            }
        }
    }
    
    public ArrayList DocDSPN() throws Exception{
        ArrayList dspn=new ArrayList<chitietphieunhapDTO>();
        try{
            
            String qry="select * from chitietphieunhap";
            rs=connect.getStatement().executeQuery(qry);
            while(rs.next()){
            chitietphieunhapDTO ctpn=new chitietphieunhapDTO();
                ctpn.idphieunhap=rs.getString(1);
                ctpn.idsanpham=rs.getString(2);
                ctpn.soluong=rs.getInt(3); 
                ctpn.dongia=rs.getDouble(4);
                ctpn.thanhtien=rs.getDouble(5);
                dspn.add(ctpn);
            }
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Lỗi đọc thông tin chi tiết phiếu Nhập.");
        }
        return dspn;
    }
    
    public ArrayList DocDSCTPN(String condition) throws Exception{
        ArrayList dsctpn=new ArrayList<chitietphieunhapDTO>();
        try{
            String qry="select * from ChiTietPhieuNhap where idphieunhap = '"+condition+"'";
            st=conn.createStatement();
            rs=st.executeQuery(qry);
            while(rs.next()){
            chitietphieunhapDTO pn=new chitietphieunhapDTO();
                pn.idphieunhap=rs.getString(1);
                pn.idsanpham=rs.getString(2);
                pn.soluong=rs.getInt(3); 
                pn.dongia=rs.getDouble(4);
                
                
                dsctpn.add(pn);
            }
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Lỗi đọc thông tin Phiếu Nhập.");
        }
        return dsctpn;
    }
    public void them(chitietphieunhapDTO ctpn) throws Exception{
        
        try {
            String qry1 = "INSERT INTO chitietphieunhap "
                    + "VALUES ('" + ctpn.idphieunhap + "', '" + ctpn.idsanpham + "', '" + ctpn.soluong + "', '" + ctpn.dongia + "', '"+ ctpn.thanhtien + "')";
            System.out.println(qry1);
            connect.getStatement().executeUpdate(qry1);

        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "Lỗi thêm thông tin nhà cung cấp");
        }
    }
}
