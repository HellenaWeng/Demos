package org.na.dao;

import org.na.entity.ClinicApplication;
import org.na.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClinicApplicationDao {
    //增加一条记录
    public void addClinicApplication(ClinicApplication CA) throws SQLException {
        Connection conn = DbUtil.getConnection();
        String sql = "INSERT INTO clinic_application(clinic_use, prescriptionID) values(?, ?)";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setString(1, CA.getClinicUse());
        ptmt.setInt(2, CA.getPrescriptionID());
        ptmt.execute();
    }
}
