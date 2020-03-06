/*
 * Name: Cole Anam
 * Student ID: 100702841
 * Project: Assignment Question 4
 * Date: March 5, 2020
 */

package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main extends Application {

    // values of reoccurring letters from a - z
    private int count[] = new int[26];

    @Override
    public void start(Stage primaryStage) throws Exception{
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.TOP_LEFT);
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 310, 225, Color.WHITE);

        // canvas for drawing histogram
        Canvas canvas = new Canvas(400, 220);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        primaryStage.setResizable(false);

        // filename text field
        TextField txtFile = new TextField();
        txtFile.setTranslateY(190);

        // letters label
        Label letters = new Label("A B C D E F G H  I  J K L MN O P QR S T U V W X Y Z");
        letters.setTranslateX(10);
        letters.setTranslateY(160);
        gc.strokeLine(15, 169, 289, 169);

        // view Button
        Button viewButton = new Button("View");
        viewButton.setTranslateX(260);
        viewButton.setTranslateY(190);

        // view button event
        viewButton.setOnAction(e -> {
            // get file location from txtFile text field
            File fileLoc = new File(txtFile.getText());

            // scanner instance to read file
            Scanner input = null;
            try {
                input = new Scanner(fileLoc);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }

            // gets contents of file
            String text = "";
            while (input.hasNext()) {
                text = input.next();
            }
            input.close();

            // counter the number of times each letter occurs in the given file
            count(text);

            // draws histogram
            drawGraph(gc);
        });

        pane.setPadding(new Insets(5));
        pane.setHgap(10);
        pane.setVgap(10);

        root.getChildren().add(canvas);
        pane.getChildren().add(letters);
        pane.getChildren().add(txtFile);
        pane.getChildren().add(viewButton);

        primaryStage.setTitle("Assignment_Q4");
        root.setCenter(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // counts the number of times each letter occurs in the given file
    public void count (String text) {
        // loops through every letter in the given file
        for (int i = 0; i < text.length(); i++)
        {
            // checks if the current letter equals a - z and adds 1 to their respective counter
            if (text.charAt(i) == 'a' || text.charAt(i) == 'A') { count[0]++; }
            else if (text.charAt(i) == 'b' || text.charAt(i) == 'B') { count[1]++; }
            else if (text.charAt(i) == 'c' || text.charAt(i) == 'C') { count[2]++; }
            else if (text.charAt(i) == 'd' || text.charAt(i) == 'D') { count[3]++; }
            else if (text.charAt(i) == 'e' || text.charAt(i) == 'E') { count[4]++; }
            else if (text.charAt(i) == 'f' || text.charAt(i) == 'F') { count[5]++; }
            else if (text.charAt(i) == 'g' || text.charAt(i) == 'G') { count[6]++; }
            else if (text.charAt(i) == 'h' || text.charAt(i) == 'H') { count[7]++; }
            else if (text.charAt(i) == 'i' || text.charAt(i) == 'I') { count[8]++; }
            else if (text.charAt(i) == 'j' || text.charAt(i) == 'J') { count[9]++; }
            else if (text.charAt(i) == 'k' || text.charAt(i) == 'K') { count[10]++; }
            else if (text.charAt(i) == 'l' || text.charAt(i) == 'L') { count[11]++; }
            else if (text.charAt(i) == 'm' || text.charAt(i) == 'M') { count[12]++; }
            else if (text.charAt(i) == 'n' || text.charAt(i) == 'N') { count[13]++; }
            else if (text.charAt(i) == 'o' || text.charAt(i) == 'O') { count[14]++; }
            else if (text.charAt(i) == 'p' || text.charAt(i) == 'P') { count[15]++; }
            else if (text.charAt(i) == 'q' || text.charAt(i) == 'Q') { count[16]++; }
            else if (text.charAt(i) == 'r' || text.charAt(i) == 'R') { count[17]++; }
            else if (text.charAt(i) == 's' || text.charAt(i) == 'S') { count[18]++; }
            else if (text.charAt(i) == 't' || text.charAt(i) == 'T') { count[19]++; }
            else if (text.charAt(i) == 'u' || text.charAt(i) == 'U') { count[20]++; }
            else if (text.charAt(i) == 'v' || text.charAt(i) == 'V') { count[21]++; }
            else if (text.charAt(i) == 'w' || text.charAt(i) == 'W') { count[22]++; }
            else if (text.charAt(i) == 'x' || text.charAt(i) == 'X') { count[23]++; }
            else if (text.charAt(i) == 'y' || text.charAt(i) == 'Y') { count[24]++; }
            else if (text.charAt(i) == 'z' || text.charAt(i) == 'Z') { count[25]++; }
        }
    }

    // draws bars of bar chart
    private void drawGraph(GraphicsContext gc) {
        gc.setStroke(Color.BLACK);
        // width of each bar
        double width = 10.592;

        for (int i = 0; i < 26; i++)
        {
            // draws left, top then right lines for every bar
            gc.strokeLine(15 + (i * width), 169, 15 + (i * width), 169 - (count[i] * 15));
            gc.strokeLine(15 + (i * width), 169 - (count[i] * 15), 23 + (i * width), 169 - (count[i] * 15));
            gc.strokeLine(23 + (i * width), 169 - (count[i] * 15), 23 + (i * width), 169);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
