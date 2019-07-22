import java.util.*;


public class LoadWorld {

	/* this function returns a map that has all the rooms initialized in a game */
	public static Map<String, Room> loadWorld()  {
		Map<String, Room> m = new HashMap<String, Room>();
		
		/* water */
		Item bottle_of_water = new Item("bottle-of-water", 0, 65);
		Item coke = new Item("coke", 0, 34);
		Item pee = new Item("pee", 0, 30);
		Item doctor_pepper = new Item("doctor-pepper", 0, 25);
		Item tea = new Item("tea", 0, 58);
		Item coffee = new Item("coffee", 0, 25);
		
		/* food */
		Item pizza = new Item("pizza", 1, 86);
		Item apple = new Item("apple", 1, 33);
		Item tomato = new Item("tomato", 1, 27);
		Item cookie = new Item("cookie", 1, 15);
		Item cup_cake = new Item("cup-cake", 1, 29);
		Item chicken_leg = new Item("chicken-leg", 1, 32);
		
		/* medicine */
		Item pill = new Item("pill", 2, 4);
		Item adrenaline = new Item("adrenaline", 2, 15);
		
		/* weapon */
		Item pistol = new Item("pistol", 4, 53);
		Item knife = new Item("knife", 4, 26);

		/* initializing living room 1 */
		itemInventory livsinv = new itemInventory();

		livsinv.add(apple);
		livsinv.add(pill);
		livsinv.add(pill);
		livsinv.add(coke);
		livsinv.add(tomato);
		livsinv.add(bottle_of_water);
		m.put("small living room", new Livingroom("small living room", 
				"wall", "small bedroom", "wall", "wall", 
				livsinv));

		/* initializing living room 2 */
		itemInventory bedsinv = new itemInventory();
		bedsinv.add(cup_cake);
		bedsinv.add(pizza);
		bedsinv.add(doctor_pepper);
		bedsinv.add(bottle_of_water);
		bedsinv.add(tea);
		bedsinv.add(adrenaline);
		m.put("small bedroom", new Bedroom("small bedroom", "wall", 
				"big living room", "wall", "small living room", 
				bedsinv));
		
		/* initializing living room 3 */
		itemInventory livbinv = new itemInventory();
		livbinv.add(chicken_leg);
		livbinv.add(cookie);
		livbinv.add(coffee);
		livbinv.add(bottle_of_water);
		livbinv.add(tea);
		m.put("big living room", new Livingroom("big living room", "balcony", 
				"bathroom", "kitchen", "small bedroom", 
				livbinv));
		
		/* initializing living room 4 */
		itemInventory kitinv = new itemInventory();
		kitinv.add(chicken_leg);
		kitinv.add(pizza);
		kitinv.add(tomato);
		kitinv.add(bottle_of_water);
		kitinv.add(pee);
		m.put("kitchen", new Kitchen("kitchen", "big living room", 
				"big bed room", "wall", "wall", 
				kitinv));
		
		/* initializing living room 5 */
		itemInventory balco = new itemInventory();
		balco.add(apple);
		m.put("balcony", new Balcony("balcony", "wall", 
				"wall", "big living room", "wall", 
				balco));
		
		/* initializing living room 6 */
		itemInventory bath = new itemInventory();
		bath.add(pee);
		bath.add(adrenaline);
		bath.add(pill);
		bath.add(pistol);
		m.put("bathroom", new Bathroom("bathroom", "wall", 
				"wall", "big bed room", "big living room", 
				bath));
		
		/* initializing living room 7 */
		itemInventory bigbed = new itemInventory();
		bigbed.add(apple);
		bigbed.add(knife);
		bigbed.add(cup_cake);
		bigbed.add(bottle_of_water);
		m.put("big bed room", new Bedroom("big bed room", "bathroom", 
				"wall", "wall", "kitchen", 
				bigbed));
		return m;
	}

}
