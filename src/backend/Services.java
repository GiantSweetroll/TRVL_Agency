package backend;

<<<<<<< Updated upstream
=======
import models.Destination;
>>>>>>> Stashed changes
import models.DestinationGraph;

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
    public double[] getShortestDistances(DestinationGraph destinations, int reference)
    {
        Dijkstra d = new Dijkstra(destinations.getDistanceMatrixArray());
        return d.compute(reference);
    }

    /**
     * Get the lowest cost between a destination and all other destinations.
     * @param destinations - an object of DestinationGraph
     * @param reference - index of the source destination.
     * @return an array of double that signifies the lowest cost from that destination to the source destination.
     */
    public double[] getCheapestCosts(DestinationGraph destinations, int reference)
    {
        Dijkstra d = new Dijkstra(destinations.getCostMatrixArray());
        return d.compute(reference);
    }

    public static void main (String args[])
    {
<<<<<<< Updated upstream
        System.out.println("Hello dfdfgb");
=======
        Services s = new Services();
        DestinationGraph dg = new DestinationGraph();
        dg.addDestination(new Destination("Jakarta"));
        dg.addDestination(new Destination("Surabaya"));
>>>>>>> Stashed changes
    }
}
