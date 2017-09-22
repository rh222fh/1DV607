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

    public String getCompactList(){
        String compactList = ("ID\tName\tNumber of boats\n");
        for (int i = 0; i <members.size() ; i++) {
            compactList+=(i+"\t" +this.getMember(i).getName() +"\t"+this.getMember(i).countBoats() +"\n");
        }
        return compactList;
    }

    public String getVerboseList(){
        String verboseList = ("ID          Name             Personal number            boats \n");
        for (int i = 0; i <members.size() ; i++) {
            verboseList+=(i+"\t" +this.getMember(i).getName()+"\t" +this.getMember(i).getpNumber()+"\t" +this.getMember(i).countBoats() +"\n");
        }
        return verboseList;

    }






}
