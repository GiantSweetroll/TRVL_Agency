package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.paint.Paint;
import javafx.scene.image.ImageView;
import java.awt.*;


public class Controller {
    //Initialise Button, ChoiceBox, and Lines for the UI
    @FXML
    public Button CheapButton = new Button();
    @FXML
    public Button FastButton = new Button();
    @FXML
    ChoiceBox<String> toChoiceBox = new ChoiceBox<>();
    @FXML
    ChoiceBox<String> fromChoiceBox = new ChoiceBox<>();
    @FXML
    Line indoToSing = new Line();
    @FXML
    Line indoToMal = new Line();
    @FXML
    Line indoToBrunei = new Line();
    @FXML
    Line indoToPH = new Line();
    @FXML
    Line indoToCamb = new Line();
    @FXML
    Line indoToThai = new Line();
    @FXML
    Line indoToLaos = new Line();
    @FXML
    Line indoToMyan = new Line();
    @FXML
    Line indoToNam = new Line();
    @FXML
    Line sgToMyan = new Line();
    @FXML
    Line sgToMal = new Line();
    @FXML
    Line sgToThai = new Line();
    @FXML
    Line sgToLaos = new Line();
    @FXML
    Line sgToCamb = new Line();
    @FXML
    Line sgToNam = new Line();
    @FXML
    Line sgToBrunei = new Line();
    @FXML
    Line sgToPH = new Line();
    @FXML
    Line malToMyan = new Line();
    @FXML
    Line malToThai = new Line();
    @FXML
    Line malToLaos = new Line();
    @FXML
    Line malToCamb = new Line();
    @FXML
    Line malToNam = new Line();
    @FXML
    Line malToBrunei = new Line();
    @FXML
    Line malToPH = new Line();
    @FXML
    Line bruToMyan = new Line();
    @FXML
    Line bruToThai = new Line();
    @FXML
    Line bruToLaos = new Line();
    @FXML
    Line bruToCamb = new Line();
    @FXML
    Line bruToNam = new Line();
    @FXML
    Line bruToPH = new Line();
    @FXML
    Line cambToMyan = new Line();
    @FXML
    Line cambToThai = new Line();
    @FXML
    Line cambToLaos = new Line();
    @FXML
    Line cambToNam = new Line();
    @FXML
    Line cambToPH = new Line();
    @FXML
    Line thaiToMyan = new Line();
    @FXML
    Line thaiToLaos = new Line();
    @FXML
    Line thaiToNam = new Line();
    @FXML
    Line thaiToPH = new Line();
    @FXML
    Line phToMyan = new Line();
    @FXML
    Line phToLaos = new Line();
    @FXML
    Line phToNam = new Line();
    @FXML
    Line laosToMyan = new Line();
    @FXML
    Line laosToNam = new Line();
    @FXML
    Line myanToNam = new Line();
    @FXML
    ImageView map = new ImageView();

    @FXML
    public void initialize(){
        toChoiceBox.getItems().addAll("Indonesia", "Vietnam", "Brunei", "Laos", "Cambodia", "Thailand", "Philippines", "Malaysia", "Singapore", "Myanmar");
        fromChoiceBox.getItems().addAll("Indonesia", "Vietnam", "Brunei", "Laos", "Cambodia", "Thailand", "Philippines", "Malaysia", "Singapore", "Myanmar");
    }
    @FXML
    public void CheapButtonClicked(){
        Line destinationLine = indoToSing;
        if (toChoiceBox.getValue().equals("line1")) {
            destinationLine.setStroke(Color.AQUA);
        }
        else{
            destinationLine.setStroke(Color.VIOLET);
        }
    }
    @FXML
    public void FastButtonClicked(){
        Line testing = new Line(538, 225, -197,  294);
        testing.setStroke(Color.AQUA);
        testing.setLayoutX(538);
        testing.setLayoutY(303);
        indoToSing.setStroke(Color.RED);
    }
}
