
package DAO;

import DTO.khuyenmaiDTO;
import JDBC.MySQLConnect;
import java.awt.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class khuyenmaiDAO {
    String user="root";
    String pass ="";
    String url="jdbc:mysql://localhost:3306/JAVASQL?useUnicode=true&characterEncoding=UTF-8";
    public Statement st=null;
    public ResultSet rs=null;
    public static Connection conn=null;
    public JTextField tfsearch;
    
    public khuyenmaiDAO(){
        if (conn == null){
            try 
            {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(url,user,pass);
            }
            catch (ClassNotFoundException ex){
                JOptionPane.showMessageDialog(null, "khong tim thay class" );               
            }
            catch(SQLException e){
                JOptionPane.showMessageDialog(null, " khong tim thay sql");
            }
        }
    }
    
    
    public ArrayList docDSKM(){
        ArrayList dskm = new ArrayList<khuyenmaiDTO>();
        try {
            String qry ="select * from khuyenmai";
            st=conn.createStatement();
            rs=st.executeQuery(qry);
                while (rs.next()){ 
                    khuyenmaiDTO km = new khuyenmaiDTO(); 
                    km.idkm=rs.getString(1); 
                    km.tenkm=rs.getString(2); 
                    km.dieukienkm=rs.getFloat(3);
                    km.phantramkm=rs.getFloat(4); 
                    km.ngaybatdau=rs.getDate(5); 
                    km.ngayketthuc=rs.getDate(6); 
                    dskm.add(km);                    
                }
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, " loi doc data");
            }
        return dskm;
       
    }
    public void them(khuyenmaiDTO km) {
        try {
            String qry = "Insert into khuyenmai VALUES (";
            qry =qry+ "'"+km.idkm+"'";
            qry =qry+",'"+km.tenkm+"'";
            qry =qry+",'"+km.dieukienkm+"'";
            qry =qry+",'"+km.phantramkm+"'";
            qry =qry+",'"+km.ngaybatdau+"'";
            qry =qry+",'"+km.ngayketthuc+"'";
            qry =qry+")";
            st=conn.createStatement();
            st.executeUpdate(qry);
        } 
        catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "them thanh cong");
        }
    }
    public void sua(khuyenmaiDTO km) {
        try {
            String qry ="Update khuyenmai Set ";
            qry=qry+"tenkhuyenmai='"+km.tenkm+"'";
            qry=qry+",dieukienkhuyenmai='"+km.dieukienkm+"'";
            qry=qry+",phantramkhuyenmai='"+km.phantramkm+"'";   
            qry=qry+",ngaybatdau='"+km.ngaybatdau+"'";
            qry=qry+",ngayketthuc='"+km.ngayketthuc+"'";
            qry=qry+" where idkhuyenmai='"+km.idkm+"'";
            st=conn.createStatement();
            st.executeUpdate(qry);
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "sua thanh cong");
        }
    }
    
    public void xoa (String idkm) {
        try {
            String qry = "Delete from khuyenmai where idkhuyenmai='"+idkm+"'";
            st=conn.createStatement();
            st.executeUpdate(qry);
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "xoa thanh cong");
        }
    }
    
    public ArrayList checkTimkiem(String tenkm){
        ArrayList listtk = new ArrayList<khuyenmaiDTO>();
            try {
                String qry = "Select * from khuyenmai where tenkhuyenmai like '%"+tenkm+"%'";
                st=conn.createStatement();
                rs=st.executeQuery(qry);
                while(rs.next()){
                    khuyenmaiDTO km = new khuyenmaiDTO();
                    km.setIdkm(rs.getString(1));
                    km.setTenkm(rs.getString(2));
                    km.setDieukienkm(rs.getFloat(3));
                    km.setPhantramkm(rs.getFloat(4));
                    km.setNgaybatdau(rs.getDate(5));
                    km.setNgayketthuc(rs.getDate(6));
                    listtk.add(km);   
                }
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "vui long nhap lai");
            }
            finally{
                try {
                    rs.close(); st.close(); conn.close();
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(null,e);
                }
        return listtk;
    }
  }
    
   
}