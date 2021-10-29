package intro;
import java.util.Arrays;
import java.util.Scanner;



/**
 * Provides necessary methods to deal through the Warehouse management actions
 *
 * @author riteshp
 */
class TheWarehouseManager implements Repository {
    // =====================================================================================
    // Member Variables
    // =====================================================================================

    // To read inputs from the console/CLI
    private final Scanner reader = new Scanner(System.in);

    private final String[] userOptions = {
            "1. List items by warehouse", "2. Search an item and place an order", "3. Quit"
    };
    // To refer the user provided name.
    private String userName;

    // =====================================================================================
    // Public Member Methods
    // =====================================================================================

    /**
     * Welcome User
     */
    public void welcomeUser() {
        this.seekUserName();
        this.greetUser();
    }

    /**
     * Ask for user's choice of action
     */
    public int getUsersChoice() {
        // TODO
        int userChoice;
        System.out.println("Please select one option, by typing 1,2 or 3: ");
        System.out.println(Arrays.toString(userOptions));
        while (true) {
            userChoice = reader.nextInt();
            if (userChoice > 3 || userChoice < 1) {
                System.out.println("Please enter a valid number. ");
            } else {
                break;//if the user types the correct option 1, 2 or 3, then it will break and show the result. Otherwise the while loop ask the user to give the correct option
            }
        }
        return userChoice;

    }

    /**
     * Initiate an action based on given option
     */
    public void performAction(int option) {
        // TODO

        if (option == 1) {
            listItemsByWarehouse();
        } else if (option == 2) {
            System.out.println(" Please write the name of item you are looking for: ");
            String userItem = reader.next();
            userItem += reader.nextLine();
            getAvailableAmount(userItem);
        }else if (option == 3) {
            quit();
        }

    }

    /**
     * Confirm an action
     *
     * @return action
     */
    /*public boolean confirm(String message) {
        // TODO
    }*/

    /**
     * End the application
     */
    public void quit() {
        System.out.printf("\nThank you for your visit, %s!\n", this.userName);
        System.exit(0);
    }

    // =====================================================================================
    // Private Methods
    // =====================================================================================

    /**
     * Get user's name via CLI
     */
    private void seekUserName() {
        // TODO

        System.out.println("Could you please enter your name: ");
        this.userName = reader.nextLine();

    }

    /**
     * Print a welcome message with the given user's name
     */
    private void greetUser() {
        // TODO
        System.out.println("Hello dear " + this.userName + " Welcome to our Warehouse! ");
    }

    private void listItemsByWarehouse() {
        // TODO
        System.out.println("Items in our Warehouse1 are: ");
        for (String item : WAREHOUSE1) {
            System.out.println(item);
        }
        System.out.println("Items in our Warehouse2 are: ");
        for (String item : WAREHOUSE2) {
            System.out.println(item);
        }
    }

    private void listItems(String[] warehouse) {
        // TODO
        System.out.println("Items in this Warehouse are: ");
        for (String item : warehouse) {
            System.out.println(item);
        }
    }

    /*private void searchItemAndPlaceOrder() {
        // TODO
    }*/

    /**
     * Ask the user to specify an Item to Order
     *
     * @return String itemName
     */
    /*private String askItemToOrder() {
        // TODO
    }*/


    /**
     * Calculate total availability of the given item
     *
     * @param itemName itemName
     * @return integer availableCount
     */
    private int getAvailableAmount(String itemName) {
        // TODO

        int count1 = 0;
        int count2 = 0;
        for (String item : WAREHOUSE1) {
            if (itemName.equals(item)) {
                count1++;
            }
        }
        for (String item : WAREHOUSE2) {
            if (itemName.equals(item)) {
                count2++;
            }
        }
        int totalCount = count1 + count2;
        System.out.println("The total number of " + itemName + " in our warehouses: " + totalCount);
        if(totalCount==0){
            System.out.println(itemName+ " is not in stock");
        }else if (count1>0 && count2>0){
            System.out.println("Both Warehouses");
            if(count1>count2){
                System.out.println("Warehouse 1 has the highest amount of "+ itemName+" equal to: "+count1);
            }else if(count1<count2){
                System.out.println("Warehouse2 has the highest amount of "+ itemName+" equal to: "+count2);
            }
        }else{
            System.out.println("There are the same amounts in both Warehouses: "+count1);
        }
        if(count1+count2>0){
            System.out.println("Do you want to order the item? please type yes or No");
            String answer=reader.nextLine();
            if(answer.toLowerCase().equals("yes")){
                askAmountAndConfirmOrder(totalCount, itemName);
            }else {
                System.out.println(" Thank you for your visit");
            }

        }
        return count1 + count2;
    }




    /**
     * Find the count of an item in a given warehouse
     *
     * @param item the item
     * @param warehouse the warehouse
     * @return count
     */
    private int find(String item, String[] warehouse) {
        // TODO
        int count = 0;
        for (String eachitem : warehouse) {
            if (item.equals(eachitem)) {
                count++;
            }
        }
        return count;
    }

    /** Ask order amount and confirm order */
    private void askAmountAndConfirmOrder(int availableAmount, String item) {
        // TODO
        System.out.println("How many of "+ item+ " do you want to order?");
        int numberByUser= reader.nextInt();
        if(numberByUser<= availableAmount){
            System.out.println("the order has been placed: "+numberByUser+" x "+item);
        }else {
            System.out.println("The total available amount is: "+availableAmount+ " \n " +
                    "Would you like to order the highest amount avalable in stock?");
            String answer=reader.next();
            if(answer.toLowerCase().equals("yes")){
                System.out.println("the order has been placed: "+availableAmount+" x "+item);
            }else {
                System.out.println(" Thank you for your visit");
            }
        }
    }

    /**
     * Get amount of order
     *
     * @param availableAmount
     * @return
     */
    /*private int getOrderAmount(int availableAmount) {
        // TODO
    }*/
}