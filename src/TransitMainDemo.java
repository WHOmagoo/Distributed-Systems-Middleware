//import model.Notifiable;
//import model.Position;
//import model.Vehicle;
//
//import java.io.BufferedInputStream;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.sql.Timestamp;
//import java.util.ArrayList;
//
//public class TransitMainDemo {
//
//
//    private final Vehicle[][] vehicles;
//
//    private ArrayList<Notifiable> toNotify;
//
//    public TransitMainDemo() throws IOException {
//        PropertiesLoader loader = new PropertiesLoader(new BufferedInputStream(new FileInputStream("pub.properties")));
//        vehicles = loader.makeBusSystem();
//
//        toNotify = new ArrayList<Notifiable>();
//
//
//        System.out.println("MessageType\tRoute\t\tVehicle\tTraffic\tStop#\t#Stops\tTimeBetweenStops\tFill%\tTimestamp");
//    }
//
//    public boolean add(Notifiable n){
//        return toNotify.add(n);
//    }
//
//    public boolean remove(Notifiable n){
//        return toNotify.remove(n);
//    }
//
//    public void start(){
//        long currentTimeMilliseconds = 0;
//
//        while(true) {
//            currentTimeMilliseconds += 50;
//
//            for (Vehicle[] routeVehicles : vehicles) {
//
//                for (Vehicle vehicle : routeVehicles) {
//                    Timestamp ts = new Timestamp(currentTimeMilliseconds);
//
////                    Position result = vehicle.move(ts.toString().substring(11).substring(0, 8));
//
//                    if(result != null){
//                        for (Notifiable notify :toNotify) {
//                            notify.notifyObject();
//                        }
//                        System.out.println(result);
//                    }
//                }
//            }
//
//
//            try {
//                Thread.sleep(50);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
