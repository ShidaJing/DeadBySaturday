
public class Balcony extends Room {

	public Balcony(String name, String e, String s, String w, String n, itemInventory itemList) {
		super(name, e, s, w, n, itemList);
		
		/* This room has a human */
		this.human = 100;
	}

	
	@Override
	public void lookaround() {
		System.out.println("This room looks like a balcony.");
		System.out.println("It is cold out here.");
		/* if the room has a human, tell the user that there is a human */
		if  (this.human >= 0)  {
			System.out.println("A girl is standing against the wall, staring at you.");
			System.out.println("She is holding a knife.");
		}
	}
	
	
	public void search()  {
		this.searched = true;
		System.out.println("You searched the balcony, and found:");
		this.itemList.print();
		

	}

}
