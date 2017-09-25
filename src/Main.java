import Back.Boat;
import Back.Member;
import Back.Registry;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name;
        String pNumber;
        int id = 0;

        Registry reg = new Registry();
        String page = "0";
        String confirm;
        String input;

        while (!page.equals("7")) {
            switch (page) {
                case "0":
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
                    page = scanner.nextLine();
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
                    break;
                case "6":
                    break;
                case "7":
                    break;
                case "8":
                    System.out.println("|============== Member:  "+ id +" ==============|");
                    System.out.println("| Select a number to get to the             |");
                    System.out.println("| corresponding page.                       |");
                    System.out.println("| 9. Edit member                            |");
                    System.out.println("| 10. Add boat                              |");
                    System.out.println("| 11. Edit boat                             |");
                    System.out.println("| 12. Delete boat                           |");
                    System.out.println("| 13. Delete member                         |");
                    System.out.println("| 0. Return                                 |");
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
                                page = "10";
                                break;
                            case "12":
                                page = "10";
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
                    }
                    else {
                        page = "0";
                    }

                    break;
                case "9":
                    System.out.println("|========== Edit member:  "+ id +" =========|");
                    System.out.println("| Select a number to get to the             |");
                    System.out.println("| corresponding page.                       |");
                    System.out.println("| 1. Edit name                             |");
                    System.out.println("| 2. Edit personal number                  |");
                    System.out.println("| 0. Return                                 |");
                    System.out.println("|===========================================|");

                    input = scanner.nextLine();
                    if (!page.equals("0")) {
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
                        page = "0";
                    }
                    break;
                case "10":
                    System.out.println("Page 10");
                    break;
                case "11":
                    System.out.println("Page 11");
                    break;
                case "12":
                    System.out.println("Page 12");
                    break;
                case "13":
                    System.out.println("Page 13");
                    break;
                case "14":
                    System.out.println("Page 13");
                    break;
                case "15":
                    System.out.println("Page 13");
                    break;
            }
        }
    }
    public static void clear() {
    }
}