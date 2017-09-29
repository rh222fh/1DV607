package Back;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Registry {

    public ArrayList<Member> members = new ArrayList<Member>();
    public int countId;

    public void addMember(Member m){
       members.add(m);
       m.setId(generateId(m));
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

    public void changeMember(int id, String name, String pNumber){
        getMember(id).setName(name);
        getMember(id).setPersonalNumber(pNumber);
    }

    public void deleteMember(Member m){
        members.remove(m);
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
        int boatLength;
        Object boatType;
        String boatInfo = "";

        System.out.printf("%-5s %-10s %-20s %-10s\n", "ID", "Name", "Personal Number", "Boat information");

        for (Member m: members) {
            ID = m.getId();
            name = m.getName();
            pNumber = m.getPersonalNumber();
            for (int i = 0; i <= m.countBoats(); i++){
                boatType = m.getBoats().get(i).getType();
                boatLength = m.getBoats().get(i).getLength();
                boatInfo = i + ". " + boatType + " " + boatLength + "\n";
            }

            System.out.printf("%-5s %-10s %-20s %-10s\n", ID, name, pNumber, boatInfo);

        }
    }

    public boolean memberExists(int id) {
        for (Member m: members) {
            if (id == m.getId())
                return true;
        }
        return false;
    }

    public void saveRegistry(String path){
        String printer = "";
            try (PrintWriter outputFile = new PrintWriter(new FileWriter(path, true))) {
                for (Member m : members) {
                    printer += m.getName() + "%" + m.getPersonalNumber() + "%%" + m.getId() + "%%%";
                    if(!m.getBoats().isEmpty()) {
                        for (int i = 0; i < m.countBoats(); i++) {
                            printer += m.getBoats().get(i).getType() + "%%%%" + m.getBoats().get(i).getLength() + "%%%%%";
                        }
                    }
                    outputFile.println(printer);
                    printer = "";

                }
            } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadRegistry(String path)throws FileNotFoundException {
        this.members.clear();

        FileInputStream fstream = new FileInputStream(path);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String id="";
        String name="";
        String pNumber="";
        String boatType="";


        String strLine;
        String test = "%";
        try {
            while ((strLine = br.readLine()) != null) {
                System.out.println();
                String reader = "";
                int counter = 0;
                Member m;
                for (int i = 0; i < strLine.length()+1; i++) {

                    if (counter == 1) {
                        name=reader;
                        reader = "";
                        counter = 0;
                    } else if (counter == 2) {
                        pNumber=reader;
                        reader = "";
                        counter = 0;
                    } else if (counter == 3) {
                        m = new Member(pNumber, name);
                        this.addMember(m);
                        m.setId(Integer.valueOf(reader));
                        id=reader;
                        reader = "";
                        counter = 0;
                    } else if (counter == 4) {
                        boatType=reader;
                        reader = "";
                        counter = 0;
                    } else if (counter == 5) {
                        Boat boat = new Boat(Boat.Type.valueOf(boatType.toLowerCase()), Integer.valueOf(reader));
                        this.getMember(Integer.valueOf(id)).addBoat(boat);
                        reader = "";
                        counter = 0;
                    }
                    String start = strLine + "       ";
                    if (start.charAt(i) == test.charAt(0)) {
                        counter = 1;
                        if (start.charAt(i + 1) == test.charAt(0)) {
                            counter = 2;
                            i++;
                            if (start.charAt(i + 1) == test.charAt(0)) {
                                counter = 3;
                                i++;
                                if (start.charAt(i + 1) == test.charAt(0)) {
                                    counter = 4;
                                    i++;
                                    if (start.charAt(i + 1) == test.charAt(0)) {
                                        counter = 5;
                                        i++;
                                    }}}}}else{
                        if(i != strLine.length()) {
                            reader += strLine.charAt(i);
                        }
                    }}
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }


    }




}







