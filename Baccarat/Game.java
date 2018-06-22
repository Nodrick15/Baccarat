import java.lang.Math;
import java.util.Scanner;

/*
 * Created by Mattia Gennaro
 * sc15mg
 */



public class Game{
	//setting the fields
	private Shoe shoe;
	private Hand player;
	private Hand banker;
	
	//constructor that initialise a game
	public Game(int numDecks){
		//create a shoe
		shoe = new Shoe(numDecks);
		//create two new hands
		player = new Hand();
		banker = new Hand();
		//shuffle
		shoe.shuffle();
	}
	//method that plays a round
	public void playRound(){
		//discard the cards from both hands
		player.discard();
		banker.discard();
		//give two cards
		for(int x = 0; x < 2; x++){
			player.add(shoe.deal());
			banker.add(shoe.deal());
		}
		//print the cards and the value of the hands
		System.out.println("Player: "+ player.toString()+"= " + player.value());
		System.out.println("Banker: "+ banker.toString()+"= " + banker.value());
		
		int playerScore = player.value();
		int bankerScore = banker.value();
		//if the value of the player hand is less then 5, the player take a third card
		if(playerScore <= 5){
			
			System.out.println("Dealing a third card to the player...");
			player.add(shoe.deal());
			//getting the value of the third card
			Card card = player.cards.get(player.cards.size()-1);
			//printing wich card did the player get
			int last = card.value();
			System.out.println("The player draw : " + card.getRank()+card.getSuit());
			System.out.println();
			
			/*Using the mathematical formula to compute if the banker has to take a third card as well:
			 * 
			 * Instead of using all those if and else statement, on the wikipidia page there is a formula
			 * that computes whenever or not the banker has to draw.
			 * 
			 */
			if(last == 10){
				last = 0;
			}
	
			if(last == 8){
				last = -2;
			}
			else if(last == 9){
				last = -1;
			}
			
			int calculation = (int) Math.floor(last/2);
			
			if(calculation == 1 || calculation == -1){
				calculation = 0;
			}
			
			calculation += 3;
			//if the calulation is greater than the bankerScore, the banker draws a third card
			if(bankerScore <= calculation){
				System.out.println("Dealing a third card to the banker...");
				banker.add(shoe.deal());
				Card cardB = banker.cards.get(banker.size()-1);
				System.out.println("The banker draw : "+cardB.getRank()+cardB.getSuit());
				System.out.println();
			}
		}
		//but also, if the player doesn't draw a third card, but the banker value is less or equal then 5
		//banker draws a card
		else if (banker.value() <= 5){
			System.out.println("Dealing a third card to the banker...");
			banker.add(shoe.deal());
			Card cardB = banker.cards.get(banker.size()-1);
			System.out.println("The banker draw : "+cardB.getRank()+cardB.getSuit());
			System.out.println();
		}
		//print hands and total again
		if(player.size() == 3 || banker.size() == 3){
			System.out.println("Player: "+ player.toString()+"= " + player.value());
			System.out.println("Banker: "+ banker.toString()+"= " + banker.value());
			System.out.println();
		}
		
	}
	/*Same function as playRound()
	 * 
	 * But instead of having only a round, it play's untill the shoe is finished
	 * 
	 */
	public void play(){
		
		int pw = 0;
		int bw = 0;
		int t = 0;
			//Keep playing untill you reach the end of the shoe 
		for(int y = 1; y < shoe.size(); y++){	
			System.out.println();
			System.out.println("Round " +y );
				//call playRound
			playRound();
				
				//print the statistics:
			if(player.value() > banker.value()){
				System.out.println();
				System.out.println("Player wins!");
				pw++;
			}
			else if(banker.value() > player.value()){
				System.out.println();
				System.out.println("Banker wins!");
				bw++;
			}
			else if(player.value() == banker.value()){
				System.out.println();System.out.println();
				System.out.println("Tie!");
				t++;
			}
			System.out.println(pw+": Player wins");
			System.out.println(bw+": Banker wins");
			System.out.println(t+": Ties");
			System.out.println(y+": Round played");
		}
	}
	
	/*This method is really similar to the play() as well
	 * 
	 * We just get the user input and we keep going if the input
	 * is "y" and the shoe is not exausted
	 * 
	 */
	public void playWithPrompt(){
		//create the scanner
		Scanner input = new Scanner (System.in);
		String y = "y";
		String n = "n";
		String answer = null;
		int count = 1;
		int pw = 0;
		int bw = 0;
		int t = 0;
		//Play a normal round
		System.out.println("Round " + count);
		playRound();
		if(player.value() > banker.value()){
			System.out.println("Player wins!");
			pw++;
		}
		else if(banker.value() > player.value()){
			System.out.println("Banker wins!");
			bw++;
		}
		else if(player.value() == banker.value()){
			System.out.println("Tie!");
			t++;
		}
		//take the input
		System.out.print("Do you want to play an other hand? (y / n) : ");
		answer = input.nextLine();
		System.out.println();
		
		//if the input is "n",print the statistics
		if(n.equals(answer)){
			System.out.println(pw+": Player wins");
			System.out.println(bw+": Banker wins");
			System.out.println(t+": Ties");
			System.out.println(count+": Round played");
				
			if(pw > bw){
				System.out.println("In this simulation the player won more rounds then the banker.");
			}
			else if (pw < bw){
				System.out.println("In this simulation the banker won more rounds then the player.");
			}
			else if(t > pw && t > bw){
				System.out.println("There have been more ties then winning rounds.");
			}
			else if (pw == bw) {
				System.out.println("In this simulation the player and the banker won the same amount of times");
			}
			
		}
			//while the shoe has cards
			while(shoe.isEmpty() == false){
				//if the input is "y", keep playing
				while(y.equals(answer)){
					System.out.println("Round " + (count+1));
					playRound();
					
					if(player.value() > banker.value()){
						System.out.println("Player wins!");
						pw++;
					}
					else if(banker.value() > player.value()){
						System.out.println("Banker wins!");
						bw++;
					}
					else if(player.value() == banker.value()){
						System.out.println("Tie!");
						t++;
					}
					
					if(shoe.isEmpty() == true){
						System.out.println("The shoe is empty");
					}
					System.out.print("Do you want to play another hand? (y / n) : ");
					answer = input.nextLine();
					System.out.println();
					
					count++;
					
				/*I had to do this if statement again because the one before was true only
				 * if the player played just a round.
				 * 
				 * Now that we are in the loop, I need to repeat it, otherwise I wont have an output
				 * 
				 */
					
				if(n.equals(answer)){
					System.out.println(pw+": Player wins");
					System.out.println(bw+": Banker wins");
					System.out.println(t+": Ties");
					System.out.println(count+": Round played");
						
					if(pw > bw){
						System.out.println("In this simulation the player won more rounds then the banker.");
					}
					else if (pw < bw){
						System.out.println("In this simulation the banker won more rounds then the player.");
					}
					else if(t > pw && t > bw){
						System.out.println("There have been more ties then winning rounds.");
					}
					
					else if (pw == bw) {
						System.out.println("In this simulation the player and the banker won the same amount of times");
					}
					
				}
				
			}	
		
		}
		//close the input
		input.close();
	}
	
	//create the main
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		System.out.print("Please select the number of decks that you want to play with: ");
		int in = input.nextInt();
		
		Game test = new Game(in);
		//playing 
		test.playWithPrompt();
		
		input.close();
	}
}
