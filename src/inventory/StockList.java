//** StockList - interface for data structure that holds ItemRecords */

package inventory;

public interface StockList<ItemRecord> {
	
	/** find - find ItemRecord with keyValue */
	public ItemRecord find();

	/** insert - insert new ItemRecord */
	public void insert(ItemRecord newItem);

	/** delete - delete old ItemRecord */
	public void delete(ItemRecord oldItem);

}
