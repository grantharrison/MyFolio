/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gthgg6finalproject;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author grant harrison
 */

public class HomeController extends Switchable implements Initializable, Welcome {
    
    @FXML
    public Text welcome;
    
    @FXML
    public Text create;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        hello();
        createdBy();
    }
    
    @FXML
    private void handleGoToB(ActionEvent event) {
        Switchable.switchTo("Research");
    }
    
    @FXML
    private void handleGoToC(ActionEvent event) {
        Switchable.switchTo("Watchlist");
    }

    @Override
    public void hello() {
        welcome.setText("Welcome to my Application, please enjoy and learn more about the markets!");
    }
    
    @Override
    public void createdBy() {
        create.setText("Created by Grant Harrison");
    }
    
    @FXML
    private void handleAbout(ActionEvent event) {
        displayAboutAlert();
    }
    
    private void displayAboutAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("MyFolio");
        alert.setContentText("This application was developed by Grant Harrison at the University of Missouri.");
        
        TextArea textArea = new TextArea("MyFolio is an application to help people who do not want to spend money on expensive stock analysis software");
        textArea.appendText(" Please Enjoy!");
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
            
        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(textArea, 0, 0);

        alert.getDialogPane().setExpandableContent(expContent);
        
        alert.showAndWait();
    }
}
