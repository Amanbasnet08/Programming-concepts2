module com.example.logintest2b {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires jdk.compiler;

    opens com.example.logintest2b to javafx.fxml;
    exports com.example.logintest2b;
}