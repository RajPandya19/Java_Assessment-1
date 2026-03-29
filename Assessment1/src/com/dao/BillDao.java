package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.model.BillModel;
import com.util.DBUtil;

public class BillDao {

    // ✅ 1. ADD BILL
    public static boolean addBill(BillModel b) {
        try {
            Connection cn = new DBUtil().getConnectionData();

            if (cn == null) {
                System.out.println("Database Connection Failed!");
                return false;
            }

            String sql = "INSERT INTO bill(appointment_id, medicine_cost, tax, discount, total) VALUES(?,?,?,?,?)";
            PreparedStatement ps = cn.prepareStatement(sql);

            ps.setInt(1, b.getAppointmentId());
            ps.setDouble(2, b.getMedicineCost());
            ps.setDouble(3, b.getTax());
            ps.setDouble(4, b.getDiscount());
            ps.setDouble(5, b.getTotal());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ✅ 2. VIEW ALL BILLS (BASIC)
    public static ArrayList<BillModel> getAllBills() {

        ArrayList<BillModel> list = new ArrayList<>();

        try {
            Connection cn = new DBUtil().getConnectionData();

            String sql = "SELECT * FROM bill";
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                BillModel b = new BillModel();

                b.setAppointmentId(rs.getInt("appointment_id"));
                b.setMedicineCost(rs.getDouble("medicine_cost"));
                b.setTax(rs.getDouble("tax"));
                b.setDiscount(rs.getDouble("discount"));
                b.setTotal(rs.getDouble("total"));

                list.add(b);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // ✅ 3. VIEW BILL BY APPOINTMENT ID (FULL DETAILS 🔥)
    public static BillModel getBillByAppointmentId(int appointmentId) {

        BillModel b = null;

        try {
            Connection cn = new DBUtil().getConnectionData();

            String sql = "SELECT b.*, a.service_type, a.appointment_date, a.cost, p.name " +
                         "FROM bill b " +
                         "JOIN appointment a ON b.appointment_id = a.appointment_id " +
                         "JOIN patient p ON a.patient_id = p.patient_id " +
                         "WHERE b.appointment_id = ?";

            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, appointmentId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                b = new BillModel();

                b.setAppointmentId(rs.getInt("appointment_id"));
                b.setMedicineCost(rs.getDouble("medicine_cost"));
                b.setTax(rs.getDouble("tax"));
                b.setDiscount(rs.getDouble("discount"));
                b.setTotal(rs.getDouble("total"));

                // 🔥 PRINT COMPLETE BILL
                System.out.println("\n===== BILL DETAILS =====");
                System.out.println("Patient Name: " + rs.getString("name"));
                System.out.println("Service: " + rs.getString("service_type"));
                System.out.println("Date: " + rs.getString("appointment_date"));
                System.out.println("Appointment Cost: " + rs.getDouble("cost"));

                System.out.println("Medicine Cost: " + b.getMedicineCost());
                System.out.println("Tax: " + b.getTax());
                System.out.println("Discount: " + b.getDiscount());
                System.out.println("Total Bill: " + b.getTotal());
                System.out.println("----------------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return b;
    }
}