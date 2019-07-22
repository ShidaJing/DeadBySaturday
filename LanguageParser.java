import java.util.Scanner;

public class LanguageParser {

	
	public static void languageParse(String c, TextAdventure t, Scanner in)  {
		
		/* split the input string and store it */
		String[] inString = c.split(" ");
		
		/* check commands individually */
		if  (inString.length == 1
				&& inString[0].equalsIgnoreCase("rest") )  {
			t.player.rest();
			t.oneturn();
			
		}  else  if  (inString.length == 2
				&& inString[0].equalsIgnoreCase("go") )  {
			t.player.go(inString[1], t.m);
			t.oneturn();
			
		}  else  if  (inString.length == 2 
				&& inString[0].equalsIgnoreCase("consume") )  {
			t.player.consume(inString[1]);
			t.oneturn();
			
		}  else  if  (inString.length == 3
				&& inString[0].equalsIgnoreCase("pick") 
				&& inString[1].equalsIgnoreCase("up"))  {
			t.player.pickup(inString[2]);
			t.oneturn();
			
		}  else  if  (inString.length == 3
				&& inString[0].equalsIgnoreCase("item") 
				&&  inString[1].equalsIgnoreCase("info"))  {
			t.player.itemInfo(inString[2]);
			t.oneturn();
			
		}  else  if  (inString.length == 2
				&& inString[0].equalsIgnoreCase("look")
				&& inString[1].equalsIgnoreCase("around"))  {
			t.player.lookaround();
			t.oneturn();
			
		}  else  if  (inString.length == 2 && inString[0].equalsIgnoreCase("discard"))  {
			t.player.discard(inString[1]);
			t.oneturn();
			
		}  else  if  (inString.length == 2
				&& inString[1].equalsIgnoreCase("room") 
				&& inString[0].equalsIgnoreCase("search") )  {
			t.player.search();
			t.oneturn();
			
		}  else  if  (inString.length == 1 && inString[0].equalsIgnoreCase("status"))  {
			t.player.status();
			t.oneturn();
			
		}  else  if  (inString.length == 2 
				&& inString[0].equalsIgnoreCase("command") 
				&& inString[1].equalsIgnoreCase("menu")) {
			t.player.commands();
			
		}  else  if  (inString.length == 1 
				&& inString[0].equalsIgnoreCase("bag")) {
			t.player.bag();
			t.oneturn();
			
		}  else  if  (inString.length == 1 
				&& inString[0].equalsIgnoreCase("location")) {
			t.player.location();
			t.oneturn();
			
		}  else  if  (inString.length == 3 
				&& inString[0].equalsIgnoreCase("attack")
				&& inString[1].equalsIgnoreCase("with")) {
			t.player.attack(inString[2], in);
			t.oneturn();
			
		}  else  {
			System.out.println("Invalid input. Check your spellings, or type in <command menu> for instructions");
		}
	}
}
