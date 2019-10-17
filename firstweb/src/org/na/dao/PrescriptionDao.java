package org.na.dao;

import org.na.entity.Prescription;
import org.na.util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionDao {
    //增加数据
    public void addPrescription(Prescription p) throws SQLException {
        Connection conn = DbUtil.getConnection();
        //sql
        String sql = "INSERT INTO prescription(opTitle, presName, otherNames, pinYin, englishName, nation, classify, tropism, source, famous, principle, useway, effects, indications, attention, size, reposit, smell, song, ancientID) values("+"?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        //预编译
        PreparedStatement ptmt = conn.prepareStatement(sql); //预编译SQL，减少sql执行
        //传参
        ptmt.setString(1, p.getOpTitle());
        ptmt.setString(2,p.getPresName());
        ptmt.setString(3,p.getOtherNames());
        ptmt.setString(4, p.getPinYin());
        ptmt.setString(5, p.getEnglishName());
        ptmt.setString(6, p.getNation());
        ptmt.setString(7, p.getClassify());
        ptmt.setString(8, p.getTropism());
        ptmt.setString(9, p.getSource());
        ptmt.setString(10, p.getFamous());
        ptmt.setString(11, p.getPrinciple());
        ptmt.setString(12, p.getUseway());
        ptmt.setString(13, p.getEffects());
        ptmt.setString(14, p.getIndications());
        ptmt.setString(15, p.getAttention());
        ptmt.setString(16, p.getSize());
        ptmt.setString(17, p.getReposit());
        ptmt.setString(18, p.getSmell());
        ptmt.setString(19, p.getSong());
        ptmt.setInt(20, p.getAncientID());
        //执行
        System.out.println(sql);
        ptmt.execute();
    }
    public Prescription query() throws SQLException {
        Connection conn = DbUtil.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT ID FROM prescription");

        Prescription p = null;
        while(rs.next()){
            p = new Prescription();
            p.setID(rs.getInt("ID"));
        }
        return p;
    }

    public Prescription get(Integer id) throws SQLException {
        Prescription p = null;
        //获取连接
        Connection conn = DbUtil.getConnection();
        //sql, 每行加空格
        String sql = "select * from  imooc_goddess where id=?";
        //预编译SQL，减少sql执行
        PreparedStatement ptmt = conn.prepareStatement(sql);
        //传参
        ptmt.setInt(1, id);
        //执行
        ResultSet rs = ptmt.executeQuery();
        while(rs.next()){
            p = new Prescription();
            p.setOpTitle(rs.getString("opTitle"));
//            g.setUser_name(rs.getString("user_name"));
//            g.setAge(rs.getInt("age"));
//            g.setSex(rs.getInt("sex"));
//            g.setBirthday(rs.getDate("birthday"));
//            g.setEmail(rs.getString("email"));
//            g.setMobile(rs.getString("mobile"));
//            g.setCreate_date(rs.getDate("create_date"));
//            g.setCreate_user(rs.getString("create_user"));
//            g.setUpdate_date(rs.getDate("update_date"));
//            g.setUpdate_user(rs.getString("update_user"));
//            g.setIsDel(rs.getInt("isdel"));
        }
        return p;
    }
}
