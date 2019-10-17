package org.na.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class DatabaseAccess extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");//解决数据库查出数据在页面显示乱码或问号
        final String JDBC_DRIVER = "com.mysql.jdbc.Driver" ;
        final String DB_URL = "jdbc:mysql://localhost:3306/medicinetest?characterEncoding=utf-8" ;

        final String USER = "root" ;
        final String PASS = "5201230618102x" ;

        PrintWriter out = resp.getWriter();
        String title = "数据库结果" ;
        String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n" ;
                out.println
                        (
                        docType +
                        "<html>\n" +
                        "<head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n<title>" + title + "</title></head>\n" +
                        "<body bgcolor=\"f0f0f0\">\n" +
                        "<h1 align=\"center\">" + title + "</h1>\n"
                );

        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            Statement stmt = conn.createStatement();
            String sql;
            sql = "select id, title, content from prescription" ;
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                int id = rs.getInt("id");
                String wordTitle = rs.getString("title");
                String wordContent = rs.getString("content") ;

                out.println("id: " + id + "<br/>");
                out.println("wordTitle: " + wordTitle + "<br/>");
                out.println("wordContent: "+ wordContent + "<br/>");
            }
            out.println("</body></html>");

            rs.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
}
