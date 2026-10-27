package com.example.prog_tech_lab2.task4;

import com.example.prog_tech_lab2.Shape;
import com.example.prog_tech_lab2.task1.Circle;
import com.example.prog_tech_lab2.task1.Rectangle;
import com.example.prog_tech_lab2.task2.ShapeFactory;
import com.example.prog_tech_lab2.task3.Memento;
import com.example.prog_tech_lab2.task3.MemoSelect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Task4Controller {

    @FXML private Canvas canvas;
    @FXML private ColorPicker fillPicker;
    @FXML private ColorPicker strokePicker;
    @FXML private TextField tfStrokeWidth;
    @FXML private Label lblInfo;
    @FXML private Label lblTool;

    private enum Tool { PENCIL, LINE, ELLIPSE, RECT, DRAG, LOGIC }
    private Tool currentTool = Tool.PENCIL;
    private int logicNumber = 0;

    private final List<Shape> shapes = new ArrayList<>();
    private final MemoSelect caretaker = new MemoSelect();
    private final ShapeFactory logicFactory = new ShapeFactory();

    private FreeShape preview;
    private Shape dragging;
    private double dragOffsetX, dragOffsetY;   // смещение курсора внутри фигуры
    private boolean dragWasMoved;              // был ли реальный сдвиг

    @FXML
    public void initialize() {
        fillPicker.setValue(Color.TRANSPARENT);
        strokePicker.setValue(Color.BLACK);
        tfStrokeWidth.setText("2");

        Shape r1 = new Rectangle(Color.BLACK, 150, 150, 100, 80);
        r1.setFillColor(Color.LIGHTBLUE);
        shapes.add(r1);

        Shape c1 = new Circle(Color.BLACK, 400, 200, 50);
        c1.setFillColor(Color.LIGHTYELLOW);
        shapes.add(c1);

        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED,  this::onPress);
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED,  this::onDrag);
        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, this::onRelease);

        redraw();
        updateToolLabel();
    }

    private double sw() {
        try { return Double.parseDouble(tfStrokeWidth.getText()); }
        catch (Exception e) { return 2; }
    }

    private void updateToolLabel() { lblTool.setText("Инструмент: " + currentTool); }

    // ─── инструменты ───
    @FXML public void toolPencil(ActionEvent e)  { currentTool = Tool.PENCIL;  updateToolLabel(); }
    @FXML public void toolLine(ActionEvent e)    { currentTool = Tool.LINE;    updateToolLabel(); }
    @FXML public void toolEllipse(ActionEvent e) { currentTool = Tool.ELLIPSE; updateToolLabel(); }
    @FXML public void toolRect(ActionEvent e)    { currentTool = Tool.RECT;    updateToolLabel(); }
    @FXML public void toolDrag(ActionEvent e)    { currentTool = Tool.DRAG;    updateToolLabel(); }

    @FXML public void logicOr(ActionEvent e)   { logicNumber = 0; currentTool = Tool.LOGIC; updateToolLabel(); }
    @FXML public void logicNor(ActionEvent e)  { logicNumber = 1; currentTool = Tool.LOGIC; updateToolLabel(); }
    @FXML public void logicNand(ActionEvent e) { logicNumber = 2; currentTool = Tool.LOGIC; updateToolLabel(); }
    @FXML public void logicAnd(ActionEvent e)  { logicNumber = 3; currentTool = Tool.LOGIC; updateToolLabel(); }
    @FXML public void logicNot(ActionEvent e)  { logicNumber = 4; currentTool = Tool.LOGIC; updateToolLabel(); }

    // ─── мышь ───
    public void onPress(MouseEvent e) {
        double mx = e.getX(), my = e.getY();

        switch (currentTool) {
            case PENCIL:
                // снимок ДО начала рисования
                caretaker.push(new Memento(shapes));
                preview = new FreeShape(strokePicker.getValue(), mx, my, sw());
                preview.setFillColor(fillPicker.getValue());
                shapes.add(preview);
                redraw();
                break;

            case LINE:
            case ELLIPSE:
            case RECT: {
                // снимок ДО начала рисования
                caretaker.push(new Memento(shapes));
                FreeShape.Kind kind = currentTool == Tool.LINE ? FreeShape.Kind.LINE
                        : currentTool == Tool.ELLIPSE ? FreeShape.Kind.ELLIPSE
                        : FreeShape.Kind.RECT;
                preview = new FreeShape(kind, strokePicker.getValue(), fillPicker.getValue(),
                        mx, my, mx, my, sw());
                shapes.add(preview);
                redraw();
                break;
            }

            case LOGIC: {
                // снимок ДО добавления
                caretaker.push(new Memento(shapes));
                Shape s = logicFactory.getShape(logicNumber, strokePicker.getValue());
                if (s == null) { caretaker.poll(); return; }
                s.setPosition(mx, my);
                s.setFillColor(fillPicker.getValue());
                s.setStrokeWidth(sw());
                shapes.add(s);
                redraw();
                break;
            }

            case DRAG: {
                Shape hit = hitTest(mx, my);
                if (hit == null) return;
                dragging = hit;
                // запоминаем смещение, чтобы фигура не «прыгала» под курсор
                dragOffsetX = hit.getX() - mx;
                dragOffsetY = hit.getY() - my;
                dragWasMoved = false;
                // снимок ДО перетаскивания
                caretaker.push(new Memento(shapes));
                // визуальное выделение
                hit.setStrokeWidth(hit.getStrokeWidth() + 2);
                hit.setStrokeColor(Color.RED);
                shapes.remove(hit);
                shapes.add(hit);
                redraw();
                break;
            }
        }
    }

    public void onDrag(MouseEvent e) {
        double mx = e.getX(), my = e.getY();

        if (currentTool == Tool.PENCIL && preview != null) {
            preview.addPoint(mx, my);
            redraw();
            return;
        }
        if ((currentTool == Tool.LINE || currentTool == Tool.ELLIPSE || currentTool == Tool.RECT)
                && preview != null) {
            preview.setEnd(mx, my);
            redraw();
            return;
        }
        if (currentTool == Tool.DRAG && dragging != null) {
            dragging.relocate(mx + dragOffsetX, my + dragOffsetY);
            dragWasMoved = true;
            redraw();
        }
    }

    public void onRelease(MouseEvent e) {
        if (currentTool == Tool.DRAG && dragging != null) {
            // восстановить исходные параметры (убрать выделение)
            if (!dragWasMoved) {
                // фигура не двигалась — снимок не нужен
                caretaker.poll();
            }
            // убираем «выделение»: возвращаем толщину и цвет из снимка (если был)
            // проще: снять «+2» и вернуть цвет на чёрный по умолчанию
            dragging.setStrokeWidth(Math.max(1, dragging.getStrokeWidth() - 2));
            dragging.setStrokeColor(strokePicker.getValue());
            dragging = null;
            redraw();
            return;
        }
        if (preview != null) {
            preview = null;
            redraw();
        }
    }

    private Shape hitTest(double mx, double my) {
        for (int i = shapes.size() - 1; i >= 0; i--) {
            if (shapes.get(i).contains(mx, my)) return shapes.get(i);
        }
        return null;
    }

    // ─── удаление ───
    @FXML
    public void onDelete(ActionEvent e) {
        Shape last = null;
        for (int i = shapes.size() - 1; i >= 0; i--) {
            if (shapes.get(i).getStrokeColor() == Color.RED) {
                last = shapes.get(i);
                break;
            }
        }
        if (last == null) {
            if (shapes.isEmpty()) return;
            last = shapes.get(shapes.size() - 1);
        }
        // снимок ДО удаления
        caretaker.push(new Memento(shapes));
        shapes.remove(last);
        redraw();
        lblInfo.setText("Удалено: " + last.discriptor() + ". Снимков: " + caretaker.size());
    }

    // ─── undo ───
    @FXML
    public void onUndo(ActionEvent e) {
        Memento m = caretaker.poll();
        if (m == null) { lblInfo.setText("Снимков нет"); return; }

        if (m.isCanvasSnapshot()) {
            // полный откат холста
            shapes.clear();
            for (Shape s : m.getCanvasSnapshot()) shapes.add(s.copy());
            redraw();
            lblInfo.setText("Откат. Снимков: " + caretaker.size());
        } else {
            // fallback для Task-3-подобных снимков
            Shape snap = m.getState();
            for (Shape s : shapes) {
                if (s.discriptor().equals(snap.discriptor())) {
                    s.setStrokeColor(snap.getStrokeColor());
                    s.setStrokeWidth(snap.getStrokeWidth());
                    s.setFillColor(snap.getFillColor());
                    s.relocate(snap.getX(), snap.getY());
                    break;
                }
            }
            redraw();
            lblInfo.setText("Отменено изменение");
        }
    }

    @FXML
    public void onClear(ActionEvent e) {
        // снимок ДО очистки — чтобы можно было отменить
        caretaker.push(new Memento(shapes));
        shapes.clear();
        redraw();
        lblInfo.setText("Холст очищен. Снимков: " + caretaker.size());
    }

    // ─── переходы ───
    @FXML public void goToTask1(ActionEvent e) throws Exception { switchScene(e, "/task1.fxml"); }
    @FXML public void goToTask2(ActionEvent e) throws Exception { switchScene(e, "/task2.fxml"); }
    @FXML public void goToTask3(ActionEvent e) throws Exception { switchScene(e, "/task3.fxml"); }

    private void switchScene(ActionEvent e, String fxml) throws Exception {
        Stage stage = (Stage) ((Button) e.getSource()).getScene().getWindow();
        stage.setScene(new Scene(new FXMLLoader(getClass().getResource(fxml)).load(), 900, 650));
    }

    private void redraw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (Shape s : shapes) s.draw(gc);
    }
}