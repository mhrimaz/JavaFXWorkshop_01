/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxworkshop_01;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 *
 * @author mhrimaz
 */
public class JavaFXWorkshop_01 extends Application {

    Circle source, target;

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        root.setOnMouseClicked(event -> {
            if (event.isConsumed() || event.getSource().getClass().getSimpleName().equals("Circle")) {
                return;
            }
            Circle circle = new Circle(event.getSceneX(), event.getSceneY(), 15, Color.DARKSALMON);
            circle.setOnMouseDragged(eventDrag -> {
                source = (Circle) eventDrag.getSource();
                source.setCenterX(eventDrag.getSceneX());
                source.setCenterY(eventDrag.getSceneY());
                eventDrag.consume();
            });
            circle.setOnMouseClicked(eventClick -> {
                if (eventClick.isConsumed()) {
                    return;
                }
                if (source == null) {
                    source = (Circle) eventClick.getSource();
                    source.setFill(Color.SLATEGRAY);
                } else if (target == null) {
                    target = (Circle) eventClick.getSource();
                    if (!source.equals(target)) {
                        Line line = new Line(source.getCenterX(), source.getCenterY(), target.getCenterX(), target.getCenterY());
                        root.getChildren().add(line);
                        line.toBack();
                        line.startXProperty().bind(source.centerXProperty());
                        line.startYProperty().bind(source.centerYProperty());
                        line.endXProperty().bind(target.centerXProperty());
                        line.endYProperty().bind(target.centerYProperty());
                        
                    }
                    source.setFill(Color.DARKSALMON);
                    source = null;
                    target = null;
                }
                eventClick.consume();
            });
            root.getChildren().add(circle);
        });
        Scene scene = new Scene(root, 500, 600);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
