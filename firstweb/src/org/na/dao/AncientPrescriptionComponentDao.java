package org.na.dao;

import org.na.entity.AncientPrescription;
import org.na.entity.AncientPrescriptionComponent;
import org.na.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AncientPrescriptionComponentDao {
    //增加数据
    public void addAncientPrescriptionComponent(AncientPrescriptionComponent APC) throws SQLException {
        Connection conn = DbUtil.getConnection();
        //sql
        String sql = "INSERT into ancient_prescriptioncomponent(ancient_prescriptionID,plantName,dosage) values(?,?,?)";
        //预编译
        PreparedStatement ptmt = conn.prepareStatement(sql) ;

        ptmt.setInt(1, APC.getAncientPrescriptionID());
        ptmt.setString(2, APC.getAPCEles().getPlantName());
        ptmt.setString(3, APC.getAPCEles().getDosage());

        System.out.println("APC.getAncientPrescriptionID() = " + APC.getAncientPrescriptionID());
        System.out.println("APC.getAPCEles().getPlantName()" + APC.getAPCEles().getPlantName());
        System.out.println("APC.getAPCEles().getDosage()" + APC.getAPCEles().getDosage());
        System.out.println(sql);

        //执行
        ptmt.execute();

    }

}
