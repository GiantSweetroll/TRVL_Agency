package backend;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Experiment {
    long timeStart = System.nanoTime();

    /**
     * Public method to get the sample from a certain txt file
     * @param fileName - name of the file we want to read an take the sample from
     * @return a List of double that signifies vertices or edges we want to take from the txt file
     */
    public List<ArrayList<Double>> getSample(String fileName)
    {
        List<ArrayList<Double>> ls = new ArrayList<>();
        File f = new File(fileName + ".txt");
        if (f.exists())
        {
            try
            {
                Scanner sc = new Scanner(f);
                int num = sc.nextInt();
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

    long timeEnd = System.nanoTime();

}
