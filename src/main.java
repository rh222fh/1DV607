import Back.Boat;
import Back.Member;
import Back.Registry;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.IOException;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name;
        String pNumber;

        Registry reg = new Registry();
        String page = "0";
        String confirm;

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
                    System.out.println("Test2");
                    break;
                case "3":
                    System.out.println("test");
                    break;
                case "4":
                    break;
                case "5":
                    break;
                case "6":
                    break;
                case "7":
                    break;
            }
        }
    }
    public static void clear() {
        for(int clear = 0; clear < 1000; clear++)
        {
            System.out.println("\b") ;
        }
    }
}
