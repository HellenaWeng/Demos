package org.na.dao;

import org.na.entity.AncientPrescription;
import org.na.entity.Prescription;
import org.na.util.DbUtil;

import java.sql.*;

public class AncientPrescriptionDao {

    //增加数据
    public void addAncientPrescription(AncientPrescription aP) throws SQLException {
        Connection conn = DbUtil.getConnection();
        //sql
        String sql = "INSERT INTO ancient_prescription(ancient_use) values(?)";
        //预编译
        PreparedStatement ptmt = conn.prepareStatement(sql); //预编译SQL，减少sql执行
        //传参
        ptmt.setString(1, aP.getAncientUse());
        System.out.println("aP.getAncientUse() = "+aP.getAncientUse());
        System.out.println(sql);
        //执行
        ptmt.execute();

    }

    //查询最后一条记录的ID
    public AncientPrescription query() throws SQLException {
        Connection conn = DbUtil.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT ID FROM ancient_prescription");

//        List<AncientPrescription> aPs = new ArrayList<AncientPrescription>();
        AncientPrescription aP = null;
        while(rs.next()){
            aP = new AncientPrescription();
            aP.setID(rs.getInt("ID"));
        }
        return aP;
    }
}
