/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gthgg6finalproject;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author grant harrison
 * 
 */
public class ResearchController extends Switchable implements Initializable {

    private Stage stage;
    
    @FXML
    private TextField searchTextField;
    
    @FXML
    private Label helpText;
    
    @FXML
    private WebView webView;
    
    private String searchString = "AAPL";
    private WebEngine webEngine;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        displayWebsite(searchString);
    }
    
    public void ready(Stage stage) {
        this.stage = stage;
        webEngine = webView.getEngine();
        
        searchTextField.setText(searchString);

    }
    
    @FXML
    private void handleSearch(ActionEvent event) {
        if (searchTextField.getText().equals("")) {
            displayErrorAlert("The search field cannot be blank. Enter one or more search words.");
            return;
        }
        searchString = searchTextField.getText();
        displayWebsite(searchString);
    }
    
    @FXML
    private void handleUpdate(ActionEvent event) {
        displayWebsite(searchString);
    }
    
    @FXML
    private void handleAbout(ActionEvent event) {
        displayAboutAlert();
    }
    
    private void displayErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error!");
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    private void displayExceptionAlert(Exception ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Exception");
        alert.setHeaderText("An Exception Occurred!");
        alert.setContentText("An exception occurred.  View the exception information below by clicking Show Details.");

        // Create expandable Exception.
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("The exception stacktrace was:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        // Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();
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
    
    @FXML
    private void handleGoToA(ActionEvent event) {
        helpText.setText("");
        Switchable.switchTo("Home");
    }
    
    @FXML
    private void handleGoToC(ActionEvent event) {   
        helpText.setText("");
        Switchable.switchTo("Watchlist"); 
    }
    
    @FXML
    private void displayWebsite(String searchString) {
        webEngine = webView.getEngine();
        try {
        webEngine.load("https://finance.yahoo.com/quote/" + searchString + "?p=MSFT&.tsrc=fin-srch");
        } catch(Exception ex) {
            displayExceptionAlert(ex);
        }
    }
    
    @FXML
    private void handleHelp(ActionEvent event) {   
        helpText.setText(" - Please search by stock ticker (i.e. 'AAPL' instead of 'Apple') - "); 
    }
    
}