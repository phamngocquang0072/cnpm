package DAO;

import DTO.sanphamDTO;
import JDBC.MySQLConnect;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class sanphamDAO {
    public static Connection conn=null;
    public Statement st=null;
    public ResultSet rs=null;
    public MySQLConnect connect=new MySQLConnect("localhost","root","","javasql?useUnicode=true&characterEncoding=UTF-8");
    
    public sanphamDAO(){
        if(conn==null){
            try{
                conn=connect.getConnect();
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Không tìm thấy class "+sanphamDAO.class.getName());
            }
        }
    }
    public ArrayList docDSSP() throws Exception{
        ArrayList dssp=new ArrayList<sanphamDTO>();
        try{
            String qry="select * from sanpham";
            rs=connect.getStatement().executeQuery(qry);
            while(rs.next()){
                sanphamDTO sp=new sanphamDTO();
                sp.idsanpham=rs.getString(1);
                sp.idloaisanpham=rs.getString(2);
                sp.tensanpham=rs.getString(3);
                sp.dongia=rs.getDouble(4);
                sp.soluong=rs.getInt(5);
                sp.mota=rs.getString(6);
                sp.anh=rs.getString(7);
                dssp.add(sp);
            }
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Lỗi đọc thông tin sản phẩm.");
        }
        return dssp;
    }
    public void them(sanphamDTO sp) throws Exception{
        String qry="Insert into sanpham Values(";
        qry=qry+"'"+sp.idsanpham+"'";
        qry=qry+",'"+sp.idloaisanpham+"'";
        qry=qry+",'"+sp.tensanpham+"'";
        qry=qry+",'"+sp.dongia+"'";
        qry=qry+",'"+sp.soluong+"'";
        qry=qry+",'"+sp.mota+"'";
        qry=qry+",'"+sp.anh+"'";
        qry=qry+")";
        connect.getStatement().executeUpdate(qry);
    }
    public void sua(sanphamDTO sp) throws FileNotFoundException, IOException{
        try{
            String qry="Update sanpham Set ";
            qry=qry+" idloaisanpham='"+sp.idloaisanpham+"'";
            qry=qry+",tensanpham='"+sp.tensanpham+"'";
            qry=qry+",dongia='"+sp.dongia+"'";
            qry=qry+",soluong='"+sp.soluong+"'";
            qry=qry+",mota='"+sp.mota+"'";
            qry=qry+",anh='"+sp.anh+"'";
            qry=qry+"  where idsanpham='"+sp.idsanpham+"'";
            st=conn.createStatement();
            st.executeUpdate(qry);
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Lỗi cập nhật sản phẩm!");
        }
    }
    public void xoa(String s) throws Exception{
        try{
            String qry="Delete from sanpham where idsanpham='"+s+"'";
            connect.getStatement().executeUpdate(qry);
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Lỗi xóa sản phẩm!");
        }
    }
    public void suaSL(sanphamDTO sp){
        try{
            String qry="Update sanpham Set";
            qry=qry+" soluong='"+sp.soluong+"'";
            qry=qry+"  where idsanpham='"+sp.idsanpham+"'";
            st=conn.createStatement();
            st.executeUpdate(qry);
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Lỗi cập nhật số lượng!");
        }
    }
}
