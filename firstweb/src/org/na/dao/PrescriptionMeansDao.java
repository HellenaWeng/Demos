package org.na.dao;

import org.na.entity.Prescription;
import org.na.entity.PrescriptionMeans;
import org.na.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PrescriptionMeansDao {
    //增加记录
    public void addPrescriptionMeans(PrescriptionMeans PM) throws SQLException {
        Connection conn = DbUtil.getConnection();
        String sql = "INSERT INTO prescriptionmeans(meaning, prescriptionID) values(?,?)";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setString(1, PM.getMeaning());
        ptmt.setInt(2, PM.getPrescriptionID());
        ptmt.execute();
    }
}
