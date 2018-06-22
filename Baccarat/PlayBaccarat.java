/**
 * Created by Mattia Gennaro on 12/04/16.
 */

import javafx.animation.*;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


/*
 * 
 * Creating the class that will contain all the functions
 * and the logic. Basically the game will show the cards
 * with a brief animation, the score of the hands and the statistics as well.
 * 
 */

public class PlayBaccarat extends Application {
	
	//declaring most of the variables here so I can use them in all my functions
	
    private Group root;
    private Group cards;
    private Scene scene;
    private Shoe shoe;
    private Hand player;
    private Hand banker;
    private Card firstP;
    private Card secondP;
    private Card thirdP;
    private Card firstB;
    private Card secondB;
    private Card thirdB;
    private TranslateTransition thirdPlayerCard;
    private TranslateTransition thirdBankerCard;
    private PauseTransition pause;
    private int playerScore;
    private int bankerScore;

    private IntegerProperty x;
    private IntegerProperty y;
    private IntegerProperty z;
    private IntegerProperty rounds;

    private Text roundsT;
    private Text statisticRound;
    private Text playerWon;
    private Text statisticPlayer;
    private Text bankerWon;
    private Text statisticBanker;
    private Text tie;
    private Text statisticTie;
    private Text playerHand;
    private Text bankerHand;

    private IntegerProperty playerHandScore;
    private IntegerProperty bankerHandScore;

    private Text bHandV;
    private Text pHandV;
    

    /*
     * 
     * The main just launches the stage and displays everything
     * 
     */

    public static void main(String[] args) {
        launch(args);
    }
    
    /*
     * 
     * The start function contains the creation of the objects
     * and the trasitions as well as some little functions like the play()
     * and the score().
     * 
     */

    @Override
    public void start(Stage primaryStage) {

        root = new Group();
        cards = new Group();
        scene = new Scene(root,800,600, Color.DARKGREEN);

        root.getChildren().add(cards);
      
        Image backCard = new Image("card_back.png",72,96,false,false);
        ImageView back = new ImageView(backCard);
        root.getChildren().add(back);
        back.setX(360);
        back.setY(250);

        Button play = new Button();
        root.getChildren().add(play);
        play.setText("Play");
        play.setLayoutX(375);
        play.setLayoutY(350);
        
        rounds = new SimpleIntegerProperty(0);
        x = new SimpleIntegerProperty(0);
        y = new SimpleIntegerProperty(0);
        z = new SimpleIntegerProperty(0);
        playerHandScore = new SimpleIntegerProperty(0);
        bankerHandScore = new SimpleIntegerProperty(0);

        roundsT = new Text();
        playerWon = new Text();
        bankerWon = new Text();
        tie = new Text();
        pHandV = new Text();
        bHandV = new Text();

        statisticPlayer = new Text();
        statisticPlayer.setText("Player wins: ");
        statisticPlayer.setFont(Font.font("Verdana",15));
        statisticPlayer.setFill(Color.DARKRED);
        statisticPlayer.setX(20);
        statisticPlayer.setY(325);

        statisticBanker = new Text();
        statisticBanker.setText("Banker wins: ");
        statisticBanker.setFont(Font.font("Verdana",15));
        statisticBanker.setFill(Color.DARKRED);
        statisticBanker.setX(20);
        statisticBanker.setY(350);

        statisticTie = new Text();
        statisticTie.setText("Ties: ");
        statisticTie.setFont(Font.font("Verdana",15));
        statisticTie.setFill(Color.DARKRED);
        statisticTie.setX(20);
        statisticTie.setY(375);

        statisticRound = new Text();
        statisticRound.setText("Round played: ");
        statisticRound.setFont(Font.font("Verdana",15));
        statisticRound.setFill(Color.DARKRED);
        statisticRound.setX(20);
        statisticRound.setY(300);
        
        playerHand = new Text();
        playerHand.setText("Hand score: ");
        playerHand.setFont(Font.font("Verdana",15));
        playerHand.setFill(Color.DARKRED);
        playerHand.setX(300);
        playerHand.setY(465);
        
        bankerHand = new Text();
        bankerHand.setText("Hand score: ");
        bankerHand.setFont(Font.font("Verdana",15));
        bankerHand.setFill(Color.DARKRED);
        bankerHand.setX(300);
        bankerHand.setY(150);

        root.getChildren().addAll(statisticRound,statisticPlayer,statisticBanker,statisticTie,playerHand,bankerHand);
        
        pause = new PauseTransition(Duration.millis(2000));

        play.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            	//clearing the group everytime the play button is pressed.
                cards.getChildren().clear();

                play();
                
                rounds.set(rounds.getValue()+1);
                roundsT.textProperty().bind(rounds.asString());
                roundsT.setX(130);
                roundsT.setY(300);

                Image p1 = firstP.getImage();
                Image p2 = secondP.getImage();
                Image b1 = firstB.getImage();
                Image b2 = secondB.getImage();

                ImageView player1 = new ImageView(p1);
                ImageView player2 = new ImageView(p2);
                ImageView banker1 = new ImageView(b1);
                ImageView banker2 = new ImageView(b2);

                cards.getChildren().addAll(player1,player2,banker1,banker2);

                TranslateTransition firstPlayerCard = new TranslateTransition(Duration.millis(500),player1);
                player1.setX(360);
                player1.setY(250);
                firstPlayerCard.setToX(-85);
                firstPlayerCard.setToY(240);


                TranslateTransition secondPlayerCard = new TranslateTransition(Duration.millis(500),player2);
                player2.setX(360);
                player2.setY(250);
                secondPlayerCard.setToX(90);
                secondPlayerCard.setToY(240);


                TranslateTransition firstBankerCard = new TranslateTransition(Duration.millis(500), banker1);
                banker1.setX(360);
                banker1.setY(250);
                firstBankerCard.setToX(-85);
                firstBankerCard.setToY(-240);


                TranslateTransition secondBankerCard = new TranslateTransition(Duration.millis(500),banker2);
                banker2.setX(360);
                banker2.setY(250);
                secondBankerCard.setToX(90);
                secondBankerCard.setToY(-240);


                SequentialTransition sequence = new SequentialTransition(firstPlayerCard,firstBankerCard,secondPlayerCard,secondBankerCard);
                
                //stoppig the opportunity to play the button again while ther animation is running. 
                sequence.statusProperty().addListener((obs, oldStatus, newStatus) -> play.setDisable(newStatus == Animation.Status.RUNNING));
                sequence.play();

                if (playerScore <= 5) {

                    thirdP = shoe.deal();
                    player.add(thirdP);
                    Card card  = player.cards.get(player.cards.size() - 1);
                    playerScore = player.value();
                    int last = card.value();
                    Image p3 = thirdP.getImage();
                    ImageView player3 = new ImageView(p3);
                    cards.getChildren().add(player3);

                    thirdPlayerCard = new TranslateTransition(Duration.millis(500),player3);
                    player3.setX(360);
                    player3.setY(250);
                    thirdPlayerCard.setToX(2);
                    thirdPlayerCard.setToY(240);

                    SequentialTransition thirdCardPlayer = new SequentialTransition(pause,thirdPlayerCard);
                    thirdCardPlayer.statusProperty().addListener((obs, oldStatus, newStatus) -> play.setDisable(newStatus == Animation.Status.RUNNING));
                    thirdCardPlayer.play();
                    
                    /*
                     * I putted part of the logic here because otherwise I had a Null pointer error,
                     * infact only if the score is less the the calculation, create the image and do the transition.
                     * 
                     */

                    if (bankerScore <= calculation(last)) {
                        thirdB = shoe.deal();
                        banker.add(thirdB);
                        bankerScore = banker.value();
                        Image b3 = thirdB.getImage();
                        ImageView banker3 = new ImageView(b3);
                        cards.getChildren().add(banker3);

                        thirdBankerCard = new TranslateTransition(Duration.millis(500),banker3);
                        banker3.setX(360);
                        banker3.setY(250);
                        thirdBankerCard.setToX(2);
                        thirdBankerCard.setToY(-240);

                        SequentialTransition thirdCardBanker = new SequentialTransition(pause,thirdBankerCard);
                        thirdCardBanker.statusProperty().addListener((obs, oldStatus, newStatus) -> play.setDisable(newStatus == Animation.Status.RUNNING));
                        thirdCardBanker.play();

                    }

                } else if (bankerScore <= 5) {
                    thirdB = shoe.deal();
                    banker.add(thirdB);
                    bankerScore = banker.value();
                    Image b3 = thirdB.getImage();
                    ImageView banker3 = new ImageView(b3);
                    cards.getChildren().add(banker3);

                    thirdBankerCard = new TranslateTransition(Duration.millis(500),banker3);
                    banker3.setX(360);
                    banker3.setY(250);
                    thirdBankerCard.setToX(2);
                    thirdBankerCard.setToY(-240);

                    SequentialTransition thirdCardBanker = new SequentialTransition(pause,thirdBankerCard);
                    thirdCardBanker.statusProperty().addListener((obs, oldStatus, newStatus) -> play.setDisable(newStatus == Animation.Status.RUNNING));
                    thirdCardBanker.play();
                }

                statistics(playerScore,bankerScore);
                displayHandsScore();
                
                Timeline timeline = new Timeline(new KeyFrame(Duration.millis(2800),ae -> score(playerScore,bankerScore)));
                timeline.play();      
                
                //if the hand is not empty, discard all the cards. This will avoid to play with an infinite shoe.
                if(player.isEmpty() != true){
                    player.discard();
                }
                if(banker.isEmpty() != true){
                    banker.discard();
                }
                
            }
        });
        
        root.getChildren().addAll(roundsT,playerWon,bankerWon,tie,pHandV,bHandV);


        primaryStage.setTitle("Baccarat");
        primaryStage.setScene(scene);
        primaryStage.show();
        //don't allow to resize the scene.
        primaryStage.setResizable(false);
    }

    /*
     * 
     * Function that contains the logic of the game
     * 
     */
    
    public void play(){

        shoe = new Shoe(4);

        shoe.shuffle();

        player = new Hand();
        banker = new Hand();

        firstP = shoe.deal();
        secondP = shoe.deal();
        firstB = shoe.deal();
        secondB = shoe.deal();

        player.add(firstP);
        player.add(secondP);
        banker.add(firstB);
        banker.add(secondB);

        playerScore = player.value();
        bankerScore = banker.value();
    }
    
    /*
     * Function that uses an algorithm to check if a third card is needed 
     */

    public int calculation(int last){

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

        return calculation;
    }
    
    /*
     * Simple function that display who won each round
     * 
     */

    public void score(int playerScore,int bankerScore){
    	
        if(playerScore > bankerScore){
            Text playerW = new Text();
            playerW.setText("Player won!");
            playerW.setFont(Font.font("Verdana",25));
            playerW.setFill(Color.BLACK);
            playerW.setY(430);
            playerW.setX(320);
            cards.getChildren().add(playerW);
            
        }
        else if(bankerScore > playerScore){
            Text bankerW = new Text();
            bankerW.setText("Banker Won!");
            bankerW.setFont(Font.font("Verdana",25));
            bankerW.setFill(Color.BLACK);
            bankerW.setY(180);
            bankerW.setX(320);
            cards.getChildren().add(bankerW);
            
        }

        else if(playerScore == bankerScore){
            Text tieP = new Text();
            Text tieB = new Text();
            tieP.setText("Tie!");
            tieB.setText("Tie!");
            tieP.setFont(Font.font("Verdana",35));
            tieB.setFont(Font.font("Verdana",35));
            tieP.setFill(Color.BLACK);
            tieB.setFill(Color.BLACK);
            tieP.setY(430);
            tieP.setX(360);
            tieB.setY(200);
            tieB.setX(360);
            cards.getChildren().addAll(tieP,tieB);
        }
    }
    
    /*
     * 
     * Function that displays the statistics of each round.
     * 
     */

    private void statistics(int playerScore,int bankerScore) {

        if (playerScore > bankerScore) {
            x.set(x.getValue()+1);
            playerWon.textProperty().bind(x.asString());
            playerWon.setX(130);
            playerWon.setY(325);
        }

        if (bankerScore > playerScore) {
            y.set(y.getValue()+1);
            bankerWon.textProperty().bind(y.asString());
            bankerWon.setX(130);
            bankerWon.setY(350);
        }

        if(playerScore == bankerScore) {
            z.set(y.getValue()+1);
            tie.textProperty().bind(z.asString());
            tie.setX(130);
            tie.setY(375);
        }
    }

    /*
     *Function that displays the score of both hands. 
     */
    
    private void displayHandsScore() {

        playerHandScore.set(playerScore);
        pHandV.textProperty().bind(playerHandScore.asString());
        pHandV.setX(400);
        pHandV.setY(465);

        bankerHandScore.set(bankerScore);
        bHandV.textProperty().bind(bankerHandScore.asString());
        bHandV.setX(400);
        bHandV.setY(150);
    }
}