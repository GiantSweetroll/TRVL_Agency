package backend;

import models.Destination;
import models.DestinationGraph;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Experiment {
    long timeStart = System.nanoTime();
    long timeEnd = System.nanoTime();

    /**
     * Generates a string with pattern of "v"vertexCount"e"edgesCount.
     * @param vertexCount
     * @param edgesCount
     * @return
     */
    public static String generateName(int vertexCount, int edgesCount)
    {
        return "v" + vertexCount + "e" + edgesCount;
    }

    /**
     * Public method to get the sample from a certain txt file
     * @param fileName - name of the file we want to read an take the sample from
     * @return a List of double that signifies vertices or edges we want to take from the txt file
     */
    public static List<ArrayList<Double>> getSample(String fileName)
    {
        List<ArrayList<Double>> ls = new ArrayList<>();
        File f = new File("./" + fileName + ".txt");
        if (f.exists())
        {
            try
            {
                Scanner sc = new Scanner(f);
                double num = sc.nextDouble();
                while(sc.hasNext())
                {
                    ArrayList<Double> sub = new ArrayList<>();
                    for (int i=0; i<num; i++)
                    {
                        sub.add(sc.nextDouble());
                    }
                    sc.nextLine();
                    ls.add(sub);
                }
            }
            catch(IOException ex)
            {
                ex.printStackTrace();
            }
        }

        return ls;
    }

    /**
     * Generate the adjacency matrix with the specified amount of vertices and edges. Might do several recursions.
     * @param vertexCount
     * @param edgesCount
     * @param fileName
     * @throws IOException
     */
    public static void generateMatrix(int vertexCount, int edgesCount, String fileName) throws IOException
    {
        //Random params
        int minDist = 300;
        int maxDist = 4000;

        //File preparation
        File file = new File("./" + fileName + ".txt");
        if (!file.exists())
        {
            file.createNewFile();
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(file, false));
        bw.write(Integer.toString(vertexCount));
        bw.newLine();

        //Check edges --> must be max at vertexCount^2-vertexCount
        int maxEdges = (int)Math.pow(vertexCount, 2) - vertexCount;
        if (edgesCount > maxEdges)
        {
            edgesCount = maxEdges;
        }

        //Generate matrix
        if (edgesCount == maxEdges) //If edgesCount == maxEdges, just generate all
        {
            for (int i=0; i<vertexCount; i++)
            {
                for (int a=0; a<vertexCount; a++)
                {
                    if (i==a)
                    {
                        bw.write(0.0d + " ");
                    }
                    else
                    {
                        bw.write(Math.round(ThreadLocalRandom.current().nextDouble(minDist, maxDist+1d)) + " ");
                    }
                }
                bw.newLine();
            }
        }
        else        //If not, gotta do some magic
        {
            int curEdges = 0;       //Keeps track the number of edges currently already made
            for (int i=0; i<vertexCount; i++)
            {
                for (int a=0; a<vertexCount; a++)
                {
                    if (i==a)
                    {
                        bw.write(0.0d + " ");
                    }
                    else
                    {
                        if (curEdges < edgesCount && ThreadLocalRandom.current().nextInt(0, vertexCount) == 1)    //Give X% chance of generating an edge to distribute the edges across the other vertices
                        {
                            bw.write(Math.round(ThreadLocalRandom.current().nextDouble(minDist, maxDist+1d)) + " ");
                            curEdges++;
                        }
                        else
                        {
                            bw.write(0.0d + " ");
                        }
                    }
                }
                bw.newLine();
            }

            //If number of current edges is less than the target, redo the operation lmao
            if (curEdges < edgesCount)
            {
                bw.close();
                generateMatrix(vertexCount, edgesCount, fileName);
            }
        }

        bw.close();
    }

    /**
     * Calls the generateMatrix() method with the specified amount of vertices and edges. Filename is automatically generated here.
     * @param vertexCount
     * @param edgesCount
     */
    public static void generateSamples(int vertexCount, int edgesCount)
    {
        System.out.println("Generating data samples of " + vertexCount + " vertices and " + edgesCount + " edges...");
        try
        {
            generateMatrix(vertexCount, edgesCount, generateName(vertexCount, edgesCount));
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
        System.out.println("Done!");
    }

    public static void executeExperiment(int vertexCount, int edgesCount, double[][] adjMatrix, double[][] rowMatrix, String fileName, int trials) throws IOException
    {
        System.out.println("Experimenting on " + vertexCount + " vertices and " + edgesCount + " edges...");
        double timeStart;
        double timeStop;
        ///Dijkstra
        double[] dijkstra = new double[trials];
        Dijkstra dj = new Dijkstra(adjMatrix);
        for (int i=0; i<trials; i++)
        {
            double timeTaken;
            //Start time
            timeStart = System.nanoTime();
            //Start experiment
            dj.compute(0);      //We will just start from the first vertex
            //Stop time
            timeStop = System.nanoTime();
            //Gather time interval
            timeTaken = (timeStop - timeStart) * Math.pow(10, -6);
            //Add it to array
            dijkstra[i] = timeTaken;
        }
        double dijkstraAvg = getAverage(dijkstra);

        ///Bellman-Ford
        double bellman[] = new double[trials];
        BellmanFord bf = new BellmanFord(rowMatrix, adjMatrix.length);
        for (int i=0; i<trials; i++)
        {
            double timeTaken;
            //Start time
            timeStart = System.nanoTime();
            //Start experiment
            bf.compute(0);      //We will just start from the first vertex
            //Stop time
            timeStop = System.nanoTime();
            //Gather time interval
            timeTaken = (timeStop - timeStart) * Math.pow(10, -6);
            //Add it to array
            bellman[i] = timeTaken;
        }
        double bellmanAvg = getAverage(bellman);

        //Export to file
        File file = new File("./" + fileName + ".txt");
        if (!file.exists())
        {
            file.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, false));
            bw.write("Vertices Edges Dijkstra Bellman-Ford");
            bw.newLine();
            bw.close();
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
        bw.write(vertexCount + " " + edgesCount + " " + dijkstraAvg + " " + bellmanAvg);
        bw.newLine();
        bw.close();
        System.out.println("Done!");
    }

    private static double getAverage(double[] values)
    {
        double total = 0;
        for (double val : values)
        {
            total += val;
        }

        return total/values.length;
    }

    public static void main(String args[])
    {
        //Generate data
//        generateSamples(10, 90);
//        generateSamples(100, 90);
//        generateSamples(1000, 90);
//        generateSamples(10000, 90);
//        generateSamples(10000, 10);
//        generateSamples(10000, 100);
//        generateSamples(10000, 1000);
//        generateSamples(10000, 10000);
//        generateSamples(10, (int)Math.pow(10, 2)-10);
//        generateSamples(100, (int)Math.pow(100, 2)-100);
//        generateSamples(1000, (int)Math.pow(1000, 2)-1000);
//        generateSamples(10000, (int)Math.pow(10000, 2)-10000);

        //Conduct Experiment
        int trials = 100;
        System.out.println("Conducting experiment...");
        try
        {
            //Vertex experiment
            System.out.println("Conducting vertex experiment...");
            for (int i=1; i<=4; i++)
            {
                int vertex = (int)Math.pow(10, i);
                int edges = 90;
                String fileName = generateName(vertex, edges);
                System.out.println("Loading file " + fileName + "....");
                List<ArrayList<Double>> sample = getSample(fileName);
                executeExperiment(vertex, edges, Services.convert2DListToArray(sample), Services.convertAdjMatrixToRowGraphMatrix(sample), "Experiment_Vertex", trials);
            }

            //Edges experiment
            System.out.println("Conducting edges experiment...");
            for (int i=1; i<=4; i++)
            {
                int vertex = 10000;
                int edges = (int)Math.pow(10, i);
                String fileName = generateName(vertex, edges);
                System.out.println("Loading file " + fileName + "....");
                List<ArrayList<Double>> sample = getSample(fileName);
                executeExperiment(vertex, edges, Services.convert2DListToArray(sample), Services.convertAdjMatrixToRowGraphMatrix(sample), "Experiment_Edges", trials);
            }

            //Square matrix experiment
            System.out.println("Conducting square matrix experiment...");
            for (int i=1; i<=4; i++)
            {
                int vertex = (int)Math.pow(10, i);
                int edges = (int)Math.pow(vertex, 2) - vertex;
                String fileName = generateName(vertex, edges);
                System.out.println("Loading file " + fileName + "....");
                List<ArrayList<Double>> sample = getSample(fileName);
                executeExperiment(vertex, edges, Services.convert2DListToArray(sample), Services.convertAdjMatrixToRowGraphMatrix(sample), "Experiment_Square", trials);
            }
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }
}
