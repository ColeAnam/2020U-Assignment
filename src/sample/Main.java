/*
 * Name: Cole Anam
 * Student ID: 100702841
 * Project: Assignment Question 2
 * Date: March 5, 2020
 */

package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.text.DecimalFormat;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        // investment amount label and text field
        Label invAmntL = new Label("Investment Amount");
        TextField invAmntF = new TextField();
        invAmntF.setAlignment(Pos.CENTER_RIGHT);
        // years label and text field
        Label yearsL = new Label("Years");
        TextField yearsF = new TextField();
        yearsF.setAlignment(Pos.CENTER_RIGHT);
        // annual interest rate label and text field
        Label annIntRatL = new Label("Annual Interest Rate");
        TextField annIntRatF = new TextField();
        annIntRatF.setAlignment(Pos.CENTER_RIGHT);
        // future value label
        Label futValL = new Label("Future value");

        GridPane pane = new GridPane();

        // Calculate Button
        Button calButton = new Button("Calculate");
        calButton.setOnAction(e -> {
            // get float values form text fields
            float investmentAmount = Float.parseFloat(invAmntF.getText());
            float years = Float.parseFloat(yearsF.getText())*12;
            float monthlyInterestRate = Float.parseFloat(annIntRatF.getText())/100/12;

            // future value calculation
            double futureValue = (investmentAmount * Math.pow((1 + monthlyInterestRate), (years)));
            // format to 2 decimals and round up
            DecimalFormat df = new DecimalFormat("0.000");
            DecimalFormat dF = new DecimalFormat("0.00");
            Label futValR = new Label("\t\t\t\t" + dF.format(Float.parseFloat(df.format(futureValue))));
            futValR.setAlignment(Pos.CENTER_RIGHT);
            pane.add(futValR, 1, 3);
        });


        pane.setAlignment(Pos.TOP_LEFT);
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 300, 175, Color.WHITE);
        primaryStage.setTitle("Assignment_Q2");

        pane.setPadding(new Insets(5));
        pane.setHgap(10);
        pane.setVgap(10);

        // adds investment amount
        pane.add(invAmntL, 0, 0);
        pane.add(invAmntF, 1, 0);
        // adds years
        pane.add(yearsL, 0, 1);
        pane.add(yearsF, 1, 1);
        // adds annual interest rate
        pane.add(annIntRatL, 0, 2);
        pane.add(annIntRatF, 1, 2);
        // adds future labels
        pane.add(futValL, 0, 3);
        // adds calculate button
        pane.add(calButton, 1, 4);

        root.setCenter(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
