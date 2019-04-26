package model;

public class Route {
    private String name;
    private int timeBetweenStops;
    private int numberOfStops;

    public Route(String name, int numberOfStops, int timeBetweenStops){
        this.name = name;
        this.numberOfStops = numberOfStops;
        this.timeBetweenStops = timeBetweenStops;
    }

    public String getName() {
        return name;
    }

    public int getTimeBetweenStops() {
        return timeBetweenStops;
    }

    public int getNumberOfStops() {
        return numberOfStops;
    }
}
