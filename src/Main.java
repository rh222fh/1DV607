import Back.Boat;
import Back.Member;
import Back.Registry;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        String name;
        String pNumber;
        int id = 0;
        String input;
        int boatId = 0;

        Registry reg = new Registry();
        String page = "0";
        String confirm;

        while (!page.equals("7")) {
            switch (page) {
                case "0":
                    System.out.println("|=============== Startpage ================|");
                    System.out.println("| Select a number to get to the             |");
                    System.out.println("| corresponding page.                       |");
                    System.out.println("| 1. Add member(s)                          |");
                    System.out.println("| 2. Select member                          |");
                    System.out.println("| 3. Show verbose list                      |");
                    System.out.println("| 4. Show compact list                      |");
                    System.out.println("| 5. Load registry                          |");
                    System.out.println("| 6. Save registry                          |");
                    System.out.println("| 7. Exit                                   |");
                    System.out.println("|===========================================|");
                    System.out.print(":");
                    confirm = scanner.nextLine();

                    if (confirm.equals("1") || confirm.equals("2") || confirm.equals("3") || confirm.equals("4") || confirm.equals("5") || confirm.equals("6") || confirm.equals("7")) {
                        page = confirm;
                    }
                    else {
                        System.err.println("You can only press one of the keys listed above, try again.");
                    }
                    break;
                case "1":
                    System.out.println("|================ Add Member ===============|");
                    System.out.println("| Enter member information and press        |");
                    System.out.println("| ENTER to continue or press 0 to return.   |");
                    System.out.println("| 0. Return                                 |");
                    System.out.println("|===========================================|");
                    System.out.print("Waiting for key press...");
                    if (!scanner.nextLine().equals("0")) {
                        System.out.print("Enter name: ");
                        name = scanner.nextLine();
                        System.out.print("Enter personal number: ");
                        pNumber = scanner.nextLine();


                        System.out.println("Add member to registry? Yes/No");
                        confirm = scanner.nextLine();
                        if (confirm.toLowerCase().equals("yes")) {
                            Member m = new Member(pNumber, name);
                            reg.addMember(m);
                            System.out.println(m.getName()+"(Id="+m.getId()+") "+"added to registry");
                        }
                        else if(confirm.toLowerCase().equals("no")) {
                            System.out.println("Member was not added.");
                            page = "1";
                        }
                        else {
                            System.err.println("Member was not added, you can only write yes or no. Try again.");
                        }

                    }
                    else {
                        page = "0";
                    }
                    break;
                case "2":
                    System.out.println("|============== Select member ==============|");
                    System.out.println("| Enter member ID and press ENTER to        |");
                    System.out.println("| continue or press 0 to return.            |");
                    System.out.println("| 0. Return                                 |");
                    System.out.println("|===========================================|");
                    System.out.print("Waiting for key press...");
                    if (!scanner.nextLine().equals("0")) {
                        System.out.println("Enter ID: ");
                        id = Integer.parseInt(scanner.nextLine());
                        page = "8";
                    }
                    else {
                        page = "0";
                    }
                    break;
                case "3":
                    System.out.println("|============ Show verbose list ============|");
                    System.out.println("| Press ENTER to show a verbose list of all |");
                    System.out.println("| members, press 0 to return to startpage   |");
                    System.out.println("| 0. Return                                 |");
                    System.out.println("|===========================================|");
                    System.out.print("Waiting for key press...");
                    if (!scanner.nextLine().equals("0")) {
                        reg.getVerboseList();
                        System.out.print("Press any key to return to startpage....");
                        scanner.nextLine();
                        page = "0";
                    }

                    break;
                case "4":
                    System.out.println("|============ Show compact list ============|");
                    System.out.println("| Press ENTER to show a compact list of all |");
                    System.out.println("| members, press 0 to return to startpage   |");
                    System.out.println("| 0. Return                                 |");
                    System.out.println("|===========================================|");
                    System.out.print("Waiting for key press...");
                    if (!scanner.nextLine().equals("0")) {
                        reg.getCompactList();
                        System.out.print("Press any key to return to startpage....");
                        scanner.nextLine();
                        page = "0";
                    }
                    break;
                case "5":
                    System.out.println("|============ Load new registry ============|");
                    System.out.println("| Press YES to load a new registry          |");
                    System.out.println("| Press No to return                        |");
                    System.out.println("|===========================================|");
                    System.out.println("Load new registry? Unsaved data will be lost! "+" Yes/No");
                    confirm = scanner.nextLine();
                    if (confirm.toLowerCase().equals("yes")) {
                            reg.loadRegistry();
                            System.out.println("Registry was loaded!");
                            page = "0";
                    }
                    else if(confirm.toLowerCase().equals("No")) {
                        page = "0";
                    }
                    else {
                        System.err.println("Nothing was loaded, you can only write yes or no. Try again.");
                    }
                    break;
                case "6":
                    System.out.println("|============ save new registry ============|");
                    System.out.println("| Press YES to save registry                |");
                    System.out.println("| Press NO to return                        |");
                    System.out.println("|===========================================|");
                    System.out.println("Save registry?"+" Yes/No");
                    confirm = scanner.nextLine();
                    if (confirm.toLowerCase().equals("yes")) {
                        reg.saveRegistry();
                        System.out.println("Registry was saved");
                        page = "0";
                    }
                    else if(confirm.toLowerCase().equals("no")) {
                        page = "0";
                    }
                    else {
                        System.err.println("Nothing was saved, you can only write yes or no. Try again.");
                    }
                    break;
                case "7":
                    break;
                case "8":
                    if (reg.memberExists(id)) {

                        System.out.println("|============== Member: " + id + " ===============|");
                        System.out.println("| Select a number to get to the             |");
                        System.out.println("| corresponding page.                       |");
                        System.out.println("| 9.  Edit member                           |");
                        System.out.println("| 10. Add boat                              |");
                        System.out.println("| 11. Edit boat                             |");
                        System.out.println("| 12. Delete boat                           |");
                        System.out.println("| 13. Delete member                         |");
                        System.out.println("| 0.  Return                                |");
                        System.out.println("|===========================================|");
                        input = scanner.nextLine();
                        if (!input.equals("0")) {
                            switch (input) {
                                case "9":
                                    page = "9";
                                    break;
                                case "10":
                                    page = "10";
                                    break;
                                case "11":
                                    page = "11";
                                    break;
                                case "12":
                                    page = "12";
                                    break;
                                case "13":
                                    System.out.println("Are you sure that you want to delete "+reg.getMember(id).getName()+"("+reg.getMember(id).getId()+")"+" Yes/No");
                                    confirm = scanner.nextLine();
                                    if (confirm.toLowerCase().equals("yes")) {
                                        reg.deleteMember(reg.getMember(id));
                                    }
                                    else if(confirm.toLowerCase().equals("no")) {
                                        System.out.println(reg.getMember(id).getName()+" was not deleted.");
                                        page = "8";
                                    }
                                    else {
                                        System.err.println("Member was not deleted, you can only write yes or no. Try again.");
                                    }
                                    break;
                                default:
                                    System.err.println("You can only press one of the keys listed above, try again.");
                                    page = "8";
                                    break;
                            }
                        } else {
                            page = "0";
                        }
                    }
                    else {
                        System.err.println("Member doesnt exist. Try again!");
                        page = "2";
                    }
                    break;
                case "9":
                    System.out.println("|========== Edit member:  "+ id +" =========|");
                    System.out.println("| Select a number to get to the             |");
                    System.out.println("| corresponding page.                       |");
                    System.out.println("| 1. Edit name                              |");
                    System.out.println("| 2. Edit personal number                   |");
                    System.out.println("| 0. Return                                 |");
                    System.out.println("|===========================================|");
                    input = scanner.nextLine();
                    if (!input.equals("0")) {
                        switch (input) {
                            case "1":
                                System.out.print("Enter name: ");
                                name = scanner.nextLine();
                                reg.getMember(id).setName(name);
                                page = "9";
                                break;
                            case "2":
                                System.out.print("Enter personal number: ");
                                pNumber = scanner.nextLine();
                                reg.getMember(id).setPersonalNumber(pNumber);
                                page = "9";
                                break;
                            default:
                                System.err.println("You can only press one of the keys listed above, try again.");
                                page = "9";
                                break;
                        }
                    }
                    else {
                        page = "8";
                    }
                    break;
                case "10":
                    String boatType = "";
                    String boatLength;
                    System.out.println("|================= Add Boat ================|");
                    System.out.println("| Enter boat information and press          |");
                    System.out.println("| ENTER to continue or press 0 to return.   |");
                    System.out.println("| 0. Return                                 |");
                    System.out.println("|===========================================|");
                    System.out.print("Waiting for key press...");
                    if (!scanner.nextLine().equals("0")) {
                        System.out.print("Enter type (Sailboat, Motorsailer, Canoe, Other): ");
                        boatType = scanner.nextLine();
                        //boatType.substring(0,1).toUpperCase().substring(1, boatType.length() - 1).toLowerCase();
                        System.out.print("Enter boat length(cm): ");
                        boatLength = scanner.nextLine();

                        System.out.println("Add boat to member? Yes/No");
                        confirm = scanner.nextLine();
                        if (confirm.toLowerCase().equals("yes")) {
                            Boat boat = new Boat(Boat.Type.valueOf(boatType.toLowerCase()), Integer.valueOf(boatLength));
                            reg.getMember(id).addBoat(boat);
                            System.out.println(boat.getType()+"("+reg.getMember(id).getBoats().get(boatId).getLength()+"cm) was added");
                        }
                        else if(confirm.toLowerCase().equals("no")) {
                            System.out.println("Boat was not added.");
                            page = "10";
                        }
                        else {
                            System.err.println("Boat was not added, you can only write yes or no. Try again.");
                        }
                    }
                    else {
                        page = "8";
                    }
                    break;
                case "11":
                    System.out.println("|================ Edit boat ================|");
                    System.out.println("| Enter boat ID and press ENTER to          |");
                    System.out.println("| continue or press 0 to return.            |");
                    System.out.println("| 0. Return                                 |");
                    System.out.println("|===========================================|");
                    System.out.print("Waiting for key press...");
                    if (!scanner.nextLine().equals("0")) {
                        System.out.println("Enter ID: ");
                        boatId = Integer.parseInt(scanner.nextLine());
                        String oldType = "" +reg.getMember(id).getBoats().get(boatId).getType()+"("+reg.getMember(id).getBoats().get(boatId).getLength()+"cm)";


                        System.out.println("Are you sure you want to edit "+reg.getMember(id).getBoats().get(boatId).getType()+"("+reg.getMember(id).getBoats().get(boatId).getLength()+"cm)"+"? Yes/No");
                        confirm = scanner.nextLine();
                        if (confirm.toLowerCase().equals("yes")) {
                            System.out.print("Enter type (Sailboat, Motorsailer, Canoe, Other): ");
                            boatType = scanner.nextLine();
                            System.out.print("Enter boat length(cm): ");
                            boatLength = scanner.nextLine();

                            reg.getMember(id).getBoats().get(boatId).setType(Boat.Type.valueOf(boatType.toLowerCase()));
                            reg.getMember(id).getBoats().get(boatId).setLength(Integer.valueOf(boatLength));
                            System.out.println(oldType+" was changed to "+reg.getMember(id).getBoats().get(boatId).getType()+"("+reg.getMember(id).getBoats().get(boatId).getLength()+"cm)");
                        }
                        else if(confirm.toLowerCase().equals("no")) {
                            System.out.println(oldType+" was not edited.");
                            page = "8";
                        }
                        else {
                            System.err.println(oldType+"was not deleted, you can only write yes or no. Try again.");
                        }
                    }
                    else {
                        page = "8";
                    }
                    break;
                case "12":
                    System.out.println("|=============== Delete boat ===============|");
                    System.out.println("| Enter boat ID and press ENTER to          |");
                    System.out.println("| continue or press 0 to return.            |");
                    System.out.println("| 0. Return                                 |");
                    System.out.println("|===========================================|");
                    System.out.print("Waiting for key press...");
                    if (!scanner.nextLine().equals("0")) {
                        System.out.println("Enter ID: ");
                        boatId = Integer.parseInt(scanner.nextLine());
                        System.out.println("Are you sure you want to remove: "+boatId +". " + reg.getMember(id).getBoats().get(boatId).getType() +" " + reg.getMember(id).getBoats().get(boatId).getLength() + "cm ?");


                        confirm = scanner.nextLine();
                        if (confirm.toLowerCase().equals("yes")) {
                            reg.getMember(id).getBoats().remove(boatId);
                        }
                        else if(confirm.toLowerCase().equals("no")) {
                            System.out.println("The boat was not deleted.");
                            page = "8";
                        }
                        else {
                            System.err.println("The boat was not deleted, you can only write yes or no. Try again.");
                        }
                    }
                    else{
                        page = "0";
                    }
                    break;
            }
        }
    }
    public static void clear() {
    }
}
