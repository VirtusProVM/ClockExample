package com.clock.clockexample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        ClockPane pane = new ClockPane();
        pane.setMaxSize(400, 400);
        
        HBox btnBox = new HBox(5);
        btnBox.setAlignment(Pos.CENTER);
        
        
        BorderPane root = new BorderPane();
        
        root.setCenter(pane);
        
        Scene scene = new Scene(root, 400, 400);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Clock");
        
    }

    public static void main(String[] args) {
        launch();
    }

}