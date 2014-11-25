/** Holds the record in memory for a single item */

package inventory;

import java.util.*;

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
	
	public ItemRecord(String file) {
		Scanner sc = new Scanner(new File(file));
		SKU = sc.nextInt();
		numInStore = sc.nextInt();
		numAtWarehouse = sc.nextInt();
		numInTransit = sc.nextInt();
		numSold = sc.nextInt();
		name = sc.next();
		StringBuilder sb = new StringBuilder();
		while (sc.hasNext()) {
			sb.append(sc.next());
		}
		descrip = sb.toString();
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
		needed = int(salesVelocity * 30); // enough for 30 days
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
	
	/** getPrice - return unit price */
	public getPrice() {
		return unitPrice;
	}

	/** getInStock - return number currently at store */
	public getInStock() {
		return numInStore;
	}

	/** getOnOrder - return number in transit */
	public getOnOrder() {
		return numInTransit;
	}

	/** getInWarehouse - return number in warehouse */
	public getInWarehouse() {
		return numInWarehouse;
	}

// method to display item data on screen

	public void displayItem() {
		System.out.println();
		System.out.println("SKU: " + SKU);
		System.out.println("Name: " + name);
		System.out.println(descrip);
		System.out.println("In stock: " + numInStore);
		System.out.println("On order: " + numInTransit);
		System.out.println("In warehouse: " + numInWarehouse);
		System.out.println();	
	}

// toString - only for writing to file

	public String toString() {
		result = "";
		result += (String)(SKU) + '\t';
		result += name + '\t';
		result += descrip + '\t';
		result += (String)(numInStore) + '\t';
		result += (String)(numAtWarehouse) + '\t';
		result += (String)(numInTransit) + '\n';
	}

} 
