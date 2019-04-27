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
        this.stopNumber = stopNumber - 1;
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

    private int randomizeFillInRatio() {
        return (int) Math.round(Math.random() * 100);
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


            Timestamp t = new Timestamp(System.currentTimeMillis());

            position.timestamp = t.toString().substring(11, 19);

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

            position.stopNumber = this.stopNumber;

            publisher.write(position);
            System.out.println(position.vehicle + " published a " + ("accident".equalsIgnoreCase(position.trafficConditions)? "Accident" : "Message") +" at stop " + position.stopNumber + " on route " + route.getNumberOfStops() + " at " + position.getTimestamp());

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
