package com.mango.trycrud.Controllers;


import com.mango.trycrud.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

public class HOMESCENEController {
    @FXML
    public static Scene editDatabaseScene;
    @FXML
    public static Scene deleteRecordsScene;
    @FXML
    public static Scene insertRecordsScene;
    @FXML
    TableView<rowData> CUSTOMERINFO;
    @FXML
    TableColumn<rowData, Integer> CUSTOMERID;
    @FXML
    TableColumn<rowData, String> CUSTOMERNAME;
    @FXML
    TableColumn<rowData, Integer> ITEMSBOUGHT;
    @FXML
    TableColumn<rowData, Date> JOINEDDATE;
    @FXML
    TableColumn<rowData, Integer> PHONENUMBER;
    @FXML
    Button loadOrRefresh;

    public static Scene homeScene;
    public static Parent root;
    public static Stage stage;

    @FXML
    protected void refreshDatabase (ActionEvent e) throws SQLException {
        fetchTableData();
        loadOrRefresh.setText("REFRESH");
    }

    private void fetchTableData() throws SQLException {
        CUSTOMERID.setCellValueFactory(new PropertyValueFactory<>("id"));
        CUSTOMERNAME.setCellValueFactory(new PropertyValueFactory<>("name"));
        ITEMSBOUGHT.setCellValueFactory(new PropertyValueFactory<>("itemsBought"));
        JOINEDDATE.setCellValueFactory(new PropertyValueFactory<>("joineddate"));
        PHONENUMBER.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        CUSTOMERINFO.setItems(mysqlConnector.fetchTableData());
    }

    @FXML
    protected void insertRecordsButton(ActionEvent e) throws IOException {
        if (insertRecordsScene == null){
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("INSERTRECORDS.fxml"));
            insertRecordsScene = new Scene(loader.load());
        }
        storeHomeScene(e);
        stage.setScene(insertRecordsScene);
        stage.show();
    }

    @FXML
    protected void deleteRecordsButton(ActionEvent e) throws IOException {
        if (deleteRecordsScene == null){
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("DELETERECORDS.fxml"));
            deleteRecordsScene = new Scene(loader.load());
        }
        storeHomeScene(e);
        stage.setScene(deleteRecordsScene);
        stage.show();
    }

    @FXML
    protected void editDatabaseButton(ActionEvent e) throws IOException {
        if (editDatabaseScene == null){
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("EDITDATABASE.fxml"));
            editDatabaseScene = new Scene(loader.load());
        }
        storeHomeScene(e);
        stage.setScene(editDatabaseScene);
        stage.show();
    }

    private void storeHomeScene(ActionEvent e){
        if (homeScene == null){
            homeScene = ((Node)e.getSource()).getScene();
            root = ((Node)e.getSource()).getParent();
            homeScene.setRoot(root);
            stage = (Stage)homeScene.getWindow();
        }
    }
}