package view;

import model.Boat;
import model.Member;
import model.Registry;

import java.io.IOException;
import java.util.Scanner;

public class Console {
    private String name;
    private String pNumber;
    private int id = 0;
    private String input;
    private int boatId = 0;
    private Registry reg = new Registry();
    private String page = "0";
    private String confirm;
    private String boatType = "";
    private String boatLength;

    public void start() throws IOException{
        Scanner scanner = new Scanner(System.in);
        System.out.println("|================ Startpage ================|");
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
            pageSwitcher(confirm);
        }
        else {
            System.err.println("You can only press one of the keys listed above, try again.");
            start();
        }
    }

    public void pageSwitcher(String s) throws IOException{

        if (s.equals("1")) {
            pageOne();
        }
        if (s.equals("2")) {
            pageTwo();
        }
        if (s.equals("3")) {
            pageThree();
        }
        if (s.equals("4")) {
            pageFour();
        }
        if (s.equals("5")) {
            pageFive();
        }
        if (s.equals("6")) {
            pageSix();
        }
        if (s.equals("7")) {
            pageSeven();
        }

        if (s.equals("8")) {
            pageEight();
        }

        if (s.equals("9")) {
            pageNine();
        }

        if (s.equals("10")) {
            pageTen();
        }

        if (s.equals("11")) {
            pageEleven();
        }

        if (s.equals("12")) {
            pageTwelve();
        }
    }

    public void pageOne() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("|================ Add Member ===============|");
        System.out.println("| Enter member information and press        |");
        System.out.println("| ENTER to continue or press 0 to return.   |");
        System.out.println("| 0. Return                                 |");
        System.out.println("|===========================================|");
        System.out.print("Waiting for key press...");
        if (!scanner.nextLine().equals("0")) {
            System.out.print("Enter name: ");
            name = scanner.nextLine();
            if (name.isEmpty() || name.matches("\\d+")) {
                System.err.println("The name can't be empty or contain digits, please try again.");
                pageSwitcher("1");
            }
            System.out.print("Enter personal number: ");
            pNumber = scanner.nextLine();
            String clean = pNumber.replaceAll("-", "");
            if(!clean.matches("\\d+")){
                System.err.println("The personal number can only contain digits and \"-\", please try again.");
                pageSwitcher("1");
            }

            else {
                System.out.println("Add member to registry? Yes/No");
                confirm = scanner.nextLine();
                if (confirm.toLowerCase().equals("yes")) {
                    Member m = new Member(pNumber, name);
                    reg.addMember(m);
                    System.out.println(m.getName() + "(Id=" + m.getId() + ") " + "added to registry");
                    start();
                } else if (confirm.toLowerCase().equals("no")) {
                    System.out.println("Member was not added.");
                    pageSwitcher("1");
                } else {
                    System.err.println("Member was not added, you can only write yes or no. Try again.");
                    pageSwitcher("1");
                }
            }
        }
        else {
            start();
        }
    }

    public void pageTwo() throws IOException{
        Scanner scanner = new Scanner(System.in);
        System.out.println("|============== Select member ==============|");
        System.out.println("| Enter member ID and press ENTER to        |");
        System.out.println("| continue or press 0 to return.            |");
        System.out.println("| 0. Return                                 |");
        System.out.println("|===========================================|");
        System.out.print("Waiting for key press...");
        if (!scanner.nextLine().equals("0")) {
            System.out.print("Enter ID: ");
            String temp = scanner.nextLine();
            if (temp.matches("\\d+")) {
                id = Integer.valueOf(temp);
                if (reg.memberExists(id)) {
                    pageSwitcher("8");
                } else {
                    System.err.println("A member with id: " + id + " doesn't exist, please try again.");
                    pageSwitcher("2");
                }
            }
            else {
                System.err.println("The id must be an integer, please try again. ");
                pageSwitcher("2");
            }

        }
        else {
            start();
        }

    }

    public void pageThree() throws IOException{
        Scanner scanner = new Scanner(System.in);
        System.out.println("|============ Show verbose list ============|");
        System.out.println("| Press ENTER to show a verbose list of all |");
        System.out.println("| members, press 0 to return to startpage   |");
        System.out.println("| 0. Return                                 |");
        System.out.println("|===========================================|");
        System.out.print("Waiting for key press...");
        if (!scanner.nextLine().equals("0")) {
            reg.printVerboseList();
            System.out.print("Press any key to return to startpage....");
            scanner.nextLine();
            start();
        }

    }

    public void pageFour() throws IOException{
        Scanner scanner = new Scanner(System.in);
        System.out.println("|============ Show compact list ============|");
        System.out.println("| Press ENTER to show a compact list of all |");
        System.out.println("| members, press 0 to return to startpage   |");
        System.out.println("| 0. Return                                 |");
        System.out.println("|===========================================|");
        System.out.print("Waiting for key press...");
        if (!scanner.nextLine().equals("0")) {
            reg.printCompactList();
            System.out.print("Press any key to return to startpage....");
            scanner.nextLine();
            start();
        }
    }

    public void pageFive() throws IOException{
        Scanner scanner = new Scanner(System.in);

        System.out.println("|============ Load new registry ============|");
        System.out.println("| Press YES to load a new registry          |");
        System.out.println("| Press NO to return                        |");
        System.out.println("|===========================================|");
        System.out.println("Load new registry? Unsaved data will be lost! "+" Yes/No");
        confirm = scanner.nextLine();
        if (confirm.toLowerCase().equals("yes")) {
            reg.loadRegistry();
            System.out.println("Registry was loaded!");
            start();
        }
        else if(confirm.toLowerCase().equals("no")) {
            start();
        }
        else {
            System.err.println("Nothing was loaded, you can only write yes or no. Try again.");
            pageSwitcher("5");
        }

    }

    public void pageSix() throws IOException{
        Scanner scanner = new Scanner(System.in);
        System.out.println("|============ Save new registry ============|");
        System.out.println("| Press YES to save registry                |");
        System.out.println("| Press NO to return                        |");
        System.out.println("|===========================================|");
        System.out.println("Save registry?"+" Yes/No");
        confirm = scanner.nextLine();
        if (confirm.toLowerCase().equals("yes")) {
            reg.saveRegistry();
            System.out.println("Registry was saved");
            start();
        }
        else if(confirm.toLowerCase().equals("no")) {
            start();
        }
        else {
            System.err.println("Nothing was saved, you can only write yes or no. Try again.");
            pageSwitcher("6");
        }

    }

    public void pageSeven() throws IOException{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Are you sure that you want to delete exit? Yes/No");
        confirm = scanner.nextLine();
        if (confirm.toLowerCase().equals("yes")) {
            System.exit(0);
        } else if (confirm.toLowerCase().equals("no")) {
            start();
        } else {
            System.err.println("You can only write yes or no. Try again.");
            pageSwitcher("7");
        }
    }

    public void pageEight() throws IOException{
        Scanner scanner = new Scanner(System.in);
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
            while (!input.equals("0")) {
                   if(input.equals("13")) {
                       System.out.println("Are you sure that you want to delete " + reg.getMember(id).getName() + "(" + reg.getMember(id).getId() + ")" + " Yes/No");
                       confirm = scanner.nextLine();
                       if (confirm.toLowerCase().equals("yes")) {
                           reg.deleteMember(reg.getMember(id));
                           System.out.println("Member " + "id " + "was deleted.");
                           start();
                       } else if (confirm.toLowerCase().equals("no")) {
                           System.out.println(reg.getMember(id).getName() + " was not deleted.");
                           pageSwitcher("8");
                       } else {
                           System.err.println("Member was not deleted, you can only write yes or no. Try again.");
                           input = "13";
                       }
                   }else if(input.matches("\\d+")&&(Integer.valueOf(input)<=13 && Integer.valueOf(input)>=9)){
                       pageSwitcher(input);
                   }else{
                       System.err.println("You can only press on of the pages listed above. Try again.");
                       pageEight();
                }
            }
            start();
        }
        else {
            System.err.println("Member doesn't exist. Try again!");
            pageSwitcher("2");
        }
    }

    public void pageNine() throws IOException{
        Scanner scanner = new Scanner(System.in);
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
                    pageSwitcher("9");
                    break;
                case "2":
                    System.out.print("Enter personal number: ");
                    pNumber = scanner.nextLine();
                    reg.getMember(id).setPersonalNumber(pNumber);
                    pageSwitcher("9");
                    break;
                default:
                    System.err.println("You can only press one of the keys listed above, try again.");
                    pageSwitcher("9");
                    break;
            }
        }
        else {
            pageSwitcher("8");
        }
    }

    public void pageTen() throws IOException{
        Scanner scanner = new Scanner(System.in);

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
                pageSwitcher("10");
            }
            else if(confirm.toLowerCase().equals("no")) {
                System.out.println("Boat was not added.");
                pageSwitcher("10");
            }
            else {
                System.err.println("Boat was not added, you can only write yes or no. Try again.");
                pageSwitcher("10");
            }
        }
        else {
            pageSwitcher("8");
        }
    }

    public void pageEleven() throws IOException{
        Scanner scanner = new Scanner(System.in);
        System.out.println("|================ Edit boat ================|");
        System.out.println("| Enter boat ID and press ENTER to          |");
        System.out.println("| continue or press 0 to return.            |");
        System.out.println("| 0. Return                                 |");
        System.out.println("|===========================================|");
        System.out.print("Waiting for key press...");
        if (!scanner.nextLine().equals("0")) {
            System.out.print("Enter ID: ");
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
                pageSwitcher("11");
            }
            else if(confirm.toLowerCase().equals("no")) {
                System.out.println(oldType+" was not edited.");
                pageSwitcher("8");
            }
            else {
                System.err.println(oldType+"was not deleted, you can only write yes or no. Try again.");
            }
        }
        else {
            pageSwitcher("8");
        }
    }

    public void pageTwelve() throws IOException{
        Scanner scanner = new Scanner(System.in);
        System.out.println("|=============== Delete boat ===============|");
        System.out.println("| Enter boat ID and press ENTER to          |");
        System.out.println("| continue or press 0 to return.            |");
        System.out.println("| 0. Return                                 |");
        System.out.println("|===========================================|");
        System.out.print("Waiting for key press...");
        if (!scanner.nextLine().equals("0")) {
            System.out.print("Enter ID: ");
            String temp = scanner.nextLine();
            if (temp.matches("\\d+")) {
                boatId = Integer.valueOf(temp);
                if (reg.getMember(id).boatExists(boatId)) {
                    System.out.println("Are you sure you want to remove: "+boatId +". " + reg.getMember(id).getBoats().get(boatId).getType() +" " + reg.getMember(id).getBoats().get(boatId).getLength() + "cm ?");
                    confirm = scanner.nextLine();
                    if (confirm.toLowerCase().equals("yes")) {
                        reg.getMember(id).getBoats().remove(boatId);
                        pageSwitcher("8");
                    }
                    else if(confirm.toLowerCase().equals("no")) {
                        System.out.println("The boat was not deleted.");
                        pageSwitcher("8");
                    }
                    else {
                        System.err.println("The boat was not deleted, you can only write yes or no. Try again.");
                        pageSwitcher("12");
                    }
                }
                else {
                    System.err.println("The boat that you are trying to delete doesn't exist, please try again.");
                    pageSwitcher("12");
                }

            }
            else {
                System.err.println("You must type in a number, please try again.");
                pageSwitcher("12");
            }
        }
        else{
            pageEight();
        }
    }
}
