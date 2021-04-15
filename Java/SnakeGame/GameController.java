package SnakeGame;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    @FXML
    private Pane Board;

    @FXML
    private Rectangle SnakeHead;

    @FXML
    private Group Snake;

    @FXML
    private Rectangle Target;

    @FXML
    private Text score;

    final int BLOCK_SIZE = 20;
    private KeyBoard CURRENT_DIRECTION;
    private int SCORE;
    private int LEVEL;
    enum KeyBoard{
        UP, DOWN, LEFT, RIGHT;
    }
    ObservableList<Rectangle> SnakeCore;
    private double Max_Height;
    private double Max_Width;
    private int SPEED;
    Timeline timeline;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)  {
        SPEED = 300;
        LEVEL = 1;
        Max_Height = Board.getMaxHeight();
        Max_Width = Board.getMaxWidth();
        SnakeCore = FXCollections.observableArrayList();
        SnakeCore.add(SnakeHead);
        Snake.getChildren().addAll(SnakeCore);
        forward();
    }

    @FXML
    private void handleControl(KeyEvent event) throws Exception {
            if(event.getCode() == KeyCode.UP){
                CURRENT_DIRECTION = KeyBoard.UP;
            }else if(event.getCode().equals(KeyCode.DOWN)){
                CURRENT_DIRECTION = KeyBoard.DOWN;
            }else if(event.getCode().equals(KeyCode.LEFT)){
                CURRENT_DIRECTION = KeyBoard.LEFT;
            }else if(event.getCode().equals(KeyCode.RIGHT)){
                CURRENT_DIRECTION = KeyBoard.RIGHT;
            }
            move(CURRENT_DIRECTION);
    }

    private boolean checkCrash() throws Exception {
        double X = SnakeHead.getX();
        double Y = SnakeHead.getY();
        if(X < 0 || X > (Max_Width-BLOCK_SIZE) || Y < 0 || Y > (Max_Height-BLOCK_SIZE)) {
            return true;
        }else{
            return false;
        }
    }

    private void forward(){
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        System.out.println(SPEED);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(SPEED), actionEvent -> {
            try {
                move(CURRENT_DIRECTION);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    private void crash() throws Exception {
        PauseTransition delay = new PauseTransition(Duration.millis(1));
        delay.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    timeline.stop();
                    PopUp.display();
                    ((Stage)Board.getScene().getWindow()).close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        delay.play();
    }

    private void eat(){
        int random_X = ((int) (Math.random()*Max_Width/BLOCK_SIZE))*BLOCK_SIZE;
        int random_Y = ((int) (Math.random()*Max_Height/BLOCK_SIZE))*BLOCK_SIZE;
        SCORE = SnakeCore.size()*10;
        if(SCORE/LEVEL == 100){
            LEVEL += 1;
            SPEED -= 20;
            timeline.stop();
            forward();
        }
        score.setText(String.format("%09d",SCORE));
        double xLast = ((Rectangle) Snake.getChildren().get(SnakeCore.size()-1)).getX();
        double yLast = ((Rectangle) Snake.getChildren().get(SnakeCore.size()-1)).getY();
        Rectangle newHead = drawRectangle(xLast,yLast);
        SnakeCore.add(newHead);
        Snake.getChildren().clear();
        Snake.getChildren().addAll(SnakeCore);
        Target.setX(random_X);
        Target.setY(random_Y);
    }

    private void move(KeyBoard key) throws Exception {
        double X = SnakeHead.getX();
        double Y = SnakeHead.getY();
        if(key == KeyBoard.UP){
            reDraw(X, Y - BLOCK_SIZE);
        }else if(key == KeyBoard.DOWN){
            reDraw(X, Y + BLOCK_SIZE);
        }else if(key == KeyBoard.LEFT){
            reDraw(X-BLOCK_SIZE,Y);
        }else if(key == KeyBoard.RIGHT){
            reDraw(X+BLOCK_SIZE,Y);
        }
        if(checkCrash()){
            crash();
        }
        if(SnakeHead.getX()==Target.getX() && SnakeHead.getY()==Target.getY()){
            eat();
        }
    }

    private Rectangle drawRectangle(double X, double Y){
        Rectangle newHead = new Rectangle(X,Y,BLOCK_SIZE, BLOCK_SIZE);
        newHead.setFill(Color.DODGERBLUE);
        newHead.setStroke(Color.BLACK);
        return newHead;
    }
    private void reDraw(double X, double Y){
        Rectangle newHead = drawRectangle(X,Y);
        SnakeCore.add(newHead);
        SnakeCore.remove(0);
        SnakeHead = (Rectangle) SnakeCore.get(SnakeCore.size()-1);
        Snake.getChildren().clear();
        Snake.getChildren().addAll(SnakeCore);
    }



}

