package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that represents a member registry
 */
public class Registry {
    private ArrayList<Member> members = new ArrayList<>();

    /**
     * Method for adding a member
     * @param m Specifies which member
     */
    public void addMember(Member m){
        members.add(m);
        m.setId(generateId(m));
    }

    /**
     * Method for generating a random member ID
     * @param m Specifies which member to get a randomized ID
     * @return Returns the generated ID as an int
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
     * Method for deleting a member from the registry
     * @param m Specifies which member to be deleted
     */
    public void deleteMember(Member m){
        members.remove(m);
    }

    /**
     * Method to get a member
     * @param id Specifies which id
     * @return Returns member object
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
     * Method for printing a specific member info
     * @param m Specifies which member to print
     */
    public void printMember(Member m) {
        int ID = m.getId();
        String name = m.getName();
        String pNumber = m.getPersonalNumber();
        int boatLength;
        Object boatType;
        String boatInfo;

        /* Table header */
        System.out.printf("%-5s %-22s %-20s %-10s\n", "ID", "Name", "Personal Number", "Boat information");
        /* If member has boats it prints them, otherwise skips printing them */
        if (!m.getBoats().isEmpty()) {
            System.out.printf("%-5s %-22s %-20s %-10s\n", ID, name, pNumber, "0" + ". " + m.getBoats().get(0).getType() + ". " + m.getBoats().get(0).getLength() + "cm");
            for (int i = 1; i < m.countBoats(); i++) {
                boatType = m.getBoats().get(i).getType();
                boatLength = m.getBoats().get(i).getLength();
                boatInfo = i + ". " + boatType + ". " + boatLength + "cm";
                System.out.printf("%-5s %-22s %-20s %-10s\n", "", "", "", boatInfo);
            }
        } else {
            System.out.printf("%-5s %-22s %-20s\n", ID, name, pNumber);
        }
    }

    /**
     * Method for printing a compact list
     */
    public void printCompactList(){
        int ID;
        String name;
        int numberOfBoats;

        /* Table header */
        System.out.printf("%-5s %-20s %-10s\n", "ID", "Name", "Number of Boats");
        /* Loops through the list of members and prints them */
        for (Member m: members) {
            ID = m.getId();
            name = m.getName();
            numberOfBoats = m.countBoats();
            System.out.printf("%-5s %-20s %-10s\n", ID, name, numberOfBoats);
        }
    }

    /**
     * Method for printing a verbose list
     */
    public void printVerboseList(){
        int ID;
        String name;
        String pNumber;
        int boatLength;
        Object boatType;
        String boatInfo;

        /* Table header */
        System.out.printf("%-5s %-22s %-20s %-10s\n", "ID", "Name", "Personal Number", "Boat information");
        /* Loops through the list of members and prints them */
        for (Member m: members) {
            ID = m.getId();
            name = m.getName();
            pNumber = m.getPersonalNumber();
            /* If member has boats it prints them, otherwise skips printing them */
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
            /* Line separator between each member */
            System.out.printf("%-5s %-20s %-20s %-10s\n", "-----", "----------------------", "--------------------", "-------------------------");

        }
    }

    /**
     * Boolean method that checks if member exists
     * @param id Specifies which member ID
     * @return Returns true if member exists, otherwise false
     */
    public boolean memberExists(int id) {
        for (Member m: members) {
            if (id == m.getId())
                return true;
        }
        return false;
    }

    /**
     * Method for saving a registry to a text file
     */
    public void saveRegistry(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write your filepath and filename");
        System.out.println("on mac: /Users/test/Desktop/registry.txt");
        System.out.println("on windows: \\Users\\test\\Desktop\\registry.txt");
        System.out.print(": ");
        String filepath= scanner.nextLine();
        StringBuilder printer = new StringBuilder();

        /* If file ends with .txt */
        if(filepath.matches(".*.txt")) {
            try {
                PrintWriter outputFile = new PrintWriter(new FileOutputStream(filepath, true));
                /* Loops through members and writes a number of % symbols, which is later used when loading specific info */
                for (Member m : members) {
                    printer.append(m.getName()).append("%").append(m.getPersonalNumber()).append("%%").append(m.getId()).append("%%%");
                    /* If member has boats */
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
     * Method for loading a registry from a text file
     */
    public void loadRegistry()throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write your filepath");
        System.out.println("on mac: /Users/test/Desktop/registry.tex");
        System.out.println("on windows: \\Users\\test\\Desktop\\registry.tex");
        System.out.print(": ");
        String filepath= scanner.nextLine();
        File f = new File(filepath);

        /* If file exists and if it ends with .txt */
        if (f.exists() && filepath.matches(".*.txt")) {
            FileInputStream fstream = new FileInputStream(filepath);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            this.members.clear();
            String id = "";
            String name = "";
            String pNumber = "";
            String boatType = "";


            String strLine;
            char symbol = '%';
            try {
                while ((strLine = br.readLine()) != null) {
                    System.out.println();
                    String reader = "";
                    int counter = 0;
                    Member m;
                    /* Loop that goes through the file */
                    for (int i = 0; i < strLine.length() + 1; i++) {
                        if (counter == 1) { // If there is 1 % symbol, set value to name
                            name = reader;
                            reader = "";
                            counter = 0;
                        } else if (counter == 2) { // If there are 2 % symbols, set value to pNumber
                            pNumber = reader;
                            reader = "";
                            counter = 0;
                        } else if (counter == 3) { // If there are 3 % symbols, create member and set value to id
                            m = new Member(pNumber, name);
                            this.addMember(m);
                            m.setId(Integer.valueOf(reader));
                            id = reader;
                            reader = "";
                            counter = 0;
                        } else if (counter == 4) { // If there are 4 % symbols, set value to boatType
                            boatType = reader;
                            reader = "";
                            counter = 0;
                        } else if (counter == 5) { // If there are 5 % symbols, create boat
                            Boat boat = new Boat(Boat.Type.valueOf(boatType.toLowerCase()), Integer.valueOf(reader));
                            this.getMember(Integer.valueOf(id)).addBoat(boat);
                            reader = "";
                            counter = 0;
                        }
                        String start = strLine + "       ";
                        if (start.charAt(i) == symbol) {
                            counter = 1;
                            if (start.charAt(i + 1) == symbol) {
                                counter = 2;
                                i++;
                                if (start.charAt(i + 1) == symbol) {
                                    counter = 3;
                                    i++;
                                    if (start.charAt(i + 1) == symbol) {
                                        counter = 4;
                                        i++;
                                        if (start.charAt(i + 1) == symbol) {
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







