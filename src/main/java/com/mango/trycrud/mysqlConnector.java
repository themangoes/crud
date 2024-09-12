package com.mango.trycrud;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.util.ArrayList;

public class mysqlConnector {

    public static Connection connection;

    public static void connect() throws SQLException, ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/tryCrud";
        String user = "root";
        String pass = "password";
        connection = DriverManager.getConnection(url, user, pass);
    }

    public static ObservableList<rowData> fetchTableData() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("select * from customerinfo;");
        ArrayList<rowData> arrayListRows = new ArrayList<>();
        while (result.next()){
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
        return FXCollections.observableList(arrayListRows);
    }

    public static ObservableList<rowData> fetchTableDataWithID(int ID) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("select * from customerinfo where ID=" +ID+ ";");
        ArrayList<rowData> arrayListRows = new ArrayList<>();
        while (result.next()){
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
        return FXCollections.observableList(arrayListRows);
    }
}
