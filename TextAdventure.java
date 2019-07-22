import java.util.*;

public class TextAdventure {

	public Player player;
	public Map<String, Room> m;
	public int turns;
	public int days;
	
	public TextAdventure()  {
		this.m = LoadWorld.loadWorld();
		this.player = new Player("small living room", m);
		this.turns = 0;
		this.days = 1;
	}
	
	
	public void rest()  {
		this.player.rest();
		this.turns += 1;
	}
	
	
	/* this function is called every time the user input a valid command */
	public void oneturn()  {
		
		this.player.checkCap();
		this.turns += 1;
		this.player.oneturn();
		System.out.println("");
		System.out.println("Time of day: [" + (this.turns + 6) + ": 00 ]");
		
		/* the following part is only called when we pass one day */
		if  (this.turns >= 12)  {
			this.turns = 0;
			this.days += 1;
			System.out.println("The night has fallen.");
			System.out.println("You have survived " + (this.days - 1) + " days.");
			System.out.println("");
			
			/* start a new day */
			System.out.println("Day " + this.days);
			
			/* print out weather description from one of the followings */
			Random rand = new Random();
			int  r = rand.nextInt((3 - 0) + 1) + 0;
			if  (r == 0)  {
			System.out.println("Today is sunny. The sky is clear and beyond reach.");
			System.out.println("Everything happens for a reason.");
			}  else  if  (r == 1)  {
				System.out.println("Today is rainy. Rain drops hit the roof, making noises.");
				System.out.println("\"I don't know,\" you think to yourself, \"if I can get out alive.\"");
			}  else  if  (r == 2)  {
				System.out.println("Today is cloudy and cold. It might start raining soon. You feel lonely.");
			}  else  if  (r == 3)  {
				System.out.println("Today is windy. The wind is moaning outside the window.");
				System.out.println("You feel like the house is almost falling.");
			}
		}
	}
	
	
	/* welcome message and instructions */
	public static void welcomeToTheGame(Scanner in, TextAdventure t)  {
		System.out.println("");
		System.out.println("You wake up in an unfamiliar room, unable to remember anything from last night.");
		System.out.println("You find yourself locked in a house with several other strangers.");
		System.out.println("There is no way out.");
		System.out.println("[hit Enter]");
		in.nextLine();
		System.out.println("Your objective is to gather resources and stay alive.");
		System.out.println("[hit Enter]");
		in.nextLine();
		System.out.println("You have four statuses:");
		System.out.println("Health, food, water, and mental state.");
		System.out.println("If any of them drops to 0, you are dead.");
		System.out.println("Be aware that water and mental state drops more quickly.");
		System.out.println("[hit Enter]");
		in.nextLine();
		System.out.println("You can print this menu at anytime during the game.");
		t.player.commands();
		System.out.println("[hit Enter]");
		in.nextLine();
		System.out.println("Novice Guide:");
		System.out.println("Always search your room first.");
		System.out.println("You cannot consume items before picking them up.");
		System.out.println("If mental status is low, use medicine or take rest.");
		System.out.println("[type command]");
	}
	
	
	/* start the game */
	public static void main(String[] argc)  {
		
		TextAdventure t = new TextAdventure();
		Scanner in = new Scanner(System.in);
		
		welcomeToTheGame(in, t);
		
		String input = in.nextLine();
		
		while  (!input.equalsIgnoreCase("suicide"))  {
		LanguageParser.languageParse(input, t, in);
		t.player.checkWarning();
		
		if  (t.player.checkAlive() == false)  {
			/* if the user is dead, stop the game */
			break;
		}
		
		input = in.nextLine();
		System.out.println();
		}
		
		System.out.println();
		System.out.println("You are DEAD.");
		System.out.println("You survided " + t.days + " days.");
		
		/* check how you died */
		t.player.checkDeathReason();
		
		System.out.println("Thanks for playing.");
		in.close();

	}
	
}
