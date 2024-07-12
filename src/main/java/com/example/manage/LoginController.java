package com.example.manage;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleLoginButtonAction() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (DatabaseUtil.validateLogin(username, password)) {
            openMainPage();
        } else {
            showAlert("Login Failed", "Incorrect username or password");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void openMainPage() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/manage/main.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle("Main Page");
            stage.setScene(new Scene(root, 400, 300));
            stage.show();

            // Đóng cửa sổ đăng nhập
            Stage loginStage = (Stage) usernameField.getScene().getWindow();
            loginStage.close();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to open the main page.");
        }
    }
}
