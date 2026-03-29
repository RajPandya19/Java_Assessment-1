package com.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.dao.BillDao;
import com.model.BillModel;
import com.util.DBUtil;

public class BillController 
{

    public static void generateBill(BillModel b) 
    {

        double appointmentCost = 0;

        try {
            Connection cn = new DBUtil().getConnectionData();

            String sql = "SELECT cost FROM appointment WHERE appointment_id = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, b.getAppointmentId());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) 
            {
                appointmentCost = rs.getDouble("cost");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // ✅ CORRECT TOTAL CALCULATION
        double total = appointmentCost + b.getMedicineCost() + b.getTax() - b.getDiscount();

        b.setTotal(total);

        BillDao.addBill(b);

        System.out.println("Bill Generated Successfully!");
        System.out.println("Total Amount: " + total);
    }
}