package model;

public class Customer {

    //Fields
    private int customerId;
    private String customerName;
    private int customerAddress;
    private String phoneNumber;
    private String billingAccount;

    public Customer(int customerId, String customerName, int customerAddress, String phoneNumber, String billingAccount) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.phoneNumber = phoneNumber;
        this.billingAccount = billingAccount;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(int customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBillingAccount() {
        return billingAccount;
    }

    public void setBillingAccount(String billingAccount) {
        this.billingAccount = billingAccount;
    }
}
