package org.na.dao;

import org.na.entity.PrescriptionArguments;
import org.na.util.DbUtil;

import java.awt.image.DataBufferUShort;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PrescriptionArgumentDao {
    //插入数据
    public void addPrescritionArgumrnts(PrescriptionArguments PA) throws SQLException {
        Connection conn = DbUtil.getConnection();
        String sql = "Insert into prescriptionarguments(argument,prescriptionID) values(?,?)";
        //预编译
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setString(1, PA.getArgument());
        ptmt.setInt(2, PA.getPrescritionID());

        System.out.println(sql);
        //执行
        ptmt.execute();
    }

}
