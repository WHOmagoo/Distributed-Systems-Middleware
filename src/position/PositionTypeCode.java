package position;
/*
WARNING: THIS FILE IS AUTO-GENERATED. DO NOT MODIFY.

This file was generated from .idl using "rtiddsgen".
The rtiddsgen tool is part of the RTI Connext distribution.
For more information, type 'rtiddsgen -help' at a command shell
or consult the RTI Connext manual.
*/

import com.rti.dds.typecode.*;

public class PositionTypeCode {
    public static final TypeCode VALUE = getTypeCode();

    private static TypeCode getTypeCode() {
        TypeCode tc = null;
        int __i=0;
        StructMember sm[]=new StructMember[8];

        sm[__i]=new  StructMember("timestamp", false, (short)-1,  false,(TypeCode) new TypeCode(TCKind.TK_STRING,255),0 , false);__i++;
        sm[__i]=new  StructMember("route", false, (short)-1,  false,(TypeCode) new TypeCode(TCKind.TK_STRING,255),1 , false);__i++;
        sm[__i]=new  StructMember("vehicle", false, (short)-1,  false,(TypeCode) new TypeCode(TCKind.TK_STRING,255),2 , false);__i++;
        sm[__i]=new  StructMember("stopNumber", false, (short)-1,  false,(TypeCode) TypeCode.TC_LONG,3 , false);__i++;
        sm[__i]=new  StructMember("numStops", false, (short)-1,  false,(TypeCode) TypeCode.TC_LONG,4 , false);__i++;
        sm[__i]=new  StructMember("timeBetweenStops", false, (short)-1,  false,(TypeCode) TypeCode.TC_LONG,5 , false);__i++;
        sm[__i]=new  StructMember("trafficConditions", false, (short)-1,  false,(TypeCode) new TypeCode(TCKind.TK_STRING,255),6 , false);__i++;
        sm[__i]=new  StructMember("fillInRatio", false, (short)-1,  false,(TypeCode) TypeCode.TC_LONG,7 , false);__i++;

        tc = TypeCodeFactory.TheTypeCodeFactory.create_struct_tc("Position",ExtensibilityKind.EXTENSIBLE_EXTENSIBILITY,  sm);
        return tc;
    }
}

