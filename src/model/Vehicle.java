package model;

import position.PubThread;

import java.sql.Timestamp;

public class Vehicle implements Runnable {
    private int startingLocation;
    private Route route;
    private int stopNumber;
    private int curLoopNumber = 0;
    private Position position;
    private PubThread publisher;

    public Vehicle(String name, Route route, int stopNumber) {
        this.route = route;
        this.stopNumber = stopNumber;
        this.startingLocation = stopNumber;

        position = new Position();

        this.position.vehicle = name;
        this.position.route = route.getName();
        this.position.stopNumber = stopNumber;
        this.position.setTimeBetweenStops(route.getTimeBetweenStops());
        this.position.fillInRatio = randomizeFillInRatio();
        this.position.numStops = route.getNumberOfStops();
        generateTrafficCondition();
    }

    private long randomizeFillInRatio() {
        return Math.round(Math.random() * 100);
    }

    public Route getRoute() {
        return route;
    }

    public int getStopNumber() {
        return stopNumber;
    }

    //Moves one second into the future
    public void run(){
        while(true) {
            //Prepare results
//            System.out.println(position.vehicle + " arrived at a stop at " + timeSinceLastStop + " should be" + position.getTimeBetweenStops());
            generateTrafficCondition();
            Position result = new Position();
            result.copy_from(position);
            Timestamp t = new Timestamp(System.currentTimeMillis());

            result.timestamp = t.toString().substring(11, 19);

            stopNumber += 1;

            if (stopNumber > route.getNumberOfStops()) {
                stopNumber = 1;
            }

            if (stopNumber == startingLocation) {
                curLoopNumber++;
                if(curLoopNumber >= 4){
                    break;
                }
            }

            publisher.write(result);
//            System.out.println(result);

            try {
                Thread.sleep((int) (position.getTimeBetweenStops() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Position getPosition(){
        return position;
    }

    private void generateTrafficCondition(){
        double result = Math.random();

        if(result < .25){
            position.setTrafficConditions("light");
            return;
        }

        result -= .25;

        if (result < .1){
            position.setTrafficConditions("heavy");
            return;
        }

        result -= .1;

        if (result < .05){
            position.setTrafficConditions("accident");
            return;
        }

        position.setTrafficConditions("     ");
    }

    public void assignPublisher(PubThread publisher) {
        this.publisher = publisher;
    }
}
