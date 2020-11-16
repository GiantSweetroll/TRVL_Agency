package backend;

import models.Destination;
import models.DestinationGraph;

import java.util.ArrayList;

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
        BellmanFord b =  new BellmanFord(destinations.getDistanceBellmanArray());
        int E = destinations.getDistanceBellmanArray().length;
//        return b.compute(E, reference);
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
        BellmanFord b = new BellmanFord(destinations.getCostBellmanArray());
        int E  = destinations.getDistanceBellmanArray().length;
//        return b.compute(E, reference);
        return d.compute(reference);
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
