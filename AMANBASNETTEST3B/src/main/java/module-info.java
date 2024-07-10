module com.example.rajin {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    requires java.sql;

    opens com.example.rajin to javafx.fxml;
    exports com.example.rajin;
}