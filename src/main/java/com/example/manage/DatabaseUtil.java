package com.example.manage;

import java.sql.*;

public class DatabaseUtil {
    private static final String URL = "jdbc:sqlserver://localhost\\DESKTOP-IGAJH3M:1434;database=hoai; trustServerCertificate=true";
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
}
