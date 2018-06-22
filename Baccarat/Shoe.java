
/*
 * Created by Mattia Gennaro
 * sc15mg
 */

import java.util.Collections;
//creating a class that allows to choose the number of decks
public class Shoe extends CardCollection {
	
	public int numDecks;
	//constructor that needs a parameter
	public Shoe(int numDecks){
		
		this.numDecks = numDecks;
		//if the number is not 4,6 or 8:
		if (numDecks == 4 || numDecks == 6 || numDecks == 8){
			//looping through the number of decks 
			for(int x = 0; x < numDecks; x++){
				//for each suit in the SUIT list
				for(char ranks : Card.getRanks())
					//for each rank in the RANK list
					for(char suit : Card.getSuits()){
						//add them to the list
						cards.add(new BaccaratCard(ranks,suit));
					}
				}
			}else{
				throw new IllegalArgumentException("You can only choose between 4,6, and 8");
			}
		}

	//creating a method that removes the first card of the deck
	public Card deal(){
		
		return cards.remove(0);
	}
	//creating a method that shuffles the cards
	public void shuffle(){
		
		Collections.shuffle(cards);
	}
	
}
