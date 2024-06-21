package com.example.test2b;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField username;
    @FXML
    private PasswordField passwordField;

    // Static credentials
    private final String staticUsername = "AMAN";
    private final String staticPassword = "123";

    @FXML
    protected void onHelloButtonClick() {
        String enteredUsername = username.getText();
        String enteredPassword = passwordField.getText();

        if (enteredUsername.isEmpty() || enteredPassword.isEmpty()) {
            welcomeText.setText("Please Provide Username or Password.");
        } else if (enteredUsername.equals(staticUsername) && enteredPassword.equals(staticPassword)) {
            welcomeText.setText("Success!!!");
        } else {
            welcomeText.setText("Sorry, Invalid Username or Password.");
        }
    }
}