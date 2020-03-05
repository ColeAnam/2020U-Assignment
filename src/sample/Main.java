/*
* Name: Cole Anam
* Student ID: 100702841
* Project: Assignment Question 1
* Date: March 5, 2020
 */

package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.Random;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        // array of selected image numbers
        int[] selectedCards = new int[3];

        // image number
        int cardNum = 0;

        // gets random number and checks for dupes
        for (int i = 0; i < selectedCards.length; i++) {
            // used for checking for duplicate selectedCards
            boolean dupe = false;
            // creates a random number from 1 - 54
            int randNum = new Random().nextInt(54) + 1;
            // assigns random number to cardNum
            cardNum = randNum;

            // checks for duplicate cards
            if (i != 0)
            {
                // loops for all previous selected cards
                for (int j = 1; j <= i; j++) {
                    if (cardNum == selectedCards[j - 1]) {
                        // goes through loop again
                        i--;
                        dupe = true;
                        break;
                    }
                }
            }

            // checks if there are no dupes
            if (dupe == false)
            {
                // adds cardNum to the selectedCard array
                selectedCards[i] = cardNum;
            }
        }

        // creates 3 imageviews from the 3 numbers in selectedCards
        ImageView card1 = new ImageView(new Image("card/" + selectedCards[0] + ".png"));
        ImageView card2 = new ImageView(new Image("card/" + selectedCards[1] + ".png"));
        ImageView card3 = new ImageView(new Image("card/" + selectedCards[2] + ".png"));

        GridPane pane = new GridPane();
        pane.setAlignment(Pos.TOP_LEFT);
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 622, 317, Color.WHITE);
        primaryStage.setTitle("Assignment_Q1");

        // adds images to pane
        pane.add(card1, 0, 0);
        pane.add(card2, 1, 0);
        pane.add(card3, 2, 0);

        root.setCenter(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}