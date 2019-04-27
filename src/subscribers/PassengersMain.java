package subscribers;

public class PassengersMain {
    public static void main(String[] args) throws InterruptedException {
        Passenger p1 = new Passenger(0, args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]));
        p1.listen();
    }
}
