package org.na.dao;

import org.na.entity.AncientPrescriptionTreats;
import org.na.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AncientPrescriptionTreatsDao {
    public void addAncientPrescriptionTreats(AncientPrescriptionTreats APT) throws SQLException {
        Connection conn = DbUtil.getConnection();
        String sql = "INSERT INTO ancient_prescriptiontreats(ancient_prescriptionID, treatName) values(?,?)";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setInt(1, APT.getAncientPrescriptionID());
        ptmt.setString(2, APT.getTreatName());
        System.out.println("APT.getTreatName() = " + APT.getTreatName());
        ptmt.execute();
    }
}
