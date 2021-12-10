/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author phamn
 */
public class MySQLConnect {
    String Host ;
    String UserName ;
    String Password;
    String Database;
    
    Connection connect = null;
    Statement statement = null;
    ResultSet resultset = null;
    public MySQLConnect(String Host, String UserName, String Password, String Database){
        this.Host = Host;
        this.UserName = UserName;
        this.Password = Password;
        this.Database = Database;
    }
    //hàm kiểm tra cơ sở dũ liệu co sẵn sàng hay chưa
    protected void driverTest() throws Exception{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (java.lang.ClassNotFoundException e) {
            throw new Exception("Không tìm thấy driver");
        }
    }
    //hàm lấy connection
    public Connection getConnect()throws Exception{
        if(this.connect==null){
            // kiem tra driver
            driverTest();
            // tao url ket noi den db
            String url ="jdbc:mysql://"+this.Host+":3306/"+this.Database;
            System.out.println(this.Host);
             System.out.println(this.UserName);
              System.out.println(this.Password);
               System.out.println(this.Database);
            try {
                this.connect = DriverManager.getConnection(url, this.UserName, this.Password);
                //JOptionPane.showMessageDialog(null, "ket noi thanh cong database");
            } catch (java.sql.SQLException e) {
                throw new Exception("Khong the ket noi den database");
            }
        }
        return this.connect; 
    }
    //tạo Statement đẻ thực hiện query
    public Statement getStatement()throws Exception{
        if(this.statement == null? true: this.statement.isClosed()){
            this.statement = this.getConnect().createStatement();
        }
        return this.statement;
    }
    //hàm thực thi câu lệnh SELECT lấy dữu liệu từ database
    public ResultSet excuteQuery(String Query) throws Exception{
        try {
            this.resultset = this.getStatement().executeQuery(Query);
        } catch (Exception e) {
            throw new Exception("Error: "+e.getMessage() + "-" + Query);
        }
        return this.resultset;
    }
    //thực thi các câu lệnh imsert, update, delete;
    public int executeUpdate(String Query) throws Exception{
         int res = Integer.MAX_VALUE;
        try {
            res = this.getStatement().executeUpdate(Query);
        } catch (Exception e) {
            throw new Exception("Error: "+e.getMessage() + "-" + Query);
        }
        finally{
            this.Close();
        }
        return res;
    }
     //hàm đóng kết nối
    public void Close() throws Exception{
     //neu resultset chua dong. Dong resultset  
     if(this.resultset !=null && ! this.resultset.isClosed()){
         this.resultset.close();
         this.resultset=null;
     }
     //neu statement chua dong. Dong statement  
     if(this.statement != null && ! this.statement.isClosed()){
         this.statement.close();
         this.statement = null;
     }
     //neu connection chua dong. Dong connection 
     if(this.connect !=null && ! this.connect.isClosed()){
         this.connect.close();
         this.connect = null;
     }
    }

    public PreparedStatement getStatement(String sql) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
