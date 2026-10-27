package com.example.vectoreditor.VectorEditor;

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

public class VectorEditorController {

    @FXML private Canvas canvas;
    @FXML private ColorPicker fillPicker;
    @FXML private ColorPicker strokePicker;
    @FXML private ColorPicker shadowPicker;
    @FXML private TextField tfStrokeWidth;
    @FXML private TextField tfShadowOffset;
    @FXML private Label lblInfo;
    @FXML private Label lblTool;

    private Tool currentTool = Tool.POLYGON;
    private final List<Shape> shapes = new ArrayList<>();
    private final MemoSelect caretaker = new MemoSelect();

    private Polygon polygonInProgress;
    private Shape selected;
    private Shape dragging;
    private double dragOffsetX, dragOffsetY;
    private boolean dragMoved;

    @FXML
    public void initialize() {
        fillPicker.setValue(Color.LIGHTBLUE);
        strokePicker.setValue(Color.BLACK);
        shadowPicker.setValue(Color.rgb(0, 0, 0, 0.4));
        tfStrokeWidth.setText("2");
        tfShadowOffset.setText("6");

        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED,  this::onPress);
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED,  this::onDrag);
        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, this::onRelease);

        redraw();
        updateToolLabel();
    }

    // ─── вспомогательные ───
    private double sw() {
        try {
            double v = Double.parseDouble(tfStrokeWidth.getText());
            return Math.max(1, v);
        } catch (Exception e) { return 2; }
    }
    private double shadowOffset() {
        try {
            double v = Double.parseDouble(tfShadowOffset.getText());
            return Math.abs(v) < 1 ? 6 : v;
        } catch (Exception e) { return 6; }
    }
    private Color fillColor() {
        Color c = fillPicker.getValue();
        return (c == null || c == Color.TRANSPARENT) ? Color.LIGHTBLUE : c;
    }

    private void updateToolLabel() { lblTool.setText("Инструмент: " + currentTool); }

    // разворачивает цепочку декораторов до базового Shape
    private Shape unwrap(Shape s) {
        while (s instanceof ShapeDecorator) {
            s = ((ShapeDecorator) s).getWrapped();
        }
        return s;
    }

    // проверка: внутри цепочки — готовый Polygon (≥3 вершин)
    private boolean isReadyPolygon(Shape s) {
        Shape base = unwrap(s);
        return base instanceof Polygon && ((Polygon) base).size() >= 3;
    }

    // ─── инструменты ───
    @FXML public void toolPolygon(ActionEvent e) { currentTool = Tool.POLYGON; updateToolLabel(); }
    @FXML public void toolPoint(ActionEvent e)   { currentTool = Tool.POINT;   updateToolLabel(); }
    @FXML public void toolCircle(ActionEvent e)  { currentTool = Tool.CIRCLE;  updateToolLabel(); }
    @FXML public void toolSquare(ActionEvent e)  { currentTool = Tool.SQUARE;  updateToolLabel(); }
    @FXML public void toolRect(ActionEvent e)    { currentTool = Tool.RECT;    updateToolLabel(); }
    @FXML public void toolDrag(ActionEvent e)    { currentTool = Tool.DRAG;    updateToolLabel(); }

    // ─── мышь ───
    public void onPress(MouseEvent e) {
        double mx = e.getX(), my = e.getY();

        switch (currentTool) {
            case POLYGON:
                if (polygonInProgress == null) {
                    caretaker.push(new Memento(shapes));
                    polygonInProgress = new Polygon();
                    polygonInProgress.setStrokeColor(strokePicker.getValue());
                    polygonInProgress.setStrokeWidth(sw());
                    shapes.add(polygonInProgress);
                }
                polygonInProgress.addVertex(mx, my);
                redraw();
                break;

            case POINT:
            case CIRCLE:
            case SQUARE:
            case RECT: {
                caretaker.push(new Memento(shapes));
                RasterShape.Kind k = currentTool == Tool.POINT  ? RasterShape.Kind.POINT
                        : currentTool == Tool.CIRCLE ? RasterShape.Kind.CIRCLE
                        : currentTool == Tool.SQUARE ? RasterShape.Kind.SQUARE
                        : RasterShape.Kind.RECT;
                RasterShape r = new RasterShape(k, mx, my, 40);
                r.setFillColor(fillPicker.getValue());
                r.setStrokeColor(strokePicker.getValue());
                r.setStrokeWidth(sw());
                shapes.add(r);
                redraw();
                break;
            }

            case DRAG: {
                Shape hit = hitTest(mx, my);
                if (hit == null) { selected = null; redraw(); return; }
                selected = hit;
                dragging = hit;
                dragOffsetX = hit.getX() - mx;
                dragOffsetY = hit.getY() - my;
                dragMoved = false;
                caretaker.push(new Memento(shapes));
                // визуальное выделение
                hit.setStrokeColor(Color.RED);
                shapes.remove(hit); shapes.add(hit);
                redraw();
                lblInfo.setText("Выбрана: " + hit.descriptor());
                break;
            }
        }
    }

    public void onDrag(MouseEvent e) {
        if (currentTool == Tool.DRAG && dragging != null) {
            dragging.relocate(e.getX() + dragOffsetX - dragging.getX(),
                    e.getY() + dragOffsetY - dragging.getY());
            dragMoved = true;
            redraw();
        }
    }

    public void onRelease(MouseEvent e) {
        if (currentTool == Tool.DRAG && dragging != null) {
            if (!dragMoved) caretaker.poll();   // отменяем пустой снимок
            dragging = null;
            redraw();
        }
    }

    private Shape hitTest(double mx, double my) {
        for (int i = shapes.size() - 1; i >= 0; i--) {
            if (shapes.get(i).contains(mx, my)) return shapes.get(i);
        }
        return null;
    }

    // ─── завершение полигона ───
    @FXML
    public void onFinishPolygon(ActionEvent e) {
        if (polygonInProgress == null) return;
        if (polygonInProgress.size() < 3) {
            shapes.remove(polygonInProgress);
            lblInfo.setText("Полигон должен иметь минимум 3 вершины");
        } else {
            lblInfo.setText("Создан " + polygonInProgress.descriptor()
                    + ". Теперь выделите его инструментом «Тащить» и примените декораторы.");
        }
        polygonInProgress = null;
        redraw();
    }

    // ─── применение декораторов ───
    private int indexOfSelected() {
        if (selected == null) return -1;
        return shapes.indexOf(selected);
    }

    @FXML
    public void onApplyFill(ActionEvent e) {
        if (!isReadyPolygon(selected)) { lblInfo.setText("Сначала выделите полигон (≥3 вершин) инструментом «Тащить»"); return; }
        int idx = indexOfSelected();
        if (idx < 0) { lblInfo.setText("Фигура не найдена на холсте"); return; }

        caretaker.push(new Memento(shapes));
        Shape decorated = new FillDecorator(selected, fillColor());
        shapes.set(idx, decorated);
        selected = decorated;
        redraw();
        lblInfo.setText("Применена заливка → " + decorated.descriptor());
    }

    @FXML
    public void onApplyBorder(ActionEvent e) {
        if (!isReadyPolygon(selected)) { lblInfo.setText("Сначала выделите полигон"); return; }
        int idx = indexOfSelected();
        if (idx < 0) { lblInfo.setText("Фигура не найдена на холсте"); return; }

        caretaker.push(new Memento(shapes));
        Shape decorated = new BorderDecorator(selected, strokePicker.getValue(), sw());
        shapes.set(idx, decorated);
        selected = decorated;
        redraw();
        lblInfo.setText("Применён контур → " + decorated.descriptor());
    }

    @FXML
    public void onApplyShadow(ActionEvent e) {
        if (!isReadyPolygon(selected)) { lblInfo.setText("Сначала выделите полигон"); return; }
        int idx = indexOfSelected();
        if (idx < 0) { lblInfo.setText("Фигура не найдена на холсте"); return; }

        caretaker.push(new Memento(shapes));
        double off = shadowOffset();
        Shape decorated = new ShadowDecorator(selected, off, off, shadowPicker.getValue());
        shapes.set(idx, decorated);
        selected = decorated;
        redraw();
        lblInfo.setText("Применена тень → " + decorated.descriptor());
    }

    // ─── удаление ───
    @FXML
    public void onDelete(ActionEvent e) {
        if (selected == null) { lblInfo.setText("Ничего не выделено"); return; }
        int idx = indexOfSelected();
        if (idx < 0) { lblInfo.setText("Фигура не найдена"); return; }
        caretaker.push(new Memento(shapes));
        shapes.remove(idx);
        selected = null;
        redraw();
        lblInfo.setText("Удалено. Снимков: " + caretaker.size());
    }

    // ─── undo ───
    @FXML
    public void onUndo(ActionEvent e) {
        Memento m = caretaker.poll();
        if (m == null) { lblInfo.setText("Снимков нет"); return; }
        shapes.clear();
        for (Shape s : m.getSnapshot()) shapes.add(s.copy());
        selected = null;
        polygonInProgress = null;
        redraw();
        lblInfo.setText("Откат. Снимков: " + caretaker.size());
    }

    @FXML
    public void onClear(ActionEvent e) {
        caretaker.push(new Memento(shapes));
        shapes.clear();
        selected = null;
        polygonInProgress = null;
        redraw();
        lblInfo.setText("Холст очищен");
    }

    // ─── отрисовка ───
    private void redraw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (Shape s : shapes) s.draw(gc);

        if (polygonInProgress != null) {
            gc.setFill(Color.RED);
            for (double[] v : polygonInProgress.getVertices()) {
                gc.fillOval(v[0] - 4, v[1] - 4, 8, 8);
            }
        }
    }

    @FXML
    public void goToChristmasTree(ActionEvent e) throws Exception {
        Stage stage = (Stage) ((Button) e.getSource()).getScene().getWindow();
        stage.setScene(new Scene(
                new FXMLLoader(getClass().getResource("/ChristmasTree.fxml")).load(),
                900, 750
        ));
    }
}