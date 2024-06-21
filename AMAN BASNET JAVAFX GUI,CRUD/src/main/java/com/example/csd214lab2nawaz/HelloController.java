package com.example.csd214lab2nawaz;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;
import java.sql.*;

public class HelloController implements Initializable {
    public TableView<User> usertable;
    public TableColumn<User,Integer> id;
    public TableColumn <User,String> name;
    public TableColumn <User,String> email;
    public TableColumn <User,String> zipcode;
    public TextField sid;
    public TextField sname;
    public TextField semail;
    public TextField szipcode;
    @FXML
    private Label welcomeText;

    ObservableList<User> list = FXCollections.observableArrayList();

    @FXML
    protected void onHelloButtonClick() {
        fethdata();
    }

    private void fethdata() {
        list.clear();

        String jdbcUrl = "jdbc:mysql://localhost:3306/db_csd214_lab2";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
            // Execute a SQL query to retrieve data from the database
            String query = "SELECT * FROM usertable";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            // Populate the table with data from the database
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String zipcode = resultSet.getString("zipcode");
                usertable.getItems().add(new User(id, name, email, zipcode));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        id.setCellValueFactory(new PropertyValueFactory<User,Integer>("id"));
        name.setCellValueFactory(new PropertyValueFactory<User,String>("name"));
        email.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
        zipcode.setCellValueFactory(new PropertyValueFactory<User,String>("zipcode"));
        usertable.setItems(list);


    }

    public void InsertData(ActionEvent actionEvent) {



        String name = sname.getText();
        String email = semail.getText();
        String zipcode = szipcode.getText();




        String jdbcUrl = "jdbc:mysql://localhost:3306/db_csd214_lab2";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
            // Execute a SQL query to retrieve data from the database
            String query = "INSERT INTO `usertable`( `name`, `email`, `zipcode`) VALUES ('"+name+"','"+email+"','"+zipcode+"')";
            Statement statement = connection.createStatement();
            statement.execute(query);
            // Populate the table with data from the database

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void UpdateData(ActionEvent actionEvent) {
        String id = sid.getText();
        String name = sname.getText();
        String email = semail.getText();
        String zipcode = szipcode.getText();




        String jdbcUrl = "jdbc:mysql://localhost:3306/db_csd214_lab2";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
            // Execute a SQL query to retrieve data from the database
            String query = "UPDATE `usertable` SET `name`='"+name+"',`email`='"+email+"',`zipcode`='"+zipcode+"' WHERE id='"+id+"' ";
            Statement statement = connection.createStatement();
            statement.execute(query);
            // Populate the table with data from the database

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void DeleteData(ActionEvent actionEvent) {

        String id = sid.getText();




        String jdbcUrl = "jdbc:mysql://localhost:3306/db_csd214_lab2";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
            // Execute a SQL query to retrieve data from the database
            String query = "DELETE FROM `usertable` WHERE id='"+id+"'";
            Statement statement = connection.createStatement();
            statement.execute(query);
            // Populate the table with data from the database

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void LoadData(ActionEvent actionEvent) {

        String id = sid.getText();

        String jdbcUrl = "jdbc:mysql://localhost:3306/db_csd214_lab2";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
            // Execute a SQL query to retrieve data from the database
            String query = "SELECT * FROM usertable WHERE id='"+id+"'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            // Populate the table with data from the database
            while (resultSet.next()) {

                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String zipcode = resultSet.getString("zipcode");

                sname.setText(name);
                semail.setText(email);
                szipcode.setText(zipcode);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}