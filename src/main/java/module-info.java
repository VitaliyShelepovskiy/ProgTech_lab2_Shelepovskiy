module com.example.vectoreditor {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.vectoreditor.ChristmasTree to javafx.fxml;
    opens com.example.vectoreditor.VectorEditor to javafx.fxml;

    exports com.example.vectoreditor.ChristmasTree;
    exports com.example.vectoreditor.VectorEditor;

}