package org.na.dao;

import org.apache.poi.hssf.record.DBCellRecord;
import org.na.entity.DerivationChange;
import org.na.util.DbUtil;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DerivationChangeDao {
    public void addDerivationChange(DerivationChange DC) throws SQLException {
        Connection conn = DbUtil.getConnection();
        String sql0 = "SET FOREIGN_KEY_CHECKS=0";
        String sql = "INSERT INTO derivation_change(plantName, changeFlag, derivationID) values(?, ?, ?)";
        String sql2 = "SET FOREIGN_KEY_CHECKS=1";
        PreparedStatement ptmt0 = conn.prepareStatement(sql0);
        PreparedStatement ptmt = conn.prepareStatement(sql);
        PreparedStatement ptmt2 = conn.prepareStatement(sql2);

        ptmt.setString(1, DC.getPlantName());
        ptmt.setInt(2, DC.getChangeFlag());
        ptmt.setInt(3, DC.getDerivationID());
        System.out.println("DC.getPlantName() = " + DC.getPlantName());
        System.out.println("DC.getChangeFlag() = " + DC.getChangeFlag());
        System.out.println("DC.getDerivationID() = " + DC.getDerivationID());
        ptmt0.execute();
        ptmt.execute();
        ptmt2.execute();
    }
}
