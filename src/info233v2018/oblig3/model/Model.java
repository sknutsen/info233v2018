package model;

import db.DBAdapter;

import java.sql.*;
import java.util.ArrayList;

public class Model {
    private DBAdapter adapter;

    public Model(DBAdapter dba) {
        adapter = dba;
    }

    public String[] selectAllFromStringColumn(String column, String tbl){
        String sql = "SELECT " + column + " FROM " + tbl + ";";
        ArrayList<String> list = new ArrayList<>();

        try (Connection conn = adapter.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                list.add(rs.getString(column));
            }

            return list.toArray(new String[list.size()]);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return new String[1];
    }

    public Integer[] selectAllFromIntColumn(String column, String tbl){
        String sql = "SELECT " + column + " FROM " + tbl + ";";
        ArrayList<Integer> list = new ArrayList<>();

        try (Connection conn = adapter.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                list.add(rs.getInt(column));
            }

            return list.toArray(new Integer[list.size()]);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return new Integer[1];
    }

    public Float[] selectAllFromFloatColumn(String column, String tbl){
        String sql = "SELECT " + column + " FROM " + tbl + ";";
        ArrayList<Float> list = new ArrayList<>();

        try (Connection conn = adapter.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                list.add(rs.getFloat(column));
            }

            return list.toArray(new Float[list.size()]);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return new Float[1];
    }

    public void insert (String tbl, String insertedTxt) {
        String sql = "INSERT INTO " + tbl + " VALUES (" + insertedTxt + ");";

        try (Connection conn = adapter.connect();
             Statement pstmt = conn.createStatement()) {
            pstmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void executeCustomSQL (String sql) {
        try (Connection conn = adapter.connect();
             Statement pstmt = conn.createStatement()) {
            pstmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Integer[] getAddressId() {
        return selectAllFromIntColumn("address_id", "address");
    }

    public String[] getStreetNumber() {
        return selectAllFromStringColumn("street_number", "address");
    }

    public String[] getStreetName() {
        return selectAllFromStringColumn("street_name", "address");
    }

    public String[] getPostalCode() {
        return selectAllFromStringColumn("postal_code", "address");
    }

    public String[] getPostalTown() {
        return selectAllFromStringColumn("postal_town", "address");
    }

    public Integer[] getCategoryId() {
        return selectAllFromIntColumn("category_id", "category");
    }

    public String[] getCategoryName() {
        return selectAllFromStringColumn("category_name", "category");
    }

    public Integer[] getCustomerId() {
        return selectAllFromIntColumn("customer_id", "customer");
    }

    public String[] getCustomerName() {
        return selectAllFromStringColumn("customer_name", "customer");
    }

    public Integer[] getCustomerAddress() {
        return selectAllFromIntColumn("customer_name", "customer");
    }

    public String[] getPhoneNumber() {
        return selectAllFromStringColumn("phone_number", "customer");
    }

    public String[] getBillingAccount() {
        return selectAllFromStringColumn("billing_account", "customer");
    }

    public Integer[] getInvoiceId() {
        return selectAllFromIntColumn("invoice_id", "invoice");
    }

    public String[] getDate() {
        return selectAllFromStringColumn("dato", "invoice");
    }

    public Integer[] getInvoiceCustomer() {
        return selectAllFromIntColumn("customer", "invoice");
    }

    public Integer[] getInvoiceItemsInvoice() {
        return selectAllFromIntColumn("invoice", "invoice_items");
    }

    public Integer[] getInvoiceItemsProduct() {
        return selectAllFromIntColumn("product", "invoice_items");
    }

    public Integer[] getProductId() {
        return selectAllFromIntColumn("product_id", "product");
    }

    public String[] getProductName() {
        return selectAllFromStringColumn("product_name", "product");
    }

    public String[] getDescription() {
        return selectAllFromStringColumn("description", "product");
    }

    public Float[] getPrice() {
        return selectAllFromFloatColumn("price", "product");
    }

    public Integer[] getProductCategory() {
        return selectAllFromIntColumn("category", "product");
    }
}
