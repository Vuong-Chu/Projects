package emailapp;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class controller{
    @FXML
    private AnchorPane root;
    @FXML
    private Button btn;
    @FXML
    private TextField fname;
    @FXML
    private TextField lname;
    @FXML
    private ChoiceBox<String> dept;
    @FXML
    private TextArea result;
    
    ObservableList<String> department = FXCollections.observableArrayList("IT","HR","Sales","N/A");
    
    
    @FXML
    private void initialize(){
        dept.setItems(department);
        dept.setValue("N/A");
        
        btn.disableProperty().bind(Bindings.or(
                fname.textProperty().isEmpty(),
                lname.textProperty().isEmpty()));
        btn.setOnAction((event)->{
            Email e = new Email(fname.getText(), lname.getText(), dept.getValue());
            e.createPassword();
            e.createEmail();
            String str = String.format(
                    "DISPLAY NAME: %s %s\n"
                    + "COMPANY EMAIL: %s\n"
                    + "PASSWORD: %s\n"
                    + "MAILBOX CAPACITY: %s", e.getFirstname(), e.getLastname(), e.getEmail(), e.getPassword() ,e.getMailBoxCapacity());
            result.setText(str);
        });
    }

    

}
