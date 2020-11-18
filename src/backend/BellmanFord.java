package backend;

public class BellmanFord {
    //Fields
    private final double [][] GRAPH;
    private final int VERTEX_COUNT;

    //Constructor
    public BellmanFord(double[][] graph) {
        this.GRAPH = graph;
        this.VERTEX_COUNT = graph.length;
    }

    //Public Methods
    /**
     * Calculates the shortest distances from one point in the graph to all other points using Bellman Ford's algorithm.
     * @param E - the total number of edges in the graph index.
     * @param start - the start index. The algorithm will calculate the shortest distance of all vertices to this point.
     * @return an array of double with the shortest distances from the start point to all other points.
     */
    public double[][] compute(int E, int start)
    {
        double [][] pathTable = new double[2][this.VERTEX_COUNT];  //Will be used for output. It will contain the shortest path from start to all other vertices as well as the previous node.
        double [] shortestPath = new double[this.VERTEX_COUNT];    //Contain the shortest path from start to all other vertices.

        //Set all distances to MAX value to represent infinity
        for(int i = 0; i < this.VERTEX_COUNT; i++)
        {
            shortestPath[i] = Double.MAX_VALUE;
        }

        //Set the distance from source to source as 0
        shortestPath[start] = 0;

        //Find shortest path from source to the other vertices
        //Relax all edges V(vertices) - 1 times.
        for(int i = 0; i < this.VERTEX_COUNT - 1; i++)
        {
            for(int j = 0; j < E; j++)
            {
                //Relaxation process where it will switch the default value (which is infinity) of the next vertex
                //to the sum of previous vertex and edge to next vertex's weight. With condition :
                //If the sum value is smaller than next vertex weight.
                if (shortestPath[(int) this.GRAPH[j][0]]+ this.GRAPH[j][2] <
                        shortestPath[(int) this.GRAPH[j][1]])
                    shortestPath[(int) this.GRAPH[j][1]] =
                            shortestPath[(int) this.GRAPH[j][0]] + this.GRAPH[j][2];
            }
        }

        //This block of code purpose is to check negative-weighted cycles.
        //And to check if we got a shorter path than above result means that the graph contains cycle.
        for (int i = 0; i < E; i++)
        {
            int u = (int) this.GRAPH[i][0];
            int v = (int) this.GRAPH[i][1];
            int weight = (int) this.GRAPH[i][2];

            //Condition statement to check if u's weight is not equal to infinity and if the sum of u's weight and the edge weight
            //between u and v, is smaller than v's weight.
            //Then, it prints error message telling the user that graph contains negative weight cycle
            if (shortestPath[u] != Double.MAX_VALUE &&
                    shortestPath[u] + weight < shortestPath[v])
                System.out.println("Graph contains negative weight cycle");
        }

        //Add shortest path data
        pathTable[0] = shortestPath;

        return pathTable;
    }
}
