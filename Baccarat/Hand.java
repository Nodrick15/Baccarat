
/*
 * Created by Mattia Gennaro
 * sc15mg
 */

//creating the class
public class Hand extends CardCollection {
	//overriding the toString() method	
	@Override
	public String toString(){
		//creating an empty string
		String result = "";
		//looping trough the list 
		for(Card card : cards){
			//storing the toString()+" " into value
			String value = card.toString()+" ";
			//concatenating the two strings
			result += value;
		}
		//returning the string
		return String.format(result);
	}
	//getting the total value of the cards inside the list
	public int value(){
		//setting total to 0
		int total = 0;
		//loopimg trough the list
		for(Card card : cards){
			//storing the value of each card into value
			int value = card.value();
			//summing them togheter
			total += value;
			//if the total is greater then 10 and less then 19
			if(total >= 10 && total <= 19){
				//substract 10
				total = total - 10;
			}
			//else if is greater or equal then 20 and less then 29
			else if(total >= 20 && total <= 29){
				//substract 20
				total = total - 20;
			}
		} 
		//returning the total.
		return total;
	}
}
