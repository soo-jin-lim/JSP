package common;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySQLConnectPool {
    public Connection conn;
    public Statement stmt;
    public PreparedStatement psmt;
    public ResultSet rs;

    public MySQLConnectPool(){
        try{
            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)envContext.lookup("dbcp_mysql");
            conn = ds.getConnection();
            System.out.println("MySQL DB connect pool success");
        }catch (Exception ex){
            System.out.print("MySQL DB connect pool fail");
            ex.printStackTrace();
        }
    }
    // 연결 해제(자원 반납)
    public void close() {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (psmt != null) psmt.close();
            if (conn != null) conn.close();  // 자동으로 커넥션 풀로 반납됨

            System.out.println("MySQL DB connect pool resource release");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
