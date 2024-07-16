package com.example.manage;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.HashMap;
import java.util.Map;

import static com.example.manage.DatabaseUtil.deleteduan;
import static com.example.manage.DatabaseUtil.themduan;


public class duanController {

   @FXML
   private TableView<Map<String, String>> duan;

   @FXML
   private TableColumn<Map<String, String>, String> idColumn;

   @FXML
   private TableColumn<Map<String, String>, String> tenduan;
   @FXML
   private TableColumn<Map<String, String>, String> tennv;
   @FXML
   private ObservableList<Map<String, String>> duanData;

   @FXML
   private TextField idduanTextField;

   @FXML
   private TextField tenduanTextField;

   @FXML
   private TextField tennvTextField;

   @FXML
   public void initialize() {
      idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get("id")));
      tenduan.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get("tenduan")));
      tennv.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get("tennv")));

      duanData = FXCollections.observableArrayList();
      duan.setItems(duanData);

      loadduanData();
   }

   private void loadduanData() {
      DatabaseUtil.loadduanData(duanData);
   }



   public void them(javafx.event.ActionEvent event) {
if (idduanTextField.getText().isEmpty() || tenduanTextField.getText().isEmpty() || tennvTextField.getText().isEmpty()) {
   showErrorAlert("Vui lòng điền đầy đủ thông tin.");
   return;
}
    Map<String, String> newProject = new HashMap<>();
      newProject.put("id", idduanTextField.getText());
      newProject.put("tenduan", tenduanTextField.getText());
      newProject.put("tennv", tennvTextField.getText());
           duanData.add(newProject);

      // Add to database
      themduan(newProject);
   }

   public void delete(ActionEvent event) {
      Map<String, String> selectedItem = duan.getSelectionModel().getSelectedItem();

      if (selectedItem != null) {
         // Remove from TableView
         duanData.remove(selectedItem);

         // Remove from database (assuming you have a method to delete from database)
         deleteduan(selectedItem);
      }
   }
   private void showduanDetails(Map<String, String> duan) {
      if (duan != null) {
         idduanTextField.setText(duan.get("id"));
         tenduanTextField.setText(duan.get("tenduan"));
         tennvTextField.setText(duan.get("tennv"));
      } else {
         // If the row selection is cleared, clear the text fields
         idduanTextField.clear();
         tenduanTextField.clear();
         tennvTextField.clear();
      }
   }
   private void showErrorAlert(String message) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Lỗi");
      alert.setHeaderText(null);
      alert.setContentText(message);
      alert.showAndWait();
   }
   }
