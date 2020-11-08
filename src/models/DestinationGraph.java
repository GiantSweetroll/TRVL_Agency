package models;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that represents a graph for both distance and cost between destinations using adjacency matrix.
 */
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

        //Update all other destinations to adjust to new destination
        for (int i=0; i<destSize-1; i++)
        {
            this.distMatrix.get(i).add(0d);
            this.costMatrix.get(i).add(0d);
        }

        //Add sub matrix for the new destination
        this.distMatrix.add(new ArrayList<>());
        this.costMatrix.add(new ArrayList<>());
        for (int i=0; i<destSize; i++)
        {
            this.distMatrix.get(destSize-1).add(0d);
            this.costMatrix.get(destSize-1).add(0d);
        }
    }

    /**
     * Remove a destination by its index in the adjacency matrix
     * @param index
     */
    public void removeDestination(int index)
    {
        this.destNames.remove(index);

        //Remove sub matrix of the destination
        this.distMatrix.remove(index);
        this.costMatrix.remove(index);

        //Update references in the other destinations
        int destSize = this.destNames.size();
        for (int i=0; i<destSize; i++)
        {
            this.distMatrix.get(i).remove(index);
            this.costMatrix.get(i).remove(index);
        }
    }

    /**
     * Removes a destination by its name. If multiple destinations share the first name, it will remove the first occurrence.
     * @param name
     */
    public void removeDestination(String name)
    {
        int index = this.destNames.indexOf(name);
        this.removeDestination(index);
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
        this.costMatrix.set(source, costSubMatrix);
    }

    /**
     * Get the distance matrix in a form of a List of ArrayLists
     * @return a List<ArrayList<Double>> object.
     */
    public List<ArrayList<Double>> getDistanceMatrix()
    {
        return this.distMatrix;
    }

    /**
     * Get the distance matrix in a form of a 2D array
     * @return a 2D array of double
     */
    public double[][] getDistanceMatrixArray()
    {
        int size = this.distMatrix.size();
        double[][] arr = new double[size][size];

        for (int i=0; i<size; i++)
        {
            for (int a=0; a<size; a++)
            {
                arr[i][a] = this.distMatrix.get(i).get(a);
            }
        }

        return arr;
    }

    /**
     * Get the cost matrix in a form of a List of ArrayLists
     * @return a List<ArrayList<Double>> object.
     */
    public List<ArrayList<Double>> getCostMatrix()
    {
        return this.costMatrix;
    }

    /**
     * Get the cost matrix in a form of a 2D array
     * @return a 2D array of double
     */
    public double[][] getCostMatrixArray()
    {
        int size = this.costMatrix.size();
        double[][] arr = new double[size][size];

        for (int i=0; i<size; i++)
        {
            for (int a=0; a<size; a++)
            {
                arr[i][a] = this.costMatrix.get(i).get(a);
            }
        }

        return arr;
    }

    /**
     * Get the list of destination names
     * @return an object of List<String>
     */
    public List<String> getDestinationNames()
    {
        return this.destNames;
    }

    public static void main(String[] args)
    {
        DestinationGraph dg = new DestinationGraph();
        dg.addDestination(new Destination("Jakarta"));
        dg.addDestination(new Destination("Surabaya"));

        dg.setDistance(0, 1, 15);
        dg.setCost(0, 1, 2000);
        dg.setDistance(1, 0, 10);

        for (ArrayList<Double> sub : dg.getDistanceMatrix())
        {
            for (Double d : sub)
            {
                System.out.print(d + " ");
            }
            System.out.println();
        }
    }
}
