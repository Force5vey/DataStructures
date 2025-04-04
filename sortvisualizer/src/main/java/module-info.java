module umgc {
    requires javafx.controls;
    requires javafx.fxml;

    opens umgc to javafx.fxml;
    exports umgc;
}
