
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;
import java.sql.*;
import java.util.HashMap;
/**
 *
 * @author phamn
 */
public class MyConnectUnit {
    private MySQLConnect connect;

    
    
    //hàm hỗ trợ cơ sở dữ liệu
    //SELECT *FROM TableName WHERE Condition ORDER BY OrderBy
    public ResultSet Select(String TableName, String Condition, String OrderBy ) throws Exception{
        //khai báo biến Stringbuilder để tạo chuỗi select
        StringBuilder query = new StringBuilder("SELECT * FROM "+TableName);
        //đưa câu lệnh điều kiênj vào trong query
        this.AddCondition(query,Condition);
        //đưa câu lệnh OrderBy vào trong query
        this.AddOrderBy(query,OrderBy);
        //chèn kí tự ';' vào dòng lệnh query để cách các câu lệnh 
        query.append(";");
        //thực thi câu lệnh query và trả kêt quả ra ngoài
        return this.connect.excuteQuery(query.toString());
    }
    //hàm over load select giảm orderby parameter
    public ResultSet Select(String TableName, String Condition ) throws Exception{
        return this.Select(TableName, Condition,null);
    }
    //hàm over load select giảm condition parameter
    public ResultSet Select(String TableName) throws Exception{
        return this.Select(TableName, null);
    }
    //hàm thêm điều kiện vào query
    private void AddCondition(StringBuilder query, String Condition) {
        //kiểm tra nêu condition khác null
        if(Condition != null){
            query.append("WHERE" + Condition);
        }
    }
    //hàm thêm orderby vào query
    private void AddOrderBy(StringBuilder query, String OrderBy) {
        //Kieemr tra orderby khac null
        if(OrderBy != null){
            query.append("ORDER BY" + OrderBy);
        }
    }
    //hàm hỗ trọ insert xuong csdl
    //INSERT INTO TableName (columname,...) values (Column value,...);
    public boolean Insert(String TableName, HashMap<String,Object> ColumnValues) throws Exception{
        //khai báo biến StringBuilder để tạo chuỗi SELECT
        StringBuilder query = new StringBuilder("INSERT INTO" + TableName);
        //khai báo biến StirngBuilder để tạo chuỗi column Values.
        StringBuilder valueInsert = new StringBuilder();
        query.append("(");
        //duyệt và đưa thông tin lên cột và giá trị của các cột vào
        for(String key: ColumnValues.keySet()){
            query.append(",");
            valueInsert.append("'" + ColumnValues.get(key).toString()+"' ,");
            
        }
        //cắt bớt kí tự ',' cuối câu
        query = query.delete(query.length()-1, query.length());
        valueInsert = valueInsert.delete(valueInsert.length()-1, valueInsert.length());
        //đưa giá trị của cột vào câu query
        query.append(") VALUES ("+valueInsert.toString()+")");
        //chèn kí tự ";" vào cuối dòng lệnh
        query.append(";");
        //thực thi câu lệch query và trả ra ngoài kết quả
        return this.connect.executeUpdate(query.toString()) > 0;
        
        
        
    }
    //hàm hỗ trợ upadte trong csdl
    //Update tableName set columname = columnvalue where conditon = condition;
    public boolean Update(String TableName, HashMap<String,Object> ColumnValues, String Condition) throws Exception{
        //khai báo biến StringBuilder để tạo chuỗi SELECT
        StringBuilder query = new StringBuilder("UPDATE" + TableName + "SET");
       
        //duyệt và đưa thông tin lên cột và giá trị của các cột vào
        for(String key: ColumnValues.keySet()){
            
            query.append(key + " = '" + ColumnValues.get(key).toString()+"' ,");
            
        }
        //cắt bớt kí tự ',' cuối câu
        query = query.delete(query.length()-1, query.length());
     
        //đưa câu điều kiện vào trong câu query
        this.AddCondition(query, Condition);
        //chèn kí tự ";" vào cuối dòng lệnh
        query.append(";");
        //thực thi câu lệch query và trả ra ngoài kết quả
        return this.connect.executeUpdate(query.toString()) > 0;
        
        
        
    }
    //hàm hỗ trợ Delete trong csdl
    //delete from tablename where condition
    public boolean Delete(String TableName,String Condition) throws Exception{
        //khai báo biến StringBuilder để tạo chuỗi SELECT
        StringBuilder query = new StringBuilder("DELETE FROM " + TableName +"WHERE");
        //đưa câu điều kiện vào trong câu query
        this.AddCondition(query, Condition);
        //chèn kí tự ";" vào cuối dòng lệnh
        query.append(";");
        //thực thi câu lệch query và trả ra ngoài kết quả
        return this.connect.executeUpdate(query.toString()) > 0;
        
        
        
    }
    //đếm số cột trong result select từ csdl
    public static int getColumnCount(ResultSet result) throws SQLException{
        return result.getMetaData().getColumnCount();
    }
    //hàm lấy danh sách tên cột trong result select từ csdl
    public static String[]getColumnName(ResultSet result) throws SQLException{
        //lấy resultmetadata từ result
        ResultSetMetaData rsMetaData = (ResultSetMetaData) result.getMetaData();
        //Lấy số lượng cột trong result
        int count = rsMetaData.getColumnCount();
        //khai báo danh sách các cột
        String[] list = new String[count];
        //duyệt các cột 
        for(int i=0;i<count;i++)
        //lấy tên đưa vào danh sách
            list[i]=rsMetaData.getColumnName(i);
        //trả danh sách ra ngoài
        return list;
    }
    //đóng kết nối
    
}
