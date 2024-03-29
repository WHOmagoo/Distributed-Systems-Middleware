

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

public class Accident   implements Copyable, Serializable{

    public String timestamp=  "" ; /* maximum length = (255) */
    public String route=  "" ; /* maximum length = (255) */
    public String vehicle=  "" ; /* maximum length = (255) */
    public int stopNumber= 0;

    public Accident() {

    }
    public Accident (Accident other) {

        this();
        copy_from(other);
    }

    public static Object create() {

        Accident self;
        self = new  Accident();
        self.clear();
        return self;

    }

    public void clear() {

        timestamp=  ""; 
        route=  ""; 
        vehicle=  ""; 
        stopNumber= 0;
    }

    public boolean equals(Object o) {

        if (o == null) {
            return false;
        }        

        if(getClass() != o.getClass()) {
            return false;
        }

        Accident otherObj = (Accident)o;

        if(!timestamp.equals(otherObj.timestamp)) {
            return false;
        }
        if(!route.equals(otherObj.route)) {
            return false;
        }
        if(!vehicle.equals(otherObj.vehicle)) {
            return false;
        }
        if(stopNumber != otherObj.stopNumber) {
            return false;
        }

        return true;
    }

    public int hashCode() {
        int __result = 0;
        __result += timestamp.hashCode(); 
        __result += route.hashCode(); 
        __result += vehicle.hashCode(); 
        __result += (int)stopNumber;
        return __result;
    }

    /**
    * This is the implementation of the <code>Copyable</code> interface.
    * This method will perform a deep copy of <code>src</code>
    * This method could be placed into <code>AccidentTypeSupport</code>
    * rather than here by using the <code>-noCopyable</code> option
    * to rtiddsgen.
    * 
    * @param src The Object which contains the data to be copied.
    * @return Returns <code>this</code>.
    * @exception NullPointerException If <code>src</code> is null.
    * @exception ClassCastException If <code>src</code> is not the 
    * same type as <code>this</code>.
    * @see com.rti.dds.infrastructure.Copyable#copy_from(java.lang.Object)
    */
    public Object copy_from(Object src) {

        Accident typedSrc = (Accident) src;
        Accident typedDst = this;

        typedDst.timestamp = typedSrc.timestamp;
        typedDst.route = typedSrc.route;
        typedDst.vehicle = typedSrc.vehicle;
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
            strBuffer.append(desc).append(":\n");
        }

        CdrHelper.printIndent(strBuffer, indent+1);        
        strBuffer.append("timestamp: ").append(timestamp).append("\n");  
        CdrHelper.printIndent(strBuffer, indent+1);        
        strBuffer.append("route: ").append(route).append("\n");  
        CdrHelper.printIndent(strBuffer, indent+1);        
        strBuffer.append("vehicle: ").append(vehicle).append("\n");  
        CdrHelper.printIndent(strBuffer, indent+1);        
        strBuffer.append("stopNumber: ").append(stopNumber).append("\n");  

        return strBuffer.toString();
    }

}
