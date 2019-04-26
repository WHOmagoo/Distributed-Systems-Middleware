import model.Route;
import model.Vehicle;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {
    private Properties properties;

    public PropertiesLoader(BufferedInputStream stream) throws IOException {
        properties = new Properties();
        properties.load(stream);
    }

    public Vehicle[][] makeBusSystem(){
        int routeCount = Integer.parseInt(properties.getProperty("numRoutes"));
        int vehicleCount = Integer.parseInt(properties.getProperty("numVehicles"));

        Vehicle[][] vehicles = new Vehicle[routeCount][vehicleCount];

        for (int routeNum = 1; routeNum <= routeCount; routeNum++) {
            String routeName = properties.getProperty("route" + routeNum);

            String stopsCountString = properties.getProperty("route" + routeNum + "numStops");

            int stopsCount = Integer.parseInt(stopsCountString);
            int timeBetweenStops = Integer.parseInt(properties.getProperty("route"+routeNum+"TimeBetweenStops"));

            Route curRoute = new Route(routeName, stopsCount, timeBetweenStops);


            for (int vehicleNum = 1; vehicleNum <= vehicleCount; vehicleNum++) {
                String curVehicleName = properties.getProperty("route"+routeNum+"Vehicle"+vehicleNum);


                Vehicle newVechicle = new Vehicle(curVehicleName, curRoute, (int) (Math.random() * (curRoute.getNumberOfStops() - 1) + 1));

                vehicles[routeNum-1][vehicleNum-1] = newVechicle;
            }

        }

        return vehicles;

    }
}
