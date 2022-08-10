module com.example.pt07_2072008 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens com.example.pt07_2072008 to javafx.fxml;
    exports com.example.pt07_2072008;
    exports com.example.pt07_2072008.model;
    opens com.example.pt07_2072008.model to com.google.gson;
}