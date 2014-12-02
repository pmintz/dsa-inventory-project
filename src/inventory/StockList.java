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


public class HashTable<ItemRecord> implements Set<ItemRecord> {
	
	private ItemRecord deleted;
	private ItemRecord[] data;
	private int fullness;
	
	public HashTable(ItemRecord deleted){
		data = (ItemRecord[])(new Object [1]);
		fullness = 0;
		this.deleted = deleted;
		
	}

	protected int hash1(ItemRecord target){
		return Math.abs(target.hashCode()) % data.length;
	}
	
	protected int hash2(ItemRecord target){
		int result = Math.abs(target.hashCode()) % (data.length-1);
		if(result%2 == 0){ return result + 1;}
		return result;
	}
	public int size(){
		int tally = 0;
		for (ItemRecord target: data){
			if((item != null) && (item != deleted)){
				tally++
			}
		}
		return tally;
	}
	
	public boolean contains(ItemRecord target){
		int start = hash1(target);
		int i = start;
		while (data[i] != null){
			if(target.equals(data[i])){
				return true;
			}
			i = (i+ hash2(target)) % data.length;
			if(i == start){
				return false;
				
			}
		}
		return false;
	}
	public void add(ItemRecord target){
		if(fullness >= data.length/2){
			rehash();
		}
		int start = hash1(target);
		int i = start;
		while(data[i] != null){
			if(target.equals(data[i])){
				return;
			}
			i = (i+hash2(target)) % data.length;
			if(i == start){
				return;
			}
		}
		data[i] = target;
		fullness++;
		}
	public void rehash(){
HashTable<ItemRecord> newTable = new HashTable<ItemRecord>(deleted);
newTable.data = (ItemRecord[])(new Object[data.length *2]);
for(int i = 0; i<data.length; i++){
	if((data[i] != null) && (data[i] != deleted)){
		newTable.add((ItemRecord)(data[i]));
	}
}

data = newTable.data;
fullness = newTable.fullness;

	}
	
	public void remove(ItemRecord target){
		int start = hash1(target);
		int i = start;
		while (data[i] != null){
			if(target.equals(data[i])){
				data[i] = deleted;
				return;
			}
			i = (i + hash2(target)) % data.length;
			if(i == start){
				return;
				
			}
		}


