package org.na.dao;

import org.na.entity.JunComponent;
import org.na.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JunComponentDao {
    //插入记录
    public void addJunComponent(JunComponent JC) throws SQLException {
        Connection conn = DbUtil.getConnection();
        String sql = "insert into juncomponent (plantName, prescriptionID) values (?,?)";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setString(1, JC.getPlantName());
        ptmt.setInt(2, JC.getPrescriptionID());
        System.out.println(sql);
        System.out.println("JC.getPlantName() = " + JC.getPlantName());
        System.out.println("JC.getPrescriptionID() = " + JC.getPrescriptionID());
        ptmt.execute();
    }
}
