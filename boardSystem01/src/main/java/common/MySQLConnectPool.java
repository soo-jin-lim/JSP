package common;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySQLConnectPool {
    public Connection conn;     //db 연결
    public Statement stmt;      //완성형 쿼리 작성하여 전송
    public PreparedStatement pstmt;     //파라미터형 쿼리 작성하여 전송
    public ResultSet rs;    //Select문 쿼리 결과를 리턴 받을 때 사용

    public MySQLConnectPool(){
        try {
            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)envContext.lookup("dbcp_mysql");
            conn = ds.getConnection();
            System.out.println("MySQL DB connect pool success");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("MySQL DB connect pool fail");
        }
    }

    public void close(){
        try{
            if(rs != null) rs.close();
            if(stmt != null) stmt.close();
            if(pstmt != null) pstmt.close();
            if(conn != null) conn.close();

            System.out.println("MySQL DB connect pool resource release");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
