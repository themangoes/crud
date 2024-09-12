package com.mango.trycrud;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.mango.trycrud.HOMESCENEController.*;
import static com.mango.trycrud.mysqlConnector.connection;

public class EDITDATABASEController {

    static int ID;
    @FXML
    TextField CUSTOMERID;
    @FXML
    TextField NAME;
    @FXML
    TextField ITEMSBOUGHT;
    @FXML
    TextField PHONENUMBER;
    @FXML
    TextField JOINEDDATE;
    @FXML
    Label status;

    public EDITDATABASEController() throws SQLException {
    }

    @FXML
    protected void searchID (ActionEvent e) throws SQLException {
        ID = Integer.parseInt(CUSTOMERID.getText());
        String findCustomerQuery = "select * from customerinfo where ID = ?;";
        PreparedStatement findCustomer = connection.prepareStatement(findCustomerQuery);
        findCustomer.setInt(1, ID);
        ResultSet customerRecord = findCustomer.executeQuery();

        if(customerRecord.next()) {
            NAME.setText(customerRecord.getString(2));
            ITEMSBOUGHT.setText(customerRecord.getString(3));
            JOINEDDATE.setText(customerRecord.getString(4));
            PHONENUMBER.setText(customerRecord.getString(5));
            status.setText("Customer Found");
        }
        else{
            status.setText("Customer ID Not Found");
        }
    }

    @FXML
    protected void editSubmittedInfo (ActionEvent e) throws SQLException {

        try {
            String[] data = {
                    NAME.getText(),
                    ITEMSBOUGHT.getText(),
                    JOINEDDATE.getText(),
                    PHONENUMBER.getText()
            };
            String editQuery = "update customerinfo set Name = ?, ItemsBought = ?, JoinedDate = ?, PhoneNumber =? where ID = ?;";
            PreparedStatement editRow = connection.prepareStatement(editQuery);
            editRow.setString(1, data[0]);
            editRow.setInt(2, Integer.parseInt(data[1]));
            editRow.setDate(3, Date.valueOf(data[2]));
            editRow.setInt(4, Integer.parseInt(data[3]));
            editRow.setInt(5, ID);
            int temp = editRow.executeUpdate();
            status.setText("Successfully Updated Record");
        } catch (Exception exc){
            exc.printStackTrace();
            status.setText("Something Went Wrong..");
        }
    }

    @FXML
    protected void returnToHome (ActionEvent e){
        stage.setScene(homeScene);
        stage.show();
    }
}
