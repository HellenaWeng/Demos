package org.na.dao;

import org.na.entity.ZuoComponent;
import org.na.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ZuoComponentDao {
    //插入记录
    public void addZuoComponent(ZuoComponent ZC) throws SQLException {
        Connection conn = DbUtil.getConnection();
        String sql = "insert into zuocomponent (plantName, prescriptionID) values (?,?)";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setString(1, ZC.getPlantName());
        ptmt.setInt(2, ZC.getPrescriptionID());
        System.out.println(sql);
        System.out.println("ZC.getPlantName() = " + ZC.getPlantName());
        System.out.println("ZC.getPrescriptionID() = " + ZC.getPrescriptionID());
        ptmt.execute();
    }
}
