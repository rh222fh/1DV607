package model;

/**
 * Class that represents a boat object
 */
public class Boat {

    /** Fields */
    private Type boatType;
    private int length;
    private int id;

    /**
     * Enum with all the different boat types
     */
    public enum Type { sailboat, motorsailer, canoe, other }

    /**
     * Constructor for boat with parameters
     * @param boatType Specifies the type of the boat
     * @param length Specifies the length of the boat
     */
    public Boat(Type boatType, int length) {
        this.boatType = boatType;
        this.length = length;
    }

    /**
     * Empty constructor for boat
     */
    public Boat() {}

    /**
     * Method for getting the type
     * @return Returns the boat type
     */
    public Type getType() { return boatType; }


    /**
     * Method for setting the boat ID
     * @param id Specifies the ID
     */
    public void setId(int id) { this.id = id; }

    /**
     * Method for getting the boat ID
     */
    public int getId() { return id; }


    /**
     * Method for setting the boat type
     * @param bt Specifies the type
     */
    public void setType(Type bt) { this.boatType = bt; }

    /**
     * Method for getting the length
     * @return Returns the boat length
     */
    public int getLength() { return length; }

    /**
     * Method for setting the length
     * @param length Specifies the length
     */
    public void setLength(int length) { this.length = length; }
}
