module com.umgc.multiplebounceball {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.umgc.multiplebounceball to javafx.fxml;
    exports com.umgc.multiplebounceball;
}