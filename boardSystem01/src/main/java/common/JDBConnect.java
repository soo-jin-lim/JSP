package common;

import jakarta.servlet.ServletContext;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class JDBConnect {
    public Connection conn;     //db 연결
    public Statement stmt;      //완성형 쿼리 작성하여 전송
    public PreparedStatement pstmt;     //파라미터형 쿼리 작성하여 전송
    public ResultSet rs;    //Select문 쿼리 결과를 리턴 받을 때 사용

    public JDBConnect(){
        try{
            //db driver 로드  //오라클 : oracle.jdbc.OracleDaiver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //db 연결
            String url = "jdbc:mysql://localhost:3306/boarddb?useSSL=false&serverTimezone=Asia/Seoul&characterEncoding=UTF-8";
            String id = "lsj";
            String pw = "1234";
            conn = DriverManager.getConnection(url, id, pw);
            System.out.println("db connection success!!");
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public JDBConnect(String driver, String url, String id, String pw) {
        try{
            Class.forName(driver);
            conn = DriverManager.getConnection(url, id, pw);
            System.out.println("JDBC2 connect !!!!!!!");
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public JDBConnect(ServletContext application){
        try{
            String driver = application.getInitParameter("MySQLDriver");
            Class.forName(driver);
            String url = application.getInitParameter("MySQLURL");
            String id = application.getInitParameter("MySQLId");
            String pw = application.getInitParameter("MySQLPw");
            conn = DriverManager.getConnection(url, id, pw);
            System.out.println("JDBC3 connect !!!!!!!");
        }catch (Exception ex){
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
