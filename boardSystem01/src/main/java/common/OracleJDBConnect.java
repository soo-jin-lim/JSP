package common;

import java.sql.*;

public class OracleJDBConnect {
    public Connection conn;
    public Statement stmt;
    public PreparedStatement pstmt;
    public ResultSet rs;

    public OracleJDBConnect() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            String url = "jdbc:oracle:thin:@localhost:1521:orcl";
            String id = "lmh";
            String pw = "1234";
            conn = DriverManager.getConnection(url, id, pw);
            System.out.println("Oracle JDBC1!!!!!!!!!!!!!!!!!!!!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void close(){
        try{
            if(rs != null) rs.close();
            if(stmt != null) stmt.close();
            if(pstmt != null) pstmt.close();
            if(conn != null) conn.close();
        }catch (Exception exception){}
    }

}
