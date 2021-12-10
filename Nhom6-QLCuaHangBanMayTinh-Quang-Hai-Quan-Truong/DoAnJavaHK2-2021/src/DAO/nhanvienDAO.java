package DAO;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import DTO.nhanvienDTO;
import BUS.nhanvienBUS;

public class nhanvienDAO {
    String user="root";
    String pass="";
    String url="jdbc:mysql://localhost:3306/JAVASQL?useUnicode=true&characterEncoding=UTF-8";
    Connection conn=null;
    Statement st=null;
    ResultSet rs=null;
    public nhanvienDAO(){
        if(conn==null){
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn=DriverManager.getConnection(url,user,pass);
            }
            catch(ClassNotFoundException e){
                JOptionPane.showMessageDialog(null,"Lỗi không tìm thấy class.");
            }
            catch(SQLException e){
                JOptionPane.showMessageDialog(null,"Lỗi không tìm thấy sql.");
            }
        }
    }
    public ArrayList docDSNV()throws Exception{
        ArrayList dsnv=new ArrayList<nhanvienDTO>();
        try{
            String qry="select * from NhanVien";
            st=conn.createStatement();
            rs=st.executeQuery(qry);
            while(rs.next()){
                nhanvienDTO nv=new nhanvienDTO();
                nv.ma=rs.getString(1);
                nv.hoten=rs.getString(2);
                nv.ngsinh=rs.getDate(3);                
                nv.diachi=rs.getString(4);                
                nv.sdt=rs.getString(5);
                nv.email=rs.getString(6);
                dsnv.add(nv);
            }
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Lỗi đọc thông tin nhân viên.");
        }
        return dsnv;
    }
    public void them(nhanvienDTO nv){
        try{
            String qry="Insert into NhanVien Values(";
            qry=qry+"'"+nv.ma+"'";
            qry=qry+",'"+nv.hoten+"'";
            qry=qry+",'"+(new SimpleDateFormat("yyyy-MM-dd").format(nv.ngsinh))+"'";
            qry=qry+",'"+nv.diachi+"'";
            qry=qry+",'"+nv.sdt+"'";
            qry=qry+",'"+nv.email+"'";
            qry=qry+")";
            st=conn.createStatement();
            st.executeUpdate(qry);
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Thêm Thành Công");
        }
    }
    public void xoa(String s){
        try{
            String qry="Delete from NhanVien where idnhanvien='"+s+"'";
            st=conn.createStatement();
            st.executeUpdate(qry);
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Đã Xóa");
        }
    }
    public void sua(nhanvienDTO nv){
        try{
            String qry="Update NhanVien Set ";
            qry=qry+" tennhanviem='"+nv.hoten+"'";
            qry=qry+",ngaysinh='"+(new SimpleDateFormat("yyyy-MM-dd").format(nv.ngsinh))+"'";           
            qry=qry+",diachi='"+nv.diachi+"'";
            qry=qry+",sdt='"+nv.sdt+"'";
            qry=qry+",gmail='"+nv.email+"'";
            qry=qry+"  where idnhanvien='"+nv.ma+"'";
            st=conn.createStatement();
            st.executeUpdate(qry);
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Sửa Thành Công");
        }
    }
}