package model;/*
WARNING: THIS FILE IS AUTO-GENERATED. DO NOT MODIFY.
This file was generated from .idl using "rtiddsgen".
The rtiddsgen tool is part of the RTI Connext distribution.
For more information, type 'rtiddsgen -help' at a command shell
or consult the RTI Connext manual.
*/


import com.rti.dds.infrastructure.Copyable;

import java.io.Serializable;

public class Position implements Copyable, Serializable{
    public String timestamp;
    public String route;
    public String vehicle;
    public long stopNumber;
    public long numStops;
    public long timeBetweenStops;
    public String trafficConditions;
    public long fillInRatio;

    public Position() {
    }
    public Position(Position other) {
        this();
        copy_from(other);
    }
    public static Object create() {
        Position self;
        self = new Position();
        self.clear();
        return self;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getRoute() {
        return route;
    }

    public String getVehicle() {
        return vehicle;
    }

    public long getStopNumber() {
        return stopNumber;
    }

    public long getNumStops(){
        return numStops;
    }

    public double getTimeBetweenStops() {
        if("heavy".equalsIgnoreCase(trafficConditions))
            return timeBetweenStops * 1.5;

        if("light".equalsIgnoreCase(trafficConditions))
            return timeBetweenStops * .75;

        return timeBetweenStops;
    }

    public String getTrafficConditions() {
        return trafficConditions;
    }

    public long getFillInRatio() {
        return fillInRatio;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public void setStopNumber(long stopNumber) {
        this.stopNumber = stopNumber;
    }

    public void setTimeBetweenStops(long timeBetweenStops) {
        this.timeBetweenStops = timeBetweenStops;
    }

    public void setNumStops(long numStops){
        this.numStops = numStops;
    }

    public void setTrafficConditions(String trafficConditions) {
        if(trafficConditions == null){
            trafficConditions = "";
        }
        this.trafficConditions = trafficConditions;
    }

    public void setFillInRatio(long fillInRatio) {
        this.fillInRatio = fillInRatio;
    }

    public void clear() {
        timestamp = "";
        route = "";
        vehicle = "";
        stopNumber = -1;
        timeBetweenStops = -1;
        trafficConditions = "";
        fillInRatio = -1;
    }

    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if(getClass() != o.getClass()) {
            return false;
        }
        Position otherObj = (Position)o;
        return otherObj.route.equalsIgnoreCase(this.route) && this.vehicle.equalsIgnoreCase(otherObj.vehicle);
    }
    public int hashCode() {
        int __result = 0;
//        __result += route.hashCode();
        __result += route.hashCode();
        __result += vehicle.hashCode();
        return __result;
    }
    public Object copy_from(Object src) {
        Position typedSrc = (Position) src;
        Position typedDst = this;
        
        this.timestamp = typedSrc.timestamp;
        this.route = typedSrc.route;
        this.vehicle = typedSrc.vehicle;
        this.stopNumber = typedSrc.stopNumber;
        this.numStops = typedSrc.numStops;
        this.timeBetweenStops = typedSrc.timeBetweenStops;
        this.trafficConditions = typedSrc.trafficConditions;
        this.fillInRatio = typedSrc.fillInRatio;

        return this;
    }
    public String toString(){
        return toString("", 0);
    }
    public String toString(String desc, int indent) {
        StringBuffer strBuffer = new StringBuffer();

        if (desc != null) {
            boolean isAccident = "accident".equalsIgnoreCase(trafficConditions);
            if(isAccident)
                return accidentToString();


            strBuffer.append("Position\t");
            strBuffer.append(route + "\t");
            strBuffer.append(vehicle + "\t");
            strBuffer.append(trafficConditions + "\t");
            strBuffer.append(stopNumber + "\t\t");
            strBuffer.append(numStops + "\t\t");
            strBuffer.append(String.format("%4.2f", getTimeBetweenStops()) + "\t\t\t\t");
            strBuffer.append(fillInRatio + "\t\t");
            strBuffer.append(timestamp);
//            strBuffer.append(desc).append("BusSystem.Msg object:\n");
//
//
//            strBuffer.append("timestamp: ").append(timestamp).append("\t");
//
//            strBuffer.append("strBuffer: ").append(strBuffer).append("\t");
//
//            strBuffer.append("vehicle: ").append(vehicle).append("\t");
//
//            strBuffer.append("stopNumber: ").append(stopNumber).append("\t");
//
//            strBuffer.append("timeBetweenStops: ").append(timeBetweenStops).append("\t");
//
//            strBuffer.append("trafficConditions: ").append(trafficConditions).append("\t");
//
//            strBuffer.append("fillInRatio: ").append(fillInRatio).append("\t");
        }
        return strBuffer.toString();
    }

    private String accidentToString() {
        StringBuffer strBuffer = new StringBuffer();

        strBuffer.append("Accident\t");
        strBuffer.append(route + "\t");
        strBuffer.append(vehicle + "\t");
        strBuffer.append("\t\t");
        strBuffer.append(stopNumber + "\t");
        strBuffer.append("\t\t\t\t\t\t\t\t\t\t");
        strBuffer.append(timestamp);

        return strBuffer.toString();
    }
}