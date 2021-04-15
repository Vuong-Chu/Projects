package SnakeGame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class PopUp {
    @FXML
    private Button btn1;

    @FXML
    private Button btn2;
    public static void display() throws IOException {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        Parent root = FXMLLoader.load(PopUp.class.getResource("Dialog.fxml"));
        dialog.setTitle("Warning!");
        Scene scene = new Scene(root, 300, 100);
        dialog.setScene(scene);
        dialog.show();
    }

    @FXML
    private void noOnAction(ActionEvent event) {
        ((Stage) btn2.getScene().getWindow()).close();
    }

    @FXML
    private void yesOnAction(ActionEvent event) throws Exception {
        Main main = new Main();
        Stage primaryStage = new Stage();
        main.start(primaryStage);
        ((Stage) btn1.getScene().getWindow()).close();
    }

}
