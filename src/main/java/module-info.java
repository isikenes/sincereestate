module com.g5.sincereestate {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;


    opens com.g5.sincereestate to javafx.fxml;
    exports com.g5.sincereestate;
    exports com.g5.admin;
    opens com.g5.admin to javafx.fxml;
}