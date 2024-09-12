package com.mango.trycrud;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.mango.trycrud.mysqlConnector.connection;
import static com.mango.trycrud.HOMESCENEController.homeScene;
import static com.mango.trycrud.HOMESCENEController.stage;

public class INSERTRECORDSController {
    @FXML
    TextField CUSTOMERID;
    @FXML
    TextField NAME;
    @FXML
    TextField ITEMSBOUGHT;
    @FXML
    TextField JOINEDDATE;
    @FXML
    TextField PHONENUMBER;
    @FXML
    Label status;

    @FXML
    protected void insertRecord (ActionEvent e) throws SQLException {
        try {
            String insertQuery = "insert into customerinfo values(?, ?, ?, ?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
            insertStatement.setInt(1, Integer.parseInt(CUSTOMERID.getText()));
            insertStatement.setString(2, NAME.getText());
            insertStatement.setInt(3, Integer.parseInt(ITEMSBOUGHT.getText()));
            insertStatement.setDate(4, Date.valueOf(JOINEDDATE.getText()));
            insertStatement.setInt(5, Integer.parseInt(PHONENUMBER.getText()));
            insertStatement.executeUpdate();
            status.setText("Successfully Inserted Record");
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
