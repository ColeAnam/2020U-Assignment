/*
 * Name: Cole Anam
 * Student ID: 100702841
 * Project: Assignment Question 3
 * Date: March 5, 2020
 */

package sample;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Pane pane = new Pane();
        Scene scene = new Scene(pane, 400, 400);
        primaryStage.setResizable(false);

        // x and y cords and radius of base circle
        double xB = 200;
        double yB = 200;
        double rB = 150;

        // x and y cords of 3 points
        double x[] = new double[3];
        double y[] = new double[3];

        // angles and lengths of connecting lines
        AtomicReference<Double> a = new AtomicReference<>((double) 0);
        AtomicReference<Double> A = new AtomicReference<>((double) 0);
        AtomicReference<Double> b = new AtomicReference<>((double) 0);
        AtomicReference<Double> B = new AtomicReference<>((double) 0);
        AtomicReference<Double> c = new AtomicReference<>((double) 0);
        AtomicReference<Double> C = new AtomicReference<>((double) 0);

        // create base circle
        Circle base = new Circle(xB, yB, rB);
        base.setFill(null);
        base.setStroke(Color.BLACK);

        // get random x and y cords for 3 points
        for (int i = 0; i < 3; i++) {
            // creates a random number from 0 - 360
            double randNum = new Random().nextDouble();
            double angle = 0 + (randNum * (360 - 0));
            // converts random angle into radians
            double radians = Math.toRadians(angle);
            // calculates radians to x and y cords
            x[i] = rB * Math.cos(radians) + xB;
            y[i] = rB * Math.sin(radians) + yB;
        }

        // draw connecting lines
        Line line12 = new Line(x[0], y[0], x[1], y[1]);
        line12.setStrokeWidth(3);
        line12.setStroke(Color.BLACK);

        Line line31 = new Line(x[2], y[2], x[0], y[0]);
        line31.setStrokeWidth(3);
        line31.setStroke(Color.BLACK);

        Line line23 = new Line(x[1], y[1], x[2], y[2]);
        line23.setStrokeWidth(3);
        line23.setStroke(Color.BLACK);


        // create 3 random points
        Circle point1 = new Circle(x[0], y[0], 10);
        point1.setFill(Color.RED);
        point1.setStroke(Color.BLACK);

        Circle point2 = new Circle(x[1], y[1], 10);
        point2.setFill(Color.RED);
        point2.setStroke(Color.BLACK);

        Circle point3 = new Circle(x[2], y[2], 10);
        point3.setFill(Color.RED);
        point3.setStroke(Color.BLACK);


        // calculates angles and lengths of the connecting lines
        a.set(length(x[0], y[0], x[1], y[1]));
        b.set(length(x[2], y[2], x[0], y[0]));
        c.set(length(x[1], y[1], x[2], y[2]));

        A.set(angle(a.get(), b.get(), c.get()));
        B.set(angle(b.get(), a.get(), c.get()));
        C.set(angle(c.get(), b.get(), a.get()));

        // set angle value labels
        DecimalFormat dF = new DecimalFormat("0");
        Label AL = new Label(dF.format(A.get()));
        Label BL = new Label(dF.format(B.get()));
        Label CL = new Label(dF.format(C.get()));
        AL.setTranslateX(x[2] + 10);
        AL.setTranslateY(y[2] + 10);
        CL.setTranslateX(x[0] + 10);
        CL.setTranslateY(y[0] + 10);
        BL.setTranslateX(x[1] + 10);
        BL.setTranslateY(y[1] + 10);


        // mouse click and drag event for point 1
        point1.setOnMouseDragged((MouseEvent me) -> {
            // gets x and y cords of the center of the base circle
            Point2D redCenter = new Point2D(base.getCenterX(), base.getCenterY());
            // gets x and y cords of mouse
            Point2D mouse = new Point2D(me.getX(), me.getY());
            // subtracts cords of mouse from center
            Point2D centerToMouse = mouse.subtract(redCenter);
            Point2D centerToNewPoint = centerToMouse.normalize().multiply(base.getRadius());
            Point2D newPoint = centerToNewPoint.add(redCenter);
            // places point along base circle perimeter
            point1.setCenterX(newPoint.getX());
            point1.setCenterY(newPoint.getY());

            // change x and y cords of connecting lines
            x[0] = newPoint.getX();
            y[0] = newPoint.getY();
            line12.setStartX(x[0]);
            line12.setStartY(y[0]);
            line31.setEndX(x[0]);
            line31.setEndY(y[0]);

            // calculates angles and lengths of the connecting lines
            a.set(length(x[0], y[0], x[1], y[1]));
            b.set(length(x[2], y[2], x[0], y[0]));
            A.set(angle(a.get(), b.get(), c.get()));
            B.set(angle(b.get(), a.get(), c.get()));
            C.set(angle(c.get(), b.get(), a.get()));

            // change angle label
            BL.setText(dF.format(B.get()));
            CL.setText(dF.format(C.get()));
            AL.setText(dF.format(A.get()));
            CL.setTranslateX(x[0] + 10);
            CL.setTranslateY(y[0] + 10);
        });

        // mouse click and drag event for point 2
        point2.setOnMouseDragged((MouseEvent me) -> {
            Point2D redCenter = new Point2D(base.getCenterX(), base.getCenterY());
            Point2D mouse = new Point2D(me.getX(), me.getY());
            Point2D centerToMouse = mouse.subtract(redCenter);
            Point2D centerToNewPoint = centerToMouse.normalize().multiply(base.getRadius());
            Point2D newPoint = centerToNewPoint.add(redCenter);
            point2.setCenterX(newPoint.getX());
            point2.setCenterY(newPoint.getY());

            // change x and y cords of connecting lines
            x[1] = newPoint.getX();
            y[1] = newPoint.getY();
            line23.setStartX(x[1]);
            line23.setStartY(y[1]);
            line12.setEndX(x[1]);
            line12.setEndY(y[1]);

            // calculates angles and lengths of the connecting lines
            c.set(length(x[1], y[1], x[2], y[2]));
            a.set(length(x[0], y[0], x[1], y[1]));
            A.set(angle(a.get(), b.get(), c.get()));
            B.set(angle(b.get(), a.get(), c.get()));
            C.set(angle(c.get(), b.get(), a.get()));

            // change angle label
            CL.setText(dF.format(C.get()));
            AL.setText(dF.format(A.get()));
            BL.setText(dF.format(B.get()));
            BL.setTranslateX(x[1] + 10);
            BL.setTranslateY(y[1] + 10);
        });

        // mouse click and drag event for point 3
        point3.setOnMouseDragged((MouseEvent me) -> {
            Point2D redCenter = new Point2D(base.getCenterX(), base.getCenterY());
            Point2D mouse = new Point2D(me.getX(), me.getY());
            Point2D centerToMouse = mouse.subtract(redCenter);
            Point2D centerToNewPoint = centerToMouse.normalize().multiply(base.getRadius());
            Point2D newPoint = centerToNewPoint.add(redCenter);
            point3.setCenterX(newPoint.getX());
            point3.setCenterY(newPoint.getY());

            // change x and y cords of connecting lines
            x[2] = newPoint.getX();
            y[2] = newPoint.getY();
            line31.setStartX(x[2]);
            line31.setStartY(y[2]);
            line23.setEndX(x[2]);
            line23.setEndY(y[2]);

            // calculates angles and lengths of the connecting lines
            b.set(length(x[2], y[2], x[0], y[0]));
            c.set(length(x[1], y[1], x[2], y[2]));
            A.set(angle(a.get(), b.get(), c.get()));
            B.set(angle(b.get(), a.get(), c.get()));
            C.set(angle(c.get(), b.get(), a.get()));

            // change angle label
            AL.setText(dF.format(A.get()));
            CL.setText(dF.format(C.get()));
            BL.setText(dF.format(B.get()));
            AL.setTranslateX(x[2] + 10);
            AL.setTranslateY(y[2] + 10);
        });


        pane.getChildren().addAll(base, line12, line31, line23, point1, point2, point3, AL, BL, CL);

        primaryStage.setTitle("Assignment_Q3");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    // calculates the length of the connecting line between 2 points
    public double length(double x1, double y1, double x2, double y2) {

        double hyp;
        double adj = 0;
        double opp = 0;

        // calculate lengths depending on position of 2 points
        if (y2 > y1 && x2 > x1)
        {
            adj = y2 - y1;
            opp = x2 - x1;
        }
        else if (y1 > y2 && x2 > x1)
        {
            adj = y1 - y2;
            opp = x2 - x1;
        }
        else if (y2 > y1 && x1 > x2)
        {
            adj = y2 - y1;
            opp = x1 - x2;
        }
        else if (y1 > y2 && x1 > x2)
        {
            adj = y1 - y2;
            opp = x1 - x2;
        }

        // calculate length of connecting line
        hyp = Math.sqrt(Math.pow(adj, 2) + Math.pow(opp, 2));
        return hyp;
    }

    // calculates the angle of the connecting line between two points
    public double angle(double first, double second, double third) {

        double result = ((second * second) + (third * third) - (first * first)) / (2 * second * third);
        double angle = Math.acos(result);
        return Math.toDegrees(angle);
    }

    public static void main(String[] args) {
        launch(args);
    }
}