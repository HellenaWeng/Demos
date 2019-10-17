package org.na.dao;

import org.na.entity.ModernStudy;
import org.na.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ModernStudyDao {
    //插入记录
    public void addModernStudy(ModernStudy MS) throws SQLException {
        Connection conn = DbUtil.getConnection();
        String sql = "INSERT INTO modern_study(study, prescriptionID) values(?,?)";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setString(1, MS.getStudy());
        ptmt.setInt(2, MS.getPrescriptionID());
        ptmt.execute();
    }
}
