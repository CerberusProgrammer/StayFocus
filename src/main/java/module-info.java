module com.example.stayfocus {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.stayfocus to javafx.fxml;
    exports com.example.stayfocus;
}