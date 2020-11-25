package sample;

import backend.Services;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.paint.Paint;
import javafx.scene.image.ImageView;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.control.ComboBox;

import models.*;


public class Controller {
    //Initialise Button, ChoiceBox, and Lines for the UI
    @FXML
    public Button CheapButton = new Button();
    @FXML
    public Button FastButton = new Button();
    @FXML
    ComboBox<String> toComboBox = new ComboBox<>();
    @FXML
    ComboBox<String> fromComboBox = new ComboBox<>();
    @FXML
    Line indoToSing = new Line();
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
    Line malToMyan = new Line();
    @FXML
    Line malToLaos = new Line();
    @FXML
    Line malToCamb = new Line();
    @FXML
    Line malToNam = new Line();
    @FXML
    Line malToBrunei = new Line();
    @FXML
    Line bruToMyan = new Line();
    @FXML
    Line bruToThai = new Line();
    @FXML
    Line bruToCamb = new Line();
    @FXML
    Line bruToPH = new Line();
    @FXML
    Line cambToMyan = new Line();
    @FXML
    Line cambToLaos = new Line();
    @FXML
    Line cambToPH = new Line();
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
    HashMap<Integer, HashMap<Integer, Line>> correspondingLines = new HashMap<>();




    @FXML
    public void initialize(){
        toComboBox.getItems().addAll("Indonesia", "Singapore", "Malaysia", "Brunei", "Vietnam", "Myanmar", "Thailand", "Philippines", "Cambodia", "Laos");
        fromComboBox.getItems().addAll("Indonesia", "Singapore", "Malaysia", "Brunei", "Vietnam", "Myanmar", "Thailand", "Philippines", "Cambodia", "Laos");
        HashMap<Integer, Line> hashMaplineindo = new HashMap<>();
        HashMap<Integer, Line> hashMaplineSg = new HashMap<>();
        hashMaplineindo.put(1, indoToSing);
        hashMaplineindo.put(3, indoToBrunei);
        hashMaplineindo.put(4, indoToNam);
        hashMaplineindo.put(5, indoToMyan);
        hashMaplineindo.put(6, indoToThai);
        hashMaplineindo.put(7, indoToPH);
        hashMaplineindo.put(8, indoToCamb);
        hashMaplineindo.put(9, indoToLaos);
        correspondingLines.put(0, hashMaplineindo);

    }
    @FXML
    public void CheapButtonClicked() {
        int fromIndex = 0;
        int toIndex = 0;
        for(int i = 0; i < Main.country.size(); i ++){
            if(fromComboBox.getValue().equals(Main.country.get(i))){
                fromIndex = i;
                break;
            }
        }
        for(int i = 0; i < Main.country.size(); i++){
            if(toComboBox.getValue().equals(Main.country.get(i))){
                toIndex = i;
                break;
            }
        }
        double[][] gardyanBacot = Services.getCheapestCosts(Main.dg, fromIndex);
        int curIndex = fromIndex;
        double totalCost = gardyanBacot[curIndex][0];;
        ArrayList<Integer> completeListCheap = new ArrayList<>();
        while(toIndex != curIndex){
            curIndex = (int)gardyanBacot[curIndex][1];
            completeListCheap.add(curIndex);
        }
    }
    @FXML
    public void FastButtonClicked(){

    }
}
