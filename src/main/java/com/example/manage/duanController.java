package com.example.manage;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.HashMap;
import java.util.Map;

public class duanController {

   @FXML
   private TableView<Map<String, String>> duan;

   @FXML
   private TableColumn<Map<String, String>, String> idColumn;

   @FXML
   private TableColumn<Map<String, String>, String> tenduan;

   @FXML


   private ObservableList<Map<String, String>> employeeData;

   @FXML
   public void initialize() {
      idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get("id")));
      tenduan.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get("tenduan")));


      employeeData = FXCollections.observableArrayList();
      duan.setItems(employeeData);

      loadEmployeeDataFromDatabase();
   }

   private void loadEmployeeDataFromDatabase() {
      DatabaseUtil.loadEmployeeDataFromDatabase(employeeData);
   }
}
