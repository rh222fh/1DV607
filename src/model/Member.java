package model;

import java.util.ArrayList;
public class Member {

    /** Fields */
    private String pNumber;
    private String name;
    private int id;
    private ArrayList<Boat> boats = new ArrayList<>();


    /**
     * Constructor for member with parameters
     * @param number Specifies the personal number for the member
     * @param mName Specifies the name of the member
     */
    public Member(String number, String mName){
        pNumber = number;
        name = mName;
    }


    /**
     * Empty constructor for member
     */
    public Member(){}


    /**
     * Method for setting a members name
     * @param s Specifies the new name
     */
    public void setName(String s){
        name = s;
    }

    /**
     * Method for setting a members personal number
     * @param s Specifies the new personal number
     */
    public void setPersonalNumber(String s){
        pNumber = s;
    }

    /**
     * Method for adding a boat
     * @param b Specifies the boat to be added
     */
    public void addBoat(Boat b){
        boats.add(b);
    }

    /**
     * Method for getting a list of boats for a specific member
     * @return returns the list of boats
     */
    public ArrayList<Boat> getBoats(){ return boats; }

    /**
     * Method to check if a specific boat exists
     * @param id Specifies the boat id
     * @return Return tru if boat exists, otherwise return false
     */
    public boolean boatExists(int id) {
        for (int i = 0; i < boats.size(); i++) {
            if (id == i) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method for counting boats
     * @return Returns the number of boats for a specific member
     */
    public int countBoats(){
        return boats.size();
    }

    /**
     * Method for getting a members name
     * @return Returns a specific members name
     */
    public String getName(){
        return name;
    }

    /**
     * Method for getting a members personal number
     * @return Returns a specific members personal number
     */
    public String getPersonalNumber(){
        return pNumber;
    }

    /**
     * Method for getting a member id
     * @return Return a specific members id
     */
    public int getId(){ return id;}

    /**
     * Method for setting a members id
     * @param id Specifies the id for a member
     * @return Returns id for a specific member
     */
    public void setId(Integer id){this.id = id;}

}
