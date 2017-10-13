package view;

import model.Boat;
import model.Member;
import model.Registry;

import java.io.*;
import java.util.Scanner;

/**
 * Class that represents the console display
 */
public class Console{
    /** Fields */
    private String name;
    private String pNumber;
    private int id = 0;
    private String input;
    private int boatId = 0;
    private Registry reg;
    private view.langInterface print;
    private String confirm;
    private String boatType = "";
    private String boatLength;



    public void start(Registry registry, view.langInterface view) throws IOException {
        reg = registry;
        print = view;
        
        startPage();
    }

        /**
         * Method that displays the start page alternatives to the user
         *
         *
         */

    public void startPage() throws IOException {
        Scanner scanner = new Scanner(System.in);
        print.startPageHeader();
        confirm = scanner.nextLine();
        /* If the user input equals any of the alternative numbers, otherwise it displays error message and reruns the start() method */
        if (confirm.equals("1") || confirm.equals("2") || confirm.equals("3") || confirm.equals("4") || confirm.equals("5") || confirm.equals("6") || confirm.equals("7")) {
            pageSwitcher(confirm);
        } else {
            print.errorMessage(1);
            startPage();
        }
    }

    /**
     * Method that switches cases with the number given in console
     *
     * @param s Specifies the given page number
     */
    private void pageSwitcher(String s) throws IOException {
        switch (s) {
            case "1": // Add member
                pageOne();
                break;
            case "2": // Select member
                pageTwo();
                break;
            case "3": // Show verbose list
                pageThree();
                break;
            case "4": // Show compact list
                pageFour();
                break;
            case "5": // Load registry
                pageFive();
                break;
            case "6": // Save registry
                pageSix();
                break;
            case "7": // Exit program
                pageSeven();
                break;
            case "8": // Member selected menu
                pageEight();
                break;
            case "9": // Edit member
                pageNine();
                break;
            case "10": // Add boat
                pageTen();
                break;
            case "11": // Edit boat
                pageEleven();
                break;
            case "12": // Delete boat
                pageTwelve();
                break;
            case "14": // Display member info
                pageFourteen();
                break;
        }
    }

    /**
     * Method that displays the add member function to the user
     */
    private void pageOne() throws IOException {
        Scanner scanner = new Scanner(System.in);
        print.addMemHeader();
        if (!scanner.nextLine().equals("0")) { // If scanner.nextLine() isn't 0
            print.outputMessage(1);
            name = scanner.nextLine();
            if (name.isEmpty() || name.matches(".*\\d+.*")) { // If name is empty or contains digits
                print.errorMessage(2);
                pageSwitcher("1");
            }
            print.outputMessage(2);
            pNumber = scanner.nextLine();
            String clean = pNumber.replaceAll("-", "");
            if (!clean.matches("\\d+")) {
                print.errorMessage(3); // If personal number contains letters or symbols
                pageSwitcher("1");
            } else {
                print.outputMessage(3);
                confirm = scanner.nextLine();
                if (confirm.toLowerCase().equals("yes")) { // If input equals yes
                    Member m = new Member(pNumber, name);
                    reg.addMember(m);
                    String info = m.getName() + "(Id=" + m.getId() + ") ";
                    print.addConfirmation(info);
                    startPage();
                } else if (confirm.toLowerCase().equals("no")) { // If input equals no
                    print.outputMessage(4);
                    pageSwitcher("1");
                } else { // Prints error message if something other than yes or no is inputted
                    print.errorMessage(4);
                    pageSwitcher("1");
                }
            }
        } else { // If 0, rerun start
            startPage();
        }
    }

    /**
     * Method that displays the select member function to the user
     */
    private void pageTwo() throws IOException {
        Scanner scanner = new Scanner(System.in);
        print.selectMemHeader();
        if (!scanner.nextLine().equals("0")) { // If scanner.nextLine() isn't 0
            print.outputMessage(5);
            String temp = scanner.nextLine();
            if (temp.matches("\\d+")) { // If the input contains digits
                id = Integer.valueOf(temp);
                if (reg.memberExists(id)) { // If member exists
                    pageSwitcher("8");
                } else { // If member doesn't exist, print error message and return to page 2
                    print.errorMessage(5);
                    pageSwitcher("2");
                }
            } else { // Prints error message if id contains anything other than digits
                print.errorMessage(7);
                pageSwitcher("2");
            }

        } else { // Rerun start method
            startPage();
        }

    }

    /**
     * Method for displaying the verbose list to the user
     */
    private void pageThree() throws IOException {
        Scanner scanner = new Scanner(System.in);
        print.verboseListHeader();
        if (!scanner.nextLine().equals("0")) {  /*User didn't press return. Showing verbose list*/
            this.printVerboseList();
            print.outputMessage(6);   /*Waiting for key press to return to start page*/
            scanner.nextLine();
            startPage();
        }

    }

    /**
     * Method for displaying the compact list to the user
     */
    private void pageFour() throws IOException {
        Scanner scanner = new Scanner(System.in);
        print.compactListHeader();

        if (!scanner.nextLine().equals("0")) {  /*User didnt press return. Showing compact list*/
            this.printCompactList();
            print.outputMessage(6);
            scanner.nextLine();
            startPage();
        }
    }

    /**
     * Method displaying the function to load a registry
     */
    private void pageFive() throws IOException {
        Scanner scanner = new Scanner(System.in);
        print.loadRegHeader();

        confirm = scanner.nextLine();
        if (confirm.toLowerCase().equals("yes")) {  /*User wants to load registry. Loading and return to start*/
            print.filePathHeader();
            String filepath= scanner.nextLine();
            File f = new File(filepath);

        /* If file exists and if it ends with .txt */
            if (f.exists() && filepath.matches(".*.txt")) {
                reg.loadRegistry(filepath);
            }else{
                System.err.println("File/filepath was not found, try again!");
                pageFive();
            }
            print.outputMessage(7);
            startPage();
        }else if (confirm.toLowerCase().equals("no")) {   /*User dint want to load. returning to start*/
            startPage();
        } else {      /*User typed wrong, yes/no*/
            print.errorMessage(4);
            pageSwitcher("5");
        }

    }

    /**
     * Method displaying the function to save a registry
     */
    private void pageSix() throws IOException {
        Scanner scanner = new Scanner(System.in);
        print.saveRegHeader();

        confirm = scanner.nextLine();
        if (confirm.toLowerCase().equals("yes")) {  /*User wants to save registry. Saving and returning to start*/
            print.filePathHeader();
            String filepath= scanner.nextLine();
            /* If file ends with .txt */
            if(filepath.matches(".*.txt")) {
                try{
                    PrintWriter outputFile = new PrintWriter(new FileOutputStream(filepath, true));

                }catch (IOException e1) {
                    System.err.println("Can't write to that path!");
                    pageSix();
                }
            }else{
                System.err.println("File need to be of type .txt");
                pageSix();
            }
            reg.saveRegistry(filepath);
            print.outputMessage(8);
            startPage();
        } else if (confirm.toLowerCase().equals("no")) {   /*User didnt want to save registry. returning to start*/
            startPage();
        } else {      /*User typed wrong, yes/no*/
            print.errorMessage(4);
            pageSwitcher("6");
        }

    }

    /**
     * Method that displays the option to exit the software to the user
     */
    private void pageSeven() throws IOException {
        Scanner scanner = new Scanner(System.in);
        print.outputMessage(9);
        confirm = scanner.nextLine();
        if (confirm.toLowerCase().equals("yes")) {  /*User wants to exit program*/
            print.outputMessage(10);
            System.exit(0);
        } else if (confirm.toLowerCase().equals("no")) {    /*User didn't want to exit program. return to start page*/
            startPage();
        } else {            /*User typed wrong, yes/no*/
            print.errorMessage(4);
            pageSwitcher("7");
        }
    }

    /**
     * Method that displays the options for a specific member to the user
     */
    private void pageEight() throws IOException {/*Select member*/
        Scanner scanner = new Scanner(System.in);
        if (reg.memberExists(id)) {/*Checking if user exist*/
            print.memberMenuHeader(id);
            input = scanner.nextLine();
            while (!input.equals("0")) {  /*User didn't press return*/
                if (input.equals("13")) { /*User wants to delete member*/
                    String info = reg.getMember(id).getName() + "(" + reg.getMember(id).getId() + ") ";
                    print.deleteConfirmation(info);/*Asking user if he wants to delete member*/
                    confirm = scanner.nextLine();
                    if (confirm.toLowerCase().equals("yes")) { /*User types yes, delete member*/
                        reg.deleteMember(reg.getMember(id));
                        print.outputMessage(11);
                        startPage();
                    } else if (confirm.toLowerCase().equals("no")) {  /*User types no, return to page 8*/
                        print.outputMessage(12);
                        pageSwitcher("8");

                    } else { /*User didnt write yes or no, go back to delete member and ask again*/
                        print.errorMessage(4);
                        input = "13";
                    }
                } else if (input.matches("\\d+") && (Integer.valueOf(input) <= 14 && Integer.valueOf(input) >= 9)) {/*User wants to go to another page*/
                    pageSwitcher(input);/*change page*/
                } else {
                    print.errorMessage(1);/*User pressed wrong button or a page that dosent exist, return to page 8 again*/
                    pageEight();
                }
            }
            startPage();
        } else {/*User dosent exist, return to page 2 and ask user to write a new id*/
            print.errorMessage(5);
            pageSwitcher("2");
        }
    }

    /**
     * Method that displays the options for editing a specific member to the user
     */
    private void pageNine() throws IOException {/*Edit member*/
        Scanner scanner = new Scanner(System.in);
        print.editMemberHeader(id);

        input = scanner.nextLine();
        if (!input.equals("0")) {/*User didnt press return*/
            switch (input) {
                case "1":  /*User pressed edit name*/
                    print.outputMessage(1);
                    name = scanner.nextLine();
                    if (name.isEmpty() || name.matches("\\d+")) {   /*Checking if name is incorrect, empty or digits.  Return to edit member and ask again.*/
                        print.errorMessage(2);
                        pageSwitcher("9");
                    } else { /*If name was correct, change name*/
                        reg.getMember(id).setName(name);
                        pageSwitcher("9");
                    }
                    break;
                case "2":   /*User pressed edit personal number*/
                    print.outputMessage(2);
                    pNumber = scanner.nextLine();
                    if (!pNumber.matches("\\d+")) {   /*Checking if pNumber is incorrect, not digits. Return to edit member and ask again. */
                        print.errorMessage(3);
                        pageSwitcher("9");
                    } else {  /*If pNumber was correct, change pNumber*/
                        reg.getMember(id).setPersonalNumber(pNumber);
                        pageSwitcher("9");
                    }
                    break;
                default:    /*If user didnt press any of the keys listed in the menu, return to edit member again and ask for right input*/
                    print.errorMessage(1);
                    pageSwitcher("9");
                    break;
            }
        } else {  /*User pressed return, return to page 8(select member)*/
            pageSwitcher("8");
        }
    }

    /**
     * Method that display the function for adding a boat to the user
     */
    private void pageTen() throws IOException {
        Scanner scanner = new Scanner(System.in);
        print.addBoatHeader();

        if (!scanner.nextLine().equals("0")) {  /*User didnt press return*/
            print.outputMessage(13);
            boatType = scanner.nextLine().toLowerCase();
            if (boatType.equals("sailboat") || boatType.equals("motorsailer") || boatType.equals("canoe") || boatType.equals("other")) {    /*Checks if user typed correct boat type*/
                print.outputMessage(14);
                boatLength = scanner.nextLine();
                if (boatLength.matches("\\d+")) {    /*Checking if user typed correct boat length, only digits*/
                    print.outputMessage(15);
                    confirm = scanner.nextLine();
                    if (confirm.toLowerCase().equals("yes")) {  /*If user wants to add boat, add boat to member*/
                        Boat boat = new Boat(Boat.Type.valueOf(boatType.toLowerCase()), Integer.valueOf(boatLength));
                        reg.getMember(id).addBoat(boat);
                        String info = boatType + "(" + boatLength;
                        print.addBoatConfirmation(info);
                        pageSwitcher("10");
                    } else if (confirm.toLowerCase().equals("no")) {    /*If user dosent want to add boat, return to page 10 again*/
                        print.outputMessage(16);
                        pageSwitcher("10");
                    } else {        /*User typed wrong input, yes/no. Return to page 10 again*/
                        print.errorMessage(4);
                        pageSwitcher("10");
                    }
                } else {  /*If boatlenght was incorrect, not digits.  return to page 10 again*/
                    print.errorMessage(8);
                    pageSwitcher("10");
                }
            } else {  /*If boattype was incorrect. return to page 10 again*/
                print.errorMessage(9);
                pageSwitcher("10");
            }
        } else {    /*If user pressed return, return to page 8*/
            pageSwitcher("8");
        }

    }

    /**
     * Method that displays the function for editing a specific boat
     */
    private void pageEleven() throws IOException {
        Scanner scanner = new Scanner(System.in);
        print.editBoatHeader();
        if (!scanner.nextLine().equals("0")) {      /*User didnt press return*/
            print.outputMessage(5);
            String tempBoatID = scanner.nextLine();
            if (tempBoatID.matches("\\d+")) {     /*Checks if boat id is correct, only digits*/
                boatId = Integer.valueOf(tempBoatID);
                if (reg.getMember(id).boatExists(boatId)) { /*Checks if boat exist*/
                    boatId = Integer.parseInt(tempBoatID);
                    String oldType = "" + reg.getMember(id).getBoats().get(boatId).getType() + "(" + reg.getMember(id).getBoats().get(boatId).getLength() + "cm)";
                    print.editBoatConfirmation(oldType);
                    confirm = scanner.nextLine();
                    if (confirm.toLowerCase().equals("yes")) {  /*User wants to edit boat*/
                        print.outputMessage(13);
                        boatType = scanner.nextLine();
                        if (boatType.equals("sailboat") || boatType.equals("motorsailer") || boatType.equals("canoe") || boatType.equals("other")) {   /*Checking if boattype is correct, no digits and right type*/
                            print.outputMessage(14);
                            boatLength = scanner.nextLine();
                            if (boatLength.matches("\\d+")) {    /*Checking if boatlenght is correct, only digits.*/
                                reg.getMember(id).getBoats().get(boatId).setType(Boat.Type.valueOf(boatType.toLowerCase()));
                                reg.getMember(id).getBoats().get(boatId).setLength(Integer.valueOf(boatLength));
                                String newInfo = reg.getMember(id).getBoats().get(boatId).getType() + "(" + reg.getMember(id).getBoats().get(boatId).getLength();
                                print.editBoatMessage(oldType, newInfo);
                                pageSwitcher("11");
                            } else {  /*Boatlengt was incorrect, not digits. Return to page 11 again*/
                                print.errorMessage(8);
                                pageSwitcher("11");
                            }
                        } else {  /*Boattype was incorrect, digits or wrong type*/
                            print.errorMessage(9);
                            pageSwitcher("11");
                        }
                    } else if (confirm.toLowerCase().equals("no")) {    /*User didnt want to edit boat*/
                        print.outputMessage(17);
                        pageSwitcher("8");
                    } else {    /*User typed wrong input, not yes/no*/
                        print.errorMessage(4);
                    }
                } else {/*User typed wrong id, boat doesn't exist*/
                    print.errorMessage(6);
                    pageSwitcher("11");
                }
            } else {  /*Id was not digits, return to page 11 again*/
                print.errorMessage(7);
                pageSwitcher("11");
            }
        } else {      /*User pressed return*/
            pageSwitcher("8");
        }
    }

    /**
     * Method that displays the function for deleting a specific boat
     */
    private void pageTwelve() throws IOException {
        Scanner scanner = new Scanner(System.in);
        print.deleteBoatHeader();
        if (!scanner.nextLine().equals("0")) {  /*User didnt press return*/
            print.outputMessage(5);
            String tempBoatID = scanner.nextLine();
            if (tempBoatID.matches("\\d+")) {   /*Checks if boatid was correct, only digits*/
                boatId = Integer.valueOf(tempBoatID);
                if (reg.getMember(id).boatExists(boatId)) { /*Checking if boat with that id exist*/
                    String info = +boatId + ". " + reg.getMember(id).getBoats().get(boatId).getType() + " " + reg.getMember(id).getBoats().get(boatId).getLength();
                    print.deleteBoatConfiramtion(info);
                    confirm = scanner.nextLine();
                    if (confirm.toLowerCase().equals("yes")) {  /*User wants to remove boat. removing boat and return to page 8*/
                        reg.getMember(id).getBoats().remove(boatId);
                        pageSwitcher("8");
                    } else if (confirm.toLowerCase().equals("no")) {   /*User didnt't want to remove boat. return to page 8*/
                        print.outputMessage(18);
                        pageSwitcher("8");
                    } else {      /*User typed wrong, yes/no*/
                        print.errorMessage(4);
                        pageSwitcher("12");
                    }
                } else {      /*Wrong id, no boat with that id*/
                    print.errorMessage(6);
                    pageSwitcher("12");
                }

            } else {      /*Boatid typed incorrect, not digits*/
                print.errorMessage(7);
                pageSwitcher("12");
            }
        } else {       /*User pressed return. return to page 8*/
            pageEight();
        }
    }

    /**
     * Method that displays the information of a specific member to the user
     */
    private void pageFourteen() throws IOException {
        Scanner scanner = new Scanner(System.in);
        print.memberInfoHeader(id);
        if (!scanner.nextLine().equals("0")) {      /*User didnt press return. showing information*/
            this.printMember(reg.getMember(id));
            print.outputMessage(6);
            scanner.nextLine();
            pageSwitcher("8");
        }
    }

    /**
     * Method for printing a verbose list
     */
    public void printVerboseList() {
        int ID;
        String name;
        String pNumber;
        int boatLength;
        Object boatType;
        String boatInfo;
        /* Table header */
        System.out.printf("%-5s %-22s %-20s %-10s\n", "ID", "Name", "Personal Number", "Boat information");
        /* Loops through the list of members and prints them */
        for (Member m : reg.getMembers()) {
            ID = m.getId();
            name = m.getName();
            pNumber = m.getPersonalNumber();
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
            /* Line separator between each member */
            System.out.printf("%-5s %-20s %-20s %-10s\n", "-----", "----------------------", "--------------------", "-------------------------");

        }
    }

    /**
     * Method for printing a compact list
     */
    public void printCompactList() {
        int ID;
        String name;
        int numberOfBoats;

        /* Table header */
        System.out.printf("%-5s %-20s %-10s\n", "ID", "Name", "Number of Boats");
        /* Loops through the list of members and prints them */
        for (Member m : reg.getMembers()) {
            ID = m.getId();
            name = m.getName();
            numberOfBoats = m.countBoats();
            System.out.printf("%-5s %-20s %-10s\n", ID, name, numberOfBoats);
        }
    }


    /**
     * Method for printing a specific member info
     *
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
}
