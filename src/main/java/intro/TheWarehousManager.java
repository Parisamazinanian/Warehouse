package intro;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.TimeUnit;


/**
 * Provides necessary methods to deal through the Warehouse management actions
 *
 * @author riteshp
 */
class TheWarehouseManager implements RepositoryOld {
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

/** Welcome User */
public void welcomeUser() {
        this.seekUserName();
        this.greetUser();
        }

/** Ask for user's choice of action */
//3- showing the user choice of action
public int getUsersChoice() {
        // TODO
        //Ask the user to insert a number to show choose a performance
        int userChoice;
        while (true) {
        System.out.println("Please choose 1, 2 or 3 for: \n"+Arrays.toString(userOptions));
        userChoice = reader.nextInt();
        if (userChoice > 3 || userChoice < 1) {
        System.out.println("Please enter a valid number. ");
        } else {
        break;//if the user types the correct option 1, 2 or 3, then it will break and show the result. Otherwise the while loop ask the user to give the correct option
        }
        }
        return userChoice;
        }

/** Initiate an action based on given option */
//user choice will return following options
public void performAction(int option) {
        // TODO
        if(option==1){
        listItemsByWarehouse();
        }else if(option==2){
        askItemToOrder();
        }else {
        quit();
        }
        }

/**
 * Confirm an action
 *
 * @return action
 */
public boolean confirm(String message) {
        // TODO
        boolean check;
        System.out.println(message);
        String userAnswer= reader.next();
        userAnswer+= reader.nextLine();
        if(userAnswer.equals("yes".toLowerCase())){
        check=true;
        }else {
        check=false;
        }
        return check;
        }

/** End the application */
public void quit() {
        System.out.printf("\nThank you for your visit, %s!\n", this.userName);
        System.exit(0);
        }

// =====================================================================================
// Private Methods
// =====================================================================================

/** Get user's name via CLI */
//1- asking user to provide his/her name:
private void seekUserName() {
        // TODO
        System.out.println("Please enter your name: ");
        userName=reader.nextLine();
        }

/** Print a welcome message with the given user's name */
//2- Greet the user
private void greetUser() {
        // TODO
        System.out.println("Hello "+userName+" ! Welcome to our Warehouse.");
        }
//4.i in the ListItems I wrote a loop to get every element of a warehouse. now is time to call the listItems method to
//get the elements of each warehouse
private void listItemsByWarehouse() {
        // TODO
        Set<Integer> warehouseIDs = Repository.getWarehouses();
        for (int id:warehouseIDs) {
                System.out.println("List of items in warehouse "+id+" :");
                System.out.println(Repository.getItemsByWarehouse(id));
                System.out.println("Total items in warehouse "+id+" : "+Repository.getItemsByWarehouse(id).size());
        }

        /*System.out.println("Items in Warehouse1: ");
        listItems(WAREHOUSE1);
        System.out.println("Items in Warehouse2: ");
        listItems(WAREHOUSE2);*/

        }
//4.i>> this part is for getting the items of a warehouse
private void listItems(String[] warehouse) {
        // TODO
        //For each element in the warehouse array print the element. Please consider the element type is important
        for (String element:warehouse) {
        System.out.println(element);
        }
        }
//4.ii.c.a&b ask the user if he/she wants to place an order
private void searchItemAndPlaceOrder() {
        // TODO
        System.out.println("Do you want to order the searched item?\n"+" Please type yes or no!");
        /*String UserOrderchoice= reader.next();
        UserOrderchoice+= reader.nextLine();

        if (UserOrderchoice.equals("yes")){
            askAmountAndConfirmOrder(getAvailableAmount(askItemToOrder()), askItemToOrder());
        }else if(UserOrderchoice.equals("no")){
            quit();
        }*/
        }

/**
 * Ask the user to specify an Item to Order
 *
 * @return String itemName
 */
//4.ii.a ask the user to search for an item
private String askItemToOrder() {
        // TODO
        System.out.println("Please type the name of an item you are looking for: ");
        String searchedItem=reader.next().toLowerCase();
        searchedItem+= reader.nextLine().toLowerCase();
        getAvailableAmount(searchedItem);
        return searchedItem;
        }

/**
 * Calculate total availability of the given item
 *
 * @param itemName itemName
 * @return integer availableCount
 */
//4.ii.b.b&c the total amount of searched item and the amount of the item in each warehouse
private int getAvailableAmount(String itemName) {
        // TODO
        int count1 = find(itemName, Repository.getItemsByWarehouse(1));
        int count2 = find(itemName,Repository.getItemsByWarehouse(2));
        int totalCount = count1+count2;
        System.out.println("The total number of " + itemName + " in our warehouses: " + totalCount);
        if(totalCount==0){
        System.out.println(itemName+ " is not in stock");
        }else {
        if (count1>0 && count2>0){
        System.out.println("We have the good in Both Warehouses");
        if(count1>count2){
        System.out.println("Warehouse 1 has the highest amount of "+ itemName+" equal to: "+count1);
        }else if(count1<count2){
        System.out.println("Warehouse 2 has the highest amount of "+ itemName+" equal to: "+count2);
        }else {
        System.out.println("There are the same amounts of the item in both Warehouses: " + count1 + " in each");
        }
        }else{
        if(count1>0){
        System.out.println("Warehouse 1 has the highest amount of "+ itemName+" equal to: "+count1);
        } else{
        System.out.println("Warehouse 2 has the highest amount of "+ itemName+" equal to: "+count2);
        }
        }
        searchItemAndPlaceOrder();
        String UserOrderchoice= reader.next();
        UserOrderchoice+= reader.nextLine();

        if (UserOrderchoice.equals("yes")){
        askAmountAndConfirmOrder(totalCount, itemName);
        }else if(UserOrderchoice.equals("no")){
        quit();
        }


        }
        return totalCount;
        }


/**
 * Find the count of an item in a given warehouse
 *
 * @param item the item
 * @param warehouse the warehouse
 * @return count
 */

//4.ii.b.a the amount of searched item in each warehouse
private int find(String item, List<Item> warehouse) {
        // TODO
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        int count=0;
        for(Item element:warehouse){
                if(item.equals(String.format("%s %s", element.getState().toLowerCase(), element.getCategory().toLowerCase()))) {
                        LocalDate itemStockDate = element.getDateOfStock().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        System.out.println("- Warehouse "+element.getWarehouse()+ " (in stock for "+ChronoUnit.DAYS.between(itemStockDate, now)+" days)");
                        count++;
                }
        }
        return count;
        }

/** Ask order amount and confirm order */
private void askAmountAndConfirmOrder(int availableAmount, String item) {
        // TODO
        int amoutForOrder = getOrderAmount(availableAmount);
        if (amoutForOrder<= availableAmount){
        System.out.println("the order has been placed: "+amoutForOrder+" x "+item);
        }else {
        System.out.println("The total available amount is: "+availableAmount+ " \n " +
        "Would you like to order the highest amount avalable in stock?");
        String answer=reader.next();
        answer += reader.nextLine();
        if(answer.equals("yes")){
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
//4.ii.c.b
private int getOrderAmount(int availableAmount) {
        // TODO
        System.out.println("How many do you want to order?");
        int userAmount=reader.nextInt();
        return userAmount;
        }
}
