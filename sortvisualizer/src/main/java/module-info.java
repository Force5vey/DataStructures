module umgc {
    requires javafx.controls;
    requires javafx.fxml;

    opens umgc to javafx.fxml;
    opens umgc.bigfilesort to umgc.bigfilesort;

    exports umgc;
    exports umgc.bigfilesort;
}
