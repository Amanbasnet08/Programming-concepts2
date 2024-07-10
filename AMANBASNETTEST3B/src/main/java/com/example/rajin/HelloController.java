package com.example.rajin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    public TableView<PizzaOrder> pizzatable;
    public TableColumn<PizzaOrder, Integer> id;
    public TableColumn<PizzaOrder, String> customername;
    public TableColumn<PizzaOrder, String> mobilenumber;
    public TableColumn<PizzaOrder, String> pizzasize;
    public TableColumn<PizzaOrder, Integer> numberoftoppings;
    public TextField sid;
    public TextField scustomerName;
    public TextField smobileNumber;
    public TextField spizzaSize;
    public TextField snumtoppings;

    @FXML
    private Label welcomeText;

    ObservableList<PizzaOrder> list = FXCollections.observableArrayList();

    @FXML
    protected void onHelloButtonClick() {
        FetchData();
    }

    @FXML
    private void FetchData() {
        list.clear();

        String jdbcUrl = "jdbc:mysql://localhost:3306/pizza";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String query = "SELECT * FROM pizzabl";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String customerName = resultSet.getString("customername");
                String mobileNumber = resultSet.getString("mobilenumber");
                String pizzaSize = resultSet.getString("pizzasize");
                int numToppings = resultSet.getInt("numberoftoppings");
                pizzatable.getItems().add(new PizzaOrder(id, customerName, mobileNumber, pizzaSize, numToppings));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        customername.setCellValueFactory(new PropertyValueFactory<>("customername"));
        mobilenumber.setCellValueFactory(new PropertyValueFactory<>("mobilenumber"));
        pizzasize.setCellValueFactory(new PropertyValueFactory<>("pizzasize"));
        numberoftoppings.setCellValueFactory(new PropertyValueFactory<>("numberoftoppings"));
        pizzatable.setItems(list);
    }

    public void InsertData(ActionEvent actionEvent) {
        String customerName = scustomerName.getText();
        String mobileNumber = smobileNumber.getText();
        String pizzaSize = spizzaSize.getText();
        int numToppings = Integer.parseInt(snumtoppings.getText());

        String jdbcUrl = "jdbc:mysql://localhost:3306/pizza";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String query = "INSERT INTO pizzabl (customername, mobilenumber, pizzasize, numberoftoppings) VALUES ('" + customerName + "','" + mobileNumber + "','" + pizzaSize + "','" + numToppings + "')";
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void UpdateData(ActionEvent actionEvent) {
        int id = Integer.parseInt(sid.getText());
        String customerName = scustomerName.getText();
        String mobileNumber = smobileNumber.getText();
        String pizzaSize = spizzaSize.getText();
        int numToppings = Integer.parseInt(snumtoppings.getText());

        String jdbcUrl = "jdbc:mysql://localhost:3306/pizza";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String query = "UPDATE pizzabl SET customername = '" + customerName + "', mobilenumber = '" + mobileNumber + "', pizzasize = '" + pizzaSize + "', numberoftoppings = '" + numToppings + "' WHERE id = " + id;
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void DeleteData(ActionEvent actionEvent) {
        int id = Integer.parseInt(sid.getText());

        String jdbcUrl = "jdbc:mysql://localhost:3306/pizza";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String query = "DELETE FROM pizzabl WHERE id = " + id;
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void LoadData(ActionEvent actionEvent) {
        int id = Integer.parseInt(sid.getText());

        String jdbcUrl = "jdbc:mysql://localhost:3306/pizza";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String query = "SELECT * FROM pizzabl WHERE id = " + id;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                String customerName = resultSet.getString("customername");
                String mobileNumber = resultSet.getString("mobilenumber");
                String pizzaSize = resultSet.getString("pizzasize");
                int numToppings = resultSet.getInt("numberoftoppings");

                scustomerName.setText(customerName);
                smobileNumber.setText(mobileNumber);
                spizzaSize.setText(pizzaSize);
                snumtoppings.setText(String.valueOf(numToppings));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void CalculateTotal(ActionEvent actionEvent) {
        String pizzaSize = spizzaSize.getText();
        int numToppings = Integer.parseInt(snumtoppings.getText());
        double total = 0.0;

        // Define the base price for each pizza size
        double basePrice;
        switch (pizzaSize.toLowerCase()) {
            case "xl":
                basePrice = 15.00;
                break;
            case "l":
                basePrice = 12.00;
                break;
            case "m":
                basePrice = 10.00;
                break;
            case "s":
                basePrice = 8.00;
                break;
            default:
                // Handle invalid pizza size
                showAlert("Invalid pizza size", "Please enter a valid pizza size (XL, L, M, S).");
                return;
        }

        // Define the price for each topping
        double toppingPrice = 1.50;

        // Calculate the subtotal
        double subtotal = basePrice + (numToppings * toppingPrice);

        // Calculate the total cost with 15% HST
        double hst = subtotal * 0.15;
        total = subtotal + hst;

        // Show the total cost in an alert dialog
        showAlert("Total Cost", String.format("The total cost of the pizza order is $%.2f (including 15%% HST).", total));
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}