package GameAccount;

import java.sql.*;

public class DataOperation {
    Connection connection = null;
    ResultSet rs = null;

    String username = "root";
    String password = "ly003910";
    String url = "jdbc:mysql://localhost:3306/dataaccountdesign?user="
            + username + "&password=" + password ;//+ "&serverTimezone=UTC&useUnicode=true&characterEncoding=gbk";

    public DataOperation() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("mysql数据库驱动加载成功");
        }
        catch(java.lang.ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void connect() {
        try{
            connection = DriverManager.getConnection(url);
            if(connection != null) {
                System.out.println("数据库连接成功");
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void disconnect(){
        try{
            if(connection != null){
                connection.close();
                connection = null;
                System.out.println("数据库断开成功");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    //查询
    public ResultSet executeQuery(String sql) {
        try {
            System.out.println("executeQuery(). sql = " + sql);
            PreparedStatement pstm = connection.prepareStatement(sql);
            rs = pstm.executeQuery();
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
        return rs;
    }

    //执行增，删，改或SQL DDL语句
    public int executeUpdate(String sql) {
        int count = 0;
        connect();
        try {
            Statement stmt = connection.createStatement();
            count = stmt.executeUpdate(sql);
        }
        catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        disconnect();
        return count;
    }
}
