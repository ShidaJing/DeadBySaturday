import java.util.*;

public class itemInventory {

	/* this inventory contains items */

	/* this is an array of items. The maximum size of the array is set to be 7 */
	private ArrayList<Item>  items;


	/* constructor */
	public itemInventory()  {
		this.items = new ArrayList<Item>();
	}


	public int size()  {
		return this.items.size();
	}

	
	/**
	 * Checks if the inventory contains an item. 
	 * The input is a string of the name of the item we are looking for.
	 * @param item
	 * @return
	 */
	public boolean contains(String itemName)  {

		/* return value */
		boolean ret = false;

		for (Item i : this.items)  {

			/* checks if the input string is the name of the current item */
			ret = ret || i.isItem(itemName);
		}

		return ret;
	}


	public boolean isFull()  {
		
		/* if the inventory has more than 7 items, it is full */
		return this.size() >= 7;
	}


	public void add(Item item)  {
		
		/* if the inventory is full, we do NOT print out the error message in this function */
		
		/* we print the error message in the next function */
		if  (!this.isFull())  {
			this.items.add(item);
		}
	}


	/**
	 * This function is used on the player's bag when the player pick up something
	 * @param item
	 */
	/* NOTICE: this method takes an item, not a string. */
	/* the input item comes from the room's inventory (the "return and delete" function)*/
	public void pickup(Item item)  {

		if  (this.isFull())  {
			System.out.println("You cannot pick up this item because your bag is full.");


			/* if the player's bag is not full, do the following */
		}  else  if  (item.equals(null)){
			/* if the item we are picking is not in the room's inventory, the room will return null */
			/* if the input is null, that means the room does not contain it */
			/* for more details see the next function */

		}  else  {

			this.items.add(item);

			System.out.println("You have picked up " + item.getName());

		}	
	}


	/**
	 * this function is called by the room's inventory when the user pick up an item
	 * It returns null if the room does not have this item. It takes a string.
	 * @param name
	 * @return
	 */
	public Item returnItemAndDelete(String name)  {

		/* if the room does not contain the item */
		if (!this.contains(name))  {
			return null;
			
		}  else  {

			/* if the room contains the item, find it and return it */
			for  (Item i : this.items)  {
				if  (i.isItem(name))  {

					/* if we find the item i */
					Item ret = i;

					/* remove the item from the room */
					this.items.remove(i);

					return ret;
				}
			}
		}
		return null;	
	}


	/**
	 *  this method returns an item from the inventory, 
	 *  but does NOT delete it from the inventory.
	 *  This function is used when the user input "item info <item name>"
	 *  It returns the item from the inventory and applies "itemInfo()" function in
	 *  the "Item.java" 
	 */
	public Item returnItemDontDelete(String name)  {

		/* if the inventory does not contain the item */
		if (!this.contains(name))  {
			return null;
			
		}  else  {

			/* if the inventory contains the item, find it and return it */
			for  (Item i : this.items)  {
				if  (i.isItem(name))  {

					/* if we find the item */
					Item ret = i;
					return ret;
				}
			}
			return null;
		}
	}


	/**
	 * called by player
	 * @param itemName
	 * @return if return null, it has no effect on the player
	 */
	public Item consume(String itemName)  {

		if  (this.contains(itemName))  {

			Item ret = null;

			for (Item i : this.items)  {
				if (i.isItem(itemName))  {
					ret = i;

					/* if we find the item, check category */
					if  (i.getCategory() == 4)  {
						this.items.remove(i);
						System.out.println("You have consumed " + i.getName() + 
								", but you are not feeling really well after eating it.");
						System.out.println("Maybe it's not something you are supposed to eat, is it?");
						return null;
						
					}  else  {
						this.items.remove(i);
						System.out.println("You have consumed " + i.getName());
						return ret;
					}
				}
			}

		}  else  {
			System.out.println("You cannot consume this item because you don't have it in your bag yet.");
			return null;
		}	
		return null;
	}




	/**
	 * called by player
	 * @param name
	 */
	public boolean discard(String name)  {

		if  (this.contains(name))  {

			for  (Item i : this.items)  {
				if  (i.isItem(name))  {
					this.items.remove(i);
					System.out.println("You have discarded one " + i.getName());
					return true;
				}
			}

			/* ignore the following three lines */
			System.out.println("If you see this message, the discard function is not working properly.");
			System.out.println("Please contact game developer immediately.");
			return false;

		}  else  {

			/* if the bag does not contain the item */
			System.out.println("You cannot discard this item because you don't have it yet.");
			return false;
		}
	}


	public void print()  {

		if  (this.size() == 0)  {
			
			/* if the bag has nothing */
			System.out.println("Absolutely nothing.");
			
		}  else  {

			/* add everything in the inventory to a map */
			Map<Item, Integer> m = new HashMap<Item, Integer>();

			/* traverse the inventory */
			for (Item i: this.items)  {

				if  (m.containsKey(i))  {
					m.put(i, m.get(i)+1);

				}  else  {
					m.put(i, 1);
				}
			}   /* finish putting all items in the map */

			/* print out the map */
			Iterator<Map.Entry<Item, Integer>> itor = m.entrySet().iterator();
			while (itor.hasNext()) {
				Map.Entry<Item, Integer> pair = (Map.Entry<Item, Integer>)itor.next();
				System.out.println(pair.getKey().getName() + "---" + pair.getValue());
			}
		}
	}

}
