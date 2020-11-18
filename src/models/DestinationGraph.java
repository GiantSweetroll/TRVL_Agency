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

    //Public Methods
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
     * Set the distance and cost between two destinations. Calling this method is equivalent in calling the setDistance() and setCost() methods individually.
     * @param source - index of the source destination. This index determines which sublist to be manipulated in the cost matrix.
     * @param dest - index of the target destination. This index determines which element to be manipulated in the sublist.
     * @param distance - the new distance value between the two destinations.
     * @param cost - the new cost between the two destinations.
     */
    public void setDistanceAndCost(int source, int dest, double distance, double cost)
    {
        this.setDistance(source, dest, distance);
        this.setCost(source, dest, cost);
    }

    /**
     * Get the distance matrix in a form of a List of ArrayLists (in adjacency matrix format)
     * @return a List<ArrayList<Double>> object.
     */
    public List<ArrayList<Double>> getDistanceMatrix()
    {
        return this.distMatrix;
    }

    /**
     * Get the distance matrix (in adjacency matrix format) in a form of a 2D array
     * @return a 2D array of double
     */
    public double[][] getDistanceAdjMatrixArray()
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
     * Get the distance matrix in the form of the row graph matrix 2D array format.
     * @return a double[][3] object
     */
    public double[][] getDistanceRowGraphArray()
    {
        return this.convertAdjMatrixToRowGraphMatrix(this.distMatrix);
    }

    /**
     * Get the cost matrix in a form of a List of ArrayLists (in adjacency matrix format)
     * @return a List<ArrayList<Double>> object.
     */
    public List<ArrayList<Double>> getCostMatrix()
    {
        return this.costMatrix;
    }

    /**
     * Get the cost matrix (in adjacency matrix format) in a form of a 2D array
     * @return a 2D array of double
     */
    public double[][] getCostAdjMatrixArray()
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
     * Get the cost matrix in the form of the row graph matrix 2D array format.
     * @return a double[][3] object
     */
    public double[][] getCostRowGraphArray()
    {
        return this.convertAdjMatrixToRowGraphMatrix(this.costMatrix);
    }

    /**
     * Get the list of destination names
     * @return an object of List<String>
     */
    public List<String> getDestinationNames()
    {
        return this.destNames;
    }

    /**
     * Get the location index of the Destination name.
     * @param destinationName - The name of the destination. If multiple destinations have the same name, the first occurrence will be selected.
     * @return the index of the destination in the order they were added. If the destination is not found, -1 will be returned instead.
     */
    public int getIndex(String destinationName)
    {
        int index = -1;

        for (int i=0; i<this.destNames.size(); i++)
        {
            if (this.destNames.get(i).equals(destinationName))
            {
                index = i;
                break;
            }
        }

        return index;
    }

    /**
     * Return the distance between two destinations.
     * @param source - the index of the source destination
     * @param target - the index of the target destination
     * @return a double. If there is no connection between the two distances, returns 0.0.
     */
    public double getDistance(int source, int target)
    {
        return this.distMatrix.get(source).get(target);
    }

    /**
     * Return the cost between two destinations.
     * @param source - the index of the source destination
     * @param target - the index of the target destination
     * @return a double. If there is no connection between the two distances, returns 0.0.
     */
    public double getCost(int source, int target)
    {
        return this.costMatrix.get(source).get(target);
    }

    //Private Methods
    /**
     * Converts adjacency matrix to row graph matrix (in 2D array format)
     * @param adjMatrix - a List<ArrayList<Double>> object
     * @return a double[][3] object
     */
    private double[][] convertAdjMatrixToRowGraphMatrix(List<ArrayList<Double>> adjMatrix)
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

    //Main Method
    public static void main(String[] args)
    {
        DestinationGraph dg = new DestinationGraph();
        dg.addDestination(new Destination("Jakarta"));
        dg.addDestination(new Destination("Surabaya"));
        dg.addDestination(new Destination("Solo"));

        dg.setDistance(0, 1, 15);
        dg.setCost(0, 1, 2000);
        dg.setDistance(1, 0, 10);
        dg.setCost(0, 1, 5000);
        dg.setDistanceAndCost(0, 2, 20, 12000);
        dg.setDistanceAndCost(2, 1, 6, 2000);

        for (ArrayList<Double> sub : dg.getDistanceMatrix())
        {
            for (Double d : sub)
            {
                System.out.print(d + " ");
            }
            System.out.println();
        }
        System.out.println();
        for (double[] sub : dg.getDistanceRowGraphArray())
        {
            for (double val : sub)
            {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}
