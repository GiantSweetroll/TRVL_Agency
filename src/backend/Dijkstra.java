package backend;

public class Dijkstra
{
    //Fields
    private final int VERTEX_COUNT;
    private final double[][] GRAPH;

    //Constructor
    public Dijkstra(double[][] graph)
    {
        this.GRAPH = graph;
        this.VERTEX_COUNT = graph.length;
    }

    //Public Methods
    /**
     * Calculates the shortest distances from one point in the graph to all other points using Dijkstra's algorithm.
     * @param start - the start index. The algorithm will calculate the shortest distance of all vertices to this point.
     * @return an array of double with the shortest distances from the start point to all other points.
     */
    public double[][] compute(int start)
    {
        double[][] pathTable = new double[2][this.VERTEX_COUNT];     //Will be used for output. It will contain the shortest path from start to all other vertices as well as the previous node.
        double[] shortestPaths = new double[this.VERTEX_COUNT];      //Contain the shortest path from start to all other vertices.
        boolean[] pathSet = new boolean[this.VERTEX_COUNT];          //To check if vertex is included in the shortest path tree or the shortest distance from start to that vertex is set.

        //Set all distances to MAX value to represent infinity, and set all values of pathSet to false.
        for (int i = 0; i < this.VERTEX_COUNT; i++)
        {
            shortestPaths[i] = Double.MAX_VALUE;
            pathSet[i] = false;
        }

        //set the distance from source to source as 0
        shortestPaths[start] = 0;
        pathTable[1][start] = start;

        //Find shortest path to the other vertices
        for (int i = 0; i < this.VERTEX_COUNT - 1; i++)
        {
            //Get index of the shortest path of the vertex that is not set yet.
            int minIndex = this.getIndexOfMinDistance(shortestPaths, pathSet);
            pathSet[minIndex] = true;       //Mark as set

            //Update the shortest paths of adjacent vertices from vertex "minIndex"
            for (int a = 0; a < VERTEX_COUNT; a++)
            {
                /**
                 * Update shortestPath[a] if:
                 * 1. vertex "a" is not in pathSet
                 * 2. There is a connection from vertex "a" to vertex "minIndex"
                 * 3. The total weight of the path from start to vertex "a" through vertex "minIndex" is smaller than
                 * the current value stored in shortestPaths[a]
                 */
                if (!pathSet[a] &&
                        this.GRAPH[minIndex][a] != 0 &&
                        shortestPaths[minIndex] != Double.MAX_VALUE &&
                        shortestPaths[minIndex] + this.GRAPH[minIndex][a] < shortestPaths[a])
                {
                    shortestPaths[a] = shortestPaths[minIndex] + this.GRAPH[minIndex][a];
                    pathTable[1][a] = minIndex;
                }
            }
        }

        //Add shortest path data
        pathTable[0] = shortestPaths;

        return pathTable;
    }

    //Private Methods
    private int getIndexOfMinDistance(double[] shortestPaths, boolean[] pathSet)
    {
        double min = Double.MAX_VALUE;
        int minIndex = -1;

        for (int i = 0; i < this.VERTEX_COUNT; i++)
        {
            if (pathSet[i] == false && shortestPaths[i] <= min)
            {
                min = shortestPaths[i];
                minIndex = i;
            }
        }

        return minIndex;
    }
}

//Reference used: https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-greedy-algo-7/