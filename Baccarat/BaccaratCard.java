
/*
 * Created by Mattia Gennaro
 * sc15mg
 */

//creating the BaccaratCard class
public class BaccaratCard extends Card{
	//creating a constructor using super
	public BaccaratCard(char newRank, char newSuit){
		
		super(newRank,newSuit);
	}
	//creating a cunstructor that takes a string as parameter
	public BaccaratCard(String newCode){
		
		super(newCode);
		//checking the length of the string
		if (newCode.length() != 2) {
			//if contains more then 2 elements, give this error
		      throw new IllegalArgumentException("card codes must be two characters");
		    }
	}
	//using @Override to get the value
	@Override public int value(){
		
		return (Math.min(getRanks().indexOf(getRank()) + 1, 10));
	}
}
