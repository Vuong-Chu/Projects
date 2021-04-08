package bmiapp;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class Controller implements Initializable{
    
    @FXML
    private BorderPane bmi;
    
    @FXML
    private Button bt1;

    @FXML
    private TextField height;

    @FXML
    private TextField weight;

    @FXML
    private Label r1;

    @FXML
    private Label r2;
    

    
    @FXML
    void buttonAction(ActionEvent event) {
        if(!height.getText().trim().isEmpty() && !weight.getText().trim().isEmpty() && !height.getText().trim().equals(0) && !weight.getText().trim().equals(0)) {
            double h = Double.parseDouble(height.getText());
            double w = Double.parseDouble(weight.getText());
            double result = w*703/(h*h);
            r1.setText(String.format("%.2f", result));
        }else {
            r1.setText("Wrong input");
        }
        
    }
    
    @FXML
    void binding() {
        DoubleBinding result = new DoubleBinding() {
            {
                super.bind(height.textProperty(), weight.textProperty());
            }
            @Override
            protected double computeValue() {
                if(!height.getText().trim().isEmpty() && !weight.getText().trim().isEmpty() && !height.getText().trim().equals(0) && !weight.getText().trim().equals(0)) {
                    double h = Double.parseDouble(height.getText().trim());
                    double w = Double.parseDouble(weight.getText().trim());
                    return w*703/(h*h);
                }else {
                    return 0;
                }
            }
        };
        r2.textProperty().bind(Bindings.format("%.2f", result));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        binding();
    }                                   
}
