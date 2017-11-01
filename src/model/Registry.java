package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
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
     * Method for returning all of the members
     */
    public Iterator<Member> getMemberIterator(){
        return this.members.iterator();
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
        return null;
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
    public void saveRegistry(String filepath){
        //String filepath= scanner.nextLine();
        StringBuilder printer = new StringBuilder();
        Iterator<Member> memberIterator = this.getMemberIterator();
        Iterator<Boat> boatIterator;

        /* If file ends with .txt */
        if(filepath.matches(".*.txt")) {
            try {
                PrintWriter outputFile = new PrintWriter(new FileOutputStream(filepath, false));
                /* Loops through members and writes a number of % symbols, which is later used when loading specific info */
               while(memberIterator.hasNext()) {
                   Member member = memberIterator.next();
                    printer.append(member.getName()).append("%").append(member.getPersonalNumber()).append("%%").append(member.getId()).append("%%%");
                    /* If member has boats */
                    if (member.hasBoats()) {
                        boatIterator = member.getBoatIterator();
                        while(boatIterator.hasNext()) {
                            Boat boat = boatIterator.next();
                            printer.append(boat.getType()).append("%%%%").append(boat.getLength()).append("%%%%%");
                        }
                    }
                    outputFile.println(printer);
                    printer = new StringBuilder();

                }
                outputFile.close();
            } catch (IOException e1) {
              //  System.err.println("Can't write to that path!");
            }
        }
    }

    /**
     * Method for loading a registry from a text file
     */
    public void loadRegistry(String filepath)throws FileNotFoundException {
        File file = new File(filepath);

        /* If file exists and if it ends with .txt */
        if (file.exists() && filepath.matches(".*.txt")) {
            FileInputStream fstream = new FileInputStream(filepath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fstream));

            this.members.clear();
            String id = "";
            String name = "";
            String pNumber = "";
            String boatType = "";


            String strLine;
            char symbol = '%';
            try {
                while ((strLine = bufferedReader.readLine()) != null) {
                    System.out.println();
                    String reader = "";
                    int counter = 0;
                    Member member;
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
                            member = new Member(pNumber, name);
                            this.addMember(member);
                            member.setId(Integer.valueOf(reader));
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
        }
    }
}







