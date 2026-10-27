module com.example.vectoreditor {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.vectoreditor.ChristmasTree to javafx.fxml;


    exports com.example.vectoreditor.ChristmasTree;
    exports com.example.vectoreditor.VectorEditor;
    opens com.example.vectoreditor.VectorEditor to javafx.fxml;
}