package com.example.prog_tech_lab2.task2;

import com.example.prog_tech_lab2.Shape;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Task2Controller {

    @FXML private Canvas canvas;
    @FXML private TextField tfNumber;
    @FXML private ColorPicker colorpicker;
    @FXML private ColorPicker fillPicker;
    @FXML private Label lblInfo;

    private final ShapeFactory factory = new ShapeFactory();

    @FXML
    public void initialize() {
        colorpicker.setValue(Color.DARKBLUE);     // цвет контура
        fillPicker.setValue(Color.TRANSPARENT);   // по умолчанию без заливки
        tfNumber.setText("0");
        lblInfo.setText("Введите число 0..4 и нажмите «Выполнить»");
    }

    @FXML
    public void onDraw(ActionEvent e) {
        int n;
        try {
            n = Integer.parseInt(tfNumber.getText().trim());
        } catch (Exception ex) {
            lblInfo.setText("Ошибка: введите целое число от 0 до 4");
            return;
        }

        Shape shape = factory.getShape(n, colorpicker.getValue());
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        if (shape == null) {
            lblInfo.setText("Неизвестный номер: " + n + ". Допустимо 0..4");
            return;
        }

        shape.setPosition(canvas.getWidth() / 2, canvas.getHeight() / 2);
        shape.setFillColor(fillPicker.getValue());
        shape.setStrokeWidth(3);
        shape.draw(gc);
        lblInfo.setText("Нарисован: " + shape.discriptor() + "  (номер " + n + ")");
    }

    @FXML
    public void goToTask1(ActionEvent e) throws Exception {
        switchScene(e, "/task1.fxml");
    }

    @FXML
    public void goToTask3(ActionEvent e) throws Exception {
        switchScene(e, "/task3.fxml");
    }

    @FXML
    public void goToTask4(ActionEvent e) throws Exception {
        switchScene(e, "/task4.fxml");
    }

    private void switchScene(ActionEvent e, String fxml) throws Exception {
        Stage stage = (Stage) ((Button) e.getSource()).getScene().getWindow();
        stage.setScene(new Scene(new FXMLLoader(getClass().getResource(fxml)).load(), 900, 650));
    }
}