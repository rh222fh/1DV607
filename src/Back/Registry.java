package Back;

import java.util.ArrayList;
import java.util.List;

public class Registry {

    public ArrayList<Member> members = new ArrayList<Member>();
    public int countId;

    public void addMember(Member m){
       members.add(m);
       m.setId(generateId(m));
       System.out.println(m.getName()+"added to registry");
    }

    public int generateId(Member m){
        int id = (int)(Math.random()*9000)+1000;
        for (int i = 0; i <members.size() ; i++) {
            if (m.getId() == id){
                id = (int)(Math.random()*9000)+1000;
                i = 0;
            }
        }
        return id;
    }

    public void deleteMember(Member m){
        members.remove(m);
        System.out.println(m+"deleted from registry");
    }

    public Member getMember(int id) {
        for (Member m : members) {
            if (id == m.getId()) {
                return m;
            }
        }
        System.out.println("Member not found!");
        return null;
    }

    public ArrayList getList(){return  members;}

    public void getCompactList(){
        int ID;
        String name;
        int numberOfBoats;

        System.out.printf("%-5s %-10s %-10s\n", "ID", "Name", "Number of Boats");

        for (Member m: members) {
            ID = m.getId();
            name = m.getName();
            numberOfBoats = m.countBoats();
            System.out.printf("%-5s %-10s %-10s\n", ID, name, numberOfBoats);
        }
    }

    public void getVerboseList(){
        int ID;
        String name;
        String pNumber;
        int numberOfBoats;

        System.out.printf("%-5s %-10s %-20s %-10s\n", "ID", "Name", "Personal Number", "Number of Boats");

        for (Member m: members) {
            ID = m.getId();
            name = m.getName();
            pNumber = m.getPersonalNumber();
            numberOfBoats = m.countBoats();
            System.out.printf("%-5s %-10s %-20s %-10s\n", ID, name, pNumber, numberOfBoats);

        }
    }






}
