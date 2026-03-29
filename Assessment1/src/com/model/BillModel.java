package com.model;
public class BillModel 
{
    private int billId;
    private int appointmentId;
    private double medicineCost;
    private double tax;
    private double discount;
    private double total;
    
	public int getBillId() {
		return billId;
	}
	public void setBillId(int billId) {
		this.billId = billId;
	}
	public int getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}
	public double getMedicineCost() {
		return medicineCost;
	}
	public void setMedicineCost(double medicineCost) {
		this.medicineCost = medicineCost;
	}
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}

}