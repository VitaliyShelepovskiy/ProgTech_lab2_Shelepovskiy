package com.example.prog_tech_lab2.task1;

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

public class Task1Controller {

    @FXML private Canvas canvas;
    @FXML private ColorPicker colorpicker;
    @FXML private ColorPicker fillPicker;
    @FXML private TextField tfSize;
    @FXML private Label lblInfo;

    @FXML
    public void initialize() {
        colorpicker.setValue(Color.BLACK);        // цвет контура
        fillPicker.setValue(Color.LIGHTBLUE);     // цвет заливки
        tfSize.setText("100");
    }

    private double getSize() {
        try { return Double.parseDouble(tfSize.getText()); }
        catch (Exception e) { return 100; }
    }

    private double cx() { return canvas.getWidth() / 2; }
    private double cy() { return canvas.getHeight() / 2; }

    private void show(Shape s) {
        s.setFillColor(fillPicker.getValue());
        s.setStrokeWidth(2);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        s.draw(gc);
        lblInfo.setText("Последняя фигура: " + s);
        System.out.println(s);
    }

    @FXML public void onCircle(ActionEvent e) {
        show(new Circle(colorpicker.getValue(), cx(), cy(), getSize() / 2));
    }

    @FXML public void onEllipse(ActionEvent e) {
        show(new Ellipse(colorpicker.getValue(), cx(), cy(), getSize() / 2, getSize() / 4));
    }

    @FXML public void onRectangle(ActionEvent e) {
        show(new Rectangle(colorpicker.getValue(), cx(), cy(), getSize(), getSize() / 2));
    }

    @FXML public void onRoundedRectangle(ActionEvent e) {
        show(new RoundedRectangle(colorpicker.getValue(), cx(), cy(), getSize(), getSize() / 2, 25));
    }

    @FXML public void onSquare(ActionEvent e) {
        show(new Square(colorpicker.getValue(), cx(), cy(), getSize() / 2));
    }

    @FXML
    public void goToTask2(ActionEvent e) throws Exception {
        switchScene(e, "/task2.fxml");
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
