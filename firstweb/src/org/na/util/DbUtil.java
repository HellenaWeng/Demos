package org.na.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver" ;
    public static final String DB_URL = "jdbc:mysql://localhost:3306/medicinetest?characterEncoding=utf-8" ;
    public static final String USER = "root" ;
    public static final String PASS = "5201230618102x" ;
    //    public static final String URL = "jdbc:mysql://localhost:3306/imooc";
//    public static final String USER = "liulx";
//    public static final String PASSWORD = "123456";
    private static Connection conn = null;
    static{
        try {
            //1.加载驱动程序
            Class.forName(JDBC_DRIVER);
            //2. 获得数据库连接
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        return conn;
    }


}
