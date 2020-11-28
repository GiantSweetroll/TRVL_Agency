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
    ArrayList<Line> allLines = new ArrayList<>();




    @FXML
    public void initialize(){
        toComboBox.getItems().addAll("Indonesia", "Singapore", "Malaysia", "Brunei", "Vietnam", "Myanmar", "Thailand", "Philippines", "Cambodia", "Laos");
        fromComboBox.getItems().addAll("Indonesia", "Singapore", "Malaysia", "Brunei", "Vietnam", "Myanmar", "Thailand", "Philippines", "Cambodia", "Laos");
        allLines.add(indoToSing);
        allLines.add(indoToBrunei);
        allLines.add(indoToNam);
        allLines.add(indoToMyan);
        allLines.add(indoToThai);
        allLines.add(indoToPH);
        allLines.add(indoToCamb);
        allLines.add(indoToLaos);
        allLines.add(sgToMal);
        allLines.add(sgToBrunei);
        allLines.add(sgToNam);
        allLines.add(sgToMyan);
        allLines.add(sgToThai);
        allLines.add(sgToCamb);
        allLines.add(sgToLaos);
        allLines.add(malToBrunei);
        allLines.add(malToNam);
        allLines.add(malToMyan);
        allLines.add(malToCamb);
        allLines.add(malToLaos);
        allLines.add(bruToCamb);
        allLines.add(bruToMyan);
        allLines.add(bruToPH);
        allLines.add(bruToThai);
        allLines.add(cambToMyan);
        allLines.add(cambToLaos);
        allLines.add(cambToPH);
        allLines.add(thaiToLaos);
        allLines.add(thaiToNam);
        allLines.add(thaiToPH);
        allLines.add(phToLaos);
        allLines.add(phToMyan);
        allLines.add(phToLaos);
        allLines.add(laosToMyan);
        allLines.add(laosToNam);
        allLines.add(myanToNam);

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
        correspondingLines.put(1, hashMaplineSg);

        HashMap<Integer,Line> hashMaplineMal = new HashMap<>();
        hashMaplineMal.put(1,sgToMal);
        hashMaplineMal.put(3,malToBrunei);
        hashMaplineMal.put(4,malToNam);
        hashMaplineMal.put(5,malToMyan);
        hashMaplineMal.put(8,malToCamb);
        hashMaplineMal.put(9,malToLaos);
        correspondingLines.put(2, hashMaplineMal);

        HashMap<Integer,Line> hashMaplineBru = new HashMap<>();
        hashMaplineBru.put(0,indoToBrunei);
        hashMaplineBru.put(1,sgToBrunei);
        hashMaplineBru.put(2,malToBrunei);
        hashMaplineBru.put(5,bruToMyan);
        hashMaplineBru.put(6,bruToThai);
        hashMaplineBru.put(7,bruToPH);
        hashMaplineBru.put(8,bruToCamb);
        correspondingLines.put(3, hashMaplineBru);

        HashMap<Integer,Line> hashMaplineNam = new HashMap<>();
        hashMaplineNam.put(0,indoToNam);
        hashMaplineNam.put(1,sgToNam);
        hashMaplineNam.put(2,malToNam);
        hashMaplineNam.put(5,myanToNam);
        hashMaplineNam.put(6,thaiToNam);
        hashMaplineNam.put(7,phToNam);
        hashMaplineNam.put(9,laosToNam);
        correspondingLines.put(4, hashMaplineNam);

        HashMap<Integer,Line> hashMaplineMyan = new HashMap<>();
        hashMaplineMyan.put(0, indoToMyan);
        hashMaplineMyan.put(1,sgToMyan);
        hashMaplineMyan.put(2,malToMyan);
        hashMaplineMyan.put(3,bruToMyan);
        hashMaplineMyan.put(4,myanToNam);
        hashMaplineMyan.put(7,phToMyan);
        hashMaplineMyan.put(8,cambToMyan);
        hashMaplineMyan.put(9,laosToMyan);
        correspondingLines.put(5, hashMaplineMyan);

        HashMap<Integer,Line> hashMaplineThai = new HashMap<>();
        hashMaplineThai.put(0,indoToThai);
        hashMaplineThai.put(1,sgToThai);
        hashMaplineThai.put(3,bruToThai);
        hashMaplineThai.put(4,thaiToNam);
        hashMaplineThai.put(7,thaiToPH);
        hashMaplineThai.put(8,thaiToLaos);
        correspondingLines.put(6, hashMaplineThai);

        HashMap<Integer,Line> hashMaplinePh = new HashMap<>();
        hashMaplinePh.put(0,indoToPH);
        hashMaplinePh.put(3,bruToPH);
        hashMaplinePh.put(4,phToNam);
        hashMaplinePh.put(5,phToMyan);
        hashMaplinePh.put(6,thaiToPH);
        hashMaplinePh.put(8,cambToPH);
        hashMaplinePh.put(9,phToLaos);
        correspondingLines.put(7, hashMaplinePh);

        HashMap<Integer, Line> hashMaplineCamb = new HashMap<>();
        hashMaplineCamb.put(0,indoToCamb);
        hashMaplineCamb.put(1,sgToCamb);
        hashMaplineCamb.put(2,malToCamb);
        hashMaplineCamb.put(3,bruToCamb);
        hashMaplineCamb.put(5,cambToMyan);
        hashMaplineCamb.put(7,cambToPH);
        hashMaplineCamb.put(9,cambToLaos);
        correspondingLines.put(8, hashMaplineCamb);

        HashMap<Integer,Line> hashMaplineLaos = new HashMap<>();
        hashMaplineLaos.put(0,indoToLaos);
        hashMaplineLaos.put(1,sgToLaos);
        hashMaplineLaos.put(2,malToLaos);
        hashMaplineLaos.put(4,laosToNam);
        hashMaplineLaos.put(5,laosToMyan);
        hashMaplineLaos.put(6,thaiToLaos);
        hashMaplineLaos.put(7,phToLaos);
        hashMaplineLaos.put(8,cambToLaos);
        correspondingLines.put(9, hashMaplineLaos);
    }
    @FXML
    public void CheapButtonClicked() {
        int fromIndex = 0;
        int toIndex = 0;
        for(int i = 0; i < allLines.size(); i++){
            allLines.get(i).setStroke(Color.TRANSPARENT);
        }
        for(int i = 0; i < Main.country.size(); i ++){
            if(fromComboBox.getValue().equals(Main.country.get(i))){
                fromIndex = i;
                System.out.println(i);
                break;
            }
        }
        for(int i = 0; i < Main.country.size(); i++){
            if(toComboBox.getValue().equals(Main.country.get(i))){
                toIndex = i;
                System.out.println(i);
                break;
            }
        }
        double[][] cheapDestinations = Services.getCheapestCosts(Main.dg, fromIndex);
        for (int i=0; i<Main.dg.getDestinationNames().size(); i++)
        {
            System.out.println(i + " " + cheapDestinations[0][i] + " " + cheapDestinations[1][i]);
        }
        int curIndex = toIndex;
        double totalCost = cheapDestinations[0][curIndex];;
        ArrayList<Integer> completeListCheap = new ArrayList<>();
        completeListCheap.add(curIndex);
        while(curIndex != fromIndex){
            System.out.println("Current Index" + curIndex);


            curIndex = (int)cheapDestinations[1][curIndex];
            completeListCheap.add(curIndex);
            System.out.println("Current Index" + curIndex);

        }

        System.out.println("Complete List Cheap" + completeListCheap);
        System.out.println(curIndex);
        System.out.println();
        for(int i = 0; i < (completeListCheap.size() - 1); i++){
            correspondingLines.get(completeListCheap.get(i)).get(completeListCheap.get(i+1)).setStroke(Color.DARKGREEN);
            System.out.println("Coloring line" + i);
        }
    }
    @FXML
    public void FastButtonClicked(){

    }
}
