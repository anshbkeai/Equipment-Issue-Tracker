package com.anshbkeai.issuetrackermaven.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.anshbkeai.issuetrackermaven.pojo.ROLE;
import com.anshbkeai.issuetrackermaven.service.JwtService;

public class AppUserRepository {
    private final Connection connection; 
    private final JwtService jwtService;
    public AppUserRepository() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://192.168.1.5:3306/issuetracker","root","root");
            jwtService = new JwtService();
            System.out.println("Database connection established successfully.");
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void signup(String username, String password , String role , String authMode) {
        try {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            var statement = connection.prepareStatement("INSERT INTO app_user\n" + //
                                "(user_id, username, password, role, auth_mode)\n" + //
                                "VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, UUID.randomUUID().toString());
            statement.setString(2, username);
            statement.setString(3, encoder.encode(password));
            statement.setString(4, role);
            statement.setString(5, authMode);
            statement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String login(String username, String password) {
        try {
            var statement = connection.prepareStatement("SELECT * FROM app_user WHERE username = ? ");
            statement.setString(1, username);
            var resultSet = statement.executeQuery();
            //System.out.println("ResultSet: " + resultSet.next() + " " + username + " " + password); ;
            if (resultSet.next()) {
                String storedPassword = resultSet.getString("password");
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                if (encoder.matches(password, storedPassword)) {
                    return jwtService.generateJwt(username, ROLE.USER);
                }
                else {
                    return "Invalid password"; // Invalid password
                }
            } else {
                return "User not found"; // User not found
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
