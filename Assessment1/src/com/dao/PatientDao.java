package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.model.PatientModel;
import com.util.DBUtil;

public class PatientDao{


    public static int addPatient(PatientModel p) {
        try {
        	Connection cn=null;
        	
        	cn = new DBUtil().getConnectionData();

            String sql = "INSERT INTO patient(name, phone) VALUES(?,?)";
            PreparedStatement st=cn.prepareStatement(sql);

            st.setString(1, p.getName());
            st.setString(2, p.getPhone());

            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}