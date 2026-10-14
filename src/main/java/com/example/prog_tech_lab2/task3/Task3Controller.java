package com.example.prog_tech_lab2.task3;

import com.example.prog_tech_lab2.Shape;
import com.example.prog_tech_lab2.task1.Circle;
import com.example.prog_tech_lab2.task1.Rectangle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Task3Controller {

    @FXML private Canvas canvas;
    @FXML private Label lblInfo;

    private final List<Shape> shapes = new ArrayList<>();
    private final MemoSelect caretaker = new MemoSelect();
    private Memento temp;
    private Shape dragging;

    @FXML
    public void initialize() {
        // стартовый набор фигур — используем классы из Task 1
        shapes.add(new Rectangle(Color.BLACK, Color.RED, 150, 150, 100, 80));
        shapes.add(new Circle(Color.BLACK, Color.RED, 400, 200, 45));
        shapes.add(new Rectangle(Color.BLACK, Color.RED, 650, 150, 120, 80));
        shapes.add(new Circle(Color.BLACK, Color.RED,250, 400, 45));

        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, this::onBegin);
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, this::onDrag);
        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, this::onEnd);

        redraw();
        lblInfo.setText("Фигур: " + shapes.size());
    }

    private void redraw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (Shape s : shapes) s.draw(gc);
    }

    public void onBegin(MouseEvent e) {
        Shape hit = null;
        for (int i = shapes.size() - 1; i >= 0; i--) {
            if (shapes.get(i).contains(e.getX(), e.getY())) { hit = shapes.get(i); break; }
        }
        if (hit == null) return;

        dragging = hit;
        temp = new Memento(hit);
        caretaker.push(temp);
        temp.initState();

        // наверх по z-order
        shapes.remove(hit);
        shapes.add(hit);

        redraw();
        lblInfo.setText("Выбрана фигура. Снимков: " + caretaker.size());
    }

    public void onDrag(MouseEvent e) {
        if (temp == null || dragging == null) return;
        dragging.setPosition(e.getX(), e.getY());
        redraw();
    }

    public void onEnd(MouseEvent e) {
        if (temp == null) return;
        // восстановить свойства из снимка
        dragging.setStrokeWidth(temp.getWidth());
        dragging.setFillColor(temp.getFillColor());
        dragging.setPosition(e.getX(), e.getY());
        temp = null;
        dragging = null;
        redraw();
        lblInfo.setText("Фигура перемещена. Снимков: " + caretaker.size());
    }

    @FXML
    public void onUndo(ActionEvent e) {
        Memento m = caretaker.poll();
        if (m == null) { lblInfo.setText("Снимков нет"); return; }
        Shape s = m.getState();
        s.setStrokeWidth(m.getWidth());
        s.setFillColor(m.getFillColor());
        s.setPosition(m.getX(), m.getY());
        redraw();
        lblInfo.setText("Восстановлено. Осталось снимков: " + caretaker.size());
    }

    @FXML
    public void goToTask1(ActionEvent e) throws Exception {
        Stage stage = (Stage) ((Button) e.getSource()).getScene().getWindow();
        stage.setScene(new Scene(new FXMLLoader(getClass().getResource("/task1.fxml")).load(), 900, 650));
    }

    @FXML
    public void goToTask2(ActionEvent e) throws Exception {
        Stage stage = (Stage) ((Button) e.getSource()).getScene().getWindow();
        stage.setScene(new Scene(new FXMLLoader(getClass().getResource("/task2.fxml")).load(), 900, 650));
    }
}