package backend;

import models.Destination;
import models.DestinationGraph;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by user on 10/28/2020.
 */
public class Services
{
    /**
     * Get the shortest distance between a destination and all other destinations.
     * @param destinations - an object of DestinationGraph
     * @param reference - index of the source destination.
     * @return an array of double that signifies the shortest distance from that destination to the source destination.
     */
    public static double[][] getShortestDistances(DestinationGraph destinations, int reference)
    {
        Dijkstra d = new Dijkstra(destinations.getDistanceAdjMatrixArray());
        BellmanFord b =  new BellmanFord(destinations.getDistanceRowGraphArray(), destinations.getDestinationNames().size());
        return d.compute(reference);
    }

    /**
     * Get the lowest cost between a destination and all other destinations.
     * @param destinations - an object of DestinationGraph
     * @param reference - index of the source destination.
     * @return an array of double that signifies the lowest cost from that destination to the source destination.
     */
    public static double[][] getCheapestCosts(DestinationGraph destinations, int reference)
    {
        Dijkstra d = new Dijkstra(destinations.getCostAdjMatrixArray());
        BellmanFord b = new BellmanFord(destinations.getCostRowGraphArray(), destinations.getDestinationNames().size());
        return d.compute(reference);
    }

    /**
     * Convert a 2D List to a 2D Array
     * @param list
     * @return a 2D Array
     */
    public static double[][] convert2DListToArray(List<ArrayList<Double>> list)
    {
        int size = list.size();
        double[][] arr = new double[size][size];

        for (int i=0; i<size; i++)
        {
            for (int a=0; a<size; a++)
            {
                arr[i][a] = list.get(i).get(a);
            }
        }

        return arr;
    }

    /**
     * Converts adjacency matrix to row graph matrix (in 2D array format)
     * @param adjMatrix - a List<ArrayList<Double>> object
     * @return a double[][3] object
     */
    public static double[][] convertAdjMatrixToRowGraphMatrix(List<ArrayList<Double>> adjMatrix)
    {
        int size = adjMatrix.size();
        List<ArrayList<Double>> list = new ArrayList<>(); //Each connection will have 3 items: vertex 1 index, vertex 2 index, weight

        //Convert adjacency matrix to adjacency list
        for (int i=0; i<size; i++)
        {
            for (int a=0; a<size; a++)
            {
                double val = adjMatrix.get(i).get(a);
                if (val != 0d)
                {
                    //Establish connection
                    ArrayList<Double> subList = new ArrayList<>();
                    subList.add((double)i);     //Vertex 1 index
                    subList.add((double)a);     //Vertex 2 index
                    subList.add(val);           //Weight

                    //Add to list
                    list.add(subList);
                }
            }
        }

        //Convert to primitive 2D array
        double[][] arr = new double[list.size()][3];   //Each connection will have 3 items: vertex 1 index, vertex 2 index, weight
        for (int i=0; i<list.size(); i++)
        {
            for (int a=0; a<3; a++)
            {
                arr[i][a] = list.get(i).get(a);
            }
        }

        return arr;
    }

    public static void main (String args[])
    {
        DestinationGraph dg = new DestinationGraph();
        dg.addDestination(new Destination("Jakarta"));
        dg.addDestination(new Destination("Surabaya"));
        dg.addDestination(new Destination("Jogja"));

        dg.setDistance(0, 1, 15);
        dg.setDistance(1, 0, 10);
        dg.setDistance(2, 0, 30);
        dg.setDistance(0, 2, 17);
        dg.setDistance(2, 1, 12);

        for (ArrayList<Double> sub : dg.getDistanceMatrix())
        {
            for (Double d : sub)
            {
                System.out.print(d + " ");
            }
            System.out.println();
        }

        System.out.println();

        double[][] pathTable = Services.getShortestDistances(dg, 1 );

        for (int i=0; i<dg.getDestinationNames().size(); i++)
        {
            System.out.println(i + " " + pathTable[0][i] + " " + pathTable[1][i]);
        }
    }
}
