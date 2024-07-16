module com.example.manage {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires jdk.dynalink;
    requires java.desktop;

    opens com.example.manage to javafx.fxml;
    exports com.example.manage;
}