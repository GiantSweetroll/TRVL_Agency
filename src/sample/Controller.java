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
import java.awt.datatransfer.SystemFlavorMap;
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
        hashMaplineindo.put(1, indoToSing);
        hashMaplineindo.put(3, indoToBrunei);
        hashMaplineindo.put(4, indoToNam);
        hashMaplineindo.put(5, indoToMyan);
        hashMaplineindo.put(6, indoToThai);
        hashMaplineindo.put(7, indoToPH);
        hashMaplineindo.put(8, indoToCamb);
        hashMaplineindo.put(9, indoToLaos);
        correspondingLines.put(0, hashMaplineindo);

        HashMap<Integer, Line> hashMaplineSg = new HashMap<>();
        hashMaplineSg.put(0,indoToSing);
        hashMaplineSg.put(2,sgToMal);
        hashMaplineSg.put(3,sgToBrunei);
        hashMaplineSg.put(4,sgToNam);
        hashMaplineSg.put(5,sgToMyan);
        hashMaplineSg.put(6,sgToThai);
        hashMaplineSg.put(8,sgToCamb);
        hashMaplineSg.put(9,sgToLaos);

        HashMap<Integer,Line> hashMaplineMal = new HashMap<>();
        hashMaplineMal.put(1,sgToMal);
        hashMaplineMal.put(3,malToBrunei);
        hashMaplineMal.put(4,malToNam);
        hashMaplineMal.put(5,malToMyan);
        hashMaplineMal.put(8,malToCamb);
        hashMaplineMal.put(9,malToLaos);

        HashMap<Integer,Line> hashMaplineBru = new HashMap<>();
        hashMaplineBru.put(0,indoToBrunei);
        hashMaplineBru.put(1,sgToBrunei);
        hashMaplineBru.put(2,malToBrunei);
        hashMaplineBru.put(5,bruToMyan);
        hashMaplineBru.put(6,bruToThai);
        hashMaplineBru.put(7,bruToPH);
        hashMaplineBru.put(8,bruToCamb);

        HashMap<Integer,Line> hashMaplineNam = new HashMap<>();
        hashMaplineNam.put(0,indoToNam);
        hashMaplineNam.put(1,sgToNam);
        hashMaplineNam.put(2,malToNam);
        hashMaplineNam.put(5,myanToNam);
        hashMaplineNam.put(6,thaiToNam);
        hashMaplineNam.put(7,phToNam);
        hashMaplineNam.put(9,laosToNam);

        HashMap<Integer,Line> hashMaplineMyan = new HashMap<>();
        hashMaplineMyan.put(0, indoToMyan);
        hashMaplineMyan.put(1,sgToMyan);
        hashMaplineMyan.put(2,malToMyan);
        hashMaplineMyan.put(3,bruToMyan);
        hashMaplineMyan.put(4,myanToNam);
        hashMaplineMyan.put(7,phToMyan);
        hashMaplineMyan.put(8,cambToMyan);
        hashMaplineMyan.put(9,laosToMyan);

        HashMap<Integer,Line> hashMaplineThai = new HashMap<>();
        hashMaplineThai.put(0,indoToThai);
        hashMaplineThai.put(1,sgToThai);
        hashMaplineThai.put(3,bruToThai);
        hashMaplineThai.put(4,thaiToNam);
        hashMaplineThai.put(7,thaiToPH);
        hashMaplineThai.put(8,thaiToLaos);

        HashMap<Integer,Line> hashMaplinePh = new HashMap<>();
        hashMaplinePh.put(0,indoToPH);
        hashMaplinePh.put(3,bruToPH);
        hashMaplinePh.put(4,phToNam);
        hashMaplinePh.put(5,phToMyan);
        hashMaplinePh.put(6,thaiToPH);
        hashMaplinePh.put(8,cambToPH);
        hashMaplinePh.put(9,phToLaos);

        HashMap<Integer, Line> hashMaplineCamb = new HashMap<>();
        hashMaplineCamb.put(0,indoToCamb);
        hashMaplineCamb.put(1,sgToCamb);
        hashMaplineCamb.put(2,malToCamb);
        hashMaplineCamb.put(3,bruToCamb);
        hashMaplineCamb.put(5,cambToMyan);
        hashMaplineCamb.put(7,cambToPH);
        hashMaplineCamb.put(9,cambToLaos);

        HashMap<Integer,Line> hashMaplineLaos = new HashMap<>();
        hashMaplineLaos.put(0,indoToLaos);
        hashMaplineLaos.put(1,sgToLaos);
        hashMaplineLaos.put(2,malToLaos);
        hashMaplineLaos.put(4,laosToNam);
        hashMaplineLaos.put(5,laosToMyan);
        hashMaplineLaos.put(6,thaiToLaos);
        hashMaplineLaos.put(7,phToLaos);
        hashMaplineLaos.put(8,cambToLaos);
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
