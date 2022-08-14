module ca.georgiancollege.comp1011m2022finaltest {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens ca.georgiancollege.comp1011m2022finaltest to javafx.fxml;
    exports ca.georgiancollege.comp1011m2022finaltest;
}