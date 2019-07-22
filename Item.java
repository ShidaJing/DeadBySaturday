public class Item {

	/**
	 * Examples of name: tomato, apple.
	 * Examples of category: liquid(0), food(1),  medicine(2), weapon(4).
	 * Examples of effect: 1, 10, 32, etc.
	 * The effect field indicates, for example, 
	 * how many points of the food index do we increase
	 * if the player consumes this item. 
	 */
	protected String name;
	protected int category;
	protected int effect;

	
	/* constructor */
	public Item(String name, int category, int effect)  {
		this.name = name;
		this.category = category;
		this.effect = effect;
	}

	
	/* takes an input string, and checks if the string is the item's name */
	public boolean isItem(String name)  {
		return this.name.equalsIgnoreCase(name);
	}

	
	public String getName()  {
		return this.name;
	}

	
	public int getCategory()  {
		return this.category;
	}

	
	public int getEffect()  {
		return this.effect;
	}

	
	/* when the user input "item info <item name>", invoke this function
	* if this function is confusing to you, take a look at the "returnItemDontDelete()" 
	 * function in the itemInventory.java
	 */
	public static void itemInfo(Item item)  {

		/* if the input is null */
		if  (item == null)  {
			System.out.println("There is no such item in your bag, you cannot look at it.");
		}  else  {

			if (item.category == 0)  {
				System.out.println("This is just an ordinay " + item.name + ".");
				System.out.println("You can try drink it. It will increase your water status by " + item.getEffect() +".");
			}  else  if  (item.category == 1)  {
				System.out.println("This is just an ordinay " +item.name +".");
				System.out.println("You can try eat it. It will increase your food status by " + item.getEffect() +".");
			}  else  if  (item.category == 2)  {
				System.out.println("This is just an ordinay " + item.name +".");
				System.out.println("You can try eat it. It will increase your health status by " + item.getEffect() +".");
				System.out.println("It will increase your mental status by 45.");
			}  else  if  (item.category == 4)  {
				System.out.println("This is just an ordinay " + item.name +".");
				System.out.println("You can use it whenever necessary.");
			}
		}
	}
}