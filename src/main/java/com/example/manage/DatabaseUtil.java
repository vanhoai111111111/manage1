package com.example.manage;

import javafx.collections.ObservableList;
import jdk.dynalink.beans.StaticClass;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DatabaseUtil {
    private static final String URL = "jdbc:sqlserver://localhost\\DESKTOP-IGAJH3M:1433;database=hoai1;trustServerCertificate=true";
    private static final String USER = "sa";
    private static final String PASSWORD = "123@";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static boolean validateLogin(String username, String password) {
        String query = "SELECT * FROM admin WHERE taikhoan = ? AND matkhau = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();  // Trả về true nếu có bản ghi khớp
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static void loadduanData(ObservableList<Map<String, String>> duanData) {


            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {


                try (Statement statement = connection.createStatement();
                     ResultSet rs = statement.executeQuery("SELECT * FROM duan")) {

                    while (rs.next()) {
                        Map<String, String> row = new HashMap<>();
                        row.put("id", rs.getString("duanid"));
                        row.put("tenduan", rs.getString("tenduan"));
                        row.put("tennv", rs.getString("Tennv"));

                        duanData.add(row);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    public static void themduan(Map<String, String> project) {


        String query = "INSERT INTO duan (duanid, tenduan, tennv) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, project.get("id"));
            preparedStatement.setString(2, project.get("tenduan"));
            preparedStatement.setString(3, project.get("tennv"));

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
   protected static void deleteduan(Map<String, String> project) {

        String query = "DELETE FROM duan WHERE duanid = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, project.get("id"));
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}


