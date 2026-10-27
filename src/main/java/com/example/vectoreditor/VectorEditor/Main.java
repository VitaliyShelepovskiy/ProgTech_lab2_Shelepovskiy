package com.example.vectoreditor.VectorEditor;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        URL url = getClass().getResource("/vectorEditor.fxml");
        if (url == null) {
            throw new IllegalStateException(
                    "vectorEditor.fxml не найден в classpath. " +
                            "Проверьте, что файл лежит в src/main/resources/vectorEditor.fxml"
            );
        }
        FXMLLoader loader = new FXMLLoader(url);
        Scene scene = new Scene(loader.load(), 1200, 760);
        stage.setTitle("Vector Editor — Decorator");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

