package util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
    private static DataSource ds;

    static {
        try {
            Properties pro = new Properties();
            pro.load(JDBCUtils.class.getClassLoader().getResourceAsStream("db.properties"));
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection GetConnection() throws SQLException {
        return ds.getConnection();
    }

    public static DataSource GetDataSource(){
        return ds;
    }

    public static void Close(Statement stmt, Connection conn){
        Close(conn,stmt,null);
    }

    public static void Close(Connection conn, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    /*public static Connection conn;
    public static String role;

    static {
        try {
            if (role != "admin") {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myblogs?useSSL=false&serverTimezone=GMT%2B8",
                        "Galaxy5069",
                        "Continue/84157308");
            }
            else {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myblogs?useSSL=false&serverTimezone=GMT%2B8",
                        "adminMyblogs",
                        "Continue/84157308");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }*/

}
