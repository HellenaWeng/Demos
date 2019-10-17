package org.na.dao;

import org.na.entity.ChenComponent;
import org.na.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ChenComponentDao {
    //插入记录
    public void addChenComponent(ChenComponent CC) throws SQLException {
        Connection conn = DbUtil.getConnection();
        String sql = "insert into chencomponent (plantName, prescriptionID) values (?,?)";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setString(1, CC.getPlantName());
        ptmt.setInt(2, CC.getPrescriptionID());
        System.out.println(sql);
        System.out.println("CC.getPlantName() = " + CC.getPlantName());
        System.out.println("CC.getPrescriptionID() = " + CC.getPrescriptionID());
        ptmt.execute();
    }
}
