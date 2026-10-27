module com.example.prog_tech_lab2 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.prog_tech_lab2 to javafx.fxml;
    opens com.example.prog_tech_lab2.task1 to javafx.fxml;
    opens com.example.prog_tech_lab2.task2 to javafx.fxml;
    opens com.example.prog_tech_lab2.task3 to javafx.fxml;
    opens com.example.prog_tech_lab2.task4 to javafx.fxml;
    exports com.example.prog_tech_lab2;
}