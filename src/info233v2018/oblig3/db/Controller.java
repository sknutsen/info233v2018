package db;

import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.*;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private Model model;

    //Address input fields
    @FXML TextField addIdField;
    @FXML TextField addStrNumField;
    @FXML TextField addStrNamField;
    @FXML TextField addPosCodField;
    @FXML TextField addPosTowField;

    //Category input fields
    @FXML TextField catIdField;
    @FXML TextField catNamField;

    //Customer input fields
    @FXML TextField cusIdField;
    @FXML TextField cusNamField;
    @FXML TextField cusAddField;
    @FXML TextField cusPhNrField;
    @FXML TextField cusBilAccField;

    //Invoice input fields
    @FXML TextField invIdField;
    @FXML TextField invCusIdField;
    @FXML TextField invDateField;

    //Invoice Items input fields
    @FXML TextField invItInvIdField;
    @FXML TextField invItProdIdField;

    //Product input fields
    @FXML TextField prodIdField;
    @FXML TextField prodNamField;
    @FXML TextField prodDescField;
    @FXML TextField prodPriceField;
    @FXML TextField prodCatIdField;

    //Invoice tab labels
    @FXML Label customerNameLabel1;
    @FXML Label customerNameLabel2;
    @FXML Label customerStreetLabel1;
    @FXML Label customerStreetLabel2;
    @FXML Label customerPostalLabel1;
    @FXML Label customerPostalLabel2;
    @FXML Label customerPhoneNumberLabel;
    @FXML Label customerAccountLabel;
    @FXML Label customerIdLabel;
    @FXML Label invoiceIdLabel;
    @FXML Label invoiceDateLabel;
    @FXML Label totalPriceLabel;

    //Table
    @FXML TableView table;
    @FXML TableView invoiceTable;
    @FXML TableColumn col1;
    @FXML TableColumn col2;
    @FXML TableColumn col3;
    @FXML TableColumn col4;
    @FXML TableColumn col5;

    //ListViews
    @FXML ListView productIdListView;
    @FXML ListView productDescriptionListView;
    @FXML ListView productNameListView;
    @FXML ListView productCategoryListView;
    @FXML ListView productPriceListView;

    @FXML MenuItem selectInvoiceDropDown1;

    public Controller() {
        DBAdapter dba = new DBAdapter();
        model = new Model(dba);
    }

    public void insertAll() {
        model.insert("address", addIdField.getText() + ", " + addStrNumField.getText() + ", '" +
                addStrNamField.getText() + "', " + addPosCodField.getText() + ", '" + addPosTowField.getText() + "'");
        model.insert("category", catIdField.getText() + ", " + catNamField.getText());
        model.insert("customer", cusIdField.getText() + ", " + cusNamField.getText() + ", " +
                cusAddField.getText() + ", " + cusPhNrField.getText() + ", " + cusBilAccField.getText());
        model.insert("invoice", invIdField.getText() + ", " + invCusIdField.getText() + ", " + invDateField.getText());
        model.insert("invoice_items", invItInvIdField.getText() + ", " + invItProdIdField.getText());
        model.insert("product", prodIdField.getText() + ", " + prodNamField.getText() + ", " +
                prodDescField.getText() + ", " + prodPriceField.getText() + ", " + prodCatIdField.getText());
    }

    public void insertAddress() {
        model.insert("address", addIdField.getText() + ", " + addStrNumField.getText() + ", " +
                addStrNamField.getText() + ", " + addPosCodField.getText() + ", " + addPosTowField.getText());
    }

    public void insertCategory() {
        model.insert("category", catIdField.getText() + ", " + catNamField.getText());
    }

    public void insertCustomer() {
        model.insert("customer", cusIdField.getText() + ", " + cusNamField.getText() + ", " +
                cusAddField.getText() + ", " + cusPhNrField.getText() + ", " + cusBilAccField.getText());
    }

    public void insertInvoice() {
        model.insert("invoice", invIdField.getText() + ", " + invCusIdField.getText() + ", " + invDateField.getText());

    }

    public void insertInvoiceItems() {
        model.insert("invoice_items", invItInvIdField.getText() + ", " + invItProdIdField.getText());
    }

    public void insertProduct() {
        model.insert("product", prodIdField.getText() + ", " + prodNamField.getText() + ", " +
                prodDescField.getText() + ", " + prodPriceField.getText() + ", " + prodCatIdField.getText());
    }

    public void getInvoiceData(int id) {
            String[] names = model.getCustomerName();
            customerNameLabel1.setText(names[id]);
            customerNameLabel2.setText(customerNameLabel1.getText());
            String[] rsStreetNames = model.getStreetName();
            String[] rsStreetNums = model.getStreetNumber();
            customerStreetLabel1.setText(rsStreetNames[id] + " " + rsStreetNums[id]);
            customerStreetLabel2.setText(customerStreetLabel1.getText());
            String[] rsPC = model.getPostalCode();
            String[] rsPT = model.getPostalTown();
            customerPostalLabel1.setText(rsPC[id] + " " + rsPT[id]);
            customerPostalLabel2.setText(customerPostalLabel1.getText());
            String[] rsPN = model.getPhoneNumber();
            customerPhoneNumberLabel.setText("Phonenumber: " + rsPN[id]);
            String[] rsBA = model.getBillingAccount();
            customerAccountLabel.setText("Account: " + rsBA[id]);
            Integer[] rsCId = model.getCustomerId();
            customerIdLabel.setText("Customer: " + rsCId[id]);
            Integer[] rsIId = model.getInvoiceId();
            invoiceIdLabel.setText("Invoice: " + rsIId[id]);
            String[] rsD = model.getDate();
            invoiceDateLabel.setText("Date: " + rsD[id]);
            Float[] nums = model.getPrice();
            Float total = 0.0f;
            for (Float f : nums) {
                total = f + total;
                Label label = new Label("" + f);
                productPriceListView.getItems().add(label);
            }
            totalPriceLabel.setText("Total: " + total);
        Integer[] ids = model.getProductId();
        for (Integer i : ids) {
            Label label = new Label("" + i);
            productIdListView.getItems().add(label);
        }
        String[] desc = model.getDescription();
        for (String s : desc) {
            Label label = new Label(s);
            productDescriptionListView.getItems().add(label);
        }
        String[] prodNames = model.getProductName();
        for (String s : prodNames) {
            Label label = new Label(s);
            productNameListView.getItems().add(label);
        }
        String[] catNames = model.getProductName();
        for (String s : catNames) {
            Label label = new Label(s);
            productCategoryListView.getItems().add(label);
        }
    }

    /*public void getAddressTable() throws SQLException {
        ResultSet rs = model.selectAllFromStringColumn("*", "address");
        ResultSetMetaData rsmd = rs.getMetaData();
        ObservableList observableList = FXCollections.observableArrayList();

         //
         // TABLE COLUMN ADDED DYNAMICALLY
         //
        table.getColumns().clear();

        for(int i=0 ; i < rsmd.getColumnCount(); i++){
            final int j = i;
            TableColumn col = new TableColumn(rsmd.getColumnName(i + 1));//oppretter en ny col med navn
            col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param -> new SimpleStringProperty(param.getValue().get(j).toString()));

            table.getColumns().addAll(col);


        }

        *//**
         * Data added to ObservableList *
         **//*
        while (rs.next()) {
            //Iterate Row
            ObservableList<String> row = FXCollections.observableArrayList();
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                //Iterate Column
                row.add(rs.getString(i));
            }
            System.out.println("Row [1] added " + row);
            observableList.add(row);


        }

        table.setItems(observableList);

    }*/

    /*private void populateTable() throws SQLException {
        ResultSet rs ;
        ResultSetMetaData rsmd = rs.getMetaData();
        ObservableList observableList = FXCollections.observableArrayList();



        *//**
         * TABLE COLUMN ADDED DYNAMICALLY *
         **//*
        table.getColumns().clear();

        for(int i=0 ; i<rsmd.getColumnCount(); i++){
            final int j = i;
            TableColumn col = new TableColumn(rsmd.getColumnName(i+1));//oppretter en ny col med navn
            col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                    return new SimpleStringProperty(param.getValue().get(j).toString());
                }
            });

            table.getColumns().addAll(col);


        }

        *//**
         * Data added to ObservableList *
         **//*
        while (rs.next()) {
            //Iterate Row
            ObservableList<String> row = FXCollections.observableArrayList();
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                //Iterate Column
                row.add(rs.getString(i));
            }
            System.out.println("Row [1] added " + row);
            observableList.add(row);


        }

        table.setItems(observableList);

    }*/

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectInvoiceDropDown1.setOnAction((event1) -> {
            getInvoiceData(0);
        });
    }
}
