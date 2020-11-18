package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.ChoiceBox;
import models.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main extends Application {

    public static void main() {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        DestinationGraph dg = new DestinationGraph();

//        make list of country
        ArrayList<String>country = new ArrayList<String>();
        country.add("Indonesia");
        country.add("Singapore");
        country.add("Malaysia");
        country.add("Brunei");
        country.add("Vietnam");
        country.add("Myanmar");
        country.add("Thailand");
        country.add("Philippines");
        country.add("Cambodia");
        country.add("Laos");


//make the distance each country to the other using arraylist
        ArrayList<Integer>indonesiaDistance = new ArrayList<Integer>(Arrays.asList(0,882,1167,1531,1877,2806,2312,2788,1976,1689));
        ArrayList<Integer>singaporeDistance = new ArrayList<Integer>(Arrays.asList(882,0,313,1292,1371,1196,1433,2391,1146,1156));
        ArrayList<Integer>malaysiaDistance = new ArrayList<Integer>(Arrays.asList(1167,313,0,1479,2043,1643,1189,2466,999,1655));
        ArrayList<Integer>bruneiDistance = new ArrayList<Integer>(Arrays.asList(1531,1292,1479,0,2041,2440,1859,1260,1331,1979));
        ArrayList<Integer>vietnamDistance = new ArrayList<Integer>(Arrays.asList(1887,2206,2043,2041,0,1126,990,1756,1060,486));
        ArrayList<Integer>myanmarDistance = new ArrayList<Integer>(Arrays.asList(2806,1643,1925,2440,1126,0,583,2674,1108,693));
        ArrayList<Integer>thailandDistance = new ArrayList<Integer>(Arrays.asList(2312,1433,1189,1859,990,583,0,2211,529,519));
        ArrayList<Integer>philippinesDistance = new ArrayList<Integer>(Arrays.asList(2788,2391,2468,1260,1756,2674,2211,0,1781,2005));
        ArrayList<Integer>cambodiaDistance = new ArrayList<Integer>(Arrays.asList(1976,1146,999,1331,1060,1108,529,1781,0,756));
        ArrayList<Integer>laosDistance = new ArrayList<Integer>(Arrays.asList(2719,1861,1655,1979,486,693,519,2005,756,0));

//make an arraylist to store each contry distance list
        ArrayList<ArrayList<Integer>>distanceList = new ArrayList<ArrayList<Integer>>(Arrays.asList(indonesiaDistance,singaporeDistance,malaysiaDistance,bruneiDistance,vietnamDistance,myanmarDistance,thailandDistance,philippinesDistance,cambodiaDistance,laosDistance));


//        initialize random object to use for generate a random price between 100-500
        Random random = new Random();

//        initialize destination
        for (int i =0 ; i<country.size() ;  i++) {
            dg.addDestination(new Destination(country.get(i)));
        }

        for (int i = 0 ;  i<country.size();i++){
            for (int j =0 ; j < country.size();j++){
                if (country.get(i).equals(country.get(j))){
                    continue;
                }
                else {
                    dg.setDistanceAndCost(i, j, distanceList.get(i).get(j),(random.nextInt(41)+10)*10);
                }
            }
        }

        //Loads the UI and the positions of the widgets
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        //Sets title of the program
        //Sets the resolution of the application windows
        primaryStage.setScene(new Scene(root, 1280, 720));
        //Shows the application window
        primaryStage.show();
    }
}