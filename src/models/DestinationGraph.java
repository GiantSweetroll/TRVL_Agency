package models;

import java.util.ArrayList;
import java.util.List;

public class DestinationGraph
{
    //Fields
    private List<ArrayList<Double>> distMatrix;
    private List<String> destNames;
    private List<ArrayList<Double>> costMatrix;

    //Constructor
    public DestinationGraph()
    {
        this.distMatrix = new ArrayList<>();
        this.costMatrix = new ArrayList<>();
        this.destNames = new ArrayList<>();
    }

    //Methods
    /**
     * Adds a new destination to the graph. It won't point to anything yet.
     * @param dest
     */
    public void addDestination(Destination dest)
    {
        this.destNames.add(dest.getName());

        int destSize = this.destNames.size();
        this.distMatrix.add(new ArrayList<>(destSize));
        this.costMatrix.add(new ArrayList<>(destSize));
    }

    /**
     * Configure the distance between 2 destinations.
     * @param source - index of the source destination. This index determines which sublist to be manipulated in the distance matrix.
     * @param dest - index of the target destination. This index determines which element to be manipulated in the sublist.
     * @param distance - the new distance value between the two destinations.
     */
    public void setDistance(int source, int dest, double distance)
    {
        ArrayList<Double> distSubMatrix = this.distMatrix.get(source);
        distSubMatrix.set(dest, distance);
        this.distMatrix.set(source, distSubMatrix);
    }

    /**
     * Configure the cost between 2 destinations.
     * @param source - index of the source destination. This index determines which sublist to be manipulated in the cost matrix.
     * @param dest - index of the target destination. This index determines which element to be manipulated in the sublist.
     * @param cost - the new cost between the two destinations.
     */
    public void setCost(int source, int dest, double cost)
    {
        ArrayList<Double> costSubMatrix = this.costMatrix.get(source);
        costSubMatrix.set(dest, cost);
        this.distMatrix.set(source, costSubMatrix);
    }
}
