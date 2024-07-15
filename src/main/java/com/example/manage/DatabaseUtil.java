package com.example.manage;

import javafx.collections.ObservableList;
import jdk.dynalink.beans.StaticClass;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DatabaseUtil {
    private static final String URL = "jdbc:sqlserver://localhost:1434;databaseName=hoai;trustServerCertificate=true";
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
    public static void loadEmployeeDataFromDatabase(ObservableList<Map<String, String>> employeeData) {


            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {


                try (Statement statement = connection.createStatement();
                     ResultSet rs = statement.executeQuery("SELECT * FROM duan")) {

                    while (rs.next()) {
                        Map<String, String> row = new HashMap<>();
                        row.put("id", rs.getString("duanid"));
                        row.put("tenduan", rs.getString("tenduan"));


                        employeeData.add(row);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }    }


