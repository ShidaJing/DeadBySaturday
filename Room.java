

public abstract class Room {

	protected String name;
	protected String north;
	protected String east;
	protected String west;
	protected String south;
	protected itemInventory itemList;
	protected boolean searched;
	protected int human;

	public Room(String name, String e, String s, String w,  String n, itemInventory itemList)  {
		this.name = name;
		this.north = n;
		this.east = e;
		this.west = w;
		this.south = s;
		this.itemList = itemList;
		this.searched = false;
		
		/* default: no human in the room */
		this.human = -1;
	}

	
	public String getName()  {
	return this.name;	
	}
	
	
	public String go(String direction)  {
		if  (direction.equalsIgnoreCase("north"))  {
			return this.north;
		}  else  if  (direction.equalsIgnoreCase("east"))  {
			return this.east;
		}  else  if  (direction.equalsIgnoreCase("west"))  {
			return this.west;
		}  else  if  (direction.equalsIgnoreCase("south"))  {
			return this.south;
		}  else  {
			System.out.println("Cannot recognize this direction. Check your spellings");
			return null;
		}
	}
	
	
	public Item pickup (String name)  {
		
		if (this.searched == true)  {
			
		/* returnItemAndDelete returns null if the item is not found */
		/* the same behavior is passed here */
		Item i = this.itemList.returnItemAndDelete(name);
		if  (i == null)  {
			
			/* if i is null, the room does not have this item */
			System.out.println("There is no such item in the room.");
			return null;
		}  else  {
			
			return i;
		}
		}  else  {
			
			/* if the player did not search the room first, don't return anything */
			System.out.println("You haven't searched the room yet.");
			return null;
		}
	}
	
	public abstract void lookaround();
	
	public abstract void search();
	
}