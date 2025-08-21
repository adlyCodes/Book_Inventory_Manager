module com.example.mysqlconnector1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.mysqlconnector1 to javafx.fxml;
    exports com.example.mysqlconnector1;
    exports com.example.demoo;
    opens com.example.demoo to javafx.fxml;
    exports com.example.demo;
    opens com.example.demo to javafx.fxml;
}