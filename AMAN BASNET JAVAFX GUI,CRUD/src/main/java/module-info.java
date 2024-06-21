module com.example.csd214lab2nawaz {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.csd214lab2nawaz to javafx.fxml;
    exports com.example.csd214lab2nawaz;
}