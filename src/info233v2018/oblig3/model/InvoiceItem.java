package model;

public class InvoiceItem {

    //Fields
    private int customer;
    private int invoice;
    private int product;

    public InvoiceItem(int customer, int invoice, int product) {
        this.customer = customer;
        this.invoice = invoice;
        this.product = product;
    }

    public int getCustomer() {
        return customer;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }

    public int getInvoice() {
        return invoice;
    }

    public void setInvoice(int invoice) {
        this.invoice = invoice;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }
}
