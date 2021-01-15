package model;

public class Invoice {

    //Fields
    private int invoiceId;
    private String date;
    private int customer;

    public Invoice(int invoiceId, String date, int customer) {
        this.invoiceId = invoiceId;
        this.date = date;
        this.customer = customer;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCustomer() {
        return customer;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }
}
