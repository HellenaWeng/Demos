package org.na.dao;

import org.na.entity.AncientPrescription;
import org.na.entity.PrescriptionDerivation;
import org.na.util.DbUtil;

import java.sql.*;

public class PrescriptionDerivationDao {
    //增加数据
    public void addPrescriptionDerivation(PrescriptionDerivation PD) throws SQLException {
        Connection conn = DbUtil.getConnection();
        //sql
        String sql = "INSERT INTO prescription_derivation(name,prescriptionID) values(?,?)";
        //预编译
        PreparedStatement ptmt = conn.prepareStatement(sql); //预编译SQL，减少sql执行
        //传参
        ptmt.setString(1, PD.getName());
        ptmt.setInt(2, PD.getPrescriptionID());
        System.out.println("PD.getName() = " + PD.getName());
        //执行
        ptmt.execute();

    }

    //查询最后一条记录的ID----不能是查询最后一条记录的ID, 每条ID都要要
    public PrescriptionDerivation query() throws SQLException {
        Connection conn = DbUtil.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT ID FROM prescription_derivation");

//        List<AncientPrescription> aPs = new ArrayList<AncientPrescription>();
        PrescriptionDerivation pD = null;
        while(rs.next()){
            pD = new PrescriptionDerivation();
            pD.setID(rs.getInt("ID"));
        }
        return pD;
    }
}
