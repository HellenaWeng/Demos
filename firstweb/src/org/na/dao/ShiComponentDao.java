package org.na.dao;

import org.na.entity.ShiComponent;
import org.na.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ShiComponentDao {
    //插入记录
    public void addShiComponent(ShiComponent SC) throws SQLException {
        Connection conn = DbUtil.getConnection();
        String sql = "insert into shicomponent (plantName, prescriptionID) values (?,?)";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setString(1, SC.getPlantName());
        ptmt.setInt(2, SC.getPrescriptionID());
        System.out.println(sql);
        System.out.println("SC.getPlantName() = " + SC.getPlantName());
        System.out.println("SC.getPrescriptionID() = " + SC.getPrescriptionID());
        ptmt.execute();
    }
}
