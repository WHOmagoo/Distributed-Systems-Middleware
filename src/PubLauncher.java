import model.Vehicle;
import position.PubThread;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class PubLauncher {
    private static ArrayList<Thread> vehicleThreads;

    public static void main(String[] args) throws IOException {
        PropertiesLoader loader = new PropertiesLoader(new BufferedInputStream(new FileInputStream("pub.properties")));
        PubThread publisher = new PubThread(0);
        Vehicle[][] vehicles = loader.makeBusSystem();

        vehicleThreads = new ArrayList<>();

        for (Vehicle[] route : vehicles) {
            for(Vehicle vehicle : route){
                vehicle.assignPublisher(publisher);
                vehicleThreads.add(new Thread(vehicle));
            }
        }

        for (Thread t : vehicleThreads){
            t.start();
        }

        for (Thread t : vehicleThreads){
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Finished");
        publisher.shutdown();
    }
}
