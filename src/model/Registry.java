package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Registry {

    /** Fields */
    private ArrayList<Member> members = new ArrayList<>();

    /**
     * Method for adding a member
     * @param m Specifies a specific member
     */
    public void addMember(Member m){
       members.add(m);
       m.setId(generateId(m));
    }

    /**
     * Method for generating a random Id
     * @param m Specifies a specific member
     * @return Returns a random Id
     */
    private int generateId(Member m){
        int id = (int)(Math.random()*9000)+1000;
        for (int i = 0; i <members.size() ; i++) {
            if (m.getId() == id){
                id = (int)(Math.random()*9000)+1000;
                i = 0;
            }
        }
        return id;
    }

    /**
     * Method for removing a member from array list members
     * @param m Specifies a specific member
     */
    public void deleteMember(Member m){
        members.remove(m);
    }

    /**
     * Method for getting member
     * @param id Specifies a specific member Id
     * @return Returns member corresponding to Id, otherwise prints message and returns null
     */
    public Member getMember(int id) {
        for (Member m : members) {
            if (id == m.getId()) {
                return m;
            }
        }
        System.out.println("Member not found!");
        return null;
    }

    /**
     * Method for printing compact list
     */
    public void getCompactList(){
        int ID;
        String name;
        int numberOfBoats;

        System.out.printf("%-5s %-20s %-10s\n", "ID", "Name", "Number of Boats");

        for (Member m: members) {
            ID = m.getId();
            name = m.getName();
            numberOfBoats = m.countBoats();
            System.out.printf("%-5s %-20s %-10s\n", ID, name, numberOfBoats);
        }
    }

    /**
     * Method for printing verbose list
     */
    public void getVerboseList(){
        int ID;
        String name;
        String pNumber;
        int boatLength;
        Object boatType;
        String boatInfo;

        System.out.printf("%-5s %-22s %-20s %-10s\n", "ID", "Name", "Personal Number", "Boat information");

        for (Member m: members) {
            ID = m.getId();
            name = m.getName();
            pNumber = m.getPersonalNumber();
            if(!m.getBoats().isEmpty()){
                System.out.printf("%-5s %-22s %-20s %-10s\n", ID, name, pNumber, "0" + ". " + m.getBoats().get(0).getType() + ". " + m.getBoats().get(0).getLength()+"cm");
                for (int i = 1; i <m.countBoats(); i++){
                    boatType = m.getBoats().get(i).getType();
                    boatLength = m.getBoats().get(i).getLength();
                    boatInfo = i + ". " + boatType + ". " + boatLength + "cm";
                    System.out.printf("%-5s %-22s %-20s %-10s\n", "", "", "", boatInfo);
                }
            }else{
                System.out.printf("%-5s %-22s %-20s\n", ID, name, pNumber);
            }
            System.out.printf("%-5s %-20s %-20s %-10s\n", "-----", "----------------------", "--------------------", "-------------------------");
           // System.out.printf("%-5s %-20s %-20s %-10s\n", ID, name, pNumber, boatInfo);

        }
    }

    /**
     * Method for checking if a specific member exists
     * @param id Specifies a member id
     * @return Returns true if member exists, otherwise returns false
     */
    public boolean memberExists(int id) {
        for (Member m: members) {
            if (id == m.getId())
                return true;
        }
        return false;
    }

    /**
     * Method for saving registry
     */
    public void saveRegistry(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write your filepath and filename");
        System.out.println("on mac: /Users/test/Desktop/registry.txt");
        System.out.println("on windows: \\Users\\test\\Desktop\\registry.txt");
        System.out.println(": ");
        String filepath= scanner.nextLine();
        StringBuilder printer = new StringBuilder();
        if(filepath.matches(".*.txt")) {
            try {
                PrintWriter outputFile = new PrintWriter(new FileOutputStream(filepath, true));
                for (Member m : members) {
                    printer.append(m.getName()).append("%").append(m.getPersonalNumber()).append("%%").append(m.getId()).append("%%%");
                    if (!m.getBoats().isEmpty()) {
                        for (int i = 0; i < m.countBoats(); i++) {
                            printer.append(m.getBoats().get(i).getType()).append("%%%%").append(m.getBoats().get(i).getLength()).append("%%%%%");
                        }
                    }
                    outputFile.println(printer);
                    printer = new StringBuilder();

                }
            } catch (IOException e1) {
                System.err.println("Can't write to that path!");
                this.saveRegistry();
            }
        }else{
            System.err.println("File need to be of type .txt");
            this.saveRegistry();
            }
    }


    /**
     * Method for loading registry
     */
    public void loadRegistry()throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write your filepath");
        System.out.println("on mac: /Users/test/Desktop/registry.tex");
        System.out.println("on windows: \\Users\\test\\Desktop\\registry.tex");
        System.out.println(": ");
        String filepath= scanner.nextLine();
        File f = new File(filepath);

        if (f.exists()) {
            FileInputStream fstream = new FileInputStream(filepath);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            this.members.clear();
            String id = "";
            String name = "";
            String pNumber = "";
            String boatType = "";


            String strLine;
            String test = "%";
            try {
                while ((strLine = br.readLine()) != null) {
                    System.out.println();
                    String reader = "";
                    int counter = 0;
                    Member m;
                    for (int i = 0; i < strLine.length() + 1; i++) {

                        if (counter == 1) {
                            name = reader;
                            reader = "";
                            counter = 0;
                        } else if (counter == 2) {
                            pNumber = reader;
                            reader = "";
                            counter = 0;
                        } else if (counter == 3) {
                            m = new Member(pNumber, name);
                            this.addMember(m);
                            m.setId(Integer.valueOf(reader));
                            id = reader;
                            reader = "";
                            counter = 0;
                        } else if (counter == 4) {
                            boatType = reader;
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
                                        }
                                    }
                                }
                            }
                        } else {
                            if (i != strLine.length()) {
                                reader += strLine.charAt(i);
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else{
            System.err.println("File/filepath was not found, try again!");
            this.loadRegistry();
        }
    }




}







