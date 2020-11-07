package models;

import java.util.ArrayList;
import java.util.List;

public class Graph
{
    //Fields
    private List<ArrayList<Integer>> distMatrix;
    private List<String> destNames;
    private List<ArrayList<Double>> costMatrix;

    //Constructor
    public Graph()
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
        this.distMatrix.add(new ArrayList<>());
        this.costMatrix.add(new ArrayList<>());
    }

    /**
     * Configure the destination at the selected index.
     * @param index - location of the destination in the array
     * @param distance - the distance matrix of the destination. Size must be the same as the amount of destinations.
     * @param cost - the cost matrix of the destination. Size must be the same as the amount of destinations.
     */
    public void configureDest(int index, ArrayList<Integer> distance, ArrayList<Double> cost)
    {
        int destSize = this.destNames.size();
        //Check if index exists and distance and cost are of the correct size
        if (index <= destSize-1 && index >= 0 && distance.size() == destSize && cost.size() == destSize)
        {
            this.distMatrix.set(index, distance);
            this.costMatrix.set(index, cost);
        }
        else
        {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    /**
     * Configure the distance matrix in the selected index
     * @param index - index of the distance matrix to be updated.
     * @param distance - the new distance matrix to replace it.
     */
    public void configureDistance(int index, ArrayList<Integer> distance)
    {
        int destSize = this.destNames.size();
        //Check if index exists and if distance is of the correct size
        if (index <= destSize-1 && index >= 0 && distance.size() == destSize)
        {
            this.distMatrix.set(index, distance);
        }
        else
        {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    /**
     * Configure the cost matrix in the selected index
     * @param index - index of the distance matrix to be updated.
     * @param cost - the new cost matrix to replace it.
     */
    public void configureCost(int index, ArrayList<Integer> cost)
    {
        int destSize = this.destNames.size();
        //Check if index exists and if cost is of the correct size
        if (index <= destSize-1 && index >= 0 && cost.size() == destSize)
        {
            this.distMatrix.set(index, cost);
        }
        else
        {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
}
