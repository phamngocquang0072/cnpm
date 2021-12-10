
package DAO;

import DTO.khachhangDTO;

import JDBC.MySQLConnect;
import com.mysql.cj.xdevapi.Result;
import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class khachhangDAO {
    String user="root";
    String pass="";
    String url="jdbc:mysql://localhost:3306/JAVASQL?useUnicode=true&characterEncoding=UTF-8";
    public static Connection conn=null;
    public Statement st= null;
    public ResultSet rs = null;
    
    
    
    public khachhangDAO() {
        if(conn == null){
            try 
            {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(url,user,pass);
            }
            catch(ClassNotFoundException ex){
                JOptionPane.showMessageDialog(null, "khong tim thay class");   
            }
            catch(SQLException e){
                JOptionPane.showMessageDialog(null,"khong tim thay sql");
            }
        }
    }
    
    
    public ArrayList docDSKH() throws Exception{
        ArrayList dskh = new ArrayList<khachhangDTO>();
        try {
            String qry="select * from khachhang";
            st=conn.createStatement();
            rs= st.executeQuery(qry);
            while(rs.next()){
                khachhangDTO kh = new khachhangDTO();
                kh.idkhachhang=rs.getString(1);
                kh.tenkhachhang=rs.getString(2);
                kh.diachi=rs.getString(3);
                kh.sdt=rs.getString(4);
                kh.gmail=rs.getString(5);
                dskh.add(kh);
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "loi doc data");
        }
        return dskh;   
    }
    

    public void them(khachhangDTO kh) {
        try {
            String qry = "Insert into khachhang VALUES (";
            qry =qry+"'"+kh.idkhachhang +"'";
            qry =qry+",'"+kh.tenkhachhang+"'";
            qry =qry+",'"+kh.diachi+"'";
            qry =qry+",'"+ kh.sdt+"'";
            qry =qry+",'"+ kh.gmail+"'";
            qry= qry+")";
            st=conn.createStatement();
            st.executeUpdate(qry);
             
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "them khach hang thanh cong");    
        }
    }

    public void sua(khachhangDTO kh) {
        try {
            String qry="Update khachhang Set ";
            qry=qry+"tenkhachhang='"+kh.tenkhachhang+"'";
            qry=qry+",diachi='"+kh.diachi+"'";
            qry=qry+",sdt='"+kh.sdt+"'";
            qry=qry+",gmail='"+kh.gmail+"'";
            qry=qry+" where idkhachhang='"+kh.idkhachhang+"'";
            st=conn.createStatement();
            st.executeUpdate(qry); 
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, " sua thong tin khach hang thanh cong");
        }
    }

    public void xoa(String idkhachhang){
        try {
            String qry ="Delete from khachhang where idkhachhang='"+idkhachhang+"'";
            st=conn.createStatement();
            st.executeUpdate(qry);  
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "xoa khach hang thanh cong");   
        }
    }


    public ArrayList<khachhangDTO> search(String value) {
       ArrayList dskh2 = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pst = null;
        try {
            String qry ="SELECT * FROM khachhang where tenkhachhang LIKE ?";
            pst = conn.prepareStatement(qry);
            pst.setString(1, value);
            rs=pst.executeQuery();
            while(rs.next()){
                khachhangDTO kh = new khachhangDTO();
                kh.idkhachhang=rs.getString("idkhachhang");
                kh.tenkhachhang=rs.getString("tenkhachhang");
                kh.diachi=rs.getString("diachi");
                kh.sdt=rs.getString("sdt");
                kh.gmail=rs.getString("gmail");
                dskh2.add(kh);
            }
            return dskh2;
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
            return null;
        }finally{
            try {
                if(conn != null){
                    conn.close();
                }
                if(pst != null){
                    pst.close();
                }   
            }catch(Exception ex2)
            {
                JOptionPane.showMessageDialog(null,ex2);
            }
        }   
    }

   

    
    
}
