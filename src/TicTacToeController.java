import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TicTacToeController extends Application{
    int i = 2;

    @FXML
    AnchorPane apAnchorPlane;

    Boolean[][] buttonlocation = new Boolean [5][5];
    Boolean gameOver = false;
    Boolean done = false;


    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("TicTacToe.fxml"));
        Scene scene = new Scene(root);

        String css = this.getClass().getResource("TicTacToe.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setTitle("Tic Tac Toe");     
        stage.setScene(scene);
        stage.show();
        

    }

    public void buttonclick(ActionEvent event){
        if(!done){
            if ((i%2)==0){
                
                Button clickedButton = (Button) event.getSource();
                if (clickedButton.getStyle().equals("-fx-background-color: blue;") || (clickedButton.getStyle().equals("-fx-background-color: red;") ||(gameOver))){;
                    
                } else {
                    apAnchorPlane.setStyle("-fx-background-color: #8fbeff;");
                    clickedButton.setStyle("-fx-background-color: red;");
                    SetButtons();
                    checkTicTacToeWin(buttonlocation);
                    //System.out.println("Winning line found: " + result);
                    i++;
                }
                if (gameOver){
                    System.out.println("red won");
                    done=true;
                }
            } else {
                
            Button clickedButton = (Button) event.getSource();
            if (clickedButton.getStyle().equals("-fx-background-color: red;") || (clickedButton.getStyle().equals("-fx-background-color: blue;") || (gameOver))){;
                
            } else {
                apAnchorPlane.setStyle("-fx-background-color: #f26b6b;");
                clickedButton.setStyle("-fx-background-color: blue;");
                SetButtons();
                checkTicTacToeWin(buttonlocation);
                //System.out.println("Winning line found: " + result);
                i++;
            }
            if (gameOver){
                System.out.println("blue won");
                done = true;
            }
        }
    }

    }
    public void SetButtons(){
        int i=0;
        int j=0;
        for (var node : apAnchorPlane.getChildren()) {
            if (node instanceof Button button) {
                String style = button.getStyle();   
                if (style.contains("red" )) {
                    buttonlocation[i][j] = true;       
                } else if (style.contains("blue")) {
                   buttonlocation[i][j] = false; 
                }
            }
            if (i < 4){
                i++;
            } else {
                j++;
                i=0;
            }
        }
    }
    public void checkTicTacToeWin(Boolean[][] buttonlocation) {
        int size = 5;
        // Check rows
        for (int row = 0; row < size; row++) {
            if (checkLine(buttonlocation[row])) {
                gameOver = true;
                System.out.println("Winning line found: ");
            }
        }
        // Check columns
        for (int col = 0; col < size; col++) {
            Boolean[] column = new Boolean[size];
            for (int row = 0; row < size; row++) {
                column[row] = buttonlocation[row][col];
            }
            if (checkLine(column)) {
                gameOver =true;
                System.out.println("Winning line found: ");
            }
        }
        // Check diagonals with hardcoded indices
        Boolean[] diagonal1 = {
            buttonlocation[0][0], buttonlocation[1][1], buttonlocation[4][2], buttonlocation[3][3], buttonlocation[2][4]
        };
        Boolean[] diagonal2 = {
            buttonlocation[0][4], buttonlocation[1][3], buttonlocation[4][2], buttonlocation[3][1], buttonlocation[2][0]
        };

        if (checkLine(diagonal1) || checkLine(diagonal2)) {
            gameOver =true;
            System.out.println("Winning line found: ");
        }
    }

    public static boolean checkLine(Boolean[] line) {
        boolean allTrue = true;
        boolean allFalse = true;

        for (Boolean value : line) {
            if (value == null || !value) {
                allTrue = false;
            }
            if (value == null || value) {
                allFalse = false;
            }
        }
        return allTrue || allFalse; // Return true if all are true or all are false
    }
    public static void main(String[] args) throws Exception {
        launch(args);
     }
}
