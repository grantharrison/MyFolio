/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gthgg6finalproject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.json.simple.JSONObject;

/**
 * FXML Controller class
 *
 * @author grant harrison
 */
public class WatchlistController extends Switchable implements Initializable {

    private Stage stage1;
    
    Stock stock;
    
    @FXML
    private TextField stock1TextField; 
     @FXML
    private TextField stock2TextField;
    @FXML
    private TextField stock3TextField;
    @FXML
    private TextArea stock1notesTextField;
    @FXML
    private TextArea stock2notesTextField;
    @FXML
    private TextArea stock3notesTextField;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        stock = new Stock();
    }
    
    public void ready(Stage stage) {
        this.stage1 = stage;
        
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent we) {
                Stock formStock = createStockFromFormData();
                if (stock != null) {
                    if (!stock.equals(formStock)) {
                        if (!confirmContinueOnUnsavedData()) {
                            we.consume();  // this stops the closing from happening
                        }
                    }
                }
            }
        });
    }
    
    @FXML
    private void handleGoToA(ActionEvent event) {
        Switchable.switchTo("Home");      
    }
    
    @FXML
    private void handleGoToB(ActionEvent event) {
        Switchable.switchTo("Research"); 
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
    
    private void displayExceptionAlert(String message, Exception ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Exception Dialog");
        alert.setHeaderText("Exception!");
        alert.setContentText(message);

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
    
     @FXML
    public void handleOpen(ActionEvent event) {
        Stock formStock = createStockFromFormData();
        if (stock != null) {
            if (!stock.equals(formStock)) {
                if (!confirmContinueOnUnsavedData()) {
                    return;
                }
            }
        }       
        
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage1);
        Switchable.switchTo("Watchlist");
        if (file != null) {
            try
            {
                FileReader fileReader = new FileReader(file.getPath());
                BufferedReader bufferedReader = new BufferedReader(fileReader);
 
                String json = "";
                String line = null;
                while((line = bufferedReader.readLine()) != null) {
                    json += line;
                }
                bufferedReader.close();
                fileReader.close();
                
                stock.initFromJsonString(json);
               
                fillFormFromStock(stock);
            }catch(IOException ioex)
            {
               String message = "Exception occurred while opening " + file.getPath();
               displayExceptionAlert(message, ioex);
            }
        }
    }
    
    @FXML
    public void handleSave(ActionEvent event) {
        stock = createValidatedStockFromFormData();
        if (stock == null) {
            return;
        }
        
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showSaveDialog(stage1);
        Switchable.switchTo("Watchlist");
        if (file != null) {
            try
            {
               String jsonString = stock.toJsonString();
               PrintWriter out = new PrintWriter(file.getPath());
               out.print(jsonString);
               out.close();
            }catch(IOException ioex)
            {
                String message = "Exception occurred while saving to " + file.getPath();
                displayExceptionAlert(message, ioex);
            } 
        } 
    }
    
    private Stock createValidatedStockFromFormData() {
        Stock p = new Stock();
        
        p.setStock1(stock1TextField.getText());
        p.setStock2(stock2TextField.getText());
        p.setStock3(stock3TextField.getText());
        p.setStock1Notes(stock1notesTextField.getText());
        p.setStock2Notes(stock2notesTextField.getText());
        p.setStock3Notes(stock3notesTextField.getText());

        return p;
    }
    
    private Stock createStockFromFormData() {
        Stock p = new Stock();
        
        p.setStock1(stock1TextField.getText());
        p.setStock2(stock2TextField.getText());
        p.setStock3(stock3TextField.getText());
        
        p.setStock1Notes(stock1notesTextField.getText());
        p.setStock2Notes(stock2notesTextField.getText());
        p.setStock3Notes(stock3notesTextField.getText());
        
        

        return p;
    }
    
    private void fillFormFromStock(Stock stock) {  
        if (stock.getStock1() != null) {
            stock1TextField.setText(stock.getStock1());
        } else {
            stock1TextField.setText("");
        }
        
        if (stock.getStock2() != null) {
            stock2TextField.setText(stock.getStock2());
        } else {
            stock2TextField.setText("");
        }
        
        if (stock.getStock3() != null) {
            stock3TextField.setText(stock.getStock3());
        } else {
            stock3TextField.setText("");
        }
        
        if (stock.getStock1Notes() != null) {
            stock1notesTextField.setText(stock.getStock1Notes());
        } else {
            stock1notesTextField.setText("");
        }
        
        if (stock.getStock2Notes() != null) {
            stock2notesTextField.setText(stock.getStock2Notes());
        } else {
            stock2notesTextField.setText("");
        }
        
        if (stock.getStock3Notes() != null) {
            stock3notesTextField.setText(stock.getStock3Notes());
        } else {
            stock3notesTextField.setText("");
        }
          
    }
    
    private boolean confirmContinueOnUnsavedData() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Unsaved Data");
        alert.setHeaderText("Changes have not been saved.");
        alert.setContentText("Are you sure you want to continue?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            // ... user chose OK
            return true;
        } else {
            // ... user chose CANCEL or closed the dialog
            return false;
        }
    }  
    
    @FXML
    public void handleClose(ActionEvent event) {
        Stock formStock = createStockFromFormData();
        if (stock != null) {
            if (!stock.equals(formStock)) {
                if (!confirmContinueOnUnsavedData()) {
                    return;
                }
            }
        }
        
        stage1.close();
    }
}
