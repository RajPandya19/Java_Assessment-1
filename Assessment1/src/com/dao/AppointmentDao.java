package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.model.AppointmentModel;
import com.util.DBUtil;

public class AppointmentDao {

    public static boolean addAppointment(AppointmentModel a) {
        try {  
        	
        	Connection cn=null;
        	cn = new DBUtil().getConnectionData();

            String sql = "INSERT INTO appointment(patient_id, service_type, appointment_date, cost) VALUES(?,?,?,?)";
            PreparedStatement ps = cn.prepareStatement(sql);

            ps.setInt(1, a.getPatientId());
            ps.setString(2, a.getServiceType());
            ps.setString(3, a.getDate());
            ps.setDouble(4, a.getCost());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}