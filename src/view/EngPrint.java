package view;

public class EngPrint implements IView {
    public void startPageHeader() {
        System.out.println("|=============== Start page ================|");
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
    }

    public void addMemHeader() {
        System.out.println("|================ Add Member ===============|");
        System.out.println("| Enter member information and press        |");
        System.out.println("| ENTER to continue or press 0 to return.   |");
        System.out.println("| 0. Return                                 |");
        System.out.println("|===========================================|");
        System.out.print("Waiting for key press...");
    }

    public void selectMemHeader() {
        System.out.println("|============== Select member ==============|");
        System.out.println("| Enter member ID and press ENTER to        |");
        System.out.println("| continue or press 0 to return.            |");
        System.out.println("| 0. Return                                 |");
        System.out.println("|===========================================|");
        System.out.print("Waiting for key press...");
    }

    public void verboseListHeader() {
        System.out.println("|============ Show verbose list ============|");
        System.out.println("| Press ENTER to show a verbose list of all |");
        System.out.println("| members, press 0 to return to startpage   |");
        System.out.println("| 0. Return                                 |");
        System.out.println("|===========================================|");
        System.out.print("Waiting for key press...");
    }

    public void compactListHeader() {
        System.out.println("|============ Show compact list ============|");
        System.out.println("| Press ENTER to show a compact list of all |");
        System.out.println("| members, press 0 to return to startpage   |");
        System.out.println("| 0. Return                                 |");
        System.out.println("|===========================================|");
        System.out.print("Waiting for key press...");
    }

    public void loadRegHeader() {
        System.out.println("|============ Load new registry ============|");
        System.out.println("| Press YES to load a new registry          |");
        System.out.println("| Press NO to return                        |");
        System.out.println("|===========================================|");
        System.out.println("Load new registry? Unsaved data will be lost! " + " Yes/No");
    }

    public void saveRegHeader() {
        System.out.println("|============ Save new registry ============|");
        System.out.println("| Press YES to save registry                |");
        System.out.println("| Press NO to return                        |");
        System.out.println("|===========================================|");
        System.out.println("Save registry?" + " Yes/No");
    }

    public void filePathHeader(){
        System.out.println("Write your filepath and filename");
        System.out.println("on mac: /Users/test/Desktop/registry.txt");
        System.out.println("on windows: \\Users\\test\\Desktop\\registry.txt");
        System.out.print(": ");
    }

    public void memberMenuHeader(int id) {
        System.out.println("|============== Member: " + id + " ===============|");
        System.out.println("| Select a number to get to the             |");
        System.out.println("| corresponding page.                       |");
        System.out.println("| 9.  Edit member                           |");
        System.out.println("| 10. Add boat                              |");
        System.out.println("| 11. Edit boat                             |");
        System.out.println("| 12. Delete boat                           |");
        System.out.println("| 13. Delete member                         |");
        System.out.println("| 14. Display member info                   |");
        System.out.println("| 0.  Return                                |");
        System.out.println("|===========================================|");
    }

    public void editMemberHeader(int id) {
        System.out.println("|========== Edit member:  " + id + " =========|");
        System.out.println("| Select a number to get to the             |");
        System.out.println("| corresponding page.                       |");
        System.out.println("| 1. Edit name                              |");
        System.out.println("| 2. Edit personal number                   |");
        System.out.println("| 0. Return                                 |");
        System.out.println("|===========================================|");
    }

    public void addBoatHeader() {
        System.out.println("|================= Add Boat ================|");
        System.out.println("| Enter boat information and press          |");
        System.out.println("| ENTER to continue or press 0 to return.   |");
        System.out.println("| 0. Return                                 |");
        System.out.println("|===========================================|");
        System.out.print("Waiting for key press...");
    }

    public void editBoatHeader() {
        System.out.println("|================ Edit boat ================|");
        System.out.println("| Enter boat ID and press ENTER to          |");
        System.out.println("| continue or press 0 to return.            |");
        System.out.println("| 0. Return                                 |");
        System.out.println("|===========================================|");
        System.out.print("Waiting for key press...");
    }

    public void deleteBoatHeader() {
        System.out.println("|=============== Delete boat ===============|");
        System.out.println("| Enter boat ID and press ENTER to          |");
        System.out.println("| continue or press 0 to return.            |");
        System.out.println("| 0. Return                                 |");
        System.out.println("|===========================================|");
        System.out.print("Waiting for key press...");
    }

    public void memberInfoHeader(int id) {
        System.out.println("|========== Member " + id + " info =========|");
        System.out.println("| Press ENTER to show member information,   |");
        System.out.println("| press 0 to return to startpage.           |");
        System.out.println("| 0. Return                                 |");
        System.out.println("|===========================================|");
        System.out.print("Waiting for key press...");
    }

    public void addConfirmation(String in){
        System.out.println(in +"added to registry");
    }

    public void deleteConfirmation(String in){
        System.out.println("Are you sure that you want to delete " +in + " Yes/No");
    }

    public void addBoatConfirmation(String in){
        System.out.println(in+" cm) was added");
    }

    public void editBoatConfirmation(String in){
        System.out.println("Are you sure you want to edit " +in+ "cm)" + "? Yes/No");
    }

    public void editBoatMessage(String old, String changed){
        System.out.println(old + " was changed to " + changed+ "cm)");
    }

    public void deleteBoatConfiramtion(String in){
        System.out.println("Are you sure you want to remove: "+in+ "cm ? Yes/No");
    }

    public void errorMessage(int messageID) {
        switch (messageID) {
            case 1:
                System.err.println("You can only press one of the keys listed above, try again.");
                break;
            case 2:
                System.err.println("The name can't be empty or contain digits, please try again.");
                break;
            case 3:
                System.err.println("The personal number can only contain digits and \"-\", please try again.");
                break;
            case 4:
                System.err.println("You can only write yes or no. Try again.");
                break;
            case 5:
                System.err.println("A member with that ID doesn't exist, please try again.");
                break;
            case 6:
                System.err.println("A boat with that ID doesn't exist, please try again.");
                break;
            case 7:
                System.err.println("The ID must be a number, please try again.");
                break;
            case 8:
                System.err.println("The boat length must be a number, please try again.");
                break;
            case 9:
                System.err.println("Boat was not added, it can only be one of the types(Sailboat, Motorsailer, Canoe, Other). Try again.");
                break;
        }
    }

    public void outputMessage(int messageID) {
        switch (messageID) {
            case 1:
                System.out.print("Enter name: ");
                break;
            case 2:
                System.out.print("Enter personal number: ");
                break;
            case 3:
                System.out.println("Add member to registry? Yes/No");
                break;
            case 4:
                System.out.println("Member was not added.");
                break;
            case 5:
                System.out.print("Enter ID: ");
                break;
            case 6:
                System.out.print("Press any key to return to startpage....");
                break;
            case 7:
                System.out.println("Registry was loaded!");
                break;
            case 8:
                System.out.println("Registry was saved");
                break;
            case 9:
                System.out.println("Are you sure that you want to exit? Yes/No");
                break;
            case 10:
                System.out.println("Stopping program....");
                break;
            case 11:
                System.out.println("Member was deleted.");
                break;
            case 12:
                System.out.println("Member was not deleted.");
                break;
            case 13:
                System.out.print("Enter type (Sailboat, Motorsailer, Canoe, Other): ");
                break;
            case 14:
                System.out.print("Enter boat length(cm): ");
                break;
            case 15:
                System.out.println("Add boat to member? Yes/No");
                break;
            case 16:
                System.out.println("Boat was not added.");
                break;
            case 17:
                System.out.println("Boat was not edited.");
                break;
            case 18:
                System.out.println("Boat was not deleted.");
                break;
            case 19:
                System.out.println("Press any key to return...");
                break;

        }
    }
}
