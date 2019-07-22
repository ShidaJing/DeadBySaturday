import java.util.Map;
import java.util.Scanner;

public class Player {

	private int health;
	private int food;
	private int water;
	private int mental;
	private itemInventory bag;
	private Room currentRoom;

	/* if the user kills humans, add these two to the currentRoom's item inventory */
	private Item humanFlesh;
	private Item humanBlood;


	public Player(String roomName, Map<String, Room> m)  {
		this.health = 100;
		this.food = 100;
		this.water = 100;
		this.mental = 100;
		this.bag = new itemInventory();
		this.currentRoom = m.get(roomName);
		this.humanBlood = new Item("human-blood", 0, 99);
		this.humanFlesh = new Item("human-flesh", 1, 99);
	}


	public void status()  {
		System.out.println("Current status:");
		System.out.println("Health: " + this.health);
		System.out.println("Food: " + this.food);
		System.out.println("Water: " + this.water);
		System.out.println("Mental status: " + this.mental);
		System.out.println("Your bag contains: ");
		this.bag.print();
	}


	public void rest()  {
		this.health += 6;
		this.mental += 31;
	}


	public void bag()  {
		System.out.println("Your bag contains:");
		this.bag.print();
	}


	public void location()  {
		System.out.println("You are in a:");
		System.out.println(this.currentRoom.name);
	}


	public void go(String direction, Map<String, Room> m)  {

		/* newRoom is going to be null if the direction is invalid */
		String newRoom = this.currentRoom.go(direction);
		if (newRoom == null)  {

			/* do nothing, because the spelling is incorrect */

		}  else  {

			if  (newRoom.equalsIgnoreCase("wall"))  {
				System.out.println("You cannot go this way, there is no door in this direction.");

				/* the following four lines are to restore the user's life status */
				/* If the user runs into a wall, it is not going to drop life status much */
				/* otherwise the game will be too hard */
				this.health += 1;
				this.food += 2;
				this.water += 5;
				this.mental += 7;

			}  else  {

				/* if newRoom is not null or a wall */
				this.currentRoom = m.get(newRoom);
				System.out.println("You have entered another room.");
			}
		}
	}


	public void consume(String item)  {

		/* check if the player has the item */
		if  (!this.bag.contains(item))  {
			System.out.println("Cannot consume this item, because it is not in your bag.");
		}  else  {

			/* if the bag does contain the item, consume and return it */
			Item ret = this.bag.consume(item);

			/* if ret is null, it means there is no effect on the player, otherwise, do the effect */
			if  (ret != null)  {	

				/* every time you eat or drink something, your mental state increases */
				this.mental += 10;

				if  (ret.getCategory() == 0)  {
					this.water += ret.getEffect();

				}  else  if  (ret.getCategory() == 1)  {
					this.food += ret.getEffect();

				}  else if  (ret.getCategory() == 2)  {

					/* if you take medicine, both your mental and health status is increased */
					this.health += ret.getEffect();
					this.mental += 45;
				}
			}
		}

	}  /* end of consume function */


	public void pickup(String item)  {

		if  (this.bag.isFull())  {
			System.out.println("Cannot pick up this item because your bag is full.");
		}  else  {

			/* take the item out of the room inventory */
			Item pick = this.currentRoom.pickup(item);

			if  (pick != null)  {
				this.bag.pickup(pick);
			}
		}
	}


	public void itemInfo(String item)  {

		/* if you did not search the room, you cannot examine anything */
		if  (this.currentRoom.searched == false)  {
			System.out.println("You haven't searched the room yet.");

		}  else  {

			/* if the player has searched the room */
			Item look = this.currentRoom.itemList.returnItemDontDelete(item);

			if  (look == null && !this.bag.contains(item))  {

				/* if the neither the room nor the bag contains the item */
				System.out.println("You cannot look at this item, "
						+ "because its not in your bag or the room.");
			}  else  {

				/* at this point, either your bag or the room contains the item */

				/* if the item is in the room */
				if  (look != null)  {
					Item.itemInfo(look);
				}  else  {

					/* if the item is in the bag */
					Item.itemInfo(this.bag.returnItemDontDelete(item));
				}
			}
		}
	}


	public void lookaround()  {
		this.currentRoom.lookaround();
	}


	public void discard(String item)  {
		this.bag.discard(item);
	}


	public void search()  {
		this.currentRoom.lookaround();
		this.currentRoom.search();
	}


	public void attack(String weapon, Scanner in)  {

		/* check if the player has that item */
		if  (!this.bag.contains(weapon))  {
			System.out.println("You cannot attack because you don't have this weapon yet.");

			/* if the person has the item */
		}  else  if  (this.currentRoom.human <= 0)  {

			/* if the room does not have a living human */
			System.out.println("There is no one for you to attack.");

			/* if the room has living human */
		}  else  if  (this.currentRoom.human > 0)  {

			/* if the item is not a weapon */
			if  (this.bag.returnItemDontDelete(weapon).getCategory() != 4)  {
				System.out.println("Cannot attack.");
				System.out.println("Because " + weapon + " is not a weapon.");
			}  else  {

				/* if everything is ready */
				System.out.println("Are you sure? You may potentially kill this person.");
				System.out.println("Answer [yes] or [no]");
				String answer = in.nextLine();

				/* interpret user's answer */
				if  (answer.equalsIgnoreCase("yes"))  {
					/* attack the person */
					System.out.println("You attack the girl with your " + 
							this.bag.returnItemDontDelete(weapon).getName() + ".");

					System.out.println("She screams.");

					/* decrease the human's life status */
					this.currentRoom.human -= this.bag.returnItemDontDelete(weapon).getEffect();

					/* check if the person is dead */
					if  (this.currentRoom.human <= 0)  {
						System.out.println("You killed this person.");
						System.out.println("The person drops human flesh and blood. You may pick them up now or later.");
						System.out.println("To pick them, type <pick up human-flesh> or <pick up human-blood>");
						System.out.println("");
						this.currentRoom.itemList.add(humanFlesh);
						this.currentRoom.itemList.add(humanBlood);
					}  else  {

						/* if the attack does not kill the person */
						/* no effect */
					}

					/* if the user stop the attack */
				}  else  if  (answer.equalsIgnoreCase("no"))  {
					System.out.println("Attack terminated.");
				}  else  {

					/* if the answer is neither yes nor no */
					System.out.println("Cannot recognize your answer. Attack terminated.");
				}
			}
		}
	}


	/* check if the user is alive before every turn */
	public boolean checkAlive()  {
		return (this.health > 0 
				&& this.food > 0
				&& this.water > 0
				&& this.mental > 0);
	}


	/* for each turn, decrease life status */
	public void oneturn()  {
		this.water -= 6;
		this.food -= 3;
		this.health -= 1;
		this.mental -= 8;
	}


	/* if any of the life status is low, print out warning */
	public void checkWarning()  {

		System.out.println("");

		if  (this.health <= 5)  {
			System.out.println("WARNING: extremely tired. Seek MEDICINE or REST immediately.\n");
		}
		if  (this.water <= 30)  {
			System.out.println("WARNING: extremely thirsty. Seek LIQUID immediately.\n");
		}
		if  (this.food <= 15)  {
			System.out.println("WARNING: extremely hungry. Seek FOOD immediately.\n");
		}
		if  (this.mental <= 40)  {
			System.out.println("WARNING: mental state low. Take medicine or rest immediately.\n");
		}
	}


	/* this function checks if your status goes over 100. */
	/* if yes, cap them */
	/* this function is called before every turn */
	public void checkCap()  {
		if  (this.health > 100)  {
			this.health = 100;
		}
		if  (this.food > 100)  {
			this.food = 100;
		}
		if  (this.water > 100)  {
			this.water = 100;
		}
		if  (this.mental > 100)  {
			this.mental = 100;
		}
	}


	public void checkDeathReason()  {
		if  (this.health <= 0)  {
			System.out.println("You died of injury and tiredness.");
			System.out.println("Take rests or pills next time.");
		}
		if  (this.food <= 0)  {
			System.out.println("You died of hunger.");
			System.out.println("Eat more food.");
		}
		if  (this.water <= 0)  {
			System.out.println("You died of thirst.");
			System.out.println("Keep hydrated next time.");
		}
		if  (this.mental <= 0)  {
			System.out.println("You were too depressed to carry on.");
			System.out.println("Watch your mental state.");
		}
	}

	/* print out command menu */
	public void commands()  {
		System.out.println("Command Menu:");
		System.out.println("\"go <direction>\": goes to a certain direction.");
		System.out.println("\"status\": prints out player life status.");
		System.out.println("\"rest\": take a rest. Mental and Health status will increase.");
		System.out.println("\"consume <item name>\": consumes one item in your bag.");
		System.out.println("\"pick up <item name>\": picks up one item found in the room.");
		System.out.println("\"item info <item name>\": gives you info about an item in the bag or the room");
		System.out.println("\"discard <item name>\": discard one item from bag.");
		System.out.println("\"search room\": search the room for items.");
		System.out.println("\"bag\": see what's in your bag.");
		System.out.println("\"location\": tells you locations.");
		System.out.println("\"command menu\": prints out this menu.");
		System.out.println("\"attack with <item name>\": attack person in the room with item");
		System.out.println("\"suicide\": suicide.");
	}

}
