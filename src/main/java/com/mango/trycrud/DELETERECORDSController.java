package com.mango.trycrud;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.util.ArrayList;

import static com.mango.trycrud.mysqlConnector.connection;
import static com.mango.trycrud.HOMESCENEController.homeScene;
import static com.mango.trycrud.HOMESCENEController.stage;

public class DELETERECORDSController {
    static int ID;
    @FXML
    TextField CUSTOMERID;
    @FXML
    Label status;
    @FXML
    TableView<rowData> CUSTOMERINFO;
    @FXML
    TableColumn<rowData, Integer> CUSTOMERIDCOL;
    @FXML
    TableColumn<rowData, String> CUSTOMERNAME;
    @FXML
    TableColumn<rowData, Integer> ITEMSBOUGHT;
    @FXML
    TableColumn<rowData, Date> JOINEDDATE;
    @FXML
    TableColumn<rowData, Integer> PHONENUMBER;

    private void fetchTableData(int i) throws SQLException {
        if (i == 1) {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from customerinfo where ID = " + ID + ";");
            ArrayList<rowData> arrayListRows = new ArrayList<>();
            while (result.next()) {
                arrayListRows.add(
                        new rowData(
                                result.getString(2),
                                result.getInt(1),
                                result.getInt(3),
                                result.getInt(5),
                                result.getDate(4)
                        )
                );
            }
            CUSTOMERIDCOL.setCellValueFactory(new PropertyValueFactory<>("id"));
            CUSTOMERNAME.setCellValueFactory(new PropertyValueFactory<>("name"));
            ITEMSBOUGHT.setCellValueFactory(new PropertyValueFactory<>("itemsBought"));
            JOINEDDATE.setCellValueFactory(new PropertyValueFactory<>("joineddate"));
            PHONENUMBER.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
            ObservableList<rowData> rows = FXCollections.observableList(arrayListRows);
            CUSTOMERINFO.setItems(rows);
        }
        else
            CUSTOMERINFO.setItems(null);
    }

    @FXML
    protected void findCustomer (ActionEvent e) throws SQLException {
        ID = Integer.parseInt(CUSTOMERID.getText());
        String findCustomerQuery = "select * from customerinfo where ID = ?";
        PreparedStatement findCustomerStatement = connection.prepareStatement(findCustomerQuery);
        findCustomerStatement.setInt(1,ID);
        ResultSet customerRecord = findCustomerStatement.executeQuery();
        if (customerRecord.next()){
            status.setText("Customer Found");
            fetchTableData(1);
        } else {
            status.setText("Customer Not Found");
            fetchTableData(0);
        }
    }

    @FXML
    protected void deleteRecord (ActionEvent e) throws SQLException {
        try {
            String deleteRecordQuery = "delete from customerinfo where ID = ?";
            PreparedStatement deleteRecordStatement = connection.prepareStatement(deleteRecordQuery);
            deleteRecordStatement.setInt(1, ID);
            int temp = deleteRecordStatement.executeUpdate();
            status.setText("Successfully Deleted Record");
        } catch (Exception exc){
            status.setText("Something Went Wrong..");
        }

    }

    @FXML
    protected void returnToHome (ActionEvent e){
        stage.setScene(homeScene);
        stage.show();
    }
}
