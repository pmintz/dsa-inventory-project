/** Holds the record in memory for a single item */

 
import java.io.*;
import java.util.*;
import java.math.*;

public class ItemRecord {

  // data members - all private

	/** SKU number - also acts as key value */
	private int SKU;

	/** numInStore - # items currently in store */
	private int numInStore;

	/** numAtStart - # items in store at period start */
	private int numAtStart;

	/** numAtWarehouse - # items available to order */
	private int numAtWarehouse;

	/** numInTransit - ordered but not received */
	private int numInTransit;

	/** numSold - keeps track of number sold since last receipt */
	private int numSold;

	/** salesVelocity - units sold per day in current month */
	private double salesVelocity;

	/** unitPrice - price of a single item */
	private double unitPrice;

	/** name - label name of item for sale */
	private String name;

	/** descrip - label description of item for sale */
	private String descrip;

  // class methods

  // initialize only from file
  
  /**Added Buffered Reader and changed File(file) to FileReader(file) and added catch and try*/	
	public ItemRecord(String file) {
	    try {
		Scanner sc = new Scanner(new BufferedReader(new FileReader(file)));
		SKU = sc.nextInt();
		name = sc.next();
		StringBuilder sb = new StringBuilder();
		while (sc.hasNext()) {
			sb.append(sc.next());
		}
		descrip = sb.toString();
		numInStore = sc.nextInt();
		numAtWarehouse = sc.nextInt();
		numInTransit = sc.nextInt();
		numSold = sc.nextInt();
        }
		
		catch (Exception e){
    System.out.println(e);
   }
	}

	/** sellItem - decrements numInStore
	 *  calls updateVeloc() and orderItem()
	 */
	public void sellItem(int num) {
		numInStore -= num;
		numSold += num;
		updateVeloc();
		orderItem();
	}

	/** updateVeloc - calculate sales velocity */
	protected void updateVeloc() {
		salesVelocity = (1/30) * (numSold)/numAtStart;
	}

	/** orderItem - order more if velocity and stock level adequate */
	protected void orderItem() {
	    float salesvel;
	    salesvel = (float) salesVelocity;
		int needed = Math.round(salesvel * 30); // enough for 30 days
		if (numInStore > needed) {
			return;
		} else {
			numAtWarehouse -= needed;
			numInTransit += needed;	
		}
	}
	
	/** receiveItem - update stock levels upon receipt */
	public void receiveItem(int numRcvd) {
		numInTransit -= numRcvd;
		numInStore += numRcvd;
		numAtStart = numInStore;
		numSold = 0;
	}

  // methods for retrieving information
  /** Added return types*/	
	/** getPrice - return unit price */
	public double getPrice() {    
		return unitPrice;
	}

	/** getInStock - return number currently at store */
	public int getInStock() {
		return numInStore;
	}

	/** getOnOrder - return number in transit */
	public int getOnOrder() {
		return numInTransit;
	}
    /** numAtWarehouse or numInWarehouse?  You had numInWarehouse here in the return but
     * field declaration said numAtWarehouse
     */
	/** getInWarehouse - return number in warehouse */
	public int getInWarehouse() {
		return numAtWarehouse;
	}

  // method to display item data on screen

	public void displayItem() {
		System.out.println();
		System.out.println("SKU: " + SKU);
		System.out.println("Name: " + name);
		System.out.println(descrip);
		System.out.println("In stock: " + numInStore);
		System.out.println("On order: " + numInTransit);
		System.out.println("In warehouse: " + numAtWarehouse);
		System.out.println();	
	}

  // toString - only for writing to file
  /** Added String result; because result was not defined.  Also changed Integer.toString because (String) was not compiling*/
	public String toString() {
	    String result;
		result = "";
		result += Integer.toString(SKU) + '\t';
		result += name + '\t';
		result += descrip + '\t';
		result += Integer.toString(numInStore) + '\t';
		result += Integer.toString(numAtWarehouse) + '\t';
		result += Integer.toString(numInTransit) + '\n';
		return result;
	}

} 
