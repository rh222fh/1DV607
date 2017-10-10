package view;

import model.Boat;
import model.Member;
import model.Registry;

import java.io.IOException;
import java.util.Scanner;

/**
 * Class that represents the console display
 */
public class Console {
    /** Fields */
    private String name;
    private String pNumber;
    private int id = 0;
    private String input;
    private int boatId = 0;
    private Registry reg = new Registry();
    private EngPrinter engPrint = new EngPrinter();
    private String confirm;
    private String boatType = "";
    private String boatLength;

    /**
     * Method that displays the start page alternatives to the user
     */
    public void start() throws IOException{
        Scanner scanner = new Scanner(System.in);
        engPrint.startPageHeader();
        confirm = scanner.nextLine();
        /* If the user input equals any of the alternative numbers, otherwise it displays error message and reruns the start() method */
        if (confirm.equals("1") || confirm.equals("2") || confirm.equals("3") || confirm.equals("4") || confirm.equals("5") || confirm.equals("6") || confirm.equals("7")) {
            pageSwitcher(confirm);
        }
        else {
            engPrint.errorMessage(1);
            start();
        }
    }

    /**
     * Method that switches cases with the number given in console
     * @param s Specifies the given page number
     */
    private void pageSwitcher(String s) throws IOException{
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
        engPrint.addMemHeader();
        if (!scanner.nextLine().equals("0")) { // If scanner.nextLine() isn't 0
            System.out.print("Enter name: ");
            name = scanner.nextLine();
            if (name.isEmpty() || name.matches("\\d+")) { // If name is empty or contains digits
                engPrint.errorMessage(2);
                pageSwitcher("1");
            }
            System.out.print("Enter personal number: ");
            pNumber = scanner.nextLine();
            String clean = pNumber.replaceAll("-", "");
            if(!clean.matches("\\d+")){
                engPrint.errorMessage(3); // If personal number contains letters or symbols
                pageSwitcher("1");
            }
            else {
                System.out.println("Add member to registry? Yes/No");
                confirm = scanner.nextLine();
                if (confirm.toLowerCase().equals("yes")) { // If input equals yes
                    Member m = new Member(pNumber, name);
                    reg.addMember(m);
                    System.out.println(m.getName() + "(Id=" + m.getId() + ") " + "added to registry");
                    start();
                } else if (confirm.toLowerCase().equals("no")) { // If input equals no
                    System.out.println("Member was not added.");
                    pageSwitcher("1");
                } else { // Prints error message if something other than yes or no is inputted
                    engPrint.errorMessage(4);
                    pageSwitcher("1");
                }
            }
        }
        else { // If 0, rerun start
            start();
        }
    }

    /**
     * Method that displays the select member function to the user
     */
    private void pageTwo() throws IOException{
        Scanner scanner = new Scanner(System.in);
        engPrint.selectMemHeader();
        if (!scanner.nextLine().equals("0")) { // If scanner.nextLine() isn't 0
            System.out.print("Enter ID: ");
            String temp = scanner.nextLine();
            if (temp.matches("\\d+")) { // If the input contains digits
                id = Integer.valueOf(temp);
                if (reg.memberExists(id)) { // If member exists
                    pageSwitcher("8");
                } else { // If member doesn't exist, print error message and return to page 2
                    engPrint.errorMessage(5);
                    pageSwitcher("2");
                }
            }
            else { // Prints error message if id contains anything other than digits
                engPrint.errorMessage(7);
                pageSwitcher("2");
            }

        }
        else { // Rerun start method
            start();
        }

    }

    /**
     * Method for displaying the verbose list to the user
     */
    private void pageThree() throws IOException{
        Scanner scanner = new Scanner(System.in);
        engPrint.verboseListHeader();
        if (!scanner.nextLine().equals("0")) {  /*User didn't press return. Showing verbose list*/
            reg.printVerboseList();
            System.out.print("Press any key to return to startpage....");   /*Waiting for key press to return to start page*/
            scanner.nextLine();
            start();
        }

    }

    /**
     * Method for displaying the compact list to the user
     */
    private void pageFour() throws IOException{
        Scanner scanner = new Scanner(System.in);
        engPrint.compactListHeader();

        if (!scanner.nextLine().equals("0")) {  /*User didnt press return. Showing compact list*/
            reg.printCompactList();
            System.out.print("Press any key to return to startpage....");   /*Waiting for key press to return to startpage*/
            scanner.nextLine();
            start();
        }
    }

    /**
     * Method displaying the function to load a registry
     */
    private void pageFive() throws IOException{
        Scanner scanner = new Scanner(System.in);
        engPrint.loadRegHeader();

        confirm = scanner.nextLine();
        if (confirm.toLowerCase().equals("yes")) {  /*User wants to load registry. Loading and return to start*/
            reg.loadRegistry();
            System.out.println("Registry was loaded!");
            start();
        }
        else if(confirm.toLowerCase().equals("no")) {   /*User dint want to load. returning to start*/
            start();
        }
        else {      /*User typed wrong, yes/no*/
            engPrint.errorMessage(4);
            pageSwitcher("5");
        }

    }

    /**
     * Method displaying the function to save a registry
     */
    private void pageSix() throws IOException{
        Scanner scanner = new Scanner(System.in);
        engPrint.saveRegHeader();

        confirm = scanner.nextLine();
        if (confirm.toLowerCase().equals("yes")) {  /*User wants to save registry. Saving and returning to start*/
            reg.saveRegistry();
            System.out.println("Registry was saved");
            start();
        }
        else if(confirm.toLowerCase().equals("no")) {   /*User didnt want to save registry. returning to start*/
            start();
        }
        else {      /*User typed wrong, yes/no*/
            engPrint.errorMessage(4);
            pageSwitcher("6");
        }

    }

    /**
     * Method that displays the option to exit the software to the user
     */
    private void pageSeven() throws IOException{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Are you sure that you want to exit? Yes/No");
        confirm = scanner.nextLine();
        if (confirm.toLowerCase().equals("yes")) {  /*User wants to exit program*/
            System.out.println("Stopping program....");
            System.exit(0);
        } else if (confirm.toLowerCase().equals("no")) {    /*User didn't want to exit program. return to start page*/
            start();
        } else {            /*User typed wrong, yes/no*/
            engPrint.errorMessage(4);
            pageSwitcher("7");
        }
    }

    /**
     * Method that displays the options for a specific member to the user
     */
    private void pageEight() throws IOException{/*Select member*/
        Scanner scanner = new Scanner(System.in);
        if (reg.memberExists(id)) {/*Checking if user exist*/
            engPrint.memberMenuHeader(id);
            input = scanner.nextLine();

            while (!input.equals("0")) {  /*User didn't press return*/

                   if(input.equals("13")) { /*User wants to delete member*/

                       System.out.println("Are you sure that you want to delete " + reg.getMember(id).getName() + "(" + reg.getMember(id).getId() + ")" + " Yes/No");/*Asking user if he wants to delete member*/
                       confirm = scanner.nextLine();

                       if (confirm.toLowerCase().equals("yes")) { /*User types yes, delete member*/
                           reg.deleteMember(reg.getMember(id));
                           System.out.println("Member " + "id " + "was deleted.");
                           start();

                       } else if (confirm.toLowerCase().equals("no")) {  /*User types no, return to page 8*/
                           System.out.println(reg.getMember(id).getName() + " was not deleted.");
                           pageSwitcher("8");

                       } else { /*User didnt write yes or no, go back to delete member and ask again*/
                           engPrint.errorMessage(4);
                           input = "13";
                       }
                   }else if(input.matches("\\d+")&&(Integer.valueOf(input)<=14 && Integer.valueOf(input)>=9)){/*User wants to go to another page*/
                       pageSwitcher(input);/*change page*/
                   }else{
                       engPrint.errorMessage(1);/*User pressed wrong button or a page that dosent exist, return to page 8 again*/
                       pageEight();
                }
            }
            start();
        }
        else {/*User dosent exist, return to page 2 and ask user to write a new id*/
            engPrint.errorMessage(5);
            pageSwitcher("2");
        }
    }

    /**
     * Method that displays the options for editing a specific member to the user
     */
    private void pageNine() throws IOException{/*Edit member*/
        Scanner scanner = new Scanner(System.in);
        engPrint.editMemberHeader(id);

        input = scanner.nextLine();
        if (!input.equals("0")) {/*User didnt press return*/
            switch (input) {
                case "1":  /*User pressed edit name*/
                    System.out.print("Enter name: ");
                    name = scanner.nextLine();
                    if (name.isEmpty() || name.matches("\\d+")) {   /*Checking if name is incorrect, empty or digits.  Return to edit member and ask again.*/
                        engPrint.errorMessage(2);
                        pageSwitcher("9");
                    }else { /*If name was correct, change name*/
                        reg.getMember(id).setName(name);
                        pageSwitcher("9");
                    }
                    break;
                case "2":   /*User pressed edit personal number*/
                    System.out.print("Enter personal number: ");
                    pNumber = scanner.nextLine();
                    if(!pNumber.matches("\\d+")){   /*Checking if pNumber is incorrect, not digits. Return to edit member and ask again. */
                        engPrint.errorMessage(3);
                        pageSwitcher("9");
                    }else{  /*If pNumber was correct, change pNumber*/
                        reg.getMember(id).setPersonalNumber(pNumber);
                        pageSwitcher("9");
                    }
                    break;
                default:    /*If user didnt press any of the keys listed in the menu, return to edit member again and ask for right input*/
                    engPrint.errorMessage(1);
                    pageSwitcher("9");
                    break;
            }
        }
        else {  /*User pressed return, return to page 8(select member)*/
            pageSwitcher("8");
        }
    }

    /**
     * Method that display the function for adding a boat to the user
     */
    private void pageTen() throws IOException{
        Scanner scanner = new Scanner(System.in);
        engPrint.addMemHeader();

        if (!scanner.nextLine().equals("0")) {  /*User didnt press return*/
            System.out.print("Enter type (Sailboat, Motorsailer, Canoe, Other): ");
            boatType = scanner.nextLine().toLowerCase();
            if(boatType.equals("sailboat")||boatType.equals("motorsailer")||boatType.equals("canoe")||boatType.equals("other")){    /*Checks if user typed correct boat type*/
                System.out.print("Enter boat length(cm): ");
                boatLength = scanner.nextLine();
                if(boatLength.matches("\\d+")) {    /*Checking if user typed correct boat length, only digits*/
                    System.out.println("Add boat to member? Yes/No");
                    confirm = scanner.nextLine();
                    if (confirm.toLowerCase().equals("yes")) {  /*If user wants to add boat, add boat to member*/
                        Boat boat = new Boat(Boat.Type.valueOf(boatType.toLowerCase()), Integer.valueOf(boatLength));
                        reg.getMember(id).addBoat(boat);
                        System.out.println(boatType + "(" +boatLength + "cm) was added");
                        pageSwitcher("10");
                    } else if (confirm.toLowerCase().equals("no")) {    /*If user dosent want to add boat, return to page 10 again*/
                        System.out.println("Boat was not added.");
                        pageSwitcher("10");
                    } else {        /*User typed wrong input, yes/no. Return to page 10 again*/
                        engPrint.errorMessage(4);
                        pageSwitcher("10");
                    }
                }else{  /*If boatlenght was incorrect, not digits.  return to page 10 again*/
                    engPrint.errorMessage(8);
                    pageSwitcher("10");
                }
            }else{  /*If boattype was incorrect. return to page 10 again*/
                engPrint.errorMessage(9);
                pageSwitcher("10");
            }}else {    /*If user pressed return, return to page 8*/
            pageSwitcher("8");
        }

    }

    /**
     * Method that displays the function for editing a specific boat
     */
    private void pageEleven() throws IOException {
        Scanner scanner = new Scanner(System.in);
        engPrint.editBoatHeader();

        if (!scanner.nextLine().equals("0")) {      /*User didnt press return*/
            System.out.print("Enter ID: ");
            String tempBoatID = scanner.nextLine();
            if (tempBoatID.matches("\\d+")) {     /*Checks if boat id is correct, only digits*/
                boatId = Integer.valueOf(tempBoatID);
                if (reg.getMember(id).boatExists(boatId)) { /*Checks if boat exist*/
                    boatId = Integer.parseInt(tempBoatID);
                    String oldType = "" + reg.getMember(id).getBoats().get(boatId).getType() + "(" + reg.getMember(id).getBoats().get(boatId).getLength() + "cm)";
                    System.out.println("Are you sure you want to edit " + reg.getMember(id).getBoats().get(boatId).getType() + "(" + reg.getMember(id).getBoats().get(boatId).getLength() + "cm)" + "? Yes/No");
                    confirm = scanner.nextLine();
                    if (confirm.toLowerCase().equals("yes")) {  /*User wants to edit boat*/
                        System.out.print("Enter type (Sailboat, Motorsailer, Canoe, Other): ");
                        boatType = scanner.nextLine();
                        if(boatType.equals("sailboat")||boatType.equals("motorsailer")||boatType.equals("canoe")||boatType.equals("other")) {   /*Checking if boattype is correct, no digits and right type*/
                            System.out.print("Enter boat length(cm): ");
                            boatLength = scanner.nextLine();
                            if(boatLength.matches("\\d+")) {    /*Checking if boatlenght is correct, only digits.*/
                                reg.getMember(id).getBoats().get(boatId).setType(Boat.Type.valueOf(boatType.toLowerCase()));
                                reg.getMember(id).getBoats().get(boatId).setLength(Integer.valueOf(boatLength));
                                System.out.println(oldType + " was changed to " + reg.getMember(id).getBoats().get(boatId).getType() + "(" + reg.getMember(id).getBoats().get(boatId).getLength() + "cm)");
                                pageSwitcher("11");
                            }else{  /*Boatlengt was incorrect, not digits. Return to page 11 again*/
                                engPrint.errorMessage(8);
                                pageSwitcher("11");
                            }
                        }else{  /*Boattype was incorrect, digits or wrong type*/
                            engPrint.errorMessage(9);
                            pageSwitcher("11");
                        }
                    } else if (confirm.toLowerCase().equals("no")) {    /*User didnt want to edit boat*/
                        System.out.println(oldType + " was not edited.");
                        pageSwitcher("8");
                    } else {    /*User typed wrong input, not yes/no*/
                        engPrint.errorMessage(4);
                    }
                }
                else {/*User typed wrong id, boat doesn't exist*/
                    engPrint.errorMessage(6);
                    pageSwitcher("11");
                }
            }
            else {  /*Id was not digits, return to page 11 again*/
                engPrint.errorMessage(7);
                pageSwitcher("11");
            }
        }
        else {      /*User pressed return*/
            pageSwitcher("8");
        }
    }

    /**
     * Method that displays the function for deleting a specific boat
     */
    private void pageTwelve() throws IOException{
        Scanner scanner = new Scanner(System.in);
        engPrint.deleteBoatHeader();
        if (!scanner.nextLine().equals("0")) {  /*User didnt press return*/
            System.out.print("Enter ID: ");
            String tempBoatID = scanner.nextLine();
            if (tempBoatID.matches("\\d+")) {   /*Checks if boatid was correct, only digits*/
                boatId = Integer.valueOf(tempBoatID);
                if (reg.getMember(id).boatExists(boatId)) { /*Checking if boat with that id exist*/
                    System.out.println("Are you sure you want to remove: "+boatId +". " + reg.getMember(id).getBoats().get(boatId).getType() +" " + reg.getMember(id).getBoats().get(boatId).getLength() + "cm ?");
                    confirm = scanner.nextLine();
                    if (confirm.toLowerCase().equals("yes")) {  /*User wants to remove boat. removing boat and return to page 8*/
                        reg.getMember(id).getBoats().remove(boatId);
                        pageSwitcher("8");
                    }
                    else if(confirm.toLowerCase().equals("no")) {   /*User didnt't want to remove boat. return to page 8*/
                        System.out.println("The boat was not deleted.");
                        pageSwitcher("8");
                    }
                    else {      /*User typed wrong, yes/no*/
                        engPrint.errorMessage(4);
                        pageSwitcher("12");
                    }
                }
                else {      /*Wrong id, no boat with that id*/
                    engPrint.errorMessage(6);
                    pageSwitcher("12");
                }

            }
            else {      /*Boatid typed incorrect, not digits*/
                engPrint.errorMessage(7);
                pageSwitcher("12");
            }
        }
        else{       /*User pressed return. return to page 8*/
            pageEight();
        }
    }

    /**
     * Method that displays the information of a specific member to the user
     */
    private void pageFourteen() throws IOException {
        Scanner scanner = new Scanner(System.in);
        engPrint.memberInfoHeader(id);
        if (!scanner.nextLine().equals("0")) {      /*User didnt press return. showing information*/
            reg.printMember(reg.getMember(id));
            System.out.print("Press any key to return to startpage....");   /*Waiting for key press, return to page 8*/
            scanner.nextLine();
            pageSwitcher("8");
        }
    }
}
