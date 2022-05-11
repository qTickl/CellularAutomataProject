module com.qtickl.javafx_1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.qtickl.javafx_1 to javafx.fxml;
    exports com.qtickl.javafx_1;
}