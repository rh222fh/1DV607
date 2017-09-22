package Back;

import java.util.ArrayList;

public class Member {

    private String pNumber;
    private String name;
    private ArrayList<Boat> boats = new ArrayList<>();

    public Member(String number, String mName){
        pNumber = number;
        name = mName;

    }

    public void setName(String s){
        name = s;
    }

    public void setPersonalNumber(String s){
        pNumber = s;
    }

    public void addBoat(Boat b){
        boats.add(b);
    }

    public ArrayList<Boat> getBoats(){
        return boats;
    }

    public int countBoats(){
        return boats.size();
    }

    public String getName(){
        return name;
    }

    public String getpNumber(){
        return pNumber;
    }
}
