package intro;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * The Data Repository
 *
 * @author riteshp
 */
public class Repository {

  private static final List<Item> ITEM_LIST = new ArrayList<>();

  /*
   * Load item records from the data.json file
   */
  static {
    // System.out.println("Loading items");
    try (BufferedReader reader =
                 new BufferedReader(
                         new InputStreamReader(
                                 Objects.requireNonNull(
                                         Repository.class.getClassLoader().getResourceAsStream("data.json"))))) {

      Object data = JSONValue.parse(reader);
      if (data instanceof JSONArray) {
        JSONArray dataArray = (JSONArray) data;
        for (Object obj : dataArray) {
          if (obj instanceof JSONObject) {
            JSONObject jsonData = (JSONObject) obj;
            Item item = new Item();
            item.setState(jsonData.get("state").toString());
            item.setCategory(jsonData.get("category").toString());
            item.setWarehouse(Integer.parseInt(jsonData.get("warehouse").toString()));
            String date = jsonData.get("date_of_stock").toString();
            // System.out.println("Item Date " + date);
            item.setDateOfStock(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(date));
            // System.out.println(item);

            ITEM_LIST.add(item);
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Get All items available in the repository
   *
   * @return the list of items
   */
  public static List<Item> getAllItems() {
    // TODO
    return ITEM_LIST;
  }

  // By Warehouse

  /**
   * Get the list of unique warehouse IDs
   *
   * @return the warehouses
   */
  public static Set<Integer> getWarehouses() {
    // TODO
    Set<Integer> setOfIDs = new HashSet<Integer>();
    for (Item item : getAllItems()) {
      //System.out.println(item.getWarehouse());
      setOfIDs.add(item.getWarehouse());//accessing to warehouse number of object "item" through getter

    }
    return setOfIDs;
  }

  /**
   * Get the list of all items in a specific warehouse
   *
   * @param warehouse the warehouse ID
   * @return the items
   */
  public static List<Item> getItemsByWarehouse(int warehouse) {
    // TODO

    return getItemsByWarehouse(warehouse, getAllItems());
  }

  /**
   * Get the list of items related to a specific warehouse in a given master-list
   *
   * @param warehouse the warehouse ID
   * @return the items
   */
  public static List<Item> getItemsByWarehouse(int warehouse, List<Item> masterList) {
    // TODO
    List<Item> listItem = new ArrayList<Item>();
    for (Item item : masterList) {
      if (item.getWarehouse() == warehouse) {
        listItem.add(item);
      }

    }return listItem;
  }

    // By Category
    /**
     * Get the list of unique Categories
     *
     * @return the categories
     */
    public static Set<String> getCategories(){
      // TODO

      return null;
    }

    /**
     * Get the list of all items of a specific category
     *
     * @param category the category
     * @return the items
     */
    public static List<Item> getItemsByCategory (String category){
      // TODO

      return null;
    }

    /**
     * Get the list of items of a specific category in a given master-list
     *
     * @param category the category
     * @return the items
     */
    public static List<Item> getItemsByCategory (String category, List < Item > masterList){
      // TODO


      return null;
    }
  }

