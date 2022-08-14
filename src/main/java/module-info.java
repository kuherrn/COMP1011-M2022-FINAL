module ca.georgiancollege.comp1011m2022finaltest {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.google.gson;
    requires java.net.http;

    opens ca.georgiancollege.comp1011m2022finaltest to javafx.fxml, com.google.gson;
    exports ca.georgiancollege.comp1011m2022finaltest;
}