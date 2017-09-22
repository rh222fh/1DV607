package Back;

import java.util.ArrayList;
import java.util.List;

public class Registry {

    public List<Member> members = new ArrayList<Member>();
    public int countId;

    public void addMember(Member m){
       members.add(m);
        System.out.println(m+"added to registry");
    }

    public void deleteMember(Member m){
        members.remove(m);
        System.out.println(m+"deleted from registry");
    }

    public Member getMember(int id){
        return members.get(id);
    }

    public void getCompactList(){
        int ID;
        String name;
        int numberOfBoats;

        System.out.printf("%-3s %-10s %-10s\n", "ID", "Name", "Number of Boats");

        for (int i = 0; i <members.size() ; i++) {
            ID = i;
            name = this.getMember(i).getName();
            numberOfBoats = this.getMember(i).countBoats();
            System.out.printf("%-3s %-10s %-10s\n", ID, name, numberOfBoats);

        }
    }

    public void getVerboseList(){
        int ID;
        String name;
        String pNumber;
        int numberOfBoats;

        System.out.printf("%-3s %-10s %-20s %-10s\n", "ID", "Name", "Personal Number", "Number of Boats");

        for (int i = 0; i <members.size() ; i++) {
            ID = i;
            name = this.getMember(i).getName();
            pNumber = this.getMember(i).getpNumber();
            numberOfBoats = this.getMember(i).countBoats();
            System.out.printf("%-3s %-10s %-20s %-10s\n", ID, name, pNumber, numberOfBoats);

        }
    }






}
