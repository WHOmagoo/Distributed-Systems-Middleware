/*
WARNING: THIS FILE IS AUTO-GENERATED. DO NOT MODIFY.
This file was generated from .idl using "rtiddsgen".
The rtiddsgen tool is part of the RTI Connext distribution.
For more information, type 'rtiddsgen -help' at a command shell
or consult the RTI Connext manual.
*/

import com.rti.dds.cdr.CdrHelper;
import com.rti.dds.infrastructure.Copyable;

import java.io.Serializable;

public class Accident implements Copyable, Serializable{
    public String vehicle = "" ; /* maximum length = (256) */
    public String route =  "" ; /* maximum length = (256) */
    public String timestamp = "";
    public long stopNumber = -1;

    public Accident() {
    }
    public Accident(Accident other) {
        this();
        copy_from(other);
    }
    public static Object create() {
        Accident self;
        self = new Accident();
        self.clear();
        return self;
    }
    public void clear() {
        route =  "";
        vehicle = "";
        stopNumber = -1;
        timestamp = "";
    }
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if(getClass() != o.getClass()) {
            return false;
        }
        Accident otherObj = (Accident)o;
        return route.equals(otherObj.route) && vehicle.equals(otherObj.vehicle);
    }
    public int hashCode() {
        int __result = 0;
        __result += route.hashCode();
        __result += vehicle.hashCode();
        return __result;
    }
    public Object copy_from(Object src) {
        Accident typedSrc = (Accident) src;
        Accident typedDst = this;
        typedDst.route = typedSrc.route;
        typedDst.vehicle = typedSrc.vehicle;
        typedDst.timestamp = typedSrc.timestamp;
        typedDst.stopNumber = typedSrc.stopNumber;
        return this;
    }
    public String toString(){
        return toString("", 0);
    }
    public String toString(String desc, int indent) {
        StringBuffer strBuffer = new StringBuffer();
        if (desc != null) {
            CdrHelper.printIndent(strBuffer, indent);
            strBuffer.append(desc).append("BusSystem.Msg object:\n");
            CdrHelper.printIndent(strBuffer, indent+1);
            strBuffer.append("vehicle: ").append(vehicle).append("\n");
            CdrHelper.printIndent(strBuffer, indent+1);
            strBuffer.append("message: ").append(route).append("\n");
            CdrHelper.printIndent(strBuffer, indent+1);
            strBuffer.append("timestamp: ").append(timestamp).append("\n");
            CdrHelper.printIndent(strBuffer, indent+1);
            strBuffer.append("stop number: ").append(stopNumber).append("\n");
        }
        return strBuffer.toString();
    }
}