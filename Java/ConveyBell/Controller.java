package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private SplitPane main;

    @FXML
    private AnchorPane board;

    @FXML
    private Rectangle rect;

    @FXML
    private Circle cir;

    GraphicsContext gc;

    private Canvas canvas;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        drawBoard();
    }

    private void drawBoard(){
        Line line;
        for(int i=1; i<=26; i++){
            line = new Line(72*i,0,0,72*Math.tan(Math.PI/6)*i);
            board.getChildren().add(line);
        }
        for(int i=1; i<=52; i++){
            line = new Line(72*i,72*Math.tan(Math.PI/6)*25,0,72*Math.tan(Math.PI/6)*25-72*Math.tan(Math.PI/6)*i);
            board.getChildren().add(line);
        }
    }
}