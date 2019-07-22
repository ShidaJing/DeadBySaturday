

public class Bedroom extends Room {

	public Bedroom(String name, String e, String s, String w, String n, itemInventory itemList) {
		super(name, e, s, w, n, itemList);
	}
	

	@Override
	public void lookaround() {
		System.out.println("This room looks like a bedroom.");
		System.out.println("It looks just like your own.");
	}
	
	
	public void search()  {
		this.searched = true;
		System.out.println("You searched the room, and found:");
		this.itemList.print();
	}

}
