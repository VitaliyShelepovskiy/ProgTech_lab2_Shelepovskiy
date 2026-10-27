package com.example.vectoreditor.ChristmasTree;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ChristmasTreeController implements Initializable {

    @FXML private Pane paintPane;
    @FXML private Label lblStatus;

    private ChristmasTree tree;       // базовый объект
    private TreeDecorator dtree;      // текущая декорированная цепочка

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tree = new ChristmasTreeImpl();
        dtree = null;
        redraw();
        updateStatus();
    }

    private void redraw() {
        paintPane.getChildren().clear();
        if (dtree != null) dtree.draw(paintPane);
        else tree.draw(paintPane);
    }

    private void updateStatus() {
        ChristmasTree current = (dtree != null) ? dtree : tree;
        lblStatus.setText(current.decorate());
    }

    @FXML
    public void addLights(ActionEvent e) {
        dtree = (dtree == null) ? new Garland(tree) : new Garland(dtree);
        redraw(); updateStatus();
    }

    @FXML
    public void addOrnaments(ActionEvent e) {
        dtree = (dtree == null) ? new Ornaments(tree) : new Ornaments(dtree);
        redraw(); updateStatus();
    }

    @FXML
    public void addPresents(ActionEvent e) {
        dtree = (dtree == null) ? new Presents(tree) : new Presents(dtree);
        redraw(); updateStatus();
    }

    @FXML
    public void addStar(ActionEvent e) {
        dtree = (dtree == null) ? new Star(tree) : new Star(dtree);
        redraw(); updateStatus();
    }

    @FXML
    public void addAll(ActionEvent e) {
        // применяем все декораторы подряд
        dtree = new Garland(tree);
        dtree = new Ornaments(dtree);
        dtree = new Presents(dtree);
        dtree = new Star(dtree);
        redraw(); updateStatus();
    }

    @FXML
    public void onReset(ActionEvent e) {
        dtree = null;
        redraw(); updateStatus();
    }


}
