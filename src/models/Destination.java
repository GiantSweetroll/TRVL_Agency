package models;

public class Destination
{
    //Fields
    private String name;

    //Constructor
    /**
     * Instatiates an object of Destination. The name field will be an empty String.
     */
    public Destination()
    {
        this.name = "";
    }
    /**
     * Instantiates an object of Destination with the specified name.
     *
     * @param name
     */
    public Destination(String name)
    {
        this.name = name;
    }

    //Public Methods
    /**
     * @return a String of the destination's name
     */
    public String getName()
    {
        return name;
    }
    /**
     * Set the name of the destination.
     * @param name
     */
    public void setName(String name)
    {
        this.name = name;
    }
}
