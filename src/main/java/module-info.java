module com.example.absolutelyfinalautootclickwork {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires java.net.http;
    requires unirest.java;


    opens com.example.absolutelyfinalautootclickwork to javafx.fxml;
    exports com.example.absolutelyfinalautootclickwork;
}